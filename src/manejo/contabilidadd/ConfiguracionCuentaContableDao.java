/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contabilidadd;

import java.util.ArrayList;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.ConfiguracionCuentaContable;
import modelo.DetalleConfiguaracionCuentaContable;
import modelo.DetalleEntradaDiario;
import modelo.Factura;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class ConfiguracionCuentaContableDao extends ManejoEstandar<ConfiguracionCuentaContable> {

    private static ConfiguracionCuentaContableDao manejoalmacen = null;
    private Session session = HibernateUtil.getSession();

    public static ConfiguracionCuentaContableDao getInstancia() {
        if (manejoalmacen == null) {
            manejoalmacen = new ConfiguracionCuentaContableDao();
        }

        return manejoalmacen;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<ConfiguracionCuentaContable> getConfiguracionCuentaContable() {

        String query = "SELECT * FROM configuracion_cuenta_contable";

        return session.createSQLQuery(query).addEntity(ConfiguracionCuentaContable.class).list();

    }

    public ConfiguracionCuentaContable getConfiguracionCuentaContableTipoConcepto(int tipoCooncepto) {

        ConfiguracionCuentaContable configuracionCuentaContable;
        String query = " SELECT * FROM configuracion_cuenta_contable where  tipo_concepto=:codigo and habilitada=true ";

        configuracionCuentaContable = (ConfiguracionCuentaContable) session.createSQLQuery(query)
                .addEntity(ConfiguracionCuentaContable.class).setParameter("codigo", tipoCooncepto).uniqueResult();

        return configuracionCuentaContable;
    }

    public ConfiguracionCuentaContable getConfigCuentaPorTipoConcepto(int tipoConceto) {

        ConfiguracionCuentaContable configuracionCuentaContable;
        String query = " SELECT * FROM configuracion_cuenta_contable where  tipo_concepto=:codigo and habilitada=true ";

        configuracionCuentaContable = (ConfiguracionCuentaContable) session.createSQLQuery(query)
                .addEntity(ConfiguracionCuentaContable.class).setParameter("codigo", tipoConceto).uniqueResult();

        return configuracionCuentaContable;
    }

    public ConfiguracionCuentaContable getConfigCuentaPorTipoConcepto(int tipoConceto, int modulo) {

        ConfiguracionCuentaContable configuracionCuentaContable;
        String query = " SELECT * FROM configuracion_cuenta_contable "
                + " where  tipo_concepto=:codigo and habilitada=true  and  modulo=:modulo ";

        configuracionCuentaContable = (ConfiguracionCuentaContable) session.createSQLQuery(query)
                .addEntity(ConfiguracionCuentaContable.class)
                .setParameter("codigo", tipoConceto)
                .setParameter("modulo", modulo)
                .uniqueResult();
        
        System.out.println("SQL "+query);

        return configuracionCuentaContable;
    }

    public List<DetalleConfiguaracionCuentaContable> getDetalleConfiguracionCuentaContable(int configuracionCuenta) {

        List<DetalleConfiguaracionCuentaContable> lista;
        String query = " SELECT * FROM detalle_configuaracion_cuenta_contable where configuracion_cuenta_contable=:config  ";

        lista = session.createSQLQuery(query).addEntity(DetalleConfiguaracionCuentaContable.class).setParameter("config", configuracionCuenta).list();

        return lista;
    }

    public void guardar(ConfiguracionCuentaContable configuracionCuentaContable) {

        try {

            session.beginTransaction();

            session.save(configuracionCuentaContable);

            session.getTransaction().commit();

        } catch (Exception e) {

            session.getTransaction().rollback();
            e.printStackTrace();

        }

    }
    
     public List<DetalleEntradaDiario> generarConfiguracionCuenta(ConfiguracionCuentaContable config) {

        List<DetalleEntradaDiario> listaDetalleEnt = new ArrayList();

        DetalleEntradaDiario detalle;

        try {

            if (config == null) {

                return listaDetalleEnt;
            }

            List<DetalleConfiguaracionCuentaContable> listaDetConfiguracionCuenta;

            listaDetConfiguracionCuenta = ConfiguracionCuentaContableDao.getInstancia()
                    .getDetalleConfiguracionCuentaContable(config.getCodigo());

            for (DetalleConfiguaracionCuentaContable detConfi : listaDetConfiguracionCuenta) {

                detalle = new DetalleEntradaDiario();

                detalle.setCatalogo(detConfi.getCatalogo());
                detalle.setCuenta(detConfi.getCuenta());
                detalle.setDescripcionCuenta(detConfi.getNombreCuenta());
                detalle.setCuentaControl(detalle.getCatalogo().getCuentaControl());
                detalle.setDebito(0.00);
                detalle.setCredito(0.00);

                listaDetalleEnt.add(detalle);
                System.out.println("Det " + detalle.getCatalogo().getCuenta());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaDetalleEnt;
    }
    

    @Override
    public Class getReferencia() {
        return ConfiguracionCuentaContable.class;
    }

    public static void main(String[] args) {

        System.out.println("Config " + getInstancia().getConfigCuentaPorTipoConcepto(4));
    }

}
