/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contabilidadd;

import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import modelo.DocumentoAnulado;
import modelo.TipoDocumento;
import org.hibernate.Session;
import utiles.VariablesGlobales;

public class DocumentoAnuladoDao {

    private static DocumentoAnuladoDao manejoDocumentoAnuladoDao = null;
    private Session session = HibernateUtil.getSession();

    public static DocumentoAnuladoDao getInstancia() {
        if (manejoDocumentoAnuladoDao == null) {
            manejoDocumentoAnuladoDao = new DocumentoAnuladoDao();
        }
        return manejoDocumentoAnuladoDao;
    }

    public Session getSession() {
        return session;
    }

    public List<DocumentoAnulado> getLista() {

        String query = "SELECT * FROM documento_anulado";

        return session.createSQLQuery(query).addEntity(DocumentoAnulado.class).list();

    }
    
     public List<DocumentoAnulado> getLista(String paramSql) {

        String query = "SELECT * FROM documento_anulado  "+paramSql;

        return session.createSQLQuery(query).addEntity(DocumentoAnulado.class).list();

    }

    public void salvar(DocumentoAnulado documentoAnulado) {

        session.beginTransaction();

        session.save(documentoAnulado);

        session.getTransaction().commit();

    }

    public void registroDocumentoAnulado(int documento, String numero, int tipoDocumento, String comentario) {

        try {

            DocumentoAnulado documentoAnulado = new DocumentoAnulado();
            documentoAnulado.setDocumento(documento);
            documentoAnulado.setNumeroDocumento(numero);
            documentoAnulado.setFecha(new Date());
            documentoAnulado.setFechaRegistro(new Date());
            documentoAnulado.setTipoDocumento(new TipoDocumento(tipoDocumento));
            documentoAnulado.setUsuario(VariablesGlobales.USUARIO);
            documentoAnulado.setComentario(comentario);

            getInstancia().salvar(documentoAnulado);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

    }

}
