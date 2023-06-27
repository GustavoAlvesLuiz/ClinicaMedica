/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author 08463037607
 */
public class DAOMedicos {
    DAOEspecialidades objDAOEspecialidades = new DAOEspecialidades();
   
    public List<Medicos> getLista(){
        String sql = "select * from medicos";
        List<Medicos> listaMedicos = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Medicos objMedicos = new Medicos();
                objMedicos.setCodigoMedico(rs.getInt("codigo"));
                objMedicos.setNomeMedico(rs.getString("nome"));
                objMedicos.setCrmMedico(rs.getString("crm"));
                objMedicos.setCpfMedico(rs.getString("cpf"));
                objMedicos.setRgMedico(rs.getString("rg"));
                objMedicos.setTelefoneMedico(rs.getString("telefone"));
                objMedicos.setObjEspecialidades(objDAOEspecialidades.localizar(rs.getInt("especialidade")));
                listaMedicos.add(objMedicos);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL no getLista do DAOMedicos: "+ex.getMessage());
        }
        return listaMedicos;
    }
    
   public boolean incluir(Medicos obj) {
        String sql = "insert into medicos (nome,crm,cpf,rg,telefone,especialidade) values(?,?,?,?,?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeMedico());
            pst.setString(2, obj.getCrmMedico());
            pst.setString(3, obj.getCpfMedico());
            pst.setString(4, obj.getRgMedico());
            pst.setString(5, obj.getTelefoneMedico());
            pst.setInt(6, obj.getObjEspecialidades().getCodigoEspecialidade());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Medico incluido");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Medico não incluido");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAOMedicos" + e.getMessage());

      }
       return false;
    }
   
   public boolean alterar(Medicos obj) {
        String sql = "update medicos set nome=?,crm=?,cpf=?,rg=?,telefone=?,especialidade=? where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
           pst.setString(1, obj.getNomeMedico());
            pst.setString(2, obj.getCrmMedico());
            pst.setString(3, obj.getCpfMedico());
            pst.setString(4, obj.getRgMedico());
            pst.setString(5, obj.getTelefoneMedico());
            pst.setInt(6, obj.getObjEspecialidades().getCodigoEspecialidade());
            pst.setInt(7, obj.getCodigoMedico());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Medico alterado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Medico não alterado");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOMedicos" + e.getMessage());

      }
       return false;
    }
   
   public boolean remover(Medicos obj) {
        String sql = "delete from medicos where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigoMedico());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Medico removido");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Medico não removido");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no remover do DAOMedicos" + e.getMessage());

      }
       return false;
    }
   
   
   public boolean salvar(Medicos obj) {
        if (obj.getCodigoMedico()== null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }

    }
   
    public Medicos localizar(Integer id){
        String sql = "select * from medicos where codigo=?";
        Medicos obj = new Medicos();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
        while(rs.next()){
                obj.setCodigoMedico(rs.getInt("codigo"));
                obj.setNomeMedico(rs.getString("nome"));
                obj.setCrmMedico(rs.getString("crm"));
                obj.setCpfMedico(rs.getString("cpf"));
                obj.setRgMedico(rs.getString("rg"));
                obj.setTelefoneMedico(rs.getString("telefone"));
                obj.setObjEspecialidades(objDAOEspecialidades.localizar(rs.getInt("especialidade")));
                return obj;
            }
    }catch(SQLException e){
            JOptionPane.showMessageDialog
        (null,"Erro de SQL Localizar"+e.getMessage());
    }
        return null;
    }
}
