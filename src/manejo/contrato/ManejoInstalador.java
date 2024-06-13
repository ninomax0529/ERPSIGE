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
import modelo.Instalador;
import modelo.RegistroDeSim;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoInstalador extends ManejoEstandar<Instalador> {

    private static ManejoInstalador manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoInstalador getInstancia() {
        if (manejo == null) {
            manejo = new ManejoInstalador();
        }
        return manejo;
    }

    public List<Instalador> getLista() {

        String query = " SELECT * FROM instalador  ";

        return session.createSQLQuery(query).addEntity(Instalador.class).list();

    }

    public List<Instalador> getListaEntreFecha(Date fi, Date ff) {

        String query = " SELECT * FROM registro_de_sim "
                + "  where   date(fecha_registro)  between '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'" + " order by codigo   ";

        return session.createSQLQuery(query)
                .addEntity(Instalador.class)
                .list();

    }

    public List<Instalador> getLista(Boolean disponible) {

        String query = " SELECT * FROM instalador  where disponible=:estado order by codigo desc ";

        return session.createSQLQuery(query)
                .addEntity(Instalador.class)
                .setParameter("estado", disponible)
                .list();

    }

    public List<Instalador> getListaDuplicado(Boolean duplicado) {

        String query = " SELECT * FROM registro_de_sim  where duplicado=:duplicado order by codigo desc ";

        return session.createSQLQuery(query)
                .addEntity(Instalador.class)
                .setParameter("duplicado", duplicado)
                .list();

    }

    public RegistroDeSim getRegistroDeSim(int codigo) {

        String query = " SELECT * FROM registro_de_sim  where codigo=" + codigo;

        return (RegistroDeSim) session.createSQLQuery(query).addEntity(RegistroDeSim.class).uniqueResult();

    }


    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return Instalador.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
