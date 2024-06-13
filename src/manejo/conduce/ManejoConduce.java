/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.conduce;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import manejo.cliente.ManejoCreditoCliente;
import modelo.Cliente;
import modelo.Conduce;
import modelo.DetalleConduce;
import modelo.Factura;
import org.hibernate.Session;
import util.FormatNum;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoConduce extends ManejoEstandar<Conduce> {

    private static ManejoConduce manejoConduce = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoConduce getInstancia() {
        if (manejoConduce == null) {
            manejoConduce = new ManejoConduce();
        }
        return manejoConduce;
    }

    
    
    @Override
    public Session getSession() {
        return session;
    }
    

     public Conduce getConduce(int codigo) {

        Conduce conduce = null;

        String query = "select * From conduce c where c.codigo=:codigo ";

        System.out.println("Query " + query);

        conduce = (Conduce) getSession().createSQLQuery(query).addEntity(Conduce.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

        return conduce;
    }
     
    public List<Conduce> getLista() {

        String query = " SELECT * FROM conduce where unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(Conduce.class).list();

    }

    public List<Conduce> getLista(Cliente cliente) {

        String query = " SELECT * FROM factura  "
                + " where anulada=false and  cliente=:cliente and"
                + "  pendiente>0 and pagada=false and tipo_venta=2 and unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(Factura.class).setParameter("cliente", cliente.getCodigo()).list();

    }

    public List<Factura> getFacturaPendiente(Cliente cliente) {

        String query = " SELECT * FROM factura  where anulada=false and  "
                + " cliente=:cliente and pendiente>0 and pagada=false and tipo_venta=2 ";

        return session.createSQLQuery(query).addEntity(Factura.class).setParameter("cliente", cliente.getCodigo()).list();

    }

    public Double getMontoPendiente(Cliente cliente) {

        Double montoPendiente = 0.00;

        String query = " SELECT FN_BALANCE_PENDIENTE_CLIENTE " + "(" + cliente.getCodigo() + ") as monto ";

        System.out.println("Query " + query);

        montoPendiente = (Double) session.createSQLQuery(query)
                .uniqueResult();

        return montoPendiente == null ? 0.00 : montoPendiente;
    }

    public Double getMontoDisponible(Cliente cliente) {

        Double montoPendiente = getMontoPendiente(cliente);

        Double montoCredito = ManejoCreditoCliente.getInstancia().getMontoCreditoCliente(cliente.getCodigo());
        System.out.println("Monto Credito " + montoCredito + " monto pendinte " + montoPendiente);

        return FormatNum.FormatearDouble(montoCredito - montoPendiente, 2);

//        return FormatNum.FormatearDouble(cliente.getMontoCredito() - montoPendiente, 2);
    }

    public Double getMontoDisponibleCliente(Cliente cliente) {

        Double montoPendiente = getMontoPendiente(cliente);

        Double montoCredito = ManejoCreditoCliente.getInstancia().getMontoCreditoCliente(2);

        return FormatNum.FormatearDouble(montoCredito - montoPendiente, 2);
    }

    public List<Conduce> getLista(Date fechaDesde, Date fechaHasta) {

        String query = " SELECT * FROM conduce where fecha "
                + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' "
                + "and '" + new SimpleDateFormat("yyyy-MM-dd")
                        .format(fechaHasta) + "' and unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(Conduce.class).list();

    }

    public List<DetalleConduce> getDetalleConduce(int conduce) {

        String query = " SELECT * FROM detalle_conduce  where conduce=:conduce  ";

        return session.createSQLQuery(query).addEntity(DetalleConduce.class).setParameter("conduce", conduce).list();

    }

    @Override
    public Class getReferencia() {
        return Conduce.class;
    }

    public boolean existeNCF(String ncf) {

        boolean flag = false;

        Factura fact = (Factura)session.createSQLQuery("SELECT * FROM factura f  where anulada=false and "
                + "  f.ncf=:ncf  limit 1 ")
                .addEntity(Factura.class)
                .setParameter("ncf", ncf).uniqueResult();

        if (!(fact==null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }
    
      public boolean existeSecuencia(int numero) {

        boolean flag = false;

        Conduce conduce = (Conduce)session.createSQLQuery(" SELECT * FROM conduce f  where anulado=false and "
                + "  f.numero=:numero limit 1 ")
                .addEntity(Conduce.class)
                .setParameter("numero", numero).uniqueResult();

        if (!(conduce==null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(numero)+1,0) from conduce ";

        return (BigInteger) session.createSQLQuery(query).uniqueResult();

    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getMontoPendiente(ManejoCliente.getInstancia().getCliente(2)));
//        System.out.println("Datos " + getInstancia().existeSecuencia(1000));
        System.out.println("Datos " + getInstancia().existeNCF("b02"));
    }

}
