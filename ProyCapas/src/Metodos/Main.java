/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodos;

import Clases.Bodegero;
import Clases.Repartidor;
import java.util.Scanner;

/**
 *
 * @author sofiv
 */
public class Main {

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
            System.out.println("==============");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Sellecione el tipo de empleado a crear: ");
                    System.out.println("<1> Bodeguero");
                    System.out.println("<2> Repartidor");
                    int opc = sc.nextInt();
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
                            crearBodegero(Bodegero objbg
                            );
                            break;
                        case 2:
                            System.out.println("Creando Empleado Repartidor");
                            System.out.println("Ingrese la cedula ");
                            aux = sc.nextLine();
                            System.out.println("Ingrese el apellido");
                            aux = sc.nextLine();
                            System.out.println("Ingrese la nombre");
                            aux = sc.nextLine();
                            System.out.println("Ingrese el correo");
                            aux = sc.nextLine();
                            break;
                    }

                case 2:
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

        /*
    public void crearBodegero(Bodegero bg) {
        if (aux == "bodegero") {
            id = "333" + ramdom.Math;
            bodegero id = "444" repartidor String cod = "333";
            int codigo = Integer.parseInt(cod);
            bg.setIdBodegero(codigo + Math.random());
        }
         */
    }

}
