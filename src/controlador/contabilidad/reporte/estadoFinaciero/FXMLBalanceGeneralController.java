/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.reporte.estadoFinaciero;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;

import java.io.IOException;
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
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javax.swing.SwingWorker;
import modelo.PeriodoContable;
import reporte.estadoFinanciero.RptEstadoFinanciero;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class FXMLBalanceGeneralController implements Initializable {

    @FXML
    private JFXButton btnAceptar;

    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private JFXButton btnSalir;

    ObservableList<PeriodoContable> listaPeriodo = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        inicializarTabla();
        dpFechaInicio.setValue(LocalDate.now());

    }

    @FXML
    private void btnAceptarActionEvent(ActionEvent event) {

        try {

            imprimir();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void imprimir() {

        try {

            Date fechaFin = ClaseUtil.asDate(dpFechaInicio.getValue());

            RptEstadoFinanciero.getInstancia().balanceGeneral(fechaFin, 3);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnSalirActionEvent(ActionEvent event) {

        // get a handle to the stage
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        // do what you have to do
        stage.close();
    }



}
