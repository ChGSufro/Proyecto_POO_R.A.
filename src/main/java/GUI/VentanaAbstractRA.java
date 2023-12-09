package GUI;

import GestionDeArchivos.GestorDeArchivos;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Clase abstracta que sirve como base para las ventanas de la aplicación.
 * Proporciona métodos comunes para configurar la apariencia de las ventanas.
 */
public abstract class VentanaAbstractRA extends JFrame {

    protected JPanel fondo; //panel de la ventana

    /**
     * Constructor de la clase VentanaAbstractRA.
     * Configura propiedades comunes de las ventanas, como el gradiente.
     */
    public VentanaAbstractRA(){
        setSize(new Dimension(300, 600));
        setIconImage(new ImageIcon("src/main/resources/Logos/iconoApp.png").getImage());

        fondo = panelConGradiente();
        fondo.setPreferredSize(new Dimension(0, 0));
        fondo.setLayout(new BoxLayout(fondo, BoxLayout.PAGE_AXIS));

        setResizable(false);//Bloqueo boton de maximizar
        setLocationRelativeTo(null);//Posiciona la ventana en el centro
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Detiene el programa cuando se cierra la ventana
    }

    /**
     * Método que crea un panel con un gradiente como fondo.
     * @return JPanel con un gradiente como fondo.
     */
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

    /**
     * Crea un botón con un fondo de color y otros atributos específicos, como los bordes redondeados.
     * @param color Color hexadecimal para el fondo del botón.
     * @return Devuelve JButton con el color de fondo y atributos especificados.
     */
    public JButton crearBoton(String color){//Crea un boton con un texto, color de fondo y tamaño de letra
        JButton boton = new JButton(){
            @Override//Pinto un fondo con efecto de rectangulo redondeado
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(Color.decode(color));//Establezco el color
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));//Pinto un rectangulo de bordes redondeados
                super.paintComponent(g);//Pinto el original por encima para que se vean todas las propiedades
            }
        };
        boton.setContentAreaFilled(false); //Hago que no se genere el fondo por defecto
        boton.setBorderPainted(false); //Elimina bordes por defecto
        boton.setFocusPainted(false); //Elimina el efecto de click
        boton.setForeground(Color.BLACK); //Cambio el color de la letra
        return boton;
    }

    /**
     * Agrega un scroll invisible a un panel.
     * @param panel JPanel al que se agregará el scroll.
     * @return JScrollPane con scroll invisible.
     */
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

    // Métodos para componentes reutilizables

    /**
     * Agrega un logo y un título predeterminado al inicio de un panel.
     * @param panel JPanel al que se agregarán los componentes.
     */
    public void cargarLogoPrincipal(JPanel panel){//Agrego un logo y un titulo posicionado por defecto al comienzo de la ventana
        JLabel titulo = new JLabel("RESERV-APP");
        titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("IBM Plex Sans", Font.BOLD, 30));
        titulo.setBounds(50,50, 200, 30);
        panel.add(titulo);

        //Agrego un png
        JLabel logo = new JLabel(new GestorDeArchivos().cargarPng("Logos/logo.png", 200, 200));
        logo.setBounds(50, 80, 200, 200);
        panel.add(logo);

        JLabel subTitulo = new JLabel("Reservas de cabañas");
        subTitulo.setForeground(Color.BLACK);
        subTitulo.setFont(new Font("IBM Plex Sans", Font.BOLD, 20));
        subTitulo.setBounds(40,280,220,20);
        panel.add(subTitulo);

        JLabel subTitulo2 = new JLabel("IX región.");
        subTitulo2.setForeground(Color.BLACK);
        subTitulo2.setFont(new Font("IBM Plex Sans", Font.BOLD, 20));
        subTitulo2.setBounds(100,300,100,30);
        panel.add(subTitulo2);
    }

    /**
     * Crea un menú superior con un logo y un botón de usuario.
     * @return JMenuBar con los elementos del menú.
     */
    public JMenuBar menu(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(300, 40));//Establezco dimensiones
        menuBar.setBackground(Color.decode("#BA813E"));//Cambio color
        menuBar.setBorderPainted(false);//Elimino bordes

        JLabel logo = new JLabel(new GestorDeArchivos().cargarPng("Logos/logo.png", 50, 50));

        JLabel separacion = new JLabel(
                "                                                     ");//Etiqueta en blaco para separar los elementos, no se pudo de otra forma

        // Añadir el botón y logo a la barra de menú
        menuBar.add(logo);
        menuBar.add(separacion);

        return menuBar;
    }
    /**
     * Crea un botón con un ícono de usuario, el cual podra desplegar la VentanaModicifarUsuario.
     * @return JButton con ícono de usuario.
     */
    public JButton botonMenu() {
        // Crear un botón con un logo
        JButton b_usuario = new JButton(new ImageIcon("src/main/resources/Logos/usuario.png"));
        b_usuario.setContentAreaFilled(false);//Elimino relleno automatico
        b_usuario.setBorderPainted(false);//Elimino bordes
        b_usuario.setFocusPainted(false);//Elimino efecto de click
        return b_usuario;
    }

}
