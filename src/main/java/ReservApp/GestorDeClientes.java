package ReservApp;

import GestionDeArchivos.GestorDeArchivos;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Clase encargada de la administracion de los datos de los clientes, ArrayList, datos, login, entre otros.
 */
public class GestorDeClientes {

    private ArrayList<Cliente> listaClientes; // lista de clientes obtenidos de archivo JSON

    /**
     * Constructor que se encarga de rellenar el ArrayList de clientes desde el archivo Clientes.json.
     */
    public GestorDeClientes(){
        listaClientes = setListaClientes(new GestorDeArchivos().obtenerClientesDesdeArchivoJson());
    }

    /**
     * Metodo getListaClientes
     * @return Devuelve un ArrayList de tipo cliente.
     */
    public ArrayList<Cliente> getListaClientes(){
        return this.listaClientes;
    }

    /**
     * genera un cliente a partir de un JSONObject.
     * @param jsonCliente JSONObject con los datos del cliente.
     * @return Devuelve un objeto de tipo Cliente.
     */
    private Cliente instanciarClienteJson (JSONObject jsonCliente) {
        return new Cliente(jsonCliente.getString("usuario"), jsonCliente.getString("contraseña"), jsonCliente.getInt("celular"));
    }

    //

    /**
     * Genera una ArrayList de tipo Cliente a partir de una ArrayList de JSONObjetc de clientes.
     * @param jsonClientes ArrayList JSONObject con datos de clientes
     * @return Devuelve ArrayList de Clientes
     */
    private ArrayList<Cliente> setListaClientes(ArrayList<JSONObject> jsonClientes){
        ArrayList<Cliente> newListClientes = new ArrayList<>();
        for (JSONObject cliente : jsonClientes){
            newListClientes.add(instanciarClienteJson(cliente));
        }
        return newListClientes;
    }

    /**
     * Metodo usado en VentanaSignUP para realizar el registro de un nuevo usuario.
     * @param usuario Nombre de usuario.
     * @param celular Celular del usuario.
     * @param password Contraseña del usuario.
     * @param passwordConf Contraseña confirmacion de usuario.
     * @return Devuelve un verdadero solo cuando los datos son validos y el usuario no existia previamente.
     */
    public boolean signUP(String usuario, int celular, String password, String passwordConf){
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

    /**
     * Metodo encargado de verificar la existencia de un usuario en el ArrayList
     * @param usuario
     * @return
     */
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

    public void registrarClientesEnArchivoJson(){
        ArrayList<JSONObject> list = new ArrayList<>();
        for (Cliente cliente : listaClientes){
            list.add(cliente.clienteToJson());
        }
        new GestorDeArchivos().escribirClientesEnArchivoJson(list);
    }

    public void modificarNombreUsuario(Cliente usuarioIngresado, String nuevoNombre){
        if (!usuarioExiste(nuevoNombre)) {
            usuarioIngresado.setUsuario(nuevoNombre);

        }
    }

    public Boolean contraseñaCorrecta(Cliente usuarioIngresado, String contraseña){
        return usuarioIngresado.getContraseña().equals(contraseña);
    }


    public Boolean modificarContraseña(
            Cliente usuarioIngresado, String nuevaContraseña, String nuevaContraseña2){

        if (nuevaContraseña.equals(nuevaContraseña2)){
            usuarioIngresado.setContraseña(nuevaContraseña);
            return true;
        }
        return false;
    }

    public Boolean modificarCelular(Cliente usuarioIngresado, int nuevoCelular){

        if (Integer.toString(nuevoCelular).length() == 9) {
            usuarioIngresado.setCelular(nuevoCelular);
        }
        return true;
    }

}
