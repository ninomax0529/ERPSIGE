/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.devolucion;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.inventario.devolucion.ManejoDevolucionDeInventario;
import modelo.DetalleDevolucionDeInventario;
import modelo.DevolucionDeInventario;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class DevolucionDeInventarioController implements Initializable {

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
    private TableView<DevolucionDeInventario> tbAjusteDeInventario;
    @FXML
    private TableColumn<DevolucionDeInventario, Integer> tbcAjuste;
    @FXML
    private TableColumn<DevolucionDeInventario, String> tbcSuplidor;
    @FXML
    private TableColumn<DevolucionDeInventario, Date> tbcFecha;
    @FXML
    private TableColumn<DevolucionDeInventario, Date> tbcFechaRegistro;
    @FXML
    private TableColumn<DevolucionDeInventario, String> tbcAnulada;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TableView<DetalleDevolucionDeInventario> tbDetalleAjusteDeInventario;
    @FXML
    private TableColumn<DetalleDevolucionDeInventario, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleDevolucionDeInventario, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleDevolucionDeInventario, String> tbcUnidad;
    @FXML
    private TableColumn<DetalleDevolucionDeInventario, Double> tbcCantidadAjustada;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidadArticulo;
    @FXML
    private JFXTextField txtValorTotal;

    ObservableList<DetalleDevolucionDeInventario> listadetalle = FXCollections.observableArrayList();

    ObservableList<DevolucionDeInventario> lista = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabla();
        iniciarTablaDetalle();
    }

    private void iniciarTabla() {

        // listaEntrada.clear();
        lista.addAll(ManejoDevolucionDeInventario.getInstancia().getDevolucionDeInventario());
        tbAjusteDeInventario.setItems(lista);
        txtCantidad.setText(Integer.toString(lista.size()));

        tbcAjuste.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        tbcAnulada.setCellValueFactory(new PropertyValueFactory<>("anulada"));
        tbcAnulada.setCellValueFactory(new PropertyValueFactory<>("tipoDevolucion"));

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

        tbDetalleAjusteDeInventario.setItems(listadetalle);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcionArticulo"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidadEntrada"));
        tbcCantidadAjustada.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

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
                        property.setValue(cellData.getValue().getArticulo().getUnidadEntrada().getDescripcion());
                    }
                    return property;
                });

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/devolucion/RegistroDevolucionDeInventario.fxml"));

        ClaseUtil.crearStageModal(root);

        lista.clear();
        lista.addAll(ManejoDevolucionDeInventario.getInstancia().getDevolucionDeInventario());
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnVerEntradaActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {
    }

    @FXML
    private void tbAjusteDeInventarioMouseClicked(MouseEvent event) {
        if (!(tbAjusteDeInventario.getSelectionModel().getSelectedIndex() == -1)) {

            DevolucionDeInventario dev = tbAjusteDeInventario.getSelectionModel().getSelectedItem();
            listadetalle.clear();
            listadetalle.addAll(ManejoDevolucionDeInventario.getInstancia().getDetalleDevolucion(dev));
            txtComentario.setText(dev.getObservacion());

        }

    }

    @FXML
    private void tbDetalleEntradaInventarioMouseCliked(MouseEvent event) {
    }

}
