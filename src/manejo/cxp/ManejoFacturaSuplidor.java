/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cxp;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Cliente;
import modelo.DetalleFacturaSuplidor;
import modelo.Factura;
import modelo.FacturaSuplidor;
import modelo.Suplidor;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoFacturaSuplidor extends ManejoEstandar<FacturaSuplidor> {

    private static ManejoFacturaSuplidor manejoFacturaSuplidor = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoFacturaSuplidor getInstancia() {
        if (manejoFacturaSuplidor == null) {
            manejoFacturaSuplidor = new ManejoFacturaSuplidor();
        }
        return manejoFacturaSuplidor;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<FacturaSuplidor> getLista() {

        String query = " SELECT * FROM factura_suplidor  where  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(FacturaSuplidor.class).list();

    }

    public List<FacturaSuplidor> getLista(Suplidor suplidor) {

        String query = " SELECT * FROM factura_suplidor  "
                + "  where anulada=false and  suplidor_cxp=:suplidor  and"
                + "  pendiente>0 and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(FacturaSuplidor.class)
                .setParameter("suplidor", suplidor.getCodigo()).list();

    }

    public List<FacturaSuplidor> getFacturaPendiente(Suplidor suplidor) {

        String query = "   SELECT * from ( SELECT f.*,\n"
                + "                f.total-(SELECT IFNULL(sum(total),0) from comprobante_de_pago ri INNER JOIN detalle_comprobante_de_pago dri \n"
                + "                on ri.codigo=dri.comprobante_de_pago where ri.anulado=false and  factura=f.codigo  ) as pendienteFact\n"
                + "                \n"
                + "                from suplidor s \n"
                + "                 INNER  JOIN factura_suplidor f  on  s.codigo=f.suplidor_cxp\n"
                + "                  where  f.anulada=false  \n"
                + "                 and s.codigo=:suplidor \n"
                + "                  )  as r  where   round(r.pendienteFact,2)>0  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(FacturaSuplidor.class).setParameter("suplidor", suplidor.getCodigo()).list();

    }

    public List<FacturaSuplidor> getFacturaPendienteSinNotaDeCredito(Suplidor suplidor) {

        String query = "   SELECT * from ( SELECT f.*,\n"
                + "                f.total-(SELECT IFNULL(sum(total),0) from comprobante_de_pago ri INNER JOIN detalle_comprobante_de_pago dri \n"
                + "                on ri.codigo=dri.comprobante_de_pago where ri.anulado=false and  factura=f.codigo  ) as pendienteFact\n"
                + "                \n"
                + "                from suplidor s \n"
                + "                 INNER  JOIN factura_suplidor f  on  s.codigo=f.suplidor_cxp\n"
                + "                  where  f.anulada=false and f.codigo not in ( SELECT factura FROM nota_credito   "
                + "  where  anulada=false  and tipo_nc=2 and tipo_documento=12 )  \n"
                + "                 and s.codigo=:suplidor \n"
                + "                  )  as r  where   round(r.pendienteFact,2)>0  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("query : " + query);
        return session.createSQLQuery(query).addEntity(FacturaSuplidor.class).setParameter("suplidor", suplidor.getCodigo()).list();

    }

    public List<FacturaSuplidor> getFacturaPendienteSinNotaDeDebito(Suplidor suplidor) {

        String query = "   SELECT * from ( SELECT f.*,\n"
                + "                f.total-(SELECT IFNULL(sum(total),0) from comprobante_de_pago ri INNER JOIN detalle_comprobante_de_pago dri \n"
                + "                on ri.codigo=dri.comprobante_de_pago where ri.anulado=false and  factura=f.codigo  ) as pendienteFact\n"
                + "                \n"
                + "                from suplidor s \n"
                + "                 INNER  JOIN factura_suplidor f on  s.codigo=f.suplidor_cxp \n"
                + "                  where  f.anulada=false and f.codigo not in ( SELECT factura FROM nota_debito   "
                + "  where  anulada=false  and tipo_nd=2 and tipo_documento=12 )  \n"
                + "                 and s.codigo=:suplidor \n"
                + "                  )  as r  where   round(r.pendienteFact,2)>0  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("query : " + query);
        return session.createSQLQuery(query).addEntity(FacturaSuplidor.class).setParameter("suplidor", suplidor.getCodigo()).list();

    }

    public Double getMontoPendiente(int factura) {

        Double montoPendiente = 0.00;

        String query = " SELECT FN_MONTO_PENDIENTE_FACT_SUPL " + "(" + factura + ") as monto ";

        System.out.println("Query " + query);

        montoPendiente = (Double) session.createSQLQuery(query)
                .uniqueResult();

        return montoPendiente == null ? 0.00 : montoPendiente;
    }

    public Double getMontoPendiente(Cliente cliente) {

        Double montoPendiente = 0.00;

        String query = " SELECT FN_BALANCE_PENDIENTE_CLIENTE " + "(" + cliente.getCodigo() + ") as monto ";

        System.out.println("Query " + query);

        montoPendiente = (Double) session.createSQLQuery(query)
                .uniqueResult();

        return montoPendiente == null ? 0.00 : montoPendiente;
    }

    public List<FacturaSuplidor> getLista(Date fechaDesde, Date fechaHasta) {

        String query = " SELECT * FROM factura_suplidor where anulada=false and  fecha "
                + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "'   and  ncf like 'B01%' "
                + "  and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(FacturaSuplidor.class).list();

    }

    public List<FacturaSuplidor> getListaFactura(Date fechaDesde, Date fechaHasta) {

        String query = " SELECT * FROM factura_suplidor where  fecha "
                + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "' "
                + "  and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(FacturaSuplidor.class).list();

    }

    public List<FacturaSuplidor> getLista(Date fechaDesde, Date fechaHasta, String tipoNcf) {

        String query = " SELECT * FROM factura_suplidor where anulada=false and  fecha "
                + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "'   and  ncf not like '" + tipoNcf + "%' "
                + "  and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(FacturaSuplidor.class).list();

    }

    public List<DetalleFacturaSuplidor> getDetalleFactura(int factura) {

        String query = " SELECT * FROM detalle_factura_suplidor  where  factura_suplidor=:factura  ";

        return session.createSQLQuery(query).addEntity(DetalleFacturaSuplidor.class).setParameter("factura", factura).list();

    }

    @Override
    public Class getReferencia() {
        return Factura.class;
    }

    public boolean validarNcf(String ncf) {

        boolean flag = false;

        String sql = " SELECT f.* FROM factura_suplidor f where anulada=false  "
                + " and unidad_de_negocio=:ung  and f.ncf=:ncf  limit 1 ";
        FacturaSuplidor fact = (FacturaSuplidor) session.createSQLQuery(sql)
                .addEntity(FacturaSuplidor.class)
                .setParameter("ncf", ncf.trim())
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .uniqueResult();

//        List<Factura> list = session.createQuery("SELECT * FROM factura_suplidor f where anulada=false  "
//                + " and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()
//                + "  and f.ncf=:ncf").setParameter("ncf", ncf).list();
        if (!(fact == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }
    
    

    public boolean validarNcf(String ncf, int ung) {

        boolean flag = false;

        String sql = " SELECT f.* FROM factura_suplidor f where anulada=false  "
                + " and unidad_de_negocio=:ung  and f.ncf=:ncf  limit 1 ";
        FacturaSuplidor fact = (FacturaSuplidor) session.createSQLQuery(sql)
                .addEntity(FacturaSuplidor.class)
                .setParameter("ncf", ncf.trim())
                .setParameter("ung", ung)
                .uniqueResult();

//        List<Factura> list = session.createQuery("SELECT * FROM factura_suplidor f where anulada=false  "
//                + " and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()
//                + "  and f.ncf=:ncf").setParameter("ncf", ncf).list();
        if (!(fact == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public boolean validarNcf(String ncf, int ung,int codsuplidor) {

        boolean flag = false;

        String sql = " SELECT f.* FROM factura_suplidor f where anulada=false  "
                + " and unidad_de_negocio=:ung  and f.ncf='"+ncf+"'"
                + " and suplidor=:supulidor  limit 1 ";
        FacturaSuplidor fact = (FacturaSuplidor) session.createSQLQuery(sql)
                .addEntity(FacturaSuplidor.class)
//                .setParameter("ncf", ncf.trim())
                .setParameter("ung", ung)
                .setParameter("supulidor", codsuplidor)
                .uniqueResult();

        System.out.println("sql verificar ncf "+sql);
        if (!(fact == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public boolean existeSecuencia(int numero) {

        boolean flag = false;

        FacturaSuplidor factura = (FacturaSuplidor) session.createSQLQuery(" SELECT * FROM  factura_suplidor ent  where anulada=false  and  "
                + "  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()
                + " and  ent.numero=:numero limit 1 ")
                .addEntity(FacturaSuplidor.class)
                .setParameter("numero", numero).uniqueResult();

        if (!(factura == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(codigo)+1,0) from factura_suplidor where unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return (BigInteger) session.createSQLQuery(query).uniqueResult();

    }

    public Boolean existeNcf(String numNcf) {

        boolean existe = false;
        String query = " select ncf from  factura_suplidor  where  anulada=false  and unidad_de_negocio=:ung and ncf=:ncf "
                + " limit 1 ";

        existe = (String) getSession()
                .createSQLQuery(query)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .uniqueResult() == null ? false : true;

        return existe;

    }

    public Double getMontoPagado(int codFact) {

        Double montoPendiente = 0.00;

        String query = " SELECT FN_MONTO_PAGADO_FACT_SUPLIDOR " + "(" + codFact + ") as monto ";

        System.out.println("Query " + query);

        montoPendiente = (Double) session.createSQLQuery(query)
                .uniqueResult();

        return montoPendiente == null ? 0.00 : montoPendiente;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().validarNcf("B1300000197"));
//        System.out.println("Datos " + getInstancia().getMontoDisponible(ManejoCliente.getInstancia().getCliente(3)));
    }

}
