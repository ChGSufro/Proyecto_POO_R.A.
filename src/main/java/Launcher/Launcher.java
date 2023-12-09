package Launcher;

import GUI.VentanaMenuBienvenida;
import ReservApp.GestorDeClientes;

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

    // color Amarillento #FFBC61 (fondo)
    // color Amarillento/anaranjado #EC9E48 (boton)
    // color Azul/verde #047994 (boton)
    // color gris #D9D9D9 (campo de texto)

    //Tipografia logo: IBM Plex Sans Bold
}
