/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contrato;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import manejo.articulo.ManejoArticulo;
import modelo.Articulo;
import modelo.Cliente;
import modelo.ContratoDeServicio;
import modelo.DatosDeVehiculo;
import modelo.DetalleContratoDeServicio;
import modelo.dto.ContratoVencidoDto;
//import modelo.Usuariop;
import org.hibernate.Session;
import utiles.ClaseUtil;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoContratoDeServicio extends ManejoEstandar<ContratoDeServicio> {

    private static ManejoContratoDeServicio manejoContratoDeServicio = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoContratoDeServicio getInstancia() {
        if (manejoContratoDeServicio == null) {
            manejoContratoDeServicio = new ManejoContratoDeServicio();
        }
        return manejoContratoDeServicio;
    }

    public List<ContratoDeServicio> getLista() {

        String query = " SELECT * FROM contrato_de_servicio  ";

        session.clear();
        return session.createSQLQuery(query).addEntity(ContratoDeServicio.class).list();

    }

    public List<DetalleContratoDeServicio> getDetalleContrato(int contrato) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio where  contrato_de_servicio=:contrato ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class).setParameter("contrato", contrato).list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoServicio(int contrato) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio det INNER JOIN articulo art \n "
                + " on art.codigo=det.equipo \n "
                + " where  contrato_de_servicio=:contrato and art.tipo_articulo=3 ";
        session.clear();
        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class)
                .setParameter("contrato", contrato)
                .list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoServicio(int contrato, boolean traspasado) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio det INNER JOIN articulo art \n "
                + " on art.codigo=det.equipo \n "
                + " where  contrato_de_servicio=:contrato and art.tipo_articulo=3  and traspasado=:traspasado ";

        session.clear();
        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class)
                .setParameter("contrato", contrato)
                .setParameter("traspasado", traspasado)
                .list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoEquipo(int contrato) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio det INNER JOIN articulo art \n "
                + " on art.codigo=det.equipo \n "
                + " where  contrato_de_servicio=:contrato and art.tipo_articulo<>3 ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class)
                .setParameter("contrato", contrato)
                .list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoEquipo(int contrato, boolean traspasado) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio det INNER JOIN articulo art \n "
                + " on art.codigo=det.equipo \n "
                + " where  contrato_de_servicio=:contrato and art.tipo_articulo<>3  and traspasado=:traspasado ";
        session.clear();
        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class)
                .setParameter("contrato", contrato)
                .setParameter("traspasado", traspasado)
                .list();

        return lista;
    }

    public DetalleContratoDeServicio getDetalleContratoEquipo(int contrato, int equipo) {

        session.flush();
        DetalleContratoDeServicio det;
        String query = " SELECT * FROM detalle_contrato_de_servicio det INNER JOIN articulo art \n "
                + " on art.codigo=det.equipo \n "
                + " where  contrato_de_servicio=:contrato and art.tipo_articulo<>3   and det.codigo=:equipo ";

        det = (DetalleContratoDeServicio) session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class)
                .setParameter("contrato", contrato)
                .setParameter("equipo", equipo)
                .uniqueResult();

        return det;
    }

    public List<DetalleContratoDeServicio> getMovimientoSimCard(int sim) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio where  descripcion like '%EQUI%' and  sim=:sim ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class).setParameter("sim", sim).list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getMovimientoImeiGps(int imeiGps) {

        List<DetalleContratoDeServicio> lista;
        String query = "SELECT * FROM detalle_contrato_de_servicio where  descripcion like '%EQUI%' and  imei=:imei ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class).setParameter("imei", imeiGps).list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoHabilitado(int contrato) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio  "
                + " where contrato_de_servicio=:contrato "
                + "  and habilitado=true  and tipo_de_servicio is not null   ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class).setParameter("contrato", contrato).list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoHabilitado(int contrato, int frecuencia, Date fechaCorte) {

        List<DetalleContratoDeServicio> lista = null;

        try {

            String query = " SELECT * FROM detalle_contrato_de_servicio  "
                    + "  where contrato_de_servicio=:contrato and habilitado=true  and  tipo_de_servicio  is not null  "
                    + "  and fecha_ultimo_pago_hasta  is not null  "
                    + "  and frecuencia_de_pago=:frecuencia  "
                    + "  and  fecha_ultimo_pago_hasta<='" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'";

            System.out.println("query " + query);
            lista = session.createSQLQuery(query)
                    .addEntity(DetalleContratoDeServicio.class)
                    .setParameter("contrato", contrato)
                    .setParameter("frecuencia", frecuencia)
                    .list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoHabilitado(int contrato, int frecuencia) {

        List<DetalleContratoDeServicio> lista = null;

        try {

            String query = " SELECT * FROM detalle_contrato_de_servicio  "
                    + "  where  contrato_de_servicio=:contrato and habilitado=true  and  tipo_de_servicio  is not null  "
                    + "  and fecha_ultimo_pago_hasta  is not null  "
                    + "  and frecuencia_de_pago=:frecuencia   ";

            System.out.println("query " + query);
            lista = session.createSQLQuery(query)
                    .addEntity(DetalleContratoDeServicio.class)
                    .setParameter("contrato", contrato)
                    .setParameter("frecuencia", frecuencia)
                    .list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoHabilitado(int contrato, String param) {

        List<DetalleContratoDeServicio> lista = null;

        try {

            String query = " SELECT * FROM detalle_contrato_de_servicio  \n"
                    + "                    where contrato_de_servicio=:contrato and habilitado=true  and  tipo_de_servicio  is not null  \n"
                    + "                     and fecha_ultimo_pago_hasta  is not null  \n"
                    + "                      and frecuencia_de_pago=2 \n"
                    + "                      and codigo in (" + param + ") ";

            System.out.println("query " + query);
            lista = session.createSQLQuery(query)
                    .addEntity(DetalleContratoDeServicio.class)
                    .setParameter("contrato", contrato)
                    .list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoDeRenovacion(int contrato, String param) {

        List<DetalleContratoDeServicio> lista = null;

        try {

            String query = " SELECT * FROM detalle_contrato_de_servicio  \n"
                    + "                    where contrato_de_servicio=:contrato and habilitado=true  and  tipo_de_servicio  is not null  \n"
                    + "                     and fecha_ultimo_pago_hasta  is not null  \n"
                    + "                      and frecuencia_de_pago=1 \n"
                    + "                      and codigo in (" + param + ") ";

            System.out.println("query " + query);
            lista = session.createSQLQuery(query)
                    .addEntity(DetalleContratoDeServicio.class)
                    .setParameter("contrato", contrato)
                    .list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoVencido(int contrato, Date fechaCorte) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio where  "
                + "  contrato_de_servicio=:contrato and  habilitado=true  and tipo_de_servicio is not null "
                + " and fecha_ultimo_pago_hasta<='" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'";

        System.out.println("query " + query);

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class).setParameter("contrato", contrato).list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoVencido(int contrato, Date fechaCorte, int frecuencia) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio where  "
                + "  contrato_de_servicio=:contrato and  habilitado=true  and tipo_de_servicio is not null "
                + " and fecha_ultimo_pago_hasta<='"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "' and frecuencia_de_pago=" + frecuencia;

        System.out.println("query " + query);

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class).setParameter("contrato", contrato).list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getListaDetalleContrato(int contrato) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio where  "
                + "  contrato_de_servicio=:contrato and  habilitado=true and  tipo_de_servicio is not null";

        System.out.println("query " + query);

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class).setParameter("contrato", contrato).list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoVencido(int contrato) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio where  contrato_de_servicio=:contrato and  habilitado=true "
                + " and fecha_ultimo_pago_hasta is null and facturado=false  ";

//        String query = " SELECT * FROM detalle_contrato_de_servicio dc where  contrato_de_servicio=:contrato and  habilitado=true "
//                + " and fecha_ultimo_pago_hasta is null  and not EXISTS "
//                + " ( SELECT * from  factura f INNER JOIN detalle_factura df on f.codigo=df.factura \n"
//                + "    where f.anulada=FALSE  and dc.equipo=df.articulo )  ";
        System.out.println("query " + query);

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class).setParameter("contrato", contrato).list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoParaFacturar(int contrato) {

        List<DetalleContratoDeServicio> lista;
        String query = " SELECT * FROM detalle_contrato_de_servicio where  contrato_de_servicio=:contrato and  habilitado=true "
                + " and fecha_ultimo_pago_hasta is null and facturado=false   group by equipo,precio_pagado  ";

        System.out.println("query " + query);

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class).setParameter("contrato", contrato).list();

        return lista;
    }

    public List<DetalleContratoDeServicio> getDetalleContratoParaFacturar(int contrato, Date fechaHasta, int frecuencia) {

        List<DetalleContratoDeServicio> lista;

        String query = " SELECT * FROM detalle_contrato_de_servicio where  "
                + "  contrato_de_servicio=:contrato and  habilitado=true  and tipo_de_servicio is not null "
                + " and fecha_ultimo_pago_hasta<='"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "' and frecuencia_de_pago=" + frecuencia + " group by equipo,precio_pagado,fecha_ultimo_pago_hasta ";

//        String query = " SELECT * FROM detalle_contrato_de_servicio where  contrato_de_servicio=:contrato and  habilitado=true "
//                + " and fecha_ultimo_pago_hasta is null and facturado=false and  frecuencia_de_pago=" + frecuencia
//                + "  group by equipo,precio_pagado  ";
        System.out.println("query getDetalleContratoParaFacturar " + query);

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class).setParameter("contrato", contrato).list();

        return lista;
    }

    public Integer getDetalleContratoCantidadArticulo(int contrato, DetalleContratoDeServicio det) {

        BigInteger cantidad;

        String query = " SELECT count(equipo) FROM detalle_contrato_de_servicio "
                + "   where  contrato_de_servicio=:contrato  and equipo=:equipo "
                + "  and precio_pagado=:precio and  habilitado=true "
                + " and fecha_ultimo_pago_hasta is null and facturado=false   ";

        cantidad = (BigInteger) session.createSQLQuery(query)
                .setParameter("contrato", contrato)
                .setParameter("equipo", det.getEquipo().getCodigo())
                .setParameter("precio", det.getPrecioPagado())
                .uniqueResult();

        return cantidad == null ? 0 : cantidad.intValue();
    }

    public Integer getDetCtoCantidadDeGps(DetalleContratoDeServicio det, Date fechaHasta, int frecuencia) {

        BigDecimal cantidad;

        String query = " SELECT sum(cantidad) as cantidad FROM detalle_contrato_de_servicio "
                + "  where  contrato_de_servicio=:contrato  and  equipo=:equipo  and  precio_pagado=:precio "
                + "  and  habilitado=true  and tipo_de_servicio is not null "
                + "  and fecha_ultimo_pago_hasta<='"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta) + "' and frecuencia_de_pago=" + frecuencia + " group by equipo,precio_pagado ";

        System.out.println("query cantidad gps " + query);
        System.out.println("det.getPrecioPagado() " + det.getPrecioPagado());
        System.out.println("det.getEquipo().getCodigo() " + det.getEquipo().getCodigo());
        System.out.println(" det.getContratoDeServicio().getCodigo() " + det.getContratoDeServicio().getCodigo());

        cantidad = (BigDecimal) session.createSQLQuery(query)
                .setParameter("contrato", det.getContratoDeServicio().getCodigo())
                .setParameter("equipo", det.getEquipo().getCodigo())
                .setParameter("precio", det.getPrecioPagado())
                .uniqueResult();

        return cantidad == null ? 0 : cantidad.intValue();
    }

    public Integer getDetCtoCantidadDeGps(DetalleContratoDeServicio det, Date fechaHasta, int frecuencia, Date fechaDet) {

        BigDecimal cantidad;

        String query = " SELECT sum(cantidad) as cantidad FROM detalle_contrato_de_servicio "
                + "  where  contrato_de_servicio=:contrato  and  equipo=:equipo  and  precio_pagado=:precio "
                + "  and  habilitado=true  and tipo_de_servicio is not null "
                + "  and fecha_ultimo_pago_hasta='"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaDet) + "' and frecuencia_de_pago=" + frecuencia + "  ";

        System.out.println("query cantidad gps " + query);
        System.out.println("det.getPrecioPagado() " + det.getPrecioPagado());
        System.out.println("det.getEquipo().getCodigo() " + det.getEquipo().getCodigo());
        System.out.println(" det.getContratoDeServicio().getCodigo() " + det.getContratoDeServicio().getCodigo());

        cantidad = (BigDecimal) session.createSQLQuery(query)
                .setParameter("contrato", det.getContratoDeServicio().getCodigo())
                .setParameter("equipo", det.getEquipo().getCodigo())
                .setParameter("precio", det.getPrecioPagado())
                .uniqueResult();

        return cantidad == null ? 0 : cantidad.intValue();
    }

    public List<DatosDeVehiculo> getDatosVehiculoo(int servicio) {

        List<DatosDeVehiculo> lista;
        String query = "SELECT * FROM datos_de_vehiculo where habilitado=true and  servicio=:servicio ";

        lista = session.createSQLQuery(query)
                .addEntity(DatosDeVehiculo.class)
                .setParameter("servicio", servicio)
                .list();

        return lista;
    }

    public List<DatosDeVehiculo> getDatosVehiculo(int servicio) {

        List<DatosDeVehiculo> lista;
        String query = "SELECT * FROM datos_de_vehiculo where  servicio=:servicio ";

        lista = session.createSQLQuery(query)
                .addEntity(DatosDeVehiculo.class).setParameter("servicio", servicio).list();

        return lista;
    }

    public List<DatosDeVehiculo> getDatosVehiculoPorEquipo(int equipo) {

        List<DatosDeVehiculo> lista;
        String query = "SELECT * FROM datos_de_vehiculo where  equipo_gps=:equipo ";

        lista = session.createSQLQuery(query)
                .addEntity(DatosDeVehiculo.class).setParameter("equipo", equipo).list();

        return lista;
    }

    public List<DatosDeVehiculo> getVehiculos(int contrato) {

        List<DatosDeVehiculo> lista;
        String query = "SELECT * FROM datos_de_vehiculo where  contrato=:contrato ";

        lista = session.createSQLQuery(query)
                .addEntity(DatosDeVehiculo.class).setParameter("contrato", contrato).list();

        return lista;
    }

    public List<DatosDeVehiculo> getDatosVehiculo(int servicio, boolean traspasado) {

        List<DatosDeVehiculo> lista;
        String query = "SELECT * FROM datos_de_vehiculo where  servicio=:servicio  and traspasasado=:traspasado ";

        lista = session.createSQLQuery(query)
                .addEntity(DatosDeVehiculo.class)
                .setParameter("servicio", servicio)
                .setParameter("traspasado", traspasado)
                .list();

        return lista;
    }

    public List<DatosDeVehiculo> getDatosVehiculoPorContrato(int contrato, boolean traspasado) {

        List<DatosDeVehiculo> lista;
        String query = "SELECT * FROM datos_de_vehiculo where  contrato=:contrato  and traspasasado=:traspasado ";

        lista = session.createSQLQuery(query)
                .addEntity(DatosDeVehiculo.class)
                .setParameter("contrato", contrato)
                .setParameter("traspasado", traspasado)
                .list();

        return lista;
    }

    public List<DatosDeVehiculo> getDatosVehiculoPorContrto(int contrato) {

        List<DatosDeVehiculo> lista;
        String query = "SELECT * FROM datos_de_vehiculo where  contrato=:contrato ";

        lista = session.createSQLQuery(query)
                .addEntity(DatosDeVehiculo.class).setParameter("contrato", contrato).list();

        return lista;
    }

    public ContratoDeServicio getContrato(int codigo) {

        ContratoDeServicio factura = null;

        String query = "select * From contrato_de_servicio c where c.codigo=:codigo ";

        System.out.println("Query " + query);

        factura = (ContratoDeServicio) getSession().createSQLQuery(query).addEntity(ContratoDeServicio.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

        return factura;
    }

    public ContratoDeServicio getContrato(String numero) {

        ContratoDeServicio factura = null;

        String query = "select * From contrato_de_servicio c where c.numero=:numero ";

        System.out.println("Query " + query);

        factura = (ContratoDeServicio) getSession().createSQLQuery(query).addEntity(ContratoDeServicio.class)
                .setParameter("numero", numero)
                .uniqueResult();

        return factura;
    }

    public List<ContratoDeServicio> getLista(Date fechaini, Date fechafin) {

        String query = "SELECT * FROM contrato_de_servicio  where  fecha between '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechaini) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "' order by nombre_cliente ";

        System.out.println("Query " + query);

        return session.createSQLQuery(query).addEntity(ContratoDeServicio.class).list();

    }

    public List<ContratoDeServicio> getLista(Date fecha) {

        String query = "SELECT * FROM contrato_de_servicio  where anulado=false and fecha='" + new SimpleDateFormat("yyyy-MM-dd").format(fecha) + "'";
        System.out.println("Query " + query);

        return session.createSQLQuery(query).addEntity(ContratoDeServicio.class).list();

    }

    public List<ContratoDeServicio> getListaContratoPorCliente(Cliente cliente) {

        String query = " SELECT * FROM contrato_de_servicio  where anulado=false  and  cliente=:cliente ";
        System.out.println("Query " + query);
        return session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .setParameter("cliente", cliente)
                .list();

    }

    public List<ContratoDeServicio> getListaContratoPorCliente(Cliente cliente, int plan) {

        String query = " SELECT * FROM contrato_de_servicio  where anulado=false  and  cliente=:cliente  and plan_de_servicio=:plan ";
        System.out.println("Query " + query);
        return session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .setParameter("cliente", cliente)
                .setParameter("plan", plan)
                .list();

    }

    public List<ContratoDeServicio> getListaContratoSinFaturar() {

        String query = "  SELECT * FROM contrato_de_servicio ct  where "
                + "            EXISTS (  SELECT * FROM detalle_contrato_de_servicio det \n"
                + "                         where  ct.codigo=det.contrato_de_servicio\n"
                + "                            and ct.anulado=false  and  ct.estado=1 "
                 + "  and det.tipo_de_servicio is not null \n"
                + "                      and  fecha_ultimo_pago_hasta is null   \n"
                + "                and  habilitado=true  and reemplazo=false ) ";

//        String query = "  SELECT * FROM contrato_de_servicio ct,detalle_contrato_de_servicio det \n"
//                + "  \n"
//                + " where  ct.codigo=det.contrato_de_servicio \n"
//                + "and anulado=false  and  estado=1    \n"
//                + " and  fecha_ultimo_pago_hasta is null ";
        return session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .list();

    }

    public List<ContratoDeServicio> getContratoVencido(Date fechaCorte, int frecuencia) {

        String query = " SELECT * FROM contrato_de_servicio ct  where \n"
                + "EXISTS (  SELECT * FROM detalle_contrato_de_servicio det \n"
                + "           \n"
                + "               where  ct.codigo=det.contrato_de_servicio \n"
                + "               and ct.anulado=false  and  ct.estado=1 and det.tipo_de_servicio is not null    \n"
                + " and fecha<'" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'"
                + "  and fecha_ultimo_pago_hasta<='" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'"
                + "  and  habilitado=true  ) and frecuencia_de_pago=:frecuencia  ORDER BY ct.numero_de_contrato";

        System.out.println("sql " + query);

        return session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .setParameter("frecuencia", frecuencia)
                .list();

    }

    public List<ContratoDeServicio> getContratoVencidoRecurrente(Date fechaCorte, int frecuencia) {

        String query = " SELECT * FROM contrato_de_servicio ct  where \n"
                + "EXISTS (  SELECT * FROM detalle_contrato_de_servicio det \n"
                + "           \n"
                + "               where  ct.codigo=det.contrato_de_servicio \n"
                + "               and ct.anulado=false  and  ct.estado=1 and det.tipo_de_servicio is not null    \n"
                + " and fecha<'" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'  and det.frecuencia_de_pago=:frecuencia "
                + "  and fecha_ultimo_pago_hasta<='" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'"
                + "  and  habilitado=true  and descripcion like '%mensuali%' )  ORDER BY ct.numero_de_contrato";

        System.out.println("sql " + query);

        return session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .setParameter("frecuencia", frecuencia)
                .list();

    }

    public List<ContratoDeServicio> getContratoVencido(Date fechaCorte) {

        String query = " SELECT * FROM contrato_de_servicio ct  where \n"
                + "EXISTS (  SELECT * FROM detalle_contrato_de_servicio det \n"
                + "           \n"
                + "               where  ct.codigo=det.contrato_de_servicio \n"
                + "               and ct.anulado=false  and  ct.estado=1 and det.tipo_de_servicio is not null    \n"
                + " and fecha<'" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'"
                + "  and fecha_ultimo_pago_hasta<'" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'"
                + "  and  habilitado=true  )  ORDER BY ct.numero_de_contrato";

        System.out.println("sql " + query);

        return session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .list();

    }

    public List<ContratoDeServicio> getContratoVencidoRegistroNuevo(Date fechaCorte) {

        String query = " SELECT * FROM contrato_de_servicio ct  where \n"
                + "EXISTS (  SELECT * FROM detalle_contrato_de_servicio det \n"
                + "           \n"
                + "               where  ct.codigo=det.contrato_de_servicio \n"
                + "               and ct.anulado=false  and  ct.estado=1 and det.tipo_de_servicio is not null    \n"
                + " and fecha<'" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'"
                + "  and fecha_ultimo_pago_hasta<'" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'"
                + "  and  habilitado=true  ) ORDER BY ct.numero_de_contrato";

        System.out.println("sql " + query);

        return session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .list();

    }

    public List<ContratoDeServicio> getContratoPorVencer() {

//        Date fechaPrimerdiaMes = util.ClaseUtil.getFechaPrimerDiasDelMes(fechaCorte);
        String query = "  SELECT * FROM contrato_de_servicio ct,detalle_contrato_de_servicio det \n"
                + "  \n"
                + " where  ct.codigo=det.contrato_de_servicio \n"
                + "and anulado=false  and  estado=1  and DATEDIFF(NOW(),fecha_ultimo_pago_hasta)=cuandia_antes_de_vencer ";

        System.out.println("sql " + query);

        return session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .list();

    }

    public List<ContratoVencidoDto> getContratoPorVencerDetalle() {

        List<DetalleContratoDeServicio> lista = null;
        ContratoVencidoDto detContrato = null;
        List<ContratoVencidoDto> listaContrataDto = new ArrayList<>();

        String query = "  SELECT * FROM contrato_de_servicio ct,detalle_contrato_de_servicio det,articulo art \n"
                + "  where  ct.codigo=det.contrato_de_servicio  and art.codigo=det.equipo and art.tipo_articulo=3 "
                + " and ct.plan_de_servicio=1 and det.tipo_de_servicio is not null \n"
                + " and anulado=false  and  ct.estado=1 and det.habilitado=true "
                + " and  DATEDIFF(fecha_ultimo_pago_hasta,Now())+1  between 1 "
                + "  and  ct.cuandia_antes_de_vencer and ct.frecuencia_de_pago=1 and det.frecuencia_de_pago=1 ";

        System.out.println("sql " + query);
        Long dias = 0l;

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class)
                .list();

        for (DetalleContratoDeServicio det : lista) {

            dias = ClaseUtil.diferenciaDiasEntreDosFecha(det.getFechaUltimoPagoHasta(), new Date());
            detContrato = new ContratoVencidoDto();
            detContrato.setCliente(det.getContratoDeServicio().getCliente().getNombre());
            detContrato.setContrato(det.getContratoDeServicio().getNumeroDeContrato());
            detContrato.setDescripcion(det.getDescripcion());
            detContrato.setPlan(det.getContratoDeServicio().getPlanDeServicio().getNombre());
            detContrato.setFechaVencimiento(det.getFechaUltimoPagoHasta());
            detContrato.setDias(dias.intValue());
            detContrato.setPrecioRenovacion(det.getPrecioRenovacion());
            listaContrataDto.add(detContrato);

        }

        return listaContrataDto;

    }

    public List<ContratoVencidoDto> getContratoPorVencerHoy(Date fecha) {

        List<DetalleContratoDeServicio> lista = null;
        ContratoVencidoDto detContrato = null;
        List<ContratoVencidoDto> listaContrataDto = new ArrayList<>();
        String fechaStr = new SimpleDateFormat("yyyy/MM/dd").format(fecha);

        String query = "  SELECT * FROM contrato_de_servicio ct,detalle_contrato_de_servicio det,articulo art \n"
                + "  where  ct.codigo=det.contrato_de_servicio  and art.codigo=det.equipo and art.tipo_articulo=3 "
                + " and ct.plan_de_servicio=1 and det.tipo_de_servicio is not null \n"
                + " and anulado=false  and  ct.estado=1 and det.habilitado=true "
                //  + " and  DATEDIFF(fecha_ultimo_pago_hasta,Now())+1  between 1 "
                + " and  DATEDIFF(('" + fechaStr + "'),fecha_ultimo_pago_hasta)=0 "
                + " and  ct.cuandia_antes_de_vencer and ct.frecuencia_de_pago=1 and det.frecuencia_de_pago=1 ";

        System.out.println("sql " + query);
        Long dias = 0l;

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class)
                .list();

        for (DetalleContratoDeServicio det : lista) {

            dias = ClaseUtil.diferenciaDiasEntreDosFecha(det.getFechaUltimoPagoHasta(), new Date());
            detContrato = new ContratoVencidoDto();
            detContrato.setCliente(det.getContratoDeServicio().getCliente().getNombre());
            detContrato.setTelefono(det.getContratoDeServicio().getCliente().getCelular());
            detContrato.setContrato(det.getContratoDeServicio().getNumeroDeContrato());
            detContrato.setDescripcion(det.getDescripcion());
            detContrato.setPlan(det.getContratoDeServicio().getPlanDeServicio().getNombre());
            detContrato.setFechaVencimiento(det.getFechaUltimoPagoHasta());
            detContrato.setDias(dias.intValue());
            listaContrataDto.add(detContrato);

        }

        return listaContrataDto;

    }

    public List<ContratoVencidoDto> getContratoVencido() {

        List<DetalleContratoDeServicio> lista = null;
        ContratoVencidoDto detContrato = null;
        List<ContratoVencidoDto> listaContrataDto = new ArrayList<>();

        String query = "    SELECT * FROM contrato_de_servicio ct,detalle_contrato_de_servicio det,articulo art \n"
                + "               where  ct.codigo=det.contrato_de_servicio  and art.codigo=det.equipo and art.tipo_articulo=3 \n"
                + "               and ct.plan_de_servicio=1\n"
                + "          and det.tipo_de_servicio is not null \n"
                + "                 and anulado=false  and  ct.estado=1 and det.habilitado=true \n"
                + "                and   Now()>fecha_ultimo_pago_hasta  \n"
                + "                and ct.frecuencia_de_pago=1 and det.frecuencia_de_pago=1  ";

        System.out.println("sql " + query);
        Long dias = 0l;

        lista = session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class)
                .list();

        for (DetalleContratoDeServicio det : lista) {

            dias = ClaseUtil.diferenciaDiasEntreDosFecha(det.getFechaUltimoPagoHasta(), new Date());
            detContrato = new ContratoVencidoDto();
            detContrato.setCliente(det.getContratoDeServicio().getCliente().getNombre());
            detContrato.setContrato(det.getContratoDeServicio().getNumeroDeContrato());
            detContrato.setDescripcion(det.getDescripcion());
            detContrato.setPlan(det.getContratoDeServicio().getPlanDeServicio().getNombre());
            detContrato.setFechaVencimiento(det.getFechaUltimoPagoHasta());
            detContrato.setDias(dias.intValue());

            listaContrataDto.add(detContrato);

        }

        return listaContrataDto;

    }

    public List<DetalleContratoDeServicio> getContratoVencidoDetalle() {

        String query = "    SELECT * FROM contrato_de_servicio ct,detalle_contrato_de_servicio det,articulo art \n"
                + "               where  ct.codigo=det.contrato_de_servicio  and art.codigo=det.equipo and art.tipo_articulo=3 \n"
                + "               and ct.plan_de_servicio=1\n"
                + "          and det.tipo_de_servicio is not null \n"
                + "                 and anulado=false  and  ct.estado=1 and det.habilitado=true \n"
                + "                and   Now()>fecha_ultimo_pago_hasta  \n"
                + "                and ct.frecuencia_de_pago=1 and det.frecuencia_de_pago=1  ";

        System.out.println("sql " + query);

        return session.createSQLQuery(query)
                .addEntity(DetalleContratoDeServicio.class)
                .list();

    }

    public Integer getDiasParaVencer(int contrato) {

        String query = "  SELECT  DATEDIFF(Now(),fecha_ultimo_pago_hasta)+1 "
                + " FROM contrato_de_servicio ct,detalle_contrato_de_servicio det, articulo art \n"
                + " where  ct.codigo=det.contrato_de_servicio and art.codigo=det.equipo and art.tipo_articulo=3 "
                + " and  ct.plan_de_servicio=1 and det.tipo_de_servicio is not null \n"
                + " and anulado=false  and  ct.estado=1 and det.habilitado=true "
                + " and  DATEDIFF(fecha_ultimo_pago_hasta,Now())+1  between 1 and  ct.cuandia_antes_de_vencer and det.frecuencia_de_pago=1 "
                + "  and ct.codigo=" + contrato;

        System.out.println("sql " + query);

        BigInteger dias = ((BigInteger) session.createSQLQuery(query)
                .uniqueResult());

        return dias == null ? 0 : dias.intValue();
    }

    public Integer getDiasVencido(int contrato) {

        String query = "  SELECT  DATEDIFF(Now(),fecha_ultimo_pago_hasta) "
                + " FROM contrato_de_servicio ct,detalle_contrato_de_servicio det,articulo art \n"
                + " where  ct.codigo=det.contrato_de_servicio and  art.codigo=det.equipo and art.tipo_articulo=3 "
                + "  and ct.plan_de_servicio=1 and det.tipo_de_servicio is not null \n"
                + " and anulado=false  and  ct.estado=1 and det.habilitado=true and det.frecuencia_de_pago=1 "
                + "  and   Now()>fecha_ultimo_pago_hasta  and  det.contrato_de_servicio=" + contrato + " limit 1 ";

        System.out.println("sql " + query);

        BigInteger dias = ((BigInteger) session.createSQLQuery(query)
                .uniqueResult());

        return dias == null ? 0 : dias.intValue();
    }

    public List<ContratoDeServicio> getListaContrato(Date fechaCorte) {

//        Date fechaPrimerdiaMes = util.ClaseUtil.getFechaPrimerDiasDelMes(fechaCorte);
        String query = " SELECT * FROM contrato_de_servicio ct \n"
                + " where  anulado=false  and  estado=1  and  "
                + "  ct.codigo  not in "
                + "( SELECT det.contrato  from\n"
                + "corte_de_facturacion ct,\n"
                + " detalle_corte_de_facturacion \n"
                + "det \n"
                + "where ct.codigo=det.corte_de_facturacion  \n"
                + "and fecha=:fecha "
                + "\n"
                + ") and fecha<'" + new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte) + "'";

//        String query = " SELECT * FROM contrato_de_servicio ct,\n" +
//                  " detalle_contrato_de_servicio dtc "
//                   + " where "
//                  + "  ct.codigo=dtc.contrato_de_servicio "
//                + " and anulado=false  and  estado=1  and  "
//                + "  ct.codigo  not in "               
//                + "( SELECT det.contrato  from\n"
//                + "corte_de_facturacion ct,\n"
//                + " detalle_corte_de_facturacion \n"
//                + "det \n"
//                + "where ct.codigo=det.corte_de_facturacion   \n"
//                + "and fecha=:fecha "
//                + "\n"
//                + ") and  DAY(dtc.fecha_hasta) between DAY('"+new SimpleDateFormat("yyyy-MM-dd")
//                        .format(fechaPrimerdiaMes)+"')"+" and day('"+new SimpleDateFormat("yyyy-MM-dd").format(fechaCorte)+"')"
//                + " ";
        System.out.println("sql " + query);

        return session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .setParameter("fecha", new SimpleDateFormat("yyyy/MM/dd").format(fechaCorte))
                .list();

    }

    public BigInteger getNumero() {

        String query = " select ifnull(max(numero)+1,0) from contrato_de_servicio ";

        return (BigInteger) session.createSQLQuery(query).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return ContratoDeServicio.class;
    }

    public static void main(String[] args) {

//        System.out.println(ManejoArticulo.getInstancia().getArticulo(987654384).getNombre());
//        System.out.println("Datos " + getInstancia().getContratoPorVencerHoy(new Date("2022/10/06")).size());
//        System.out.println("Datos " + getInstancia().getDetalleContratoCantidadArticulo(676,
//                ManejoArticulo.getInstancia().getArticulo(987654384)).toString());
    }

}
