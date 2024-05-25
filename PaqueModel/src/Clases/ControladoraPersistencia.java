/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.List;
import persistencia.ControladoraJPA;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

public class ControladoraPersistencia {

    ControladoraJPA controJPA = new ControladoraJPA();

    // Métodos para operar con la entidad Bodeguero
    public void crearBodeguero(Bodeguero bodeguero) throws Exception {
        controJPA.crearBodeguero(bodeguero);
    }

    public void editarBodeguero(Bodeguero bodeguero) throws Exception {
        controJPA.editarBodeguero(bodeguero);
    }

    public void eliminarBodeguero(String id) throws Exception {
        controJPA.eliminarBodeguero(id);
    }

    public List<Bodeguero> listarBodegueros() {
        return controJPA.listarBodegueros();
    }

    public Bodeguero buscarBodeguero(String id) {
        return controJPA.buscarBodeguero(id);
    }

    public int contarBodegueros() {
        return controJPA.contarBodegueros();
    }
    // Métodos para operar con la entidad Cliente

    public void crearCliente(Cliente cliente) throws Exception {
        controJPA.crearCliente(cliente);
    }

    public void editarCliente(Cliente cliente) throws Exception {
        controJPA.editarCliente(cliente);
    }

    public void eliminarCliente(String id) throws Exception {
        controJPA.eliminarCliente(id);
    }

    public List<Cliente> listarClientes() {
        return controJPA.listarClientes();
    }

    public Cliente buscarCliente(String id) {
        return controJPA.buscarCliente(id);
    }

    public int contarClientes() {
        return controJPA.contarClientes();
    }
    // Métodos para operar con la entidad Direccion

    public void crearDireccion(Direccion direccion) {
        controJPA.crearDireccion(direccion);
    }

    public void editarDireccion(Direccion direccion) throws Exception {
        controJPA.editarDireccion(direccion);
    }

    public void eliminarDireccion(Long id) throws Exception {
        controJPA.eliminarDireccion(id);
    }

    public List<Direccion> listarDirecciones() {
        return controJPA.listarDirecciones();
    }

    public Direccion buscarDireccion(Long id) {
        return controJPA.buscarDireccion(id);
    }

    public int contarDirecciones() {
        return controJPA.contarDirecciones();
    }
    // Métodos para operar con la entidad Empleado

    public void crearEmpleado(Empleado empleado) throws Exception {
        controJPA.crearEmpleado(empleado);
    }

    public void editarEmpleado(Empleado empleado) throws Exception {
        controJPA.editarEmpleado(empleado);
    }

    public void eliminarEmpleado(String id) throws Exception {
        controJPA.eliminarEmpleado(id);
    }

    public List<Empleado> listarEmpleados() {
        return controJPA.listarEmpleados();
    }

    public Empleado buscarEmpleado(String id) {
        return controJPA.buscarEmpleado(id);
    }

    public int contarEmpleados() {
        return controJPA.contarEmpleados();
    }
    // Métodos para operar con la entidad Entrega

    public void crearEntrega(Entrega entrega) {
        controJPA.crearEntrega(entrega);
    }

    public void editarEntrega(Entrega entrega) throws Exception {
        controJPA.editarEntrega(entrega);
    }

    public void eliminarEntrega(Long id) throws Exception {
        controJPA.eliminarEntrega(id);
    }

    public List<Entrega> listarEntregas() {
        return controJPA.listarEntregas();
    }

    public Entrega buscarEntrega(Long id) {
        return controJPA.buscarEntrega(id);
    }

    public int contarEntregas() {
        return controJPA.contarEntregas();
    }
    // Métodos para operar con la entidad Estado

    public void crearEstado(Estado estado) {
        controJPA.crearEstado(estado);
    }

    public void editarEstado(Estado estado) throws Exception {
        controJPA.editarEstado(estado);
    }

    public void eliminarEstado(Long id) throws Exception {
        controJPA.eliminarEstado(id);
    }

    public List<Estado> listarEstados() {
        return controJPA.listarEstados();
    }

    public Estado buscarEstado(Long id) {
        return controJPA.buscarEstado(id);
    }

    public int contarEstados() {
        return controJPA.contarEstados();
    }
    // Métodos para operar con la entidad Paquete

    public void crearPaquete(Paquete paquete) {
        controJPA.crearPaquete(paquete);
    }

    public void editarPaquete(Paquete paquete) throws Exception {
        controJPA.editarPaquete(paquete);
    }

    public void eliminarPaquete(int id) throws Exception {
        controJPA.eliminarPaquete(id);
    }

    public List<Paquete> listarPaquetes() {
        return controJPA.listarPaquetes();
    }

    public Paquete buscarPaquete(int id) {
        return controJPA.buscarPaquete(id);
    }

    public int contarPaquetes() {
        return controJPA.contarPaquetes();
    }
    // Métodos para operar con la entidad Persona

    public void crearPersona(Persona persona) throws PreexistingEntityException, Exception {
        controJPA.crearPersona(persona);
    }

    public void editarPersona(Persona persona) throws NonexistentEntityException, Exception {
        controJPA.editarPersona(persona);
    }

    public void eliminarPersona(String id) throws NonexistentEntityException {
        controJPA.eliminarPersona(id);
    }

    public List<Persona> listarPersonas() {
        return controJPA.listarPersonas();
    }

    public Persona buscarPersona(String id) {
        return controJPA.buscarPersona(id);
    }

    public int contarPersonas() {
        return controJPA.contarPersonas();
    }
    // Métodos para operar con la entidad Repartidor

    public void crearRepartidor(Repartidor repartidor) throws PreexistingEntityException, Exception {
        controJPA.crearRepartidor(repartidor);
    }

    public void editarRepartidor(Repartidor repartidor) throws NonexistentEntityException, Exception {
        controJPA.editarRepartidor(repartidor);
    }

    public void eliminarRepartidor(String id) throws NonexistentEntityException {
        controJPA.eliminarRepartidor(id);
    }

    public List<Repartidor> listarRepartidores() {
        return controJPA.listarRepartidores();
    }

    public Repartidor buscarRepartidor(String id) {
        return controJPA.buscarRepartidor(id);
    }

    public int contarRepartidores() {
        return controJPA.contarRepartidores();
    }
}
