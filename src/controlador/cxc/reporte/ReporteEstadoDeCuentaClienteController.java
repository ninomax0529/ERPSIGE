/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cxc.reporte;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import controlador.venta.cliente.FXMLBusClienterController;
import java.io.IOException;
import java.net.URL;
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
import reporte.cxc.RptEstadoCuentaCliente;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class ReporteEstadoDeCuentaClienteController implements Initializable {

    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXDatePicker dpFechaInicio;
 
    private Label lbCliente;
    Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpFechaInicio.setValue(LocalDate.now());
      
    }

    @FXML
    private void btnAceptarActionEvent(ActionEvent event) {

        try {

            Date fechaCorte = ClaseUtil.asDate(dpFechaInicio.getValue());
        
            RptEstadoCuentaCliente.getInstancia().estadoCuentaCliente(fechaCorte);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSalirActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();

    }

    private void btnBuscarClienteActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/cliente/FXMLBusCliente.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        utiles.ClaseUtil.getStageModal(root);

        FXMLBusClienterController articuloController = loader.getController();

        if (!(articuloController.getCliente() == null)) {

            setCliente(articuloController.getCliente());
            lbCliente.setText(getCliente().getNombre());
            getCliente().setMontoDisponible(ManejoFactura.getInstancia().getMontoDisponible(getCliente()));

        }

    }

}
