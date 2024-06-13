/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.venta.facturacion;

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
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class ReporteDeVentaEntreFechaController implements Initializable {

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnBuscarCliente;
    @FXML
    private Label lbCliente;
    private Cliente cliente;

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

            String sqlParam = "";

            Date fechaInicio = ClaseUtil.asDate(dpFechaInicio.getValue());
            Date fechaFinal = ClaseUtil.asDate(dpFechaFinal.getValue());

            sqlParam = " and  f.fecha  between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio) + "'  and  '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(fechaFinal) + "' ";

            if (!lbCliente.getText().isEmpty()) {

                sqlParam += " and f.cliente=" + getCliente().getCodigo();
            }

            System.out.println("Query " + sqlParam);
            RptFactura.getInstancia().reporteEntreFecha(sqlParam,fechaInicio,fechaFinal);

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
