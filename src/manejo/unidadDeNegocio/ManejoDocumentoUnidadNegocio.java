/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.unidadDeNegocio;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DocumentoUnidadDeNegocio;
import modelo.UnidadDeNegocio;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoDocumentoUnidadNegocio extends ManejoEstandar<DocumentoUnidadDeNegocio> {

    private static ManejoDocumentoUnidadNegocio manejoUnidad = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoDocumentoUnidadNegocio getInstancia() {
        if (manejoUnidad == null) {
            manejoUnidad = new ManejoDocumentoUnidadNegocio();
        }
        return manejoUnidad;
    }

    public List<DocumentoUnidadDeNegocio> getLista() {

        String query = " SELECT * FROM documento_unidad_de_negocio  ";

        return session.createSQLQuery(query).addEntity(UnidadDeNegocio.class).list();

    }

    public List<DocumentoUnidadDeNegocio> getLista(String empresa) {

        String query = " SELECT * FROM documento_unidad_de_negocio  where  nombre_empresa_o_grupo like '%" + empresa + "%' ";

        System.out.println("query " + query);
        return session.createSQLQuery(query)
                .addEntity(UnidadDeNegocio.class)
                .list();

    }

    public List<DocumentoUnidadDeNegocio> getListaConRnc() {

        String query = " SELECT * FROM documento_unidad_de_negocio  where LENGTH(rnc)>5  ";

        System.out.println("query " + query);
        return session.createSQLQuery(query)
                .addEntity(DocumentoUnidadDeNegocio.class)
                .list();

    }

    public DocumentoUnidadDeNegocio getTipoDocumento(int codUnidad, int tipodoc) {

        String query = " SELECT * FROM documento_unidad_de_negocio  where unidad_negocio=:ung and tipo_documento=:tipodoc ";

        return (DocumentoUnidadDeNegocio) session.createSQLQuery(query)
                .addEntity(DocumentoUnidadDeNegocio.class)
                .setParameter("ung", codUnidad)
                .setParameter("tipodoc", tipodoc)
                .uniqueResult();

    }

     public DocumentoUnidadDeNegocio getTipoDocumento(int codUnidad, int tipodoc,int formato) {

        String query = " SELECT * FROM documento_unidad_de_negocio  where unidad_negocio=:ung and tipo_documento=:tipodoc and numero=:formato ";

        return (DocumentoUnidadDeNegocio) session.createSQLQuery(query)
                .addEntity(DocumentoUnidadDeNegocio.class)
                .setParameter("ung", codUnidad)
                .setParameter("tipodoc", tipodoc)
                 .setParameter("formato", formato)
                .uniqueResult();

    }
    
    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return DocumentoUnidadDeNegocio.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().getTipoDocumento(5, 7).getNumero());
    }

}
