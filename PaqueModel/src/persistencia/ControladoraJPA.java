/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import Clases.Bodeguero;
import Clases.Cliente;
import Clases.Direccion;
import Clases.Empleado;
import Clases.Entrega;
import Clases.Estado;
import Clases.Paquete;
import Clases.Persona;
import Clases.Repartidor;
import java.util.List;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author omerb
 */
public class ControladoraJPA {

    BodegueroJpaController bodegueroJPA = new BodegueroJpaController();

    // Métodos para delegar las llamadas a los controladores JPA
    // Métodos para operar con la entidad Cliente
    public void crearBodeguero(Bodeguero bodeguero) throws Exception {
        bodegueroJPA.create(bodeguero);
    }

    public void editarBodeguero(Bodeguero bodeguero) throws Exception {
        bodegueroJPA.edit(bodeguero);
    }

    public void eliminarBodeguero(String id) throws Exception {
        bodegueroJPA.destroy(id);
    }

    public List<Bodeguero> listarBodegueros() {
        return bodegueroJPA.findBodegueroEntities();
    }

    public Bodeguero buscarBodeguero(String id) {
        return bodegueroJPA.findBodeguero(id);
    }

    public int contarBodegueros() {
        return bodegueroJPA.getBodegueroCount();
    }
    // Métodos para operar con la entidad Cliente
    ClienteJpaController clienteJPA = new ClienteJpaController();

    public void crearCliente(Cliente cliente) throws Exception {
        clienteJPA.create(cliente);
    }

    public void editarCliente(Cliente cliente) throws Exception {
        clienteJPA.edit(cliente);
    }

    public void eliminarCliente(String id) throws Exception {
        clienteJPA.destroy(id);
    }

    public List<Cliente> listarClientes() {
        return clienteJPA.findClienteEntities();
    }

    public Cliente buscarCliente(String id) {
        return clienteJPA.findCliente(id);
    }

    public int contarClientes() {
        return clienteJPA.getClienteCount();
    }
    // Métodos para operar con la entidad Direccion
    DireccionJpaController direccionJPA = new DireccionJpaController();

    public void crearDireccion(Direccion direccion) {
        direccionJPA.create(direccion);
    }

    public void editarDireccion(Direccion direccion) throws Exception {
        direccionJPA.edit(direccion);
    }

    public void eliminarDireccion(String codigo) throws Exception {
        direccionJPA.destroy(codigo);
    }

    public List<Direccion> listarDirecciones() {
        return direccionJPA.findDireccionEntities();
    }

    public Direccion buscarDireccion(String codigo) {
        return direccionJPA.findDireccion(codigo);
    }

    public int contarDirecciones() {
        return direccionJPA.getDireccionCount();
    }
    // Métodos para operar con la entidad Empleado
    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();

    public void crearEmpleado(Empleado empleado) throws Exception {
        empleadoJPA.create(empleado);
    }

    public void editarEmpleado(Empleado empleado) throws Exception {
        empleadoJPA.edit(empleado);
    }

    public void eliminarEmpleado(String id) throws Exception {
        empleadoJPA.destroy(id);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoJPA.findEmpleadoEntities();
    }

    public Empleado buscarEmpleado(String id) {
        return empleadoJPA.findEmpleado(id);
    }

    public int contarEmpleados() {
        return empleadoJPA.getEmpleadoCount();
    }
    // Métodos para operar con la entidad Entrega
    EntregaJpaController entregaJPA = new EntregaJpaController();

    public void crearEntrega(Entrega entrega) {
        entregaJPA.create(entrega);
    }

    public void editarEntrega(Entrega entrega) throws Exception {
        entregaJPA.edit(entrega);
    }

    public void eliminarEntrega(Long id) throws Exception {
        entregaJPA.destroy(id);
    }

    public List<Entrega> listarEntregas() {
        return entregaJPA.findEntregaEntities();
    }

    public Entrega buscarEntrega(Long id) {
        return entregaJPA.findEntrega(id);
    }

    public int contarEntregas() {
        return entregaJPA.getEntregaCount();
    }
    // Métodos para operar con la entidad Estado
    EstadoJpaController estadoJPA = new EstadoJpaController();

    public void crearEstado(Estado estado) {
        estadoJPA.create(estado);
    }

    public void editarEstado(Estado estado) throws Exception {
        estadoJPA.edit(estado);
    }

    public void eliminarEstado(Long id) throws Exception {
        estadoJPA.destroy(id);
    }

    public List<Estado> listarEstados() {
        return estadoJPA.findEstadoEntities();
    }

    public Estado buscarEstado(Long id) {
        return estadoJPA.findEstado(id);
    }

    public int contarEstados() {
        return estadoJPA.getEstadoCount();
    }
    // Métodos para operar con la entidad Paquete
    PaqueteJpaController paqueteJPA = new PaqueteJpaController();

    public void crearPaquete(Paquete paquete) {
        paqueteJPA.create(paquete);
    }

    public void editarPaquete(Paquete paquete) throws Exception {
        paqueteJPA.edit(paquete);
    }

    public void eliminarPaquete(String codigo) throws Exception {
        paqueteJPA.destroy(codigo);
    }

    public List<Paquete> listarPaquetes() {
        return paqueteJPA.findPaqueteEntities();
    }

    public Paquete buscarPaquete(String codigo) {
        return paqueteJPA.findPaquete(codigo);
    }

    public int contarPaquetes() {
        return paqueteJPA.getPaqueteCount();
    }
    PersonaJpaController personaJPA = new PersonaJpaController();

    public void crearPersona(Persona persona) throws PreexistingEntityException, Exception {
        personaJPA.create(persona);
    }

    public void editarPersona(Persona persona) throws NonexistentEntityException, Exception {
        personaJPA.edit(persona);
    }

    public void eliminarPersona(String id) throws NonexistentEntityException {
        personaJPA.destroy(id);
    }

    public List<Persona> listarPersonas() {
        return personaJPA.findPersonaEntities();
    }

    public Persona buscarPersona(String id) {
        return personaJPA.findPersona(id);
    }

    public int contarPersonas() {
        return personaJPA.getPersonaCount();
    }
    // Métodos para operar con la entidad Repartidor
    RepartidorJpaController repartidorJPA = new RepartidorJpaController();

    public void crearRepartidor(Repartidor repartidor) throws PreexistingEntityException, Exception {
        repartidorJPA.create(repartidor);
    }

    public void editarRepartidor(Repartidor repartidor) throws NonexistentEntityException, Exception {
        repartidorJPA.edit(repartidor);
    }

    public void eliminarRepartidor(String id) throws NonexistentEntityException {
        repartidorJPA.destroy(id);
    }

    public List<Repartidor> listarRepartidores() {
        return repartidorJPA.findRepartidorEntities();
    }

    public Repartidor buscarRepartidor(String id) {
        return repartidorJPA.findRepartidor(id);
    }

    public int contarRepartidores() {
        return repartidorJPA.getRepartidorCount();
    }
}
