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

    private Boolean usuarioExiste(String usuario){
        for (Cliente cliente : this.listaClientes){
            if (cliente.getUsuario().equals(usuario)){
                return true;
            }
        }
        return false;
    }

    private Boolean celularExiste(int celular){
        for (Cliente cliente : this.listaClientes){
            if (cliente.getCelular() == celular){
                return true;
            }
        }
        return false;
    }

    private Boolean celularValido(int celular){
        try{
            if (Integer.toString(celular).length() == 9){
                return true;
            }
        } catch (Exception e){
            return false;
        }
        return false;
    }



    public Boolean registrarUsuario(String usuario, int celular ,String contraseña, String confContraseña) {
        if (usuarioExiste(usuario)) {
            return false;
        }

        if (!contraseña.equals(confContraseña)) {
            return false;
        }

        if (!celularValido(celular) && celularExiste(celular)) {
            return false;
        }

        this.listaClientes.add(new Cliente(usuario, contraseña, celular));
        return true;
    }

    public Cliente loginUsario(String usuario, String contraseña) {

        if (validarUsuario(usuario, contraseña)) {
            return this.listaClientes.get(obtenerPosicionUsuario(usuario));
        }

        return null;
    }

    private boolean validarUsuario(String usuario, String contraseña) {
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

}
