/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.reportes.cxc;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import controlador.venta.cliente.FXMLBusClienterController;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import modelo.Cliente;
import reporte.cxc.RptEstadoCuentaCliente;
import reporte.ightrack.RptFacturaPendiente;
import reporte.ightrack.cxc.RptResumenCxc;
//import reporte.factura.RptFactura;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ReporteCuentasPorCobrarController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaDesde1;
    @FXML
    private JFXDatePicker dpFecgaHasta1;
    @FXML
    private JFXButton btnVerReporteFactPendiente;

    @FXML
    private JFXCheckBox chEntreFecha;
    @FXML
    private JFXCheckBox chAlCorte;
    @FXML
    private JFXCheckBox chDetallado;
    @FXML
    private JFXCheckBox chResumido;
    @FXML
    private JFXButton btnBuscarCliente;
    Cliente cliente;
    @FXML
    private JFXCheckBox chIncluirPendienteCero;
    @FXML
    private JFXButton btnBuscarClienteEstadoCuenta;
    @FXML
    private JFXButton btnVerReporteEstadoCuenta;
    @FXML
    private JFXCheckBox chIncluirPendienteCero1;
    @FXML
    private JFXDatePicker dpFechaCorte;
    @FXML
    private Label lbCliente;
    @FXML
    private JFXCheckBox chEstadoAlCorte;
    @FXML
    private JFXCheckBox chEntreFecha1;
    @FXML
    private JFXDatePicker dpEstadoFechaDesde;
    @FXML
    private JFXDatePicker dpEstadoFecgaHasta;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFechaCorte.setValue(LocalDate.now());
        dpFechaDesde1.setValue(LocalDate.now());
        dpFecgaHasta1.setValue(LocalDate.now());
        dpEstadoFecgaHasta.setValue(LocalDate.now());
        dpEstadoFechaDesde.setValue(LocalDate.now());

        dpEstadoFechaDesde.setDisable(true);
        dpEstadoFecgaHasta.setDisable(true);

        dpFechaCorte.setDisable(true);

    }

    @FXML
    private void btnVerReporteFactPendienteAcionEvent(ActionEvent event) {

        Date fi = ClaseUtil.asDate(dpFechaDesde1.getValue()),
                ff = ClaseUtil.asDate(dpFecgaHasta1.getValue());

        String sqlParam = "  and  unidad_de_negocio="
                + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        String sqlCliente = " ", sqlPendienteCero = " where r.pendiente>0 ", sqlFecha = null;

        if (!(getCliente() == null)) {
            sqlCliente = " and c.codigo=" + getCliente().getCodigo();

        }

        if (chIncluirPendienteCero.isSelected()) {

            sqlPendienteCero = " where r.pendiente>=0 ";
        }

        if (chDetallado.isSelected()) {

            if (chAlCorte.isSelected()) {

                sqlParam += "  and  fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' ";

                sqlCliente += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

                sqlFecha = " Al : " + new SimpleDateFormat("yyyy-MM-dd").format(ff);
                fi = null;
            } else if (chEntreFecha.isSelected()) {

                sqlParam += "  and  fecha  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and   '"
                        + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  ";

                sqlCliente += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

                sqlFecha = " Desde : " + new SimpleDateFormat("yyyy-MM-dd").format(fi) + " Hasta : " + new SimpleDateFormat("yyyy-MM-dd").format(ff);
            }

            System.out.println(" sqlParam detallado " + sqlParam);

            System.out.println(" sqlCliente detallado " + sqlCliente);

            RptFacturaPendiente.getInstancia().facturaPendiente(fi, ff, sqlParam, sqlCliente, sqlPendienteCero, sqlFecha);

        } else if (chResumido.isSelected()) {

            if (!(getCliente() == null)) {
                sqlParam += " and c.codigo=" + getCliente().getCodigo();

            }

            if (chAlCorte.isSelected()) {

                sqlParam += "  and fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' ";

                sqlCliente += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

                fi = null;

                System.out.println(" sqlParam resumido chAlCorte " + sqlParam);
                System.out.println(" sqlCliente resumido chAlCorte " + sqlCliente);

                sqlFecha = " Al : " + new SimpleDateFormat("yyyy-MM-dd").format(ff);
                RptResumenCxc.getInstancia().resumenCxc(ff, sqlParam, sqlCliente, sqlPendienteCero, sqlFecha);

            } else if (chEntreFecha.isSelected()) {

                sqlParam += " and  fecha between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and '"
                        + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  ";

                sqlCliente += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

                System.out.println(" sqlParam resumido chEntreFecha " + sqlParam);

                System.out.println(" sqlCliente resumido chEntreFecha " + sqlCliente);

                sqlFecha = " Desde : " + new SimpleDateFormat("yyyy-MM-dd").format(fi) + " Hasta : " + new SimpleDateFormat("yyyy-MM-dd").format(ff);
                RptResumenCxc.getInstancia().resumenCxc(ff, sqlParam, sqlCliente, sqlPendienteCero, sqlFecha);
            }
        }

        setCliente(null);
    }

    @FXML
    private void chEntreFechaActionEvent(ActionEvent event) {

        if (chEntreFecha.isSelected()) {

            chAlCorte.setSelected(false);
            dpFechaDesde1.setDisable(false);
            dpFecgaHasta1.setDisable(false);
        } else {
            chEntreFecha.setSelected(false);
            dpFechaDesde1.setDisable(true);
            dpFecgaHasta1.setDisable(false);
        }
    }

    @FXML
    private void chAlCorteActionEvent(ActionEvent event) {

        if (chAlCorte.isSelected()) {

            dpFechaDesde1.setDisable(true);
            chEntreFecha.setSelected(false);
            dpFecgaHasta1.setDisable(false);
        } else {
            chAlCorte.setSelected(false);
            dpFechaDesde1.setDisable(false);
            dpFecgaHasta1.setDisable(false);
        }
    }

    @FXML
    private void chDetalladoActionEvent(ActionEvent event) {

        if (chDetallado.isSelected()) {

            chResumido.setSelected(false);

        }
    }

    @FXML
    private void chResumidoActionevent(ActionEvent event) {

        if (chResumido.isSelected()) {

            chDetallado.setSelected(false);

        }
    }

    @FXML
    private void btnBuscarClienteActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/cliente/FXMLBusCliente.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        utiles.ClaseUtil.getStageModal(root);

        FXMLBusClienterController articuloController = loader.getController();

        if (!(articuloController.getCliente() == null)) {

            setCliente(articuloController.getCliente());

        }

    }

    @FXML
    private void btnBuscarClienteEstadoCuentaActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/cliente/FXMLBusCliente.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        utiles.ClaseUtil.getStageModal(root);

        FXMLBusClienterController articuloController = loader.getController();

        if (!(articuloController.getCliente() == null)) {

            setCliente(articuloController.getCliente());
            lbCliente.setText(getCliente().getNombre());

        }
    }

    @FXML
    private void btnVerReporteEstadoCuentaActionEvent(ActionEvent event) {

        Boolean entreFecha = false;
        Date ff = null, fi = null;
        String paramFecha = "";
        String paramFechaFactura = "", paramDescripcionBusqueda = " ";
        if (getCliente() == null) {

            ClaseUtil.mensaje(" Tiene que seleccionar un Cliente ");
            return;
        }

        if (chEntreFecha1.isSelected()) {
            entreFecha = true;
            fi = ClaseUtil.asDate(dpEstadoFechaDesde.getValue());
            ff = ClaseUtil.asDate(dpEstadoFecgaHasta.getValue());

            paramFecha = " and  fecha between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  ";

            paramFechaFactura = " and  f.fecha between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  ";

            paramDescripcionBusqueda = "Desde :" + new SimpleDateFormat("dd-MM-yyyy").format(fi) + " Hasta :" + new SimpleDateFormat("dd-MM-yyyy").format(ff);

        } else if (chEstadoAlCorte.isSelected()) {
            fi = ClaseUtil.asDate(dpFechaCorte.getValue());
            paramFecha = " and fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'";
            paramFechaFactura = " and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'";
            paramDescripcionBusqueda = "Al :" + new SimpleDateFormat("dd-MM-yyyy").format(fi);
        }

        System.out.println("paramFecha : " + paramFecha);
        System.out.println("paramFechaFactura : " + paramFechaFactura);
        System.out.println("paramDescripcionBusqueda : " + paramDescripcionBusqueda);
        String sqlPendienteCero = " where r.pendiente>0 ";

        if (chIncluirPendienteCero1.isSelected()) {

            sqlPendienteCero = " where r.pendiente>=0 ";
        }

        System.out.println("Estado de cuenta cliente : "+getCliente().getCodigo()+
                " paramFecha : "+paramFecha+
                " paramFechaFactura : "+paramFechaFactura+" sqlPendienteCero ; "+sqlPendienteCero
                  +" paramDescripcionBusqueda : "+paramDescripcionBusqueda
                  +" entreFecha : "+entreFecha
                  +" fi: "+fi
                 +" ff : "+ff );
         RptEstadoCuentaCliente.getInstancia().estadoDeCuentaCliente(getCliente().getCodigo(),
                 fi,
        sqlPendienteCero );
//        RptEstadoCuentaCliente.getInstancia()
//                .estadoDeCuentaCliente(getCliente().getCodigo(), paramFecha, 
//                        paramFechaFactura, sqlPendienteCero,
//                        paramDescripcionBusqueda,entreFecha,fi,ff);

    }

    @FXML
    private void chEstadoAlCorteACtionEvent(ActionEvent event) {
        if (chEstadoAlCorte.isSelected()) {
            dpFechaCorte.setDisable(false);
            chEntreFecha1.setSelected(false);
            dpEstadoFechaDesde.setDisable(true);
            dpEstadoFecgaHasta.setDisable(true);
        } else {
            dpFechaCorte.setDisable(true);
            chEstadoAlCorte.setSelected(false);
        }
    }

    @FXML
    private void chEstadoEntreFechaActionEvent(ActionEvent event) {

        if (chEntreFecha1.isSelected()) {

            chEstadoAlCorte.setSelected(false);
            dpEstadoFechaDesde.setDisable(false);
            dpEstadoFecgaHasta.setDisable(false);
        } else {
            chEntreFecha1.setSelected(false);
            dpEstadoFechaDesde.setDisable(true);
            dpEstadoFecgaHasta.setDisable(true);
        }
    }

}
