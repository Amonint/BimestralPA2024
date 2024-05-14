/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test1;

import java.util.stream.IntStream;

/**
 *
 * @author sofiv
 */
public class Test1 {

    /**
     * @param args the command line arguments
     */
    private static final IntStream values = IntStream.range(100_000,1_000_000);
    public static void main(String[]args){
        var results = values.filter(Test1::isPrime);
        System.out.printf("Nro primos: %d\n",results.count());}
    
    public static boolean isPrime(int nro){
        if (nro<2)return false;
        if (nro == 2)return true;
        if (nro % 2 == 0)return false;
        for (int i = 2; i < nro; i++) {
            if (nro % i == 0)return false;

        }
        return false;
    }
    

    
}
