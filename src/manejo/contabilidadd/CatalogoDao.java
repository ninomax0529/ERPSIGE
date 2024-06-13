/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contabilidadd;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Catalogo;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author luis
 */
public class CatalogoDao extends ManejoEstandar<Catalogo> {

    private static CatalogoDao manejo = null;
    private Session session = HibernateUtil.getSession();

    public static CatalogoDao getInstancia() {
        if (manejo == null) {
            manejo = new CatalogoDao();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Catalogo> getCatalogo() {

        String query = " SELECT * FROM catalogo where unidad_de_negocio=:ung  order by cuenta";

        return session.createSQLQuery(query).addEntity(Catalogo.class)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

    }

    public List<Catalogo> getCuentaControl(String grupo) {

        List<Catalogo> lista;
        String query = "select * from catalogo where nivel=3  and unidad_de_negocio=:ung   and  grupo like '" + grupo + "%'";

        lista = session.createSQLQuery(query).addEntity(Catalogo.class)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

        return lista;
    }

    public Catalogo getCuentaControl(int grupo, int subGrupo, int nivel) {

        Catalogo catalogo;
        String query = " select * from catalogo where nivel=:nivel and unidad_de_negocio=:ung and  grupo=:grupo  and sub_grupo=:subGrupo ";

        catalogo = (Catalogo) session.createSQLQuery(query).addEntity(Catalogo.class)
                .setParameter("nivel", nivel)
                .setParameter("grupo", grupo)
                .setParameter("subGrupo", subGrupo)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list().get(0);

        return catalogo;
    }

//    public void salvar(Catalogo catalogo) {
//
//        session.beginTransaction();
//
//        session.save(catalogo);
//
//        session.getTransaction().commit();
//
//    }

    public void remover(Catalogo catalogo) {

        try {

            session.beginTransaction();

            session.delete(catalogo);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    public void exportCatalogo(List<Catalogo> catalogos) throws IOException {

        FileWriter csvWriter = new FileWriter("catalogo.csv");
        csvWriter.append("cuenta");
        csvWriter.append(";");
        csvWriter.append("descripcion");
        csvWriter.append(";");
        csvWriter.append("origen");
        csvWriter.append("\n");

        for (Catalogo cat : catalogos) {
            csvWriter.append(cat.getCuenta());
            csvWriter.append(";");
            csvWriter.append(cat.getDescripcion());
            csvWriter.append(";");
            csvWriter.append(cat.getNivel().toString());
            csvWriter.append("\n");

        }

        csvWriter.flush();
        csvWriter.close();

    }

    public static void main(String[] args) throws IOException {

        List<Catalogo> catalogo = CatalogoDao.getInstancia().getCatalogo();
        CatalogoDao.getInstancia().exportCatalogo(catalogo);
        System.out.println("Finish exporting!");
//        for (int i = 0; i < catalogo.size(); i++) {
//            System.out.println("Catalogo: " + catalogo.get(i).getDescripcion());
//        }

    }

    @Override
    public Class getReferencia() {
       return Catalogo.class;
    }

}
