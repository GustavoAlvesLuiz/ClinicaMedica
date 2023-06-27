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
 * @author Pichau
 */
public class Especialidades implements Serializable{
    private Integer codigoEspecialidade;
    private String nomeEspecialidade;
    private Double salarioEspecialidade;

    public Integer getCodigoEspecialidade() {
        return codigoEspecialidade;
    }

    public void setCodigoEspecialidade(Integer codigoEspecialidade) {
        this.codigoEspecialidade = codigoEspecialidade;
    }

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }

    public Double getSalarioEspecialidade() {
        return salarioEspecialidade;
    }

    public void setSalarioEspecialidade(Double salarioEspecialidade) {
        this.salarioEspecialidade = salarioEspecialidade;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.codigoEspecialidade);
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
        final Especialidades other = (Especialidades) obj;
        if (!Objects.equals(this.codigoEspecialidade, other.codigoEspecialidade)) {
            return false;
        }
        return true;
    }
    
     @Override
    public String toString() {
        return nomeEspecialidade;
    }
    
}
