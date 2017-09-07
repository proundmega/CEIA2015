package objetos_datos;

import java.util.List;

/** Este objeto unicamente encapsula un grupo de alumnos que han asistido 
 *  a un taller. Se usa esta clase intermedia porque se necesita el nombre
 *  del taller al que asistieron y los alumnos que asistieron, ademas del orden.
 *
 * @author Vansi Adonay Olivares.
 */
public class AlumnosPorTaller {
    private Taller taller;
    private List<Persona> personas;

    public AlumnosPorTaller(Taller taller, List<Persona> alumnos) {
        this.taller = taller;
        this.personas = alumnos;
    }

    public Taller getTaller() {
        return taller;
    }

    public List<Persona> getAlumnos() {
        return personas;
    }

    @Override
    public String toString() {
        return "AlumnosPorTaller{" + "taller=" + taller + ", alumnos=" + personas + '}';
    }
    
}
