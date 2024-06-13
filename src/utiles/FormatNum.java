/*
 * FormatNum.java
 *
 * Created on July 18, 2007, 11:24 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package utiles;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author dario
 */
public class FormatNum {

    private static NumberFormat nf = NumberFormat.getInstance();
    private static FormatNum var = null;
    private static int STANDART = 2;
    private static DecimalFormat decf = new DecimalFormat("$###,###,###.00");
    private static DecimalFormat df = new DecimalFormat("#########.00");
      private static DecimalFormat dfComa = new DecimalFormat("###,###,###.00");
    private static DecimalFormat dff = new DecimalFormat("###,###,###");

    public static String FormatearString(double i, int posicion) {

        BigDecimal bd = new BigDecimal(i);

//         bd = bd.setScale(posicion,BigDecimal.ROUND_CEILING);

        bd = bd.setScale(posicion, BigDecimal.ROUND_UP);

        return df.format(bd);
    }

    public static String FormatearString2(BigDecimal bd, int posicion) {

        bd = bd.setScale(posicion, BigDecimal.ROUND_HALF_UP);

        return bd.toString();
    }

    public static double FormatearDouble(double i, int posicion) {
        BigDecimal bd = new BigDecimal(i);
        bd = bd.setScale(posicion, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    
      public static double FormatearDoubleAEntero(double i, int posicion) {
        BigDecimal bd = new BigDecimal(i);
        bd = bd.setScale(posicion, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    public static double FormatearDoubleSinRedondeo(Double i) {

        String num = i.toString();

        if (num.length() > 4) {
            
            return Double.parseDouble(num.substring(1, 5));
            
        } else {
            
            return Double.parseDouble(num);
        }


    }

     public static double FormatearDoubleSinRedondeo(Double i,int numeroDecimales) {

        String num = i.toString();

        if (num.length() > 4) {
            
            return Double.parseDouble(num.substring(1, numeroDecimales));
            
        } else {
            
            return Double.parseDouble(num);
        }


    }
     public static double FormatearDoubleSinRedondeoPorciento(Double i) {

        String num = i.toString();

            return Double.parseDouble(num.substring(1, 12));
       
    }
    
     
    public static String SepararMiles(double i) {
        return decf.format(i);
    }

    public static Double formatoNumero(double i) {

        return Double.parseDouble(df.format(i));
    }

    public static String numeroConComa(double i) {

        return dfComa.format(i);
    }

     public static String getNumeroConComa(double i) {

        return  dff.format(i);
    }
     
    public static String formatoNum(double numero) {

        return df.format(numero);
    }

    public static void main(String[] args) {
//        double decimal =0.0138603037190083;
//
//        System.out.println(FormatearDoubleSinRedondeo(decimal,12));
//        
//        System.out.println("redondeo "+(18004.51));
//        
        System.out.println(FormatearDoubleSinRedondeo(0.327376425855513));
    }
}
