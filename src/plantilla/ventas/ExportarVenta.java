/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plantilla.ventas;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejo.factura.ManejoFactura;
import modelo.Factura;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

/**
 *
 * @author maximilianoa-te
 */
public class ExportarVenta {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ExportarVenta() {
//    public ExportarDato(String plantilla) {

        generarPlantilla();
    }

    public static void exportarFacturaXLs(List<Factura> lista) {

        Map beans = new HashMap();

        File directorio = new File("C://sigexls");
        directorio.mkdirs();
        String destFileName = directorio.getAbsolutePath() + "//factura_out.xls";

        String templateFileName = "factura.xls";//nombre de la plantilla

        beans.put("fact", lista);

        XLSTransformer transformer = new XLSTransformer();

        try {

            try {

                transformer.transformXLS(templateFileName, beans, destFileName);

            } catch (ParsePropertyException ex) {
                Logger.getLogger(ExportarVenta.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExportarVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
//
            try {

                Desktop.getDesktop().open(new File(destFileName));

            } catch (IOException ex) {
                Logger.getLogger(ExportarVenta.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException ex) {

            Logger.getLogger(ExportarVenta.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void exportarFacturaXLs(List<Factura> lista, Date fi, Date ff) {

        Map beans = new HashMap();

        String destFileName = "C://sigexls/factura_out1.xls";

        File directorio = new File(destFileName);
//        File directorio = new File("C://sigexls");
        directorio.mkdirs();

        // Si el archivo no existe es creado
        if (directorio.exists()) {
            System.out.println(" name file existe  " + directorio.getName());
            directorio.delete();
            System.out.println(" name file nuevo " + directorio.getName());
        }

//        String destFileName = directorio.getAbsolutePath() + "//factura_out.xls";
        String templateFileName = "factura.xls";//nombre de la plantilla

        beans.put("fact", lista);
        beans.put("fi", fi);
        beans.put("ff", ff);

        XLSTransformer transformer = new XLSTransformer();

        try {

            try {

                transformer.transformXLS(templateFileName, beans, destFileName);

            } catch (ParsePropertyException ex) {
                Logger.getLogger(ExportarVenta.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExportarVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
//
            try {

                Desktop.getDesktop().open(new File(destFileName));

            } catch (IOException ex) {
                Logger.getLogger(ExportarVenta.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException ex) {

            Logger.getLogger(ExportarVenta.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

//Aqui se inscriben todas las plantilla que se van a generarar
    public void generarPlantilla() {

        inputStreamAFile("factura.xls");

    }

    private void inputStreamAFile(String archivo) {

        try {

            InputStream entrada = getClass().getResourceAsStream(archivo);

            File f = new File(archivo);//
            OutputStream salida = new FileOutputStream(f);
            byte[] buf = new byte[1024];//
            int len;
            while ((len = entrada.read(buf)) > 0) {
                salida.write(buf, 0, len);
            }
            System.out.println("Creado con exito");
            salida.close();
            entrada.close();
//            System.out.println("Se realizo la conversion con exito");
        } catch (IOException e) {
            System.out.println("Error Creando " + e.getMessage());
//            System.out.println("Se produjo el error : " + e.toString());
        }

    }

    public static void main(String[] args) {
        new ExportarVenta();

        Date fechaDesde = new Date("2023/06/01"), fechaHasta = new Date("2023/06/29");

        List<Factura> lista = ManejoFactura.getInstancia().getLista(fechaDesde, fechaHasta);

        System.out.println("lista " + lista.size());
        ExportarVenta.exportarFacturaXLs(lista, fechaDesde, fechaHasta);
    }

}
