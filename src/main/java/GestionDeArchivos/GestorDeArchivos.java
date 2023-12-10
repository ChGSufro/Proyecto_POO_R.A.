package GestionDeArchivos;

import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Esta clase se encarga de leer, escribir los archivos .json de la carperta Archivos.
 */

public class GestorDeArchivos {
    /**
     * Crea una carpeta en el proyecto
     * @param nombre es el nombre que tendrá la carpeta
     */
    private void crearcarpetas(String nombre){
        File Carpeta = new File(nombre);
        Carpeta.mkdirs();
    }

    //Archivos Json

    /**
     * Metodo el cual escribe en los json.
     * Es el encargado de guardar los cambios a la hora de cerrar el programa.
     * @param ruta recibe el nombre del archivo el cual será escrito o sobre escrito.
     * @param list Es un ArrayList de JSONObject de donde se obtendrán los datos para escribir.
     */
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
            throw new RuntimeException("Error al guardar los datos, intentelo mas tarde");
        }
    }

    /**
     * Se encarga de leer un archivo json.
     * @param ruta es la ruta en el proyecto que hace referencia al archivo que se quiere leer.
     * @return devuelve un ArrayList de JSONObjetc del archivo json leido.
     */
    private ArrayList<JSONObject> leerArchivoJson(String ruta) {
        ArrayList<JSONObject> listaJson = new ArrayList<>();

        try {
            BufferedReader lector = new BufferedReader(new FileReader(ruta));//Lee el archivo y lo almacena en un BufferedReader
            String linea;

            while ((linea = lector.readLine()) != null) {//Mientras haya lineas en el archivo cada linia del archivo se la pasa a la variable linea
                try {
                    JSONObject json = new JSONObject(linea.toString());//Convierte la linea en un JSONObject
                    listaJson.add(json);//Añade el JSONObject a la lista
                } catch (JSONException ignore){}
            }

        } catch (IOException ignore) {}

        return listaJson;
    }

///publicos

    /**
     * Se encarga de escribir las cabañas en el archivo "Cabañas" haciendo uso del metodo escribirArchivoJson.
     * @param listaCabañas es el ArrayList de JSONObject de cabañas el cual se escribirá en el archivo.
     */
    public void escribirCabañasEnArchivoJson(ArrayList<JSONObject> listaCabañas) {
        crearcarpetas("src/main/resources/Archivos");
        escribirArchivoJSON("src/main/resources/Archivos/Cabañas.json", listaCabañas);
    }

    /**
     * Se encarga de escribir las cabañas en el archivo "Clientes" haciendo uso del metodo escribirArchivoJson.
     * @param listaClientes es el ArrayList de JSONObject de clientes el cual se escribirá en el archivo.
     */
    public void escribirClientesEnArchivoJson(ArrayList<JSONObject> listaClientes) {
        crearcarpetas("src/main/resources/Archivos");
        escribirArchivoJSON("src/main/resources/Archivos/Clientes.json", listaClientes);
    }

    /**
     * Hace uso de leerArchivoJson para leer el archivo Cabañas.json.
     * @return devuelve el ArrayList de JSONObject de cabañas.
     */

    public ArrayList<JSONObject> obtenerCabañasDesdeArchivoJson() {
        return leerArchivoJson("src/main/resources/Archivos/Cabañas.json");
    }

    /**
     * Hace uso de leerArchivoJson para leer el archivo Clientes.json.
     * @return devuelve el ArrayList de JSONObject de clientes.
     */
    public ArrayList<JSONObject> obtenerClientesDesdeArchivoJson() {
        return leerArchivoJson("src/main/resources/Archivos/Clientes.json");
    }

    //Gui

    /**
     * Metodo encargado de obtener y cargar una imagen en las ventanas desde su ruta en el proyecto.
     * @param ruta direccion de la imagen que será cargada.
     * @param ancho ancho de la imagen.
     * @param alto alto de la imagen.
     * @return devuelve el objeto de tipo ImageIcon que será mostrado en la ventana correspondiente
     */
    public ImageIcon cargarPng(String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon("src/main/resources/" + ruta);
        Image imagen = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH); // escala la imagen
        return new ImageIcon(imagen);
    }
}
