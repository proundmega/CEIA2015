package consultas;

import conexiones.Conexiones;
import exceptions.ConnectionException;
import objetos_datos.DiaAsistencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** La clase representando los datos en la tabla Dia_Ponencia
 *
 * @author Vansi Adonay Olivares
 */
public class DiaAsistenciaData {
    private static DiaAsistenciaData data = new DiaAsistenciaData();

    private DiaAsistencia hoy;
            
    private DiaAsistenciaData(){
        hoy = null;
    }
    
    /** Verifica si el dia de hoy hay o no ponencia
     * 
     * @return true si hay ponencias hoy, false si no las hay
     */
    public static boolean hoyHayPonencia(){
        ResultSet resultSet = querySiExistePoneciaHoy();
        return procesarSiHayPonencia(resultSet);
    }
    
    private static ResultSet querySiExistePoneciaHoy(){
        LocalDate hoy = LocalDate.now();
        String query = "select COUNT(*) from Dia_asistencia where Dia='" + hoy.toString() + "'";
        return Conexiones.executeQuery(query);
    }
    
    private static boolean procesarSiHayPonencia(ResultSet resultSet){
        try {
            resultSet.first();
            return resultSet.getInt(1) != 0;
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    
    /** Devuelve el dia de asistencia correspondiente al dia de hoy. Si no
     *  hay dia de asistencia para este dia, se arroja una excepcion.
     * 
     * @return el objeto dia de asistencia de hoy.
     * @throws IllegalArgumentException si no hay ponencia para hoy
     */
    public static List<DiaAsistencia> getDiasAsistenciaHoy(){
        verificarSiHayPonenciaHoy();
        ResultSet datos = queryDiaAsistenciaActual();
        return crearDiaAsistencia(datos);
    }
    
    private static void verificarSiHayPonenciaHoy(){
        if(!hoyHayPonencia()){
            throw new IllegalArgumentException("Hoy no hay ponencia");
        }
    }
    
    private static ResultSet queryDiaAsistenciaActual(){
        LocalDate hoy = LocalDate.now();
        String query = "select * from Dia_asistencia where Dia='" + hoy.toString() + "'";
        return Conexiones.executeQuery(query);
    }
    
    private static List<DiaAsistencia> crearDiaAsistencia(ResultSet datos){
        try {
            List<DiaAsistencia> listaDias = new ArrayList<>();
            while(datos.next()){
                String idDia = datos.getString(1);
                String diaString = datos.getString(2);
                String descripcion = datos.getString(3);
                DiaAsistencia dia = new DiaAsistencia(idDia, diaString, descripcion);
                listaDias.add(dia);
            }
            return listaDias;
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    /** Agregar al alumno con el carnet dado al dia de asistencia actual.
     * 
     * @param carnet el carnet del alumno a agregar
     * @throws IllegalArgumentException si el alumno no esta agregado
     * @throws UnsupportedOperationException si no hay una ponencia programada para hoy
     */
    public static void agregarAlumnoADiaActual(String carnet){
        validarAlumnoYAsistencia(carnet);
        verificarSiYaEstaRegistrado(carnet);
        agregarAlumno(carnet);
    }
    
    private static void verificarSiYaEstaRegistrado(String carnet){
        ResultSet datos = querySiExisteTupla(carnet);
        procesarSiExisteTupla(datos);
    }
    
    private static ResultSet querySiExisteTupla(String carnet) {
        String query = "select * from Asistencia_por_alumno where Carnet='"
                + carnet + "' and id_Dia='"
                + data.hoy.getIdDia() + "'";
        return Conexiones.executeQuery(query);
    }
    
    private static void procesarSiExisteTupla(ResultSet datos) {
        try {
            if(datos.first()){
                throw new UnsupportedOperationException("Este alumno ya se registro");
            }
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    private static void validarAlumnoYAsistencia(String carnet){
        if(!AlumnoData.isAlumnoRegistrado(carnet)){
            throw new IllegalArgumentException("Ingrese el alumno a la base de datos "
                    + "antes de ponerlo en este dia");
        }
        if(!hoyHayPonencia()){
            throw new UnsupportedOperationException("Hoy no hay ponencias");
        }
    }
    
    private static void agregarAlumno(String carnet){
        DiaAsistencia hoy = data.hoy;
        String query = "insert into Asistencia_por_alumno values('"
                + carnet + "','"
                + hoy.getIdDia() + "')";
        Conexiones.executeNonQuery(query);
    }
    
    public static void setDiaTrabajo(DiaAsistencia dia) {
        data.hoy = dia;
    }
}
