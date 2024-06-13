/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.descuento;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DescuentoPorUsuario;

import org.hibernate.Session;
//import utils.Encriptar;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoDescuentoPorUsuario extends ManejoEstandar<DescuentoPorUsuario> {

    private static ManejoDescuentoPorUsuario manejoDescuentoPorUsuario = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoDescuentoPorUsuario getInstancia() {
        if (manejoDescuentoPorUsuario == null) {
            manejoDescuentoPorUsuario = new ManejoDescuentoPorUsuario();
        }
        return manejoDescuentoPorUsuario;
    }

    public DescuentoPorUsuario getDescuentoPorUsuario(int usuario) {

        DescuentoPorUsuario descuento = (DescuentoPorUsuario) session.createQuery("FROM DescuentoPorUsuario WHERE usuario.codigo = :usuario AND habilitado=true  limit 1 ")
                .setParameter("usuario", usuario)
                //                .setParameter("clave", getPassword(claveLogin))
                .uniqueResult();
        return descuento;
    }

    public DescuentoPorUsuario getDescuentoPorUsuario(String usuario, String clave) {

        DescuentoPorUsuario descuento = (DescuentoPorUsuario) session.createQuery("FROM DescuentoPorUsuario "
                + "  WHERE nombreUsuario=:usuario AND clave=:clave and  habilitado=true")
                .setParameter("usuario", usuario)
                .setParameter("clave", clave)
                //                .setParameter("clave", getPassword(claveLogin))
                .uniqueResult();
        return descuento;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<DescuentoPorUsuario> getLista() {

        String query = "SELECT * FROM  descuento_por_usuario  ";

        return session.createSQLQuery(query).addEntity(DescuentoPorUsuario.class).list();

    }

    @Override
    public Class getReferencia() {
        return DescuentoPorUsuario.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().getDescuentoPorUsuario("max","123").getMaximo());
    }

}
