package manejo.inventario.salida;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DetalleSalidaInventario;
import modelo.Factura;
import modelo.SalidaInventario;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utiles.VariablesGlobales;

public class ManejoSalidaInventario extends ManejoEstandar<SalidaInventario> {

    private static final Logger LOG = Logger.getLogger(ManejoSalidaInventario.class.getName());
    private static ManejoSalidaInventario manejoSalidaInventario = null;
       private Session session = HibernateUtil.getSession();
    public static ManejoSalidaInventario getInstancia() {
        if (manejoSalidaInventario == null) {
            manejoSalidaInventario = new ManejoSalidaInventario();
        }
        return manejoSalidaInventario;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<SalidaInventario> getListaSalidaInventario(boolean anulado) {
        try {
            String query = " SELECT * FROM  salida_inventario where date(fecha)=date(now()) and anulada=" + anulado + " ";
            return (List<SalidaInventario>) getSession().createSQLQuery(query)
                    .addEntity(SalidaInventario.class)
                    .list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            getSession().close();
        }
        return null;
    }

    public List<SalidaInventario> getListabyFecha(Date fechaDesde, Date fechaHasta, boolean anulado) {

        try {

            String query = " SELECT * FROM salida_inventario where fecha "
                    + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "'  "
                    + "  and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "' and anulada=" + anulado + ""  + " "
                    + "  and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

            return (List<SalidaInventario>) getSession().createSQLQuery(query)
                    .addEntity(SalidaInventario.class)
                    .list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            getSession().close();
        }
        return null;
    }

    public int guardarSalidaInventario(SalidaInventario salidaInventario) {
        int idCodigo = 0;
        Transaction transaction = null;
        Session s = null;
        try {
            s = getSession();

            transaction = s.beginTransaction();
            s.beginTransaction();

            salidaInventario.getSolicitudSalida().setCondicion("Procesado");
            salidaInventario.getSolicitudSalida().setEstado("Procesado");

            //session.merge(solicitudSalidaInventario);  
            //Inserto la Salida de Inventario
            s.save(salidaInventario);
            s.merge(salidaInventario.getSolicitudSalida());

            s.flush(); //force the SQL INSERT
            s.refresh(salidaInventario);

            idCodigo = salidaInventario.getCodigo();

            s.getTransaction().commit();

            return idCodigo;
        } catch (HibernateException e) {

            if (s != null) {
                if (s.getTransaction().isActive()) {
                    s.getTransaction().rollback();
                }
            }
            System.out.println(e.getMessage());

            return 0;
        } finally {
//            if (s != null) {
//                s.close();
//            }
        }
    }

    public boolean anularSalidaInventario(SalidaInventario salidaInventario) {
        Transaction transaction = null;
        Session s = null;
        try {
            //Cargamos la Sesion Actual            
            s = getSession();

            transaction = s.beginTransaction();
            
            salidaInventario.getSolicitudSalida().setCondicion("Pendiente");
            salidaInventario.getSolicitudSalida().setEstado("Pendiente");
            salidaInventario.setAnulada(true);
            salidaInventario.setFechaAnulada(new Date());

            //Inserto la Salida de Inventario
            s.merge(salidaInventario);
            //Actualizo la solicitud Salida de Inventario
            s.merge(salidaInventario.getSolicitudSalida());
            s.flush();

            transaction.commit();

            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            return false;
        } finally {
//            if (s != null) {
//                s.close();
//            }
        }
    }

    public SalidaInventario getSalidaInventario(int salidaInventario) {
        try {
            String query = "SELECT * FROM  salida_inventario  where codigo=:solicitud "
                    + "  and  unidad_de_negocio="+VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

            return (SalidaInventario) getSession().createSQLQuery(query)
                    .addEntity(SalidaInventario.class)
                    .setParameter("solicitud", salidaInventario)
                    .uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            getSession().close();
        }
        return null;
    }

    public List<DetalleSalidaInventario> getDetalleSalidaInventario(SalidaInventario salidaInventario) {
        try {
            List<DetalleSalidaInventario> lista = new ArrayList<>();
            String query = " SELECT * FROM detalle_salida_inventario where  salida_inventario=:solicitud ";

            lista = (List<DetalleSalidaInventario>) getSession().createSQLQuery(query)
                    .addEntity(DetalleSalidaInventario.class)
                    .setParameter("solicitud", salidaInventario.getCodigo())
                    .list();

            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            getSession().close();
        }
        return null;
    }
    
    
     public boolean existeSecuencia(int numero) {

        boolean flag = false;

        SalidaInventario salida = (SalidaInventario) session.createSQLQuery(" SELECT * FROM salida_inventario s  where anulada=false and "
                + "  s.numero=:numero limit 1 ")
                .addEntity(Factura.class)
                .setParameter("numero", numero).uniqueResult();

        if (!(salida == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }


    @Override
    public Class getReferencia() {
        return SalidaInventario.class;
    }

    public BigInteger getNumero() {
        try {
            String query = "select ifnull((select ifnull(max(codigo)+1,1) from salida_inventario),1)";

            return (BigInteger) getSession().createSQLQuery(query)
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
    }

}
