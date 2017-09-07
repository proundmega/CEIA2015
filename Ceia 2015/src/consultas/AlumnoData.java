package consultas;

import conexiones.Conexiones;
import exceptions.ConnectionException;
import objetos_datos.Alumno;
import objetos_datos.Carrera;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import misc.Tools;

/** Esta clase hace las consultas e inserciones sobre la tabla Alumno de
 *  la base de datos.
 *
 * @author Vansi Adonay Olivares
 */
public class AlumnoData {
    
    private AlumnoData(){}
    
    /** Se verifica si el alumno ingresado esta o no en la base 
     *  de datos.
     * 
     * @param carnet el carner con que se verificara en la base de datos
     * @return true si existe, false si no esta en la base de datos.
     */
    public static boolean isAlumnoRegistrado(String carnet){
        ResultSet resultSet = queryExistenciaAlumno(carnet);
        return procesarSiExisteAlumno(resultSet);
    }
    
    private static ResultSet queryExistenciaAlumno(String carnet){
        String query = "select COUNT(*) from Alumno where Carnet='" + carnet + "'";
        return Conexiones.executeQuery(query);
    }
    
    private static boolean procesarSiExisteAlumno(ResultSet resultSet){
        try {
            resultSet.first();
            boolean existe = resultSet.getInt(1) != 0;
            resultSet.close();
            return existe;
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    
    
    
    /** Este metodo devuelve el alumno que esta registrado en la base
     *  de datos a partir del carnet ingresado
     * 
     * @param carnet el carnet con que se buscara el alumno
     * @return un obejto Alumno que posee toda la informacion del alumno
     * @throws IllegalArgumentException si no existe el alumno en la base de datos
     */
    public static Alumno getAlumno(String carnet){
        ResultSet resultSet = queryAlumno(carnet);
        return procesarAlumno(resultSet);
    }
    
    private static ResultSet queryAlumno(String carnet){
        String query = "select * from Alumno natural join Carrera where Carnet='" + carnet + "'";
        return Conexiones.executeQuery(query);
    }
    
    private static Alumno procesarAlumno(ResultSet resultSet){
        try {
            resultSet.first();
            String carnet = resultSet.getString("Carnet");
            String apellidos = resultSet.getString("Apellidos");
            String nombres = resultSet.getString("Nombres");
            
            String codigoCarrera = resultSet.getString("Codigo_Carrera");
            String carrera = resultSet.getString("Carrera");
            Carrera objCarrera = new Carrera(codigoCarrera, carrera);
            
            resultSet.close();
            return new Alumno(carnet, nombres, apellidos, objCarrera);
            
        } catch (SQLException ex) {
            throw new IllegalArgumentException("No existe dicho alumno");
        }
        
    }
    
    
    
    
    /** Inserta el alumno enviado como parametro a la base de datos.
     * 
     * @param alumno el alumno a insertar
     * @throws IllegalArgumentException si el alumno ya existe
     */
    
    public static void insertarAlumno(Alumno alumno){
        verificarSiExisteElAlumno(alumno);
        procederAInsertarAlumno(alumno);
    }
    
    private static void verificarSiExisteElAlumno(Alumno alumno){
        if(isAlumnoRegistrado(alumno.getCarnet())){
            throw new IllegalArgumentException("Alumno ya registrado");
        }
    }
    
    private static void procederAInsertarAlumno(Alumno alumno){
        String query = "insert into Alumno values('" 
                + alumno.getCarnet() + "','"
                + alumno.getApellidos() + "','" 
                + alumno.getNombres()  + "','"
                + alumno.getCarrera().getCodigoCarrera() + "')";
        Conexiones.executeNonQuery(query);
    }
    
    
    
    /** Evnvia los carnets de todos los alumnos registrados en la base de datos 
     *  en una conveniente lista.
     * 
     * @return la lista de carnets de todos los alumnos 
     */
    public static List<String> queryTodosLosCarnets(){
        try {
            List<String> lista = new ArrayList<>();
            String query = "select * from Alumno natural join Carrera";
            ResultSet datos = Conexiones.executeQuery(query);
            while(datos.next()){
                lista.add(datos.getString("Carnet"));
            }
            return lista;
            
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
}
