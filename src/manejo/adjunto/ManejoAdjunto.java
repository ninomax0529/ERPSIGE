package manejo.adjunto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Adjunto;

import org.hibernate.Session;

public class ManejoAdjunto extends ManejoEstandar<Adjunto> {

    private static final Logger LOG = Logger.getLogger(ManejoAdjunto.class.getName());
    private static ManejoAdjunto manejoAdjunto = null;

    public static ManejoAdjunto getInstancia() {
        if (manejoAdjunto == null) {
            manejoAdjunto = new ManejoAdjunto();
        }
        return manejoAdjunto;
    }

    @Override
    public Session getSession() {
        return HibernateUtil.getSession();
    }

    public List<Adjunto> getLista() {
        try {
            String query = " SELECT * FROM adjunto  ";
            return (List<Adjunto>) getSession().createSQLQuery(query)
                    .addEntity(Adjunto.class)
                    .list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            getSession().close();
        }
        return null;
    }

       public List<Adjunto> getListaAdjunto(int documento) {
        try {
            String query = " SELECT * FROM adjunto where documento="+documento+" ";
            return (List<Adjunto>) getSession().createSQLQuery(query)
                    .addEntity(Adjunto.class)
                    .list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            getSession().close();
        }
        return null;
    }
       
          public List<Adjunto> getListaAdjunto(int documento,int tipoDocumento) {
        try {
            String query = " SELECT * FROM adjunto where documento="+documento+" and tipo_documento="+tipoDocumento;
            return (List<Adjunto>) getSession().createSQLQuery(query)
                    .addEntity(Adjunto.class)
                    .list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            getSession().close();
        }
        return null;
    }
       
    public Adjunto getAdjuntobyCodigo(int codigo) {
        try {
            String query = " SELECT * FROM adjunto where codigo=:codigo ";

            return (Adjunto) getSession().createSQLQuery(query)
                    .addEntity(Adjunto.class)
                    .setParameter("codigo", codigo)
                    .uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            getSession().close();
        }
        return null;
    }

    @Override
    public Class getReferencia() {
        return Adjunto.class;
    }

    public static void main(String[] args) {

        Adjunto adjunto = null;

        List<Adjunto> lista = new ArrayList<>();

        for (int i = 0; i < 4; i++) {

            adjunto = new Adjunto();
            adjunto.setDocumento(1);
            adjunto.setNombreDocumentoDestino("ejemplo "+i+1);
            adjunto.setComentario("na");
            adjunto.setTipoDocumento(1);
            adjunto.setUrlDestino("adjunto/");
            adjunto.setFecha(new Date());
            lista.add(adjunto);
        }

        for (Adjunto aj : lista) {

            getInstancia().salvar(aj);
        }

        System.out.println(LOG);
    }

}
