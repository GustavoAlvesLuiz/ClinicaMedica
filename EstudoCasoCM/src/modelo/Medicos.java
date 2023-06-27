/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author 08463037607
 */
public class Medicos implements Serializable {
    private Integer codigoMedico;
    private String nomeMedico, crmMedico, cpfMedico, rgMedico, telefoneMedico;
    Especialidades objEspecialidades;

    public Integer getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(Integer codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(String crmMedico) {
        this.crmMedico = crmMedico;
    }

    public String getCpfMedico() {
        return cpfMedico;
    }

    public void setCpfMedico(String cpfMedico) {
        this.cpfMedico = cpfMedico;
    }

    public String getRgMedico() {
        return rgMedico;
    }

    public void setRgMedico(String rgMedico) {
        this.rgMedico = rgMedico;
    }

    public String getTelefoneMedico() {
        return telefoneMedico;
    }

    public void setTelefoneMedico(String telefoneMedico) {
        this.telefoneMedico = telefoneMedico;
    }

    public Especialidades getObjEspecialidades() {
        return objEspecialidades;
    }

    public void setObjEspecialidades(Especialidades objEspecialidades) {
        this.objEspecialidades = objEspecialidades;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.codigoMedico);
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
        final Medicos other = (Medicos) obj;
        if (!Objects.equals(this.codigoMedico, other.codigoMedico)) {
            return false;
        }
        return true;
    }
    
     @Override
    public String toString() {
        return nomeMedico;
    }
    
}
