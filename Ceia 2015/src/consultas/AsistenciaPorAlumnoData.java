package consultas;

import conexiones.Conexiones;
import exceptions.ConnectionException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Esta bella clase solo es para consultar los alumnos con derecho a
 *  diploma por asistir
 *
 * @author Vansi Adonay Olivares
 */
public class AsistenciaPorAlumnoData {
    
    
    /** Devuelve todos los carnets diferentes que estaban en la tabla
     *  Asistencia_por_alumno
     * 
     * @return lee lo de arriba
     */
    public static Set<String> getCarnetsEnTablaAsistenciaPorAlumno(){
        ResultSet datos = queryCarnetsEnTablaAsistenciaPorAlumno();
        return procesarPeticionDeCarnets(datos);
    }
    
    private static ResultSet queryCarnetsEnTablaAsistenciaPorAlumno(){
        String query = "select * from Asistencia_por_alumno";
        return Conexiones.executeQuery(query);
    }
    
    private static Set<String> procesarPeticionDeCarnets(ResultSet datos){
        try {
            
            Set<String> set = new HashSet<>();
            while(datos.next()){
                set.add(datos.getString("Carnet"));
            }
            return set;
            
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
}
