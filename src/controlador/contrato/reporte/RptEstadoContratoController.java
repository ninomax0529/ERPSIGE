/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.reporte;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import modelo.DetalleReciboIngreso;
import modelo.ReciboIngreso;
import reporte.ightrack.RptEstadoContrato;
import reporte.unidadnegocio.RptContrato;

import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class RptEstadoContratoController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    ObservableList<ReciboIngreso> listaReibo = FXCollections.observableArrayList();
    ObservableList<DetalleReciboIngreso> listadetalle = FXCollections.observableArrayList();
    private TabPane tabPane;
    @FXML
    private JFXButton btnVerReporte;
    @FXML
    private JFXCheckBox chActivo;
    @FXML
    private JFXCheckBox chSuspendidos;
    @FXML
    private JFXCheckBox chTodos;
    @FXML
    private JFXCheckBox chEntreFecha;
    @FXML
    private JFXCheckBox chRptServicioCliente;
    @FXML
    private JFXCheckBox chDetalldo;
    @FXML
    private JFXCheckBox chPorVencer;
    @FXML
    private JFXCheckBox chVencido;
    @FXML
    private JFXCheckBox chalCorte;
    @FXML
    private JFXCheckBox chResumido;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        iniciarTablaRecibo();
//        iniciarTablaDetalle();
//        iniciazarFiltro();

        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

    }

    @FXML
    private void btnVerReporteActionEvent(ActionEvent event) {

        Date fi, ff;
        fi = ClaseUtil.asDate(dpFechaInicio.getValue());
        ff = ClaseUtil.asDate(dpFechaFinal.getValue());
        String sqlParam = "";
        String fechacorteSrt = "";

        if (chEntreFecha.isSelected()) {

            sqlParam += " and dct.fecha_desde between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "' and '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' ";

        } else if (chalCorte.isSelected()) {

            fechacorteSrt = new SimpleDateFormat("yyyy-MM-dd").format(fi);
//            sqlParam += " and dct.fecha_desde between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "' and '"
//                    + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' ";

        }

        if (chPorVencer.isSelected()) {

            sqlParam += " and   DATEDIFF(fecha_ultimo_pago_hasta,Now())+1  between 1 "
                    + "  and  ct.cuandia_antes_de_vencer and ct.frecuencia_de_pago=1 and dct.frecuencia_de_pago=1  ";

        } else if (chVencido.isSelected()) {

            sqlParam += " and  fecha_ultimo_pago_hasta<='" + fechacorteSrt + "' and ct.frecuencia_de_pago=1 and dct.frecuencia_de_pago=1  ";
//            sqlParam += " and  DATEDIFF(('" + fechacorteSrt + "'),fecha_ultimo_pago_hasta)<=0  and ct.frecuencia_de_pago=1 and dct.frecuencia_de_pago=1  ";

        }

        if (chActivo.isSelected()) {

            sqlParam += " and dct.habilitado=true  ";
//            RptEstadoContrato.getInstancia().imprimir(sqlParam);
        } else if (chTodos.isSelected()) {

            sqlParam += "  order by cod_cliente,fecha ";
//            RptEstadoContrato.getInstancia().imprimir(" order by cod_cliente,fecha ");
        } else if (chSuspendidos.isSelected()) {

            sqlParam += " and dct.habilitado=false  ";

        }

        System.out.println("sql compuesto  : " + sqlParam);

        if (chRptServicioCliente.isSelected()) {
            System.out.println("sql compuesto  : servicio cliente ");
            RptEstadoContrato.getInstancia().servicioCliente();

        } else {

            System.out.println(" no servicio  : servicio cliente ");
            if (chDetalldo.isSelected() && chSuspendidos.isSelected()) {

                System.out.println(" detallado y suspendido  ");
                RptContrato.getInstancia().verReporte(fi, ff);

            } else {

                if (chResumido.isSelected()) {
                    System.out.println("sql compuesto  : " + sqlParam + "  chResumido.isSelected() ");
                    RptEstadoContrato.getInstancia().imprimirResumen(sqlParam);
                } else {
                    System.out.println("sql compuesto  :detalledao  ");
                    RptEstadoContrato.getInstancia().imprimir(sqlParam);
                }

            }

        }

    }

    @FXML
    private void chActivoActionEvent(ActionEvent event) {

        if (chActivo.isSelected()) {
            chSuspendidos.setSelected(false);
            chTodos.setSelected(false);
            chRptServicioCliente.setSelected(false);
        }
    }

    @FXML
    private void chSuspendidosActionEvent(ActionEvent event) {

        if (chSuspendidos.isSelected()) {
            chActivo.setSelected(false);
            chTodos.setSelected(false);
            chRptServicioCliente.setSelected(false);
        }
    }

    @FXML
    private void chTodosActionEvent(ActionEvent event) {

        if (chTodos.isSelected()) {
            chActivo.setSelected(false);
            chSuspendidos.setSelected(false);
            chRptServicioCliente.setSelected(false);
        }
    }

    @FXML
    private void chEntreFechaActionEvent(ActionEvent event) {

        if (chEntreFecha.isSelected()) {

            dpFechaInicio.setDisable(false);
            dpFechaFinal.setDisable(false);
            chRptServicioCliente.setSelected(false);
            chalCorte.setDisable(true);
        } else {
            dpFechaInicio.setDisable(true);
            dpFechaFinal.setDisable(true);
            chRptServicioCliente.setSelected(false);
            chalCorte.setDisable(false);
        }
    }

    @FXML
    private void chRptServicioClienteActionEvent(ActionEvent event) {
    }

    @FXML
    private void chDetalldoActtionevent(ActionEvent event) {

        if (chDetalldo.isSelected()) {

            chResumido.setSelected(false);
        }
    }

    @FXML
    private void chPorVencerActionEvent(ActionEvent event) {

        if (chPorVencer.isSelected()) {

            chVencido.setSelected(false);

        }
    }

    @FXML
    private void chVencidoActionEvent(ActionEvent event) {

        if (chVencido.isSelected()) {

            chPorVencer.setSelected(false);

        }
    }

    @FXML
    private void chalCorteActionEvent(ActionEvent event) {

        if (chalCorte.isSelected()) {

            dpFechaInicio.setDisable(true);
            dpFechaFinal.setDisable(false);
            chEntreFecha.setDisable(true);
        } else {
            dpFechaInicio.setDisable(true);
            dpFechaFinal.setDisable(true);
            chEntreFecha.setDisable(false);
        }

    }

    @FXML
    private void chResumidoActionEvent(ActionEvent event) {

        if (chDetalldo.isSelected()) {

            chDetalldo.setSelected(false);
        }
    }

}
