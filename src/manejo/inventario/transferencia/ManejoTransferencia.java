/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.inventario.transferencia;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Catalogo;
import modelo.DetalleEntradaInventario;
import modelo.DetalleTransferenciaAlmacen;
import modelo.EntradaInventario;
import modelo.Factura;
import modelo.TransferenciaAlmacen;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTransferencia extends ManejoEstandar<TransferenciaAlmacen> {

    private static ManejoTransferencia manejoTransferencia = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTransferencia getInstancia() {
        if (manejoTransferencia == null) {
            manejoTransferencia = new ManejoTransferencia();
        }
        return manejoTransferencia;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<TransferenciaAlmacen> getTrnasferecnia() {

        String query = "SELECT * FROM transferencia_almacen   where  fecha=date(now()) "
                + "  and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(TransferenciaAlmacen.class).list();

    }

    public List<TransferenciaAlmacen> getTrnasferecnia(Date fechaini, Date fechafin) {

        String query = "SELECT * FROM transferencia_almacen  where  fecha between '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaini) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "' "
                + "  and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
        System.out.println("Query " + query);

        return session.createSQLQuery(query).addEntity(TransferenciaAlmacen.class).list();

    }

    public List<TransferenciaAlmacen> getEntradaInventario(String parametro) {

        String query = "SELECT * FROM transferencia_almacen  " + parametro;

        return session.createSQLQuery(query).addEntity(TransferenciaAlmacen.class).list();

    }

    public TransferenciaAlmacen getEntradaInventario(int codigo) {

        TransferenciaAlmacen entradaInventario;
        String query = "SELECT * FROM transferencia_almacen where  codigo=:codigo "
                + "  and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        entradaInventario = (TransferenciaAlmacen) session.createSQLQuery(query).addEntity(Catalogo.class).setParameter("codigo", codigo).uniqueResult();

        return entradaInventario;
    }

    public List<DetalleTransferenciaAlmacen> getDetalleTrnasferecnia(TransferenciaAlmacen trans) {

        List<DetalleTransferenciaAlmacen> lista;
        String query = "SELECT * FROM detalle_transferencia_almacen  where  transferencia_almacen=:trans ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleTransferenciaAlmacen.class).setParameter("trans", trans.getCodigo()).list();

        return lista;
    }


    public BigInteger getNumero() {

        String query = " select ifnull(max(numero)+1,0) from transferencia_almacen ";

        return (BigInteger) session.createSQLQuery(query).uniqueResult();

    }
    
      public boolean existeSecuencia(int numero) {

        boolean flag = false;

          TransferenciaAlmacen transf = (TransferenciaAlmacen)session.createSQLQuery(" SELECT * FROM transferencia_almacen f  where anulada=false and "
                + "  f.numero=:numero limit 1 ")
                .addEntity(TransferenciaAlmacen.class)
                .setParameter("numero", numero).uniqueResult();

        if (!(transf==null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    @Override
    public Class getReferencia() {
        return TransferenciaAlmacen.class;
    }

    public static void main(String[] args) {

    }

}
