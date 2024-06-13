/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cotizacionDeVenta;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Cliente;
import modelo.CotizacionDeVenta;
import modelo.DetalleCotizacionDeVenta;
import modelo.Factura;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoCotizacionDeVenta extends ManejoEstandar<CotizacionDeVenta> {

    private static ManejoCotizacionDeVenta manejoCotizacionDeVenta = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoCotizacionDeVenta getInstancia() {
        if (manejoCotizacionDeVenta == null) {
            manejoCotizacionDeVenta = new ManejoCotizacionDeVenta();
        }
        return manejoCotizacionDeVenta;
    }

    
    
    @Override
    public Session getSession() {
        return session;
    }
    

     public CotizacionDeVenta getCotizacion(int codigo) {

        CotizacionDeVenta cotizacion = null;

        String query = "select * From cotizacion_de_venta f where f.codigo=:codigo ";

        System.out.println("Query " + query);

        cotizacion = (CotizacionDeVenta) getSession().createSQLQuery(query).addEntity(Factura.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

        return cotizacion;
    }
     
    public List<CotizacionDeVenta> getLista() {

        String query = " SELECT * FROM cotizacion_de_venta  where unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(CotizacionDeVenta.class).list();

    }

     public List<CotizacionDeVenta> getListaCotizaciones() {

        String query = " SELECT * FROM cotizacion_de_venta where facturada=false  and unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(CotizacionDeVenta.class).list();

    }
    
    public List<CotizacionDeVenta> getLista(Cliente cliente) {

        String query = " SELECT * FROM cotizacion_de_venta  "
                + " where anulada=false and  cliente=:cliente  and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(CotizacionDeVenta.class).setParameter("cliente", cliente.getCodigo()).list();

    }

    public List<CotizacionDeVenta> getCotizacionPendiente(Cliente cliente) {

        String query = " SELECT * FROM cotizacion_de_venta  where anulada=false and  "
                + " cliente=:cliente  and unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(CotizacionDeVenta.class).setParameter("cliente", cliente.getCodigo()).list();

    }


    public List<CotizacionDeVenta> getLista(Date fechaDesde, Date fechaHasta) {

        String query = " SELECT * FROM cotizacion_de_venta where fecha "
                + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' "
                + " and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "'  "
                + "  and unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(CotizacionDeVenta.class).list();

    }

    public List<DetalleCotizacionDeVenta> getDetalleCotizacion(int cotizacion) {

        String query = " SELECT * FROM detalle_cotizacion_de_venta  where cotizacion_de_venta=:cotizacion  ";

        return session.createSQLQuery(query).addEntity(DetalleCotizacionDeVenta.class).setParameter("cotizacion", cotizacion).list();

    }

    @Override
    public Class getReferencia() {
        return CotizacionDeVenta.class;
    }

    
      public boolean existeSecuencia(int numero) {

        boolean flag = false;

        Factura factura = (Factura)session.createSQLQuery(" SELECT * FROM cotizacion_de_venta f  where anulada=false and "
                + "  f.numero=:numero limit 1 ")
                .addEntity(Factura.class)
                .setParameter("numero", numero).uniqueResult();

        if (!(factura==null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(numero)+1,0) from cotizacion_de_venta ";

        return (BigInteger) session.createSQLQuery(query).uniqueResult();

    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getMontoPendiente(ManejoCliente.getInstancia().getCliente(2)));
        System.out.println("Datos " + getInstancia().existeSecuencia(1000));
    }

}
