package GUI;

import ReservApp.GestorDeClientes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

/**
 * GUI encargada de realizar el registro de un nuevo usuario al sistema.
 */
public final class VentanaSignUp extends VentanaAbstractRA implements ActionListener {

    private JPanel panel;
    private JLabel l_usuario, l_celular, l_contarseña, l_conf_contraseña;
    private JTextField field_usuario, field_celular;
    private JPasswordField field_contraseña, field_conf_contraseña;
    private JButton b_registrasr, b_regresar;
    private GestorDeClientes gestorDeClientes;

    /**
     * Constructor de la ventana que setea sus caracteristicas y carga los paneles.
     * @param gestorDeClientes gestor de clientes el cual se encarga del registro del nuevo usuario en la ventana.
     */
    public VentanaSignUp(GestorDeClientes gestorDeClientes){
        this.gestorDeClientes = gestorDeClientes;
        setTitle("Registro de usuario");

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 800));
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
     * Metodo que crea label con que dice celular.
     * @return Devuelve un Jlabel que dice Celular:.
     */
    private JLabel crearLabelCelular(){
        JLabel labelCelular = new JLabel("Celular: ");
        labelCelular.setBounds(25, 400, 100, 20);
        labelCelular.setForeground(Color.BLACK);
        return labelCelular;
    }

    /**
     * Metodo que crea un Field donde el Cliente debe ingresar su celular.
     * @return JtextField donde el cliente debe ingresar el celular.
     */
    private JTextField crearFieldCelular(){
        JTextField fieldCelular = new JTextField();
        fieldCelular.setBounds(25, 420, 250, 20);
        fieldCelular.setBackground(Color.decode("#D9D9D9"));
        fieldCelular.setForeground(Color.BLACK);
        return fieldCelular;
    }

    /**
     * Metodo que crea label con que dice Contraseña.
     * @return Devuelve un Jlabel que dice Contraseña.
     */
    private JLabel crearLabelContraseña(){
        JLabel labelContraseña = new JLabel("Contraseña: ");
        labelContraseña.setBounds(25, 450, 100, 20);
        labelContraseña.setForeground(Color.BLACK);
        return labelContraseña;
    }

    /**
     * Metodo que crea un Field donde el Cliente debe ingresar su contraseña.
     * @return JPasswordField donde el cliente debe ingresar la contraseña.
     */
    private JPasswordField crearFieldContraseña(){
        JPasswordField fieldContraseña = new JPasswordField();
        fieldContraseña.setBounds(25, 470, 250, 20);
        fieldContraseña.setBackground(Color.decode("#D9D9D9"));
        fieldContraseña.setForeground(Color.BLACK);
        return fieldContraseña;
    }

    /**
     * Metodo que crea label con que dice confirmar contraseña.
     * @return Devuelve un Jlabel que dice  Confirmar Contraseña.
     */
    private JLabel crearLabelConfContraseña(){
        JLabel labelConfContraseña = new JLabel("Confirmar contraseña: ");
        labelConfContraseña.setBounds(25, 500, 150, 20);
        labelConfContraseña.setForeground(Color.BLACK);
        return labelConfContraseña;
    }

    /**
     * Metodo que crea un Field donde el Cliente debe ingresar su confirmacion de contraseña.
     * @return JPasswordField donde el cliente debe ingresar la confirmacion de contraseña.
     */
    private JPasswordField crearFieldConfContraseña(){
        JPasswordField fieldConfContraseña = new JPasswordField();
        fieldConfContraseña.setBounds(25, 520, 250, 20);
        fieldConfContraseña.setBackground(Color.decode("#D9D9D9"));
        fieldConfContraseña.setForeground(Color.BLACK);
        return fieldConfContraseña;
    }

    /**
     * Metodo encargado de crear el boton que registra el nuevo usuario.
     * @return JButton que el usuario puede presionar para registrarse.
     */
    private JButton crearBotonRegistrarse(){
        JButton botonIniciarSesion = crearBoton("#EC9E48");
        botonIniciarSesion.setText("Registrar usuario");
        botonIniciarSesion.setFont(new Font("IBM Plex Sans", Font.BOLD, 15));
        botonIniciarSesion.setBounds(50, 600, 200, 50);
        botonIniciarSesion.setForeground(Color.BLACK);
        return botonIniciarSesion;
    }

    /**
     * Metodo que crea el boton para regresar a la ventana anterior.
     * @return Devuelve el JButton que cierra la ventana y vuelve a la anterior.
     */
    private JButton crearBotonRegresar(){
        JButton botonRegresar = crearBoton("#047994");
        botonRegresar.setText("Regresar");
        botonRegresar.setFont(new Font("IBM Plex Sans", Font.BOLD, 15));
        botonRegresar.setBounds(75, 670, 150, 50);
        botonRegresar.setForeground(Color.BLACK);
        return botonRegresar;
    }

    /**
     * Metodo de la interfaz ActionListener que captura los eventos de la ventana.
     * @param event El evento que ocurrió en la ventana.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == b_registrasr){
            try {
                if (field_usuario.getText().isEmpty() || field_celular.getText().isEmpty() || field_contraseña.getText().isEmpty() || field_conf_contraseña.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");

                } else {
                    String usuarioIngresado = field_usuario.getText();
                    String passwordIngresada = new String(field_contraseña.getPassword());
                    String passwordCongIngresada = new String(field_conf_contraseña.getPassword());
                    try {
                        int celularUsuario = Integer.parseInt(field_celular.getText());
                        if(gestorDeClientes.signUP(usuarioIngresado, celularUsuario, passwordIngresada, passwordCongIngresada)){
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

        if (event.getSource() == b_regresar){
            this.dispose();
            VentanaMenuBienvenida menu = new VentanaMenuBienvenida(this.gestorDeClientes);
            menu.setVisible(true);
        }
    }
}
