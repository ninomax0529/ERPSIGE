/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.notaCredito;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Cliente;
import modelo.DetalleNotaCredito;
import modelo.NotaCredito;
import modelo.NotaDebito;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoNotaDeCredito extends ManejoEstandar<NotaCredito> {

    private static ManejoNotaDeCredito manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoNotaDeCredito getInstancia() {
        if (manejo == null) {
            manejo = new ManejoNotaDeCredito();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public NotaCredito getNotaCredito(int codigo) {

        NotaCredito notaCredito = null;

        String query = "select * From nota_credito nc where nc.codigo=:codigo ";

        System.out.println("Query " + query);

        notaCredito = (NotaCredito) getSession().createSQLQuery(query).addEntity(NotaCredito.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

        return notaCredito;
    }

    public List<NotaCredito> getLista() {

        String query = " SELECT * FROM nota_credito  where  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(NotaCredito.class).list();

    }

    public List<NotaCredito> getLista(Date fechaDesde, Date fechaHasta) {

        String query = " SELECT * FROM nota_credito where fecha "
                + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' "
                + "and '" + new SimpleDateFormat("yyyy-MM-dd")
                        .format(fechaHasta) + "' and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(NotaCredito.class).list();

    }

    public List<NotaCredito> getListaNotaCredito(Date fecha) {

        String query = " SELECT * FROM  nota_credito  where  fecha=:fecha ";
//        String query = " SELECT * FROM  factura  where  fecha=:fecha  and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query)
                .addEntity(NotaCredito.class)
                .setParameter("fecha", fecha)
                .list();

    }

    public List<NotaCredito> getLista(Cliente cliente) {

        String query = " SELECT * FROM nota_credito  "
                + " where anulada=false and  cliente=:cliente and "
                + "   unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(NotaCredito.class).setParameter("cliente", cliente.getCodigo()).list();

    }

    public List<DetalleNotaCredito> getDetalleNotaDeCredito(int nota) {

        String query = " SELECT * FROM detalle_nota_credito  where nota_credito=:nc  ";

        return session.createSQLQuery(query).addEntity(DetalleNotaCredito.class)
                .setParameter("nc", nota).list();

    }

    @Override
    public Class getReferencia() {
        return NotaCredito.class;
    }

    public boolean existeNCF(String ncf) {

        boolean flag = false;

        NotaCredito fact = (NotaCredito) session.createSQLQuery(" SELECT * FROM nota_credito f  where anulada=false and "
                + "  f.ncf=:ncf  limit 1 ")
                //and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
                .addEntity(NotaCredito.class)
                .setParameter("ncf", ncf).uniqueResult();

        if (!(fact == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }
    
      public boolean existeNCF(String ncf,int ung) {

        boolean flag = false;

        NotaCredito fact = (NotaCredito) session.createSQLQuery(" SELECT * FROM nota_credito nc "
                + "  where anulada=false and  nc.ncf=:ncf  and  unidad_de_negocio=:ung  limit 1 ")      
                .addEntity(NotaCredito.class)
                .setParameter("ncf", ncf)
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

        NotaCredito nc = (NotaCredito) session.createSQLQuery(" SELECT * FROM nota_credito f  where anulada=false and "
                + "  f.numero=:numero limit 1 ")
                .addEntity(NotaCredito.class)
                .setParameter("numero", numero).uniqueResult();

        if (!(nc == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(numero)+1,0) from nota_credito  where unidad_de_negocio=:ung ";

        return (BigInteger) session.createSQLQuery(query)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .uniqueResult();

    }

    public static void main(String[] args) {

    }

}
