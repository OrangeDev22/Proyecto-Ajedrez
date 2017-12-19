/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author espkh
 */
public class Nombres {

    public static String nombre1() {
        String n1 = JOptionPane.showInputDialog(null, "Ingrese el nombre del player1");
        String nt = n1.toUpperCase();
        return nt;

    }

 
    
}
