/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.reportes.cxp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import controlador.suplidor.FXMLBusSuplidorController;
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
import modelo.Suplidor;
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
public class ReporteCuentasPorPagarController implements Initializable {

    /**
     * @return the suplidor
     */
    public Suplidor getSuplidor() {
        return suplidor;
    }

    /**
     * @param suplidor the suplidor to set
     */
    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }

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
    private Suplidor suplidor;
    @FXML
    private JFXCheckBox chIncluirPendienteCero;

    @FXML
    private JFXButton btnBuscarSuplidor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFechaDesde1.setValue(LocalDate.now());
        dpFecgaHasta1.setValue(LocalDate.now());

    }

    @FXML
    private void btnVerReporteFactPendienteAcionEvent(ActionEvent event) {

        Date fi = ClaseUtil.asDate(dpFechaDesde1.getValue()),
                ff = ClaseUtil.asDate(dpFecgaHasta1.getValue());

        String sqlParam = "  and  ri.unidad_de_negocio="
                + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        String sqlSuplidor = "", sqlPendienteCero = " where r.pendiente>0 ", sqlFecha = null;

        if (!(getSuplidor()== null)) {
            sqlSuplidor = " and s.codigo=" + getSuplidor().getCodigo();

        }

        if (chIncluirPendienteCero.isSelected()) {

            sqlPendienteCero = " where r.pendiente>=0 ";
        }

        if (chDetallado.isSelected()) {

            if (chAlCorte.isSelected()) {

                sqlParam += "  and  ri.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' ";

                sqlSuplidor += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

                sqlFecha = "Al : " + new SimpleDateFormat("yyyy-MM-dd").format(ff);
                fi = null;
            } else if (chEntreFecha.isSelected()) {

                sqlParam += "  and  ri.fecha  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and   '"
                        + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  ";

                sqlSuplidor += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

                sqlFecha = " Desde : " + new SimpleDateFormat("yyyy-MM-dd").format(fi) + " Hasta : " + new SimpleDateFormat("yyyy-MM-dd").format(ff);
            }

            System.out.println("sqlParam detallado " + sqlParam);

            System.out.println("sqlCliente detallado " + sqlSuplidor);

            RptFacturaPendiente.getInstancia().facturaPendiente(fi, ff, sqlParam, sqlSuplidor, sqlPendienteCero, sqlFecha);

        } else if (chResumido.isSelected()) {

            if (!(getSuplidor() == null)) {
                sqlParam += " and s.codigo=" + getSuplidor().getCodigo();

            }

            if (chAlCorte.isSelected()) {

                sqlParam += "  and fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' ";

                sqlSuplidor += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

                fi = null;

                System.out.println("sqlParam resumido chAlCorte " + sqlParam);
                System.out.println("sqlSuplidor resumido chAlCorte " + sqlSuplidor);

                sqlFecha = "Al : " + new SimpleDateFormat("yyyy-MM-dd").format(ff);
                RptResumenCxc.getInstancia().resumenCxc(ff, sqlParam, sqlSuplidor, sqlPendienteCero, sqlFecha);

            } else if (chEntreFecha.isSelected()) {

                sqlParam += " and  ri.fecha between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and '"
                        + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  ";

                sqlSuplidor += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

                System.out.println("sqlParam resumido chEntreFecha " + sqlParam);

                System.out.println("sqlSuplidor resumido chEntreFecha " + sqlSuplidor);

                sqlFecha = " Desde : " + new SimpleDateFormat("yyyy-MM-dd").format(fi) + " Hasta : " + new SimpleDateFormat("yyyy-MM-dd").format(ff);
                RptResumenCxc.getInstancia().resumenCxc(ff, sqlParam, sqlSuplidor, sqlPendienteCero, sqlFecha);
            }
        }

        setSuplidor(null);
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
    private void btnBuscarSuplidorActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
      

        loader.setLocation(getClass().getResource("/vista/suplidor/FXMLBusSuplidor.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        utiles.ClaseUtil.getStageModal(root);

        FXMLBusSuplidorController articuloController = loader.getController();

        if (!(articuloController.getSuplidor() == null)) {

            setSuplidor(articuloController.getSuplidor());
           

        }

    }

}
