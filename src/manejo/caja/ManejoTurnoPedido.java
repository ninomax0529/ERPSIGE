/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.caja;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TurnoPedido;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTurnoPedido extends ManejoEstandar<TurnoPedido> {

    private static ManejoTurnoPedido manejoTurnoPedido = null;
   
    private Session session = HibernateUtil.getSession();

    public static ManejoTurnoPedido getInstancia() {
        if (manejoTurnoPedido == null) {
            manejoTurnoPedido = new ManejoTurnoPedido();
        }
        return manejoTurnoPedido;
    }

    @Override
    public Session getSession() {

        return session;
    }

    public List<TurnoPedido> getListaTurno() {

        String query = " SELECT * FROM turno_pedido  ";

        return getSession().createSQLQuery(query).addEntity(TurnoPedido.class).list();

    }

    public boolean existeTurno(Date fechaDia) {

        String query = " SELECT * FROM turno_pedido  where  fecha=' " + new SimpleDateFormat("yyyy-MM-dd").format(fechaDia) + "'";

        return (TurnoPedido) getSession().createSQLQuery(query).addEntity(TurnoPedido.class).uniqueResult() == null ? false : true;

    }

    public TurnoPedido getNumeroTurno(Date fechaDia) {

        int numeroTurno = 0;
        TurnoPedido turnoPedido;
           
        String query = " SELECT * FROM turno_pedido  where  fecha='" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDia) + "'";

        System.out.println("Sql " + query);
        turnoPedido = ((TurnoPedido) getSession().createSQLQuery(query).addEntity(TurnoPedido.class).uniqueResult());

        return turnoPedido;
    }

    @Override
    public Class getReferencia() {
        return TurnoPedido.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().getNumeroTurno(new Date("2021/02/08")));
    }

}
