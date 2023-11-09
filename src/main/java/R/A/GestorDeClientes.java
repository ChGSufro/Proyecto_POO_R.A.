package R.A;

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

    public void singUP(){
        String Usuario;
        int Celular = 0;
        String Contraseña;
        String Contraseña2;
        System.out.println("\n#----REGISTRAR USUARIO----#");
        System.out.println("\n-> Ingrese el nombre de su nuevo usuario: ");
        Usuario = lecturaString();
        do {
            System.out.println("-> Ingrese celular valido. (9 digitos, solo numeros): ");
            try{
                Celular = lecturaInt();}
            catch (Exception e){
                System.out.println("Valores no validos");
            }
        } while (!(Integer.toString(Celular).length() == 9));
        do {
            System.out.println("-> Ingrese Contraseña: ");
            Contraseña = lecturaString();
            System.out.println("-> Confirme Contaseña: ");
            Contraseña2 = lecturaString();
        } while (!Contraseña.equals(Contraseña2));
        if (!usuarioExiste(Usuario)) {
            this.listaClientes.add(new Cliente(Usuario, Contraseña, Celular));
            System.out.println("¡Usuario creado correctamente!.");
        } else {
            System.out.println("Usuario ya existe.");
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

    public Cliente loginUsario() {
        int posicion;
        String usuario;
        boolean validar = false;
        do {
            System.out.println("\n#----INICIO DE SESION----#");
            System.out.println("\nA continuacion ingrese los datos solicitados");
            System.out.println("-> Ingrese su nombre previamente registrado: ");
            usuario = lecturaString();
            System.out.println("-> Ingrese su contraseña: ");
            String contraseña = lecturaString();
            posicion = 0;
            if (validarUsuario(usuario, contraseña)) {
                posicion = obtenerPosicionUsuario(usuario);
                validar = true;
            }
        } while (!validar);
        return listaClientes.get(posicion);
    }

    private boolean validarUsuario(String usuario, String contraseña) {
        for (int i = 0; i < listaClientes.size(); i++) {

            if ((listaClientes.get(i)).getUsuario().equals(usuario) && (listaClientes.get(i)).getContraseña().equals(contraseña)) {
                return true;
            }
        }
        System.out.println("Usuario y/o contraseña incorrecto");
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
