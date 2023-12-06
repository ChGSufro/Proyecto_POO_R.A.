package GUI;

import GestionDeArchivos.GestorDeArchivos;
import ReservApp.Cabaña;
import ReservApp.GestorDeCabañas;
import ReservApp.GestorDeClientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaMostrarCabaña extends VentanaAbstractRA implements ActionListener{

    private GestorDeClientes gestorDeClientes;
    private GestorDeCabañas gestorDeCabañas;
    JButton b_regreso;

    public VentanaMostrarCabaña(GestorDeClientes gestorDeClientes, GestorDeCabañas gestorDeCabañas){

        this.gestorDeClientes = gestorDeClientes;
        this.gestorDeCabañas = gestorDeCabañas;

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, gestorDeCabañas.getListaCabañas().size()*250 + 500));
        panel.setLayout(null);
        panel.setOpaque(false);

        b_regreso = crearBotonRegresar();
        b_regreso.addActionListener(this);
        panel.add(b_regreso);

        cargarLogoPrincipal(panel);

        cargarCabañas(gestorDeCabañas.getListaCabañas(), panel);

        fondo.add(scrollInvisible(panel));

        add(fondo);

        setVisible(true);
    }

    //Componentes

    private void cargarCabañas(ArrayList<Cabaña> listaCabañas, JPanel panel){
        int posicion = 500;
        for (Cabaña cabaña : listaCabañas){
            panel.add(cabañaTitulo(cabaña, posicion));
            posicion += 40;

            panel.add(cabañaImagen(cabaña, posicion));
            posicion += 120;

            panel.add(cabañaHaitacion(cabaña, posicion));
            posicion += 20;

            panel.add(cabañaBaños(cabaña, posicion));
            posicion += 20;

            panel.add(cabañaEstado(cabaña, posicion));
            posicion += 50;
        }
    }
    private JLabel cabañaTitulo(Cabaña cabaña, int posicion){
        JLabel titulo = new JLabel(cabaña.getId() + ") " + cabaña.getNombre());
        titulo.setFont(new Font("IBM Plex Sans", Font.BOLD, 30));
        titulo.setBounds(40, posicion, 200, 40);
        return titulo;
    }
    private JLabel cabañaImagen(Cabaña cabaña, int posicion){
        JLabel imagen = new JLabel(new GestorDeArchivos().cargarImgIcono("CabañasImagenes", Integer.toString(cabaña.getId()), 200, 120));
        imagen.setBackground(new Color(217, 217, 217, 100));//Gris 50% transparente
        imagen.setOpaque(true);//Hago que se vea el fondo
        imagen.setBounds(40, posicion, 200, 120);
        return imagen;
    }
    private JLabel cabañaHaitacion(Cabaña cabaña, int posicion){
        JLabel habitaciones = new JLabel("Habitaciones: " + cabaña.getHabitaciones());
        habitaciones.setBounds(40, posicion, 200, 20);
        return habitaciones;
    }
    private JLabel cabañaBaños(Cabaña cabaña, int posicion){
        JLabel baños = new JLabel("Baños: " + cabaña.getBaños());
        baños.setBounds(40, posicion, 200, 20);
        return baños;
    }
    private JLabel cabañaEstado(Cabaña cabaña, int posicion){
        JLabel precio = new JLabel("Estado ocupada: " + cabaña.getIsOcupada());
        precio.setBounds(40, posicion, 200, 20);
        return precio;
    }

    private JButton crearBotonRegresar(){
        JButton boton = crearBoton("#047994");
        boton.setText("Volver");
        boton.setFont(new Font( "IBM Plex Sans", Font.BOLD, 20));
        boton.setBounds(125, 420, 150, 50);
        return boton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_regreso){
            dispose();
            VentanaMenuPrincipal menu = new VentanaMenuPrincipal(this.gestorDeClientes, this.gestorDeCabañas);
            menu.setVisible(true);
        }

    }
}


