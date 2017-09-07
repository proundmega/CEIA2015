package consultas;

import conexiones.Conexiones;
import exceptions.ConnectionException;
import objetos_datos.Alumno;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos_datos.Persona;

/** Esta clase crea la lista de personas que rebirian un diploma por asistir
 *
 * @author Vansi Adonay Olivares
 */
public class DiplomasPorAsistencia {
    
    
    /** Devuelve la lista de personas con derecho a diploma por asistir al 
     *  al menos a 3 de los 5 dias del congreso. Esta lista incluye alumnos de
     *  la UES como particulares.
     * 
     * @return la lista de personas que recibiran diploma.
     */
    public static List<Persona> getListaPersonasConDiplomaAsistencia(){
        Set<String> carnets = AsistenciaPorAlumnoData.getCarnetsEnTablaAsistenciaPorAlumno();
        List<Persona> lista = getAlumnosValidosAsistencia(carnets);
        agregarPersonasExternas(lista);
        return lista;
    }
    
    private static List<Persona> getAlumnosValidosAsistencia(Set<String> carnets){
        List<Persona> lista = new ArrayList<>();
        carnets.stream().forEach((carnet) -> {
            try {
                lista.add(getAlumnoSiTieneDiplomaPorAsistencia(carnet));
            }
            catch(InstantiationException ex){}
        });
        return lista;
    }
    
    private static Persona getAlumnoSiTieneDiplomaPorAsistencia(String carnet) throws InstantiationException{
        ResultSet consulta = querySiAlumnoTieneDiplomaPorAsistencia(carnet);
        return procesarSiAlumnoTieneDiplomaPorAsistencia(consulta, carnet);
    }
    
    private static ResultSet querySiAlumnoTieneDiplomaPorAsistencia(String carnet){
        String query = "select COUNT(*) from Asistencia_por_alumno where Carnet='" + carnet + "'";
        return Conexiones.executeQuery(query);
    }
    
    private static Persona procesarSiAlumnoTieneDiplomaPorAsistencia(ResultSet consulta, String carnet) throws InstantiationException{
        try {
            consulta.first();
            int asistencias = consulta.getInt(1);
            consulta.close();
            if(recibiraDiplomaPorAsistencia(asistencias)){
                Alumno alumno = AlumnoData.getAlumno(carnet);
                return new Persona(alumno.getNombres(), alumno.getApellidos());
            }
            else{
                throw new InstantiationException();
            }
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    private static boolean recibiraDiplomaPorAsistencia(int asistencias){
        return asistencias >= 3;
    }
    
    protected static void agregarPersonasExternas(List<Persona> lista){
        ResultSet datos = queryPersonasExternasConDiploma();
        Set<Integer> idPersonas = getIdPersonas(datos);
        procesarYAgregarPersonas(lista, idPersonas);
    }
    
    private static Set<Integer> getIdPersonas(ResultSet datos) {
        try {
            Set<Integer> idPersonas = new HashSet<>();
            while(datos.next()){
                idPersonas.add(datos.getInt("idPersona"));
            }
            return idPersonas;
            
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }

    private static ResultSet queryPersonasExternasConDiploma() {
        String query = "select * from Asistencia_por_persona";
        return Conexiones.executeQuery(query);
    }

    private static void procesarYAgregarPersonas(List<Persona> lista, Set<Integer> idPersonas) {
        for(int idPersona : idPersonas){
            intentarIngresarPersona(lista, idPersona);
        }
    }

    private static void intentarIngresarPersona(List<Persona> lista, int idPersona){
        try {
            
            ResultSet consulta = querySiPersonaTieneDerechoDiploma(idPersona);
            consulta.first();
            if(recibiraDiplomaPorAsistencia(consulta.getInt(1))){
                lista.add(PersonaData.getPersona(idPersona));
            }
            consulta.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DiplomasPorAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static ResultSet querySiPersonaTieneDerechoDiploma(int codigoPersona) {
        String query = "select count(*) from Asistencia_por_persona where idPersona="
                + codigoPersona;
        return Conexiones.executeQuery(query);
    }

}
