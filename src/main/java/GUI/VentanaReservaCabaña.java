package GUI;

import GestionDeArchivos.GestorDeArchivos;
import ReservApp.Cabaña;
import ReservApp.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * GUI encargada de la reserva de las cabañas disponibles.
 */
public final class VentanaReservaCabaña extends VentanaAbstractRA implements ActionListener {

    private JPanel panelInf, panelSup; // panel sin scroll y con scroll respectivamente
    private JLabel idCabaña;
    private JTextField field_idCabaña;
    private JButton b_reservar, b_regresar;
    private ArrayList<Cabaña> listaCabañas;
    private Cliente usuarioIngresado;

    /**
     * Constructor de la clase donde se establecen las configuraciones de la ventana
     * @param listaCabañas ArrayList de tipo Cabaña con las cabañas disponibles para arrendar.
     * @param usuarioIngresado Usuario logeado actualmente, el cual puede reservar.
     */
    public VentanaReservaCabaña(ArrayList<Cabaña> listaCabañas, Cliente usuarioIngresado){
        this.usuarioIngresado = usuarioIngresado;
        this.listaCabañas = listaCabañas;
        setTitle("Reserva de cabaña");

        panelSup = new JPanel();
        panelSup.setPreferredSize(new Dimension(0, listaCabañas.size()*250 + 450)); //?
        panelSup.setOpaque(false);
        panelSup.setBorder(BorderFactory.createEmptyBorder());
        panelSup.setLayout(null);

        cargarLogoPrincipal(panelSup);

        b_regresar = crearBotonRegresar();
        b_regresar.addActionListener(this);
        panelSup.add(b_regresar);

        cargarCabañas(this.listaCabañas, panelSup);

        panelInf = new JPanel();
        panelInf.setPreferredSize(new Dimension(0, 200));
        panelInf.setOpaque(false);
        panelInf.setBorder(BorderFactory.createEmptyBorder());
        panelInf.setLayout(null);

        idCabaña = crearLabelIdCabaña();
        panelInf.add(idCabaña);

        field_idCabaña = crearFieldIdCabaña();
        panelInf.add(field_idCabaña);

        b_reservar = crearBotonReservar();
        b_reservar.addActionListener(this);
        panelInf.add(b_reservar);

        fondo.add(scrollInvisible(panelSup));
        fondo.add(panelInf);
        setContentPane(fondo);
    }

    /**
     * Metodo encargo de crear y setear caracteristicas del boton volver.
     * @return JButton para volver a la Ventana anterior.
     */
    private JButton crearBotonRegresar(){
        JButton botonRegresar = crearBoton("#047994");
        botonRegresar.setText("Regresar");
        botonRegresar.setFont(new Font("IBM Plex Sans", Font.BOLD, 15));
        botonRegresar.setBounds(75, 350, 150, 50);
        botonRegresar.setForeground(Color.BLACK);
        return botonRegresar;
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
     * Metodo que se encarga de crear el mensaje para indicar el ID de la cabaña que se quiere arrendar.
     * @return JLabel con el mensaje "ID cabaña a reservar:".
     */
    private JLabel crearLabelIdCabaña(){
        JLabel labelIdCabaña = new JLabel("ID cabaña a reservar: ");
        labelIdCabaña.setBounds(25, 10, 130, 20);
        labelIdCabaña.setForeground(Color.BLACK);
        return labelIdCabaña;
    }

    /**
     * Metodo que crea el Field donde se indica el ID de la cabaña que se quiere arrendar.
     * @return JTextField donde se indica el ID de la cabaña que se quiere arrendar.
     */
    private JTextField crearFieldIdCabaña(){
        JTextField fieldIdCabaña = new JTextField();
        fieldIdCabaña.setBounds(150, 10, 50, 20);
        fieldIdCabaña.setBackground(Color.decode("#D9D9D9"));
        fieldIdCabaña.setForeground(Color.BLACK);
        return fieldIdCabaña;
    }

    /**
     * Metodo que crea el boton que recibe la informacion del ID de la cabaña que se quiere arrendar.
     * @return JButton que realiza la reserva de la cabaña.
     */
    private JButton crearBotonReservar(){
        JButton botonReservar = crearBoton("#EC9E48");
        botonReservar.setText("Reservar");
        botonReservar.setFont(new Font("IBM Plex Sans", Font.BOLD, 15));
        botonReservar.setBounds(75, 40, 150, 50);
        botonReservar.setForeground(Color.BLACK);
        return botonReservar;
    }

    /**
     * Metodo de la interfaz ActionListener que captura los eventos de la ventana.
     * @param event El evento que ocurrió en la ventana.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(b_regresar)){
            dispose();
        }
        if (event.getSource().equals(b_reservar)){
            try {
                boolean idValido = false;
                int idCabaña = Integer.parseInt(field_idCabaña.getText());
                for (Cabaña cabaña : this.listaCabañas) {
                    if (cabaña.getId() == idCabaña) {
                        cabaña.reservarCabaña_GUI(this.usuarioIngresado);
                        JOptionPane.showMessageDialog(null, "Cabaña reservada exitosamente");
                        dispose();
                        idValido = true;
                        break; // Terminar el bucle una vez que se encuentre el ID
                    }
                }if (!idValido) {
                    JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
                }
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
            }
        }
    }
}
