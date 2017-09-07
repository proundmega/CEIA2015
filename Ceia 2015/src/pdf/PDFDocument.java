package pdf;

/** Solo es la interface para crear los documentos PDF. Esta clase solo funciona
 *  en Windows.
 *
 * @author Vansi Adonay Olivares
 */
public interface PDFDocument {
    
    /** Guarda todo lo hecho al PDF en el escritorio del usuario actual.
     */
    public void guardar();
    
    /** Cierra el documento creado. Los datos sin guardar se pierden.
     */
    public void cerrar();
    
    
    /** Retorna el nombre del archivo que se creara.
     * 
     * @return el nombre del archivo que debe ser buscado en el Escritorio
     */
    public String getNombreArchivo();
}
