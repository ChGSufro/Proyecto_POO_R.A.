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

    private void escribirArchivoJSON(String carpeta, String Nombre, ArrayList<JSONObject> list){
        crearCarpeta(carpeta);//Creo la carpeta si no existe

        try (FileWriter file = new FileWriter(carpeta + File.separator + Nombre + ".json")) {
            file.write("[\n");//Agrego un corchete al inicio
            for (int posicion = 0; posicion < list.size(); posicion++) {
                file.write(list.get(posicion).toString());//Escribe cada JSONObject de la lista en una linea

                if (posicion < list.size() - 1) {//Añade coma al final de cada objeto json exceptuando el ultimo
                    file.write(",");
                }
                file.write("\n");//Salta un espacio despues de cada JSONObject
            }
            file.write("]");//Cierro con un corchete
        } catch (IOException ignored) {
        }
    }

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

        } catch (IOException ignore) {}//No se cae si el archivo no existe


        return listaJson;
    }

///publicos

    public void escribirCabañaJson(ArrayList<JSONObject> listaCabañas) {
        escribirArchivoJSON("Archivos", "Cabañas", listaCabañas);
    }

    public void escribirClienteJson(ArrayList<JSONObject> listaClientes) {
        escribirArchivoJSON("Archivos", "Clientes", listaClientes);
    }

    public ArrayList<JSONObject> listaCabañaJson() {
        return leerArchivoJson("Archivos/Cabañas.json");
    }

    public ArrayList<JSONObject> listaClienteJson() {
        return leerArchivoJson("Archivos/Clientes.json");
    }

    //Gui
    public ImageIcon cargarImgIcono(String carpeta, String nombre, int ancho, int alto) {
        crearCarpeta(carpeta);

        ImageIcon icono = new ImageIcon(carpeta + File.separator + nombre + ".png");
        Image image = icono.getImage(); // transforma el icono en una imagen
        Image newimg = image.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH); // escala la imagen
        icono = new ImageIcon(newimg);  // transforma la imagen escalada en un icono
        return icono;
    }


}
