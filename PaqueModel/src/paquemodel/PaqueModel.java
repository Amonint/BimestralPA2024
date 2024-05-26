/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package paquemodel;

import Clases.Bodeguero;
import Clases.Cliente;
import Clases.ControladoraPersistencia;
import Clases.Direccion;

import static Clases.Entrega_.fecha;
import Clases.Estado;
import Clases.Paquete;
import Clases.Repartidor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author omerb
 *
 */
public class PaqueModel {

    /**
     * @param args the command line arguments
     */
    static Scanner sc = new Scanner(System.in);
    static String aux;
    static int num;
    static String ced;
    static int op;
    static Bodeguero bod = new Bodeguero();
    static Cliente cli = new Cliente();

    static Repartidor rep = new Repartidor();
    static Direccion dir = new Direccion();
    static Paquete paq = new Paquete();
    static Estado est = new Estado();
    static Date fecha = new Date();
    static List<Bodeguero> bodegueros = new ArrayList<>();
    static List<Repartidor> repartidores = new ArrayList<>();
    static List<Cliente> clientes = new ArrayList<>();
    static List<Direccion> direcciones = new ArrayList<>();
    static List<Estado> estados = new ArrayList<>();

    static ControladoraPersistencia con = new ControladoraPersistencia();

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Bodeguero bodG = new Bodeguero("Centro", "Loja", "11111", "Saritama", "Jose", "srmjs@gmail.com");
        con.crearBodeguero(bodG);
        boolean band = true;
//    ControladoraPersistencia con = new ControladoraPersistencia();

        System.out.println("=====MENU=====");
        System.out.println(" Ingrese su cedula");
        aux = sc.nextLine();
        buscarPersona(aux);

    }

    public static void buscarPersona(String ced) throws Exception {

        cli = con.buscarCliente(ced);
        rep = con.buscarRepartidor(ced);
        bod = con.buscarBodeguero(ced);

        if (cli != null) {
            menuCliente(ced);
        } else {
            if (rep != null) {
                menuRepartidor(ced);
            } else {
                if (bod != null) {
                    menuBodeguero(ced);
                } else {
                    System.out.println("No existe la cedula");
                }
            }
        }

    }

    public static void listarClientes() {
        System.out.println("=====LISTA CLIENTES=====");
        for (int i = 0; i < clientes.size(); i++) {
            con.listarClientes();

        }
    }

    public static void listarDirecciones(String ced) {
        System.out.println("=====LISTA DE DIRECCIONES=====");

        con.buscarCliente(ced).getDirecciones();
    }

    public static void listarPaquetes(String ced) {
        System.out.println("=====LISTA DE PAQUETES=====");

        con.buscarCliente(ced).getPaquetes();
    }

    public static void listarPaq() {
        System.out.println("=====LISTA DE PAQUETES=====");
        con.listarPaquetes();

    }

    public static void listarEntregas() {
        System.out.println("=====LISTA DE ENTREGAS=====");
        con.listarEntregas();

    }

    private static void menuCliente(String ced) throws Exception {
        Cliente clin = new Cliente();
        Direccion dirc = new Direccion();
        Paquete pa = new Paquete();
        Estado est = new Estado();

        boolean bandera = true;
        do {
            System.out.println("=====Bienvenido =====");
            System.out.println("1. Registrar nueva dirección");
            System.out.println("2. Cambiar estado de dirección");
            System.out.println("3. Revisar paquete");
            System.out.println("4. Salir");
            System.out.println("==============");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.println("Ingrese el codigo de la direccion: ");
                    aux = sc.nextLine();
                    dirc.setCodigo(aux);
                    System.out.println("Ingrese la calle principal:");
                    aux = sc.nextLine();
                    dirc.setCalle1(aux);
                    System.out.println("Ingrese la calle secundaria:");
                    aux = sc.nextLine();
                    dirc.setCalle2(aux);
                    System.out.println("Ingrese una referencia:");
                    aux = sc.nextLine();
                    dirc.setReferencia(aux);
                    System.out.println("Desea registrar esta direccion como actual(Si/No):");
                    aux = sc.nextLine();
                    aux = aux.toLowerCase();
                    if (aux == "si") {
                        dirc.setActual(true);
                    } else if (aux == "no") {
                        dirc.setActual(false);
                    } else {
                        System.out.println("Ingrese una opcion valida");
                    }
                    con.crearDireccion(dirc);
                    direcciones.add(dirc);
                    con.buscarCliente(ced).setDirecciones(direcciones);
                    break;
                case 2:

                    System.out.println("Ingrese el codigo de la direccion a cambiar");
                    aux = sc.nextLine();
                    dirc = con.buscarDireccion(aux);
                    System.out.println("Desea registrar esta direccion como actual(Si/No):");
                    aux = sc.nextLine();
                    aux.toLowerCase();
                    if (aux == "si") {
                        dirc.setActual(true);
                    } else if (aux == "no") {
                        dirc.setActual(false);
                    }
                    con.crearDireccion(dirc);
                    direcciones.add(dirc);
                    con.buscarCliente(ced).setDirecciones(direcciones);

                    break;
                case 3:
                    System.out.println("Ingrese el codigo del paquete que desea revisar");
                    aux = sc.nextLine();
                    pa = con.buscarPaquete(aux);
                    pa.getEstados();
                    break;
                case 4:
                    bandera = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (true);

    }

    private static void menuBodeguero(String ced) throws Exception {
        Cliente clin = new Cliente();
        Direccion dirc = new Direccion();
        Paquete pa = new Paquete();
        Estado est = new Estado();

        boolean bandera = true;

        do {
            System.out.println("=====Bienvenido =====");
            System.out.println("1. Registrar un nuevo cliente");
            System.out.println("2. Registrar una nueva dirección");
            System.out.println("3. Registrar un nuevo paquete");
            System.out.println("4. Despachar un paquete");
            System.out.println("5. Salir");
            System.out.println("==============");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.println("Ingrese la cedula ");
                    aux = sc.nextLine();
                    clin.setCedula(aux);
                    System.out.println("Ingrese los apellidos");
                    aux = sc.nextLine();
                    clin.setApellidos(aux);
                    System.out.println("Ingrese los nombres");
                    aux = sc.nextLine();
                    clin.setNombres(aux);
                    System.out.println("Ingrese el correo");
                    aux = sc.nextLine();
                    clin.setMail(aux);
                    System.out.println("Ingrese el celular");
                    aux = sc.nextLine();
                    clin.setCelular(aux);
                    System.out.println(clin);
                    con.crearCliente(clin);
                    break;
                case 2:
                    System.out.println("Ingrese la cedula del cliente: ");
                    aux = sc.nextLine();
                    cli = con.buscarCliente(aux);
                    if (cli != null) {
                        System.out.println(cli);
                    } else {
                        System.out.println("No existe el usuario");
                    }
                    System.out.println("Ingrese el codigo de la direccion: ");
                    aux = sc.nextLine();
                    dirc.setCodigo(aux);
                    System.out.println("Ingrese la calle principal:");
                    aux = sc.nextLine();
                    dirc.setCalle1(aux);
                    System.out.println("Ingrese la calle secundaria:");
                    aux = sc.nextLine();
                    dirc.setCalle2(aux);
                    System.out.println("Ingrese una referencia:");
                    aux = sc.nextLine();
                    dirc.setReferencia(aux);
                    System.out.println("Desea registrar esta direccion como actual(1.Si/2.No):");
                    aux = sc.nextLine();
                    String op = aux.toUpperCase();
                    if (op.matches("SI")) {
                        dirc.setActual(true);
                    } else if (op.matches("NO")) {
                        dirc.setActual(false);
                    } else {
                        System.out.println("Ingrese una opcion valida");
                    }
                    direcciones.add(dirc);
                    cli.setDirecciones(direcciones);
                    break;

                case 3:
                    System.out.println("Ingrese el codigo del paquete");
                    aux = sc.nextLine();
                    pa.setCodigo(aux);
                    System.out.println("Agregue la descripcion:");
                    aux = sc.nextLine();
                    pa.setDescripcion(aux);
                    System.out.println("Ingrese el peso del paquete");
                    num = sc.nextInt();
                    pa.setPeso(num);
                    System.out.println("Ingrese el alto del paquete");
                    num = sc.nextInt();
                    pa.setAlto(num);
                    pa.setEstados(estados);
                    con.crearPaquete(pa);
                    break;
                case 4:
                    System.out.println("Ingrese el codigo del paquete que desea despachar");
                    aux = sc.nextLine();
                    paq = con.buscarPaquete(aux);
                    System.out.println("Ingrese las observaciones del paquete");
                    aux = sc.nextLine();
                    est.setObservacion(aux);
                    est.setEstado("Despachado");
                    est.setTipo(2);
                    est.setFecha(fecha);
                    est.setPaquete(paq);
                    con.crearEstado(est);
                    break;
                case 5:
                    bandera = false;

                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (bandera);

    }

    private static void menuRepartidor(String ced) {
        Paquete pa = new Paquete();
        Estado est = new Estado();
        boolean bandera = true;
        do {
            System.out.println("=====Bienvenido =====");
            System.out.println("1. Registrar una nueva entrega");
            System.out.println("2. Salir");
            System.out.println("==============");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.println("Ingrese el codigo del paquete que desea entregar");
                    aux = sc.nextLine();
                    paq = con.buscarPaquete(aux);
                    System.out.println("Ingrese las observaciones del paquete");
                    aux = sc.nextLine();
                    est.setObservacion(aux);
                    est.setEstado("Entregado");
                    est.setTipo(3);
                    est.setFecha(fecha);
                    est.setPaquete(paq);
                    con.crearEstado(est);
                    break;
                case 2:
                    bandera = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (true);
    }

}
