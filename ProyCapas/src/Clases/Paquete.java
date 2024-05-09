/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author sofiv
 */
@Entity
@Table(name = "paquete")
@NamedQueries({
    @NamedQuery(name = "Paquete.findAll", query = "SELECT p FROM Paquete p"),
    @NamedQuery(name = "Paquete.findByIdPaquete", query = "SELECT p FROM Paquete p WHERE p.idPaquete = :idPaquete"),
    @NamedQuery(name = "Paquete.findByCodigo", query = "SELECT p FROM Paquete p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Paquete.findByDescripcion", query = "SELECT p FROM Paquete p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Paquete.findByPeso", query = "SELECT p FROM Paquete p WHERE p.peso = :peso"),
    @NamedQuery(name = "Paquete.findByAlto", query = "SELECT p FROM Paquete p WHERE p.alto = :alto")})
public class Paquete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_paquete")
    private Integer idPaquete;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "peso")
    private Integer peso;
    @Column(name = "alto")
    private Integer alto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pkPaquete")
    private Collection<Estado> estadoCollection;
    @JoinColumn(name = "pk_entrega", referencedColumnName = "id_entrega")
    @ManyToOne(optional = false)
    private Entrega pkEntrega;

    public Paquete() {
    }

    public Paquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getAlto() {
        return alto;
    }

    public void setAlto(Integer alto) {
        this.alto = alto;
    }

    public Collection<Estado> getEstadoCollection() {
        return estadoCollection;
    }

    public void setEstadoCollection(Collection<Estado> estadoCollection) {
        this.estadoCollection = estadoCollection;
    }

    public Entrega getPkEntrega() {
        return pkEntrega;
    }

    public void setPkEntrega(Entrega pkEntrega) {
        this.pkEntrega = pkEntrega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaquete != null ? idPaquete.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paquete)) {
            return false;
        }
        Paquete other = (Paquete) object;
        if ((this.idPaquete == null && other.idPaquete != null) || (this.idPaquete != null && !this.idPaquete.equals(other.idPaquete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Paquete[ idPaquete=" + idPaquete + " ]";
    }
    
}
