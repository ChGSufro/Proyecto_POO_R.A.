package ReservApp;

import org.json.JSONObject;
import GestionDeArchivos.GestorDeArchivos;

import java.util.ArrayList;

/**
 * Clase encargada de Gestionar los arreglos y datos de las cabañas.
 */
public class    GestorDeCabañas {

    private ArrayList<Cabaña> listaCabañas;

    /**
     * Constructor de la clase que setea el ArrayList de Cabañas con los datos obtenidos desde el archivo Cabañas.json.
     * @param gestorDeClientes necesario para gestionar el arriendo y desalojo de las cabañas asociadas a usuarios.
     */
    public GestorDeCabañas(GestorDeClientes gestorDeClientes){
        listaCabañas = setListaCabaña(new GestorDeArchivos().obtenerCabañasDesdeArchivoJson(),gestorDeClientes);
    }

    /**
     * Devuelve la lista de cabañas.
     * @return ArrayList de las Cabañas existentes.
     */
    public ArrayList<Cabaña> getListaCabañas() {
        return this.listaCabañas;
    }

    /**
     * Metodo que obtiene solo las cabañas reservadas por el usuario que se pasa como parametro.
     * @param usuarioIngresado Usuario logueado actualmente en el sistema.
     * @return ArrayList de las Cabañas arrendadas por el usuario.
     */
    public ArrayList<Cabaña> getCabañasReservadas(Cliente usuarioIngresado){
        ArrayList<Cabaña> cabañasReservadas = new ArrayList<>();
        for (Cabaña cabaña : this.listaCabañas){
            if (cabaña.getIsOcupada()){
                if (cabaña.getArrendatario().equals(usuarioIngresado)){
                    cabañasReservadas.add(cabaña);
                }
            }
        }
        return cabañasReservadas;
    }

    /**
     * Metodo que devuelve solo las cabañas disponibles, es decir, aquellas que su paremetro isOcupado es false.
     * @return ArrayList de las Cabañas disponibles para arrendar.
     */
    public ArrayList<Cabaña> getCabañasDisponibles(){
        ArrayList<Cabaña> cabañasDisponibles = new ArrayList<>();
        for (Cabaña cabaña : this.listaCabañas){
            if (!cabaña.getIsOcupada()){
                cabañasDisponibles.add(cabaña);
            }
        }
        return cabañasDisponibles;
    }


    /**
     * Metodo usado en el constructor y que genera una lista de cabañas a partir del archivo Cabañas.json
     * @param cabañas
     * @param gestorDeClientes
     * @return
     */
    private ArrayList<Cabaña> setListaCabaña(ArrayList<JSONObject> cabañas, GestorDeClientes gestorDeClientes){
        ArrayList<Cabaña> newListCabaña = new ArrayList<>();
        for (JSONObject cabaña : cabañas){
            newListCabaña.add(instanciarCabañasDesdeJson(cabaña, gestorDeClientes));
        }
        return newListCabaña;
    }

    //Metodo para instanciar los objetos, a partir de un Json:

    /**
     * Metodo encargado de cargar los datos de una cabaña desde un JSONObject y retornar un objeto Cabaña con sus datos.
     * En caso de que la cabaña no tenga arrendatario, la instancia sin uno.
     * @param jsonCabaña JSONObject con los datos de las cabaña.
     * @param gestorDeClientes obtiene la posicion del usuario y recupera su nombre.
     * @return Cabaña con los valores obtenidos del JSONObject.
     */
    private Cabaña instanciarCabañasDesdeJson(JSONObject jsonCabaña, GestorDeClientes gestorDeClientes) {
        //GestorDeClientes gestorDeClientes = new GestorDeClientes();
        if (jsonCabaña.getBoolean("isOcupada")){
            int posicionUsuario = gestorDeClientes.obtenerPosicionUsuario(jsonCabaña.getString("arrendatarios"));
            try{
                return new Cabaña(
                        jsonCabaña.getInt("id"),
                        jsonCabaña.getString("nombre"),
                        jsonCabaña.getInt("habitaciones"),
                        jsonCabaña.getInt("baños"),
                        jsonCabaña.getBoolean("isOcupada"),
                        gestorDeClientes.getListaClientes().get(posicionUsuario));
            }catch (IndexOutOfBoundsException error){
                System.out.println("Arrendatario no registrado, se desocupara la cabaña.");
            }
        }
        return new Cabaña(
                jsonCabaña.getInt("id"),
                jsonCabaña.getString("nombre"),
                jsonCabaña.getInt("habitaciones"),
                jsonCabaña.getInt("baños"));
    }

    /**
     * Se encarga de guardar los cambios hechos durante la sesion, escribiendo la lista de cabañas en el archivo Cabañas.json.
     */
    public void registrarCabañasEnArchivoJson(){
        ArrayList<JSONObject> listaCabañasJson = new ArrayList<>();
        for (Cabaña cabaña : listaCabañas){
            listaCabañasJson.add(cabaña.cabañaToJson());
        }
        new GestorDeArchivos().escribirCabañasEnArchivoJson(listaCabañasJson);
    }
}

