/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.reporte;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import reporte.inventario.RptEntradaInventario;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class ReporteEntradaArticuloEntreFechaController implements Initializable {

    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private Label lbArticulo;
      private int codigoArticulo;

    public Label getLbArticulo() {
        return lbArticulo;
    }

    public void setLbArticulo(Label lbArticulo) {
        this.lbArticulo = lbArticulo;
    }

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
            Date fechaInicio = ClaseUtil.asDate(dpFechaInicio.getValue());
            Date fechaFinal = ClaseUtil.asDate(dpFechaFinal.getValue());
            RptEntradaInventario.getInstancia().entradaInventario(fechaInicio, fechaFinal,codigoArticulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSalirActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void lbArticuloActionevent(MouseEvent event) {
    }

    public int getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(int codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

}
