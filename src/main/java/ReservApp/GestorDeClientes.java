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
        this.listaClientes = setListaClientes(new GestorDeArchivos().obtenerClientesDesdeArchivoJson());
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

    /**
     * Metodo usado en el login del sistema para obtener cliente logeado haciendo uso del metodo "obtenerPosicionUsuario".
     * @param usuario Nombre del usuario
     * @return Devuelve objeto de tipo Cliente, luego de obtener su posicion en el ArrayList de usuarios.
     */
    public Cliente loginUsario(String usuario){
        int posicion = 0;
        posicion = obtenerPosicionUsuario(usuario);
        return listaClientes.get(posicion);
    }

    /**
     * Metodo que verifica si las credenciales ingresadas por el usuario coinciden con los datos existentes.
     * @param usuario Usuario ingresado.
     * @param contraseña Contraseña ingresada.
     * @return Retorna verdadero en el caso de que las credenciales coincidan con los datos existentes.
     */
    public boolean validarUsuario(String usuario, String contraseña) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if ((listaClientes.get(i)).getUsuario().equals(usuario) && (listaClientes.get(i)).getContraseña().equals(contraseña)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene el index del usuario pasado como parametro.
     * @param Usuario Usuario el cual se quiere buscar.
     * @return retorna la posicion del usuario en la lista, en caso de no existir devuelve -1, lo que indica que no existe.
     */
    public int obtenerPosicionUsuario(String Usuario) {
        int posicion = -1;
        for (int i = 0; i < this.listaClientes.size(); i++) {
            if ((this.listaClientes.get(i)).getUsuario().equals(Usuario)) {
                posicion = i;
            }
        }
        return posicion;
    }

    /**
     * Metodo encargado de ingresar los datos de clientes en el archivo "Clientes.json".
     * Haciendo uso del metodo "escribirClientesEnArchivoJson(...)" de la clase GestorDeArchivos.
     */
    public void registrarClientesEnArchivoJson(){
        ArrayList<JSONObject> list = new ArrayList<>();
        for (Cliente cliente : listaClientes){
            list.add(cliente.clienteToJson());
        }
        new GestorDeArchivos().escribirClientesEnArchivoJson(list);
    }

    /**
     * Metodo que modifica los datos de los usuarios desde la VentanaModificarUsuario.
     * @param usuarioIngresado Objeto Cliente, es cual se encuentra logeado en el sistema.
     * @param nuevoNombre Nuevo nombre del cliente
     */
    public void modificarNombreUsuario(Cliente usuarioIngresado, String nuevoNombre){
        if (!usuarioExiste(nuevoNombre)) {
            usuarioIngresado.setUsuario(nuevoNombre);
        }
    }

    /**
     * Verifica si la contraseña del Cliente logeado coincide con la contraseña recibida por la VentanaModificarUsuario.
     * @param usuarioIngresado Cliente logeado en el sistema
     * @param contraseña Contraseña recibida desde la ventana
     * @return Devuelve verdadero en el caso de que las contraseñas coincidan.
     */
    public Boolean contraseñaCorrecta(Cliente usuarioIngresado, String contraseña){
        return usuarioIngresado.getContraseña().equals(contraseña);
    }

    /**
     * Metodo que valida que contraseña y contraseñaConfirmacion sean iguales para confirmar el cambio.
     * @param usuarioIngresado Cliente logeado actualmente
     * @param nuevaContraseña Contraseña ingresada para modificacion.
     * @param nuevaContraseña2 ContraseñaConfirmacion ingresada para modificacion.
     * @return Devuelve verdadero en el caso de que ambas sean iguales.
     */
    public Boolean modificarContraseña(Cliente usuarioIngresado,
                                       String nuevaContraseña, String nuevaContraseña2){
        if (nuevaContraseña.equals(usuarioIngresado.getContraseña())){
            return false;
        }

        if (!nuevaContraseña.equals(nuevaContraseña2)){
            usuarioIngresado.setContraseña(nuevaContraseña);
            return false;
        }
        return true;
    }

    /**
     * Metodo que verifica que el numero de caracteres del celular sea igual a 9.
     * @param usuarioIngresado Cliente logeado actualmente.
     * @param nuevoCelular Nuevo numero de celular del usuario
     * @return Devuelve verdadero en el caso de que el numero sea de largo 9.
     */
    public Boolean modificarCelular(Cliente usuarioIngresado, int nuevoCelular){

        if (Integer.toString(nuevoCelular).length() == 9) {
            usuarioIngresado.setCelular(nuevoCelular);
        }
        return true;
    }

}
