package GUI;

import ReservApp.GestorDeClientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI que se muestra a la hora de ejecutar el programa y que permite logear o registrar.
 */
public final class VentanaMenuBienvenida extends VentanaAbstractRA implements ActionListener {

    JPanel panel;
    JButton b_registroSesion, b_inicioSesion;
    private GestorDeClientes gestorDeClientes;

    /**
     * Constructor de la ventana, que setea las caracteristicas de esta y recibe el gestor de clientes para el logeo o registro.
     * @param gestorDeClientes Encargado de realizar el logeo o registro.
     */
    public VentanaMenuBienvenida(GestorDeClientes gestorDeClientes){
        this.gestorDeClientes = gestorDeClientes;

        setTitle("Bienvenido a Resev-App");

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 800));
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

    /**
     * Metodo que crea el boton que dirige a la ventana de SignUp.
     * @return JButton que el usuario puede presionar para registrarse.
     */
    public JButton b_registroSesion() {
        JButton boton = crearBoton("#EC9E48");
        boton.setText("Registro             ");
        boton.setFont(new Font("IBM Plex Sans", Font.BOLD, 20));
        boton.setBounds(100, 350, 250, 50);
        boton.setForeground(Color.BLACK);
        return boton;
    }

    /**
     * Metodo que crea el boton que dirige a la ventana de LogIn.
     * @return JButton que el usuario puede presionar para logearse.
     */
    public JButton b_inicioSesion(){
        JButton boton = crearBoton("#047994");
        boton.setText("Inicio Sesión     ");
        boton.setFont(new Font( "IBM Plex Sans", Font.BOLD, 20));
        boton.setBounds(100, 430, 250, 50);
        boton.setForeground(Color.BLACK);
        return boton;
    }

    //Eventos de action listener

    /**
     * Metodo de la interfaz ActionListener que captura los eventos de la ventana.
     * @param event El evento que ocurrió en la ventana.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == b_registroSesion) {
            dispose();
            VentanaSignUp menu = new VentanaSignUp(this.gestorDeClientes);
            menu.setVisible(true);
        }
        else if(event.getSource() == b_inicioSesion) {
            dispose();
            VentanaLogIn menu = new VentanaLogIn(this.gestorDeClientes);
            menu.setVisible(true);
        }
    }
}
