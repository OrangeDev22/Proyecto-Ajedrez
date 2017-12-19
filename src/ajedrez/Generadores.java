/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

import java.util.Random;

/**
 *
 * @author espkh
 */
public class Generadores {
    


public int nRandom (){
    int numero;
           Random random = new Random();
        numero = random.nextInt(100)+1;
        return numero;
}
    private char[] arreglo = {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'ñ', 'z', 'x', 'c', 'v', 'b', 'n',
        'm', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'};
 
 
    public char obtenerCaracter() {
        return arreglo[random(arreglo.length)];
    }

    public int random(int lenght) {
        return new Random().nextInt(lenght);
    }

    public String generadorC() {
        boolean corredor = true;
        int contador = 0;
        int maximo = 200;
        String s="";
        while (corredor) {
            StringBuilder sb = new StringBuilder();
            int size = random (25) + random (25);
            for (int i = 0; i < 1; i++) {
            sb.append(obtenerCaracter());
            }
          s =  sb.toString();
                if (contador++ == maximo) {
                    corredor = false;
                    System.out.println("...termino...");
                }
                   break;
        }
        return s;
    }
}


