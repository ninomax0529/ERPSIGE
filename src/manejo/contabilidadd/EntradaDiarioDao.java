/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contabilidadd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.Catalogo;
import modelo.ComprobanteDePago;
import modelo.ConfiguracionCuentaContable;
import modelo.DetalleConfiguaracionCuentaContable;
import modelo.DetalleEntradaDiario;
import modelo.DetalleEntradaInventario;
import modelo.EntradaDiario;
import modelo.ReciboIngreso;
import modelo.SecuenciaDocumento;
import org.hibernate.Session;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 *
 * @author luis
 */
public class EntradaDiarioDao extends ManejoEstandar<EntradaDiario> {

    private static EntradaDiarioDao manejoentradadiario = null;
    private Session session = HibernateUtil.getSession();

    public static EntradaDiarioDao getInstancia() {
        if (manejoentradadiario == null) {
            manejoentradadiario = new EntradaDiarioDao();
        }
        return manejoentradadiario;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<EntradaDiario> getEntradasDiario() {

        String query = " SELECT * FROM entrada_diario  where anulada=false and fecha=now()  "
                + "  and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(EntradaDiario.class).list();

    }

    public Double getMontoEjecutado(String mes, int anio, int cuenta) {

        String query = "SELECT ifnull(sum(ded.debito),0) FROM entrada_diario ed,detalle_entrada_diario ded\n"
                + "\n"
                + " where  ed.codigo=ded.entrada_diario and anulada=false \n"
                + "\n"
                + "and month(ed.fecha)=:mes and year(ed.fecha)=:anio and ded.catalogo=:cuenta";

        return (Double) session.createSQLQuery(query)
                .setParameter("mes", mes)
                .setParameter("anio", anio)
                .setParameter("cuenta", cuenta)
                .uniqueResult();

    }

    public List<EntradaDiario> getEntradasDiario(Date fechaini, Date fechafin) {

        String query = "SELECT * FROM entrada_diario  where anulada=false and fecha between '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaini) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "' "
                + "  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
        System.out.println("Query " + query);

        return session.createSQLQuery(query).addEntity(EntradaDiario.class).list();

    }

    public List<EntradaDiario> getEntradasDiario(String parametro) {

        String query = " SELECT * FROM entrada_diario  " + parametro + " "
                + "  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

             System.out.println("Query " + query);
        return session.createSQLQuery(query).addEntity(EntradaDiario.class).list();

    }

    public EntradaDiario getEntradaDiario(int codigo) {

        EntradaDiario entradadiario;
        String query = " SELECT * FROM entrada_diario  where  codigo=:codigo  "
                + "  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        entradadiario = (EntradaDiario) session.createSQLQuery(query)
                .addEntity(Catalogo.class)
                .setParameter("codigo", codigo).uniqueResult();

        return entradadiario;
    }

    public EntradaDiario getEntradaDiarioPorDocumento(int documento, int tipo) {

        EntradaDiario entradadiario;
        String query = "SELECT * FROM entrada_diario where anulada=false and tipo_documento=:tipo  and  documento=:documento "
                + "   and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() + "  limit 1";

        entradadiario = (EntradaDiario) session.createSQLQuery(query)
                .addEntity(EntradaDiario.class)
                .setParameter("documento", documento).setParameter("tipo", tipo).uniqueResult();

        return entradadiario;
    }

    public List<EntradaDiario> getListaEntradaDiarioPorDocumento(int documento, int tipo) {

        List<EntradaDiario> entradadiario;
        String query = "SELECT * FROM entrada_diario where anulada=false and tipo_documento=:tipo and  documento=:documento "
                + "  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        entradadiario = session.createSQLQuery(query)
                .addEntity(EntradaDiario.class)
                .setParameter("documento", documento).setParameter("tipo", tipo).list();

        return entradadiario;
    }

    public List<DetalleEntradaDiario> getDetalleEntradaDiario(EntradaDiario ent) {

        List<DetalleEntradaDiario> lista;
        String query = "SELECT * FROM detalle_entrada_diario where  entrada_diario=:entrada ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleEntradaDiario.class).setParameter("entrada", ent.getCodigo()).list();

        return lista;
    }

    public List<DetalleEntradaDiario> getDetalleEntradaDiario(int documento, int tipo) {

        List<DetalleEntradaDiario> lista;

        String query = "select * from detalle_entrada_diario ded where ded.entrada_diario in ( select codigo"
                + " from entrada_diario ed where  ed.documento=:documento and tipo_documento=:tipo ) ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleEntradaDiario.class)
                .setParameter("documento", documento).setParameter("tipo", tipo).list();

        return lista;
    }

    public DetalleEntradaDiario getTieneMovimientoEstaCuenta(Catalogo catalogo) {

        boolean existe = false;

        DetalleEntradaDiario detalleEntradaDiario;

        String query = "SELECT * FROM detalle_entrada_diario where  cuenta='" + catalogo.getCuenta() + "' limit 1 ";

        detalleEntradaDiario = (DetalleEntradaDiario) session.createSQLQuery(query)
                .addEntity(DetalleEntradaDiario.class).uniqueResult();

        System.out.println("Valor " + detalleEntradaDiario);

        return detalleEntradaDiario;
    }

//    public void salvar(EntradaDiario entradadiario) {
//        session.beginTransaction();
//
//        session.save(entradadiario);
//
//        session.getTransaction().commit();
//
//    }
    public void registrarEntradaDiario(int documento, String numeroDocumento,
            int tiopoDocumento, String concepto,
            int modulo, List<DetalleEntradaDiario> listaDetalleEnt, Date fechaContable, ConfiguracionCuentaContable configuracionCuenta) {

        EntradaDiario entdiario = new EntradaDiario();

        entdiario.setConcepto(concepto);
        entdiario.setNumeroDocumento(numeroDocumento);
        entdiario.setDocumento(documento);
        entdiario.setTipoDocumento(TipoDocumentoDao.getInstancia().getTipoDocumento(tiopoDocumento));
        entdiario.setFecha(fechaContable);
        entdiario.setFechaRegistro(new Date());
        entdiario.setModulo(ModuloDao.getInstancia().getModulo(modulo));
        entdiario.setTipoAsiento(TipoAsientoDao.getInstancia().getTipoAsiento(1));
        entdiario.setTipoEntrada(TipoEntradaDao.getInstancia().getTipoEntrada(2));
        entdiario.setUsuario(utiles.VariablesGlobales.USUARIO.getCodigo());
        entdiario.setUnidadDeNegocio(utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio());
        entdiario.setAnulada(false);
        entdiario.setConfiguracionCuentaContable(configuracionCuenta.getCodigo());

        for (int i = 0; i < listaDetalleEnt.size(); i++) {

            listaDetalleEnt.get(i).setEntradaDiario(entdiario);

        }

        entdiario.setDetalleEntradaDiarioCollection(listaDetalleEnt);

        EntradaDiarioDao.getInstancia().salvar(entdiario);

        System.out.println("Guardado!");

    }

    public void registrarEntradaDiario(int documento, String numeroDocumento,
            int tiopoDocumento, String concepto,
            int modulo, List<DetalleEntradaDiario> listaDetalleEnt, Date fechaContable) {

        EntradaDiario entdiario = new EntradaDiario();

        entdiario.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
        entdiario = asignarSecuenciaDoc(entdiario);
        entdiario.setMonto(getTotalDebito(listaDetalleEnt).toString());

        entdiario.setConcepto(concepto);
        entdiario.setNumeroDocumento(numeroDocumento);
        entdiario.setDocumento(documento);
        entdiario.setTipoDocumento(TipoDocumentoDao.getInstancia().getTipoDocumento(tiopoDocumento));
        entdiario.setFecha(fechaContable);
        entdiario.setFechaRegistro(new Date());
        entdiario.setModulo(ModuloDao.getInstancia().getModulo(modulo));
        entdiario.setTipoAsiento(TipoAsientoDao.getInstancia().getTipoAsiento(1));
        entdiario.setTipoEntrada(TipoEntradaDao.getInstancia().getTipoEntrada(2));
        entdiario.setUsuario(utiles.VariablesGlobales.USUARIO.getCodigo());
        entdiario.setAnulada(false);
        entdiario.setConfiguracionCuentaContable(null);

        for (int i = 0; i < listaDetalleEnt.size(); i++) {

            listaDetalleEnt.get(i).setEntradaDiario(entdiario);

        }

        entdiario.setDetalleEntradaDiarioCollection(listaDetalleEnt);

        EntradaDiarioDao.getInstancia().salvar(entdiario);

        System.out.println("Guardado!");

    }

    public void editarEntradaDiario(int documento, String numeroDocumento,
            int tiopoDocumento, String concepto,
            int modulo, List<DetalleEntradaDiario> listaDetalleEnt,
            Date fechaContable, ConfiguracionCuentaContable configuracionCuenta, EntradaDiario entdiario) {

        entdiario.setConcepto(concepto);
        entdiario.setNumeroDocumento(numeroDocumento);
        entdiario.setDocumento(documento);
        entdiario.setTipoDocumento(TipoDocumentoDao.getInstancia().getTipoDocumento(tiopoDocumento));
        entdiario.setFecha(fechaContable);
        entdiario.setFechaRegistro(new Date());
        entdiario.setModulo(ModuloDao.getInstancia().getModulo(modulo));
        entdiario.setTipoAsiento(TipoAsientoDao.getInstancia().getTipoAsiento(1));
        entdiario.setTipoEntrada(TipoEntradaDao.getInstancia().getTipoEntrada(2));
        entdiario.setUsuario(VariablesGlobales.USUARIO.getCodigo());
        entdiario.setAnulada(false);
        entdiario.setConfiguracionCuentaContable(configuracionCuenta.getCodigo());

        for (int i = 0; i < listaDetalleEnt.size(); i++) {

            listaDetalleEnt.get(i).setEntradaDiario(entdiario);

        }

        entdiario.setDetalleEntradaDiarioCollection(listaDetalleEnt);

        EntradaDiarioDao.getInstancia().salvar(entdiario);

        System.out.println("Guardado!");

    }

    public void editarEntradaDiario(int documento, String numeroDocumento,
            int tiopoDocumento, String concepto,
            int modulo, List<DetalleEntradaDiario> listaDetalleEnt,
            Date fechaContable, EntradaDiario entdiario) {

        entdiario.setConcepto(concepto);
        entdiario.setNumeroDocumento(numeroDocumento);
        entdiario.setDocumento(documento);
        entdiario.setTipoDocumento(TipoDocumentoDao.getInstancia().getTipoDocumento(tiopoDocumento));
        entdiario.setFecha(fechaContable);
        entdiario.setFechaRegistro(new Date());
        entdiario.setModulo(ModuloDao.getInstancia().getModulo(modulo));
        entdiario.setTipoAsiento(TipoAsientoDao.getInstancia().getTipoAsiento(1));
        entdiario.setTipoEntrada(TipoEntradaDao.getInstancia().getTipoEntrada(2));
        entdiario.setUsuario(VariablesGlobales.USUARIO.getCodigo());
        entdiario.setAnulada(false);
        entdiario.setConfiguracionCuentaContable(null);

        for (int i = 0; i < listaDetalleEnt.size(); i++) {

            listaDetalleEnt.get(i).setEntradaDiario(entdiario);

        }

        entdiario.setDetalleEntradaDiarioCollection(listaDetalleEnt);

        EntradaDiarioDao.getInstancia().actualizar(entdiario);

        System.out.println("editado!");

    }

    public void registrarAsientoDeDiario(int documento, String numeroDocumento,
            int tiopoDocumento, String concepto,
            int modulo, List<DetalleEntradaDiario> listaDetalleEnt, Date fechaContable) {

        EntradaDiario entdiario = new EntradaDiario();

        entdiario.setConcepto(concepto);
        entdiario.setNumeroDocumento(numeroDocumento);
        entdiario.setDocumento(documento);
        entdiario.setTipoDocumento(TipoDocumentoDao.getInstancia().getTipoDocumento(tiopoDocumento));
        entdiario.setFecha(fechaContable);
        entdiario.setFechaRegistro(new Date());
        entdiario.setModulo(ModuloDao.getInstancia().getModulo(modulo));
        entdiario.setTipoAsiento(TipoAsientoDao.getInstancia().getTipoAsiento(1));
        entdiario.setTipoEntrada(TipoEntradaDao.getInstancia().getTipoEntrada(2));
        entdiario.setUsuario(utiles.VariablesGlobales.USUARIO.getCodigo());
        entdiario.setAnulada(false);

        for (int i = 0; i < listaDetalleEnt.size(); i++) {

            listaDetalleEnt.get(i).setEntradaDiario(entdiario);

        }

        entdiario.setDetalleEntradaDiarioCollection(listaDetalleEnt);

        EntradaDiarioDao.getInstancia().salvar(entdiario);

        System.out.println("Guardado!");

    }

    public List<DetalleEntradaDiario> generarConfiguracionCuenta(ConfiguracionCuentaContable config, List<DetalleEntradaInventario> listaEntrada) {

        List<DetalleEntradaDiario> listaDetalleEnt = new ArrayList();

        DetalleEntradaDiario detalle;

        try {

            if (config == null) {

                return listaDetalleEnt;
            }

            List<DetalleConfiguaracionCuentaContable> listaDetConfiguracionCuenta;

            listaDetConfiguracionCuenta = ConfiguracionCuentaContableDao.getInstancia()
                    .getDetalleConfiguracionCuentaContable(config.getCodigo());

            Double costoArticulo = 0.00;

            for (DetalleEntradaInventario det : listaEntrada) {

                costoArticulo += det.getCostoUnitario() * det.getCantidadRecibida();

            }

            for (DetalleConfiguaracionCuentaContable detConfi : listaDetConfiguracionCuenta) {

                detalle = new DetalleEntradaDiario();

                detalle.setCatalogo(detConfi.getCatalogo());
                detalle.setCuenta(detConfi.getCuenta());
                detalle.setDescripcionCuenta(detConfi.getNombreCuenta());
                detalle.setCuentaControl(detalle.getCatalogo().getCuentaControl());

                if (detConfi.getDebitar()) {

                    detalle.setDebito(costoArticulo);
                    detalle.setCredito(0.00);

                } else if (detConfi.getAcreditar()) {

                    detalle.setDebito(0.00);
                    detalle.setCredito(costoArticulo);
                }

                listaDetalleEnt.add(detalle);
                System.out.println("Det " + detalle.getCatalogo().getCuenta());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaDetalleEnt;
    }

    public List<DetalleEntradaDiario> generarConfiguracionCuenta(ConfiguracionCuentaContable config, ReciboIngreso recibo) {

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

                if (detConfi.getDebitar()) {

                    detalle.setDebito(recibo.getMonto());
                    detalle.setCredito(0.00);

                } else if (detConfi.getAcreditar()) {

                    detalle.setDebito(0.00);
                    detalle.setCredito(recibo.getMonto());
                }

                listaDetalleEnt.add(detalle);
                System.out.println("Det " + detalle.getCatalogo().getCuenta());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaDetalleEnt;
    }

    public List<DetalleEntradaDiario> generarConfiguracionCuenta(ConfiguracionCuentaContable config, ComprobanteDePago recibo) {

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

                if (detConfi.getDebitar()) {

                    detalle.setDebito(recibo.getMonto());
                    detalle.setCredito(0.00);

                } else if (detConfi.getAcreditar()) {

                    detalle.setDebito(0.00);
                    detalle.setCredito(recibo.getMonto());
                }

                listaDetalleEnt.add(detalle);
                System.out.println("Det " + detalle.getCatalogo().getCuenta());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaDetalleEnt;
    }

    public boolean existeSecuencia(int numero) {

        boolean flag = false;

        EntradaDiario entrada = (EntradaDiario) session.createSQLQuery(" SELECT * FROM  entrada_diario ent  where anulada=false and "
                + "  ent.numero=:numero "
                  + "  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()
                + " limit 1 ")
                .addEntity(EntradaDiario.class)
                .setParameter("numero", numero).uniqueResult();

        if (!(entrada == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public void eliminar(DetalleEntradaDiario ded) {

        try {

            session.delete(ded);
//            session.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class getReferencia() {
        return EntradaDiario.class;
    }

    private EntradaDiario asignarSecuenciaDoc(EntradaDiario entdiario) {

        SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(entdiario.getUnidadDeNegocio().getCodigo(), 6);

        if (!(secDoc == null)) {

            boolean existe = EntradaDiarioDao.getInstancia().existeSecuencia(secDoc.getNumero());

            if (existe) {

                System.out.println("existe " + secDoc.getNumero());

                while (EntradaDiarioDao.getInstancia().existeSecuencia(secDoc.getNumero())) {

                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

                secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(entdiario.getUnidadDeNegocio().getCodigo(), 6);

                entdiario.setNumero(secDoc.getNumero());
                entdiario.setSecuenciaDocumento(secDoc);

            } else {

                System.out.println("No existe " + secDoc.getNumero());
                entdiario.setNumero(secDoc.getNumero());
                entdiario.setSecuenciaDocumento(secDoc);
                secDoc.setNumero(secDoc.getNumero() + 1);
                ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

            }

        }

        return entdiario;
    }

    public Double getTotalDebito(List<DetalleEntradaDiario> lista) {

        Double total = 0.00;

        for (DetalleEntradaDiario det : lista) {
            total += det.getDebito();
        }

        return ClaseUtil.roundDouble(total, 4);

    }

    public static void main(String[] args) {

    }

}
