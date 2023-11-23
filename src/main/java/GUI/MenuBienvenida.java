package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class MenuBienvenida extends VentanaRA implements ActionListener {

    JPanel panel;
    JButton b_registroSesion, b_inicioSesion;

    public MenuBienvenida(){
        setTitle("Bienvenido");

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
        boton.setText("SingUp  ->");
        boton.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));
        boton.setBounds(100, 450, 350, 90);
        return boton;
    }
    public void goRegistroActionPerformed(ActionEvent event){
        this.dispose();
        //new RegistroUsuario();
    }

    public JButton b_inicioSesion(){
        JButton boton = crearBoton("#047994");
        boton.setText("LogIn    ->");
        boton.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));
        boton.setBounds(100, 570, 350, 90);
        return boton;
    }
    public void goInicioActionPerformedInicio(ActionEvent event){
        this.dispose();
        //new InicioSesion();
    }


    //Eventos de action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_registroSesion) {
            setVisible(false);
            dispose();
        } else if (e.getSource() == b_inicioSesion) {
            setVisible(false);
            dispose();
        }
    }


}
