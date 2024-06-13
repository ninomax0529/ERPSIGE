/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contrato;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.ModalidadEquipo;
import modelo.TipoDeServicio;
import modelo.TipoVehiculo;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoDeServicio extends ManejoEstandar<TipoDeServicio> {

    private static ManejoTipoDeServicio manejoTipoDeServicio = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoDeServicio getInstancia() {
        if (manejoTipoDeServicio == null) {
            manejoTipoDeServicio = new ManejoTipoDeServicio();
        }
        return manejoTipoDeServicio;
    }

    public List<TipoDeServicio> getLista() {

        String query = " SELECT * FROM tipo_de_servicio  ";

        return session.createSQLQuery(query).addEntity(TipoDeServicio.class).list();

    }

    public TipoDeServicio getTipoDeServicio(int codigo) {

        String query = " SELECT * FROM tipo_de_servicio  where codigo=" + codigo;

        return (TipoDeServicio) session.createSQLQuery(query).addEntity(TipoDeServicio.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return TipoDeServicio.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
