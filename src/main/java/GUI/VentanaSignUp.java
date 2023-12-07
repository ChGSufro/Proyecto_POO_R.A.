package GUI;

import ReservApp.GestorDeCabañas;
import ReservApp.GestorDeClientes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class VentanaSignUp extends VentanaAbstractRA implements ActionListener {

    JPanel panel;
    JLabel l_usuario, l_celular, l_contarseña, l_conf_contraseña;
    JTextField field_usuario, field_celular;
    JPasswordField field_contraseña, field_conf_contraseña;
    JButton b_registrasr, b_regresar;

    private GestorDeClientes gestorDeClientes;
    private GestorDeCabañas gestorDeCabañas;


    public VentanaSignUp(GestorDeClientes gestorDeClientes){
        this.gestorDeClientes = gestorDeClientes;
        setTitle("Registro de usuario");

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 900));
        panel.setOpaque(false);
        panel.setLayout(null);

        cargarLogoPrincipal(panel);

        l_usuario = crearLabelUsuario();
        panel.add(l_usuario);
        field_usuario = crearFieldUsuario();
        panel.add(field_usuario);

        l_celular = crearLabelCelular();
        panel.add(l_celular);
        field_celular = crearFieldCelular();
        panel.add(field_celular);

        l_contarseña = crearLabelContraseña();
        panel.add(l_contarseña);
        field_contraseña = crearFieldContraseña();
        panel.add(field_contraseña);

        l_conf_contraseña = crearLabelConfContraseña();
        panel.add(l_conf_contraseña);
        field_conf_contraseña = crearFieldConfContraseña();
        panel.add(field_conf_contraseña);

        b_registrasr = crearBotonRegistrarse();
        b_registrasr.addActionListener(this);
        panel.add(b_registrasr);

        b_regresar = crearBotonRegresar();
        b_regresar.addActionListener(this);
        panel.add(b_regresar);


        fondo.add(scrollInvisible(panel));
        setContentPane(fondo);
    }

    private JLabel crearLabelUsuario(){
        JLabel labelUsuario = new JLabel("Usuario: ");
        labelUsuario.setBounds(50, 420, 300, 25);
        labelUsuario.setForeground(Color.BLACK);
        return labelUsuario;
    }

    private JTextField crearFieldUsuario(){
        JTextField fieldUsuario = new JTextField();
        fieldUsuario.setBounds(50, 450, 300, 25);
        fieldUsuario.setBackground(Color.decode("#D9D9D9"));
        fieldUsuario.setForeground(Color.BLACK);
        return fieldUsuario;
    }

    private JLabel crearLabelCelular(){
        JLabel labelCelular = new JLabel("Celular: ");
        labelCelular.setBounds(50, 480, 300, 25);
        labelCelular.setForeground(Color.BLACK);
        return labelCelular;
    }

    private JTextField crearFieldCelular(){
        JTextField fieldCelular = new JTextField();
        fieldCelular.setBounds(50, 510, 300, 25);
        fieldCelular.setBackground(Color.decode("#D9D9D9"));
        fieldCelular.setForeground(Color.BLACK);
        return fieldCelular;
    }

    private JLabel crearLabelContraseña(){
        JLabel labelContraseña = new JLabel("Contraseña: ");
        labelContraseña.setBounds(50, 540, 300, 25);
        labelContraseña.setForeground(Color.BLACK);
        return labelContraseña;
    }

    private JPasswordField crearFieldContraseña(){
        JPasswordField fieldContraseña = new JPasswordField();
        fieldContraseña.setBounds(50, 570, 300, 25);
        fieldContraseña.setBackground(Color.decode("#D9D9D9"));
        fieldContraseña.setForeground(Color.BLACK);
        return fieldContraseña;
    }

    private JLabel crearLabelConfContraseña(){
        JLabel labelConfContraseña = new JLabel("Confirmar contraseña: ");
        labelConfContraseña.setBounds(50, 600, 300, 25);
        labelConfContraseña.setForeground(Color.BLACK);
        return labelConfContraseña;
    }

    private JPasswordField crearFieldConfContraseña(){
        JPasswordField fieldConfContraseña = new JPasswordField();
        fieldConfContraseña.setBounds(50, 630, 300, 25);
        fieldConfContraseña.setBackground(Color.decode("#D9D9D9"));
        fieldConfContraseña.setForeground(Color.BLACK);
        return fieldConfContraseña;
    }

    private JButton crearBotonRegistrarse(){
        JButton botonIniciarSesion = crearBoton("#EC9E48");
        botonIniciarSesion.setText("Registrar usuario");
        botonIniciarSesion.setFont(new Font("IBM Plex Sans", Font.BOLD, 20));
        botonIniciarSesion.setBounds(100, 725, 200, 50);
        botonIniciarSesion.setForeground(Color.BLACK);
        return botonIniciarSesion;
    }

    private JButton crearBotonRegresar(){
        JButton botonRegresar = crearBoton("#047994");
        botonRegresar.setText("Regresar");
        botonRegresar.setFont(new Font("IBM Plex Sans", Font.BOLD, 20));
        botonRegresar.setBounds(125, 800, 150, 50);
        botonRegresar.setForeground(Color.BLACK);
        return botonRegresar;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_registrasr){
            try {
                if (field_usuario.getText().isEmpty() || field_celular.getText().isEmpty() || field_contraseña.getText().isEmpty() || field_conf_contraseña.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");

                } else {
                    String usuarioIngresado = field_usuario.getText();
                    String passwordIngresada = new String(field_contraseña.getPassword());
                    String passwordCongIngresada = new String(field_conf_contraseña.getPassword());
                    try {
                        int celularUsuario = Integer.parseInt(field_celular.getText());
                        if(gestorDeClientes.singUP(usuarioIngresado, celularUsuario, passwordIngresada, passwordCongIngresada)){
                            JOptionPane.showMessageDialog(null, "Usuario creado Existosamente");
                            this.gestorDeClientes.registrarClientesEnArchivoJson();
                            this.dispose();
                            VentanaMenuBienvenida menu = new VentanaMenuBienvenida(this.gestorDeClientes);
                            menu.setVisible(true);
                        }
                    }catch (NumberFormatException exception){
                        JOptionPane.showMessageDialog(null, "Ingrese valores validos");
                    }
                }
            }catch (RuntimeException exception){
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
            }
        }

        if (e.getSource() == b_regresar){
            this.dispose();
            VentanaMenuBienvenida menu = new VentanaMenuBienvenida(this.gestorDeClientes);
            menu.setVisible(true);
        }
    }
}
