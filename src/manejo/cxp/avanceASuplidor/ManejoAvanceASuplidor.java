/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cxp.avanceASuplidor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.AvanceASuplidor;
import modelo.AvanceDeCliente;
import modelo.DetalleAvanceASuplidor;
import modelo.DetalleAvanceDeCliente;
import org.hibernate.Session;
import utiles.ClaseUtil;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoAvanceASuplidor extends ManejoEstandar<AvanceASuplidor> {

    private static ManejoAvanceASuplidor manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoAvanceASuplidor getInstancia() {
        if (manejo == null) {
            manejo = new ManejoAvanceASuplidor();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<AvanceASuplidor> getLista() {

        String query = " SELECT * FROM avance_a_suplidor  ";

        return session.createSQLQuery(query).addEntity(AvanceASuplidor.class).list();

    }

    public List<AvanceASuplidor> getLista(Date fi, Date ff) {

        String query = " SELECT * FROM avance_a_suplidor  where  date(fecha) "
                + "  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' ";

        return session.createSQLQuery(query).addEntity(AvanceASuplidor.class).list();

    }

    public AvanceASuplidor getAvanceASuplidore(int suplidor) {

        String query = " SELECT * FROM avance_a_suplidor  where  suplidor=:suplidor ";

        return (AvanceASuplidor) session.createSQLQuery(query)
                .addEntity(AvanceASuplidor.class)
                .setParameter("suplidor", suplidor)
                .uniqueResult();

    }

    public List<DetalleAvanceASuplidor> getDetalleAvance(int avance) {

        String query = " SELECT * FROM detalle_avance_a_suplidor  where avance_a_suplidor=:avance  ";

        return session.createSQLQuery(query).addEntity(DetalleAvanceASuplidor.class).setParameter("avance", avance).list();

    }

    public Double getTotalAvance(int avance) {

        String query = " SELECT sum(avance) as montoAvance FROM detalle_avance_a_suplidor  where avance_a_suplidor=:avance  ";

        return (Double) session.createSQLQuery(query).setParameter("avance", avance).uniqueResult();

    }

    public Double getTotalCredito(int avance) {

        String query = " SELECT sum(credito) as monto FROM detalle_avance_a_suplidor  where avance_a_suplidor=:avance  ";

        return (Double) session.createSQLQuery(query).setParameter("avance", avance).uniqueResult();

    }
    
     public Double getTotalDebito(int avance) {

        String query = " SELECT sum(debito) as monto FROM detalle_avance_a_suplidor  where avance_a_suplidor=:avance  ";

        return (Double) session.createSQLQuery(query).setParameter("avance", avance).uniqueResult();

    }

    public Double getMontoDisponibleVat(int suplidor) {

        Double monto = 0.00;
        String query = "  SELECT ifnull(total_pendiente,0) as montoDisponible FROM  avance_a_suplidor av  where  suplidor=:suplidor ";

        monto = (Double) session.createSQLQuery(query).setParameter("suplidor", suplidor).uniqueResult();

        System.out.println("monto " + monto);
        return monto == null ? 0.00 : monto;
    }

    public Double getMontoDisponible(int suplidor) {

        Double montoDisponible = 0.00, montoAvanve = 0.00, pagoConAvance = 0.00;

        montoAvanve = getTotalComprobanteAvance(suplidor);
        pagoConAvance = getTotalPagadoConavance(suplidor);

        montoDisponible = montoAvanve - pagoConAvance;
        System.out.println("monto " + montoDisponible + " pagoConAvance : " + pagoConAvance + " montoAvanve : " + montoAvanve);
        return ClaseUtil.roundDouble(montoDisponible, 2);
    }

    public Double getAvanceASuplidor(int suplidor) {

        Double montoDisponible = 0.00, montoAvanve = 0.00, pagoConAvance = 0.00;

        montoAvanve = getTotalComprobanteAvance(suplidor);
        pagoConAvance = getTotalPagadoConavance(suplidor);

        montoDisponible = montoAvanve - pagoConAvance;
        System.out.println("monto " + montoDisponible);
        return ClaseUtil.roundDouble(montoDisponible, 2);
    }

    public Double getTotalAvanve(int suplidor) {

        Double monto = 0.00;
        String query = " SELECT  sum(ifnull(dri.monto_avance,0)) as monto from comprobante_de_pago  rei \n "
                + "    INNER JOIN detalle_comprobante_de_pago dri on rei.codigo=dri.comprobante_de_pago  \n"
                + "    where  rei.anulado=false and  rei.suplidor=:suplidor ";

        monto = (Double) session.createSQLQuery(query).setParameter("suplidor", suplidor).uniqueResult();

        return monto == null ? 0.00 : monto;
    }

    public Double getTotalComprobanteAvance(int suplidor) {

        Double monto = 0.00;
        String query = "  SELECT SUM(monto) as avance from comprobante_de_pago rei"
                + "   where   rei.anulado=false  and tipo_comprobante='AV'  and  rei.suplidor=:suplidor ";

        monto = (Double) session.createSQLQuery(query).setParameter("suplidor", suplidor).uniqueResult();

        return monto == null ? 0.00 : monto;
    }

    public Double getPagoConAvanceSuplidor(int suplidor) {

        Double monto = 0.00;
        String query = "  SELECT SUM(monto) as avance from comprobante_de_pago rei "
                + "   where   rei.anulado=false  and tipo_comprobante='AV'  and  rei.suplidor=:suplidor ";

        monto = (Double) session.createSQLQuery(query)
                .setParameter("suplidor", suplidor)
                .uniqueResult();

        return monto == null ? 0.00 : monto;
    }

    public Double getTotalPagadoConavance(int suplidor) {

        Double monto = 0.00;
        String query = " SELECT  sum(ifnull(dri.pago_con_avance,0)) as monto from comprobante_de_pago rei \n "
                + "    INNER JOIN detalle_comprobante_de_pago dri on rei.codigo=dri.comprobante_de_pago  \n"
                + "    where  rei.anulado=false and  rei.suplidor=:suplidor ";

        monto = (Double) session.createSQLQuery(query).setParameter("suplidor", suplidor).uniqueResult();

        return monto == null ? 0.00 : monto;
    }

    @Override
    public Class getReferencia() {
        return AvanceDeCliente.class;
    }

    public static void main(String[] args) {

        System.out.println("getMontoDisponible  " + getInstancia().getMontoDisponible(3));
//        System.out.println("Datos " + getInstancia().getMontoDisponible(2));
    }

}
