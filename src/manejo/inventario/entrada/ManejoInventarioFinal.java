/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.inventario.entrada;

import java.math.BigInteger;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DetalleInventarioFinal;
import modelo.InventarioFinal;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoInventarioFinal extends ManejoEstandar<InventarioFinal> {

    private static ManejoInventarioFinal manejoInventarioFinal = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoInventarioFinal getInstancia() {
        if (manejoInventarioFinal == null) {
            manejoInventarioFinal = new ManejoInventarioFinal();
        }
        return manejoInventarioFinal;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<InventarioFinal> getInventario() {

        String query = "SELECT * FROM inventario_final";

        return session.createSQLQuery(query).addEntity(InventarioFinal.class).list();

    }
    
    
    public List<DetalleInventarioFinal> getDetalleInventario(InventarioFinal inv) {

        List<DetalleInventarioFinal> lista;
        String query = "SELECT * FROM detalle_inventario_final  where  inventario_final=:inventario ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleInventarioFinal.class).setParameter("inventario", inv.getCodigo()).list();

        return lista;
    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(codigo)+1,0) from inventario_final ";

        return (BigInteger) session.createSQLQuery(query).uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return InventarioFinal.class;
    }

    public static void main(String[] args) {

    }

}
