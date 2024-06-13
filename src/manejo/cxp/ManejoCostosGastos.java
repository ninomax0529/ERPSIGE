/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cxp;

import manejo.caja.*;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Categoria;
import modelo.CostosYGastos;
import modelo.TipoMovimiento;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoCostosGastos extends ManejoEstandar<CostosYGastos> {

    private static ManejoCostosGastos manejo = null;
//    private Session session = HibernateUtil.getSession();

    public static ManejoCostosGastos getInstancia() {
        if (manejo == null) {
            manejo = new ManejoCostosGastos();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return HibernateUtil.getSession();
    }

    public List<CostosYGastos> getListaCostosYGastos() {

        String query = " SELECT * FROM costos_y_gastos  ";

        return getSession().createSQLQuery(query).addEntity(CostosYGastos.class).list();

    }

    public CostosYGastos getCostosYGastos(int codigo) {

        String query = " SELECT * FROM costos_y_gastos  where codigo=:codigo  ";

        return (CostosYGastos) getSession().createSQLQuery(query)
                .addEntity(CostosYGastos.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return CostosYGastos.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
