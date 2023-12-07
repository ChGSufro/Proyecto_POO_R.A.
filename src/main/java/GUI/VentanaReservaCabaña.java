package GUI;

import GestionDeArchivos.GestorDeArchivos;
import ReservApp.Cabaña;
import ReservApp.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaReservaCabaña extends VentanaAbstractRA implements ActionListener {

    JPanel panelInf, panelSup;
    JLabel idCabaña;
    JTextField field_idCabaña;
    JButton b_reservar, b_regresar;
    ArrayList<Cabaña> listaCabañas;
    Cliente usuarioIngresado;

    public VentanaReservaCabaña(ArrayList<Cabaña> listaCabañas, Cliente usuarioIngresado){
        this.usuarioIngresado = usuarioIngresado;
        this.listaCabañas = listaCabañas;
        setTitle("Reserva de cabaña");

        panelSup = new JPanel();
        panelSup.setPreferredSize(new Dimension(0, 1000));
        panelSup.setOpaque(false);
        panelSup.setBorder(BorderFactory.createEmptyBorder());
        panelSup.setLayout(null);

        cargarLogoPrincipal(panelSup);

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

        b_regresar = crearBotonRegresar();
        b_regresar.addActionListener(this);
        panelInf.add(b_regresar);

        fondo.add(scrollInvisible(panelSup));
        fondo.add(panelInf);
        setContentPane(fondo);
    }

    private JLabel crearLabelIdCabaña(){
        JLabel labelIdCabaña = new JLabel("ID cabaña a reservar: ");
        labelIdCabaña.setBounds(50, 20, 130, 25);
        labelIdCabaña.setForeground(Color.BLACK);
        return labelIdCabaña;
    }

    private JTextField crearFieldIdCabaña(){
        JTextField fieldIdCabaña = new JTextField();
        fieldIdCabaña.setBounds(180, 20, 50, 25);
        fieldIdCabaña.setBackground(Color.decode("#D9D9D9"));
        fieldIdCabaña.setForeground(Color.BLACK);
        return fieldIdCabaña;
    }

    private JButton crearBotonReservar(){
        JButton botonReservar = crearBoton("#EC9E48");
        botonReservar.setText("Reservar");
        botonReservar.setBounds(250, 10, 100, 50);
        botonReservar.setForeground(Color.BLACK);
        return botonReservar;
    }

    private void cargarCabañas(ArrayList<Cabaña> listaCabañas, JPanel panel){
        int posicion = 400;
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
        JLabel titulo = new JLabel(cabaña.getId() + ")\t" + cabaña.getNombre());
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
        JButton botonRegresar = crearBoton("#047994");
        botonRegresar.setText("Regresar");
        botonRegresar.setBounds(125, 70, 150, 50);
        botonRegresar.setForeground(Color.BLACK);
        return botonRegresar;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(b_regresar)){
            dispose();
        }

        if (e.getSource().equals(b_reservar)){
            try {
                boolean idValido = false;
                int idCabaña = Integer.parseInt(field_idCabaña.getText());
                for (Cabaña cabaña : this.listaCabañas) {
                    if (cabaña.getId() == idCabaña) {
                        cabaña.reservarCabaña_INTERFAZ(this.usuarioIngresado);
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
