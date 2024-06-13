/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.entrada;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.inventario.articulo.FXMLBuscarArticuloController;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import manejo.articulo.ManejoArticulo;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.inventario.entrada.ManejoInventarioFinal;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.DetalleInventarioFinal;
import modelo.ExistenciaArticulo;
import modelo.InventarioFinal;

import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class RegistroInventarioController implements Initializable {

    @FXML
    private JFXTextField txtNumInventario;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtArticulo;
    @FXML
    private JFXButton btnBuscarArticulo;

    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private TableView<DetalleInventarioFinal> tbArticulo;
    @FXML
    private TableColumn<DetalleInventarioFinal, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleInventarioFinal, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleInventarioFinal, String> tbcUnidad;
    @FXML
    private TableColumn<DetalleInventarioFinal, Double> tbcCantidad;
    @FXML
    private TableColumn<DetalleInventarioFinal, String> tbcalmacen;
    @FXML
    private JFXButton btnGuardar;

    Articulo articulo;
    DetalleInventarioFinal detalleInventarioFinal;
    InventarioFinal inventarioFinal;
    @FXML
    private JFXTextField txtExistencia;
    @FXML
    private Label lbCantidad;
    @FXML
    private JFXComboBox<ArticuloUnidad> cbUnidad;
    @FXML
    private JFXComboBox<ExistenciaArticulo> cbAlmacen;

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    ObservableList<DetalleInventarioFinal> listaDetalle = FXCollections.observableArrayList();
    ObservableList<ArticuloUnidad> listaUnidads = FXCollections.observableArrayList();
    ObservableList<ExistenciaArticulo> listaExistenciaArticulo = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTablaDetalle();
        inventarioFinal = new InventarioFinal();
        dpFecha.setValue(LocalDate.now());
        inicializarSecuencia();
        inicializarCombox();

    }

    private void inicializarCombox() {

        cbUnidad.setConverter(new StringConverter<ArticuloUnidad>() {

            @Override
            public String toString(ArticuloUnidad articuloUnidad) {
                return String.valueOf(articuloUnidad.getUnidad().getDescripcion());
            }

            @Override
            public ArticuloUnidad fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbAlmacen.setConverter(new StringConverter<ExistenciaArticulo>() {

            @Override
            public String toString(ExistenciaArticulo unidad) {
                return String.valueOf(unidad.getAlmacen().getNombre());
            }

            @Override
            public ExistenciaArticulo fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbAlmacen.setItems(listaExistenciaArticulo);

        cbUnidad.setItems(listaUnidads);

    }

    public void iniciarTablaDetalle() {

        listaDetalle.clear();

        tbArticulo.setItems(listaDetalle);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("existencia"));
        tbcalmacen.setCellValueFactory(new PropertyValueFactory<>("almacen"));

        tbcCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });

        tbcDescripcionArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getDescripcion());
                    }
                    return property;
                });

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

        tbcalmacen.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getAlmacen().getNombre());
                    }
                    return property;
                });

    }

    @FXML
    private void btnBuscarArticuloActionEvent(ActionEvent event) {

        try {

//            FXMLLoader loader = new FXMLLoader();
//
//            loader.setLocation(getClass().getResource("/vista/inventario/articulo/FXMLBuscarArticulo.fxml"));
//            loader.load();
//
//            Parent root = loader.getRoot();
//
//            Scene scene = new Scene(root);
//
//            Stage stage = new Stage();
//
//            stage.setScene(scene);
//
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.showAndWait();
//
//            System.out.println("ESPERANDOOO!!!");
//
//            FXMLBuscarArticuloController articuloController = loader.getController();
//            
//            articuloController.llenarCampo();
            System.out.println("Entre");
            FXMLLoader loader = new FXMLLoader();
//
            loader.setLocation(getClass().getResource("/vista/inventario/articulo/FXMLBuscarArticulo.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            FXMLBuscarArticuloController articuloController = loader.getController();
            articuloController.setOrigen(1);//el valor  3 es para  buscar solo articulos de consumo

            articuloController.llenarCampo();

            utiles.ClaseUtil.getStageModal(root);

            if (!(articuloController.getArticulo() == null)) {

                setArticulo(articuloController.getArticulo());
                txtArticulo.setText(getArticulo().getDescripcion());
                cbUnidad.requestFocus();
                listaUnidads.clear();
                listaExistenciaArticulo.clear();

                listaUnidads.addAll(ManejoArticuloUnidad.getInstancia().getListaUnidadSslida(getArticulo().getCodigo()));
                listaExistenciaArticulo.addAll(ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo(getArticulo().getCodigo()));
                cbUnidad.setItems(listaUnidads);
                cbAlmacen.setItems(listaExistenciaArticulo);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void txtCantidadSolicitada(KeyEvent event) {

        try {

            if (event.getCode() == KeyCode.ENTER) {

                if (txtArticulo.getText().isEmpty()) {
                    ClaseUtil.mensaje("Tiene que seleccionar un articulo");
                    return;
                }

                if (cbAlmacen.getSelectionModel().getSelectedIndex() == -1) {

                    utiles.ClaseUtil.mensaje(" Tiene que seleccionar un almacen ");
                    return;
                }

                if (cbUnidad.getSelectionModel().getSelectedIndex() == -1) {

                    utiles.ClaseUtil.mensaje(" Tiene que seleccionar una unidad ");
                    return;
                }

                if (txtExistencia.getText().isEmpty()) {

                    ClaseUtil.mensaje(" Tiene que digital una cantidad ");
                    return;
                }

                agregarArticulo();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {

        try {
//
            if (txtArticulo.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que seleccionar un articulo");
                return;
            }

            if (txtExistencia.getText().isEmpty()) {

                ClaseUtil.mensaje(" Tiene que digital una cantidad ");
                return;
            }

            if (cbUnidad.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje(" Tiene que seleccionar una unidad ");
                return;
            }

            agregarArticulo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        try {

            if (tbArticulo.getSelectionModel().getSelectedIndex() != -1) {

                listaDetalle.remove(tbArticulo.getSelectionModel().getSelectedIndex());
                tbArticulo.refresh();
//                txtArticulo.setText(cantidadArticulo().toString());

            } else {
                ClaseUtil.mensaje("Tiene que selccionar un registro");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (tbArticulo.getItems().size() <= 0) {

                ClaseUtil.mensaje("El inventario no tiene detalle");
                return;
            }

            inventarioFinal.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            inventarioFinal.setFechaRegistro(new Date());
            inventarioFinal.setDetalleInventarioFinalCollection(listaDetalle);
            inventarioFinal.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            inventarioFinal.setUsuario(VariablesGlobales.USUARIO.getCodigo());
            inventarioFinal.setAnulado(false);
            
            ManejoInventarioFinal.getInstancia().salvar(inventarioFinal);

            System.out.println("Actualizando existencia de articulos en almacen");
            ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorInventarioInicial(listaDetalle);

            limpiarCampo();
            ClaseUtil.mensaje("Inventario Guardada Correctamente ");

        } catch (Exception e) {

            ClaseUtil.mensaje("Hubo un problema  Guardando el Inventario  ");
            e.printStackTrace();
        }
    }

    private void agregarArticulo() {

        try {

            detalleInventarioFinal = new DetalleInventarioFinal();

            Double cantidad = Double.parseDouble(txtExistencia.getText());

            detalleInventarioFinal.setArticulo(getArticulo());
            detalleInventarioFinal.setUnidad(cbUnidad.getSelectionModel().getSelectedItem().getUnidad());
            detalleInventarioFinal.setAlmacen(cbAlmacen.getSelectionModel().getSelectedItem().getAlmacen());
            detalleInventarioFinal.setExistencia(cantidad);
            
            detalleInventarioFinal.setInventarioFinal(inventarioFinal);

            if (existe(detalleInventarioFinal)) {
                ClaseUtil.mensaje("Este Articulo ya Existe en el Detalle ");
                return;
            }

            listaDetalle.add(detalleInventarioFinal);

            txtArticulo.clear();
            txtExistencia.clear();
            lbCantidad.setText(getCantidadArticulo().toString());

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private boolean existe(DetalleInventarioFinal det) {

        for (DetalleInventarioFinal detalle : tbArticulo.getItems()) {

            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())
                    && Objects.equals(detalle.getAlmacen(), det.getAlmacen())) {

                return true;

            }
        }

        return false;
    }

    private void limpiarCampo() {

        txtArticulo.clear();

        txtExistencia.clear();

        dpFecha.setValue(null);
        listaDetalle.clear();
        detalleInventarioFinal = new DetalleInventarioFinal();

    }

    private void actualizarExistencia() {

        try {

            for (DetalleInventarioFinal det : listaDetalle) {

                ExistenciaArticulo exisArt = ManejoExistenciaArticulo.getInstancia()
                        .getExistenciaArticulo(det.getArticulo().getCodigo(), det.getAlmacen().getCodigo());
                
                Articulo articuloActualizar = exisArt.getArticulo();

                if (exisArt != null) {

                    System.out.println("Entro actualizar"
                            + " " + exisArt.getArticulo().getCodigo() + " " + exisArt.getAlmacen().getCodigo()
                            + " Existencia " + (exisArt.getExistencia() + det.getExistencia()));

                    exisArt.setExistencia(exisArt.getExistencia() + det.getExistencia());
                    ManejoExistenciaArticulo.getInstancia().actualizar(exisArt);

                    articuloActualizar.setExistencia(exisArt.getExistencia());
                    ManejoArticulo.getInstancia().actualizar(articuloActualizar);

                } else {
                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inicializarSecuencia() {

        txtNumInventario.setText(ManejoInventarioFinal.getInstancia().getNumero().toString());
    }

    @FXML
    private void txtExistencia(KeyEvent event) {

        try {

            if (event.getCode() == KeyCode.ENTER) {

                if (txtArticulo.getText().isEmpty()) {
                    ClaseUtil.mensaje("Tiene que seleccionar un articulo");
                    return;
                }

                if (txtExistencia.getText().isEmpty()) {

                    ClaseUtil.mensaje(" Tiene que digital una cantidad ");
                    return;
                }

                if (cbUnidad.getSelectionModel().getSelectedIndex() == -1) {

                    ClaseUtil.mensaje(" Tiene que seleccionar una unidad ");
                    cbUnidad.requestFocus();
                    return;
                }

                agregarArticulo();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Integer getCantidadArticulo() {

        Integer cantidad = listaDetalle.size();

        return cantidad;
    }

    @FXML
    private void cbAlmacenActionEvent(ActionEvent event) {
    }
}
