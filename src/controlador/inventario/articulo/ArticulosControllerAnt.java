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
import dto.articulo.ArticuloDTO;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import manejo.articulo.ManejoArticulo;
import modelo.Articulo;
import reporte.inventario.RptImprimirEtiqueta;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ArticulosControllerAnt implements Initializable {

    @FXML
    private JFXButton btnVerEtiqueta;
    @FXML
    private JFXButton btnImprimir;
    @FXML
    private TableColumn<Articulo, Double> tbcPrecioVenta1;
    @FXML
    private TableColumn<Articulo, Double> tbcPrecioVenta2;
    @FXML
    private TableColumn<Articulo, Double> tbcPrecioVenta3;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private TableView<ArticuloDTO> tbArticulo;
    @FXML
    private TableColumn<ArticuloDTO, String> tbcCodigo;
    @FXML
    private TableColumn<ArticuloDTO, String> tbcNombre;
    @FXML
    private TableColumn<ArticuloDTO, String> tbcUnidad;
    @FXML
    private TableColumn<ArticuloDTO, Double> tbcExistencia;
    @FXML
    private TableColumn<ArticuloDTO, Double> tbcPrecioAlDetalle;
    @FXML
    private TableColumn<ArticuloDTO, String> tbcCategoria;
//    @FXML
//    private TableColumn<ArticuloDTO, String> tbcSubCategoria;

    @FXML
    private TableColumn<ArticuloDTO, Double> tbcPrecioCompra;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Label lbCantidad;

    ObservableList<ArticuloDTO> listaArticulo = FXCollections.observableArrayList();

    private ArticuloDTO articulo;
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

    /**
     * @return the articulo
     */
    public ArticuloDTO getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(ArticuloDTO articulo) {
        this.articulo = articulo;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaArticulo();
        iniciazarFiltro();
    }

    private void iniciazarFiltro() {

        FilteredList<ArticuloDTO> filteredData = new FilteredList<>(tbArticulo.getItems(), p -> true);
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
                } else if (filtro.getCodigo() != null && filtro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ArticuloDTO> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbArticulo.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbArticulo.setItems(sortedData);
    }

    public void iniciarTablaArticulo() {

        listaArticulo.clear();

        tbArticulo.setItems(listaArticulo);
        listaArticulo.addAll(ManejoArticulo.getInstancia().getListaArticuloDTO());

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcExistencia.setCellValueFactory(new PropertyValueFactory<>("existencia"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));        
        tbcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
//        tbcSubCategoria.setCellValueFactory(new PropertyValueFactory<>("subCategoria"));
        tbcPrecioVenta1.setCellValueFactory(new PropertyValueFactory<>("precioVentaLpg"));
        tbcPrecioVenta2.setCellValueFactory(new PropertyValueFactory<>("precioVentaLpm"));
        tbcPrecioVenta3.setCellValueFactory(new PropertyValueFactory<>("precioVentaLpn"));
        tbcPrecioAlDetalle.setCellValueFactory(new PropertyValueFactory<>("precioVentaLpd"));
        tbcPrecioCompra.setCellValueFactory(new PropertyValueFactory<>("costoUnitario"));
//
//        tbcUnidad.setCellValueFactory(
//                cellData -> {
//                    SimpleStringProperty property = new SimpleStringProperty();
//                    if (cellData.getValue() != null) {
//                        property.setValue(cellData.getValue().getUnidad());
//                    }
//                    return property;
//                });
//
//        tbcCategoria.setCellValueFactory(
//                cellData -> {
//                    SimpleStringProperty property = new SimpleStringProperty();
//                    if (cellData.getValue() != null) {
//                        property.setValue(cellData.getValue().getCategoria().getNombre());
//                    }
//                    return property;
//                });
//
//        tbcSubCategoria.setCellValueFactory(
//                cellData -> {
//                    SimpleStringProperty property = new SimpleStringProperty();
//                    if (cellData.getValue() != null) {
//                        property.setValue(cellData.getValue().getSubCategoria().getNombre());
//                    }
//                    return property;
//                });

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/RegistroArticulo_1.fxml"));

        ClaseUtil.crearStageModal(root);

        listaArticulo.clear();
        listaArticulo.addAll(ManejoArticulo.getInstancia().getListaArticuloDTO());

    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbArticulo.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un Articulo");

        } else {

            setArticulo(tbArticulo.getSelectionModel().getSelectedItem());

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/inventario/articulo/RegistroArticulo_1.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroArticuloController1 registroArticuloController = loader.getController();

            registroArticuloController.setEditar(true);
            registroArticuloController.setArticulo(ManejoArticulo.getInstancia().getArticulo(getArticulo().getCodigo()));

            registroArticuloController.llenarCampo();

            ClaseUtil.getStageModal(root);

            listaArticulo.clear();
            listaArticulo.addAll(ManejoArticulo.getInstancia().getListaArticuloDTO());

        }

    }

    @FXML
    private void btnVerEtiquetaActionEvent(ActionEvent event) {

        try {

            if (!(tbArticulo.getSelectionModel().getSelectedIndex() == -1)) {

                articulo = tbArticulo.getSelectionModel().getSelectedItem();
                RptImprimirEtiqueta.getInstancia().verEtiqueta(articulo.getCodigo().toString());

            } else {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN ARTICULO");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnImprimirActionEvent(ActionEvent event) {
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

            registroArticuloController.getLbArticulo().setText(getArticulo().getNombre());
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

            Logger.getLogger(ArticulosControllerAnt.class.getName()).log(Level.SEVERE, null, ex);
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

        } else {
            vbMovimiento.setVisible(false);
        }
    }

}
