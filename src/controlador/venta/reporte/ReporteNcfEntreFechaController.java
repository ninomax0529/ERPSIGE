/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.reporte;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import controlador.venta.cliente.FXMLBusClienterController;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import manejo.factura.ManejoFactura;
import modelo.Cliente;
import reporte.factura.RptFactura;
import reporte.factura.RptNcfFacturado;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class ReporteNcfEntreFechaController implements Initializable {

    @FXML
    private JFXButton btnBuscar;

    /**
     * @return the cliente
     */
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
    }

    @FXML
    private void btnSalirActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        try {

    
            Date fechaInicio = ClaseUtil.asDate(dpFechaInicio.getValue());
            Date fechaFinal = ClaseUtil.asDate(dpFechaFinal.getValue());

            RptNcfFacturado.getInstancia().imprimir(fechaInicio, fechaFinal);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
