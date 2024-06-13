/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.direccion;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Ciudad;
import modelo.Region;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoCiudad extends ManejoEstandar<Ciudad> {

    private static ManejoCiudad manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoCiudad getInstancia() {

        if (manejo == null) {
            manejo = new ManejoCiudad();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Ciudad> getLista() {

        String query = " SELECT * FROM ciudad ";

        return session.createSQLQuery(query).addEntity(Ciudad.class).list();

    }
    
    public Ciudad getCiudad(int codigo) {

        String query = " SELECT * FROM ciudad  where codigo=:codigo ";

        return (Ciudad)session.createSQLQuery(query)
                .addEntity(Ciudad.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return Ciudad.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
