package ReservApp;
import org.json.JSONObject;

/**
 * Clase modela una cabaña con sus respectivas carecteristicas.
 */
public class Cabaña {
    //Atributos:
    private int id; // Id de la cabaña o numero de la cabaña.
    private String nombre; // Nombre de la cabaña.
    private int habitaciones; // Cantidad de habitaciones de la cabaña.
    private int baños; // Cantidad de baños de la cabaña.
    private boolean isOcupada; // Boolean que representa el estado de la cabaña.
    private Cliente arrendatario; // Arrendatario que puede o no estar asocidado a una cabaña.

    /**
     * Este contructor es para instanciar el objeto a partir del archivo Cabaña.json.
     * @param id Id de la cabaña o numero de la cabaña.
     * @param nombre Nombre de la cabaña.
     * @param habitaciones Cantidad de habitaciones de la cabaña.
     * @param baños Cantidad de baños de la cabaña.
     * @param isOcupada Boolean que representa el estado de la cabaña.
     * @param arrendatario Arrendatario que puede o no estar asocidado a una cabaña.
     */
    public Cabaña(int id, String nombre, int habitaciones, int baños, boolean isOcupada, Cliente arrendatario) {
        this.id = id;
        this.nombre = nombre;
        this.habitaciones = habitaciones;
        this.baños = baños;
        this.isOcupada = isOcupada;
        this.arrendatario = arrendatario;
    }

    /**
     * Este contructor crea las cabañas al inicio del programa, es decir que estas estan desocupadas,
     * por lo que no tienen arrendatario
     * @param id Id de la cabaña o numero de la cabaña.
     * @param nombre Nombre de la cabaña.
     * @param habitaciones Cantidad de habitaciones de la cabaña.
     * @param baños Cantidad de baños de la cabaña.
     */
    public Cabaña(int id, String nombre, int habitaciones, int baños) {
        this.id = id;
        this.nombre = nombre;
        this.habitaciones = habitaciones;
        this.baños = baños;
        this.isOcupada = false;
        this.arrendatario = null;
    }

    //Metodos getter:

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public int getHabitaciones() {
        return habitaciones;
    }
    public int getBaños() {
        return baños;
    }
    public Cliente getArrendatario() {
        return arrendatario;
    }
    public boolean getIsOcupada() {
        return isOcupada;
    }


    //Metodos setter:
    public void setIsOcupada(boolean isOcupada) {
        this.isOcupada = isOcupada;
    }
    public void setArrendatario(Cliente Arrendatario) {
        this.arrendatario = Arrendatario;
    }

    //Metodos de la clase:

    /**
     * Metodo que se encarga de reservar una cabaña, modificando los parametros "isOcupada" y "Arrendatario"
     * @param usuarioIngresado Es el cliente el cual arrendó la cabaña.
     */
    public void reservarCabaña_GUI(Cliente usuarioIngresado){
        if (!this.isOcupada){
            setArrendatario(usuarioIngresado);
            setIsOcupada(true);
        }
    }

    /**
     * Metodo que se encarga de realizar el CheckOut de una cabaña.
     * Modificando los parametros "isOcupada" y "Arrendatario" a false y null respectivamente.
     */
    public void checkOutCabaña_GUI() {
        if (this.isOcupada){
            setArrendatario(null);
            setIsOcupada(false);
        }
    }

    /**
     * Metodo que se encarga de cargar los atributos de la cabaña a un JSONObject.
     * @return Devuelve un JSONObject con los valores del objeto cabaña
     */
    public JSONObject cabañaToJson(){
        JSONObject jsonCabaña = new JSONObject();
        jsonCabaña.put("id" , this.id);
        jsonCabaña.put("nombre", this.nombre);
        jsonCabaña.put("habitaciones", this.habitaciones);
        jsonCabaña.put("baños", this.baños);
        jsonCabaña.put("isOcupada", this.isOcupada);
        if (this.arrendatario != null) {
            jsonCabaña.put("arrendatarios", this.arrendatario.getUsuario());}
        return jsonCabaña;
    }
}

    //-----------------DE AQUI PARA ABAJO YA NO LOS NECESITAMOS-----------------------
    // borré los metodos que hacian uso de la consola.