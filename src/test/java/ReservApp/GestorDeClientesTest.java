package ReservApp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase para las Pruebas de los metodos mas relevantes de la clase GestorDeClientes.
 */
class GestorDeClientesTest {

    /* valores inciales
    [
    {"celular":944894766,"usuario":"Javier","contraseña":"12345"},
    {"celular":989876285,"usuario":"Joaquin","contraseña":"asd"},
    {"celular":123456789,"usuario":"chester","contraseña":"1234"}
    ]
    referencia de los datos en el archivo de los Clientes*/

    ArrayList<Cliente> listaClientes;
    GestorDeClientes gestorDeClientes = new GestorDeClientes();

    /**
     * Metodo que setea la listaClientes con los valores del Archivo JSON.
     */
    @BeforeEach
    void setUp() {
        listaClientes = gestorDeClientes.getListaClientes();
    }

    /**
     * Se encarga de dejar los datos de la lista con los valores originales despues de la ejecucion.
     * Los valores se modifican por lo que es necesario dejarlos por defecto para el correcto funcionamiento de las pruebas.
     */
    @AfterEach
    void tearDown() {
        listaClientes = gestorDeClientes.getListaClientes();
    }

    /**
     * Se demuestra el correcto funcionamiento del metodo "validarUsuario"de la clase GestorDeClientes.
     * Se hacen 2 casos, uno correcto y el otro fallido.
     */
    @Test
    void validarUsuario() {
        assertTrue(gestorDeClientes.validarUsuario(listaClientes.get(0).getUsuario(), listaClientes.get(0).getContraseña())); //Usuario existente
        assertFalse(gestorDeClientes.validarUsuario("Pedro", "12345")); //Usuario ficticio
    }

    /**
     * Se Demuestra el correcto funcionamiento del metodo "ObtenerPosicionUsuario" de la Clase GestorDeClientes.
     * Se hacen 2 casos, uno correcto y el otro fallido.
     */
    @Test
    void obtenerPosicionUsuario() {
        // usuario en la posicion 0 es Javier
        assertEquals(gestorDeClientes.obtenerPosicionUsuario(listaClientes.get(0).getUsuario()), 0); //le paso el nombre del usuario y retorna la posicion de este.
        //usuario en la posicion 0 es Javier por lo que el resultado 1 es falso
        assertNotEquals(gestorDeClientes.obtenerPosicionUsuario(listaClientes.get(0).getUsuario()), 1); //le paso el nombre del usuario y retorna la posicion de este.
        //System.out.println(listaClientes.get(0).getUsuario());
    }

    /**
     * Se Demuestra el correcto funcionamiento del metodo "contraseñaCorrect" de la Clase GestorDeClientes.
     * Se hacen 2 casos, uno correcto y el otro fallido.
     */
    @Test
    void contraseñaCorrecta() {
        assertTrue(gestorDeClientes.contraseñaCorrecta(listaClientes.get(0), listaClientes.get(0).getContraseña())); // Prueba con valores reales, por lo que tira verdadero
        assertFalse(gestorDeClientes.contraseñaCorrecta((listaClientes.get(0)), "contraseñaFalsa")); // prueba con usuario real pero contraseña inventada, arroja falso
    }

    /**
     * Prueba donde se garantiza el correcto funcionamiento del metodo "modificarCelular" de la Clase GestorDeClientes.
     * Se ejemplifica 2 casos, uno cuando falla y otro cuando funciona.
     * Se muuestran por consola los cambios
     */
    @Test
    void modificarCelular() {
        System.out.println(listaClientes.get(0).getCelular()); // numero original
        assertTrue(gestorDeClientes.modificarCelular(listaClientes.get(0), 911111111)); // largo correcto del numero de celular
        assertFalse(gestorDeClientes.modificarCelular(listaClientes.get(0), 1234)); // largo insuficiente, no de modifica el numero del usuario.
        System.out.println(listaClientes.get(0).getCelular()); // numero modificado
    }

    /**
     * Demostracion de correcto funcionamiento del metodo "modificarContraseña" de la Clase GestorDeClientes.
     * Se ejemplifica 2 casos, uno cuando falla y otro cuando funciona.
     * Se muuestran por consola los cambios
     */
    @Test
    void modificarContraseña() {
        System.out.println(listaClientes.get(0).getContraseña()); // contraseña original.
        // la siguiente prueba es en el caso donde ambas contraseñas coinciden.
        assertTrue(gestorDeClientes.modificarContraseña(listaClientes.get(0), "holaPruebaUnitaria", "holaPruebaUnitaria"));
        // la siguiente prueba es en el caso donde ambas contraseñas no coinciden.
        assertFalse(gestorDeClientes.modificarContraseña(listaClientes.get(0), "holaPruebaUnitaria", "adiosPruebaUnitaria"));
        System.out.println(listaClientes.get(0).getContraseña()); // contraseña nueva.
    }
}