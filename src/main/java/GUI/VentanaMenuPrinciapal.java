package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMenuPrinciapal extends VentanaAbstractRA implements ActionListener {

    JPanel panel;
    JButton b_mostrarCabañas, b_arrendarCabaña, b_verReservas, b_cerrarSesion;

    public VentanaMenuPrinciapal(){
        setTitle("Resev-App");

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 0));
        panel.setOpaque(false);
        panel.setLayout(null);

        cargarLogoPrincipal(panel);

        b_mostrarCabañas = crearBotonMostrarCabañas();
        b_mostrarCabañas.addActionListener(this);
        panel.add(b_mostrarCabañas);

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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
