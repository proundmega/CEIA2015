package objetos_datos;

/** Este objeto representa el nivel de acceso de un usario en el logeo del
 *  sistema
 *
 * @author Vansi Adonay Olivares
 */
public class NivelAcceso {
    private int idNivelUsuario;
    private String nivelUsuario;

    public NivelAcceso(int idNivelUsuario, String nivelUsuario) {
        this.idNivelUsuario = idNivelUsuario;
        this.nivelUsuario = nivelUsuario;
    }

    public int getIdNivelUsuario() {
        return idNivelUsuario;
    }

    public String getNivelUsuario() {
        return nivelUsuario;
    }
    
}
