package GUI;

import GestionDeArchivos.GestorDeArchivos;
import ReservApp.Cabaña;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Ventana encargada de mostrar graficamente todas las cabañas.
 */
public final class VentanaMostrarCabaña extends VentanaAbstractRA implements ActionListener{

    private JButton b_regreso;

    /**
     * Constructor de la Ventana, que setea caracteristicas de esta.
     * @param listaCabañas ArrayList de todas las cabañas para mostrar.
     */
    public VentanaMostrarCabaña(ArrayList<Cabaña> listaCabañas){
        setTitle("Cabañas existentes");

        setJMenuBar(menu());

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, listaCabañas.size()*230 + 500));
        panel.setLayout(null);
        panel.setOpaque(false);

        b_regreso = crearBotonRegresar();
        b_regreso.addActionListener(this);
        panel.add(b_regreso);

        cargarLogoPrincipal(panel);

        cargarCabañas(listaCabañas, panel);

        fondo.add(scrollInvisible(panel));

        add(fondo);
    }

    /**
     * Metodo encargado de mostrar graficamente las cabañas en el panelSup
     * @param listaCabañas ArrayList de Cabañas disponibles para reservar.
     * @param panel panelSup donde se cargaran las cabañas
     */
    private void cargarCabañas(ArrayList<Cabaña> listaCabañas, JPanel panel){
        int posicion = 450;
        for (Cabaña cabaña : listaCabañas){
            panel.add(cabañaTitulo(cabaña, posicion));
            posicion += 20;

            panel.add(cabañaImagen(cabaña, posicion));
            posicion += 120;

            panel.add(cabañaHabitacion(cabaña, posicion));
            posicion += 20;

            panel.add(cabañaBaños(cabaña, posicion));
            posicion += 20;

            panel.add(cabañaEstado(cabaña, posicion));
            posicion += 50;
        }
    }

    /**
     * Se encarga de setear el nombre de la cabaña en un label.
     * @param cabaña Objeto Cabaña.
     * @param posicion posicion dentro del panel.
     * @return Devuelve un Jlabel con el nombre de la cabaña.
     */
    private JLabel cabañaTitulo(Cabaña cabaña, int posicion){
        JLabel titulo = new JLabel(cabaña.getId() + ")  " + cabaña.getNombre());
        titulo.setFont(new Font("IBM Plex Sans", Font.BOLD, 20));
        titulo.setBounds(25, posicion, 200, 20);
        return titulo;
    }

    /**
     * Se encarga de setear la imagen de la cabaña en un label.
     * @param cabaña Objeto Cabaña.
     * @param posicion posicion dentro del panel.
     * @return Devuelve un Jlabel con la imagen de la cabaña.
     */
    private JLabel cabañaImagen(Cabaña cabaña, int posicion){
        JLabel imagen = new JLabel(new GestorDeArchivos().cargarPng("CabañasImagenes/" + cabaña.getId() + ".png", 200, 120));
        imagen.setBackground(new Color(217, 217, 217, 100));//Gris 50% transparente
        imagen.setOpaque(true);//Hago que se vea el fondo
        imagen.setBounds(25, posicion, 200, 120);
        return imagen;
    }

    /**
     * Se encarga de setear la cantidad de habitaciones de la cabaña en un label.
     * @param cabaña Objeto Cabaña.
     * @param posicion posicion dentro del panel.
     * @return Devuelve un Jlabel con la cantidad de habitaciones de la cabaña.
     */
    private JLabel cabañaHabitacion(Cabaña cabaña, int posicion){
        JLabel habitaciones = new JLabel("Habitaciones: " + cabaña.getHabitaciones());
        habitaciones.setBounds(25, posicion, 200, 20);
        return habitaciones;
    }

    /**
     * Se encarga de setear la cantidad de baños de la cabaña en un label.
     * @param cabaña Objeto Cabaña.
     * @param posicion posicion dentro del panel.
     * @return Devuelve un Jlabel con la cantidad de baños de la cabaña.
     */
    private JLabel cabañaBaños(Cabaña cabaña, int posicion){
        JLabel baños = new JLabel("Baños: " + cabaña.getBaños());
        baños.setBounds(25, posicion, 200, 20);
        return baños;
    }

    /**
     * Se encarga de setear el estado de la cabaña en un label.
     * @param cabaña Objeto Cabaña.
     * @param posicion posicion dentro del panel.
     * @return Devuelve un Jlabel con el estado de la cabaña.
     */
    private JLabel cabañaEstado(Cabaña cabaña, int posicion){
        JLabel precio = new JLabel("Estado ocupada: " + cabaña.getIsOcupada());
        precio.setBounds(25, posicion, 200, 20);
        return precio;
    }

    /**
     * Metodo que crea el boton para regresar a la ventana anterior.
     * @return Devuelve el JButton que cierra la ventana y vuelve a la anterior.
     */
    private JButton crearBotonRegresar(){
        JButton boton = crearBoton("#047994");
        boton.setText("Regresar");
        boton.setFont(new Font( "IBM Plex Sans", Font.BOLD, 15));
        boton.setBounds(75, 350, 150, 50);
        return boton;
    }

    /**
     * Metodo de la interfaz ActionListener que captura los eventos de la ventana.
     * @param event El evento que ocurrió en la ventana.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(b_regreso)){
            dispose();
        }
    }
}


