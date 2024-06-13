/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.ajuste;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import controlador.inventario.articulo.FXMLBuscarArticuloController;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.inventario.ajuste.ManejoAjusteDeInventario;
import modelo.AjusteInventario;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.DetalleAjusteInventario;
import modelo.ExistenciaArticulo;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroAjusteDeInventarioController implements Initializable {

    @FXML
    private JFXTextField txtNumeroEntrada;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXRadioButton rbAumento;
    @FXML
    private JFXRadioButton rbDisminucion;
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
    private TableView<DetalleAjusteInventario> tbDetalleAjusteDeInventario;
    @FXML
    private TableColumn<DetalleAjusteInventario, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleAjusteInventario, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleAjusteInventario, Double> tbcExistencia11;
    @FXML
    private TableColumn<DetalleAjusteInventario, Double> tbcExistencia;
    @FXML
    private TableColumn<DetalleAjusteInventario, Double> tbcExistenciaNueva;
    @FXML
    private TableColumn<DetalleAjusteInventario, String> tbcUnidadSalida;
    @FXML
    private TableColumn<DetalleAjusteInventario, Double> tbcCantidad;
    @FXML
    private TableColumn<DetalleAjusteInventario, String> tbcUnidad;
    @FXML
    private Label lbColumnaUnidad;
    @FXML
    private TableColumn<DetalleAjusteInventario, Double> tbcValor11;
    @FXML
    private TableColumn<DetalleAjusteInventario, Double> tbcCostoTotal;
    @FXML
    private TableColumn<DetalleAjusteInventario, Double> tbcCostoUnitario;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXComboBox<ExistenciaArticulo> cbAlmacen;

    final ToggleGroup group = new ToggleGroup();

    ObservableList<DetalleAjusteInventario> listadetalle = FXCollections.observableArrayList();
    ObservableList<ArticuloUnidad> listaUnidads = FXCollections.observableArrayList();
    ObservableList<ExistenciaArticulo> listaExistenciaArticulo = FXCollections.observableArrayList();
    AjusteInventario ajusteInventario;
    DetalleAjusteInventario detalleAjusteInventario;
    Articulo articulo;

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rbAumento.setToggleGroup(group);
        rbDisminucion.setToggleGroup(group);
        rbAumento.setSelected(true);
//        rbDisminucion.setSelected(false);
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

        cbUnidad.setItems(listaUnidads);
        cbAlmacen.setItems(listaExistenciaArticulo);

    }

    public void iniciarTablaDetalle() {

        listadetalle.clear();

        tbDetalleAjusteDeInventario.setItems(listadetalle);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcionArticulo"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadAjustada"));
        tbcCostoTotal.setCellValueFactory(new PropertyValueFactory<>("costo"));
        tbcCostoUnitario.setCellValueFactory(new PropertyValueFactory<>("costoUnitario"));
        tbcUnidadSalida.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcExistenciaNueva.setCellValueFactory(new PropertyValueFactory<>("nuevaExistencia"));
        tbcExistencia.setCellValueFactory(new PropertyValueFactory<>("existencia"));

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

                DetalleAjusteInventario p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00, precioCompraUnitario = 0.00,
                        descuento = 0.00, impuesto = 0.00;

                if (data.getNewValue() >= 1) {

                    p.setCantidadAjustada(data.getNewValue());
                    cantidad = p.getCantidadAjustada();

                    if (Objects.equals(p.getUnidad(), getArticulo().getUnidadEntrada())) {

                        Double cantidadUnidad = getArticulo().getCantidadUnidades();

                        if (rbAumento.isSelected()) {
                            p.setNuevaExistencia(getArticulo().getExistencia() + (p.getCantidadAjustada() * cantidadUnidad));

                        }

                        if (rbDisminucion.isSelected()) {
                            p.setNuevaExistencia(getArticulo().getExistencia() - (p.getCantidadAjustada() * cantidadUnidad));

                        }

                    } else if (Objects.equals(p.getUnidad(), getArticulo().getUnidadSalida())) {

                        if (rbAumento.isSelected()) {

                            p.setNuevaExistencia(getArticulo().getExistencia() + p.getCantidadAjustada());

                        }

                        if (rbDisminucion.isSelected()) {

                            p.setNuevaExistencia(getArticulo().getExistencia() - p.getCantidadAjustada());

                        }

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
                listaExistenciaArticulo.clear();

                listaUnidads.addAll(ManejoArticuloUnidad.getInstancia().getListaUnidadSslida(getArticulo().getCodigo()));

                listaExistenciaArticulo.addAll(ManejoExistenciaArticulo.getInstancia()
                        .getExistenciaArticulo(getArticulo().getCodigo()));

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

        }
    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (rbAumento.isSelected() == false && rbDisminucion.isSelected() == false) {

                ClaseUtil.mensaje("Tiene que selccionar un tipo de ajusteF");

                return;
            }

            if (tbDetalleAjusteDeInventario.getItems().size() <= 0) {

                ClaseUtil.mensaje("No hay articulos para realizar este ajuste");
                return;
            }

            ajusteInventario.setAnulado(false);
            ajusteInventario.setObservacion(txtComentario.getText());
            ajusteInventario.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            ajusteInventario.setUsuario(VariablesGlobales.USUARIO.getCodigo());
            ajusteInventario.setUsuario(VariablesGlobales.USUARIO.getCodigo());
            ajusteInventario.setFechaRegistro(new Date());
            ajusteInventario.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            int tipoAjuste = 0;

            if (rbAumento.isSelected()) {

                tipoAjuste = 1;

            } else if (rbDisminucion.isSelected()) {

                tipoAjuste = 2;
            }

            ajusteInventario.setTipoAjuste(tipoAjuste);
            ajusteInventario.setDetalleAjusteInventarioCollection(listadetalle);

            AjusteInventario ajusteDb = ManejoAjusteDeInventario.getInstancia().salvar(ajusteInventario);

            if (ajusteDb == null) {

                ClaseUtil.mensaje("Hubo un error creando el ajuste ");
                return;

            } else {

                List<DetalleAjusteInventario> listaDetalleAjuste = ManejoAjusteDeInventario
                        .getInstancia().getDetalleAjusteInventario(ajusteDb);

                if (listaDetalleAjuste.size() > 0) {

                    System.out.println(" Entrando Actualizando existencia de articulos en almacen");

                    ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorAjuste(listaDetalleAjuste, tipoAjuste);

                    System.out.println(" Saliendo de Actualizando existencia  de articulos en almacen");

//                    RptEntradaInventario.getInstancia().imprimir(ajusteDb.getCodigo());
                }

                nuevo();
                listaDetalleAjuste.clear();
                listadetalle.clear();
                txtArticulo.clear();
                txtCantidad.clear();
                txtComentario.clear();
                ClaseUtil.mensaje("Ajuste registrado correctamente");

            }

        } catch (Exception e) {
            System.out.println("Hubo un error y No entro Actualizar existencia de articulos en almacen");
            e.printStackTrace();
        }

    }

    private void inicializarSecuencia() {

        txtNumeroEntrada.setText(ManejoAjusteDeInventario.getInstancia().getNumero().toString());
    }

    private void nuevo() {

        ajusteInventario = new AjusteInventario();
        detalleAjusteInventario = new DetalleAjusteInventario();
        inicializarSecuencia();
    }

    private boolean existweDetalle(DetalleAjusteInventario detalle) {

        boolean existe = false;

        for (DetalleAjusteInventario det : listadetalle) {

            if (det.getArticulo().equals(detalle)) {
                existe = true;
            }
        }

        return existe;
    }

    private void agregarArticulo() {

        if (txtArticulo.getText().isEmpty()) {
            utiles.ClaseUtil.mensaje("Tiene que seleccionar un articulo");
            return;
        }

        if (cbAlmacen.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje(" Tiene que seleccionar un almacen ");
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

        detalleAjusteInventario.setUnidad(cbUnidad.getSelectionModel().getSelectedItem().getUnidad());

        ArticuloUnidad articuloUnidad = ManejoArticuloUnidad.getInstancia()
                .getArticuloUnidadSslida(getArticulo().getCodigo(), detalleAjusteInventario.getUnidad().getCodigo());

        Double cantidadUnidad = articuloUnidad.getFatorVenta();

        Double cantidad = Double.parseDouble(txtCantidadPedida.getText());
        detalleAjusteInventario.setArticulo(getArticulo());
        detalleAjusteInventario.setCantidadAjustada(cantidad);
        detalleAjusteInventario.setDecripcionArticulo(getArticulo().getDescripcion());//   
        detalleAjusteInventario.setAlmacen( cbAlmacen.getSelectionModel().getSelectedItem().getAlmacen());

        detalleAjusteInventario.setAjusteInventario(ajusteInventario);
        detalleAjusteInventario.setCosto(0.00);

        ExistenciaArticulo exisArt = ManejoExistenciaArticulo.getInstancia()
                .getExistenciaArticulo(detalleAjusteInventario.getArticulo().getCodigo(),
                      detalleAjusteInventario.getAlmacen().getCodigo() );
        
        int almacen= detalleAjusteInventario.getAlmacen().getCodigo();
        
          Double existencia = ManejoExistenciaArticulo.getInstancia().getExistenciaArticulosPorMovimiento(getArticulo().getCodigo(), almacen);

        detalleAjusteInventario.setExistencia(existencia);
        detalleAjusteInventario.setUnidadEntrada(getArticulo().getUnidadEntrada());
        detalleAjusteInventario.setUnidadSalida(articuloUnidad.getUnidad());
        detalleAjusteInventario.setUnidad(articuloUnidad.getUnidad());

        if (rbAumento.isSelected()) {

            detalleAjusteInventario.setNuevaExistencia(existencia+(cantidad * cantidadUnidad));

        }

        if (rbDisminucion.isSelected()) {

            detalleAjusteInventario.setNuevaExistencia(existencia - (cantidad * cantidadUnidad));

        }

        txtArticulo.clear();
        txtCantidadPedida.clear();
        listaUnidads.clear();
        if (existweDetalle(detalleAjusteInventario)) {

            ClaseUtil.mensaje("Este articulo ya fue agregado");
            return;
        }

        listadetalle.add(detalleAjusteInventario);
        txtCantidad.setText(Integer.toString(listadetalle.size()));

        detalleAjusteInventario = new DetalleAjusteInventario();
    }

    @FXML
    private void cbAlmacenActionEvent(ActionEvent event) {
    }

}
