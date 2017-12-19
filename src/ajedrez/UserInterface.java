package ajedrez;

import static ajedrez.Ajedrez.contadorT;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class UserInterface extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

    static int size = 64;// tamaño de los espacios del tablero
    static int borde = 32;//tamño de los bordes
    static int mouseX, mouseY, nuevoMouseX, nuevoMouseY;//obtienen las cordenadas de las piezas con el mouselistener
    static int regulador = size + 8;//regula los mouse listeners para solo funcionar en el tablero
    static int contadorT = 0;//este es el sistema de turnos que le da permiso a cada pieza
    static String winer;//comprueba las listas en el main de las piezas blancas
    static String winer2;//comprueba las listas de las piezas negras en el main
    static String nombre = Nombres.nombre1();// llama a la clase "Nombres" para que el usuario ingrese su nombre
    static String nombre2 = Nombres2.nombre2();//igual que la variable anterior
    static boolean ks;//llama al kingsafe para comprobar si el rey esta seguro
    static boolean ksL;//lo mismo que la variable anterior pero con KingsafeL
    JButton boton;// variable para el boton que regresa las piezas a la posicion anterior
    Ajedrez obtener = new Ajedrez();// variable de instancia para llamar a los metodos que se ocupen

    public void abrir() {//jframe (por constructor)que contiene un UserInterface
        JFrame f = new JFrame("Proyecto Programacion Ajedrez");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UserInterface u = new UserInterface();
        Image img = Toolkit.getDefaultToolkit().getImage("icons");
        f.setIconImage(img);
        f.add(u);

        f.getContentPane();
        f.setVisible(true);
        String s;

        boton = new JButton("revertir jugada (presionar solo una vez por jugada)");

        u.add(boton);
        f.setBounds(600, 0, 1125, 700);
        boton.setSize(400, 400);

        boton.addActionListener(this);
    }

    @Override
    //este metodo llama a la matriz del main y la imprime
    public void paintComponent(Graphics g) {

        //
        super.paintComponent(g);
        this.setBackground(new Color(215, 234, 214));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        Bordes(g);

        for (int i = 0; i < 64; i += 2) {//dibujar el tablero
            g.setColor(new Color(215, 234, 214));
            g.fill3DRect((i % 8 + (i / 8) % 2) * size + borde, (i / 8) * size + borde, size, size, true);
            g.setColor(new Color(66, 127, 128));
            g.fill3DRect(((i + 1) % 8 - ((i + 1) / 8) % 2) * size + borde, (i / 8) * size + borde, size, size, true);
            g.setColor(Color.BLACK);
            g.fill3DRect(650, 60, 400, 300, true);
        }
        //se llama a una imagen para representar a cada caracter
        Image chessPiecesImage;
        Image ChessIconImage;
        chessPiecesImage = new ImageIcon("ChessPieces.png").getImage();
        ChessIconImage = new ImageIcon("ChessIcon.png").getImage();

        for (int i = 0; i < 64; i++) {
            int j = -1, k = -1;
            switch (Ajedrez.tableroAjedrez[i / 8][i % 8]) {
                case "P":
                    j = 5;
                    k = 1;
                    break;
                case "p":
                    j = 5;
                    k = 0;
                    break;

                case "T":
                    j = 0;
                    k = 1;
                    break;
                case "t":
                    j = 0;
                    k = 0;
                    break;
                case "C":
                    j = 4;
                    k = 1;
                    break;
                case "c":
                    j = 4;
                    k = 0;
                    break;
                case "A":
                    j = 1;
                    k = 1;
                    break;
                case "a":
                    j = 1;
                    k = 0;
                    break;

                case "Q":
                    j = 2;
                    k = 1;

                    break;
                case "q":
                    j = 2;
                    k = 0;

                    break;
                case "R":
                    j = 3;
                    k = 1;
                    break;

                case "r":
                    j = 3;
                    k = 0;
                    break;

            }
            //sirve para poner los tamaños de las imagenes
            if (j != -1 && k != -1) {
                g.drawImage(chessPiecesImage, (i % 8) * size + borde, (i / 8) * size + borde, (i % 8 + 1) * size + borde, (i / 8 + 1) * size + borde, j * 64, k * 64, (j + 1) * 64, (k + 1) * 64, this);
            }
        }
        g.setColor(Color.WHITE);//fondo del UserInterface
        ks = Ajedrez.kingSafe();//llama al kingsafe
        ksL = Ajedrez.kingSafeL();//lo mismo
        winer = Ajedrez.posiblesMovimientos();//llama a la lista de las piezas blancas
        winer2 = Ajedrez.posiblesMovimientosL();//llama a la lista de las piezas negras
        g.setFont(new Font("bankgothic ", Font.BOLD, 18));//llama a un font a usar
        g.drawString("Total de movimientos: " + contadorT, 700, 100);//dibuja un string 
        g.drawString("Es el turno de: ", 700, 149);
        // sistema de turnos
        if (contadorT % 2 == 0) {// dibuja el nombre del jugador en caso que sea su turno
            g.drawString(nombre, 840, 149);
        } else if (contadorT % 2 != 0) {//lo mismo que el if anterior
            g.drawString(nombre2, 840, 149);
        }
        //comprueba si hay jaque mate y dibuja el nombre del ganador
        if (winer.isEmpty() && ks == false) {
            g.drawString("¡JAQUE MATE! HA GANADO: " + nombre2, 700, 189);
        } else if (winer2.isEmpty() && ksL == false) {
            g.drawString("¡JAQUE MATE HA! GANADO: " + nombre, 700, 189);
        }

    }
// dibuja los bordes y la "pantalla" que muestra quien mueve y el total de movimientos
    public void Bordes(Graphics g) {
        g.setColor(Color.GRAY);
        g.fill3DRect(0, borde, borde, (8 * size), true);
        g.fill3DRect((int) (8 * size) + borde, borde, borde, (int) (8 * size), true);

        g.fill3DRect(borde, 0, (8 * size), borde, true);
        g.fill3DRect(borde, (8 * size) + borde, (8 * size), borde, true);

        g.fill3DRect(0, 0, borde, borde, true);
        g.fill3DRect((8 * size) + borde, 0, borde, borde, true);
        g.fill3DRect(0, (8 * size) + borde, borde, borde, true);
        g.fill3DRect((8 * size) + borde, (8 * size) + borde, borde, borde, true);

        //imprimir borde de pantala
        g.setColor(Color.GRAY);
        g.fill3DRect(625, 40, 450, 350, true);
        g.fill3DRect((int) (8 * size) + borde, borde, borde, (int) (8 * size), true);
        g.fill3DRect(625, 35, 50, 50, true);
        g.fill3DRect(1025, 35, 50, 50, true);
        g.fill3DRect(625, 350, 50, 50, true);
        g.fill3DRect(1025, 345, 50, 50, true);

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
   // obtienen las cordenadas de la matriz y las llevan al metodo "hacermovimiento"
    public void mousePressed(MouseEvent e) {
        if (e.getX() < 8 * regulador && e.getY() < 8 * regulador) {
            mouseX = e.getX();
            mouseY = e.getY();
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        String dragMove = "";
        if (e.getX() < 8 * regulador && e.getY() < 8 * regulador) {
            nuevoMouseX = e.getX();
            nuevoMouseY = e.getY();
            String d = "" + mouseY / regulador + mouseX / regulador + nuevoMouseY / regulador + nuevoMouseX / regulador + Ajedrez.tableroAjedrez[nuevoMouseY / regulador][nuevoMouseX / regulador];
            if (e.getButton() == MouseEvent.BUTTON1) {

                String dragMove2 = "";

                // movimiento normal
                dragMove = "" + mouseY / regulador + mouseX / regulador + nuevoMouseY / regulador + nuevoMouseX / regulador + Ajedrez.tableroAjedrez[nuevoMouseY / regulador][nuevoMouseX / regulador];

                String userPosible = Ajedrez.posiblesMovimientos();
                String userPosible2 = Ajedrez.posiblesMovimientosL();
                if (userPosible.replace(dragMove, "").length() < userPosible.length() && contadorT % 2 == 0) {
                    Ajedrez.hacerMovimiento(dragMove);
                    contadorT = contadorT + 1;
                } else if (userPosible2.replace(dragMove, "").length() < userPosible2.length() && contadorT % 2 != 0) {
                    Ajedrez.hacerMovimiento(dragMove);

                    contadorT = contadorT + 1;
                }

            }
            repaint();
        }

    }
// este metodo inicioa el vento del boton para regresar una jugada
    @Override
    public void actionPerformed(ActionEvent e) {
        String d = "";
        contadorT = contadorT - 1;
        d = "" + mouseY / regulador + mouseX / regulador + nuevoMouseY / regulador + nuevoMouseX / regulador + Ajedrez.tableroAjedrez[nuevoMouseY / regulador][nuevoMouseX / regulador];
        if (e.getSource() == boton && contadorT >= 0) {

            Ajedrez.revertir(d);

        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
