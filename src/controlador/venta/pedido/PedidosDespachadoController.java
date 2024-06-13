/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.pedido;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.DetallePedido;
import modelo.Pedido;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class PedidosDespachadoController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaDesde;
    @FXML
    private JFXDatePicker dpFecgaHasta;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnReimprimir;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private TableView<Pedido> tbPedidoDespachado;
    @FXML
    private TableColumn<Pedido, Integer> tbcNumPedidoDespachado;
    @FXML
    private TableColumn<Pedido, Date> tbcFechaPedidoDespachado;
    @FXML
    private TableColumn<Pedido, Date> tbcHoraPedidoDespachado;
    @FXML
    private TableColumn<Pedido, String> tbcClientePedidoDespachado;
    @FXML
    private TableColumn<Pedido, String> tbcNumCallePedidoDespachado;
    @FXML
    private TableColumn<Pedido, String> tbcNumCasaPedidoDespachado;
    @FXML
    private TableColumn<Pedido, Double> tbcTotalPedidoDespachado;

    @FXML
    private TableView<DetallePedido> tbDetallePedidoDespachado;
    @FXML
    private TableColumn<DetallePedido, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetallePedido, String> tbcArticulo;
    @FXML
    private TableColumn<DetallePedido, String> tbcUnidad;
    @FXML
    private TableColumn<DetallePedido, Double> tbcCantidad;
    @FXML
    private TableColumn<DetallePedido, Double> tbcPrecioUnitario;
    @FXML
    private TableColumn<DetallePedido, Double> tbcSubTotal;

    ObservableList<DetallePedido> listadetallePedido = FXCollections.observableArrayList();

    ObservableList<Pedido> listaPedido = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTablaEncabezado();
        inicializarTablaDetalle();
    }

    public void inicializarTablaEncabezado() {

        listaPedido.clear();

        tbPedidoDespachado.setItems(listaPedido);

        tbcNumPedidoDespachado.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNumCallePedidoDespachado.setCellValueFactory(new PropertyValueFactory<>("calle"));
        tbcNumCasaPedidoDespachado.setCellValueFactory(new PropertyValueFactory<>("casa"));
        tbcClientePedidoDespachado.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        tbcTotalPedidoDespachado.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcFechaPedidoDespachado.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcHoraPedidoDespachado.setCellValueFactory(new PropertyValueFactory<>("horaCreacion"));

    }

    public void inicializarTablaDetalle() {

        listadetallePedido.clear();

        tbDetallePedidoDespachado.setItems(listadetallePedido);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcArticulo.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidadSalida"));
        tbcSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        tbcPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tbcArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getNombreArticulo());
                    }
                    return property;
                });

        tbcCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnReimprimirActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnAnularActionEvent(ActionEvent event) {
    }

    @FXML
    private void tbFacturaMouseCliked(MouseEvent event) {
    }

}
