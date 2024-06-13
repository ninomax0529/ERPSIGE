/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cxp.avanceCliente;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.AvanceDeCliente;
import modelo.DetalleAvanceDeCliente;
import org.hibernate.Session;
import utiles.ClaseUtil;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoAvanceDeCliente extends ManejoEstandar<AvanceDeCliente> {

    private static ManejoAvanceDeCliente manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoAvanceDeCliente getInstancia() {
        if (manejo == null) {
            manejo = new ManejoAvanceDeCliente();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<AvanceDeCliente> getLista() {

        String query = " SELECT * FROM avance_de_cliente  ";

        return session.createSQLQuery(query).addEntity(AvanceDeCliente.class).list();

    }

    public List<AvanceDeCliente> getLista(Date fi, Date ff) {

        String query = " SELECT * FROM avance_de_cliente  where  date(fecha) "
                + "  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' ";

        return session.createSQLQuery(query).addEntity(AvanceDeCliente.class).list();

    }

    public AvanceDeCliente getAvanceCliente(int cliente) {

        String query = " SELECT * FROM avance_de_cliente  where cliente=:cliente ";

        return (AvanceDeCliente) session.createSQLQuery(query)
                .addEntity(AvanceDeCliente.class)
                .setParameter("cliente", cliente)
                .uniqueResult();

    }

    public List<DetalleAvanceDeCliente> getDetalleAvance(int avance) {

        String query = " SELECT * FROM detalle_avance_de_cliente  where avance_de_cliente=:avance  ";

        return session.createSQLQuery(query).addEntity(DetalleAvanceDeCliente.class).setParameter("avance", avance).list();

    }

    public Double getTotalAvance(int avance) {

        String query = " SELECT sum(avance) as montoAvance FROM detalle_avance_de_cliente  where avance_de_cliente=:avance  ";

        return (Double) session.createSQLQuery(query).setParameter("avance", avance).uniqueResult();

    }

    public Double getMontoDisponibleVat(int cliente) {

        Double monto = 0.00;
        String query = "  SELECT ifnull(total_pendiente,0) as montoDisponible FROM  avance_de_cliente av  where  cliente=:cliente ";

        monto = (Double) session.createSQLQuery(query).setParameter("cliente", cliente).uniqueResult();

        System.out.println("monto " + monto);
        return monto == null ? 0.00 : monto;
    }

    public Double getMontoDisponible(int cliente) {

        Double montoDisponible = 0.00, montoAvanve = 0.00, pagoConAvance = 0.00;

        montoAvanve = getTotalReciboAvance(cliente);
        pagoConAvance = getTotalPagadoConavance(cliente);

        montoDisponible = montoAvanve - pagoConAvance;
        System.out.println("monto " + montoDisponible);
        return ClaseUtil.roundDouble(montoDisponible, 2);
    }

    public Double getAvanceASuplidor(int suplidor) {

        Double montoDisponible = 0.00, montoAvanve = 0.00, pagoConAvance = 0.00;

        montoAvanve = getTotalReciboAvance(suplidor);
        pagoConAvance = getTotalPagadoConavance(suplidor);

        montoDisponible = montoAvanve - pagoConAvance;
        System.out.println("monto " + montoDisponible);
        return ClaseUtil.roundDouble(montoDisponible, 2);
    }

    public Double getTotalAvanve(int cliente) {

        Double monto = 0.00;
        String query = " SELECT  sum(ifnull(dri.monto_avance,0)) as monto from recibo_ingreso rei \n "
                + "    INNER JOIN detalle_recibo_ingreso dri on rei.codigo=dri.recibo \n"
                + "    where  rei.anulado=false and  rei.cliente=:cliente ";

        monto = (Double) session.createSQLQuery(query).setParameter("cliente", cliente).uniqueResult();

        return monto == null ? 0.00 : monto;
    }

    public Double getTotalReciboAvance(int cliente) {

        Double monto = 0.00;
        String query = "  SELECT SUM(monto) as avance from recibo_ingreso rei"
                + "   where   rei.anulado=false  and tipo_recibo='AV'  and  rei.cliente=:cliente ";

        monto = (Double) session.createSQLQuery(query).setParameter("cliente", cliente).uniqueResult();

        return monto == null ? 0.00 : monto;
    }

    public Double getPagoConAvanceSuplidor(int suplidor) {

        Double monto = 0.00;
        String query = "  SELECT SUM(monto) as avance from comprobante_de_pago rei"
                + "   where   rei.anulado=false  and tipo_comprobante='AV'  and  rei.suplidor=:suplidor ";

        monto = (Double) session.createSQLQuery(query)
                .setParameter("suplidor", suplidor)
                .uniqueResult();

        return monto == null ? 0.00 : monto;
    }

    public Double getTotalPagadoConavance(int cliente) {

        Double monto = 0.00;
        String query = " SELECT  sum(ifnull(dri.pago_con_avance,0)) as monto from recibo_ingreso rei \n "
                + "    INNER JOIN detalle_recibo_ingreso dri on rei.codigo=dri.recibo \n"
                + "    where  rei.anulado=false and  rei.cliente=:cliente ";

        monto = (Double) session.createSQLQuery(query).setParameter("cliente", cliente).uniqueResult();

        return monto == null ? 0.00 : monto;
    }

    @Override
    public Class getReferencia() {
        return AvanceDeCliente.class;
    }

    public static void main(String[] args) {

        System.out.println("getMontoDisponible  " + getInstancia().getMontoDisponible(342));
//        System.out.println("Datos " + getInstancia().getMontoDisponible(ManejoCliente.getInstancia().getCliente(3)));
    }

}
