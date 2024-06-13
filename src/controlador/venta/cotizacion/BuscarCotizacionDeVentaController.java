/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.cotizacion;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import manejo.cotizacionDeVenta.ManejoCotizacionDeVenta;
import modelo.CotizacionDeVenta;
import modelo.DetalleCotizacionDeVenta;
import reporte.cotizacion.RpCotizacionDeVentaPinturaTriplea;
import reporte.factura.RptFacturaIghTrack;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class BuscarCotizacionDeVentaController implements Initializable {

    @FXML
    private JFXTextField txtFiltrar;
    @FXML
    private TabPane tabPane;
    @FXML
    private TableView<CotizacionDeVenta> tbCotizacionDeVenta;
    @FXML
    private TableView<DetalleCotizacionDeVenta> tbDetalleCotizacionDeVenta;
    @FXML
    private TableColumn<CotizacionDeVenta, String> tbcFactura;
    @FXML
    private TableColumn<CotizacionDeVenta, String> tbcCliente;
    @FXML
    private TableColumn<CotizacionDeVenta, String> tbcAnulada;
    @FXML
    private TableColumn<CotizacionDeVenta, Date> tbcFecha;
    @FXML
    private TableColumn<CotizacionDeVenta, Double> tbcSubTotal;
    @FXML
    private TableColumn<CotizacionDeVenta, Double> tbcItbis;
    @FXML
    private TableColumn<CotizacionDeVenta, Double> tbcDescuento;
    @FXML
    private TableColumn<CotizacionDeVenta, Double> tbcTotal;

    @FXML
    private TableColumn<DetalleCotizacionDeVenta, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleCotizacionDeVenta, String> tbcArticulo;
    @FXML
    private TableColumn<DetalleCotizacionDeVenta, String> tbcUnidad;
    @FXML
    private TableColumn<DetalleCotizacionDeVenta, String> tbcCantidad;
    @FXML
    private TableColumn<DetalleCotizacionDeVenta, Double> tbcPrecioUnitario;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Label lbCantidad;

    ObservableList<DetalleCotizacionDeVenta> listadetalleFactura = FXCollections.observableArrayList();
    ObservableList<CotizacionDeVenta> listaFactura = FXCollections.observableArrayList();

    CotizacionDeVenta cotizacionDeVenta;

    public CotizacionDeVenta getCotizacionDeVenta() {
        return cotizacionDeVenta;
    }

    public void setCotizacionDeVenta(CotizacionDeVenta cotizacionDeVenta) {
        this.cotizacionDeVenta = cotizacionDeVenta;
    }
    Boolean origen;

    public Boolean getOrigen() {
        return origen;
    }

    public void setOrigen(Boolean origen) {
        this.origen = origen;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTablaDetalle();
        inicializarTablaEncabezado();
        iniciazarFiltro();

    }

    public void inicializarTablaEncabezado() {

        listaFactura.addAll(ManejoCotizacionDeVenta.getInstancia().getListaCotizaciones());
        tbCotizacionDeVenta.setItems(listaFactura);

        tbcFactura.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        tbcItbis.setCellValueFactory(new PropertyValueFactory<>("itbis"));
        tbcDescuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        tbcAnulada.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getAnulada() == true ? "SI" : "NO");
                    }
                    return property;
                });

        tbcCliente.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getCliente().getNombre());
                    }
                    return property;
                });
    }

    public void inicializarTablaDetalle() {

        listadetalleFactura.clear();

        tbDetalleCotizacionDeVenta.setItems(listadetalleFactura);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcArticulo.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

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

    }

    private void iniciazarFiltro() {

        FilteredList<CotizacionDeVenta> filteredData = new FilteredList<>(tbCotizacionDeVenta.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltrar.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(cotizacion -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (cotizacion.getNumero().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (cotizacion.getFecha() != null && cotizacion.getFecha().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (cotizacion.getCliente() != null && cotizacion.getCliente().getNombre().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
//                else if (factura.getCodigo() != null && factura.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
//                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<CotizacionDeVenta> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbCotizacionDeVenta.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbCotizacionDeVenta.setItems(sortedData);
    }


    @FXML
    private void tbFacturaMouseCliked(MouseEvent event) {

        if (event.getClickCount() == 2) {
            setCotizacionDeVenta(tbCotizacionDeVenta.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) tbCotizacionDeVenta.getScene().getWindow();

            stage.close();
        }
    }

    @FXML
    private void tbDetalleFacturaMouseClicked(MouseEvent event) {

    }

    public void inicializarCotizacion() {

        listaFactura.clear();
        if (getOrigen()) {
            listaFactura.addAll(ManejoCotizacionDeVenta.getInstancia().getListaCotizaciones());
        } else {
            listaFactura.addAll(ManejoCotizacionDeVenta.getInstancia().getLista());
        }

    }

}
