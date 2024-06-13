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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import com.mysql.jdbc.PreparedStatement;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;
import manejo.unidadDeNegocio.ManejoUnidadDeNegocio;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import static utiles.ClaseUtil.getMes;

/**
 *
 * @author maximilianoa-te
 */
public class ClaseUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ClaseUtil() {

    }

    public static String getMesAno(Date Fecha) {
        String mes_ano = ((Fecha.getMonth() + 1) < 10 ? "0" + (Fecha.getMonth() + 1) : (Fecha.getMonth() + 1)) + "-" + (Fecha.getYear() + 1900);
        return mes_ano;
    }

    public static String getAnioMes(Date Fecha) {
        String anioMes = (Fecha.getYear() + 1900) + "" + ((Fecha.getMonth() + 1) < 10 ? "0" + (Fecha.getMonth() + 1) : (Fecha.getMonth() + 1));
        return anioMes;
    }

    public static int getMes(Date Fecha) {

        int mes = ((Fecha.getMonth() + 1) < 10 ? 0 + (Fecha.getMonth() + 1) : (Fecha.getMonth() + 1));

        return mes;
    }

    public static int getDia(Date Fecha) {

        int dia = Fecha.getDate();

        return dia;
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static int getAno(Date Fecha) {

        int ano = (Fecha.getYear() + 1900);

        return ano;
    }

    public static int diasDelMes(int mes, int año) {

        switch (mes) {

            case 0:  // Enero
            case 2:  // Marzo
            case 4:  // Mayo
            case 6:  // Julio
            case 7:  // Agosto
            case 9:  // Octubre
            case 11: // Diciembre
                return 31;
            case 3:  // Abril
            case 5:  // Junio
            case 8:  // Septiembre
            case 10: // Noviembre

                return 30;

            case 1:  // Febrero

                if (((año % 100 == 0) && (año % 400 == 0))
                        || ((año % 100 != 0) && (año % 4 == 0))) {
                    return 29;  // Año Bisiesto

                } else {

                    return 28;
                }

            default:

                throw new java.lang.IllegalArgumentException(
                        "El mes debe estar entre 0 y 11");
        }
    }

    public static Date getFechaUltimoDiaDelMes(Date fecha) {

        int dias = ClaseUtil.diasDelMes(getMes(fecha) - 1, getAno(fecha));

        Calendar cal = Calendar.getInstance();

        cal.setTime(fecha);

        cal.set(Calendar.DAY_OF_MONTH, dias);

        return cal.getTime();
    }

    public static Date FechaMesDespues(Date jXDatePicker1, int suma) {

        SimpleDateFormat fmt;
        Calendar cal = Calendar.getInstance();
        Date posterior;

        fmt = new SimpleDateFormat("yyyy-MM-dd");
        cal.setTimeInMillis(jXDatePicker1.getTime());
        int anio = cal.get(cal.YEAR);
        int mes = cal.get(cal.MONTH);
        int dia = cal.get(cal.DAY_OF_MONTH);
        System.out.println("antes DIA: " + dia + " MES: " + mes + " AÑO: " + anio);
        mes += suma;
        System.out.println("DIA: " + dia + " MES: " + mes + " AÑO: " + anio);
        cal.set(Calendar.YEAR, anio);
        cal.set(Calendar.MONTH, mes);
        cal.set(Calendar.DAY_OF_MONTH, dia);
        fmt.setCalendar(cal);
        posterior = new Date(fmt.getCalendar().getTimeInMillis());
        //System.out.println("FECHA RETORNO: "+posterior);
        return posterior;
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

    public static Date getConstruirFecha(Date fechaCorte, int diaRenovacion, int mesRenovacion) {

        SimpleDateFormat fmt;
        Calendar cal = Calendar.getInstance();
        Date posterior;

        fmt = new SimpleDateFormat("M/dd/yyyy");
        cal.setTimeInMillis(fechaCorte.getTime());
        int anio = cal.get(cal.YEAR);
        cal.set(Calendar.YEAR, anio);
        cal.set(Calendar.MONTH, mesRenovacion - 1);
        cal.set(Calendar.DAY_OF_MONTH, diaRenovacion);

        fmt.setCalendar(cal);
        posterior = new Date(fmt.getCalendar().getTimeInMillis());

        return posterior;
    }

    public static Date getFechaUltimoDiasMesAnterior(Date fecha) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(fecha);

        cal.set(Calendar.DAY_OF_MONTH, 1);

        cal.add(Calendar.DATE, -1);

        return cal.getTime();

    }

    public static Date getFechaPrimerDiasDelMes(Date fecha) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(fecha);

        cal.set(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    public static Date getFechaPrimerDiasDelAno(Date fecha) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(fecha);

//        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(ClaseUtil.getAno(fecha), 0, 1);

        return cal.getTime();
    }

    public boolean guardarImagen(String ruta, String nombre) {

        String insert = "insert into Imagenes(imagen,nombre) values(?,?)";
        FileInputStream fis = null;
//        PreparedStatement ps = null;

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
//

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

    public static DatePicker convertidordeFecha(DatePicker datePicker) {

        String pattern = "yyyy-MM-dd";

        datePicker.setPromptText(pattern.toLowerCase());

        datePicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        return datePicker;
    }

    public static Date getFechaDesdeLocalDate(LocalDate ldate) {

//        Instant instant = Instant.from(ldate.atStartOfDay(ZoneId.of("GMT")));
        Instant instant = Instant.from(ldate.atStartOfDay(ZoneId.of("GMT")));
        Date date = Date.from(instant);
        return date;
    }

    public static long diferenciaHora(Date fe, Date fs) {

        long diferencia = 0;

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("HH :mm :ss");

            fe = sdf.parse(sdf.format(fe));

            fs = sdf.parse(sdf.format(fs));

            long lantes = fe.getTime();

            long lahora = fs.getTime();

            diferencia = (lahora - lantes);

        } catch (ParseException ex) {

            Logger.getLogger(ClaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return diferencia;

    }

    public static int diferenciaHoraLocalTime(String horaInicio, String horaFinal) {

        int minutos = 0;
        try {
//
            System.out.print("Ingrese la fecha de ingreso: " + horaInicio);
            LocalTime horaInicio1 = LocalTime.parse(horaInicio);
            System.out.print("Ingrese la fecha de salida: " + horaFinal);
            LocalTime horaFinal1 = LocalTime.parse(horaFinal);

            minutos = (int) ChronoUnit.MINUTES.between(horaInicio1, horaFinal1);
            System.out.println("Fecha de ingreso o salida inválida " + minutos);

        } catch (DateTimeParseException e) {
            System.out.println("Fecha de ingreso o salida inválida");
        }

        return minutos;
    }

    public static Date fechaAyer(Date fecha) {

        int diferenciaEnDias = 1;
//            Date fechaActual = Calendar.getInstance().getTime();
        long tiempoActual = fecha.getTime();
        long unDia = diferenciaEnDias * 24 * 60 * 60 * 1000;
        Date fechaAyer = new Date(tiempoActual - unDia);

        return fechaAyer;
    }

    public static double FormatearDoubleSinRedondeo(Double i) {

        String num = i.toString();

        if (num.length() > 4) {

            return Double.parseDouble(num.substring(1, 5));

        } else {

            return Double.parseDouble(num);
        }

    }

    public static final double roundDouble(double d, int places) {

        double roundNumber = Math.round(d * Math.pow(10, (double) places)) / Math.pow(10,
                (double) places);

        Formatter fmt = new Formatter();

        fmt.format("%.2f", roundNumber);
        roundNumber = Double.valueOf(fmt.toString());
        return roundNumber;
    }

    public static Time toSqlTime(LocalTime localTime) {

        return Time.valueOf(localTime);
    }

    public static LocalTime toLocalTime(java.sql.Time time) {

        return time.toLocalTime();
    }

    public static java.sql.Time getTime(java.util.Date startDate, int period, int amount) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(startDate);
        gc.add(period, amount);
        return new java.sql.Time(gc.getTime().getTime());
    }

    public static Date fechaManana(Date fecha) {

        int diferenciaEnDias = 1;

        long tiempoActual = fecha.getTime();
        long unDia = diferenciaEnDias * 24 * 60 * 60 * 1000;
        fecha = new Date(tiempoActual + unDia);

        System.out.println("la fecha es " + fecha);

        return fecha;
    }

    public static LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static void mensaje(String mensaje) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dialogo de Inforamacion");
        alert.setHeaderText("SIGE ERP");
        alert.setContentText(mensaje);
        alert.showAndWait();

    }

    public static Optional<ButtonType> confirmarMensaje(String mensaje) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirmation Dialog");
        alert.setContentText(mensaje);
//        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();

        return result;

    }
//    
//     public static void exportarDAtos(TableView<?> table) throws IOException {
//
//        Workbook workbook = new HSSFWorkbook();
//        Sheet spreadsheet = workbook.createSheet("sample");
//
//        Row row = spreadsheet.createRow(0);
//
//        for (int j = 0; j < table.getColumns().size(); j++) {
//            row.createCell(j).setCellValue(table.getColumns().get(j).getText());
//        }
//
//        for (int i = 0; i < table.getItems().size(); i++) {
//            row = spreadsheet.createRow(i + 1);
//            for (int j = 0; j < table.getColumns().size(); j++) {
//                if (table.getColumns().get(j).getCellData(i) != null) {
//                    row.createCell(j).setCellValue(table.getColumns().get(j).getCellData(i).toString());
//                } else {
//                    row.createCell(j).setCellValue("");
//                }
//            }
//        }
//
//        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
//        workbook.write(fileOut);
//        fileOut.close();
//
//        Desktop.getDesktop().open(new File("workbook.xls"));
//
//    }

    public static String ponerCero(int numero) {
        String num = "";

        if (numero <= 9) {
            num = "0" + numero;

        } else {
            num = Integer.toString(numero);
        }

        return num;
    }

    public static Double getPreciodeVenta(Double precioCompra, Double porcientoUtilidad) {

        Double precioVenta = 0.00;

        precioVenta = utiles.FormatNum.FormatearDouble(precioCompra / (1 - (porcientoUtilidad / 100)), 2);

        return precioVenta;

    }

    public static Double getMargenUtilidad(Double precioCompra, Double precioVenta) {

        Double margenUtilidad = 0.00;

        margenUtilidad = utiles.FormatNum.FormatearDouble(precioVenta - precioCompra, 2);

        return margenUtilidad;

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

    public static Double costoPromedio(Double valorInventario, Double valorNuevoInventario, Double existenciaActual, Double existenciaNueva) {

        Double costoPromedio = 0.00;

        costoPromedio = (valorInventario + valorNuevoInventario) / (existenciaActual + existenciaNueva);

        return FormatNum.FormatearDouble(costoPromedio, 2);
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

    public static void exportarDAtos(TableView<?> table) throws IOException {

        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("sample");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumns().get(j).getText());
        }

        for (int i = 0; i < table.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size() - 2; j++) {
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

    public static void exportarDAtos(TableView<?> table, int factorColumna) throws IOException {

        //el factorColumna es para restar columna a la derecha de la tabla
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("sample");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumns().get(j).getText());
        }

        for (int i = 0; i < table.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size() - factorColumna; j++) {
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

    public static int diasEntreFecha(Date fechainicial, Date fechafinal) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechainiciostring = df.format(fechainicial);
        try {
            fechainicial = df.parse(fechainiciostring);
        } catch (ParseException ex) {
        }

        String fechafinalstring = df.format(fechafinal);
        try {
            fechafinal = df.parse(fechafinalstring);
        } catch (ParseException ex) {
        }

        long fechainicialms = fechainicial.getTime();
        long fechafinalms = fechafinal.getTime();
        long diferencia = fechafinalms - fechainicialms;
        double dias = Math.floor(diferencia / 86400000L);// 3600*24*1000 
        return ((int) dias);
    }

    public static Integer getMesesEntreFecha(Date fechainicial, Date fechafinal) {

        int difM = 0;
        Calendar inicio = new GregorianCalendar();
        Calendar fin = new GregorianCalendar();
        inicio.setTime(fechainicial);
        fin.setTime(fechafinal);
        int difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
        difM = difA * 12 + fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);
        System.out.println(difM);

        return difM;
    }

    public static Date getFechaCorteContrato(Date fechaInicioContrato, int diaEntreFecha) {

        Date fechaFutura = null;

        int diaActual = ClaseUtil.getDia(fechaInicioContrato);
        System.out.println("diaActual : " + diaActual);

        int diaDeRenovacion = ManejoUnidadDeNegocio.getInstancia().getUnidad(2).getDiaDeRenovacion();

        System.out.println("diaDeRenovacion : " + diaDeRenovacion);

        if (diaActual > diaDeRenovacion) {

            fechaFutura = ClaseUtil.Fechadiadespues(fechaInicioContrato, diaEntreFecha + diaDeRenovacion);

        } else if (diaActual >= 1 && diaActual < diaDeRenovacion) {

            fechaFutura = ClaseUtil.Fechadiadespues(fechaInicioContrato, (diaDeRenovacion - diaEntreFecha - 1));

        } else {

            fechaFutura = ClaseUtil.Fechadiadespues(fechaInicioContrato, diaEntreFecha);
        }
//

        return fechaFutura;
    }

    public static Integer getDiasCorteContrato(Date fechaInicioContrato) {

        int diaEntreFecha = 0;

        Date fechaUltimoDiaMes = ClaseUtil.getFechaUltimoDiaDelMes(fechaInicioContrato);
        Date fechaPrimerDiaMes = ClaseUtil.getFechaPrimerDiasDelMes(fechaInicioContrato);
        int diaActual = ClaseUtil.getDia(fechaInicioContrato);
        System.out.println("diaActual : " + diaActual);
        System.out.println("fechaUltimoDiaMes : " + fechaUltimoDiaMes);

        int diaDeRenovacion = ManejoUnidadDeNegocio.getInstancia().getUnidad(2).getDiaDeRenovacion();
        System.out.println("diaDeRenovacion : " + diaDeRenovacion);

        if (diaActual > diaDeRenovacion) {

            diaEntreFecha = ClaseUtil.diasEntreFecha(fechaInicioContrato, fechaUltimoDiaMes);
            System.out.println("diaActual > diaDeRenovacion : " + diaEntreFecha);

        } else if (diaActual >= 1 && diaActual < diaDeRenovacion) {

            diaEntreFecha = ClaseUtil.diasEntreFecha(fechaPrimerDiaMes, fechaInicioContrato);
            System.out.println("diaActual > 0 && diaActual < diaDeRenovacion : " + diaEntreFecha);
            diaEntreFecha = diaDeRenovacion - diaActual;
            System.out.println(" diaEntreFecha=diaDeRenovacion-diaActual: " + diaEntreFecha);

        }

        return diaEntreFecha;
    }

    public static void main(String[] args) {

//        try {
//            Calendar inicio = new GregorianCalendar();
//            Calendar fin = new GregorianCalendar();
//            inicio.setTime(new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2024"));
//            fin.setTime(new SimpleDateFormat("dd/MM/yyyy").parse("20/10/2024"));
//            int difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
//            int difM = difA * 12 + fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);
//            System.out.println(difM);
//        } catch (ParseException ex) {
//
//        }
//        String rutaStr = "C:\\";
//        rutaStr = rutaStr.replace("\\", "//");
        Date fechaFutura = null;

//        System.out.println("rutaStr " + rutaStr);
        Date fechaUltimoDiaMes = ClaseUtil.getFechaUltimoDiaDelMes(new Date("2024/02/01"));
        Date fechaPrimerDiaMes = ClaseUtil.getFechaPrimerDiasDelMes(new Date("2024/02/01"));
        int diaActual = ClaseUtil.getDia(new Date("2024/02/24"));
//        int diaActual = 0;
//        System.out.println("diaActual : " + diaActual);
//        System.out.println("fechaUltimoDiaMes : " + fechaUltimoDiaMes);

        int diaEntreFecha = ClaseUtil.getDiasCorteContrato(new Date("2024/03/12"));

        int diaDeRenovacion = ManejoUnidadDeNegocio.getInstancia().getUnidad(2).getDiaDeRenovacion();
        int mesDeRenovacion = ManejoUnidadDeNegocio.getInstancia().getUnidad(2).getMesDeRenovacion();
//        System.out.println("diaDeRenovacion : " + diaDeRenovacion);
//        System.out.println("mesDeRenovacion : " + mesDeRenovacion);

//        if (diaActual > diaDeRenovacion) {
//
//            diaEntreFecha = ClaseUtil.diasEntreFecha(new Date("2024/02/24"), fechaUltimoDiaMes);
//            System.out.println("diaActual > diaDeRenovacion : " + diaEntreFecha);
//            fechaFutura = ClaseUtil.Fechadiadespues(new Date("2024/02/24"), diaEntreFecha + diaDeRenovacion);
//
//        } else if (diaActual >= 1 && diaActual < diaDeRenovacion) {
//
//            diaEntreFecha = ClaseUtil.diasEntreFecha(fechaPrimerDiaMes, new Date("2024/02/08"));
//            System.out.println("diaActual > 0 && diaActual < diaDeRenovacion : " + diaEntreFecha);
//            System.out.println("diaActual > 0 && diaActual < diaDeRenovacion diferencia : " + (diaDeRenovacion - diaEntreFecha));
//            fechaFutura = ClaseUtil.Fechadiadespues(new Date("2024/02/08"), (diaDeRenovacion - diaEntreFecha - 1));
//
//        } else {
//
//            fechaFutura = ClaseUtil.Fechadiadespues(new Date("2024/02/02"), diaEntreFecha);
//        }
//
        System.out.println("fecha futura : " + fechaFutura);
        int diasEntreFecha = ClaseUtil.getDiasCorteContrato(new Date("2024/03/08"));
//        fechaFutura = ClaseUtil.getFechaCorteContrato(new Date(), diasEntreFecha);
//
//        Date fechaActOctubbre = ClaseUtil.getConstruirFecha(fechaFutura, diaDeRenovacion, mesDeRenovacion);
        System.out.println("diasEntreFecha " + diasEntreFecha);
////
//        Date fechaActOctubbre = ClaseUtil.Fechadiadespues(new Date("2024/02/24"), 209);
//
//        System.out.println(" dia ; " + 209 + "  mes : " + new SimpleDateFormat("yyyy-MM-dd").format(fechaActOctubbre));
//
//        System.out.println("Meses entre fecha : " + ClaseUtil.getMesesEntreFecha(fechaFutura, fechaActOctubbre));

//        String cantidad = "2.31".replace(".", "-");
//        String catidadStr = cantidad.replace(".", "-");
//
//        String Split[] = cantidad.split("-");
//
//        System.out.println("Cantdiad " + catidadStr.split("-")[1]);
//
//        String nombre = "maximo(nino)";
//
//        String ncf = "B0200001000";
//
//        String nafile = "C:\\pantalla\\img.jpg";
//
//        System.out.println("Extension " + getExtensionArchivo("img.jpg"));
//        System.out.println("Nombre mes " + utiles.ClaseUtil.getNombreDelMes(new Date()));
//        
//
//        for (String s : nafile.split("\\")) {
//            System.out.println(s);
//        }
//        String[] arg=nafile.split(".");
//        int index = nombre.indexOf("(");
//        String nom = ncf.substring(0, 2);
//        System.out.println(nom);
//        Map<String, String> env = System.getenv();
//        for (String envName : env.keySet()) {
//            System.out.format("%s=%s%n",
//                    envName,
//                    env.get(envName));
//        }
//        
//        if (Double.compare(2.32, 2.6) == 1) {
//            
//            System.out.println("Diferente");
//        }
//        
//          if (Double.compare(2.6, 2.6) ==0) {
//            
//            System.out.println("guales");
//        }
//        
//        System.out.println(ClaseUtil.FechaMesDespues(new Date(),1));
//        System.out.println("La fecha es " + ClaseUtil.asDate(LocalDate.MIN));
//        System.out.println("La fecha es " + ClaseUtil.esAlfanumerico("uj"));
//        System.out.println("La fecha es " + ClaseUtil.getAnioMes(new Date()));
    }

}
