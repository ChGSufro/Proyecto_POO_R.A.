package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMenuPrincipal extends VentanaAbstractRA implements ActionListener {

    JPanel panel;
    JButton b_mostrarCabañas, b_arrendarCabaña, b_verReservas, b_checkOut,b_cerrarSesion;

    public static void main(String[] args) {
        VentanaMenuPrincipal menu = new VentanaMenuPrincipal();
        menu.setVisible(true);
    }

    public VentanaMenuPrincipal(){
        setTitle("Resev-App");

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 1050));
        panel.setOpaque(false);
        panel.setLayout(null);

        cargarLogoPrincipal(panel);

        b_mostrarCabañas = crearBotonMostrarCabañas();
        b_mostrarCabañas.addActionListener(this);
        panel.add(b_mostrarCabañas);

        b_arrendarCabaña = crearBotonArrendarCabaña();
        b_arrendarCabaña.addActionListener(this);
        panel.add(b_arrendarCabaña);

        b_verReservas = crearBotonVerReservas();
        b_verReservas.addActionListener(this);
        panel.add(b_verReservas);

        b_checkOut = crearBotonCheckOut();
        b_checkOut.addActionListener(this);
        panel.add(b_checkOut);

        b_cerrarSesion = crearBotonCerrarSesion();
        b_cerrarSesion.addActionListener(this);
        panel.add(b_cerrarSesion);

        fondo.add(scrollInvisible(panel));
        setContentPane(fondo);
    }

    private JButton crearBotonMostrarCabañas(){
        JButton boton = crearBoton("#EC9E48");
        boton.setText("Mostrar cabañas");
        boton.setFont(new Font("IBM Plex Sans", Font.BOLD, 20));
        boton.setBounds(100, 420, 200, 100);
        return boton;
    }

    private JButton crearBotonArrendarCabaña(){
        JButton boton = crearBoton("#EC9E48");
        boton.setText("Arrendar cabaña");
        boton.setFont(new Font("IBM Plex Sans", Font.BOLD, 20));
        boton.setBounds(100, 550, 200, 100);
        return boton;
    }

    private JButton crearBotonVerReservas(){
        JButton boton = crearBoton("#EC9E48");
        boton.setText("Ver reservas");
        boton.setFont(new Font("IBM Plex Sans", Font.BOLD, 20));
        boton.setBounds(100, 680, 200, 100);
        return boton;
    }

    private JButton crearBotonCheckOut(){
        JButton boton = crearBoton("#EC9E48");
        boton.setText("Check-out");
        boton.setFont(new Font("IBM Plex Sans", Font.BOLD, 20));
        boton.setBounds(100, 810, 200, 100);
        return boton;
    }

    private JButton crearBotonCerrarSesion(){
        JButton boton = crearBoton("#047994");
        boton.setText("Cerrar sesión");
        boton.setFont(new Font("IBM Plex Sans", Font.BOLD, 20));
        boton.setBounds(100, 940, 200, 50);
        return boton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_mostrarCabañas){
            dispose();
            //VentanaMostrarCabañas menu = new VentanaMostrarCabañas();
            //menu.setVisible(true);
        }

        if (e.getSource() == b_arrendarCabaña){
            dispose();
            //VentanaArrendarCabaña menu = new VentanaArrendarCabaña();
            //menu.setVisible(true);
        }

        if (e.getSource() == b_verReservas){
            dispose();
            //VentanaVerReservas menu = new VentanaVerReservas();
            //menu.setVisible(true);
        }

        if (e.getSource() == b_checkOut){
            dispose();
            //VentanaCheckOut menu = new VentanaCheckOut();
            //menu.setVisible(true);
        }

        if (e.getSource() == b_cerrarSesion){
            dispose();
            VentanaMenuBienvenida menu = new VentanaMenuBienvenida();
            menu.setVisible(true);
        }

    }
}
