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
        System.out.println("Ingrese el empleado ");
        aux = sc.nextLine();
        System.out.println("Ingrese el tipo de empleado ");
        aux = sc.nextLine();
        // metodos crear empleado

        //SubMenu Empleado
        //1. Selecione el empleado a crear 
        //   <1> Bodeguero
        //   <2> Repartidor 
        // 
        switch (op) {
            case 1:
                System.out.println("Ingrese la cedula ");
                aux = sc.nextLine();
                System.out.println("Ingrese el apellido");
                aux = sc.nextLine();
                System.out.println("Ingrese la nombre");
                aux = sc.nextLine();
                System.out.println("Ingrese el correo");
                aux = sc.nextLine();

                crearBodegero(Bodegero bg
                );
                break;

            case 2:
                System.out.println("Ingrese la cedula ");
                aux = sc.nextLine();
                System.out.println("Ingrese el apellido");
                aux = sc.nextLine();
                System.out.println("Ingrese la nombre");
                aux = sc.nextLine();
                System.out.println("Ingrese el correo");
                aux = sc.nextLine();

                crearRepartidor(Repartidor rp
                );
                break;

            default:
                throw new AssertionError();
        }

    }

    public void crearBodegero(Bodegero bg) {
        if (aux == "bodegero") {
            //id = "333" + ramdom.Math; bodegero
            // id = "444" repartidor
            String cod = "333";
            int codigo = Integer.parseInt(cod);
            bg.setIdBodegero(codigo + Math.random());
        }

    }
}