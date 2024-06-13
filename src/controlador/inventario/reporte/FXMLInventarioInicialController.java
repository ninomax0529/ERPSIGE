/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.reporte;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXProgressBar;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import reporte.inventario.RptInventario;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLInventarioInicialController implements Initializable {

    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXProgressBar pgBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFecha.setValue(LocalDate.now());
        pgBar.setVisible(false);
    }

    @FXML
    private void btnAceptarActionEvent(ActionEvent event) {

        try {

            imprimir();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSalirActionEvent(ActionEvent event) {

        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    private void imprimir() throws ParseException {

        try {

            Date fecha = ClaseUtil.asDate(dpFecha.getValue());

            RptInventario.getInstancia().inventarioInicial(fecha);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
