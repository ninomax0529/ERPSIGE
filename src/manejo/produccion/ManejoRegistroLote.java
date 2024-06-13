/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.produccion;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.RegistroLote;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class ManejoRegistroLote extends ManejoEstandar<RegistroLote> {

    private static ManejoRegistroLote manejoRegistroLote = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoRegistroLote getInstancia() {
        if (manejoRegistroLote == null) {
            manejoRegistroLote = new ManejoRegistroLote();
        }
        return manejoRegistroLote;
    }

    public RegistroLote getRegistroLote(int codigo) {

        RegistroLote registroLote = (RegistroLote) session.createQuery("FROM RegistroLote WHERE codigo = :codigo ")
                .setParameter("codigo", codigo)
                .uniqueResult();
        return registroLote;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<RegistroLote> getLista() {

        String query = "SELECT * FROM  registro_lote  order by fecha desc ";

        return session.createSQLQuery(query).addEntity(RegistroLote.class).list();

    }
    
      public List<RegistroLote> getLoteArticulo(int articticulo) {

        String query = "SELECT * FROM  registro_lote where producto=:art  order by fecha desc ";

        return session.createSQLQuery(query)
                .addEntity(RegistroLote.class)
                .setParameter("art", articticulo)
                .list();

    }

    public boolean existe(Integer producto, int lote) {

        boolean flag = false;

        RegistroLote reg = (RegistroLote) session.createSQLQuery(" SELECT * FROM registro_lote "
                + "  f  where producto=:producto and "
                + "  f.numero_de_lote=:numero  limit 1 ")
                .addEntity(RegistroLote.class)
                .setParameter("producto", producto)
                .setParameter("numero", lote)
                .uniqueResult();

        if (!(reg == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    @Override
    public Class getReferencia() {
        return RegistroLote.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista().get(0).getNombre());
    }

}
