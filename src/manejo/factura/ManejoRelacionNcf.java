/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.factura;

import java.math.BigInteger;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.RelacionNcf;
import modelo.TipoNcf;
import org.hibernate.Session;

/**
 *
 * @author falcon
 */
public class ManejoRelacionNcf extends ManejoEstandar<RelacionNcf> {

    Session session = HibernateUtil.getSession();
    private static ManejoRelacionNcf manejo = null;

    public static ManejoRelacionNcf getInstancia() {
        if (manejo == null) {
            manejo = new ManejoRelacionNcf();
        }
        return manejo;
    }

    public List getLista() {

        return session.createQuery("SELECT r FROM RelacionNcf r  ORDER BY r.codigo").list();
    }

    public void Salvar(RelacionNcf objeto) {
        persist(objeto);
    }

    public RelacionNcf getNCF(TipoNcf tp) {

        RelacionNcf ncf = (RelacionNcf) session.createSQLQuery("SELECT rn.* FROM relacion_ncf rn "
                + "WHERE rn.tipo_ncf=:tiponcf and activo=true")
                .addEntity(RelacionNcf.class)
                .setParameter("tiponcf", tp.getCodigo()).uniqueResult();

//        session.refresh(ncf);
//        // Control para que actualice el tipo de ncf elegido con los datos de la base de datos 
//        if (ncf != null) {
//            RelacionNcf ncf2 = (RelacionNcf) HibernateUtil.getSession().load(RelacionNcf.class, ncf.getCodigo());
//
//            if (ncf2 != null) {
//                ncf.setSecuencia(ncf2.getSecuencia());
//                ncf.setActual(ncf2.getActual());
//            }
//        }
        return ncf;

    }

    public Integer getDisponibilidad(int tipoNcf) {

        BigInteger disponible = (BigInteger) session.createSQLQuery(" SELECT (cantidad-secuencia) "
                + " from relacion_ncf  where  activo=true  and tipo_ncf=:tiponcf ")
                .setParameter("tiponcf", tipoNcf).uniqueResult();

        return disponible == null ? 0 : disponible.intValue();

    }

    public Integer getDisponibilidad(int tipoNcf, int ung) {

        BigInteger disponible = (BigInteger) session.createSQLQuery(" SELECT (cantidad-secuencia) "
                + " from relacion_ncf  "
                + "  where  activo=true  and tipo_ncf=:tiponcf  and unidad_de_negocio=:ung  ")
                .setParameter("tiponcf", tipoNcf)
                .setParameter("ung", ung)
                .uniqueResult();

        return disponible == null ? 0 : disponible.intValue();

    }

    public RelacionNcf getNCFUnidadDeNegocio(TipoNcf tp, int ung) {

        RelacionNcf ncf = (RelacionNcf) session.createSQLQuery(" SELECT rn.* FROM relacion_ncf rn "
                + " WHERE rn.tipo_ncf=:tiponcf and activo=true and unidad_de_negocio=:ung limit 1 ")
                .addEntity(RelacionNcf.class)
                .setParameter("tiponcf", tp.getCodigo())
                .setParameter("ung", ung)
                .uniqueResult();

//        session.refresh(ncf);
//        // Control para que actualice el tipo de ncf elegido con los datos de la base de datos 
//        if (ncf != null) {
//            RelacionNcf ncf2 = (RelacionNcf) HibernateUtil.getSession().load(RelacionNcf.class, ncf.getCodigo());
//
//            if (ncf2 != null) {
//                ncf.setSecuencia(ncf2.getSecuencia());
//                ncf.setActual(ncf2.getActual());
//            }
//        }
        return ncf;

    }

    public RelacionNcf getNCFEmpresaOGrupo(TipoNcf tp, int empresa) {

        RelacionNcf ncf = (RelacionNcf) session.createSQLQuery(" SELECT rn.* FROM relacion_ncf rn "
                + " WHERE rn.tipo_ncf=:tiponcf and activo=true and empresa_o_grupo=:empresa limit 1 ")
                .addEntity(RelacionNcf.class)
                .setParameter("tiponcf", tp.getCodigo())
                .setParameter("empresa", empresa)
                .uniqueResult();

//        session.refresh(ncf);
//        // Control para que actualice el tipo de ncf elegido con los datos de la base de datos 
//        if (ncf != null) {
//            RelacionNcf ncf2 = (RelacionNcf) HibernateUtil.getSession().load(RelacionNcf.class, ncf.getCodigo());
//
//            if (ncf2 != null) {
//                ncf.setSecuencia(ncf2.getSecuencia());
//                ncf.setActual(ncf2.getActual());
//            }
//        }
        return ncf;

    }

    public RelacionNcf getNCFEmpresaOGrupo(int tp, int empresa) {

        RelacionNcf ncf = (RelacionNcf) session.createSQLQuery(" SELECT rn.* FROM relacion_ncf rn "
                + " WHERE rn.tipo_ncf=:tiponcf and activo=true and empresa_o_grupo=:empresa limit 1 ")
                .addEntity(RelacionNcf.class)
                .setParameter("tiponcf", tp)
                .setParameter("empresa", empresa)
                .uniqueResult();

//        session.refresh(ncf);
//        // Control para que actualice el tipo de ncf elegido con los datos de la base de datos 
//        if (ncf != null) {
//            RelacionNcf ncf2 = (RelacionNcf) HibernateUtil.getSession().load(RelacionNcf.class, ncf.getCodigo());
//
//            if (ncf2 != null) {
//                ncf.setSecuencia(ncf2.getSecuencia());
//                ncf.setActual(ncf2.getActual());
//            }
//        }
        return ncf;

    }

    public RelacionNcf getNCFUnidadDeNegocio(String prefijo, int ung) {

        RelacionNcf ncf = (RelacionNcf) session.createSQLQuery("  SELECT rn.* FROM relacion_ncf rn INNER JOIN "
                + " tipo_ncf tncf ON rn.tipo_ncf=tncf.codigo "
                + "   WHERE  activo=true and unidad_de_negocio=:ung  and tncf.prefijo=:prefijo  limit 1 ")
                .addEntity(RelacionNcf.class)
                .setParameter("prefijo", prefijo)
                .setParameter("ung", ung)
                .uniqueResult();

//        session.refresh(ncf);
//        // Control para que actualice el tipo de ncf elegido con los datos de la base de datos 
//        if (ncf != null) {
//            RelacionNcf ncf2 = (RelacionNcf) HibernateUtil.getSession().load(RelacionNcf.class, ncf.getCodigo());
//
//            if (ncf2 != null) {
//                ncf.setSecuencia(ncf2.getSecuencia());
//                ncf.setActual(ncf2.getActual());
//            }
//        }
        return ncf;

    }

    public RelacionNcf getNCF(int tp) {

        RelacionNcf ncf = (RelacionNcf) session.createSQLQuery(" SELECT rn.* FROM relacion_ncf rn "
                + " WHERE rn.tipo_ncf=:tiponcf and activo=true ")
                .addEntity(RelacionNcf.class)
                .setParameter("tiponcf", tp).uniqueResult();

//        session.refresh(ncf);
//        // Control para que actualice el tipo de ncf elegido con los datos de la base de datos 
//        if (ncf != null) {
//            RelacionNcf ncf2 = (RelacionNcf) HibernateUtil.getSession().load(RelacionNcf.class, ncf.getCodigo());
//
//            if (ncf2 != null) {
//                ncf.setSecuencia(ncf2.getSecuencia());
//                ncf.setActual(ncf2.getActual());
//            }
//        }
        return ncf;

    }

    public RelacionNcf getNCFUnidadDeNegocio(int tp, int ung) {

        System.out.println("tipo : " + tp + " " + ung);
        RelacionNcf ncf = (RelacionNcf) session.createSQLQuery(" SELECT rn.* FROM relacion_ncf rn "
                + " WHERE rn.tipo_ncf=:tiponcf and activo=true and unidad_de_negocio=:ung limit 1 ")
                .addEntity(RelacionNcf.class)
                .setParameter("tiponcf", tp)
                .setParameter("ung", ung)
                .uniqueResult();

//        session.refresh(ncf);
//        // Control para que actualice el tipo de ncf elegido con los datos de la base de datos 
//        if (ncf != null) {
//            RelacionNcf ncf2 = (RelacionNcf) HibernateUtil.getSession().load(RelacionNcf.class, ncf.getCodigo());
//
//            if (ncf2 != null) {
//                ncf.setSecuencia(ncf2.getSecuencia());
//                ncf.setActual(ncf2.getActual());
//            }
//        }
        return ncf;

    }

    public RelacionNcf getNCFUnidadDeNegocio(int tp, int ung, boolean empresa) {

     
        
          RelacionNcf ncf=null;
         
          if(empresa){
                 System.out.println("tipo : " + tp + " unidad " + ung+"  empresa : "+empresa);
                  ncf = (RelacionNcf) session.createSQLQuery(" SELECT rn.* FROM relacion_ncf rn "
                + " WHERE rn.tipo_ncf=:tiponcf and activo=true and empresa_o_grupo=:ung limit 1 ")
                .addEntity(RelacionNcf.class)
                .setParameter("tiponcf", tp)
                .setParameter("ung", ung)
                .uniqueResult();
          }else{
              
                 System.out.println("tipo : " + tp + " unidad " + ung+"  empresa : "+empresa);
                  ncf = (RelacionNcf) session.createSQLQuery(" SELECT rn.* FROM relacion_ncf rn "
                + " WHERE rn.tipo_ncf=:tiponcf and activo=true and unidad_de_negocio=:ung limit 1 ")
                .addEntity(RelacionNcf.class)
                .setParameter("tiponcf", tp)
                .setParameter("ung", ung)
                .uniqueResult();
          }

        return ncf;

    }

    public static void main(String[] args) {
        System.out.println("RelacionNcf  : " + getInstancia().getNCFUnidadDeNegocio(1,3, true));
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return RelacionNcf.class;
    }
}
