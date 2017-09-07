package exceptions;

import misc.Tools;

public class ConnectionException extends RuntimeException{

    public ConnectionException(String message) {
        super(message);
        Tools.displayMensajeErrorConexionBD();
    }
    
    public ConnectionException(String message, boolean mostrar){
        super(message);
        if(mostrar){
            Tools.displayMensajeErrorConexionBD();
        }
    }
}
