package conexiones;

import exceptions.ConnectionException;
import java.sql.*;
import javax.swing.JOptionPane;

/** Esta clase el la encargada de conectarse a la
 *  base de datos y en general de preparar todo.
 *
 * @author Vansi Adonay Olivares
 */
public class Conexion {
    
    private Connection conexion;         // Este objeto es el verdadero objeto conexion

    
    /** Construye el objeto Conexion. Se recomienda no crear este objeto, 
     *  sino que usar el creado en Conexiones para no correr multiples
     *  objetos Connection y saturar MySql.
     *
     * @param data la direccion a donde se conectara la instancia de MySql
     */
    
    public Conexion(DataConexion data){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(data.getUrl(), data.getUsuario(), data.getPassword());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error con el driver...");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al conectarse a la base de datos...");
            throw new ConnectionException("No pude conectarme a la base de datos", false);
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
            throw new ConnectionException(c.getMessage());
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
            throw new ConnectionException(c.getMessage());
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
    
    public boolean conexionValida(){
        try {
            return conexion.isClosed();
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
}
