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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.inventario.entrada.ManejoEntradaInventario;
import modelo.DetalleEntradaInventario;
import modelo.EntradaInventario;
import reporte.inventario.RptEntradaInventario;

import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class EntradaInventarioController implements Initializable {

    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidadArticulo;
    @FXML
    private JFXTextField txtValorTotal;
    @FXML
    private TableView<DetalleEntradaInventario> tbDetalleEntradaInventario;
    @FXML
    private TableColumn<DetalleEntradaInventario, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleEntradaInventario, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleEntradaInventario, String> tbcUnidad;
    @FXML
    private TableColumn<DetalleEntradaInventario, Double> tbcPrecioCompra;
    @FXML
    private TableColumn<DetalleEntradaInventario, Double> tbcCantidadPedida;
    @FXML
    private TableColumn<DetalleEntradaInventario, Double> tbcCantidadRecibida;
    @FXML
    private TableColumn<DetalleEntradaInventario, Double> tbcValor;
    @FXML
    private TableColumn<DetalleEntradaInventario, String> tbcOrden;

    @FXML
    private TableView<EntradaInventario> tbEntradaInventario;
    @FXML
    private TableColumn<EntradaInventario, String> tbcAnulada;
    @FXML
    private TableColumn<EntradaInventario, Integer> tbcEntrada;
    @FXML
    private TableColumn<EntradaInventario, Date> tbcFecha;
    @FXML
    private TableColumn<EntradaInventario, Date> tbcFechaRegistro;

    ObservableList<EntradaInventario> listaEntrada = FXCollections.observableArrayList();
    ObservableList<DetalleEntradaInventario> listadetalle = FXCollections.observableArrayList();
    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnVerEntrada;

    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private TabPane tabPaneEntradaInventario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTablaEntrada();
        iniciarTablaDetalle();
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
        iniciazarFiltro();

    }

    private void iniciazarFiltro() {

        FilteredList<EntradaInventario> filteredData = new FilteredList<>(tbEntradaInventario.getItems(), p -> true);
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
        SortedList<EntradaInventario> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbEntradaInventario.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbEntradaInventario.setItems(sortedData);
    }

    private void iniciarTablaEntrada() {

        // listaEntrada.clear();
        listaEntrada.addAll(ManejoEntradaInventario.getInstancia().getEntradaInventario());
        tbEntradaInventario.setItems(listaEntrada);
        txtCantidad.setText(Integer.toString(listaEntrada.size()));

        tbcEntrada.setCellValueFactory(new PropertyValueFactory<>("codigo"));
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

        tbDetalleEntradaInventario.setItems(listadetalle);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcCantidadPedida.setCellValueFactory(new PropertyValueFactory<>("cantidadPedida"));
        tbcCantidadRecibida.setCellValueFactory(new PropertyValueFactory<>("cantidadRecibida"));
        tbcPrecioCompra.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tbcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tbcOrden.setCellValueFactory(new PropertyValueFactory<>("numeroOrden"));

        tbDetalleEntradaInventario.setEditable(true);

        tbcOrden.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getNumeroOrden());
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

        buscar();

    }

    @FXML
    private void tbEntradaInventarioMouseClicked(MouseEvent event) {

        if (!(tbEntradaInventario.getSelectionModel().getSelectedIndex() == -1)) {

            listadetalle.clear();
            txtCantidadArticulo.clear();
            txtValorTotal.clear();
            EntradaInventario entrada = tbEntradaInventario.getSelectionModel().getSelectedItem();

            txtComentario.setText(entrada.getComentario());

            listadetalle.addAll(ManejoEntradaInventario
                    .getInstancia()
                    .getDetalleInventario(entrada));

            valorTotal();
            txtCantidadArticulo.setText(Integer.toString(listadetalle.size()));

            if (event.getClickCount() == 2) {

                tabPaneEntradaInventario.getSelectionModel().select(1);
            }
        }
    }

    private void valorTotal() {

        Double total = 0.00;
        for (DetalleEntradaInventario detalleEntradaInventario : listadetalle) {

            total += detalleEntradaInventario.getPrecio() * detalleEntradaInventario.getCantidadRecibida();
        }

        txtValorTotal.setText(total.toString());
    }

    @FXML
    private void btnVerEntradaActionEvent(ActionEvent event) {

        try {

            if (!(tbEntradaInventario.getSelectionModel().getSelectedIndex() == -1)) {

                int entrada = tbEntradaInventario.getSelectionModel()
                        .getSelectedItem().getCodigo();

//                if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {
//
//                    RptEntradaInventariOPinturaTriplea.getInstancia().imprimir(entrada);
//                } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {
                    RptEntradaInventario.getInstancia().imprimir(entrada);
//                }

            } else {

                ClaseUtil.mensaje("Tiene que seleccionar una Entrada");

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void tbDetalleEntradaInventarioMouseCliked(MouseEvent event) {
//
//        try {
//
//            if (!(tbDetalleEntradaInventario.getSelectionModel().getSelectedIndex() == -1)) {
//
//                btnVerOrden.setVisible(true);
//
//            } else {
//
//                btnVerOrden.setVisible(false);
//            }
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/entrada/RegistroEntradaInventario.fxml"));

        ClaseUtil.crearStageModal(root);

        listaEntrada.clear();
        listaEntrada.addAll(ManejoEntradaInventario.getInstancia().getEntradaInventario());

    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {

        try {

            if (!(tbEntradaInventario.getSelectionModel().getSelectedIndex() == -1)) {

                EntradaInventario ent = tbEntradaInventario.getSelectionModel().getSelectedItem();

                if (ent.getAnulada() == true) {

                    util.ClaseUtil.mensaje("ESTA ENTRADA YA ESTA ANULADA");
                    return;
                }

                Optional<ButtonType> ok = util.ClaseUtil.confirmarMensaje("SEGURO QUE QUIERE ANULAR LA ENTRADA NUMERO " + ent.getCodigo());

                if (ok.get() == ButtonType.OK) {

                    ent.setAnulada(true);
                    ent.setFechaAnulada(new Date());
                    ent.setAnuladaPor(1);
                    EntradaInventario entradaInventarioDB = ManejoEntradaInventario.getInstancia().actualizar(ent);

                    if (entradaInventarioDB == null) {

                        ClaseUtil.mensaje("Hubo un problema anulando la entrada");
                        return;
                    }

                    List<DetalleEntradaInventario> lista = ManejoEntradaInventario.getInstancia().getDetalleInventario(entradaInventarioDB);

                    ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorAnular(lista);

                    listaEntrada.clear();
                    listadetalle.clear();
                    listaEntrada.addAll(ManejoEntradaInventario.getInstancia().getEntradaInventario());

                }

            } else {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN REGISTRO");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void buscar() {

        Date fechaini, fechafin;
        fechaini = ClaseUtil.asDate(dpFechaInicio.getValue());
        fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());
        listaEntrada.clear();
        listadetalle.clear();

        listaEntrada.addAll(ManejoEntradaInventario.getInstancia().getEntradaInventario(fechaini, fechafin));
        txtCantidad.setText(Integer.toString(listaEntrada.size()));
        txtCantidadArticulo.clear();
        txtValorTotal.clear();
    }

}
