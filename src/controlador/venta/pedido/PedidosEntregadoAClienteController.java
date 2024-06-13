/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.pedido;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.caja.ManejoCajaChica;
import manejo.caja.ManejoTipoMovimieto;
import manejo.cliente.ManejoCliente;
import manejo.cliente.ManejoPlazo;
import manejo.documento.ManejoTipodocumento;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoFormaPago;
import manejo.factura.ManejoRelacionNcf;
import manejo.factura.ManejoTipoFormaPago;
import manejo.pedido.ManejoEstadoPedido;
import manejo.pedido.ManejoPedido;
import modelo.CajaChica;
import modelo.CondicionPago;
import modelo.DetalleCajaChica;
import modelo.DetalleFactura;
import modelo.DetallePedido;
import modelo.Factura;
import modelo.FormaPago;
import modelo.Pedido;
import modelo.RelacionNcf;
import modelo.TipoFormaPago;
import reporte.factura.RptFactura;
import reporte.pedido.RptPedido;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class PedidosEntregadoAClienteController implements Initializable {

    private Label lbArtEmpacado;
    private Label lbArtPedido;

    ObservableList<DetallePedido> listadetallePedido = FXCollections.observableArrayList();

    ObservableList<Pedido> listaPedido = FXCollections.observableArrayList();
    @FXML
    private ScrollPane scpane;
    private GridPane gpDetallePedido = new GridPane();
    private GridPane gpPedido = new GridPane();
    @FXML
    private ScrollPane scpane1;
    int pedido;
    @FXML
    private Label lbPedido;
    @FXML
    private Label lbCalle;
    @FXML
    private Label lbCasa;
    @FXML
    private JFXButton btnFacturaPedido;
    @FXML
    private JFXButton btnDevolverADelivery;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarGrid();

        gpDetallePedido.setAlignment(Pos.CENTER);
        gpDetallePedido.setScaleShape(true);

        gpDetallePedido.setHgap(2);
        gpDetallePedido.setVgap(5);

        gpPedido.setAlignment(Pos.CENTER);
        gpPedido.setScaleShape(true);

        gpPedido.setHgap(10);
        gpPedido.setVgap(5);
    }

    public void inicializarGrid() {

        crearPedido();

    }

    private void vistaDetallePedido(int pedido) {

        ImageView img = null;

        List<DetallePedido> lista = ManejoPedido.getInstancia().getDetallePedido(pedido);
        System.out.println("cantidad de item " + lista.size());
        gpDetallePedido.getChildren().clear();

        File imageFile;
        int num = 0;

        for (int x = 0; x < lista.size(); x++) {

            for (int y = 0; y < 1; y++) {

//                if (num < lista.size()) {
                if (lista.get(num).getArticulo().getRutaImg() == null) {

                    imageFile = new File("foto/img_articulo.jpg");

                } else {

                    imageFile = new File(lista.get(num).getArticulo().getRutaImg());
                }

                img = new ImageView(new Image(imageFile.toURI().toString(), 70, 90, false, false));
                img.setFitWidth(90);
                img.setFitHeight(70);
                img.setStyle(" -fx-border-radius: 5px;");

                VBox vb = new VBox();
                vb.setAlignment(Pos.BOTTOM_LEFT);
                HBox hb = new HBox();
//                hb.setMaxWidth(Double.MAX_VALUE);
                vb.setMaxWidth(Double.MAX_VALUE);
                hb.setAlignment(Pos.CENTER_LEFT);
//                hb.setMaxWidth(Double.MAX_VALUE);
                hb.setSpacing(5);
                hb.setPadding(new Insets(3, 2, 2, 2));
                Label lbArticuloDesc = new Label();
                lbArticuloDesc.setStyle(" -fx-text-fill: #ffffff;"
                        + " -fx-font-size: 13pt;");

                String articuloDesc;

//                hb.setAlignment(Pos.CENTER_LEFT);
//                hb.setSpacing(10);
//
//                vb.setSpacing(5);
//                vb.setPadding(new Insets(5, 5, 5, 5));
//                hb.setStyle("   -fx-text-fill:ffffff;\n"
//                        + "    -fx-border-color:  -fx-primary;\n"
//                        + "    -fx-border-radius: 5px;\n"
//                        + "    -fx-background-radius: 10px;\n"
//                        + "    -fx-font-size: 13pt;");
                hb.setStyle("   -fx-text-fill:000000;\n"
                        + " -fx-background-color: #394B52;"
                        + "    -fx-border-color:  #00bb2d;\n"
                        + "    -fx-border-radius: 5px;\n"
                        + "    -fx-background-radius: 10px;\n"
                        + "    -fx-font-size: 12pt;");

                hb.getChildren().add(img);

//                  hb.getChildren().add(img);
//                hb.getChildren().add(new Label(lista.get(num).getCantidad().toString()));
                String unidad = lista.get(num).getArticulo().getNombreEmbase() == null ? lista.get(num).getUnidad().getDescripcion()
                        : lista.get(num).getArticulo().getNombreEmbase();
//
//                hb.getChildren().add(new Label(lista.get(num).getCantidad().toString()
//                        + "  " + unidad + "  DE "));
//                hb.getChildren().add(new Label(lista.get(num).getNombreArticulo()));
//
//                hb.getChildren().add(new Label("Valor : " + Double.toString(lista.get(num).getTotal()) + "  $ "));
//
//                hb.setId(Integer.toString(lista.get(num).getNumeroArticulo()));

                articuloDesc = lista.get(num).getCantidad().toString() + "  "
                        + unidad + " DE " + lista.get(num).getNombreArticulo() + " Valor : " + Double.toString(lista.get(num).getTotal()) + " $ ";
                lbArticuloDesc.setText(articuloDesc);

                hb.getChildren().add(lbArticuloDesc);

                System.out.println("Codigo " + lista.get(num).getCodigo());

                hb.setCursor(Cursor.HAND);

                GridPane.setConstraints(hb, y, x);
                gpDetallePedido.add(hb, y, x);// y indice de la columna,x indice de la fila 

//                String unidad = lista.get(num).getArticulo().getNombreEmbase() == null ? lista.get(num).getUnidad().getDescripcion()
//                        : lista.get(num).getArticulo().getNombreEmbase();
//
////                  NumeroALetra ma=new NumeroALetra();
////                    vb.getChildren().add(new Label(ma.Convertir(lista.get(num).getCantidad().toString(), true)
//                vb.getChildren().add(new Label(lista.get(num).getCantidad().toString()
//                        + "   " + unidad + " DE "));
//
////                vb.getChildren().add(new Label("   De "));
//                vb.getChildren().add(new Label(lista.get(num).getNombreArticulo()));
//
//                vb.getChildren().add(new Label("Valor : " + Double.toString(lista.get(num).getTotal()) + "  $ "));
//
//                hb.getChildren().add(vb);
//
//                hb.setId(Integer.toString(lista.get(num).getCodigo()));
//                hb.setCursor(Cursor.HAND);
//
//                GridPane.setConstraints(hb, y, x);
//                gpDetallePedido.add(hb, y, x);// y indice de la columna,x indice de la fila 
                num++;

            }

        }

        scpane.setContent(gpDetallePedido);
        visualizarArticuloHb();
    }

    private void detallePedido(int pedido) {

        ImageView img = null;

        List<DetallePedido> lista = ManejoPedido.getInstancia().getDetallePedido(pedido);
        System.out.println("cantidad de item " + lista.size());
        gpDetallePedido.getChildren().clear();

        File imageFile;
        int num = 0;
        for (int x = 0; x < lista.size(); x++) {

            for (int y = 0; y < 1; y++) {

//                if (num < lista.size()) {
                if (lista.get(num).getArticulo().getRutaImg() == null) {

                    imageFile = new File("foto/img_articulo.jpg");

                } else {

                    imageFile = new File(lista.get(num).getArticulo().getRutaImg());
                }

                img = new ImageView(new Image(imageFile.toURI().toString(), 150, 100, false, false));
                img.setFitWidth(140);
                img.setFitHeight(100);
                img.setStyle(" -fx-border-radius: 5px;");

                VBox vb = new VBox();
                vb.setAlignment(Pos.BOTTOM_LEFT);
                HBox hb = new HBox();
                hb.setMaxWidth(Double.MAX_VALUE);
                vb.setMaxWidth(Double.MAX_VALUE);
                hb.setSpacing(10);

                vb.setSpacing(5);
//                vb.setPadding(new Insets(5, 5, 5, 5));

                hb.setStyle("   -fx-text-fill:ffffff;\n"
                        + "    -fx-border-color:  -fx-primary;\n"
                        + "    -fx-border-radius: 5px;\n"
                        + "    -fx-background-radius: 10px;\n"
                        + "    -fx-font-size: 13pt;");

                hb.getChildren().add(img);

                String unidad = lista.get(num).getArticulo().getNombreEmbase() == null ? lista.get(num).getUnidad().getDescripcion()
                        : lista.get(num).getArticulo().getNombreEmbase();

//                  NumeroALetra ma=new NumeroALetra();
//                    vb.getChildren().add(new Label(ma.Convertir(lista.get(num).getCantidad().toString(), true)
                vb.getChildren().add(new Label(lista.get(num).getCantidad().toString()
                        + "   " + unidad + " DE "));

//                vb.getChildren().add(new Label("   De "));
                vb.getChildren().add(new Label(lista.get(num).getNombreArticulo()));

                vb.getChildren().add(new Label("Valor : " + Double.toString(lista.get(num).getTotal()) + "  $ "));

                hb.getChildren().add(vb);

                hb.setId(Integer.toString(lista.get(num).getCodigo()));
                hb.setCursor(Cursor.HAND);

                GridPane.setConstraints(hb, y, x);
                gpDetallePedido.add(hb, y, x);// y indice de la columna,x indice de la fila 

                num++;

            }

            System.out.println("Valor x " + x);
        }

        scpane.setContent(gpDetallePedido);

        visualizarArticuloHb();
    }

//    private void crearPedido() {
//
//        List<Pedido> lista = ManejoPedido.getInstancia().getPedidosPendiente(4);
//        System.out.println("cantidad de item " + lista.size());
//        gpPedido.getChildren().clear();
//
//        int num = 0;
//        int n = lista.size();
//        if (n >= 3) {
//            n = 3;
//            System.out.println("valor n if 1 " + n);
//        } else if (n >= 2) {
//            n = 2;
//            System.out.println("valor n if 2 " + n);
//        } else {
//            n = 1;
//            System.out.println("valor n if 3 " + n);
//        }
//
//        for (int x = 0; x < lista.size(); x++) {
//
//            for (int y = 0; y < n; y++) {
//
//                if (num < lista.size()) {
//
//                    VBox vbMain = new VBox();
//                    VBox vb = new VBox();
//                    vb.setAlignment(Pos.BOTTOM_LEFT);
//                    vb.setPadding(new Insets(10, 10, 10, 10));
//                    HBox hb = new HBox();
////                    hb.setAlignment(Pos.CENTER);
////                    hb.setMaxWidth(Double.MAX_VALUE);
//                    vb.setMaxWidth(Double.MAX_VALUE);
//
//                    hb.setSpacing(10);
////                    Button lbCodPedido = new Button(lista.get(num).getNumeroDeTurno().toString());
//
//                    Label lbNumeroPedido = new Label(lista.get(num).getCodigo().toString());
//                    Label lbPedido = new Label("Pedido # ");
//                    lbNumeroPedido.setStyle(" -fx-text-fill: #ffffff;"
//                            + " -fx-font-size: 14pt;");
//
//                    lbPedido.setStyle(" -fx-text-fill: #ffffff;"
//                            + " -fx-font-size: 14pt;");
//
////                    lbNumeroTurno.setStyle(" -fx-font-size: 14pt;");
//                    // + "  -fx-border-color:  -fx-primary;");
////                    lbTurno.setStyle(" -fx-font-size: 14pt;");
//                    //  + "  -fx-border-color:  -fx-primary;");
//                    lbNumeroPedido.setAlignment(Pos.CENTER);
////                    lbNumeroTurno.setMaxWidth(Double.MAX_VALUE);
////                    lbNumeroTurno.setMaxWidth(Double.MAX_VALUE);
//                    hb.getChildren().add(lbPedido);
//                    hb.getChildren().add(lbNumeroPedido);
//                    hb.setStyle(" -fx-border-color:  -fx-primary;"
//                            + "   -fx-border-radius: 20px;"
//                            + "  -fx-background-radius: 20px;"
//                            + "  -fx-background-color: #0096d2;");
//
//                    hb.setAlignment(Pos.CENTER);
//
////                    hb.setStyle("   -fx-border-color: -fx-primary;");
//                    vb.setSpacing(1);
//                    vb.setAlignment(Pos.CENTER);
//                    vb.setMaxWidth(Double.MAX_VALUE);
//                    vb.setMaxWidth(Double.MAX_VALUE);
//                    //   vb.setStyle("   -fx-background-color:ddffgg;");
////                vb.setPadding(new Insets(5, 5, 5, 5));
//
//                    vbMain.setStyle(
//                            //"    -fx-text-fill:ffffff;\n"+
//                            // + "    -fx-background-color: -fx-primary;\n"
//                            "    -fx-border-color: -fx-primary;\n"
//                            + "    -fx-border-radius: 20px;\n"
//                            + "    -fx-background-radius: 20px;\n"
//                            + "    -fx-font-size: 12pt;");
//
//                    vb.getChildren().add(new Label("Total  " + lista.get(num).getTotal() + " $"));
//                    vb.getChildren().add(new Label("Calle # " + lista.get(num).getCalle()));
//                    vb.getChildren().add(new Label("Casa  # " + lista.get(num).getCasa()));
//                    vb.getChildren().add(new Label("Direccion : " + lista.get(num).getDireccion()));
//                    vb.getChildren().add(new Label("Cliente : " + lista.get(num).getCliente().getNombre()));
////
//                    vbMain.getChildren().addAll(hb, vb);
//                    //  hb.setStyle("   -fx-background-color:#428845;");
//
//                    vbMain.setId(Integer.toString(lista.get(num).getCodigo()));
//                    vbMain.setCursor(Cursor.HAND);
//
//                    GridPane.setConstraints(vbMain, y, x);
//                    gpPedido.add(vbMain, y, x);// y indice de la columna,x indice de la fila 
//
//                    num++;
//                }
//            }
//
//            System.out.println("Valor x " + x);
//        }
//
//        scpane1.setContent(gpPedido);
//
//        visualizarPedido();
//    }
    private void crearPedido() {

        List<Pedido> lista = ManejoPedido.getInstancia().getPedidosPendiente(4);
        System.out.println("cantidad de item " + lista.size());
        gpPedido.getChildren().clear();

        int num = 0;
        int n = lista.size();
        if (n >= 3) {
            n = 3;
            System.out.println("valor n if 1 " + n);
        } else if (n >= 2) {
            n = 2;
            System.out.println("valor n if 2 " + n);
        } else {
            n = 1;
            System.out.println("valor n if 3 " + n);
        }

        for (int x = 0; x < lista.size(); x++) {

            for (int y = 0; y < n; y++) {

                if (num < lista.size()) {

                    VBox vbMain = new VBox();
                    VBox vb = new VBox();
                    vb.setAlignment(Pos.BOTTOM_LEFT);
                    vb.setPadding(new Insets(10, 10, 10, 10));
                    HBox hb = new HBox();
                    HBox hbDelivery = new HBox();
                    HBox hbTotal = new HBox();
//                    hb.setAlignment(Pos.CENTER);
//                    hb.setMaxWidth(Double.MAX_VALUE);
                    vb.setMaxWidth(Double.MAX_VALUE);

                    hb.setSpacing(10);
                    hbDelivery.setSpacing(10);
                    hbTotal.setSpacing(10);
//                    Button lbCodPedido = new Button(lista.get(num).getNumeroDeTurno().toString());

                    Label lbNumeroPedido = new Label(lista.get(num).getCodigo().toString());
                    Label lbPedido = new Label("Pedido # ");
                    Label lbDelivery = new Label("Delivery : " + lista.get(num).getNombreDelivery());
                    Label lbTotal = new Label("Total : ");
                    Label lbValorTotal = new Label(lista.get(num).getTotal()+" $");

                    lbNumeroPedido.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 14pt;");
                    lbTotal.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 14pt;");
                    lbValorTotal.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 14pt;");

                    hbTotal.setStyle(" -fx-border-color:  -fx-primary;"
                            + "   -fx-border-radius: 20px;"
                            + "  -fx-background-radius: 20px;"
                            + " -fx-background-color:  #0096d2;");

                    lbPedido.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 14pt;");
                    lbDelivery.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 12pt;");

//                    lbNumeroTurno.setStyle(" -fx-font-size: 14pt;");
                    // + "  -fx-border-color:  -fx-primary;");
//                    lbTurno.setStyle(" -fx-font-size: 14pt;");
                    //  + "  -fx-border-color:  -fx-primary;");
                    lbNumeroPedido.setAlignment(Pos.CENTER);
//                    lbNumeroTurno.setMaxWidth(Double.MAX_VALUE);
//                    lbNumeroTurno.setMaxWidth(Double.MAX_VALUE);
                    hb.getChildren().add(lbPedido);
                    hb.getChildren().add(lbNumeroPedido);

                    hbTotal.getChildren().add(lbTotal);
                    hbTotal.getChildren().add(lbValorTotal);

                    hbDelivery.getChildren().add(lbDelivery);

                    hb.setStyle(" -fx-border-color:  -fx-primary;"
                            + "   -fx-border-radius: 20px;"
                            + "  -fx-background-radius: 20px;"
                            + " -fx-background-color:  #0096d2;");
                    hb.setAlignment(Pos.CENTER);
                    hbTotal.setAlignment(Pos.CENTER);

                    hbDelivery.setAlignment(Pos.CENTER);

                    hbDelivery.setStyle(" -fx-border-color:  -fx-primary;"
                            + "   -fx-border-radius: 20px;"
                            + "  -fx-background-radius: 20px;"
                            + " -fx-background-color:  #394B52;");

//                    hb.setStyle("   -fx-border-color: -fx-primary;");
                    vb.setSpacing(1);
                    vb.setAlignment(Pos.CENTER);
                    vb.setMaxWidth(Double.MAX_VALUE);
                    vb.setMaxWidth(Double.MAX_VALUE);
                    //   vb.setStyle("   -fx-background-color:ddffgg;");
//                vb.setPadding(new Insets(5, 5, 5, 5));

                    vbMain.setStyle(
                            //"    -fx-text-fill:ffffff;\n"+
                            // + "    -fx-background-color: -fx-primary;\n"
                            "    -fx-border-color: -fx-primary;\n"
                            + "    -fx-border-radius: 20px;\n"
                            + "    -fx-background-radius: 20px;\n"
                            //  + " -fx-background-color: #394B52;"
                            + "    -fx-font-size: 12pt;");

//                    vb.getChildren().add(new Label("Total  " + lista.get(num).getTotal() + " $"));
                    vb.getChildren().add(new Label("Calle # " + lista.get(num).getCalle()));
                    vb.getChildren().add(new Label("Casa  # " + lista.get(num).getCasa()));
                    vb.getChildren().add(new Label("Direccion : " + lista.get(num).getDireccion()));
                    vb.getChildren().add(new Label("Cliente : " + lista.get(num).getCliente().getNombre()));
                    //vb.getChildren().add(hbTotal);
//
                    vbMain.getChildren().addAll(hb, hbDelivery, vb, hbTotal);
                    //  hb.setStyle("   -fx-background-color:#428845;");

                    vbMain.setId(Integer.toString(lista.get(num).getCodigo()));
                    vbMain.setCursor(Cursor.HAND);

                    GridPane.setConstraints(vbMain, y, x);
                    gpPedido.add(vbMain, y, x);// y indice de la columna,x indice de la fila 

                    num++;
                }
            }

        }

        scpane1.setContent(gpPedido);

        visualizarPedido();
    }

    public void visualizarArticuloHb() {

//        gpDetallePedido.getStylesheets().add(getClass().getResource("/css/btn_articulos.css").toExternalForm());
        for (int i = 0; i < gpDetallePedido.getChildren().size(); i++) {

            HBox btn = (HBox) gpDetallePedido.getChildren().get(i);
            btn.setCursor(Cursor.HAND);
//            btn.getStylesheets().add(getClass().getResource("/css/btn_articulos.css").toExternalForm());

            btn.setOnMouseClicked((event) -> {

                int codigo = Integer.parseInt(btn.getId().trim());
                System.out.println("CodArticulo " + btn.getId());

            });

        }

    }

    public void visualizarPedido() {

        gpPedido.getStylesheets().add(getClass().getResource("/css/btn_articulos.css").toExternalForm());

        for (int i = 0; i < gpPedido.getChildren().size(); i++) {

            VBox btn = (VBox) gpPedido.getChildren().get(i);
            btn.setCursor(Cursor.HAND);
            btn.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

            btn.setOnMouseClicked((event) -> {

                int codPedido = Integer.parseInt(btn.getId());
                pedido = codPedido;

                Pedido pedidoObj = ManejoPedido.getInstancia().getPedido(pedido);
                lbPedido.setText(pedidoObj.getCodigo().toString());
                lbCalle.setText(pedidoObj.getCalle());
                lbCasa.setText(pedidoObj.getCasa());

                vistaDetallePedido(codPedido);

            });

        }

    }

    private void btnReimprimirActionEvent(ActionEvent event) {

//        if (!(tbPedidoPendiente.getSelectionModel().getSelectedIndex() == -1)) {
        try {

            Pedido pedidoObj = ManejoPedido.getInstancia().getPedido(this.pedido);

            pedidoObj.setEstado(ManejoEstadoPedido.getInstancia().getEstadoPedido(2));
            pedidoObj.setHoraEmpacado(new Date());
            ManejoPedido.getInstancia().actualizar(pedidoObj);

            RptPedido.getInstancia().imprimirPedido(pedidoObj.getCodigo());

            crearPedido();
            detallePedido(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        }

    }

    private void tbPedidoPendienteMouseClicked(MouseEvent event) {

//        if (!(tbPedidoPendiente.getSelectionModel().getSelectedIndex() == -1)) {
        Pedido pedido = null;//tbPedidoPendiente.getSelectionModel().getSelectedItem();
//        lbClinete.setText(pedido.getNombreCliente());
//        lbDireccion.setText(pedido.getDireccion());

        lbArtPedido.setText("" + ManejoPedido.getInstancia().getDetallePedido(pedido.getCodigo()).size());
        lbArtEmpacado.setText("0");
//        lbCalle.setText(pedido.getCalle());
//        lbCasa.setText(pedido.getCasa());
//        lbNumPedido.setText(pedido.getCodigo().toString());

        detallePedido(pedido.getCodigo());

//        }
    }

    private void btnEntregarActionevent(ActionEvent event) {

        try {

            Pedido pedidoObj = ManejoPedido.getInstancia().getPedido(this.pedido);

            if (pedidoObj == null) {

                ClaseUtil.mensaje("Tiene que Seleccionar un Pedido");
                return;
            }

            pedidoObj.setEstado(ManejoEstadoPedido.getInstancia().getEstadoPedido(4));
            pedidoObj.setHoraEmpacado(new Date());
            ManejoPedido.getInstancia().actualizar(pedidoObj);

//            RptPedido.getInstancia().imprimirPedido(pedidoObj.getCodigo());
            crearPedido();
            detallePedido(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Factura facturarPedido(Pedido pedido, List<DetallePedido> listaDetPedido) {

        Factura factura = new Factura();

        Factura facturaDb = null;

        try {

            factura.setAnulada(false);
            factura.setCliente(pedido.getCliente());
            factura.setNombreCliente(pedido.getCliente().getNombre());
            factura.setNcf("na");
            factura.setTotal(pedido.getTotal());
            factura.setSubTotal(pedido.getSubTotal());
            factura.setFecha(new Date());
            factura.setDescuento(0.00);
            factura.setItbis(0.00);
            factura.setPedido(pedido);

            factura.setFechaCreacion(new Date());

            Date fechaVencimiento = new Date();

            DetalleFactura detalleFactura;
            List<DetalleFactura> listaDetalleFactura = new ArrayList();

            //Creando detalle factura con el detalle del pedido
            for (DetallePedido detallePedido : listaDetPedido) {

                detalleFactura = new DetalleFactura();

                detalleFactura.setFactura(factura);
                detalleFactura.setArticulo(detallePedido.getArticulo());
                detalleFactura.setCantidad(detallePedido.getCantidad());
                detalleFactura.setDescuento(detallePedido.getDescuento());
                detalleFactura.setExistencia(detallePedido.getExistencia());

                detalleFactura.setItbis(detallePedido.getItbis());
                detalleFactura.setNombreArticulo(detallePedido.getArticulo().getNombre());
                detalleFactura.setNuevaExistencia(detallePedido.getNuevaExistencia());
                detalleFactura.setPrecio(detallePedido.getPrecio());
                detalleFactura.setPrecioItbis(detallePedido.getPrecioItbis());

                detalleFactura.setSubTotal(detallePedido.getSubTotal());
                detalleFactura.setTotal(detallePedido.getTotal());
                detalleFactura.setUnidad(detallePedido.getUnidad());

                listaDetalleFactura.add(detalleFactura);

            }

            int tipoVenta = pedido.getTipoVenta();

            factura.setDetalleFacturaCollection(listaDetalleFactura);

            if (tipoVenta == 1) { //Vanta de Contado

                factura.setPagada(true);
                factura.setPendiente(0.00);
                factura.setTipoVenta(1);
                factura.setAbono(factura.getTotal());
                factura.setCondicionPago(new CondicionPago(1));
                factura.setFechaVencimiento(fechaVencimiento);

//                if (getDevuelta() < 0 ) {
//
//                    ClaseUtil.mensaje(" El Monto A Pagado Tiene que ser igual al monto de la Factura ");
//                    return;
//                }
            } else if (tipoVenta == 2) { //Venta a Credito

                factura.setPagada(false);
                factura.setTipoVenta(2);
                factura.setAbono(0.0);
                factura.setPendiente(factura.getTotal());
                factura.setCondicionPago(new CondicionPago(2));
                factura.setPlazo(ManejoPlazo.getInstancia().getplazo(pedido.getCliente().getDiasCredito()));
                fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, pedido.getCliente().getDiasCredito());
                factura.setFechaVencimiento(fechaVencimiento);

            }

            if (tipoVenta == 2) {

                //Comprobar limite de credito
                Double montoPendiente, montodisponible;
                montoPendiente = ManejoFactura.getInstancia().getMontoPendiente(pedido.getCliente());
                montodisponible = pedido.getCliente().getMontoCredito() - montoPendiente;

                if (montodisponible <= factura.getTotal()) {

                    ClaseUtil.mensaje("EL CLIENTE NO TIENE CREDITO DISPONIBLE");
                    return facturaDb;
                }
            }

            RelacionNcf relacionNcf;

//            Optional<ButtonType> ok = ClaseUtil.confirmarMensaje(" Crear  Factura con Comprobante  Fiscal ");
//            System.out.println("o.k " + ok.get());
//            if (ok.get() == ButtonType.YES) {
            if (false) {

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

            facturaDb = ManejoFactura.getInstancia().salvar(factura);

            if (facturaDb == null) {

                ClaseUtil.mensaje("Hubo Error creando la factura");
                return facturaDb;

            } else {

                //CREANDO ENTRADA DE DIARIO 
//                ConfiguracionCuentaContable config = null;
                String concepto = "Ventas de mercancia al contado";

                if (tipoVenta == 1) {

//                    config = ConfiguracionCuentaContableDao.getInstancia().getConfigCuentaPorTipoConcepto(6, 3);
//                    config = ConfiguracionCuentaContableDao.getInstancia().getConfigCuentaPorTipoConcepto(1, 3);
//                    System.out.println(concepto + " config " + config);
                } else if (tipoVenta == 2) { //Venta a Credito

//                    config = ConfiguracionCuentaContableDao.getInstancia().getConfigCuentaPorTipoConcepto(3, 3);
                    concepto = "Ventas de mercancia a credito ";
//                    System.out.println(concepto + " config " + config);
                }

//                List<DetalleEntradaDiario> listaDetEntradaDiario = generarConfiguracionCuenta(config, facturaDb);
//                System.out.println("lista " + listaDetEntradaDiario);
//
//                EntradaDiarioDao.getInstancia()
//                        .registrarEntradaDiario(facturaDb.getCodigo(), facturaDb.getCodigo().toString(), TipoDocumentoDao
//                                .getInstancia().getTipoDocumento(7).getCodigo(),
//                                config.getDescripcion(), 3, listaDetEntradaDiario, facturaDb.getFecha());
//
//                //REGISTRAR ENTRADA POR COSTO E INVENATRIO 
//                config = ConfiguracionCuentaContableDao.getInstancia().getConfigCuentaPorTipoConcepto(4, 3);
//                List<DetalleFactura> listaDetFactura = ManejoFactura.getInstancia().getDetalleFactura(facturaDb.getCodigo());
//                listaDetEntradaDiario = generarConfiguracionCuenta(config, listaDetFactura);
//                System.out.println("lista " + listaDetEntradaDiario);
//                EntradaDiarioDao.getInstancia()
//                        .registrarEntradaDiario(facturaDb.getCodigo(), facturaDb.getCodigo().toString(), TipoDocumentoDao
//                                .getInstancia().getTipoDocumento(7).getCodigo(),
//                                config.getDescripcion(), 3, listaDetEntradaDiario, facturaDb.getFecha());
                //CIERRE 
                //FIN ENTRADA DE DIARIO
                //Actualizando Existencia
//                System.out.println("Actualizando inventario con factura numero  " + facturaDb.getCodigo());
//                List<DetalleFactura> listaDet = new ArrayList<>();
//
//                listaDet.addAll(ManejoFactura.getInstancia().getDetalleFactura(facturaDb.getCodigo()));
//
//                //Actualizar inventario 
//                if (listaDet.size() > 0) {
//
//                    ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorSalida(listaDet);
//                }
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
                pedido.getCliente().setMontoDisponible(ManejoFactura.getInstancia().getMontoDisponible(pedido.getCliente()));
                ManejoCliente.getInstancia().actualizar(pedido.getCliente());

                int CodigoFactura = facturaDb.getCodigo();
                System.out.println("Codigo Factura " + CodigoFactura + " Factura " + facturaDb);

                FormaPago formaPago;

                //Aplicar forma de pago automatica
                TipoFormaPago tipoFormaPago = null;

                if (tipoVenta == 1) {

                    tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(1);

                } else if (tipoVenta == 2) {

                    tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);

                }

                formaPago = new FormaPago();

                formaPago.setTipoFormaPago(tipoFormaPago);
                formaPago.setMonto(facturaDb.getTotal());

                formaPago.setDocumento(facturaDb.getCodigo());
                formaPago.setTipoDocumento(1);
                formaPago.setFecha(facturaDb.getFecha());
                ManejoFormaPago.getInstancia().salvar(formaPago);

                // fin Aplicar forma de pago automatica
                System.out.println("Fatura num " + facturaDb.getCodigo());

            }

            //nota : si no se guarda el detalle es porque generaste las entidades nuevamente y el atributo cascae se le fue
        } catch (Exception e) {

            e.printStackTrace();
        }

        return facturaDb;
    }

    @FXML
    private void btnFacturaPedidoActionevent(ActionEvent event) {

        try {

            Pedido pedidoObj = ManejoPedido.getInstancia().getPedido(this.pedido);

            if (pedidoObj == null) {

                ClaseUtil.mensaje("Tiene que Seleccionar un Pedido");
                return;
            }

            pedidoObj.setEstado(ManejoEstadoPedido.getInstancia().getEstadoPedido(5));

            System.out.println("pedidoObj "+pedidoObj);
            ManejoPedido.getInstancia().actualizar(pedidoObj);

            List<DetallePedido> listaDetallePedido = ManejoPedido.getInstancia().getDetallePedido(pedidoObj.getCodigo());

            Factura facturaDb = facturarPedido(pedidoObj, listaDetallePedido);

            RptFactura.getInstancia().imprimir(facturaDb.getCodigo());

            lbCalle.setText("0");
            lbCasa.setText("0");
            lbPedido.setText("0");

            crearPedido();
            detallePedido(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnDevolverADeliveryActionEvent(ActionEvent event) {

        try {

            Pedido pedidoObj = ManejoPedido.getInstancia().getPedido(this.pedido);

            if (pedidoObj == null) {

                ClaseUtil.mensaje("Tiene que Seleccionar un Pedido");
                return;
            }

            Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("Seguro que quiere devolver este pedido al estado anterior");

            if (ok.get() == ButtonType.YES) {

                pedidoObj.setEstado(ManejoEstadoPedido.getInstancia().getEstadoPedido(3));
                ManejoPedido.getInstancia().actualizar(pedidoObj);
            }

            lbCalle.setText("0");
            lbCasa.setText("0");
            lbPedido.setText("0");

            crearPedido();
            detallePedido(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
