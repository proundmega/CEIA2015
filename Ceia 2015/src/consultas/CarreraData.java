package consultas;

import conexiones.Conexiones;
import exceptions.ConnectionException;
import objetos_datos.Carrera;
import java.sql.*;
import java.util.*;

public class CarreraData {
    private static final CarreraData me = new CarreraData();
    
    private final List<Carrera> listaCarreras;
    
    private CarreraData(){
        ResultSet datos = getCarreras();
        listaCarreras = convertirALista(datos);
    }
    
    public static List<Carrera> getListaCarreras(){
        return me.listaCarreras;
    }
    
    private static ResultSet getCarreras(){
        String query = "select * from Carrera";
        return Conexiones.executeQuery(query);
    }
    
    private static List<Carrera> convertirALista(ResultSet datos){
        ArrayList<Carrera> lista = new ArrayList<>();
        try {
            while(datos.next()){
                String codigoCarrera = datos.getString(1);
                String nombreCarrera = datos.getString(2);
                Carrera carrera = new Carrera(codigoCarrera, nombreCarrera);
                lista.add(carrera);
            }
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
        return lista;
    }
    
}
