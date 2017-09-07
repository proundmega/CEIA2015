package consultas;

import conexiones.Conexiones;
import exceptions.ConnectionException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import objetos_datos.NivelAcceso;

/** Envuelve las consultas sobre los niveles de acceso del usuario
 *
 * @author Vansi Adonay Olivares
 */
public class NivelAccesoData {
    
    public static List<NivelAcceso> getNivelesAcceso(){
        ResultSet datos = queryNivelesAcceso();
        return procesarNivelesAcceso(datos);
    }
    
    private static ResultSet queryNivelesAcceso(){
        String query = "select * from Nivel_acceso";
        return Conexiones.executeQuery(query);
    }
    
    private static List<NivelAcceso> procesarNivelesAcceso(ResultSet datos){
        try {
            
            List<NivelAcceso> lista = new ArrayList<>();
            
            while(datos.next()){
                lista.add(crearNivelAcceso(datos));
            }
            
            return lista;
            
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    private static NivelAcceso crearNivelAcceso(ResultSet datos) throws SQLException{
        int idNivelAcceso = datos.getInt(1);
        String nivel = datos.getString(2);
        return new NivelAcceso(idNivelAcceso, nivel);
    }
}
