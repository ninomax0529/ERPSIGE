/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Departamento IT
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import modelo.Cheque;

/**
 *
 * @author maximilianoa-te
 */
public class PrinterApi {

    /**
     * @param args the command line arguments
     */
    public static void imprimirFactura() {

        PrinterMatrix printer = new PrinterMatrix();

        Extenso e = new Extenso();

        e.setNumber(101.85);

        //Definir el tamanho del papel para la impresion  aca 25 lineas y 80 columnas
        printer.setOutSize(60, 80);
        //Imprimir * de la 2da linea a 25 en la columna 1;
        // printer.printCharAtLin(2, 25, 1, "*");
        //Imprimir * 1ra linea de la columa de 1 a 80
        printer.printCharAtCol(1, 1, 80, "=");
        //Imprimir Encabezado nombre del La EMpresa
        printer.printTextWrap(1, 2, 30, 80, "FACTURA DE VENTA");
        //printer.printTextWrap(linI, linE, colI, colE, null);
        printer.printTextWrap(2, 3, 1, 22, "Num. Boleta : " + "52635");
        printer.printTextWrap(2, 3, 25, 55, "Fecha de Emision: " + new Date());
        printer.printTextWrap(2, 3, 60, 80, "Hora: 12:22:51");
        printer.printTextWrap(3, 3, 1, 80, "Vendedor.  : " + "Maximilianoa " + " - " + "Zapato");
        printer.printTextWrap(4, 4, 1, 80, "CLIENTE: " + "Jose fina");
        printer.printTextWrap(5, 5, 1, 80, "RUC/CI.: " + "03400444208");
        printer.printTextWrap(6, 6, 1, 80, "DIRECCION: " + "");
        printer.printCharAtCol(7, 1, 80, "=");

        printer.printTextWrap(7, 8, 1, 80, "Codigo          Descripcion                Cant.      P  P.Unit.      P.Total");
        printer.printCharAtCol(9, 1, 80, "-");
        int filas = 4;

        for (int i = 0; i < filas; i++) {

            printer.printTextWrap(9 + i, 10, 1, 80, "n1" + "|"
                    + "n2" + "| " + "n3" + "| "
                    + "n4" + "|" + "n5");

//            printer.printTextWrap(9 + i, 10, 1, 80, tblVentas.getValueAt(i, 0).toString() + "|" + tblVentas.getValueAt(i, 1).toString() + "| " + tblVentas.getValueAt(i, 2).toString() + "| " + tblVentas.getValueAt(i, 3).toString() + "|" + tblVentas.getValueAt(i, 4).toString());
        }

        if (filas > 15) {

            printer.printCharAtCol(filas + 1, 1, 80, "=");
            printer.printTextWrap(filas + 1, filas + 2, 1, 80, "TOTAL A PAGAR " + "200");
            printer.printCharAtCol(filas + 2, 1, 80, "=");
            printer.printTextWrap(filas + 2, filas + 3, 1, 80, "Esta boleta no tiene valor fiscal, solo para uso interno.: + Descripciones........");

        } else {

            printer.printCharAtCol(25, 1, 80, "=");
            printer.printTextWrap(26, 26, 1, 80, "TOTAL A PAGAR " + "200");
            printer.printCharAtCol(27, 1, 80, "=");
            printer.printTextWrap(27, 28, 1, 80, "Esta boleta no tiene valor fiscal, solo para uso interno.: + Descripciones........");

        }
        printer.toFile("impresion.txt");

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("impresion.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }

        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);
                printer.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }

        //inputStream.close();
    }

    public static void imprimirCheque(Cheque cheque) {

        PrinterMatrix printer = new PrinterMatrix();

        Extenso e = new Extenso();

        e.setNumber(101.85);
        NumeroALetra na = new NumeroALetra();
        String monto = Double.toString(cheque.getMonto());

        Date fecha = cheque.getFecha();

        int dia, mes, ano;
        ano = ClaseUtil.getAno(fecha);
        mes = ClaseUtil.getMes(fecha);
        dia = ClaseUtil.getDia(fecha);
        String fechaCompuesta = dia + "  " + mes + "   " + ano;

        String montoLetra = na.Convertir(monto, true);

        //Definir el tamanño del papel para la impresion  60 lineas y 90 columnas
         printer.setOutSize(60, 90);

        //Imprimir Coletilla de cheque
        printer.printTextWrap(3, 3, 3, 15,fecha.toString());
//        printer.printTextWrap(3, 5, 20, 65, ChequeDao.getInstancia().getCheque(26).getSolicitud().getConcepto());
        printer.printTextWrap(3, 3, 80, 90, monto);

        //Imprimir Cuerpo de Cheque 
        printer.printTextWrap(15, 15, 79, 90, fechaCompuesta);
        //printer.printTextWrap(linI, linE, colI, colE, null);
        printer.printTextWrap(18, 18, 20, 75, cheque.getBeneficiario());
        printer.printTextWrap(18, 18, 80, 90, monto);

        printer.printTextWrap(20, 22, 14, 74, montoLetra);

        printer.toFile("impresion.txt");

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("impresion.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }

        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);
                printer.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }

        //inputStream.close();
    }

    public static void imprimirLineaColumna() {

        PrinterMatrix printer = new PrinterMatrix();

        Extenso e = new Extenso();

        e.setNumber(101.85);

        //Definir el tamanho del papel para la impresion  aca 25 lineas y 80 columnas
        printer.setOutSize(60, 80);
        //Imprimir * de la 2da linea a 25 en la columna 1;
        // printer.printCharAtLin(2, 25, 1, "*");
//        //Imprimir * 1ra linea de la columa de 1 a 80
//        printer.printCharAtCol(1, 1, 80, "=");
//        //Imprimir Encabezado nombre del La EMpresa
//        printer.printTextWrap(1, 2, 30, 80, "FACTURA DE VENTA");
//        //printer.printTextWrap(linI, linE, colI, colE, null);
//        printer.printTextWrap(2, 3, 1, 22, "Num. Boleta : " + "52635");
//        printer.printTextWrap(2, 3, 25, 55, "Fecha de Emision: " + new Date());
//        printer.printTextWrap(2, 3, 60, 80, "Hora: 12:22:51");
//        printer.printTextWrap(3, 3, 1, 80, "Vendedor.  : " + "Maximilianoa " + " - " + "Zapato");
//        printer.printTextWrap(4, 4, 1, 80, "CLIENTE: " + "Jose fina");
//        printer.printTextWrap(5, 5, 1, 80, "RUC/CI.: " + "03400444208");
//        printer.printTextWrap(6, 6, 1, 80, "DIRECCION: " + "");
//        printer.printCharAtCol(7, 1, 80, "=");
//
//        printer.printTextWrap(7, 8, 1, 80, "Codigo          Descripcion                Cant.      P  P.Unit.      P.Total");
//        printer.printCharAtCol(9, 1, 80, "-");
//        int filas = 4;

        int j = 49;
        for (int i = 0; i < 30; i++) {

            j--;
            printer.printCharAtCol(i + 1, 1, 50, Integer.toString(i + 1) + " ");

        }

        printer.toFile("impresion.txt");

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("impresion.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }

        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
//                printJob.print(document, attributeSet);
                printer.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }

        //inputStream.close();
    }

    public static void imprimirPorConsola() {

        PrinterMatrix printer = new PrinterMatrix();

//Define o tamanho do papel/tela para impressão, aqui 25 linhas e 80 colunas
        printer.setOutSize(25, 80);

//imprimir * da 2a linha a 25 na coluna 1
        printer.printCharAtLin(2, 25, 1, "*");

//imprimir * na 1a linha da coluna 1 a 80
        printer.printCharAtCol(1, 1, 80, "*");

//imprimir * da 2a linha a 25 na coluna 1
        printer.printCharAtLin(2, 25, 80, "*");

//imprimir O na linha 3 coluna 39
        printer.printTextLinCol(3, 39, "OK");

//imprimir Teste na linha 14 e coluna 40
        printer.printTextLinCol(14, 40, "Teste");

//imprimir * na linha 25 da coluna 1 a 80
        printer.printCharAtCol(25, 1, 80, "*");

//imprimir Imprimindo em modo texto na linha 14 e coluna 10
        printer.printTextLinCol(14, 10, "Imprimindo em modo texto");

//imprimir – na linha 13 da coluna 2 a 79
        printer.printCharAtCol(13, 2, 79, "-");

//imprimir – na linha 15 da coluna 2 a 79
        printer.printCharAtCol(15, 2, 79, "-");

//imprimir + da linha 17 a linha 24 da coluna 50 a 79
        printer.printCharAtLinCol(17, 24, 50, 79, "+");

//imprimir + da linha 17 a linha 24 da coluna 2 a 40
        printer.printCharAtLinCol(17, 24, 2, 40, "+");

//imprimir + da linha 17 a linha 20 da coluna 41 a 49
        printer.printCharAtLinCol(17, 20, 41, 49, "+");

        printer.toImageFile("printermatrix.jpg");
        printer.show();
//printer.toFile( “teste.txt” );
//printer.toPrinterServer(“192.168.9.12”,9100);
//printer.toPrinter(“LPT1”);
        printer.mapearDocumentoImageFile(25, 80, "printermatrix1.jpg");
    }

    public static void main(String[] args) {

//        imprimirFactura();
//        imprimirPorConsola();
//        imprimirLineaColumna();
//        imprimirCheque();
    }
}
