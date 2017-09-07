package objetos_datos;

import java.util.Objects;

/** Este objeto encapsula 2 cosas: la tabla Persona y es la interface que se
 *  usara para imprimir los PDF
 * 
 * @author Vansi Adonay Olivares
 */
public class Persona {
    private String nombres;
    private String apellidos;

    public Persona(String nombres, String apellidos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.nombres);
        hash = 79 * hash + Objects.hashCode(this.apellidos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.nombres, other.nombres)) {
            return false;
        }
        if (!Objects.equals(this.apellidos, other.apellidos)) {
            return false;
        }
        return true;
    }
    
    
    
}
