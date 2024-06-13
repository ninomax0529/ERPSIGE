/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.devolucion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.inventario.articulo.FXMLBuscarArticuloController;
import controlador.suplidor.FXMLBusSuplidorController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.inventario.ajuste.ManejoAjusteDeInventario;
import manejo.inventario.devolucion.ManejoDevolucionDeInventario;
import manejo.inventario.devolucion.ManejoTipoDevolucion;
import modelo.AjusteInventario;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.DetalleAjusteInventario;
import modelo.DetalleDevolucionDeInventario;
import modelo.DevolucionDeInventario;
import modelo.ExistenciaArticulo;
import modelo.Suplidor;
import modelo.TipoDevolucion;
import modelo.Unidad;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroDevolucionDeInventarioController implements Initializable {

    @FXML
    private JFXTextField txtNumeroEntrada;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtArticulo;
    @FXML
    private JFXButton btBuscarArticulo;
    @FXML
    private JFXComboBox<ArticuloUnidad> cbUnidad;
    @FXML
    private JFXTextField txtCantidadPedida;
    @FXML
    private JFXButton btnAgregarArticulo;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private TableView<DetalleDevolucionDeInventario> tbDetalleAjusteDeInventario;
    @FXML
    private TableColumn<DetalleDevolucionDeInventario, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleDevolucionDeInventario, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleDevolucionDeInventario, ?> tbcExistencia11;
    @FXML
    private TableColumn<DetalleDevolucionDeInventario, Double> tbcExistencia;

    @FXML
    private TableColumn<DetalleDevolucionDeInventario, String> tbcUnidadSalida;
    @FXML
    private TableColumn<DetalleDevolucionDeInventario, Double> tbcCantidad;
//
//    private TableColumn<DetalleDevolucionDeInventario, String> tbcUnidad;
    @FXML
    private Label lbColumnaUnidad;
    @FXML
    private TableColumn<?, ?> tbcValor11;
    @FXML
    private TableColumn<DetalleDevolucionDeInventario, Double> tbcCostoTotal;
    @FXML
    private TableColumn<DetalleDevolucionDeInventario, Double> tbcCostoUnitario;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXButton btnGuardar;

    Articulo articulo;
    @FXML
    private JFXComboBox<TipoDevolucion> cbTipoDevolucion;
    @FXML
    private JFXTextField txtNombreSuplidor;
    @FXML
    private JFXButton btBuscarASuplidor;

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    ObservableList<DetalleDevolucionDeInventario> listadetalle = FXCollections.observableArrayList();

    ObservableList<ArticuloUnidad> listaUnidads = FXCollections.observableArrayList();
    ObservableList<TipoDevolucion> listaTipoDev = FXCollections.observableArrayList();

    DevolucionDeInventario devolucionDeInventario;
    DetalleDevolucionDeInventario detalleDevolucionDeInventario;
    Suplidor suplidor;

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFecha.setValue(LocalDate.now());

        inicializarSecuencia();
        iniciarTablaDetalle();
        inicializarCombox();
        nuevo();

        txtCantidadPedida.setOnKeyReleased((event) -> {

            if (event.getCode() == KeyCode.ENTER) {

                try {

                    agregarArticulo();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }

    private void inicializarCombox() {

        cbUnidad.setConverter(new StringConverter<ArticuloUnidad>() {

            @Override
            public String toString(ArticuloUnidad unidad) {
                return String.valueOf(unidad.getUnidad().getDescripcion());
            }

            @Override
            public ArticuloUnidad fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoDevolucion.setConverter(new StringConverter<TipoDevolucion>() {

            @Override
            public String toString(TipoDevolucion tipoDev) {
                return String.valueOf(tipoDev.getNombre());
            }

            @Override
            public TipoDevolucion fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaTipoDev.addAll(ManejoTipoDevolucion.getInstancia().getLista());
        cbUnidad.setItems(listaUnidads);
        cbTipoDevolucion.setItems(listaTipoDev);

    }

    public void iniciarTablaDetalle() {

        listadetalle.clear();

        tbDetalleAjusteDeInventario.setItems(listadetalle);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcionArticulo"));

        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcCostoTotal.setCellValueFactory(new PropertyValueFactory<>("costo"));
        tbcCostoUnitario.setCellValueFactory(new PropertyValueFactory<>("costoUnitario"));
//        tbcUnidadSalida.setCellValueFactory(new PropertyValueFactory<>("unidad"));
//        tbcExistenciaNueva.setCellValueFactory(new PropertyValueFactory<>("nuevaExistencia"));
        tbcExistencia.setCellValueFactory(new PropertyValueFactory<>("existenciaActual"));

        tbDetalleAjusteDeInventario.setEditable(true);

        tbcUnidadSalida.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {

                        property.setValue(ManejoArticuloUnidad.getInstancia()
                                .getArticuloUnidadSslida(cellData.getValue()
                                        .getArticulo().getCodigo()).getUnidad()
                                .getDescripcion());
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

        tbcDescripcionArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getDescripcionArticulo());
                    }
                    return property;
                });
//
//        tbcUnidad.setCellValueFactory(
//                cellData -> {
//                    SimpleStringProperty property = new SimpleStringProperty();
//                    if (cellData.getValue() != null) {
//                        property.setValue(cellData.getValue().getUnidadSalida().toString());
//                    }
//                    return property;
//                });

        tbcCantidad.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcCantidad.setOnEditCommit(data -> {

            try {

                DetalleDevolucionDeInventario p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00, precioCompraUnitario = 0.00,
                        descuento = 0.00, impuesto = 0.00;

                if (data.getNewValue() >= 1) {

                    p.setCantidad(data.getNewValue());
                    cantidad = p.getCantidad();

                    if (Objects.equals(p.getArticulo().getUnidadEntrada(), getArticulo().getUnidadEntrada())) {

                        Double cantidadUnidad = getArticulo().getCantidadUnidades();

                    } else if (Objects.equals(p.getUnidadSalida(), getArticulo().getUnidadSalida())) {

                        p.setNuevaExistencia(getArticulo().getExistencia() + p.getCantidad());

                    }

                    subTotal = ClaseUtil.roundDouble(p.getCostoUnitario() * cantidad, 2);
                    p.setCosto(subTotal);

                    tbDetalleAjusteDeInventario.refresh();
                    tbDetalleAjusteDeInventario.requestFocus();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleAjusteDeInventario.refresh();
                    tbDetalleAjusteDeInventario.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    @FXML
    private void btBuscarArticuloActionEvent(ActionEvent event) {

        try {
//
//            System.out.println("Entre");
            FXMLLoader loader = new FXMLLoader();
//
            loader.setLocation(getClass().getResource("/vista/inventario/articulo/FXMLBuscarArticulo.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            FXMLBuscarArticuloController articuloController = loader.getController();
            articuloController.setOrigen(1);//el valor  3 es para  buscar solo articulos de consumo

            articuloController.llenarCampo();

            ClaseUtil.getStageModal(root);

            if (!(articuloController.getArticulo() == null)) {

                setArticulo(articuloController.getArticulo());
                txtArticulo.setText(getArticulo().getDescripcion());
                txtCantidadPedida.requestFocus();
                listaUnidads.clear();
//                String unidadES = getArticulo().getUnidadEntrada().getCodigo() + "," + getArticulo().getUnidadSalida().getCodigo();

                listaUnidads.addAll(ManejoArticuloUnidad.getInstancia().getListaUnidadSslida(getArticulo().getCodigo()));
                cbUnidad.setItems(listaUnidads);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void cbUnidadActionEvent(ActionEvent event) {

    }

    @FXML
    private void btnAgregarArticuloActionEvent(ActionEvent event) {

        try {

            agregarArticulo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        if (tbDetalleAjusteDeInventario.getSelectionModel().getSelectedIndex() != -1) {

            listadetalle.remove(tbDetalleAjusteDeInventario.getSelectionModel().getFocusedIndex());
            tbDetalleAjusteDeInventario.refresh();
            txtCantidad.setText(Integer.toString(listadetalle.size()));

        }
    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            try {

                if (tbDetalleAjusteDeInventario.getItems().size() <= 0) {

                    ClaseUtil.mensaje("No hay articulos para realizar esta devolucion");
                    return;
                }

                devolucionDeInventario.setAnulada(false);
                devolucionDeInventario.setObservacion(txtComentario.getText());
                devolucionDeInventario.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
                devolucionDeInventario.setUsuario(VariablesGlobales.USUARIO);
                devolucionDeInventario.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

                devolucionDeInventario.setFechaRegistro(new Date());

                devolucionDeInventario.setTipoDevolucion(cbTipoDevolucion.getSelectionModel().getSelectedItem());
                devolucionDeInventario.setDetalleDevolucionDeInventarioCollection(listadetalle);

                DevolucionDeInventario devolucionDb = ManejoDevolucionDeInventario.getInstancia().salvar(devolucionDeInventario);

                if (devolucionDb == null) {

                    ClaseUtil.mensaje("Hubo un error creando la devolucion ");
                    return;

                } else {

                    List<DetalleDevolucionDeInventario> listaDetalle = ManejoDevolucionDeInventario
                            .getInstancia().getDetalleDevolucion(devolucionDb);

                    if (listaDetalle.size() > 0) {

                        System.out.println(" Devolucion Actualizando existencia de articulos en almacen");

                        ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorDevolucion(listaDetalle);

                        System.out.println(" Devolucion de Actualizando existencia  de articulos en almacen");

//                    RptEntradaInventario.getInstancia().imprimir(ajusteDb.getCodigo());
                    }

                    nuevo();
                    listaDetalle.clear();
                    listadetalle.clear();
                    txtArticulo.clear();
                    txtCantidad.clear();
                    txtComentario.clear();
                    ClaseUtil.mensaje("Devolucion registrada correctamente");

                }

            } catch (Exception e) {
                System.out.println("Hubo un error y No entro Actualizar existencia de articulos en almacen");
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void cbTipoDevolucionActionEvent(ActionEvent event) {

        if (!(cbTipoDevolucion.getSelectionModel().getSelectedIndex() == -1)) {
            if (cbTipoDevolucion.getSelectionModel().getSelectedIndex() == 1) {

                btBuscarASuplidor.setDisable(true);
            } else {
                btBuscarASuplidor.setDisable(false);
            }
        }
    }

    private void agregarArticulo() {

        if (txtArticulo.getText().isEmpty()) {
            utiles.ClaseUtil.mensaje("Tiene que seleccionar un articulo");
            return;
        }

        if (cbUnidad.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje(" Tiene que seleccionar una unidad ");
            return;
        }

        if (txtCantidadPedida.getText().isEmpty()) {

            utiles.ClaseUtil.mensaje(" Tiene que digital una cantidad ");
            txtCantidadPedida.requestFocus();
            return;
        }

        detalleDevolucionDeInventario.setUnidadEntrada(cbUnidad.getSelectionModel().getSelectedItem());

//        ArticuloUnidad articuloUnidad = ManejoArticuloUnidad.getInstancia()
//                .getArticuloUnidadEntrada(getArticulo().getCodigo(), detalleDevolucionDeInventario.getArticulo().getUnidadEntrada().getCodigo());
//        Double cantidadUnidad = articuloUnidad.getFatorVenta();
        Double cantidad = Double.parseDouble(txtCantidadPedida.getText());
        detalleDevolucionDeInventario.setArticulo(getArticulo());
        detalleDevolucionDeInventario.setCantidad(cantidad);
        detalleDevolucionDeInventario.setNuevaExistencia(cantidad);
        detalleDevolucionDeInventario.setDescripcionArticulo(getArticulo().getDescripcion());//   
        detalleDevolucionDeInventario.setDescripcionUnidadEntrada(cbUnidad.getSelectionModel().getSelectedItem().getUnidad().getDescripcion());

        detalleDevolucionDeInventario.setDevolucionDeInventario(devolucionDeInventario);
        detalleDevolucionDeInventario.setCosto(0.00);

        ExistenciaArticulo exisArt = ManejoExistenciaArticulo.getInstancia()
                .getExistenciaArticulo(detalleDevolucionDeInventario.getArticulo().getCodigo(),
                        detalleDevolucionDeInventario.getArticulo().getAlmacen());

        detalleDevolucionDeInventario.setExistenciaActual(exisArt.getExistencia());
        detalleDevolucionDeInventario.setUnidadEntrada(cbUnidad.getValue());
        detalleDevolucionDeInventario.setUnidadSalida(cbUnidad.getValue().getCodigo());

        txtArticulo.clear();
        txtCantidadPedida.clear();
        listaUnidads.clear();
        if (existweDetalle(detalleDevolucionDeInventario)) {

            ClaseUtil.mensaje("Este articulo ya fue agregado");
            return;
        }

        listadetalle.add(detalleDevolucionDeInventario);
        txtCantidad.setText(Integer.toString(listadetalle.size()));

        detalleDevolucionDeInventario = new DetalleDevolucionDeInventario();
    }

    private void nuevo() {

        devolucionDeInventario = new DevolucionDeInventario();
        detalleDevolucionDeInventario = new DetalleDevolucionDeInventario();
        inicializarSecuencia();
    }

    private boolean existweDetalle(DetalleDevolucionDeInventario detalle) {

        boolean existe = false;

        for (DetalleDevolucionDeInventario det : listadetalle) {

            if (det.getArticulo().equals(detalle)) {
                existe = true;
            }
        }

        return existe;
    }

    private void inicializarSecuencia() {

        txtNumeroEntrada.setText(ManejoDevolucionDeInventario.getInstancia().getNumero().toString());
    }

    @FXML
    private void btBuscarASuplidorActionevent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/suplidor/FXMLBusSuplidor.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        System.out.println("ESPERANDOOO!!!");

        FXMLBusSuplidorController suplidorController = loader.getController();

        if (!(suplidorController.getSuplidor() == null)) {

            setSuplidor(suplidorController.getSuplidor());
            txtNombreSuplidor.setText(getSuplidor().getDescripcion() + " (" + getSuplidor().getTipoSuplidor().getNombre() + ")");

        }

    }
}
