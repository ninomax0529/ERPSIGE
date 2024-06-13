/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.ajuste;

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
import manejo.inventario.ajuste.ManejoAjusteDeInventario;
import modelo.AjusteInventario;
import modelo.DetalleAjusteInventario;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class AjusteDeInventarioController implements Initializable {

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
    private TableView<AjusteInventario> tbAjusteDeInventario;
    @FXML
    private TableColumn<AjusteInventario, String> tbcAjuste;
    @FXML
    private TableColumn<AjusteInventario, String> tbcTipoAjuste;
    @FXML
    private TableColumn<AjusteInventario, Date> tbcFecha;
    @FXML
    private TableColumn<AjusteInventario, Date> tbcFechaRegistro;
    @FXML
    private TableColumn<AjusteInventario, String> tbcAnulada;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TableView<DetalleAjusteInventario> tbDetalleAjusteDeInventario;
    @FXML
    private TableColumn<DetalleAjusteInventario, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleAjusteInventario, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleAjusteInventario, Double> tbcCantidadAjustada;
    @FXML
    private TableColumn<DetalleAjusteInventario, String> tbcUnidad;

    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidadArticulo;
    @FXML
    private JFXTextField txtValorTotal;

    ObservableList<DetalleAjusteInventario> listadetalle = FXCollections.observableArrayList();

    ObservableList<AjusteInventario> lista = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaAjuste();
        iniciarTablaDetalle();
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
        btnVerEntrada.setVisible(false);
    }

    private void iniciarTablaAjuste() {

        // listaEntrada.clear();
        lista.addAll(ManejoAjusteDeInventario.getInstancia().getAjusteInventario());
        tbAjusteDeInventario.setItems(lista);
        txtCantidad.setText(Integer.toString(lista.size()));

        tbcAjuste.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        tbcAnulada.setCellValueFactory(new PropertyValueFactory<>("anulada"));
        tbcAnulada.setCellValueFactory(new PropertyValueFactory<>("tipoAjuste"));

        tbcAnulada.setCellValueFactory(
                cellData -> {

                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {

                        if (cellData.getValue().getAnulado()) {
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
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcCantidadAjustada.setCellValueFactory(new PropertyValueFactory<>("cantidadAjustada"));

        tbDetalleAjusteDeInventario.setEditable(true);

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidadSalida().getDescripcion());
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
        tbcDescripcionArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getDescripcion());
                    }
                    return property;
                });

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/ajuste/RegistroAjusteDeInventario.fxml"));

        ClaseUtil.crearStageModal(root);

        lista.clear();
        lista.addAll(ManejoAjusteDeInventario.getInstancia().getAjusteInventario());

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
    private void tbDetalleEntradaInventarioMouseCliked(MouseEvent event) {
    }

    @FXML
    private void tbAjusteDeInventarioMouseClicked(MouseEvent event) {

        if (!(tbAjusteDeInventario.getSelectionModel().getSelectedIndex() == -1)) {

            listadetalle.clear();
            AjusteInventario ajuste = tbAjusteDeInventario.getSelectionModel().getSelectedItem();
            txtComentario.setText(ajuste.getObservacion());
            listadetalle.addAll(ManejoAjusteDeInventario.getInstancia().getDetalleAjusteInventario(ajuste));
            txtCantidadArticulo.setText(Integer.toString(listadetalle.size()));
        }
    }

}
