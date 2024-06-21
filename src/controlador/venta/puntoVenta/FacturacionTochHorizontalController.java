/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.puntoVenta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import controlador.cxc.reciboIngreso.RegistroReciboDeIngresoController;
import controlador.inventario.articulo.RegistroArticuloController;
import controlador.venta.cliente.FXMLBusClienterController;
import controlador.venta.cotizacion.BuscarCotizacionDeVentaController;
import controlador.venta.descuento.UsuarioDescuentoController;
import controlador.venta.vendedor.BuscarVendedorController;
import dto.articulo.ArticuloDTO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import manejo.ManejoConfiguracion;
import manejo.articulo.ManejoAlmacen;
import manejo.articulo.ManejoArticulo;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoCategoria;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.articulo.ManejoListaDePrecio;
import manejo.articulo.ManejoSubCategoria;
import manejo.caja.ManejoCajaChica;
import manejo.caja.ManejoTipoMovimieto;
import manejo.cliente.ManejoCliente;
import manejo.cliente.ManejoPlazo;
import manejo.contabilidadd.ConfiguracionCuentaContableDao;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.contabilidadd.TipoDocumentoDao;
import manejo.cotizacionDeVenta.ManejoCotizacionDeVenta;
import manejo.documento.ManejoTipodocumento;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoFormaPago;
import manejo.factura.ManejoHistoricoBalancePendiente;
import manejo.factura.ManejoOrigenFactura;
import manejo.factura.ManejoRelacionNcf;
import manejo.factura.ManejoTipoDeIngreso;
import manejo.factura.ManejoTipoFormaPago;
import manejo.factura.ManejoTipoNcf;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import manejo.unidad.ManejoUnidad;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.CajaChica;
import modelo.Categoria;
import modelo.Cliente;
import modelo.CondicionPago;
import modelo.ConfiguracionCuentaContable;
import modelo.CotizacionDeVenta;
import modelo.DescuentoPorUsuario;
import modelo.DetalleCajaChica;
import modelo.DetalleConfiguaracionCuentaContable;
import modelo.DetalleCotizacionDeVenta;
import modelo.DetalleEntradaDiario;
import modelo.DetalleFactura;
import modelo.DetalleListaDePrecio;
import modelo.EjecutivoDeVenta;
import modelo.ExistenciaArticulo;
import modelo.Factura;
import modelo.FormaPago;
import modelo.HistoricoBalancePendiente;
import modelo.MenuModulo;
import modelo.RelacionNcf;
import modelo.RolMenuModulo;
import modelo.SecuenciaDocumento;
import modelo.SubCategoria;
import modelo.TipoDeIngreso;
import modelo.TipoFormaPago;
import modelo.TipoNcf;
import reporte.unidadnegocio.RptFactura;
import util.GUIUtils;
import util.RetencionDGII;

import utiles.ClaseUtil;
import utiles.FormatNum;
import utiles.VariablesGlobales;
import vista.venta.facturacion.FormaPagoController;
import vista.venta.facturacion.TecladoDigitalController;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FacturacionTochHorizontalController implements Initializable {

//    private ScrollPane scpane;
    private GridPane gpArticulo = new GridPane();
    ObservableList<Button> listaArticulo = FXCollections.observableArrayList();
    @FXML
    private TableView<DetalleFactura> tbDetalleFactura;
    @FXML
    private TableColumn<DetalleFactura, String> tbcArticulo;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcCantidad;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcPrecioUnitario;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcImporte;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcDescuento;
    @FXML
    private TableColumn<DetalleFactura, String> tbcUnidadSalida;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcItbis;
    @FXML
    private TableView<Articulo> tbArticulo;
    @FXML
    private TableColumn<Articulo, Articulo> tbcImagenArticulo;
    @FXML
    private JFXTextField txtSubTotal;
    @FXML
    private JFXTextField txtDescuento;
    @FXML
    private JFXTextField txtItbis;
    @FXML
    private JFXTextField txtTotal;
    FacturacionTochHorizontalController facturacionTochController;
    @FXML
    private JFXTextField txtCodigoArticulo;
    @FXML
    private Button btnBuscarArticulo;
    @FXML
    private JFXComboBox<TipoNcf> cbTipoNcf;

    @FXML
    private VBox VbSubCate;
    int unidadDespacho = 1;

//    @FXML
//    private Label lbCategoriaSelecionada;
    private Label lbCategoria1;
    private Label lbProgreso;
    private JFXProgressBar pgBar;
    private ProgressIndicator pgIndicador;
    @FXML
    private HBox hbTipoVenta;
    @FXML
    private VBox vbCategoriaArticulo;
    @FXML
    private JFXButton btnCategoria;
    @FXML
    private Label lbCategoriaSeleccionada;
    @FXML
    private VBox vbMenu;
    @FXML
    private VBox vbMenuArticulo;
    @FXML
    private HBox vhMenuArticulo;
    @FXML
    private JFXButton btnBuscarCotizacion;
    @FXML
    private JFXButton btnDescuento;
    @FXML
    private VBox vbDescuento;
    @FXML
    private JFXTextField txtPorcientoMinimo;
    @FXML
    private JFXTextField txtPorcientoMaximo;
    @FXML
    private JFXTextField txtPorciento;
    @FXML
    private JFXButton btnAplicarDesc;
    @FXML
    private JFXTextField txtVendedor;
    @FXML
    private JFXButton btnVendedor;
    @FXML
    private JFXComboBox<TipoDeIngreso> cbTipoDeIngreso;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcMontoExcento;
    @FXML
    private JFXTextField txtMontoExcento;
    @FXML
    private JFXTextField txtMontoGravado;
    @FXML
    private JFXTextArea txtConcepto;
    @FXML
    private JFXButton btnReciboIngreso;

    public FacturacionTochHorizontalController getFacturacionTochController() {
        return facturacionTochController;
    }

    public void setFacturacionTochController(FacturacionTochHorizontalController facturacionTochController) {
        this.facturacionTochController = facturacionTochController;
    }

    ObservableList<DetalleFactura> listadetalleFactura = FXCollections.observableArrayList();
    ObservableList<Articulo> listaArticulos = FXCollections.observableArrayList();
    ObservableList<TipoNcf> listaTipoNcf = FXCollections.observableArrayList();
    ObservableList<String> listaTipoVenta = FXCollections.observableArrayList();
    ObservableList<TipoDeIngreso> listaTipoIngreso = FXCollections.observableArrayList();

    Double totalPago;

    int tipoVenta = 2;// El valor uno es venta de contado y el dos es venta a credito ;
    int tipoNcf = 2;// el valor uno sustentan costo y gasto y el dos consumidor final;
    public List<FormaPago> listFormaPagoCollection;

    public List<FormaPago> getListFormaPagoCollection() {
        return listFormaPagoCollection;
    }

    public void setListFormaPagoCollection(List<FormaPago> listFormaPagoCollection) {
        this.listFormaPagoCollection = listFormaPagoCollection;
    }
    RelacionNcf relacionNcf;
    Double devuelta;
    int CodigoFactura;

    Cliente cliente;
    Factura factura;
    Factura facturaDb = null;
    boolean tieneMontoExcento = false;
    DetalleFactura detalleFactura;
    FormaPago formaPago;
    Articulo articulo;
    @FXML
    private JFXTextField txtNumeroFactura;
    @FXML
    private JFXTextField txtNcf;
    @FXML
    private JFXTextField txtNombreCliente;
    @FXML
    private JFXButton btnBuscarCliente;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnFormaPago;
    private JFXButton btnGuardar;

    public JFXButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JFXButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }
    @FXML
    private JFXButton btnImprimir;
    @FXML
    private JFXButton btnEliminar;
    private TextField txtCAtidad;

    Integer cantidad = 0;
    Double catidadArticulo = 0.0;

    Double existenciaEnPeso = 0.00;
    String numStr;
    @FXML
    private JFXButton btnDeContado;
    @FXML
    private JFXButton btnCredito;
    @FXML
    private Label lbTipoVenta;

    ArticuloDTO articuloDTO;
    Double precioVenta = 0.00;
    CotizacionDeVenta contiDeVenta;
    DescuentoPorUsuario descuentoPorUsuario;
    EjecutivoDeVenta ejecutivoDeVenta;

    public EjecutivoDeVenta getEjecutivoDeVenta() {
        return ejecutivoDeVenta;
    }

    public void setEjecutivoDeVenta(EjecutivoDeVenta ejecutivoDeVenta) {
        this.ejecutivoDeVenta = ejecutivoDeVenta;
    }

    public DescuentoPorUsuario getDescuentoPorUsuario() {
        return descuentoPorUsuario;
    }

    public void setDescuentoPorUsuario(DescuentoPorUsuario descuentoPorUsuario) {
        this.descuentoPorUsuario = descuentoPorUsuario;
    }

    public CotizacionDeVenta getContiDeVenta() {
        return contiDeVenta;
    }

    public void setContiDeVenta(CotizacionDeVenta contiDeVenta) {
        this.contiDeVenta = contiDeVenta;
    }

    AnchorPane panelFormaPago;
//    FormaPagoController formaDePagoController;

    FormaPagoController formaDePagoController;

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getDevuelta() {
        return devuelta;
    }

    public void setDevuelta(Double devuelta) {
        this.devuelta = devuelta;
    }

    /**
     * Initializes the controller class.
     */
    int codigoRol = utiles.VariablesGlobales.USUARIO.getRol().getCodigo();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        activacionPuntuales(true);
        agregarOpciones();
        activarOpciones();

//        agregarOpcionesDeArticulo();
//        activarOpcionesDeArticulo();
        nuevo();
        vbCategoriaArticulo.setPrefWidth(0);
        vbCategoriaArticulo.setVisible(false);

        inicializarTabla();
        iniciazarFiltro();
        inicializarDatos();
        inicializarCombox();
        vbDescuento.setVisible(false);

        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                txtCodigoArticulo.requestFocus();

            }
        });
//        taskMarcarProbetaParaRotura.run();
        txtCodigoArticulo.setOnKeyPressed(value -> {

            if (!txtCodigoArticulo.getText().isEmpty()) {

                if (value.getCode() == KeyCode.ENTER) {

                    List<Articulo> lista = ManejoArticulo.getInstancia().getListaArticulo(txtCodigoArticulo.getText());

                    crearArticuloPorBusqueda(lista);
                    txtCodigoArticulo.clear();
                    txtCodigoArticulo.requestFocus();
                }

            }

        });

        txtPorciento.setOnAction(ev -> {

            try {

                aplicarDescuento();

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    private void inicializarCombox() {

        listaTipoNcf.addAll(ManejoTipoNcf.getInstancia().getListaTipoNcf("venta"));

        listaTipoIngreso.addAll(ManejoTipoDeIngreso.getInstancia().getListaTipoDeIngreso());

        cbTipoNcf.setItems(listaTipoNcf);
        cbTipoDeIngreso.setItems(listaTipoIngreso);
        cbTipoDeIngreso.getSelectionModel().selectFirst();
        cbTipoNcf.setConverter(new StringConverter<TipoNcf>() {

            @Override
            public String toString(TipoNcf unidad) {
                return unidad.getDescripcion();
            }

            @Override
            public TipoNcf fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoDeIngreso.setConverter(new StringConverter<TipoDeIngreso>() {

            @Override
            public String toString(TipoDeIngreso tipo) {
                return tipo.getNombre();
            }

            @Override
            public TipoDeIngreso fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

    }

    private void iniciazarFiltro() {

        FilteredList<Articulo> filteredData = new FilteredList<>(tbArticulo.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtCodigoArticulo.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(articuloFiltro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (articuloFiltro.getNumero() != null && articuloFiltro.getNumero().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (articuloFiltro.getNombre() != null && articuloFiltro.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
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

    public void inicializarTabla() {

        listadetalleFactura.clear();
        listaArticulos.clear();

        tbDetalleFactura.setItems(listadetalleFactura);

        tbcArticulo.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcUnidadSalida.setCellValueFactory(new PropertyValueFactory<>("unidadSalida"));
        tbcImporte.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        tbcDescuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        tbcItbis.setCellValueFactory(new PropertyValueFactory<>("itbis"));
        tbcMontoExcento.setCellValueFactory(new PropertyValueFactory<>("montoExcento"));

        tbcPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tbcImagenArticulo.setCellValueFactory(new PropertyValueFactory<>("numero"));

        listaArticulos.addAll(ManejoArticulo.getInstancia().getListaArticuloPorUnidadDeNegocio());

        tbArticulo.setItems(listaArticulos);
        GUIUtils.autoFitTable(tbArticulo);

        tbDetalleFactura.setEditable(true);

        tbcArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getNumero() + "-" + cellData.getValue().getNombreArticulo());
                    }
                    return property;
                });

        tbcUnidadSalida.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

        tbcImagenArticulo.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });
//Seleccionar articulo
        tbcImagenArticulo.setCellFactory((TableColumn<Articulo, Articulo> param) -> {

            TableCell<Articulo, Articulo> cellsc = new TableCell<Articulo, Articulo>() {
                @Override
                public void updateItem(Articulo item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item != null) {

                        VBox hbox = visualizarArticulo(item);

                        HBox bmas = (HBox) hbox.getChildren().get(2);
                        JFXButton btnSumar = (JFXButton) bmas.getChildren().get(0);
                        JFXButton btnRestar = (JFXButton) bmas.getChildren().get(1);
                        JFXButton btnTeclado = (JFXButton) bmas.getChildren().get(2);
//
//                        System.out.println("btnTeclado " + btnTeclado.getId());
                        btnTeclado.setOnMouseClicked((event) -> {

                            setArticulo(item);

                            try {

                                FXMLLoader loader = new FXMLLoader();

                                loader.setLocation(getClass().getResource("/vista/venta/facturacion/TecladoDigital.fxml"));
                                loader.load();

                                Parent root = loader.getRoot();
                                TecladoDigitalController tecladoDigitalController = loader.getController();

                                tecladoDigitalController.setArticulo(getArticulo());
                                tecladoDigitalController.llenarCampo();
                                tecladoDigitalController.getTxtCAtidad().requestFocus();

                                ClaseUtil.getStageModal(root);

                                unidadDespacho = tecladoDigitalController.getUnidadDespacho();

                                System.out.println("Unidad despacho " + unidadDespacho);

                                if (!(getArticulo() == null) && !tecladoDigitalController.getTxtCAtidad().getText().isEmpty()) {

                                    catidadArticulo = Double.parseDouble(tecladoDigitalController.getTxtCAtidad().getText());

                                    int unidad = getArticulo().getUnidadEntrada().getCodigo();  //tecladoDigitalController.getArticuloDTO().getCodigoUnidad();
                                    int listaPrecio = 4; // tecladoDigitalController.getArticuloDTO().getListaDePrecio();
                                    int almacen = 6; //tecladoDigitalController.getArticuloDTO().getAlmacen();
                                    String nombreUnidad = getArticulo().getUnidadEntrada().getDescripcion(); // tecladoDigitalController.getArticuloDTO().getUnidad();
                                    precioVenta = getArticulo().getPrecioVenta1();// tecladoDigitalController.getArticuloDTO().getPrecioVenta();

                                    System.out.println("Precio de Venta  " + precioVenta + " lista precio  " + listaPrecio);
                                    Double existencia = ManejoExistenciaArticulo.getInstancia()
                                            .getExistenciaArticulosPorMovimiento(getArticulo().getCodigo(), 6);
                                    //tecladoDigitalController.getArticuloDTO().getExistencia();

                                    existencia = FormatNum.FormatearDouble(existencia, 2);

                                    System.out.println("existencia < existenciaEnPeso " + existencia + " " + existenciaEnPeso);

                                    if (unidadDespacho == 2) {

                                        existenciaEnPeso = FormatNum
                                                .FormatearDouble(catidadArticulo / getArticulo()
                                                        .getPrecioVenta1(), 4);
                                        System.out.println("Cantidad en peso " + catidadArticulo);

                                    } else {
                                        existenciaEnPeso = catidadArticulo;
                                    }

                                    if (existencia < existenciaEnPeso && !(getArticulo().getTipoArticulo().getCodigo() == 3)) {

                                        ClaseUtil.mensaje(" La cantidad a Despachar " + catidadArticulo
                                                + "  no puede ser mayor que la existencia ,"
                                                + " Existencia igual a  " + existencia + "   " + nombreUnidad);

                                        return;
                                    }

                                    if (!(getContiDeVenta() == null)) {
                                        ClaseUtil.mensaje("Esta factura esta con una cotizacion");
                                        return;
                                    }

                                    agregarArticuloConPrecioDeLista(unidad, almacen, listaPrecio);

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        });

                        btnSumar.setOnMouseClicked((event) -> {

                            setArticulo(item);

                            try {

                                unidadDespacho = 1; //tecladoDigitalController.getUnidadDespacho();

                                System.out.println("Unidad despacho " + unidadDespacho);

//                                if (!(getArticulo() == null) && !tecladoDigitalController.getTxtCAtidad().getText().isEmpty()) {
                                catidadArticulo = 1.0;// Double.parseDouble(tecladoDigitalController.getTxtCAtidad().getText());

                                int unidad = getArticulo().getUnidadEntrada().getCodigo();  //tecladoDigitalController.getArticuloDTO().getCodigoUnidad();
                                int listaPrecio = 4; // tecladoDigitalController.getArticuloDTO().getListaDePrecio();
                                int almacen = 6; //tecladoDigitalController.getArticuloDTO().getAlmacen();
                                String nombreUnidad = getArticulo().getUnidadEntrada().getDescripcion(); // tecladoDigitalController.getArticuloDTO().getUnidad();
                                precioVenta = getArticulo().getPrecioVenta1();// tecladoDigitalController.getArticuloDTO().getPrecioVenta();

                                System.out.println("Precio de Venta  " + precioVenta + " lista precio  " + listaPrecio);
                                Double existencia = ManejoExistenciaArticulo.getInstancia()
                                        .getExistenciaArticulosPorMovimiento(getArticulo().getCodigo(), 6);
                                //tecladoDigitalController.getArticuloDTO().getExistencia();

                                existencia = FormatNum.FormatearDouble(existencia, 2);

                                System.out.println("existencia < existenciaEnPeso " + existencia + " " + existenciaEnPeso);

                                if (unidadDespacho == 2) {

                                    existenciaEnPeso = FormatNum
                                            .FormatearDouble(catidadArticulo / getArticulo()
                                                    .getPrecioVenta1(), 4);
                                    System.out.println("Cantidad en peso " + catidadArticulo);

                                } else {
                                    existenciaEnPeso = catidadArticulo;
                                }

                                if (existencia < existenciaEnPeso && !(getArticulo().getTipoArticulo().getCodigo() == 3)) {

                                    ClaseUtil.mensaje(" La cantidad a Despachar " + catidadArticulo
                                            + "  no puede ser mayor que la existencia ,"
                                            + " Existencia igual a  " + existencia + "   " + nombreUnidad);

                                    return;
                                }

                                if (!(getContiDeVenta() == null)) {
                                    ClaseUtil.mensaje("Esta factura esta con una cotizacion");
                                    return;
                                }

                                agregarArticuloConPrecioDeLista(unidad, almacen, listaPrecio);

//                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        });

                        btnRestar.setOnMouseClicked((event) -> {

                            setArticulo(item);

                            try {

                                unidadDespacho = 1; //tecladoDigitalController.getUnidadDespacho();

                                System.out.println("Unidad despacho " + unidadDespacho);

//                                if (!(getArticulo() == null) && !tecladoDigitalController.getTxtCAtidad().getText().isEmpty()) {
                                catidadArticulo = -1.0;// Double.parseDouble(tecladoDigitalController.getTxtCAtidad().getText());

                                int unidad = getArticulo().getUnidadEntrada().getCodigo();  //tecladoDigitalController.getArticuloDTO().getCodigoUnidad();
                                int listaPrecio = 4; // tecladoDigitalController.getArticuloDTO().getListaDePrecio();
                                int almacen = 6; //tecladoDigitalController.getArticuloDTO().getAlmacen();
                                String nombreUnidad = getArticulo().getUnidadEntrada().getDescripcion(); // tecladoDigitalController.getArticuloDTO().getUnidad();
                                precioVenta = getArticulo().getPrecioVenta1();// tecladoDigitalController.getArticuloDTO().getPrecioVenta();

                                System.out.println("Precio de Venta  " + precioVenta + " lista precio  " + listaPrecio);
                                Double existencia = ManejoExistenciaArticulo.getInstancia()
                                        .getExistenciaArticulosPorMovimiento(getArticulo().getCodigo(), 6);
                                //tecladoDigitalController.getArticuloDTO().getExistencia();

                                existencia = FormatNum.FormatearDouble(existencia, 2);

                                System.out.println("existencia < existenciaEnPeso " + existencia + " " + existenciaEnPeso);

                                if (unidadDespacho == 2) {

                                    existenciaEnPeso = FormatNum
                                            .FormatearDouble(catidadArticulo / getArticulo()
                                                    .getPrecioVenta1(), 4);
                                    System.out.println("Cantidad en peso " + catidadArticulo);

                                } else {
                                    existenciaEnPeso = catidadArticulo;
                                }

                                if (existencia < existenciaEnPeso && !(getArticulo().getTipoArticulo().getCodigo() == 3)) {

                                    ClaseUtil.mensaje(" La cantidad a Despachar " + catidadArticulo
                                            + "  no puede ser mayor que la existencia ,"
                                            + " Existencia igual a  " + existencia + "   " + nombreUnidad);

                                    return;
                                }

                                if (!(getContiDeVenta() == null)) {
                                    ClaseUtil.mensaje("Esta factura esta con una cotizacion");
                                    return;
                                }

                                agregarArticuloConPrecioDeLista(unidad, almacen, listaPrecio);

//                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        setGraphic(hbox);
                        setText(null);
                    } else {
                        setGraphic(null);
                        setText(null);
                    }
                }
            };

            return cellsc;
        });

//        tbcDescuento.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
//            @Override
//            public String toString(Double object) {
//
//                return object.toString();
//            }
//
//            @Override
//            public Double fromString(String string) {
//
//                return Double.parseDouble(string);
//
//            }
//
//        }));
//        tbcDescuento.setOnEditCommit(data -> {
//
//            try {
//
//                DetalleFactura p = data.getRowValue();
//                Double subTotal = 0.00, total = 0.00, cantidad = 0.00, precioCompraUnitario = 0.00,
//                        descuento = 0.00, impuesto = 0.00, precio = p.getPrecio();
//
//                if (data.getNewValue() >= 0) {
//
//                    p.setDescuento(data.getNewValue());
//
//                    System.out.println("Dscuento " + descuento + " Precio ant " + precio + " Precio nuevo " + p.getPrecio());
//                    editarDetalleArticulo(p);
//                    tbDetalleFactura.refresh();
//                    tbDetalleFactura.requestFocus();
//
//                } else {
//
//                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
//                    tbDetalleFactura.refresh();
//                    tbDetalleFactura.requestFocus();
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        });
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

        tbcMontoExcento.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object == null ? "0" : object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcMontoExcento.setOnEditCommit(data -> {

            try {

                DetalleFactura p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00, precioCompraUnitario = 0.00,
                        descuento = 0.00, impuesto = 0.00, precio = p.getPrecio(), valorImpuesto = 0.00;

                Double existencia = ManejoExistenciaArticulo
                        .getInstancia()
                        .getExistenciaArticulo(p.getArticulo().getCodigo(), 1, p.getUnidad().getCodigo(), p.getListaDePrecio());

                if (data.getNewValue() >= 0) {

                    p.setMontoExcento(data.getNewValue());
                    p.setMontoGravado(p.getSubTotal() - p.getMontoExcento());
                    valorImpuesto = util.FormatNum.FormatearDouble((p.getMontoGravado()) * (impuesto / 100), 2);
                    p.setMontoItbisExcento(p.getItbis() - valorImpuesto);

                    editarDetalleArticulo(p);
                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser  menor que cero");
                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcCantidad.setOnEditCommit(data -> {

            try {

                DetalleFactura p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00, precioCompraUnitario = 0.00,
                        descuento = 0.00, impuesto = 0.00, precio = p.getPrecio(),
                        existencia = ManejoExistenciaArticulo.getInstancia()
                                .getExistenciaArticulosPorMovimiento(p.getArticulo().getCodigo(), p.getAlmacen().getCodigo());

                if (data.getNewValue() > 0) {

                    if (existencia < data.getNewValue() && p.getArticulo().getTipoArticulo().getCodigo() != 3) {

                        p.setCantidad(data.getOldValue());
                        ClaseUtil.mensaje(" La cantidad a Despachar  " + data.getNewValue()
                                + "  no puede ser mayor que la existencia ,"
                                + " Existencia igual a  " + existencia);

                        tbDetalleFactura.refresh();
                        tbDetalleFactura.requestFocus();
                        return;
                    }

                    p.setCantidad(data.getNewValue());

                    editarDetalleArticulo(p);
                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    private void crearArticulo(int categoria) {

//        ImageView img = null;
//        Button btn;
////        VBox vb = new VBox();
//
////        vb.getChildren().add(new HBox());
//        List<Articulo> lista = ManejoArticulo.getInstancia().getArticuloPorCategoria(categoria);
//
//        gpArticulo.getChildren().clear();
//
//        File imageFile;
//        int num = 0;
//        for (int x = 0; x < lista.size(); x++) {
//
//            for (int y = 0; y < 3; y++) {
//
//                if (num < lista.size()) {
//
//                    if (lista.get(num).getRutaImg() == null) {
//
//                        imageFile = new File("foto/img_articulo.jpg");
//
//                    } else {
//
//                        imageFile = new File(lista.get(num).getRutaImg());
//                    }
////                    File imageFile = new File(lista.get(num).getRutaImg());
//
//                    img = new ImageView(new Image(imageFile.toURI().toString(), 130, 100, false, false));
//                    img.setFitWidth(130);
//                    img.setFitHeight(100);
//                    Label lbNombre = new Label();
//
//                    lbNombre.setStyle(" -fx-text-fill: #ffffff;"
//                            + " -fx-font-size: 14pt;");
//
//                    VBox vb = new VBox();
//                    vb.setAlignment(Pos.TOP_LEFT);
//                    vb.setStyle("   -fx-text-fill:000000;"
//                            + " -fx-background-color: #394B52;"
//                            + "    -fx-border-color:  #00bb2d;\n"
//                            + "    -fx-border-radius: 5px;\n"
//                            + "    -fx-background-radius: 10px;\n"
//                            + "    -fx-font-size: 12pt;");
//
////                    btn = new Button(lista.get(num).getNombre(), img);
////                    btn.setStyle("-fx-base: coral;");
////                    btn.setContentDisplay(ContentDisplay.TOP);
//                    vb.setId(Integer.toString(lista.get(num).getCodigo()));
//                    vb.setAccessibleHelp(lista.get(num).getNombre());
//
//                    vb.setPrefSize(140, 120);
//                    vb.setMinWidth(140);
//                    vb.setMaxWidth(140);
//                    vb.getChildren().add(img);
//                    vb.getChildren().add(lbNombre);
//
//                    GridPane.setConstraints(vb, y, x);
//
//                    gpArticulo.add(vb, y, x);
//
//                }
//
//                num++;
//
//            }
//
//        }
//
//        scpane.setContent(gpArticulo);
//        visualizarArticulo();
        List<Articulo> lista = ManejoArticulo.getInstancia().getArticuloPorCategoria(categoria);
        listaArticulos.clear();
        listaArticulos.addAll(lista);
//
//        gpArticulo.getChildren().clear();
//
//        dibujarArticulo(lista, gpArticulo);

//        scpane.setContent(gpArticulo);
//        visualizarArticulo();
    }

    private void crearArticulo() {

        List<Articulo> lista = ManejoArticulo.getInstancia().getArticuloPorCategoria(1);
        listaArticulos.clear();
        listaArticulos.addAll(lista);
//
//        gpArticulo.getChildren().clear();
//
//        dibujarArticulo(lista, gpArticulo);
//
////        scpane.setContent(gpArticulo);
//        visualizarArticulo();

    }

    private void dibujarArticulo(List<Articulo> lista, GridPane gpArticulo) {

        ImageView img = null;
        File imageFile;
        int num = 0;

        for (int x = 0; x < lista.size(); x++) {

            for (int y = 0; y < 1; y++) {

                if (num < lista.size()) {

                    if (lista.get(num).getRutaImg() == null) {

                        imageFile = new File("foto/img_articulo.jpg");

                    } else {

                        imageFile = new File(lista.get(num).getRutaImg());
                    }
//                    File imageFile = new File(lista.get(num).getRutaImg());

                    img = new ImageView(new Image(imageFile.toURI().toString(), 130, 100, false, false));
                    img.setFitWidth(90);
                    img.setFitHeight(70);
//                    img.setStyle(" -fx-border-radius: 5px;");

                    VBox vb = new VBox();
                    vb.setAlignment(Pos.CENTER_LEFT);

                    HBox hb = new HBox();

                    hb.setAlignment(Pos.CENTER_LEFT);
//                    hb.setMaxWidth(Double.MAX_VALUE);
//                    vb.setMaxWidth(Double.MAX_VALUE);
//                    hb.setSpacing(5);

                    vb.setSpacing(5);
                    vb.setPadding(new Insets(5, 5, 5, 5));

                    hb.setStyle("   -fx-text-fill:000000;\n"
                            + " -fx-background-color: #FFFFFF;"
                            + "    -fx-border-color:  #00bb2d;\n"
                            + "    -fx-border-radius: 5px;\n"
                            + "    -fx-background-radius: 10px;\n"
                            + "    -fx-font-size: 12pt;");

                    Label lbCodio = new Label("Codigo  :  " + lista.get(num).getCodigo().toString());
                    Label lbPrecio = new Label("Precio :  " + Double.toString(lista.get(num).getPrecioVenta1()) + "  $ ");
                    Label lbNombre = new Label("Nombre :  " + lista.get(num).getNombre());

                    lbCodio.setStyle(" -fx-text-fill: #000000;"
                            + " -fx-font-size: 13pt;");
                    lbPrecio.setStyle(" -fx-text-fill: #000000;"
                            + " -fx-font-size: 13pt;");
                    lbNombre.setStyle(" -fx-text-fill: #000000;"
                            + " -fx-font-size: 13pt;");

                    vb.getChildren().add(lbCodio);

                    vb.getChildren().add(lbNombre);

                    vb.getChildren().add(lbPrecio);

                    hb.getChildren().add(img);
                    hb.getChildren().add(vb);

                    hb.setId(Integer.toString(lista.get(num).getCodigo()));
                    hb.setCursor(Cursor.HAND);

                    GridPane.setConstraints(hb, y, x);
                    gpArticulo.add(hb, y, x);// y indice de la columna,x indice de la fila 

                }

                num++;

            }

        }

    }

    private void crearArticuloPorBusqueda(List<Articulo> lista) {

        gpArticulo.getChildren().clear();

        dibujarArticulo(lista, gpArticulo);

//        scpane.setContent(gpArticulo);
        visualizarArticulo();

//        ImageView img = null;
//        Button btn;
//        File imageFile;
//
//        gpArticulo.getChildren().clear();
//        int num = 0;
//        for (int x = 0; x < lista.size(); x++) {
//
//            for (int y = 0; y < 3; y++) {
//
//                if (num < lista.size()) {
//
//                    if (lista.get(num).getRutaImg() == null) {
//
//                        imageFile = new File("foto/img_articulo.jpg");
//
//                    } else {
//
//                        imageFile = new File(lista.get(num).getRutaImg());
//                    }
////                    File imageFile = new File(lista.get(num).getRutaImg());
//
//                    img = new ImageView(new Image(imageFile.toURI().toString(), 130, 100, false, false));
//                    img.setFitWidth(130);
//                    img.setFitHeight(100);
//                    Label lbNombre = new Label();
//
//                    lbNombre.setStyle(" -fx-text-fill: #ffffff;"
//                            + " -fx-font-size: 14pt;");
//
//                    VBox vb = new VBox();
//                    vb.setAlignment(Pos.TOP_LEFT);
//                    vb.setStyle("   -fx-text-fill:000000;"
//                            + " -fx-background-color: #394B52;"
//                            + "    -fx-border-color:  #00bb2d;\n"
//                            + "    -fx-border-radius: 5px;\n"
//                            + "    -fx-background-radius: 10px;\n"
//                            + "    -fx-font-size: 12pt;");
//
////                    btn = new Button(lista.get(num).getNombre(), img);
////                    btn.setStyle("-fx-base: coral;");
////                    btn.setContentDisplay(ContentDisplay.TOP);
//                    vb.setId(Integer.toString(lista.get(num).getCodigo()));
//                    vb.setAccessibleHelp(lista.get(num).getNombre());
//
//                    vb.setPrefSize(140, 120);
//                    vb.setMinWidth(140);
//                    vb.setMaxWidth(140);
//                    vb.getChildren().add(img);
//                    vb.getChildren().add(lbNombre);
//
//                    GridPane.setConstraints(vb, y, x);
//
//                    gpArticulo.add(vb, y, x);
//
//                }
//
//                num++;
//
//            }
//
//        }
//
//        scpane.setContent(gpArticulo);
//
//        visualizarArticulo();
    }

    private void crearArticuloPorSubCategoria(int subCategoria) {

        listaArticulos.clear();
        listaArticulos.addAll(ManejoArticulo.getInstancia().getArticuloPorSubCategoria(subCategoria));
        tbArticulo.refresh();
//        gpArticulo.getChildren().clear();
//
//        dibujarArticulo(lista, gpArticulo);
//
////        scpane.setContent(gpArticulo);
//        visualizarArticulo();
//
//        int num = 0;
//        for (int x = 0; x < lista.size(); x++) {
//
//            for (int y = 0; y < 3; y++) {
//
//                if (num < lista.size()) {
//
//                    if (lista.get(num).getRutaImg() == null) {
//
//                        imageFile = new File("foto/img_articulo.jpg");
//
//                    } else {
//
//                        imageFile = new File(lista.get(num).getRutaImg());
//                    }
////                    File imageFile = new File(lista.get(num).getRutaImg());
//
//                    img = new ImageView(new Image(imageFile.toURI().toString(), 130, 100, false, false));
//                    img.setFitWidth(130);
//                    img.setFitHeight(100);
//                    Label lbNombre = new Label();
//
//                    lbNombre.setStyle(" -fx-text-fill: #ffffff;"
//                            + " -fx-font-size: 14pt;");
//
//                    VBox vb = new VBox();
//                    vb.setAlignment(Pos.TOP_LEFT);
//                    vb.setStyle("   -fx-text-fill:000000;"
//                            + " -fx-background-color: #394B52;"
//                            + "    -fx-border-color:  #00bb2d;\n"
//                            + "    -fx-border-radius: 5px;\n"
//                            + "    -fx-background-radius: 10px;\n"
//                            + "    -fx-font-size: 12pt;");
//
////                    btn = new Button(lista.get(num).getNombre(), img);
////                    btn.setStyle("-fx-base: coral;");
////                    btn.setContentDisplay(ContentDisplay.TOP);
//                    vb.setId(Integer.toString(lista.get(num).getCodigo()));
//                    vb.setAccessibleHelp(lista.get(num).getNombre());
//
//                    vb.setPrefSize(140, 120);
//                    vb.setMinWidth(140);
//                    vb.setMaxWidth(140);
//                    vb.getChildren().add(img);
//                    vb.getChildren().add(lbNombre);
//
//                    GridPane.setConstraints(vb, y, x);
//
//                    gpArticulo.add(vb, y, x);
//
//                }
//
//                num++;
//
//            }
//
//        }
//
//        scpane.setContent(gpArticulo);
//
//        visualizarArticulo();
    }

    private void crearCategoria() {

        Button btn = new Button("BOTON");
        VbSubCate.getChildren().clear();
        gpArticulo.getChildren().clear();

        List<Categoria> lista = ManejoCategoria.getInstancia().getCategoria();

        for (int i = 0; i < lista.size(); i++) {

            btn = new Button(lista.get(i).getNombre());
            btn.setId(Integer.toString(lista.get(i).getCodigo()));
            btn.setCursor(Cursor.HAND);
            btn.setPrefSize(135, 50);
            btn.setMinWidth(135);
            btn.setMaxWidth(135);
//
//            btn.setPrefSize(170, 150);
//            btn.setMinWidth(170);
//            btn.setMaxWidth(200);
//            hbCategoria.getChildren().add(btn);
            VbSubCate.getChildren().add(btn);

        }

        visualizarCategoria();

    }

    private void crearSubCategoria(int categoria) {

        Button btn = new Button("BOTON");

        List<SubCategoria> lista = ManejoSubCategoria.getInstancia()
                .getSubCategorias(categoria);
        VbSubCate.getChildren().clear();

        for (int i = 0; i < lista.size(); i++) {

            btn = new Button("" + lista.get(i).getNombre());
            btn.setId(Integer.toString(lista.get(i).getCodigo()));
            btn.setCursor(Cursor.HAND);

            btn.setPrefSize(125, 50);
            btn.setMinWidth(125);
            btn.setMaxWidth(125);
            VbSubCate.getChildren().add(btn);

        }

        visualizarSubCategoria();

    }

    public void visualizarArticulo() {

//        gpArticulo.getStylesheets().add(getClass().getResource("/css/btn_articulos.css").toExternalForm());
        for (int i = 0; i < gpArticulo.getChildren().size(); i++) {

            HBox hb = (HBox) gpArticulo.getChildren().get(i);
            hb.setCursor(Cursor.HAND);

            hb.setOnMouseClicked((event) -> {

                int codigo = Integer.parseInt(hb.getId().trim());
                System.out.println("CodArticulo " + hb.getId());

                setArticulo(ManejoArticulo.getInstancia().getArticulo(codigo));

                try {

                    FXMLLoader loader = new FXMLLoader();

                    loader.setLocation(getClass().getResource("/vista/venta/facturacion/TecladoDigital.fxml"));
                    loader.load();

                    Parent root = loader.getRoot();
                    TecladoDigitalController tecladoDigitalController = loader.getController();

                    tecladoDigitalController.setArticulo(getArticulo());
                    tecladoDigitalController.llenarCampo();
                    tecladoDigitalController.getTxtCAtidad().requestFocus();

                    ClaseUtil.getStageModal(root);

                    unidadDespacho = tecladoDigitalController.getUnidadDespacho();

                    System.out.println("Unidad despacho " + unidadDespacho);

                    if (!(getArticulo() == null) && !tecladoDigitalController.getTxtCAtidad().getText().isEmpty()) {

                        catidadArticulo = Double.parseDouble(tecladoDigitalController.getTxtCAtidad().getText());

                        articuloDTO = tecladoDigitalController.getArticuloDTO();

                        int unidad = tecladoDigitalController.getArticuloDTO().getCodigoUnidad();

                        int listaPrecio = tecladoDigitalController.getArticuloDTO().getListaDePrecio();
                        int almacen = tecladoDigitalController.getArticuloDTO().getAlmacen();
                        precioVenta = tecladoDigitalController.getArticuloDTO().getPrecioVenta();

                        Double existencia = ManejoExistenciaArticulo
                                .getInstancia()
                                .getExistenciaArticulo(getArticulo().getCodigo(), almacen, unidad, listaPrecio);

                        existencia = FormatNum.FormatearDouble(existencia, 2);

                        System.out.println("existencia < existenciaEnPeso " + existencia + " " + existenciaEnPeso
                                + " tipo art " + getArticulo().getTipoArticulo().getCodigo());

                        if (unidadDespacho == 2) {

                            existenciaEnPeso = FormatNum
                                    .FormatearDouble(catidadArticulo / getArticulo()
                                            .getPrecioVenta1(), 4);

                            System.out.println("Cantidad en peso " + catidadArticulo);

                        } else {

                            existenciaEnPeso = catidadArticulo;
                        }

                        System.out.println("getArticulo().getTipoArticulo().getCodigo() " + getArticulo().getTipoArticulo().getCodigo());

                        if (existencia < existenciaEnPeso && !(getArticulo().getTipoArticulo().getCodigo() == 3)) {

                            ClaseUtil.mensaje("  estoy aqui  La cantidad a Despachar " + catidadArticulo
                                    + "  no puede ser mayor que la existencia ,"
                                    + " Existencia igual a " + existencia);

                            return;
                        }

                        agregarArticuloConPrecioDeLista(unidad, almacen, listaPrecio);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

        }

    }

    public void visualizarArticuloEjemplo() {

        System.out.println("Cantidad de articulo " + gpArticulo.getChildren().size());
        for (int i = 0; i < gpArticulo.getChildren().size(); i++) {

            VBox vbArt = (VBox) gpArticulo.getChildren().get(i);
//            vbArt.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());
            vbArt.setStyle("-fx-background-color: cornsilk; -fx-padding: 10; -fx-font-size: 20;");
//                    vbArt.setStyle("-fx-base: coral;");

            vbArt.setOnMouseClicked((event) -> {

                int codigo = Integer.parseInt(vbArt.getId().trim());
                System.out.println("CodArticulo " + vbArt.getId());

                setArticulo(ManejoArticulo.getInstancia().getArticulo(codigo));
                agregarArticulo();
            });

        }

    }

    public void visualizarCategoria() {

        VbSubCate.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

//            for (int i = 0; i < hbCategoria.getChildren().size(); i++) {
        for (int i = 0; i < VbSubCate.getChildren().size(); i++) {

            Button bt = (Button) VbSubCate.getChildren().get(i);
//            Button bt = (Button) hbCategoria.getChildren().get(i);

            bt.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

            bt.setOnMouseClicked((event) -> {

                Image image = new Image(getClass().getResourceAsStream("/imagen/flecha_izquirda.png"));
                ImageView imgview = new ImageView(image);
                lbCategoriaSeleccionada.setText(bt.getText());
//                imgview.setFitHeight(30);
////                imgview.setFitWidth(70);
//                lbCategoria.setText("SubCategoria");
//                hbTituloCategoria.getChildren().clear();
////                hbTituloCategoria.getChildren().addAll(imgview, lbCategoria);
//                hbTituloCategoria.setAlignment(Pos.CENTER);
//                imgview.setFitWidth(70);

//                lbCategoria.setText("Sub Categoria");
                VbSubCate.getChildren().clear();
//                l.getChildren().addAll(imgview, lbCategoria);
                VbSubCate.setAlignment(Pos.TOP_CENTER);
//                lbCategoria.setGraphic(imgview);
                crearSubCategoria(Integer.parseInt(bt.getId()));

//                crearArticulo(Integer.parseInt(bt.getId()));
            });

        }

    }

    public void visualizarSubCategoria() {

        for (int i = 0; i < VbSubCate.getChildren().size(); i++) {

            Button bt = (Button) VbSubCate.getChildren().get(i);
            bt.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

            bt.setCursor(Cursor.HAND);

//            bt.setStyle("  -fx-background-color: #5CB85C;"
//                    + "  -fx-font-size: 11pt;\n"
//                    + "    -fx-font-family: \"Segoe UI Semibold\";\n"
//                    + "    -fx-text-fill:ffffff;\n"
//                    // + "    -fx-opacity: 0.6;"
//                    + "    -fx-background-radius: 20px;");
            bt.setOnMouseClicked((event) -> {

                System.out.println("SubCategoria " + bt.getText());

                crearArticuloPorSubCategoria(Integer.parseInt(bt.getId()));

            });

        }

    }

    private void llenarClientedeContado() {

//        setCliente(ManejoCliente.getInstancia().getCliente(1));
//        txtCodigoCliente.setText(getCliente().getCodigo().toString());
//        txtNombreCliente.setText(getCliente() == null ? "na " : getCliente().getNombre());
    }

    private void inicializarDatos() {

        llenarClientedeContado();
        dpFecha.setValue(LocalDate.now());
//        TipoNcf tnf = manejo.factura.ManejoTipoNcf.getInstancia().getListaTipoNcf().get(0);
//        relacionNcf = ManejoRelacionNcf.getInstancia().getNCF(tnf);
//        txtNcf.setText(relacionNcf.getActual());

    }

    private void limpiar() {

        txtNombreCliente.clear();
        txtSubTotal.clear();
        txtTotal.clear();
        txtDescuento.clear();
        txtDescuento.clear();
        txtNumeroFactura.clear();
        txtMontoExcento.clear();
        txtMontoGravado.clear();
        txtItbis.clear();
        txtNcf.clear();
//        txtTotal1.clear();
//        txtTotalItbisRetendo.clear();
//        txtISR.clear();
        cbTipoNcf.getSelectionModel().select(-1);
        btnDescuento.setDisable(false);
        txtPorciento.clear();
        txtPorcientoMaximo.clear();
        txtPorcientoMinimo.clear();
        listadetalleFactura.clear();
        txtNombreCliente.clear();
        txtVendedor.clear();
        btnBuscarCliente.setDisable(false);
        btnBuscarCotizacion.setDisable(false);
        txtConcepto.clear();
        setCliente(null);
        setEjecutivoDeVenta(null);
        llenarClientedeContado();

    }

    private void nuevo() {

        factura = new Factura();
        detalleFactura = new DetalleFactura();
        btnFormaPago.setDisable(true);
        hbTipoVenta.setStyle(" -fx-background-color: #5CB85C;\n"
                + "    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);\n"
                + "    -fx-border-color: -fx-secondary;\n"
                + "    -fx-border-radius: 15px;\n"
                + "    -fx-background-radius: 15px;\n"
                + "    -fx-font-size: 14pt;");

        lbTipoVenta.setStyle("-fx-text-fill: #ffffff");
        lbTipoVenta.setText("  Tipo de Venta Seleccionado : " + "Credito ");

        limpiar();
        vbDescuento.setVisible(false);
        btnCategoria.setVisible(false);

        txtNumeroFactura.setText(ManejoFactura.getInstancia().getNumero().toString());

    }

    private void agregarArticulo() {

        try {

            detalleFactura = new DetalleFactura();
            Double total = 0.00, subTotal = 0.00, valorItbis = 0.00, precioVenta = 0.00,
                    precioVentaItbis = 0.00, valorEnPeso = 0.00;

            switch (cliente.getPrecio()) {

                case 1:
                    precioVenta = getArticulo().getPrecioVenta1();
                    precioVentaItbis = getArticulo().getPrecioventa1Itbis();
                    System.out.println("Precio v1 " + precioVenta);
                    break;
                case 2:
                    precioVenta = getArticulo().getPrecioVenta2();
                    precioVentaItbis = getArticulo().getPrecioventa1Itbis();
                    System.out.println("Precio v2 " + precioVenta);
                    break;
                case 3:
                    System.out.println("Precio v3 " + precioVenta);
                    precioVentaItbis = getArticulo().getPrecioventa1Itbis();
                    precioVenta = getArticulo().getPrecioVenta3();
                    break;
            }

            if (unidadDespacho == 2) {

                valorEnPeso = catidadArticulo;
                catidadArticulo = FormatNum.FormatearDouble(catidadArticulo / precioVenta, 4);
            }

            detalleFactura.setArticulo(getArticulo());
            detalleFactura.setCantidad(catidadArticulo);
            detalleFactura.setDescuento(Double.parseDouble(txtDescuento.getText().isEmpty() ? "0" : txtDescuento.getText()));
            detalleFactura.setExistencia(getArticulo().getExistencia());
            detalleFactura.setNombreArticulo(getArticulo().getNombre());
            detalleFactura.setNuevaExistencia(0.0);
            detalleFactura.setPrecio(precioVenta);
            detalleFactura.setPrecioItbis(precioVentaItbis);

            if (unidadDespacho == 2) {

                subTotal = valorEnPeso;

            } else {

                subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
            }

//            subTotal=ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(),2);
            if (getArticulo().getExentoItbis()) {

                valorItbis = 0.0;
                detalleFactura.setTasaItbis(0.00);

            } else {

                double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                valorItbis = ClaseUtil.roundDouble((subTotal - detalleFactura.getDescuento()) * (itbis / 100), 2);
                detalleFactura.setPrecioItbis(precioVentaItbis);
                detalleFactura.setTasaItbis(itbis);
            }

            total = ClaseUtil.roundDouble((subTotal - detalleFactura.getDescuento()) + valorItbis, 2);
            detalleFactura.setItbis(valorItbis);
            detalleFactura.setSubTotal(subTotal);
            detalleFactura.setTotal(total);
            detalleFactura.setUnidad(getArticulo().getUnidadSalida());
            detalleFactura.setFactura(factura);

            if (existe(detalleFactura)) {

                agruparDetallePorArticulo(detalleFactura);

            } else {

                listadetalleFactura.add(detalleFactura);
            }

            txtCodigoArticulo.clear();
            iniciazarFiltro();

            txtSubTotal.setText(getSubTotal().toString());
            txtDescuento.setText(getDescuento().toString());
            txtItbis.setText(getItbis().toString());
            txtTotal.setText(getTotal().toString());

            detalleFactura = new DetalleFactura();
            btnFormaPago.setDisable(false);
            setArticulo(null);
//            GUIUtils.autoFitTable(tbDetalleFactura);
//            crearArticulo();
            txtCodigoArticulo.requestFocus();

            if (tipoVenta == 1) {

                btnFormaPago.setDisable(false);
            } else if (tipoVenta == 2) {
                btnFormaPago.setDisable(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void agregarArticuloConPrecioDeLista(int unidadSalida, int almacen, int listaPrecio) {

        try {

            detalleFactura = new DetalleFactura();
            Double total = 0.00, subTotal = 0.00, valorItbis = 0.00, montoGRavado = 0.00,
                    precioVentaItbis = 0.00, valorEnPeso = 0.00, porcientoGravado = 0.00,
                    itbisGravadoArticulo = 100.0,
                    porcientoItbisGravado = VariablesGlobales.USUARIO.getUnidadDeNegocio().getMontoGravado();

            itbisGravadoArticulo = getArticulo().getItbisGravado() == null ? 100 : getArticulo().getItbisGravado();

            if (itbisGravadoArticulo == 100) {
                porcientoGravado = porcientoItbisGravado / 100;
                System.out.println("getUnidadDeNegocio().getMontoGravado() " + porcientoItbisGravado);
            } else {
                porcientoGravado = getArticulo().getItbisGravado() / 100;
                System.out.println(" getArticulo().getItbisGravado() " + getArticulo().getItbisGravado());
            }

            double itbis = 0.00;
            System.out.println("Precio de venta " + precioVenta);

            //Obtenemos el precio de lista
            DetalleListaDePrecio detalleListaDePrecio = ManejoListaDePrecio.getInstancia()
                    .getDetalleListaDePrecio(getArticulo().getCodigo(), unidadSalida, catidadArticulo);

            if (!(detalleListaDePrecio == null)) {

                precioVenta = detalleListaDePrecio.getPrecio();
                System.out.println("Precio Lista general " + precioVenta);
            }

            if (precioVenta <= 0) {

                ClaseUtil.mensaje(" Este Articulo con esta unidad no tiene precio ");
                return;
            }

//            if (unidadDespacho == 2) {
//
            valorEnPeso = catidadArticulo;
//                catidadArticulo = FormatNum.FormatearDouble(catidadArticulo / precioVenta, 4);
//            }

            detalleFactura.setArticulo(getArticulo());

            detalleFactura.setCantidad(catidadArticulo);
            detalleFactura.setDescuento(Double.parseDouble(txtDescuento.getText().isEmpty() ? "0" : txtDescuento.getText()));
            detalleFactura.setNombreArticulo(getArticulo().getNombre());
            detalleFactura.setAlmacen(ManejoAlmacen.getInstancia().getalmacen(almacen));

            ExistenciaArticulo exisArt = ManejoExistenciaArticulo.getInstancia()
                    .getExistenciaArticulo(detalleFactura.getArticulo().getCodigo(),
                            almacen);

            if (exisArt == null) {

                ClaseUtil.confirmarMensaje(" ESTE ARTICULO  "
                        + " " + detalleFactura.getArticulo().getNombre() + " NO TIENE ALMACEN ");
                return;
            }

//            Optional<ButtonType> op = ClaseUtil.confirmarMensaje(" ESTE ARTICULO "
//                    + " " + detalleFactura.getArticulo().getNombre() + " NO TIENE ALMACEN ");
//            
//            if (op.get()==ButtonType.OK || op.get() == ButtonType.YES) {
//
//            }
            System.out.println("exisArt " + exisArt);
            detalleFactura.setExistencia(exisArt.getExistencia());

            detalleFactura.setPrecio(precioVenta);
            detalleFactura.setPrecioItbis(precioVentaItbis);
            detalleFactura.setUnidad(ManejoUnidad.getInstancia().getUnidad(unidadSalida));

//            Integer unidadInventario = ManejoArticuloUnidad.getInstancia()
//                    .getArticuloUnidadSslida(detalleFactura.getArticulo().getCodigo()).getUnidad().getCodigo();
//            ArticuloUnidad artUnidad = ManejoArticuloUnidad.getInstancia()
//                    .getArticuloUnidadSslida(detalleFactura.getArticulo().getCodigo(), detalleFactura.getUnidad().getCodigo());
            detalleFactura.setUnidadInventario(unidadSalida);
            detalleFactura.setCantidadInventario(catidadArticulo);
            detalleFactura.setNuevaExistencia(exisArt.getExistencia() - detalleFactura.getCantidadInventario());
            detalleFactura.setCostoUnitario(getArticulo().getPrecioCompraUnitario());

            detalleFactura.setFactorUnidadSalida(1.0);
            detalleFactura.setListaDePrecio(listaPrecio);

//            if (unidadDespacho == 2) {
//
//                subTotal = valorEnPeso;
//
//            } else {
//
            subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
//            }

//            subTotal=ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(),2);
            if (getArticulo().getExentoItbis()) {

                valorItbis = 0.0;
                detalleFactura.setTasaItbis(0.00);

            } else {

                itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                valorItbis = ClaseUtil.roundDouble((subTotal - detalleFactura.getDescuento()) * (itbis / 100), 2); //esto es cuando el precio ya tiene el itbis
//                valorItbis = ClaseUtil.roundDouble(subTotal * (itbis / 100), 4);
                detalleFactura.setPrecioItbis(precioVentaItbis);
                detalleFactura.setTasaItbis(itbis);
            }

            montoGRavado = ClaseUtil.roundDouble(porcientoGravado * (subTotal - detalleFactura.getDescuento()), 2);

            System.out.println("montoGRavado : " + montoGRavado);
            detalleFactura.setItbis(valorItbis);
            detalleFactura.setSubTotal(subTotal);

            detalleFactura.setFactura(factura);
            detalleFactura.setMontoExcento((subTotal - montoGRavado));

            if (detalleFactura.getMontoExcento() > 0) {
                tieneMontoExcento = true;
            }

            detalleFactura.setMontoGravado(montoGRavado);
            double valorItbisGravado = util.FormatNum.FormatearDouble((detalleFactura.getMontoGravado()) * (itbis / 100), 2);
            detalleFactura.setMontoItbisExcento(valorItbis - valorItbisGravado);
            detalleFactura.setItbis(valorItbisGravado);

            total = ClaseUtil.roundDouble((((subTotal - detalleFactura.getDescuento()) + valorItbisGravado)), 2);
            detalleFactura.setTotal(total);
            detalleFactura.setTotalACobrar(total);

            if (getCliente() == null) {
                ClaseUtil.mensaje("Tiene que seleccionar un CLiente");
                return;
            }

            detalleFactura = calcularRetencion(detalleFactura);

            if (existe(detalleFactura)) {

                agruparDetallePorArticulo(detalleFactura);

            } else if (!existe(detalleFactura)) {

                listadetalleFactura.add(detalleFactura);
            }

            txtCodigoArticulo.clear();
            iniciazarFiltro();

            txtSubTotal.setText(getSubTotal().toString());
            txtDescuento.setText(getDescuento().toString());
            txtItbis.setText(getItbis().toString());
            txtTotal.setText(getTotal().toString());

            txtMontoExcento.setText(getMontoExcento().toString());
            txtMontoGravado.setText(getMontoGravado().toString());

            detalleFactura = new DetalleFactura();
            btnFormaPago.setDisable(false);
            setArticulo(null);
//            GUIUtils.autoFitTable(tbDetalleFactura);
//            crearArticulo();
            txtCodigoArticulo.requestFocus();

            if (tipoVenta == 1) {

                btnFormaPago.setDisable(false);
            } else if (tipoVenta == 2) {
                btnFormaPago.setDisable(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Double getSubTotal() {

        Double subTotal = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

//            subTotal += det.getCantidad() * det.getPrecio();
            subTotal += det.getSubTotal();
        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotal() {

        Double total = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

//            total += (det.getCantidad() * det.getPrecio()) + det.getItbis();
            total += det.getTotal();
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getMontoExcento() {

        Double total = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

//            total += (det.getCantidad() * det.getPrecio()) + det.getItbis();
            total += det.getMontoExcento() == null ? 0 : det.getMontoExcento();
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getMontoGravado() {

        Double total = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

//            total += (det.getCantidad() * det.getPrecio()) + det.getItbis();
            total += (det.getSubTotal() - det.getMontoExcento());
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getMontoItbisExcento() {

        Double total = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

//            total += (det.getCantidad() * det.getPrecio()) + det.getItbis();
            total += det.getMontoItbisExcento();
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getDescuento() {

        Double descuento = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

            descuento += det.getDescuento();
        }

        return FormatNum.FormatearDouble(descuento, 2);
    }

    private Double getItbis() {

        Double itbis = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

            itbis += det.getItbis();
        }

        return FormatNum.FormatearDouble(itbis, 2);
    }

    private Double getTotalACobrar() {

        Double total = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

            total += det.getTotalACobrar() == null ? 0 : det.getTotalACobrar();
        }

        return util.FormatNum.FormatearDouble(total, 2);
    }

    private Double getTotalItbisRetenido() {

        Double total = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

            total += det.getItbisRetenido() == null ? 0 : det.getItbisRetenido();
        }

        return util.FormatNum.FormatearDouble(total, 2);
    }

    private Double getTotalIsrRetenido() {

        Double totalIsr = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

            totalIsr += det.getIsrRetenido();
        }
        return util.FormatNum.FormatearDouble(totalIsr, 2);
    }

    @FXML

    private void btnBuscarClienteActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/cliente/FXMLBusCliente.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        FXMLBusClienterController articuloController = loader.getController();

        if (!(articuloController.getCliente() == null)) {

            setCliente(articuloController.getCliente());

            txtNombreCliente.setText(getCliente().getNombre());
            getCliente().setMontoDisponible(ManejoFactura.getInstancia().getMontoDisponible(getCliente()));

            if (tipoVenta == 2) {

                if (getCliente().getCreditoBloquiado() == true) {

                    ClaseUtil.mensaje(" ESTE CLIENTE TIENE EL CREDITO BLOQUIADO   \n  COMUNICARSE CON LA ADMINISTRACION ");
                    nuevo();
                    return;
                }

                for (DetalleFactura det : listadetalleFactura) {
                    calcularRetencion(det);
                }

//                Double montoDisponible = ManejoFactura.getInstancia().getMontoDisponible(getCliente());
//                if (montoDisponible <= 0) {
//
//                    ClaseUtil.mensaje(" ESTE CLIENTE NO TIENE CREDITO DISPONIBLE   \n  COMUNICARSE CON LA ADMINISTRACION ");
//                    nuevo();
//                }
            }

        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {

        if (ClaseUtil.confirmarMensaje(" Seguro que que quiere limpiar los campos ").get() == ButtonType.YES) {
            nuevo();
        }

    }

    @FXML
    private void btnFormaPagoActionEvent(ActionEvent event) {

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/venta/facturacion/FormaPago.fxml"));
//
//        try {
//
//            panelFormaPago = loader.load();
//            bpPrincipal.setRight(panelFormaPago);
//
//            formaDePagoController = loader.getController(); // retrieve controller
//
//            formaDePagoController.getTxtTotalFactura().setText(txtTotal.getText());
//            formaDePagoController.getTxtEfectivo().setText(txtTotal.getText());
//            formaDePagoController.setBpPrincipal(bpPrincipal);
//            formaDePagoController.setFacturacionTochController(facturacionTochController);
//
////
////            ClaseUtil.getStageModal(root);
////
////            if (formaDePagoController.getListaFormaPago().size()>0) {
////
////                listFormaPagoCollection = formaDePagoController.getListaFormaPago();
////                setDevuelta(formaDePagoController.getTotalPago());
////
////            }
//        } catch (Exception e) {
//
//            System.out.println("unable to load tab1");
//            e.printStackTrace();
//        }
//
        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/venta/facturacion/FormaPago.fxml"));
            loader.load();

            Parent root = loader.getRoot();
            formaDePagoController = loader.getController();
//
            formaDePagoController.getTxtTotalFactura().setText(txtTotal.getText());
            formaDePagoController.getTxtEfectivo().setText(txtTotal.getText());

            ClaseUtil.getStageModal(root);

            if (!(formaDePagoController.getFormaPago() == null)) {

                listFormaPagoCollection = formaDePagoController.getListaFormaPago();
                setDevuelta(formaDePagoController.getTotalPago());

            }
            /*else {

                ClaseUtil.mensaje("Tiene que aplicar una forma de pago");
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void btnGuardarActionevent(ActionEvent event) {

        try {

            if (tbDetalleFactura.getItems().size() <= 0) {

                ClaseUtil.mensaje("No tiene Articulos agregado para esta Factura");
                return;
            }

            if (getDevuelta() == null && tipoVenta == 1) {

                ClaseUtil.mensaje("Tiene que seleccionar una forma de pago");
                return;
            }

            if (tipoVenta == 2 && txtNombreCliente.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que seleccionar un cliente");
                return;
            }

            if (tipoVenta == 1) {

                if (formaDePagoController == null) {

                    ClaseUtil.mensaje("Tiene que aplicar una forma de pago");

                    return;
                }

                listFormaPagoCollection = formaDePagoController.getListaFormaPago();

                setDevuelta(formaDePagoController.getDevuelta());

            }

            if (listFormaPagoCollection == null && tipoVenta == 1) {

                ClaseUtil.mensaje("Tiene que aplicar una forma de pago");
                return;
            }

            if (txtNcf.getText().isEmpty()) {

                ClaseUtil.mensaje(" Tiene que seleccionar un comprobante fiscal ");
                return;
            }

            if (cbTipoDeIngreso.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje(" Tiene que seleccionar un tipo de ingreso ");
                return;
            }

            System.out.println(" mn : " + txtConcepto.getText().isEmpty() + " " + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
            if (txtConcepto.getText().isEmpty() && VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 5) {

                ClaseUtil.mensaje(" Tiene que digitar un concepto ");
                return;
            }

            guardar();

//            if (txtNcf.getText().isEmpty()) {
//
//                ButtonType ok = ClaseUtil.confirmarMensaje(" Tiene que seleccionar un comprobante fiscal ").get();
//
//                System.out.println("ok " + ok);
//                if (ok == ButtonType.YES) {
//
//                    guardar();
//                } else {
//                    return;
//                }
//
//            } else {
//
//            guardar();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnImprimirActionEvent(ActionEvent event) {

        try {

            if (getCliente() == null) {

                ClaseUtil.mensaje(" Tiene que seleccionar un cliente ");
                return;
            }

            if (tbDetalleFactura.getItems().size() <= 0) {

                ClaseUtil.mensaje(" No tiene Articulos agregado para esta Factura");
                return;
            }

            if (txtVendedor.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que seleccionar un vendedor ");
                return;
            }

            if (getDevuelta() == null && tipoVenta == 1) {

                ClaseUtil.mensaje("Tiene que seleccionar una forma de pago");
                return;
            }

            if (tipoVenta == 2 && txtNombreCliente.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que seleccionar un cliente");
                return;
            }

            if (tipoVenta == 1) {

                if (formaDePagoController == null) {

                    ClaseUtil.mensaje("Tiene que aplicar una forma de pago");

                    return;
                }

                listFormaPagoCollection = formaDePagoController.getListaFormaPago();

                setDevuelta(formaDePagoController.getDevuelta());

            }

            if (listFormaPagoCollection == null && tipoVenta == 1) {

                ClaseUtil.mensaje("Tiene que aplicar una forma de pago");
                return;
            }

            if (txtNcf.getText().isEmpty()) {

                ClaseUtil.mensaje(" Tiene que seleccionar un comprobante fiscal ");
                return;
            }

            if (cbTipoDeIngreso.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje(" Tiene que seleccionar un tipo de ingreso ");
                return;
            }

            if (txtConcepto.getText().isEmpty() && VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 5) {

                ClaseUtil.mensaje(" Tiene que digitar un concepto ");
                return;
            }

            guardar();

//            if (txtNcf.getText().isEmpty()) {
//
//                ButtonType ok = ClaseUtil.confirmarMensaje(" Factar sin ncf ").get();
//
//                System.out.println("ok " + ok);
//                if (ok == ButtonType.YES) {
//
//                    guardar();
//                } else {
//                    return;
//                }
//
//            } else {
//
//                guardar();
//            }
            System.out.println("Antes de crear la factura ** " + CodigoFactura);
            //Guandanto factura ****
//            guardar();

            System.out.println("Despues de crear la  Factura ** " + CodigoFactura);

            int ung = VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

            if (CodigoFactura > 0) {

                if (ManejoConfiguracion.getInstancia().getConfiguracion().getFacturarSinImprimir() == false) {

//          
                    RptFactura.getInstancia().verFactura(CodigoFactura, ung, facturaDb.getFormato());
//                 
//                    if (null != VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())  
//                        
//                    switch (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()) {
//                        case 1:
//                            RptFacturaPinturaTriplea.getInstancia().verFactura(CodigoFactura);
//                            break;
//                        case 2:
//                            RptFacturaIghTrack.getInstancia().verFactura(CodigoFactura);
//                            break;
//                        case 4:
//                            RptFacturaIghTrack.getInstancia().verFactura(CodigoFactura);
//                            break;
//                        case 3:
//                            RptFacturaIghTrack.getInstancia().verFactura(CodigoFactura);
//                            break;
//                        case 5:
//                            RptFacturaFsConstrucciones.getInstancia().verFactura(CodigoFactura);
//                            break;
//                        default:
//                            break;
//                    }
                }
            }

            CodigoFactura = 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        try {

            if (tbDetalleFactura.getSelectionModel().getSelectedIndex() != -1) {

                listadetalleFactura.remove(tbDetalleFactura.getSelectionModel().getSelectedIndex());
                tbDetalleFactura.refresh();
                txtSubTotal.setText(getSubTotal().toString());
                txtDescuento.setText(getDescuento().toString());
                txtItbis.setText(getItbis().toString());
                txtTotal.setText(getTotal().toString());
                txtMontoExcento.setText(getMontoExcento().toString());
                txtMontoGravado.setText(getMontoGravado().toString());

            } else {
                ClaseUtil.mensaje("Tiene que Selccionar un Registro");
            }

            if (getTotal() <= 0) {

                btnFormaPago.setDisable(true);

            } else {
                btnFormaPago.setDisable(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean existe(DetalleFactura det) {

        for (DetalleFactura detalle : tbDetalleFactura.getItems()) {

            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())
                    && Objects.equals(detalle.getUnidad().getCodigo(), det.getUnidad().getCodigo())) {

                return true;

            }
        }

        return false;
    }

    private void btn1ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 1;
        txtCAtidad.setText(numStr);

    }

    private void btn2ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 2;
        txtCAtidad.setText(numStr);
    }

    private void btn4ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 4;
        txtCAtidad.setText(numStr);
    }

    private void btn5ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 5;
        txtCAtidad.setText(numStr);
    }

    private void btn7ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 7;
        txtCAtidad.setText(numStr);
    }

    private void btn8ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 8;
        txtCAtidad.setText(numStr);

    }

    private void btn3ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 3;
        txtCAtidad.setText(numStr);
    }

    private void btn6ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 2;
        txtCAtidad.setText(numStr);
    }

    private void btn9ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 9;
        txtCAtidad.setText(numStr);
    }

    private void btnAgregarActionEvent(ActionEvent event) {

        if (getArticulo() == null) {

            ClaseUtil.mensaje("Tiene que seleccionar un Articulo");
            return;
        }

        if (txtCAtidad.getText().isEmpty()) {

            ClaseUtil.mensaje("Tiene que digitar una cantidad");

            return;
        }

        agregarArticulo();

    }

    private void btnPuntoActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + ".";
        txtCAtidad.setText(numStr);
    }

    private void btn0ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + "0";
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btnDeContadoActionEvent(ActionEvent event) {

        tipoVenta = 1;//Venta De Contado
        btnFormaPago.setDisable(false);
        llenarClientedeContado();

        hbTipoVenta.setStyle("   -fx-background-color: #5CB85C;\n"
                + "    -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C);\n"
                + "    -fx-border-color: -fx-secondary;\n"
                + "    -fx-border-radius: 15px;\n"
                + "    -fx-background-radius: 15px;\n"
                + "    -fx-font-size: 12pt;");
        lbTipoVenta.setStyle("-fx-text-fill: #ffffff");
        lbTipoVenta.setText("  Tipo de Venta Seleccionado : " + " De Contado ");
    }

    @FXML
    private void btnCreditoActionEvent(ActionEvent event) {
        tipoVenta = 2;//Venta a Credito
//        txtCodigoCliente.clear();
        txtNombreCliente.clear();
        btnFormaPago.setDisable(true);
        btnBuscarCliente.setDisable(false);

        hbTipoVenta.setStyle(" -fx-background-color: #5CB85C;\n"
                + "    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);\n"
                + "    -fx-border-color: -fx-secondary;\n"
                + "    -fx-border-radius: 15px;\n"
                + "    -fx-background-radius: 15px;\n"
                + "    -fx-font-size: 14pt;");

        lbTipoVenta.setStyle("-fx-text-fill: #ffffff");
        lbTipoVenta.setText("  Tipo de Venta Seleccionado : " + "Credito ");
    }

    @FXML
    private void btnBuscarArticuloActionEvent(ActionEvent event) {

        txtCodigoArticulo.clear();
        listaArticulos.clear();
        listaArticulos.addAll(ManejoArticulo.getInstancia().getListaArticuloPorUnidadDeNegocio());
        txtCodigoArticulo.requestFocus();
//        tbArticulo.setItems(listaArticulos);
        iniciazarFiltro();
//        if (!txtCodigoArticulo.getText().isEmpty()) {
//
//            List<Articulo> lista = ManejoArticulo.getInstancia().getListaArticulo(txtCodigoArticulo.getText());
////            List<Articulo> lista = ManejoArticulo.getInstancia().getArticuloPorCodigo(Integer.parseInt(txtCodigoArticulo.getText()));
//
//            crearArticuloPorBusqueda(lista);
//            txtCodigoArticulo.clear();
//            txtCodigoArticulo.requestFocus();
//
//        } else {
//
//            gpArticulo.getChildren().clear();
//        }
    }

    private void hbTituloCategoriaMouseCliked(MouseEvent event) {
        crearCategoria();
    }

    private void guardar() {

        try {

            factura.setAnulada(false);
            factura.setCliente(getCliente());
            factura.setNombreCliente(getCliente().getNombre());
            factura.setNcf(txtNcf.getText());
            factura.setTotal(getTotal());
            factura.setSubTotal(getSubTotal());
            factura.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            factura.setDescuento(getDescuento());
            factura.setItbis(getItbis());
            factura.setUsuario(VariablesGlobales.USUARIO);
            factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            factura.setVendedor(getEjecutivoDeVenta());
            factura.setDespachada(false);
            factura.setTipoDeIngreso(cbTipoDeIngreso.getSelectionModel().getSelectedItem());
            factura.setOrigenFactura(ManejoOrigenFactura.getInstancia().getOrigenFactura(5));
            factura.setItbisPercibido(0.00);
            factura.setItbisRetenido(getTotalItbisRetenido());
            factura.setTotalACobrar(getTotalACobrar());
            factura.setMontoExcento(getMontoExcento());
            factura.setMontoGravado(getMontoGravado());
            factura.setConcepto(txtConcepto.getText().isEmpty() ? "VENTAS DE MERCANCIA " : txtConcepto.getText());
            factura.setFormato(tieneMontoExcento ? 2 : 1);

            factura.setIsrPercibido(0.00);
            factura.setIsrRetenido(getTotalIsrRetenido());

            if (factura.getIsrRetenido() > 0 || factura.getItbisRetenido() > 0) {
                factura.setFechaRetencion(factura.getFecha());
            }

            if (factura.getDescuento() > 0 && getContiDeVenta() == null) {

                factura.setAutorizadorDescuento(getDescuentoPorUsuario().getUsuario().getCodigo());

            } else if (factura.getDescuento() > 0 && !(getContiDeVenta() == null)) {

                factura.setAutorizadorDescuento(getContiDeVenta().getAutorizadorDescuento());
            }

            factura.setFechaCreacion(new Date());

            Date fechaVencimiento = ClaseUtil.asDate(dpFecha.getValue());

            factura.setDetalleFacturaCollection(listadetalleFactura);
            factura.setCompatibilidad(false);

            if (tipoVenta == 1) { //Vanta de Contado

                factura.setPagada(true);
                factura.setPendiente(0.00);
                factura.setTipoVenta(1);
                factura.setAbono(factura.getTotal());
                factura.setCondicionPago(new CondicionPago(1));
                factura.setFechaVencimiento(fechaVencimiento);

                if (getDevuelta() < 0) {

                    ClaseUtil.mensaje(" El Monto Pagado Tiene que ser igual al monto de la Factura ");
                    return;
                }

            } else if (tipoVenta == 2) { //Venta a Credito

                factura.setPagada(false);
                factura.setTipoVenta(2);
                factura.setAbono(0.0);
                factura.setPendiente(factura.getTotal());
                factura.setCondicionPago(new CondicionPago(2));
                factura.setPlazo(ManejoPlazo.getInstancia().getplazo(getCliente().getDiasCredito()));
                fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, getCliente().getDiasCredito());
                factura.setFechaVencimiento(fechaVencimiento);

                formaPago = new FormaPago();
                TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
                formaPago.setTipoFormaPago(tipoFormaPago);
                formaPago.setMonto(factura.getTotal());

            }

            if (tipoVenta == 2) {

                //Comprobar limite de credito
                Double montoPendiente, montodisponible;
//                montoPendiente = ManejoFactura.getInstancia().getMontoPendiente(getCliente());
//                montodisponible = getCliente().getMontoCredito() - montoPendiente;
                montodisponible = ManejoFactura.getInstancia().getMontoDisponible(getCliente());

            }

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);

            if (!(secDoc == null)) {

                boolean existe = ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero());

                if (existe) {

                    System.out.println("existe " + secDoc.getNumero());

                    while (ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero())) {

                        secDoc.setNumero(secDoc.getNumero() + 1);
                        ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                    }

                    secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);

                    factura.setNumero(secDoc.getNumero());
                    factura.setSecuenciaDocumento(secDoc);

                } else {

                    System.out.println("No existe " + secDoc.getNumero());
                    factura.setNumero(secDoc.getNumero());
                    factura.setSecuenciaDocumento(secDoc);
                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

            } else {

                ClaseUtil.mensaje("La secuencia para la factura de la unidad de negocio "
                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return;
            }

            boolean conNcf = true;
            int ung = factura.getUnidadDeNegocio().getCodigo();
            boolean empresa = false;

            if (!(cbTipoNcf.getSelectionModel().getSelectedIndex() == -1)) {

//                factura.setTipoNcf(ManejoTipoNcf.getInstancia().getListaTipoNcf());//Sustentan Costo y gastos
                factura.setTipoNcf(cbTipoNcf.getSelectionModel().getSelectedItem());
                factura.setNombreTipoNcf(cbTipoNcf.getSelectionModel().getSelectedItem().getNombre());

                if (!(cbTipoNcf.getSelectionModel().getSelectedItem().getCodigo() == 3)) {//el 3 es tipo ncf manual,si no es 3 es tipo ncf automatico 

                    relacionNcf = ManejoRelacionNcf.getInstancia()
                            .getNCFUnidadDeNegocio(factura.getTipoNcf(), ung);

                    if (relacionNcf == null) {

                        ung = factura.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo();

                        relacionNcf = ManejoRelacionNcf.getInstancia()
                                .getNCFEmpresaOGrupo(factura.getTipoNcf(), ung);//Comprobante fiscal del grupo fs comercial
                        empresa = true;

                    }

                    boolean existe = ManejoFactura.getInstancia().existeNCF(relacionNcf.getActual(), factura.getUnidadDeNegocio().getCodigo());

                    if (existe) {

                        System.out.println("factura.getUnidadDeNegocio().getCodigo()"
                                + " " + factura.getUnidadDeNegocio().getCodigo() + " " + relacionNcf.getActual());

                        while (ManejoFactura.getInstancia().existeNCF(relacionNcf.getActual(), factura.getUnidadDeNegocio().getCodigo())) {

                            relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo(), ung, empresa);

                        }

                    } else {

                        relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo(), ung, empresa);

                    }

                    factura.setNcf(relacionNcf.getActual());

                    factura.setNcfFechaValidoHasta(relacionNcf.getFechaValidoHasta());

                    System.out.println(" ncf automatico  " + relacionNcf.getActual());

                } else {    //ncf colocado de forma manual

                    if (txtNcf.getText().substring(2, 3).equals("1")) {
                        factura.setTipoNcf(new TipoNcf(1)); //Credito fiscal
                    } else {
                        factura.setTipoNcf(new TipoNcf(2)); //Consumidor Final
                    }
                    System.out.println(" ncf manual  " + txtNcf.getText());

                    factura.setNcf(txtNcf.getText());

                    relacionNcf = ManejoRelacionNcf.getInstancia().getNCFUnidadDeNegocio(factura.getTipoNcf(), factura.getUnidadDeNegocio().getCodigo());

                    if (relacionNcf == null) {

                        System.out.println("factura.getTipoNcf() " + factura.getTipoNcf());
                        relacionNcf = ManejoRelacionNcf.getInstancia()
                                .getNCFUnidadDeNegocio(factura.getTipoNcf(), ung);//Comprobante fiscal del grupo fs comercial

                    }

                    System.out.println("relacion ncf " + relacionNcf + " factura.getTipoNcf() " + factura.getTipoNcf() + ""
                            + " factura.getUnidadDeNegocio().getCodigo() " + factura.getUnidadDeNegocio().getCodigo());

                    factura.setNcfFechaValidoHasta(relacionNcf.getFechaValidoHasta());

                }

                conNcf = true;

            } else {

                factura.setTipoNcf(null); //Consumidor Final
                factura.setNcf("n/a");
                factura.setNombreTipoNcf("NA");
                System.out.println("Sin ncf");
            }

            Double balancePendiente = ManejoFactura.getInstancia().getMontoPendiente(factura.getCliente());

            facturaDb = ManejoFactura.getInstancia().salvar(factura);
            String concepto = "";

            if (facturaDb == null) {

                ClaseUtil.mensaje("Hubo Error creando la factura");
                return;

            } else {

                if (tipoVenta == 2) {

                    HistoricoBalancePendiente hBalance = new HistoricoBalancePendiente();

                    hBalance.setCliente(facturaDb.getCliente().getCodigo());
                    hBalance.setFactura(facturaDb.getCodigo());
                    hBalance.setFechaVencimiento(facturaDb.getFecha());
                    hBalance.setTotal(balancePendiente);
                    ManejoHistoricoBalancePendiente.getInstancia().salvar(hBalance);
                }

                //Actualizar cotizacion
                if (!(facturaDb.getCotizacionDeVenta() == null)) {
                    System.out.println("Actualizando cotizacion " + getContiDeVenta().getCodigo());
                    getContiDeVenta().setFacturada(true);
                    ManejoCotizacionDeVenta.getInstancia().actualizar(getContiDeVenta());
                }

                //Actualizar el ncf 
                if (conNcf == true) {

                    if (!(relacionNcf == null)) {

                        ManejoRelacionNcf.getInstancia().actualizar(relacionNcf);
                    }

                }

                //Actualizando Existencia
                System.out.println("Actualizando inventario con factura numero  " + facturaDb.getCodigo());
                List<DetalleFactura> listaDet = new ArrayList<>();

                listaDet.addAll(ManejoFactura.getInstancia().getDetalleFactura(facturaDb.getCodigo()));

                //Actualizar inventario 
                if (listaDet.size() > 0) {

                    ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorSalida(listaDet);
                }

                //CREANDO ENTRADA DE DIARIO 
                ConfiguracionCuentaContable config = null;
                List<DetalleEntradaDiario> listaDetEntradaDiario = null;

                if (tipoVenta == 1) {// Ventas de contado

                    concepto = " Ventas de mercancia al contado ";

                    //                    config = ConfiguracionCuentaContableDao.getInstancia().getConfigCuentaPorTipoConcepto(6, 3);
                    config = ConfiguracionCuentaContableDao.getInstancia().getConfigCuentaPorTipoConcepto(1, 3);
                    System.out.println(concepto + " config " + config);

                } else if (tipoVenta == 2) { //Venta a Credito

                    config = ConfiguracionCuentaContableDao.getInstancia().getConfigCuentaPorTipoConcepto(3, 3);
                    concepto = "Ventas de mercancia a credito ";
                    System.out.println(concepto + " config " + config);
                }

                if (!(config == null)) {

                    listaDetEntradaDiario = generarConfiguracionCuenta(config, facturaDb);
                    System.out.println("lista " + listaDetEntradaDiario);

                    EntradaDiarioDao.getInstancia()
                            .registrarEntradaDiario(facturaDb.getCodigo(), facturaDb.getCodigo().toString(), TipoDocumentoDao
                                    .getInstancia().getTipoDocumento(7).getCodigo(),
                                    config.getDescripcion(), 3, listaDetEntradaDiario, facturaDb.getFecha());

                }

                //REGISTRAR ENTRADA POR COSTO DE INVENATRIO 
                config = ConfiguracionCuentaContableDao.getInstancia().getConfigCuentaPorTipoConcepto(4, 3);

                if (!(config == null)) {

                    List<DetalleFactura> listaDetFactura = ManejoFactura.getInstancia().getDetalleFactura(facturaDb.getCodigo());

                    listaDetEntradaDiario = generarConfiguracionCuenta(config, listaDetFactura);
                    System.out.println("lista " + listaDetEntradaDiario);

                    EntradaDiarioDao.getInstancia()
                            .registrarEntradaDiario(facturaDb.getCodigo(), facturaDb.getCodigo().toString(), TipoDocumentoDao
                                    .getInstancia().getTipoDocumento(7).getCodigo(),
                                    config.getDescripcion(), 3, listaDetEntradaDiario, facturaDb.getFecha());

                }

                //CIERRE 
                //FIN ENTRADA DE DIARIO
                System.out.println("Factura Creada correctamente codigo " + facturaDb.getCodigo());

                //Actualizar caja chica
                CajaChica cajaChica = ManejoCajaChica.getInstancia().getCajaChica(new Date(), "abierta");

                if (!(cajaChica == null)) {

                    List<DetalleCajaChica> listaDetalleCajaChica = new ArrayList<>();

                    DetalleCajaChica detalleCajaChica = new DetalleCajaChica();
                    detalleCajaChica.setCajaChica(cajaChica);
                    detalleCajaChica.setTipoMovimiento(ManejoTipoMovimieto
                            .getInstancia()
                            .getTipoMovimiento(9));

                    detalleCajaChica.setConcepto("Ventas");
                    detalleCajaChica.setTipoDocumento(ManejoTipodocumento
                            .getInstancia()
                            .getTipoDocumento(7));
                    detalleCajaChica.setDocumento(facturaDb.getCodigo().toString());
                    detalleCajaChica.setMonto(facturaDb.getTotal());
                    detalleCajaChica.setNombreMovimiento("INGRESO");
                    listaDetalleCajaChica.add(detalleCajaChica);

                    cajaChica.setDetalleCajaChicaCollection(listaDetalleCajaChica);
                    ManejoCajaChica.getInstancia().actualizar(cajaChica);
                }
                //fin

                getCliente().setMontoDisponible(ManejoFactura.getInstancia().getMontoDisponible(cliente));
                ManejoCliente.getInstancia().actualizar(getCliente());

                CodigoFactura = facturaDb.getCodigo();
                System.out.println("Codigo Factura " + CodigoFactura + " Factura " + facturaDb);

//                txtNcf.setText(relacionNcf.getActual());
                if (tipoVenta == 1) { //venta de contado

                    for (FormaPago fp : listFormaPagoCollection) {

                        fp.setDocumento(facturaDb.getCodigo());
                        fp.setTipoDocumento(7);
                        fp.setFecha(util.ClaseUtil.asDate(dpFecha.getValue()));
                        fp.setUnidadDeNegocio(facturaDb.getUnidadDeNegocio().getCodigo());
                        ManejoFormaPago.getInstancia().salvar(fp);

                    }

                } else if (tipoVenta == 2) { //venta credito

                    formaPago.setDocumento(facturaDb.getCodigo());
                    formaPago.setTipoDocumento(7);
                    formaPago.setFecha(util.ClaseUtil.asDate(dpFecha.getValue()));
                    formaPago.setUnidadDeNegocio(facturaDb.getUnidadDeNegocio().getCodigo());
                    ManejoFormaPago.getInstancia().salvar(formaPago);

                }

                System.out.println("Fatura num " + facturaDb.getCodigo());

                nuevo();

            }

            //nota : si no se guarda el detalle es porque generaste las entidades nuevamente y el atributo cascae se le fue
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private List<DetalleEntradaDiario> generarConfiguracionCuenta(ConfiguracionCuentaContable config, Factura fact) {

        List<DetalleEntradaDiario> listaDetalleEnt = new ArrayList();

        DetalleEntradaDiario detalle;

        try {

            if (config == null) {

                return listaDetalleEnt;
            }

            List<DetalleConfiguaracionCuentaContable> listaDetConfiguracionCuenta;

            listaDetConfiguracionCuenta = ConfiguracionCuentaContableDao.getInstancia()
                    .getDetalleConfiguracionCuentaContable(config.getCodigo());

            for (DetalleConfiguaracionCuentaContable detConfi : listaDetConfiguracionCuenta) {

                detalle = new DetalleEntradaDiario();

                detalle.setCatalogo(detConfi.getCatalogo());
                detalle.setCuenta(detConfi.getCuenta());
                detalle.setDescripcionCuenta(detConfi.getNombreCuenta());
                detalle.setCuentaControl(detalle.getCatalogo().getCuentaControl());

                if (detConfi.getDebitar()) {

                    detalle.setDebito(fact.getTotal());
                    detalle.setCredito(0.00);

                } else if (detConfi.getAcreditar()) {

                    if (detConfi.getNombreCuenta().contains("ITBI")) {

                        detalle.setDebito(0.00);
                        detalle.setCredito(fact.getItbis());

                    } else {

                        detalle.setDebito(0.00);
                        detalle.setCredito(fact.getSubTotal());
                    }

                }

//                if (detConfi.getDebitar()) {
//
//                    detalle.setDebito(fact.getTotal());
//                    detalle.setCredito(0.00);
//
//                } else if (detConfi.getAcreditar()) {
//
//                    detalle.setDebito(0.00);
//                    detalle.setCredito(fact.getTotal());
//                }
                listaDetalleEnt.add(detalle);
                System.out.println("Det " + detalle.getCatalogo().getCuenta());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaDetalleEnt;
    }

    private List<DetalleEntradaDiario> generarConfiguracionCuenta(ConfiguracionCuentaContable config, List<DetalleFactura> detListaFactura) {

        List<DetalleEntradaDiario> listaDetalleEnt = new ArrayList();
        DetalleEntradaDiario detalle;
        try {

            if (config == null) {

                return listaDetalleEnt;
            }

            List<DetalleConfiguaracionCuentaContable> listaDetConfiguracionCuenta;

            listaDetConfiguracionCuenta = ConfiguracionCuentaContableDao.getInstancia()
                    .getDetalleConfiguracionCuentaContable(config.getCodigo());

            Double costoArticulo = 0.00;

            for (DetalleFactura det : detListaFactura) {

                costoArticulo += det.getCostoUnitario() * det.getCantidad();
                System.out.println("Costo articulo " + costoArticulo);
            }

            for (DetalleConfiguaracionCuentaContable detConfi : listaDetConfiguracionCuenta) {

                detalle = new DetalleEntradaDiario();
                detalle.setCatalogo(detConfi.getCatalogo());
                detalle.setCuenta(detConfi.getCuenta());
                detalle.setDescripcionCuenta(detConfi.getNombreCuenta());
                detalle.setCuentaControl(detalle.getCatalogo().getCuentaControl());

                if (detConfi.getDebitar()) {

                    detalle.setDebito(costoArticulo);
                    detalle.setCredito(0.00);

                } else if (detConfi.getAcreditar()) {

                    detalle.setDebito(0.00);
                    detalle.setCredito(costoArticulo);
                }

                listaDetalleEnt.add(detalle);

                System.out.println("Det " + detalle.getCatalogo().getCuenta());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaDetalleEnt;
    }

    private void btnConsultaActionEvent(ActionEvent event) throws IOException {

//    
//        try {
//
//            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/ConsultaFacturacion.fxml"));
//            li.setText("Modulo de Venta -> Consulta Factura");
//            bpVenta.setCenter(node);
//
//        } catch (IOException ex) {
//
//            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/venta/facturacion/ConsultaFacturacion.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            ClaseUtil.getStageModal(root);

            ConsultaFacturacionController articuloController = loader.getController();

            if (!(articuloController == null)) {

//                setCliente(articuloController.getCliente());
                getCliente().setMontoDisponible(ManejoFactura.getInstancia().getMontoDisponible(getCliente()));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void vbSubTituloCategoriaMouseCliked(MouseEvent event) {
        vbCategoriaArticulo.setPrefWidth(180);
        crearCategoria();
//        crearArticulo();

    }

    private void btnArticuloActionEvent(ActionEvent event) throws IOException {

        if (tbDetalleFactura.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un Articulo");

        } else {

            setArticulo(tbDetalleFactura.getSelectionModel().getSelectedItem().getArticulo());

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/inventario/articulo/RegistroArticulo.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroArticuloController registroArticuloController = loader.getController();

            registroArticuloController.setEditar(true);
            registroArticuloController.setArticulo(getArticulo());

            registroArticuloController.llenarCampo();

            ClaseUtil.getStageModal(root);
//            listadetalleFactura.remove(tbDetalleFactura.getSelectionModel().getSelectedIndex());
//            agregarArticulo();

        }
    }

    @FXML
    private void btnCategoriaActionevent(ActionEvent event) {

        lbCategoriaSeleccionada.setText("Categoria");
        vbCategoriaArticulo.setPrefWidth(180);
        vbCategoriaArticulo.setVisible(true);
        crearCategoria();
    }

    @FXML
    private void cbTipoNcfActionEvent(ActionEvent event) {

        if (!(cbTipoNcf.getSelectionModel().getSelectedIndex() == -1)) {

            TipoNcf tnf = cbTipoNcf.getSelectionModel().getSelectedItem();

            int ung = VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

            if (!(tnf.getCodigo() == 3)) {

                relacionNcf = ManejoRelacionNcf.getInstancia()
                        .getNCFUnidadDeNegocio(tnf, ung);

                if (relacionNcf == null) {

                    ung = VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo();

                    relacionNcf = ManejoRelacionNcf.getInstancia()
                            .getNCFEmpresaOGrupo(tnf, ung);//Comprobante fiscal del grupo 

                }

                if (relacionNcf == null) {
                    ClaseUtil.mensaje("El tipo de comprobante seleccionado no tiene secuencia activa");
                    cbTipoNcf.getSelectionModel().select(-1);
                    return;
                }

                txtNcf.setText(relacionNcf.getActual());

            }
        }

    }

    @FXML
    private void btnBuscarCotizacionActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/cotizacion/BuscarCotizacionDeVenta.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        BuscarCotizacionDeVentaController articuloController = loader.getController();

        articuloController.setOrigen(true);
        articuloController.inicializarCotizacion();

        ClaseUtil.getStageModal(root);

        if (!(articuloController.getCotizacionDeVenta() == null)) {

            btnBuscarCliente.setDisable(true);
            btnDescuento.setDisable(true);
            listadetalleFactura.clear();
            setContiDeVenta(articuloController.getCotizacionDeVenta());
            factura.setCotizacionDeVenta(getContiDeVenta());

            setCliente(getContiDeVenta().getCliente());
//            setDescuentoPorUsuario(ManejoDescuentoPorUsuario.getInstancia().getDescuentoPorUsuario(getContiDeVenta().getAutorizadorDescuento()));
            setEjecutivoDeVenta(getContiDeVenta().getVendedor());
            txtNombreCliente.setText(getContiDeVenta().getCliente().getNombre());
            txtVendedor.setText(getContiDeVenta().getVendedor().getNombre());

            getContiDeVenta().getDetalleCotizacionDeVentaCollection().forEach((det) -> {
                crearDetalleFactura(det);
            });

            btnEliminar.setDisable(true);
        } else {

            btnDescuento.setDisable(false);
            btnBuscarCliente.setDisable(false);
            btnEliminar.setDisable(false);
        }
    }

    @FXML
    private void btnDescuentoActionEvent(ActionEvent event) throws IOException {

        vbDescuento.setVisible(false);
        txtPorcientoMinimo.clear();
        txtPorcientoMaximo.clear();

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/descuento/usuarioDescuento.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        UsuarioDescuentoController descuentoController = loader.getController();

        ClaseUtil.getStageModal(root);

        if (!(descuentoController.getDescuentoPorUsuario() == null)) {

            if (vbDescuento.isVisible()) {
                vbDescuento.setVisible(false);
            } else {
                vbDescuento.setVisible(true);
            }

            setDescuentoPorUsuario(descuentoController.getDescuentoPorUsuario());
            txtPorcientoMinimo.setText(Double.toString(getDescuentoPorUsuario().getMinimo()));
            txtPorcientoMaximo.setText(Double.toString(getDescuentoPorUsuario().getMaximo()));
            txtPorciento.requestFocus();

        } else {

            vbDescuento.setVisible(false);
            txtPorcientoMinimo.clear();
            txtPorcientoMaximo.clear();
            setDescuentoPorUsuario(null);
        }

    }

    @FXML
    private void btnAplicarDescActionEvent(ActionEvent event) {

        try {

            aplicarDescuento();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void aplicarDescuento() {

        Double porcientoDescuento = 0.00, descuento = 0.00, valorDescuento = 0.00, valorItbis = 0.00,
                monttarItbis = 1.0;
        double itbis = 0.00;
        if (txtPorciento.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar un porciento de descuento");
            txtPorciento.requestFocus();
            return;
        }

        valorDescuento = Double.parseDouble(txtPorciento.getText());

        if (valorDescuento > Double.parseDouble(txtPorcientoMaximo.getText())) {

            ClaseUtil.mensaje("El descuento no puede ser mayor el porciento maximo");
            return;
        }

        if (valorDescuento < Double.parseDouble(txtPorcientoMinimo.getText())) {

            ClaseUtil.mensaje("El descuento no puede ser menor que  el porciento minimo");
            return;
        }

        porcientoDescuento = valorDescuento / 100;

        for (DetalleFactura detalle : listadetalleFactura) {

            if (detalle.getArticulo().getExentoItbis()) {

                valorItbis = 0.0;
                detalle.setTasaItbis(0.00);

            } else {

                itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();

                detalle.setTasaItbis(itbis);
            }

            descuento = porcientoDescuento * detalle.getSubTotal();
            detalle.setDescuento(ClaseUtil.roundDouble(descuento, 2));

            valorItbis = ClaseUtil.roundDouble((detalle.getSubTotal() - detalle.getDescuento()) * (itbis / 100), 4); //esto es cuando el precio ya tiene el itbis

            monttarItbis += (itbis / 100);
            detalle.setItbis(valorItbis);

            detalle.setPrecioItbis(detalle.getPrecio() * monttarItbis);

            detalle.setPorcientoDescuento(valorDescuento);
            detalle.setTotal((detalle.getSubTotal() - detalle.getDescuento()) + detalle.getItbis());
        }

        txtSubTotal.setText(getSubTotal().toString());
        txtDescuento.setText(getDescuento().toString());
        txtItbis.setText(getItbis().toString());
        txtTotal.setText(getTotal().toString());

        tbDetalleFactura.refresh();

    }

    @FXML
    private void btnVendedorActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/vendedor/BuscarVendedor.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        BuscarVendedorController vendedorController = loader.getController();

        if (!(vendedorController.getEjecutivoDeVenta() == null)) {

            setEjecutivoDeVenta(vendedorController.getEjecutivoDeVenta());
            txtVendedor.setText(getEjecutivoDeVenta().getNombre());

        }

    }

    @FXML
    private void cbTipoDeIngresoActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnReciboIngresoActionEvent(ActionEvent event) {

        try {

            System.out.println("factDb recibo: " + facturaDb);
            if (facturaDb == null) {

                ClaseUtil.mensaje("Tiene que guardar una factura para poder crearle el recibo ");
                return;
            }

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/cxc/reciboIngreso/RegistroReciboIngreso.fxml"));

            loader.load();

            Parent root = loader.getRoot();

            RegistroReciboDeIngresoController controller = loader.getController();
            controller.setCliente(facturaDb.getCliente());

            controller.llenarCampo();

            ClaseUtil.getStageModalcONTRATO(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class TaskMarcarProbetaParaRotura extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            try {

//                pbArticulo.setVisible(true);
                crearCategoria();
                crearArticulo();
//        crearSubCategoria(1);
                inicializarTabla();
                inicializarDatos();

            } catch (Exception e) {

                e.printStackTrace();
            }

//            pbArticulo.setVisible(false);
            return null;

        }

    }

    private VBox visualizarArticulo(Articulo articulo) {

        ImageView img = null;
        File imageFile;

        if (articulo.getRutaImg() == null) {

            imageFile = new File("foto/img_articulo.jpg");

        } else {

            imageFile = new File(articulo.getRutaImg());
        }

        img = new ImageView(new Image(imageFile.toURI().toString(), 130, 100, false, false));
        img.setFitWidth(90);
        img.setFitHeight(70);

        VBox vb = new VBox();
        VBox vbBtn = new VBox();
//        vbBtn.setAlignment(Pos.CENTER_LEFT);
        vbBtn.setSpacing(5);
        vbBtn.setPadding(new Insets(5, 5, 5, 5));

        // TextField txtCantidad = new TextField("1");
        JFXButton btnAumental = new JFXButton("+");
        JFXButton btnRestar = new JFXButton("-");
        JFXButton btnTeclado = new JFXButton("Teclado");

        // Button btnDisminur = new Button("-");
        vbBtn.getChildren().add(btnAumental);
//        vbBtn.getChildren().add(txtCantidad);
//        vbBtn.getChildren().add(btnDisminur);

        vbBtn.setAlignment(Pos.TOP_RIGHT);

        HBox hb = new HBox();

        hb.setAlignment(Pos.BOTTOM_CENTER);
//                    hb.setMaxWidth(Double.MAX_VALUE);
//                    vb.setMaxWidth(Double.MAX_VALUE);
        hb.setSpacing(15);
        hb.setPadding(new Insets(5, 5, 5, 5));

        vb.setSpacing(5);
        vb.setPadding(new Insets(5, 5, 5, 5));

        vb.setStyle("   -fx-text-fill:000000;\n"
                + " -fx-background-color: #FFFFFF;"
                + "    -fx-border-color:  #00bb2d;\n"
                + "    -fx-border-radius: 5px;\n"
                + "    -fx-background-radius: 10px;\n"
                + "    -fx-font-size: 12pt;");

        Label lbCodio = new Label("Codigo  :  " + articulo.getNumero().toString());
        Label lbPrecio = new Label("Precio :  " + Double.toString(articulo.getPrecioVenta1() == null ? 0 : articulo.getPrecioVenta1()) + "  $ ");
        Label lbNombre = new Label("Nombre :  " + articulo.getNombre() == null ? articulo.getDescripcion() : articulo.getNombre());

        lbCodio.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 15pt;");
        lbPrecio.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 15pt;");
        lbNombre.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 12pt;");
        vbBtn.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 12pt;");
        btnAumental.setStyle("   -fx-background-color: #FFFFFF;"
                + "    -fx-border-color:  #00bb2d;\n"
                + "    -fx-background-radius: 10px;\n");
        btnRestar.setStyle("   -fx-background-color: #FFFFFF;"
                + "    -fx-border-color:  #00bb2d;\n"
                + "    -fx-background-radius: 10px;\n");
        btnTeclado.setStyle("   -fx-background-color: #FFFFFF;"
                + "    -fx-border-color:  #00bb2d;\n"
                + "    -fx-background-radius: 10px;\n");

//        btnAumental.setStyle("   -fx-text-fill:000000;\n"
//                + " -fx-background-color: #FFFFFF;"
//                + "    -fx-border-color:  #00bb2d;\n"
//                + "    -fx-border-radius: 5px;\n"
//                + "    -fx-background-radius: 10px;\n"
//                + "    -fx-font-size: 12pt ;");
//      
        hb.getChildren().add(btnAumental);
        hb.getChildren().add(btnRestar);
        hb.getChildren().add(btnTeclado);

        vb.getChildren().add(lbCodio);

        vb.getChildren().add(lbNombre);
//        Separator separador = new Separator();
//        vb.getChildren().add(separador);

        vb.getChildren().add(hb);
//        hb.getChildren().add(img);
//        hb.getChildren().add(vb);
//        hb.getChildren().add(vbBtn);
        vb.setCursor(Cursor.HAND);
        return vb;

    }

    private void agruparDetallePorArticulo(DetalleFactura det) {

        for (DetalleFactura detalle : tbDetalleFactura.getItems()) {

            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())
                    && Objects.equals(detalle.getUnidad().getCodigo(), det.getUnidad().getCodigo())) {

                detalle.setCantidad(detalle.getCantidad() + det.getCantidad());
                detalle.setSubTotal(detalle.getSubTotal() + det.getSubTotal());
                detalle.setTotal(detalle.getTotal() + det.getTotal());
                detalle.setDescuento(detalle.getDescuento() + det.getDescuento());
                detalle.setItbis(detalle.getItbis() + det.getItbis());

                tbDetalleFactura.refresh();

            }
        }

    }

    private void editarDetalleArticulo(DetalleFactura det) {

        try {

            detalleFactura = det;
//            catidadArticulo = det.getCantidad();
//            setArticulo(detalleFactura.getArticulo());
            Double total = 0.00, subTotal = 0.00, valorItbis = 0.00, precioVenta = 0.00,
                    precioVentaItbis = 0.00, valorEnPeso = 0.00, valorImpuesto = 0.00;

            if (unidadDespacho == 2) {

                subTotal = valorEnPeso;

            } else {

                subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
            }

//            subTotal=ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(),2);
            if (detalleFactura.getArticulo().getExentoItbis()) {

                valorItbis = 0.0;
                detalleFactura.setTasaItbis(0.00);

            } else {

                double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();

                if (det.getMontoExcento() > 0) {
                    valorItbis = ClaseUtil.roundDouble((det.getMontoGravado()) * (itbis / 100), 2);
                } else {
                    valorItbis = ClaseUtil.roundDouble((subTotal - detalleFactura.getDescuento()) * (itbis / 100), 2);
                }

                valorImpuesto = ClaseUtil.roundDouble((subTotal - detalleFactura.getDescuento()) * (itbis / 100), 2);
                detalleFactura.setPrecioItbis(precioVentaItbis);
                detalleFactura.setTasaItbis(itbis);
            }

            total = ClaseUtil.roundDouble((subTotal - detalleFactura.getDescuento()) + valorItbis, 2);
            detalleFactura.setItbis(valorItbis);
            detalleFactura.setSubTotal(subTotal);
            detalleFactura.setTotal(total);
            detalleFactura.setMontoItbisExcento(ClaseUtil.roundDouble(valorImpuesto - detalleFactura.getItbis(), 2));
            detalleFactura = calcularRetencion(detalleFactura);

            detalleFactura.setFactura(factura);

            txtCodigoArticulo.clear();
            iniciazarFiltro();

            txtSubTotal.setText(getSubTotal().toString());
            txtDescuento.setText(getDescuento().toString());
            txtItbis.setText(getItbis().toString());
            txtTotal.setText(getTotal().toString());
//            txtTotal1.setText(getTotalACobrar().toString());
            txtMontoExcento.setText(getMontoExcento().toString());
            txtMontoGravado.setText(getMontoGravado().toString());
//            txtItbisExcento.setText(getMontoItbisExcento().toString());
            btnFormaPago.setDisable(false);
            setArticulo(null);

            txtCodigoArticulo.requestFocus();

            if (tipoVenta == 1) {

                btnFormaPago.setDisable(false);
            } else if (tipoVenta == 2) {
                btnFormaPago.setDisable(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void agregarOpciones() {

        MenuModulo menuModulo;

        List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(8);
        int codigoModulo = ManejoModulo.getInstancia().getModulo(8).getCodigo();
        String nombreModulo = ManejoModulo.getInstancia().getModulo(8).getNombre();

        boolean actualizando = false;

        for (Node n : vbMenu.getChildren()) {

            if (!(n.getId() == null)) {

                menuModulo = new MenuModulo();

                menuModulo.setIdMenu(n.getId());
                menuModulo.setMenu(n.getAccessibleText());
                menuModulo.setModulo(codigoModulo);
                menuModulo.setNombreModulo(nombreModulo);

                for (MenuModulo memu : listaMenuModulo) {

                    if (n.getId().equals(memu.getIdMenu())) {

                        menuModulo = memu;
                        actualizando = true;
                        break;
                    }
                }

                if (actualizando) {

                    ManejoMenuModulo.getInstancia().actualizar(menuModulo);

                } else {

                    ManejoMenuModulo.getInstancia().salvar(menuModulo);
                }

                n.setDisable(true);
            }

        }
    }

    private void agregarOpcionesDeArticulo() {

        MenuModulo menuModulo;

        List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(8);
        int codigoModulo = ManejoModulo.getInstancia().getModulo(8).getCodigo();
        String nombreModulo = ManejoModulo.getInstancia().getModulo(8).getNombre();

        boolean actualizando = false;

        for (Node n : vbMenuArticulo.getChildren()) {

            if (!(n.getId() == null)) {

                menuModulo = new MenuModulo();

                menuModulo.setIdMenu(n.getId());
                menuModulo.setMenu(n.getAccessibleText());
                menuModulo.setModulo(codigoModulo);
                menuModulo.setNombreModulo(nombreModulo);

                for (MenuModulo memu : listaMenuModulo) {

                    if (n.getId().equals(memu.getIdMenu())) {

                        menuModulo = memu;
                        actualizando = true;
                        break;
                    }
                }

                if (actualizando) {

                    ManejoMenuModulo.getInstancia().actualizar(menuModulo);

                } else {

                    ManejoMenuModulo.getInstancia().salvar(menuModulo);
                }

            }

            n.setDisable(true);

        }
    }

    private void activarOpciones() {

        if (codigoRol == 1) {//rol de administrador
            activacionPuntuales(false);

            for (Node n : vbMenu.getChildren()) {
                n.setDisable(false);
                tbArticulo.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, 8);

            for (Node n : vbMenu.getChildren()) {

                if (!(n.getId() == null)) {

                    for (RolMenuModulo memu : listaRolMenuModulos) {

                        if (n.getId().equals(memu.getMenuModulo().getIdMenu())) {
                            n.setDisable(!memu.getHabilitado());

                            activacionPuntuales(!memu.getHabilitado());

//                            tbArticulo.setDisable(!memu.getHabilitado());
//                            btnCategoria.setDisable(!memu.getHabilitado());
//                            btnBuscarArticulo.setDisable(!memu.getHabilitado());
//                            btnBuscarCliente.setDisable(!memu.getHabilitado());
//                            btnEliminar.setDisable(!memu.getHabilitado());
                        }
                    }

                }

            }
        }

    }

    private void activarOpcionesDeArticulo() {

        if (codigoRol == 1) {//rol de administrador
            btnBuscarCliente.setDisable(false);
            for (Node n : vbMenuArticulo.getChildren()) {
                n.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, 8);

            for (Node n : vbMenuArticulo.getChildren()) {

                if (!(n.getId() == null)) {

                    for (RolMenuModulo memu : listaRolMenuModulos) {

                        if (n.getId().equals(memu.getMenuModulo().getIdMenu())) {
                            n.setDisable(!memu.getHabilitado());
                            btnBuscarCliente.setDisable(false);

                        }
                    }

                }

            }
        }

    }

    private void activacionPuntuales(boolean activado) {

        tbArticulo.setDisable(activado);
        btnCategoria.setDisable(activado);
        btnBuscarArticulo.setDisable(activado);
        btnBuscarCliente.setDisable(activado);
        btnEliminar.setDisable(activado);
    }

    private void crearDetalleFactura(DetalleCotizacionDeVenta detCotiz) {

        detalleFactura = new DetalleFactura();
        detalleFactura.setArticulo(detCotiz.getArticulo());

        detalleFactura.setCantidad(detCotiz.getCantidad());
        detalleFactura.setDescuento(detCotiz.getDescuento());
        detalleFactura.setNombreArticulo(detCotiz.getArticulo().getNombre());
        detalleFactura.setAlmacen(detCotiz.getAlmacen());

        ExistenciaArticulo exisArt = ManejoExistenciaArticulo.getInstancia()
                .getExistenciaArticulo(detalleFactura.getArticulo().getCodigo(),
                        detalleFactura.getAlmacen().getCodigo());

        detalleFactura.setExistencia(exisArt.getExistencia());

        detalleFactura.setPrecio(detCotiz.getPrecio());
        detalleFactura.setPrecioItbis(detCotiz.getPrecioItbis());
        detalleFactura.setUnidad(detCotiz.getUnidad());
        detalleFactura.setPorcientoDescuento(detCotiz.getPorcientoDescuento());
        detalleFactura.setDescuento(detCotiz.getDescuento());

        Integer unidadInventario = ManejoArticuloUnidad.getInstancia()
                .getArticuloUnidadSslida(detalleFactura.getArticulo().getCodigo()).getUnidad().getCodigo();

        ArticuloUnidad artUnidad = ManejoArticuloUnidad.getInstancia()
                .getArticuloUnidadSslida(detalleFactura.getArticulo().getCodigo(),
                        detalleFactura.getUnidad().getCodigo());

        detalleFactura.setUnidadInventario(unidadInventario);

        detalleFactura.setCantidadInventario(detalleFactura.getCantidad() * artUnidad.getFatorVenta());
        detalleFactura.setNuevaExistencia(exisArt.getExistencia() - detalleFactura.getCantidadInventario());
        detalleFactura.setCostoUnitario(artUnidad.getCostoUnitario());

        detalleFactura.setFactorUnidadSalida(artUnidad.getFatorVenta());
        detalleFactura.setListaDePrecio(ManejoListaDePrecio.getInstancia().getListaDePrecio(1).getCodigo());

        detalleFactura.setItbis(detCotiz.getItbis());
        detalleFactura.setTasaItbis(detCotiz.getTasaItbis());
        detalleFactura.setSubTotal(detCotiz.getSubTotal());
        detalleFactura.setTotal(detCotiz.getTotal());
        detalleFactura.setMontoExcento(0.00);
        detalleFactura.setMontoGravado(detCotiz.getSubTotal());
        detalleFactura.setIsrRetenido(0.00);

        detalleFactura.setFactura(factura);

        if (existe(detalleFactura)) {

            agruparDetallePorArticulo(detalleFactura);

        } else if (!existe(detalleFactura)) {

            listadetalleFactura.add(detalleFactura);
        }

        txtCodigoArticulo.clear();
        iniciazarFiltro();

        txtSubTotal.setText(getSubTotal().toString());
        txtDescuento.setText(getDescuento().toString());
        txtItbis.setText(getItbis().toString());
        txtTotal.setText(getTotal().toString());

        detalleFactura = new DetalleFactura();
        btnFormaPago.setDisable(false);
        setArticulo(null);
//            GUIUtils.autoFitTable(tbDetalleFactura);
//            crearArticulo();
        txtCodigoArticulo.requestFocus();

        if (tipoVenta == 1) {

            btnFormaPago.setDisable(false);
        } else if (tipoVenta == 2) {
            btnFormaPago.setDisable(true);
        }
    }

    private DetalleFactura calcularRetencion(DetalleFactura detFact) {

        int tipoServicio = 0, tipoCliente;
        tipoCliente = getCliente().getTipoCliente().getCodigo();
        if (detFact.getArticulo().getTipoArticulo().getCodigo() == 3) {
            tipoServicio = detFact.getArticulo().getTipoServicio();
        }

        Double valorRetenidoItbis = 0.00, valorRetenidoIsr = 0.00;

        if (detFact.getArticulo().getTipoArticulo().getCodigo() == 3) {//3 ES EL ARTICULO DE SERVICIO

            valorRetenidoItbis = RetencionDGII.retencionITBISPorServicio(detFact.getItbis(), tipoServicio, tipoCliente);

            if (tipoCliente == 2) {//2 ES SOCIO DE NEGOCIO INFORMAL

                Double monto = detFact.getMontoExcento() > 0 ? detFact.getMontoGravado() : detFact.getSubTotal();
                valorRetenidoIsr = RetencionDGII.retencionISRPorServicio(monto, tipoServicio);
            }

        } else if (!(detFact.getArticulo().getTipoArticulo().getCodigo() == 3)) {

            valorRetenidoItbis = RetencionDGII.retencionITBISPorBienes(detFact.getItbis(), tipoCliente);
        }

        detFact.setIsrRetenido(valorRetenidoIsr);
        detFact.setTotalACobrar(detFact.getTotal() - (valorRetenidoIsr + valorRetenidoItbis));
        detFact.setItbisRetenido(valorRetenidoItbis);

        return detFact;
    }

}
