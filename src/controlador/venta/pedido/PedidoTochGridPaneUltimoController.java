/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.pedido;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controlador.direccion.FXMLBuscarDireccionrController;
import controlador.teclado.TecladoDigitalDireccionController;
import controlador.teclado.TecladoDigitalNumericoController;
import controlador.venta.cliente.FXMLBusClienterController;
import dto.articulo.ArticuloDTO;
import dto.articulo.DetallePedidoDTO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.annotation.PostConstruct;
import manejo.ManejoConfiguracion;
import manejo.pedido.ManejoPedido;
import manejo.articulo.ManejoArticulo;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.articulo.ManejoListaDePrecio;
import manejo.caja.ManejoTurnoPedido;
import manejo.cliente.ManejoCliente;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoFormaPago;
import manejo.factura.ManejoRelacionNcf;
import manejo.factura.ManejoTipoFormaPago;
import manejo.pedido.ManejoEstadoPedido;
import manejo.unidad.ManejoUnidad;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.DetalleListaDePrecio;
import modelo.DetallePedido;
import modelo.Direccion;
import modelo.EstadoPedido;
import modelo.FormaPago;
import modelo.Pedido;
import modelo.RelacionNcf;
import modelo.TipoFormaPago;
import modelo.TipoNcf;
import modelo.TurnoPedido;
import util.GUIUtils;
import util.VariablesGlobales;
import utiles.ClaseUtil;
import utiles.FormatNum;
import vista.venta.facturacion.FormaPagoController;
import vista.venta.facturacion.TecladoDigitalController;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class PedidoTochGridPaneUltimoController implements Initializable {

    @FXML
    private JFXButton btnRuta;
    @FXML
    private JFXButton btnDeContado;
    @FXML
    private JFXTextField txtNombreCliente;
    @FXML
    private JFXButton btnBuscarCliente;
    @FXML
    private TableView<DetallePedido> tbDetallePedido;
    @FXML
    private TableView<Articulo> tbArticulo;
    @FXML
    private TableColumn<Articulo, Articulo> tbcImagenArticulo;
    @FXML
    private TableColumn<DetallePedido, DetallePedido> tbcImagenDetallePedido;

    /**
     * @return the editar
     */
    public Boolean getEditar() {
        return editar;
    }

    /**
     * @param editar the editar to set
     */
    public void setEditar(Boolean editar) {
        this.editar = editar;
    }

    /**
     * @return the pedido
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

//    private ScrollPane scpane1;
    /**
     * @return the direccion
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    private GridPane gpArticulo = new GridPane();
    private GridPane gpDetallePedido = new GridPane();
    ObservableList<Button> listaArticulo = FXCollections.observableArrayList();

    ArticuloDTO articuloDTO;
    Double precioVenta = 0.00;

    @FXML
    private JFXTextField txtTotal;
    PedidoTochGridPaneUltimoController facturacionTochController;
    @FXML
    private JFXTextField txtCodigoArticulo;
    @FXML
    private Button btnBuscarArticulo;

    int unidadDespacho = 1;

    @FXML
    private JFXTextField txtNumeroCalle;
    @FXML
    private JFXTextField txtNumeroCasa;
    @FXML
    private JFXTextField txtDireccion;

    public PedidoTochGridPaneUltimoController getFacturacionTochController() {
        return facturacionTochController;
    }

    public void setFacturacionTochController(PedidoTochGridPaneUltimoController facturacionTochController) {
        this.facturacionTochController = facturacionTochController;
    }

    ObservableList<DetallePedido> listadetallePedido = FXCollections.observableArrayList();
    ObservableList<DetallePedidoDTO> listadetallePedidoDto = FXCollections.observableArrayList();
    ObservableList<String> listaTipoVenta = FXCollections.observableArrayList();

    ObservableList<Articulo> listaArticulos = FXCollections.observableArrayList();

    Double totalPago;
    int numeroArticulo = 0;

    int tipoVenta = 1;// El valor uno es venta de contado y el dos es venta a credito ;
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
    int codigoPedido;

    Cliente cliente;
    private Direccion direccion;

    private Pedido pedido;
    DetalleFactura detalleFactura;
    DetallePedido detallePedido;
    FormaPago formaPago;
    Articulo articulo;
    @FXML
    private JFXTextField txtNumeroFactura;

    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnGuardar;

    public JFXButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JFXButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }
    private TextField txtCAtidad;
    @FXML
    private JFXButton btnCredito;

    Integer cantidad = 0;
    Double catidadArticulo = 0.0;
    Double existenciaEnPeso = 0.00;
    Double valorDevuelta = 0.00;
    Double deVueltaDe = 0.0;
    String numStr;
    private Boolean editar = false;

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
    @PostConstruct
    public void afterInitialize() {
        // Do things that are reliant upon the FXML being loaded
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTabla();
        iniciazarFiltro();
        inicializarTablaDetaPedido();
        gpDetallePedido.setHgap(0);
        gpDetallePedido.setVgap(3);

        gpArticulo.setAlignment(Pos.TOP_CENTER);
        gpArticulo.setScaleShape(true);

        gpArticulo.setHgap(2);
        gpArticulo.setVgap(8);

        if (getEditar() == false) {
            nuevo();
        }

        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                crearArticulo();

                txtCodigoArticulo.requestFocus();

            }
        });

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

                if (articuloFiltro.getCodigo() != null && articuloFiltro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (articuloFiltro.getNombre() != null && articuloFiltro.getNombre().toLowerCase().contains(lowerCaseFilter)) {
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

        listaArticulos.addAll(ManejoArticulo.getInstancia().getListaArticulos(2));

        tbcImagenArticulo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbArticulo.setItems(listaArticulos);
        GUIUtils.autoFitTable(tbArticulo);

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

                                    if (existencia < existenciaEnPeso) {

                                        ClaseUtil.mensaje("La cantidad a Despachar " + catidadArticulo
                                                + "  no puede ser mayor que la existencia ,"
                                                + " Existencia igual a  " + existencia + "   " + nombreUnidad);

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

    }

    public void inicializarTablaDetaPedido() {

        listadetallePedido.clear();
        tbcImagenDetallePedido.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbDetallePedido.setItems(listadetallePedido);

        tbcImagenDetallePedido.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });
//Seleccionar pedido
        tbcImagenDetallePedido.setCellFactory((TableColumn<DetallePedido, DetallePedido> param) -> {

            TableCell<DetallePedido, DetallePedido> cellsc = new TableCell<DetallePedido, DetallePedido>() {
                @Override
                public void updateItem(DetallePedido item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item != null) {

                        HBox hbox = visualizarDetallePedido(item);

                        hbox.setOnMouseClicked((event) -> {

//                            setArticulo(item);
//
//                            try {
//
//                                FXMLLoader loader = new FXMLLoader();
//
//                                loader.setLocation(getClass().getResource("/vista/venta/facturacion/TecladoDigital.fxml"));
//                                loader.load();
//
//                                Parent root = loader.getRoot();
//                                TecladoDigitalController tecladoDigitalController = loader.getController();
//
//                                tecladoDigitalController.setArticulo(getArticulo());
//                                tecladoDigitalController.llenarCampo();
//                                tecladoDigitalController.getTxtCAtidad().requestFocus();
//
//                                ClaseUtil.getStageModal(root);
//
//                                unidadDespacho = tecladoDigitalController.getUnidadDespacho();
//
//                                System.out.println("Unidad despacho " + unidadDespacho);
//
//                                if (!(getArticulo() == null) && !tecladoDigitalController.getTxtCAtidad().getText().isEmpty()) {
//
//                                    catidadArticulo = Double.parseDouble(tecladoDigitalController.getTxtCAtidad().getText());
//
//                                    int unidad = tecladoDigitalController.getArticuloDTO().getCodigoUnidad();
//                                    int listaPrecio = tecladoDigitalController.getArticuloDTO().getListaDePrecio();
//                                    int almacen = tecladoDigitalController.getArticuloDTO().getAlmacen();
//                                    String nombreUnidad = tecladoDigitalController.getArticuloDTO().getUnidad();
//                                    precioVenta = tecladoDigitalController.getArticuloDTO().getPrecioVenta();
//
//                                    System.out.println("Precio de Venta  " + precioVenta + " lista precio  " + listaPrecio);
//                                    Double existencia = ManejoExistenciaArticulo
//                                            .getInstancia()
//                                            .getExistenciaArticulo(getArticulo().getCodigo(), almacen, unidad, listaPrecio);
//
//                                    existencia = FormatNum.FormatearDouble(existencia, 2);
//
//                                    System.out.println("existencia < existenciaEnPeso " + existencia + " " + existenciaEnPeso);
//
//                                    if (unidadDespacho == 2) {
//
//                                        existenciaEnPeso = FormatNum
//                                                .FormatearDouble(catidadArticulo / getArticulo()
//                                                        .getPrecioVenta1(), 4);
//                                        System.out.println("Cantidad en peso " + catidadArticulo);
//
//                                    } else {
//                                        existenciaEnPeso = catidadArticulo;
//                                    }
//
//                                    if (existencia < existenciaEnPeso) {
//
//                                        ClaseUtil.mensaje("La cantidad a Despachar " + catidadArticulo
//                                                + "  no puede ser mayor que la existencia ,"
//                                                + " Existencia igual a  " + existencia + "   " + nombreUnidad);
//
//                                        return;
//                                    }
//
////                                    agregarArticuloConPrecioDeLista(unidad, almacen, listaPrecio);
//                                }
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
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

        Label lbCodio = new Label("Codigo  :  " + articulo.getCodigo().toString());
        Label lbPrecio = new Label("Precio :  " + Double.toString(articulo.getPrecioVenta1()) + "  $ ");
        Label lbNombre = new Label("Nombre :  " + articulo.getNombre());

        lbCodio.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 13pt;");
        lbPrecio.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 13pt;");
        lbNombre.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 11pt;");

        vb.getChildren().add(lbCodio);

        vb.getChildren().add(lbNombre);

        vb.getChildren().add(lbPrecio);

        hb.getChildren().add(img);
        hb.getChildren().add(vb);
        hb.setCursor(Cursor.HAND);
        return hb;

    }

    private HBox visualizarDetallePedido(DetallePedido detallePedido) {

        ImageView img = null;
        File imageFile;

        if (detallePedido.getArticulo().getRutaImg() == null) {

            imageFile = new File("foto/img_articulo.jpg");

        } else {

            imageFile = new File(detallePedido.getArticulo().getRutaImg());
        }

        img = new ImageView(new Image(imageFile.toURI().toString(), 130, 100, false, false));
        img.setFitWidth(80);
        img.setFitHeight(40);

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER_LEFT);

        HBox hb = new HBox();

        hb.setAlignment(Pos.CENTER_LEFT);

        hb.setSpacing(6);

        vb.setSpacing(5);
        vb.setPadding(new Insets(5, 5, 5, 5));

        hb.setStyle("   -fx-text-fill:000000;\n"
                + " -fx-background-color: #FFFFFF;"
                + "    -fx-border-color:  #00bb2d;\n"
                + "    -fx-border-radius: 5px;\n"
                + "    -fx-background-radius: 10px;\n"
                + "    -fx-font-size: 12pt;");

        String unidad = detallePedido.getArticulo().getNombreEmbase() == null ? detallePedido.getUnidad().getDescripcion()
                : detallePedido.getArticulo().getNombreEmbase();

        if (getEditar() == true && !(detallePedido.getCodigo() == null)) {

            hb.setAccessibleText(Integer.toString(detallePedido.getCodigo()));

        }

        Label lbCantidad = null;

        String catidadStr = detallePedido.getCantidad().toString().replace(".", "-");

        String cantidadNueva = catidadStr.split("-")[1];

        if (Integer.parseInt(cantidadNueva) > 0) {

            lbCantidad = new Label(detallePedido.getCantidad().toString());

        } else {

            cantidadNueva = catidadStr.split("-")[0];

            lbCantidad = new Label(cantidadNueva);
        }

        lbCantidad.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 14pt;"
                + "  ");

        String articuloDesc
                = //detallePedido.getCantidad().toString() + "  "
                " " + unidad + " DE " + detallePedido.getNombreArticulo() + "   Total : " + Double.toString(detallePedido.getTotal()) + " $ ";

        Label lbCodio = new Label("Codigo  :  " + detallePedido.getArticulo()
                .getCodigo().toString() + " -> " + detallePedido.getArticulo().getNombre());

        Label lbPrecio = new Label("Precio :  " + Double.toString(detallePedido.getArticulo().getPrecioVenta1()) + "  $ "
                + " Cantidad :  " + detallePedido.getCantidad() + " Total : " + detallePedido.getTotal()
        );
        Label lbArticuloDetalle = new Label(articuloDesc);

        lbCodio.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 13pt;");
        lbPrecio.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 13pt;");

        lbArticuloDetalle.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 11pt;");

        vb.getChildren().add(lbArticuloDetalle);

//        vb.getChildren().add(lbCodio);
//        vb.getChildren().add(lbPrecio);
        hb.getChildren().add(img);
        hb.getChildren().add(lbCantidad);
        hb.getChildren().add(vb);
        hb.setCursor(Cursor.HAND);
        return hb;

    }

    private void crearArticulo(int categoria) {

        ImageView img = null;
        Button btn;
//        VBox vb = new VBox();

//        vb.getChildren().add(new HBox());
        List<Articulo> lista = ManejoArticulo.getInstancia().getArticuloPorCategoria(categoria);

        gpArticulo.getChildren().clear();

        File imageFile;
        int num = 0;
        for (int x = 0; x < lista.size(); x++) {

            for (int y = 0; y < 3; y++) {

                if (num < lista.size()) {

                    if (lista.get(num).getRutaImg() == null) {

                        imageFile = new File("foto/img_articulo.jpg");

                    } else {

                        imageFile = new File(lista.get(num).getRutaImg());
                    }

//                   imageFile = new File(lista.get(num).getRutaImg());
                    img = new ImageView(new Image(imageFile.toURI().toString(), 130, 100, false, false));
                    img.setFitWidth(130);
                    img.setFitHeight(100);

                    btn = new Button(lista.get(num).getNombre(), img);

//                    btn.setStyle("-fx-base: coral;");
                    btn.setContentDisplay(ContentDisplay.TOP);

                    btn.setId(Integer.toString(lista.get(num).getCodigo()));
                    btn.setAccessibleHelp(lista.get(num).getNombre());
                    btn.setCursor(Cursor.HAND);

                    btn.setPrefSize(140, 120);
                    btn.setMinWidth(140);
                    btn.setMaxWidth(140);
//                    vb = new VBox();
//                    vb.getChildren().add(btn);
//                    btn.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

                    GridPane.setConstraints(btn, y, x);

                    gpArticulo.add(btn, y, x);

                }

                num++;

//                }
            }
            x++;

        }

        visualizarArticuloHb();

    }

    private void crearArticulo() {

        ImageView img = null;

        List<Articulo> lista = ManejoArticulo.getInstancia().getArticuloPorCategoria(1);

        gpArticulo.getChildren().clear();

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

//                   imageFile = new File(lista.get(num).getRutaImg());
                    img = new ImageView(new Image(imageFile.toURI().toString(), 90, 70, false, false));
                    img.setFitWidth(90);
                    img.setFitHeight(70);

                    VBox vb = new VBox();
                    HBox hb = new HBox();
                    hb.setMaxWidth(Double.MAX_VALUE);
                    hb.setAlignment(Pos.CENTER_LEFT);
                    vb.setMaxWidth(Double.MAX_VALUE);
                    hb.setSpacing(5);
                    vb.setSpacing(5);

                    hb.setPadding(new Insets(5, 5, 5, 5));
                    hb.setStyle("   -fx-text-fill:000000;\n"
                            + " -fx-background-color: #394B52;"
                            + "    -fx-border-color:  #00bb2d;\n"
                            + "    -fx-border-radius: 5px;\n"
                            + "    -fx-background-radius: 10px;\n"
                            + "    -fx-font-size: 12pt;");

                    hb.getChildren().add(img);

                    Label lbNombre = new Label();
                    Label lbPrecio = new Label("  Precio : " + Double.toString(lista.get(num).getPrecioVenta1()) + "  $");
                    Label lbExistencia = new Label("Existencia : "
                            + Double.toString(lista.get(num).getExistencia()) + "  " + lista.get(num).getUnidadSalida().getDescripcion());

                    lbNombre.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 13pt;");
                    lbPrecio.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 13pt;");
                    lbExistencia.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 13pt;");

                    String desArticulo = " " + lista.get(num).getNombre() + " " + lbPrecio.getText();

                    lbNombre.setText(desArticulo);
//                    vb.getChildren().add(lbNombre);
//                    vb.getChildren().add(lbPrecio);
//                    vb.getChildren().add(lbExistencia);
                    hb.getChildren().add(lbNombre);

                    hb.setId(Integer.toString(lista.get(num).getCodigo()));
                    hb.setCursor(Cursor.HAND);

                    GridPane.setConstraints(hb, y, x);

                    gpArticulo.add(hb, y, x);// y indice de la columna,x indice de la fila 

                }

                num++;

            }
        }

        visualizarArticuloHb();

    }

    private void vistaDetallePedido(List<DetallePedido> lista) {

        ImageView img = null;

//        List<DetallePedido> lista = ManejoPedido.getInstancia().getDetallePedido(pedido);
//        System.out.println("cantidad de item " + lista.size());
        gpDetallePedido.getChildren().clear();

        File imageFile;
        int num = 0;
        int indice = 1;
        for (int x = 0; x < lista.size(); x++) {

            for (int y = 0; y < 1; y++) {

//                if (num < lista.size()) {
                if (lista.get(num).getArticulo().getRutaImg() == null) {

                    imageFile = new File("foto/img_articulo.jpg");

                } else {

                    imageFile = new File(lista.get(num).getArticulo().getRutaImg());
                }

                img = new ImageView(new Image(imageFile.toURI().toString(), 75, 50, false, false));
                img.setFitWidth(70);
                img.setFitHeight(50);
//                img.setStyle(" -fx-border-radius: 5px;");

                HBox hb = new HBox();
                hb.setAlignment(Pos.CENTER_LEFT);
                hb.setMaxWidth(Double.MAX_VALUE);
                hb.setSpacing(15);
                hb.setPadding(new Insets(3, 2, 2, 2));

                Label lbArticuloDesc = new Label();
                lbArticuloDesc.setStyle(" -fx-text-fill: #ffffff;"
                        + " -fx-font-size: 13pt;");
                String articuloDesc;

                hb.setStyle("   -fx-text-fill:000000;\n"
                        + " -fx-background-color: #394B52;"
                        + "    -fx-border-color:  #00bb2d;\n"
                        + "    -fx-border-radius: 5px;\n"
                        + "    -fx-background-radius: 10px;\n"
                        + "    -fx-font-size: 14pt;");

                hb.getChildren().add(img);

//                hb.getChildren().add(new Label(lista.get(num).getCantidad().toString()));
                String unidad = lista.get(num).getArticulo().getNombreEmbase() == null ? lista.get(num).getUnidad().getDescripcion()
                        : lista.get(num).getArticulo().getNombreEmbase();

                hb.setId(Integer.toString(lista.get(num).getNumeroArticulo()));

                System.out.println("Codigo " + lista.get(num).getCodigo());

                if (getEditar() == true && !(lista.get(num).getCodigo() == null)) {

                    hb.setAccessibleText(Integer.toString(lista.get(num).getCodigo()));

                }

                articuloDesc = lista.get(num).getCantidad().toString() + "  "
                        + unidad + " DE " + lista.get(num).getNombreArticulo() + "  Valor : " + Double.toString(lista.get(num).getTotal()) + " $ ";
                lbArticuloDesc.setText(articuloDesc);

                hb.getChildren().add(lbArticuloDesc);

                hb.setCursor(Cursor.HAND);

                GridPane.setConstraints(hb, y, x);
                gpDetallePedido.add(hb, y, x);// y indice de la columna,x indice de la fila 

                num++;
                indice++;

//                }
            }

            System.out.println("Valor x " + x);
        }

//        scpane1.setContent(gpDetallePedido);
        visualizarDetallePedido();

    }

    public void visualizarDetallePedido() {

        gpDetallePedido.getStylesheets().add(getClass().getResource("/css/btn_articulos.css").toExternalForm());

        organizarIndice();

        for (int i = 0; i < gpDetallePedido.getChildren().size(); i++) {

            HBox btn = (HBox) gpDetallePedido.getChildren().get(i);
            btn.setCursor(Cursor.HAND);
            btn.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

            btn.setOnMouseClicked((event) -> {

                int codigoDetallePedido = Integer.parseInt(btn.getAccessibleText() == null ? "0" : btn.getAccessibleText());

                int numeroDeLinea = Integer.parseInt(btn.getId());

                System.out.println("numeroDeLinea " + numeroDeLinea + " codigoDetallePedido  " + codigoDetallePedido);

                detallePedido = ManejoPedido.getInstancia().getDetallePedidoPorCodigo(codigoDetallePedido);

                if (getEditar() == true) {

                    codigoDetallePedido = Integer.parseInt(btn.getAccessibleText() == null ? "0" : btn.getAccessibleText());

                    numeroDeLinea = Integer.parseInt(btn.getId());

                    System.out.println("numeroDeLinea " + numeroDeLinea + " codigoDetallePedido  " + codigoDetallePedido);

                    detallePedido = ManejoPedido.getInstancia().getDetallePedidoPorCodigo(codigoDetallePedido);

                    String articuloNombre = detallePedido == null ? " " : detallePedido.getArticulo().getNombre();

                    if (ClaseUtil.confirmarMensaje("Seguro que quiere eliminar este articulo \n  " + articuloNombre).get() == ButtonType.YES) {

                        if (!(detallePedido == null)) {

                            ManejoPedido.getInstancia().remover(detallePedido);
                        }

                        listadetallePedido.remove(numeroDeLinea);

                        organizarIndice();
                        txtTotal.setText(getTotal().toString());
                        vistaDetallePedido(listadetallePedido);
                    }

                } else {

                    if (ClaseUtil.confirmarMensaje("Seguro que quiere eliminar este articulo ").get() == ButtonType.YES) {

                        listadetallePedido.remove(numeroDeLinea);
                        organizarIndice();
                        txtTotal.setText(getTotal().toString());
                        vistaDetallePedido(listadetallePedido);
                    }

                }

            });

        }

    }

    private void crearArticuloPorBusqueda(List<Articulo> lista) {

        ImageView img = null;
        Button btn;
//        VBox vb = new VBox();

//        vb.getChildren().add(new HBox());
//        List<Articulo> lista = ManejoArticulo.getInstancia().getArticuloPorCategoria(1);
        gpArticulo.getChildren().clear();

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

//                   imageFile = new File(lista.get(num).getRutaImg());
                    img = new ImageView(new Image(imageFile.toURI().toString(), 90, 70, false, false));
                    img.setFitWidth(90);
                    img.setFitHeight(70);

                    VBox vb = new VBox();
                    HBox hb = new HBox();
                    hb.setMaxWidth(Double.MAX_VALUE);
                    hb.setAlignment(Pos.CENTER_LEFT);
                    vb.setMaxWidth(Double.MAX_VALUE);
                    hb.setSpacing(5);
                    vb.setSpacing(5);

                    hb.setPadding(new Insets(5, 5, 5, 5));
                    hb.setStyle("   -fx-text-fill:000000;\n"
                            + " -fx-background-color: #394B52;"
                            + "    -fx-border-color:  #00bb2d;\n"
                            + "    -fx-border-radius: 5px;\n"
                            + "    -fx-background-radius: 10px;\n"
                            + "    -fx-font-size: 12pt;");

                    hb.getChildren().add(img);

                    Label lbNombre = new Label();
                    Label lbPrecio = new Label("  Precio : " + Double.toString(lista.get(num).getPrecioVenta1()) + "  $");
                    Label lbExistencia = new Label("Existencia : "
                            + Double.toString(lista.get(num).getExistencia()) + "  " + lista.get(num).getUnidadSalida().getDescripcion());

                    lbNombre.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 13pt;");
                    lbPrecio.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 13pt;");
                    lbExistencia.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 13pt;");

                    String desArticulo = lista.get(num).getNombre() + "  " + lbPrecio.getText();

                    lbNombre.setText(desArticulo);
//                    vb.getChildren().add(lbNombre);
//                    vb.getChildren().add(lbPrecio);
//                    vb.getChildren().add(lbExistencia);
                    hb.getChildren().add(lbNombre);

                    hb.setId(Integer.toString(lista.get(num).getCodigo()));
                    hb.setCursor(Cursor.HAND);

                    GridPane.setConstraints(hb, y, x);

                    gpArticulo.add(hb, y, x);// y indice de la columna,x indice de la fila 

                }

                num++;

            }
        }

        visualizarArticuloHb();

    }

    private void crearArticuloPorBusquedaAnt(List<Articulo> lista) {

        ImageView img = null;
        Button btn;
        File imageFile;

        gpArticulo.getChildren().clear();
        int num = 0;
        for (int x = 0; x < lista.size(); x++) {

            for (int y = 0; y < 3; y++) {

                if (num < lista.size()) {

                    if (lista.get(num).getRutaImg() == null) {

                        imageFile = new File("foto/img_articulo.jpg");

                    } else {

                        imageFile = new File(lista.get(num).getRutaImg());
                    }

//                    File imageFile = new File(lista.get(num).getRutaImg());
                    img = new ImageView(new Image(imageFile.toURI().toString(), 150, 130, false, false));
                    img.setFitWidth(150);
                    img.setFitHeight(130);

                    btn = new Button(lista.get(num).getNombre(), img);
//                    btn.setStyle("-fx-base: coral;");
                    btn.setContentDisplay(ContentDisplay.TOP);

                    btn.setId(Integer.toString(lista.get(num).getCodigo()));
                    btn.setAccessibleHelp(lista.get(num).getNombre());

                    btn.setPrefSize(160, 140);
                    btn.setMinWidth(160);
                    btn.setMaxWidth(160);
//                    btn.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

                    GridPane.setConstraints(btn, y, x);

                    gpArticulo.add(btn, y, x);

                }

                num++;

//                }
            }
            x++;

            System.out.println("|");
        }

        visualizarArticuloHb();
    }

    private void crearArticuloPorSubCategoria(int subCategoria) {

        ImageView img = null;
        Button btn;

        File imageFile;
        List<Articulo> lista = ManejoArticulo
                .getInstancia()
                .getArticuloPorSubCategoria(subCategoria);
        gpArticulo.getChildren().clear();

        int num = 0;
        for (int x = 0; x < lista.size(); x++) {

            for (int y = 0; y < 3; y++) {

                if (num < lista.size()) {

                    if (lista.get(num).getRutaImg() == null) {

                        imageFile = new File("foto/img_articulo.jpg");

                    } else {

                        imageFile = new File(lista.get(num).getRutaImg());
                    }
//                    File imageFile = new File(lista.get(num).getRutaImg());

                    img = new ImageView(new Image(imageFile.toURI().toString(), 130, 100, false, false));
                    img.setFitWidth(130);
                    img.setFitHeight(100);

                    btn = new Button(lista.get(num).getNombre(), img);
//                    btn.setStyle("-fx-base: coral;");
                    btn.setContentDisplay(ContentDisplay.TOP);

                    btn.setId(Integer.toString(lista.get(num).getCodigo()));
                    btn.setAccessibleHelp(lista.get(num).getNombre());

                    btn.setPrefSize(140, 120);
                    btn.setMinWidth(140);
                    btn.setMaxWidth(140);

                    GridPane.setConstraints(btn, y, x);

                    gpArticulo.add(btn, y, x);

                }

                num++;

            }

        }

        visualizarArticuloHb();
    }

    public void visualizarArticuloHb() {

        gpArticulo.getStylesheets().add(getClass().getResource("/css/btn_articulos.css").toExternalForm());
        for (int i = 0; i < gpArticulo.getChildren().size(); i++) {

            HBox btn = (HBox) gpArticulo.getChildren().get(i);
            btn.setCursor(Cursor.HAND);
//            btn.getStylesheets().add(getClass().getResource("/css/btn_articulos.css").toExternalForm());

            btn.setOnMouseClicked((event) -> {

                int codigo = Integer.parseInt(btn.getId().trim());
                System.out.println("CodArticulo " + btn.getId());

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
//
                        catidadArticulo = Double.parseDouble(tecladoDigitalController.getTxtCAtidad().getText());

                        Double existencia = FormatNum.FormatearDouble(getArticulo().getExistencia(), 2);

                        if (unidadDespacho == 2) {

                            existenciaEnPeso = FormatNum
                                    .FormatearDouble(catidadArticulo / getArticulo()
                                            .getPrecioVenta1(), 4);
                            System.out.println("Cantidad en peso " + catidadArticulo);

                        } else {
                            existenciaEnPeso = catidadArticulo;
                        }

                        if (existencia < existenciaEnPeso) {

                            ClaseUtil.mensaje("La cantidad a Despachar " + catidadArticulo
                                    + "  no puede ser mayor que la existencia ,"
                                    + " Existencia igual a " + existencia);

                            return;
                        }

                        agregarArticulo();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

        }

    }

    private void llenarClientedeContado() {

        setCliente(ManejoCliente.getInstancia().getCliente(1));
//        txtCodigoCliente.setText(getCliente().getCodigo().toString());
//        txtNombreCliente.setText(getCliente().getNombre());
    }

    private void inicializarDatos() {

        llenarClientedeContado();

        TipoNcf tnf = manejo.factura.ManejoTipoNcf.getInstancia().getListaTipoNcf().get(0);
        relacionNcf = ManejoRelacionNcf.getInstancia().getNCF(tnf);

    }

    private void limpiar() {

        txtTotal.clear();
        txtNumeroCalle.clear();
        txtNumeroCasa.clear();
        txtDireccion.clear();

        listadetallePedido.clear();

        llenarClientedeContado();
    }

    private void nuevo() {

        setPedido(new Pedido());
        detallePedido = new DetallePedido();

        txtNumeroFactura.setText(ManejoPedido.getInstancia().getNumero().toString());
        listaArticulo.clear();
        gpDetallePedido.getChildren().clear();

        crearArticulo();

        txtCodigoArticulo.requestFocus();

        limpiar();

    }

    private void agregarArticuloConPrecioDeLista(int unidadSalida, int almacen, int listaPrecio) {

        try {

            detallePedido = new DetallePedido();
            Double total = 0.00, subTotal = 0.00, valorItbis = 0.00,
                    precioVentaItbis = 0.00, valorEnPeso = 0.00;;

            System.out.println("Precio de venta " + precioVenta);

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

            detallePedido.setArticulo(getArticulo());
            detallePedido.setCantidad(catidadArticulo);
            detallePedido.setDescuento(Double.parseDouble("0"));
            detallePedido.setExistencia(getArticulo().getExistencia());
            detallePedido.setNombreArticulo(getArticulo().getNombre());
            detallePedido.setNuevaExistencia(0.0);
            detallePedido.setPrecio(precioVenta);

            detallePedido.setPrecioItbis(precioVentaItbis);
            detallePedido.setUnidad(ManejoUnidad.getInstancia().getUnidad(unidadSalida));

            if (unidadDespacho == 2) {

                subTotal = valorEnPeso;

            } else {

                subTotal = ClaseUtil.roundDouble(detallePedido.getCantidad() * detallePedido.getPrecio(), 2);
            }

            detallePedido.setPrecioItbis(precioVenta);
            total = ClaseUtil.roundDouble((subTotal - detallePedido.getDescuento()) + valorItbis, 2);
//            detallePedido.setItbis(valorItbis);
//            detallePedido.setSubTotal(subTotal);
//            detallePedido.setTotal(total);

            ArticuloUnidad artUnidad = ManejoArticuloUnidad.getInstancia()
                    .getArticuloUnidadSslida(detallePedido.getArticulo().getCodigo(), detallePedido.getUnidad().getCodigo());

            detallePedido.setUnidad(artUnidad.getUnidad());
            detallePedido.setPedido(getPedido());
            detallePedido.setNumeroArticulo(numeroArticulo);

            System.out.println("Codigo objeto " + detallePedido.getNumeroArticulo());

//            Integer unidadInventario = ManejoArticuloUnidad.getInstancia()
//                    .getArticuloUnidadSslida(detalleFactura.getArticulo().getCodigo()).getUnidad().getCodigo();
//
//            detallePedido.setUnidadInventario(unidadInventario);
            detallePedido.setCantidadInventario(catidadArticulo * artUnidad.getFatorVenta());
            detallePedido.setCostoUnitario(artUnidad.getCostoUnitario());

            detallePedido.setFactorUnidadSalida(artUnidad.getFatorVenta());
            detallePedido.setListaDePrecio(listaPrecio);

            if (unidadDespacho == 2) {

                subTotal = valorEnPeso;

            } else {

                subTotal = ClaseUtil.roundDouble(detallePedido.getCantidad() * detallePedido.getPrecio(), 2);
            }

//            subTotal=ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(),2);
            if (getArticulo().getExentoItbis()) {

                valorItbis = 0.0;
                detallePedido.setTasaItbis(0.00);

            } else {

                double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                valorItbis = ClaseUtil.roundDouble((subTotal - detallePedido.getDescuento()) * (itbis / 100), 2);
                detallePedido.setPrecioItbis(precioVentaItbis);
                detallePedido.setTasaItbis(itbis);
            }

            total = ClaseUtil.roundDouble((subTotal - detallePedido.getDescuento()) + valorItbis, 2);

            detallePedido.setItbis(valorItbis);
            detallePedido.setSubTotal(subTotal);
            detallePedido.setTotal(total);
            detallePedido.setPedido(pedido);

            txtCodigoArticulo.clear();
            iniciazarFiltro();

            if (existe(detallePedido)) {

                agruparDetallePorArticulo(detallePedido);

            } else if (!existe(detallePedido)) {

                listadetallePedido.add(detallePedido);

            }

            visualizarDetallePedido(detallePedido);
            txtTotal.setText(getTotal().toString());

            setArticulo(null);

            txtCodigoArticulo.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean existe(DetalleFactura det) {

        for (DetallePedido detalle : tbDetallePedido.getItems()) {

            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())
                    && Objects.equals(detalle.getUnidad().getCodigo(), det.getUnidad().getCodigo())) {

                return true;

            }
        }

        return false;
    }

    private void agregarArticulo() {

        try {

            detallePedido = new DetallePedido();
            Double total = 0.00, subTotal = 0.00, valorItbis = 0.00, precioVenta = 0.00,
                    precioVentaItbis = 0.00, valorEnPeso = 0.00;;

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

            detallePedido.setArticulo(getArticulo());
            detallePedido.setCantidad(catidadArticulo);
            detallePedido.setDescuento(Double.parseDouble("0"));
            detallePedido.setExistencia(getArticulo().getExistencia());
            detallePedido.setNombreArticulo(getArticulo().getNombre());
            detallePedido.setNuevaExistencia(0.0);
            detallePedido.setPrecio(precioVenta);

            detallePedido.setPrecioItbis(precioVentaItbis);

            if (unidadDespacho == 2) {

                subTotal = valorEnPeso;

            } else {

                subTotal = ClaseUtil.roundDouble(detallePedido.getCantidad() * detallePedido.getPrecio(), 2);
            }

            detallePedido.setPrecioItbis(precioVenta);
            total = ClaseUtil.roundDouble((subTotal - detallePedido.getDescuento()) + valorItbis, 2);
            detallePedido.setItbis(valorItbis);
            detallePedido.setSubTotal(subTotal);
            detallePedido.setTotal(total);
            detallePedido.setUnidad(getArticulo().getUnidadSalida());
            detallePedido.setPedido(getPedido());
            detallePedido.setNumeroArticulo(numeroArticulo);
            System.out.println("Codigo objeto " + detallePedido.getNumeroArticulo());

//            if (!(existe(detalleFactura))) {
//                
            listadetallePedido.add(detallePedido);
            numeroArticulo++;
//            }

            System.out.println("Total : " + getTotal());
            txtTotal.setText(getTotal().toString());

            vistaDetallePedido(listadetallePedido);
            txtCodigoArticulo.requestFocus();

            crearArticulo();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void agregarArticuloDTO() {

        try {

            DetallePedidoDTO detallePedidoDto = new DetallePedidoDTO();
            Double total = 0.00, subTotal = 0.00, valorItbis = 0.00, precioVenta = 0.00,
                    precioVentaItbis = 0.00, valorEnPeso = 0.00;;

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

            detallePedidoDto.setArticulo(getArticulo());
            detallePedidoDto.setCantidad(catidadArticulo);
            detallePedidoDto.setDescuento(Double.parseDouble("0"));
            detallePedidoDto.setExistencia(getArticulo().getExistencia());
            detallePedidoDto.setNombreArticulo(getArticulo().getNombre());
            detallePedidoDto.setNuevaExistencia(0.0);
            detallePedidoDto.setPrecio(precioVenta);

            detallePedidoDto.setPrecioItbis(precioVentaItbis);

            if (unidadDespacho == 2) {

                subTotal = valorEnPeso;

            } else {

                subTotal = ClaseUtil.roundDouble(detallePedidoDto.getCantidad() * detallePedidoDto.getPrecio(), 2);
            }

            detallePedidoDto.setPrecioItbis(precioVenta);
            total = ClaseUtil.roundDouble((subTotal - detallePedidoDto.getDescuento()) + valorItbis, 2);
            detallePedidoDto.setItbis(valorItbis);
            detallePedidoDto.setSubTotal(subTotal);
            detallePedidoDto.setTotal(total);
            detallePedidoDto.setUnidad(getArticulo().getUnidadSalida());
            detallePedidoDto.setPedido(getPedido());
            detallePedidoDto.setNumeroArticulo(numeroArticulo);
            System.out.println("Codigo objeto " + detallePedidoDto.getNumeroArticulo());

//            if (!(existe(detalleFactura))) {
//                
            listadetallePedidoDto.add(detallePedidoDto);
            numeroArticulo++;
//            }

            System.out.println("Total : " + getTotal());
            txtTotal.setText(getTotal().toString());

            vistaDetallePedido(listadetallePedido);
            txtCodigoArticulo.requestFocus();

            crearArticulo();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Double getSubTotal() {

        Double subTotal = 0.00;

        for (DetallePedido det : listadetallePedido) {

            subTotal += det.getCantidad() * det.getPrecio();
//            subTotal += det.getSubTotal();
        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    public Double getTotal() {

        Double total = 0.00;

        for (DetallePedido det : listadetallePedido) {

            total += (det.getCantidad() * det.getPrecio()) + det.getItbis();
//            total += det.getTotal();
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getDescuento() {

        Double descuento = 0.00;

        for (DetallePedido det : listadetallePedido) {

            descuento += det.getDescuento();
        }

        return FormatNum.FormatearDouble(descuento, 2);
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

            if (getCliente().getEstadoCliente().getCodigo() == 3) {
                ClaseUtil.mensaje("ESTE CLIENTE ESTA BLOQUIADO  \n COMUNICARSE CON LA ADMINISTRACION");
                nuevo();
            }

        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {
        nuevo();
    }

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

            formaDePagoController.getTxtTotalFactura().setText(txtTotal.getText());
            formaDePagoController.getTxtEfectivo().setText(txtTotal.getText());

            ClaseUtil.getStageModal(root);

            if (!(formaDePagoController.getFormaPago() == null)) {

                listFormaPagoCollection = formaDePagoController.getListaFormaPago();
                setDevuelta(formaDePagoController.getTotalPago());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnGuardarActionevent(ActionEvent event) {

        try {

            if (listadetallePedido.size() <= 0) {

                ClaseUtil.mensaje("No tiene Articulos agregado para este Pedido");
                return;
            }
            if (txtNumeroCalle.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digital el numero de la calle del cliente");
                txtNumeroCalle.requestFocus();
                return;
            }

            if (txtNumeroCasa.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digital el numero de la casa del cliente");
                txtNumeroCasa.requestFocus();
                return;
            }

            if (txtDireccion.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digital una dirección");
                txtDireccion.requestFocus();
                return;
            }

            //Si la venta es a credito, tipoVenta == 2 venta a credito
            if (tipoVenta == 2 && txtNombreCliente.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que seleccionar un cliente");
                return;
            }

            guardar();

            if (getEditar() == true) {

                Stage stage = (Stage) btnGuardar.getScene().getWindow();
                stage.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void btnEliminarActionEvent(ActionEvent event) {

        try {
//
//            if (tbDetalleFactura.getSelectionModel().getSelectedIndex() != -1) {
//
//                listadetallePedido.remove(tbDetalleFactura.getSelectionModel().getSelectedIndex());
//                tbDetalleFactura.refresh();
//                txtTotal.setText(getTotal().toString());
//
//            } else {
//                ClaseUtil.mensaje("Tiene que Selccionar un Registro");
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean existe(DetallePedido det) {

        for (DetallePedido detalle : listadetallePedido) {

            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())) {

                return true;

            }
        }
        return false;
    }

    private void agruparDetallePorArticulo(DetallePedido det) {

        for (DetallePedido detalle : tbDetallePedido.getItems()) {

            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())
                    && Objects.equals(detalle.getUnidad().getCodigo(), det.getUnidad().getCodigo())) {

                System.out.println("detalle.getCantidad() + det.getCantidad()" + detalle.getCantidad() + " " + +det.getCantidad());
                detalle.setCantidad(detalle.getCantidad() + det.getCantidad());
                detalle.setSubTotal(detalle.getSubTotal() + det.getSubTotal());
                detalle.setTotal(detalle.getTotal() + det.getTotal());
                detalle.setDescuento(detalle.getDescuento() + det.getDescuento());
                detalle.setItbis(detalle.getItbis() + det.getItbis());

                tbDetallePedido.refresh();

            }
        }

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

        agregarArticuloDTO();

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

        llenarClientedeContado();
//        lbTipoVenta.setText("Tipo de Venta Seleccionado : " + " De Contado ");
    }

    @FXML
    private void btnCreditoActionEvent(ActionEvent event) {
        tipoVenta = 2;//Venta a Credito
//        txtCodigoCliente.clear();

//        lbTipoVenta.setText("Tipo de Venta Seleccionado : " + "Credito ");
    }

    @FXML
    private void btnBuscarArticuloActionEvent(ActionEvent event) {

        if (!txtCodigoArticulo.getText().isEmpty()) {

            List<Articulo> lista = ManejoArticulo.getInstancia().getListaArticulo(txtCodigoArticulo.getText());
//            List<Articulo> lista = ManejoArticulo.getInstancia().getArticuloPorCodigo(Integer.parseInt(txtCodigoArticulo.getText()));

            crearArticuloPorBusqueda(lista);
            txtCodigoArticulo.clear();
            txtCodigoArticulo.requestFocus();

        } else {

            gpArticulo.getChildren().clear();
        }
    }

    private void guardar() {

        try {

            TurnoPedido turnoPedido = null;
            getPedido().setAnulado(false);
            getPedido().setCliente(getCliente());

            if (txtNombreCliente.getText().isEmpty()) {

                getPedido().setNombreCliente(getCliente().getNombre());

            } else {

                getPedido().setNombreCliente(txtNombreCliente.getText());
            }

            getPedido().setFecha(new Date());

            EstadoPedido estadoPedido;

            if (getEditar() == true) {

                estadoPedido = ManejoEstadoPedido.getInstancia().getEstadoPedido(getPedido().getEstado().getCodigo());

            } else {

                estadoPedido = ManejoEstadoPedido.getInstancia().getEstadoPedido(1);

                System.out.println("getPedido().getFecha() " + " ");
                turnoPedido = ManejoTurnoPedido.getInstancia().getNumeroTurno(getPedido().getFecha());

                System.out.println("turnoPedido " + turnoPedido);
                System.out.println("Numero  turno " + turnoPedido.getNumero());
                getPedido().setNumeroDeTurno(turnoPedido.getNumero());

            }

            getPedido().setTotal(getTotal());
            getPedido().setSubTotal(getSubTotal());

            getPedido().setFechaCreacion(new Date());
            getPedido().setHoraCreacion(new Date());
            getPedido().setCalle(txtNumeroCalle.getText());
            getPedido().setCasa(txtNumeroCasa.getText());
            getPedido().setUsuario(VariablesGlobales.USUARIO);
            getPedido().setEstado(estadoPedido);
            getPedido().setDireccion(txtDireccion.getText());
            getPedido().setUnidadDeNegocio(utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio());

            getPedido().setDevueltaDe(deVueltaDe);
            getPedido().setValorDevuelta(valorDevuelta);

            getPedido().setDetallePedidoCollection(listadetallePedido);

            if (tipoVenta == 1) { //Vanta de Contado

                getPedido().setTipoVenta(1);

            } else if (tipoVenta == 2) { //Venta a Credito

                getPedido().setTipoVenta(2);
            }

            if (tipoVenta == 2) {

                //Comprobar limite de credito
                Double montoPendiente, montodisponible;
                montoPendiente = ManejoFactura.getInstancia().getMontoPendiente(getCliente());
                montodisponible = getCliente().getMontoCredito() - montoPendiente;

                if (montodisponible <= getPedido().getTotal()) {

                    ClaseUtil.mensaje("EL CLIENTE NO TIENE CREDITO DISPONIBLE");
                    return;
                }
            }

            Pedido pedidoDb = null;

            if (getEditar() == true) {
                pedidoDb = ManejoPedido.getInstancia().actualizar(getPedido());
            } else {
                pedidoDb = ManejoPedido.getInstancia().salvar(getPedido());

            }

            if (pedidoDb == null) {

                ClaseUtil.mensaje("Hubo Error creando el pedido");
                return;

            } else {

                if (getEditar() == false) {

                    turnoPedido.setNumero(turnoPedido.getNumero() + 1);
                    ManejoTurnoPedido.getInstancia().actualizar(turnoPedido);
                }

                //CREANDO ENTRADA DE DIARIO 
//                ConfiguracionCuentaContable config = null;
                String concepto = "Ventas de mercancia al contado";

                System.out.println("Factura Creada correctamente codigo " + pedidoDb.getCodigo());

                getCliente().setMontoDisponible(ManejoFactura.getInstancia().getMontoDisponible(cliente));
                ManejoCliente.getInstancia().actualizar(getCliente());

                codigoPedido = pedidoDb.getCodigo();
                System.out.println("Codigo pedido " + codigoPedido + " Pedido " + pedidoDb);

                //Aplicar forma de pago automatica
                if (listFormaPagoCollection == null) {

                    TipoFormaPago tipoFormaPago = null;

                    if (tipoVenta == 1) {

                        tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(1);

                    } else if (tipoVenta == 2) {

                        tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);

                    }

                    formaPago = new FormaPago();

                    formaPago.setTipoFormaPago(tipoFormaPago);
                    formaPago.setMonto(pedidoDb.getTotal());

                    formaPago.setDocumento(pedidoDb.getCodigo());
                    formaPago.setTipoDocumento(1);
                    formaPago.setFecha(new Date());
                    ManejoFormaPago.getInstancia().salvar(formaPago);

                } else {

                    if (tipoVenta == 1) { //venta de contado

                        for (FormaPago fp : listFormaPagoCollection) {

                            fp.setDocumento(pedidoDb.getCodigo());
                            fp.setTipoDocumento(1);
                            fp.setFecha(new Date());
                            ManejoFormaPago.getInstancia().salvar(fp);

                        }

                    } else if (tipoVenta == 2) { //venta credito

                        formaPago = new FormaPago();
                        TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
                        formaPago.setTipoFormaPago(tipoFormaPago);
                        formaPago.setMonto(pedidoDb.getTotal());

                        formaPago.setDocumento(pedidoDb.getCodigo());
                        formaPago.setTipoDocumento(1);
                        formaPago.setFecha(new Date());
                        ManejoFormaPago.getInstancia().salvar(formaPago);

                    }

                }
                // fin Aplicar forma de pago automatica

                System.out.println("Pedido  num " + pedidoDb.getCodigo());

                nuevo();

            }

            //nota : si no se guarda el detalle es porque generaste las entidades nuevamente y el atributo cascae se le fue
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

//    private void btnArticuloActionEvent(ActionEvent event) throws IOException {
//
//        if (tbDetalleFactura.getSelectionModel().getSelectedIndex() == -1) {
//
//            ClaseUtil.mensaje("Tiene que seleccionar un Articulo");
//
//        } else {
//
//            setArticulo(tbDetalleFactura.getSelectionModel().getSelectedItem().getArticulo());
//
//            FXMLLoader loader = new FXMLLoader();
//
//            loader.setLocation(getClass().getResource("/vista/inventario/articulo/RegistroArticulo.fxml"));
//            loader.load();
//
//            Parent root = loader.getRoot();
//
//            RegistroArticuloController registroArticuloController = loader.getController();
//
//            registroArticuloController.setEditar(true);
//            registroArticuloController.setArticulo(getArticulo());
//
//            registroArticuloController.llenarCampo();
//
//            ClaseUtil.getStageModal(root);
////            listadetalleFactura.remove(tbDetalleFactura.getSelectionModel().getSelectedIndex());
////            agregarArticulo();
//
//        }
//    }
    private void btnBuscarDireccionActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/direccion/FXMLBuscarDireccion.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        FXMLBuscarDireccionrController direccionController = loader.getController();

        if (!(direccionController.getDireccion() == null)) {

            setDireccion(direccionController.getDireccion());
            txtDireccion.setText(getDireccion().getDireccion());

        }

    }

    public void llenarCampo() {

        System.out.println("Pedido " + getPedido().getCalle());
        txtNombreCliente.setText(getPedido().getNombreCliente());
        txtDireccion.setText(getPedido().getDireccion());
        deVueltaDe = getPedido().getDevueltaDe();
        valorDevuelta = getPedido().getValorDevuelta();
        txtNumeroCalle.setText(getPedido().getCalle());
        txtNumeroCasa.setText(getPedido().getCasa());
        listadetallePedido.clear();
        listadetallePedido.addAll(ManejoPedido.getInstancia().getDetallePedido(pedido.getCodigo()));
        vistaDetallePedido(listadetallePedido);
    }

    private void organizarIndice() {

        numeroArticulo = 0;
        for (DetallePedido det : listadetallePedido) {

            det.setNumeroArticulo(numeroArticulo);
            System.out.println("DetallePedido " + det.getNumeroArticulo());
            numeroArticulo++;

        }
    }

    @FXML
    private void txtNumeroCalleActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/teclado/TecladoDigitalNumerico.fxml"));
        loader.load();

        Parent root = loader.getRoot();
        TecladoDigitalNumericoController tecladoDigitalController = loader.getController();

        ClaseUtil.getStageModal(root);
        txtNumeroCalle.setText(tecladoDigitalController.getTxtCAtidad().getText());
    }

    @FXML
    private void txtNumeroCasaActionevent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/teclado/TecladoDigitalNumerico.fxml"));
        loader.load();

        Parent root = loader.getRoot();
        TecladoDigitalNumericoController tecladoDigitalController = loader.getController();

        ClaseUtil.getStageModal(root);

        txtNumeroCasa.setText(tecladoDigitalController.getTxtCAtidad().getText());
    }

    private void txtDevueltaDeActionevent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/teclado/TecladoDigitalNumerico.fxml"));
        loader.load();

        Parent root = loader.getRoot();
        TecladoDigitalNumericoController tecladoDigitalController = loader.getController();

        ClaseUtil.getStageModal(root);

//        txtDevueltaDe.setText(tecladoDigitalController.getTxtCAtidad().getText());
    }

    @FXML
    private void btnRutaActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/teclado/TecladoDigitalDireccion.fxml"));
        loader.load();

        Parent root = loader.getRoot();
        TecladoDigitalDireccionController tecladoDigitalController = loader.getController();

        if (getEditar() == true) {

            tecladoDigitalController.setCalle(txtNumeroCalle.getText());
            tecladoDigitalController.setCasa(txtNumeroCasa.getText());
            tecladoDigitalController.setDireccion(txtDireccion.getText());
            tecladoDigitalController.setTotal(getTotal());
            tecladoDigitalController.getTxtLeSobran().setText(getPedido().getValorDevuelta().toString());

            tecladoDigitalController.llenarCampo();
        }

        tecladoDigitalController.setTotal(getTotal());
        ClaseUtil.getStageModal(root);

        txtNumeroCalle.setText(tecladoDigitalController.getTxtCalleNumero().getText());
        txtNumeroCasa.setText(tecladoDigitalController.getTxtCasaNumero().getText());
        txtDireccion.setText(tecladoDigitalController.getTxtDireccion().getText());
        String strdevuelta = tecladoDigitalController.getTxtPagadocon().getText().isEmpty() ? "0"
                : tecladoDigitalController.getTxtPagadocon().getText();

        deVueltaDe = Double.parseDouble(strdevuelta);
        valorDevuelta = tecladoDigitalController.getValorDevuelta();

        System.out.println("Le sobran " + valorDevuelta);

    }
}
