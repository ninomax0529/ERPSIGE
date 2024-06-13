/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.puntoVenta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.venta.cliente.FXMLBusClienterController;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.ManejoConfiguracion;
import manejo.articulo.ManejoArticulo;
import manejo.articulo.ManejoCategoria;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.articulo.ManejoSubCategoria;
import manejo.caja.ManejoCajaChica;
import manejo.caja.ManejoTipoMovimieto;
import manejo.cliente.ManejoCliente;
import manejo.cliente.ManejoPlazo;
import manejo.documento.ManejoTipodocumento;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoFormaPago;
import manejo.factura.ManejoRelacionNcf;
import manejo.factura.ManejoTipoFormaPago;
import modelo.Articulo;
import modelo.CajaChica;
import modelo.Categoria;
import modelo.Cliente;
import modelo.CondicionPago;
import modelo.DetalleCajaChica;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.FormaPago;
import modelo.RelacionNcf;
import modelo.SubCategoria;
import modelo.TipoFormaPago;
import modelo.TipoNcf;
import reporte.factura.RptFactura;
import util.GUIUtils;
import utiles.ClaseUtil;
import utiles.FormatNum;
import vista.venta.facturacion.FormaPagoController;
import vista.venta.facturacion.TecladoDigitalController;


/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FacturacionTochController implements Initializable {

    @FXML
    private HBox hbSubCategoria;
    @FXML
    private VBox vbCategoria;
    @FXML
    private ScrollPane scpane;
    private GridPane gpArticulo = new GridPane();
    ObservableList<Button> listaArticulo = FXCollections.observableArrayList();
    @FXML
    private TableView<DetalleFactura> tbDetalleFactura;
    @FXML
    private TableColumn<DetalleFactura, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleFactura, String> tbcArticulo;
    @FXML
    private TableColumn<DetalleFactura, Integer> tbcCantidad;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcPrecioUnitario;
    @FXML
    private JFXTextField txtSubTotal;
    @FXML
    private JFXTextField txtDescuento;
    @FXML
    private JFXTextField txtItbis;
    @FXML
    private JFXTextField txtTotal;
    FacturacionTochController facturacionTochController;
    @FXML
    private JFXTextField txtCodigoArticulo;
    @FXML
    private Button btnBuscarArticulo;

    public FacturacionTochController getFacturacionTochController() {
        return facturacionTochController;
    }

    public void setFacturacionTochController(FacturacionTochController facturacionTochController) {
        this.facturacionTochController = facturacionTochController;
    }

    ObservableList<DetalleFactura> listadetalleFactura = FXCollections.observableArrayList();
    ObservableList<TipoNcf> listaTipoNcf = FXCollections.observableArrayList();
    ObservableList<String> listaTipoVenta = FXCollections.observableArrayList();

    Double totalPago;

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
    int CodigoFactura;

    Cliente cliente;
    Factura factura;
    DetalleFactura detalleFactura;
    FormaPago formaPago;
    Articulo articulo;
    @FXML
    private JFXTextField txtNumeroFactura;
    @FXML
    private JFXTextField txtNcf;
    @FXML
    private JFXTextField txtCodigoCliente;
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
    @FXML
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
    String numStr;
    @FXML
    private JFXButton btnDeContado;
    @FXML
    private JFXButton btnCredito;
    @FXML
    private Label lbTipoVenta;
    @FXML
    private BorderPane bpPrincipal;

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        gpArticulo.setHgap(20);
        gpArticulo.setVgap(20);
        gpArticulo.setAlignment(Pos.CENTER);
        gpArticulo.setScaleShape(true);
        crearCategoria();
        inicializarTabla();
        inicializarDatos();
        nuevo();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtCodigoArticulo.requestFocus();
            }
        });

    }

    public void inicializarTabla() {

        listadetalleFactura.clear();

        tbDetalleFactura.setItems(listadetalleFactura);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcArticulo.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        tbcPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tbcArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getNombreArticulo());
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

    }

    private void crearArticulo(int categoria) {

        ImageView img = null;
        Button btn;

        List<Articulo> lista = ManejoArticulo.getInstancia().getArticuloPorCategoria(categoria);

        gpArticulo.getChildren().clear();
        int num = 0;
        for (int x = 0; x < lista.size(); x++) {

            for (int y = 0; y < 3; y++) {

                if (num < lista.size()) {

                    File imageFile = new File(lista.get(num).getRutaImg());

                    img = new ImageView(new Image(imageFile.toURI().toString(), 150, 130, false, false));
                    img.setFitWidth(150);
                    img.setFitHeight(130);

                    btn = new Button(lista.get(num).getNombre(), img);
//                    btn.setStyle("-fx-base: coral;");
                    btn.setContentDisplay(ContentDisplay.TOP);

                    btn.setId(Integer.toString(lista.get(num).getCodigo()));
                    btn.setAccessibleHelp(lista.get(num).getNombre());
                    btn.setCursor(Cursor.HAND);

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

        scpane.setContent(gpArticulo);

        visualizarArticulo();
    }

    private void crearArticuloPorBusqueda(List<Articulo> lista) {

        ImageView img = null;
        Button btn;

        gpArticulo.getChildren().clear();
        int num = 0;
        for (int x = 0; x < lista.size(); x++) {

            for (int y = 0; y < 3; y++) {

                if (num < lista.size()) {

                    File imageFile = new File(lista.get(num).getRutaImg());

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

        scpane.setContent(gpArticulo);

        visualizarArticulo();
    }

    private void crearArticuloEjemplo(int categoria) {

        ImageView img = new ImageView();
        Label lbNombre = new Label();
        VBox vbimg = new VBox(10);

        List<Articulo> lista = ManejoArticulo.getInstancia().getArticuloPorCategoria(categoria);
        gpArticulo.getChildren().clear();
        int num = 0;
        for (int x = 0; x < lista.size(); x++) {

            for (int y = 0; y < 3; y++) {

                if (num < lista.size()) {

                    img = new ImageView();
                    lbNombre = new Label();
                    vbimg = new VBox(10);
                    vbimg.setAlignment(Pos.CENTER);

                    vbimg.setId(Integer.toString(lista.get(num).getCodigo()));

                    if (!(lista.get(num).getRutaImg() == null)) {

                        img.setAccessibleHelp(lista.get(num).getRutaImg());
                        img.setFitWidth(150);
                        img.setFitHeight(70);

                        File imageFile = new File(img.getAccessibleHelp());
                        Image image = new Image(imageFile.toURI().toString(), 150, 100, false, false);

                        img.setImage(image);
                    }

                    lbNombre.setText(lista.get(num).getNombre());

                    vbimg.getChildren().add(img);
                    vbimg.getChildren().add(lbNombre);

                    GridPane.setConstraints(vbimg, y, x);

                    gpArticulo.add(vbimg, y, x);

                }

                num++;

//                }
            }
            x++;

            System.out.println("|");
        }

        scpane.setContent(gpArticulo);

        visualizarArticuloEjemplo();
    }

    private void crearArticuloPorSubCategoria(int subCategoria) {

        ImageView img = null;
        Button btn;

        List<Articulo> lista = ManejoArticulo
                .getInstancia()
                .getArticuloPorSubCategoria(subCategoria);
        gpArticulo.getChildren().clear();

        int num = 0;
        for (int x = 0; x < lista.size(); x++) {

            for (int y = 0; y < 3; y++) {

                if (num < lista.size()) {

                    File imageFile = new File(lista.get(num).getRutaImg());

                    img = new ImageView(new Image(imageFile.toURI().toString(), 80, 60, false, false));
                    img.setFitWidth(80);
                    img.setFitHeight(60);

                    btn = new Button(lista.get(num).getNombre(), img);
//                    btn.setStyle("-fx-base: coral;");
                    btn.setContentDisplay(ContentDisplay.TOP);

                    btn.setId(Integer.toString(lista.get(num).getCodigo()));
                    btn.setAccessibleHelp(lista.get(num).getNombre());

                    btn.setPrefSize(120, 80);
                    btn.setMinWidth(120);
                    btn.setMaxWidth(120);

                    GridPane.setConstraints(btn, y, x);

                    gpArticulo.add(btn, y, x);

                }

                num++;

            }

        }

        scpane.setContent(gpArticulo);

        visualizarArticulo();
    }

    private void crearCategoria() {

        Button btn = new Button("BOTON");

        List<Categoria> lista = ManejoCategoria.getInstancia().getCategoria();

        for (int i = 0; i < lista.size(); i++) {

            btn = new Button(lista.get(i).getNombre());
            btn.setId(Integer.toString(lista.get(i).getCodigo()));
            btn.setCursor(Cursor.HAND);

            btn.setPrefSize(150, 40);
            btn.setMinWidth(150);
            btn.setMaxWidth(150);
            vbCategoria.getChildren().add(btn);

        }

        visualizarCategoria();

    }

    private void crearSubCategoria(int categoria) {

        Button btn = new Button("BOTON");

        List<SubCategoria> lista = ManejoSubCategoria.getInstancia()
                .getSubCategorias(categoria);
        hbSubCategoria.getChildren().clear();

        for (int i = 0; i < lista.size(); i++) {

            btn = new Button("" + lista.get(i).getNombre());
            btn.setId(Integer.toString(lista.get(i).getCodigo()));
            btn.setCursor(Cursor.HAND);

            btn.setPrefSize(150, 40);
            btn.setMinWidth(100);
            btn.setMaxWidth(200);
            hbSubCategoria.getChildren().add(btn);

        }

        visualizarSubCategoria();

    }

    public void visualizarArticulo() {

        gpArticulo.getStylesheets().add(getClass().getResource("/css/button_articulo.css").toExternalForm());
        for (int i = 0; i < gpArticulo.getChildren().size(); i++) {

            Button btn = (Button) gpArticulo.getChildren().get(i);
//            btn.getStylesheets().add(getClass().getResource("/css/button_articulo.css").toExternalForm());
//
//            File imageFile = new File(bt.getAccessibleHelp());
//            Image image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
//
//            bt.setFitHeight(100);
//            bt.setFitWidth(150);
//            bt.setImage(image);

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

                    ClaseUtil.getStageModal(root);

                    if (!(getArticulo() == null) && !tecladoDigitalController.getTxtCAtidad().getText().isEmpty()) {

                        catidadArticulo = Double.parseDouble(tecladoDigitalController.getTxtCAtidad().getText());
                        agregarArticulo();

                    } else {

                        ClaseUtil.mensaje("Tiene que digital una cantidad");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

        }
        System.out.println("|");
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

        System.out.println("Cantidad de categoria " + vbCategoria.getChildren().size());
        vbCategoria.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

        for (int i = 0; i < vbCategoria.getChildren().size(); i++) {

            Button bt = (Button) vbCategoria.getChildren().get(i);

            bt.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

            bt.setOnMouseClicked((event) -> {

                crearSubCategoria(Integer.parseInt(bt.getId()));
                crearArticulo(Integer.parseInt(bt.getId()));
            });

        }
        System.out.println("|");
    }

    public void visualizarSubCategoria() {

        System.out.println("Cantidad de subcategoria " + hbSubCategoria.getChildren().size());
        for (int i = 0; i < hbSubCategoria.getChildren().size(); i++) {

            Button bt = (Button) hbSubCategoria.getChildren().get(i);
            bt.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

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
        System.out.println("|");
    }

    private void llenarClientedeContado() {

        setCliente(ManejoCliente.getInstancia().getCliente(1));
        txtCodigoCliente.setText(getCliente().getCodigo().toString());
        txtNombreCliente.setText(getCliente().getNombre());

    }

    private void inicializarDatos() {

        llenarClientedeContado();
        dpFecha.setValue(LocalDate.now());
        TipoNcf tnf = manejo.factura.ManejoTipoNcf.getInstancia().getListaTipoNcf().get(0);
        relacionNcf = ManejoRelacionNcf.getInstancia().getNCF(tnf);
        txtNcf.setText(relacionNcf.getActual());

    }

    private void limpiar() {

        txtCodigoCliente.clear();
        txtNombreCliente.clear();
        txtSubTotal.clear();
        txtTotal.clear();
        txtDescuento.clear();
        txtDescuento.clear();
        txtItbis.clear();

        listadetalleFactura.clear();
//        btnFormaPago.setDisable(false);

        llenarClientedeContado();
    }

    private void nuevo() {

        factura = new Factura();
        detalleFactura = new DetalleFactura();
        btnFormaPago.setDisable(true);
        lbTipoVenta.setText("Tipo de Venta Seleccionado : " + " De Contado ");
        limpiar();

    }

    private void agregarArticulo() {

        try {

            detalleFactura = new DetalleFactura();
            Double total = 0.00, subTotal = 0.00, valorItbis = 0.00, precioVenta = 200.00;

            switch (cliente.getPrecio()) {

                case 1:
                    precioVenta = getArticulo().getPrecioVenta1();
                    System.out.println("Precio v1 " + precioVenta);
                    break;
                case 2:
                    precioVenta = getArticulo().getPrecioVenta2();
                    System.out.println("Precio v2 " + precioVenta);
                    break;
                case 3:
                    System.out.println("Precio v3 " + precioVenta);
                    precioVenta = getArticulo().getPrecioVenta3();
                    break;
            }

            detalleFactura.setArticulo(getArticulo());
            detalleFactura.setCantidad(catidadArticulo);
            detalleFactura.setDescuento(Double.parseDouble(txtDescuento.getText().isEmpty() ? "0" : txtDescuento.getText()));
            detalleFactura.setExistencia(getArticulo().getExistencia());
            detalleFactura.setNombreArticulo(getArticulo().getNombre());
            detalleFactura.setNuevaExistencia(0.0);
            detalleFactura.setPrecio(precioVenta);

            subTotal = detalleFactura.getCantidad() * detalleFactura.getPrecio();

            if (getArticulo().getExentoItbis()) {

                valorItbis = 0.0;

            } else {

                double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                valorItbis = (subTotal - detalleFactura.getDescuento()) * (itbis / 100);
                System.out.println("Itbis " + (itbis / 100));
            }

            total = (subTotal - detalleFactura.getDescuento()) + valorItbis;
            detalleFactura.setItbis(valorItbis);
            detalleFactura.setSubTotal(subTotal);
            detalleFactura.setTotal(total);
            detalleFactura.setUnidad(getArticulo().getUnidadSalida());
            detalleFactura.setFactura(factura);

//            if (!(existe(detalleFactura))) {
//                
            listadetalleFactura.add(detalleFactura);
//            }

            txtSubTotal.setText(getSubTotal().toString());
            txtDescuento.setText(getDescuento().toString());
            txtItbis.setText(getItbis().toString());
            txtTotal.setText(getTotal().toString());

            detalleFactura = new DetalleFactura();
            btnFormaPago.setDisable(false);
            setArticulo(null);
            GUIUtils.autoFitTable(tbDetalleFactura);

//            if (tipoVenta == 1) {
//                btnFormaPago.setDisable(false);
//            } else if (tipoVenta == 2) {
//                btnFormaPago.setDisable(true);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Double getSubTotal() {

        Double subTotal = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

            subTotal += det.getCantidad() * det.getPrecio();
//            subTotal += det.getSubTotal();
        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotal() {

        Double total = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

            total += (det.getCantidad() * det.getPrecio()) + det.getItbis();
//            total += det.getTotal();
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
            txtCodigoCliente.setText(getCliente().getCodigo().toString());
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

            formaDePagoController.getTxtTotalFactura().setText(txtTotal.getText());
            formaDePagoController.getTxtEfectivo().setText(txtTotal.getText());

            ClaseUtil.getStageModal(root);

            if (!(formaDePagoController.getFormaPago() == null)) {

                listFormaPagoCollection = formaDePagoController.getListaFormaPago();
                setDevuelta(formaDePagoController.getTotalPago());

            } else {

                ClaseUtil.mensaje("Tiene que aplicar una forma de pago");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnGuardarActionevent(ActionEvent event) {

        try {

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

            if (tbDetalleFactura.getItems().size() <= 0) {

                ClaseUtil.mensaje("No tiene Articulos agregado para esta Factura");
                return;
            }

            if (listFormaPagoCollection == null && tipoVenta == 1) {

                ClaseUtil.mensaje("Tiene que aplicar una forma de pago");
                return;
            }

            factura.setAnulada(false);
            factura.setCliente(getCliente());
            factura.setNombreCliente(getCliente().getNombre());
            factura.setNcf(txtNcf.getText());
            factura.setTotal(getTotal());
            factura.setSubTotal(getSubTotal());
            factura.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            factura.setDescuento(getDescuento());
            factura.setItbis(getItbis());

            factura.setFechaCreacion(new Date());

            Date fechaVencimiento = ClaseUtil.asDate(dpFecha.getValue());

            factura.setDetalleFacturaCollection(listadetalleFactura);

            if (tipoVenta == 1) { //Vanta de Contado

                factura.setPagada(true);
                factura.setPendiente(0.00);
                factura.setTipoVenta(1);
                factura.setAbono(factura.getTotal());
                factura.setCondicionPago(new CondicionPago(1));
                factura.setFechaVencimiento(fechaVencimiento);

                if (getDevuelta() < 0) {

                    ClaseUtil.mensaje(" El Monto A Pagado Tiene que ser igual al monto de la Factura ");
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

            //Comprobar limite de credito
            Double montoPendiente, montodisponible;

            montoPendiente = ManejoFactura.getInstancia().getMontoPendiente(getCliente());
            montodisponible = getCliente().getMontoCredito() - montoPendiente;

            if (tipoVenta == 2) {

                if (montodisponible <= factura.getTotal()) {

                    ClaseUtil.mensaje("EL CLIENTE NO TIENE CREDITO DISPONIBLE");
                    return;
                }
            }

            Optional<ButtonType> ok = ClaseUtil.confirmarMensaje(" Crear  Factura con Comprobante  Fiscal ");

            System.out.println("o.k " + ok.get());
            if (ok.get() == ButtonType.YES) {

                factura.setTipoNcf(manejo.factura.ManejoTipoNcf.getInstancia().getListaTipoNcf().get(0));//Sustentan Costo y gastos

                relacionNcf = ManejoRelacionNcf.getInstancia().getNCF(factura.getTipoNcf());

                boolean existe = ManejoFactura.getInstancia().existeNCF(relacionNcf.getActual());

                if (existe) {

                    while (ManejoFactura.getInstancia().existeNCF(relacionNcf.getActual())) {

                        relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo());

                    }

                } else {

                    relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo());

                }

                factura.setNcf(relacionNcf.getActual());

                ManejoRelacionNcf.getInstancia().actualizar(relacionNcf);
                System.out.println("Sin ncf");

            } else {

                factura.setTipoNcf(null); //Consumidor Final
                factura.setNcf("n/a");
                System.out.println("Sin ncf");
            }

            factura = ManejoFactura.getInstancia().salvar(factura);

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
                detalleCajaChica.setDocumento(factura.getCodigo().toString());
                detalleCajaChica.setMonto(factura.getTotal());
                detalleCajaChica.setNombreMovimiento("INGRESO");
                listaDetalleCajaChica.add(detalleCajaChica);

                cajaChica.setDetalleCajaChicaCollection(listaDetalleCajaChica);
                ManejoCajaChica.getInstancia().actualizar(cajaChica);
            }
            //fin

            getCliente().setMontoDisponible(ManejoFactura.getInstancia().getMontoDisponible(cliente));
            ManejoCliente.getInstancia().actualizar(getCliente());

            CodigoFactura = factura.getCodigo();
            System.out.println("Codigo Factura " + CodigoFactura + " Factura " + factura);

            txtNcf.setText(relacionNcf.getActual());

            if (tipoVenta == 1) { //venta de contado

                for (FormaPago fp : listFormaPagoCollection) {

                    fp.setDocumento(factura.getCodigo());
                    fp.setTipoDocumento(1);
                    fp.setFecha(util.ClaseUtil.asDate(dpFecha.getValue()));
                    ManejoFormaPago.getInstancia().salvar(fp);

                }

            } else if (tipoVenta == 2) { //venta credito

                formaPago.setDocumento(factura.getCodigo());
                formaPago.setTipoDocumento(1);
                formaPago.setFecha(util.ClaseUtil.asDate(dpFecha.getValue()));
                ManejoFormaPago.getInstancia().salvar(formaPago);
            }

            ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorSalida(listadetalleFactura);

            nuevo();

            //nota : si no se guarda el detalle es porque generaste las entidades nuevamente y el atributo cascae se le fue
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @FXML
    private void btnImprimirActionEvent(ActionEvent event) {

        try {

            if (CodigoFactura > 0) {

                System.out.println("Codigo " + CodigoFactura);
                RptFactura.getInstancia().imprimir(CodigoFactura);
                CodigoFactura = 0;
            }

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

            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())) {

                detalle.setCantidad(detalle.getCantidad() + 1);
                tbDetalleFactura.refresh();

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
        lbTipoVenta.setText("Tipo de Venta Seleccionado : " + " De Contado ");
    }

    @FXML
    private void btnCreditoActionEvent(ActionEvent event) {
        tipoVenta = 2;//Venta a Credito
        txtCodigoCliente.clear();
        txtNombreCliente.clear();
        btnFormaPago.setDisable(true);
        btnBuscarCliente.setDisable(false);
        lbTipoVenta.setText("Tipo de Venta Seleccionado : " + "Credito ");
    }

    @FXML
    private void btnBuscarArticuloActionEvent(ActionEvent event) {

        if (!txtCodigoArticulo.getText().isEmpty()) {

            List<Articulo> lista = ManejoArticulo.getInstancia().getListaArticulo(txtCodigoArticulo.getText());

            crearArticuloPorBusqueda(lista);
            txtCodigoArticulo.clear();
            txtCodigoArticulo.requestFocus();

        } else {

            gpArticulo.getChildren().clear();
        }
    }

}
