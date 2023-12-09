package GUI;

import ReservApp.Cliente;
import ReservApp.GestorDeClientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * GUI donde el usuario puede modificar sus propios datos.
 */
public final class VentanaModificarUsuario extends VentanaAbstractRA implements ActionListener {

    JPanel panel;
    JLabel l_usuario, l_celular, l_contraseñaActual, l_contraseñaNueva, l_confirmarContraseña;
    JTextField field_usuario, field_celular;
    JComboBox<String> box_modificarUsuario;
    JPasswordField field_contraseñaActual, field_contraseñaNueva, field_confirmarContraseña;
    JButton b_guardar, b_regresar;
    Cliente cliente;
    GestorDeClientes gestorDeClientes;

    /**
     * Construccion de la ventana, donde se establecen sus carecteristicas y se le da como parametro el gestor de clientes y el usuario logeado.
     * @param gestorDeClientes Encargado de gestionar los datos del usuario.
     * @param cliente Cliente logeado actualmente el cual modificará sus datos.
     */
    public VentanaModificarUsuario(Cliente cliente, GestorDeClientes gestorDeClientes){
        this.cliente = cliente;
        this.gestorDeClientes = gestorDeClientes;

        setTitle("Modificar usuario");

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 1000));
        panel.setOpaque(false);
        panel.setLayout(null);

        cargarLogoPrincipal(panel);

        b_regresar = crearBotonRegresar();
        b_regresar.addActionListener(this);
        panel.add(b_regresar);

        b_guardar = crearBotonGuardar();
        b_guardar.addActionListener(this);
        panel.add(b_guardar);

        box_modificarUsuario = crearBoxModificarUsuario();
        box_modificarUsuario.addActionListener(this);
        panel.add(box_modificarUsuario);

        l_usuario = crearLabelUsuario();
        panel.add(l_usuario);

        field_usuario = crearFieldUsuario();
        panel.add(field_usuario);

        l_celular = crearLabelCelular();
        l_celular.setVisible(false);
        panel.add(l_celular);

        field_celular = crearFieldCelular();
        field_celular.setVisible(false);
        panel.add(field_celular);

        l_contraseñaActual = crearLabelContraseñaActual();
        l_contraseñaActual.setVisible(false);
        panel.add(l_contraseñaActual);

        field_contraseñaActual = crearFieldContraseñaActual();
        field_contraseñaActual.setVisible(false);
        panel.add(field_contraseñaActual);

        l_contraseñaNueva = crearLabelContraseñaNueva();
        l_contraseñaNueva.setVisible(false);
        panel.add(l_contraseñaNueva);

        field_contraseñaNueva = crearFieldContraseñaNueva();
        field_contraseñaNueva.setVisible(false);
        panel.add(field_contraseñaNueva);

        l_confirmarContraseña = crearLabelConfirmarContraseña();
        l_confirmarContraseña.setVisible(false);
        panel.add(l_confirmarContraseña);

        field_confirmarContraseña = crearFieldConfirmarContraseña();
        field_confirmarContraseña.setVisible(false);
        panel.add(field_confirmarContraseña);

        fondo.add(scrollInvisible(panel));
        setContentPane(fondo);
    }

    /**
     * Metodo que crea el boton que recibe la informacion del ID de la cabaña que se quiere arrendar.
     * @return JButton que realiza la reserva de la cabaña.
     */
    private JButton crearBotonRegresar(){
        JButton botonRegresar = crearBoton("#047994");
        botonRegresar.setText("Regresar");
        botonRegresar.setBounds(75, 350, 150, 50);
        botonRegresar.setFont(new Font("IBM Plex Sans ", Font.BOLD, 15));
        botonRegresar.setForeground(Color.BLACK);
        return botonRegresar;
    }

    /**
     * Metodo que crea el boton el cual guardará los cambios del usuario en el objeto Cliente.
     * @return JButton el cual realiza la modificacion de los datos del usuario.
     */
    private JButton crearBotonGuardar(){
        JButton botonGuardar = crearBoton("#EC9E48");
        botonGuardar.setText("Guardar");
        botonGuardar.setBounds(75, 420, 150, 50);
        botonGuardar.setFont(new Font("IBM Plex Sans", Font.BOLD, 15));
        botonGuardar.setForeground(Color.BLACK);
        return botonGuardar;
    }

    /**
     * Metodo que crea el JComboBox que indica dato que el usuario quiera modificar.
     * @return JComboBox el cual solo muestra los Labels y Fields del dato que el usuario quiera modificar.
     */
    private JComboBox<String> crearBoxModificarUsuario(){
        JComboBox<String> boxModificarUsuario = new JComboBox<>();
        boxModificarUsuario.setBounds(25, 500, 250, 20);
        boxModificarUsuario.setBackground(Color.decode("#D9D9D9"));
        boxModificarUsuario.setForeground(Color.BLACK);

        boxModificarUsuario.addItem("Nombre Usuario:");
        boxModificarUsuario.addItem("Celular");
        boxModificarUsuario.addItem("Contraseña");
        return boxModificarUsuario;
    }

    /**
     * Metodo que crea label con que dice usuario.
     * @return Devuelve un Jlabel que dice usuario.
     */
    private JLabel crearLabelUsuario(){
        JLabel labelUsuario = new JLabel("Usuario: ");
        labelUsuario.setBounds(25, 550, 100, 20);
        labelUsuario.setForeground(Color.BLACK);
        return labelUsuario;
    }

    /**
     * Metodo que crea un Field donde el Cliente debe ingresar su Usuario.
     * @return JtextField donde el cliente debe ingresar el usuario.
     */
    private JTextField crearFieldUsuario(){
        JTextField fieldUsuario = new JTextField(cliente.getUsuario());
        fieldUsuario.setBounds(25, 570, 250, 20);
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
        labelCelular.setBounds(25, 550, 100, 20);
        labelCelular.setForeground(Color.BLACK);
        return labelCelular;
    }

    /**
     * Metodo que crea un Field donde el Cliente debe ingresar su celular.
     * @return JtextField donde el cliente debe ingresar el celular.
     */
    private JTextField crearFieldCelular(){
        JTextField fieldCelular = new JTextField(Integer.toString(this.cliente.getCelular()));
        fieldCelular.setBounds(25, 570, 250, 20);
        fieldCelular.setBackground(Color.decode("#D9D9D9"));
        fieldCelular.setForeground(Color.BLACK);
        return fieldCelular;
    }

    /**
     * Metodo que crea label con que dice Contraseña actual.
     * @return Devuelve un Jlabel que dice Contraseña actual.
     */
    private JLabel crearLabelContraseñaActual(){
        JLabel labelContraseñaActual = new JLabel("Contraseña actual: ");
        labelContraseñaActual.setBounds(25, 550, 150, 20);
        labelContraseñaActual.setForeground(Color.BLACK);
        return labelContraseñaActual;
    }

    /**
     * Metodo que crea un Field donde el Cliente debe ingresar su contraseña actual.
     * @return JPasswordField donde el cliente debe ingresar la contraseña actual.
     */
    private JPasswordField crearFieldContraseñaActual(){
        JPasswordField fieldContraseñaActual = new JPasswordField();
        fieldContraseñaActual.setBounds(25, 570, 250, 20);
        fieldContraseñaActual.setBackground(Color.decode("#D9D9D9"));
        fieldContraseñaActual.setForeground(Color.BLACK);
        return fieldContraseñaActual;
    }
    /**
     * Metodo que crea label con que dice Contraseña Nueva.
     * @return Devuelve un Jlabel que dice  Confirmar Contraseña.
     */
    private JLabel crearLabelContraseñaNueva(){
        JLabel labelContraseñaNueva = new JLabel("Contraseña nueva: ");
        labelContraseñaNueva.setBounds(25, 600, 130, 20);
        labelContraseñaNueva.setForeground(Color.BLACK);
        return labelContraseñaNueva;
    }

    /**
     * Metodo que crea un Field donde el Cliente debe ingresar su confirmacion de contraseña.
     * @return JPasswordField donde el cliente debe ingresar la confirmacion de contraseña.
     */
    private JPasswordField crearFieldContraseñaNueva(){
        JPasswordField fieldContraseñaNueva = new JPasswordField();
        fieldContraseñaNueva.setBounds(25, 620, 250, 20);
        fieldContraseñaNueva.setBackground(Color.decode("#D9D9D9"));
        fieldContraseñaNueva.setForeground(Color.BLACK);
        return fieldContraseñaNueva;
    }

    /**
     * Metodo que crea label con que dice confirmar contraseña.
     * @return Devuelve un Jlabel que dice  Confirmar Contraseña.
     */
    private JLabel crearLabelConfirmarContraseña(){
        JLabel labelConfirmarContraseña = new JLabel("Confirmar contraseña: ");
        labelConfirmarContraseña.setBounds(25, 650, 150, 20);
        labelConfirmarContraseña.setForeground(Color.BLACK);
        return labelConfirmarContraseña;
    }

    /**
     * Metodo que crea un Field donde el usuario debe ingresar su confirmacion de contraseña.
     * @return JPasswordField donde el usuario debe ingresar la confirmacion de contraseña.
     */
    private JPasswordField crearFieldConfirmarContraseña(){
        JPasswordField fieldConfirmarContraseña = new JPasswordField();
        fieldConfirmarContraseña.setBounds(25, 670, 250, 20);
        fieldConfirmarContraseña.setBackground(Color.decode("#D9D9D9"));
        fieldConfirmarContraseña.setForeground(Color.BLACK);
        return fieldConfirmarContraseña;
    }

    /**
     * Metodo de la interfaz ActionListener que captura los eventos de la ventana.
     * @param event El evento que ocurrió en la ventana.
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == box_modificarUsuario){
            if (Objects.equals(box_modificarUsuario.getSelectedItem(), "Nombre Usuario:")){
                l_usuario.setVisible(true);
                field_usuario.setVisible(true);
                l_celular.setVisible(false);
                field_celular.setVisible(false);
                l_contraseñaActual.setVisible(false);
                field_contraseñaActual.setVisible(false);
                l_contraseñaNueva.setVisible(false);
                field_contraseñaNueva.setVisible(false);
                l_confirmarContraseña.setVisible(false);
                field_confirmarContraseña.setVisible(false);

            } else if (Objects.equals(box_modificarUsuario.getSelectedItem(), "Celular")){
                l_usuario.setVisible(false);
                field_usuario.setVisible(false);
                l_celular.setVisible(true);
                field_celular.setVisible(true);
                l_contraseñaActual.setVisible(false);
                field_contraseñaActual.setVisible(false);
                l_contraseñaNueva.setVisible(false);
                field_contraseñaNueva.setVisible(false);
                l_confirmarContraseña.setVisible(false);
                field_confirmarContraseña.setVisible(false);

            }
            else if (Objects.equals(box_modificarUsuario.getSelectedItem(), "Contraseña")){
                l_usuario.setVisible(false);
                field_usuario.setVisible(false);
                l_celular.setVisible(false);
                field_celular.setVisible(false);
                l_contraseñaActual.setVisible(true);
                field_contraseñaActual.setVisible(true);
                l_contraseñaNueva.setVisible(true);
                field_contraseñaNueva.setVisible(true);
                l_confirmarContraseña.setVisible(true);
                field_confirmarContraseña.setVisible(true);
            }

        }if (event.getSource() == b_guardar){
            if (Objects.equals(box_modificarUsuario.getSelectedItem(), "Nombre Usuario:")){
                //Campos no vacios
                if (field_usuario.getText().replace(" ", "").isEmpty()){
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre de usuario valido");
                }
                else{
                    gestorDeClientes.modificarNombreUsuario(cliente, field_usuario.getText());
                    JOptionPane.showMessageDialog(null, "Nombre de usuario modificado correctamente");
                }
            }else if (Objects.equals(box_modificarUsuario.getSelectedItem(), "Celular")){
                //Campos no vacios
                if (field_celular.getText().replace(" ", "").isEmpty()){
                    JOptionPane.showMessageDialog(null, "Ingrese un celular valido");
                }
                else{
                    try{
                        gestorDeClientes.modificarCelular(cliente, Integer.parseInt(field_celular.getText()));
                        JOptionPane.showMessageDialog(null, "Celular modificado correctamente");
                    }catch (NumberFormatException error){
                        JOptionPane.showMessageDialog(null, "Ingrese un celular valido");
                    }
                }
            }else if (Objects.equals(box_modificarUsuario.getSelectedItem(), "Contraseña")){
                //Campos no vacios
                if (field_contraseñaActual.getText().replace(" ", "").isEmpty()||
                        field_contraseñaNueva.getText().replace(" ", "").isEmpty() ||
                        field_confirmarContraseña.getText().replace(" ", "").isEmpty()){
                    JOptionPane.showMessageDialog(null, "Ingrese todos los campos");
                }
                else{
                    if (!gestorDeClientes.contraseñaCorrecta(cliente, field_contraseñaActual.getText())){
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                        return;
                    }
                    if (gestorDeClientes.modificarContraseña(cliente, field_contraseñaNueva.getText(), field_confirmarContraseña.getText())) {
                        JOptionPane.showMessageDialog(null, "Contraseña modificada correctamente");
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "Las nuevas contraseñas no coinciden");
                    }
                }
            }

        if (event.getSource() == b_regresar){
            dispose();
        }
    }
}
