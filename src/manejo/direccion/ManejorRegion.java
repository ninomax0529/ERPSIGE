/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.direccion;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Region;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejorRegion extends ManejoEstandar<Region> {

    private static ManejorRegion manejorRegion = null;
    private Session session = HibernateUtil.getSession();

    public static ManejorRegion getInstancia() {

        if (manejorRegion == null) {
            manejorRegion = new ManejorRegion();
        }
        return manejorRegion;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Region> getLista() {

        String query = " SELECT * FROM region ";

        return session.createSQLQuery(query).addEntity(Region.class).list();

    }
    
    public Region getRegion(int codigo) {

        String query = " SELECT * FROM region  where codigo=:codigo ";

        return (Region)session.createSQLQuery(query)
                .addEntity(Region.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return Region.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
