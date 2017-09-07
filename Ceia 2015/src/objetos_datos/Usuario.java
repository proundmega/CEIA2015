package objetos_datos;

/** Esta clase solo encapsula los datos de usuario y contraseña
 *  escritos en el formulario principal.
 *  
 * @author Vansi Adonay Olivares
 */
public class Usuario {
    private final String usuario;
    private final String contraseña;
    
    /** Construye el objeto usuario
     * 
     * @param usuario el usuario que ingreso... el usuario :p
     * @param contraseña la contraseña que ingreso el usuario
     */
    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    
    
}
