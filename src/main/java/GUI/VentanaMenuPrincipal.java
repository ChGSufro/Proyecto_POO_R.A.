package GUI;

import ReservApp.Cliente;
import ReservApp.GestorDeCabañas;
import ReservApp.GestorDeClientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana Principal despues del Login, donde se muestran los botones que puede realizar el usuario.
 */
public final class VentanaMenuPrincipal extends VentanaAbstractRA implements ActionListener {

    private JPanel panel;
    private JButton b_mostrarCabañas, b_arrendarCabaña, b_verReservas, b_checkOut, b_cerrarSesion, b_modificarUsuario;
    private GestorDeClientes gestorDeClientes;
    private GestorDeCabañas gestorDeCabañas;
    private Cliente usuarioIngresado;

    /**
     * Constructor de la ventana, que setea las caracteristicas de esta, recibe el gestor de clientes y cliente logeado.
     * @param gestorDeClientes Encargado de administrar los clientes del sistema.
     * @param usuarioIngresado Usuario logeado actualmente en el sistema.
     */
    public VentanaMenuPrincipal(GestorDeClientes gestorDeClientes, Cliente usuarioIngresado){
        this.gestorDeClientes = gestorDeClientes;
        this.gestorDeCabañas = new GestorDeCabañas(this.gestorDeClientes);
        this.usuarioIngresado = usuarioIngresado;
        setTitle("Resev-App");

        setJMenuBar(menu());
        b_modificarUsuario = botonMenu();
        b_modificarUsuario.addActionListener(this);
        getJMenuBar().add(b_modificarUsuario);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 850));
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

    /**
     * Metodo que crea el boton que dirige a la ventana donde se muestran todas las cabañas.
     * @return Devuelve el JButton que el usuario puede presionar para ver todas las cabañas.
     */
    private JButton crearBotonMostrarCabañas(){
        JButton boton = crearBoton("#EC9E48");
        boton.setText("Mostrar cabañas");
        boton.setFont(new Font("IBM Plex Sans", Font.BOLD, 15));
        boton.setBounds(50, 350, 200, 70);
        return boton;
    }

    /**
     * Metodo que crea el boton que dirige a la ventana donde se arriendan las cabañas.
     * @return Devuelve el JButton que el usuario puede presionar para arrendar cabañas.
     */
    private JButton crearBotonArrendarCabaña(){
        JButton boton = crearBoton("#EC9E48");
        boton.setText("Arrendar cabaña");
        boton.setFont(new Font("IBM Plex Sans", Font.BOLD, 15));
        boton.setBounds(50, 440, 200, 70);
        return boton;
    }

    /**
     * Metodo que crea el boton que dirige a la ventana donde se muestran todas las cabañas reservadas por el usuario logeado.
     * @return Devuelve el JButton que el usuario puede presionar para ver todas las cabañas reservadas por el usuario actual.
     */
    private JButton crearBotonVerReservas(){
        JButton boton = crearBoton("#EC9E48");
        boton.setText("Ver reservas");
        boton.setFont(new Font("IBM Plex Sans", Font.BOLD, 15));
        boton.setBounds(50, 530, 200, 70);
        return boton;
    }

    /**
     * Metodo que crea el boton que dirige a la ventana donde el usuario puede realizar el checkout de sus cabañas.
     * @return Devuelve el JButton que el usuario puede presionar para realizar el checkout de la cabaña.
     */
    private JButton crearBotonCheckOut(){
        JButton boton = crearBoton("#EC9E48");
        boton.setText("Check-out");
        boton.setFont(new Font("IBM Plex Sans", Font.BOLD, 15));
        boton.setBounds(50, 620, 200, 70);
        return boton;
    }

    /**
     * Metodo que crea el boton que cierra la sesion del usuario y guarda los cambios en los archivos Clientes.json y Cabañas.json.
     * @return Devuelve el JButton que el usuario puede presionar para el cierre de la sesion.
     */
    private JButton crearBotonCerrarSesion(){
        JButton boton = crearBoton("#047994");
        boton.setText("Cerrar sesión");
        boton.setFont(new Font("IBM Plex Sans", Font.BOLD, 15));
        boton.setBounds(75, 710, 150, 50);
        return boton;
    }

    /**
     * Metodo de la interfaz ActionListener que captura los eventos de la ventana.
     * @param event El evento que ocurrió en la ventana.
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == b_mostrarCabañas){
            VentanaMostrarCabaña ventanaMostrarCabaña = new VentanaMostrarCabaña(gestorDeCabañas.getListaCabañas());
            ventanaMostrarCabaña.setVisible(true);
        }

        if (event.getSource() == b_arrendarCabaña){
            if (gestorDeCabañas.getCabañasDisponibles().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay cabañas disponibles");
            } else {
                VentanaReservaCabaña ventanaReservaCabaña = new VentanaReservaCabaña(gestorDeCabañas.getCabañasDisponibles(), this.usuarioIngresado);
                ventanaReservaCabaña.setVisible(true);
            }
        }

        if (event.getSource() == b_verReservas){
            if (gestorDeCabañas.getCabañasReservadas(this.usuarioIngresado).isEmpty()){
                JOptionPane.showMessageDialog(null, "No tiene cabañas reservadas");

            } else {
                VentanaMostrarCabaña ventanaMostrarCabaña = new VentanaMostrarCabaña(gestorDeCabañas.getCabañasReservadas(this.usuarioIngresado));
                ventanaMostrarCabaña.setVisible(true);
            }
        }

        if (event.getSource() == b_checkOut){
            if(gestorDeCabañas.getCabañasReservadas(this.usuarioIngresado).isEmpty()){
                JOptionPane.showMessageDialog(null, "No tiene cabañas reservadas");
            }else{
                VentanaCheckOut ventanaCheckOut = new VentanaCheckOut(gestorDeCabañas.getCabañasReservadas(this.usuarioIngresado), this.usuarioIngresado);
                ventanaCheckOut.setVisible(true);
            }
        }

        if (event.getSource() == b_cerrarSesion){
            dispose();
            this.gestorDeCabañas.registrarCabañasEnArchivoJson();
            this.gestorDeClientes.registrarClientesEnArchivoJson();
            VentanaMenuBienvenida menu = new VentanaMenuBienvenida(this.gestorDeClientes);
            menu.setVisible(true);
        }

        if (event.getSource() == b_modificarUsuario){
            VentanaModificarUsuario menu = new VentanaModificarUsuario(this.usuarioIngresado, this.gestorDeClientes);
            menu.setVisible(true);
        }

    }
}
