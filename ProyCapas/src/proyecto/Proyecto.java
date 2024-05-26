/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto;

import Clases.Bodegero;
import Clases.Cliente;
import Clases.Repartidor;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Abraham
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    static Scanner sc = new Scanner(System.in);
    static String aux;
    static int op;
    static Bodegero objbg = new Bodegero();
    static Repartidor objrep = new Repartidor();
    static Cliente objcli = new Cliente();
    static List<Bodegero> bodegueros = new ArrayList<>();
    static List<Repartidor> repartidores = new ArrayList<>();
    static List<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        boolean bandera = true;
        do {
            System.out.println("=====MENU=====");
            System.out.println("1. Ingresar Empleado");
            System.out.println("2. Ingresae Cliente");
            System.out.println("3. Listar Empleados y Clientes");
            System.out.println("4. Listar");
            System.out.println("5. Salir");
            System.out.println("==============");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.println("Sellecione el tipo de empleado a crear: ");
                    System.out.println("<1> Bodeguero");
                    System.out.println("<2> Repartidor");
                    int opc = sc.nextInt();
                    sc.nextLine();
                    switch (opc) {
                        case 1:
                            System.out.println("Creando Empleado Bodeguero");
                            System.out.println("Ingrese la cedula ");
                            aux = sc.nextLine();
                            System.out.println("Ingrese el apellido");
                            aux = sc.nextLine();
                            System.out.println("Ingrese la nombre");
                            aux = sc.nextLine();
                            System.out.println("Ingrese el correo");
                            aux = sc.nextLine();
                            Bodegero bg = new Bodegero(); // Supone que hay un constructor adecuado
                            crearBodegero(objbg);
                            bodegueros.add(bg);
                        break;
                        case 2:
                            System.out.println("Creando Empleado Repartidor");
                            aux = sc.nextLine();
                            System.out.println("Ingrese la cedula ");
                            aux = sc.nextLine();
                            aux = sc.nextLine();
                            System.out.println("Ingrese el apellido");
                            aux = sc.nextLine();
                            System.out.println("Ingrese la nombre");
                            aux = sc.nextLine();
                            System.out.println("Ingrese el correo");
                            aux = sc.nextLine();
                            Repartidor rep = new Repartidor();
                            crearRepartidor(objrep);
                            repartidores.add(rep);
                            
                        break;
                    }

                case 3:
                    System.out.println("CREANDO UN CLIENTE");
                    System.out.println("Ingrese la cedula ");
                    aux = sc.nextLine();
                    System.out.println("Ingrese el apellido");
                    aux = sc.nextLine();
                    System.out.println("Ingrese la nombre");
                    aux = sc.nextLine();
                    System.out.println("Ingrese el correo");
                    aux = sc.nextLine(); 
                    clientes.add(objcli);

                case 4:
                    listarEmpleadosYClientes();
                    break;
                case 5:
                    bandera = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;

            }
        } while (true);
        
    }
    
    public static void crearBodegero(Bodegero bg) {
        Random random = new Random();
        int idNum = 333 + random.nextInt(1000);
        String id = Integer.toString(idNum);
        bg.setIdBodegero(id);
        System.out.println("Bodegero creado con ID: " + id);
    }
    
    public static void crearRepartidor(Repartidor objrep) {
        Random random = new Random();
        int idNum = 333 + random.nextInt(1000);
        String id = Integer.toString(idNum);
        objrep.setIdRepartidor(id);
        System.out.println("Bodegero creado con ID: " + id);
    }
    
    public static void listarEmpleadosYClientes() {
        System.out.println("=====LISTA DE EMPLEADOS Y CLIENTES=====");
        System.out.println("Bodegueros:");
        for (Bodegero b : bodegueros) {
            System.out.println("ID: " + b.getIdBodegero() + ", Nombre: " + 
                    b.getNombre() + " " + b.getApellido() + ", Cédula: " +
                    b.getCedula() + ", Correo: " + b.getCorreo());
        }
        System.out.println("Repartidores:");
        for (Repartidor r : repartidores) {
            System.out.println("Nombre: " + r.getNombre() + " " + r.getApellido() 
                    + ", Cédula: " + r.getCedula() + ", Correo: " + r.getCorreo());
        }
        System.out.println("Clientes:");
        for (Cliente c : clientes) {
            System.out.println("Nombre: " + c.getNombre() + " " + c.getApellido() +
                    ", Cédula: " + c.getCedula() + ", Correo: " + c.getCorreo());
        }
    }
}
