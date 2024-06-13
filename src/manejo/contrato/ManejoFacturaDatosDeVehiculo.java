/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contrato;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.ContratoDeServicio;
import modelo.DatosDeVehiculo;
import modelo.DetalleContratoDeServicio;
import modelo.FacturaDatosDeVehiculo;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoFacturaDatosDeVehiculo extends ManejoEstandar<FacturaDatosDeVehiculo> {

    private static ManejoFacturaDatosDeVehiculo manejoFacturaDatosDeVehiculo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoFacturaDatosDeVehiculo getInstancia() {
        if (manejoFacturaDatosDeVehiculo == null) {
            manejoFacturaDatosDeVehiculo = new ManejoFacturaDatosDeVehiculo();
        }
        return manejoFacturaDatosDeVehiculo;
    }

    public List<DatosDeVehiculo> getLista() {

        String query = " SELECT * FROM contrato_de_servicio  ";

        return session.createSQLQuery(query).addEntity(DatosDeVehiculo.class).list();

    }

    public List<DatosDeVehiculo> getDatosDeVehiculo(int servicio) {

        List<DatosDeVehiculo> lista;
        String query = "SELECT * FROM datos_de_vehiculo where  servicio=:servicio ";

        lista = session.createSQLQuery(query)
                .addEntity(DatosDeVehiculo.class).setParameter("servicio", servicio).list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoHabilitado(int contrato) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio where  contrato_de_servicio=:contrato and habilitado=true";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class).setParameter("contrato", contrato).list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoVencido(int contrato, Date fechaCorte) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio where  contrato_de_servicio=:contrato and  habilitado=true "
                + " and fecha_ultimo_pago_hasta<='" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'";

        System.out.println("query " + query);

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class).setParameter("contrato", contrato).list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoVencido(int contrato) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio where  contrato_de_servicio=:contrato and  habilitado=true "
                + " and fecha_ultimo_pago_hasta is null ";

        System.out.println("query " + query);

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class).setParameter("contrato", contrato).list();

        return lista;
    }

    public List<DatosDeVehiculo> getDatosVehiculoo(int servicio) {

        List<DatosDeVehiculo> lista;
        String query = "SELECT * FROM datos_de_vehiculo where  servicio=:servicio ";

        lista = session.createSQLQuery(query)
                .addEntity(DatosDeVehiculo.class).setParameter("servicio", servicio).list();

        return lista;
    }

    public ContratoDeServicio getContrato(int codigo) {

        ContratoDeServicio factura = null;

        String query = "select * From contrato_de_servicio c where c.codigo=:codigo ";

        System.out.println("Query " + query);

        factura = (ContratoDeServicio) getSession().createSQLQuery(query).addEntity(ContratoDeServicio.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

        return factura;
    }

    public List<ContratoDeServicio> getLista(Date fechaini, Date fechafin) {

        String query = "SELECT * FROM contrato_de_servicio  where  fecha between '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaini) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "'";
        System.out.println("Query " + query);

        return session.createSQLQuery(query).addEntity(ContratoDeServicio.class).list();

    }

    public List<ContratoDeServicio> getLista(Date fecha) {

        String query = "SELECT * FROM contrato_de_servicio  where anulado=false and fecha='" + new SimpleDateFormat("yyyy-MM-dd").format(fecha) + "'";
        System.out.println("Query " + query);

        return session.createSQLQuery(query).addEntity(ContratoDeServicio.class).list();

    }

    public List<ContratoDeServicio> getListaContratoSinFaturar() {

        String query = "  SELECT * FROM contrato_de_servicio ct,detalle_contrato_de_servicio det \n"
                + "  \n"
                + " where  ct.codigo=det.contrato_de_servicio \n"
                + "and anulado=false  and  estado=1    \n"
                + " and  fecha_ultimo_pago_hasta is null ";

        return session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .list();

    }

    public List<ContratoDeServicio> getContratoVencido(Date fechaCorte) {

//        Date fechaPrimerdiaMes = util.ClaseUtil.getFechaPrimerDiasDelMes(fechaCorte);
        String query = "  SELECT * FROM contrato_de_servicio ct,detalle_contrato_de_servicio det \n"
                + "  \n"
                + " where  ct.codigo=det.contrato_de_servicio \n"
                + "and anulado=false  and  estado=1    \n"
                + " and fecha<'" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'"
                + "  and fecha_ultimo_pago_hasta<='" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'";

        System.out.println("sql " + query);

        return session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .list();

    }

    public List<ContratoDeServicio> getContratoPorVencer() {

//        Date fechaPrimerdiaMes = util.ClaseUtil.getFechaPrimerDiasDelMes(fechaCorte);
        String query = "  SELECT * FROM contrato_de_servicio ct,detalle_contrato_de_servicio det \n"
                + "  \n"
                + " where  ct.codigo=det.contrato_de_servicio \n"
                + "and anulado=false  and  estado=1  and DATEDIFF(NOW(),fecha_ultimo_pago_hasta)=cuandia_antes_de_vencer ";

        System.out.println("sql " + query);

        return session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .list();

    }

    public List<DetalleContratoDeServicio> getContratoPorVencerDetalle() {

        String query = "  SELECT * FROM contrato_de_servicio ct,detalle_contrato_de_servicio det \n"
                + "  \n"
                + " where  ct.codigo=det.contrato_de_servicio \n"
                + "and anulado=false  and  estado=1 and det.habilitado=true  and DATEDIFF(fecha_ultimo_pago_hasta,NOW())=cuandia_antes_de_vencer ";

        System.out.println("sql " + query);

        return session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class)
                .list();

    }

    public List<ContratoDeServicio> getListaContrato(Date fechaCorte) {

//        Date fechaPrimerdiaMes = util.ClaseUtil.getFechaPrimerDiasDelMes(fechaCorte);
        String query = " SELECT * FROM contrato_de_servicio ct \n"
                + " where  anulado=false  and  estado=1  and  "
                + "  ct.codigo  not in "
                + "( SELECT det.contrato  from\n"
                + "corte_de_facturacion ct,\n"
                + " detalle_corte_de_facturacion \n"
                + "det \n"
                + "where ct.codigo=det.corte_de_facturacion  \n"
                + "and fecha=:fecha "
                + "\n"
                + ") and fecha<'" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'";

//        String query = " SELECT * FROM contrato_de_servicio ct,\n" +
//                  " detalle_contrato_de_servicio dtc "
//                   + " where "
//                  + "  ct.codigo=dtc.contrato_de_servicio "
//                + " and anulado=false  and  estado=1  and  "
//                + "  ct.codigo  not in "               
//                + "( SELECT det.contrato  from\n"
//                + "corte_de_facturacion ct,\n"
//                + " detalle_corte_de_facturacion \n"
//                + "det \n"
//                + "where ct.codigo=det.corte_de_facturacion   \n"
//                + "and fecha=:fecha "
//                + "\n"
//                + ") and  DAY(dtc.fecha_hasta) between DAY('"+new SimpleDateFormat("yyyy-MM-dd")
//                        .format(fechaPrimerdiaMes)+"')"+" and day('"+new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte)+"')"
//                + " ";
        System.out.println("sql " + query);

        return session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .setParameter("fecha", new SimpleDateFormat("yyyy/MM/dd").format(fechaCorte))
                .list();

    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(numero)+1,0) from contrato_de_servicio ";

        return (BigInteger) session.createSQLQuery(query).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return FacturaDatosDeVehiculo.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().getContratoPorVencer().get(0).getNumero());
    }

}
