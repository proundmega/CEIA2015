package objetos_datos;

import java.time.LocalDate;

/** Esta clase representa a un dato en la base de datos de la tabla DiaAsistencia
 *
 * @author Vansi Adonay Olivares
 */
public class DiaAsistencia {
    private String idDia;
    private LocalDate date;
    private String descripcion;
    
    /** El constructor basico de siempre. Se inserta un String con la fecha que
     *  devuelve la base de datos, asi que se debe usar un getString() para
     *  el Dia.
     * 
     * @param idDia el id del dia de la asistencia
     * @param dia el dia de la asistencia
     * @param descripcion una descripcion del dia de asistencia
     */
    public DiaAsistencia(String idDia, String dia, String descripcion) {
        this.idDia = idDia;
        this.date = LocalDate.parse(dia);
        this.descripcion = descripcion;
    }

    public String getIdDia() {
        return idDia;
    }

    public LocalDate getDate() {
        return date;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}
