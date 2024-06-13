/////*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
package util;

import modelo.Usuario;


public class VariablesGlobales {

    public static Usuario USUARIO = null;
    public final static String SYSTEM_VERSION;
    public final static String URL_DATA_BASE;
    public  static String NOMBRE_PROYECTO;

    static {

        String version = "1.6.8";
//
//        try {
//            
////            version = "" + StringUtils.readResource("/ftpUpdate/version.txt");
//            
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        SYSTEM_VERSION = version;
        URL_DATA_BASE="";

    }
}
