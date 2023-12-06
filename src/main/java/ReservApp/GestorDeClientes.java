package ReservApp;

import GestionDeArchivos.GestorDeArchivos;
import org.json.JSONObject;
import java.util.ArrayList;

public class GestorDeClientes {

    private ArrayList<Cliente> listaClientes;

    public GestorDeClientes(){
        listaClientes = setListaClientes(new GestorDeArchivos().listaClienteJson());
    }

    public ArrayList<Cliente> getListaClientes(){
        return this.listaClientes;
    }

    private Cliente instanciarClienteJson (JSONObject archivoCliente) {
        return new Cliente(archivoCliente.getString("usuario"), archivoCliente.getString("contraseña"), archivoCliente.getInt("celular"));
    }

    //genera una lista de clientes a partir de una lista de archivos json
    private ArrayList<Cliente> setListaClientes(ArrayList<JSONObject> clientes){
        ArrayList<Cliente> newListClientes = new ArrayList<>();
        for (JSONObject cliente : clientes){
            newListClientes.add(instanciarClienteJson(cliente));
        }
        return newListClientes;
    }

    public boolean singUP(String usuario, int celular, String password, String passwordConf){
        if(Integer.toString(celular).length() != 9){
            return false;
        }else if(!password.equals(passwordConf)) {
            return false;
        }
        if (!usuarioExiste(usuario)){
            this.listaClientes.add(new Cliente(usuario, password, celular));
            return true;
        }else{
            return false;
        }
    }

    private Boolean usuarioExiste(String usuario){
        for (Cliente cliente : this.listaClientes){
            if (cliente.getUsuario().equals(usuario)){
                return true;
            }
        }
        return false;
    }

    public Cliente loginUsario(String usuario, String contraseña){
        int posicion = 0;
        posicion = obtenerPosicionUsuario(usuario);
        return listaClientes.get(posicion);
    }

    public boolean validarUsuario(String usuario, String contraseña) {
        for (int i = 0; i < listaClientes.size(); i++) {

            if ((listaClientes.get(i)).getUsuario().equals(usuario) && (listaClientes.get(i)).getContraseña().equals(contraseña)) {
                return true;
            }
        }
        return false;
    }

    public int obtenerPosicionUsuario(String Usuario) {

        int posicion = -1;

        for (int i = 0; i < this.listaClientes.size(); i++) {
            if ((this.listaClientes.get(i)).getUsuario().equals(Usuario)) {
                posicion = i;
            }
        }
        return posicion;
    }

    private String lecturaString(){
        java.util.Scanner leer = new java.util.Scanner(System.in);
        return leer.nextLine();
    }

    private int lecturaInt(){
        java.util.Scanner leer = new java.util.Scanner(System.in);
        return leer.nextInt();
    }

    public void registrarClientesEnArchivoJson(){
        ArrayList<JSONObject> list = new ArrayList<>();
        for (Cliente cliente : listaClientes){
            list.add(cliente.clienteToJson());
        }
        new GestorDeArchivos().escribirClienteJson(list);
    }

    private void modificarNombreUsuario(Cliente usuarioIngresado, String nuevoNombre){
        if (!usuarioExiste(nuevoNombre)) {
            usuarioIngresado.setUsuario(nuevoNombre);
        }
    }

    private void modificarContraseña(Cliente usuarioIngresado, String nuevaContraseña, String nuevaContraseña2){
        if (nuevaContraseña.equals(nuevaContraseña2)){
            usuarioIngresado.setContraseña(nuevaContraseña);
        }
    }

    private void modificarCelular(Cliente usuarioIngresado, int nuevoCelular){

        if (Integer.toString(nuevoCelular).length() == 9) {
            usuarioIngresado.setCelular(nuevoCelular);
        }

    }

}
