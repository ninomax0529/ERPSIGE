/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cliente;

import manejo.unidad.*;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Plazo;
import modelo.TipoGarantia;
import modelo.Unidad;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoGarantia extends ManejoEstandar<TipoGarantia> {

    private static ManejoTipoGarantia manejoTipoGarantia = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoGarantia getInstancia() {
        if (manejoTipoGarantia == null) {
            manejoTipoGarantia = new ManejoTipoGarantia();
        }
        return manejoTipoGarantia;
    }

    public List<TipoGarantia> getLista() {

        String query = " SELECT * FROM tipo_garantia  ";

        return session.createSQLQuery(query).addEntity(TipoGarantia.class).list();

    }
    
   

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return TipoGarantia.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
