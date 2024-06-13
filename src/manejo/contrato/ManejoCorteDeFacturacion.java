/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contrato;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.ContratoDeServicio;
import modelo.CorteDeFacturacion;
import modelo.DetalleCorteDeFacturacion;
import modelo.EstadoContrato;
import modelo.Factura;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoCorteDeFacturacion extends ManejoEstandar<CorteDeFacturacion> {

    private static ManejoCorteDeFacturacion manejoCorteDeFacturacion = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoCorteDeFacturacion getInstancia() {
        if (manejoCorteDeFacturacion == null) {
            manejoCorteDeFacturacion = new ManejoCorteDeFacturacion();
        }
        return manejoCorteDeFacturacion;
    }

    public List<CorteDeFacturacion> getLista() {

        String query = " SELECT * FROM corte_de_facturacion  ";

        return session.createSQLQuery(query).addEntity(CorteDeFacturacion.class).list();

    }

    public List<DetalleCorteDeFacturacion> getDetalleCorteDeFacturacion(int corte) {

        List<DetalleCorteDeFacturacion> lista;
        String query = "SELECT * FROM detalle_corte_de_facturacion  where corte_de_facturacion=:corte ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleCorteDeFacturacion.class).setParameter("corte", corte).list();

        return lista;
    }

    public CorteDeFacturacion getCorteDeFacturacion(int codigo) {

        CorteDeFacturacion corte = null;

        String query = "select * From contrato_de_servicio c where c.codigo=:codigo ";

        System.out.println("Query " + query);

        corte = (CorteDeFacturacion) getSession().createSQLQuery(query).addEntity(CorteDeFacturacion.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

        return corte;
    }

    public List<ContratoDeServicio> getLista(Date fechaini, Date fechafin) {

        String query = "SELECT * FROM contrato_de_servicio  where anulada=false and fecha between '"
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

    public List<ContratoDeServicio> getListaContrato(EstadoContrato estado) {

        String query = " SELECT * FROM contrato_de_servicio  where anulado=false  and  estado=:estado ";

        return session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .setParameter("estado", estado.getCodigo())
                .list();

    }

    public boolean existeCortedeMensualDeFacturacion(Date fecha) {

        boolean flag = false;

        CorteDeFacturacion corteFact = (CorteDeFacturacion) session.createSQLQuery(" SELECT * from corte_de_facturacion ct "
                + "  where MONTH(fecha)=:mes  and  YEAR(fecha)=:anio limit 1 ")
                .addEntity(CorteDeFacturacion.class)
                .setParameter("mes", new SimpleDateFormat("MM").format(fecha))
                .setParameter("anio", new SimpleDateFormat("yyyy").format(fecha)).uniqueResult();

        if (!(corteFact == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public boolean existeCrtedeFacturacion(Date fecha) {

        boolean flag = false;

        CorteDeFacturacion corteFact = (CorteDeFacturacion) session.createSQLQuery("  SELECT * from corte_de_facturacion ct where fecha=:fecha limit 1 ")
                .addEntity(CorteDeFacturacion.class)
                .setParameter("fecha", new SimpleDateFormat("yyyy-MM-dd").format(fecha)).uniqueResult();

        if (!(corteFact == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return ContratoDeServicio.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
