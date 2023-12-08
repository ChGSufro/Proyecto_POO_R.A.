package GUI;

import ReservApp.Cliente;
import ReservApp.GestorDeClientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI que se encarga del Login del sistema.
 */
public final class VentanaLogIn extends VentanaAbstractRA implements ActionListener {

    JPanel panel;
    JLabel l_usuario, l_contrasena;
    JTextField field_usuario;
    JPasswordField field_contrasena;
    JButton b_iniciarSesion, b_regresar;
    private GestorDeClientes gestorDeClientes;

    /**
     * Construccion de la ventana, donde se establecen sus carecteristicas y se le da como parametro el gestor de clientes.
     * @param gestorDeClientes encargado de gestionar el login del usuario.
     */
    public VentanaLogIn(GestorDeClientes gestorDeClientes){
        this.gestorDeClientes = gestorDeClientes;
        setTitle("Inicio de sesión");

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 700));
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

    /**
     * Metodo que crea label con que dice usuario.
     * @return Devuelve un Jlabel que dice usuario.
     */
    private JLabel crearLabelUsuario(){
        JLabel labelUsuario = new JLabel("Usuario: ");
        labelUsuario.setBounds(25, 350, 100, 20);
        labelUsuario.setForeground(Color.BLACK);
        return labelUsuario;
    }

    /**
     * Metodo que crea un Field donde el Cliente debe ingresar su Usuario.
     * @return JtextField donde el cliente debe ingresar el usuario.
     */
    private JTextField crearFieldUsuario(){
        JTextField fieldUsuario = new JTextField();
        fieldUsuario.setBounds(25, 370, 250, 20);
        fieldUsuario.setBackground(Color.decode("#D9D9D9"));
        fieldUsuario.setForeground(Color.BLACK);
        return fieldUsuario;
    }

    /**
     * Metodo que crea label con que dice Contraseña.
     * @return Devuelve un Jlabel que dice Contraseña.
     */
    private JLabel cargarLabelContrasena(){
        JLabel labelContrasena = new JLabel("Contraseña: ");
        labelContrasena.setBounds(25, 400, 100, 20);
        labelContrasena.setForeground(Color.BLACK);
        return labelContrasena;
    }

    /**
     * Metodo que crea un Field donde el Cliente debe ingresar su contraseña.
     * @return JPasswordField donde el cliente debe ingresar la contraseña.
     */
    private JPasswordField cargarFieldContrasena(){
        JPasswordField fieldContrasena = new JPasswordField();
        fieldContrasena.setBounds(25, 420, 250, 20);
        fieldContrasena.setBackground(Color.decode("#D9D9D9"));
        fieldContrasena.setForeground(Color.BLACK);
        return fieldContrasena;
    }

    /**
     * Metodo que crea el boton para iniciar sesion en el sistema.
     * @return Devuelve el JButton que realiza el inicio de sesion al sistema.
     */
    private JButton crearBotonIniciarSesion(){
        JButton botonIniciarSesion = crearBoton("#EC9E48");
        botonIniciarSesion.setText("Iniciar sesión");
        botonIniciarSesion.setFont(new Font("IBM Plex Sans", Font.BOLD, 15));
        botonIniciarSesion.setBounds(75, 500, 150, 50);
        botonIniciarSesion.setForeground(Color.BLACK);
        return botonIniciarSesion;
    }


    private JButton crearBotonRegresar(){
        JButton botonRegresar = crearBoton("#047994");
        botonRegresar.setText("Regresar");
        botonRegresar.setFont(new Font("IBM Plex Sans", Font.BOLD, 15));
        botonRegresar.setBounds(75, 570, 150, 50);
        botonRegresar.setForeground(Color.BLACK);
        return botonRegresar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_iniciarSesion){
            if (field_usuario.getText().isEmpty() || field_contrasena.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los datos");

            } else {
                if (this.gestorDeClientes.validarUsuario(field_usuario.getText(), new String(field_contrasena.getPassword()))) {
                    this.dispose();
                    Cliente usuario_activo = this.gestorDeClientes.loginUsario(field_usuario.getText());
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
