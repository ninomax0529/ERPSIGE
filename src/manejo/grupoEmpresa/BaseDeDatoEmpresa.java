/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.grupoEmpresa;

/**
 *
 * @author maximilianoa-te
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import utiles.Conexion;

/**
 *
 * @author Maximiliano Almonte
 */
public class BaseDeDatoEmpresa {

    Connection conexion;
//    static DatabaseMetaData metadatos;
//    static ResultSetMetaData rsmetadatos;
////    private static String host = "localhost";
//    private static String host = ConfigDao.getInstance().prop.getProperty("db.host");
////    private static String usuario = "root";
//    private static String usuario = ConfigDao.getInstance().prop.getProperty("db.user");
////    private static String contraseña = "root123";
//    private static String password = ConfigDao.getInstance().prop.getProperty("db.password");
//    private static String driver = "com.mysql.jdbc.Driver";
//    private static String driver = "com.mysql.cj.jdbc.Driver";
    static BaseDeDatoEmpresa baseDeDato;

    static ResultSet resulSet;

    public BaseDeDatoEmpresa() {


    }

    public static BaseDeDatoEmpresa getInsatancia() {

        if (baseDeDato == null) {

            baseDeDato = new BaseDeDatoEmpresa();

            return baseDeDato;
        } else {
            return baseDeDato;
        }

    }

    public Connection getConnectionDB() {
        return conexion;
    }

    public List<String> getBaseDatoEmpresa() {

        List<String> lista = null;
        try {

            lista = new ArrayList();

            conexion = Conexion.getInsatancia().getConnectionDB();
            resulSet = conexion.createStatement().executeQuery("show databases");

            String baseDato = "";

            while (resulSet.next()) {

                if (!resulSet.isFirst()) {

                    baseDato = resulSet.getNString("DataBase");

                    if (!baseDato.startsWith("db_")) {
                        continue;
                    }

//                    if (baseDato.equals("db_colmd") || baseDato.equals("db_sige") || baseDato.equals("db_comedor")) {
//                        continue;
//                    }
                    lista.add(baseDato);

//                    System.out.println("Nombre base de datos : " + baseDato);
                }

            }

        } catch (SQLException ex) {

            Logger.getLogger(BaseDeDatoEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public void crearSchema(String schema) {

        try {

            conexion = Conexion.getInsatancia().getConnectionDB();
            conexion.createStatement().execute("create schema " + schema);

        } catch (SQLException ex) {

            Logger.getLogger(BaseDeDatoEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void crearBaseDeDato(String usuario, String clave, String baseDeDato, String rutaArchivo) throws FileNotFoundException, IOException {

        Process p = null;
        File file = new File(rutaArchivo);

        System.out.println("Ruta " + rutaArchivo);

        try {

            BaseDeDatoEmpresa.getInsatancia().crearSchema(baseDeDato);

            p = Runtime.getRuntime().exec("mysql -u " + usuario + " -p" + clave + " " + baseDeDato);

            OutputStream os = p.getOutputStream(); //Pedimos la salida
            FileInputStream fis = new FileInputStream(file); //creamos el archivo para le respaldo
            byte[] buffer = new byte[1000]; //Creamos una variable de tipo byte para el buffer

            int leido = fis.read(buffer);//Devuelve el número de bytes leídos o -1 si se alcanzó el final del stream.
            while (leido > 0) {
//                System.out.println("Leido " + leido);
                os.write(buffer, 0, leido); //Buffer de caracteres, Desplazamiento de partida para empezar a escribir caracteres, Número de caracteres para escribir
                leido = fis.read(buffer);
            }

            os.flush(); //vacía los buffers de salida
            os.close(); //Cierra
            fis.close(); //Cierra respaldo        
        } catch (IOException ex) {

            ex.printStackTrace();
            Logger.getLogger(BaseDeDatoEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cerrarConexiones() {

        List<Integer> lista = getListaProcesoDB();

        try {

            conexion = Conexion.getInsatancia().getConnectionDB();

            int i = 1;
            if (lista.size()>=10) {

                for (Integer id : lista) {

                    conexion.createStatement().execute("KILL " + id);

                    if (i == 5) {
                        break;
                    }

                    i++;
                }
            }

           

        } catch (SQLException ex) {

            Logger.getLogger(BaseDeDatoEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Integer> getListaProcesoDB() {

        List<Integer> lista = new ArrayList();
        try {

            conexion =Conexion.getInsatancia().getConnectionDB();
            resulSet = conexion.createStatement().executeQuery("SHOW PROCESSLIST");

            String id = "";

            while (resulSet.next()) {

                System.out.println("id  " + resulSet.getInt("id"));
                lista.add(resulSet.getInt("id"));

            }

         

        } catch (SQLException ex) {

            Logger.getLogger(BaseDeDatoEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public static void main(String args[]) {

        getInsatancia().cerrarConexiones();
//
//        try {
////
////            BaseDeDato.getInsatancia().crearBaseDeDato("root", "root123",
////                    "db_proyecto010", "C:\\erp\\nuevo\\ultimo\\falcon26\\db_36.sql");
////
////            // "C:/SistemaErp/erp nuevo/falcon26/ERP_DB.sql"
////            //   
//////
            for (String proyecto : BaseDeDatoEmpresa.getInsatancia().getBaseDatoEmpresa()) {

                System.out.println("Proyecto " + proyecto);
            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
