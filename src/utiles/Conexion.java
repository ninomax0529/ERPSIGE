/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

/**
 *
 * @author maximilianoa-te
 */
/*
 * ConectDB.java
 *
 * Created on 7 de septiembre de 2019, 02:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author maximiliano
 */
public class Conexion {

    //nota ,si no me permite conectarme   al servidor remoto es por el firewall del servidor
    private static Connection con = null;
    private String bd = "db_erp_sige";
//    private String server = "localhost";
    private String server = "10.0.0.80";
    private String name = "root";
    private String password = "root123";
    private String url = "jdbc:mysql://" + server + ":3306/" + bd;
    private static String driver = "com.mysql.jdbc.Driver";
    private static Conexion conexion;
    String cad_ant;

    /**
     * Creates a new instance of ConectDB
     */
    private Conexion() {

        try {

            Class.forName(driver);
            System.out.println(" Driver cargado Exitosamente ");

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.err.println("Error al cargar driver !");
        }

        // Creando coneccion
        try {
            con = DriverManager.getConnection(url, name, password);
            System.out.println("Conexion Establecida Exitosamente");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error al intentar conectar Base de Datos");
        }
    }

    public static Conexion getInsatancia() {

        if (conexion == null) {

            conexion = new Conexion();
            return conexion;

        } else {

            return conexion;
        }
    }

    public Connection getConnectionDB() {

//        if (con == null) {
//
//            JOptionPane.showMessageDialog(null, "LA CONEXION NO SE PUDO ESTABLECER");
//
//            return null;
//
//        } else {
//
//            JOptionPane.showMessageDialog(null, "LA CONEXION se ESTABLECIO CON EXITO");
//
        return con;
//        }

    }

    private String getCodigoSeguridad() {

        Random rnd = new Random();
        String abecedario = "ABCDEFGHIJKLMOPQRSTUVWXYZ";
        String cadena = "";
        int m = 0, pos = 0, num;

        while (m < 1) {

            pos = (int) (rnd.nextDouble() * abecedario.length() - 1 + 0);
            num = (int) (rnd.nextDouble() * 999 + 1000);
            cadena = cadena + abecedario.charAt(pos) + num;
            pos = (int) (rnd.nextDouble() * abecedario.length() - 1 + 0);
            cadena = cadena + abecedario.charAt(pos);

            if (cadena.length() != 6 || cad_ant.equals(cadena)) {
                m = 0;
            }

            cad_ant = cadena;

            cadena = "";

            m++;

        }

        return cad_ant;
    }

    public static void main(String[] args) {

//        Conexion.getInsatancia().getConnectionDB();
    }
}
