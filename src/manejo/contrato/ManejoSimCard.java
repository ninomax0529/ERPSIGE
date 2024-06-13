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
import modelo.ContratoDeServicio;
import modelo.RegistroDeSim;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoSimCard extends ManejoEstandar<RegistroDeSim> {

    private static ManejoSimCard manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoSimCard getInstancia() {
        if (manejo == null) {
            manejo = new ManejoSimCard();
        }
        return manejo;
    }

    public List<RegistroDeSim> getLista() {

        String query = " SELECT * FROM registro_de_sim  ";

        return session.createSQLQuery(query).addEntity(RegistroDeSim.class).list();

    }

    public List<RegistroDeSim> getListaEntreFecha(Date fi, Date ff) {

        String query = " SELECT * FROM registro_de_sim "
                + "  where   date(fecha_registro)  between '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'" + " order by codigo   ";

        return session.createSQLQuery(query)
                .addEntity(RegistroDeSim.class)
                .list();

    }

    public List<RegistroDeSim> getLista(Boolean disponible) {

        String query = " SELECT * FROM registro_de_sim  where disponible=:estado order by codigo desc ";

        return session.createSQLQuery(query)
                .addEntity(RegistroDeSim.class)
                .setParameter("estado", disponible)
                .list();

    }

    public List<RegistroDeSim> getListaSimDisponible() {

        String query = "  SELECT *  from  registro_de_sim  where duplicado=false  and  numero not  in  "
                + " (  SELECT det.numero_sim from  detalle_contrato_de_servicio det  "
                + "   INNER JOIN  articulo art on art.codigo=det.equipo \n"
                + "  where det.numero_sim is not null and art.tipo_articulo<>3 \n"
                + "    and LENGTH(det.numero_sim)>4   and det.habilitado=true  \n"
                + ") "
                + " and  numero not  in ( SELECT sim from asignacion_de_flota where estado='ACTIVA' ) "
                + " and  numero  not in ( SELECT sim from registro_de_flota where asignada=true ) ";

        System.out.println("sql sim : " + query);

        return session.createSQLQuery(query)
                .addEntity(RegistroDeSim.class)
                .list();

    }

    public Boolean estaDisponible(String sim) {

        boolean disponible = false;

        String query = " SELECT det.numero_sim from  detalle_contrato_de_servicio det  "
                + "    INNER JOIN  articulo art on art.codigo=det.equipo   \n"
                + "   where det.numero_sim is not null and art.tipo_articulo<>3  "
                + "   and LENGTH(det.numero_sim)>4  and det.habilitado=true and det.numero_sim like '" + sim + "' limit 1 ";

        System.out.println("sql sim : " + query);

        disponible = session.createSQLQuery(query)
                .uniqueResult() == null ? true : false;

        return disponible;

    }

    public ContratoDeServicio estaDisponibleEsteSim(String sim) {

        ContratoDeServicio contrato = null;

        String query = " SELECT c.* from contrato_de_servicio c "
                  + "  INNER JOIN  detalle_contrato_de_servicio det  on det.contrato_de_servicio=c.codigo "
                + "    INNER JOIN  articulo art on art.codigo=det.equipo   \n"
                + "   where det.numero_sim is not null and art.tipo_articulo<>3  "
                + "   and LENGTH(det.numero_sim)>4  and det.habilitado=true and det.numero_sim like '" + sim + "' limit 1 ";

        System.out.println("sql sim : " + query);

        contrato = (ContratoDeServicio) session.createSQLQuery(query)
                .addEntity(ContratoDeServicio.class)
                .uniqueResult();

        return contrato;

    }

    public List<RegistroDeSim> getListaDuplicado(Boolean duplicado) {

        String query = " SELECT * FROM registro_de_sim  where duplicado=:duplicado order by codigo desc ";

        return session.createSQLQuery(query)
                .addEntity(RegistroDeSim.class)
                .setParameter("duplicado", duplicado)
                .list();

    }

    public RegistroDeSim getRegistroDeSim(int codigo) {

        String query = " SELECT * FROM registro_de_sim  where codigo=" + codigo;

        return (RegistroDeSim) session.createSQLQuery(query).addEntity(RegistroDeSim.class).uniqueResult();

    }

    public Boolean existeNumeroDeSim(String numero) {

        boolean existe = false;
        String query = " SELECT * FROM registro_de_sim  where numero like '" + numero.trim() + "' limit 1 ";

        RegistroDeSim res = (RegistroDeSim) session.createSQLQuery(query).addEntity(RegistroDeSim.class).uniqueResult();

        existe = res != null ? true : false;
        return existe;

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return RegistroDeSim.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().estaDisponibleEsteSim("829-730-4846").getCliente().getNombre());
    }

}
