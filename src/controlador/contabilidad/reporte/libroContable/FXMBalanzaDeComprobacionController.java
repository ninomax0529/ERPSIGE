/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.reporte.libroContable;

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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javax.swing.SwingWorker;
import modelo.PeriodoContable;
import reporte.contabilidad.RptLibroContable;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMBalanzaDeComprobacionController implements Initializable {

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

        Date fecha = ClaseUtil.asDate(dpFechaInicio.getValue());

        Date fechaFin = ClaseUtil.asDate(dpFechaFinal.getValue());

//        dpFecha.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaPeriodo));
        RptLibroContable.getInstancia().imprimirBalanzaDeComprobacion(fecha, fechaFin);

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

                Logger.getLogger(FXMLLibroDiarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;

        }

        @Override
        public void done() {

            pgBar.setVisible(false);

        }
    }
}
