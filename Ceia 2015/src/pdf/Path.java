package pdf;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.*;

public class Path {
    
    /** Obtiene la direccion del escritorio del usuario actual. Solo funciona con
     *  Windows
     * 
     * @return la direccion del escritorio del usuario actual
     */
    public static String getDireccionEscritorio(){
        char[] pszPath = new char[WinDef.MAX_PATH];
        Shell32.INSTANCE.SHGetFolderPath(null,
        ShlObj.CSIDL_DESKTOPDIRECTORY, null, ShlObj.SHGFP_TYPE_CURRENT, pszPath);
        return Native.toString(pszPath);
    }
}
