package consultas;

import conexiones.Conexiones;
import exceptions.ConnectionException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos_datos.Persona;

/**Esta clase es la encargada de accesar a los datos de la tabla Persona.
 *
 * @author Vansi Olivares
 */
public class PersonaData {
    
    /** Verifica si esta persona esta o no registrada en la base de datos usando
     *  como base sus nombres y apellidos. Tristemente es la unica manera...
     * 
     * @param persona la persona a verificar si existe o no
     * @return true si tanto los nombres como los apellidos coinciden, false si no
     */
    public static boolean estaRegistrado(Persona persona){
        ResultSet datos = querySiEstaRegitrado(persona);
        return procesarSiEstaRegistrado(datos);
    }
    
    private static ResultSet querySiEstaRegitrado(Persona persona){
        String query = "select * from Persona where Apellidos='"
                + persona.getApellidos() + "' and Nombres='"
                + persona.getNombres() + "'";
        return Conexiones.executeQuery(query);
    }
    
    private static boolean procesarSiEstaRegistrado(ResultSet datos){
        try {
            
            return datos.first();
            
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    /** Ingresa la persona dada si no se encuentra registrada en la base de datos.
     * 
     * @param persona la pesona a ingresar a la base de datos
     * @throws IllegalArgumentException si la persona ya ha sido ingresada
     */
    public static void ingresarPersona(Persona persona){
        if(!estaRegistrado(persona)){
            registrarPersona(persona);
        }
        else{
            throw new IllegalArgumentException("Esta persona ya esta registrada");
        }
    }
    
    private static void registrarPersona(Persona persona){
        String query = "insert into Persona(Apellidos, Nombres) values('"
                + persona.getApellidos() + "','"
                + persona.getNombres() + "')";
        Conexiones.executeNonQuery(query);
    }
    
    
    public static Persona getPersona(int idPersona){
        ResultSet datos = queryPersona(idPersona);
        return procesarPersona(datos);
    }

    private static ResultSet queryPersona(int idPersona) {
        String query = "select * from Persona where idPersona="
                + idPersona;
        return Conexiones.executeQuery(query);
    }

    private static Persona procesarPersona(ResultSet datos) {
        try {
            
            datos.first();
            String nombres = datos.getString("Nombres");
            String apellidos = datos.getString("Apellidos");
            return new Persona(nombres, apellidos);
            
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
}
