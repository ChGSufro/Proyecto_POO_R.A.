package Launcher;

import GUI.VentanaMenuBienvenida;
import ReservApp.GestorDeClientes;

import javax.swing.*;

/**
 * Clase encargada de lanzar la VentanaMenuBienvenida, la cual comienza la app.
 */
public class Launcher {
    /**
     * Metodo main.
     * @param args main.
     */
    public static void main(String[] args) {
        GestorDeClientes gestorDeClientes = new GestorDeClientes();
        VentanaMenuBienvenida menuBienvenida = new VentanaMenuBienvenida(gestorDeClientes);
        menuBienvenida.setVisible(true);
    }

    // color #FFBC61 - #FFFFFF (fondo)
    // color #EC9E48 (boton amarillo/anaranjado)
    // color #047994 (boton azul)
    // color #D9D9D9 (campo de texto)
    // color #BA813E (menu)

    // Fuente logo: IBM Plex Sans
}
