/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Usuario
 */
public class CEmpleado extends CPersona{
    int idEmpleado;
    int direccion;

    public CEmpleado() {
    }

    public CEmpleado(int idEmpleado, int direccion, String cedula, String nombre, 
            String apellido, String email) {
        super(cedula, nombre, apellido, email);
        this.idEmpleado = idEmpleado;
        this.direccion = direccion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "CEmpleado{" + "idEmpleado=" + idEmpleado + ", direccion=" + direccion + '}';
    }
    
    
    
}
