/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import com.mysql.jdbc.PreparedStatement;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Optional;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manejo.HibernateUtil;
import manejo.factura.ManejoRelacionNcf;
import modelo.Adjunto;
import modelo.RelacionNcf;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Session;
import static util.ClaseUtil.getMes;

/**
 *
 * @author maximilianoa-te
 */
public class ClaseUtil {

    private Session session = HibernateUtil.getSession();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ClaseUtil() {

    }

    public static void abrirArchivo(File file, Adjunto adjunto) throws Exception {

        if (Desktop.isDesktopSupported()) {

            new Thread(() -> {

                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
//                    ClaseUtil.mensaje("Este archivo " + adjunto.getNombreDocumento() + " no existen en esta rura " +adjunto.getUrl());
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static String FormatearNumero(double numero) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(numero);
    }

    public static Date FechaMesDespues(Date jXDatePicker1, int suma) {

        SimpleDateFormat fmt;
        Calendar cal = Calendar.getInstance();
        Date posterior;

        fmt = new SimpleDateFormat("M/dd/yyyy");
        cal.setTimeInMillis(jXDatePicker1.getTime());
        int anio = cal.get(cal.YEAR);
        int mes = cal.get(cal.MONTH);
        int dia = cal.get(cal.DAY_OF_MONTH);
        mes += suma;
        //System.out.println("DIA: "+dia+"MES: "+mes+"AÑO: "+anio);
        cal.set(Calendar.YEAR, anio);
        cal.set(Calendar.MONTH, mes);
        cal.set(Calendar.DAY_OF_MONTH, dia);
        fmt.setCalendar(cal);
        posterior = new Date(fmt.getCalendar().getTimeInMillis());
        //System.out.println("FECHA RETORNO: "+posterior);
        return posterior;
    }

    public static Date FechaMesDespues(Date fecha, int suma, String formato) {

        SimpleDateFormat fmt;
        Calendar cal = Calendar.getInstance();
        Date posterior;

        fmt = new SimpleDateFormat(formato);
        cal.setTimeInMillis(fecha.getTime());
        int anio = cal.get(cal.YEAR);
        int mes = cal.get(cal.MONTH);
        int dia = cal.get(cal.DAY_OF_MONTH);
        mes += suma;
        //System.out.println("DIA: "+dia+"MES: "+mes+"AÑO: "+anio);
        cal.set(Calendar.YEAR, anio);
        cal.set(Calendar.MONTH, mes);
        cal.set(Calendar.DAY_OF_MONTH, dia);
        fmt.setCalendar(cal);
        posterior = new Date(fmt.getCalendar().getTimeInMillis());
        //System.out.println("FECHA RETORNO: "+posterior);
        return posterior;
    }

    public static Date getFechaFacturacion() {

        SimpleDateFormat fmt;
        Calendar cal = Calendar.getInstance();
        Date fechaFacturacion;

        fmt = new SimpleDateFormat("M/dd/yyyy");
        cal.setTimeInMillis(new Date().getTime());
        int anio = cal.get(cal.YEAR);
        int mes = cal.get(cal.MONTH);
        int dia = VariablesGlobales.USUARIO.getUnidadDeNegocio().getDiaDeFacturacion();

        cal.set(Calendar.YEAR, anio);
        cal.set(Calendar.MONTH, mes);
        cal.set(Calendar.DAY_OF_MONTH, dia);
        fmt.setCalendar(cal);
        fechaFacturacion = new Date(fmt.getCalendar().getTimeInMillis());
        //System.out.println("FECHA RETORNO: "+posterior);
        return fechaFacturacion;
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date Fechadiadespues(Date jXDatePicker1, int suma) {

        SimpleDateFormat fmt;
        Calendar cal = Calendar.getInstance();
        Date posterior;

        fmt = new SimpleDateFormat("M/dd/yyyy");
        cal.setTimeInMillis(jXDatePicker1.getTime());
        int anio = cal.get(cal.YEAR);
        int mes = cal.get(cal.MONTH);
        int dia = cal.get(cal.DAY_OF_MONTH);
        dia += suma;
        System.out.println("DIA: " + dia + "MES: " + mes + "AÑO: " + anio);
        cal.set(Calendar.YEAR, anio);
        cal.set(Calendar.MONTH, mes);
        cal.set(Calendar.DAY_OF_MONTH, dia);

        fmt.setCalendar(cal);
        posterior = new Date(fmt.getCalendar().getTimeInMillis());
        //System.out.println("FECHA RETORNO: "+posterior);
        return posterior;
    }

    public static final double roundDouble(double d, int places) {

        double roundNumber = Math.round(d * Math.pow(10, (double) places)) / Math.pow(10,
                (double) places);

        Formatter fmt = new Formatter();

        fmt.format("%.2f", roundNumber);
        roundNumber = Double.valueOf(fmt.toString());
        return roundNumber;
    }

    public static final double roundDoubleSies(double d, int places) {

        double roundNumber = Math.round(d * Math.pow(10, (double) places)) / Math.pow(10,
                (double) places);

        Formatter fmt = new Formatter();

        fmt.format("%.4f", roundNumber);
        roundNumber = Double.valueOf(fmt.toString());
        return roundNumber;
    }

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

    public boolean guardarImagen(String ruta, String nombre) {

        String insert = "insert into Imagenes(imagen,nombre) values(?,?)";
        FileInputStream fis = null;
        PreparedStatement ps = null;

//        try {
//            
//            session.beginTransaction();
//            File file = new File(ruta);
//            fis = new FileInputStream(file);
//            session.createQuery(insert);
////            ps = conexion.prepareStatement(insert);
//             
//            ps.setBinaryStream(1, fis, (int) file.length());
//            ps.setString(2, nombre);
//            ps.executeUpdate();
//            conexion.commit();
//            return true;
//        } catch (Exception ex) {
//            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                ps.close();
//                fis.close();
//            } catch (Exception ex) {
//                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return false;
    }

    public static LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static boolean thereIsNumber(String cadena) {
        boolean isnum = false;
        int cantidadNum = 0;
        for (int i = 0; i < cadena.length(); i++) {
            char car = cadena.charAt(i);
            if (Character.isDigit(car)) {
                cantidadNum += 1;
                isnum = true;
            }
        }

        if (cantidadNum == cadena.length()) {
            isnum = true;

        } else {

            isnum = false;
        }

        return isnum;
    }

    public static boolean IsNumber(String cadena) {
        boolean isnum = false;
        int cantidadNum = 0;
        for (int i = 0; i < cadena.length(); i++) {
            char car = cadena.charAt(i);
            if (Character.isDigit(car)) {
                cantidadNum += 1;
                isnum = true;
            }
        }

        if (cantidadNum == cadena.length()) {
            isnum = true;

        } else {

            isnum = false;
        }

        return isnum;
    }

    public static boolean esAlfanumerico(String cadena) {
        boolean isnum = false;
        int cantidadNum = 0;
        for (int i = 0; i < cadena.length(); i++) {
            char car = cadena.charAt(i);
            if (Character.isAlphabetic(car)) {
                cantidadNum += 1;
                isnum = true;
            }
        }

        if (cantidadNum == cadena.length()) {
            isnum = true;

        } else {

            isnum = false;
        }

        return isnum;
    }

    public static long diferenciaDiasEntreDosFecha(Date fechaAnterior, Date hoy) {

        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 

//        java.util.Date hoy = new Date(); //Fecha de hoy 
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fechaAnterior);

        fechaAnterior = new java.sql.Date(calendar.getTimeInMillis());

        long diferencia = (hoy.getTime() - fechaAnterior.getTime()) / MILLSECS_PER_DAY;

//        System.out.println(diferencia);
        return diferencia;

    }

    /**
     * @return the sdf
     */
    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    /**
     * @param sdf the sdf to set
     */
    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public static void mensaje(String mensaje) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dialogo de Inforamacion");
        alert.setHeaderText("Sistema SIGE");
        alert.setContentText(mensaje);
        alert.showAndWait();

    }

    public static void mensaje(String mensaje, String msg) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dialogo de Inforamacion");
        alert.setHeaderText(msg);
        alert.setContentText(mensaje);
        alert.showAndWait();

    }

    public static void crearStageModal(Parent root) {

        Scene scene = new Scene(root);
        Stage stage = new Stage();
//        stage.setMaximized(true);
        stage.setResizable(true);
        stage.setScene(scene);

//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }

    public static void crearStageModal(Parent root, boolean resizable) {

        Scene scene = new Scene(root);
        Stage stage = new Stage();
//        stage.setMaximized(true);
        stage.setResizable(resizable);
        stage.setScene(scene);

//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }

    public static Stage getStageModal(Parent root) {

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        return stage;
    }

    public static Stage getStageModalcONTRATO(Parent root) {

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setMaximized(false);
        stage.setResizable(true);
        stage.setScene(scene);
//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        return stage;
    }

    public static RelacionNcf generarNCF(RelacionNcf td) {

        RelacionNcf dncf = td;

        String ncfNuevo = "A01001001";

        if (dncf.getSecuencia() < 9) {

            System.out.println("PRIMERO!!!!!!!!!!!!");

            if (td.getTipoNcf().getCodigo() == 1) {

                ncfNuevo = ncfNuevo + "01" + "0000000" + String.valueOf(dncf.getSecuencia() + 1);
            }

            if (td.getTipoNcf().getCodigo() == 2) {

                ncfNuevo = ncfNuevo + "02" + "0000000" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 14) {
                ncfNuevo = ncfNuevo + "14" + "0000000" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 15) {
                ncfNuevo = ncfNuevo + "15" + "0000000" + String.valueOf(dncf.getSecuencia() + 1);
            }
        }

        if (dncf.getSecuencia() < 99 && dncf.getSecuencia() >= 9) {
            System.out.println("SEGUNDO!!!!!!!!!!!!");
            if (td.getTipoNcf().getCodigo() == 1) {

                ncfNuevo = ncfNuevo + "01" + "000000" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 2) {
                ncfNuevo = ncfNuevo + "02" + "000000" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 14) {
                ncfNuevo = ncfNuevo + "14" + "000000" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 15) {
                ncfNuevo = ncfNuevo + "15" + "000000" + String.valueOf(dncf.getSecuencia() + 1);
            }
        }

        if (dncf.getSecuencia() < 999 && dncf.getSecuencia() >= 99) {
            System.out.println("TERCERO!!!!!!!!!!!!");
            if (td.getTipoNcf().getCodigo() == 1) {
                ncfNuevo = ncfNuevo + "01" + "00000" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 2) {
                ncfNuevo = ncfNuevo + "02" + "00000" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 14) {
                ncfNuevo = ncfNuevo + "14" + "00000" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 15) {
                ncfNuevo = ncfNuevo + "15" + "00000" + String.valueOf(dncf.getSecuencia() + 1);
            }
        }
        if (dncf.getSecuencia() < 9999 && dncf.getSecuencia() >= 999) {
            System.out.println("CUARTO!!!!!!!!!!!!");
            if (td.getTipoNcf().getCodigo() == 1) {
                ncfNuevo = ncfNuevo + "01" + "0000" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 2) {
                ncfNuevo = ncfNuevo + "02" + "0000" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 14) {
                ncfNuevo = ncfNuevo + "14" + "0000" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 15) {
                ncfNuevo = ncfNuevo + "15" + "0000" + String.valueOf(dncf.getSecuencia() + 1);
            }
        }

        if (dncf.getSecuencia() < 99999 && dncf.getSecuencia() >= 9999) {
            System.out.println("QUINTO!!!!!!!!!!!!");
            if (td.getTipoNcf().getCodigo() == 1) {
                ncfNuevo = ncfNuevo + "01" + "000" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 2) {
                ncfNuevo = ncfNuevo + "02" + "000" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 14) {
                ncfNuevo = ncfNuevo + "14" + "000" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 15) {
                ncfNuevo = ncfNuevo + "15" + "000" + String.valueOf(dncf.getSecuencia() + 1);
            }
        }

        if (dncf.getSecuencia() < 999999 && dncf.getSecuencia() >= 99999) {
            System.out.println("SEXTO!!!!!!!!!!!!");
            if (td.getTipoNcf().getCodigo() == 1) {
                ncfNuevo = ncfNuevo + "01" + "00" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 2) {
                ncfNuevo = ncfNuevo + "02" + "00" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 14) {
                ncfNuevo = ncfNuevo + "14" + "00" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 15) {
                ncfNuevo = ncfNuevo + "15" + "00" + String.valueOf(dncf.getSecuencia() + 1);
            }
        }

        if (dncf.getSecuencia() < 9999999 && dncf.getSecuencia() >= 999999) {
            System.out.println("SEPTIMO!!!!!!!!!!!!");
            if (td.getTipoNcf().getCodigo() == 1) {
                ncfNuevo = ncfNuevo + "01" + "0" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 2) {
                ncfNuevo = ncfNuevo + "02" + "0" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 14) {
                ncfNuevo = ncfNuevo + "14" + "0" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 15) {
                ncfNuevo = ncfNuevo + "15" + "0" + String.valueOf(dncf.getSecuencia() + 1);
            }
        }

        if (dncf.getSecuencia() < 99999999 && dncf.getSecuencia() >= 9999999) {
            System.out.println("OCTAVO!!!!!!!!!!!!");
            if (td.getTipoNcf().getCodigo() == 1) {
                ncfNuevo = ncfNuevo + "01" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 2) {
                ncfNuevo = ncfNuevo + "02" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 14) {
                ncfNuevo = ncfNuevo + "14" + String.valueOf(dncf.getSecuencia() + 1);
            }
            if (td.getTipoNcf().getCodigo() == 15) {
                ncfNuevo = ncfNuevo + "15" + String.valueOf(dncf.getSecuencia() + 1);
            }
        }
        dncf.setActual("");
        dncf.setActual(ncfNuevo);
        dncf.setSecuencia(dncf.getSecuencia() + 1);
        return dncf;
    }

    public static RelacionNcf generarNCFPorTipo(RelacionNcf td, int tipoNcf, int ung) {

        RelacionNcf dncf = td;
        Integer ncfaumentarSufijo;
        String prefijoNcf;

        String ncfNuevo = ManejoRelacionNcf.getInstancia().getNCFUnidadDeNegocio(tipoNcf, ung).getRangoInicial();
        System.out.println("ncfNuevo " + ncfNuevo);
        prefijoNcf = ncfNuevo.substring(0, 2);
        System.out.println("prefijoNcf " + prefijoNcf);
        ncfaumentarSufijo = Integer.parseInt(ncfNuevo.substring(2, ncfNuevo.length()));
        System.out.println("dncf.getSecuencia() " + dncf.getSecuencia());

        System.out.println("ncfaumentar " + ncfaumentarSufijo);
        ncfaumentarSufijo = ncfaumentarSufijo + (dncf.getSecuencia() + 1);
        System.out.println("ncfaumentar " + ncfaumentarSufijo);

        ncfNuevo = prefijoNcf + ncfaumentarSufijo;
        System.out.println("ncfNuevo " + ncfNuevo);

        dncf.setActual("");
        dncf.setActual(ncfNuevo);
        dncf.setSecuencia(dncf.getSecuencia() + 1);
        return dncf;
    }

    public static RelacionNcf generarNCFPorTipo(RelacionNcf td, int tipoNcf, int ung, boolean empresa) {

        System.out.println("relacion " + td + " tipo : " + tipoNcf + " unidad " + ung + "  empresa : " + empresa);

        RelacionNcf dncf = td;
        Integer ncfaumentarSufijo;
        String prefijoNcf;

        String ncfNuevo = ManejoRelacionNcf.getInstancia().getNCFUnidadDeNegocio(tipoNcf, ung, empresa).getRangoInicial();
        System.out.println("ncfNuevo " + ncfNuevo);
        prefijoNcf = ncfNuevo.trim().substring(0, 2);
        System.out.println("prefijoNcf " + prefijoNcf + " nuevo subString " + (ncfNuevo.trim().substring(2, ncfNuevo.trim().length())));
        ncfaumentarSufijo = Integer.parseInt(ncfNuevo.trim().substring(2, ncfNuevo.trim().length()));
        System.out.println("dncf.getSecuencia() " + dncf.getSecuencia());

        System.out.println("ncfaumentar " + ncfaumentarSufijo);
        ncfaumentarSufijo = ncfaumentarSufijo + (dncf.getSecuencia() + 1);
        System.out.println("ncfaumentar " + ncfaumentarSufijo);

        ncfNuevo = prefijoNcf + ncfaumentarSufijo;
        System.out.println("ncfNuevo " + ncfNuevo);

        dncf.setActual("");
        dncf.setActual(ncfNuevo);
        dncf.setSecuenciaActual(ncfaumentarSufijo.toString());
        dncf.setSecuencia(dncf.getSecuencia() + 1);
        return dncf;
    }

    public static RelacionNcf generarNCFPorTipo(RelacionNcf td, int tipoNcf) {

        RelacionNcf dncf = td;
        Integer ncfaumentar;
        String prefijoNcf;

        String ncfNuevo = ManejoRelacionNcf.getInstancia().getNCF(tipoNcf).getRangoInicial();
        System.out.println("ncfNuevo " + ncfNuevo);
        prefijoNcf = ncfNuevo.substring(0, 2);
        System.out.println("prefijoNcf " + prefijoNcf);
        ncfaumentar = Integer.parseInt(ncfNuevo.substring(2, ncfNuevo.length()));
        System.out.println("dncf.getSecuencia() " + dncf.getSecuencia());

        System.out.println("ncfaumentar " + ncfaumentar);
        ncfaumentar = ncfaumentar + (dncf.getSecuencia() + 1);
        System.out.println("ncfaumentar " + ncfaumentar);

        ncfNuevo = prefijoNcf + ncfaumentar;
        System.out.println("ncfNuevo " + ncfNuevo);

        dncf.setActual("");
        dncf.setActual(ncfNuevo);
        dncf.setSecuencia(dncf.getSecuencia() + 1);
        return dncf;
    }

    public static int getDiaDeLaSemana(Date fecha) {

        Calendar cal = Calendar.getInstance();

        int diaDeSemana = 0;

        cal.setTimeInMillis(fecha.getTime());
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        System.out.println("DIA: " + dia + "MES: " + mes + "AÑO: " + anio + "" + cal.get(Calendar.DAY_OF_WEEK));
        cal.set(Calendar.YEAR, anio);
        cal.set(Calendar.MONTH, mes);
        cal.set(Calendar.DAY_OF_MONTH, dia);
        diaDeSemana = cal.get(Calendar.DAY_OF_WEEK);

        cal.set(Calendar.DAY_OF_WEEK, 1);
        System.out.println("DIA: " + cal.getTime());
        return diaDeSemana;
    }

    public static String getProximoNcf(RelacionNcf td, int tipoNcf) {

        RelacionNcf dncf = td;
        Integer ncfaumentar;
        String prefijoNcf;

        String ncfNuevo = ManejoRelacionNcf.getInstancia().getNCF(tipoNcf).getRangoInicial();
        System.out.println("ncfNuevo " + ncfNuevo);
        prefijoNcf = ncfNuevo.substring(0, 2);
        System.out.println("prefijoNcf " + prefijoNcf);
        ncfaumentar = Integer.parseInt(ncfNuevo.substring(2, ncfNuevo.length()));
        System.out.println("dncf.getSecuencia() " + dncf.getSecuencia());

        System.out.println("ncfaumentar " + ncfaumentar);
        ncfaumentar = ncfaumentar + (dncf.getSecuencia() + 1);
        System.out.println("ncfaumentar " + ncfaumentar);

        ncfNuevo = prefijoNcf + ncfaumentar;
        System.out.println("ncfNuevo " + ncfNuevo);

        return (prefijoNcf + ncfaumentar);
    }

    public static Optional<ButtonType> confirmarMensaje(String mensaje) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Dialogo de Confirmacioon ");
        alert.setHeaderText("Sistema SIGE");
        alert.setContentText(mensaje);
//        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();

        return result;

    }

    public static String getExtensionArchivo(String fileName) {

        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }

        System.out.println("Extension " + extension);

        return extension;
    }

    public static Stage getStagePanel(Parent root) {

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        return stage;
    }

    public static int getMes(Date Fecha) {

        int mes = ((Fecha.getMonth() + 1) < 10 ? 0 + (Fecha.getMonth() + 1) : (Fecha.getMonth() + 1));

        return mes;
    }

    public static int getAno(Date Fecha) {

        int ano = (Fecha.getYear() + 1900);

        return ano;
    }

    public static String getMesAno(Date Fecha) {

        String mes_ano = ((Fecha.getMonth() + 1) < 10 ? "0" + (Fecha.getMonth() + 1) : (Fecha.getMonth() + 1)) + "-" + (Fecha.getYear() + 1900);
        return mes_ano;
    }

    public static Date getFechaPrimerDiasDelMes(Date fecha) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(fecha);

        cal.set(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    public static String getNombreDelMes(Date fecha) {

        String nombre = "";

        switch (getMes(fecha)) {

            case 1:
                nombre = "enero";
                break;

            case 2:
                nombre = "febrero";
                break;
            case 3:
                nombre = "marzo";
                break;
            case 4:
                nombre = "abril";
                break;
            case 5:
                nombre = "mayo";
                break;
            case 6:
                nombre = "junio";
                break;
            case 7:
                nombre = "julio";
                break;

            case 8:
                nombre = "agosto";
                break;
            case 9:
                nombre = "septiembre";
                break;
            case 10:
                nombre = "octubre";
                break;
            case 11:
                nombre = "noviembre";
                break;
            case 12:
                nombre = "diciembre";
                break;
        }

        return nombre;
    }

    public static String getNombreDia(Date date) {
        String nombreDia = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = 0;

        try {

            month = calendar.get(Calendar.DAY_OF_WEEK);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        switch (month) {

            case 1: {

                nombreDia = "Domingo";
                break;
            }

            case 2: {
                nombreDia = "Lune";
                break;
            }
            case 3: {
                nombreDia = "Marte";
                break;
            }
            case 4: {
                nombreDia = "Miercole";
                break;
            }
            case 5: {
                nombreDia = "Jueve";
                break;
            }
            case 6: {
                nombreDia = "Viernes";
                break;
            }

            case 7: {
                nombreDia = "Sabado";
                break;
            }

        }
        return nombreDia;
    }

    public static void exportarDAtos(TableView<?> table) throws IOException {

        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("sample");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumns().get(j).getText());
        }

        for (int i = 0; i < table.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(table.getColumns().get(j).getCellData(i).toString());
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        workbook.write(fileOut);
        fileOut.close();

        Desktop.getDesktop().open(new File("workbook.xls"));

    }

    public static void main(String[] args) {

        System.out.println("B0100000000".length());
        System.out.println("B0100000000".substring(2, "B0100000000".length()));

        Integer nc = Integer.parseInt("B01000000".substring(2, "B01000000".length()));
        System.out.println("nc " + getDiaDeLaSemana(new Date("2023/09/24")));
           System.out.println("nc " + getNombreDia(new Date("2023/09/24")));
//        ClaseUtil.copiarArchivo("C:\\csv\\habichuelas-negras-7lbs.jpg", "C:\\logoCCN\\nino0529.jpg");
//        String extension = "B0100001131";
//        String fileName = "nino.jpg";
//
//        RelacionNcf rl = ManejoRelacionNcf.getInstancia().getNCFUnidadDeNegocio(1,3);
//        ClaseUtil.generarNCFPorTipo(1,1,3);

//        int i = fileName.lastIndexOf('.');
//        if (i > 0) {
//            extension = fileName.substring(i + 1);
//        }
//        System.out.println("Extension " + extension.substring(2, 3));
//        String ext1 = FilenameUtils.getExtension("/path/to/file/foo.txt"); // returns "txt"
//        System.out.println("La fecha es " + ClaseUtil.diferenciaDiasEntreDosFecha(new Date("2022/06/01"), new Date("2022/06/20")));
//           System.out.println("Dias despues " + ClaseUtil.FechaMesDespues(new Date("2023/03/23"),3,"yyyy/MM/dd"));
    }

}
