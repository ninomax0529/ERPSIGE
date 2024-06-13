/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cliente;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DireccionCliente;

import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoDireccionCliente extends ManejoEstandar<DireccionCliente> {

    private static ManejoDireccionCliente manejoDireccionCliente = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoDireccionCliente getInstancia() {
        if (manejoDireccionCliente == null) {
            manejoDireccionCliente = new ManejoDireccionCliente();
        }
        return manejoDireccionCliente;
    }

    public List<DireccionCliente> getLista() {

        String query = " SELECT * FROM direccion_cliente  ";

        return session.createSQLQuery(query).addEntity(DireccionCliente.class).list();

    }
    
   

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return DireccionCliente.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
