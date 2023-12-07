package GUI;

import GestionDeArchivos.GestorDeArchivos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public abstract class VentanaAbstractRA extends JFrame {

    protected JPanel fondo;
    protected JButton b_usuario;

    public VentanaAbstractRA(){
        setSize(new Dimension(400, 700));
        setIconImage(new GestorDeArchivos().cargarImgIcono("Logos", "iconoApp", 500, 500).getImage());

        fondo = panelConGradiente();
        fondo.setPreferredSize(new Dimension(0, 0));
        fondo.setLayout(new BoxLayout(fondo, BoxLayout.PAGE_AXIS));

        setResizable(false);//Bloqueo boton de maximizar
        setLocationRelativeTo(null);//Posiciona la ventana en el centro
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Detiene el programa cuando se cierra la ventana
    }

    public JPanel panelConGradiente(){
        return new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, Color.decode("#FFBC61"), 0, getHeight(), Color.decode("#FFFFFF"));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }
    public JButton crearBoton(String color){//Crea un boton con un texto, color de fondo y tamaño de letra
        JButton boton = new JButton(){

            @Override//Pinto un fondo con efecto de rectangulo redondeado
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setPaint(Color.decode(color));//Establezco el color del fondo
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));//Pinto un rectangulo de bordes redondeados
                g2.dispose();
                super.paintComponent(g);//Pinto el original por encima para que se vean todas las propiedades
            }
        };
        boton.setContentAreaFilled(false); //Hago que no se genere el fondo por defecto
        boton.setBorderPainted(false); //Elimina bordes por defecto
        boton.setFocusPainted(false); //Elimina el efecto de click
        return boton;
    }
    public JScrollPane scrollInvisible(JPanel panel){//Agrega un scroll invisible a un panel
        JScrollPane scroll = new JScrollPane(panel);
        JScrollBar bar = new JScrollBar(JScrollBar.VERTICAL) {
            @Override//Hago que siempre detecte visible la barra vertical
            public boolean isVisible() {
                return true;
            }
        };

        bar.setUnitIncrement(20);//Incremento la velocidad de la barra
        scroll.setVerticalScrollBar(bar);//Agrego la barra al scrollpane
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);//Hago que no se vea la barra
        scroll.setOpaque(false);//Hago que no se vea el fondo del scrollpane
        scroll.getViewport().setOpaque(false);
        return scroll;
    }

    // Conjunto componente reutilizables
    public void cargarLogoPrincipal(JPanel panel){//Agrego un logo y un titulo posicionado por defecto al comienzo de la ventana
        JLabel titulo = new JLabel("RESERV-APP");
        titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("IBM Plex Sans", Font.BOLD, 40));
        titulo.setBounds(70,60, 300, 40);
        panel.add(titulo);

        //Agrego un png
        JLabel logo = new JLabel(new GestorDeArchivos().cargarImgIcono("Logos", "logo" ,250, 250));
        logo.setBounds(70, 90, 250, 250);
        panel.add(logo);

        JLabel subTitulo = new JLabel("Reservas de cabañas");
        subTitulo.setForeground(Color.BLACK);
        subTitulo.setFont(new Font("IBM Plex Sans", Font.BOLD, 25));
        subTitulo.setBounds(75,340,300,30);
        panel.add(subTitulo);

        JLabel subTitulo2 = new JLabel("IX región.");
        subTitulo2.setForeground(Color.BLACK);
        subTitulo2.setFont(new Font("IBM Plex Sans", Font.BOLD, 25));
        subTitulo2.setBounds(150,365,200,30);
        panel.add(subTitulo2);
    }

    public JMenuBar menu(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(300, 40));//Establezco dimensiones
        menuBar.setBackground(Color.decode("#BA813E"));//Cambio color
        menuBar.setBorderPainted(false);//Elimino bordes

        JLabel logo = new JLabel(new GestorDeArchivos().cargarImgIcono("Logos", "logo", 50, 50));

        JLabel separacion = new JLabel(
                "                                                                                 ");//Etiqueta en blaco para separar los elementos, no se pudo de otra forma

        // Añadir el botón y logo a la barra de menú
        menuBar.add(logo);
        menuBar.add(separacion);
        menuBar.add(botonMenu());

        return menuBar;
    }
    private JButton botonMenu() {
        // Crear un botón con un logo
        JButton b_usuario = new JButton(new GestorDeArchivos().cargarImgIcono("Logos", "usuario",40, 40));
        b_usuario.setContentAreaFilled(false);//Elimino relleno automatico
        b_usuario.setBorderPainted(false);//Elimino bordes
        return b_usuario;
    }

}
