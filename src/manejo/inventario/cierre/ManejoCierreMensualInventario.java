/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.inventario.cierre;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import manejo.articulo.ManejoAlmacen;
import manejo.articulo.ManejoArticulo;
import manejo.unidad.ManejoUnidad;
import modelo.Almacen;
import modelo.Catalogo;
import modelo.CierreMensualInventario;
import modelo.DetalleCierreMensualInventario;
import org.hibernate.Session;
import utiles.ClaseUtil;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoCierreMensualInventario extends ManejoEstandar<CierreMensualInventario> {

    private static ManejoCierreMensualInventario manejoCierreMensualInventario = null;
//    private Session session = HibernateUtil.getSession();

    public static ManejoCierreMensualInventario getInstancia() {
        if (manejoCierreMensualInventario == null) {
            manejoCierreMensualInventario = new ManejoCierreMensualInventario();
        }
        return manejoCierreMensualInventario;
    }

    @Override
    public Session getSession() {
        return HibernateUtil.getSession();
    }

    public List<CierreMensualInventario> getCierreMensualInventario() {

        String query = "SELECT * FROM cierre_mensual_inventario "
                + "  where   unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return getSession().createSQLQuery(query).addEntity(CierreMensualInventario.class).list();

    }

    public List<CierreMensualInventario> getCierreMensualInventario(Date fechaini, Date fechafin) {

        List<CierreMensualInventario> lista = null;

        try {

            String query = "SELECT * FROM cierre_mensual_inventario  where  fecha between '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(fechaini) + "' and '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "' "
                    + " and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
            System.out.println("Query " + query);

            lista = getSession().createSQLQuery(query).addEntity(CierreMensualInventario.class).list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
//        
//        String query = "SELECT * FROM cierre_mensual_inventario  where  fecha between '"
//                + new SimpleDateFormat("yyyy-MM-dd").format(fechaini) + "' and '"
//                + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "'";
//        System.out.println("Query " + query);
//
//        return getSession().createSQLQuery(query).addEntity(CierreMensualInventario.class).list();

    }

    public CierreMensualInventario getCierreMensualInventario(int codigo) {

        CierreMensualInventario cierre;
        String query = "SELECT * FROM cierre_mensual_inventario where  codigo=:codigo "
                + "  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        cierre = (CierreMensualInventario) getSession().createSQLQuery(query).addEntity(Catalogo.class).setParameter("codigo", codigo).uniqueResult();

        return cierre;
    }

    public Boolean existeCierreMensualInventario(Date fecha) {

        Boolean existe = false;
        CierreMensualInventario cierre;
        String query = "SELECT * FROM cierre_mensual_inventario where anulado=false and date(fecha)=:fecha "
                + " and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Fecha " + query);

        cierre = (CierreMensualInventario) getSession().createSQLQuery(query)
                .addEntity(CierreMensualInventario.class).setParameter("fecha", fecha).uniqueResult();

        existe = cierre == null ? false : true;
        return existe;
    }

    public List<DetalleCierreMensualInventario> getDetalleInventario(CierreMensualInventario cierre) {

        List<DetalleCierreMensualInventario> lista;
        String query = "SELECT * FROM detalle_cierre_mensual_inventario  where  cierre_mensual=:cierre ";

        lista = getSession().createSQLQuery(query)
                .addEntity(DetalleCierreMensualInventario.class).setParameter("cierre", cierre.getCodigo()).list();

        return lista;
    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(codigo)+1,0) from cierre_mensual_inventario ";

        return (BigInteger) getSession().createSQLQuery(query).uniqueResult();

    }

    public List<DetalleCierreMensualInventario> getMovimientoArticulo(Date fechaini, Date fechafin, CierreMensualInventario cierre) {

        List<DetalleCierreMensualInventario> listaDTO = new ArrayList<>();

        fechaini = util.ClaseUtil.getFechaPrimerDiasDelMes(fechafin);
        System.out.println("Fecha " + fechaini + " fecha  " + fechafin);
        try {

            String sql = " select  a.codigo, a.nombre,"
                    + "   (a.existencia+ifnull(rs.salida,0)-ifnull(re.entrada,0)) as invini_calculado,\n"
                    + "   ifnull(re.entrada,0) as entrada,ifnull(rs.salida,0) as salida,a.existencia as invfinal, au.unidad  "
                    + "    \n"
                    + "   from articulo a "
                    + "  LEFT JOIN\n"
                    + "     -- Entrada de  inventario     \n"
                    + "    ( SELECT \n"
                    + "     a.codigo as articulo ,sum(det.cantidad_recibida)  as entrada\n"
                    + "   \n"
                    + "    from  entrada_inventario e join detalle_entrada_inventario det \n"
                    + "    on e.codigo=det.entrada_inventario\n"
                    + "     inner join articulo a on det.articulo=a.codigo "
                    + "     where   fecha  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaini)
                    + " '   and  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "' "
                    + "and  e.anulada=false \n"
                    + "\n"
                    + "    GROUP BY a.codigo ) as re on re.articulo=a.codigo\n"
                    + "\n"
                    + "  -- Entrada de  inventario \n"
                    + "\n"
                    + "   left join \n"
                    + "\n"
                    + " -- Salida de  inventario \n"
                    + "    ( SELECT \n"
                    + "       a.codigo as articulo, sum(df.cantidad) as salida\n"
                    + " \n"
                    + "    from  factura  f join detalle_factura df \n"
                    + "\n"
                    + "    on f.codigo=df.factura\n"
                    + "\n"
                    + "     inner join articulo a on df.articulo=a.codigo\n"
                    + "    where   fecha  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaini)
                    + "'   and  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "' "
                    + " and  f.anulada=false \n"
                    + "    GROUP BY a.codigo  ) as rs on rs.articulo=a.codigo "
                    + "     left join   "
                    + "     ( SELECT \n"
                    + "                          au.articulo, u.codigo as unidad \n"
                    + "                   \n"
                    + "                        from  articulo_unidad  au inner join unidad u\n"
                    + "                    \n"
                    + "                      on   au.unidad=u.codigo\n"
                    + "                   \n"
                    + "                                        \n"
                    + "                       where  au.unidad_inventario=1 \n"
                    + "                   ) as au on au.articulo=a.codigo  "
                    + "    order by a.codigo ;\n"
                    + "     -- Salida de  inventario  \n"
                    + "";

            PreparedStatement ps = Conexion.getInsatancia().getConnectionDB().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            DetalleCierreMensualInventario detCierreMensual;
            Double costo = 0.00;

            while (rs.next()) {

                detCierreMensual = new DetalleCierreMensualInventario();

                detCierreMensual.setArticulo(ManejoArticulo.getInstancia().getArticulo(rs.getInt("codigo")));
                detCierreMensual.setDescripcionArticulo(rs.getString("nombre"));
                detCierreMensual.setInventarioInicial(ClaseUtil.roundDouble(rs.getDouble("invini_calculado"), 2));
                detCierreMensual.setInventarioFinal(ClaseUtil.roundDouble(rs.getDouble("invfinal"), 2));
                detCierreMensual.setAlmacen(new Almacen(1));
                detCierreMensual.setCantidadAjustada(0.00);
                detCierreMensual.setCantidadEntrada(ClaseUtil.roundDouble(rs.getDouble("entrada"), 2));
                detCierreMensual.setCantidadSalida(ClaseUtil.roundDouble(rs.getDouble("salida"), 2));
                detCierreMensual.setUnidadSalida(ManejoUnidad.getInstancia().getUnidad(rs.getInt("unidad")).getCodigo());
                detCierreMensual.setDiferencia(0.00);
                detCierreMensual.setInventarioFisico(0.00);
                detCierreMensual.setUbicacion(0);
                detCierreMensual.setCierreMensual(cierre);
                detCierreMensual.setCostoUnitario(detCierreMensual.getArticulo().getPrecioCompraUnitario());
                detCierreMensual.setPrecioVenta(detCierreMensual.getArticulo().getPrecioVenta1());
                costo = ClaseUtil.roundDouble(detCierreMensual.getCostoUnitario() * detCierreMensual.getInventarioFinal(), 2);
                detCierreMensual.setCosto(costo);

                listaDTO.add(detCierreMensual);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaDTO;
    }

    public List<DetalleCierreMensualInventario> getMovimientoArticulo(Date fechaini, Date fechafin,
            int almacen, int unidad,
            CierreMensualInventario cierre) {

        List<DetalleCierreMensualInventario> listaDTO = new ArrayList<>();

        fechaini = util.ClaseUtil.getFechaPrimerDiasDelMes(fechafin);

        try {

            String sql = " select (art.existencia+ifnull(salida.cantidad,0)-"
                    + " ( ifnull(ent.cantidad,0)+ifnull(trans_salida.cantidad,0))) as invini_calculado,art.existencia as invfinal ,\n"
                    + " art.codigo,art.nombre,ifnull(ii.cantidad,0)  as inv_inicial, ifnull(ent.cantidad,0)  as entrada,ifnull(trans.cantidad,0)  as entrada_trans,\n"
                    + "\n"
                    + "ifnull(trans_salida.cantidad,0)  as salida_trans,\n"
                    + "ifnull(salida.cantidad,0)  as salida,ifnull(factura.cantidad,0)  as salida_factura,au.unidad \n"
                    + " from articulo art\n"
                    + "\n"
                    + " LEFT JOIN\n"
                    + "\n"
                    + "( \n"
                    + "  select \n"
                    + "  dinv.articulo,ifnull(sum(existencia),0) as cantidad,u.descripcion as unidad\n"
                    + " from \n"
                    + " inventario_final inv,detalle_inventario_final dinv,unidad u\n"
                    + "\n"
                    + "where \n"
                    + "\n"
                    + " inv.codigo=dinv.inventario_final\n"
                    + "and u.codigo=dinv.unidad\n"
                    + "\n"
                    + "  and  inv.fecha>='2021-12-01' \n"
                    + " and u.codigo=1\n"
                    + "and almacen=1\n"
                    + "\n"
                    + " GROUP BY  dinv.articulo -- ,month(inv.fecha),year(fecha)\n"
                    + "\n"
                    + ") as ii on art.codigo=ii.articulo  LEFT JOIN\n"
                    + "\n"
                    + "(\n"
                    + " SELECT dent.articulo,sum(cantidad_recibida)  as cantidad,u.descripcion as unidad\n"
                    + "\n"
                    + " from  entrada_inventario ent\n"
                    + "\n"
                    + "INNER JOIN  detalle_entrada_inventario dent\n"
                    + "\n"
                    + "ON ent.codigo=dent.entrada_inventario\n"
                    + "\n"
                    + "inner JOIN unidad u on u.codigo=dent.unidad\n"
                    + "\n"
                    + " where ent.fecha  \n"
                    + "\n"
                    + "   between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaini)
                    + "'   and  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "' "
                    + "  and u.codigo=" + unidad
                    + " and dent.almacen=" + almacen
                    + "\n"
                    + " GROUP BY   dent.articulo\n"
                    + "\n"
                    + ") as  ent on art.codigo=ent.articulo  LEFT JOIN\n"
                    + "\n"
                    + "/* entrada por tranferencia  */\n"
                    + "(\n"
                    + "\n"
                    + " SELECT dent.articulo,sum(cantidad)  as cantidad,u.descripcion as unidad\n"
                    + "\n"
                    + " from  transferencia_almacen ent\n"
                    + "\n"
                    + "INNER JOIN  detalle_transferencia_almacen dent\n"
                    + "\n"
                    + "ON ent.codigo=dent.transferencia_almacen\n"
                    + "\n"
                    + "inner JOIN unidad u on u.codigo=dent.unidad\n"
                    + " where ent.fecha  \n"
                    + "   between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaini)
                    + "'   and  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "' "
                    + "  and u.codigo=" + unidad
                    + "  and dent.almacen_destino=" + almacen
                    + "\n"
                    + " GROUP BY  dent.articulo\n"
                    + "\n"
                    + ")  as  trans on art.codigo=trans.articulo  LEFT JOIN\n"
                    + "\n"
                    + "\n"
                    + "/* salidad por tranferencia  */\n"
                    + "(\n"
                    + "\n"
                    + " SELECT dent.articulo,sum(cantidad)  as cantidad,u.descripcion as unidad\n"
                    + "\n"
                    + " from  transferencia_almacen ent\n"
                    + "\n"
                    + "INNER JOIN  detalle_transferencia_almacen dent\n"
                    + "\n"
                    + "ON ent.codigo=dent.transferencia_almacen\n"
                    + "\n"
                    + "inner JOIN unidad u on u.codigo=dent.unidad\n"
                    + "\n"
                    + " where ent.fecha  \n"
                    + "\n"
                    + "   between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaini)
                    + "' and  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "' "
                    + "  and u.codigo=" + unidad
                    + " and dent.almacen_origen=" + almacen
                    + " \n"
                    + "\n"
                    + " GROUP BY  dent.articulo\n"
                    + "\n"
                    + ")  as  trans_salida on art.codigo=trans_salida.articulo  LEFT JOIN\n"
                    + "\n"
                    + "\n"
                    + "(\n"
                    + "\n"
                    + "  SELECT det.articulo, ifnull(sum(cantidad),0)  as cantidad,u.descripcion as unidad from  factura fact\n"
                    + "\n"
                    + "INNER JOIN  detalle_factura det\n"
                    + "\n"
                    + "ON fact.codigo=det.factura\n"
                    + "\n"
                    + "inner JOIN unidad u on u.codigo=det.unidad\n"
                    + "\n"
                    + " where fact.fecha  \n"
                    + "   between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaini)
                    + "'  and  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "' "
                    + "  and u.codigo=" + unidad
                    + "  and det.almacen=" + almacen
                    + "\n"
                    + " GROUP BY   det.articulo\n"
                    + "\n"
                    + ") as  factura on art.codigo=factura.articulo\n"
                    + "\n"
                    + "LEFT JOIN  \n"
                    + "\n"
                    + "( \n"
                    + "\n"
                    + " SELECT dent.articulo,sum(cantidad)  as cantidad,u.descripcion as unidad\n"
                    + "\n"
                    + " from  salida_inventario ent\n"
                    + "\n"
                    + "INNER JOIN  detalle_salida_inventario dent \n"
                    + "\n"
                    + " ON ent.codigo=dent.salida_inventario\n"
                    + "\n"
                    + "inner JOIN unidad u on u.codigo=dent.unidad \n"
                    + "\n"
                    + " where ent.fecha  \n"
                    + "\n"
                    + "   between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaini)
                    + "'   and  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "' "
                    + "  and u.codigo=" + unidad
                    + " and dent.almacen=" + almacen
                    + "\n"
                    + " GROUP BY  dent.articulo\n"
                    + "\n"
                    + ") as  salida on art.codigo=salida.articulo \n"
                    + " LEFT JOIN  \n"
                    + "( \n"
                    + "\n"
                    + " SELECT u.codigo as unidad,articulo from \n"
                    + " articulo_unidad au,unidad u  where u.codigo=au.unidad "
                    + "\n"
                    + " ) as au on au.articulo=art.codigo "
                    + " order by art.numero ";

            System.out.println("sql " + sql);

            PreparedStatement ps = Conexion.getInsatancia().getConnectionDB().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            DetalleCierreMensualInventario detCierreMensual;
            Double costo = 0.00;

            while (rs.next()) {

                try {

                    System.out.println("tiene registro " + rs.getString("codigo"));

                    detCierreMensual = new DetalleCierreMensualInventario();

                    detCierreMensual.setCierreMensual(cierre);
                    detCierreMensual.setArticulo(ManejoArticulo.getInstancia().getArticulo(rs.getInt("codigo")));
                    detCierreMensual.setDescripcionArticulo(rs.getString("nombre"));
                    detCierreMensual.setInventarioInicial(ClaseUtil.roundDouble(rs.getDouble("invini_calculado"), 2));
                    detCierreMensual.setInventarioFinal(ClaseUtil.roundDouble(rs.getDouble("invfinal"), 2));
                    detCierreMensual.setAlmacen(ManejoAlmacen.getInstancia().getalmacen(almacen));
                    detCierreMensual.setCantidadAjustada(0.00);
                    detCierreMensual.setCantidadEntrada(ClaseUtil.roundDouble(rs.getDouble("entrada"), 2));
                    detCierreMensual.setCantidadSalida(ClaseUtil.roundDouble(rs.getDouble("salida"), 2));
                    detCierreMensual.setUnidadSalida(ManejoUnidad.getInstancia().getUnidad(rs.getInt("unidad")).getCodigo());
                    detCierreMensual.setDiferencia(0.00);
                    detCierreMensual.setInventarioFisico(0.00);
                    detCierreMensual.setUbicacion(0);
                   
                    detCierreMensual.setCostoUnitario(detCierreMensual.getCosto());
                    detCierreMensual.setPrecioVenta(detCierreMensual.getArticulo().getPrecioVenta1());
                    costo = ClaseUtil.roundDouble(detCierreMensual.getCostoUnitario() * detCierreMensual.getInventarioFinal(), 2);
                    detCierreMensual.setCosto(costo);

                    listaDTO.add(detCierreMensual);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaDTO;
    }

    @Override
    public Class getReferencia() {
        return CierreMensualInventario.class;
    }

    public static void main(String[] args) {

        try {

//            System.out.println("Existe " + getInstancia()
//                    .getMovimientoArticulo(new Date("2021/09/01"), new Date("2021/12/30"), 1, 4, new CierreMensualInventario()));
            List<DetalleCierreMensualInventario> lista = getInstancia()
                    .getMovimientoArticulo(new Date("2021/12/30"), new Date("2021/12/30"), 1, 2, new CierreMensualInventario());

            System.out.println("tamano " + lista.size());
            for (DetalleCierreMensualInventario det : lista) {

                System.out.println("detalle " + det.getDescripcionArticulo());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
