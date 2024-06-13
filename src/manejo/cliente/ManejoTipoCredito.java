/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cliente;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Plazo;
import modelo.TipoCredito;
import modelo.TipoGarantia;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoCredito extends ManejoEstandar<TipoCredito> {

    private static ManejoTipoCredito manejoTipoCredito = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoCredito getInstancia() {
        if (manejoTipoCredito == null) {
            manejoTipoCredito = new ManejoTipoCredito();
        }
        return manejoTipoCredito;
    }

    public List<TipoCredito> getLista() {

        String query = " SELECT * FROM tipo_credito  ";

        return session.createSQLQuery(query).addEntity(TipoCredito.class).list();

    }
    
    
    
   

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return TipoCredito.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
