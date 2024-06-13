/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plantilla;

import dto.contabilidad.DtoMayorDeCuenta;
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
import modelo.Catalogo;
import modelo.Cheque;
import modelo.Factura;

import modelo.SolicitudCheque;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

/**
 *
 * @author maximilianoa-te
 */
public class ExportarDato {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ExportarDato() {
//    public ExportarDato(String plantilla) {

        generarPlantilla();
    }

    public static void exportarMayorDeCuentaXLs(List<DtoMayorDeCuenta> listaOt, Date fi, Date ff) {

        Map beans = new HashMap();
        String destFileName;
        String templateFileName;

        File directorio = new File("C://documento exportado");
        directorio.mkdirs();

        templateFileName = "mayordecuenta.xls";
        destFileName = directorio.getAbsolutePath() + "//mayordecuenta_out.xls";

        beans.put("dto", listaOt);
        beans.put("cta", listaOt.get(0).getCuenta());
        beans.put("dcta", listaOt.get(0).getDescripcion());
        beans.put("fi", fi);
        beans.put("ff", ff);

        XLSTransformer transformer = new XLSTransformer();

        try {

            try {

                transformer.transformXLS(templateFileName, beans, destFileName);

//                leerExcel.crearMolinoCrudo(fila);
            } catch (ParsePropertyException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }
//
            try {

                Desktop.getDesktop().open(new File(destFileName));

            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException ex) {

            Logger.getLogger(ExportarDato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void exportarMayorDeCuentaPorChequeXLs(List<DtoMayorDeCuenta> listaOt, Date fi, Date ff) {
//    public static void exportarMayorDeCuentaXLs(List<DtoMayorDeCuenta> listaOt) {

        Map beans = new HashMap();
        String destFileName;
        String templateFileName;

        File directorio = new File("C://documento exportado");
        directorio.mkdirs();

//        if (cheque == true) {
//
        templateFileName = "mayordecuentabanco.xls";
        destFileName = directorio.getAbsolutePath() + "//mayordecuentabanco_out.xls";
//
//        } else {
//            templateFileName = "mayordecuenta.xls";
//            destFileName = directorio.getAbsolutePath() + "//mayordecuenta_out.xls";
//        }

//
//        String destFileName = "C://presupuesto//presupuesto_out.xls";
//         File ruta = new File("c://consulta produccion"+destFileName);
//
        beans.put("dto", listaOt);
        beans.put("cta", listaOt.get(0).getCuenta());
        beans.put("dcta", listaOt.get(0).getDescripcion());
        beans.put("fi", fi);
        beans.put("ff", ff);

        XLSTransformer transformer = new XLSTransformer();

        try {

            try {

                transformer.transformXLS(templateFileName, beans, destFileName);

//                leerExcel.crearMolinoCrudo(fila);
            } catch (ParsePropertyException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }
//
            try {

                Desktop.getDesktop().open(new File(destFileName));

            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException ex) {

            Logger.getLogger(ExportarDato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void exportarMayorDeCuentaBanco(List<DtoMayorDeCuenta> listaOt, Date fi, Date ff) {

        Map beans = new HashMap();
        String destFileName;
        String templateFileName;

        File directorio = new File("C://documento exportado");
        directorio.mkdirs();

        templateFileName = "mayordecuentabanco.xls";
        destFileName = directorio.getAbsolutePath() + "//mayordecuentabanco_out.xls";

        beans.put("dto", listaOt);
        beans.put("cta", listaOt.get(0).getCuenta());
        beans.put("dcta", listaOt.get(0).getDescripcion());
        beans.put("fi", fi);
        beans.put("ff", ff);

        XLSTransformer transformer = new XLSTransformer();

        try {

            try {

                transformer.transformXLS(templateFileName, beans, destFileName);

//                leerExcel.crearMolinoCrudo(fila);
            } catch (ParsePropertyException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }
//
            try {

                Desktop.getDesktop().open(new File(destFileName));

            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException ex) {

            Logger.getLogger(ExportarDato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void exportarChequeXLs(List<Cheque> lista) {

        Map beans = new HashMap();
        File directorio = new File("C://documento exportado");
        directorio.mkdirs();
        String destFileName = directorio.getAbsolutePath() + "//cheque_out.xls";

//
        String templateFileName = "cheque.xls";

        beans.put("che", lista);

        XLSTransformer transformer = new XLSTransformer();

        try {

            try {

                transformer.transformXLS(templateFileName, beans, destFileName);

//                leerExcel.crearMolinoCrudo(fila);
            } catch (ParsePropertyException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }
//
            try {

                Desktop.getDesktop().open(new File(destFileName));

            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException ex) {

            Logger.getLogger(ExportarDato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void exportarSolicitudXLs(List<SolicitudCheque> lista) {

        Map beans = new HashMap();
        File directorio = new File("C://documento exportado");
        directorio.mkdirs();
        String destFileName = directorio.getAbsolutePath() + "//solicitud_out.xls";

//
        String templateFileName = "solicitud.xls";

        beans.put("soli", lista);

        XLSTransformer transformer = new XLSTransformer();

        try {

            try {

                transformer.transformXLS(templateFileName, beans, destFileName);

//                leerExcel.crearMolinoCrudo(fila);
            } catch (ParsePropertyException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }
//
            try {

                Desktop.getDesktop().open(new File(destFileName));

            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException ex) {

            Logger.getLogger(ExportarDato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void exportarCatalogo(List<Catalogo> lista) {

        Map beans = new HashMap();
        File directorio = new File("C://documento exportado");
        directorio.mkdirs();
        String destFileName = directorio.getAbsolutePath() + "//presupuestado_out.xls";

        String templateFileName = "presupuestado.xls";

        beans.put("dp", lista);

        XLSTransformer transformer = new XLSTransformer();

        try {

            try {

                transformer.transformXLS(templateFileName, beans, destFileName);

//                leerExcel.crearMolinoCrudo(fila);
            } catch (ParsePropertyException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }
//
            try {

                Desktop.getDesktop().open(new File(destFileName));

            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException ex) {

            Logger.getLogger(ExportarDato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void exportarFacturaXLs(List<Factura> lista) {

        Map beans = new HashMap();

        File directorio = new File("C://documento exportado");
        directorio.mkdirs();
        String destFileName = directorio.getAbsolutePath() + "//factura_out.xls";

        String templateFileName = "factura.xls";//nombre de la plantilla

        beans.put("fact", lista);

        XLSTransformer transformer = new XLSTransformer();

        try {

            try {

                transformer.transformXLS(templateFileName, beans, destFileName);

            } catch (ParsePropertyException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }
//
            try {

                Desktop.getDesktop().open(new File(destFileName));

            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException ex) {

            Logger.getLogger(ExportarDato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
//Aqui se inscriben todas las plantilla que se van a generarar

    public void generarPlantilla() {

//        inputStreamAFile("presupuestado.xls");
//        inputStreamAFile("presupuesto.xls");
        inputStreamAFile("factura.xls");
//        inputStreamAFile("cheque.xls");
//        inputStreamAFile("solicitud.xls");
//        inputStreamAFile("mayordecuenta.xls");
//        inputStreamAFile("mayordecuentacheque.xls");
//        inputStreamAFile("mayordecuentabanco.xls");
    }

    private void inputStreamAFile(String archivo) {

        try {

            System.out.println("ruta src : "+getClass().getResourceAsStream(archivo));
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

    public static void exportarFacturaXLs(List<Factura> lista, Date fi, Date ff) {

        Map beans = new HashMap();

        File directorio = new File("C://sigexls");
        directorio.mkdirs();

        String destFileName = directorio.getAbsolutePath() + "//factura_out.xls";

        String templateFileName = "factura.xls";//nombre de la plantilla

        beans.put("fact", lista);
        beans.put("fi", fi);
        beans.put("ff", ff);

        XLSTransformer transformer = new XLSTransformer();

        try {

            try {

                transformer.transformXLS(templateFileName, beans, destFileName);

            } catch (ParsePropertyException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }
//
            try {

                Desktop.getDesktop().open(new File(destFileName));

            } catch (IOException ex) {
                Logger.getLogger(ExportarDato.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException ex) {

            Logger.getLogger(ExportarDato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new ExportarDato();

        Date fechaDesde = new Date("2023/07/01"), fechaHasta = new Date("2023/07/30");

        List<Factura> lista = ManejoFactura.getInstancia().getLista(fechaDesde, fechaHasta);

        System.out.println("lista " + lista.size());
        ExportarDato.exportarFacturaXLs(lista,fechaDesde,fechaHasta);
    }

}
