/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.venta.facturacion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import reporte.factura.RptFactura;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class ReporteMensualoDeVentaController implements Initializable {

    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnSalir;
  
    @FXML
    private JFXDatePicker dpFechaFinal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        dpFechaFinal.setValue(LocalDate.now());
    }

    @FXML
    private void btnAceptarActionEvent(ActionEvent event) {

        try {
        
            Date fechaFinal = ClaseUtil.asDate(dpFechaFinal.getValue());
            RptFactura.getInstancia().reporteMensualVenta(fechaFinal);
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
