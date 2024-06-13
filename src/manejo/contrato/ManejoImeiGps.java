/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contrato;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.RegistroDeImei;
import modelo.RegistroDeSim;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoImeiGps extends ManejoEstandar<RegistroDeImei> {

    private static ManejoImeiGps manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoImeiGps getInstancia() {
        if (manejo == null) {
            manejo = new ManejoImeiGps();
        }
        return manejo;
    }

    public List<RegistroDeImei> getLista() {

        String query = " SELECT * FROM registro_de_imei  ";

        return session.createSQLQuery(query).addEntity(RegistroDeImei.class).list();

    }

    public List<RegistroDeImei> getListaImeiDisponible() {

        String query = "  SELECT  \n"
                + "*\n"
                + "   from  registro_de_imei \n"
                + "  where \n"
                + "  numero not  in\n"
                + " (\n"
                + "   SELECT det.numero_imei from  detalle_contrato_de_servicio det\n"
                + "   INNER JOIN  articulo art on art.codigo=det.equipo\n"
                + "  where det.numero_sim is not null and art.tipo_articulo<>3\n"
                + "    and LENGTH(det.numero_sim)>4   and det.habilitado=true \n"
                + ") ";

        return session.createSQLQuery(query)
                .addEntity(RegistroDeImei.class)               
                .list();

    }

    public List<RegistroDeImei> getListaEntreFecha(Date fi, Date ff) {

        String query = " SELECT * FROM registro_de_imei  where \n"
                + "\n"
                + " numero in (\n"
                + "SELECT numero_imei from  detalle_contrato_de_servicio  dc\n"
                + " where   dc.fecha_desde  between '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'" + ") ";

        return session.createSQLQuery(query)
                .addEntity(RegistroDeImei.class)
                .list();

    }
    
       public List<RegistroDeImei> getLista(Date fi) {

        String query = " SELECT * FROM registro_de_imei  "
                + " where date(fecha_registro)='" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "' ";

        return session.createSQLQuery(query)
                .addEntity(RegistroDeImei.class)
                .list();

    }

    public Boolean existeNumeroDeImei(String numero) {

        boolean existe = false;
        String query = " SELECT * FROM registro_de_imei  where numero like '" + numero.trim() + "' limit 1 ";

        RegistroDeImei res = (RegistroDeImei) session.createSQLQuery(query).addEntity(RegistroDeImei.class).uniqueResult();

        existe = res != null ? true : false;
        return existe;

    }

    public List<RegistroDeImei> getListaPorArticulo(Boolean disponible, int articulo) {

        String query = " SELECT * FROM registro_de_imei  where disponible=:estado  and articulo=:articulo ";

        return session.createSQLQuery(query)
                .addEntity(RegistroDeImei.class)
                .setParameter("estado", disponible)
                .setParameter("articulo", articulo)
                .list();

    }
    
     public List<RegistroDeImei> getImeiDisponiblePorArticulo(int articulo) {

             String query = "  SELECT * \n"
                + "   from  registro_de_imei \n"
                + "  where \n"
                + "  numero not  in\n"
                + " (\n"
                + "   SELECT det.numero_imei from  detalle_contrato_de_servicio det\n"
                + "   INNER JOIN  articulo art on art.codigo=det.equipo\n"
                + "  where det.numero_imei is not null and art.tipo_articulo<>3\n"
                + "    and LENGTH(det.numero_sim)>4   and det.habilitado=true \n"
                + ")  and  articulo=:articulo ";
         
//        String query = " SELECT * FROM registro_de_imei  where disponible=:estado  and articulo=:articulo ";

        return session.createSQLQuery(query)
                .addEntity(RegistroDeImei.class)             
                .setParameter("articulo", articulo)
                .list();

    }

    public RegistroDeImei getRegistroDeImei(int codigo) {

        String query = " SELECT * FROM registro_de_imei  where codigo=" + codigo;

        return (RegistroDeImei) session.createSQLQuery(query).addEntity(RegistroDeImei.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return RegistroDeImei.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
