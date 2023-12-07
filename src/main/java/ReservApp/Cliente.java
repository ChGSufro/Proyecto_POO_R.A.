package ReservApp;

import org.json.JSONObject;

/**
 * Representa un cliente en el sistema de reservas.
 */
public class Cliente {

    //Atributros:
    private String usuario; // Nombre de usuario del cliente
    private String contraseña; // Contraseña del cliente
    private int celular; // Número de celular del cliente

    /**
     * Constructor de la clase Cliente.
     * @param usuario El nombre de usuario del cliente.
     * @param contraseña La contraseña del cliente.
     * @param celular El número de celular del cliente.
     */
    public Cliente(String usuario, String contraseña, int celular) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.celular = celular;
    }

    /**
     * Obtiene el nombre de usuario del cliente.
     * @return Devuelve el nombre de usuario del cliente.
     */
    public String getUsuario() {
        return usuario;
    }
    /**
     * Obtiene el número de celular del cliente.
     * @return Devuelve el número de celular del cliente.
     */
    public int getCelular() {
        return celular;
    }
    /**
     * Obtiene la contraseña del cliente.
     * @return Devuelve la contraseña del cliente.
     */
    public String getContraseña(){
        return this.contraseña;
    }

    /**
     * Establece el nombre de usuario del Cliente.
     * @param usuario El nombre de usuario del Cliente.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    /**
     * Establece el número de celular del cliente.
     * @param celular El número de celular del cliente.
     */
    public void setCelular(int celular) {
        this.celular = celular;
    }

    /**
     * Establece la contraseña del cliente.
     * @param contraseña La nueva contraseña del cliente.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    /**
     * Convierte los datos del cliente a un objeto JSONObject.
     * @return Devuelve un objeto JSONObject con los datos del cliente.
     */
    public JSONObject clienteToJson(){
        JSONObject json = new JSONObject();
        json.put("usuario" , this.usuario);
        json.put("celular", this.celular);
        json.put("contraseña", this.contraseña);
        return json;
    }

}