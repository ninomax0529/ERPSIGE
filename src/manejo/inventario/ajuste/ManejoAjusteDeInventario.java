/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.inventario.ajuste;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.AjusteInventario;
import modelo.DetalleAjusteInventario;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoAjusteDeInventario extends ManejoEstandar<AjusteInventario> {

    private static ManejoAjusteDeInventario manejoAjusteDeInventario = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoAjusteDeInventario getInstancia() {
        if (manejoAjusteDeInventario == null) {
            manejoAjusteDeInventario = new ManejoAjusteDeInventario();
        }
        return manejoAjusteDeInventario;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<AjusteInventario> getAjusteInventario() {

        String query = "SELECT * FROM ajuste_inventario  where  fecha=date(now()) "
                + "  and unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(AjusteInventario.class).list();

    }

    public List<AjusteInventario> getAjusteInventario(Date fechaini, Date fechafin) {

        String query = "SELECT * FROM ajuste_inventario  where  fecha between '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaini) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "' "
                + "  and unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
        System.out.println("Query " + query);

        return session.createSQLQuery(query).addEntity(AjusteInventario.class).list();

    }

    public List<AjusteInventario> getAjusteInventario(String parametro) {

        String query = "SELECT * FROM ajuste_inventario  " + parametro;

        return session.createSQLQuery(query).addEntity(AjusteInventario.class).list();

    }

    public AjusteInventario getAjusteInventario(int codigo) {

        AjusteInventario ajusteInventario;
        String query = "SELECT * FROM ajuste_inventario where  codigo=:codigo "
                + "   and unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        ajusteInventario = (AjusteInventario) session.createSQLQuery(query).addEntity(AjusteInventario.class).setParameter("codigo", codigo).uniqueResult();

        return ajusteInventario;
    }

    public List<DetalleAjusteInventario> getDetalleAjusteInventario(AjusteInventario ajusteInventario) {

        List<DetalleAjusteInventario> lista;
        String query = "SELECT * FROM detalle_ajuste_inventario  where  ajuste_inventario=:ajuste ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleAjusteInventario.class).setParameter("ajuste", ajusteInventario.getCodigo()).list();

        return lista;
    }

//    public void salvar(EntradaInventario entradaInventario) {
//        session.beginTransaction();
//
//        session.save(entradaInventario);
//
//        session.getTransaction().commit();
//
//    }
    public BigInteger getNumero() {

        String query = " select ifnull(max(codigo)+1,0) from ajuste_inventario ";

        return (BigInteger) session.createSQLQuery(query).uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return AjusteInventario.class;
    }

    public static void main(String[] args) {

    }

}
