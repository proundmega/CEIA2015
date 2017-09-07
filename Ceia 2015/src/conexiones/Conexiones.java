package conexiones;

import java.sql.ResultSet;


/** La idea de esta clase es usar una sola conexion
 *  para todo el proyecto y no crear tantas conexiones 
 *  innecesarias, que es como lo hace el conector del
 *  Ing. Colon (tristemente...)
 * 
 * @author Vansi Adonay Olivares
 */

public class Conexiones {
    private static boolean conexionFallida = false;
    private static Conexion conexion = crearConexion();
    
    private static Conexion crearConexion() {
        DataConexion data = new ConexionReader().getDataConexionPorTexto();
        return new Conexion(data);
    }
    
    /** Obtiene la conexion general de la aplicacion.
     * 
     * @return la conexion.
     */
    public static Conexion getConexion(){
        return conexion;
    }
    
    public static ResultSet executeQuery(String query){
        try {
            if(conexionFallida){
                crearNuevaConexion();
            }
            return conexion.executeQuery(query);
        } catch (Exception e) {
            conexionFallida = true;
            throw e;
        }
        
    }
    
    public static void executeNonQuery(String query){
        try {
            if(conexionFallida){
                crearNuevaConexion();
            }
            conexion.executeNonQuery(query);
        } catch (Exception e) {
            conexionFallida = true;
            throw e;
        }
        
    }
    
    private static void crearNuevaConexion(){
        DataConexion data = new ConexionReader().getDataConexionPorTexto();
        conexion = new Conexion(data);
        conexionFallida = false;
    }
    
}
