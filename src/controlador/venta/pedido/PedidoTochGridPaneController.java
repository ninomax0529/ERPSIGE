/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.pedido;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controlador.direccion.FXMLBuscarDireccionrController;
import controlador.venta.cliente.FXMLBusClienterController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.annotation.PostConstruct;
import manejo.pedido.ManejoPedido;
import manejo.articulo.ManejoArticulo;
import manejo.caja.ManejoTurnoPedido;
import manejo.cliente.ManejoCliente;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoFormaPago;
import manejo.factura.ManejoRelacionNcf;
import manejo.factura.ManejoTipoFormaPago;
import manejo.pedido.ManejoEstadoPedido;
import modelo.Articulo;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.DetallePedido;
import modelo.Direccion;
import modelo.EstadoPedido;
import modelo.FormaPago;
import modelo.Pedido;
import modelo.RelacionNcf;
import modelo.TipoFormaPago;
import modelo.TipoNcf;
import modelo.TurnoPedido;
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
public class PedidoTochGridPaneController implements Initializable {

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

    @FXML
    private JFXTextField txtDevueltaDe;
    @FXML
    private JFXTextField txtDineroADevolver;
    @FXML
    private ScrollPane scpane1;

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

    @FXML
    private ScrollPane scpane;
    private GridPane gpArticulo = new GridPane();
    private GridPane gpDetallePedido = new GridPane();
    ObservableList<Button> listaArticulo = FXCollections.observableArrayList();

    @FXML
    private JFXTextField txtTotal;
    PedidoTochGridPaneController facturacionTochController;
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
    @FXML
    private JFXButton btnBuscarDireccion;

    public PedidoTochGridPaneController getFacturacionTochController() {
        return facturacionTochController;
    }

    public void setFacturacionTochController(PedidoTochGridPaneController facturacionTochController) {
        this.facturacionTochController = facturacionTochController;
    }

    ObservableList<DetallePedido> listadetallePedido = FXCollections.observableArrayList();
    ObservableList<String> listaTipoVenta = FXCollections.observableArrayList();

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
    private JFXTextField txtNombreCliente;
    @FXML
    private JFXButton btnBuscarCliente;

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
    @FXML
    private JFXButton btnEliminar;
    private TextField txtCAtidad;
    @FXML
    private JFXButton btnCredito;

    @FXML
    private BorderPane bpPrincipal;

    Integer cantidad = 0;
    Double catidadArticulo = 0.0;
    Double existenciaEnPeso = 0.00;
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

        gpDetallePedido.setHgap(0);
        gpDetallePedido.setVgap(3);
        gpDetallePedido.setScaleShape(true);
        gpDetallePedido.setAlignment(Pos.CENTER_LEFT);
        gpArticulo.setAlignment(Pos.CENTER);
        gpArticulo.setScaleShape(true);

        gpArticulo.setHgap(2);
        gpArticulo.setVgap(5);

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
//            Thread.sleep(50);
//        crearCategoria();
//        crearArticulo();
//

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

        txtDevueltaDe.setOnKeyPressed(value -> {

            if (!txtDevueltaDe.getText().isEmpty()) {

                if (value.getCode() == KeyCode.ENTER) {

                    Double valor = Double.parseDouble(txtDevueltaDe.getText()), devuelta = 0.00;
                    devuelta = FormatNum.formatoNumero(valor - getTotal());

//                    NumeroALetra na=new NumeroALetra();
                    txtDineroADevolver.setText(devuelta.toString());
                }

            }

        });

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

        scpane.setContent(gpArticulo);
        visualizarArticuloHb();

    }

    private void crearArticulo() {

        ImageView img = null;

        List<Articulo> lista = ManejoArticulo.getInstancia().getArticuloPorCategoria(1);

        gpArticulo.getChildren().clear();
        scpane.setVvalue(0);
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
                    img = new ImageView(new Image(imageFile.toURI().toString(), 150, 110, false, false));
                    img.setFitWidth(150);
                    img.setFitHeight(120);

                    VBox vb = new VBox();
                    HBox hb = new HBox();
                    hb.setMaxWidth(Double.MAX_VALUE);
                    vb.setMaxWidth(Double.MAX_VALUE);
                    hb.setSpacing(10);
                    vb.setSpacing(5);
                    vb.setPadding(new Insets(5, 5, 5, 5));
                    hb.setStyle("   -fx-text-fill:ffffff;\n"
                            + "    -fx-border-color:  -fx-primary;\n"
                            + "    -fx-border-radius: 5px;\n"
                            + "    -fx-background-radius: 10px;\n"
                            + "    -fx-font-size: 13pt;");

                    hb.getChildren().add(img);
                    vb.getChildren().add(new Label("Codigo : " + lista.get(num).getCodigo().toString()));
                    vb.getChildren().add(new Label(lista.get(num).getNombre()));
                    vb.getChildren().add(new Label("Precio : " + Double.toString(lista.get(num).getPrecioVenta1()) + " $"));
                    vb.getChildren().add(new Label("Existencia : "
                            + Double.toString(lista.get(num).getExistencia()) + " " + lista.get(num)
                            .getUnidadSalida().getDescripcion()));

                    hb.getChildren().add(vb);
                    hb.setId(Integer.toString(lista.get(num).getCodigo()));
                    hb.setCursor(Cursor.HAND);

                    GridPane.setConstraints(hb, y, x);

                    gpArticulo.add(hb, y, x);// y indice de la columna,x indice de la fila 

                }

                num++;

            }
        }

        scpane.setContent(gpArticulo);

        visualizarArticuloHb();

    }

    private void detallePedido(List<DetallePedido> lista) {

        ImageView img = null;

//        List<DetallePedido> lista = ManejoPedido.getInstancia().getDetallePedido(pedido);
        System.out.println("cantidad de item " + lista.size());
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
                img.setFitWidth(60);
                img.setFitHeight(40);
//                img.setStyle(" -fx-border-radius: 5px;");

                HBox hb = new HBox();
                hb.setAlignment(Pos.CENTER_LEFT);
                hb.setMaxWidth(Double.MAX_VALUE);
                hb.setSpacing(10);
                hb.setPadding(new Insets(2, 2, 2, 2));

                hb.setStyle("   -fx-text-fill:ffffff;\n"
                        + "    -fx-border-color:  #00bb2d;\n"
                        + "    -fx-border-radius: 5px;\n"
                        + "    -fx-background-radius: 10px;\n"
                        + "    -fx-font-size: 12pt;");

                hb.getChildren().add(img);

//                hb.getChildren().add(new Label(lista.get(num).getCantidad().toString()));
                String unidad = lista.get(num).getArticulo().getNombreEmbase() == null ? lista.get(num).getUnidad().getDescripcion()
                        : lista.get(num).getArticulo().getNombreEmbase();

                hb.getChildren().add(new Label(lista.get(num).getCantidad().toString()
                        + "  " + unidad + "  DE "));
                hb.getChildren().add(new Label(lista.get(num).getNombreArticulo()));

                hb.getChildren().add(new Label("Valor : " + Double.toString(lista.get(num).getTotal()) + "  $ "));

                hb.setId(Integer.toString(lista.get(num).getNumeroArticulo()));

                System.out.println("Codigo " + lista.get(num).getCodigo());

                if (getEditar() == true && !(lista.get(num).getCodigo() == null)) {

                    hb.setAccessibleText(Integer.toString(lista.get(num).getCodigo()));
                }

                hb.setCursor(Cursor.HAND);

                GridPane.setConstraints(hb, y, x);
                gpDetallePedido.add(hb, y, x);// y indice de la columna,x indice de la fila 

                num++;
                indice++;

//                }
            }

            System.out.println("Valor x " + x);
        }

        scpane1.setContent(gpDetallePedido);

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

                if (getEditar() == true) {

                    int codigoDetallePedido = Integer.parseInt(btn.getAccessibleText() == null ? "0" : btn.getAccessibleText());

                    int numeroDeLinea = Integer.parseInt(btn.getId());
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
                        detallePedido(listadetallePedido);
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
                    img = new ImageView(new Image(imageFile.toURI().toString(), 130, 100, false, false));
                    img.setFitWidth(130);
                    img.setFitHeight(100);

                    VBox vb = new VBox();
                    HBox hb = new HBox();
                    hb.setMaxWidth(Double.MAX_VALUE);
                    vb.setMaxWidth(Double.MAX_VALUE);
                    hb.setSpacing(10);
                    vb.setSpacing(5);
                    vb.setPadding(new Insets(5, 5, 5, 5));
                    vb.setPadding(new Insets(5, 5, 5, 5));
                    hb.setStyle("   -fx-text-fill:ffffff;\n"
                            + "    -fx-border-color:  -fx-primary;\n"
                            + "    -fx-border-radius: 14px;\n"
                            + "    -fx-background-radius: 14px;\n"
                            + "    -fx-font-size: 12pt;");

                    hb.getChildren().add(img);
                    vb.getChildren().add(new Label("Codigo : " + lista.get(num).getCodigo().toString()));
                    vb.getChildren().add(new Label(lista.get(num).getNombre()));
                    vb.getChildren().add(new Label("Precio : " + Double.toString(lista.get(num).getPrecioVenta1()) + "  $"));
                    vb.getChildren().add(new Label("Existencia : "
                            + Double.toString(lista.get(num).getExistencia()) + "  " + lista.get(num)
                            .getUnidadSalida().getDescripcion()));
                    hb.getChildren().add(vb);
                    hb.setId(Integer.toString(lista.get(num).getCodigo()));
                    hb.setCursor(Cursor.HAND);
                    GridPane.setConstraints(hb, y, x);
                    gpArticulo.add(hb, y, x);

                }

                num++;

//                }
            }

        }

        scpane.setContent(gpArticulo);

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

        scpane.setContent(gpArticulo);

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

        scpane.setContent(gpArticulo);

        visualizarArticuloHb();
    }

    public void visualizarArticuloEje() {

        gpArticulo.getStylesheets().add(getClass().getResource("/css/btn_articulos.css").toExternalForm());

        for (int i = 0; i < gpArticulo.getChildren().size(); i++) {

            Button btn = (Button) gpArticulo.getChildren().get(i);
            btn.setCursor(Cursor.HAND);

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

        txtNombreCliente.clear();

        txtTotal.clear();
        txtNumeroCalle.clear();
        txtNumeroCasa.clear();
        txtDevueltaDe.clear();
        txtDineroADevolver.clear();
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

            txtTotal.setText(getTotal().toString());

            detallePedido(listadetallePedido);

            crearArticulo();

            txtCodigoArticulo.requestFocus();

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

    private Double getTotal() {

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
//            txtCodigoCliente.setText(getCliente().getCodigo().toString());
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

            if (tipoVenta == 2 && txtNombreCliente.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que seleccionar un cliente");
                return;
            }

            guardar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
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

    private boolean existe(DetalleFactura det) {

//        for (DetallePedido detalle : tbDetalleFactura.getItems()) {
//
//            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())) {
//
//                detalle.setCantidad(detalle.getCantidad() + 1);
//                tbDetalleFactura.refresh();
//
//                return true;
//
//            }
//        }
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

    private void btnDeContadoActionEvent(ActionEvent event) {

        tipoVenta = 1;//Venta De Contado

        llenarClientedeContado();
//        lbTipoVenta.setText("Tipo de Venta Seleccionado : " + " De Contado ");
    }

    @FXML
    private void btnCreditoActionEvent(ActionEvent event) {
        tipoVenta = 2;//Venta a Credito
//        txtCodigoCliente.clear();
        txtNombreCliente.clear();

        btnBuscarCliente.setDisable(false);
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

            int estadoPedido;
            EstadoPedido objEstadppedido = null;

            if (getEditar() == true) {

                objEstadppedido = ManejoEstadoPedido.getInstancia().getEstadoPedido(getPedido().getEstado().getCodigo());

            } else {

                estadoPedido = ManejoEstadoPedido.getInstancia().getEstadoPedido(1).getCodigo();

                turnoPedido = ManejoTurnoPedido.getInstancia().getNumeroTurno(getPedido().getFecha());
                getPedido().setNumeroDeTurno(turnoPedido.getNumero());
                System.out.println("Numero  turno " + turnoPedido.getNumero());
            }

            getPedido().setTotal(getTotal());
            getPedido().setSubTotal(getSubTotal());

            getPedido().setFechaCreacion(new Date());
            getPedido().setHoraCreacion(new Date());
            getPedido().setCalle(txtNumeroCalle.getText());
            getPedido().setCasa(txtNumeroCasa.getText());
            getPedido().setUsuario(VariablesGlobales.USUARIO);
            getPedido().setEstado(objEstadppedido);
            getPedido().setDireccion(txtDireccion.getText());

            Double devueltaDe, dineroDevuelto = 0.00;

            if (!(txtDevueltaDe.getText().isEmpty())) {

                devueltaDe = Double.parseDouble(txtDevueltaDe.getText());

            } else {
                devueltaDe = FormatNum.formatoNumero(getTotal());
            }

            if (!(txtDineroADevolver.getText().isEmpty())) {
                dineroDevuelto = Double.parseDouble(txtDineroADevolver.getText());
            } else {
                dineroDevuelto = 0.0;
            }

            getPedido().setDevueltaDe(devueltaDe);
            getPedido().setValorDevuelta(dineroDevuelto);

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
    @FXML
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
        txtDevueltaDe.setText(getPedido().getDevueltaDe().toString());
        txtDineroADevolver.setText(getPedido().getValorDevuelta().toString());
        txtNumeroCalle.setText(getPedido().getCalle());
        txtNumeroCasa.setText(getPedido().getCasa());
        listadetallePedido.clear();
        listadetallePedido.addAll(ManejoPedido.getInstancia().getDetallePedido(pedido.getCodigo()));
        detallePedido(listadetallePedido);
    }

    private void organizarIndice() {

        numeroArticulo = 0;
        for (DetallePedido det : listadetallePedido) {

            det.setNumeroArticulo(numeroArticulo);
            System.out.println("DetallePedido " + det.getNumeroArticulo());
            numeroArticulo++;

        }
    }
}
