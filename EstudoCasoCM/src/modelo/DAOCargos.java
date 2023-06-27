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
public class DAOCargos {
    
     public List<Cargos> getLista(){
        String sql = "select * from cargos";
        List<Cargos> listaCargos = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Cargos objCargos = new Cargos();
                objCargos.setCodigoCargo(rs.getInt("codigo"));
                objCargos.setNomeCargo(rs.getString("nome"));
                objCargos.setValorSalario(rs.getDouble("salario"));
                listaCargos.add(objCargos);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL no getLista do DAOCargos: "+ex.getMessage());
        }
        return listaCargos;
    }
    
   public boolean incluir(Cargos obj) {
        String sql = "insert into cargos (nome,salario) values(?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeCargo());
            pst.setDouble(2, obj.getValorSalario());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cargo incluido");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cargo não incluido");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAOCargos" + e.getMessage());

      }
       return false;
    }
   
   public boolean alterar(Cargos obj) {
        String sql = "update cargos set nome=?,salario=? where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeCargo());
            pst.setDouble(2, obj.getValorSalario());
            pst.setInt(3, obj.getCodigoCargo());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cargo alterado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cargo não alterado");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOCargos" + e.getMessage());

      }
       return false;
    }
   
   public boolean remover(Cargos obj) {
        String sql = "delete from cargos where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigoCargo());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cargo removido");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cargo não removido");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no remover do DAOCargos" + e.getMessage());

      }
       return false;
    }
   
   
   public boolean salvar(Cargos obj) {
        if (obj.getCodigoCargo()== null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }

    }
   
   public Cargos localizar(Integer id){
        String sql = "select * from cargos where codigo=?";
        Cargos obj = new Cargos();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
        while(rs.next()){
                obj.setCodigoCargo(rs.getInt("codigo"));
                obj.setNomeCargo(rs.getString("nome"));
                obj.setValorSalario(rs.getDouble("salario"));
                return obj;
            }
    }catch(SQLException e){
            JOptionPane.showMessageDialog
        (null,"Erro de SQL Localizar"+e.getMessage());
    }
        return null;
    }

}
