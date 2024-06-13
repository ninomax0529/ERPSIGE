/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.pedido;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Cliente;
import modelo.DetallePedido;
import modelo.Factura;
import modelo.Pedido;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoPedido extends ManejoEstandar<Pedido> {

    private static ManejoPedido manejoPedido = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoPedido getInstancia() {
        if (manejoPedido == null) {
            manejoPedido = new ManejoPedido();
        }
        return manejoPedido;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Pedido> getLista() {

        String query = " SELECT * FROM pedido  ";

        return session.createSQLQuery(query).addEntity(Pedido.class).list();

    }
    
//      public List<Pedido> getLista(Date fechaIni,Date fechafin) {
//
//        String query = " SELECT * FROM pedido where fecha between :fi and :ff ";
//
//        return session.createSQLQuery(query)
//                
//                .addEntity(Pedido.class)
//                .setParameter("fi", fechaIni)
//                .setParameter("ff", fechafin)
//                .list();
//
//    }

    public Pedido getPedido(int codigo) {

        String query = " SELECT * FROM pedido where codigo=:codigo  "
                + "  and unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return (Pedido) session.createSQLQuery(query)
                .addEntity(Pedido.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }
    
      public DetallePedido getDetallePedidoPorCodigo(int codigo) {

        String query = " SELECT * FROM detalle_pedido where codigo=:codigo ";

        return (DetallePedido) session.createSQLQuery(query)
                .addEntity(DetallePedido.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    public List<Pedido> getLista(Cliente cliente) {

        String query = " SELECT * FROM pedido  where cliente=:cliente and pendiente>0  "
                + " and pagada=false and tipo_venta=2  "
                + "  and unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(Factura.class).setParameter("cliente", cliente.getCodigo()).list();

    }

    public List<Pedido> getPedidosPendiente(int estado) {

        String query = " SELECT * from pedido  where anulado=false  and estado=:estado "
                + " and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(Pedido.class).setParameter("estado", estado).list();

    }

    public List<Pedido> getLista(Date fechaDesde, Date fechaHasta) {

        String query = " SELECT * FROM pedido where fecha "
                + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "'  "
                + "and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "'  "
                + " and unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(Pedido.class).list();

    }

    public List<DetallePedido> getDetallePedido(int pedido) {

        String query = " SELECT * FROM detalle_pedido  where pedido=:pedido  ";

        return session.createSQLQuery(query).addEntity(DetallePedido.class).setParameter("pedido", pedido).list();

    }

    @Override
    public Class getReferencia() {
        return Factura.class;
    }

    public boolean existeNCF(String ncf) {

        boolean flag = false;

        List<Factura> list = session.createQuery("SELECT f FROM Factura f where f.ncf=:ncf").setParameter("ncf", ncf).list();

        if (list.size() > 0) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(codigo)+1,0) from pedido ";

        return (BigInteger) session.createSQLQuery(query).uniqueResult();

    }
    
      public void remover(DetallePedido det) {

        try {

            System.out.println("Detalle Pedido "+det.getCodigo());
            getSession().beginTransaction();

            getSession().delete(det);

            getSession().getTransaction().commit();

        } catch (Exception e) {
            getSession().getTransaction().rollback();
            System.out.println(e.getMessage());
        }
//        finally {
//            getSession().close();
//        }
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
