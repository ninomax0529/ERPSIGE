/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.transferencia;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import manejo.inventario.transferencia.ManejoTransferencia;
import modelo.DetalleTransferenciaAlmacen;
import modelo.EntradaInventario;
import modelo.TransferenciaAlmacen;
import reporte.inventario.transferencia.RptTransferencia;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class TransferenciaController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnVerEntrada;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TabPane tabPaneEntradaInventario;
    @FXML
    private TableView<TransferenciaAlmacen> tbTransferecnia;
    @FXML
    private TableColumn<TransferenciaAlmacen, Integer> tbcTransferencia;
    @FXML
    private TableColumn<TransferenciaAlmacen, Date> tbcFecha;
    @FXML
    private TableColumn<DetalleTransferenciaAlmacen, String> tbcAlmacenOrigen;
    @FXML
    private TableColumn<DetalleTransferenciaAlmacen, String> tbcalmacenDestino;
    @FXML
    private TableColumn<TransferenciaAlmacen, Date> tbcFechaRegistro;
    @FXML
    private TableColumn<TransferenciaAlmacen, String> tbcAnulada;
    @FXML
    private TableView<DetalleTransferenciaAlmacen> tbDetalleTransferecnia;
    @FXML
    private TableColumn<DetalleTransferenciaAlmacen, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleTransferenciaAlmacen, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleTransferenciaAlmacen, Double> tbcCantidad;
    @FXML
    private TableColumn<DetalleTransferenciaAlmacen, String> tbcUnidad;
    @FXML
    private JFXTextField txtCantidadArticulo;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidad;

    ObservableList<TransferenciaAlmacen> listaTransferencia = FXCollections.observableArrayList();
    ObservableList<DetalleTransferenciaAlmacen> listadetalle = FXCollections.observableArrayList();

    TransferenciaAlmacen transferecnia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaTransferecnia();
        iniciarTablaDetalle();
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
        iniciazarFiltro();
    }

    private void iniciarTablaTransferecnia() {

        // listaEntrada.clear();
        listaTransferencia.addAll(ManejoTransferencia.getInstancia().getTrnasferecnia());
        tbTransferecnia.setItems(listaTransferencia);
        txtCantidad.setText(Integer.toString(listaTransferencia.size()));

        tbcTransferencia.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        tbcAnulada.setCellValueFactory(new PropertyValueFactory<>("anulada"));

        tbcAnulada.setCellValueFactory(
                cellData -> {

                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {

                        if (cellData.getValue().getAnulada()) {
                            property.setValue("Si");
                        } else {
                            property.setValue("No");
                        }

                    }
                    return property;
                });

    }

    public void iniciarTablaDetalle() {

        listadetalle.clear();

        tbDetalleTransferecnia.setItems(listadetalle);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcAlmacenOrigen.setCellValueFactory(new PropertyValueFactory<>("almacenOrigen"));
        tbcalmacenDestino.setCellValueFactory(new PropertyValueFactory<>("almacenOrigen"));

        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        tbcDescripcionArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getDescripcion());
                    }
                    return property;
                });

        tbcCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getNumero().toString());
                    }
                    return property;
                });

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getNombreUnidad());
                    }
                    return property;
                });

        tbcAlmacenOrigen.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getAlmacenOrigen().getNombre());
                    }
                    return property;
                });

        tbcalmacenDestino.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getAlmacenDestino().getNombre());
                    }
                    return property;
                });

    }

    private void iniciazarFiltro() {

        FilteredList<TransferenciaAlmacen> filteredData = new FilteredList<>(tbTransferecnia.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(suplidorFiltrocatalogoFiltro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (suplidorFiltrocatalogoFiltro.getFecha() != null && suplidorFiltrocatalogoFiltro.getFecha().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (suplidorFiltrocatalogoFiltro.getNumero() != null && suplidorFiltrocatalogoFiltro.getNumero().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<TransferenciaAlmacen> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbTransferecnia.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbTransferecnia.setItems(sortedData);
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/transferencia/RegistroTRansferecniaDeAlmacen.fxml"));

        ClaseUtil.crearStageModal(root);

        listaTransferencia.clear();
        listaTransferencia.addAll(ManejoTransferencia.getInstancia().getTrnasferecnia());

    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        try {

            buscar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnVerEntradaActionEvent(ActionEvent event) {

        if (tbTransferecnia.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un registro");
            return;
        }

        int transf = tbTransferecnia.getSelectionModel().getSelectedItem().getCodigo();
        RptTransferencia.getInstancia().imprimir(transf);
    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {

    }

    @FXML
    private void tbEntradaInventarioMouseClicked(MouseEvent event) {

        if (event.getClickCount() == 2) {

            txtComentario.setText(tbTransferecnia.getSelectionModel().getSelectedItem().getObservacion());
            listadetalle.clear();
            listadetalle.addAll(
                    ManejoTransferencia.getInstancia().getDetalleTrnasferecnia(tbTransferecnia.getSelectionModel().getSelectedItem()));
            tabPaneEntradaInventario.getSelectionModel().select(1);
        }

    }

    @FXML
    private void tbDetalleEntradaInventarioMouseCliked(MouseEvent event) {
    }

    private void buscar() {

        Date fechaini, fechafin;
        fechaini = ClaseUtil.asDate(dpFechaInicio.getValue());
        fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());
        listaTransferencia.clear();
        listadetalle.clear();

        listaTransferencia.addAll(ManejoTransferencia.getInstancia().getTrnasferecnia(fechaini, fechafin));
        txtCantidad.setText(Integer.toString(listaTransferencia.size()));
        txtCantidadArticulo.clear();

    }

}
