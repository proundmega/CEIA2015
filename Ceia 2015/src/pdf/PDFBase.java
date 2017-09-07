package pdf;

import java.io.IOException;
import java.util.List;
import objetos_datos.Persona;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.*;

/** Implementa la interface PDFDocument para no hacerlo siempre
 *  para cada clase
 *
 * @author Vansi Adonay Olivares
 */
class PDFBase implements PDFDocument{
    protected String nombreArchivo;
    protected PDDocument documento;
    protected PDPage paginaActual;
    protected PDPageContentStream insertador;
    protected int posicionActual;
    private int alumnoActual;
    
    public PDFBase(String nombreArchivo){
        documento = new PDDocument();
        alumnoActual = 1;
        this.nombreArchivo = nombreArchivo;
        agregarPagina();
    }
    
    protected final void agregarPagina(){
        try {
            paginaActual = new PDPage();
            documento.addPage(paginaActual);
            insertador = new PDPageContentStream(documento, paginaActual);
            posicionActual = 720;
        } catch (IOException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }

    protected final void agregarAlumnos(List<Persona> personas) {
        try {
            
            prepararInsertador();
            personas.stream().forEach(this::ponerAlumnoEnPDF);
            closeInsertador();
            
        } catch (IOException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
    private void prepararInsertador() throws IOException{
        insertador.setFont(PDType1Font.TIMES_ROMAN, 17);
    }
    
    private void ponerAlumnoEnPDF(Persona alumno){
        try {
            
            String linea = getStringAInsertar(alumno);
            ponerDatoEnPDF(linea);
            prepararSiguienteIteracion();
            
        } catch (IOException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
    private String getStringAInsertar(Persona alumno){
        return formatActual(alumnoActual) + ".- " + alumno.getNombres() + " " + alumno.getApellidos();
    }
    
    private String formatActual(int actual){
        String data = String.valueOf(actual);
        int numero = data.length();
        switch (numero){
            case 1:
                data = "    " + data;
                break;
                
            case 2:
                data = "  " + data;
                break;
        }
        return data;
        
    }
    
    private void ponerDatoEnPDF(String linea) throws IOException{
        insertador.beginText();
        insertador.moveTextPositionByAmount(100, posicionActual);
        insertador.drawString(linea);
        insertador.endText();
    }
    
    private void prepararSiguienteIteracion() throws IOException{
        alumnoActual++;
        posicionActual -= 25;
        if(paginaCompleta()){
            closeInsertador();
            agregarPagina();
            prepararInsertador();
            posicionActual -= 50;
        }
    }
    
    private boolean paginaCompleta(){
        return posicionActual <= 100;
    }
    
    protected void closeInsertador() throws IOException{
        insertador.close();
    }
    
    /** Guarda el documento con el nombre que la clase como tal le asigne 
     *  en el escritorio.
     * 
     *  Nota: Para que se pueda guardar el documento debe estar sin ser usado
     *  por otra aplicacion, de otra manera esta operacion fallara
     * 
     *  @throws IllegalStateException si algo inesperado ocurrio
     */
    @Override
    public void guardar() {
        try {
            documento.save(Path.getDireccionEscritorio() + "\\" + nombreArchivo);
        } catch (IOException | COSVisitorException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
    /** Cierra el documento sin guardar los cambios.
     *  @throws IllegalStateException si algo inesperado ocurre
     */
    @Override
    public void cerrar() {
        try {
            documento.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }

    @Override
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    
    protected void resetearConteoAlumno(){
        alumnoActual = 1;
    }
    
    
}
