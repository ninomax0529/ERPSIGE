/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.entrada;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import manejo.inventario.entrada.ManejoInventarioFinal;
import modelo.DetalleInventarioFinal;
import modelo.InventarioFinal;
import reporte.inventario.RptInventario;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class InventarioFinalController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnVerEntrada;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<InventarioFinal> tbInventarioFinal;
    @FXML
    private TableColumn<InventarioFinal, Integer> tbcNumero;
    @FXML
    private TableColumn<InventarioFinal, Date> tbcFecha;
    @FXML
    private TableColumn<InventarioFinal, Date> tbcFechaRegistro;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TableView<DetalleInventarioFinal> tbDetalleInventarioFinal;
    @FXML
    private TableColumn<DetalleInventarioFinal, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleInventarioFinal, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleInventarioFinal, String> tbcUnidad;
    @FXML
    private TableColumn<DetalleInventarioFinal, Double> tbcCantidadPedida;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidadArticulo;

    ObservableList<InventarioFinal> listaInventario = FXCollections.observableArrayList();
    ObservableList<DetalleInventarioFinal> listadetalle = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaEntrada();
        iniciarTablaDetalle();
        iniciazarFiltro();
    }

    private void iniciazarFiltro() {

        FilteredList<InventarioFinal> filteredData = new FilteredList<>(tbInventarioFinal.getItems(), p -> true);
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
                } else if (suplidorFiltrocatalogoFiltro.getCodigo() != null && suplidorFiltrocatalogoFiltro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<InventarioFinal> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbInventarioFinal.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbInventarioFinal.setItems(sortedData);
    }

    private void iniciarTablaEntrada() {

        // listaEntrada.clear();
        listaInventario.addAll(ManejoInventarioFinal.getInstancia().getInventario());
        tbInventarioFinal.setItems(listaInventario);
        txtCantidad.setText(Integer.toString(listaInventario.size()));

        tbcNumero.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));

    }

    public void iniciarTablaDetalle() {

        listadetalle.clear();

        tbDetalleInventarioFinal.setItems(listadetalle);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcCantidadPedida.setCellValueFactory(new PropertyValueFactory<>("existencia"));

        tbcCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });
        tbcDescripcionArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getDescripcion());
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
    private void btnVerEntradaActionEvent(ActionEvent event) {
        
        try {
            
            int  inicial=tbInventarioFinal.getSelectionModel().getSelectedItem().getCodigo();
            RptInventario.getInstancia().inventarioInicial(inicial);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/entrada/RegistroInventariFinal.fxml"));

        ClaseUtil.crearStageModal(root);

        listaInventario.clear();
        listaInventario.addAll(ManejoInventarioFinal.getInstancia().getInventario());
    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {
    }

    @FXML
    private void tbInventarioFinalMouseClick(MouseEvent event) {

        if (!(tbInventarioFinal.getSelectionModel().getSelectedIndex() == -1)) {

            listadetalle.clear();
            txtCantidadArticulo.clear();
            txtCantidad.clear();
            InventarioFinal inventario = tbInventarioFinal.getSelectionModel().getSelectedItem();

//            txtComentario.setText(inventario.getComentario());
            listadetalle.addAll(ManejoInventarioFinal.getInstancia()
                    .getDetalleInventario(inventario));

            txtCantidadArticulo.setText(Integer.toString(listadetalle.size()));
        }

    }

    @FXML
    private void tbDetalleEntradaInventarioMouseCliked(MouseEvent event) {
    }

//      private void valorTotal() {
//
//        Double total = 0.00;
//        for (DetalleInventarioFinal det : listadetalle) {
//
//            total += det.getPrecio() * det.getCantidadRecibida();
//        }
//
//        txtValorTotal.setText(total.toString());
//    }
}
