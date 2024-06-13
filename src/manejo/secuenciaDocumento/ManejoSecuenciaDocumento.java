/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.secuenciaDocumento;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.SecuenciaDocumento;
import modelo.Unidad;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoSecuenciaDocumento extends ManejoEstandar<SecuenciaDocumento> {

    private static ManejoSecuenciaDocumento manejoSecuenciaDocumento = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoSecuenciaDocumento getInstancia() {
        if (manejoSecuenciaDocumento == null) {
            manejoSecuenciaDocumento = new ManejoSecuenciaDocumento();
        }
        return manejoSecuenciaDocumento;
    }

    public List<SecuenciaDocumento> getLista() {

        String query = " SELECT * FROM secuencia_documento  ";

        return session.createSQLQuery(query).addEntity(SecuenciaDocumento.class).list();

    }

    public SecuenciaDocumento getSecuenciaDocumento(int unidadNegocio, int tipoDocumento) {

        String query = " SELECT * FROM secuencia_documento  where unidad_de_negocio=:ung  and tipo_documento=:tipoDocumento ";

        return (SecuenciaDocumento) session.createSQLQuery(query)
                .addEntity(SecuenciaDocumento.class)
                .setParameter("ung", unidadNegocio)
                .setParameter("tipoDocumento", tipoDocumento)
                .uniqueResult();

    }

    public boolean existeSecuencia(SecuenciaDocumento secParam) {

        boolean flag = false;

        SecuenciaDocumento sec = (SecuenciaDocumento) session.createSQLQuery(" SELECT * FROM secuencia_documento "
                + "  where unidad_de_negocio=:ung and tipo_documento=:td limit 1 ")
                .addEntity(SecuenciaDocumento.class)
                .setParameter("ung", secParam.getUnidadDeNegocio().getCodigo())
                .setParameter("td", secParam.getTipoDocumento().getCodigo())
                .uniqueResult();

        if (!(sec == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }
    
      public boolean existeSecuencia(int ung,int tipo) {

        boolean flag = false;

        SecuenciaDocumento sec = (SecuenciaDocumento) session.createSQLQuery(" SELECT * FROM secuencia_documento "
                + "  where unidad_de_negocio=:ung and tipo_documento=:td limit 1 ")
                .addEntity(SecuenciaDocumento.class)
                .setParameter("ung",ung)
                .setParameter("td", tipo)
                .uniqueResult();

        if (!(sec == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return SecuenciaDocumento.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().existeSecuencia(5,23));
    }

}
