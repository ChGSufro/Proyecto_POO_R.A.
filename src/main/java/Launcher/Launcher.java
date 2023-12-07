package Launcher;

import GUI.VentanaMenuBienvenida;
import ReservApp.GestorDeClientes;

public class Launcher {

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
