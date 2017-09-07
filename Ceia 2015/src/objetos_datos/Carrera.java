package objetos_datos;

/** Este objeto representa a una Carrera de la universidad.
 *  En general posee 2 datos que pueden ser accedidos con sus respectivos
 *  get:
 * 
 *  - getCodigoCarrera que es el codigo de la carrera
 *  - getCarrera que devuelve el nombre de la carrera 
 *
 * @author Vansi Adonay Olivares
 */
public class Carrera {
    private final String codigoCarrera;
    private final String carrera;
    
    /** Este constructor es protected para ser llamado unicamente por 
     *  la clase CarreraData y asi no crear Carreras no existentes.
     * 
     * @param codigoCarrera el codigo de la carrera
     * @param carrera el nombre de la carrera
     */
    public Carrera(String codigoCarrera, String carrera){
        this.codigoCarrera = codigoCarrera;
        this.carrera = carrera;
    }
    
    /** Metodo para retornar el codigo de la carrera
     * 
     * @return el codigo de la carrera
     */
    public String getCodigoCarrera() {
        return codigoCarrera;
    }
    
    /** Metodo para retornar el nombre de la carrera
     *  
     * @return el nombre de la carrera 
     */
    public String getCarrera() {
        return carrera;
    }

    @Override
    public String toString() {
        return "Carrera{" + "codigoCarrera=" + codigoCarrera + ", carrera=" + carrera + '}';
    }
    
    
    
}
