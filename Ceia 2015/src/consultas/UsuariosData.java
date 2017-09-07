package consultas;

import conexiones.Conexiones;
import exceptions.ConnectionException;
import objetos_datos.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import objetos_datos.NivelAcceso;
import visual.*;

/** Esta clase maneja la intefaz de la tabla Login
 *
 * @author Vansi Adonay Olivares
 */
public class UsuariosData {
    
    /** Verifica si el usuario enviado existe, y retorna true si existe
     *  y false si no.
     * 
     * @param usuario el Usuario ingresado por el... usuario :p
     * @return true si existe el usuario dado, false si no existe el usuario
     */
    public static boolean isLogueoValido(Usuario usuario){
        ResultSet resultado = queryLogueoValido(usuario);
        return procesarVerificacionLogueo(resultado);
    }
    
    private static ResultSet queryLogueoValido(Usuario usuario){
        String query = "select * from Login where Usuario='"
                + usuario.getUsuario() + "' and Password='"
                + usuario.getContraseña() + "'";
        return Conexiones.executeQuery(query);
    }
    
    private static boolean procesarVerificacionLogueo(ResultSet resultado){
        try {
            return existeUsuario(resultado);
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    private static boolean existeUsuario(ResultSet resultado) throws SQLException{
        return resultado.first() == true;
    }
    
    /** Devuelve el formulario a mostrar al usuario en base al las credenciales
     *  ingresadas (la vista Administrador para el usuario Administrador, etc).
     * 
     * @param usuario el usuario ingresado por... el usuario :p
     * @return el formulario a mostrar en pantalla luego de ingresar exitosamente
     * @throws IllegalArgumentException si el usuario ingresado no existe en la base de datos
     * @throws UnsupportedOperationException si no se tiene un formulario para el nivel de acceso especificado
     */
    public static JFrame getFormularioDelUsuario(Usuario usuario){
        verificarSiExisteUsuario(usuario);
        return getFormulario(usuario);
    }
    
    private static void verificarSiExisteUsuario(Usuario usuario){
        if(!isLogueoValido(usuario)){
            throw new IllegalArgumentException("El usuario ingresado es invalido");
        }
    }
    
    private static JFrame getFormulario(Usuario usuario){
        ResultSet resultados = queryNivelUsuario(usuario);
        return procesarPeticionFormulario(resultados);
    }
    
    private static ResultSet queryNivelUsuario(Usuario usuario){
        String query = "select * from Login natural join Nivel_acceso where Usuario='"
                + usuario.getUsuario() + "' and Password='"
                + usuario.getContraseña() + "'";
        return Conexiones.executeQuery(query);
    }
    
    private static JFrame procesarPeticionFormulario(ResultSet resultados){
        try {
            resultados.first();
            String nivelLogueo = resultados.getString("Nivel");
            return getFormularioPorNivel(nivelLogueo);
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    private static JFrame getFormularioPorNivel(String nivel){
        JFrame form;
        switch (nivel){
            case "Administrador":
                form = new FormAministrador();
                break;
                
            case "Ponencias":
                if(!DiaAsistenciaData.hoyHayPonencia()){
                    throw new UnsupportedOperationException("Hoy no se registra la asistencia");
                }
                form = new FormPonencia();
                break;
                
            case "Talleres":
                if(!TalleresData.hoyHayTalleres()){
                    throw new UnsupportedOperationException("Hoy no hay talleres");
                }
                form = new FormTalleres();
                break;
                
            default:
                throw new UnsupportedOperationException("No hay un formulario para este tipo de usuario...");
        }
        return form;
    }
    
    
    /** Verifica si el nombre de usuario que se envia como parametro ya esta
     *  registrado en la base de datos. Se usa mas que todo para ingresar un
     *  nuevo usuario.
     * 
     * @param nombreUsuario el nombre de usuario a verificar
     * @return true si ya existe este usuario, false si no existe.
     */
    public static boolean existeUsuario(String nombreUsuario){
        ResultSet datos = queryExistenciaUsuario(nombreUsuario);
        return procesarExistenciaUsuario(datos);
    }
    
    private static ResultSet queryExistenciaUsuario(String usuario){
        String query = "select * from Login where Usuario='" + usuario + "'";
        return Conexiones.executeQuery(query);
    }
    
    private static boolean procesarExistenciaUsuario(ResultSet datos){
        try {
            
            return datos.first();
            
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    /** Agrega el nuevo usuario a la base de datos con el nivel de acceso especificado
     * 
     * @param usuario el nuevo usuario a agregar
     * @param nivelAccesoDeseado el nivel de acceso que tendra este usuario
     * @throws  IllegalArgumentException si el usuario ya esta registrado
     */
    public static void agregarUsuario(Usuario usuario, NivelAcceso nivelAccesoDeseado){
        if(!existeUsuario(usuario.getUsuario())){
            procederAAgregarUsuario(usuario, nivelAccesoDeseado);
        }
        else{
            throw new IllegalArgumentException("Este usuario ya esta ingresado");
        }
    }
    
    private static void procederAAgregarUsuario(Usuario usuario, NivelAcceso nivelAccesoDeseado){
        String query = "insert into Login values('"
                + usuario.getUsuario() + "','"
                + usuario.getContraseña() + "',"
                + nivelAccesoDeseado.getIdNivelUsuario() + ")";
        Conexiones.executeNonQuery(query);
    }
    
    /** Reestablece el usuario con los datos enviados. Se asume que el usuario existe. 
     *  Si el usuario no exite, se arroja una excepcion.
     * 
     * @param nuevoUsuario el objeto que tiene tanto el nombre de usuario y la contraseña
     *                      final para ese usuario
     * 
     */
    public static void cambiarContraseñaUsuario(Usuario nuevoUsuario){
        validarDatosCambioContraseña(nuevoUsuario);
        procesarCambioContraseña(nuevoUsuario);
    }
    
    private static void validarDatosCambioContraseña(Usuario nuevoUsuario){
        if(!existeUsuario(nuevoUsuario.getUsuario())){
            throw new IllegalArgumentException("Este usuario no existe");
        }
    }
    
    private static void procesarCambioContraseña(Usuario nuevoUsuario){
        String query = "update Login set Password='"
                + nuevoUsuario.getContraseña() + "' where Usuario='"
                + nuevoUsuario.getUsuario() + "'";
        Conexiones.executeNonQuery(query);
    }
}
