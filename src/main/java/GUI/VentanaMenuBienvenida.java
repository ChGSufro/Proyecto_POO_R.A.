package GUI;

import ReservApp.GestorDeCabañas;
import ReservApp.GestorDeClientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class VentanaMenuBienvenida extends VentanaAbstractRA implements ActionListener {

    JPanel panel;
    JButton b_registroSesion, b_inicioSesion;
    private GestorDeClientes gestorDeClientes;


    public VentanaMenuBienvenida(GestorDeClientes gestorDeClientes){
        this.gestorDeClientes = gestorDeClientes;

        setTitle("Bienvenido a Resev-App");

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 0));
        panel.setOpaque(false);
        panel.setLayout(null);

        cargarLogoPrincipal(panel);

        b_registroSesion = b_registroSesion();
        b_registroSesion.addActionListener(this);
        panel.add(b_registroSesion);

        b_inicioSesion = b_inicioSesion();
        b_inicioSesion.addActionListener(this);
        panel.add(b_inicioSesion);

        fondo.add(scrollInvisible(panel));
        setContentPane(fondo);
    }


    //Eventos independientes de action listener
    public JButton b_registroSesion() {
        JButton boton = crearBoton("#EC9E48");
        boton.setText("Registro             ");
        boton.setFont(new Font("IBM Plex Sans", Font.BOLD, 30));
        boton.setBounds(100, 450, 350, 90);
        boton.setForeground(Color.BLACK);
        return boton;
    }
    public JButton b_inicioSesion(){
        JButton boton = crearBoton("#047994");
        boton.setText("Inicio Sesión     ");
        boton.setFont(new Font( "IBM Plex Sans", Font.BOLD, 30));
        boton.setBounds(100, 570, 350, 90);
        boton.setForeground(Color.BLACK);
        return boton;
    }


    //Eventos de action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_registroSesion) {
            dispose();
            VentanaSingUp menu = new VentanaSingUp(this.gestorDeClientes);
            menu.setVisible(true);
        }

        if (e.getSource() == b_inicioSesion) {
            dispose();
            VentanaLogIn menu = new VentanaLogIn(this.gestorDeClientes);
            menu.setVisible(true);
        }
    }


}
