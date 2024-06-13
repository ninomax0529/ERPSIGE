/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author maximilianoa-te
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopiarArchivo {

    public static void copiarArchivo(String ficheroOriginal, String ficheroCopia) {
        try {
            // Se abre el fichero original para lectura
            FileInputStream fileInput = new FileInputStream(ficheroOriginal);
            BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);

            // Se abre el fichero donde se hará la copia
            FileOutputStream fileOutput = new FileOutputStream(ficheroCopia);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);

            // Bucle para leer de un fichero y escribir en el otro.
            byte[] array = new byte[10000];
            int leidos = bufferedInput.read(array);
            while (leidos > 0) {
                bufferedOutput.write(array, 0, leidos);
                leidos = bufferedInput.read(array);
            }

            // Cierre de los ficheros
            bufferedInput.close();
            bufferedOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getExtencionArchivo(String archivo) {

        String extencionArchivo = "";
        
        int i = archivo.lastIndexOf('.');

        if (i > 0) {

            extencionArchivo = archivo.substring(i + 1);

        }

        return extencionArchivo;
    }

    public static void main(String[] args) {
//        
        try {
            
        
//
//
            File archivo = new File("C:\\basedatos\\Sql actualizar datos en tablas.txt");
//            File archivoCopia = new File("C:\\basedatos\\Sql actualizar datos en tablas01");
            String NameArchivo = archivo.getAbsolutePath();
            String archivoOrigen = archivo.getName();
              String extencionArchivo =getExtencionArchivo(archivoOrigen);
//
//            System.out.println("Archivo original " + NameArchivo);
//            System.out.println("Archivo Copia " + archivoCopia);
//            System.out.println("Nombre Archivo Original  " + archivoOrigen + " posicion " + archivoOrigen.split(".")[1]);
//
////            archivoOrigen = archivoOrigen.split(".")[0];
//            String cadena = NameArchivo.replace("\\", "/");
//
            CopiarArchivo.copiarArchivo(NameArchivo, new File("//172.20.1.187/compartido/Adjuntos/" + archivoOrigen + "." + extencionArchivo).toString());
//
//            System.out.println("Extencion:" + extencionArchivo + "  cadena " + cadena);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        
// "//172.20.0.7/adjuntos/Almacen/" + nombreArchivo + "." + extencionArchivo);

    }

}
