/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Usuario
 */
public class CCliente extends CPersona {
    String celular;

    public CCliente(String celular, String cedula, String nombre, String apellido, String email) {
        super(cedula, nombre, apellido, email);
        this.celular = celular;
    }

    public CCliente() {
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "CCliente{" + "celular=" + celular + '}';
    }
    
    
    
}
