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
import modelo.ComprobanteDePago;
import modelo.DetalleComprobanteDePago;

//import modelo.Usuariop;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoComprobanteDePago extends ManejoEstandar<ComprobanteDePago> {

    private static ManejoComprobanteDePago manejoPago = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoComprobanteDePago getInstancia() {

        if (manejoPago == null) {
            manejoPago = new ManejoComprobanteDePago();
        }
        return manejoPago;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<ComprobanteDePago> getReciboIngreso() {

        String query = "SELECT * FROM  comprobante_de_pago  where  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(ComprobanteDePago.class).list();

    }

    public List<ComprobanteDePago> getReciboPorFecha(Date fechaIni, Date fechaFin) {

        String query = "SELECT * FROM  comprobante_de_pago r  where   unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()
                + " and  fecha between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaIni) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaFin) + "'";

        return session.createSQLQuery(query).addEntity(ComprobanteDePago.class).list();

    }

    public List<ComprobanteDePago> getReciboPorCliente(int suplidor) {

        String query = "SELECT * FROM  comprobante_de_pago r  where suplidor=:suplidor  and  "
                + " unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(ComprobanteDePago.class).setParameter("suplidor", suplidor).list();

    }

    public List<DetalleComprobanteDePago> getDetalleRecibo(int comprobante) {

        String query = "SELECT * FROM  detalle_comprobante_de_pago r  where r.comprobante_de_pago=:comprobante  ";
                

        return session.createSQLQuery(query).addEntity(DetalleComprobanteDePago.class).setParameter("comprobante", comprobante).list();

    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(numero)+1,0) from comprobante_de_pago  "
                + "  where unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return (BigInteger) session.createSQLQuery(query).uniqueResult();

    }

    public boolean existeSecuencia(int numero) {

        boolean flag = false;

        ComprobanteDePago comprb = (ComprobanteDePago) session.createSQLQuery(" SELECT * FROM comprobante_de_pago f  "
                + "  where anulado=false and unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()
                + " and  f.numero=:numero limit 1 ")
                .addEntity(ComprobanteDePago.class)
                .setParameter("numero", numero).uniqueResult();

        if (!(comprb == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    @Override
    public Class getReferencia() {
        return ComprobanteDePago.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos "+getInstancia().getPrestamoPorEstado(1).get(0).getCliente().getNombre());
    }

}
