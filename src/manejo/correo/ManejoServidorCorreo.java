/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.correo;

/**
 *
 * @author MAXIMILIANO
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import manejo.cotizacionDeVenta.ManejoCotizacionDeVenta;
import modelo.ServidorDeCorreo;
import org.hibernate.Session;
import utiles.Conexion;

public class ManejoServidorCorreo extends ManejoEstandar<ServidorDeCorreo> {

    private static ManejoServidorCorreo manejoServidorCorreo = null;
    private static ManejoCotizacionDeVenta manejoCotizacionDeVenta = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoServidorCorreo getInstancia() {

        if (manejoServidorCorreo == null) {
            manejoServidorCorreo = new ManejoServidorCorreo();
        }
        return manejoServidorCorreo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return ServidorDeCorreo.class;
    }


    public List<ServidorDeCorreo> getListaServidorCorreo() {

        String query = " FROM  ServidorDeCorreo cc where cc.habilitado=true";

        return session.createQuery(query).list();

    }

    public ServidorDeCorreo getServidorCorreo() {

        String query = " FROM  ServidorDeCorreo sc where sc.habilitado=true";

        return (ServidorDeCorreo) session.createQuery(query).uniqueResult();

    }

    public String getExpresionCron() {

        Statement stmt = null;
        ResultSet resultSet = null;
        Connection con = null;

        String query = " select * from  servidor_de_correo sc where sc.habilitado=true";
        String expresionCron = "";

        try {

            con = Conexion.getInsatancia().getConnectionDB();
            stmt = con.createStatement();

            resultSet = stmt.executeQuery(query);

            if (resultSet.next()) {

                expresionCron = resultSet.getString("expresion_cron");

            }

        } catch (SQLException ex) {

            Logger.getLogger(ManejoServidorCorreo.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            try {

                if (null != con) {

                    con.close();

                }
                if (null != stmt) {
                    stmt.close();

                }
                if (null != resultSet) {

                    resultSet.close();

                }

            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return expresionCron;

    }

    public ServidorDeCorreo getServidorDeCorreo() {

        Statement stmt = null;
        ResultSet resultSet = null;
        Connection con = null;

        ServidorDeCorreo servidorDeCorreo = null;

        String query = " select * from  servidor_de_correo sc where sc.habilitado=true";

        try {

            con = Conexion.getInsatancia().getConnectionDB();
            stmt = con.createStatement();

            resultSet = stmt.executeQuery(query);

            if (resultSet.next()) {

                servidorDeCorreo = new ServidorDeCorreo();
                servidorDeCorreo.setExpresionCron(resultSet.getString("expresion_cron"));
                servidorDeCorreo.setServidor(resultSet.getString("servidor"));
                servidorDeCorreo.setClave(resultSet.getString("clave"));
                servidorDeCorreo.setPuerto(resultSet.getInt("puerto"));
                servidorDeCorreo.setHabilitado(resultSet.getBoolean("habilitado"));

            }

        } catch (SQLException ex) {

            Logger.getLogger(ManejoServidorCorreo.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            try {

//                if (null != con) {
//
//                    con.close();
//
//                }
                if (null != stmt) {
                    stmt.close();

                }
                if (null != resultSet) {

                    resultSet.close();

                }

            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return servidorDeCorreo;

    }

    public static void main(String[] args) {

        System.out.println("Expresion Cron " + getInstancia().getExpresionCron());
//        for (ServidorDeCorreo hm : getInstancia().getListaServidorCorreo()) {
//            System.out.println("el material es " + hm.getServidor());
//        }
    }
}
