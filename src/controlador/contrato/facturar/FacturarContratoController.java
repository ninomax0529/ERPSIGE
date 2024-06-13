/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.facturar;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.cxc.reciboIngreso.RegistroReciboDeIngresoController;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.ManejoConfiguracion;
import manejo.articulo.ManejoAlmacen;
import manejo.articulo.ManejoArticulo;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.articulo.ManejoListaDePrecio;
import manejo.cliente.ManejoPlazo;
import manejo.contrato.ManejoContratoDeServicio;
import manejo.contrato.ManejoCorteDeFacturacion;
import manejo.contrato.ManejoFacturaDatosDeVehiculo;
import manejo.contrato.ManejoFacturaDatosDeVehiculoTemp;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoFacturaTemporal;
import manejo.factura.ManejoFormaPago;
import manejo.factura.ManejoHistoricoBalancePendiente;
import manejo.factura.ManejoOrigenFactura;
import manejo.factura.ManejoRelacionNcf;
import manejo.factura.ManejoTipoDeIngreso;
import manejo.factura.ManejoTipoFormaPago;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import manejo.unidad.ManejoUnidad;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.CondicionPago;
import modelo.ContratoDeServicio;
import modelo.CorteDeFacturacion;
import modelo.CotizacionDeVenta;
import modelo.DatosDeVehiculo;
import modelo.DetalleContratoDeServicio;
import modelo.DetalleCorteDeFacturacion;
import modelo.DetalleFactura;
import modelo.DetalleFacturaTemporal;
import modelo.DetalleListaDePrecio;
import modelo.ExistenciaArticulo;
import modelo.Factura;
import modelo.FacturaDatosDeVehiculo;
import modelo.FacturaDatosDeVehiculoTemp;
import modelo.FacturaTemporal;
import modelo.FormaPago;
import modelo.HistoricoBalancePendiente;
import modelo.MenuModulo;
import modelo.RelacionNcf;
import modelo.RolMenuModulo;
import modelo.SecuenciaDocumento;
import modelo.TipoFormaPago;
import modelo.Unidad;
import reporte.unidadnegocio.RptFactura;
import utiles.ClaseUtil;
import utiles.FormatNum;
import utiles.VariablesGlobales;
import vista.venta.facturacion.TecladoDigitalController;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FacturarContratoController implements Initializable {

    @FXML
    private TabPane tabPaneFactura;
    @FXML
    private JFXButton btnReciboIngreso;

    /**
     * @return the contiDeVenta
     */
    public CotizacionDeVenta getContiDeVenta() {
        return contiDeVenta;
    }

    /**
     * @param contiDeVenta the contiDeVenta to set
     */
    public void setContiDeVenta(CotizacionDeVenta contiDeVenta) {
        this.contiDeVenta = contiDeVenta;
    }

    @FXML
    private JFXTextField txtCodigoArticulo;
    @FXML
    private Button btnBuscarArticulo;
    @FXML
    private TableView<Articulo> tbArticulo;
    @FXML
    private TableColumn<Articulo, Articulo> tbcImagenArticulo;
    @FXML
    private JFXTextField txtSubTotal;
    @FXML
    private JFXTextField txtItbis;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXButton btnEliminar;
    FacturaTemporal facturaTemporal;

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

    @FXML
    private VBox vbVisorDeProgreso;
    @FXML
    private Label lbProgreso;
    @FXML
    private ProgressIndicator pgIndicador;
    @FXML
    private ProgressBar progresBar;
    @FXML
    private JFXButton btnBuscarContratoParaFacturar;
    @FXML
    private JFXButton btnGenerarFactura;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private TableColumn<ContratoDeServicio, ContratoDeServicio> tbcFacturar;
    @FXML
    private JFXCheckBox chFacturar;
    @FXML
    private TableView<DetalleFacturaTemporal> tbDetalleFactura;
    @FXML
    private TableColumn<DetalleFacturaTemporal, String> tbcArticulo;
    @FXML
    private TableColumn<DetalleFacturaTemporal, Double> tbcCantidad;
    @FXML
    private TableColumn<DetalleFacturaTemporal, Double> tbcPrecioUnitario;
    @FXML
    private TableColumn<DetalleFacturaTemporal, Double> tbcImporte;
    @FXML
    private TableColumn<DetalleFacturaTemporal, String> tbcUnidadSalida;
    @FXML
    private TableColumn<DetalleFacturaTemporal, Double> tbcItbis;

    /**
     * @return the codigoMenuModulo
     */
    public Integer getCodigoMenuModulo() {
        return codigoMenuModulo;
    }

    /**
     * @param codigoMenuModulo the codigoMenuModulo to set
     */
    public void setCodigoMenuModulo(Integer codigoMenuModulo) {
        this.codigoMenuModulo = codigoMenuModulo;
    }

    @FXML
    private JFXDatePicker dpFechaFacturacion;

    @FXML
    private TableView<ContratoDeServicio> tbContratoDeServicio;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcNumContratoContra;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcClienteContra;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcTipoDeContrato;
    @FXML
    private TableColumn<ContratoDeServicio, Double> tbcMontoContra;
    @FXML
    private Label lbCantidadContrato;
    @FXML
    private TableView<FacturaTemporal> tbFacturaDeContrato;
    @FXML
    private TableColumn<FacturaTemporal, String> tbcNoContratoFact;
    @FXML
    private TableColumn<FacturaTemporal, Integer> tbcFacturaFact;
    @FXML
    private TableColumn<FacturaTemporal, String> tbcClienteFact;
    @FXML
    private TableColumn<FacturaTemporal, Double> tbcMontoFact;

    @FXML
    private TableColumn<FacturaTemporal, FacturaTemporal> tbcVerFactura;

    @FXML
    private Label lbCantidadFactura;

    ObservableList<ContratoDeServicio> listaContrato = FXCollections.observableArrayList();
    ObservableList<Factura> listaFactura = FXCollections.observableArrayList();

    ObservableList<DetalleFactura> listaDetalleFactura = FXCollections.observableArrayList();
    ObservableList<DetalleFacturaTemporal> listaDetFactArticuloNuevo = FXCollections.observableArrayList();
    ObservableList<FacturaDatosDeVehiculo> listaDetalleFacDdatosVehiculo = FXCollections.observableArrayList();
    ObservableList<FacturaDatosDeVehiculoTemp> listaDetalleFacDdatosVehiculoTemp = FXCollections.observableArrayList();

    ObservableList<FacturaTemporal> listaFacturaTemporal = FXCollections.observableArrayList();

    ObservableList<DetalleFacturaTemporal> listaDetalleFacturaTemporal = FXCollections.observableArrayList();
    ObservableList<DetalleFacturaTemporal> listadetalleFactura = FXCollections.observableArrayList();
    ObservableList<Articulo> listaArticulos = FXCollections.observableArrayList();

    RelacionNcf relacionNcf = null;
    @FXML
    private HBox hbPermisos;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    private Integer codigoMenuModulo;
    TaskGenerarFacturaTemporales taskGenerarFacturaTemporales;
    TaskGenerarFactura taskGenerarFactura;
    Date fechaVencimiento = null;
    private Articulo articulo;
    Double precioVenta;
    private CotizacionDeVenta contiDeVenta;
    int unidadDespacho = 1;
    Integer cantidad = 0;
    Double catidadArticulo = 0.0;

    Double existenciaEnPeso = 0.00;

    Factura factDb = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablaContrato();
        inicializarTablaFactura();
        inicializarTablaDetFact();
        inicializarArticulo();
        iniciazarFiltro();
        dpFechaFacturacion.setValue(LocalDate.now());
        dpFechaFacturacion.setDisable(true);
        setCodigoMenuModulo(ManejoMenuModulo.getInstancia().getMenuModulo(9, "btnFacturarContrato"));
        agregarOpciones();
        activarOpciones();

        btnGenerarFactura.setDisable(true);
        vbVisorDeProgreso.setVisible(false);

    }

    public void inicializarTablaDetFact() {

        listaDetalleFacturaTemporal.clear();
        tbDetalleFactura.setItems(listaDetalleFacturaTemporal);

        tbcArticulo.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcUnidadSalida.setCellValueFactory(new PropertyValueFactory<>("unidadSalida"));
        tbcImporte.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        tbcItbis.setCellValueFactory(new PropertyValueFactory<>("itbis"));
        tbcPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tbcUnidadSalida.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

    }

    private void inicializarArticulo() {

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

                        HBox hbox = visualizarArticulo(item);

                        hbox.setOnMouseClicked((event) -> {

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

                                    if (tecladoDigitalController.getArticuloDTO() == null) {
                                        ClaseUtil.mensaje("EL ARTICULO NO TIENE UNIDAD CONFIGURADA ");
                                        return;
                                    }
                                    int unidad = tecladoDigitalController.getArticuloDTO().getCodigoUnidad();
                                    int listaPrecio = tecladoDigitalController.getArticuloDTO().getListaDePrecio();
                                    int almacen = tecladoDigitalController.getArticuloDTO().getAlmacen();
                                    String nombreUnidad = tecladoDigitalController.getArticuloDTO().getUnidad();
                                    precioVenta = tecladoDigitalController.getArticuloDTO().getPrecioVenta();

                                    System.out.println("Precio de Venta  " + precioVenta + " lista precio  " + listaPrecio);
                                    Double existencia = ManejoExistenciaArticulo
                                            .getInstancia()
                                            .getExistenciaArticulo(getArticulo().getCodigo(), almacen, unidad, listaPrecio);

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

//                        catidadArticulo = Double.parseDouble(tecladoDigitalController.getTxtCAtidad().getText());
//
//                        if (getArticulo().getExistencia() < catidadArticulo) {
//
//                            ClaseUtil.mensaje("La cantidad a Despachar " + catidadArticulo
//                                    + "  no puede ser mayor que la existencia ,Existencia igual a " + getArticulo().getExistencia());
//
//                            return;
//                        }
//                                    agregarArticulo();
                                    if (!(getContiDeVenta() == null)) {
                                        ClaseUtil.mensaje(" Esta factura esta con una cotizacion ");
                                        return;
                                    }

                                    agregarArticuloConPrecioDeLista(unidad, almacen, listaPrecio);

                                }

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

        listaArticulos.addAll(ManejoArticulo.getInstancia().getListaArticuloPorUnidadDeNegocio());
//        listaArticulos.addAll(ManejoArticulo.getInstancia().getListaArticulos(2));

        tbArticulo.setItems(listaArticulos);
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

    public void inicializarTablaContrato1() {

        try {

            listaContrato.addAll(ManejoContratoDeServicio.getInstancia().getListaContratoSinFaturar());
            tbcNumContratoContra.setCellValueFactory(new PropertyValueFactory<>("numeroDeContrato"));
            tbcClienteContra.setCellValueFactory(new PropertyValueFactory<>("cliente"));
            tbcTipoDeContrato.setCellValueFactory(new PropertyValueFactory<>("frecuenciaDePago"));
            tbcMontoContra.setCellValueFactory(new PropertyValueFactory<>("totalAPagar"));

            tbcClienteContra.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getCliente() != null) {
                            property.setValue(cellData.getValue().getCliente().getNombre());
                        }
                        return property;
                    });

            tbcTipoDeContrato.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFrecuenciaDePago() != null) {
                            property.setValue(cellData.getValue().getFrecuenciaDePago().getFrecuencia());
                        }
                        return property;
                    });

            tbContratoDeServicio.setItems(listaContrato);
            lbCantidadContrato.setText(Integer.toString(listaContrato.size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTablaContrato() {

        try {

            tbcNumContratoContra.setCellValueFactory(new PropertyValueFactory<>("numeroDeContrato"));
            tbcClienteContra.setCellValueFactory(new PropertyValueFactory<>("cliente"));

            tbcTipoDeContrato.setCellValueFactory(new PropertyValueFactory<>("frecuenciaDePago"));
            tbcMontoContra.setCellValueFactory(new PropertyValueFactory<>("totalAPagar"));

            tbcClienteContra.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getCliente() != null) {
                            property.setValue(cellData.getValue().getCliente().getNombre());
                        }
                        return property;
                    });

            tbcTipoDeContrato.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFrecuenciaDePago() != null) {
                            property.setValue(cellData.getValue().getFrecuenciaDePago().getFrecuencia());
                        }
                        return property;
                    });

            tbcFacturar.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcFacturar.setCellFactory((TableColumn<ContratoDeServicio, ContratoDeServicio> param) -> {

                TableCell<ContratoDeServicio, ContratoDeServicio> cellsc = new TableCell<ContratoDeServicio, ContratoDeServicio>() {
                    @Override
                    public void updateItem(ContratoDeServicio item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;

                        if (item != null) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(50.0, 20.0);
                            item.setFacturado(false);

                            if (item.getFacturado()) {

                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            } else {

                                btnHabilitar.setText("NO");
                                btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            }

                            HBox hbox = new HBox();

                            hbox.getChildren().add(btnHabilitar);

                            hbox.setAlignment(Pos.CENTER);

                            btnHabilitar.setOnMouseClicked((event) -> {

                                if (item.getFacturado()) {

                                    item.setFacturado(false);
                                    btnHabilitar.setText("NO");
                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 15px;\n"
                                            + "    -fx-b-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 12pt;");

                                } else {

                                    item.setFacturado(true);
                                    btnHabilitar.setText("SI");

                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 12pt;");
                                }

                            });

                            setGraphic(btnHabilitar);
                            setText(null);
                        } else {
                            setGraphic(null);
                            setText(null);
                        }
                    }
                };
                return cellsc;
            });

            tbContratoDeServicio.setItems(listaContrato);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTablaFactura() {

        try {

            tbcNoContratoFact.setCellValueFactory(new PropertyValueFactory<>("numeroContrato"));
            tbcClienteFact.setCellValueFactory(new PropertyValueFactory<>("cliente"));

            tbcFacturaFact.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tbcMontoFact.setCellValueFactory(new PropertyValueFactory<>("total"));

            tbcClienteFact.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getCliente() != null) {
                            property.setValue(cellData.getValue().getCliente().getNombre());
                        }
                        return property;
                    });

            tbcVerFactura.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcVerFactura.setCellFactory((TableColumn<FacturaTemporal, FacturaTemporal> param) -> {

                TableCell<FacturaTemporal, FacturaTemporal> cellsc = new TableCell<FacturaTemporal, FacturaTemporal>() {
                    @Override
                    public void updateItem(FacturaTemporal item, boolean empty) {
                        super.updateItem(item, empty);
                        File imageFile;
                        Image img;
                        ImageView imageview;
                        Label label;

                        if (item != null) {

//                        imageFile = new File(getClass().getResource("/imagen/img_documento.jpg").toString());
                            label = new Label("Componente");
                            imageview = new ImageView(new Image(getClass().getResource("/imagen/img_documento.jpg").toString(), 40, 20, false, false));
                            imageview.setFitWidth(40);
                            imageview.setFitHeight(20);

                            VBox hbox = new VBox();

                            hbox.getChildren().addAll(imageview);

                            hbox.setAlignment(Pos.CENTER);

                            //Evento de la fila 
                            hbox.setOnMouseClicked((event) -> {

                                RptFactura.getInstancia().verFacturaTemporal(item.getCodigo(),
                                        VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

//                                RptFacturaIghTrack.getInstancia().verFacturaTemporal(item.getCodigo());
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

            tbFacturaDeContrato.setItems(listaFacturaTemporal);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        try {

            listaContrato.clear();
            lbCantidadContrato.setText("");
            listaContrato.addAll(ManejoContratoDeServicio.getInstancia().getListaContratoSinFaturar());

            lbCantidadContrato.setText(cantidadContrato().toString());

            if (listaContrato.size() <= 0) {

                btnGenerarFactura.setDisable(true);
                ClaseUtil.mensaje("No hay  contrato para generarle factura en esta fecha ");

            } else {

                for (ContratoDeServicio contrato : listaContrato) {

                    if (contrato.getCliente().getTipoNcf() == null) {
                        ClaseUtil.mensaje("EL CLIENTE " + contrato.getCliente().getNombre()
                                + " DEL CONTRATO " + contrato.getNumero() + " TIENE QUE CONFIGUARLE EL TIPO DE FACTURACION ");

                        return;
                    }
                }

                btnGenerarFactura.setDisable(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {

        try {

            for (ContratoDeServicio contrato : listaContrato) {

                if (contrato.getCliente().getTipoNcf() == null) {
                    ClaseUtil.mensaje("EL CLIENTE " + contrato.getCliente().getNombre()
                            + " DEL CONTRATO " + contrato.getNumero() + " TIENE QUE CONFIGURALE EL TIPO DE COMPROBANTE ");
                    listaFacturaTemporal.clear();
                    return;
                }
            }

            ManejoFacturaTemporal.getInstancia().eliminarFacturaTemporal();
            ManejoFacturaDatosDeVehiculoTemp.getInstancia().eliminarFacturaDatosVehiculoTemporal();

            listaFacturaTemporal.clear();
            for (ContratoDeServicio contrato : listaContrato) {

                if (contrato.getFacturado()) {
                    guardarFacturatemporales(contrato);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void tbContratoDeServicioClicked(MouseEvent event) {
    }

    private Integer cantidadContrato() {

        return listaContrato.size();
    }

    private Integer cantidadFactura() {

        return listaFacturaTemporal.size();
    }

    @FXML
    private void tbFacturaDeContratoMouseClicked(MouseEvent event) {

        if (!(tbFacturaDeContrato.getSelectionModel().getSelectedIndex() == -1)) {

            facturaTemporal = tbFacturaDeContrato.getSelectionModel().getSelectedItem();

            if (event.getClickCount() == 2) {
                tabPaneFactura.getSelectionModel().select(1);
            }
        }
    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            System.out.println("listaContrato " + listaDetalleFacturaTemporal.size());
            if (listaDetalleFacturaTemporal.size() <= 0) {

                ClaseUtil.mensaje("No hay factura para guardar");
                return;
            }

            Optional<ButtonType> ok = ClaseUtil.confirmarMensaje(" Seguro que quiere guardar esta facturas ");

            if (ok.get() == ButtonType.NO) {

                return;
            }

            System.out.println("hay factura para guardar ");
            for (ContratoDeServicio contrato : listaContrato) {

                if (contrato.getFacturado()) {
                    guardarFacturaContrato(contrato);
                }

            }

            ManejoFacturaTemporal.getInstancia().eliminarFacturaTemporal();
            ManejoFacturaDatosDeVehiculoTemp.getInstancia().eliminarFacturaDatosVehiculoTemporal();
            listaFacturaTemporal.clear();
            listaContrato.clear();

//            ClaseUtil.mensaje("Factura guardada correctamente ");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Factura guardar(Factura fac) {

        Factura facturaDb = null;

        try {

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(fac.getUnidadDeNegocio().getCodigo(), 7);

            if (!(secDoc == null)) {

                boolean existe = ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero());

                if (existe) {

                    System.out.println("existe " + secDoc.getNumero());

                    while (ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero())) {

                        secDoc.setNumero(secDoc.getNumero() + 1);
                        ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                    }

                    secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(fac.getUnidadDeNegocio().getCodigo(), 7);

                    fac.setNumero(secDoc.getNumero());
                    fac.setSecuenciaDocumento(secDoc);

                } else {

                    System.out.println("No existe " + secDoc.getNumero());
                    fac.setNumero(secDoc.getNumero());
                    fac.setSecuenciaDocumento(secDoc);
                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

            } else {

                ClaseUtil.mensaje("La secuencia para la factura de la unidad de negocio "
                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return facturaDb;
            }

            System.out.println("");
            fac.setTipoNcf(fac.getCliente().getTipoNcf());//ponemos el tipo de ncf
            fac.setNombreTipoNcf(fac.getCliente().getTipoNcf().getNombre());
//                factura.setTipoNcf(cbTipoNcf.getSelectionModel().getSelectedItem());

            System.out.println("fac.getCliente().getTipoNcf() " + fac.getCliente().getTipoNcf() + " fac.getTipoNcf() " + fac.getTipoNcf());

            int ung = fac.getUnidadDeNegocio().getCodigo();
            boolean empresa = false;
            relacionNcf = ManejoRelacionNcf.getInstancia()
                    .getNCFUnidadDeNegocio(fac.getCliente().getTipoNcf(), fac.getUnidadDeNegocio().getCodigo());

            if (relacionNcf == null) {//SI ES IGUAL A NULL LA UNIDAD DE NEGOCIO NO TIENE COMPROBANTE FISCALES CONFIGURADO

                ung = fac.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo();

                relacionNcf = ManejoRelacionNcf.getInstancia()
                        .getNCFEmpresaOGrupo(fac.getTipoNcf(), ung);//Comprobante fiscal del grupo fs comercial
                empresa = true;

//                ung = 3;
//                System.out.println(" EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL  " + relacionNcf);
////
////                ClaseUtil.mensaje(" La secuencia de ncf para el tipo " + fac.getCliente().getTipoNcf().getDescripcion() + " de la unidad de negocio "
////                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
//
////                System.out.println("relacionNcf  " + relacionNcf);
//                relacionNcf = ManejoRelacionNcf.getInstancia()
//                        .getNCFUnidadDeNegocio(fac.getCliente().getTipoNcf(), 3);//EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL
//                System.out.println(" EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL  " + relacionNcf);
//               return null;
            }

            System.out.println("relacionNcf  " + relacionNcf);
            System.out.println("ncf actual  " + relacionNcf.getActual());

            boolean existe = ManejoFactura.getInstancia().existeNCF(relacionNcf.getActual(), fac.getUnidadDeNegocio().getCodigo());

            if (existe) {

                while (ManejoFactura.getInstancia().existeNCF(relacionNcf.getActual(), fac.getUnidadDeNegocio().getCodigo())) {

                    relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo(), ung, empresa);

                }

            } else {

                relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo(), ung, empresa);

            }

            System.out.println(" ncf obtenido   " + relacionNcf.getActual());

            fac.setNcf(relacionNcf.getActual());
            fac.setNcfFechaValidoHasta(relacionNcf.getFechaValidoHasta());
            fac.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

//            System.out.println("Sin ncf");
            facturaDb = ManejoFactura.getInstancia().salvar(fac);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return facturaDb;
    }

    private Double getSubTotal(List<DetalleFactura> lista) {

        Double subTotal = 0.00;

        for (DetalleFactura det : lista) {

            subTotal += det.getSubTotal();

        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotal(List<DetalleFactura> lista) {

        Double total = 0.00;

        for (DetalleFactura det : lista) {

            total += det.getTotal();

        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getItbis(List<DetalleFactura> lista) {

        Double itbis = 0.00;

        for (DetalleFactura det : lista) {

            itbis += det.getItbis();

        }

        return FormatNum.FormatearDouble(itbis, 2);
    }

    private void btnExportarPdfActionEvent(ActionEvent event) {

        try {

            Date fecha = ClaseUtil.asDate(dpFechaFacturacion.getValue());
            ManejoFactura.getInstancia().enviarFacturaPorCorreoEnPdf(fecha);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Agrega las opciones de menu a la base de datos
    private void agregarOpciones() {

        MenuModulo menuModulo;

        List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(9);
        int codigoModulo = ManejoModulo.getInstancia().getModulo(9).getCodigo();
        String nombreModulo = ManejoModulo.getInstancia().getModulo(9).getNombre();

        boolean actualizando = false;

        for (Node n : hbPermisos.getChildren()) {

            System.out.println("n.getId() " + n.getId());
            if (!(n.getId() == null)) {

                System.out.println("n.getId() no es null " + n.getId());
                menuModulo = new MenuModulo();

                menuModulo.setIdMenu(n.getId());
                menuModulo.setMenu(n.getAccessibleText());
                menuModulo.setModulo(codigoModulo);
                menuModulo.setNombreModulo(nombreModulo);
                menuModulo.setPermiso(getCodigoMenuModulo());
//                menuModulo.setTipoMenu(ManejoTipoMenu.getInstancia().getTipoMenu(codigoModulo));

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

        }
    }

    private void activarOpciones() {

        if (codigoRol == 1) {//rol de administrador

            for (Node n : hbPermisos.getChildren()) {
                n.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, 9);

            for (Node n : hbPermisos.getChildren()) {

                if (!(n.getId() == null)) {

                    for (RolMenuModulo memu : listaRolMenuModulos) {

                        if (n.getId().equals(memu.getMenuModulo().getIdMenu())) {
                            n.setDisable(!memu.getHabilitado());

                        }
                    }

                }

            }
        }

    }
//
//    private void generarFactTemporales() {
//
//        try {
//
//            CorteDeFacturacion corteFact = new CorteDeFacturacion();
//
//            FacturaTemporal factura;
//            DetalleFacturaTemporal detalleFactura;
//
//            List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();
//
//            corteFact.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
//            corteFact.setFechaRegistro(new Date());
//            corteFact.setUsuario(VariablesGlobales.USUARIO);
//
//            int num = 0;
//
//            vbVisorDeProgreso.setVisible(true);
//            for (ContratoDeServicio contrato : listaContrato) {
//
//                factura = new FacturaTemporal();
//
//                //****************************************
//                System.out.println("contrato.getCliente() " + contrato.getCliente().getTipoNcf());
//                factura.setAbono(0.00);
//                factura.setCliente(contrato.getCliente());
//                factura.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
//                factura.setMonto(contrato.getTotalAPagar());
//                factura.setAnulada(false);
//                factura.setNumeroContrato(contrato.getNumeroDeContrato());
//                factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
//
//                SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
//                        .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
//                factura.setNumero(secDoc.getNumero() + num);
//
//                factura.setNcf("na");
//                factura.setTotal(factura.getMonto());
//                factura.setSubTotal(contrato.getSubTotal());
//                factura.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
//                factura.setDescuento(0.00);
//                factura.setItbis(contrato.getItbis());
//                factura.setUsuario(VariablesGlobales.USUARIO);
//                factura.setVendedor(contrato.getEjecutivoDeVenta());
//
//                factura.setFechaCreacion(new Date());
//
//                Date fechaVencimiento = ClaseUtil.asDate(dpFechaFacturacion.getValue());
//                factura.setFechaVencimiento(fechaVencimiento);
//
//                factura.setPagada(false);
//                factura.setTipoVenta(2);
//                factura.setAbono(0.0);
//                factura.setPendiente(factura.getTotal());
//                factura.setCondicionPago(new CondicionPago(2));
//                factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
//                fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, factura.getCliente().getDiasCredito());
//                factura.setFechaVencimiento(fechaVencimiento);
//                factura.setDespachada(false);
//
//                FormaPago formaPago = new FormaPago();
//                TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
//                formaPago.setTipoFormaPago(tipoFormaPago);
//                formaPago.setMonto(factura.getTotal());
//
//                //agregando detalle a la factura
//                listaDetContrato.clear();
//                listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
//                        .getDetalleContratoVencido(contrato.getCodigo(), fechaVencimiento));
//
//                System.out.println("NO HUBO CLIENTE CON NCF NULO ");
//
//                Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;
//
//                listaDetalleFacturaTemporal.clear();
//                listaDetalleFacDdatosVehiculo.clear();
//                for (DetalleContratoDeServicio detCto : listaDetContrato) {
//
//                    System.out.println("detCto.getEquipo().getTipoArticulo().getCodigo() " + detCto.getEquipo().getTipoArticulo().getCodigo());
////                    if (detCto.getEquipo().getTipoArticulo().getCodigo() == 3) {//solo se creanran factura por servicio 
//
//                    detalleFactura = new DetalleFacturaTemporal();
//                    // *****************************************
//
//                    long dias = ClaseUtil.diferenciaDiasEntreDosFecha(detCto.getFechaUltimoPagoHasta(), fechaVencimiento);
//
//                    Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();
//
//                    int diasInt = Integer.parseInt(Long.toString(dias));
//
//                    if (dias < 30) {
//
//                        System.out.println("dias MENOR A 30" + dias);
//                        Double precioPorDia = ClaseUtil.roundDouble(detCto.getPrecioPagado() / 30, 2);
//                        precioPorDia = precioPorDia * dias;
//                        detalleFactura.setPrecio(precioPorDia);
//
//                    } else {
//
//                        System.out.println("dias mayor de 30 " + dias);
//                        detalleFactura.setPrecio(detCto.getPrecioPagado());
//                    }
//
//                    int cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();
//                    int cantidadGPS = detCto.getCantidad();
//                    String desc = "";
//                    Date fechaVencimientoCal = null;
//
//                    if (dias < 30) {
//
//                        fechaVencimientoCal = ClaseUtil.Fechadiadespues(detCto.getFechaUltimoPagoHasta(), diasInt);//Calculamos la fecha de vencimiento
//
//                    } else {
//
//                        fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadFrecuenciaPago);//Calculamos la fecha de vencimiento
//                        // en base a la cantidad de pago por adelantado
//                    }
//
//                    detCto.setFechaUltimoPagoDesde(detCto.getFechaUltimoPagoHasta());//Factura  que cubre el pago desde esta fecha hasta la fecha calculada
//
//                    detCto.setFechaUltimoPagoHasta(fechaVencimientoCal); //fechaVencimientoCal   a hora es el setFechaUltimoPagoHasta    
//
//                    desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
//                            + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoDesde())
//                            + " AL  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta());
//
//                    System.out.println("descripcion pago " + desc);
//                    detalleFactura.setDescripcionPago(desc);
//                    detalleFactura.setFactura(factura);
//                    detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
//                    detalleFactura.setArticulo(detCto.getEquipo());
//                    cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();//CAntidad facturado
//
//                    detalleFactura.setCantidad(cantidad);
//
//                    subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
//                    detalleFactura.setSubTotal(subTotal);
//
//                    valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);
//
//                    detalleFactura.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));
//
//                    detalleFactura.setDescuento(0.00);
//                    detalleFactura.setExistencia(0.00);
//                    detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
//                    detalleFactura.setNuevaExistencia(0.0);
//
//                    detalleFactura.setItbis(valorItbis);
//                    detalleFactura.setTasaItbis(detCto.getTasaItbis());
//
//                    detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
//                    detalleFactura.setUnidad(unidad);
//
//                    listaDetalleFacturaTemporal.add(detalleFactura);
//                }
//
//                try {
//
//                    System.out.println(" listaDetalleFactura " + listaDetalleFacturaTemporal + " listaDetalleFactura.size() " + listaDetalleFacturaTemporal.size());
//
//                    if (listaDetalleFacturaTemporal.size() > 0) {
//
//                        factura.setTotal(getTotalTemp(listaDetalleFacturaTemporal));
//                        factura.setSubTotal(getSubTotalTewmp(listaDetalleFacturaTemporal));
//                        factura.setItbis(getItbisTemp(listaDetalleFacturaTemporal));
//                        factura.setMonto(getTotalTemp(listaDetalleFacturaTemporal));
//                        factura.setPendiente(getTotalTemp(listaDetalleFacturaTemporal));
//
//                        factura.setDetalleFacturaTemporalCollection(listaDetalleFacturaTemporal);
//
//                        FacturaTemporal factDb = guardarFacturaTempotal(factura);
//
//                        listaFacturaTemporal.add(factDb);
//
//                        if (factDb == null) {
//
//                            ClaseUtil.mensaje(" Hubo Error creando la factura ");
//                            return;
//
//                        }
//                    }
//
//                } catch (Exception e) {
//
//                    e.printStackTrace();
//                }
//
//                num++;
//
//            }
//
//            listaContrato.clear();
//            lbCantidadContrato.setText("");
//            lbCantidadFactura.setText("");//
//            lbCantidadFactura.setText(cantidadFactura().toString());
//            vbVisorDeProgreso.setVisible(false);
//
//        } catch (Exception e) {
//
//            ClaseUtil.mensaje("Hubo un error guardando la factura ");
//            e.printStackTrace();
//        }
//
//    }

    private Double getSubTotalTewmp(List<DetalleFacturaTemporal> lista) {

        Double subTotal = 0.00;

        for (DetalleFacturaTemporal det : lista) {

            subTotal += det.getSubTotal();

        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotalTemp(List<DetalleFacturaTemporal> lista) {

        Double total = 0.00;

        for (DetalleFacturaTemporal det : lista) {

            total += det.getTotal();

        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getItbisTemp(List<DetalleFacturaTemporal> lista) {

        Double itbis = 0.00;

        for (DetalleFacturaTemporal det : lista) {

            itbis += det.getItbis();

        }

        return FormatNum.FormatearDouble(itbis, 2);
    }

    private FacturaTemporal guardarFacturaTempotal(FacturaTemporal fac) {

        FacturaTemporal facturaDb = null;

        try {

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(fac.getUnidadDeNegocio().getCodigo(), 7);

            if (!(secDoc == null)) {

                fac.setNumero(secDoc.getNumero());
                fac.setSecuenciaDocumento(secDoc);

            } else {

                ClaseUtil.mensaje("La secuencia para la factura de la unidad de negocio "
                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return facturaDb;
            }

            System.out.println("");
            fac.setTipoNcf(fac.getCliente().getTipoNcf());//ponemos el tipo de ncf
            fac.setNombreTipoNcf(fac.getCliente().getTipoNcf().getNombre());

            System.out.println("fac.getCliente().getTipoNcf() " + fac.getCliente().getTipoNcf() + " fac.getTipoNcf() " + fac.getTipoNcf());

            fac.setNcf("B0000001");
            fac.setNcfFechaValidoHasta(new Date());
            fac.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            System.out.println("Sin ncf");

            facturaDb = ManejoFacturaTemporal.getInstancia().salvar(fac);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return facturaDb;
    }

    private void tareFacturaTemporal() {

        try {

            btnGuardar.setDisable(true);
            progresBar.setProgress(0);
            pgIndicador.setProgress(0);
            pgIndicador.setVisible(true);
            progresBar.setVisible(true);

            taskGenerarFacturaTemporales = new TaskGenerarFacturaTemporales();

            progresBar.progressProperty().unbind();

            progresBar.progressProperty().bind(taskGenerarFacturaTemporales.progressProperty());

            pgIndicador.progressProperty().unbind();

            pgIndicador.progressProperty().bind(taskGenerarFacturaTemporales.progressProperty());
//
            lbProgreso.textProperty().unbind();
            lbProgreso.textProperty().bind(taskGenerarFacturaTemporales.messageProperty());

            taskGenerarFacturaTemporales.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent t) -> {

                lbProgreso.textProperty().unbind();

                vbVisorDeProgreso.setVisible(false);
                lbCantidadFactura.setText(Integer.toString(listaFacturaTemporal.size()));

                taskGenerarFacturaTemporales.cancel();

                progresBar.progressProperty().unbind();

                pgIndicador.progressProperty().unbind();

            });

            // Start the Task.
            new Thread(taskGenerarFacturaTemporales).start();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private void tareFactura() {

        try {

            vbVisorDeProgreso.setVisible(true);
            btnGuardar.setDisable(true);
            progresBar.setProgress(0);
            pgIndicador.setProgress(0);
            pgIndicador.setVisible(true);
            progresBar.setVisible(true);

            taskGenerarFactura = new TaskGenerarFactura();

            progresBar.progressProperty().unbind();

            progresBar.progressProperty().bind(taskGenerarFactura.progressProperty());

            pgIndicador.progressProperty().unbind();

            pgIndicador.progressProperty().bind(taskGenerarFactura.progressProperty());
//
            lbProgreso.textProperty().unbind();
            lbProgreso.textProperty().bind(taskGenerarFactura.messageProperty());

            taskGenerarFactura.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent t) -> {

                lbProgreso.textProperty().unbind();

                vbVisorDeProgreso.setVisible(false);
                lbCantidadFactura.setText(Integer.toString(listaFactura.size()));

                taskGenerarFactura.cancel();

                progresBar.progressProperty().unbind();

                pgIndicador.progressProperty().unbind();
                vbVisorDeProgreso.setVisible(false);
                lbCantidadContrato.setText("");
                btnGuardar.setDisable(false);

                ClaseUtil.mensaje("Factura guardada correctamente");

            });

            // Start the Task.
            new Thread(taskGenerarFactura).start();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @FXML
    private void chFacturarActionEvent(ActionEvent event) {
    }

    private List<DetalleFactura> convertirFactTempAFact(Factura fact) {

        List<DetalleFactura> lista = new ArrayList<>();

        System.out.println("listaDetFactArticuloNuevo for " + listaDetFactArticuloNuevo);

        try {

            DetalleFactura detTem = null;
            for (DetalleFacturaTemporal def : listaDetFactArticuloNuevo) {

                System.out.println("def " + def.getNombreArticulo());
                detTem = new DetalleFactura();

                detTem.setAlmacen(def.getAlmacen());
                detTem.setArticulo(def.getArticulo());
                detTem.setCantidad(def.getCantidad());
                detTem.setCantidadInventario(def.getCantidadInventario());
                detTem.setNombreArticulo(def.getNombreArticulo());
                detTem.setCostoUnitario(def.getCostoUnitario());
                detTem.setPrecio(def.getPrecio());
                detTem.setPrecioItbis(def.getPrecioItbis());
                detTem.setDescuento(def.getDescuento());
                detTem.setExistencia(def.getExistencia());
                detTem.setFactorUnidadSalida(def.getFactorUnidadSalida());
                detTem.setItbis(def.getItbis());
                detTem.setNuevaExistencia(def.getNuevaExistencia());
                detTem.setListaDePrecio(def.getListaDePrecio());
                detTem.setSubTotal(def.getSubTotal());
                detTem.setTotal(def.getTotal());
                detTem.setTasaItbis(def.getTasaItbis());
                detTem.setUnidadInventario(def.getUnidadInventario());
                detTem.setUnidad(def.getUnidad());
                detTem.setFactura(fact);

                lista.add(detTem);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;

    }

    @FXML
    private void btnBuscarArticuloActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        try {

            if (!(tbDetalleFactura.getSelectionModel().getSelectedIndex() == -1)) {

                listaDetalleFacturaTemporal.remove(tbDetalleFactura.getSelectionModel().getSelectedIndex());
                listaDetFactArticuloNuevo.remove(tbDetalleFactura.getSelectionModel().getSelectedItem());
                tbDetalleFactura.refresh();
                txtSubTotal.setText(getSubTotal().toString());
                txtItbis.setText(getItbis().toString());
                txtTotal.setText(getTotal().toString());
                facturaTemporal.setDetalleFacturaTemporalCollection(listaDetalleFacturaTemporal);
                ManejoFacturaTemporal.getInstancia().actualizar(facturaTemporal);

            } else {
                ClaseUtil.mensaje("Tiene que Selccionar un Registro");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void actualizandoDetalleContrato(List<DetalleContratoDeServicio> lista) {

        for (DetalleContratoDeServicio detCto : lista) {

            detCto.setFechaUltimoPagoDesde(detCto.getFechaDesde());
            detCto.setFechaUltimoPagoHasta(detCto.getFechaHasta());

            System.out.println("actualizando det contrato .... ");
            if (detCto.getEquipo().getTipoArticulo().getCodigo() != 3) {

                System.out.println("facturado true" + detCto.getEquipo().getTipoArticulo().getCodigo());
                detCto.setFacturado(true);
            } else {
                detCto.setFacturado(false);
                System.out.println("facturado false" + detCto.getEquipo().getTipoArticulo().getCodigo());
            }
        }

    }

    private void agregarVehiculo(DetalleFacturaTemporal detalleFactura) {

        List<DatosDeVehiculo> listaDetVehiculo = new ArrayList<>();
        FacturaDatosDeVehiculoTemp facturaDatosDeVehiculo;
        //agregando detalle a la factura de los vehiculos 
        listaDetVehiculo.clear();
//         listaDetVehiculo.addAll(ManejoContratoDeServicio.getInstancia()
//                .getDatosVehiculoo(25));
        listaDetVehiculo.addAll(ManejoContratoDeServicio.getInstancia()
                .getDatosVehiculoo(detalleFactura.getDetalleContrato()));

        System.out.println(" datos vehiculo " + listaDetVehiculo.size() + " detCto.getCodigo() " + detalleFactura.getDetalleContrato());

        for (DatosDeVehiculo detVeh : listaDetVehiculo) {

            System.out.println("configurando  factura datos de vehiculo ");
            facturaDatosDeVehiculo = new FacturaDatosDeVehiculoTemp();
            // *****************************************

            facturaDatosDeVehiculo.setFactura(detalleFactura.getCodigo());
            facturaDatosDeVehiculo.setAdicional(detVeh.getAdicional());
            facturaDatosDeVehiculo.setAnio(detVeh.getAnio());
            facturaDatosDeVehiculo.setChasis(detVeh.getChasis());
            facturaDatosDeVehiculo.setColor(detVeh.getColor());
            facturaDatosDeVehiculo.setMarca(detVeh.getMarca());
            facturaDatosDeVehiculo.setMatricula(detVeh.getMatricula());
            facturaDatosDeVehiculo.setModelo(detVeh.getModelo());
            facturaDatosDeVehiculo.setPlaca(detVeh.getPlaca());
            facturaDatosDeVehiculo.setTipoVehiculo(1);
            facturaDatosDeVehiculo.setVehiculo(detVeh.getVehiculo());

            listaDetalleFacDdatosVehiculoTemp.add(facturaDatosDeVehiculo);

        }
    }

    @FXML
    private void btnReciboIngresoActionEvent(ActionEvent event) {

        try {

            System.out.println("factDb recibo: " + factDb);
            if (factDb == null) {

                ClaseUtil.mensaje("Tiene que guardar una factura para poder crearle el recibo ");
                return;
            }

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/cxc/reciboIngreso/RegistroReciboIngreso.fxml"));

            loader.load();

            Parent root = loader.getRoot();

            RegistroReciboDeIngresoController controller = loader.getController();
            controller.setCliente(factDb.getCliente());

            controller.llenarCampo();

            ClaseUtil.getStageModalcONTRATO(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class TaskGenerarFacturaTemporales extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            try {

                listaFacturaTemporal.clear();
                int total = listaContrato.size();
                int i = 0;

                System.out.println("total " + total);

                for (ContratoDeServicio contrato : listaContrato) {
                    i++;
                    generarFactTemporales(contrato, i, total);
                    this.updateProgress(i, total);
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;

        }

        private void generarFactTemporales(ContratoDeServicio contrato, int i, int total) throws InterruptedException {

            try {

                CorteDeFacturacion corteFact = new CorteDeFacturacion();

                FacturaTemporal factura;
                DetalleFacturaTemporal detalleFactura;

                List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();

                corteFact.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
                corteFact.setFechaRegistro(new Date());
                corteFact.setUsuario(VariablesGlobales.USUARIO);

                int num = 0;

                vbVisorDeProgreso.setVisible(true);

                factura = new FacturaTemporal();

                //****************************************
                System.out.println("contrato.getCliente() " + contrato.getCliente().getTipoNcf());
                factura.setAbono(0.00);
                factura.setCliente(contrato.getCliente());
                factura.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
                factura.setMonto(contrato.getTotalAPagar());
                factura.setAnulada(false);
                factura.setNumeroContrato(contrato.getNumeroDeContrato());
                factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

                SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                        .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
                factura.setNumero(secDoc.getNumero() + num);

                factura.setNcf("na");
                factura.setTotal(factura.getMonto());
                factura.setSubTotal(contrato.getSubTotal());
                factura.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
                factura.setDescuento(0.00);
                factura.setItbis(contrato.getItbis());
                factura.setUsuario(VariablesGlobales.USUARIO);
                factura.setVendedor(contrato.getEjecutivoDeVenta());

                factura.setFechaCreacion(new Date());

                Date fechaVencimiento = ClaseUtil.asDate(dpFechaFacturacion.getValue());
                factura.setFechaVencimiento(fechaVencimiento);

                factura.setPagada(false);
                factura.setTipoVenta(2);
                factura.setAbono(0.0);
                factura.setPendiente(factura.getTotal());
                factura.setCondicionPago(new CondicionPago(2));
                factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
                fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, factura.getCliente().getDiasCredito());
                factura.setFechaVencimiento(fechaVencimiento);
                factura.setDespachada(false);

                FormaPago formaPago = new FormaPago();
                TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
                formaPago.setTipoFormaPago(tipoFormaPago);
                formaPago.setMonto(factura.getTotal());

                //agregando detalle a la factura
                listaDetContrato.clear();
                listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
                        .getDetalleContratoVencido(contrato.getCodigo()));

                System.out.println("NO HUBO CLIENTE CON NCF NULO ");

                Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;

                listaDetalleFacturaTemporal.clear();
                listaDetalleFacDdatosVehiculo.clear();
                for (DetalleContratoDeServicio detCto : listaDetContrato) {

                    System.out.println("detCto.getEquipo().getTipoArticulo().getCodigo() " + detCto.getEquipo().getTipoArticulo().getCodigo());
//                    if (detCto.getEquipo().getTipoArticulo().getCodigo() == 3) {//solo se creanran factura por servicio 

                    detalleFactura = new DetalleFacturaTemporal();
                    // *****************************************

                    long dias = ClaseUtil.diferenciaDiasEntreDosFecha(detCto.getFechaUltimoPagoHasta(), fechaVencimiento);

                    Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();

                    int diasInt = Integer.parseInt(Long.toString(dias));

                    if (dias < 30) {

                        System.out.println("dias MENOR A 30" + dias);
                        Double precioPorDia = ClaseUtil.roundDouble(detCto.getPrecioPagado() / 30, 2);
                        precioPorDia = precioPorDia * dias;
                        detalleFactura.setPrecio(precioPorDia);

                    } else {

                        System.out.println("dias mayor de 30 " + dias);
                        detalleFactura.setPrecio(detCto.getPrecioPagado());
                    }

                    int cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();
                    int cantidadGPS = detCto.getCantidad();
                    String desc = "";
                    Date fechaVencimientoCal = null;

                    if (dias < 30) {

                        fechaVencimientoCal = ClaseUtil.Fechadiadespues(detCto.getFechaUltimoPagoHasta(), diasInt);//Calculamos la fecha de vencimiento

                    } else {

                        fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadFrecuenciaPago);//Calculamos la fecha de vencimiento
                        // en base a la cantidad de pago por adelantado
                    }

//                    detCto.setFechaUltimoPagoDesde(detCto.getFechaUltimoPagoHasta());//Factura  que cubre el pago desde esta fecha hasta la fecha calculada
//
//                    detCto.setFechaUltimoPagoHasta(fechaVencimientoCal); //fechaVencimientoCal   a hora es el setFechaUltimoPagoHasta    
                    desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                            + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                            + " AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);

                    System.out.println("descripcion pago " + desc);

                    detalleFactura.setDescripcionPago(desc);
                    detalleFactura.setFactura(factura);
                    detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
                    detalleFactura.setArticulo(detCto.getEquipo());
                    cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();//CAntidad facturado

                    detalleFactura.setCantidad(cantidad);

                    subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
                    detalleFactura.setSubTotal(subTotal);

                    valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);

                    detalleFactura.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));

                    detalleFactura.setDescuento(0.00);
                    detalleFactura.setExistencia(0.00);
                    detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
                    detalleFactura.setNuevaExistencia(0.0);

                    detalleFactura.setItbis(valorItbis);
                    detalleFactura.setTasaItbis(detCto.getTasaItbis());

                    detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
                    detalleFactura.setUnidad(unidad);

                    listaDetalleFacturaTemporal.add(detalleFactura);
                }

                try {

                    System.out.println(" listaDetalleFactura " + listaDetalleFacturaTemporal + " listaDetalleFactura.size() " + listaDetalleFacturaTemporal.size());

                    if (listaDetalleFacturaTemporal.size() > 0) {

                        factura.setTotal(getTotalTemp(listaDetalleFacturaTemporal));
                        factura.setSubTotal(getSubTotalTewmp(listaDetalleFacturaTemporal));
                        factura.setItbis(getItbisTemp(listaDetalleFacturaTemporal));
                        factura.setMonto(getTotalTemp(listaDetalleFacturaTemporal));
                        factura.setPendiente(getTotalTemp(listaDetalleFacturaTemporal));

                        factura.setDetalleFacturaTemporalCollection(listaDetalleFacturaTemporal);

                        FacturaTemporal factDb = guardarFacturaTempotal(factura);

                        listaFacturaTemporal.add(factDb);

                        if (factDb == null) {

                            ClaseUtil.mensaje(" Hubo Error creando la factura ");
                            return;

                        }

                    }

                    this.updateMessage(" Procesada  " + i + "  De " + total);

                } catch (Exception e) {

                    e.printStackTrace();
                }

                num++;

            } catch (Exception e) {

                ClaseUtil.mensaje("Hubo un error guardando la factura ");
                e.printStackTrace();
            }

            Thread.sleep(200);
        }

    }

    private class TaskGenerarFactura extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            try {

                int total = listaContrato.size();
                int i = 0;

                for (ContratoDeServicio contrato : listaContrato) {
                    i++;
                    System.out.println(" guardarFacturaContrato ");
                    guardarFacturaContrato(contrato, i, total);
                    this.updateProgress(i, total);
                }

                listaContrato.clear();
                listaFacturaTemporal.clear();
                ManejoFacturaTemporal.getInstancia().eliminarFacturaTemporal();

            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;

        }

        private void guardarFacturaContrato(ContratoDeServicio contrato, int i, int total) throws InterruptedException {

            try {

                if (listaFactura.size() <= 0) {

                    ClaseUtil.mensaje("No hay factura para guardar");
                    return;
                }

                CorteDeFacturacion corteFact = new CorteDeFacturacion();
                DetalleCorteDeFacturacion detaCorte;
                List<DetalleCorteDeFacturacion> listaDetCorte = new ArrayList<>();
                Factura factura;
                DetalleFactura detalleFactura;
                FacturaDatosDeVehiculo facturaDatosDeVehiculo;

                List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();
                List<DatosDeVehiculo> listaDetVehiculo = new ArrayList<>();

                corteFact.setFecha(new Date());
                corteFact.setFechaRegistro(new Date());
                corteFact.setUsuario(VariablesGlobales.USUARIO);

                int num = 0;

                factura = new Factura();

                //****************************************
                factura.setAbono(0.00);
                factura.setCliente(contrato.getCliente());
                factura.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
                factura.setMonto(contrato.getTotalAPagar());
                factura.setAnulada(false);
                factura.setNumeroContrato(contrato.getNumeroDeContrato());
                factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
                factura.setOrigenFactura(ManejoOrigenFactura.getInstancia().getOrigenFactura(1));

                SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                        .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
                factura.setNumero(secDoc.getNumero() + num);

                factura.setNcf("na");
                factura.setTotal(factura.getMonto());
                factura.setSubTotal(contrato.getSubTotal());
                factura.setFecha(new Date());
                factura.setDescuento(0.00);
                factura.setItbis(contrato.getItbis());
                factura.setUsuario(VariablesGlobales.USUARIO);
                factura.setVendedor(contrato.getEjecutivoDeVenta());

                factura.setFechaCreacion(new Date());

                factura.setFechaVencimiento(factura.getFecha());

                factura.setPagada(false);
                factura.setTipoVenta(2);
                factura.setAbono(0.0);
                factura.setPendiente(factura.getTotal());
                factura.setCondicionPago(new CondicionPago(2));
                factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
                fechaVencimiento = ClaseUtil.Fechadiadespues(new Date(), factura.getCliente().getDiasCredito());
                factura.setFechaVencimiento(fechaVencimiento);
                factura.setDespachada(false);

                FormaPago formaPago = new FormaPago();
                TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
                formaPago.setTipoFormaPago(tipoFormaPago);
                formaPago.setMonto(factura.getTotal());

                //agregando detalle a la factura
                listaDetContrato.clear();
                listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
                        .getDetalleContratoVencido(contrato.getCodigo()));

                Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;

                listaDetalleFacDdatosVehiculo.clear();
                listaDetalleFactura.clear();
                for (DetalleContratoDeServicio detCto : listaDetContrato) {

                    detalleFactura = new DetalleFactura();
                    detalleFactura.setDetalleContrato(detCto.getCodigo());
                    // *****************************************

                    if (detCto.getCantidadFrecuenciaDePago() == 1) {

                        detCto.setRecurrente(true);

                    }

                    Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();

                    detalleFactura.setPrecio(detCto.getPrecioPagado());

                    String desc = "";

                    detCto.setFechaUltimoPagoDesde(detCto.getFechaDesde());
                    detCto.setFechaUltimoPagoHasta(detCto.getFechaHasta());

                    if (detCto.getEquipo().getTipoArticulo().getCodigo() != 3) {

                        System.out.println("facturado true" + detCto.getEquipo().getTipoArticulo().getCodigo());
                        detCto.setFacturado(true);
                    } else {
                        detCto.setFacturado(false);
                        System.out.println("facturado false" + detCto.getEquipo().getTipoArticulo().getCodigo());
                    }

                    System.out.println("descripcion pago " + desc);
                    detalleFactura.setDescripcionPago(detCto.getDescripcion());
                    detalleFactura.setFactura(factura);
                    detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
                    detalleFactura.setArticulo(detCto.getEquipo());
                    cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();

                    detalleFactura.setCantidad(cantidad);

                    subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
                    detalleFactura.setSubTotal(subTotal);

                    valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);

                    detalleFactura.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));

                    detalleFactura.setDescuento(0.00);
                    detalleFactura.setExistencia(0.00);
                    detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
                    detalleFactura.setNuevaExistencia(0.0);

                    detalleFactura.setItbis(valorItbis);
                    detalleFactura.setTasaItbis(detCto.getTasaItbis());

                    detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
                    detalleFactura.setUnidad(unidad);

                    //agregando detalle a la factura de los vehiculos 
                    listaDetVehiculo.clear();
                    listaDetVehiculo.addAll(ManejoContratoDeServicio.getInstancia()
                            .getDatosVehiculoo(detCto.getCodigo()));

                    System.out.println(" datos vehiculo " + listaDetVehiculo.size() + " detCto.getCodigo() " + detCto.getCodigo());

                    for (DatosDeVehiculo detVeh : listaDetVehiculo) {

                        System.out.println("configurando  factura datos de vehiculo ");
                        facturaDatosDeVehiculo = new FacturaDatosDeVehiculo();
                        // *****************************************

                        facturaDatosDeVehiculo.setFactura(detalleFactura);
                        facturaDatosDeVehiculo.setAdicional(detVeh.getAdicional());
                        facturaDatosDeVehiculo.setAnio(detVeh.getAnio());
                        facturaDatosDeVehiculo.setChasis(detVeh.getChasis());
                        facturaDatosDeVehiculo.setColor(detVeh.getColor());
                        facturaDatosDeVehiculo.setMarca(detVeh.getMarca());
                        facturaDatosDeVehiculo.setMatricula(detVeh.getMatricula());
                        facturaDatosDeVehiculo.setModelo(detVeh.getModelo());
                        facturaDatosDeVehiculo.setPlaca(detVeh.getPlaca());
                        facturaDatosDeVehiculo.setTipoVehiculo(detVeh.getTipoVehiculo());
                        facturaDatosDeVehiculo.setVehiculo(detVeh.getVehiculo());

                        listaDetalleFacDdatosVehiculo.add(facturaDatosDeVehiculo);

                    }

                    listaDetalleFactura.add(detalleFactura);

                }///fin del for 

                System.out.println(" listaDetalleFactura " + listaDetFactArticuloNuevo + "listaDetFactArticuloNuevo " + listaDetFactArticuloNuevo.size());

                try {

                    System.out.println(" listaDetalleFactura " + listaDetalleFactura + " listaDetalleFactura.size() " + listaDetalleFactura.size());

                    if (listaDetalleFactura.size() > 0) {

                        System.out.println("convertirFactTempAFact(factura) " + convertirFactTempAFact(factura).size());
                        listaDetalleFactura.addAll(convertirFactTempAFact(factura));

                        factura.setTotal(getTotal(listaDetalleFactura));
                        factura.setSubTotal(getSubTotal(listaDetalleFactura));
                        factura.setItbis(getItbis(listaDetalleFactura));
                        factura.setMonto(getTotal(listaDetalleFactura));
                        factura.setPendiente(getTotal(listaDetalleFactura));

                        factura.setDetalleFacturaCollection(listaDetalleFactura);

                        Double balancePendiente = ManejoFactura.getInstancia().getMontoPendiente(factura.getCliente());

                        Factura factDb = guardar(factura);

                        if (factDb == null) {

                            ClaseUtil.mensaje(" Hubo Error creando la factura ");
                            return;

                        } else {
//
                            listaDetalleFacDdatosVehiculo.forEach((fctdvh) -> {
                                System.out.println("item " + fctdvh);
                                ManejoFacturaDatosDeVehiculo.getInstancia().salvar(fctdvh);
                            });

                            //Actualizar el ncf 
                            ManejoRelacionNcf.getInstancia().actualizar(relacionNcf);

                            //Generando el historico de corte de facturacion 
                            detaCorte = new DetalleCorteDeFacturacion();

                            detaCorte.setFactura(factura);
                            detaCorte.setNumeroFactura(factura.getNumero());
                            detaCorte.setContrato(contrato);
                            detaCorte.setNumeroContrato(contrato.getNumeroDeContrato());
                            detaCorte.setCorteDeFacturacion(corteFact);
                            detaCorte.setTotalFactura(factura.getTotal());
                            listaDetCorte.add(detaCorte);

                            contrato.setDetalleContratoDeServicioCollection(listaDetContrato);
                            contrato.setFacturado(true);
                            ManejoContratoDeServicio.getInstancia().actualizar(contrato);

                            HistoricoBalancePendiente hBalance = new HistoricoBalancePendiente();

                            hBalance.setCliente(factura.getCliente().getCodigo());
                            hBalance.setFactura(factura.getCodigo());
                            hBalance.setFechaVencimiento(factura.getFecha());
                            hBalance.setTotal(balancePendiente);

                            ManejoHistoricoBalancePendiente.getInstancia().salvar(hBalance);

                            this.updateMessage(" Procesada  " + i + "  De " + total);

                        }

                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }

                num++;

                corteFact.setCantidadFactura(num);

                corteFact.setDetalleCorteDeFacturacionCollection(listaDetCorte);

                ManejoCorteDeFacturacion.getInstancia().salvar(corteFact);

                Thread.sleep(200);

            } catch (Exception e) {

                ClaseUtil.mensaje("Hubo un error guardando la factura ");
                e.printStackTrace();
            }

        }

    }

    private void guardarFacturatemporales(ContratoDeServicio contrato) {

        try {

            if (listaContrato.size() <= 0) {

                ClaseUtil.mensaje("No hay factura para guardar");
                return;
            }

            FacturaTemporal facturaTemporal;
            DetalleFacturaTemporal detalleFactura;

            List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();

            int num = 0;

            facturaTemporal = new FacturaTemporal();

            //****************************************
            facturaTemporal.setAbono(0.00);
            facturaTemporal.setCliente(contrato.getCliente());
            facturaTemporal.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
            facturaTemporal.setMonto(contrato.getTotalAPagar());
            facturaTemporal.setAnulada(false);
            facturaTemporal.setNumeroContrato(contrato.getNumeroDeContrato());

            facturaTemporal.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(facturaTemporal.getUnidadDeNegocio().getCodigo(), 7);
            facturaTemporal.setNumero(secDoc.getNumero() + num);

            facturaTemporal.setNcf("na");
            facturaTemporal.setTotal(facturaTemporal.getMonto());
            facturaTemporal.setSubTotal(contrato.getSubTotal());
            facturaTemporal.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
            facturaTemporal.setDescuento(0.00);
            facturaTemporal.setItbis(contrato.getItbis());
            facturaTemporal.setUsuario(VariablesGlobales.USUARIO);
//            factura.setVendedor(contrato.getEjecutivoDeVenta());

            facturaTemporal.setFechaCreacion(new Date());

            facturaTemporal.setFechaVencimiento(facturaTemporal.getFecha());

            facturaTemporal.setPagada(false);
            facturaTemporal.setTipoVenta(2);
            facturaTemporal.setAbono(0.0);
            facturaTemporal.setPendiente(facturaTemporal.getTotal());
            facturaTemporal.setCondicionPago(new CondicionPago(2));
            facturaTemporal.setPlazo(ManejoPlazo.getInstancia().getplazo(facturaTemporal.getCliente().getDiasCredito()));
            fechaVencimiento = ClaseUtil.Fechadiadespues(new Date(), facturaTemporal.getCliente().getDiasCredito());
            facturaTemporal.setFechaVencimiento(fechaVencimiento);
            facturaTemporal.setDespachada(false);

            FormaPago formaPago = new FormaPago();
            TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
            formaPago.setTipoFormaPago(tipoFormaPago);
            formaPago.setMonto(facturaTemporal.getTotal());

            //agregando detalle a la factura
            listaDetContrato.clear();
            listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
                    .getDetalleContratoParaFacturar(contrato.getCodigo()));

            Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;

            listaDetalleFacturaTemporal.clear();
            for (DetalleContratoDeServicio detCto : listaDetContrato) {

                System.out.println("detCto cantidad a facturar : " + detCto.getDescripcion());
                facturaTemporal.setVendedor(detCto.getEjecutivoDeVenta());
                detalleFactura = new DetalleFacturaTemporal();
                // *****************************************

                if (detCto.getGpsDelCliente() == true) {

                    System.out.println("detCto.getGpsDelCliente() " + detCto.getGpsDelCliente());
                    continue;

                }

                if (detCto.getCantidadFrecuenciaDePago() == 1) {

                    detCto.setRecurrente(true);

                }

                Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();

                System.out.println("detCto.getPrecioDeOferta() " + detCto.getPrecioDeOferta());

                if (!(detCto.getPrecioDeOferta() == null)) {

                    if (detCto.getPrecioDeOferta() > 0) {

                        detalleFactura.setPrecio(detCto.getPrecioDeOferta());
                    } else {

                        detalleFactura.setPrecio(detCto.getPrecioPagado());
                    }

                } else {

                    detalleFactura.setPrecio(detCto.getPrecioPagado());
                }

                String desc = "";

                System.out.println("descripcion pago " + desc);
                detalleFactura.setDetalleContrato(detCto.getCodigo());
                detalleFactura.setDescripcionPago(detCto.getDescripcion());
                detalleFactura.setFactura(facturaTemporal);
                detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
                detalleFactura.setArticulo(detCto.getEquipo());
                cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();

                String descSegundo = detCto.getDescripcion();

                int cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();
                int codContrato = detCto.getContratoDeServicio().getCodigo();

                System.out.println(" codContrato " + codContrato);

                Integer cantidadGPS = ManejoContratoDeServicio.getInstancia().getDetalleContratoCantidadArticulo(codContrato, detCto);

                System.out.println(" cantidadGPS " + cantidadGPS);

//                if (detCto.getEquipo().getTipoArticulo().getCodigo() == 3) {
//
//                    descSegundo = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion()
//                            + " POR SERVICIO DE "
//                            + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy")
//                                    .format(detCto.getFechaDesde())
//                            + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaHasta());
//
//                    detalleFactura.setDescripcionPago(descSegundo);
//
//                } else {
//
                detalleFactura.setDescripcionPago(detCto.getDescripcion());
//                }

                detalleFactura.setCantidad(cantidadGPS.doubleValue());

                detalleFactura.setFactorUnidadSalida(cantidad);

                cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();

                detalleFactura.setCantidad(cantidad);

                detalleFactura.setCantidad(cantidad);

                subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
                detalleFactura.setSubTotal(subTotal);

                valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);
//                valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getPrecio(), 2);

                detalleFactura.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));

                detalleFactura.setDescuento(0.00);
                detalleFactura.setExistencia(0.00);
                detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
                detalleFactura.setNuevaExistencia(0.0);

                detalleFactura.setItbis(valorItbis);
                detalleFactura.setTasaItbis(detCto.getTasaItbis());

                detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
                detalleFactura.setUnidad(unidad);

                listaDetalleFacturaTemporal.add(detalleFactura);

            }

            for (DetalleFacturaTemporal det : listadetalleFactura) {

                det.setFactura(facturaTemporal);
            }

            listaDetalleFacturaTemporal.addAll(listadetalleFactura);

            try {

                System.out.println(" listaDetalleFactura " + listaDetalleFacturaTemporal + " listaDetalleFactura.size() " + listaDetalleFacturaTemporal.size());

                if (listaDetalleFacturaTemporal.size() > 0) {

                    facturaTemporal.setTotal(getTotalTemp(listaDetalleFacturaTemporal));
                    facturaTemporal.setSubTotal(getSubTotalTewmp(listaDetalleFacturaTemporal));
                    facturaTemporal.setItbis(getItbisTemp(listaDetalleFacturaTemporal));
                    facturaTemporal.setMonto(getTotalTemp(listaDetalleFacturaTemporal));
                    facturaTemporal.setPendiente(getTotalTemp(listaDetalleFacturaTemporal));

                    facturaTemporal.setDetalleFacturaTemporalCollection(listaDetalleFacturaTemporal);

                    FacturaTemporal factTemp = guardarFacturaTempotal(facturaTemporal);

                    if (factTemp == null) {

                        ClaseUtil.mensaje(" Hubo Error creando la factura ");
                        return;

                    }

                    List<DetalleFacturaTemporal> listDetFactTem = ManejoFacturaTemporal.getInstancia().getDetalleFactura(factTemp.getCodigo());

                    for (DetalleFacturaTemporal detFact : listDetFactTem) {
                        agregarVehiculo(detFact);
                    }

                    listaDetalleFacDdatosVehiculoTemp.forEach((fctdvh) -> {
                        System.out.println("item " + fctdvh);
                        ManejoFacturaDatosDeVehiculoTemp.getInstancia().salvar(fctdvh);
                    });

                    listaDetalleFacDdatosVehiculoTemp.clear();

                    listaFacturaTemporal.add(factTemp);
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            num++;

        } catch (Exception e) {

            ClaseUtil.mensaje("Hubo un error guardando la factura ");
            e.printStackTrace();
        }

    }

    private void guardarFacturaContrato(ContratoDeServicio contrato) throws InterruptedException {

        try {

            CorteDeFacturacion corteFact = new CorteDeFacturacion();
            DetalleCorteDeFacturacion detaCorte;
            List<DetalleCorteDeFacturacion> listaDetCorte = new ArrayList<>();

            DetalleFactura detalleFactura;
            FacturaDatosDeVehiculo facturaDatosDeVehiculo;

            List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();
            List<DatosDeVehiculo> listaDetVehiculo = new ArrayList<>();

            corteFact.setFecha(new Date());
            corteFact.setFechaRegistro(new Date());
            corteFact.setUsuario(VariablesGlobales.USUARIO);
            Factura factura;

            int num = 0;

            factura = new Factura();

            //****************************************
            factura.setAbono(0.00);
            factura.setCliente(contrato.getCliente());
            factura.setNombreCliente(contrato.getCliente().getNombre() + " " + contrato.getCliente().getApellido());
            factura.setMonto(contrato.getTotalAPagar());
            factura.setAnulada(false);
            factura.setNumeroContrato(contrato.getNumero().toString());
            factura.setContrato(contrato.getCodigo());
            factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
            factura.setNumero(secDoc.getNumero() + num);

            factura.setNcf("na");
            factura.setTotal(factura.getMonto());
            factura.setSubTotal(contrato.getSubTotal());
            factura.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
            factura.setDescuento(0.00);
            factura.setItbis(contrato.getItbis());
            factura.setUsuario(VariablesGlobales.USUARIO);
            factura.setFormato(1);

            factura.setFechaCreacion(new Date());

            factura.setFechaVencimiento(factura.getFecha());

            factura.setPagada(false);
            factura.setTipoVenta(2);
            factura.setAbono(0.0);
            factura.setPendiente(factura.getTotal());
            factura.setCondicionPago(new CondicionPago(2));
            factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
            fechaVencimiento = ClaseUtil.Fechadiadespues(new Date(), factura.getCliente().getDiasCredito());
            factura.setFechaVencimiento(fechaVencimiento);
            factura.setDespachada(false);
            factura.setOrigenFactura(ManejoOrigenFactura.getInstancia().getOrigenFactura(1));

            //agregando detalle a la factura
            listaDetContrato.clear();

            listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
                    .getDetalleContratoParaFacturar(contrato.getCodigo()));

            List<DetalleContratoDeServicio> listaDetContratoDetallada = new ArrayList<>();

            listaDetContratoDetallada.addAll(ManejoContratoDeServicio.getInstancia()
                    .getDetalleContratoVencido(contrato.getCodigo()));

            Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;

            listaDetalleFacDdatosVehiculo.clear();
            listaDetalleFactura.clear();
            for (DetalleContratoDeServicio detCto : listaDetContrato) {

                detalleFactura = new DetalleFactura();
                factura.setVendedor(detCto.getEjecutivoDeVenta());
                // *****************************************

                if (!(detCto.getEjecutivoDeVenta() == null)) {

                    factura.setVendedor(detCto.getEjecutivoDeVenta());

                }

                if (detCto.getCompatibilidad()) {

                    factura.setCompatibilidad(true);

                } else {
                    factura.setCompatibilidad(false);
                }

                if (!(detCto.getSuplidor() == null)) {

                    factura.setSuplidorDeVenta(detCto.getSuplidor());

                }

                if (detCto.getCambioDeModalidad()) {

                    factura.setOrigenFactura(ManejoOrigenFactura.getInstancia().getOrigenFactura(4));//no entra en comision

                }

                if (detCto.getReactivacion()) {

                    factura.setOrigenFactura(ManejoOrigenFactura.getInstancia().getOrigenFactura(4));//no entra en comision

                }

                if (detCto.getCantidadFrecuenciaDePago() == 1) {

                    detCto.setRecurrente(true);

                }

                if (detCto.getGpsDelCliente() == true) {

                    System.out.println("detCto.getGpsDelCliente() " + detCto.getGpsDelCliente());
                    continue;

                }

                Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();

                if (!(detCto.getPrecioDeOferta() == null)) {

                    if (detCto.getPrecioDeOferta() > 0) {

                        detalleFactura.setPrecio(detCto.getPrecioDeOferta());
                    } else {

                        detalleFactura.setPrecio(detCto.getPrecioPagado());
                    }

                } else {

                    detalleFactura.setPrecio(detCto.getPrecioPagado());
                }

                String desc = "";
//
//                detCto.setFechaUltimoPagoDesde(detCto.getFechaDesde());
//                detCto.setFechaUltimoPagoHasta(detCto.getFechaHasta());
//
//                if (detCto.getEquipo().getTipoArticulo().getCodigo() != 3) {
//
//                    System.out.println("facturado true" + detCto.getEquipo().getTipoArticulo().getCodigo());
//                    detCto.setFacturado(true);
//                } else {
//                    detCto.setFacturado(false);
//                    System.out.println("facturado false" + detCto.getEquipo().getTipoArticulo().getCodigo());
//                }

                System.out.println("descripcion pago " + desc);

                detalleFactura.setFactura(factura);
                detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
                detalleFactura.setArticulo(detCto.getEquipo());
                detalleFactura.setDetalleContrato(detCto.getCodigo());

                if (!(detCto.getEquipo().getTipoArticulo().getCodigo() == 3)) {

                    detalleFactura.setAlmacen(ManejoAlmacen.getInstancia()
                            .getalmacen(detCto.getAlmacen(), VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()));
                } else {
                    detalleFactura.setAlmacen(ManejoAlmacen.getInstancia().getalmacen(4));
                }

                String descSegundo = detCto.getDescripcion();

                int cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();
                int codContrato = detCto.getContratoDeServicio().getCodigo();

                Integer cantidadGPS = ManejoContratoDeServicio.getInstancia().getDetalleContratoCantidadArticulo(codContrato, detCto);

//                if (detCto.getEquipo().getTipoArticulo().getCodigo() == 3) {
//
//                    descSegundo = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion()
//                            + " POR SERVICIO DE"
//                            + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy")
//                                    .format(detCto.getFechaDesde())
//                            + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaHasta());
//
//                    detalleFactura.setDescripcionPago(descSegundo);
//
//                } else {
//
                detalleFactura.setDescripcionPago(detCto.getDescripcion());
//                }

                detalleFactura.setCantidad(cantidadGPS.doubleValue());

                detalleFactura.setFactorUnidadSalida(cantidad);

                cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();

                detalleFactura.setCantidad(cantidad);

                subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
                detalleFactura.setSubTotal(subTotal);

                valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);

                detalleFactura.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));

                detalleFactura.setDescuento(0.00);
                detalleFactura.setExistencia(0.00);
                detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
                detalleFactura.setNuevaExistencia(0.0);

                detalleFactura.setItbis(valorItbis);
                detalleFactura.setTasaItbis(detCto.getTasaItbis());

                detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
                detalleFactura.setUnidad(unidad);

                //agregando detalle a la factura de los vehiculos 
                listaDetVehiculo.clear();
                listaDetVehiculo.addAll(ManejoContratoDeServicio.getInstancia()
                        .getDatosVehiculoo(detCto.getCodigo()));

                System.out.println(" datos vehiculo " + listaDetVehiculo.size() + " detCto.getCodigo() " + detCto.getCodigo());

                for (DatosDeVehiculo detVeh : listaDetVehiculo) {

                    System.out.println("configurando  factura datos de vehiculo ");
                    facturaDatosDeVehiculo = new FacturaDatosDeVehiculo();
                    // *****************************************

                    facturaDatosDeVehiculo.setFactura(detalleFactura);
                    facturaDatosDeVehiculo.setAdicional(detVeh.getAdicional());
                    facturaDatosDeVehiculo.setAnio(detVeh.getAnio());
                    facturaDatosDeVehiculo.setChasis(detVeh.getChasis());
                    facturaDatosDeVehiculo.setColor(detVeh.getColor());
                    facturaDatosDeVehiculo.setMarca(detVeh.getMarca());
                    facturaDatosDeVehiculo.setMatricula(detVeh.getMatricula());
                    facturaDatosDeVehiculo.setModelo(detVeh.getModelo());
                    facturaDatosDeVehiculo.setPlaca(detVeh.getPlaca());
                    facturaDatosDeVehiculo.setTipoVehiculo(detVeh.getTipoVehiculo());
                    facturaDatosDeVehiculo.setVehiculo(detVeh.getVehiculo());

                    listaDetalleFacDdatosVehiculo.add(facturaDatosDeVehiculo);

                }

                listaDetalleFactura.add(detalleFactura);

            }

            System.out.println("convertirFactTempAFact(factura) " + listaDetFactArticuloNuevo.size());

            try {

                System.out.println(" listaDetalleFactura " + listaDetalleFactura + " listaDetalleFactura.size() " + listaDetalleFactura.size());

                if (listaDetalleFactura.size() > 0) {

                    System.out.println("convertirFactTempAFact(factura) " + convertirFactTempAFact(factura).size());
                    listaDetalleFactura.addAll(convertirFactTempAFact(factura));
                    factura.setTotal(getTotal(listaDetalleFactura));
                    factura.setSubTotal(getSubTotal(listaDetalleFactura));
                    factura.setItbis(getItbis(listaDetalleFactura));
                    factura.setMonto(getTotal(listaDetalleFactura));
                    factura.setPendiente(getTotal(listaDetalleFactura));
                    factura.setTipoDeIngreso(ManejoTipoDeIngreso.getInstancia().getTipoDeIngreso(1));// 1 es ingresos no finaciero

                    factura.setDetalleFacturaCollection(listaDetalleFactura);

                    Double balancePendiente = ManejoFactura.getInstancia().getMontoPendiente(factura.getCliente());
                    System.out.println("balancePendiente ant de factura : " + balancePendiente);

                    factDb = guardar(factura);

                    if (factDb == null) {

                        ClaseUtil.mensaje(" Hubo Error creando la factura ");
                        return;

                    } else {

//                        // actualizar el detalle delos contrato 
                        actualizandoDetalleContrato(listaDetContratoDetallada);

                        balancePendiente = ManejoFactura.getInstancia().getMontoPendiente(factDb.getCliente());
                        System.out.println("balancePendiente despues de factura : " + (balancePendiente));
                        System.out.println("factDb " + factDb.getCodigo());
                        List<DetalleFactura> listaDet = ManejoFactura.getInstancia().getDetalleFactura(factDb.getCodigo());

                        System.out.println("listaDet " + listaDet + "  - factDb.getTotal() : " + factDb.getTotal());
                        //Actualizar inventario 
//                        if (listaDet.size() > 0) {
//                            ClaseUtil.mensaje("La factura tiene detalle");
                        ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorSalida(listaDet);
//                        } else {
//                            ClaseUtil.mensaje("La factura no  tiene detalle");
//                        }

                        listaDetalleFacDdatosVehiculo.forEach((fctdvh) -> {
                            System.out.println("item " + fctdvh);
                            ManejoFacturaDatosDeVehiculo.getInstancia().salvar(fctdvh);
                        });

                        //Actualizar el ncf 
                        ManejoRelacionNcf.getInstancia().actualizar(relacionNcf);

                        //Generando el historico de corte de facturacion 
                        detaCorte = new DetalleCorteDeFacturacion();

                        detaCorte.setFactura(factura);
                        detaCorte.setNumeroFactura(factura.getNumero());
                        detaCorte.setContrato(contrato);
                        detaCorte.setNumeroContrato(contrato.getNumeroDeContrato());
                        detaCorte.setCorteDeFacturacion(corteFact);
                        detaCorte.setTotalFactura(factura.getTotal());
                        listaDetCorte.add(detaCorte);

                        contrato.setDetalleContratoDeServicioCollection(listaDetContratoDetallada);
                        contrato.setFacturado(true);
                        ManejoContratoDeServicio.getInstancia().actualizar(contrato);

                        HistoricoBalancePendiente hBalance = new HistoricoBalancePendiente();

                        hBalance.setCliente(factura.getCliente().getCodigo());
                        hBalance.setFactura(factura.getCodigo());
                        hBalance.setFechaVencimiento(factura.getFecha());
                        hBalance.setTotal(balancePendiente - factDb.getTotal());

                        /*fORMA DE PAGO */
                        FormaPago formaPago = new FormaPago();
                        TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
                        formaPago.setTipoFormaPago(tipoFormaPago);
                        formaPago.setMonto(factura.getTotal());
                        formaPago.setUnidadDeNegocio(factDb.getUnidadDeNegocio().getCodigo());

                        formaPago.setDocumento(factDb.getCodigo());
                        formaPago.setTipoDocumento(7);
                        formaPago.setFecha(factDb.getFecha());
                        ManejoFormaPago.getInstancia().salvar(formaPago);

                        /* FIN DE LA FORMA DE PAGO */
                        ManejoHistoricoBalancePendiente.getInstancia().salvar(hBalance);
                        RptFactura.getInstancia().verFactura(factDb.getCodigo(), factDb.getUnidadDeNegocio().getCodigo());

                    }

                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            num++;

            corteFact.setCantidadFactura(num);

            corteFact.setDetalleCorteDeFacturacionCollection(listaDetCorte);

            ManejoCorteDeFacturacion.getInstancia().salvar(corteFact);

            Thread.sleep(200);

        } catch (Exception e) {

            ClaseUtil.mensaje("Hubo un error guardando la factura ");
            e.printStackTrace();
        }

    }

    private void agregarArticuloConPrecioDeLista(int unidadSalida, int almacen, int listaPrecio) {

        try {

            DetalleFacturaTemporal detalleFactura = new DetalleFacturaTemporal();
            Double total = 0.00, subTotal = 0.00, valorItbis = 0.00, montoGRavado = 0.00,
                    precioVentaItbis = 0.00, valorEnPeso = 0.00,
                    porcientoGravado = VariablesGlobales.USUARIO.getUnidadDeNegocio().getMontoGravado() / 100;
            double itbis = 0.00;

            //Obtenemos el precio de lista
            DetalleListaDePrecio detalleListaDePrecio = ManejoListaDePrecio.getInstancia()
                    .getDetalleListaDePrecio(getArticulo().getCodigo(), unidadSalida, catidadArticulo);

            if (!(detalleListaDePrecio == null)) {

                precioVenta = detalleListaDePrecio.getPrecio();
                System.out.println("Precio Lista general " + precioVenta);
            }

            if (precioVenta <= 0) {

                ClaseUtil.mensaje("Este Articulo con esta unidad no tiene precio ");
                return;
            }

            if (unidadDespacho == 2) {

                valorEnPeso = catidadArticulo;
                catidadArticulo = FormatNum.FormatearDouble(catidadArticulo / precioVenta, 4);
            }

            detalleFactura.setArticulo(getArticulo());

            detalleFactura.setCantidad(catidadArticulo);
            detalleFactura.setNombreArticulo(getArticulo().getNombre());
            detalleFactura.setAlmacen(ManejoAlmacen.getInstancia().getalmacen(almacen));

            ExistenciaArticulo exisArt = ManejoExistenciaArticulo.getInstancia()
                    .getExistenciaArticulo(detalleFactura.getArticulo().getCodigo(),
                            almacen);

            System.out.println("exisArt " + exisArt);
            detalleFactura.setExistencia(exisArt.getExistencia());

            detalleFactura.setPrecio(precioVenta);
            detalleFactura.setPrecioItbis(precioVentaItbis);
            detalleFactura.setUnidad(ManejoUnidad.getInstancia().getUnidad(unidadSalida));

            Integer unidadInventario = ManejoArticuloUnidad.getInstancia()
                    .getArticuloUnidadSslida(detalleFactura.getArticulo().getCodigo()).getUnidad().getCodigo();

            ArticuloUnidad artUnidad = ManejoArticuloUnidad.getInstancia()
                    .getArticuloUnidadSslida(detalleFactura.getArticulo().getCodigo(), detalleFactura.getUnidad().getCodigo());

            detalleFactura.setUnidadInventario(unidadInventario);
//            detalleFactura.setAlmacen(almacen);
            detalleFactura.setCantidadInventario(catidadArticulo * artUnidad.getFatorVenta());
            detalleFactura.setNuevaExistencia(exisArt.getExistencia() - detalleFactura.getCantidadInventario());
            detalleFactura.setCostoUnitario(artUnidad.getCostoUnitario());

            detalleFactura.setFactorUnidadSalida(artUnidad.getFatorVenta());
            detalleFactura.setListaDePrecio(listaPrecio);

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

                detalleFactura.setDescuento(0.00);
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

            detalleFactura.setFactura(facturaTemporal);
//            detalleFactura.setMontoExcento((subTotal - montoGRavado));
//            detalleFactura.setMontoGravado(montoGRavado);
//            double valorItbisGravado = util.FormatNum.FormatearDouble((detalleFactura.getMontoGravado()) * (itbis / 100), 2);
//            detalleFactura.setMontoItbisExcento(valorItbis - valorItbisGravado);
            total = ClaseUtil.roundDouble((((subTotal - detalleFactura.getDescuento()) + valorItbis)), 2);
            detalleFactura.setTotal(total);
//            detalleFactura.setTotalACobrar(total);

            if (existe(detalleFactura)) {

                agruparDetallePorArticulo(detalleFactura);

            } else if (!existe(detalleFactura)) {

                listaDetalleFacturaTemporal.add(detalleFactura);

            }

            listaDetFactArticuloNuevo.add(detalleFactura);
            System.out.println("listaDetFactArticuloNuevo det " + listaDetFactArticuloNuevo.size());
            txtCodigoArticulo.clear();
            iniciazarFiltro();

            txtSubTotal.setText(getSubTotal().toString());

            txtItbis.setText(getItbis().toString());
            txtTotal.setText(getTotal().toString());

            facturaTemporal.setDetalleFacturaTemporalCollection(listaDetalleFacturaTemporal);
            ManejoFacturaTemporal.getInstancia().actualizar(facturaTemporal);

            detalleFactura = new DetalleFacturaTemporal();

            setArticulo(null);

            txtCodigoArticulo.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean existe(DetalleFacturaTemporal det) {

        for (DetalleFacturaTemporal detalle : tbDetalleFactura.getItems()) {

            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())
                    && Objects.equals(detalle.getUnidad().getCodigo(), det.getUnidad().getCodigo())) {

                return true;

            }
        }

        return false;
    }

    private void agruparDetallePorArticulo(DetalleFacturaTemporal det) {

        for (DetalleFacturaTemporal detalle : tbDetalleFactura.getItems()) {

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

    private Double getSubTotal() {

        Double subTotal = 0.00;

        for (DetalleFacturaTemporal det : listaDetalleFacturaTemporal) {

//            subTotal += det.getCantidad() * det.getPrecio();
            subTotal += det.getSubTotal();
        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotal() {

        Double total = 0.00;

        for (DetalleFacturaTemporal det : listaDetalleFacturaTemporal) {

//            total += (det.getCantidad() * det.getPrecio()) + det.getItbis();
            total += det.getTotal();
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getItbis() {

        Double itbis = 0.00;

        for (DetalleFacturaTemporal det : listaDetalleFacturaTemporal) {

            itbis += det.getItbis();
        }

        return FormatNum.FormatearDouble(itbis, 2);
    }

    private HBox visualizarArticulo(Articulo articulo) {

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

        Label lbCodio = new Label("Codigo  :  " + articulo.getNumero().toString());
        Label lbPrecio = new Label("Precio :  " + Double.toString(articulo.getPrecioVenta1() == null ? 0 : articulo.getPrecioVenta1()) + "  $ ");
        Label lbNombre = new Label("Nombre :  " + articulo.getNombre() == null ? articulo.getDescripcion() : articulo.getNombre());

        lbCodio.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 15pt;");
        lbPrecio.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 15pt;");
        lbNombre.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 12pt;");

        vb.getChildren().add(lbCodio);

        vb.getChildren().add(lbNombre);

        vb.getChildren().add(lbPrecio);

//        hb.getChildren().add(img);
        hb.getChildren().add(vb);
        hb.setCursor(Cursor.HAND);
        return hb;

    }

}
