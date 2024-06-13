/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cxc.gestionDeCobro;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.GestionDeCobro;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoGestionDeCobro extends ManejoEstandar<GestionDeCobro> {

    private static ManejoGestionDeCobro manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoGestionDeCobro getInstancia() {
        if (manejo == null) {
            manejo = new ManejoGestionDeCobro();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<GestionDeCobro> getLista() {

        String query = " SELECT * FROM gestion_de_cobro  ";

        return session.createSQLQuery(query).addEntity(GestionDeCobro.class).list();

    }

    public List<GestionDeCobro> getLista(Date fi, Date ff) {

        String query = " SELECT * FROM gestion_de_cobro  where  date(fecha_cobro) "
                + "  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' ";

        return session.createSQLQuery(query).addEntity(GestionDeCobro.class).list();

    }

    @Override
    public Class getReferencia() {
        return GestionDeCobro.class;
    }

    public static void main(String[] args) {

//         System.out.println("Datos " + getInstancia().getMontoPendiente(ManejoCliente.getInstancia().getCliente(1)));
//        System.out.println("Datos " + getInstancia().getMontoDisponible(ManejoCliente.getInstancia().getCliente(3)));
    }

}
