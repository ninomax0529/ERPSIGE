/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.notaDebito;

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
public class ManejoNotaDebito extends ManejoEstandar<NotaDebito> {

    private static ManejoNotaDebito manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoNotaDebito getInstancia() {
        if (manejo == null) {
            manejo = new ManejoNotaDebito();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public NotaDebito getNotaDebito(int codigo) {

        NotaDebito notaDebito = null;

        String query = "select * From nota_debito nc where nc.codigo=:codigo ";

        System.out.println("Query " + query);

        notaDebito = (NotaDebito) getSession().createSQLQuery(query).addEntity(NotaDebito.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

        return notaDebito;
    }

    public List<NotaDebito> getLista() {

        String query = " SELECT * FROM nota_debito where  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(NotaDebito.class).list();

    }

      public List<NotaDebito> getLista(Date fechaDesde, Date fechaHasta) {

        String query = " SELECT * FROM nota_debito where fecha "
                + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' "
                + "and '" + new SimpleDateFormat("yyyy-MM-dd")
                        .format(fechaHasta) + "' and unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(NotaDebito.class).list();

    }
    
    public List<NotaDebito> getListaNotaDebito(Date fecha) {

        String query = " SELECT * FROM  nota_debito  where  fecha=:fecha ";
//        String query = " SELECT * FROM  factura  where  fecha=:fecha  and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query)
                .addEntity(NotaDebito.class)
                .setParameter("fecha", fecha)
                .list();

    }

    public List<NotaDebito> getLista(Cliente cliente) {

        String query = " SELECT * FROM nota_debito  "
                + " where anulada=false and  cliente=:cliente and "
                + "   unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(NotaDebito.class).setParameter("cliente", cliente.getCodigo()).list();

    }

    public List<DetalleNotaCredito> getDetalleNotaDeCredito(int nota) {

        String query = " SELECT * FROM detalle_nota_debito  where nota_debito=:nc  ";

        return session.createSQLQuery(query).addEntity(DetalleNotaCredito.class)
                .setParameter("nc", nota).list();

    }

    @Override
    public Class getReferencia() {
        return NotaDebito.class;
    }

    public boolean existeNCF(String ncf) {

        boolean flag = false;

        NotaDebito fact = (NotaDebito) session.createSQLQuery(" SELECT * FROM nota_debito f  where anulada=false and "
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

    public boolean existeSecuencia(int numero) {

        boolean flag = false;

        NotaDebito nc = (NotaDebito) session.createSQLQuery(" SELECT * FROM nota_debito f  where anulada=false and "
                + "  f.numero=:numero limit 1 ")
                .addEntity(NotaDebito.class)
                .setParameter("numero", numero).uniqueResult();

        if (!(nc == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(numero)+1,0) from nota_debito  where unidad_de_negocio=:ung ";

        return (BigInteger) session.createSQLQuery(query)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .uniqueResult();

    }

    public static void main(String[] args) {

    }

}
