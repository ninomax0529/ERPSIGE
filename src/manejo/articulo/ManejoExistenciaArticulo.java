/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.articulo;

/**
 *
 * @author maximilianoa-te
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;
import java.util.List;
import java.util.Objects;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.DetalleAjusteInventario;
import modelo.DetalleDevolucionDeInventario;
import modelo.DetalleEntradaInventario;
import modelo.DetalleFactura;
import modelo.DetalleFacturaSuplidor;
import modelo.DetalleInventarioFinal;
import modelo.DetalleListaDePrecio;
import modelo.DetallePedido;
import modelo.DetalleSalidaInventario;
import modelo.DetalleTransferenciaAlmacen;
import modelo.ExistenciaArticulo;
import modelo.ListaDePrecio;
import org.hibernate.Session;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 *
 * @author maximiliano
 */
public class ManejoExistenciaArticulo extends ManejoEstandar<ExistenciaArticulo> {

    private static ManejoExistenciaArticulo manejoExistenciaArticuloDao = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoExistenciaArticulo getInstancia() {

        if (manejoExistenciaArticuloDao == null) {
            manejoExistenciaArticuloDao = new ManejoExistenciaArticulo();
        }
        return manejoExistenciaArticuloDao;
    }

    public Session getSession() {
        return session;
    }

    public List<ExistenciaArticulo> getExistenciaArticulo() {

        String query = "SELECT * FROM existencia_articulo";

        return session.createSQLQuery(query).addEntity(ExistenciaArticulo.class).list();

    }

    public List<ExistenciaArticulo> getExistenciaArticulo(int articulo) {

        String query = " SELECT * FROM existencia_articulo  where articulo=:articulo";

        System.out.println("sql existen art " + query);
        return getSession().createSQLQuery(query)
                .addEntity(ExistenciaArticulo.class)
                .setParameter("articulo", articulo)
                .list();

    }

    public List<ExistenciaArticulo> getAlmacenExistenciaArticulo(int articulo) {

        String query = " SELECT * FROM existencia_articulo  "
                + " where articulo=:articulo and unidad_de_negocio=:unidadNegocio ";

        System.out.println("Articulo " + articulo + "VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() " + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        System.out.println("sql existen art " + query);
        return getSession().createSQLQuery(query)
                .addEntity(ExistenciaArticulo.class)
                .setParameter("articulo", articulo)
                .setParameter("unidadNegocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

    }

    public List<ExistenciaArticulo> getExistenciaAlmacen(int almacen) {

        String query = " SELECT * FROM existencia_articulo  where almacen=:almacen";

        return getSession().createSQLQuery(query)
                .addEntity(ExistenciaArticulo.class)
                .setParameter("almacen", almacen)
                .list();

    }

    public List<ExistenciaArticulo> getExistenciaAlmacen(int almacen, int articulo) {

        String query = "SELECT * FROM existencia_articulo  where almacen=:almacen  and articulo=:articulo ";

        return getSession().createSQLQuery(query)
                .addEntity(ExistenciaArticulo.class)
                .setParameter("almacen", almacen)
                .setParameter("articulo", articulo)
                .list();

    }

    public ExistenciaArticulo getAlmacen(int codigo) {

        ExistenciaArticulo existenciaArticulo;
        String query = "SELECT * FROM existencia_articulo where  almacen=:codigo ";

        existenciaArticulo = (ExistenciaArticulo) session.createSQLQuery(query)
                .addEntity(ExistenciaArticulo.class).setParameter("codigo", codigo).uniqueResult();

        return existenciaArticulo;
    }

    public ExistenciaArticulo getAlmacenExistencia(int almacen) {

        ExistenciaArticulo existenciaArticulo;
        String query = "SELECT * FROM existencia_articulo where  almacen=:alm limit 1";

        existenciaArticulo = (ExistenciaArticulo) session.createSQLQuery(query)
                .addEntity(ExistenciaArticulo.class).setParameter("alm", almacen).uniqueResult();

        return existenciaArticulo;
    }

    public ExistenciaArticulo getExistenciaArticulo(int articulo, int almacen) {

        ExistenciaArticulo existenciaArticulo;
        String query = " SELECT * FROM existencia_articulo "
                + "  where  articulo=:articulo and   almacen=:almacen limit 1";

        System.out.println("SQL " + query + " almacen " + almacen + " articulo " + articulo);
        existenciaArticulo = (ExistenciaArticulo) session.createSQLQuery(query)
                .addEntity(ExistenciaArticulo.class)
                .setParameter("articulo", articulo)
                .setParameter("almacen", almacen)
                .uniqueResult();

        return existenciaArticulo;
    }

    public ExistenciaArticulo getExistenciaArticulo(int articulo, int almacen, int unidadNegocio) {

        ExistenciaArticulo existenciaArticulo;
        String query = " SELECT * FROM existencia_articulo "
                + "  where  articulo=:articulo and   almacen=:almacen "
                + "  and unidad_de_negocio=:unidadNegocio limit 1";

        System.out.println("SQL " + query + " almacen " + almacen + " articulo " + articulo);
        existenciaArticulo = (ExistenciaArticulo) session.createSQLQuery(query)
                .addEntity(ExistenciaArticulo.class)
                .setParameter("articulo", articulo)
                .setParameter("almacen", almacen)
                .setParameter("unidadNegocio", unidadNegocio)
                .uniqueResult();

        return existenciaArticulo;
    }

    public Double getExistenciaArticulos(int articulo) {

        Double existenciaArticulo;

        String query = "  SELECT  sum(IFNULL(ea.existencia,ea.existencia)) as existencia from existencia_articulo ea "
                + " where articulo=:articulo LIMIT 1";

        System.out.println("SQL " + query);
        existenciaArticulo = (Double) session.createSQLQuery(query)
                .setParameter("articulo", articulo)
                .uniqueResult();

        existenciaArticulo = existenciaArticulo == null ? 0.00 : existenciaArticulo;

        return existenciaArticulo;
    }

    public Double getExistenciaArticulosPorMovimiento(int articulo, int alm) {

        Double existenciaArticulo;
        /* ejecutar esta sentencia 
         GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root123';
             FLUSH PRIVILEGES; */

        String query = "  SELECT  FN_EXISTENCIA_ART_POR_ALM(" + articulo + "," + alm + ") ";

        System.out.println("SQL " + query);
        existenciaArticulo = (Double) session.createSQLQuery(query)
                .uniqueResult();

        existenciaArticulo = existenciaArticulo == null ? 0.00 : existenciaArticulo;

        return existenciaArticulo;
    }

    public Double getExistenciaArticulo(int articulo, int almacen, int unidad, int listaPrecio) {

        Double existenciaArticulo;

        String query = "   SELECT  IFNULL(ea.existencia/au.fator_venta,ea.existencia) as existencia\n"
                + " from lista_de_precio lp,detalle_lista_de_precio dlp,\n"
                + "  unidad u,articulo_unidad au,existencia_articulo ea\n"
                + " where  lp.codigo=dlp.lista_de_precio \n"
                + "  and  u.codigo=dlp.unidad_salida\n"
                + "  and u.codigo=au.unidad \n"
                + " and au.articulo=dlp.articulo\n"
                + "  and  lp.habilitada=true\n"
                + " and ea.articulo=au.articulo\n"
                + " and dlp.articulo=:articulo   and au.unidad=:unidad and lp.codigo=:listaPrecio  and almacen=:almacen LIMIT 1";

        System.out.println("SQL " + query);
        existenciaArticulo = (Double) session.createSQLQuery(query)
                .setParameter("articulo", articulo)
                .setParameter("unidad", unidad)
                .setParameter("listaPrecio", listaPrecio)
                .setParameter("almacen", almacen)
                .uniqueResult();

        existenciaArticulo = existenciaArticulo == null ? 0.00 : existenciaArticulo;

        return existenciaArticulo;
    }

    public Double getPrecioVentaDeArticulo(int articulo, int unidad, int listaPrecio) {

        Double existenciaArticulo;

        String query = "   SELECT dlp.precio "
                + " from lista_de_precio lp,detalle_lista_de_precio dlp,\n"
                + "  unidad u,articulo_unidad au "
                + " where  lp.codigo=dlp.lista_de_precio \n"
                + "  and  u.codigo=dlp.unidad_salida\n"
                + "  and u.codigo=au.unidad \n"
                + " and au.articulo=dlp.articulo\n"
                + "  and  lp.habilitada=true\n"
                + " and dlp.articulo=:articulo   and au.unidad=:unidad and lp.codigo=:listaPrecio ";

        session.clear();
        session.flush();
        System.out.println("SQL " + query);
        existenciaArticulo = (Double) session.createSQLQuery(query)
                .setParameter("articulo", articulo)
                .setParameter("unidad", unidad)
                .setParameter("listaPrecio", listaPrecio)
                .uniqueResult();

        existenciaArticulo = existenciaArticulo == null ? 0.00 : existenciaArticulo;

        return existenciaArticulo;
    }

//    public void 
//    public void salvar(ExistenciaArticulo existenciaArticulo) {
//        session.beginTransaction();
//
//        session.save(existenciaArticulo);
//
//        session.getTransaction().commit();
//
//    }
    public void remover(ExistenciaArticulo existenciaArticulo) {

        try {

            session.beginTransaction();

            session.delete(existenciaArticulo);

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    public void actualizarExistenciaPorSalida(List<DetalleFactura> listadetalleFactura) {

        try {

            ArticuloUnidad artUnidad = null;
            ExistenciaArticulo exisArt = null;

            Integer codArt = 0, codUni = 0;
            Double existenciaPorMov = 0.00;

            for (DetalleFactura det : listadetalleFactura) {

                if (!(det.getArticulo().getTipoArticulo().getCodigo() == 3)) {
                    exisArt = getExistenciaArticulo(det.getArticulo().getCodigo(),
                            det.getAlmacen().getCodigo());
                }

                codArt = det.getArticulo().getCodigo();
                codUni = det.getUnidad().getCodigo();

                //Obtenemos el factor de ventas en que esta expresada la unidad de inventario
                artUnidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(codArt, codUni);

                System.out.println("Articulo Unidad " + artUnidad.getUnidad().getDescripcion() + " factor " + artUnidad.getFatorVenta());
                if (exisArt != null && det.getArticulo().getTipoArticulo().getCodigo() != 3) {

                    System.out.println("Entro actualizar"
                            + " " + exisArt.getArticulo().getCodigo() + " " + exisArt.getAlmacen().getCodigo());

                    exisArt.setExistenciaAnterior(exisArt.getExistencia());
                    existenciaPorMov = getExistenciaArticulosPorMovimiento(exisArt.getArticulo().getCodigo(), exisArt.getAlmacen().getCodigo());

                    exisArt.setExistencia(existenciaPorMov);
//                    exisArt.setExistencia(exisArt.getExistencia() - (det.getCantidad() * artUnidad.getFatorVenta()));
                    exisArt.setFecha(new Date());

                    Articulo art = exisArt.getArticulo();
                    art.setPrecioVentaAnterior(art.getPrecioVenta1());
                    art.setPrecioVenta1(det.getPrecio());
                    art.setExistencia(exisArt.getExistencia());

                    getInstancia().actualizar(exisArt);
                    ManejoArticulo.getInstancia().actualizar(art);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarExistenciaPorConsumo(List<DetalleSalidaInventario> lista) {

        try {

            for (DetalleSalidaInventario det : lista) {

                ExistenciaArticulo exisArt = getExistenciaArticulo(det.getArticulo().getCodigo(),
                        det.getAlmacen().getCodigo());

                if (exisArt != null) {

                    ArticuloUnidad articuloUnidad = ManejoArticuloUnidad.getInstancia()
                            .getArticuloUnidadEntrada(det.getArticulo().getCodigo(), det.getUnidad().getCodigo());

                    System.out.println("Entro actualizar"
                            + " " + exisArt.getArticulo().getCodigo() + " " + exisArt.getAlmacen().getCodigo());

                    exisArt.setExistenciaAnterior(exisArt.getExistencia());
                    exisArt.setExistencia(exisArt.getExistencia() - (det.getCantidad() * articuloUnidad.getFatorVenta()));
                    exisArt.setFecha(new Date());

                    Articulo art = exisArt.getArticulo();
                    art.setPrecioVentaAnterior(art.getPrecioVenta1());
                    art.setPrecioVenta1(det.getPrecio());
                    art.setExistencia(exisArt.getExistencia());

                    getInstancia().actualizar(exisArt);
                    ManejoArticulo.getInstancia().actualizar(art);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarExistenciaPorAnulacionDeConsumo(List<DetalleSalidaInventario> lista) {

        try {

            for (DetalleSalidaInventario det : lista) {

                ExistenciaArticulo exisArt = getExistenciaArticulo(det.getArticulo().getCodigo(),
                        det.getAlmacen().getCodigo());

                ArticuloUnidad au = ManejoArticuloUnidad.getInstancia()
                        .getArticuloUnidadEntrada(det.getArticulo().getCodigo(), det.getAlmacen().getCodigo());

                if (exisArt != null) {

                    System.out.println("Entro actualizar"
                            + " " + exisArt.getArticulo().getCodigo() + " " + exisArt.getAlmacen().getCodigo());

                    if (det.getUnidad().equals(exisArt.getUnidad())) {

                        exisArt.setExistenciaAnterior(exisArt.getExistencia());
                        exisArt.setExistencia(exisArt.getExistencia() + det.getCantidad());

                    } else {

                        exisArt.setExistenciaAnterior(exisArt.getExistencia());
                        exisArt.setExistencia(exisArt.getExistencia() + (det.getCantidad() * au.getCantidadUnidades()));
                    }

                    exisArt.setFecha(new Date());

                    Articulo art = exisArt.getArticulo();
                    art.setPrecioVentaAnterior(art.getPrecioVenta1());
                    art.setPrecioVenta1(det.getPrecio());
                    art.setExistencia(exisArt.getExistencia());

                    getInstancia().actualizar(exisArt);
                    ManejoArticulo.getInstancia().actualizar(art);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarExistenciaPorAnulacion(List<DetalleFactura> listadetalleFactura) {

        try {

            Double existencia = 0.00;
//            Integer codArt = 0, codUni = 0;
            for (DetalleFactura det : listadetalleFactura) {

                ExistenciaArticulo existenciaArticulo = ManejoArticulo.getInstancia()
                        .getExistenciaArticulo(det.getArticulo().getCodigo(), det.getAlmacen().getCodigo());

                //Buscando el factor de conversion de una unidad a otra
//                ArticuloUnidad au = ManejoArticuloUnidad.getInstancia()
//                        .getArticuloUnidadSslida(det.getArticulo().getCodigo(), existenciaArticulo.getUnidad().getCodigo());

                if (existenciaArticulo != null) {

                    existencia = ManejoExistenciaArticulo.getInstancia()
                            .getExistenciaArticulosPorMovimiento(det.getArticulo().getCodigo(), det.getAlmacen().getCodigo());
//                    
//                    codArt = det.getArticulo().getCodigo();
//                    codUni = det.getUnidad().getCodigo();

//                    ArticuloUnidad artUnidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(codArt, codUni);

                    existenciaArticulo.setExistencia(existencia);
//                    
//                    if (Objects.equals(det.getUnidad().getCodigo(), existenciaArticulo.getUnidad().getCodigo())) {//undad minima de inventario
//
//                        System.out.println("Entro actualizar"
//                                + " " + existenciaArticulo.getArticulo().getCodigo() + " " + existenciaArticulo.getAlmacen().getCodigo());
//
//                        existenciaArticulo.setExistenciaAnterior(existenciaArticulo.getExistencia());
//                        existenciaArticulo.setExistencia(existenciaArticulo.getExistencia() + det.getCantidad());
//
//                    } else {
//
//                        System.out.println("Entro actualizar"
//                                + " " + existenciaArticulo.getArticulo().getCodigo() + " " + existenciaArticulo.getAlmacen().getCodigo());
//
//                        existenciaArticulo.setExistenciaAnterior(existenciaArticulo.getExistencia());
//
//                        existenciaArticulo.setExistencia(existenciaArticulo.getExistencia() + (det.getCantidad() * au.getCantidadUnidades()));//multiplicar por el factor
//
//                    }
                    existenciaArticulo.setFecha(new Date());

                    Articulo articulo = existenciaArticulo.getArticulo();
                    articulo.setPrecioVentaAnterior(articulo.getPrecioVenta1());
                    articulo.setPrecioVenta1(det.getPrecio());
                    articulo.setExistencia(existenciaArticulo.getExistencia());

                    getInstancia().actualizar(existenciaArticulo);

                    ManejoArticulo.getInstancia().actualizar(articulo);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarExistenciaPorAnulacionFactsupli(List<DetalleFacturaSuplidor> listadetalleFactura) {

        try {

            Double existencia = 0.00;
            for (DetalleFacturaSuplidor det : listadetalleFactura) {

                ExistenciaArticulo existenciaArticulo = ManejoArticulo.getInstancia()
                        .getExistenciaArticulo(det.getArticulo().getCodigo(), det.getArticulo().getAlmacen());

                if (existenciaArticulo != null) {

                    System.out.println("Entro actualizar"
                            + " " + existenciaArticulo.getArticulo().getCodigo() + " " + existenciaArticulo.getAlmacen().getCodigo());

//                        existencia = ManejoExistenciaArticulo.getInstancia()
//                            .getExistenciaArticulosPorMovimiento(det.getArticulo().getCodigo(), det.get);
                    existenciaArticulo.setExistenciaAnterior(existenciaArticulo.getExistencia());
                    existenciaArticulo.setExistencia(existenciaArticulo.getExistencia() + det.getCantidad());
                    existenciaArticulo.setFecha(new Date());

                    Articulo articulo = existenciaArticulo.getArticulo();
                    articulo.setPrecioVentaAnterior(articulo.getPrecioVenta1());
                    articulo.setExistencia(existenciaArticulo.getExistencia());

                    getInstancia().actualizar(existenciaArticulo);

                    ManejoArticulo.getInstancia().actualizar(articulo);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"BoxedValueEquality", "NumberEquality"})
    public void actualizarExistenciaPorEntrada(List<DetalleEntradaInventario> lista) {

        try {

            Double existencia = 0.00;
            ListaDePrecio listaDePrecio = ManejoListaDePrecio.getInstancia().getListaDePrecioGeneral(4);

            List<DetalleListaDePrecio> listaDetalleDePrecio = ManejoListaDePrecio.getInstancia()
                    .getDetalleListaDePrecio(listaDePrecio.getCodigo());

            //Recorremos el detalle de la entrada de inventario
            for (DetalleEntradaInventario det : lista) {

                System.out.println("articulo " + det.getArticulo().getCodigo() + " alm " + det.getArticulo().getAlmacen());

                ExistenciaArticulo existenciaArticulo = getExistenciaArticulo(det.getArticulo().getCodigo(), det.getAlmacen().getCodigo());

                if (existenciaArticulo != null) {

                    existencia = ManejoExistenciaArticulo.getInstancia()
                            .getExistenciaArticulosPorMovimiento(det.getArticulo().getCodigo(), det.getAlmacen().getCodigo());

                    existenciaArticulo.setExistenciaAnterior(existenciaArticulo.getExistencia());

                    Articulo articulo = existenciaArticulo.getArticulo();
//
//                    ArticuloUnidad articuloUnidad = ManejoArticuloUnidad.getInstancia()
//                            .getArticuloUnidadEntrada(articulo.getCodigo(), det.getUnidad().getCodigo());

                    articulo.setPrecioCompraAnterior(articulo.getPrecioCompraUnitario());
//                    Double costoUnitario = 0.00, costo = 0.00;

                    System.out.println(" det.getCantidadRecibida() " + det.getCantidadRecibida());

                    existenciaArticulo.setExistencia(existencia);
                    existenciaArticulo.setFecha(new Date());

                    //Actualizamos el costo a la unidad de entrada base
                    articulo.setPrecioCompra(det.getCostoUnitario());

                    articulo.setPrecioCompraAnterior(det.getPrecioAnterior());

                    articulo.setPrecioVenta1(det.getPrecioVenta());
                    articulo.setPrecioVenta2(det.getPrecioVenta());
                    articulo.setPrecioVenta3(det.getPrecioVenta());

//
//                    List<ArticuloUnidad> articuloUnidadCollection = ManejoArticuloUnidad.getInstancia().getListaUnidadSslida(articulo.getCodigo());
//
//                    //Actualizar costo unitario
//                    for (ArticuloUnidad au : articuloUnidadCollection) {
//
//                        au.setCostoUnitario(articulo.getPrecioCompra() / au.getCantidadUnidades());
//
//                        ManejoArticuloUnidad.getInstancia().actualizar(au);
//
//                        costoUnitario = utiles.ClaseUtil.roundDoubleSies(costoUnitario, 6);
//
//                    }
                    //Acualizamos la lista de precio general cos los articulos de la entrada
                    for (DetalleListaDePrecio detlp : listaDetalleDePrecio) {

                        if (detlp.getArticulo().getCodigo() == det.getArticulo().getCodigo()
                                && detlp.getUnidadSalida().getCodigo() == det.getUnidad().getCodigo()) {

                            detlp.setPrecio(det.getPrecioVenta());
                            detlp.setCostoUnitario(det.getCostoUnitario());
                        }

                    }

                    articulo.setPrecioCompraUnitario(det.getCostoUnitario());
                    articulo.setExistencia(existenciaArticulo.getExistencia());
                    getInstancia().actualizar(existenciaArticulo);

                    ManejoArticulo.getInstancia().actualizar(articulo);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

            listaDePrecio.setDetalleListaDePrecioCollection(listaDetalleDePrecio);
            ManejoListaDePrecio.getInstancia().actualizar(listaDePrecio);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarExistenciaEntradaPorTransferencia(List<DetalleTransferenciaAlmacen> lista) {

        try {

            Double existencia = 0.00;
            //Recorremos el detalle de la entrada de inventario
            for (DetalleTransferenciaAlmacen det : lista) {

                ExistenciaArticulo existenciaArticulo = getExistenciaArticulo(det.getArticulo().getCodigo(), det.getAlmacenDestino().getCodigo());

//                ArticuloUnidad au = ManejoArticuloUnidad.getInstancia()
//                        .getArticuloUnidadSslida(det.getArticulo().getCodigo(), existenciaArticulo.getUnidad().getCodigo());
                if (existenciaArticulo != null) {

                    existencia = ManejoExistenciaArticulo.getInstancia()
                            .getExistenciaArticulosPorMovimiento(det.getArticulo().getCodigo(), det.getAlmacenDestino().getCodigo());
//                    if (Objects.equals(det.getUnidad().getCodigo(), existenciaArticulo.getUnidad().getCodigo())) {//undad minima de inventario
                    System.out.println("existencia : " + existencia);
//                         
                    existenciaArticulo.setExistenciaAnterior(existenciaArticulo.getExistencia());
// 
                    existenciaArticulo.setExistencia(existencia);
//
//                    } else {
//
//                        existenciaArticulo.setExistencia(existencia + (det.getCantidad() * au.getCantidadUnidades()));//multiplicar por el factor
//                    }

                    //Actualizamos el costo a la unidad de entrada base
                    existenciaArticulo.setFecha(new Date());

                    getInstancia().actualizar(existenciaArticulo);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarExistenciaSalidaPorTransferencia(List<DetalleTransferenciaAlmacen> lista) {

        try {

            Double existencia = 0.00;
            //Recorremos el detalle de la entrada de inventario
            for (DetalleTransferenciaAlmacen det : lista) {

                ExistenciaArticulo existenciaArticulo = getExistenciaArticulo(det.getArticulo().getCodigo(), det.getAlmacenOrigen().getCodigo());
//
//                ArticuloUnidad au = ManejoArticuloUnidad.getInstancia()
//                        .getArticuloUnidadSslida(det.getArticulo().getCodigo(), existenciaArticulo.getUnidad().getCodigo());

                if (existenciaArticulo != null) {

                    existencia = ManejoExistenciaArticulo.getInstancia()
                            .getExistenciaArticulosPorMovimiento(det.getArticulo().getCodigo(), det.getAlmacenOrigen().getCodigo());
//                    if (Objects.equals(det.getUnidad().getCodigo(), existenciaArticulo.getUnidad().getCodigo())) {//undad minima de inventario
//
//                         
                    existenciaArticulo.setExistenciaAnterior(existenciaArticulo.getExistencia());
// 
                    existenciaArticulo.setExistencia(existencia);

//                    if (Objects.equals(det.getUnidad().getCodigo(), existenciaArticulo.getUnidad().getCodigo())) {//undad minima de inventario
//
//                        existenciaArticulo.setExistenciaAnterior(existenciaArticulo.getExistencia());
//
//                        existenciaArticulo.setExistencia(existenciaArticulo.getExistencia() - det.getCantidad());
//
//                    } else {
//
//                        existenciaArticulo.setExistenciaAnterior(existenciaArticulo.getExistencia());
//
//                        existenciaArticulo.setExistencia(existenciaArticulo.getExistencia() - (det.getCantidad() * au.getCantidadUnidades()));
//                    }
                    //Actualizamos el costo a la unidad de entrada base
                    existenciaArticulo.setFecha(new Date());

                    getInstancia().actualizar(existenciaArticulo);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void actualizarExistenciaPorAjuste(List<DetalleAjusteInventario> lista, int tipoAjuste) {

        try {

            Double existencia = 0.00;
            for (DetalleAjusteInventario det : lista) {

                ExistenciaArticulo exisArticulo = getExistenciaArticulo(det.getArticulo().getCodigo(), det.getAlmacen().getCodigo());

                if (exisArticulo != null) {

                    existencia = ManejoExistenciaArticulo.getInstancia()
                            .getExistenciaArticulosPorMovimiento(det.getArticulo().getCodigo(), det.getAlmacen().getCodigo());

                    Articulo articulo = exisArticulo.getArticulo();

//                    ArticuloUnidad articuloUnidad = ManejoArticuloUnidad.getInstancia()
//                            .getArticuloUnidadSslida(articulo.getCodigo(), det.getUnidad().getCodigo());
                    exisArticulo.setExistenciaAnterior(exisArticulo.getExistencia());

                    articulo.setPrecioCompraAnterior(articulo.getPrecioCompraUnitario());

                    System.out.println(" det.getCantidadAjustada() " + det.getCantidadAjustada());

                    exisArticulo.setExistencia(existencia);

                    exisArticulo.setFecha(new Date());

                    articulo.setExistencia(existencia);
                    getInstancia().actualizar(exisArticulo);
                    ManejoArticulo.getInstancia().actualizar(articulo);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarExistenciaPorDevolucion(List<DetalleDevolucionDeInventario> lista) {

        try {

            for (DetalleDevolucionDeInventario det : lista) {

                ExistenciaArticulo exisArticulo = getExistenciaArticulo(det.getArticulo().getCodigo(), det.getAlmacen().getCodigo());

                System.out.println("articuloUnidad.getFatorVenta() " + exisArticulo.getArticulo().getDescripcion());
                if (exisArticulo != null) {

                    Articulo articulo = exisArticulo.getArticulo();

//                    ArticuloUnidad articuloUnidad = ManejoArticuloUnidad.getInstancia()
//                            .getArticuloUnidadEntrada(articulo.getCodigo(), det.getArticulo().getUnidadEntrada().getCodigo());
                    exisArticulo.setExistenciaAnterior(exisArticulo.getExistencia());

                    articulo.setPrecioCompraAnterior(articulo.getPrecioCompraUnitario());

                    System.out.println(" det.getCantidad() " + det.getCantidad());
//                    if (tipoAjuste == 1) {
                    exisArticulo.setExistencia(exisArticulo.getExistencia() - det.getCantidad());
//                        exisArticulo.setExistencia(exisArticulo.getExistencia() - (det.getCantidad()* articuloUnidad.getFatorVenta()));
//
//                    } else if (tipoAjuste == 2) {
//
//                        exisArticulo.setExistencia(exisArticulo.getExistencia() - (det.getCantidadAjustada() * articuloUnidad.getFatorVenta()));
//                    }

                    exisArticulo.setFecha(new Date());

                    articulo.setExistencia(exisArticulo.getExistencia());
                    getInstancia().actualizar(exisArticulo);
                    ManejoArticulo.getInstancia().actualizar(articulo);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarExistenciaPorAnular(List<DetalleEntradaInventario> lista) {

        try {

            for (DetalleEntradaInventario det : lista) {

                System.out.println("articulo " + det.getArticulo().getCodigo() + " alm " + det.getArticulo().getAlmacen());
                ExistenciaArticulo exisArticulo = getExistenciaArticulo(det.getArticulo().getCodigo(), det.getAlmacen().getCodigo());

                ArticuloUnidad au = ManejoArticuloUnidad.getInstancia().getArticuloUnidadEntrada(det.getArticulo().getCodigo(), (int) det.getUnidadSalida());

                if (exisArticulo != null) {

                    System.out.println("Entro actualizar el articulo "
                            + " " + exisArticulo.getArticulo().getCodigo() + " "
                            + exisArticulo.getAlmacen().getCodigo() + " cant Unidad " + det.getArticulo().getCantidadUnidades());
//
                    exisArticulo.setExistenciaAnterior(exisArticulo.getExistencia());

                    Articulo articulo = exisArticulo.getArticulo();

                    articulo.setPrecioCompraAnterior(articulo.getPrecioCompraUnitario());

                    if (Objects.equals(det.getUnidad().getCodigo(), (int) det.getUnidadSalida())) {
//

                        System.out.println("Unidad de entrad es igual a la unidad de salida  " + exisArticulo.getExistencia());

                        System.out.println(" CUnidad de entrad es igual a la unidad de salida  " + det.getUnidad() + " us  " + det.getUnidadSalida());

                        exisArticulo.setExistencia(exisArticulo.getExistencia() - det.getCantidadRecibida());

                        System.out.println("Existencia nueva en unidad de salida " + exisArticulo.getExistencia());

                    } else {

                        System.out.println("Existencia actual en unidad de salida " + exisArticulo.getExistencia());
                        System.out.println(" CUnidad de entrad es diferente  a la unidad de salida  " + det.getUnidad() + " us " + det.getUnidadSalida());

//                      
                        exisArticulo.setExistencia(exisArticulo.getExistencia() - (det.getCantidadRecibida() * au.getCantidadUnidades()));

                        System.out.println("Existencia nueva en unidad de salida " + exisArticulo.getExistencia());
                    }

//                    if (Objects.equals(det.getUnidad(),au.getUnidad() )) {
////
//
//                        System.out.println("Existencia actual en unidad de salida " + exisArticulo.getExistencia());
//                        System.out.println(" Cantidad Recibida  " + det.getCantidadRecibida());
//                        
//                    
////                      
//                        exisArticulo.setExistencia(exisArticulo.getExistencia() - (det.getCantidadRecibida() * au.getCantidadUnidades()));
//
//                        System.out.println("Existencia nueva en unidad de salida " + exisArticulo.getExistencia());
//
//                    } else if (Objects.equals(det.getUnidad(), det.getUnidadSalida())) {
//
//                        System.out.println("Existencia actual en unidad de salida " + exisArticulo.getExistencia());
//                        System.out.println(" Cantidad Recibida  " + det.getCantidadRecibida());
////                        
//                        exisArticulo.setExistencia(exisArticulo.getExistencia() - det.getCantidadRecibida());
////
//                    }
                    exisArticulo.setFecha(new Date());

                    articulo.setExistencia(exisArticulo.getExistencia());
                    getInstancia().actualizar(exisArticulo);
                    ManejoArticulo.getInstancia().actualizar(articulo);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarExistenciaPorInventarioInicial(List<DetalleInventarioFinal> lista) {

        try {

            for (DetalleInventarioFinal det : lista) {

                System.out.println("articulo " + det.getArticulo().getCodigo() + " alm " + det.getAlmacen());

                ExistenciaArticulo exisArt = ManejoArticulo.getInstancia()
                        .getExistenciaArticulo(det.getArticulo().getCodigo(), det.getAlmacen().getCodigo());

                if (exisArt != null) {

                    exisArt.setExistenciaAnterior(exisArt.getExistencia());

                    //Obtenemos el factor de venata para expresar el inventario en la uniudad minima                     
                    ArticuloUnidad articuloUnidad = ManejoArticuloUnidad.getInstancia()
                            .getArticuloUnidadEntrada(det.getArticulo().getCodigo(), det.getUnidad().getCodigo());

                    exisArt.setExistencia(exisArt.getExistencia() + (det.getExistencia() * articuloUnidad.getFatorVenta()));

                    exisArt.setFecha(new Date());
                    Articulo art = exisArt.getArticulo();

                    art.setExistencia(exisArt.getExistencia());

                    getInstancia().actualizar(exisArt);
                    ManejoArticulo.getInstancia().actualizar(art);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarExistencia(List<DetalleEntradaInventario> lista, String movimiento) {

        try {

            for (DetalleEntradaInventario det : lista) {

                ExistenciaArticulo exisArt = ManejoArticulo.getInstancia()
                        .getExistenciaArticulo(det.getArticulo().getCodigo(), det.getArticulo().getAlmacen());

                if (exisArt != null) {

                    if (Objects.equals(det.getUnidad(), det.getArticulo().getUnidadEntrada())) {

                    } else if (Objects.equals(det.getUnidad(), det.getArticulo().getUnidadSalida())) {

                    }

                    Double precioVenta, margenUtilidad;
                    System.out.println("Entro actualizar"
                            + " " + exisArt.getArticulo().getCodigo() + " " + exisArt.getAlmacen().getCodigo());

                    exisArt.setExistenciaAnterior(exisArt.getExistencia());

                    if (movimiento.equalsIgnoreCase("salida")) {

                        exisArt.setExistencia(exisArt.getExistencia() - det.getCantidadRecibida());
                    }

                    if (movimiento.equalsIgnoreCase("entrada")) {

                        exisArt.setExistencia(exisArt.getExistencia() + det.getCantidadRecibida() * det.getArticulo().getCantidadUnidades());
                    }

                    exisArt.setFecha(new Date());
                    Articulo art = exisArt.getArticulo();
                    precioVenta = ClaseUtil.getPreciodeVenta(det.getPrecio(), art.getPorcientoUtilidad1());
                    margenUtilidad = ClaseUtil.getMargenUtilidad(det.getPrecio(), art.getPrecioVenta1());

                    art.setExistencia(exisArt.getExistencia());
                    art.setPrecioCompraAnterior(art.getPrecioCompra());
                    art.setPrecioCompra(det.getPrecio());
                    art.setPrecioVenta1(precioVenta);
                    art.setMargenBeneficio1(margenUtilidad);

                    getInstancia().actualizar(exisArt);
                    ManejoArticulo.getInstancia().actualizar(art);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarExistenciaPorAnulacionPedido(List<DetallePedido> lista) {

        try {

            double existenciaActual = 0.00;

            for (DetallePedido det : lista) {

                ExistenciaArticulo exisArt = getExistenciaArticulo(det.getArticulo().getCodigo(),
                        det.getArticulo().getAlmacen());

                if (exisArt != null) {

                    System.out.println(" Articulo "
                            + " " + exisArt.getArticulo().getCodigo() + "  almacen " + exisArt.getAlmacen().getCodigo());
                    System.out.println(" Existencia " + exisArt.getExistencia());
                    System.out.println(" Cantidad " + det.getCantidad());

                    exisArt.setExistenciaAnterior(exisArt.getExistencia());
                    existenciaActual = ClaseUtil.roundDouble(exisArt.getExistencia() + det.getCantidad(), 2);

                    exisArt.setExistencia(existenciaActual);
                    exisArt.setFecha(new Date());

                    Articulo art = exisArt.getArticulo();
                    art.setPrecioVentaAnterior(art.getPrecioVenta1());
                    art.setPrecioVenta1(det.getPrecio());
                    art.setExistencia(exisArt.getExistencia());

                    getInstancia().actualizar(exisArt);
                    ManejoArticulo.getInstancia().actualizar(art);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void devolverExistenciaPorModificaciondePedido(List<DetallePedido> lista) {

        try {

            double existenciaActual = 0.00;

            for (DetallePedido det : lista) {

                ExistenciaArticulo exisArt = getExistenciaArticulo(det.getArticulo().getCodigo(),
                        det.getArticulo().getAlmacen());

                if (exisArt != null) {

                    System.out.println(" Articulo "
                            + " " + exisArt.getArticulo().getCodigo() + "  almacen " + exisArt.getAlmacen().getCodigo());
                    System.out.println(" Existencia " + exisArt.getExistencia());
                    System.out.println(" Cantidad " + det.getCantidad());

                    exisArt.setExistenciaAnterior(exisArt.getExistencia());
                    existenciaActual = ClaseUtil.roundDouble(exisArt.getExistencia() + det.getCantidad(), 2);

                    exisArt.setExistencia(existenciaActual);
                    exisArt.setFecha(new Date());

                    Articulo art = exisArt.getArticulo();
                    art.setPrecioVentaAnterior(art.getPrecioVenta1());
                    art.setPrecioVenta1(det.getPrecio());
                    art.setExistencia(exisArt.getExistencia());

                    getInstancia().actualizar(exisArt);
                    ManejoArticulo.getInstancia().actualizar(art);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarExistenciaPorSalidaDePedido(List<DetallePedido> lista) {

        try {

            double existenciaActual = 0.00;

            for (DetallePedido det : lista) {

                ExistenciaArticulo exisArt = getExistenciaArticulo(det.getArticulo().getCodigo(),
                        det.getArticulo().getAlmacen());

                if (exisArt != null) {

                    System.out.println(" Articulo "
                            + " " + exisArt.getArticulo().getCodigo() + "  almacen " + exisArt.getAlmacen().getCodigo());
                    System.out.println(" Existencia " + exisArt.getExistencia());
                    System.out.println(" Cantidad " + det.getCantidad());

                    exisArt.setExistenciaAnterior(exisArt.getExistencia());
                    existenciaActual = ClaseUtil.roundDouble(exisArt.getExistencia() - det.getCantidad(), 2);

                    exisArt.setExistencia(existenciaActual);
                    exisArt.setFecha(new Date());

                    Articulo art = exisArt.getArticulo();
                    art.setPrecioVentaAnterior(art.getPrecioVenta1());
                    art.setPrecioVenta1(det.getPrecio());
                    art.setExistencia(exisArt.getExistencia());

                    getInstancia().actualizar(exisArt);
                    ManejoArticulo.getInstancia().actualizar(art);

                } else {

                    System.out.println("No Entro actualizar ");
                }
            }

//            for (DetallePedido det : lista) {
//
//                ExistenciaArticulo exisArt = getExistenciaArticulo(det.getArticulo().getCodigo(),
//                        det.getArticulo().getAlmacen());
//
//                if (exisArt != null) {
//
//                    System.out.println("Entro actualizar"
//                            + " " + exisArt.getArticulo().getCodigo() + " " + exisArt.getAlmacen().getCodigo());
//
//                    exisArt.setExistenciaAnterior(exisArt.getExistencia());
//                    existenciaActual = ClaseUtil.roundDouble(exisArt.getExistencia() - det.getCantidad(), 2);
//
//                    exisArt.setExistencia(existenciaActual);
//                    exisArt.setFecha(new Date());
//
//                    Articulo art = exisArt.getArticulo();
//                    art.setPrecioVentaAnterior(art.getPrecioVenta1());
//                    art.setPrecioVenta1(det.getPrecio());
//                    art.setExistencia(exisArt.getExistencia());
//
//                    getInstancia().actualizar(exisArt);
//                    ManejoArticulo.getInstancia().actualizar(art);
//
//                } else {
//
//                    System.out.println("No Entro actualizar ");
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {

            System.out.println(ManejoExistenciaArticulo.getInstancia().getExistenciaArticulosPorMovimiento(987654331, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Class getReferencia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
