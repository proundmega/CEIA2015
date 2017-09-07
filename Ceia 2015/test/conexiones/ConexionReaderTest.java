package conexiones;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConexionReaderTest {
    
    @Test
    public void testGetDataConexionPorTexto() {
        ConexionReader conexionReader = new ConexionReader();
        DataConexion data = conexionReader.getDataConexionPorTexto();
        assertEquals("jdbc:mysql://localhost:3306/ceia2015", data.getUrl());
        assertEquals("root", data.getUsuario());
        assertEquals("", data.getPassword());
    }
    
}
