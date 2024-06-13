/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contrato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.FacturaDatosDeVehiculoTemp;
//import modelo.Usuariop;
import org.hibernate.Session;
import utiles.Conexion;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoFacturaDatosDeVehiculoTemp extends ManejoEstandar<FacturaDatosDeVehiculoTemp> {

    private static ManejoFacturaDatosDeVehiculoTemp manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoFacturaDatosDeVehiculoTemp getInstancia() {
        if (manejo == null) {
            manejo = new ManejoFacturaDatosDeVehiculoTemp();
        }
        return manejo;
    }

    public void eliminarFacturaDatosVehiculoTemporal() {

        Connection cn = Conexion.getInsatancia().getConnectionDB();
        try {

            PreparedStatement pst = cn.prepareStatement(" delete from factura_datos_de_vehiculo_temp where codigo>0 ");

            System.out.println("eliminado factura_datos_de_vehiculo_temp  " + pst.execute());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return FacturaDatosDeVehiculoTemp.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getContratoPorVencer().get(0).getNumero());
    }

}
