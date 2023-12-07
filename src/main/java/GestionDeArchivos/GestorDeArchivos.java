package GestionDeArchivos;

import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class GestorDeArchivos {
    private void crearCarpeta(String nombre){
        File Carpeta = new File(nombre);
        Carpeta.mkdirs();
    }

    //Archivos Json

    private void escribirArchivoJSON(String ruta, ArrayList<JSONObject> list){
        try (FileWriter file = new FileWriter(ruta)) {
            file.write("[\n");//Agrego un corchete al inicio
            for (int posicion = 0; posicion < list.size(); posicion++) {
                file.write(list.get(posicion).toString());//Escribe cada JSONObject de la lista en una linea

                if (posicion < list.size() - 1) {//Añade coma al final de cada objeto json exceptuando el ultimo
                    file.write(",");
                }
                file.write("\n");//Salta un espacio despues de cada JSONObject
            }
            file.write("]");//Cierro con un corchete
        } catch (IOException error) {
            throw new RuntimeException("Error al actualizar la informacion, no se guardaron los cambios.");
        }
    }

    private ArrayList<JSONObject> leerArchivoJson(String ruta) {
        ArrayList<JSONObject> listaJson = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(ruta))){
            String linea;

            while ((linea = lector.readLine()) != null) {//Mientras haya lineas en el archivo cada linia del archivo se la pasa a la variable linea
                try {
                    JSONObject json = new JSONObject(linea.toString());//Convierte la linea en un JSONObject
                    listaJson.add(json);//Añade el JSONObject a la lista
                } catch (JSONException ignore){}
            }
        } catch (IOException ignore) {}//No se cae si el archivo no existe

        return listaJson;
    }

///publicos
    public void escribirCabañaJson(ArrayList<JSONObject> listaCabañas) {
        crearCarpeta("Archivos");
        escribirArchivoJSON("Archivos/Cabañas.json", listaCabañas);
    }
    public void escribirClienteJson(ArrayList<JSONObject> listaClientes) {
        crearCarpeta("Archivos");
        escribirArchivoJSON("Archivos/Clientes.json", listaClientes);
    }

    public ArrayList<JSONObject> listaCabañaJson() {
        crearCarpeta("Archivos");
        return leerArchivoJson("Archivos/Cabañas.json");
    }

    public ArrayList<JSONObject> listaClienteJson() {
        crearCarpeta("Archivos");
        return leerArchivoJson("Archivos/Clientes.json");
    }

    //Gui
    public ImageIcon cargarImgIcono(String ruta, int ancho, int alto) {

        ImageIcon icono = new ImageIcon(ruta);
        Image imagen = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH); // escala la imagen
        return new ImageIcon(imagen);
    }

}
