package paquemodel;

import Clases.Bodeguero;
import Clases.Cliente;
import Clases.ControladoraPersistencia;
import Clases.Direccion;
import Clases.Entrega;
import Clases.Estado;
import Clases.Paquete;
import Clases.Repartidor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PaqueModel {

    static Scanner sc = new Scanner(System.in);
    static String aux;
    static int op;
    static Bodeguero bod = new Bodeguero();
    static Cliente cli = new Cliente();
    static Repartidor rep = new Repartidor();
    static Direccion dir = new Direccion();
    static Paquete paq = new Paquete();
    static Estado est = new Estado();
    static Date fecha = new Date();
    static List<Direccion> direcciones = new ArrayList<>();
    static List<Estado> estados = new ArrayList<>();
    static ControladoraPersistencia con = new ControladoraPersistencia();

    public static void main(String[] args) throws Exception {
        //Integrantes:
        //Ayala Jimenez Abraham Paul 
        //Benitez Cabrera Omer Alexis 
        //Ojeda Condo Daniel Martin
        //Vire Piedra Paola Sofia

        /* Bodeguero bodG = new Bodeguero("Centro", "Loja", "11111", "Saritama", "Jose", "srmjs@gmail.com");
        con.crearBodeguero(bodG);
        boolean band = true;
        */
        
        System.out.println("=====MENU=====");
        System.out.println("Ingrese su cedula");
        aux = sc.nextLine();
        buscarPersona(aux);
    }

    public static void buscarPersona(String ced) throws Exception {
        cli = con.buscarCliente(ced);
        rep = con.buscarRepartidor(ced);
        bod = con.buscarBodeguero(ced);

        if (cli != null) {
            menuCliente(ced);
        } else if (rep != null) {
            menuRepartidor(ced);
        } else if (bod != null) {
            menuBodeguero(ced);
        } else {
            System.out.println("No existe la cedula");
        }
    }

    public static void listarClientes() {
        System.out.println("=====LISTA CLIENTES=====");
        for (Cliente cliente : con.listarClientes()) {
            System.out.println(cliente);
        }
    }

    public static void listarDirecciones(Cliente cli) {
        System.out.println("=====LISTA DE DIRECCIONES=====");

        ArrayList<Direccion> l = con.listarDireccionesCliente(cli);
        for (Direccion direccion : l) {
            System.out.println(direccion);
        }

    }

    public static void listarPaquetes(String ced) {
        System.out.println("=====LISTA DE PAQUETES=====");
        ArrayList<Paquete> pa = con.listarPaquetesPorPersona(cli);

        for (Paquete paquete : pa) {
            System.out.println(paquete);
        }

    }

    public static void listarEntregas() {
        System.out.println("=====LISTA DE ENTREGAS=====");
        for (Entrega entrega : con.listarEntregas()) {
            System.out.println(entrega);
        }
    }

    private static void menuCliente(String ced) throws Exception {
        boolean bandera = true;
        do {
            System.out.println("=====Bienvenido Cliente=====");
            System.out.println("1. Registrar nueva dirección");
            System.out.println("2. Cambiar estado de dirección");
            System.out.println("3. Revisar paquete");
            System.out.println("4. Listar Paquetes");
            System.out.println("5. Listar Direcciones");
            System.out.println("6. Salir");
            System.out.println("==============");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    registrarNuevaDireccion(cli);
                    break;
                case 2:
                    cambiarEstadoDireccion(ced);
                    break;
                case 3:
                    revisarPaquete();
                    break;
                case 4:
                    listarPaquetes(ced);
                    break;
                case 5:
                    listarDirecciones(cli);
                    break;
                case 6:
                    bandera = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (bandera);
    }

    private static void menuBodeguero(String ced) throws Exception {
        boolean bandera = true;
        do {
            System.out.println("=====Bienvenido Bodeguero=====");
            System.out.println("1. Registrar un nuevo cliente");
            System.out.println("2. Registrar una nueva dirección para un cliente");
            System.out.println("3. Registrar un nuevo paquete");
            System.out.println("4. Despachar un paquete");
            System.out.println("5. Salir");
            System.out.println("==============");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    registrarNuevoCliente();
                    break;
                case 2:
                    registrarNuevaDireccionParaCliente();
                    break;
                case 3:
                    registrarNuevoPaquete();
                    break;
                case 4:
                    despacharPaquete();
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
        boolean bandera = true;
        do {
            System.out.println("=====Bienvenido Repartidor=====");
            System.out.println("1. Registrar una nueva entrega");
            System.out.println("2. Salir");
            System.out.println("==============");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    registrarNuevaEntrega();
                    break;
                case 2:
                    bandera = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (bandera);
    }

    private static void registrarNuevaDireccion(Cliente cli) throws Exception {
        Direccion dirc = new Direccion();
        System.out.println("Ingrese el codigo de la direccion: ");
        dirc.setCodigo(sc.nextLine());
        System.out.println("Ingrese la calle principal:");
        dirc.setCalle1(sc.nextLine());
        System.out.println("Ingrese la calle secundaria:");
        dirc.setCalle2(sc.nextLine());
        System.out.println("Ingrese una referencia:");
        dirc.setReferencia(sc.nextLine());
        dirc.setCliente(cli);
        System.out.println("Desea registrar esta direccion como actual(Si/No):");
        if (sc.nextLine().equalsIgnoreCase("si")) {
            dirc.setActual(true);
        } else {
            dirc.setActual(false);
        }

        con.crearDireccion(dirc);
        cli.getDirecciones().add(dirc);
        con.editarCliente(cli);

    }

    private static void cambiarEstadoDireccion(String ced) throws Exception {
        System.out.println("Ingrese el codigo de la direccion a cambiar");
        String codigoDireccion = sc.nextLine();
        Direccion direccion = con.buscarDireccion(codigoDireccion);

        if (direccion != null) {
            System.out.println(direccion);
            System.out.println("Desea registrar esta direccion como actual(Si/No):");
            if (sc.nextLine().equalsIgnoreCase("si")) {
                direccion.setActual(true);
            } else {
                direccion.setActual(false);
            }
            con.editarDireccion(direccion);
        } else {
            System.out.println("Direccion no encontrada.");
        }
    }

    private static void revisarPaquete() throws Exception {
        System.out.println("Ingrese el codigo del paquete que desea revisar");
        String codigoPaquete = sc.nextLine();
        Paquete paquete = con.buscarPaquete(codigoPaquete);
        if (paquete != null) {
            for (Estado estado : paquete.getEstados()) {
                System.out.println(estado);
            }
        } else {
            System.out.println("Paquete no encontrado.");
        }
    }

    private static void registrarNuevoCliente() throws Exception {
        Cliente clin = new Cliente();
        System.out.println("Ingrese la cedula: ");
        clin.setCedula(sc.nextLine());
        System.out.println("Ingrese los apellidos: ");
        clin.setApellidos(sc.nextLine());
        System.out.println("Ingrese los nombres: ");
        clin.setNombres(sc.nextLine());
        System.out.println("Ingrese el correo: ");
        clin.setMail(sc.nextLine());
        System.out.println("Ingrese el celular: ");
        clin.setCelular(sc.nextLine());
        con.crearCliente(clin);
    }

    private static void registrarNuevaDireccionParaCliente() throws Exception {
        System.out.println("Ingrese la cedula del cliente: ");
        String cedulaCliente = sc.nextLine();
        Cliente cliente = con.buscarCliente(cedulaCliente);
        if (cliente != null) {
            registrarNuevaDireccion(cliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private static void registrarNuevoPaquete() throws Exception {
        System.out.println("Ingrese la cedula del cliente: ");
        String cedulaCliente = sc.nextLine();
        Cliente cliente = con.buscarCliente(cedulaCliente);
        if (cliente != null) {
            Paquete pa = new Paquete();
            System.out.println("Ingrese el codigo del paquete: ");
            pa.setCodigo(sc.nextLine());
            System.out.println("Agregue la descripcion: ");
            pa.setDescripcion(sc.nextLine());
            System.out.println("Ingrese el peso del paquete: ");
            pa.setPeso(sc.nextInt());
            System.out.println("Ingrese el alto del paquete: ");
            pa.setAlto(sc.nextInt());
            sc.nextLine();  // Consumir nueva línea
            pa.setCliente(cliente);  // Asociar el paquete con el cliente
            con.crearPaquete(pa);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private static void despacharPaquete() throws Exception {
        System.out.println("Ingrese el codigo del paquete que desea despachar");
        String codigoPaquete = sc.nextLine();
        Paquete paquete = con.buscarPaquete(codigoPaquete);
        if (paquete != null) {
            Estado estado = new Estado();
            System.out.println("Ingrese las observaciones del paquete");
            estado.setObservacion(sc.nextLine());
            estado.setEstado("Despachado");
            estado.setTipo(2);
            estado.setFecha(new Date());
            estado.setPaquete(paquete);
            con.crearEstado(estado);
        } else {
            System.out.println("Paquete no encontrado.");
        }
    }

    private static void registrarNuevaEntrega() {
        System.out.println("Ingrese el codigo del paquete que desea entregar");
        String codigoPaquete = sc.nextLine();
        Paquete paquete = con.buscarPaquete(codigoPaquete);
        if (paquete != null) {
            Estado estado = new Estado();
            System.out.println("Ingrese las observaciones del paquete");
            estado.setObservacion(sc.nextLine());
            estado.setEstado("Entregado");
            estado.setTipo(3);
            estado.setFecha(new Date());
            estado.setPaquete(paquete);
            con.crearEstado(estado);
        } else {
            System.out.println("Paquete no encontrado.");
        }
    }
}
