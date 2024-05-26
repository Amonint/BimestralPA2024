/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author sofiv
 */
@Entity
@Table(name = "bodegero")
@NamedQueries({
    @NamedQuery(name = "Bodegero.findAll", query = "SELECT b FROM Bodegero b"),
    @NamedQuery(name = "Bodegero.findByIdBodegero", query = "SELECT b FROM Bodegero b WHERE b.idBodegero = :idBodegero"),
    @NamedQuery(name = "Bodegero.findByLocal", query = "SELECT b FROM Bodegero b WHERE b.local = :local"),
    @NamedQuery(name = "Bodegero.findByTipo", query = "SELECT b FROM Bodegero b WHERE b.tipo = :tipo")})
public class Bodegero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bodegero")
    private String idBodegero;
    @Column(name = "local")
    private String local;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;

    public Bodegero() {
    }

    public Bodegero(String idBodegero) {
        this.idBodegero = idBodegero;
    }

    public Bodegero(String idBodegero, String tipo) {
        this.idBodegero = idBodegero;
        this.tipo = tipo;
    }

    public String getIdBodegero() {
        return idBodegero;
    }

    public void setIdBodegero(String idBodegero) {
        this.idBodegero = idBodegero;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBodegero != null ? idBodegero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bodegero)) {
            return false;
        }
        Bodegero other = (Bodegero) object;
        if ((this.idBodegero == null && other.idBodegero != null) || (this.idBodegero != null && !this.idBodegero.equals(other.idBodegero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Bodegero[ idBodegero=" + idBodegero + " ]";
    }

    public String getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getApellido() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getCedula() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getCorreo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
