package conexiones;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/** Esta clase lee la conexion del archivo conexion.txt que es el que 
 *  contiene donde se conectara MySql.
 *
 * @author Vansi Adonay Olivares
 */
public class ConexionReader {
    
    public DataConexion getDataConexionPorTexto() {
        try {
            
            return crearDataConexion();
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "ERROR: No se pudo obtener el archivo de conexion a la base de datos");
            System.exit(1);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "ERROR: Archivo de conexion mal configurado...");
            System.exit(1);
        }
        return null;
    }
    
    private DataConexion crearDataConexion() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("conexion.txt"));
        String url = getUrl(reader.readLine());
        String usuario = getUsuario(reader.readLine());
        String password = getPassword(reader.readLine());
        return new DataConexion(url, usuario, password);
    }
    
    private String getUrl(String lineaUrl){
        return lineaUrl.replace("URL:", "").trim();
        
    }
    
    private String getUsuario(String lineaUsuario) {
        return lineaUsuario.replace("Usuario:", "").trim();
    }
    
    private String getPassword(String lineaPassword) {
        return lineaPassword.replace("Password:", "").trim();
    }
}
