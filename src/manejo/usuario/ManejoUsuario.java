/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.usuario;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Usuario;

import org.hibernate.Session;
//import utils.Encriptar;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoUsuario extends ManejoEstandar<Usuario> {

    private static ManejoUsuario manejoUsuario = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoUsuario getInstancia() {
        if (manejoUsuario == null) {
            manejoUsuario = new ManejoUsuario();
        }
        return manejoUsuario;
    }

    public Usuario getUsuario(String usuarioLogin, String claveLogin) {

        Usuario usuario = (Usuario) session.createQuery("FROM Usuario WHERE usuario = :usuario AND contrasena=:clave")
                .setParameter("usuario", usuarioLogin)
                .setParameter("clave", claveLogin)
                //                .setParameter("clave", getPassword(claveLogin))
                .uniqueResult();
        return usuario;
    }

    public Usuario getUsuario(String usuarioLogin, String claveLogin, int unidadNegocio) {

        Usuario usuario = (Usuario) session.createSQLQuery(" select * from  usuario WHERE usuario = :usuario AND  "
                + " contrasena=:clave and unidad_de_negocio=:ung  and activo=true limit 1 ")
                .addEntity(Usuario.class)
                .setParameter("usuario", usuarioLogin)
                .setParameter("clave", claveLogin)
                .setParameter("ung", unidadNegocio)
                .uniqueResult();

        //                .setParameter("clave", getPassword(claveLogin))
        return usuario;
    }

    private String getPassword(String u) {

        return null; //Encriptar.getMD5(String.valueOf(u));
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Usuario> getLista() {

        String query = "SELECT * FROM  usuario  ";

        return session.createSQLQuery(query).addEntity(Usuario.class).list();

    }

    @Override
    public Class getReferencia() {
        return Usuario.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista().get(0).getNombre());
    }

}
