package GestionDeArchivos;

import org.json.JSONArray;
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

    private void eliminarArchivo(File file){
        file.delete();
    }

    private String getExtension(File file) {
        String fileName = file.getName();
        int extension = fileName.lastIndexOf('.');
        return (extension == -1) ? "" : fileName.substring(extension);
    }

    //Archivos Json

    //Metodo que verifica que un archivo sea .json
    private Boolean esArchivoJson(File ruta) {
        return getExtension(ruta).equals(".json");
    }

    //Metodo que verifica si existe un .json en especifico
    private Boolean archivoJsonExiste(String carpeta, String archivo){
        File Archivo = new File(carpeta + File.separator + archivo + ".json");
        return Archivo.exists();
    }


    //Arreglar
    private void escribirArchivoJSON(String carpeta, String Nombre, ArrayList<JSONObject> list){
        crearCarpeta(carpeta);//Creo la carpeta si no existe

        try (FileWriter file = new FileWriter(carpeta + File.separator + Nombre + ".json")) {
            file.write("[\n");//Agrego un corchete al inicio
            for (int posicion = 0; posicion < list.size(); posicion++) {
                file.write(list.get(posicion).toString());

                if (posicion < list.size() - 1) {//Añade coma al final de cada objeto json exceptuando el ultimo
                    file.write(",");
                }
                file.write("\n");//Salta un espacio despues de cada JSONObject
            }
            file.write("]");//Cierro con un corchete
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ArrayList<JSONObject> leerArchivoJson(String ruta) {
        ArrayList<JSONObject> lista = new ArrayList<>();

        try {
            BufferedReader Lector = new BufferedReader(new FileReader(ruta));//Lee el archivo y lo almacena en un BufferedReader
            String linea;
            StringBuilder stringBuilderb = new StringBuilder();

            while ((linea = Lector.readLine()) != null) {//Mientras haya lineas en el archivo
                stringBuilderb.append(linea);//Añade cada linea del archivo a un StringBuilder
            }

            JSONArray jsonArray = new JSONArray(stringBuilderb.toString());//Convierte el StringBuilder a un JSONArray

            //Mantengo el formato de arraylist de JSONObject para que sea compatible con el resto del programa, pero no se si es lo mas optimo
            for (int i = 0; i < jsonArray.length(); i++) {//Añade cada JSONObject del JSONArray a un ArrayList de JSONObject
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                lista.add(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

///

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
    public ImageIcon cargarIcono(String carpeta, String nombre, int ancho, int alto) {
        crearCarpeta(carpeta);

        ImageIcon icono = new ImageIcon(carpeta + File.separator + nombre + ".png");
        Image image = icono.getImage(); // transforma el icono en una imagen
        Image newimg = image.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH); // escala la imagen
        icono = new ImageIcon(newimg);  // transforma la imagen escalada en un icono
        return icono;
        }

}
