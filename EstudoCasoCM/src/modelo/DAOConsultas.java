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
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author 08463037607
 */
public class DAOConsultas {
    
    DAOMedicos objDAOMedicos = new DAOMedicos();
    DAOPacientes objDAOPacientes = new DAOPacientes();
   
    public List<Consultas> getLista(){
        String sql = "select * from consultas";
        List<Consultas> listaConsultas = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Consultas objConsultas = new Consultas();
                objConsultas.setCodigoConsulta(rs.getInt("codigo"));
                java.sql.Date dt = rs.getDate("data");
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                objConsultas.setDataConculta(c);
                objConsultas.setObjMedicos(objDAOMedicos.localizar(rs.getInt("medico")));
                objConsultas.setObjPacientes(objDAOPacientes.localizar(rs.getInt("paciente")));
                objConsultas.setValorConsulta(rs.getDouble("valor"));
                listaConsultas.add(objConsultas);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL no getLista do DAOConsultas: "+ex.getMessage());
        }
        return listaConsultas;
    }
    
   public boolean incluir(Consultas obj) {
        String sql = "insert into consultas (data,medico,paciente,valor) values(?,?,?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
           pst.setDate(1, new java.sql.Date(obj.getDataConculta().getTimeInMillis()));
           pst.setInt(2, obj.getObjMedicos().getCodigoMedico());
           pst.setInt(3, obj.getObjPacientes().getCodigoPaciente());
           pst.setDouble(4, obj.getValorConsulta());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Consulta incluida");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Consulta não incluida");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAOConsultas" + e.getMessage());

      }
       return false;
    }
   
   public boolean alterar(Consultas obj) {
        String sql = "update consultas set data=?,medico=?,paciente=?,valor=? where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
           pst.setDate(1, new java.sql.Date(obj.getDataConculta().getTimeInMillis()));
           pst.setInt(2, obj.getObjMedicos().getCodigoMedico());
           pst.setInt(3, obj.getObjPacientes().getCodigoPaciente());
           pst.setDouble(4, obj.getValorConsulta());
           pst.setInt(5, obj.getCodigoConsulta());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Consulta alterada");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Consulta não alterada");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOConsultas" + e.getMessage());

      }
       return false;
    }
   
   public boolean remover(Consultas obj) {
        String sql = "delete from consultas where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigoConsulta());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Consulta removida");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Consulta não removida");
              return false;
            }
     } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Erro de SQL no remover do DAOConsultas" + e.getMessage());

      }
       return false;
    }
   
   
   public boolean salvar(Consultas obj) {
        if (obj.getCodigoConsulta()== null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }

    }
    
}
