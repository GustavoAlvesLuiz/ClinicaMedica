/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.beans.Transient;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author 08463037607
 */
public class Consultas implements Serializable{
    private Integer codigoConsulta;
    private Calendar dataConsulta;
    Medicos objMedicos;
    Pacientes objPacientes;
    private Double valorConsulta;

    public Integer getCodigoConsulta() {
        return codigoConsulta;
    }

    public void setCodigoConsulta(Integer codigoConsulta) {
        this.codigoConsulta = codigoConsulta;
    }

    public Calendar getDataConculta() {
        return dataConsulta;
    }

    public void setDataConculta(Calendar dataConculta) {
        this.dataConsulta = dataConculta;
    }

    public Medicos getObjMedicos() {
        return objMedicos;
    }

    public void setObjMedicos(Medicos objMedicos) {
        this.objMedicos = objMedicos;
    }

    public Pacientes getObjPacientes() {
        return objPacientes;
    }

    public void setObjPacientes(Pacientes objPacientes) {
        this.objPacientes = objPacientes;
    }

    public Double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(Double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.codigoConsulta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Consultas other = (Consultas) obj;
        if (!Objects.equals(this.codigoConsulta, other.codigoConsulta)) {
            return false;
        }
        return true;
    }
    
    @Transient // não persistente
    public String getDataFormatada(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataConsulta.getTime());
    }
    
}
