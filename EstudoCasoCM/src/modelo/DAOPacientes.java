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
public class DAOPacientes {
    
      public List<Pacientes> getLista(){
        String sql = "select * from pacientes";
        List<Pacientes> listaPacienteses = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Pacientes objPacientes = new Pacientes();
                objPacientes.setCodigoPaciente(rs.getInt("codigo"));
                objPacientes.setNomePaciente(rs.getString("nome"));
                objPacientes.setRgPaciente(rs.getString("rg"));
                objPacientes.setCpfPaciente(rs.getString("cpf"));
                objPacientes.setTelefonePaciente(rs.getString("telefone"));
                
                listaPacienteses.add(objPacientes);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL no getLista do DAOPacientes: "+ex.getMessage());
        }
        return listaPacienteses;
    }
    
   public boolean incluir(Pacientes obj) {
        String sql = "insert into pacientes (nome,rg,cpf,telefone) values(?,?,?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomePaciente());
            pst.setString(2, obj.getRgPaciente());
            pst.setString(3, obj.getCpfPaciente());
            pst.setString(4, obj.getTelefonePaciente());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Paciente incluido");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Pacinete não incluido");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAOPacientes" + e.getMessage());

      }
       return false;
    }
   
   public boolean alterar(Pacientes obj) {
        String sql = "update pacientes set nome=?,rg=?,cpf=?,telefone=? where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomePaciente());
            pst.setString(2, obj.getRgPaciente());
            pst.setString(3, obj.getCpfPaciente());
            pst.setString(4, obj.getTelefonePaciente());
            pst.setInt(5, obj.getCodigoPaciente());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Paciente alterado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Paciente não alterado");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOPacientes" + e.getMessage());

      }
       return false;
    }
   
   public boolean remover(Pacientes obj) {
        String sql = "delete from pacientes where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigoPaciente());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Paciente removido");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Paciente não removido");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no remover do DAOPacientes" + e.getMessage());

      }
       return false;
    }
   
   
   public boolean salvar(Pacientes obj) {
        if (obj.getCodigoPaciente()== null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }

    }
   
   public Pacientes localizar(Integer id){
        String sql = "select * from pacientes where codigo=?";
        Pacientes obj = new Pacientes();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
        while(rs.next()){
                obj.setCodigoPaciente(rs.getInt("codigo"));
                obj.setNomePaciente(rs.getString("nome"));
                obj.setRgPaciente(rs.getString("rg"));
                obj.setCpfPaciente(rs.getString("cpf"));
                obj.setTelefonePaciente(rs.getString("telefone"));
                return obj;
            }
    }catch(SQLException e){
            JOptionPane.showMessageDialog
        (null,"Erro de SQL Localizar"+e.getMessage());
    }
        return null;
    }
}
