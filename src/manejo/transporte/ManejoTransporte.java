/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.transporte;

import manejo.vehiculo.*;
import manejo.unidad.*;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Transporte;
import modelo.Unidad;
import modelo.Vehiculo;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTransporte extends ManejoEstandar<Transporte> {

    private static ManejoTransporte manejoTransporte = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTransporte getInstancia() {
        if (manejoTransporte == null) {
            manejoTransporte = new ManejoTransporte();
        }
        return manejoTransporte;
    }

    public List<Transporte> getLista() {

        String query = " SELECT * FROM transporte  ";

        return session.createSQLQuery(query).addEntity(Transporte.class).list();

    }

    public Transporte getUnidad(int codigo) {

        String query = " SELECT * FROM transporte  where codigo=" + codigo;

        return (Transporte) session.createSQLQuery(query).addEntity(Transporte.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return Transporte.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
