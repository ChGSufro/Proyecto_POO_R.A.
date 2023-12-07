package ReservApp;

import org.json.JSONObject;
import GestionDeArchivos.GestorDeArchivos;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GestorDeCabañas {

    private ArrayList<Cabaña> listaCabañas;

    public GestorDeCabañas(GestorDeClientes gestorDeClientes){
        listaCabañas = setListaCabaña(new GestorDeArchivos().listaCabañaJson(),gestorDeClientes);
    }

    public ArrayList<Cabaña> getListaCabañas() {
        return this.listaCabañas;
    }


    public ArrayList<Cabaña> getCabañasReservadas(Cliente usuarioIngresao){

        ArrayList<Cabaña> cabañasReservadas = new ArrayList<>();

        for (Cabaña cabaña : this.listaCabañas){
            if (cabaña.getIsOcupada()){
                if (cabaña.getArrendatario().equals(usuarioIngresao)){
                    cabañasReservadas.add(cabaña);
                }
            }
        }

        return cabañasReservadas;
    }

    public ArrayList<Cabaña> getCabañasDisponibles(){

        ArrayList<Cabaña> cabañasDisponibles = new ArrayList<>();

        for (Cabaña cabaña : this.listaCabañas){
            if (!cabaña.getIsOcupada()){
                cabañasDisponibles.add(cabaña);
            }
        }

        return cabañasDisponibles;
    }


    //genera una lista de cabañas a partir de una lista de archivos json
    private ArrayList<Cabaña> setListaCabaña(ArrayList<JSONObject> cabañas, GestorDeClientes gestorDeClientes){
        ArrayList<Cabaña> newListCabaña = new ArrayList<>();
        for (JSONObject cabaña : cabañas){
            newListCabaña.add(instanciarCabañaJson(cabaña, gestorDeClientes));
        }
        return newListCabaña;
    }

    //Metodo para instanciar los objetos, a partir de un Json:
    private Cabaña instanciarCabañaJson (JSONObject archivoCabaña, GestorDeClientes gestorDeClientes) {
        //GestorDeClientes gestorDeClientes = new GestorDeClientes();
        if (archivoCabaña.getBoolean("isOcupada")){
            int pos = gestorDeClientes.obtenerPosicionUsuario(archivoCabaña.getString("arrendatarios"));
            try{
                return new Cabaña(
                        archivoCabaña.getInt("id"),
                        archivoCabaña.getString("nombre"),
                        archivoCabaña.getInt("habitaciones"),
                        archivoCabaña.getInt("baños"),
                        archivoCabaña.getBoolean("isOcupada"),
                        gestorDeClientes.getListaClientes().get(pos));
            }catch (IndexOutOfBoundsException error){
                System.out.println("Arrendatario no registrado, se desocupara la cabaña.");
            }
        }
        return new Cabaña(
                archivoCabaña.getInt("id"),
                archivoCabaña.getString("nombre"),
                archivoCabaña.getInt("habitaciones"),
                archivoCabaña.getInt("baños"));
    }

    public void registrarCabañasEnArchivoJson(){
        ArrayList<JSONObject> list = new ArrayList<>();
        for (Cabaña cabaña : listaCabañas){
            list.add(cabaña.cabañaToJson());
        }
        new GestorDeArchivos().escribirCabañaJson(list);
    }


}

