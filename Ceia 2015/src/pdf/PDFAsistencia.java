package pdf;

import java.io.IOException;
import java.util.List;
import objetos_datos.Persona;
import org.apache.pdfbox.pdmodel.font.*;

/** Esta clase crea el archivo en PDF para todos los asistentes al congreso que cumplen
 *  con la condicion de al menos haber asistido 3 dias y ser registrados en la base
 *  de datos. Solo funciona en entornos Windows.
 * 
 * @author Vansi Adonay Olivares
 */
public class PDFAsistencia extends PDFBase implements PDFDocument{
    
    public PDFAsistencia(){
        super("Ceia2015 - Diplomas por asistencia.pdf");
        agregarTituloAPaginaActual();
    }
    
    private void agregarTituloAPaginaActual(){
        try {
            String titulo = "Nomina de alumnos con derecho a diploma por asistencia.";
            PDFont font = PDType1Font.TIMES_BOLD_ITALIC;
            
            insertador.beginText();
            insertador.setFont(font, 20);
            insertador.moveTextPositionByAmount(50, posicionActual);
            insertador.drawString(titulo);
            insertador.endText();
            posicionActual -= 100;
        } catch (IOException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
    /** Agrega las personas al PDF que contiene todos los que recibiran un diploma
     *  por haber asisitido al congreso.
     * 
     * @param personas la lista de personas con derecho a diploma
     */
    public void agregarNominaAlumnos(List<Persona> personas){
        agregarAlumnos(personas);
    }
    
   
    
}