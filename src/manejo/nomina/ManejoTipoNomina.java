/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.nomina;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoDeNomina;
import modelo.TipoVehiculo;

import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoNomina extends ManejoEstandar<TipoDeNomina> {

    private static ManejoTipoNomina manejoTipoNomina = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoNomina getInstancia() {
        if (manejoTipoNomina == null) {
            manejoTipoNomina = new ManejoTipoNomina();
        }
        return manejoTipoNomina;
    }

    public List<TipoDeNomina> getLista() {

        String query = " SELECT * FROM tipo_de_nomina  ";

        return session.createSQLQuery(query).addEntity(TipoDeNomina.class).list();

    }

    public TipoDeNomina getTipoDeNomina(int codigo) {

        String query = " SELECT * FROM tipo_de_nomina  where codigo=" + codigo;

        return (TipoDeNomina) session.createSQLQuery(query).addEntity(TipoDeNomina.class).uniqueResult();

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
