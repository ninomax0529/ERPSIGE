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
public class FXMLEstadoDeResultadoController implements Initializable {

    @FXML
    private JFXButton btnAceptar;

    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFinal;
    @FXML
    private JFXButton btnSalir;

    @FXML
    private JFXProgressBar pgBar;

    ObservableList<PeriodoContable> listaPeriodo = FXCollections.observableArrayList();

    Task task;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        inicializarTabla();
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
        pgBar.setVisible(false);
    }

    @FXML
    private void btnAceptarActionEvent(ActionEvent event) {

        task = new Task();
        task.execute();
    }

    private void imprimir() throws ParseException {

        try {

            Date fecha = ClaseUtil.asDate(dpFechaInicio.getValue());

            Date fechaFin = ClaseUtil.asDate(dpFechaFinal.getValue());

            RptEstadoFinanciero.getInstancia().imprimirEstadodeResultado(fecha, fechaFin);

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

    class Task extends SwingWorker<Void, Void> {

        @Override
        @SuppressWarnings("SleepWhileInLoop")
        public Void doInBackground() throws IOException {

            pgBar.setVisible(true);

            try {

                imprimir();

            } catch (ParseException ex) {

                Logger.getLogger(FXMLEstadoDeResultadoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;

        }

        @Override
        public void done() {

            pgBar.setVisible(false);

        }
    }

}
