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
 * @author Pichau
 */
public class DAOEspecialidades {
    
     public List<Especialidades> getLista(){
        String sql = "select * from especialidades";
        List<Especialidades> listaEspecialidades = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Especialidades objEspecialidades = new Especialidades();
                objEspecialidades.setCodigoEspecialidade(rs.getInt("codigo"));
                objEspecialidades.setNomeEspecialidade(rs.getString("nome"));
                objEspecialidades.setSalarioEspecialidade(rs.getDouble("salario"));
                listaEspecialidades.add(objEspecialidades);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL no getLista do DAOEspecialidades: "+ex.getMessage());
        }
        return listaEspecialidades;
    }
    
   public boolean incluir(Especialidades obj) {
        String sql = "insert into especialidades (nome,salario) values(?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeEspecialidade());
            pst.setDouble(2, obj.getSalarioEspecialidade());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Especialidade incluida");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Especialidade não incluida");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAOEspecialidades" + e.getMessage());

      }
       return false;
    }
   
   public boolean alterar(Especialidades obj) {
        String sql = "update especialidades set nome=?,salario=? where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeEspecialidade());
            pst.setDouble(2, obj.getSalarioEspecialidade());
            pst.setInt(3, obj.getCodigoEspecialidade());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Especialidade alterada");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Especialidade não alterada");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOEspecialidades" + e.getMessage());

      }
       return false;
    }
   
   public boolean remover(Especialidades obj) {
        String sql = "delete from especialidades where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigoEspecialidade());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Especialidade removida");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Especialidade não removida");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no remover do DAOEspecialidades" + e.getMessage());

      }
       return false;
    }
   
   
   public boolean salvar(Especialidades obj) {
        if (obj.getCodigoEspecialidade()== null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }

    }
   
   public Especialidades localizar(Integer id){
        String sql = "select * from especialidades where codigo=?";
        Especialidades obj = new Especialidades();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
        while(rs.next()){
                obj.setCodigoEspecialidade(rs.getInt("codigo"));
                obj.setNomeEspecialidade(rs.getString("nome"));
                obj.setSalarioEspecialidade(rs.getDouble("salario"));
                return obj;
            }
    }catch(SQLException e){
            JOptionPane.showMessageDialog
        (null,"Erro de SQL Localizar"+e.getMessage());
    }
        return null;
    }
}
