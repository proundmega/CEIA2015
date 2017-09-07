package conexiones;

public class DataConexion {
    private String url;
    private String usuario;
    private String password;

    public DataConexion(String url, String usuario, String password) {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }
    
}
