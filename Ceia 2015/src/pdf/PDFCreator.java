package pdf;

import objetos_datos.Alumno;
import consultas.DiplomasPorAsistencia;
import consultas.DiplomasPorTalleres;
import java.util.List;
import objetos_datos.AlumnosPorTaller;
import objetos_datos.Persona;

/** Esta es la interface que hace la vida mas facil al no necesitar entender
 *  las clases de PDF
 *
 * @author Vansi Adonay Olivares
 */
public class PDFCreator {
    
    /** Hace las consultas en la base de datos y crea los PDFs necesarios...
     *  todo con un simple metodo :)
     */
    public static void crearPDFs(){
        crearPDFAsistencias();
        crearPDFTalleres();
    }
    
    private static void crearPDFAsistencias(){
        List<Persona> lista = DiplomasPorAsistencia.getListaPersonasConDiplomaAsistencia();
        terminarPDFAsistencias(lista);
    }
    
    private static void terminarPDFAsistencias(List<Persona> lista){
        PDFAsistencia pdf = new PDFAsistencia();
        pdf.agregarNominaAlumnos(lista);
        pdf.guardar();
    }
    
    private static void crearPDFTalleres(){
        List<AlumnosPorTaller> lista = DiplomasPorTalleres.getDiplomasPorTaller();
        terminarPDFTalleres(lista);
    }
    
    private static void terminarPDFTalleres(List<AlumnosPorTaller> lista){
        PDFTalleres talleres = new PDFTalleres();
        talleres.agregarDatosAlumnosPorTaller(lista);
        talleres.guardar();
        talleres.cerrar();
    }
    
    
}
