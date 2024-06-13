/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.reconciliacion;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DetalleReconciliacionInternaCliente;
import modelo.Empleado;
import modelo.Proyecto;
import modelo.ReconciliacionInternaCliente;

import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoReconciliacionInternaCliente extends ManejoEstandar<ReconciliacionInternaCliente> {

    private static ManejoReconciliacionInternaCliente manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoReconciliacionInternaCliente getInstancia() {
        if (manejo == null) {
            manejo = new ManejoReconciliacionInternaCliente();
        }
        return manejo;
    }

    public List<ReconciliacionInternaCliente> getLista() {

        String query = " SELECT * FROM reconciliacion_interna_cliente where unidad_de_negocio=:ung  ";

        return session.createSQLQuery(query)
                .addEntity(ReconciliacionInternaCliente.class)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

    }

    public List<ReconciliacionInternaCliente> getLista(int tipoNom) {

        String query = " SELECT * FROM reconciliacion_interna_cliente where  condicion=1 and  tipo_nomina=:tipoNom and unidad_de_negocio=:ung ";

        System.out.println("sql " + query + " tipoNom " + tipoNom + " " + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        return session.createSQLQuery(query)
                .addEntity(Empleado.class)
                .setParameter("tipoNom", tipoNom)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

    }

    public ReconciliacionInternaCliente getReconciliacionInternaCliente(int codigo) {

        String query = " SELECT * FROM proyecto  where codigo=:codigo and unidad_de_negocio=:ung ";

        return (ReconciliacionInternaCliente) session.createSQLQuery(query)
                .addEntity(ReconciliacionInternaCliente.class)
                .setParameter("codigo", codigo)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .uniqueResult();

    }

    public List<ReconciliacionInternaCliente> getLista(Date fi, Date ff) {

        String query = " SELECT * FROM reconciliacion_interna_cliente  where fecha  between "
                + "'" + new SimpleDateFormat("yyyy/MM/dd").format(fi) + "' and '" + new SimpleDateFormat("yyyy/MM/dd").format(ff) + "' "
                + " and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Query " + query);
        return session.createSQLQuery(query).addEntity(ReconciliacionInternaCliente.class).list();

    }

    public List<DetalleReconciliacionInternaCliente> getDetalleReconciliacion(int recon) {

        List<DetalleReconciliacionInternaCliente> lista;
        String query = " SELECT * FROM detalle_reconciliacion_interna_cliente where  reconciliacion=:reconc ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleReconciliacionInternaCliente.class)
                .setParameter("reconc", recon)
                .list();

        return lista;
    }

    
     public BigInteger getNumero() {

        String query = " select ifnull(max(codigo)+1,0) from reconciliacion_interna_cliente ";

        return (BigInteger) session.createSQLQuery(query).uniqueResult();

    }

    
    
    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return ReconciliacionInternaCliente.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
