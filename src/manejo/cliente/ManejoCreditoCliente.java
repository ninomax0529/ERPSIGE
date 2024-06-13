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
import modelo.CreditoCliente;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoCreditoCliente extends ManejoEstandar<CreditoCliente> {

    private static ManejoCreditoCliente manejoClreditoCliente = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoCreditoCliente getInstancia() {

        if (manejoClreditoCliente == null) {
            manejoClreditoCliente = new ManejoCreditoCliente();
        }
        return manejoClreditoCliente;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<CreditoCliente> getCredito() {

        String query = "SELECT * FROM  credito_cliente  ";

        return session.createSQLQuery(query).addEntity(CreditoCliente.class).list();

    }
    
    
    
    public List<CreditoCliente> getCreditoCliente(int cliente) {

        String query = "SELECT * FROM  credito_cliente  where cliente=:cliente ";

        return session.createSQLQuery(query)
                .addEntity(CreditoCliente.class)
                .setParameter("cliente", cliente)
                .list();

    }

    public Double getMontoCreditoCliente(int cliente) {

        String query = " SELECT ifnull(sum(monto),0) as monto FROM  credito_cliente   where habilitado=true and  cliente=:cliente ";

        return (Double) session.createSQLQuery(query)
                .setParameter("cliente", cliente)
                .uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return CreditoCliente.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos "+getInstancia().getMontoCreditoCliente(2));
    }

}
