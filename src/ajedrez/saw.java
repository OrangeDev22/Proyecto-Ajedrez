/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

import javax.swing.JOptionPane;

/**
 *
 * @author espkh
 */
public class saw {

    Generadores obtener = new Generadores();

    public void main() {
        int numero = obtener.nRandom();
        int contador = 0;
        int num = 0;
        String caracter = obtener.generadorC();
        String adivinar = "";
        String adivinar2 = "";
        String comprobador = "si";
        JOptionPane.showMessageDialog(null, "Ok mira, estas son mis reglas:\n" + "Primero ingresaras un numero del 1-100. si pasas te espera algo mas sino, moriras\n");
        while (contador < 7 && num != numero) {
            contador++;
            System.out.println("(¡TRAMPOSO/A! el numero es: " + numero);
            num = Integer.parseInt(JOptionPane.showInputDialog(null, "ingresa el numero que escogi (solo tienes 7 intentos)"));
            if (contador == 7) {
                JOptionPane.showMessageDialog(null, "HAS MUERTO");
            }
        }
        if (num != numero) {
            comprobador = "no";
        }
        if (comprobador.equals("si")) {
            contador = 0;
            JOptionPane.showMessageDialog(null, "Bien, miro que aun vives para contarlo. Ahora lo que tienes que hacer es adivinar un caracter que he escogido. Recuerda: si fallas moriras");
            while (contador < 5 && !adivinar.equals(caracter)) {
                adivinar = JOptionPane.showInputDialog(null, "¿Cual es el caracter? (solo tienes 5 intentos)");
                System.out.println("(¡TRAMPOSO/A! el caracter es: " + caracter);
                contador++;
                if (contador == 5) {
                    JOptionPane.showMessageDialog(null, "HAS MUERTO");
                }
            }
        }
        if (!adivinar.equals(caracter)) {
            comprobador = "no";
        }
        if (comprobador.equals("si")) {
            adivinar = "";
            contador = 0;
            while (contador < 2 && !adivinar.equals("UN ERROR")) {
                contador = contador + 1;
                JOptionPane.showMessageDialog(null, "Ahora completaras una frase (solo tienes 2 intentos)");
                adivinar2 = JOptionPane.showInputDialog(null, "Si el universo es infinito, entonces la inteligencia humana es....");
                adivinar = adivinar2.toUpperCase();
                if (contador == 2) {
                    JOptionPane.showMessageDialog(null, "HAS MUERTO");
                }

            }
        }

    }
}
