/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.puntoVenta;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import manejo.factura.ManejoFactura;
import modelo.Factura;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class BuscarFacturaController implements Initializable {

    @FXML
    private JFXTextField txtFiltrar;
    @FXML
    private TabPane tabPane;
    @FXML
    private TableView<Factura> tbFactura;
    @FXML
    private TableColumn<Factura, Integer> tbcFactura;
    @FXML
    private TableColumn<Factura, String> tbcCliente;

    @FXML
    private TableColumn<Factura, Date> tbcFecha;

    ObservableList<Factura> listaFactura = FXCollections.observableArrayList();
    Factura factura;

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTablaEncabezado();
    }

    public void inicializarTablaEncabezado() {

        listaFactura.addAll(ManejoFactura.getInstancia().getListaFactParaDespachar());
     

        tbcFactura.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        tbcCliente.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getCliente().getNombre());
                    }
                    return property;
                });
        
           tbFactura.setItems(listaFactura);
    }

    @FXML
    private void tbFacturaMouseCliked(MouseEvent event) {

        if (!(tbFactura.getSelectionModel().getSelectedIndex() == -1)) {

            if (event.getClickCount() == 2) {

                setFactura(tbFactura.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) tbFactura.getScene().getWindow();

                stage.close();

            }
        }

    }

}
