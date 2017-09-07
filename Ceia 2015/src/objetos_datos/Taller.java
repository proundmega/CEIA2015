package objetos_datos;

import java.time.LocalDate;

/** Esta clase encapsula los talleres de la base de datos.
 *
 * @author Vansi Adonay Olivares
 */
public class Taller {
    private int idTaller;
    private String nombreTaller;
    private LocalDate fechaTaller;
    private int cuposActuales;
    private int cuposTotales;

    public Taller(int idTaller, String nombreTaller, LocalDate fechaTaller, int cuposActuales, int cuposTotales) {
        this.idTaller = idTaller;
        this.nombreTaller = nombreTaller;
        this.fechaTaller = fechaTaller;
        this.cuposActuales = cuposActuales;
        this.cuposTotales = cuposTotales;
    }

    public int getIdTaller() {
        return idTaller;
    }

    public String getNombreTaller() {
        return nombreTaller;
    }

    public LocalDate getFechaTaller() {
        return fechaTaller;
    }
    
    public int getCuposActuales() {
        return cuposActuales;
    }

    public boolean hayCupos() {
        return cuposActuales > 0;
    }

    public int getCuposTotales() {
        return cuposTotales;
    }

    @Override
    public String toString() {
        return "Taller{" + "idTaller=" + idTaller + ", nombreTaller=" + nombreTaller + ", fechaTaller=" + fechaTaller + ", cuposActuales=" + cuposActuales + ", cuposTotales=" + cuposTotales + '}';
    }
    
    public boolean puedeRecibirTaller(Alumno alumno){
        String codigoCarreraAlumno = alumno.getCarrera().getCodigoCarrera();
        return codigoCarreraAlumno.equals("I30501") || codigoCarreraAlumno.equals("I30502")
                || codigoCarreraAlumno.equals("I30515");
    }
    
}
