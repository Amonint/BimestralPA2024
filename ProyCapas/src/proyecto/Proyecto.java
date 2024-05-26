/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto;

import Clases.Bodegero;
import Clases.Repartidor;
import static java.lang.Math.random;
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

    public static void main(String[] args) {
        boolean bandera = true;
        do {
            System.out.println("=====MENU=====");
            System.out.println("1. Ingresar Empleado");
            System.out.println("2. Ingresae Cliente");
            System.out.println("3. Listar");
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

                default:
                    throw new AssertionError();

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
}
