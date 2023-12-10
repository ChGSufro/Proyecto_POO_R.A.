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
 * GUI en la que el usuario logeado puede realizar sus CheckOuts de cabañas.
 * Siempre y cuando cuente con cabañas reservadas.
 */
public final class VentanaCheckOut extends VentanaAbstractRA implements ActionListener {
    private JPanel panelInf, panelSup; // panel con
    private JLabel idCabaña;
    private JTextField field_idCabaña;
    private JButton b_checkOut, b_regresar;
    private Cliente usuarioIngresado;
    private ArrayList<Cabaña> listaCabañas;

    /**
     * Constructor de la ventana que setea sus caracteristicas y carga los paneles.
     * @param listaCabañas ArrayList de Cabañas reservadas por el usuario logeado en el caso de que tenga.
     * @param usuarioIngresado Objeto Cliente del Usuario Ingresado actualmente.
     */
    public VentanaCheckOut(ArrayList<Cabaña> listaCabañas, Cliente usuarioIngresado){
        this.usuarioIngresado = usuarioIngresado;
        this.listaCabañas = listaCabañas;
        setTitle("CheckOut de cabaña");

        panelSup = new JPanel();
        panelSup.setPreferredSize(new Dimension(0, listaCabañas.size()*230 + 500));
        panelSup.setOpaque(false);
        panelSup.setBorder(BorderFactory.createEmptyBorder());
        panelSup.setLayout(null);

        setJMenuBar(menu());

        cargarLogoPrincipal(panelSup);

        b_regresar = crearBotonRegresar();
        b_regresar.addActionListener(this);
        panelSup.add(b_regresar);

        cargarCabañas(this.listaCabañas, panelSup);

        panelInf = new JPanel();
        panelInf.setBounds(0, 450, 300, 150);
        panelInf.setPreferredSize(new Dimension(0, 150));
        panelInf.setOpaque(false);
        panelInf.setBorder(BorderFactory.createEmptyBorder());
        panelInf.setLayout(null);

        idCabaña = crearLabelIdCabaña();
        panelInf.add(idCabaña);

        field_idCabaña = crearFieldIdCabaña();
        panelInf.add(field_idCabaña);

        b_checkOut = crearBotonCheckOut();
        b_checkOut.addActionListener(this);
        panelInf.add(b_checkOut);

        JScrollPane scroll = scrollInvisible(panelSup);
        scroll.setBounds(0, 0, 300, 450);
        fondo.add(scroll);
        fondo.add(panelInf);
        setContentPane(fondo);
    }

    /**
     * Metodo que crea el boton para regresar a la ventana anterior.
     * @return Devuelve el JButton que cierra la ventana y vuelve a la anterior.
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
     * Metodo que hace uso de otros metodos para cargar las cabañas en el panel.
     * @param listaCabañas ArrayList de Cabañas reservadas.
     * @param panel panelSup donde se cargaran las cabañas.
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
     * Metodo que se encarga de crear el mensaje para indicar el ID de la cabaña que se desalojar.
     * @return JLabel con el mensaje "ID cabaña a desalojar:".
     */
    private JLabel crearLabelIdCabaña(){
        JLabel labelIdCabaña = new JLabel("ID cabaña a desalojar:     ");
        labelIdCabaña.setBounds(25, 10, 130, 20);
        labelIdCabaña.setForeground(Color.BLACK);
        return labelIdCabaña;
    }

    /**
     * Metodo que crea el Field donde se indica el ID de la cabaña que se quiere desalojar.
     * @return JTextField donde se indica el ID de la cabaña que se quiere desalojar.
     */
    private JTextField crearFieldIdCabaña(){
        JTextField fieldIdCabaña = new JTextField();
        fieldIdCabaña.setBounds(150, 10, 50, 20);
        fieldIdCabaña.setBackground(Color.decode("#D9D9D9"));
        fieldIdCabaña.setForeground(Color.BLACK);
        return fieldIdCabaña;
    }

    /**
     * Metodo que crea el boton que recibe la informacion del ID de la cabaña que se quiere desalojar.
     * @return JButton que realiza el checkout de la cabaña.
     */
    private JButton crearBotonCheckOut(){
        JButton botonReservar = crearBoton("#EC9E48");
        botonReservar.setText("Desocupar");
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
        if(event.getSource() == b_regresar){
            dispose();
        }
        if (event.getSource() == b_checkOut){
            try {
                boolean idValido = false;
                int idCabaña = Integer.parseInt(field_idCabaña.getText());
                for (Cabaña cabaña : this.listaCabañas) {
                    if (cabaña.getId() == idCabaña) {
                        cabaña.checkOutCabaña_GUI();
                        JOptionPane.showMessageDialog(null, "CheckOut realizado exitosamente");
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
