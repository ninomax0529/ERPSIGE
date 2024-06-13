/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.cotizacion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.venta.cliente.FXMLBusClienterController;
import controlador.venta.descuento.UsuarioDescuentoController;
import controlador.venta.vendedor.BuscarVendedorController;
import dto.articulo.ArticuloDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import manejo.ManejoConfiguracion;
import manejo.articulo.ManejoArticulo;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.articulo.ManejoListaDePrecio;
import manejo.cotizacionDeVenta.ManejoCotizacionDeVenta;
import manejo.factura.ManejoFactura;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import manejo.unidad.ManejoUnidad;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.Cliente;
import modelo.CotizacionDeVenta;
import modelo.DatosDeVehiculo;
import modelo.DescuentoPorUsuario;
import modelo.DetalleCotizacionDeVenta;
import modelo.DetalleCotizacionVehiculo;
import modelo.DetalleListaDePrecio;
import modelo.EjecutivoDeVenta;
import modelo.ExistenciaArticulo;
import modelo.SecuenciaDocumento;
import reporte.cotizacion.RpCotizacionDeVentaIghTrack;
import reporte.cotizacion.RpCotizacionDeVentaPinturaTriplea;
import util.GUIUtils;
import utiles.ClaseUtil;
import utiles.FormatNum;
import utiles.VariablesGlobales;
import vista.venta.facturacion.TecladoDigitalController;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class CotizacionDeVentaController implements Initializable {

    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtNumeroFactura;
    @FXML
    private JFXTextField txtNombreCliente;
    @FXML
    private JFXButton btnBuscarCliente;
    @FXML
    private TableView<DetalleCotizacionDeVenta> tbDetalleCotizaacion;
    @FXML
    private TableColumn<DetalleCotizacionDeVenta, String> tbcArticulo;
    @FXML
    private TableColumn<DetalleCotizacionDeVenta, Double> tbcCantidad;
    @FXML
    private TableColumn<DetalleCotizacionDeVenta, String> tbcUnidadSalida;
    @FXML
    private TableColumn<DetalleCotizacionDeVenta, Double> tbcPrecioUnitario;
    @FXML
    private TableColumn<DetalleCotizacionDeVenta, Double> tbcImporte;
    @FXML
    private TableColumn<DetalleCotizacionDeVenta, Double> tbcItbis;
    @FXML
    private TableColumn<DetalleCotizacionDeVenta, Double> tbcDescuento;
    @FXML
    private JFXTextField txtSubTotal;
    @FXML
    private JFXTextField txtDescuento;
    @FXML
    private JFXTextField txtItbis;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private VBox vbMenu;
    @FXML
    private JFXButton btnImprimir;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private VBox vbMenuArticulo;
    @FXML
    private JFXTextField txtCodigoArticulo;
    @FXML
    private Button btnBuscarArticulo;
    @FXML
    private JFXButton btnCategoria;
    @FXML
    private HBox vhMenuArticulo;
    @FXML
    private TableView<Articulo> tbArticulo;

    @FXML
    private VBox vbCategoriaArticulo;
    @FXML
    private Label lbCategoriaSeleccionada;
    @FXML
    private VBox VbSubCate;
    @FXML
    private HBox hbTipoVenta;
    @FXML
    private Label lbTipoVenta;
    Articulo articulo;
    Double catidadArticulo = 0.0;
    Double existenciaEnPeso = 0.00;
    String numStr;

    ArticuloDTO articuloDTO;
    Double precioVenta = 0.00;
    CotizacionDeVenta cotizacionDeVenta;
    DetalleCotizacionDeVenta detalleCotiz;
    Cliente cliente;
    @FXML
    private TableColumn<Articulo, Articulo> tbcImagenArticulo;
    @FXML
    private JFXButton btnDescuento;
    @FXML
    private JFXTextField txtPorcientoMinimo;
    @FXML
    private JFXTextField txtPorciento;
    @FXML
    private VBox vbDescuento;
    @FXML
    private JFXButton btnAplicarDesc;
    @FXML
    private JFXTextField txtPorcientoMaximo;
    DescuentoPorUsuario descuentoPorUsuario;
    @FXML
    private JFXButton btnVendedor;
    EjecutivoDeVenta ejecutivoDeVenta;
    @FXML
    private JFXTextField txtVendedor;
    @FXML
    private JFXButton btnAgregarVehiculo;
    @FXML
    private TableView<DetalleCotizacionVehiculo> tbVehiculo;
    @FXML
    private TableColumn<DetalleCotizacionVehiculo, String> tbcVehiculo;
    @FXML
    private VBox vbVehiculo;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    ObservableList<DetalleCotizacionDeVenta> listadetalleCotizacion = FXCollections.observableArrayList();
    ObservableList<Articulo> listaArticulos = FXCollections.observableArrayList();
    ObservableList<DetalleCotizacionVehiculo> listaCotVehiculo = FXCollections.observableArrayList();

    int unidadDespacho = 1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nuevo();
        vbCategoriaArticulo.setPrefWidth(0);
        vbCategoriaArticulo.setVisible(false);
        dpFecha.setValue(LocalDate.now());
        inicializarTabla();
        inicializarTablaVehiculo();
        iniciazarFiltro();
        vbDescuento.setVisible(false);
        if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {
            vbVehiculo.setVisible(true);
        } else {
            vbVehiculo.setVisible(false);
        }

        txtPorciento.setOnAction(ev -> {

            try {

                aplicarDescuento();

            } catch (Exception e) {
                e.printStackTrace();
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

    public void inicializarTablaVehiculo() {

        listaCotVehiculo.clear();

        tbVehiculo.setItems(listaCotVehiculo);

        tbcVehiculo.setCellValueFactory(new PropertyValueFactory<>("vehiculo"));

    }

    public void inicializarTabla() {

        listadetalleCotizacion.clear();
        listaArticulos.clear();

        tbDetalleCotizaacion.setItems(listadetalleCotizacion);

        tbcArticulo.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcUnidadSalida.setCellValueFactory(new PropertyValueFactory<>("unidadSalida"));
        tbcImporte.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        tbcPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tbcDescuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        tbcItbis.setCellValueFactory(new PropertyValueFactory<>("itbis"));
        listaArticulos.addAll(ManejoArticulo.getInstancia().getListaArticuloPorUnidadDeNegocio());

//        listaArticulos.addAll(ManejoArticulo.getInstancia().getListaArticulos(2));
        tbArticulo.setItems(listaArticulos);
        GUIUtils.autoFitTable(tbArticulo);

        tbDetalleCotizaacion.setEditable(true);

        tbcArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo() + "-" + cellData.getValue().getNombreArticulo());
                    }
                    return property;
                });

//        tbcCodigoArticulo.setCellValueFactory(
//                cellData -> {
//                    SimpleObjectProperty property = new SimpleObjectProperty();
//                    if (cellData.getValue() != null) {
//                        property.setValue(cellData.getValue().getArticulo());
//                    }
//                    return property;
//                });
//
//        tbcCodigoArticulo.setCellFactory((TableColumn<DetalleCotizacionDeVenta, Articulo> param) -> {
//
//            TableCell<DetalleCotizacionDeVenta, Articulo> cellsc = new TableCell<DetalleCotizacionDeVenta, Articulo>() {
//                @Override
//                public void updateItem(Articulo item, boolean empty) {
//                    super.updateItem(item, empty);
//                    File imageFile;
//                    Image img;
//                    ImageView imageview;
//                    Label label;
//                    if (item != null) {
//
//                        if (item.getRutaImg() == null) {
//
//                            imageFile = new File("foto/img_articulo.jpg");
//
//                        } else {
//
//                            imageFile = new File(item.getRutaImg());
//                        }
//
////                    File imageFile = new File(lista.get(num).getRutaImg());
//                        label = new Label(item.getCodigo().toString());
//                        imageview = new ImageView(new Image(imageFile.toURI().toString(), 130, 100, false, false));
//                        imageview.setFitWidth(50);
//                        imageview.setFitHeight(30);
//
//                        HBox hbox = new HBox();
////                        VBox vbox = new VBox();
////                        vbox.setAlignment(Pos.CENTER);
//
//                        hbox.getChildren().addAll(imageview);
//
//                        hbox.setAlignment(Pos.CENTER);
//
//                        hbox.setOnMouseClicked((event) -> {
//
//                            setArticulo(tbDetalleCotizaacion.getSelectionModel().getSelectedItem().getArticulo());
//
//                            FXMLLoader loader = new FXMLLoader();
//
//                            loader.setLocation(getClass().getResource("/vista/inventario/articulo/RegistroArticulo_1.fxml"));
//                            try {
//                                loader.load();
//                            } catch (IOException ex) {
//                                Logger.getLogger(FacturacionTochHorizontalController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//
//                            Parent root = loader.getRoot();
//
//                            RegistroArticuloController1 registroArticuloController = loader.getController();
//
//                            registroArticuloController.setEditar(true);
//                            registroArticuloController.setArticulo(getArticulo());
//
//                            registroArticuloController.llenarCampo();
//
//                            ClaseUtil.getStageModal(root);
//
//                        });
//
//                        setGraphic(hbox);
//                        setText(null);
//                    } else {
//                        setGraphic(null);
//                        setText(null);
//                    }
//                }
//            };
//            return cellsc;
//        });
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

                        HBox hbox = null;
                        try {
                            hbox = visualizarArticulo(item);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(CotizacionDeVentaController.class.getName()).log(Level.SEVERE, null, ex);
                        }

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
//
//                                    if (existencia < existenciaEnPeso) {
//
//                                        ClaseUtil.mensaje("La cantidad a Despachar " + catidadArticulo
//                                                + "  no puede ser mayor que la existencia ,"
//                                                + " Existencia igual a  " + existencia + "   " + nombreUnidad);
//
//                                        return;
//                                    }

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

                DetalleCotizacionDeVenta p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00, precioCompraUnitario = 0.00,
                        descuento = 0.00, impuesto = 0.00, precio = p.getPrecio();

                Double existencia = ManejoExistenciaArticulo
                        .getInstancia()
                        .getExistenciaArticulo(p.getArticulo().getCodigo(), 1, p.getUnidad().getCodigo(), p.getListaDePrecio());

//                if (data.getNewValue() > 0) {
//
//                    if (existencia < data.getNewValue()) {
//
//                        p.setCantidad(data.getOldValue());
//                        ClaseUtil.mensaje(" La cantidad a Despachar  " + data.getNewValue()
//                                + "  no puede ser mayor que la existencia ,"
//                                + " Existencia igual a  " + existencia);
//
//                        tbDetalleCotizaacion.refresh();
//                        tbDetalleCotizaacion.requestFocus();
//                        return;
//                    }
//
                p.setCantidad(data.getNewValue());
//
                editarDetalleArticulo(p);
                tbDetalleCotizaacion.refresh();
                tbDetalleCotizaacion.requestFocus();
//
//                } else {
//
//                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
//                    tbDetalleFactura.refresh();
//                    tbDetalleFactura.requestFocus();
//                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

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

        }

    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        try {

            if (tbDetalleCotizaacion.getSelectionModel().getSelectedIndex() != -1) {

                listadetalleCotizacion.remove(tbDetalleCotizaacion.getSelectionModel().getSelectedIndex());
                tbDetalleCotizaacion.refresh();

                txtSubTotal.setText(getSubTotal().toString());
                txtDescuento.setText(getDescuento().toString());
                txtItbis.setText(getItbis().toString());
                txtTotal.setText(getTotal().toString());

            } else {

                ClaseUtil.mensaje("Tiene que Selccionar un Registro");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnImprimirActionEvent(ActionEvent event) {

        try {

            if (getCliente() == null) {

                ClaseUtil.mensaje("Tiene que seleccionar un cliente ");
                return;
            }

            if (tbDetalleCotizaacion.getItems().size() <= 0) {

                ClaseUtil.mensaje("No tiene Articulos agregado para esta Cotizacion ");
                return;
            }

            if (txtVendedor.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que seleccionar un vendedor ");
                return;
            }

            //Guandanto cotizacion ****
            cotizacionDeVenta = guardar();
            if (cotizacionDeVenta == null) {

                ClaseUtil.mensaje(" Hubo Error creando la cotizacion");

            } else {

                System.out.println("cotizacionDeVenta " + cotizacionDeVenta);

                if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {

                    RpCotizacionDeVentaPinturaTriplea.getInstancia().verCotizacion(cotizacionDeVenta.getCodigo());

                } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {

                    RpCotizacionDeVentaIghTrack.getInstancia().verCotizacion(cotizacionDeVenta.getCodigo());
                }

                nuevo();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {

        if (ClaseUtil.confirmarMensaje(" Seguro que que quiere limpiar los campos ").get() == ButtonType.YES) {
            nuevo();

        }

    }

    @FXML
    private void btnBuscarArticuloActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnCategoriaActionevent(ActionEvent event) {
    }

    private HBox visualizarArticulo(Articulo articulo) throws FileNotFoundException {

        ImageView img = null;
        File imageFile;

        if (articulo.getRutaImg() == null) {

            imageFile = new File("/foto/articulo 1png.jpg");

        } else {

            imageFile = new File(articulo.getRutaImg());
        }

        String rutaimg = articulo.getRutaImg();

        System.out.println("ruta img : " + rutaimg);

//        FileInputStream input = new FileInputStream(imageFile);
//        Image image1 = new Image(input);
//        img = new ImageView(image1);
        img = new ImageView(new Image(imageFile.toURI().toString(),30, 40, false, false));
        img.setFitWidth(30);
        img.setFitHeight(40);

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
//        Label lbPrecio = new Label("Precio :  " + Double.toString(articulo.getPrecioVenta1()) + "  $ ");
        Label lbNombre = new Label("Nombre :  " + articulo.getNombre());

        lbCodio.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 13pt;");
//        lbPrecio.setStyle(" -fx-text-fill: #000000;"
//                + " -fx-font-size: 13pt;");
        lbNombre.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 11pt;");

        vb.getChildren().add(lbCodio);

        vb.getChildren().add(lbNombre);

//        vb.getChildren().add(lbPrecio);
//        hb.getChildren().add(img);
        hb.getChildren().add(vb);
        hb.setCursor(Cursor.HAND);
        return hb;

    }

    private void agregarArticuloConPrecioDeLista(int unidadSalida, int almacen, int listaPrecio) {

        try {

            detalleCotiz = new DetalleCotizacionDeVenta();
            Double total = 0.00, subTotal = 0.00, valorItbis = 0.00,
                    precioVentaItbis = 0.00, valorEnPeso = 0.00;
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
            detalleCotiz.setArticulo(getArticulo());

            ExistenciaArticulo exisArt = ManejoExistenciaArticulo.getInstancia()
                    .getExistenciaArticulo(detalleCotiz.getArticulo().getCodigo(),
                            almacen);

            detalleCotiz.setAlmacen(exisArt.getAlmacen());

            detalleCotiz.setCantidad(catidadArticulo);
            detalleCotiz.setDescuento(Double.parseDouble(txtDescuento.getText().isEmpty() ? "0" : txtDescuento.getText()));
            detalleCotiz.setNombreArticulo(getArticulo().getNombre());

            detalleCotiz.setPrecio(precioVenta);
            detalleCotiz.setPrecioItbis(precioVentaItbis);
            detalleCotiz.setUnidad(ManejoUnidad.getInstancia().getUnidad(unidadSalida));

            Integer unidadInventario = ManejoArticuloUnidad.getInstancia()
                    .getArticuloUnidadSslida(detalleCotiz.getArticulo().getCodigo()).getUnidad().getCodigo();

            ArticuloUnidad artUnidad = ManejoArticuloUnidad.getInstancia()
                    .getArticuloUnidadSslida(detalleCotiz.getArticulo().getCodigo(), detalleCotiz.getUnidad().getCodigo());

            detalleCotiz.setUnidadInventario(unidadInventario);
//            detalleFactura.setAlmacen(almacen);
            detalleCotiz.setCantidadInventario(catidadArticulo * artUnidad.getFatorVenta());

            detalleCotiz.setCostoUnitario(artUnidad.getCostoUnitario());

            detalleCotiz.setFactorUnidadSalida(artUnidad.getFatorVenta());
            detalleCotiz.setListaDePrecio(listaPrecio);

            if (unidadDespacho == 2) {

                subTotal = valorEnPeso;

            } else {

                subTotal = ClaseUtil.roundDouble(detalleCotiz.getCantidad() * detalleCotiz.getPrecio(), 2);
            }

//            subTotal=ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(),2);
            if (getArticulo().getExentoItbis()) {

                valorItbis = 0.0;
                detalleCotiz.setTasaItbis(0.00);

            } else {

                double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                valorItbis = ClaseUtil.roundDouble(subTotal * (itbis / 100), 4);
//                valorItbis = ClaseUtil.roundDouble((subTotal - detalleCotiz.getDescuento()) * (itbis / 100), 2);
                detalleCotiz.setPrecioItbis(precioVentaItbis);
                detalleCotiz.setTasaItbis(itbis);
            }

            total = ClaseUtil.roundDouble((subTotal + valorItbis) - detalleCotiz.getDescuento(), 2);
            detalleCotiz.setItbis(valorItbis);
            detalleCotiz.setSubTotal(subTotal);
            detalleCotiz.setTotal(total);
            detalleCotiz.setCotizacionDeVenta(cotizacionDeVenta);

            if (existe(detalleCotiz)) {

                agruparDetallePorArticulo(detalleCotiz);

            } else if (!existe(detalleCotiz)) {

                listadetalleCotizacion.add(detalleCotiz);
            }

            txtCodigoArticulo.clear();
            iniciazarFiltro();

            txtSubTotal.setText(getSubTotal().toString());
            txtDescuento.setText(getDescuento().toString());
            txtItbis.setText(getItbis().toString());
            txtTotal.setText(getTotal().toString());

            detalleCotiz = new DetalleCotizacionDeVenta();

            setArticulo(null);

            txtCodigoArticulo.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void nuevo() {

        cotizacionDeVenta = new CotizacionDeVenta();
        detalleCotiz = new DetalleCotizacionDeVenta();
        listadetalleCotizacion.clear();
        listaCotVehiculo.clear();

        hbTipoVenta.setStyle("   -fx-background-color: #5CB85C;\n"
                + "    -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C);\n"
                + "    -fx-border-color: -fx-secondary;\n"
                + "    -fx-border-radius: 15px;\n"
                + "    -fx-background-radius: 15px;\n"
                + "    -fx-font-size: 12pt;");
        lbTipoVenta.setStyle("-fx-text-fill: #ffffff");
        lbTipoVenta.setText("  Tipo de Venta Seleccionado : " + " De Contado ");

        txtNumeroFactura.setText(ManejoCotizacionDeVenta.getInstancia().getNumero().toString());

        vbDescuento.setVisible(false);
        btnCategoria.setVisible(false);

        limpiar();

    }

    private void limpiar() {

        txtNombreCliente.clear();
        txtSubTotal.clear();
        txtTotal.clear();
        txtDescuento.clear();
        txtDescuento.clear();
        txtItbis.clear();

        listadetalleCotizacion.clear();
        setCliente(null);
        setEjecutivoDeVenta(null);

    }

    private void editarDetalleArticulo(DetalleCotizacionDeVenta det) {

        try {

            detalleCotiz = det;
//            catidadArticulo = det.getCantidad();
//            setArticulo(detalleFactura.getArticulo());
            Double total = 0.00, subTotal = 0.00, valorItbis = 0.00, precioVenta = 0.00,
                    precioVentaItbis = 0.00, valorEnPeso = 0.00;

            if (unidadDespacho == 2) {

                subTotal = valorEnPeso;

            } else {

                subTotal = ClaseUtil.roundDouble(detalleCotiz.getCantidad() * detalleCotiz.getPrecio(), 2);
            }

//            subTotal=ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(),2);
            if (detalleCotiz.getArticulo().getExentoItbis()) {

                valorItbis = 0.0;
                detalleCotiz.setTasaItbis(0.00);

            } else {

                double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                valorItbis = ClaseUtil.roundDouble((subTotal - detalleCotiz.getDescuento()) * (itbis / 100), 2);
                detalleCotiz.setPrecioItbis(precioVentaItbis);
                detalleCotiz.setTasaItbis(itbis);
            }

            total = ClaseUtil.roundDouble((subTotal - detalleCotiz.getDescuento()) + valorItbis, 2);
            detalleCotiz.setItbis(valorItbis);
            detalleCotiz.setSubTotal(subTotal);
            detalleCotiz.setTotal(total);
//            detalleFactura.setUnidad(getArticulo().getUnidadSalida());
            detalleCotiz.setCotizacionDeVenta(cotizacionDeVenta);

            txtCodigoArticulo.clear();
            iniciazarFiltro();

            txtSubTotal.setText(getSubTotal().toString());
            txtDescuento.setText(getDescuento().toString());
            txtItbis.setText(getItbis().toString());
            txtTotal.setText(getTotal().toString());

            setArticulo(null);

            txtCodigoArticulo.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Double getSubTotal() {

        Double subTotal = 0.00;

        for (DetalleCotizacionDeVenta det : listadetalleCotizacion) {

//            subTotal += det.getCantidad() * det.getPrecio();
            subTotal += det.getSubTotal();
        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotal() {

        Double total = 0.00;

        for (DetalleCotizacionDeVenta det : listadetalleCotizacion) {

//            total += (det.getCantidad() * det.getPrecio()) + det.getItbis();
            total += det.getTotal();
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getDescuento() {

        Double descuento = 0.00;

        for (DetalleCotizacionDeVenta det : listadetalleCotizacion) {

            descuento += det.getDescuento();
        }

        return FormatNum.FormatearDouble(descuento, 2);
    }

    private Double getItbis() {

        Double itbis = 0.00;

        for (DetalleCotizacionDeVenta det : listadetalleCotizacion) {

            itbis += det.getItbis();
        }

        return FormatNum.FormatearDouble(itbis, 2);
    }

    private boolean existe(DetalleCotizacionDeVenta det) {

        for (DetalleCotizacionDeVenta detalle : tbDetalleCotizaacion.getItems()) {

            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())
                    && Objects.equals(detalle.getUnidad().getCodigo(), det.getUnidad().getCodigo())) {

                return true;

            }
        }

        return false;
    }

    private void agruparDetallePorArticulo(DetalleCotizacionDeVenta det) {

        for (DetalleCotizacionDeVenta detalle : tbDetalleCotizaacion.getItems()) {

            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())
                    && Objects.equals(detalle.getUnidad().getCodigo(), det.getUnidad().getCodigo())) {

                detalle.setCantidad(detalle.getCantidad() + det.getCantidad());
                detalle.setSubTotal(detalle.getSubTotal() + det.getSubTotal());
                detalle.setTotal(detalle.getTotal() + det.getTotal());
                detalle.setDescuento(detalle.getDescuento() + det.getDescuento());
                detalle.setItbis(detalle.getItbis() + det.getItbis());

                tbDetalleCotizaacion.refresh();

            }
        }

    }

    private CotizacionDeVenta guardar() {

        CotizacionDeVenta cotizacionDb = null;
        try {

            cotizacionDeVenta.setAnulada(false);
            cotizacionDeVenta.setCliente(getCliente());
            cotizacionDeVenta.setNombreCliente(getCliente().getNombre());

            cotizacionDeVenta.setTotal(getTotal());
            cotizacionDeVenta.setSubTotal(getSubTotal());
            cotizacionDeVenta.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            cotizacionDeVenta.setDescuento(getDescuento());
            cotizacionDeVenta.setItbis(getItbis());
            cotizacionDeVenta.setUsuario(VariablesGlobales.USUARIO);
            cotizacionDeVenta.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            if (!(getDescuentoPorUsuario() == null)) {
                cotizacionDeVenta.setAutorizadorDescuento(getDescuentoPorUsuario().getUsuario().getCodigo());
            } else {
                cotizacionDeVenta.setAutorizadorDescuento(null);
            }

            cotizacionDeVenta.setVendedor(getEjecutivoDeVenta());

            cotizacionDeVenta.setFechaCreacion(new Date());

            cotizacionDeVenta.setDetalleCotizacionDeVentaCollection(listadetalleCotizacion);
            if (listaCotVehiculo.size() > 0) {
                cotizacionDeVenta.setDetalleCotizacionVehiculoCollection(listaCotVehiculo);
            }

            // asignar ncf a la factura
            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(cotizacionDeVenta.getUnidadDeNegocio().getCodigo(), 14);

            if (!(secDoc == null)) {

                boolean existe = ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero());

                if (existe) {

                    System.out.println("existe " + secDoc.getNumero());

                    while (ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero())) {

                        secDoc.setNumero(secDoc.getNumero() + 1);
                        ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                    }

                    secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(cotizacionDeVenta.getUnidadDeNegocio().getCodigo(), 14);

                    cotizacionDeVenta.setNumero(secDoc.getNumero());
                    cotizacionDeVenta.setSecuenciaDocumento(secDoc);

                } else {

                    System.out.println("No existe " + secDoc.getNumero());
                    cotizacionDeVenta.setNumero(secDoc.getNumero());
                    cotizacionDeVenta.setSecuenciaDocumento(secDoc);
                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

            } else {

                ClaseUtil.mensaje("La secuencia para la cotizacion de la unidad de negocio "
                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return cotizacionDeVenta;
            }

            cotizacionDb = ManejoCotizacionDeVenta.getInstancia().salvar(cotizacionDeVenta);

            //nota : si no se guarda el detalle es porque generaste las entidades nuevamente y el atributo cascae se le fue
        } catch (Exception e) {

            e.printStackTrace();
        }

        return cotizacionDb;
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

        Double porcientoDescuento = 0.00, descuento = 0.00, valorDescuento = 0.00,
                valorItbis = 0.00,
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
        for (DetalleCotizacionDeVenta detalle : listadetalleCotizacion) {

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

//
//            descuento = porcientoDescuento * (detalle.getSubTotal() + detalle.getItbis());
//            detalle.setDescuento(ClaseUtil.roundDouble(descuento, 2));
//            detalle.setPorcientoDescuento(valorDescuento);
//            detalle.setTotal((detalle.getSubTotal() + detalle.getItbis()) - detalle.getDescuento());
        }

        txtSubTotal.setText(getSubTotal().toString());
        txtDescuento.setText(getDescuento().toString());
        txtItbis.setText(getItbis().toString());
        txtTotal.setText(getTotal().toString());

        tbDetalleCotizaacion.refresh();

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
    private void btnAgregarVehiculoActionEvent(ActionEvent event) throws IOException {

        if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {
            if (!(getCliente() == null)) {

                listaCotVehiculo.clear();
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/vista/venta/cotizacion/BuscarVehiculoCotizacion.fxml"));
                loader.load();

                Parent root = loader.getRoot();

                BuscarVehiculoCotizacionController controller = loader.getController();
                controller.setCodigoClienete(getCliente().getCodigo());
                controller.iniData();

                ClaseUtil.getStageModal(root);

                DetalleCotizacionVehiculo det = null;

                if (controller.getListaVehiculoSeleccionado().size() > 0) {

                    for (DatosDeVehiculo dv : controller.getListaVehiculoSeleccionado()) {

                        det = new DetalleCotizacionVehiculo();

                        det.setCotizacion(cotizacionDeVenta);
                        det.setMarca(dv.getMarca());
                        det.setVehiculo(dv.getVehiculo());
                        det.setPlaca(dv.getPlaca());
                        det.setColor(dv.getColor());
                        listaCotVehiculo.add(det);

                    }

                    for (DetalleCotizacionVehiculo dc : listaCotVehiculo) {

                        System.out.println("Detalle : " + dc.getVehiculo());
                    }

                } else {
                    System.out.println("tamaño : " + controller.getListaVehiculoSeleccionado().size());
                }
            }
        } else {
            vbVehiculo.setVisible(false);
        }
    }

}
