package Launcher;

import GUI.VentanaMenuBienvenida;
import ReservApp.GestorDeCabañas;
import ReservApp.GestorDeClientes;

public class Launcher {

    public static void main(String[] args) {
        VentanaMenuBienvenida menuBienvenida = new VentanaMenuBienvenida(new GestorDeClientes());
        menuBienvenida.setVisible(true);
    }


    // color Amarillento #FFBC61 (fondo)
    // color Amarillento/anaranjado #EC9E48 (boton)
    // color Azul/verde #047994 (boton)

    //Tipografia logo: IBM Plex Sans Bold
}
