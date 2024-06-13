/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cliente;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Cliente;
//import modelo.Usuariop;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoCliente extends ManejoEstandar<Cliente> {

    private static ManejoCliente manejoCliente = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoCliente getInstancia() {

        if (manejoCliente == null) {
            manejoCliente = new ManejoCliente();
        }
        return manejoCliente;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Cliente> getCliente() {

        String query = " SELECT * FROM  Cliente "
                + "  where  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(Cliente.class).list();

    }
    
     public List<Cliente> getClienteCumpleaNio() {

        String query = " SELECT * FROM cliente  where  day(fecha_cumpleano)=day(NOW())  and MONTH(fecha_cumpleano)=MONTH(now())  ";
              

        return session.createSQLQuery(query).addEntity(Cliente.class).list();

    }

    public List<Cliente> getClientePorEstado(int estado) {

        String query = " SELECT * FROM  Cliente "
                + "  where estado_cliente=:estado and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(Cliente.class).list();

    }

    public Cliente getCliente(int codigo) {

        String query = "SELECT * FROM  Cliente where codigo=:cliente  and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return (Cliente) session.createSQLQuery(query).addEntity(Cliente.class)
                .setParameter("cliente", codigo)
                .uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return Cliente.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos "+getInstancia().getLista().get(0).getApellidos());
    }

}
