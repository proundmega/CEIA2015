package temp;

import java.sql.*;
import javax.swing.JOptionPane;

/** Esta clase el la encargada de conectarse a la
 *  base de datos y en general de preparar todo.
 *
 * @author Vansi Adonay Olivares
 */
public class Conexion {
    
    private final String url = "jdbc:mysql://localhost:3306/ceia2015";
    private final String login = "root"; 
    private final String password = "";
    private Connection conexion;         // Este objeto es el verdadero objeto conexion

    
    /** Construye el objeto Conexion. Se recomienda no crear este objeto, 
     *  sino que usar el creado en Conexiones para no correr multiples
     *  objetos Connection y saturar MySql.
     */
    
    public Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error con el driver...");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al conectarse a la base de datos...");
        }
    }
    
    /** Ejecuta la sentencia SQL enviada pero no retorna resultados.
     *  Esta se usa principalmente para inserciones, updates y borrado de datos.
     * 
     * @param sql la instruccion sql a ejecutar.
     */
    
    public void executeNonQuery(String sql) {
        try {
            
            Statement statement = conexion.createStatement();
            statement.executeUpdate(sql);
            statement.closeOnCompletion();
        } catch (SQLException c) {
            JOptionPane.showMessageDialog(null, "ERROR: " + c.getMessage());
            JOptionPane.showMessageDialog(null, "Por favor pongase en contanto con el administrador");
            throw new UnsupportedOperationException("El sql no funciona");
        }
    }

    
    /** Ejecuta la sentencia SQL enviada. Este metodo debe ser llamado solo para
     *  consultas select, que esperan retorno de datos desde la base de datos.
     * 
     * @param sql la sentencia SQL a ejecutar
     * @return los resultados usando el bendito ResultSet
     */
    
    public ResultSet executeQuery(String sql) {
        try {
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            statement.closeOnCompletion();
            return resultSet;
        } catch (SQLException c) {
            JOptionPane.showMessageDialog(null, "ERROR: " + c.getMessage());
            JOptionPane.showMessageDialog(null, "Por favor pongase en contanto con el administrador");
            throw new UnsupportedOperationException("El sql no funciona");
        } 
    }
    
    /** Cierra la conexion hecha a MySql
     * 
     */
    public void close(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo cerrar la conexion a la base de datos");
        }
    }
    
    
}
