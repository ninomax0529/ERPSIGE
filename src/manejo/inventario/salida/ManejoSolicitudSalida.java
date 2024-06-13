package manejo.inventario.salida;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DetalleSolicitudSalidaInventario;
import modelo.SolicitudSalidaInventario;

import org.hibernate.Session;
import utiles.VariablesGlobales;

public class ManejoSolicitudSalida extends ManejoEstandar<SolicitudSalidaInventario> {

    private static final Logger LOG = Logger.getLogger(ManejoSolicitudSalida.class.getName());
    private Session session = HibernateUtil.getSession();
    private static ManejoSolicitudSalida manejoSolicitudSalida = null;

    public static ManejoSolicitudSalida getInstancia() {
        if (manejoSolicitudSalida == null) {
            manejoSolicitudSalida = new ManejoSolicitudSalida();
        }
        return manejoSolicitudSalida;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<SolicitudSalidaInventario> getLista(Date fechaDesde, Date fechaHasta) {

        try {
            List<SolicitudSalidaInventario> list = new ArrayList<>();
            String query = " SELECT * FROM solicitud_salida_inventario where fecha "
                    + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' "
                    + "  and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "' "
                    + "  and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

            System.out.println("Sql " + query);

            list = (List<SolicitudSalidaInventario>) getSession().createSQLQuery(query)
                    .addEntity(SolicitudSalidaInventario.class)
                    .list();
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            getSession().close();
        }
        return null;
    }

    public List<SolicitudSalidaInventario> getListaFechaEstado(Date fechaDesde, Date fechaHasta, String estado, boolean anulado) {
        try {
            String query = " SELECT * FROM solicitud_salida_inventario where Date(fecha) "
                    + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "' and "
                    + " LOWER(estado)='" + estado.toLowerCase() + "' and   anulada=" + anulado 
                    + "  and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

            return (List<SolicitudSalidaInventario>) getSession().createSQLQuery(query)
                    .addEntity(SolicitudSalidaInventario.class)
                    .list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            getSession().close();
        }
        return null;
    }

    public List<SolicitudSalidaInventario> getListaSolicitud() {
        try {
            String query = " SELECT * FROM solicitud_salida_inventario where fecha=date(now())  "
                    + "  and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

            return getSession().createSQLQuery(query)
                    .addEntity(SolicitudSalidaInventario.class)
                    .list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            getSession().close();
        }
        return null;
    }

    public List<SolicitudSalidaInventario> getListaSolicitudPorEstado(String estado, boolean anulado) {
        try {

            String query = " SELECT * FROM solicitud_salida_inventario where LOWER(estado)=:estadoDR and anulada=:anuladaDR "
                    + "  and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

            System.out.println("SQL " + query);

            return (List<SolicitudSalidaInventario>) getSession().createSQLQuery(query)
                    .addEntity(SolicitudSalidaInventario.class)
                    .setParameter("estadoDR", estado.toLowerCase())
                    .setParameter("anuladaDR", anulado)
                    .list();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            getSession().close();
        }
        return null;
    }

    public SolicitudSalidaInventario getSolicitud(int solicitud) {
        try {
            String query = "SELECT * FROM  solicitud_salida_inventario  where codigo =:solicitud "
                    + " and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

            return (SolicitudSalidaInventario) getSession().createSQLQuery(query)
                    .addEntity(SolicitudSalidaInventario.class)
                    .setParameter("solicitud", solicitud)
                    .uniqueResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            getSession().close();
        }
        return null;
    }

    public List<DetalleSolicitudSalidaInventario> getDetalleSolicitud(SolicitudSalidaInventario solicitud) {
        try {
            List<DetalleSolicitudSalidaInventario> lista;
            String query = " SELECT * FROM detalle_solicitud_salida_inventario where  solicitud_salida_inventario=:solicitud ";

            lista = (List<DetalleSolicitudSalidaInventario>) getSession().createSQLQuery(query)
                    .addEntity(DetalleSolicitudSalidaInventario.class)
                    .setParameter("solicitud", solicitud.getCodigo())
                    .list();

            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            getSession().close();
        }
        return null;
    }

    @Override
    public Class getReferencia() {
        return SolicitudSalidaInventario.class;
    }

    public BigInteger getNumero() {
        try {
            String query = " select ifnull(max(codigo)+1,1) from solicitud_salida_inventario ";

            return (BigInteger) getSession().createSQLQuery(query)
                    .uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            getSession().close();
        }
        return BigInteger.valueOf(0);
    }

    public BigInteger tieneMovimientoEsteAlmacen(int almacen, int articulo) {
        try {
            String query = " select ifnull(count(*),0) from detalle_solicitud_salida_inventario "
                    + "  where almacen=:almacen  and  articulo=:articulo ";

            return (BigInteger) getSession().createSQLQuery(query)
                    .setParameter("almacen", almacen)
                    .setParameter("articulo", articulo)
                    .uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            getSession().close();
        }
        return BigInteger.valueOf(0);
    }

    public static void main(String[] args) {
        System.out.println(LOG);
        System.out.println("Datos " + getInstancia().tieneMovimientoEsteAlmacen(1, 6));
    }

}
