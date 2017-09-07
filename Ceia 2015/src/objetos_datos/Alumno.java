package objetos_datos;

/** Esta clase esta diseñada para el ingreso de un nuevo alumno y para
 *  obtener los datos de un alumno registrado y simplemente mostrarlos 
 *  en los formularios de registro de ponencias o talleres.
 * 
 * @author Vansi Adonay Olivares
 */

public class Alumno {
    private final String carnet;
    private final String nombres;
    private final String apellidos;
    private final Carrera carrera;
    
    /** Constructor basico, nada del otro mundo
     *  
     * @param carnet
     * @param nombres
     * @param apellidos
     * @param carrera 
     */
    
    public Alumno(String carnet, String nombres, String apellidos, Carrera carrera) {
        this.carnet = carnet;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.carrera = carrera;
    }

    public String getCarnet() {
        return carnet;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    @Override
    public String toString() {
        return "Alumno{" + "carnet=" + carnet + ", nombres=" + nombres + ", apellidos=" + apellidos + ", carrera=" + carrera + '}';
    }
    
}
