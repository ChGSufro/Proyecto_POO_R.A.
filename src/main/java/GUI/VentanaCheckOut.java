package GUI;

import GestionDeArchivos.GestorDeArchivos;
import ReservApp.Cabaña;
import ReservApp.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class VentanaCheckOut extends VentanaAbstractRA implements ActionListener {


    JPanel panelInf, panelSup;
    JLabel idCabaña;
    JTextField field_idCabaña;
    JButton b_checkOut, b_regresar;
    Cliente usuarioIngresado;
    ArrayList<Cabaña> listaCabañas;

    public VentanaCheckOut(ArrayList<Cabaña> listaCabañas, Cliente usuarioIngresado){
        this.usuarioIngresado = usuarioIngresado;
        this.listaCabañas = listaCabañas;
        setTitle("Reserva de cabaña");

        panelSup = new JPanel();
        panelSup.setPreferredSize(new Dimension(0, listaCabañas.size()*250 + 450));
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

        b_checkOut = crearBotonReservar();
        b_checkOut.addActionListener(this);
        panelInf.add(b_checkOut);

        fondo.add(scrollInvisible(panelSup));
        fondo.add(panelInf);
        setContentPane(fondo);
    }

    private JButton crearBotonRegresar(){
        JButton botonRegresar = crearBoton("#047994");
        botonRegresar.setText("Regresar");
        botonRegresar.setFont(new Font("IBM Plex Sans", Font.BOLD, 15));
        botonRegresar.setBounds(75, 350, 150, 50);
        botonRegresar.setForeground(Color.BLACK);
        return botonRegresar;
    }
    private void cargarCabañas(ArrayList<Cabaña> listaCabañas, JPanel panel){
        int posicion = 450;
        for (Cabaña cabaña : listaCabañas){
            panel.add(cabañaTitulo(cabaña, posicion));
            posicion += 20;

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
        JLabel titulo = new JLabel(cabaña.getId() + ")  " + cabaña.getNombre());
        titulo.setFont(new Font("IBM Plex Sans", Font.BOLD, 20));
        titulo.setBounds(25, posicion, 200, 20);
        return titulo;
    }
    private JLabel cabañaImagen(Cabaña cabaña, int posicion){
        JLabel imagen = new JLabel(new GestorDeArchivos().cargarImgIcono("CabañasImagenes/" + cabaña.getId() + ".png", 200, 120));
        imagen.setBackground(new Color(217, 217, 217, 100));//Gris 50% transparente
        imagen.setOpaque(true);//Hago que se vea el fondo
        imagen.setBounds(25, posicion, 200, 120);
        return imagen;
    }
    private JLabel cabañaHaitacion(Cabaña cabaña, int posicion){
        JLabel habitaciones = new JLabel("Habitaciones: " + cabaña.getHabitaciones());
        habitaciones.setBounds(25, posicion, 200, 20);
        return habitaciones;
    }
    private JLabel cabañaBaños(Cabaña cabaña, int posicion){
        JLabel baños = new JLabel("Baños: " + cabaña.getBaños());
        baños.setBounds(25, posicion, 200, 20);
        return baños;
    }
    private JLabel cabañaEstado(Cabaña cabaña, int posicion){
        JLabel precio = new JLabel("Estado ocupada: " + cabaña.getIsOcupada());
        precio.setBounds(25, posicion, 200, 20);
        return precio;
    }
    private JLabel crearLabelIdCabaña(){
        JLabel labelIdCabaña = new JLabel("ID cabaña a reservar: ");
        labelIdCabaña.setBounds(25, 10, 130, 20);
        labelIdCabaña.setForeground(Color.BLACK);
        return labelIdCabaña;
    }
    private JTextField crearFieldIdCabaña(){
        JTextField fieldIdCabaña = new JTextField();
        fieldIdCabaña.setBounds(150, 10, 50, 20);
        fieldIdCabaña.setBackground(Color.decode("#D9D9D9"));
        fieldIdCabaña.setForeground(Color.BLACK);
        return fieldIdCabaña;
    }
    private JButton crearBotonReservar(){
        JButton botonReservar = crearBoton("#EC9E48");
        botonReservar.setText("Desocupar");
        botonReservar.setFont(new Font("IBM Plex Sans", Font.BOLD, 15));
        botonReservar.setBounds(75, 40, 150, 50);
        botonReservar.setForeground(Color.BLACK);
        return botonReservar;
    }

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
                        cabaña.checkOutCabaña_INTERFAZ();
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
