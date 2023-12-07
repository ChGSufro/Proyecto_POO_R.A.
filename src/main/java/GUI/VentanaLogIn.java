package GUI;

import ReservApp.Cliente;
import ReservApp.GestorDeClientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogIn extends VentanaAbstractRA implements ActionListener {

    JPanel panel;
    JLabel l_usuario, l_contrasena;
    JTextField field_usuario;
    JPasswordField field_contrasena;
    JButton b_iniciarSesion, b_regresar;
    private GestorDeClientes gestorDeClientes;

    public VentanaLogIn(GestorDeClientes gestorDeClientes){
        this.gestorDeClientes = gestorDeClientes;
        setTitle("Inicio de sesión");

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 800));
        panel.setOpaque(false);
        panel.setLayout(null);

        cargarLogoPrincipal(panel);


        l_usuario = crearLabelUsuario();
        panel.add(l_usuario);
        field_usuario = crearFieldUsuario();
        panel.add(field_usuario);

        l_contrasena = cargarLabelContrasena();
        panel.add(l_contrasena);
        field_contrasena = cargarFieldContrasena();
        panel.add(field_contrasena);

        b_iniciarSesion = crearBotonIniciarSesion();
        b_iniciarSesion.addActionListener(this);
        panel.add(b_iniciarSesion);

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

    private JLabel cargarLabelContrasena(){
        JLabel labelContrasena = new JLabel("Contraseña: ");
        labelContrasena.setBounds(50, 480, 300, 25);
        labelContrasena.setForeground(Color.BLACK);
        return labelContrasena;
    }

    private JPasswordField cargarFieldContrasena(){
        JPasswordField fieldContrasena = new JPasswordField();
        fieldContrasena.setBounds(50, 510, 300, 25);
        fieldContrasena.setBackground(Color.decode("#D9D9D9"));
        fieldContrasena.setForeground(Color.BLACK);
        return fieldContrasena;
    }

    private JButton crearBotonIniciarSesion(){
        JButton botonIniciarSesion = crearBoton("#EC9E48");
        botonIniciarSesion.setText("Iniciar sesión");
        botonIniciarSesion.setFont(new Font("IBM Plex Sans", Font.BOLD, 20));
        botonIniciarSesion.setBounds(100, 600, 200, 50);
        botonIniciarSesion.setForeground(Color.BLACK);
        return botonIniciarSesion;
    }

    private JButton crearBotonRegresar(){
        JButton botonRegresar = crearBoton("#047994");
        botonRegresar.setText("Regresar");
        botonRegresar.setFont(new Font("IBM Plex Sans", Font.BOLD, 20));
        botonRegresar.setBounds(125, 675, 150, 50);
        botonRegresar.setForeground(Color.BLACK);
        return botonRegresar;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_iniciarSesion){
            if (field_usuario.getText().isEmpty() || field_contrasena.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los datos");
            } else {
                if (this.gestorDeClientes.validarUsuario(field_usuario.getText(), new String(field_contrasena.getPassword() ) )) {
                    this.dispose();
                    Cliente usuario_activo = this.gestorDeClientes.loginUsario(field_usuario.getText(), new String(field_contrasena.getPassword()));
                    VentanaMenuPrincipal menu = new VentanaMenuPrincipal(this.gestorDeClientes, usuario_activo);
                    menu.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos");
                }
            }
        }

        if (e.getSource() == b_regresar){
            this.dispose();
            VentanaMenuBienvenida menu = new VentanaMenuBienvenida(this.gestorDeClientes);
            menu.setVisible(true);
        }
    }
}
