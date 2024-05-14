/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

/**
 *
 * @author sofiv
 */
public class DemoThreads {

    public static void main(String[] args) {
        System.out.printf("%s-ejecutandose\n", Thread.currentThread().getName());
        for (var i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.printf("%s-ejecutandose\n", Thread.currentThread().getName());
             }).start();
 
        }
        
        System.out.printf("Fin del %s\n",Thread.currentThread().getName());
        
    }
}
