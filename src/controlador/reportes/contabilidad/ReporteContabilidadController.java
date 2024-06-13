/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.reportes.contabilidad;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.Factura;
import reporte.contabilidad.RptLibroContable;
import reporte.estadoFinanciero.RptEstadoDeResultadoComercial;
import reporte.estadoFinanciero.RptEstadoDeSituacion;
import reporte.estadoFinanciero.RptEstadoFlujoDeEfectivo;
//import reporte.factura.RptFactura;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ReporteContabilidadController implements Initializable {

    ObservableList<DetalleFactura> listadetalleFactura = FXCollections.observableArrayList();
    ObservableList<Factura> listaFactura = FXCollections.observableArrayList();

    ObservableList<DetalleFactura> listadetalleFacturaPendiente = FXCollections.observableArrayList();
    ObservableList<Factura> listaFacturaPendiente = FXCollections.observableArrayList();

    @FXML
    private JFXCheckBox chEntreFecha;
    @FXML
    private JFXCheckBox chAlCorte;

    Cliente cliente;
    @FXML
    private VBox vbEntreFecha;
    @FXML
    private VBox vbEntreFecha1;
    @FXML
    private JFXRadioButton rbLibroDiario;
    @FXML
    private JFXRadioButton rbLibroMayor;
    @FXML
    private JFXRadioButton rbBalanzaDeComprobacion;
    @FXML
    private JFXDatePicker dpFechaDesdeLc;
    @FXML
    private JFXDatePicker dpFechaHastaLc;
    @FXML
    private JFXCheckBox chEntreFecha1;
    @FXML
    private JFXCheckBox chAlCorte1;
    @FXML
    private JFXButton btnVerLibroContable;
    @FXML
    private JFXRadioButton rbEstadoDeSituacion;
    @FXML
    private JFXRadioButton rbEstadoDeResultado;
    @FXML
    private JFXRadioButton rbEstadoFlujoDeEfectivo;
    @FXML
    private JFXDatePicker dpFechaDesdeEf;
    @FXML
    private JFXDatePicker dpFechaHastaEf;
    @FXML
    private JFXButton btnVerEstadoFinanciero;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    ToggleGroup toggleGroupEf = new ToggleGroup();
    ToggleGroup toggleGroupLc = new ToggleGroup();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFechaDesdeEf.setValue(LocalDate.now());
        dpFechaHastaEf.setValue(LocalDate.now());
        rbEstadoDeSituacion.setToggleGroup(toggleGroupEf);
        rbEstadoDeResultado.setToggleGroup(toggleGroupEf);
        rbEstadoFlujoDeEfectivo.setToggleGroup(toggleGroupEf);
        /* Libro contable  */
        rbBalanzaDeComprobacion.setToggleGroup(toggleGroupLc);
        rbLibroMayor.setToggleGroup(toggleGroupLc);
        rbLibroDiario.setToggleGroup(toggleGroupLc);

    }

    @FXML
    private void chEntreFechaActionEvent(ActionEvent event) {

//        if (chEntreFecha.isSelected()) {
//
//            chAlCorte.setSelected(false);
//            dpFechaDesde.setDisable(false);
//            dpFechaHasta.setDisable(false);
//        } else {
//            chEntreFecha.setSelected(false);
//            dpFechaDesde.setDisable(true);
//            dpFechaHasta.setDisable(false);
//        }
    }

    @FXML
    private void chAlCorteActionEvent(ActionEvent event) {

//        if (chAlCorte.isSelected()) {
//
//            dpFechaDesde.setDisable(true);
//            chEntreFecha.setSelected(false);
//            dpFechaHasta.setDisable(false);
//        } else {
//            chAlCorte.setSelected(false);
//            dpFechaDesde.setDisable(false);
//            dpFechaHasta.setDisable(false);
//        }
    }


    @FXML
    private void btnVerLibroContableActionEvent(ActionEvent event) {

        Date fi = ClaseUtil.asDate(dpFechaDesdeLc.getValue()),
                ff = ClaseUtil.asDate(dpFechaHastaLc.getValue());

        try {

            if (rbLibroDiario.isSelected()) {

                RptLibroContable.getInstancia().imprimirLibroDiario(fi, ff);

            } else if (rbLibroMayor.isSelected()) {

                RptLibroContable.getInstancia().imprimirLibroMayor(fi, ff);

            } else if (rbBalanzaDeComprobacion.isSelected()) {

                RptLibroContable.getInstancia().imprimirBalanzaDeComprobacion(fi, ff);
            }

        } catch (ParseException ex) {

            Logger.getLogger(ReporteContabilidadController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnVerEstadoFinancieroActionEvent(ActionEvent event) {

        Date fi = ClaseUtil.asDate(dpFechaDesdeEf.getValue()),
                ff = ClaseUtil.asDate(dpFechaHastaEf.getValue());

        try {

            if (rbEstadoDeSituacion.isSelected()) {

                RptEstadoDeSituacion.getInstancia().estadoDesituacion(ff);

            } else if (rbEstadoDeResultado.isSelected()) {

                RptEstadoDeResultadoComercial.getInstancia().estadoDeresultado(fi, ff);

            } else if (rbEstadoFlujoDeEfectivo.isSelected()) {

                RptEstadoFlujoDeEfectivo.getInstancia().estadoFlujoDeEfectivo(fi, ff);
            }

        } catch (ParseException ex) {

            Logger.getLogger(ReporteContabilidadController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
