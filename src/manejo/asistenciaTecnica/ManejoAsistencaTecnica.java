/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.asistenciaTecnica;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.AsistenciaTecnica;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoAsistencaTecnica extends ManejoEstandar<AsistenciaTecnica> {

    private static ManejoAsistencaTecnica manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoAsistencaTecnica getInstancia() {
        if (manejo == null) {
            manejo = new ManejoAsistencaTecnica();
        }
        return manejo;
    }

    public List<AsistenciaTecnica> getLista() {

        String query = " SELECT * FROM asistencia_tecnica  ";

        return session.createSQLQuery(query).addEntity(AsistenciaTecnica.class).list();

    }

    public List<AsistenciaTecnica> getListaEntreFecha(Date fi, Date ff) {

        String query = " SELECT * FROM asistencia_tecnica a  where   a.fecha_registro  between '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'" + " ";

        return session.createSQLQuery(query)
                .addEntity(AsistenciaTecnica.class)
                .list();

    }

    public AsistenciaTecnica existeInstalacion(int detContrato) {

        String query = " SELECT * FROM asistencia_tecnica  where detalle_contrato=" + detContrato;

        return (AsistenciaTecnica) session.createSQLQuery(query)
                .addEntity(AsistenciaTecnica.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return AsistenciaTecnica.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
