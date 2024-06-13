/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.ReciboIngreso;

import dto.cobro.ReciboIngresoDTO;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DetalleReciboIngreso;
import modelo.ReciboIngreso;

//import modelo.Usuariop;
import org.hibernate.Session;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoReciboIngreso extends ManejoEstandar<ReciboIngreso> {

    private static ManejoReciboIngreso manejoPago = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoReciboIngreso getInstancia() {

        if (manejoPago == null) {
            manejoPago = new ManejoReciboIngreso();
        }
        return manejoPago;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<ReciboIngreso> getReciboIngreso() {

        String query = "SELECT * FROM  recibo_ingreso  "
                + " where  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query)
                .addEntity(ReciboIngreso.class).list();

    }

    public ReciboIngreso getReciboIngreso(int recibo) {

        String query = "SELECT * FROM  recibo_ingreso  "
                + "   where codigo=:recibo and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return (ReciboIngreso) session.createSQLQuery(query)
                .addEntity(ReciboIngreso.class)
                .setParameter("recibo", recibo)
                .uniqueResult();

    }

    public List<ReciboIngreso> getReciboPorFecha(Date fechaIni, Date fechaFin) {

        String query = "SELECT * FROM  recibo_ingreso r  where  fecha  "
                + "  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaIni) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaFin) + "' "
                + " and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(ReciboIngreso.class).list();

    }

    public List<ReciboIngresoDTO> getReciboPorFechaDTO(Date fechaIni, Date fechaFin) {

        Statement stmt = null;
        ResultSet resultSet = null;
        List<ReciboIngresoDTO> listaDto = new ArrayList<>();

        ReciboIngresoDTO reci = new ReciboIngresoDTO();

        String query = " SELECT \n"
                + "\n"
                + "ri.codigo,\n"
                + "  ri.fecha,ri.numero,\n"
                + "  c.nombre,c.codigo as cod_cliente,\n"
                + "  ri.tipo_recibo,\n"
                + "\n"
                + "case when ri.tipo_recibo='AV' THEN   ri.monto else 0 end as monto_avance,\n"
                + " \n"
                + "( SELECT IFNULL(sum(monto_pagado),0) from detalle_recibo_ingreso dr where dr.recibo=ri.codigo ) as cobrado,\n"
                + "\n"
                + "round(( SELECT IFNULL(sum(dr.total),0) from detalle_recibo_ingreso dr where dr.recibo=ri.codigo )-\n"
                + "( SELECT IFNULL(sum(monto_pagado),0) from detalle_recibo_ingreso dr where dr.recibo=ri.codigo ),2)\n"
                + " as pago_con_avance,\n"
                + "\n"
                + "( SELECT IFNULL(sum(dr.total),0) from detalle_recibo_ingreso dr where dr.recibo=ri.codigo ) as total,concepto\n"
                + "\n"
                + " from recibo_ingreso ri \n"
                + "  INNER JOIN cliente c\n"
                + "  on  c.codigo=ri.cliente \n"
                + "\n"
                + "  where   ri.anulado=false  and ri.fecha  \n"
                + "  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaIni) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaFin) + "' "
                + " and ri.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        try {

            stmt = Conexion.getInsatancia().getConnectionDB().createStatement();

            resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {

                reci.setFecha(resultSet.getString("fecha"));
                reci.setCliente(resultSet.getString("nombre"));
                reci.setNumero(resultSet.getString("numero"));
                reci.setTipoRecibo(resultSet.getString("tipo_recibo"));
                reci.setMontoAvance(resultSet.getDouble("monto_avance"));
                reci.setMontoCobrado(resultSet.getDouble("cobrado"));
                reci.setPagoConAvance(resultSet.getDouble("pago_con_avance"));
                reci.setTotal(reci.getMontoAvance() + reci.getMontoCobrado());
                reci.setConcepto(resultSet.getString("concepto"));

                listaDto.add(reci);
                reci = new ReciboIngresoDTO();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManejoReciboIngreso.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaDto;
    }

    public List<ReciboIngreso> getReciboPorCliente(int cliente) {

        String query = "SELECT * FROM  recibo_ingreso r  where cliente=:cliente";

        return session.createSQLQuery(query).addEntity(ReciboIngreso.class).setParameter("cliente", cliente).list();

    }

    public List<ReciboIngreso> getReciboAvancePorCliente(int cliente) {

        String query = " select * from recibo_ingreso r  "
                + "  where r.anulado=false and  tipo_recibo='AV' "
                + "   and  round(r.monto-FN_MONTO_PAGADO_RECONCILIACION_RECIBO(r.codigo) ,2)>0 "
                + "  and r.cliente=:cliente  ";

//
//        String query = " SELECT *  FROM  recibo_ingreso r  where anulado=false and  tipo_recibo='AV' and \n"
//                + "  r.codigo not in "
//                + " ( SELECT drci.documento FROM  reconciliacion_interna_cliente rci \n"
//                + "  inner join  detalle_reconciliacion_interna_cliente drci on drci.reconciliacion=rci.codigo "
//                + "   and  rci.anulada=false  where drci.tipo_documento=8 ) "
//                + "   and cliente=:cliente ";
//        String query = " SELECT  *  FROM  recibo_ingreso r  where "
//                + "               round(r.monto-("
//                + "                (  SELECT  ifnull(sum(debito),0) as monto"
//                + "                 FROM  reconciliacion_interna_cliente rci\n"
//                + "                 inner join  detalle_reconciliacion_interna_cliente drci on drci.reconciliacion=rci.codigo \n"
//                + "                   and  rci.anulada=false  and tipo_documento=8 \n"
//                + "                   \n"
//                + "                   where  r.codigo=drci.documento  \n"
//                + "                   \n"
//                + "                   \n"
//                + "                   ) ),2)>0  and anulado=false  and  tipo_recibo='AV'   "
//                + "   and cliente=:cliente  ";
        return session.createSQLQuery(query)
                .addEntity(ReciboIngreso.class)
                .setParameter("cliente", cliente)
                .list();

    }

    public Double avancePendiente(int recibo) {

        String query = " SELECT round(r.monto-(SELECT  ifnull(sum(monto_recibo),0) as monto FROM  reconciliacion_interna_cliente rci  inner join "
                + "    detalle_reconciliacion_interna_cliente drci on drci.reconciliacion=rci.codigo  and  rci.anulada=false "
                + "    where r.anulado=drci.recibo ),2) as pendiente  FROM  recibo_ingreso r  where   anulado=false and  tipo_recibo='AV' and codigo=:recibo ";

        return (Double) session.createSQLQuery(query)
                .setParameter("recibo", recibo)
                .uniqueResult();

    }

    public List<DetalleReciboIngreso> getDetalleRecibo(int recibo) {

        String query = "SELECT * FROM  detalle_recibo_ingreso r  where r.recibo=:recibo ";

        return session.createSQLQuery(query).addEntity(DetalleReciboIngreso.class).setParameter("recibo", recibo).list();

    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(numero)+1,0) from recibo_ingreso ";

        return (BigInteger) session.createSQLQuery(query).uniqueResult();

    }

    public Double getTotalAbono(int factura) {

        String query = " SELECT sum(avance) as montoAvance FROM detalle_avance_de_cliente  where avance_de_cliente=:avance  ";

        return (Double) session.createSQLQuery(query).setParameter("avance", factura).uniqueResult();

    }

    public Double getAvanceUsado(int recibo) {

        String query = " SELECT FN_MONTO_PAGADO_RECONCILIACION_RECIBO " + "(" + recibo + ") as monto ";

        return (Double) session.createSQLQuery(query).uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return ReciboIngreso.class;
    }

    public static void main(String[] args) {

        System.out.println("avance pendiente " + getInstancia().avancePendiente(273));
//
//        Date fechaIni = new Date(), fechaFin = new Date();
//        List<ReciboIngresoDTO> lista = getInstancia().getReciboPorFechaDTO(fechaIni, fechaFin);
//
//        for (ReciboIngresoDTO reciboIngresoDTO : lista) {
//            System.out.println("recibo " + reciboIngresoDTO.getPagoConAvance());
//        }
    }

}
