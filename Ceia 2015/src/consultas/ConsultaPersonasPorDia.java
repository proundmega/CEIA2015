package consultas;

import conexiones.Conexiones;
import exceptions.ConnectionException;
import java.sql.*;
import java.time.DayOfWeek;
import java.util.*;
import javax.swing.table.*;
import objetos_datos.DiaAsistencia;

public class ConsultaPersonasPorDia {
    private DefaultTableModel modelo;
    
    public ConsultaPersonasPorDia() {
        crearModeloBase();
        agregarDatos();
    }
    
    private void crearModeloBase() {
        modelo = new DefaultTableModel(new String[]{
            "Dia asistencia", "Cantidad de personas"
        }, 0);
    }
    
    private void agregarDatos() {
        List<DiaAsistencia> dias = getTodosDiasAsistencia();
        agregarDiasAsistenciaATabla(dias);
    }
    
    protected List<DiaAsistencia> getTodosDiasAsistencia() {
        ResultSet datos = queryTodosTalleres();
        return procesarDiaAsistencias(datos);
    }
    
    private ResultSet queryTodosTalleres() {
        String query = "select * from Dia_asistencia";
        return Conexiones.executeQuery(query);
    }
    
    private List<DiaAsistencia> procesarDiaAsistencias(ResultSet datos){
        try {
            
            List<DiaAsistencia> dias = new ArrayList<>();
            while(datos.next()){
                dias.add(getDiaAsistencia(datos));
            }
            return dias;
            
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    private DiaAsistencia getDiaAsistencia(ResultSet datos) throws SQLException{
        String idDia = datos.getString(1);
        String dia = datos.getString(2);
        String descripcion = datos.getString(3);
        return new DiaAsistencia(idDia, dia, descripcion);
    }
    
    private void agregarDiasAsistenciaATabla(List<DiaAsistencia> dias) {
        dias.stream().forEach(dia -> {
            modelo.addRow(new Object[]{
                getNombreDia(dia) + " " +  dia.getDate().getDayOfMonth() + getDescripcion(dia), getCantidadAsistentes(dia)
            });
        });
    }
    
    private String getNombreDia(DiaAsistencia dia) {
        DayOfWeek local = dia.getDate().getDayOfWeek();
        return traslate(local);
    }
    
    private String traslate(DayOfWeek dia) {
        String diaTraslated = "";
        switch (dia) {
            case MONDAY:
                diaTraslated = "Lunes";
                break;
                
            case TUESDAY:
                diaTraslated = "Martes";
                break;
                
            case WEDNESDAY:
                diaTraslated = "Miercoles";
                break;
                
            case THURSDAY:
                diaTraslated = "Jueves";
                break;
                
            case FRIDAY:
                diaTraslated = "Viernes";
                break;
                
            case SATURDAY:
                diaTraslated = "Sabado";
                break;
                
            case SUNDAY:
                diaTraslated = "Dominigo";
                break;
                
        }
        return diaTraslated;
    }
    
    private int getCantidadAsistentes(DiaAsistencia dia) {
        ResultSet datos = queryConteo(dia);
        return procesarConteo(datos);
    }
    
    private ResultSet queryConteo(DiaAsistencia dia) {
        String query = "select count(*) from Asistencia_por_alumno where id_Dia="
                + dia.getIdDia();
        return Conexiones.executeQuery(query);
    }
    
    private int procesarConteo(ResultSet datos) {
        try {
            
            datos.first();
            return datos.getInt(1);
            
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }
    
    private String getDescripcion(DiaAsistencia dia) {
        return " (" + dia.getDescripcion() + ")";
    }
    
    /** Metodo para retornar la tabla
     * 
     * @return mira lo de arriba
     */
    public TableModel getModel() {
        return modelo;
    }

}
