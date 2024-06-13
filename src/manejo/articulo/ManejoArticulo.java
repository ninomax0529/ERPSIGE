/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.articulo;

import dto.articulo.ArticuloDTO;
import dto.articulo.EntradaArticulo;
import dto.articulo.SalidaArticulo;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.ExistenciaArticulo;
import org.hibernate.Session;
import reporte.factura.RptFactura;
import reporte.factura.RptFacturaIghTrack;
import reporte.factura.RptFacturaPinturaTriplea;
import reporte.inventario.RptEntradaInventario;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoArticulo extends ManejoEstandar<Articulo> {

    private static ManejoArticulo manejoArticulo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoArticulo getInstancia() {
        if (manejoArticulo == null) {
            manejoArticulo = new ManejoArticulo();
        }
        return manejoArticulo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Articulo> getListaArticulo() {

        String query = "  SELECT * from\n"
                + "articulo a,\n"
                + " existencia_articulo  ea ,almacen al\n"
                + "\n"
                + "  where a.codigo=ea.articulo  and al.codigo=ea.almacen  and a.unidad_de_negocio=:ug ";

        return session.createSQLQuery(query)
                .addEntity(Articulo.class)
                .setParameter("ug", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

    }

    public List<Articulo> getListaArticulos() {

        String query = " SELECT * from  articulo a where  a.unidad_de_negocio=:ug ";

        return session.createSQLQuery(query)
                .addEntity(Articulo.class)
                .setParameter("ug", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

    }

    public List<Articulo> getListaServicioEquipo() {

        String query = " SELECT * from  articulo a where  a.unidad_de_negocio=:ug ";

        return session.createSQLQuery(query)
                .addEntity(Articulo.class)
                .setParameter("ug", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

    }

    //Para despachar
    public List<Articulo> getListaArticuloPorUnidadDeNegocio() {

        String query = " SELECT * from\n"
                + "articulo a,\n"
                + " existencia_articulo  ea ,almacen al\n"
                + "\n"
                + "  where a.codigo=ea.articulo  and al.codigo=ea.almacen  and a.unidad_de_negocio=:ug  and   al.despacho=true ";

        return session.createSQLQuery(query)
                .addEntity(Articulo.class)
                .setParameter("ug", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

    }

    //Orgen significa si la llamada es de 
    public List<Articulo> getListaArticulos(int origen) {

        System.out.println("Origen " + origen);
        String query = "";

        if (origen == 1) {
            query = " SELECT * FROM articulo ";
            System.out.println("Origen " + origen + " " + query);
        } else if (origen == 2) {
            query = " SELECT * FROM articulo where para_consumo=false ";
            System.out.println("Origen " + origen + " " + query);
        } else if (origen == 3) {

            query = " SELECT * FROM articulo where para_consumo=true ";
            System.out.println("Origen " + origen + " " + query);
        }

        return session.createSQLQuery(query).addEntity(Articulo.class)
                .list();

    }

    //Orgen significa si la llamada es de 
    public List<Articulo> getListaAticuloPorTipo(String tipo) {

        String query = "";

        query = " SELECT * from  articulo a where a.tipo_articulo in(" + tipo + ") "
                + "  and a.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() + " ";
        System.out.println("Origen " + 0 + " " + query);

        return session.createSQLQuery(query)
                .addEntity(Articulo.class)
                .list();

    }

    //Orgen significa si la llamada es de 
    public List<Articulo> getListaEquipoGps(String tipo) {

        String query = "";

        query = " SELECT * from  articulo a where  categoria=3  and sub_categoria=19  and  a.tipo_articulo in(" + tipo + ") "
                + "  and a.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() + " ";

        System.out.println("Origen " + 0 + " " + query);

        System.out.println("query " + query);

        return session.createSQLQuery(query)
                .addEntity(Articulo.class)
                .list();

    }

    public List<Articulo> getListaArticulo(int estado) {

        String query = "SELECT * FROM  Articulo  where estado=:estado";

        return session.createSQLQuery(query).addEntity(Articulo.class).setParameter("estado", estado).list();

    }

    public List<Articulo> getListaArticulo(String art) {

//        String query = " SELECT * FROM  Articulo  where nombre like '%"+art+"%' or codigo like '%"+art+"%'";
        String query = " SELECT * FROM  Articulo  where  nombre  like '%" + art + "%' or codigo like '%" + art + "%' and  para_consumo=false ";

        return session.createSQLQuery(query).addEntity(Articulo.class).list();

    }

    public Articulo getArticulo(int articulo) {

        String query = "SELECT * FROM  Articulo  where codigo=:articulo";

        return (Articulo) session.createSQLQuery(query)
                .addEntity(Articulo.class)
                .setParameter("articulo", articulo).uniqueResult();

    }

    public List<Articulo> getArticuloPorCategoria(int categoria) {

        String query = "SELECT * FROM  Articulo  where categoria=:categoria  and para_consumo=false ";

        return session.createSQLQuery(query).addEntity(Articulo.class).setParameter("categoria", categoria).list();

    }

    public List<ArticuloUnidad> getArticuloUnidad(int articulo) {

        String query = " SELECT * FROM  articulo_unidad  where articulo=:articulo ";

        session.clear();
        session.flush();

        return session.createSQLQuery(query).addEntity(ArticuloUnidad.class).setParameter("articulo", articulo).list();

    }

    public List<Articulo> getArticuloPorCodigo(int codigo) {

        String query = "SELECT * FROM  articulo  where codigo=:codigo";

        return session.createSQLQuery(query).addEntity(Articulo.class).setParameter("codigo", codigo).list();

    }

    public List<Articulo> getArticulos() {

        String query = "SELECT * FROM  Articulo";

        return session.createSQLQuery(query).addEntity(Articulo.class).list();

    }

    public List<Articulo> getArticuloPorSubCategoria(int subCategoria) {

        String query = "SELECT * FROM  Articulo  where sub_categoria=:subcategoria  and para_consumo=false";

        return session.createSQLQuery(query)
                .addEntity(Articulo.class)
                .setParameter("subcategoria", subCategoria)
                .list();

    }

    public ExistenciaArticulo getAlmacen(int codigo) {

        ExistenciaArticulo existenciaArticulo;
        String query = "SELECT * FROM existencia_articulo where  almacen=:codigo ";

        existenciaArticulo = (ExistenciaArticulo) session.createSQLQuery(query)
                .addEntity(ExistenciaArticulo.class).setParameter("codigo", codigo).uniqueResult();

        return existenciaArticulo;
    }

    public Articulo getArticulo() {

        Articulo articulo = new Articulo();

        String query = "SELECT * FROM  articulo   where codigo=? ";

        try {

            PreparedStatement ps = Conexion.getInsatancia().getConnectionDB().prepareStatement(query);

            ps.setInt(1, 17);

            ResultSet rs = ps.executeQuery();

            if (1 == 1) {

                Blob blob = rs.getBlob("imagen");

//                articulo.set(blob.getBytes(1, (int) blob.length()));
            } else {

                System.out.println("el objeto esta nulo");
            }

        } catch (SQLException ex) {

            System.out.println("el objeto esta nulo");
            ex.printStackTrace();
            Logger.getLogger(ManejoArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return articulo;

    }

    public ExistenciaArticulo getExistenciaArticulo(int articulo, int almacen) {

        ExistenciaArticulo existenciaArticulo;
        String query = "SELECT * FROM existencia_articulo "
                + "  where  articulo=:articulo and   almacen=:almacen ";

        existenciaArticulo = (ExistenciaArticulo) session.createSQLQuery(query)
                .addEntity(ExistenciaArticulo.class)
                .setParameter("articulo", articulo)
                .setParameter("almacen", almacen)
                .uniqueResult();

        return existenciaArticulo;
    }

    @Override
    public Class getReferencia() {
        return Articulo.class;
    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(numero)+1,0) from articulo ";

        return (BigInteger) session.createSQLQuery(query).uniqueResult();

    }

    public BigInteger getCodigo() {

        String query = " select ifnull(max(codigo)+1,0) from articulo where unidad_de_negocio=:ung ";

        return (BigInteger) session.createSQLQuery(query)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .uniqueResult();

    }

    public List<EntradaArticulo> getListaEntradaArticuloDTO(int articulo, Date fechaini, Date fechafin) {

        List<EntradaArticulo> listaDTO = new ArrayList<>();
        try {

            String sql = "select  e.codigo as entrada,e.fecha, case when e.anulada then 'SI' else 'NO' end as anulada,\n"
                    + " de.cantidad_recibida as cantidad,u.descripcion as unidad, CONCAT(a.codigo,'-',a.descripcion) as nombre\n"
                    + ",round(de.precio,4) as costo,round(de.cantidad_recibida*de.precio,4) as costo_total\n"
                    + "\n"
                    + " from  entrada_inventario  e  inner join \n"
                    + "\n"
                    + "  detalle_entrada_inventario de on  e.codigo=de.entrada_inventario\n"
                    + "\n"
                    + "  INNER JOIN articulo a  on  a.codigo=de.articulo\n"
                    + "\n"
                    + "   inner join unidad u on u.codigo=de.unidad\n"
                    + "   where   e.fecha   between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaini)
                    + "'   and  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "' and a.codigo=" + articulo;

            PreparedStatement ps = Conexion.getInsatancia().getConnectionDB().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            EntradaArticulo entradaArticulo;

            while (rs.next()) {

                entradaArticulo = new EntradaArticulo();

//                entradaArticulo.setCodigo(rs.getInt("codigo"));
                entradaArticulo.setNombre(rs.getString("nombre"));
                entradaArticulo.setEntrada(rs.getInt("entrada"));
                entradaArticulo.setFecha(rs.getDate("fecha"));
                entradaArticulo.setAnulada(rs.getString("anulada"));
                entradaArticulo.setCantidad(rs.getDouble("cantidad"));
                entradaArticulo.setUnidad(rs.getString("unidad"));
                entradaArticulo.setCostoUnitario(rs.getDouble("costo"));
                entradaArticulo.setCostoTotal(rs.getDouble("costo_total"));
                Button btn = new Button("Ver");
                btn.setPrefHeight(30);
                btn.setCursor(Cursor.HAND);
                btn.setStyle("  -fx-background-color: #5CB85C;\n"
                        + "    -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C);\n"
                        + "    -fx-border-color: -fx-secondary;\n"
                        + "    -fx-border-radius: 10px;\n"
                        + "     -fx-text-fill:ffffff;"
                        + "    -fx-font-size: 10pt;");

                btn.setOnAction((event) -> {

                    RptEntradaInventario.getInstancia().imprimir(Integer.parseInt(btn.getId()));
                });

                btn.setId(entradaArticulo.getEntrada().toString());
                entradaArticulo.setButton(btn);
                listaDTO.add(entradaArticulo);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaDTO;
    }

    public List<EntradaArticulo> getListaEntradaArticuloDTO(int articulo) {

        List<EntradaArticulo> listaDTO = new ArrayList<>();
        try {

            String sql = "select  e.codigo as entrada,e.fecha, case when e.anulada then 'SI' else 'NO' end as anulada,\n"
                    + " de.cantidad_recibida as cantidad,u.descripcion as unidad, CONCAT(a.codigo,'-',a.descripcion) as nombre\n"
                    + ",round(de.precio,4) as costo,round(de.cantidad_recibida*de.precio,4) as costo_total\n"
                    + "\n"
                    + " from  entrada_inventario  e  inner join \n"
                    + "\n"
                    + "  detalle_entrada_inventario de on  e.codigo=de.entrada_inventario\n"
                    + "\n"
                    + "  INNER JOIN articulo a  on  a.codigo=de.articulo\n"
                    + "\n"
                    + "   inner join unidad u on u.codigo=de.unidad\n "
                    + "   where   a.codigo=" + articulo;

            PreparedStatement ps = Conexion.getInsatancia().getConnectionDB().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            EntradaArticulo entradaArticulo;

            while (rs.next()) {

                entradaArticulo = new EntradaArticulo();

//                entradaArticulo.setCodigo(rs.getInt("codigo"));
                entradaArticulo.setNombre(rs.getString("nombre"));
                entradaArticulo.setEntrada(rs.getInt("entrada"));
                entradaArticulo.setFecha(rs.getDate("fecha"));
                entradaArticulo.setAnulada(rs.getString("anulada"));
                entradaArticulo.setCantidad(rs.getDouble("cantidad"));
                entradaArticulo.setUnidad(rs.getString("unidad"));
                entradaArticulo.setCostoUnitario(rs.getDouble("costo"));
                entradaArticulo.setCostoTotal(rs.getDouble("costo_total"));
                Button btn = new Button("Ver");
                btn.setPrefHeight(30);
                btn.setCursor(Cursor.HAND);
                btn.setStyle("  -fx-background-color: #5CB85C;\n"
                        + "    -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C);\n"
                        + "    -fx-border-color: -fx-secondary;\n"
                        + "    -fx-border-radius: 10px;\n"
                        + "     -fx-text-fill:ffffff;"
                        + "    -fx-font-size: 10pt;");

                btn.setOnAction((event) -> {

                    RptEntradaInventario.getInstancia().imprimir(Integer.parseInt(btn.getId()));
                });

                btn.setId(entradaArticulo.getEntrada().toString());
                entradaArticulo.setButton(btn);
                listaDTO.add(entradaArticulo);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaDTO;
    }

    public List<ArticuloDTO> getListaArticuloDTO() {

        List<ArticuloDTO> listaDTO = new ArrayList<>();
        try {

            String sql = " SELECT \n"
                    + " a.codigo,a.nombre,\n"
                    + " u.descripcion as unidad,\n"
                    + " cat.descripcion as categoria,"
                    + "a.precio_compra_unitario as costoUnitario,\n"
                    + "a.existencia,\n"
                    + "ifnull(lpg.precio,0) as plg,\n"
                    + "ifnull(lpm.precio,0) as plm,ifnull(lpn.precio,0) as pln,\n"
                    + "ifnull(lpd.precio,0) as pld\n"
                    + " from articulo a \n"
                    + " inner join articulo_unidad au on a.codigo=au.articulo "
                    + " inner join unidad u on u.codigo=au.unidad "
                    + " inner join categoria cat on a.categoria=cat.codigo "
                    + " LEFT JOIN \n"
                    + " ( SELECT articulo, precio from lista_de_precio lp,detalle_lista_de_precio dlp where lp.codigo=dlp.lista_de_precio \n"
                    + "                 and  lp.habilitada=true  and  lp.tipo_venta=4 ) as lpg on a.codigo=lpg.articulo\n"
                    + "\n"
                    + "LEFT JOIN\n"
                    + "\n"
                    + " ( SELECT articulo, precio from lista_de_precio lp,detalle_lista_de_precio dlp where lp.codigo=dlp.lista_de_precio \n"
                    + "                 and  lp.habilitada=true  and  lp.tipo_venta=1  ) as lpm on a.codigo=lpm.articulo\n"
                    + "\n"
                    + "LEFT JOIN\n"
                    + "\n"
                    + " ( SELECT articulo, precio from lista_de_precio lp,detalle_lista_de_precio dlp where lp.codigo=dlp.lista_de_precio \n"
                    + "                 and  lp.habilitada=true  and  lp.tipo_venta=2  ) as lpn on a.codigo=lpn.articulo\n"
                    + "\n"
                    + "LEFT JOIN\n"
                    + "\n"
                    + "( SELECT articulo, precio from lista_de_precio lp,detalle_lista_de_precio dlp where lp.codigo=dlp.lista_de_precio \n"
                    + "  and  lp.habilitada=true  and  lp.tipo_venta=3  ) as lpd on a.codigo=lpd.articulo  order by  a.codigo ";

            PreparedStatement ps = Conexion.getInsatancia().getConnectionDB().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            ArticuloDTO articuloDTO;

            while (rs.next()) {

                articuloDTO = new ArticuloDTO();

                articuloDTO.setCodigo(rs.getInt("codigo"));
                articuloDTO.setNombre(rs.getString("nombre"));
                articuloDTO.setUnidad(rs.getString("unidad"));
                articuloDTO.setCategoria(rs.getString("categoria"));
                articuloDTO.setExistencia(rs.getDouble("existencia"));

                articuloDTO.setCostoUnitario(rs.getDouble("costoUnitario"));
                articuloDTO.setPrecioVentaLpg(rs.getDouble("plg"));
                articuloDTO.setPrecioVentaLpm(rs.getDouble("plm"));
                articuloDTO.setPrecioVentaLpn(rs.getDouble("pln"));
                articuloDTO.setPrecioVentaLpd(rs.getDouble("pld"));

                listaDTO.add(articuloDTO);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaDTO;
    }

    public List<ArticuloDTO> getListaArticuloDTO(Articulo articulo) {

        List<ArticuloDTO> listaDTO = new ArrayList<>();
        try {

//            String sql = " SELECT lp.nombre as nombre_lista,\n"
//                    + " dlp.costo_unitario,dlp.precio,u.descripcion as unidad_venta \n"
//                    + " from lista_de_precio lp,detalle_lista_de_precio dlp,\n"
//                    + "  unidad u\n"
//                    + " where  lp.codigo=dlp.lista_de_precio \n"
//                    + "  and u.codigo=dlp.unidad_salida \n"
//                    + "    and  lp.habilitada=true and  articulo="+articulo.getCodigo();
            String sql = "   SELECT lp.codigo as codigo_lp,au.unidad, ea.almacen,dlp.articulo, lp.nombre as nombre_lista,\n"
                    + " dlp.costo_unitario,dlp.precio,u.descripcion as unidad_venta,\n"
                    + "   round(IFNULL(ea.existencia/au.fator_venta,ea.existencia),2) as existencia\n"
                    + " from lista_de_precio lp,detalle_lista_de_precio dlp, almacen alm,\n"
                    + "  unidad u,articulo_unidad au,existencia_articulo ea\n"
                    + " where  lp.codigo=dlp.lista_de_precio \n"
                    + "  and  u.codigo=dlp.unidad_salida and dlp.habilitado=true \n"
                    + "  and u.codigo=au.unidad \n"
                    + "  and au.articulo=dlp.articulo\n"
                    + " and  lp.habilitada=true "
                    + "  and alm.codigo=ea.almacen  and alm.despacho=true "
                    + " and  ea.articulo=au.articulo and dlp.articulo=" + articulo.getCodigo()
                    + "  and ea.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

            PreparedStatement ps = Conexion.getInsatancia().getConnectionDB().prepareStatement(sql);

            System.out.println("sql articulo pv " + sql);

            System.out.println("ArticuloDTO ");
            ResultSet rs = ps.executeQuery();

            ArticuloDTO articuloDTO;

            while (rs.next()) {

                articuloDTO = new ArticuloDTO();
//
                articuloDTO.setCostoUnitario(rs.getDouble("costo_unitario"));
                articuloDTO.setNombre(rs.getString("nombre_lista"));
                articuloDTO.setUnidad(rs.getString("unidad_venta"));
                articuloDTO.setPrecioVenta(rs.getDouble("precio"));
                articuloDTO.setAlmacen(rs.getInt("almacen"));
                articuloDTO.setCodigo(rs.getInt("articulo"));

                Double existencia = ManejoExistenciaArticulo.getInstancia().getExistenciaArticulosPorMovimiento(articuloDTO.getCodigo(), articuloDTO.getAlmacen());

                articuloDTO.setExistencia(existencia);
//                articuloDTO.setExistencia(rs.getDouble("existencia"));

                articuloDTO.setListaDePrecio(rs.getInt("codigo_lp"));
                articuloDTO.setCodigoUnidad(rs.getInt("unidad"));

                listaDTO.add(articuloDTO);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaDTO;
    }

    public List<ArticuloDTO> getExistenciaArticuloArticuloDTO(Articulo articulo) {

        List<ArticuloDTO> listaDTO = new ArrayList<>();
        try {

            String sql = "  SELECT \n"
                    + "   alm.nombre as almacen,\n"
                    + "                    u.descripcion as unidad,\n"
                    + "                    round(IFNULL(ea.existencia/au.fator_venta,ea.existencia),2) as existencia "
                    + "                     from \n"
                    + "\n"
                    + "                       almacen alm,\n"
                    + "                      unidad u,articulo_unidad au,existencia_articulo ea,\n"
                    + "                     articulo art\n"
                    + "                     where\n"
                    + "                    \n"
                    + "                       u.codigo=au.unidad \n"
                    + "                      and au.articulo=art.codigo\n"
                    + "                   \n"
                    + "                     and alm.codigo=ea.almacen \n"
                    + "                    and  ea.articulo=au.articulo \n"
                    + "                    and art.codigo=ea.articulo\n"
                    + "                   and  art.codigo=" + articulo.getCodigo()
                    + "   and  ea.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

            PreparedStatement ps = Conexion.getInsatancia().getConnectionDB().prepareStatement(sql);

            System.out.println("sql " + sql);

            System.out.println("ArticuloDTO ");
            ResultSet rs = ps.executeQuery();

            ArticuloDTO articuloDTO;

            while (rs.next()) {

                articuloDTO = new ArticuloDTO();
//
                articuloDTO.setUnidad(rs.getString("unidad"));
               
                Double existencia = ManejoExistenciaArticulo.getInstancia().getExistenciaArticulosPorMovimiento(articuloDTO.getCodigo(), articuloDTO.getAlmacen());

                articuloDTO.setExistencia(existencia);
//                articuloDTO.setExistencia(rs.getDouble("existencia"));
                System.out.println(" Existencia " + articuloDTO.getExistencia());
                articuloDTO.setNombreAlmacen(rs.getString("almacen"));

                listaDTO.add(articuloDTO);

            }

        } catch (Exception e) {
            System.out.println("error");
        }

        return listaDTO;
    }

    public List<ArticuloDTO> getListaDePrecioArticuloDTO(Articulo articulo) {

        List<ArticuloDTO> listaDTO = new ArrayList<>();

        try {

            String sql = "  SELECT  lp.nombre as nombre_lista,\n"
                    + "                     dlp.costo_unitario,dlp.precio,u.descripcion as unidad \n"
                    + "                     from  lista_de_precio lp,detalle_lista_de_precio dlp,\n"
                    + "                      unidad u \n"
                    + "                     where lp.codigo=dlp.lista_de_precio \n"
                    + "                     and   dlp.habilitado=true \n"
                    + "                     and  u.codigo=dlp.unidad_salida \n"
                    + "                     and  lp.habilitada=true "
                    + "                     and  dlp.articulo=" + articulo.getCodigo();

            PreparedStatement ps = Conexion.getInsatancia().getConnectionDB().prepareStatement(sql);

            System.out.println("sql " + sql);

            System.out.println("ArticuloDTO ");
            ResultSet rs = ps.executeQuery();

            ArticuloDTO articuloDTO;

            while (rs.next()) {

                articuloDTO = new ArticuloDTO();
//
                articuloDTO.setCostoUnitario(rs.getDouble("costo_unitario"));
                articuloDTO.setNombre(rs.getString("nombre_lista"));
                articuloDTO.setUnidad(rs.getString("unidad"));
                articuloDTO.setPrecioVenta(rs.getDouble("precio"));

                listaDTO.add(articuloDTO);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaDTO;
    }

    public List<SalidaArticulo> getListaSalidaArticuloDTO(int articulo, Date fechaini, Date fechafin) {

        List<SalidaArticulo> listaDTO = new ArrayList<>();
        try {

            String sql = "select  \n"
                    + "\n"
                    + "  f.codigo as factura,f.fecha,\n"
                    + " case when  f.anulada then 'SI' else 'NO' end as anulada,\n"
                    + "\n"
                    + "df.cantidad,u.descripcion as unidad, CONCAT(a.codigo,'-',a.descripcion) as nombre,df.precio\n"
                    + "\n"
                    + " from  factura  f  inner join \n"
                    + "\n"
                    + "  detalle_factura df on  f.codigo=df.factura\n"
                    + "\n"
                    + "  INNER JOIN  articulo a  on  a.codigo=df.articulo\n"
                    + "\n"
                    + "   inner join unidad u  on u.codigo=df.unidad "
                    + "   where   f.fecha   between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaini)
                    + "'   and  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "' and a.codigo=" + articulo;

            PreparedStatement ps = Conexion.getInsatancia().getConnectionDB().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            SalidaArticulo salidaArticulo;

            while (rs.next()) {

                salidaArticulo = new SalidaArticulo();

//                entradaArticulo.setCodigo(rs.getInt("codigo"));
                salidaArticulo.setNombre(rs.getString("nombre"));
                salidaArticulo.setFactura(rs.getInt("factura"));
                salidaArticulo.setFecha(rs.getDate("fecha"));
                salidaArticulo.setAnulada(rs.getString("anulada"));
                salidaArticulo.setCantidad(rs.getDouble("cantidad"));
                salidaArticulo.setUnidad(rs.getString("unidad"));
                salidaArticulo.setPrecioVenta(rs.getDouble("precio"));

                Button btn = new Button("Ver");

                btn.setPrefHeight(30);

                btn.setCursor(Cursor.HAND);
                btn.setStyle("  -fx-background-color: #5CB85C;\n"
                        + "    -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C);\n"
                        + "    -fx-border-color: -fx-secondary;\n"
                        + "    -fx-border-radius: 10px;\n"
                        + "     -fx-text-fill:ffffff;"
                        + "    -fx-font-size: 10pt;");

                btn.setOnAction((event) -> {

                    if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {

                        RptFacturaPinturaTriplea.getInstancia().verFactura(Integer.parseInt(btn.getId()));

                    } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {

                        RptFacturaIghTrack.getInstancia().verFactura(Integer.parseInt(btn.getId()));
                    }

                    RptFactura.getInstancia().imprimir(Integer.parseInt(btn.getId()));
                });

                btn.setId(salidaArticulo.getFactura().toString());
                salidaArticulo.setButton(btn);

                listaDTO.add(salidaArticulo);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaDTO;
    }

    public List<SalidaArticulo> getListaSalidaArticuloDTO(int articulo) {

        List<SalidaArticulo> listaDTO = new ArrayList<>();
        try {

            String sql = "select  \n"
                    + "\n"
                    + "  f.codigo as factura,f.fecha,\n"
                    + " case when  f.anulada then 'SI' else 'NO' end as anulada,\n"
                    + "\n"
                    + "df.cantidad,u.descripcion as unidad, CONCAT(a.codigo,'-',a.descripcion) as nombre,df.precio\n"
                    + "\n"
                    + " from  factura  f  inner join \n"
                    + "\n"
                    + "  detalle_factura df on  f.codigo=df.factura\n"
                    + "\n"
                    + "  INNER JOIN  articulo a  on  a.codigo=df.articulo\n"
                    + "\n"
                    + "   inner join unidad u  on u.codigo=df.unidad "
                    + "   where  a.codigo=" + articulo;

            PreparedStatement ps = Conexion.getInsatancia().getConnectionDB().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            SalidaArticulo salidaArticulo;

            while (rs.next()) {

                salidaArticulo = new SalidaArticulo();

//                entradaArticulo.setCodigo(rs.getInt("codigo"));
                salidaArticulo.setNombre(rs.getString("nombre"));
                salidaArticulo.setFactura(rs.getInt("factura"));
                salidaArticulo.setFecha(rs.getDate("fecha"));
                salidaArticulo.setAnulada(rs.getString("anulada"));
                salidaArticulo.setCantidad(rs.getDouble("cantidad"));
                salidaArticulo.setUnidad(rs.getString("unidad"));
                salidaArticulo.setPrecioVenta(rs.getDouble("precio"));

                Button btn = new Button("Ver");

                btn.setPrefHeight(30);

                btn.setCursor(Cursor.HAND);
                btn.setStyle("  -fx-background-color: #5CB85C;\n"
                        + "    -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C);\n"
                        + "    -fx-border-color: -fx-secondary;\n"
                        + "    -fx-border-radius: 10px;\n"
                        + "     -fx-text-fill:ffffff;"
                        + "    -fx-font-size: 10pt;");

                btn.setOnAction((event) -> {

                    RptFactura.getInstancia().imprimir(Integer.parseInt(btn.getId()));
                });

                btn.setId(salidaArticulo.getFactura().toString());
                salidaArticulo.setButton(btn);

                listaDTO.add(salidaArticulo);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaDTO;
    }

    public boolean existeSecuencia(int numero) {

        boolean flag = false;

        Articulo articulo = (Articulo) session.createSQLQuery(" SELECT * FROM articulo f  where  "
                + "  f.numero=:numero limit 1 ")
                .addEntity(Articulo.class)
                .setParameter("numero", numero).uniqueResult();

        if (!(articulo == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public static void main(String[] args) {

        try {

            System.out.println("getInstancia().getExistenciaArticuloArticuloDTO(new Articulo(987654384) )"
                    + " " + getInstancia().getExistenciaArticuloArticuloDTO(new Articulo(987654384)).size());
            for (ArticuloDTO adt : getInstancia().getExistenciaArticuloArticuloDTO(new Articulo(987654384))) {
                System.out.println("Existencia " + adt.getExistencia());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

//        for (SalidaArticulo entrada : getInstancia().getListaSalidaArticuloDTO(120, new Date(), new Date())) {
//            System.out.println("Datos " + entrada.getNombre());
//        }
    }

}
