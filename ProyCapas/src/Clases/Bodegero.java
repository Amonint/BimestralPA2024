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
    @NamedQuery(name = "Bodegero.findByLocal", query = "SELECT b FROM Bodegero b WHERE b.local = :local")})
public class Bodegero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bodegero")
    private Integer idBodegero;
    @Column(name = "local")
    private String local;

    public Bodegero() {
    }

    public Bodegero(Integer idBodegero) {
        this.idBodegero = idBodegero;
    }

    public Integer getIdBodegero() {
        return idBodegero;
    }

    public void setIdBodegero(Integer idBodegero) {
        this.idBodegero = idBodegero;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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
    
}
