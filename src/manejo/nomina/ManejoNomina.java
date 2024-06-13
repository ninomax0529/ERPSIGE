/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.nomina;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DetalleNomina;
import modelo.Factura;
import modelo.Nomina;

import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoNomina extends ManejoEstandar<Nomina> {

    private static ManejoNomina manejoNomina = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoNomina getInstancia() {
        if (manejoNomina == null) {
            manejoNomina = new ManejoNomina();
        }
        return manejoNomina;
    }

    public List<Nomina> getLista() {

        String query = " SELECT * FROM nomina  where  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() + ""
                + " order by codigo desc  ";

        return session.createSQLQuery(query).addEntity(Nomina.class).list();

    }

    public List<Nomina> getLista(Date fi, Date ff) {

        String query = " SELECT * FROM nomina  where fecha_corte between "
                + "'" + new SimpleDateFormat("yyyy/MM/dd").format(fi) + "' and '" + new SimpleDateFormat("yyyy/MM/dd").format(ff) + "' "
                + " and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() + " order by codigo desc  ";

        System.out.println("Query " + query);
        return session.createSQLQuery(query).addEntity(Nomina.class).list();

    }

    public Nomina getNomina(int codigo) {

        String query = " SELECT * FROM nomina  where codigo=" + codigo + " "
                + " and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return (Nomina) session.createSQLQuery(query).addEntity(Nomina.class).uniqueResult();

    }

    public DetalleNomina getUltimaNomina(int codEmp, int tipoNom, int ung) {

        String query = "  select dn.* "
                + "   from nomina n inner join detalle_nomina dn on dn.nomina=n.codigo "
                + "  where n.tipo_nomina=:tipoNom  and n.anulada=false  and empleado=:codEmp  and  unidad_de_negocio=:ung  and dn.codigo="
                + "  ( select  max(dn.codigo) from nomina n inner join detalle_nomina dn on dn.nomina=n.codigo "
                + "  where n.tipo_nomina=:tipoNom  and n.anulada=false  and empleado=:codEmp  and  unidad_de_negocio=:ung  ) ";

        return (DetalleNomina) session.createSQLQuery(query)
                //.addEntity(DetalleNomina.class)
                .setParameter("tipoNom", tipoNom)
                .setParameter("codEmp", codEmp)
                .setParameter("ung", ung)
                .uniqueResult();

    }

    public Nomina getNominaEnProceso(int estado, int tipoNom) {

        String query = " SELECT * FROM nomina  where  anulada=false and  estado=:estado and "
                + "  tipo_nomina=:tipoNom and  "
                + " unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() + " order by codigo desc limit 1 ";

        System.out.println("query " + query);
        return (Nomina) session.createSQLQuery(query)
                .addEntity(Nomina.class)
                .setParameter("estado", estado)
                .setParameter("tipoNom", tipoNom)
                .uniqueResult();

    }

    public Nomina getNominasinPagar(int estado, int tipoNom) {

        String query = " SELECT * FROM nomina  where anulada=false and estado=:estado and tipo_nomina=:tipoNom  and  pagada=false "
                + "  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() + " "
                + "  order by codigo desc limit 1 ";

        System.out.println("query " + query);
        return (Nomina) session.createSQLQuery(query)
                .addEntity(Nomina.class)
                .setParameter("estado", estado)
                .setParameter("tipoNom", tipoNom)
                .uniqueResult();

    }

    public List<Nomina> getNominasinPagar(int estado) {

        String query = " SELECT * FROM nomina  where anulada=false and estado=:estado   and  pagada=false "
                + "  and  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() + " "
                + "  order by codigo desc  ";

        System.out.println("query " + query);
        return session.createSQLQuery(query)
                .addEntity(Nomina.class)
                .setParameter("estado", estado)
                .list();

    }

    public List<DetalleNomina> getLista(int nomina) {

        String query = " SELECT * FROM detalle_nomina det "
                + "  where det.nomina=:nomina ";

        return session.createSQLQuery(query)
                .addEntity(DetalleNomina.class)
                .setParameter("nomina", nomina)
                .list();

    }

    public boolean existeNomina(int tipoNom, int anio, int mes, String periodo) {

        boolean flag = false;

        Nomina nomina = (Nomina) session.createSQLQuery(" SELECT * FROM nomina n "
                + "  where anulada=false and anio=:anio and mes=:mes and periodo like '" + periodo + "' "
                + "  and unidad_de_negocio=:ung limit 1 ")
                .addEntity(Nomina.class)
                .setParameter("anio", anio)
                .setParameter("mes", mes)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .uniqueResult();

        if (!(nomina == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public boolean existeSecuencia(int numero) {

        boolean flag = false;

        Nomina nomina = (Nomina) session.createSQLQuery(" SELECT * FROM nomina n  where anulada=false and "
                + "  n.numero=:numero  and unidad_de_negocio=:ung limit 1 ")
                .addEntity(Nomina.class)
                .setParameter("numero", numero)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .uniqueResult();

        if (!(nomina == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    public Double getUltimoSueldoQuincenal(int codEmp, int tipoNom, int ung) {

        Double monto = 0.00;
        String query = "  select ifnull(dn.sueldo_quincenal,0) "
                + "   from nomina n inner join detalle_nomina dn on dn.nomina=n.codigo "
                + "  where n.tipo_nomina=:tipoNom  and n.anulada=false  and empleado=:codEmp  and  unidad_de_negocio=:ung  and dn.codigo="
                + "  ( select  max(dn.codigo) from nomina n inner join detalle_nomina dn on dn.nomina=n.codigo "
                + "  where n.tipo_nomina=:tipoNom  and n.anulada=false  and empleado=:codEmp  and  unidad_de_negocio=:ung  ) ";

        monto = (Double) session.createSQLQuery(query)
                .setParameter("tipoNom", tipoNom)
                .setParameter("codEmp", codEmp)
                .setParameter("ung", ung)
                .uniqueResult();

        return monto == null ? 0 : monto;

    }

    public Double getUltimaOtraRemuneraciones(int codEmp, int tipoNom, int ung) {

        Double monto = 0.00;
        String query = "  select ifnull(dn.otras_remuneraciones,0) "
                + "   from nomina n inner join detalle_nomina dn on dn.nomina=n.codigo "
                + "  where n.tipo_nomina=:tipoNom  and n.anulada=false  and dn.empleado=:codEmp  and  unidad_de_negocio=:ung  and dn.codigo="
                + "  ( select  max(dn.codigo) from nomina n inner join detalle_nomina dn on dn.nomina=n.codigo "
                + "  where n.tipo_nomina=:tipoNom  and n.anulada=false  and empleado=:codEmp  and  unidad_de_negocio=:ung  ) ";

        monto = (Double) session.createSQLQuery(query)
                .setParameter("tipoNom", tipoNom)
                .setParameter("codEmp", codEmp)
                .setParameter("ung", ung)
                .uniqueResult();

        return monto == null ? 0 : monto;
    }

    public Double getUltimoMontoDependienteAdicionales(int codEmp, int tipoNom, int ung) {

        Double monto = 0.00;
        String query = "  select ifnull(dn.monto_dependiente_adicional,0) "
                + "   from nomina n inner join detalle_nomina dn on dn.nomina=n.codigo "
                + "  where n.tipo_nomina=:tipoNom  and n.anulada=false  and dn.empleado=:codEmp  and  unidad_de_negocio=:ung  and dn.codigo="
                + "  ( select  max(dn.codigo) from nomina n inner join detalle_nomina dn on dn.nomina=n.codigo "
                + "  where n.tipo_nomina=:tipoNom  and n.anulada=false  and empleado=:codEmp  and  unidad_de_negocio=:ung  ) ";

        monto = (Double) session.createSQLQuery(query)
                .setParameter("tipoNom", tipoNom)
                .setParameter("codEmp", codEmp)
                .setParameter("ung", ung)
                .uniqueResult();

        return monto == null ? 0 : monto;
    }

    public Double getUltimoMontoDependiente(int codEmp, int tipoNom, int ung) {

        Double monto = 0.00;
        String query = "  select ifnull(dn.monto_dependiente,0) "
                + "   from nomina n inner join detalle_nomina dn on dn.nomina=n.codigo "
                + "  where n.tipo_nomina=:tipoNom  and n.anulada=false  and dn.empleado=:codEmp  and  unidad_de_negocio=:ung  and dn.codigo="
                + "  ( select  max(dn.codigo) from nomina n inner join detalle_nomina dn on dn.nomina=n.codigo "
                + "  where n.tipo_nomina=:tipoNom  and n.anulada=false  and empleado=:codEmp  and  unidad_de_negocio=:ung  ) ";

        monto = (Double) session.createSQLQuery(query)
                .setParameter("tipoNom", tipoNom)
                .setParameter("codEmp", codEmp)
                .setParameter("ung", ung)
                .uniqueResult();

        return monto == null ? 0 : monto;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return Nomina.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().getUltimoMontoDependienteAdicionales(1, 2, 2));
    }

}
