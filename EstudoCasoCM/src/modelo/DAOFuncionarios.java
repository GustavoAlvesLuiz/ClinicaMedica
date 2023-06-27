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
public class DAOFuncionarios {
    DAOCargos objDAOCargos = new DAOCargos();
   
    public List<Funcionarios> getLista(){
        String sql = "select * from funcionarios";
        List<Funcionarios> listaFuncionarios = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Funcionarios objFuncionarios = new Funcionarios();
                objFuncionarios.setCodigoFuncionario(rs.getInt("codigo"));
                objFuncionarios.setNomeFuncionario(rs.getString("nome"));
                objFuncionarios.setCpfFuncionario(rs.getString("cpf"));
                objFuncionarios.setRgFuncionario(rs.getString("rg"));
                objFuncionarios.setTelefoneFuncionario(rs.getString("telefone"));
                objFuncionarios.setObjCargos(objDAOCargos.localizar(rs.getInt("cargo")));
                listaFuncionarios.add(objFuncionarios);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL no getLista do DAOFuncionarios: "+ex.getMessage());
        }
        return listaFuncionarios;
    }
    
   public boolean incluir(Funcionarios obj) {
        String sql = "insert into funcionarios (nome,cpf,rg,telefone,cargo) values(?,?,?,?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeFuncionario());
            pst.setString(2, obj.getCpfFuncionario());
            pst.setString(3, obj.getRgFuncionario());
            pst.setString(4, obj.getTelefoneFuncionario());
            pst.setInt(5, obj.getObjCargos().getCodigoCargo());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionario incluido");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Funcionario não incluido");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAOFuncionarios" + e.getMessage());

      }
       return false;
    }
   
   public boolean alterar(Funcionarios obj) {
        String sql = "update funcionarios set nome=?,cpf=?,rg=?,telefone=?,cargo=? where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeFuncionario());
            pst.setString(2, obj.getCpfFuncionario());
            pst.setString(3, obj.getRgFuncionario());
            pst.setString(4, obj.getTelefoneFuncionario());
            pst.setInt(5, obj.getObjCargos().getCodigoCargo());
            pst.setInt(6, obj.getCodigoFuncionario());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionario alterado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Funcionario não alterado");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOFuncionarios" + e.getMessage());

      }
       return false;
    }
   
   public boolean remover(Funcionarios obj) {
        String sql = "delete from funcionarios where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigoFuncionario());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionario removido");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Funcionario não removido");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no remover do DAOFuncionarios" + e.getMessage());

      }
       return false;
    }
   
   
   public boolean salvar(Funcionarios obj) {
        if (obj.getCodigoFuncionario()== null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }

    }
}
