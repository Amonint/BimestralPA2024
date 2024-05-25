package Clases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author omerb
 */

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Entrega implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private String observacion;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Repartidor repartidor;

    @OneToOne(mappedBy = "entrega")
    private Paquete paquete;

    public Entrega() {
    }

    public Entrega(Long id, String codigo, Date fecha, String observacion, Cliente cliente, Repartidor repartidor, Paquete paquete) {
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.observacion = observacion;
        this.cliente = cliente;
        this.repartidor = repartidor;
        this.paquete = paquete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    @Override
    public String toString() {
        return "Entrega{" + "id=" + id + ", codigo=" + codigo + ", fecha=" + fecha + ", observacion=" + observacion + ", cliente=" + cliente + ", repartidor=" + repartidor + ", paquete=" + paquete + '}';
    }

   
}

