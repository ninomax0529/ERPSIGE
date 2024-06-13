/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.pedido;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
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
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.pedido.ManejoEstadoPedido;
import manejo.pedido.ManejoPedido;
import modelo.DetallePedido;
import modelo.Pedido;
import reporte.pedido.RptPedido;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ConsultaPedidosController implements Initializable {

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
    private JFXButton btnImprimir;
    @FXML
    private JFXDatePicker dpFechaDesde;
    @FXML
    private JFXDatePicker dpFecgaHasta;
    @FXML
    private JFXButton btnBuscar;
    Date fechaIni, fechaFin;

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

        dpFecgaHasta.setValue(LocalDate.now());
        dpFechaDesde.setValue(LocalDate.now());

        fechaIni = ClaseUtil.asDate(dpFechaDesde.getValue());
        fechaFin = ClaseUtil.asDate(dpFecgaHasta.getValue());
        crearPedido(fechaIni, fechaFin);

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

                hb.setStyle("   -fx-text-fill:000000;"
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
        visualizarDetallePedido();
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

        visualizarDetallePedido();
    }

    private void crearPedido(Date fi, Date ff) {

        List<Pedido> lista = ManejoPedido.getInstancia().getLista(fi, ff);
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
                    vb.setSpacing(10);
                    HBox hb = new HBox();
//                    hb.setAlignment(Pos.CENTER);
//                    hb.setMaxWidth(Double.MAX_VALUE);
                    vb.setMaxWidth(Double.MAX_VALUE);

                    hb.setSpacing(10);
//                    Button lbCodPedido = new Button(lista.get(num).getNumeroDeTurno().toString());

                    Label lbNumeroPedido = new Label(lista.get(num).getCodigo().toString());
                    Label lbPedido = new Label("Pedido # ");
                    lbNumeroPedido.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 14pt;");

                    lbPedido.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 14pt;");

//                    lbNumeroTurno.setStyle(" -fx-font-size: 14pt;");
                    // + "  -fx-border-color:  -fx-primary;");
//                    lbTurno.setStyle(" -fx-font-size: 14pt;");
                    //  + "  -fx-border-color:  -fx-primary;");
                    lbNumeroPedido.setAlignment(Pos.CENTER);
//                    lbNumeroTurno.setMaxWidth(Double.MAX_VALUE);
//                    lbNumeroTurno.setMaxWidth(Double.MAX_VALUE);
                    hb.getChildren().add(lbPedido);
                    hb.getChildren().add(lbNumeroPedido);
                    hb.setStyle(" -fx-border-color:  -fx-primary;"
                            + "   -fx-border-radius: 20px;"
                            + "  -fx-background-radius: 20px;"
                            + "  -fx-background-color: #0096d2;");

                    hb.setAlignment(Pos.CENTER);

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
                            + "    -fx-font-size: 12pt;");

                    vb.getChildren().add(new Label("Fecha : " + lista.get(num).getFecha()));
                    vb.getChildren().add(new Label("Total  " + lista.get(num).getTotal() + " $"));
                    vb.getChildren().add(new Label("Calle # " + lista.get(num).getCalle()));
                    vb.getChildren().add(new Label("Casa  # " + lista.get(num).getCasa()));
                    vb.getChildren().add(new Label("Direccion : " + lista.get(num).getDireccion()));
                    vb.getChildren().add(new Label("Cliente : " + lista.get(num).getCliente().getNombre()));

//
                    vbMain.getChildren().addAll(hb, vb);
                    //  hb.setStyle("   -fx-background-color:#428845;");

                    vbMain.setId(Integer.toString(lista.get(num).getCodigo()));
                    vbMain.setCursor(Cursor.HAND);

                    GridPane.setConstraints(vbMain, y, x);
                    gpPedido.add(vbMain, y, x);// y indice de la columna,x indice de la fila 

                    num++;
                }
            }

            System.out.println("Valor x " + x);
        }

        scpane1.setContent(gpPedido);

        visualizarPedido();
    }

    public void visualizarDetallePedido() {

        gpDetallePedido.getStylesheets().add(getClass().getResource("/css/btn_articulos.css").toExternalForm());

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

                vistaDetallePedido(pedido);
//                detallePedido(codPedido);

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

            listaPedido.clear();
            listaPedido.addAll(ManejoPedido.getInstancia()
                    .getPedidosPendiente(ManejoEstadoPedido
                            .getInstancia().getEstadoPedido(1).getCodigo()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void btnAnularActionEvent(ActionEvent event) {

        try {

            Pedido pedidoObj = ManejoPedido.getInstancia().getPedido(this.pedido);

            if (pedidoObj == null) {

                ClaseUtil.mensaje("Tiene que Seleccionar un Pedido");
                return;
            }

            Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("Seguro que quiere anular este pedido ");

            if (ok.get() == ButtonType.YES) {

                pedidoObj.setAnulado(true);
                pedidoObj.setFechaAnulado(new Date());
                Pedido pedidoactualizado = ManejoPedido.getInstancia().actualizar(pedidoObj);

                if (!(pedidoactualizado == null)) {

                    List<DetallePedido> listaDetallePedido = ManejoPedido.getInstancia().getDetallePedido(pedidoactualizado.getCodigo());

                    ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorAnulacionPedido(listaDetallePedido);

                    lbCalle.setText("0");
                    lbCasa.setText("0");
                    lbPedido.setText("0");

//                    crearPedido();
                    detallePedido(0);

                    ClaseUtil.mensaje("Pedido anulado Exitosamente ");

                }

            }

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error  anulando el pedido ");
            e.printStackTrace();
        }

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
            pedidoObj.setHoraEntragadoCliente(new Date());
            ManejoPedido.getInstancia().actualizar(pedidoObj);

            lbCalle.setText("0");
            lbCasa.setText("0");
            lbPedido.setText("0");

//            RptPedido.getInstancia().imprimirPedido(pedidoObj.getCodigo());
//            crearPedido();
            detallePedido(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void btnDevolverADeliveryActionEvent(ActionEvent event) {

        try {

            Pedido pedidoObj = ManejoPedido.getInstancia().getPedido(this.pedido);

            if (pedidoObj == null) {

                ClaseUtil.mensaje("Tiene que Seleccionar un Pedido");
                return;
            }

            Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("Seguro que quiere devolver este pedido al estado anterior");

            if (ok.get() == ButtonType.YES) {

                pedidoObj.setEstado(ManejoEstadoPedido.getInstancia().getEstadoPedido(2));
                ManejoPedido.getInstancia().actualizar(pedidoObj);

                lbCalle.setText("0");
                lbCasa.setText("0");
                lbPedido.setText("0");

//                crearPedido();
                detallePedido(0);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnImprimirActionevent(ActionEvent event) {

        Pedido pedidoObj = ManejoPedido.getInstancia().getPedido(this.pedido);

        if (pedidoObj == null) {

            ClaseUtil.mensaje("Tiene que Seleccionar un Pedido");
            return;
        }

        RptPedido.getInstancia().imprimirPedido(pedidoObj.getCodigo());
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        try {

            listadetallePedido.clear();
            detallePedido(0);

            fechaIni = ClaseUtil.asDate(dpFechaDesde.getValue());
            fechaFin = ClaseUtil.asDate(dpFecgaHasta.getValue());
            crearPedido(fechaIni, fechaFin);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
