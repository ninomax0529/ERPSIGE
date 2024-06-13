/////*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
package utiles;

import modelo.Usuario;

/**
 *
 * @author hector
 */
public class VariablesGlobales {

    public static Usuario USUARIO = null;
    public final static String SYSTEM_VERSION;

    static {

        String version = "  v0.9-ERR";
//
//        try {
//            
////            version = "" + StringUtils.readResource("/ftpUpdate/version.txt");
//            
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        SYSTEM_VERSION = version;

    }
}
