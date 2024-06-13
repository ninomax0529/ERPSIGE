/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.venta.cliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import reporte.cliente.RptClientes;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class ReporteClienteController implements Initializable {

    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXCheckBox chEntreFecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
    }

    @FXML
    private void btnAceptarActionEvent(ActionEvent event) {

        try {

//            Date fechaInicio = ClaseUtil.asDate(dpFechaInicio.getValue());
//            Date fechaFinal = ClaseUtil.asDate(dpFechaFinal.getValue());
//            RptFactura.getInstancia().reporteFacturaPendiente(fechaInicio, fechaFinal);
            RptClientes.getInstancia().imprimir();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSalirActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();

    }

}
