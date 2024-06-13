/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.ordenCompra;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DetalleOrdenCompra;
import modelo.FacturaSuplidor;
import modelo.OrdenCompra;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class OrdenDeCompraDao extends ManejoEstandar<OrdenCompra> {

    private static OrdenDeCompraDao manejoOrdenDeCompraDao = null;
    private Session session = HibernateUtil.getSession();

    public static OrdenDeCompraDao getInstancia() {
        if (manejoOrdenDeCompraDao == null) {
            manejoOrdenDeCompraDao = new OrdenDeCompraDao();
        }
        return manejoOrdenDeCompraDao;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<OrdenCompra> getListOrdenDeCompra() {

        String query = "SELECT * FROM orden_compra WHERE anulada=false";

        return session.createSQLQuery(query).addEntity(OrdenCompra.class).list();

    }

    public List<OrdenCompra> getListOrdenDeCompra(String estado) {

        String query = " SELECT * FROM orden_compra WHERE anulada=false  and estado=:estado ";

        return session.createSQLQuery(query).addEntity(OrdenCompra.class).setParameter("estado", estado).list();

    }
    
     public OrdenCompra getOrdenDeCompra(int orden) {

        String query = " SELECT * FROM orden_compra where codigo=:codigo ";

        return (OrdenCompra)session.createSQLQuery(query)
                .addEntity(OrdenCompra.class).setParameter("codigo", orden).uniqueResult();

    }

    public List<OrdenCompra> getListOrdenDeCompraPendiente() {

        String query = " SELECT * FROM orden_compra WHERE anulada=false and autorizada=false ";

        return session.createSQLQuery(query).addEntity(OrdenCompra.class).list();

    }

    public List<OrdenCompra> getListOrdenDeCompraAutorizada() {

        String query = " SELECT * FROM orden_compra WHERE anulada=false  and autorizada=true ";

        return session.createSQLQuery(query).addEntity(OrdenCompra.class).list();

    }

    public List<OrdenCompra> getListOrdenDeCompraAutorizada(String estado) {

        String query = " SELECT * FROM orden_compra WHERE anulada=false "
                + " and autorizada=true and  completada='no' and  estado=:estado  and  facturada=false ";

        return session.createSQLQuery(query).addEntity(OrdenCompra.class)
                .setParameter("estado", estado).list();

    }

    public List<OrdenCompra> getListOrdenDeCompraPorSuplidor(int suplidor) {

        String query = " SELECT * FROM orden_compra WHERE anulada=false "
                + " and autorizada=true and completada='no'  and  suplidor=:suplidor ";

        System.out.println("Query " + query);

        return session.createSQLQuery(query).addEntity(OrdenCompra.class)
                .setParameter("suplidor", suplidor).list();

    }

    public List<OrdenCompra> getListaOrdenCompra(Date fechaini, Date fechafin) {

        String query = " SELECT * FROM orden_compra  where  anulada=false  and   fecha between '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaini) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "'";

        System.out.println("Query anulada " + query);

        return session.createSQLQuery(query).addEntity(OrdenCompra.class).list();

    }

    public List<OrdenCompra> getListaOrdenCompra(Date fechaini, Date fechafin, String estado) {

        String query = "SELECT * FROM orden_compra  where  anulada=false  estado=:estado "
                + "  and   fecha between '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaini) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "'";

        System.out.println("Query anulada " + query);

        return session.createSQLQuery(query).addEntity(FacturaSuplidor.class).setParameter("estado", estado).list();

    }

    public List<DetalleOrdenCompra> getDetalleOrden(int orden) {

        String query = " SELECT * FROM detalle_orden_compra  where orden_compra=:orden ";

        return session.createSQLQuery(query)
                .addEntity(DetalleOrdenCompra.class).setParameter("orden", orden).list();

    }
    
      public DetalleOrdenCompra getArticuloDetalleOrden(int orden,int articulo) {

        String query = " SELECT * FROM  detalle_orden_compra  "
                + " where orden_compra=:orden and articulo=:articulo ";

        return (DetalleOrdenCompra)session.createSQLQuery(query)
                .addEntity(DetalleOrdenCompra.class)
                .setParameter("orden", orden)
                .setParameter("articulo", articulo)
                .uniqueResult();

    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(codigo)+1,0) from orden_compra ";

        return (BigInteger) session.createSQLQuery(query).uniqueResult();

    }

    public static void main(String[] args) {

        for (OrdenCompra oc : getInstancia().getListOrdenDeCompraPorSuplidor(4)) {
            System.out.println("Orden " + oc.getEstado());
        }
    }

    @Override
    public Class getReferencia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
