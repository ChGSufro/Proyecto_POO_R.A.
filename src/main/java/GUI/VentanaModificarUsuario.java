package GUI;

import ReservApp.Cliente;
import ReservApp.GestorDeClientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaModificarUsuario extends VentanaAbstractRA implements ActionListener {

    public static void main(String[] args) {
        VentanaModificarUsuario ventanaModificarUsuario = new VentanaModificarUsuario(new GestorDeClientes().getListaClientes().get(0));
        ventanaModificarUsuario.setVisible(true);
    }

    JPanel panel;
    JLabel l_usuario, l_celular, l_contraseñaActual, l_contraseñaNueva, l_confirmarContraseña;
    JTextField field_usuario, field_celular;
    JComboBox<String> box_modificarUsuario;
    JPasswordField field_contraseñaActual, field_contraseñaNueva, field_confirmarContraseña;
    JButton b_guardar, b_regresar;
    Cliente cliente;

    public VentanaModificarUsuario(Cliente cliente){
        this.cliente = cliente;

        setTitle("Modificar usuario");

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 1000));
        panel.setOpaque(false);
        panel.setLayout(null);

        cargarLogoPrincipal(panel);

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

        b_guardar = crearBotonGuardar();
        b_guardar.addActionListener(this);
        panel.add(b_guardar);

        b_regresar = crearBotonRegresar();
        b_regresar.addActionListener(this);
        panel.add(b_regresar);

        fondo.add(scrollInvisible(panel));
        setContentPane(fondo);
    }

    private JLabel crearLabelUsuario(){
        JLabel labelUsuario = new JLabel("Usuario: ");
        labelUsuario.setBounds(50, 650, 130, 25);
        labelUsuario.setForeground(Color.BLACK);
        return labelUsuario;
    }

    private JTextField crearFieldUsuario(){
        JTextField fieldUsuario = new JTextField(cliente.getUsuario());
        fieldUsuario.setBounds(50, 680, 200, 25);
        fieldUsuario.setBackground(Color.decode("#D9D9D9"));
        fieldUsuario.setForeground(Color.BLACK);
        return fieldUsuario;
    }

    private JLabel crearLabelCelular(){
        JLabel labelCelular = new JLabel("Celular: ");
        labelCelular.setBounds(50, 650, 130, 25);
        labelCelular.setForeground(Color.BLACK);
        return labelCelular;
    }

    private JTextField crearFieldCelular(){
        JTextField fieldCelular = new JTextField(Integer.toString(this.cliente.getCelular()));
        fieldCelular.setBounds(50, 680, 200, 25);
        fieldCelular.setBackground(Color.decode("#D9D9D9"));
        fieldCelular.setForeground(Color.BLACK);
        return fieldCelular;
    }

    private JLabel crearLabelContraseñaActual(){
        JLabel labelContraseñaActual = new JLabel("Contraseña actual: ");
        labelContraseñaActual.setBounds(50, 650, 130, 25);
        labelContraseñaActual.setForeground(Color.BLACK);
        return labelContraseñaActual;
    }

    private JPasswordField crearFieldContraseñaActual(){
        JPasswordField fieldContraseñaActual = new JPasswordField();
        fieldContraseñaActual.setBounds(50, 680, 200, 25);
        fieldContraseñaActual.setBackground(Color.decode("#D9D9D9"));
        fieldContraseñaActual.setForeground(Color.BLACK);
        return fieldContraseñaActual;
    }

    private JLabel crearLabelContraseñaNueva(){
        JLabel labelContraseñaNueva = new JLabel("Contraseña nueva: ");
        labelContraseñaNueva.setBounds(50, 720, 130, 25);
        labelContraseñaNueva.setForeground(Color.BLACK);
        return labelContraseñaNueva;
    }

    private JPasswordField crearFieldContraseñaNueva(){
        JPasswordField fieldContraseñaNueva = new JPasswordField();
        fieldContraseñaNueva.setBounds(50, 740, 200, 25);
        fieldContraseñaNueva.setBackground(Color.decode("#D9D9D9"));
        fieldContraseñaNueva.setForeground(Color.BLACK);
        return fieldContraseñaNueva;
    }

    private JLabel crearLabelConfirmarContraseña(){
        JLabel labelConfirmarContraseña = new JLabel("Confirmar contraseña: ");
        labelConfirmarContraseña.setBounds(50, 780, 130, 25);
        labelConfirmarContraseña.setForeground(Color.BLACK);
        return labelConfirmarContraseña;
    }

    private JPasswordField crearFieldConfirmarContraseña(){
        JPasswordField fieldConfirmarContraseña = new JPasswordField();
        fieldConfirmarContraseña.setBounds(50, 810, 200, 25);
        fieldConfirmarContraseña.setBackground(Color.decode("#D9D9D9"));
        fieldConfirmarContraseña.setForeground(Color.BLACK);
        return fieldConfirmarContraseña;
    }

private JComboBox<String> crearBoxModificarUsuario(){
        JComboBox<String> boxModificarUsuario = new JComboBox<>();
        boxModificarUsuario.setBounds(50, 600, 300, 25);
        boxModificarUsuario.setBackground(Color.decode("#D9D9D9"));
        boxModificarUsuario.setForeground(Color.BLACK);

        boxModificarUsuario.addItem("Nombre Usuario:");
        boxModificarUsuario.addItem("Celular");
        boxModificarUsuario.addItem("Contraseña");
        return boxModificarUsuario;
    }

    private JButton crearBotonRegresar(){
        JButton botonRegresar = crearBoton("#047994");
        botonRegresar.setText("Regresar");
        botonRegresar.setBounds(125, 430, 150, 50);
        botonRegresar.setForeground(Color.BLACK);
        return botonRegresar;
    }

    private JButton crearBotonGuardar(){
        JButton botonGuardar = crearBoton("#EC9E48");
        botonGuardar.setText("Guardar");
        botonGuardar.setBounds(125, 500, 150, 50);
        botonGuardar.setForeground(Color.BLACK);
        return botonGuardar;
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == box_modificarUsuario){
            if (box_modificarUsuario.getSelectedItem().equals("Nombre Usuario:")){
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
            }
            else if (box_modificarUsuario.getSelectedItem().equals("Celular")){
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
            else if (box_modificarUsuario.getSelectedItem().equals("Contraseña")){
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
        }

    }
}
