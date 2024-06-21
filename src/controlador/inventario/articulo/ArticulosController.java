/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controlador.inventario.ModuloInventarioController;
import controlador.inventario.articulo.movimiento.EntradaArticuloController;
import controlador.inventario.articulo.movimiento.SalidaArticuloController;
import controlador.inventario.reporte.ReporteEntradaArticuloEntreFechaController;
import controlador.produccion.lote.BusacrLoteDeProduccionController;
import dto.articulo.ArticuloDTO;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import manejo.articulo.ManejoArticulo;
import manejo.articulo.ManejoExistenciaArticulo;
import modelo.Articulo;
import modelo.DetalleListaDePrecio;
import modelo.ExistenciaArticulo;
import modelo.RegistroLote;
import reporte.inventario.RptImprimirEtiqueta;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ArticulosController implements Initializable {

    @FXML
    private JFXButton btnVerEtiqueta;
    @FXML
    private JFXButton btnImprimir;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private TableView<Articulo> tbArticulo;
    @FXML
    private TableColumn<Articulo, String> tbcCodigo;
    @FXML
    private TableColumn<Articulo, String> tbcNombre;
    @FXML
    private TableColumn<Articulo, String> tbcCategoria;
    @FXML
    private TableColumn<Articulo, String> tbcSubCategoria;
    @FXML
    private TableView<ArticuloDTO> tbListaDePrecio;
    @FXML
    private TableColumn<ArticuloDTO, String> tbcListaPrecio;
    @FXML
    private TableColumn<ArticuloDTO, Double> tbcCostoUnitario;
    @FXML
    private TableColumn<ArticuloDTO, Double> tbcPrecioVenta;
    @FXML
    private TableColumn<ArticuloDTO, String> tbcUnidadVenta;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Label lbCantidad;

    private Articulo articulo;
    @FXML
    private JFXButton btnExportar;
    @FXML
    private JFXButton btnVerEntrada;
    @FXML
    private BorderPane bpPrincipal;
    @FXML
    private JFXButton btnEntrada;
    @FXML
    private JFXButton btnAjuste;
    @FXML
    private JFXButton btnSalida;
    @FXML
    private VBox vbMovimiento;
    @FXML
    private TabPane tabPane;
    @FXML
    private Label lbArticuloAlmacen;
    @FXML
    private Label lbArticuloListaPrecio;
    @FXML
    private Label lbCantidadArticulo;
    @FXML
    private TableView<ExistenciaArticulo> tbExistenciaArticulo;
    @FXML
    private TableColumn<ArticuloDTO, String> tbcAlmacen;
    @FXML
    private TableColumn<ArticuloDTO, String> tbcUnidad;
    @FXML
    private TableColumn<ArticuloDTO, Double> tbcExistencia;

    ObservableList<Articulo> listaArticulo = FXCollections.observableArrayList();

    ObservableList<ArticuloDTO> listaDetalleArticulo = FXCollections.observableArrayList();
    ObservableList<ExistenciaArticulo> listaExistenciaArticulo = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnSeleccionarLote;
    @FXML
    private Label lbLote;
    RegistroLote registroLote;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcFechaActualizacion;
    @FXML
    private TableColumn<ArticuloDTO, String> tbcHabilitado;

    public RegistroLote getRegistroLote() {
        return registroLote;
    }

    public void setRegistroLote(RegistroLote registroLote) {
        this.registroLote = registroLote;
    }

    /**
     * @return the articulo
     */
    public Articulo getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaArticulo();
        iniciarTablaDetalleArticulo();
        iniciarTablaExistenciaArticulo();
        iniciazarFiltro();
        txtFiltro.requestFocus();

    }

    private void iniciazarFiltro() {

        FilteredList<Articulo> filteredData = new FilteredList<>(tbArticulo.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getNombre() != null && filtro.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getNumero() != null && filtro.getNumero().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filtro.getSubCategoria() != null && filtro.getSubCategoria().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filtro.getCategoria() != null && filtro.getCategoria().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filtro.getDescripcion() != null && filtro.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filtro.getCalidadPintura() != null && filtro.getCalidadPintura().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Articulo> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbArticulo.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbArticulo.setItems(sortedData);
    }

    public void iniciarTablaArticulo() {

        try {

            listaArticulo.clear();

            tbArticulo.setItems(listaArticulo);
            listaArticulo.addAll(ManejoArticulo.getInstancia().getListaArticulos());

            tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tbcNombre.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tbcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
            tbcSubCategoria.setCellValueFactory(new PropertyValueFactory<>("subCategoria"));

//            tbcNombre.setCellValueFactory(
//                    cellData -> {
//                        SimpleStringProperty property = new SimpleStringProperty();
//                        if (cellData.getValue() != null) {
//                            property.setValue(cellData.getValue().getCalidadPintura() == null ? cellData.getValue().getNombre() + " "
//                                    : cellData.getValue().getNombre() + " " + cellData.getValue().getCalidadPintura().getNombre());
//                        }
//                        return property;
//                    });
//            
            tbcNombre.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue().getDescripcion());
                        }
                        return property;
                    });

            tbcCategoria.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue().getCategoria().getNombre());
                        }
                        return property;
                    });

            tbcSubCategoria.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue().getSubCategoria().getNombre());
                        }
                        return property;
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iniciarTablaDetalleArticulo() {

        listaDetalleArticulo.clear();

        tbListaDePrecio.setItems(listaDetalleArticulo);

        tbcCostoUnitario.setCellValueFactory(new PropertyValueFactory<>("costoUnitario"));
        tbcListaPrecio.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcUnidadVenta.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        tbcExistencia.setCellValueFactory(new PropertyValueFactory<>("existencia"));
        tbcHabilitado.setCellValueFactory(new PropertyValueFactory<>("habilitado"));

        tbcHabilitado.setCellValueFactory(
                cellData -> {

                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {

                        if (cellData.getValue().getHabilitado()) {
                            property.setValue("Si");
                        } else {
                            property.setValue("No");
                        }

                    }
                    return property;
                });

    }

    public void iniciarTablaExistenciaArticulo() {

        listaExistenciaArticulo.clear();

        tbExistenciaArticulo.setItems(listaExistenciaArticulo);

        tbcAlmacen.setCellValueFactory(new PropertyValueFactory<>("nombreAlmacen"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("nombreUnidadBase"));
        tbcExistencia.setCellValueFactory(new PropertyValueFactory<>("existencia"));
        tbcFechaActualizacion.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        tbcFechaActualizacion.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getFecha() != null) {
                        property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFecha()));
                    }
                    return property;
                });
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

//        Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/RegistroArticulo_1.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/RegistroArticulos.fxml"));

        ClaseUtil.getStageModal(root);

        listaArticulo.clear();
        listaArticulo.addAll(ManejoArticulo.getInstancia().getListaArticulos());

    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbArticulo.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un Articulo");

        } else {

            setArticulo(tbArticulo.getSelectionModel().getSelectedItem());

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/inventario/articulo/RegistroArticulos.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroArticulosController articuloControllller = loader.getController();

            articuloControllller.setEditar(true);
            articuloControllller.setArticulo(getArticulo());

            articuloControllller.llenarCampo();

            ClaseUtil.getStageModal(root);

            listaArticulo.clear();
            listaArticulo.addAll(ManejoArticulo.getInstancia().getListaArticulos());

        }

    }

    @FXML
    private void btnVerEtiquetaActionEvent(ActionEvent event) {

        try {

            if (tbArticulo.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN ARTICULO");
                return;
            }

            articulo = tbArticulo.getSelectionModel().getSelectedItem();

            if (lbLote.getText().isEmpty() && articulo.getUnidadDeNegocio().getCodigo() == 1) {

                ClaseUtil.mensaje(" Tiene que seleccionar un lote ");
                return;
            }

            if (articulo.getUnidadDeNegocio().getCodigo() == 1) {

                System.out.println(" getRegistroLote().getCapacidad() " + getRegistroLote().getUnidad() + " "
                        + getRegistroLote().getCapacidad() + " " + getRegistroLote().getUnidad().getDescripcion());

                RptImprimirEtiqueta.getInstancia().verEtiqueta(
                        articulo.getCodigo().toString(),
                        Integer.parseInt(lbLote.getText().trim()),
                        getRegistroLote().getUnidad().getDescripcion());
            } else {

                RptImprimirEtiqueta.getInstancia().verEtiquetaGenerica(
                        articulo.getCodigo().toString(), articulo.getUnidadDeNegocio().getCodigo());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnImprimirActionEvent(ActionEvent event) {

        try {

            if (lbLote.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que seleccionar un lote");
                return;
            }

            if (!(tbArticulo.getSelectionModel().getSelectedIndex() == -1)) {

                articulo = tbArticulo.getSelectionModel().getSelectedItem();
                RptImprimirEtiqueta.getInstancia().imprimirLabe(articulo.getCodigo().toString(), Integer.parseInt(lbLote.getText().trim()));

            } else {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN ARTICULO");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) {

        try {

            util.ClaseUtil.exportarDAtos(tbArticulo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnVerEntradaActionEvent(ActionEvent event) {

        try {

            if (tbArticulo.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar un Articulo");
                return;
            }

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/inventario/reporte/ReporteEntradaArticuloEntreFecha.fxml"));
            loader.load();

            setArticulo(tbArticulo.getSelectionModel().getSelectedItem());
            Parent root = loader.getRoot();

            ReporteEntradaArticuloEntreFechaController registroArticuloController = loader.getController();

            registroArticuloController.getLbArticulo().setText(getArticulo().getDescripcion());
            registroArticuloController.setCodigoArticulo(getArticulo().getCodigo());

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnEntradaActionEvent(ActionEvent event) {

        try {

            if (tbArticulo.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que selccionar un articulo");
                return;
            }
//            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/movimiento/EntradaArticulo.fxml"));
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/inventario/articulo/movimiento/EntradaArticulo.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            EntradaArticuloController registroArticuloController = loader.getController();
            registroArticuloController.setCodigoArticuulo(getArticulo().getCodigo());
            registroArticuloController.llenarCampo();
            bpPrincipal.setCenter(root);// 
//
//            registroArticuloController.setEditar(true);
//            registroArticuloController.setArticulo(getArticulo());
//

        } catch (IOException ex) {

            Logger.getLogger(ArticulosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAjusteActionevent(ActionEvent event) {

    }

    @FXML
    private void btnSalidaActionEvent(ActionEvent event) throws IOException {

        if (tbArticulo.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje("Tiene que selccionar un articulo");
            return;
        }
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/inventario/articulo/movimiento/SalidaArticulo.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        SalidaArticuloController salidaArticuloController = loader.getController();
        salidaArticuloController.setCodigoArticuulo(getArticulo().getCodigo());
        salidaArticuloController.llenarCampo();
        bpPrincipal.setCenter(root);// 

    }

    @FXML
    private void tbArticuloMouseClicked(MouseEvent event) {

        if (!(tbArticulo.getSelectionModel().getSelectedIndex() == -1)) {

            vbMovimiento.setVisible(true);
            setArticulo(tbArticulo.getSelectionModel().getSelectedItem());
            lbArticuloListaPrecio.setText(getArticulo().getDescripcion());
            lbArticuloAlmacen.setText(getArticulo().getDescripcion());

            listaDetalleArticulo.clear();
            listaDetalleArticulo.addAll(ManejoArticulo.getInstancia().getListaDePrecioArticuloDTO(getArticulo()));

            listaExistenciaArticulo.clear();
            listaExistenciaArticulo.addAll(ManejoExistenciaArticulo.getInstancia().getAlmacenExistenciaArticulo(getArticulo().getCodigo()));

            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(1);
            }

        } else {

            vbMovimiento.setVisible(false);
        }
    }

    @FXML
    private void btnSeleccionarLoteActionEvent(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/produccion/lote/BuscarLoteDeProduccion.fxml"));
            loader.load();

            BusacrLoteDeProduccionController registroArticuloController = loader.getController();

            registroArticuloController.setArticulo(getArticulo());
            registroArticuloController.buscarLote();
            Parent root = loader.getRoot();

            ClaseUtil.getStageModal(root);

            if (!(registroArticuloController.getRegistroLote() == null)) {

                setRegistroLote(registroArticuloController.getRegistroLote());
                lbLote.setText(" " + registroArticuloController.getRegistroLote().getNumeroDeLote());
            }

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
