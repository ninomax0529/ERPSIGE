/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.reporte.dgii;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.SwingWorker;
import modelo.PeriodoContable;
import reporte.contabilidad.dgii.RptFormatoDgii;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class ReporteFormato606Controller implements Initializable {

    @FXML
    private JFXButton btnAceptar;

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private JFXButton btnSalir;

    ObservableList<PeriodoContable> listaPeriodo = FXCollections.observableArrayList();

    Task task;
    @FXML
    private RadioButton rbRpt606;
    @FXML
    private RadioButton rbRpt607;
    ToggleGroup toggleGroup = new ToggleGroup();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        inicializarTabla();
        dpFechaInicio.setValue(LocalDate.now());
        rbRpt606.setToggleGroup(toggleGroup);
        rbRpt607.setToggleGroup(toggleGroup);
        rbRpt606.setSelected(true);

    }

    @FXML
    private void btnAceptarActionEvent(ActionEvent event) {

        imprimir();
//        task = new Task();
//        task.execute();
    }

    private void imprimir() {

        try {

            Date fechaFin = ClaseUtil.asDate(dpFechaInicio.getValue());
            fechaFin = ClaseUtil.getFechaUltimoDiaDelMes(fechaFin);
            Date fechaIni = ClaseUtil.getFechaPrimerDiasDelMes(fechaFin);

            if (rbRpt607.isSelected()) {
                RptFormatoDgii.getInstancia().reporte607(fechaIni, fechaFin);
            } else if (rbRpt606.isSelected()) {
                RptFormatoDgii.getInstancia().reporte606(fechaIni, fechaFin);
            }

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

//            try {
            imprimir();

//            } catch (ParseException ex) {
//
//                Logger.getLogger(ReporteFormato606Controller.class.getName()).log(Level.SEVERE, null, ex);
//            }
            return null;

        }

        @Override
        public void done() {

        }
    }

}
