package misc;

import javax.swing.JOptionPane;
import visual.FormCierre;

/**Clase con todo tipo de metodos que son de utileria.
 *
 * @author Vansi Adonay Olivares
 */
public class Tools {
    
    /** Retorna un String para nombres o apellidos "sanitizado", es decir que
     *  deja en mayúsculas cada letra inicial, remueve todos los espacios extras
     *  y entre otras cosas.
     * 
     * @param datos el String a sanitizar.
     * @return una version sanitizada del String ingresado.
     */
    public static String sanitizarString(String datos){
        datos = removerEspaciosDeMas(datos);
        String[] data = datos.split(" ");
        String resultado = "";
        for (String elemento : data) {
            resultado += elemento.substring(0, 1).toUpperCase() + elemento.substring(1).toLowerCase() + " ";
        }
        resultado = resultado.substring(0, resultado.length() - 1);
        return resultado;
    }
    
    private static String removerEspaciosDeMas(String datos){
        String cambiado = datos;
        while(cambiado.contains("  ")){
            cambiado = cambiado.replace("  ", " ");
        }
        if(cambiado.endsWith(" ")){
            cambiado = cambiado.substring(0, cambiado.length() - 1);
        }
        if(cambiado.startsWith(" ")){
            cambiado = cambiado.substring(1);
        }
        return cambiado;
    }
    
    
    /** Este es un filtrado para solo permitir en un JTextField solo numeros y letras
     * 
     * @param evt el evento a modificar
     */
    public static void soloNumerosYLetras(java.awt.event.KeyEvent evt){
        char caracter = evt.getKeyChar();
        if(!(Character.isLetter(caracter) || Character.isDigit(caracter))){
            evt.consume();
        }
    }
    
    public static void displayMensajeErrorConexionBD(){
        JOptionPane.showMessageDialog(null, "Ocurrio un error al conectarse a la base de datos...");
    }
    
    
    
    public static void mostrarCierreSecion(){
        new FormCierre(null, true).setVisible(true);
    }
    
}
