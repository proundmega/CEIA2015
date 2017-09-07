package consultas;

import conexiones.Conexiones;
import exceptions.ConnectionException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import objetos_datos.Taller;

/** Esta clase se encargar de trabajar la tabla Taller de la base de datos.
 *
 * @author Vansi Adonay Olivares
 */
public class TalleresData {
    
    /** Verifica si el dia de hoy hay o no talleres para registrar personas
     * 
     * @return true si hoy hay talleres, false si no los hay.
     */
    public static boolean hoyHayTalleres(){
        ResultSet datos = queryTalleresHoy();
        return procesarSiHayTalleres(datos);
    }
    
    private static ResultSet queryTalleresHoy() {
        LocalDate hoy = LocalDate.now();
        String query = "select * from Taller where Fecha='"
                + hoy.toString() + "'";
        return Conexiones.executeQuery(query);
    }
    
    private static boolean procesarSiHayTalleres(ResultSet datos){
        try {
            
            return datos.first();
            
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    /** Devuelve la lista de talleres que estan programados para hoy
     * 
     * @return lee lo de arriba :)
     */
    public static List<Taller> getTalleresHoy(){
        ResultSet datos = queryTalleresHoy();
        return procesarTalleresHoy(datos);
    }

    private static List<Taller> procesarTalleresHoy(ResultSet datos) {
        try {
            
            List<Taller> talleres = new ArrayList<>();
            while(datos.next()){
                talleres.add(crearTaller(datos));
            }
            return talleres;
            
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    private static Taller crearTaller(ResultSet datos) throws SQLException{
        int id = datos.getInt(1);
        String nombreTaller = datos.getString(2);
        LocalDate hoy = LocalDate.now();
        int cuposDisponibles = datos.getInt(4);
        int cuposTotales = datos.getInt(5);
        
        return new Taller(id, nombreTaller, hoy, cuposDisponibles, cuposTotales);
    }

    /** Devuelve la cantidad de cupos que posee el taller enviado como parametro
     *
     * @param taller el taller a consultar
     * @return la cantidad de cupos en este taller
     */
    public static int getCupos(Taller taller) {
        try {
            ResultSet datos = queryCupos(taller);
            datos.first();
            return datos.getInt(1);
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    private static ResultSet queryCupos(Taller taller) {
        String query = "select Cupos_Actuales from Taller where id_Taller='"
                + taller.getIdTaller() + "'";
        return Conexiones.executeQuery(query);
    }

    /** Verifica si hay o no cupos disponibles para el taller dado
     *
     * @param taller el taller que se revisara si tiene cupos
     * @return true si el taller tiene cupos, false si no tiene cupos
     */
    public static boolean hayCupos(Taller taller) {
        return TalleresData.getCupos(taller) > 0;
    }

}
