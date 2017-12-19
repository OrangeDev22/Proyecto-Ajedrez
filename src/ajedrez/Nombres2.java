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
public class Nombres2 {
       public static String nombre2() {

        String n2 = JOptionPane.showInputDialog(null, "Ingrese el nombre del player2");
                String nt = n2.toUpperCase();
        return nt;
        
    }
}
