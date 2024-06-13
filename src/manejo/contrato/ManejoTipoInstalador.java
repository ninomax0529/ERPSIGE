/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contrato;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoInstalador;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoInstalador extends ManejoEstandar<TipoInstalador> {

    private static ManejoTipoInstalador manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoInstalador getInstancia() {
        if (manejo == null) {
            manejo = new ManejoTipoInstalador();
        }
        return manejo;
    }

    public List<TipoInstalador> getLista() {

        String query = " SELECT * FROM tipo_instalador  ";

        return session.createSQLQuery(query).addEntity(TipoInstalador.class).list();

    }

    public TipoInstalador TipoInstalador(int codigo) {

        String query = " SELECT * FROM tipo_instalador  where codigo=" + codigo;

        return (TipoInstalador) session.createSQLQuery(query).addEntity(TipoInstalador.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return TipoInstalador.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
