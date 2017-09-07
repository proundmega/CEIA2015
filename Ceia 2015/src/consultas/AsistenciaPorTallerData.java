package consultas;

import conexiones.Conexiones;
import exceptions.ConnectionException;
import java.sql.ResultSet;
import java.sql.SQLException;
import misc.Tools;
import objetos_datos.Alumno;
import objetos_datos.Taller;

/** Esta clase simplemente es el intermediario entre la tabla AsistenciaPorTaller
 *  y la aplicación
 *  
 *
 * @author Vansi Adonay Olivares
 */
public class AsistenciaPorTallerData {
    
    /** Añade al alumno dado al taller que se envia. Se arroja una excepción si 
     *  ya no hay cupos en el taller.
     * 
     * @param alumno el alumno que se ingresara a esta tabla
     * @param taller el taller en el que participó el alumno
     * @throws IndexOutOfBoundsException si ya no hay cupos
     */
    public static void ingresarAlumnoEnTaller(Alumno alumno, Taller taller){
        verificarSiHayCupos(alumno, taller);
    }
    
    private static void verificarSiHayCupos(Alumno alumno, Taller taller){
        if(TalleresData.hayCupos(taller)){
            procederAIngresarAlumnoATaller(alumno, taller);
            actualizarCupos(taller);
        }
        else{
            throw new IndexOutOfBoundsException("Ya no hay cupos para este taller...");
        }
    }
    
    private static void procederAIngresarAlumnoATaller(Alumno alumno, Taller taller) {
        if(tuplaNueva(alumno, taller)){
            String query = "insert into Taller_por_alumno values('"
                + alumno.getCarnet() + "',"
                + taller.getIdTaller() + ")";
            Conexiones.executeNonQuery(query);
        }
        else{
            throw new UnsupportedOperationException("Este alumno ya esta registrado");
        }
    }
    
    private static boolean tuplaNueva(Alumno alumno, Taller taller){
        try {
            String query = "select * from Taller_por_alumno where Carnet='"
                    + alumno.getCarnet() + "' and id_Taller="
                    + taller.getIdTaller();
            ResultSet datos = Conexiones.executeQuery(query);
            return !datos.first();
        } catch (SQLException ex) {
            Tools.displayMensajeErrorConexionBD();
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    private static void actualizarCupos(Taller taller){
        int cupos = TalleresData.getCupos(taller);
        cupos--;
        String query = "update Taller set Cupos_Actuales="
                + cupos + " where id_Taller="
                + taller.getIdTaller();
        Conexiones.executeNonQuery(query);
    }
    
}
