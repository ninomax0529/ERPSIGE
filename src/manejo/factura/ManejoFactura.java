/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.factura;

import correo.ServicioDeCorreo;
import java.io.File;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import manejo.cliente.ManejoCreditoCliente;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.Factura;
import org.hibernate.Session;
import reporte.factura.RptFacturaIghTrack;
import util.FormatNum;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoFactura extends ManejoEstandar<Factura> {

    private static ManejoFactura manejoFactura = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoFactura getInstancia() {
        if (manejoFactura == null) {
            manejoFactura = new ManejoFactura();
        }
        return manejoFactura;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public Factura getFactura(int codigo) {

        Factura factura = null;

        String query = "select * From factura f where f.codigo=:codigo ";

        System.out.println("Query " + query);

        factura = (Factura) getSession().createSQLQuery(query).addEntity(Factura.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

        return factura;
    }

    public List<Factura> getLista() {

        String query = " SELECT * FROM factura  where  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(Factura.class).list();

    }

    public List<Factura> getListaFactura(Date fecha) {

        String query = " SELECT * FROM  factura  where  fecha=:fecha ";
//        String query = " SELECT * FROM  factura  where  fecha=:fecha  and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query)
                .addEntity(Factura.class)
                .setParameter("fecha", fecha)
                .list();

    }

    public List<Factura> getFacturaPorEnviarPorCorreo() {

        String query = " SELECT * FROM  factura  where  enviada_por_correo=false and year(fecha)=year(now()) and  MONTH(fecha)=MONTH(now()) "
                + " and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query)
                .addEntity(Factura.class)
                .list();

    }

    public List<Factura> getFacturaPorEnviarPorCorreo(Date fechaDesde, Date fechaHasta) {

        String query = " SELECT * FROM factura where   fecha "
                + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' "
                + "and '" + new SimpleDateFormat("yyyy-MM-dd")
                        .format(fechaHasta) + "' and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(Factura.class).list();

//        String query = " SELECT * FROM  factura  where  enviada_por_correo=false and year(fecha)=year(now()) and  MONTH(fecha)=MONTH(now()) "
//                + " and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
//
//        return session.createSQLQuery(query)
//                .addEntity(Factura.class)
//                .list();
    }

    public List<Factura> getListaFactParaDespachar() {

        String query = " SELECT * FROM factura  where despachada=false  "
                + " and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(Factura.class).list();

    }

    public List<Factura> getLista(Cliente cliente) {

        String query = " SELECT * FROM factura  "
                + " where anulada=false and  cliente=:cliente and"
                + "  pendiente>0 and pagada=false and tipo_venta=2  "
                + "  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(Factura.class).setParameter("cliente", cliente.getCodigo()).list();

    }

    public List<Factura> getFacturaPendiente(Cliente cliente) {

        String query = " SELECT * FROM factura  where anulada=false and  "
                + " cliente=:cliente and pendiente>0 and pagada=false and tipo_venta=2  "
                + "  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(Factura.class).setParameter("cliente", cliente.getCodigo()).list();

    }

    public List<Factura> getListaFacturaPendiente(Cliente cliente) {

        String query = " SELECT  f.* from cliente c  INNER  JOIN factura f  on  c.codigo=f.cliente "
                + "  where  f.tipo_venta=2  and f.anulada=false  and c.codigo=:cliente  "
                + "  and  round(f.total-FN_MONTO_PAGADO(f.codigo) ,2)>0 "
                + "  and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
        
            System.out.println("query FACTURA : " + query);
             return session.createSQLQuery(query)
                     .addEntity(Factura.class)
                     .setParameter("cliente", cliente.getCodigo())
                      .list();


//        String query = " SELECT * from ( SELECT f.*,"
//                + " f.total-(SELECT IFNULL(sum(total),0) from recibo_ingreso ri INNER JOIN detalle_recibo_ingreso dri \n"
//                + "on ri.codigo=dri.recibo  where ri.anulado=false and  factura=f.codigo  ) "
//                + "-( SELECT IFNULL(sum(monto),0) from nota_credito nc \n"
//                + "  where nc.anulada=false and  nc.factura=f.codigo  and nc.tipo_nc=1 and tipo_documento=7 ) as pendienteFact \n"
//                + " from cliente c \n"
//                + " INNER  JOIN factura f  on  c.codigo=f.cliente \n"
//                + "  where    f.tipo_venta=2 \n"
//                + "    and f.anulada=false  \n"
//                + "  and c.codigo=:cliente "
//                + "  )  as r  where   round(r.pendienteFact,2)>0  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
      
    }

    public List<Factura> getListaFacturaPendienteSinNotaCredito(Cliente cliente) {

        String query = " SELECT * from (SELECT f.*, f.total-( SELECT IFNULL(sum(total),0) from recibo_ingreso ri INNER JOIN detalle_recibo_ingreso dri \n"
                + "on ri.codigo=dri.recibo  where ri.anulado=false and  factura=f.codigo  ) as pendienteFact \n"
                + " from cliente c \n"
                + " INNER  JOIN factura f  on  c.codigo=f.cliente \n"
                + "  where  f.codigo not in ( SELECT factura FROM nota_credito   where  anulada=false  and tipo_nc=1 and tipo_documento=7 )   \n"
                + "    and f.tipo_venta=2 \n"
                + "    and f.anulada=false  \n"
                + "  and c.codigo=:cliente  )  as r  where  round(r.pendienteFact,2)>0  "
                + "   and   unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("query : " + query);
        return session.createSQLQuery(query).addEntity(Factura.class).setParameter("cliente", cliente.getCodigo()).list();

    }

    public List<Factura> getListaFacturaPendienteSinNotaDeDebito(Cliente cliente) {

        String query = " SELECT * from ( \n"
                + " SELECT f.*, f.total-( SELECT IFNULL(sum(total),0) from recibo_ingreso ri INNER JOIN detalle_recibo_ingreso dri \n"
                + "on ri.codigo=dri.recibo  where ri.anulado=false and  factura=f.codigo  ) as pendienteFact\n"
                + " from cliente c \n"
                + " INNER  JOIN factura f  on  c.codigo=f.cliente \n"
                + "  where  f.codigo not in ( SELECT factura FROM nota_debito   where  anulada=false  and tipo_nd=1 and tipo_documento=7 )   \n"
                + "    and f.tipo_venta=2 \n"
                + "    and f.anulada=false  \n"
                + "  and c.codigo=:cliente  )  as r  where  round(r.pendienteFact,2)>0  "
                + "   and   unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("query : " + query);
        return session.createSQLQuery(query).addEntity(Factura.class).setParameter("cliente", cliente.getCodigo()).list();

    }

    public List<Factura> getFacturaPendiente() {

        String query = " SELECT * FROM factura  where anulada=false  and pendiente>0 and pagada=false and tipo_venta=2  "
                + "  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query)
                .addEntity(Factura.class).list();

    }

    public Double getMontoPendiente(Cliente cliente) {

        Double montoPendiente = 0.00;

        String query = " SELECT FN_BALANCE_PENDIENTE_CLIENTE " + "(" + cliente.getCodigo() + ") as monto ";

        System.out.println("Query " + query);

        montoPendiente = (Double) session.createSQLQuery(query)
                .uniqueResult();

        return montoPendiente == null ? 0.00 : montoPendiente;
    }

    public Double getMontoDeuda(int cliente) {

        Double montoPendiente = 0.00;

        String query = " SELECT FN_BALANCE_PENDIENTE_CLIENTE " + "(" + cliente + ") as monto ";

        System.out.println("Query " + query);

        montoPendiente = (Double) session.createSQLQuery(query)
                .uniqueResult();

        return montoPendiente == null ? 0.00 : montoPendiente;
    }
    
    
    public Double getMontoDeudaEntreFecha(int cliente,Date fi,Date ff) {

        Double montoPendiente = 0.00;

        String query = " SELECT FN_BALANCE_PENDIENTE_CLIENTE_ENTRE_FECHA " + "(" + cliente+",'" 
                + new SimpleDateFormat("yyyy-MM-dd").format(fi)+"','"
                +  new SimpleDateFormat("yyyy-MM-dd").format(ff)+"'"
                + ") as monto ";

        System.out.println("Query " + query);

        montoPendiente = (Double) session.createSQLQuery(query)
                .uniqueResult();

        return montoPendiente == null ? 0.00 : montoPendiente;
    }

    public Double getMontoPagado(int codFact) {

        Double montoPendiente = 0.00;

        String query = " SELECT FN_MONTO_PAGADO " + "(" + codFact + ") as monto ";

        System.out.println("Query " + query);

        montoPendiente = (Double) session.createSQLQuery(query)
                .uniqueResult();

        return montoPendiente == null ? 0.00 : montoPendiente;
    }

    public Double getMontoPendiente(int factura) {

        Double montoPendiente = 0.00;

        String query = " SELECT FN_MONTO_PENDIENTE " + "(" + factura + ") as monto ";

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

    public List<Factura> getLista(Date fechaDesde, Date fechaHasta) {

        String query = " SELECT * FROM factura where   fecha "
                + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' "
                + " and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "' and  unidad_de_negocio="
                + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(Factura.class).list();

    }

    public List<Factura> getListaFacturaPendiente(Date fechaDesde, Date fechaHasta) {

        String query = "  SELECT \n"
                + "    f.nombre_cliente,f.ncf,f.fecha,f.total,f.abono,f.pendiente,f.numero\n"
                + "      , DATEDIFF(NOW(),f.fecha_vencimiento) as dias_vencimiento\n"
                + "   from factura f \n"
                + "\n"
                + "   where  f.anulada=false and   f.pendiente>0   and "
                + " fecha  between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' "
                + "and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "'"
                + "  and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(Factura.class).list();
    }

    public List<Factura> getListaFacturaDeContrato(Date fechaDesde, Date fechaHasta) {

        String query = " SELECT * FROM factura where numero_contrato is not null and  fecha "
                + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' "
                + "and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "'"
                + "  and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(Factura.class).list();

    }

    public List<Factura> getFacturaDeContrato(Date fechaDesde, Date fechaHasta) {

        String query = "  SELECT \n"
                + "    f.nombre_cliente,f.ncf,f.fecha,f.total,f.abono,f.pendiente,f.numero\n"
                + "      , DATEDIFF(NOW(),f.fecha_vencimiento) as dias_vencimiento\n"
                + "   from factura f \n"
                + "\n"
                + "   where  f.anulada=false and   f.pendiente>0   and "
                + " fecha  between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' "
                + "and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "'"
                + "  and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(Factura.class).list();

    }

    public List<DetalleFactura> getDetalleFactura(int factura) {

        String query = " SELECT * FROM detalle_factura  where factura=:factura  ";

        return session.createSQLQuery(query).addEntity(DetalleFactura.class).setParameter("factura", factura).list();

    }

    public List<Factura> getLista607(Date fechaDesde, Date fechaHasta, String tipoNcf) {

        String query = " SELECT * FROM factura where anulada=false and  fecha "
                + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "'   and  ncf not like '" + tipoNcf + "%' "
                + "  and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(Factura.class).list();

    }

    @Override
    public Class getReferencia() {
        return Factura.class;
    }

    public boolean existeNCF(String ncf) {

        boolean flag = false;

        Factura fact = (Factura) session.createSQLQuery(" SELECT * FROM factura f  where anulada=false and "
                + "  f.ncf=:ncf  limit 1 ")
                //and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
                .addEntity(Factura.class)
                .setParameter("ncf", ncf).uniqueResult();

        if (!(fact == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public boolean existeNCF(String ncf, int ung) {

        boolean flag = false;

        Factura fact = (Factura) session.createSQLQuery(" SELECT * FROM factura f  where anulada=false and "
                + "  f.ncf=:ncf  and f.unidad_de_negocio=:ung  limit 1 ")
                //and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
                .addEntity(Factura.class)
                .setParameter("ncf", ncf.trim())
                .setParameter("ung", ung)
                .uniqueResult();

        if (!(fact == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public boolean existeSecuencia(int numero) {

        boolean flag = false;

        Factura factura = (Factura) session.createSQLQuery(" SELECT * FROM factura f  where anulada=false and "
                + "  f.numero=:numero limit 1 ")
                .addEntity(Factura.class)
                .setParameter("numero", numero).uniqueResult();

        if (!(factura == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public void enviarFacturaPorCorreoEnPdf(Date fecha) {

        ServicioDeCorreo sc = new ServicioDeCorreo();
        String rutaFactura = "", emaeil = "";

        try {

            List<Factura> lista = getListaFactura(fecha);

            for (Factura factura : lista) {

                if (factura.getCliente().getEmail() == null) {
                    factura.getCliente().setEmail("ninomax0529@gmail.com");
                }
                rutaFactura = RptFacturaIghTrack.getInstancia().exportarFacturaAPDF(factura);

                System.out.println("Factura num " + factura.getNumero() + " " + rutaFactura);

                sc.enviarCorreoFact("FACTURA DE IGHTRACK ",
                        "Del cliente " + factura.getCliente().getNombre(), factura.getCliente().getEmail(),
                        new File(rutaFactura));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(numero)+1,0) from factura  where unidad_de_negocio=:ung ";

        return (BigInteger) session.createSQLQuery(query)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .uniqueResult();

    }

    public void eliminarFacturaTemporal() {

        Connection cn = Conexion.getInsatancia().getConnectionDB();
        try {

            PreparedStatement pst = cn.prepareStatement(" delete from detalle_factura_temporal where codigo>0 ");

            System.out.println("eliminado detalle  " + pst.execute());

            pst = cn.prepareStatement(" delete from factura_temporal where codigo>0 ");
            System.out.println("eliminado header  " + pst.execute());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cn.close();
                System.out.println("conexion cerrada " + cn.isClosed());
            } catch (SQLException ex) {
                Logger.getLogger(ManejoFactura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getMontoPendiente(ManejoCliente.getInstancia().getCliente(2)));
//        System.out.println("Datos " + getInstancia().existeSecuencia(1000));
//        System.out.println("Datos " + );
//        getInstancia().enviarFacturaPorCorreoEnPdf(new Date("2022/05/20"));
        System.out.println("monto pagado : " + getInstancia().getMontoPendiente(220));
    }

}
