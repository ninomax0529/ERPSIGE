/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plantilla.cobros;

import dto.cobro.ReciboIngresoDTO;
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
import manejo.ReciboIngreso.ManejoReciboIngreso;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

/**
 *
 * @author maximilianoa-te
 */
public class ExportarCobro {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ExportarCobro() {
//    public ExportarDato(String plantilla) {

        generarPlantilla();
    }

    public static void exportarCobrosXLs(List<ReciboIngresoDTO> lista, Date fi, Date ff) {

        Map beans = new HashMap();

        File directorio = new File("C://sigexls");
        directorio.mkdirs();
        
        String destFileName = directorio.getAbsolutePath() + "//cobro_out.xls";

        String templateFileName = "cobro.xls";//nombre de la plantilla

        beans.put("recibo", lista);
        beans.put("fi", fi);
        beans.put("ff", ff);

        XLSTransformer transformer = new XLSTransformer();

        try {

            try {

                transformer.transformXLS(templateFileName, beans, destFileName);

            } catch (ParsePropertyException ex) {
                Logger.getLogger(ExportarCobro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExportarCobro.class.getName()).log(Level.SEVERE, null, ex);
            }
//
            try {

                Desktop.getDesktop().open(new File(destFileName));

            } catch (IOException ex) {
                Logger.getLogger(ExportarCobro.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException ex) {

            Logger.getLogger(ExportarCobro.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
//Aqui se inscriben todas las plantilla que se van a generarar

    public void generarPlantilla() {

        inputStreamAFile("cobro.xls");

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
        new ExportarCobro();

        Date fechaDesde = new Date("2023/07/01"), fechaHasta = new Date("2023/07/31");

        List<ReciboIngresoDTO> lista = ManejoReciboIngreso.getInstancia().getReciboPorFechaDTO(fechaDesde, fechaHasta);

        System.out.println("lista " + lista.size());
        ExportarCobro.exportarCobrosXLs(lista,fechaDesde,fechaHasta);
    }

}
