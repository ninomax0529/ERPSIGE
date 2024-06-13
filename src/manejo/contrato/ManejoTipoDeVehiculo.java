/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contrato;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.ModalidadEquipo;
import modelo.TipoVehiculo;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoDeVehiculo extends ManejoEstandar<TipoVehiculo> {

    private static ManejoTipoDeVehiculo manejoTipoDeVehiculo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoDeVehiculo getInstancia() {
        if (manejoTipoDeVehiculo == null) {
            manejoTipoDeVehiculo = new ManejoTipoDeVehiculo();
        }
        return manejoTipoDeVehiculo;
    }

    public List<TipoVehiculo> getLista() {

        String query = " SELECT * FROM tipo_vehiculo  ";

        return session.createSQLQuery(query).addEntity(TipoVehiculo.class).list();

    }

    public TipoVehiculo getTipoVehiculo(int codigo) {

        String query = " SELECT * FROM tipo_vehiculo  where codigo=" + codigo;

        return (TipoVehiculo) session.createSQLQuery(query).addEntity(TipoVehiculo.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return TipoVehiculo.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
