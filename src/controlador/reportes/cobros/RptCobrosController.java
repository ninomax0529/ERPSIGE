/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.reportes.cobros;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import dto.cobro.ReciboIngresoDTO;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import manejo.ReciboIngreso.ManejoReciboIngreso;
import plantilla.cobros.ExportarCobro;
import reporte.cobros.RptCobrosPorDias;
import reporte.cxc.reciboIngreso.RptCobros;

import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class RptCobrosController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;

    @FXML
    private JFXButton btnVerReporte;
    @FXML
    private JFXCheckBox chPorDias;
    @FXML
    private JFXCheckBox chAvanceCliente;
    @FXML
    private VBox vbRptPorDias;
    @FXML
    private JFXCheckBox chPagoFactura;
    @FXML
    private JFXCheckBox chTodos;
    @FXML
    private JFXCheckBox chDetallado;
    @FXML
    private JFXButton btnXLS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

    }

    @FXML
    private void btnVerReporteActionEvent(ActionEvent event) {

        Date fi, ff;
        fi = ClaseUtil.asDate(dpFechaInicio.getValue());
        ff = ClaseUtil.asDate(dpFechaFinal.getValue());
        String sqlParam = " and tipo_recibo in('PF','AV')  and   ri.unidad_de_negocio="
                + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo(), tipoRecibo = "TODOS";

        if (chPorDias.isSelected()) {

            if (chAvanceCliente.isSelected()) {

                sqlParam += " and tipo_recibo='AV' ";
                tipoRecibo = " Avance de Cliente ";
            } else if (chPagoFactura.isSelected()) {

                sqlParam += " and tipo_recibo='PF' ";
                tipoRecibo = "Pago de Factura";
            } else {
                sqlParam += " and tipo_recibo in('PF','AV')  ";
            }

            sqlParam += " order by ri.fecha ";

            System.out.println("sqlParam : " + sqlParam);
            RptCobrosPorDias.getInstancia().imprimir(fi, ff, sqlParam, tipoRecibo);
        } else if (chDetallado.isSelected()) {
            RptCobros.getInstancia().imprimir(fi, ff);
        }

    }

    @FXML
    private void chPorDiasActionEvent(ActionEvent event) {

        if (chPorDias.isSelected()) {
            chAvanceCliente.setSelected(false);
            vbRptPorDias.setDisable(false);
            chDetallado.setSelected(false);
        } else {
            vbRptPorDias.setDisable(true);
            chAvanceCliente.setSelected(false);
            chTodos.setSelected(false);
            chPagoFactura.setSelected(false);
        }
    }

    @FXML
    private void chPagoFacturaActionEvent(ActionEvent event) {

        if (chPagoFactura.isSelected()) {
            chAvanceCliente.setSelected(false);
            chTodos.setSelected(false);
        } else {
            chPagoFactura.setSelected(false);
        }
    }

    @FXML
    private void chAvanceClienteActionEvent(ActionEvent event) {

        if (chAvanceCliente.isSelected()) {
            chPagoFactura.setSelected(false);
            chTodos.setSelected(false);
        } else {
            chAvanceCliente.setSelected(false);
        }
    }

    @FXML
    private void chTodosActionEvent(ActionEvent event) {

        if (chTodos.isSelected()) {
            chPagoFactura.setSelected(false);
            chAvanceCliente.setSelected(false);
        } else {
            chTodos.setSelected(false);
        }
    }

    @FXML
    private void chDetalladoActionEvent(ActionEvent event) {

        if (chDetallado.isSelected()) {
            chPorDias.setSelected(false);
        } else {
            chDetallado.setSelected(false);
        }
    }

    @FXML
    private void actionEventbtnXLS(ActionEvent event) {

        try {

            Date fi, ff;
            fi = ClaseUtil.asDate(dpFechaInicio.getValue());
            ff = ClaseUtil.asDate(dpFechaFinal.getValue());

            List<ReciboIngresoDTO> lista = ManejoReciboIngreso.getInstancia().getReciboPorFechaDTO(fi, ff);

            ExportarCobro.exportarCobrosXLs(lista,fi,ff);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
