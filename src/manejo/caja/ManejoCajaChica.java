/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.caja;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Caja;
import modelo.CajaChica;
import modelo.DetalleCajaChica;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoCajaChica extends ManejoEstandar<CajaChica> {

    private static ManejoCajaChica manejoCajaChica = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoCajaChica getInstancia() {

        if (manejoCajaChica == null) {
            manejoCajaChica = new ManejoCajaChica();
        }
        return manejoCajaChica;
    }

    @Override
    public Session getSession() {
        return  session;
    }

    public List<CajaChica> getListaCajaChica() {

        String query = " SELECT * FROM caja_chica order by codigo desc ";

        return getSession().createSQLQuery(query).addEntity(CajaChica.class).list();

    }

    public List<DetalleCajaChica> getDetalleCajaChica(CajaChica cajaChica) {

        String query = " SELECT * FROM detalle_caja_chica  where caja_chica=:cajaChica  ";

        return getSession().createSQLQuery(query)
                .addEntity(DetalleCajaChica.class)
                .setParameter("cajaChica", cajaChica.getCodigo())
                .list();

    }
    
       public DetalleCajaChica getUltimoRegistroDetalleCajaChica(CajaChica cajaChica) {

        String query = " SELECT * FROM detalle_caja_chica  where caja_chica=:cajaChica  order by codigo desc limit 1 ";

        return (DetalleCajaChica)getSession().createSQLQuery(query)
                .addEntity(DetalleCajaChica.class)
                .setParameter("cajaChica", cajaChica.getCodigo())
                .uniqueResult();

    }

       public List<CajaChica> getCajaChica(Date fi,Date ff) {

        String query = " SELECT * FROM caja_chica  where  fecha_apertura  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi)
                + "'  and ' " + new SimpleDateFormat("yyyy-MM-dd").format(ff)+"' ";

        System.out.println("Query " + query);
        
        return (List<CajaChica>) getSession().createSQLQuery(query)
                .addEntity(CajaChica.class)              
                .list();

    }
    
    public CajaChica getCajaChica(Date fecha, String estado) {

        String query = " SELECT * FROM caja_chica  where estado=:estado and fecha_apertura='" + new SimpleDateFormat("yyyy-MM-dd").format(fecha)
                + "' limit 1";

        System.out.println("Query " + query);
        return (CajaChica) getSession().createSQLQuery(query)
                .addEntity(CajaChica.class)
                .setParameter("estado", estado)
                .uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return CajaChica.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().getCajaChica(new Date(), "abierta").getCaja().getNombre());
    }

}
