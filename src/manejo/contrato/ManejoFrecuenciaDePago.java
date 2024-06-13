/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contrato;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Delivery;
import modelo.FrecuenciaDePago;

import org.hibernate.Session;
//import utils.Encriptar;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoFrecuenciaDePago extends ManejoEstandar<FrecuenciaDePago> {

    private static ManejoFrecuenciaDePago manejoFrecuenciaDePago = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoFrecuenciaDePago getInstancia() {
        if (manejoFrecuenciaDePago == null) {
            manejoFrecuenciaDePago = new ManejoFrecuenciaDePago();
        }
        return manejoFrecuenciaDePago;
    }

    public FrecuenciaDePago getFrecuenciaDePago(int codigo) {

        FrecuenciaDePago frecuencia = (FrecuenciaDePago) session.createQuery("FROM frecuencia_de_pago WHERE codigo = :codigo ")
                .setParameter("codigo", codigo)
                .uniqueResult();
        return frecuencia;
    }


    @Override
    public Session getSession() {
        return session;
    }

    public List<FrecuenciaDePago> getLista() {

        String query = "SELECT * FROM  frecuencia_de_pago  ";

        return session.createSQLQuery(query).addEntity(FrecuenciaDePago.class).list();

    }

    @Override
    public Class getReferencia() {
        return FrecuenciaDePago.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista().get(0).getNombre());
    }

}
