/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.inventario.entrada;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Catalogo;
import modelo.DetalleEntradaInventario;
import modelo.EntradaInventario;
import modelo.Factura;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoEntradaInventario extends ManejoEstandar<EntradaInventario> {

    private static ManejoEntradaInventario manejoentraInventarioDao = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoEntradaInventario getInstancia() {
        if (manejoentraInventarioDao == null) {
            manejoentraInventarioDao = new ManejoEntradaInventario();
        }
        return manejoentraInventarioDao;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<EntradaInventario> getEntradaInventario() {

        String query = "SELECT * FROM entrada_inventario  where  fecha=date(now()) and  "
                + "  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(EntradaInventario.class).list();

    }

    public List<EntradaInventario> getEntradaInventario(Date fechaini, Date fechafin) {

        String query = " SELECT * FROM entrada_inventario  where  fecha between '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaini) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "'  and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
        
        System.out.println("Query " + query);

        return session.createSQLQuery(query).addEntity(EntradaInventario.class).list();

    }

    public List<EntradaInventario> getEntradaInventario(String parametro) {

        String query = "SELECT * FROM entrada_inventario  " + parametro;

        return session.createSQLQuery(query).addEntity(EntradaInventario.class).list();

    }

    public EntradaInventario getEntradaInventario(int codigo) {

        EntradaInventario entradaInventario;
        String query = "SELECT * FROM entrada_inventario  where  codigo=:codigo "
                + "    and unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        entradaInventario = (EntradaInventario) session.createSQLQuery(query).addEntity(Catalogo.class).setParameter("codigo", codigo).uniqueResult();

        return entradaInventario;
    }

    public List<DetalleEntradaInventario> getDetalleInventario(EntradaInventario ent) {

        List<DetalleEntradaInventario> lista;
        String query = "SELECT * FROM detalle_entrada_inventario  where  entrada_inventario=:entrada ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleEntradaInventario.class).setParameter("entrada", ent.getCodigo()).list();

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

        String query = " select ifnull(max(numero)+1,0) from entrada_inventario where unidad_de_negocio=:ung";

        return (BigInteger) session.createSQLQuery(query)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .uniqueResult();

    }
    
      public boolean existeSecuencia(int numero) {

        boolean flag = false;

        EntradaInventario entrada = (EntradaInventario)session.createSQLQuery(" SELECT * FROM entrada_inventario f  where anulada=false and "
                + "  f.numero=:numero limit 1 ")
                .addEntity(EntradaInventario.class)
                .setParameter("numero", numero).uniqueResult();

        if (!(entrada==null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    @Override
    public Class getReferencia() {
        return EntradaInventario.class;
    }

    public static void main(String[] args) {

    }

}
