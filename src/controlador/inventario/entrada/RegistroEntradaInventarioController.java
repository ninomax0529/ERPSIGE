/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.entrada;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import controlador.compra.consulta.FXMLBusOrdenCompraController;

import controlador.inventario.articulo.FXMLBuscarArticuloController;
import controlador.suplidor.FXMLBusSuplidorController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.articulo.ManejoAlmacen;
import manejo.articulo.ManejoArticulo;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.articulo.ManejoListaDePrecio;
import manejo.contabilidadd.ConfiguracionCuentaContableDao;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.contabilidadd.TipoDocumentoDao;
import manejo.inventario.entrada.ManejoEntradaInventario;
import manejo.ordenCompra.OrdenDeCompraDao;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import manejo.suplidor.SuplidorDao;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.ConfiguracionCuentaContable;
import modelo.DetalleEntradaDiario;
import modelo.DetalleEntradaInventario;
import modelo.DetalleOrdenCompra;
import modelo.EntradaInventario;
import modelo.ExistenciaArticulo;
import modelo.OrdenCompra;
import modelo.SecuenciaDocumento;
import reporte.inventario.RptEntradaInventariOPinturaTriplea;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class RegistroEntradaInventarioController implements Initializable {

    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtOrdenCompra;
    @FXML
    private JFXTextField txtSuplidor;
    @FXML
    private JFXTextField txtNumeroFactura;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXButton btnEliminar;
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
    private TableColumn<DetalleEntradaInventario, String> tbcUnidadSalida;
    @FXML
    private TableColumn<DetalleEntradaInventario, Double> tbcPrecioVenta;
    @FXML
    private TableColumn<DetalleEntradaInventario, ?> tbcPrecioCompra11;
    @FXML
    private TableColumn<DetalleEntradaInventario, Double> tbcPrecioCompraNuevo;
    @FXML
    private TableColumn<DetalleEntradaInventario, Articulo> tbcPrecioVenta11;
    @FXML
    private TableColumn<DetalleEntradaInventario, Double> tbcPrecioVentaNuevo;
    @FXML
    private TableColumn<DetalleEntradaInventario, Double> tbcExistencia;
    @FXML
    private TableColumn<?, ?> tbcExistencia11;
    @FXML
    private TableColumn<DetalleEntradaInventario, Double> tbcExistenciaNueva;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXTextField txtNumeroEntrada;
    @FXML
    private JFXTextField txtValorTotal;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btBuscarOrden;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXTextField txtArticulo;
    @FXML
    private JFXButton btBuscarArticulo;
    @FXML
    private JFXTextField txtCantidadPedida;
    @FXML
    private JFXRadioButton rbManual;
    @FXML
    private JFXRadioButton rbConOrden;
    @FXML
    private JFXButton btnAgregarArticulo;
    @FXML
    private JFXComboBox<ArticuloUnidad> cbUnidad;
    @FXML
    private JFXComboBox<ExistenciaArticulo> cbAlmacen;

    ObservableList<DetalleEntradaInventario> listadetalle = FXCollections.observableArrayList();
    ObservableList<DetalleOrdenCompra> listadetalleOrden = FXCollections.observableArrayList();
    private OrdenCompra ordenCompra;
    EntradaInventario entradaInventario;
    DetalleEntradaInventario detalleEntradaInventario;
    ObservableList<ArticuloUnidad> listaUnidads = FXCollections.observableArrayList();
    ObservableList<ExistenciaArticulo> listaExistenciaArticulo = FXCollections.observableArrayList();

    final ToggleGroup group = new ToggleGroup();

    private Articulo articulo;
    int i = 0;
    @FXML
    private JFXButton btnBuscarSuplidor;
    @FXML
    private Label lbColumnaUnidad;
    @FXML
    private Label lbColumnaCosto;

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
        iniciarTablaDetalle();
        entradaInventario = new EntradaInventario();
        inicializarSecuencia();
        inicializarCombox();
        rbConOrden.setToggleGroup(group);
        rbManual.setToggleGroup(group);
        btBuscarOrden.setDisable(true);
        btnAgregar.setVisible(false);
        nuevo();

        txtCantidadPedida.setOnKeyReleased((event) -> {

            if (event.getCode() == KeyCode.ENTER) {

                try {

                    agregarSinOrden();

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

//    
    public void iniciarTablaDetalle() {

        listadetalle.clear();

        tbDetalleEntradaInventario.setItems(listadetalle);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcCantidadPedida.setCellValueFactory(new PropertyValueFactory<>("cantidadPedida"));
        tbcCantidadRecibida.setCellValueFactory(new PropertyValueFactory<>("cantidadRecibida"));
        tbcPrecioCompra.setCellValueFactory(new PropertyValueFactory<>("precioAnterior"));
        tbcPrecioCompraNuevo.setCellValueFactory(new PropertyValueFactory<>("costoUnitario"));
        tbcUnidadSalida.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVentaAnterior"));
        tbcPrecioVentaNuevo.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        tbcExistenciaNueva.setCellValueFactory(new PropertyValueFactory<>("existencia"));
        tbcExistencia.setCellValueFactory(new PropertyValueFactory<>("existenciaAnterior"));

        tbDetalleEntradaInventario.setEditable(true);

        tbcUnidadSalida.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {

                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
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

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

        tbcCantidadRecibida.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcCantidadRecibida.setOnEditCommit(data -> {

            try {

                DetalleEntradaInventario p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00, precioCompraUnitario = 0.00,
                        descuento = 0.00, impuesto = 0.00, precio = p.getPrecio();

                if (data.getNewValue() >= 1) {

                    if (data.getNewValue() > data.getOldValue()) {

                        p.setCantidadRecibida(data.getOldValue());
                        cantidad = p.getCantidadRecibida();
                        ClaseUtil.mensaje("La cantidad recibida "
                                + " no  puede ser mayor que la cantidad pedida" + data.getNewValue() + "-" + data.getOldValue());
                        tbDetalleEntradaInventario.refresh();
                        tbDetalleEntradaInventario.requestFocus();
                        return;

                    } else {
                        p.setCantidadRecibida(data.getNewValue());
                        cantidad = p.getCantidadRecibida();
                    }

                    subTotal = ClaseUtil.roundDouble(precio * cantidad, 2);
                    p.setValor(subTotal);
                    tbDetalleEntradaInventario.refresh();
                    tbDetalleEntradaInventario.requestFocus();
                    valorTotal();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleEntradaInventario.refresh();
                    tbDetalleEntradaInventario.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcPrecioCompraNuevo.setOnEditCommit(data -> {

            try {

                DetalleEntradaInventario p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00, precioCompraUnitario = 0.00,
                        descuento = 0.00, impuesto = 0.00, costoUnitario = p.getCostoUnitario();

                if (data.getNewValue() >= 1) {

                    cantidad = p.getCantidadRecibida();
                    p.setPrecioAnterior(costoUnitario);

                    p.setCostoUnitario(data.getNewValue());

                    subTotal = ClaseUtil.roundDouble(p.getCostoUnitario() * cantidad, 2);
////
//                    precioCompraUnitario = p.getPrecio() / p.getArticulo().getCantidadUnidades();
////
//                    precioCompraUnitario = utiles.ClaseUtil.roundDoubleSies(precioCompraUnitario, 6);
//
//                    System.out.println("Precio Unitario " + precioCompraUnitario);
////
//                    p.setCostoUnitario(precioCompraUnitario);

                    p.setValor(subTotal);

                    tbDetalleEntradaInventario.refresh();
                    tbDetalleEntradaInventario.requestFocus();
                    valorTotal();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleEntradaInventario.refresh();
                    tbDetalleEntradaInventario.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcPrecioCompraNuevo.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcPrecioVentaNuevo.setOnEditCommit(data -> {

            try {

                DetalleEntradaInventario p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00,
                        descuento = 0.00, impuesto = 0.00, precio = p.getPrecioVenta();

//                if (Objects.equals(p.getUnidad(), p.getArticulo().getUnidadEntrada())) {
//
//                    precio = p.getArticulo().getPrecioCompra() / p.getArticulo().getCantidadUnidades();
//
//                    precio = ClaseUtil.roundDouble(precio, 6);
//
//                } else if (Objects.equals(p.getUnidad(), p.getArticulo().getUnidadSalida())) {
//
//                    precio = getArticulo().getPrecioCompraUnitario();
//
//                }
                if (data.getNewValue() > precio) {

                    System.out.println("Cambiando precio " + data.getNewValue());

                    p.setPrecioVentaAnterior(p.getPrecioVentaAnterior());
                    p.setPrecioVenta(data.getNewValue());

                    System.out.println("Precio de venta asignado  " + p.getPrecioVenta());

                    tbDetalleEntradaInventario.refresh();
                    tbDetalleEntradaInventario.requestFocus();

                } else if (data.getNewValue() <= 0) {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleEntradaInventario.refresh();
                    tbDetalleEntradaInventario.requestFocus();

                } else if (data.getNewValue() < precio) {

                    ClaseUtil.mensaje("El precio de venta  no puede ser  menor que el precio de compra ");
                    tbDetalleEntradaInventario.refresh();
                    tbDetalleEntradaInventario.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcPrecioVentaNuevo.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

    }

    @FXML
    private void btBuscarOrdenActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        try {

            loader.setLocation(getClass().getResource("/vista/compra/consulta/FXMLBuscarOrdenCompra.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            Scene scene = new Scene(root);

            Stage stage = new Stage();

            stage.setScene(scene);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();

            System.out.println("ESPERANDOOO!!!");

            FXMLBusOrdenCompraController fxmlBuscarOrdenCompraController = loader.getController();

            if (!(fxmlBuscarOrdenCompraController.getOrdenCompra() == null)) {

                setOrdenCompra(fxmlBuscarOrdenCompraController.getOrdenCompra());

                txtOrdenCompra.setText(getOrdenCompra().getCodigo().toString());
                txtSuplidor.setText(getOrdenCompra().getNombreSuplidor());
                txtNumeroFactura.requestFocus();
                agregar();
                btBuscarArticulo.setDisable(true);

            } else {

                btBuscarArticulo.setDisable(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        if (tbDetalleEntradaInventario.getSelectionModel().getSelectedIndex() != -1) {

            listadetalle.remove(tbDetalleEntradaInventario.getSelectionModel().getSelectedIndex());

            valorTotal();

        }
    }

    private void tbDetalleEntradaInventarioCantidadRecibidaEditCommit(CellEditEvent edittedCell) {

        Double total = 0.00;
        DetalleEntradaInventario detalleSelected = tbDetalleEntradaInventario.getSelectionModel().getSelectedItem();
        detalleSelected.setCantidadRecibida(Double.valueOf(edittedCell.getNewValue().toString()));
        total = detalleSelected.getPrecio() * detalleSelected.getCantidadRecibida();
        detalleSelected.setValor(total);

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (txtNumeroFactura.getText().isEmpty()) {

                ClaseUtil.mensaje("No hay un numero de factura para esta entrada");
                txtNumeroFactura.requestFocus();
                return;
            }

            if (txtSuplidor.getText().isEmpty()) {

                ClaseUtil.mensaje("No hay un suplidor seleccionado  para esta entrada");
                txtSuplidor.requestFocus();
                return;
            }

            if (tbDetalleEntradaInventario.getItems().size() <= 0) {

                ClaseUtil.mensaje(" No hay articulos para realizar esta entrada ");
                return;
            }

            entradaInventario.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(entradaInventario.getUnidadDeNegocio().getCodigo(), 11);

            if (!(secDoc == null)) {

                boolean existe = ManejoEntradaInventario.getInstancia().existeSecuencia(secDoc.getNumero());

                if (existe) {

                    System.out.println("existe " + secDoc.getNumero());

                    while (ManejoEntradaInventario.getInstancia().existeSecuencia(secDoc.getNumero())) {

                        secDoc.setNumero(secDoc.getNumero() + 1);
                        ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                    }

                    secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(entradaInventario.getUnidadDeNegocio().getCodigo(), 11);

                    entradaInventario.setNumero(secDoc.getNumero());
                    entradaInventario.setSecuenciaDocumento(secDoc);

                } else {

                    System.out.println("No existe " + secDoc.getNumero());
                    entradaInventario.setNumero(secDoc.getNumero());
                    entradaInventario.setSecuenciaDocumento(secDoc);
                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

            } else {

                ClaseUtil.mensaje("La secuencia para la Enrtada de inventario de la unidad de negocio "
                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return;
            }

            entradaInventario.setAlmacen(1);
            entradaInventario.setAnulada(false);
            entradaInventario.setComentario(txtComentario.getText());
            entradaInventario.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            entradaInventario.setUsuario(VariablesGlobales.USUARIO.getCodigo());
            entradaInventario.setFechaRegistro(new Date());
            entradaInventario.setEstado("n/a");
            entradaInventario.setTipoEntrada(null);

            entradaInventario.setNumeroFactura(txtNumeroFactura.getText());
            entradaInventario.setOrdenCompra(ordenCompra);

            if (!(ordenCompra == null)) {

                entradaInventario.setSuplidor(SuplidorDao.getInstancia().getSuplidor(ordenCompra.getSuplidor()));
                entradaInventario.setNombreSuplidor(entradaInventario.getSuplidor().getDescripcion());

            } else {

                entradaInventario.setNombreSuplidor(txtSuplidor.getText());
                entradaInventario.setSuplidor(null);
            }

            entradaInventario.setDetalleEntradaInventarioCollection(listadetalle);

            EntradaInventario entrada = ManejoEntradaInventario.getInstancia().salvar(entradaInventario);
//

            if (entrada == null) {

                ClaseUtil.mensaje("Hubo un error creando la entrada ");
                return;

            } else {

                List<DetalleEntradaInventario> listaDetEntrada = ManejoEntradaInventario
                        .getInstancia().getDetalleInventario(entrada);

                List<DetalleEntradaInventario> listaDet = new ArrayList<>();

                listaDet.addAll(listaDetEntrada);

                if (listaDet.size() > 0) {

                    System.out.println(" Entrando Actualizando existencia de articulos en almacen");

                    ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorEntrada(listaDet);

                    System.out.println(" Saliendo de Actualizando existencia  de articulos en almacen");

                }

                if (rbConOrden.isSelected()) {

                    for (DetalleEntradaInventario dev : listadetalle) {

                        dev.getOrdenCompra().setEstado("Cerrada");

                        OrdenDeCompraDao.getInstancia().actualizar(dev.getOrdenCompra());
                        System.out.println("Actualizando Orden de compra No. " + dev.getOrdenCompra().getCodigo());
                    }
                }

                //
                ConfiguracionCuentaContable config = ConfiguracionCuentaContableDao.getInstancia().getConfigCuentaPorTipoConcepto(2, 2);

                if (!(config == null)) {

                    List<DetalleEntradaDiario> listaDetEntradaDiario = EntradaDiarioDao.getInstancia()
                            .generarConfiguracionCuenta(config, listaDetEntrada);

                    System.out.println("lista " + listaDetEntradaDiario);

                    EntradaDiarioDao.getInstancia()
                            .registrarEntradaDiario(entrada.getCodigo(), entrada.getCodigo().toString(), TipoDocumentoDao
                                    .getInstancia().getTipoDocumento(11).getCodigo(),
                                    config.getDescripcion(), 2, listaDetEntradaDiario, entrada.getFecha());

                }

                nuevo();

                RptEntradaInventariOPinturaTriplea.getInstancia().imprimir(entrada.getCodigo());

            }

        } catch (Exception e) {

            System.out.println("Hubo un error y No entro Actualizar existencia de articulos en almacen");
            e.printStackTrace();
        }
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

//    private void agregar() {
//
//        try {
//
//            List<DetalleEntradaInventario> lista = new ArrayList();
//            DetalleEntradaInventario detalleEntradaInventario;
//
//            //listadetalle.clear();
//            for (DetalleOrdenCompra detalleOrdenCompra : OrdenDeCompraDao.getInstancia().getDetalleOrden(getOrdenCompra().getCodigo())) {
//
//                detalleEntradaInventario = new DetalleEntradaInventario();
//                detalleEntradaInventario.setArticulo(detalleOrdenCompra.getArticulo());
//                detalleEntradaInventario.setCantidadPedida(detalleOrdenCompra.getCantidad());
//                detalleEntradaInventario.setCantidadRecibida(detalleOrdenCompra.getCantidad());
//                detalleEntradaInventario.setDescripcionArticulo(detalleOrdenCompra.getDescripcionArticulo());
//                detalleEntradaInventario.setPrecio(detalleOrdenCompra.getPrecioUnitario());
//                detalleEntradaInventario.setUnidad(detalleOrdenCompra.getArticulo().getUnidadEntrada());
//                detalleEntradaInventario.setValor(detalleOrdenCompra.getSubTotal());
//                detalleEntradaInventario.setOrdenCompra(detalleOrdenCompra.getOrdenCompra());
//                detalleEntradaInventario.setFactura(txtNumeroFactura.getText());
//                detalleEntradaInventario.setSuplidor(getOrdenCompra().getSuplidor());
//                detalleEntradaInventario.setEntradaInventario(entradaInventario);
//
//                lista.add(detalleEntradaInventario);
//
//            }
//
//            listadetalle.addAll(lista);
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//
//    }
    private void agregar() {

        try {

            List<DetalleEntradaInventario> lista = new ArrayList();
            DetalleEntradaInventario detEntrada;
            Double precio = 0.00, subTotal = 0.00;

            //listadetalle.clear();
            for (DetalleOrdenCompra det : OrdenDeCompraDao.getInstancia().getDetalleOrden(getOrdenCompra().getCodigo())) {

                detEntrada = new DetalleEntradaInventario();
                detEntrada.setArticulo(det.getArticulo());
                detEntrada.setCantidadPedida(det.getCantidad());
                detEntrada.setCantidadRecibida(det.getCantidad());
                detEntrada.setDescripcionArticulo(det.getDescripcionArticulo());
                detEntrada.setPrecio(det.getPrecioUnitario());
                detEntrada.setUnidad(det.getUnidad());
                detEntrada.setValor(det.getSubTotal());
                detEntrada.setOrdenCompra(det.getOrdenCompra());
                detEntrada.setFactura(txtNumeroFactura.getText());
                detEntrada.setSuplidor(getOrdenCompra().getSuplidor());
                detEntrada.setEntradaInventario(entradaInventario);
                detEntrada.setAlmacen(ManejoAlmacen.getInstancia().getalmacen(1));
                System.out.println("Asignando almacen  " + ManejoAlmacen.getInstancia().getalmacen(1));

                ArticuloUnidad articuloUnidad = ManejoArticuloUnidad.getInstancia()
                        .getArticuloUnidadEntrada(detEntrada.getArticulo().getCodigo(), detEntrada.getUnidad().getCodigo());

                System.out.println("articuloUnidad " + articuloUnidad);
                detEntrada.setCostoUnitario(articuloUnidad.getCostoUnitario());
                detEntrada.setPrecioAnterior(articuloUnidad.getCostoUnitario());
                precio = detEntrada.getCostoUnitario();

                subTotal = ClaseUtil.roundDouble(precio * detEntrada.getCantidadRecibida(), 2);
                detEntrada.setValor(subTotal);

                detEntrada.setPendiente(detEntrada.getCantidadPedida() - detEntrada.getCantidadRecibida());

                Double precioVenta = ManejoListaDePrecio.getInstancia()
                        .getPrecioVentaDeArticulo(detEntrada.getArticulo().getCodigo(), articuloUnidad.getUnidad().getCodigo());

                ExistenciaArticulo exisArt = ManejoExistenciaArticulo.getInstancia()
                        .getExistenciaArticulo(detEntrada.getArticulo().getCodigo(),
                                detEntrada.getArticulo().getAlmacen());

                detEntrada.setPrecioVenta(precioVenta);
                detEntrada.setPrecioVentaAnterior(precioVenta);
                detEntrada.setExistenciaAnterior(exisArt.getExistencia());

                lista.add(detEntrada);

            }

            listadetalle.addAll(lista);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void agregarSinOrden() {

        try {

            String numOrden = txtOrdenCompra.getText().isEmpty() ? "n/a" : txtOrdenCompra.getText();

            if (txtNumeroFactura.getText().isEmpty()) {

                utiles.ClaseUtil.mensaje("Tiene que digitar una factura");
                txtNumeroFactura.requestFocus();
                return;
            }

            if (txtSuplidor.getText().isEmpty()) {

                utiles.ClaseUtil.mensaje("Tiene que digitar un Suplidor ");
                txtSuplidor.requestFocus();
                return;
            }

            if (txtArticulo.getText().isEmpty()) {
                utiles.ClaseUtil.mensaje("Tiene que seleccionar un articulo");
                return;
            }

            if (cbAlmacen.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje(" Tiene que seleccionar un almacen ");
                return;
            }

//            if (cbUnidad.getSelectionModel().getSelectedIndex() == -1) {
//
//                ClaseUtil.mensaje(" Tiene que seleccionar una unidad ");
//                return;
//            }
            if (txtCantidadPedida.getText().isEmpty()) {

                utiles.ClaseUtil.mensaje(" Tiene que digital una cantidad ");
                txtCantidadPedida.requestFocus();
                return;
            }

            Double subTotal = 0.00, total = 0.00, cantidad = 0.00, precio = 0.00;

            cantidad = Double.parseDouble(txtCantidadPedida.getText());

            detalleEntradaInventario.setArticulo(getArticulo());
            detalleEntradaInventario.setCantidadPedida(cantidad);
            detalleEntradaInventario.setCantidadRecibida(cantidad);
            detalleEntradaInventario.setDescripcionArticulo(getArticulo().getDescripcion());
            detalleEntradaInventario.setUnidad(getArticulo().getUnidadEntrada());
//            detalleEntradaInventario.setUnidad(cbUnidad.getSelectionModel().getSelectedItem().getUnidad());
            detalleEntradaInventario.setAlmacen(cbAlmacen.getSelectionModel().getSelectedItem().getAlmacen());

            detalleEntradaInventario.setNumeroOrden(numOrden);
            detalleEntradaInventario.setOrdenCompra(null);
//
//            ArticuloUnidad articuloUnidad = ManejoArticuloUnidad.getInstancia()
//                    .getArticuloUnidadSslida(getArticulo().getCodigo(), detalleEntradaInventario.getUnidad().getCodigo());
//
//            Double cantidadUnidad = articuloUnidad.getFatorVenta();

//            ExistenciaArticulo exisArt = ManejoExistenciaArticulo.getInstancia()
//                    .getExistenciaArticulo(detalleEntradaInventario.getArticulo().getCodigo(),
//                            detalleEntradaInventario.getAlmacen().getCodigo());
            int almacen = detalleEntradaInventario.getAlmacen().getCodigo();

            Double existencia = ManejoExistenciaArticulo.getInstancia().getExistenciaArticulosPorMovimiento(getArticulo().getCodigo(), almacen);
            detalleEntradaInventario.setExistencia(existencia);
           

//            detalleEntradaInventario.setExistencia(existencia + (detalleEntradaInventario.getCantidadRecibida() * cantidadUnidad));
//            detalleEntradaInventario.setCostoUnitario(articuloUnidad.getCostoUnitario());
//            detalleEntradaInventario.setPrecioAnterior(articuloUnidad.getCostoUnitario());
            detalleEntradaInventario.setCostoUnitario(getArticulo().getPrecioCompra());
            detalleEntradaInventario.setPrecioAnterior(0.00);
            precio = detalleEntradaInventario.getCostoUnitario();

            subTotal = ClaseUtil.roundDouble(precio * cantidad, 2);
            detalleEntradaInventario.setValor(subTotal);
            detalleEntradaInventario.setOrdenCompra(null);
            detalleEntradaInventario.setFactura(txtNumeroFactura.getText());
            detalleEntradaInventario.setNombreSuplidor(txtSuplidor.getText());
            detalleEntradaInventario.setSuplidor(0);

            Double precioVenta = ManejoListaDePrecio.getInstancia()
                    .getPrecioVentaDeArticulo(getArticulo().getCodigo(), detalleEntradaInventario.getUnidad().getCodigo());

            detalleEntradaInventario.setPrecioVenta(precioVenta);
            detalleEntradaInventario.setPrecioVentaAnterior(precioVenta);

            detalleEntradaInventario.setEntradaInventario(entradaInventario);
            detalleEntradaInventario.setExistenciaAnterior(existencia);
//
//            Integer unidadInventario = ManejoArticuloUnidad
//                    .getInstancia().getArticuloUnidadSslida(detalleEntradaInventario.getArticulo().getCodigo()).getUnidad().getCodigo();

            detalleEntradaInventario.setUnidadSalida(getArticulo().getUnidadDeVenta());

            listadetalle.add(detalleEntradaInventario);

            txtCantidad.setText(Integer.toString(listadetalle.size()));
            valorTotal();
            txtArticulo.clear();
            txtCantidadPedida.clear();
            detalleEntradaInventario = new DetalleEntradaInventario();
            lbColumnaCosto.setText("Costo Unitario");
            lbColumnaUnidad.setText("Unidad");
            listaExistenciaArticulo.clear();
            listaUnidads.clear();
            setArticulo(null);

            i--;

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void valorTotal() {

        Double total = 0.00;
        for (DetalleEntradaInventario det : listadetalle) {

            total += ClaseUtil.roundDouble(det.getValor(), 2);
        }

        txtValorTotal.setText(total.toString());
    }

    private void actualizarExistencia() {

        try {

            for (DetalleEntradaInventario det : listadetalle) {

                ExistenciaArticulo exisArt = ManejoExistenciaArticulo.getInstancia()
                        .getExistenciaArticulo(det.getArticulo().getCodigo(), 1);

                if (exisArt != null) {

                    System.out.println("Entro actualizar"
                            + " " + exisArt.getArticulo().getCodigo() + " " + exisArt.getAlmacen().getCodigo());

                    exisArt.setExistencia(exisArt.getExistencia() + det.getCantidadRecibida());
                    exisArt.setFecha(new Date());
                    ManejoExistenciaArticulo.getInstancia().actualizar(exisArt);

                    Articulo art = exisArt.getArticulo();

                    art.setExistencia(exisArt.getExistencia());
                    ManejoArticulo.getInstancia().actualizar(art);

                } else {
                    System.out.println("No Entro actualizar ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inicializarSecuencia() {

        txtNumeroEntrada.setText(ManejoEntradaInventario.getInstancia().getNumero().toString());
    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {

        try {

            if (!(txtOrdenCompra.getText().isEmpty())) {

                agregar();
                valorTotal();
                txtCantidad.setText(Integer.toString(listadetalle.size()));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
                listaExistenciaArticulo.addAll(ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo(getArticulo().getCodigo()));
                cbUnidad.setItems(listaUnidads);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnAgregarArticuloActionEvent(ActionEvent event) {

        try {

            agregarSinOrden();
            valorTotal();
            txtCantidad.setText(Integer.toString(listadetalle.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void nuevo() {

        entradaInventario = new EntradaInventario();
        detalleEntradaInventario = new DetalleEntradaInventario();
        dpFecha.setValue(LocalDate.now());

        inicializarSecuencia();

        limpiar();
    }

    private void limpiar() {

        listadetalle.clear();
        txtCantidad.clear();
        txtComentario.clear();
        txtNumeroFactura.clear();
        txtOrdenCompra.clear();
        txtSuplidor.clear();
        txtValorTotal.clear();
        txtCantidad.clear();

    }

    @FXML
    private void cbUnidadActionEvent(ActionEvent event) {

        if (!(cbUnidad.getSelectionModel().getSelectedIndex() == -1) && getArticulo() != null) {

            String unidad = " ", costo = " ";

            if (Objects.equals(getArticulo().getUnidadSalida(), cbUnidad.getSelectionModel().getSelectedItem())) {

                unidad = "Unidad de\n Salida";
                costo = "Costo Unitario";
//
//                lbColumnaUnidad.setStyle("\n"
//                        + "    -fx-background-color: #5CB85C;\n"
//                        + "    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);\n"
//                        + "    -fx-border-color: -fx-secondary;\n"
//                        + "    -fx-text-fill:ffffff;"
//                        + "    -fx-font-size: 11pt;");
//
//                lbColumnaCosto.setStyle("\n"
//                        + "    -fx-background-color: #5CB85C;\n"
//                        + "    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);\n"
//                        + "    -fx-border-color: -fx-secondary;\n"
//                        + "    -fx-text-fill:ffffff;"
//                        + "    -fx-font-size: 11pt;");

            } else if (Objects.equals(getArticulo().getUnidadEntrada(), cbUnidad.getSelectionModel().getSelectedItem())) {

                unidad = "Unidad de \nEntrada";
                costo = "Costo";
//
//                lbColumnaUnidad.setStyle(" -fx-background-color: #5CB85C; "
//                        + "-fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C);"
//                        + "    -fx-border-color: -fx-secondary;\n"
//                        + "-fx-text-fill:ffffff;"
//                        // + "    -fx-background-radius: 15px;\n"
//                        + "    -fx-font-size: 11pt;");
//
//                lbColumnaCosto.setStyle(" -fx-background-color: #5CB85C; "
//                        + "-fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C);"
//                        + "    -fx-border-color: -fx-secondary;\n"
//                        + "-fx-text-fill:ffffff;"
//                        // + "    -fx-background-radius: 15px;\n"
//                        + "    -fx-font-size: 11pt;");

            }

            lbColumnaUnidad.setText(unidad);
            lbColumnaCosto.setText(costo);
//            String unidad=cbUnidad.getSelectionModel().getSelectedItem().getDescripcion();
//            lbColumnaUnidad.setText(unidad);
            System.out.println("Unidad " + lbColumnaUnidad.getText());

            txtCantidadPedida.requestFocus();
        }

    }

    @FXML
    private void btnBuscarSuplidorActionevent(ActionEvent event) throws IOException {

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

            txtSuplidor.setText(suplidorController.getSuplidor().getDescripcion());

        }
    }

    @FXML
    private void rbConOrdenDeCompraMouseClicked(MouseEvent event) {

        if (rbConOrden.isSelected()) {

            btBuscarOrden.setDisable(false);
            btnEliminar.setDisable(true);
            btnBuscarSuplidor.setDisable(true);
            btnAgregarArticulo.setDisable(true);
            btBuscarArticulo.setDisable(true);

        } else {

            btnBuscarSuplidor.setDisable(false);
            btBuscarOrden.setDisable(true);
            btnEliminar.setDisable(false);
            btnAgregarArticulo.setDisable(false);
            btBuscarArticulo.setDisable(false);
        }
    }

    @FXML
    private void rbEntradaManualMouseClicked(MouseEvent event) {

        if (rbManual.isSelected()) {

            btBuscarOrden.setDisable(true);
            btnEliminar.setDisable(false);
            btnBuscarSuplidor.setDisable(false);
            btnAgregarArticulo.setDisable(false);
            btBuscarArticulo.setDisable(false);
        } else {
            btBuscarOrden.setDisable(false);
            btnEliminar.setDisable(true);
            btnBuscarSuplidor.setDisable(true);
            btnAgregarArticulo.setDisable(true);
            btBuscarArticulo.setDisable(true);
        }

    }

    @FXML
    private void cbAlmacenActionEvent(ActionEvent event) {
    }

}
