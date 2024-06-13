/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.proyecto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Empleado;
import modelo.Proyecto;
import modelo.RegistroHoraExtra;

import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoProyecto extends ManejoEstandar<Proyecto> {

    private static ManejoProyecto manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoProyecto getInstancia() {
        if (manejo == null) {
            manejo = new ManejoProyecto();
        }
        return manejo;
    }

    public List<Proyecto> getLista() {

        String query = " SELECT * FROM proyecto where unidad_de_negocio=:ung  ";

        return session.createSQLQuery(query)
                .addEntity(Proyecto.class)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

    }

    public List<Proyecto> getLista(int tipoNom) {

        String query = " SELECT * FROM empleado where  condicion=1 and  tipo_nomina=:tipoNom and unidad_de_negocio=:ung ";

        System.out.println("sql " + query + " tipoNom " + tipoNom + " " + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        return session.createSQLQuery(query)
                .addEntity(Empleado.class)
                .setParameter("tipoNom", tipoNom)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

    }

    public Proyecto getProyecto(int codigo) {

        String query = " SELECT * FROM proyecto  where codigo=:codigo and unidad_de_negocio=:ung " ;

        return (Proyecto) session.createSQLQuery(query)
                .addEntity(Empleado.class)
                .setParameter("codigo", codigo)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .uniqueResult();

    }
    
    
      public List<Proyecto> getLista(Date fi, Date ff) {

        String query = " SELECT * FROM proyecto  where fecha_inicio between "
                + "'" + new SimpleDateFormat("yyyy/MM/dd").format(fi) + "' and '" + new SimpleDateFormat("yyyy/MM/dd").format(ff) + "' "
                + " and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Query " + query);
        return session.createSQLQuery(query).addEntity(Proyecto.class).list();

    }

    

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return Proyecto.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
