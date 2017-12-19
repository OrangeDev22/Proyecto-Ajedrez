package ajedrez;

import java.util.Arrays;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.Image;
import java.awt.Toolkit;

public class Ajedrez {
    //piezas blanca = mayusculas
    //piezas negras = minusculas
    //torres = t/T
    // alfiles = a/A
    //caballos = c/C
    // reina(queen)=q/Q
    //peones = p/P
    // espacios vacios = " "

    static String tableroAjedrez[][] = {//matriz que contiene los valores del tablero
        {"t", "c", "a", "q", "r", "a", "c", "t"},
        {"p", "p", "p", "p", "p", "p", "p", "p"},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {"P", "P", "P", "P", "P", "P", "P", "P"},
        {"T", "C", "A", "Q", "R", "A", "C", "T"}};
    static int posicionReyM, posicionReyL;//variables para buscar las posiciones de los reyes
    static int contadorT;//contador que indica las cantidades de movimientos que se han hecho
    static String comprobador1, comprobador2;// variables que sirven para saber si la lista de cada equipo esta vacia

    public static void main(String[] args) {

        while (!"R".equals(tableroAjedrez[posicionReyM / 8][posicionReyM % 8])) {
            posicionReyM++;
        }//busca la posicion del rey blanco
        while (!"r".equals(tableroAjedrez[posicionReyL / 8][posicionReyL % 8])) {
            posicionReyL++;
        }//busca la posicion del rey negro

        interfaz2 menu = new interfaz2();//llama al jframe que contiene el menu
        menu.setVisible(true);
        System.out.println("Empiezan las piezas blancas"); //dice quien empieza 
        for (int i = 0; i < 8; i++) {//imprime la matriz normalmente y solo una vez
            System.out.println(Arrays.toString(tableroAjedrez[i]));

        }

        System.out.println("------------------------");// esto es solo para separar los tableros a imprimir

    }
public static String[] piezas(String piezas){
    String [] piezasDeboradas = new String [32];
    
    for (int i = 0; i < piezasDeboradas.length; i++) {
     
      
        
    }
    return piezasDeboradas;
}
    public static void hacerMovimiento(String mover) { //Este metodo se encarga de recibir las cordenadas de cada pieza y moverlas

        contadorT = contadorT + 1;//aqui se suman las veces que se han hecho movimientos
        tableroAjedrez[Character.getNumericValue(mover.charAt(2))][Character.getNumericValue(mover.charAt(3))] = tableroAjedrez[Character.getNumericValue(mover.charAt(0))][Character.getNumericValue(mover.charAt(1))];
        tableroAjedrez[Character.getNumericValue(mover.charAt(0))][Character.getNumericValue(mover.charAt(1))] = " ";//obtienen los valores numeros de la lista (cordenada) para mover las piezas
        comprobador1 = posiblesMovimientos();//al primer comprobador se le asigna la lista de las piezas blancas
        comprobador2 = posiblesMovimientosL();//al segundo la lista de las negras
        for (int i = 0; i < 8; i++) {//este ciclo imprime la matriz cada vez que se mueve una pieza
            System.out.println(Arrays.toString(tableroAjedrez[i]));
        }
        if (kingSafe() == false && contadorT % 2 == 0) {// comprueba si el rey blanco esta en jaque
            JOptionPane.showMessageDialog(null, "!EL REY BLANCO ESTA EN JAQUE!");
        }
        if (kingSafeL() == false && contadorT % 2 != 0) {// comprueba si el rey negro esta en jaque
            JOptionPane.showMessageDialog(null, "!EL REY NEGRO ESTA EN JAQUE!");
        }
        System.out.println("------------------------");//separa las matrices 
        //indica a quienes les toca mover
        if (contadorT % 2 == 0) {
            System.out.print("Turno piezas blancas");
            System.out.println("");
        }
        if (contadorT % 2 != 0) {
            System.out.print("Turno piezas negras");
            System.out.println("");
        }
        // estos dos if comprueban si hay un jaque mate
        if (comprobador2.isEmpty() && kingSafeL() == false) {
            JOptionPane.showMessageDialog(null, "!EL JUEGO HA TERMINADO JAQUEMATE A REY NEGRO!");
        }
        if (comprobador1.isEmpty() && kingSafe() == false) {
            JOptionPane.showMessageDialog(null, "!EL JUEGO HA TERMINADO JAQUEMATE A REY BLANCO!");

        }
        // los siguientes souts afectan el rendimiento del juego 
        //System.out.println("los posibles movimientos (cordenadas) a realizar de las piezas blancas son: ");
        // System.out.print(posiblesMovimientos());
        //System.out.println("los posibles movimientos (cordenadas) a realizar de las piezas negras son: "); ////////////esto hace que el programa a a la larga falle. 
         //System.out.print(posiblesMovimientos());
        // no creo que haga falta explicar. solo muestra lo anteriormente dicho
        System.out.print("\n");
        System.out.println("El total de movimientos realizados es: ");
        System.out.printf("%d", contadorT);
        System.out.println("");

    }
    // este metodo sirve para revertir una sola jugada
    public static void revertir(String mover) {
//funciona igual al metodo anterior pero al reves excepto el for
        tableroAjedrez[Character.getNumericValue(mover.charAt(0))][Character.getNumericValue(mover.charAt(1))] = tableroAjedrez[Character.getNumericValue(mover.charAt(2))][Character.getNumericValue(mover.charAt(3))];
        tableroAjedrez[Character.getNumericValue(mover.charAt(2))][Character.getNumericValue(mover.charAt(3))] = " ";
        contadorT = contadorT - 1;
        for (int i = 0; i < 8; i++) {
            System.out.println(Arrays.toString(tableroAjedrez[i]));

        }
    }
//este metodo obtiene con una lista las cordenadas de las piezas y los lleva al metodo "hacerMovimiento" o al metodo "revertir"
    public static String posiblesMovimientos() { 
        String lista = "";//lita de los posibles movimientos
        for (int i = 0; i < 64; i++) {
            // identifica los caracteres en el arreglo y ejecuta los metodos de cada uno
            if ("P".equals(tableroAjedrez[i / 8][i % 8])) {
                lista += posibleP(i);
            } else if ("T".equals(tableroAjedrez[i / 8][i % 8])) {
                lista += posibleT(i);
            } else if ("A".equals(tableroAjedrez[i / 8][i % 8])) {
                lista += posibleA(i);
            } else if ("C".equals(tableroAjedrez[i / 8][i % 8])) {
                lista += posibleC(i);
            } else if ("Q".equals(tableroAjedrez[i / 8][i % 8])) {
                lista += posibleQ(i);
            } else if ("R".equals(tableroAjedrez[i / 8][i % 8])) {
                lista += posibleR(i);
            }
        }
        return lista;//x1,y1,x2,y2 piezas capturadas
    }
// funcional igual que el metodo anterior pero solo con las minusculas (piezas negras)
    public static String posiblesMovimientosL() {
        String lista = "";//lita de los posibles movimientos
        if (contadorT % 2 != 0) {
            for (int i = 0; i < 64; i++) {
                if ("p".equals(tableroAjedrez[i / 8][i % 8])) {
                    lista += posiblePL(i);

                } else if ("t".equals(tableroAjedrez[i / 8][i % 8])) {
                    lista += posibleTL(i);
                } else if ("a".equals(tableroAjedrez[i / 8][i % 8])) {
                    lista += posibleAL(i);
                } else if ("c".equals(tableroAjedrez[i / 8][i % 8])) {
                    lista += posibleCL(i);
                } else if ("q".equals(tableroAjedrez[i / 8][i % 8])) {
                    lista += posibleQL(i);

                } else if ("r".equals(tableroAjedrez[i / 8][i % 8])) {
                    lista += posibleRL(i);
                }
            }
        }

        return lista;
    }
    ///////////////////////   PIEZAS BLANCAS  /////////////////////
//EL METODO KINGSAFE COMPRUEBA SI EL REY ESTA SEGURO. SI LO ESTA ENTONCES SE AGREGAN VALORES A LA VISTA 
    //peon
    public static String posibleP(int i) {
        String lista = "", piezaAnterior;
        int r = i / 8, c = i % 8;
        for (int j = -1; j <= 1; j += 2) {
            try {//capturar
                if (Character.isLowerCase(tableroAjedrez[r - 1][c + j].charAt(0)) && i >= 8 && tableroAjedrez[r - 1][c + j] != "r") {
                    piezaAnterior = tableroAjedrez[r - 1][c + j];
                    tableroAjedrez[r][c] = " ";
                    tableroAjedrez[r - 1][c + j] = "P";
                    if (kingSafe()) {
                        //columna1, columna2,captura-pieza, new-pieza,P
                        lista = lista + r + c + (r - 1) + (c + j) + piezaAnterior;
                    }
                    tableroAjedrez[r][c] = "P";
                    tableroAjedrez[r - 1][c + j] = piezaAnterior;
                }
            } catch (Exception e) {
            }

            //mover arriba
        }
        try {//mover arriba 1
            if (" ".equals(tableroAjedrez[r - 1][c]) && i >= 8) {
                piezaAnterior = tableroAjedrez[r - 1][c];
                tableroAjedrez[r][c] = " ";
                tableroAjedrez[r - 1][c] = "P";
                if (kingSafe()) {
                    lista = lista + r + c + (r - 1) + c + piezaAnterior;
                }
                tableroAjedrez[r][c] = "P";
                tableroAjedrez[r - 1][c] = piezaAnterior;
            }
        } catch (Exception e) {
        }
        try {//ascender(Cambiar de pieza)
            if (i < 8) {

                JOptionPane.showMessageDialog(null, "Ingrese la pieza a remplazar\n" + "Q-(reina)\n" + "A-(Alfil)\n" + "C-(Caballo)\n" + "T-(Torre)\n");
                String remplazar = JOptionPane.showInputDialog(null, "ingrese la pieza aqui");

                tableroAjedrez[r][c] = remplazar;

            }
        } catch (Exception e) {
        }
        //mover dos espacios (solo al inicio)
        try {
            if (" ".equals(tableroAjedrez[r - 1][c]) && " ".equals(tableroAjedrez[r - 2][c]) && i >= 48) {

                {
                    piezaAnterior = tableroAjedrez[r - 2][c];
                    tableroAjedrez[r][c] = " ";
                    tableroAjedrez[r - 2][c] = "P";
                    if (kingSafe()) {
                        lista = lista + r + c + (r - 2) + c + piezaAnterior;
                    }
                }

                tableroAjedrez[r][c] = "P";
                tableroAjedrez[r - 2][c] = piezaAnterior;

            }
        } catch (Exception e) {
        }

        return lista;
    }

    //torre
    public static String posibleT(int i) {
        String lista = "", piezaAnterior;
        int temp = 1;
        int r = i / 8, c = i % 8;
        for (int j = -1; j <= 1; j += 2) {
            //movimientos horizontales
            try {
                while (" ".equals(tableroAjedrez[r][c + temp * j])|| Character.isLowerCase(tableroAjedrez[r][c + temp * j].charAt(0))) {
                    piezaAnterior = tableroAjedrez[r][c + temp * j];
                    tableroAjedrez[r][c] = " ";
                    tableroAjedrez[r][c + temp * j] = "T";
                    if (kingSafe()) {
                        lista = lista + r + c + r + (c + temp * j) + piezaAnterior;
                    }
                    tableroAjedrez[r][c] = "T";
                    tableroAjedrez[r][c + temp * j] = piezaAnterior;

                    temp++;
                }
         
            } catch (Exception e) {
            }
            temp = 1;
            //movimientos verticales
            try {
                while (" ".equals(tableroAjedrez[r + temp * j][c]) || Character.isLowerCase(tableroAjedrez[r + temp * j][c].charAt(0))) {
                    piezaAnterior = tableroAjedrez[r + temp * j][c];
                    tableroAjedrez[r][c] = " ";
                    tableroAjedrez[r + temp * j][c] = "T";
                    if (kingSafe()) {
                        lista = lista + r + c + (r + temp * j) + c + piezaAnterior;
                    }
                    tableroAjedrez[r][c] = "T";
                    tableroAjedrez[r + temp * j][c] = piezaAnterior;
                    temp++;

                }
         
            } catch (Exception e) {
            }
            temp = 1;
         
        }
        return lista;
    }

    //alfil
    public static String posibleA(int i) {

        String lista = "", piezaAnterior;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j += 2) {
            for (int k = -1; k <= 1; k += 2) {
                if (j != 0 || k != 0) {

                    try {
                        //gemera los movimientos del alfil
                        while (" ".equals(tableroAjedrez[r + temp * j][c + temp * k])) {
                            piezaAnterior = tableroAjedrez[r + temp * j][c + temp * k];
                            tableroAjedrez[r][c] = " ";
                            tableroAjedrez[r + temp * j][c + temp * k] = "A";
                            if (kingSafe()) {
                                lista = lista + r + c + (r + temp * j) + (c + temp * k) + piezaAnterior;
                            }

                            tableroAjedrez[r][c] = "A";
                            tableroAjedrez[r + temp * j][c + temp * k] = piezaAnterior;
                            temp++;
                        } //captura caracteres en minuscula 
                        if (Character.isLowerCase(tableroAjedrez[r + temp * j][c + temp * k].charAt(0))) {
                            piezaAnterior = tableroAjedrez[r + temp * j][c + temp * k];
                            tableroAjedrez[r][c] = " ";
                            tableroAjedrez[r + temp * j][c + temp * k] = "A";
                            if (kingSafe()) {
                                lista = lista + r + c + (r + temp * j) + (c + temp * k) + piezaAnterior;
                            }
                            tableroAjedrez[r][c] = "P";
                            tableroAjedrez[r + temp * j][c + temp * k] = piezaAnterior;

                        }
                    } catch (Exception e) {
                    }
                
                    temp = 1;
                }
            }
        }

        return lista;
    }
    
    // caballo 
    //funciona casi igual que el peon
    public static String posibleC(int i) {
        String lista = "", piezaAnterior;
        int r = i / 8, c = i % 8;

        for (int j = -1; j <= 1; j += 2) {
            for (int k = -1; k <= 1; k += 2) {
                try {
                    if (Character.isLowerCase(tableroAjedrez[r + j][c + k * 2].charAt(0)) || " ".equals(tableroAjedrez[r + j][c + k * 2])) {
                        piezaAnterior = tableroAjedrez[r + j][c + k * 2];
                        tableroAjedrez[r][c] = " ";
                        tableroAjedrez[r + j][c + k * 2] = "C";
                        if (kingSafe()) {
                            lista = lista + r + c + (r + j) + (c + k * 2) + piezaAnterior;
                        }
                        tableroAjedrez[r][c] = "C";
                        tableroAjedrez[r + j][c + k * 2] = piezaAnterior;

                    }
                } catch (Exception e) {
                }
                try {
                    if (Character.isLowerCase(tableroAjedrez[r + j * 2][c + k].charAt(0)) || " ".equals(tableroAjedrez[r + j * 2][c + k])) {
                        piezaAnterior = tableroAjedrez[r + j * 2][c + k];
                        tableroAjedrez[r][c] = " ";
                        tableroAjedrez[r + j * 2][c + k] = "C";
                        if (kingSafe()) {
                            lista = lista + r + c + (r + j * 2) + (c + k) + piezaAnterior;
                        }
                        tableroAjedrez[r][c] = "C";
                        tableroAjedrez[r + j * 2][c + k] = piezaAnterior;

                    }
                } catch (Exception e) {
                }

            }
        }
        return lista;
    }
    
    //reina

    public static String posibleQ(int i) {
        String lista = "", piezaAnterior;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
                try {
                    // los movimientos de la reina
                    while (" ".equals(tableroAjedrez[r + temp * j][c + temp * k])) {
                        piezaAnterior = tableroAjedrez[r + temp * j][c + temp * k];
                        tableroAjedrez[r][c] = " ";
                        tableroAjedrez[r + temp * j][c + temp * k] = "Q";
                        if (kingSafe()) {
                            lista = lista + r + c + (r + temp * j) + (c + temp * k) + piezaAnterior;
                        }
                        tableroAjedrez[r][c] = "Q";
                        tableroAjedrez[r + temp * j][c + temp * k] = piezaAnterior;
                        temp++;
                    }
                    //captura
                    if (Character.isLowerCase(tableroAjedrez[r + temp * j][c + temp * k].charAt(0))) {
                        piezaAnterior = tableroAjedrez[r + temp * j][c + temp * k];
                        tableroAjedrez[r][c] = " ";
                        tableroAjedrez[r + temp * j][c + temp * k] = "Q";
                        if (kingSafe()) {
                            lista = lista + r + c + (r + temp * j) + (c + temp * k) + piezaAnterior;
                        }
                        tableroAjedrez[r][c] = "Q";
                        tableroAjedrez[r + temp * j][c + temp * k] = piezaAnterior;

                    }

                } catch (Exception e) {
                }
                temp = 1;

            }

        }

        return lista;
    }
    //rey
// funciona casi igual que la reina
    public static String posibleR(int i) {
        String lista = "", piezaAnterior;
        int r = i / 8, c = i % 8;

        for (int j = 0; j < 9; j++) {
            if (j != 4) {
                try {
                    if (true) {

                    }
                    if (Character.isLowerCase(tableroAjedrez[r - 1 + j / 3][c - 1 + j % 3].charAt(0)) || " ".equals(tableroAjedrez[r - 1 + j / 3][c - 1 + j % 3])) {
                        piezaAnterior = tableroAjedrez[r - 1 + j / 3][c - 1 + j % 3];
                        tableroAjedrez[r][c] = " ";
                        tableroAjedrez[r - 1 + j / 3][c - 1 + j % 3] = "R";

                        int kingTemp = posicionReyM;
                        posicionReyM = i + ((j / 3) * 8) + (j % 3) - 9;
                        if (kingSafe()) {
                            lista = lista + r + c + (r - 1 + j / 3) + (c - 1 + j % 3) + piezaAnterior;
                        }
                        tableroAjedrez[r][c] = "R";
                        tableroAjedrez[r - 1 + j / 3][c - 1 + j % 3] = piezaAnterior;
                        posicionReyM = i;
                    }

                } catch (Exception e) {
                }
            }

        }

        return lista;

    }

    ///////////////////////  PIEZAS NEGRAS ///////////////////////////////
    // todas las piezas negras (exceptuando al peon) son un copy paste de las blancas
    //peon
    public static String posiblePL(int i) {
        String lista = "", piezaAnterior;
        int r = i / 8, c = i % 8;
        for (int j = -1; j <= 1; j += 2) {
            try {//capturar
                if (Character.isUpperCase(tableroAjedrez[r + 1][c + j].charAt(0)) && i >= 8 && i < 56) {
                    piezaAnterior = tableroAjedrez[r + 1][c + j];
                    tableroAjedrez[r][c] = " ";
                    tableroAjedrez[r + 1][c + j] = "p";
                    if (kingSafeL()) {
                        //columna1, columna2,captura-pieza, new-pieza,P
                        lista = lista + r + c + (r + 1) + (c + j) + piezaAnterior;
                    }
                    tableroAjedrez[r][c] = "p";
                    tableroAjedrez[r + 1][c + j] = piezaAnterior;
                }
            } catch (Exception e) {
            }

        }
        try {//mover arriba 1
            if (" ".equals(tableroAjedrez[r + 1][c]) && i >= 8 && i < 48) {
                piezaAnterior = tableroAjedrez[r + 1][c];
                tableroAjedrez[r][c] = " ";
                tableroAjedrez[r + 1][c] = "p";
                if (kingSafeL()) {
                    lista = lista + r + c + (r + 1) + c + piezaAnterior;
                }
                tableroAjedrez[r][c] = "p";
                tableroAjedrez[r + 1][c] = piezaAnterior;
            }
        } catch (Exception e) {
        }
        try {//ascender(Cambiar de pieza)
            if (i >= 56) {

                JOptionPane.showMessageDialog(null, "Ingrese la pieza a remplazar\n" + "q-reina)\n" + "a-(Alfil)\n" + "c-(Caballo)\n" + "t-(Torre)\n");
                String remplazar = JOptionPane.showInputDialog(null, "ingrese la pieza aqui");

                tableroAjedrez[r][c] = remplazar;
                contadorT = contadorT - 1;

            }
        } catch (Exception e) {
        }
        try {
            if (" ".equals(tableroAjedrez[r + 1][c]) && " ".equals(tableroAjedrez[r + 2][c]) && i <= 16) {
                piezaAnterior = tableroAjedrez[r + 2][c];
                tableroAjedrez[r][c] = " ";
                tableroAjedrez[r + 2][c] = "p";
                if (kingSafeL()) {
                    lista = lista + r + c + (r + 2) + c + piezaAnterior;
                }
                tableroAjedrez[r][c] = "p";
                tableroAjedrez[r + 2][c] = piezaAnterior;
            }
        } catch (Exception e) {
        }

        return lista;
    }

    // torre
    public static String posibleTL(int i) {
        String lista = "", piezaAnterior;
        int temp = 1;
        int r = i / 8, c = i % 8;
        for (int j = -1; j <= 1; j += 2) {
            try {
                while (" ".equals(tableroAjedrez[r][c + temp * j])) {
                    piezaAnterior = tableroAjedrez[r][c + temp * j];
                    tableroAjedrez[r][c] = " ";
                    tableroAjedrez[r][c + temp * j] = "t";
                    if (kingSafeL()) {
                        lista = lista + r + c + r + (c + temp * j) + piezaAnterior;
                    }
                    tableroAjedrez[r][c] = "t";
                    tableroAjedrez[r][c + temp * j] = piezaAnterior;

                    temp++;

                }

                if (Character.isUpperCase(tableroAjedrez[r][c + temp * j].charAt(0))) {
                    piezaAnterior = tableroAjedrez[r][c + temp * j];
                    tableroAjedrez[r][c] = " ";
                    tableroAjedrez[r][c + temp * j] = "t";
                    if (kingSafeL()) {
                        lista = lista + r + c + r + (c + temp * j) + piezaAnterior;

                    }
                    tableroAjedrez[r][c] = "t";
                    tableroAjedrez[r][c + temp * j] = piezaAnterior;

                }

            } catch (Exception e) {
            }
            temp = 1;
            try {
                while (" ".equals(tableroAjedrez[r + temp * j][c])) {
                    piezaAnterior = tableroAjedrez[r + temp * j][c];
                    tableroAjedrez[r][c] = " ";
                    tableroAjedrez[r + temp * j][c] = "t";
                    if (kingSafeL()) {
                        lista = lista + r + c + (r + temp * j) + c + piezaAnterior;
                    }
                    tableroAjedrez[r][c] = "t";
                    tableroAjedrez[r + temp * j][c] = piezaAnterior;
                    temp++;

                }
                if (Character.isUpperCase(tableroAjedrez[r + temp * j][c].charAt(0))) {
                    piezaAnterior = tableroAjedrez[r + temp * j][c];
                    tableroAjedrez[r][c] = " ";
                    tableroAjedrez[r + temp * j][c] = "t";
                    if (kingSafeL()) {
                        lista = lista + r + c + (r + temp * j) + c + piezaAnterior;
                    }
                    tableroAjedrez[r][c] = "t";
                    tableroAjedrez[r + temp * j][c] = piezaAnterior;

                }
            } catch (Exception e) {
            }
            temp = 1;

        }
        return lista;
    }

    //alfil
    public static String posibleAL(int i) {

        String lista = "", piezaAnterior;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j += 2) {
            for (int k = -1; k <= 1; k += 2) {
                if (j != 0 || k != 0) {

                    try {
                        while (" ".equals(tableroAjedrez[r + temp * j][c + temp * k])) {
                            piezaAnterior = tableroAjedrez[r + temp * j][c + temp * k];
                            tableroAjedrez[r][c] = " ";
                            tableroAjedrez[r + temp * j][c + temp * k] = "a";
                            if (kingSafeL()) {
                                lista = lista + r + c + (r + temp * j) + (c + temp * k) + piezaAnterior;
                            }

                            tableroAjedrez[r][c] = "a";
                            tableroAjedrez[r + temp * j][c + temp * k] = piezaAnterior;
                            temp++;
                        }
                        if (Character.isUpperCase(tableroAjedrez[r + temp * j][c + temp * k].charAt(0))) {
                            piezaAnterior = tableroAjedrez[r + temp * j][c + temp * k];
                            tableroAjedrez[r][c] = " ";
                            tableroAjedrez[r + temp * j][c + temp * k] = "a";
                            if (kingSafeL()) {
                                lista = lista + r + c + (r + temp * j) + (c + temp * k) + piezaAnterior;
                            }
                            tableroAjedrez[r][c] = "a";
                            tableroAjedrez[r + temp * j][c + temp * k] = piezaAnterior;

                        }
                    } catch (Exception e) {
                    }
                    temp = 1;
                }
            }
        }

        return lista;
    }

    //caballo
    public static String posibleCL(int i) {
        String lista = "", piezaAnterior;
        int r = i / 8, c = i % 8;

        for (int j = -1; j <= 1; j += 2) {
            for (int k = -1; k <= 1; k += 2) {
                try {
                    if (Character.isUpperCase(tableroAjedrez[r + j][c + k * 2].charAt(0)) || " ".equals(tableroAjedrez[r + j][c + k * 2])) {
                        piezaAnterior = tableroAjedrez[r + j][c + k * 2];
                        tableroAjedrez[r][c] = " ";
                        tableroAjedrez[r + j][c + k * 2] = "c";
                        if (kingSafeL()) {
                            lista = lista + r + c + (r + j) + (c + k * 2) + piezaAnterior;
                        }
                        tableroAjedrez[r][c] = "c";
                        tableroAjedrez[r + j][c + k * 2] = piezaAnterior;

                    }
                } catch (Exception e) {
                }
                try {
                    if (Character.isUpperCase(tableroAjedrez[r + j * 2][c + k].charAt(0)) || " ".equals(tableroAjedrez[r + j * 2][c + k])) {
                        piezaAnterior = tableroAjedrez[r + j * 2][c + k];
                        tableroAjedrez[r][c] = " ";
                        tableroAjedrez[r + j * 2][c + k] = "c";
                        if (kingSafeL()) {
                            lista = lista + r + c + (r + j * 2) + (c + k) + piezaAnterior;
                        }
                        tableroAjedrez[r][c] = "c";
                        tableroAjedrez[r + j * 2][c + k] = piezaAnterior;

                    }
                } catch (Exception e) {
                }

            }
        }
        return lista;
    }

    //reina
    public static String posibleQL(int i) {
        String lista = "", piezaAnterior;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
                try {
                    while (" ".equals(tableroAjedrez[r + temp * j][c + temp * k])) {
                        piezaAnterior = tableroAjedrez[r + temp * j][c + temp * k];
                        tableroAjedrez[r][c] = " ";
                        tableroAjedrez[r + temp * j][c + temp * k] = "q";
                        if (kingSafeL()) {
                            lista = lista + r + c + (r + temp * j) + (c + temp * k) + piezaAnterior;
                        }
                        tableroAjedrez[r][c] = "q";
                        tableroAjedrez[r + temp * j][c + temp * k] = piezaAnterior;
                        temp++;
                    }
                    if (Character.isUpperCase(tableroAjedrez[r + temp * j][c + temp * k].charAt(0))) {
                        piezaAnterior = tableroAjedrez[r + temp * j][c + temp * k];
                        tableroAjedrez[r][c] = " ";
                        tableroAjedrez[r + temp * j][c + temp * k] = "q";
                        if (kingSafeL()) {
                            lista = lista + r + c + (r + temp * j) + (c + temp * k) + piezaAnterior;
                        }
                        tableroAjedrez[r][c] = "q";
                        tableroAjedrez[r + temp * j][c + temp * k] = piezaAnterior;

                    }

                } catch (Exception e) {
                }
                temp = 1;

            }

        }

        return lista;
    }

    public static String posibleRL(int i) {
        String lista = "", piezaAnterior;
        int r = i / 8, c = i % 8;

        for (int j = 0; j < 9; j++) {
            if (j != 4) {
                try {

                    if (Character.isUpperCase(tableroAjedrez[r - 1 + j / 3][c - 1 + j % 3].charAt(0)) || " ".equals(tableroAjedrez[r - 1 + j / 3][c - 1 + j % 3])) {
                        piezaAnterior = tableroAjedrez[r - 1 + j / 3][c - 1 + j % 3];
                        tableroAjedrez[r][c] = " ";
                        tableroAjedrez[r - 1 + j / 3][c - 1 + j % 3] = "r";
                        int kingTemp = posicionReyL;
                        posicionReyL = i + ((j / 3) * 8) + (j % 3) - 9;
                        if (kingSafeL()) {
                            lista = lista + r + c + (r - 1 + j / 3) + (c - 1 + j % 3) + piezaAnterior;
                        }
                        tableroAjedrez[r][c] = "r";
                        tableroAjedrez[r - 1 + j / 3][c - 1 + j % 3] = piezaAnterior;
                        posicionReyL = i;
                    }
                } catch (Exception e) {
                }
            }

        }
        return lista;
    }

    // kingsafe piezas blancas
    /* comprobador que usa las variables que localizan al rey, si cerca de su posicion hay piezas minusculas devolvera un valor false.
  esto hace que las listas de las piezas mayusculas no puedan ejecutarse.
    */
    
    public static boolean kingSafe() {
        //alfil y reina (mitad)
        int temp = 1;
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {

                try {
                    while (" ".equals(tableroAjedrez[posicionReyM / 8 + temp * i][posicionReyM % 8 + temp * j])) {
                        temp++;
                    }
                    if ("a".equals(tableroAjedrez[posicionReyM / 8 + temp * i][posicionReyM % 8 + temp * j])
                            || "q".equals(tableroAjedrez[posicionReyM / 8 + temp * i][posicionReyM % 8 + temp * j])) {
                        return false;
                    }

                } catch (Exception e) {
                }
                temp = 1;

            }

        }
        //torre y reina

        for (int i = -1; i <= 1; i += 2) {
            try {
                while (" ".equals(tableroAjedrez[posicionReyM / 8][posicionReyM % 8 + temp * i])) {
                    temp++;
                }
                if ("t".equals(tableroAjedrez[posicionReyM / 8][posicionReyM % 8 + temp * i])
                        || "q".equals(tableroAjedrez[posicionReyM / 8][posicionReyM % 8 + temp * i])) {
                    return false;
                }

            } catch (Exception e) {
            }
            temp = 1;
            try {
                while (" ".equals(tableroAjedrez[posicionReyM / 8 + temp * i][posicionReyM % 8])) {
                    temp++;
                }
                if ("t".equals(tableroAjedrez[posicionReyM / 8 + temp * i][posicionReyM % 8])
                        || "q".equals(tableroAjedrez[posicionReyM / 8 + temp * i][posicionReyM % 8])) {
                    return false;
                }

            } catch (Exception e) {
            }
            temp = 1;

        }
        // caballo 
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {

                try {
                    if ("c".equals(tableroAjedrez[posicionReyM / 8 + i][posicionReyM % 8 + j * 2])) {
                        return false;
                    }

                } catch (Exception e) {
                }
                try {
                    if ("c".equals(tableroAjedrez[posicionReyM / 8 + i * 2][posicionReyM % 8 + j])) {
                        return false;
                    }

                } catch (Exception e) {
                }

            }

        }
        //peon
        if (posicionReyM >= 16) {
            try {
                if ("p".equals(tableroAjedrez[posicionReyM / 8 - 1][posicionReyM % 8 - 1])) {
                    return false;
                }
            } catch (Exception e) {
            }
            try {
                if ("p".equals(tableroAjedrez[posicionReyM / 8 - 1][posicionReyM % 8 + 1])) {
                    return false;
                }
            } catch (Exception e) {
            }
            //king
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) {
                        try {
                            if ("r".equals(tableroAjedrez[posicionReyM / 8 + i][posicionReyM % 8 + j])) {
                                return false;
                            }
                        } catch (Exception e) {
                        }
                    }

                }
            }
        }
        return true;
    }

    // king safe piezas negras
    //funciona igual que el kingsafe. excepto que, ahora comprueba si hay piezas minusculas.
    public static boolean kingSafeL() {
        //alfil y reina (mitad)
        int temp = 1;
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {

                try {
                    while (" ".equals(tableroAjedrez[posicionReyL / 8 + temp * i][posicionReyL % 8 + temp * j])) {
                        temp++;
                    }
                    if ("A".equals(tableroAjedrez[posicionReyL / 8 + temp * i][posicionReyL % 8 + temp * j])
                            || "Q".equals(tableroAjedrez[posicionReyL / 8 + temp * i][posicionReyL % 8 + temp * j])) {
                        return false;
                    }

                } catch (Exception e) {
                }
                temp = 1;

            }

        }
        //torre y reina

        for (int i = -1; i <= 1; i += 2) {
            try {
                while (" ".equals(tableroAjedrez[posicionReyL / 8][posicionReyL % 8 + temp * i])) {
                    temp++;
                }
                if ("T".equals(tableroAjedrez[posicionReyL / 8][posicionReyL % 8 + temp * i])
                        || "Q".equals(tableroAjedrez[posicionReyL / 8][posicionReyL % 8 + temp * i])) {

                    return false;
                }

            } catch (Exception e) {
            }
            temp = 1;
            try {
                while (" ".equals(tableroAjedrez[posicionReyL / 8 + temp * i][posicionReyL % 8])) {
                    temp++;
                }
                if ("T".equals(tableroAjedrez[posicionReyL / 8 + temp * i][posicionReyL % 8])
                        || "Q".equals(tableroAjedrez[posicionReyL / 8 + temp * i][posicionReyL % 8])) {

                    return false;
                }

            } catch (Exception e) {
            }
            temp = 1;

        }
        // caballo 
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {

                try {
                    if ("C".equals(tableroAjedrez[posicionReyL / 8 + i][posicionReyL % 8 + j * 2])) {
                        return false;
                    }

                } catch (Exception e) {
                }
                try {
                    if ("C".equals(tableroAjedrez[posicionReyL / 8 + i * 2][posicionReyL % 8 + j])) {
                        return false;
                    }

                } catch (Exception e) {
                }

            }

        }
        //peon
        if (posicionReyL <= 40) {
            try {
                if ("P".equals(tableroAjedrez[posicionReyL / 8 + 1][posicionReyL % 8 - 1])) {
                    return false;
                }

            } catch (Exception e) {
            }
            try {
                if ("P".equals(tableroAjedrez[posicionReyL / 8 + 1][posicionReyL % 8 + 1])) {
                    return false;
                }

            } catch (Exception e) {
            }
            //rey
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) {
                        try {
                            if ("R".equals(tableroAjedrez[posicionReyL / 8 + i][posicionReyL % 8 + j])) {
                                return false;
                            }

                        } catch (Exception e) {
                        }

                    }

                }

            }
        }
        return true;
    }

}
