/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.factura;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.OrigenFactura;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoOrigenFactura extends ManejoEstandar<OrigenFactura> {
    
    private static ManejoOrigenFactura manejo = null;
    private Session session = HibernateUtil.getSession();
    
    public static ManejoOrigenFactura getInstancia() {
        if (manejo == null) {
            manejo = new ManejoOrigenFactura();
        }
        return manejo;
    }
    
    @Override
    public Session getSession() {
        return session;
    }
    
    public List<OrigenFactura> getOrigenFactura() {
        
        String query = " SELECT * FROM origen_factura ";
        
        return session.createSQLQuery(query).addEntity(OrigenFactura.class).list();
        
    }
    
    public OrigenFactura getOrigenFactura(int codigo) {
        
        String query = " SELECT * FROM origen_factura  where codigo=:codigo ";
        
        return (OrigenFactura) session.createSQLQuery(query)               
                .addEntity(OrigenFactura.class)
                 .setParameter("codigo", codigo)
                .uniqueResult();
        
    }
    
    @Override
    public Class getReferencia() {
        return OrigenFactura.class;
    }
    
    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }
    
}
