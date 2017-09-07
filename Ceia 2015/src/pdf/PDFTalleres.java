package pdf;

import java.io.IOException;
import java.util.List;
import objetos_datos.AlumnosPorTaller;
import objetos_datos.Taller;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/** Esta clase es la encargada de crear el PDF para los talleres.
 *
 * @author Vansi Adonay Olivares
 */
public class PDFTalleres extends PDFBase{
    
    public PDFTalleres(){
        super("Ceia2015 - Diplomas por talleres.pdf");
    }
    
    public void agregarDatosAlumnosPorTaller(List<AlumnosPorTaller> lista){
        for(AlumnosPorTaller dato : lista){
            procesarAlumnoPorTaller(dato);
        }
    }
    
    private void procesarAlumnoPorTaller(AlumnosPorTaller dato){
        agregarEncabezadoTaller(dato.getTaller());
        agregarAlumnos(dato.getAlumnos());
        agregarPagina();
        resetearConteoAlumno();
    }
    
    private void agregarEncabezadoTaller(Taller taller){
        try {
            String titulo = "Alumnos con derecho a diploma del taller:";
            String nombreTaller = taller.getNombreTaller();
            PDFont font = PDType1Font.TIMES_BOLD_ITALIC;
            
            insertador.beginText();
            insertador.setFont(font, 20);
            insertador.moveTextPositionByAmount(50, posicionActual);
            insertador.drawString(titulo);
            insertador.endText();
            
            posicionActual -= 50;
            
            insertador.beginText();
            insertador.moveTextPositionByAmount(70, posicionActual);
            insertador.drawString(nombreTaller);
            insertador.endText();
            posicionActual -= 100;
            
        } catch (IOException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
}
