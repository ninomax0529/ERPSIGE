/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.empresa;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.EmpresaOGrupo;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoEmpresaOGrupo extends ManejoEstandar<EmpresaOGrupo> {

    private static ManejoEmpresaOGrupo manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoEmpresaOGrupo getInstancia() {
        if (manejo == null) {
            manejo = new ManejoEmpresaOGrupo();
        }
        return manejo;
    }

    public List<EmpresaOGrupo> getLista() {

        String query = " SELECT * FROM empresa_o_grupo  ";

        return session.createSQLQuery(query).addEntity(EmpresaOGrupo.class).list();

    }
    
 
      
         public EmpresaOGrupo getEmpresaOGrupo(int codigo) {

        String query = " SELECT * FROM empresa_o_grupo  where codigo="+codigo;

        return (EmpresaOGrupo)session.createSQLQuery(query).addEntity(EmpresaOGrupo.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return EmpresaOGrupo.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
