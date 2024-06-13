/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.nomina;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DetalleRegistroHoraExtra;
import modelo.Nomina;
import modelo.RegistroHoraExtra;

import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoRegistroHoraExtra extends ManejoEstandar<RegistroHoraExtra> {

    private static ManejoRegistroHoraExtra manejoRegistroHoraExtra = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoRegistroHoraExtra getInstancia() {
        if (manejoRegistroHoraExtra == null) {
            manejoRegistroHoraExtra = new ManejoRegistroHoraExtra();
        }
        return manejoRegistroHoraExtra;
    }

    public List<RegistroHoraExtra> getLista() {

        String query = " SELECT * FROM registro_hora_extra  where  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(RegistroHoraExtra.class).list();

    }

    public List<RegistroHoraExtra> getLista(Date fi, Date ff) {

        String query = " SELECT * FROM registro_hora_extra  where fecha_inicio between "
                + "'" + new SimpleDateFormat("yyyy/MM/dd").format(fi) + "' and '" + new SimpleDateFormat("yyyy/MM/dd").format(ff) + "' "
                + " and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Query " + query);
        return session.createSQLQuery(query).addEntity(RegistroHoraExtra.class).list();

    }

    public List<DetalleRegistroHoraExtra> getLista(int rgHoraExtra) {

        String query = " SELECT * FROM detalle_registro_hora_extra det "
                + "  where det.registro_hora_extra=:rhextra ";

        return session.createSQLQuery(query)
                .addEntity(DetalleRegistroHoraExtra.class)
                .setParameter("rhextra", rgHoraExtra)
                .list();

    }

    public RegistroHoraExtra getRegistroHoraExtra(int codigo) {

        String query = " SELECT * FROM registro_hora_extra  where codigo=" + codigo;

        return (RegistroHoraExtra) session.createSQLQuery(query).addEntity(RegistroHoraExtra.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return RegistroHoraExtra.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
