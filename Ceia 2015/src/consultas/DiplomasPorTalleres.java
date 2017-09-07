package consultas;

import conexiones.Conexiones;
import exceptions.ConnectionException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import objetos_datos.*;

/** Esta clase devuelve la lista de personas que recibiran diploma por cada taller
 *  al que asistieron.
 *
 * @author Vansi Adonay Olivares
 */
public class DiplomasPorTalleres {
    
    /** Devuelve la lista de talleres junto con los asistentes de cada taller
     * 
     * @return solo lee lo de arriba :)
     */
    public static List<AlumnosPorTaller> getDiplomasPorTaller(){
        List<Taller> talleres = getListaTalleres();
        List<AlumnosPorTaller> diplomas = new ArrayList<>();
        for(Taller taller : talleres){
            diplomas.add(getDatosPorTaller(taller));
        }
        return diplomas;
    }
    
    private static List<Taller> getListaTalleres(){
        ResultSet datos = queryListaTalleres();
        return procesarListaTalleres(datos);
    }
    
    private static ResultSet queryListaTalleres(){
        String query = "select * from Taller";
        return Conexiones.executeQuery(query);
    }
    
    private static List<Taller> procesarListaTalleres(ResultSet datos){
        try {
            List<Taller> talleres = new ArrayList<>();
            
            while(datos.next()){
                Taller taller = getTaller(datos);
                talleres.add(taller);
            }
            
            return talleres;
            
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    private static Taller getTaller(ResultSet datos) throws SQLException{
        int idTaller = datos.getInt("id_Taller");
        String nombreTaller = datos.getString("Taller");
        LocalDate fechaTaller = LocalDate.parse(datos.getString("Fecha"));
        int cuposActuales = datos.getInt("Cupos_Actuales");
        int cuposTotales = datos.getInt("Cupos_Totales");
        return new Taller(idTaller, nombreTaller, fechaTaller, cuposActuales, cuposTotales);
    }
    
    private static AlumnosPorTaller getDatosPorTaller(Taller taller){
        ResultSet datos = queryAlumnosPorTaller(taller);
        List<Persona> alumnos = procesarAlumnosPorTaller(datos);
        return new AlumnosPorTaller(taller, alumnos);
    }
    
    private static ResultSet queryAlumnosPorTaller(Taller taller){
        String query = "select * from Taller_por_alumno natural join Alumno "
                + "natural join Carrera where id_Taller='"
                + taller.getIdTaller() + "'";
        return Conexiones.executeQuery(query);
    }
    
    private static List<Persona> procesarAlumnosPorTaller(ResultSet datos){
        try {
            List<Persona> alumnos = new ArrayList<>();
            
            while(datos.next()){
                alumnos.add(getAlumnoPorTaller(datos));
            }
            
            return alumnos;
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    private static Persona getAlumnoPorTaller(ResultSet datos) throws SQLException{
        String apellidos = datos.getString("Apellidos");
        String nombres = datos.getString("Nombres");
        
        Persona persona = new Persona(nombres, apellidos);
        return persona;
    }
    
}
