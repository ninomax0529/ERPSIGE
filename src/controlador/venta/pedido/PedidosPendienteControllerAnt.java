/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.pedido;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.pedido.ManejoEstadoPedido;
import manejo.pedido.ManejoPedido;
import modelo.DetallePedido;
import modelo.Pedido;
import reporte.pedido.RptPedido;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class PedidosPendienteControllerAnt implements Initializable {

    @FXML
    private JFXButton btnReimprimir;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private TableView<Pedido> tbPedidoPendiente;
    @FXML
    private TableColumn<Pedido, Integer> tbcNumPedidoPendiente;
    @FXML
    private TableColumn<Pedido, Date> tbcFechaPedidoPendiente;
    @FXML
    private TableColumn<Pedido, Date> tbcHoraPedidoPendiente;
    @FXML
    private TableColumn<Pedido, String> tbcClientePedidoPendiente;
    @FXML
    private TableColumn<Pedido, String> tbcNumCallePedidoPendiente;
    @FXML
    private TableColumn<Pedido, String> tbcNumCasaPedidoPendiente;
    @FXML
    private TableColumn<Pedido, Double> tbcTotalPedidoPendiente;
    @FXML
    private Label lbClinete;
    @FXML
    private Label lbNumPedido;
    @FXML
    private Label lbArtEmpacado;
    @FXML
    private Label lbArtPedido;
    @FXML
    private Label lbCalle;
    @FXML
    private Label lbCasa;

    ObservableList<DetallePedido> listadetallePedido = FXCollections.observableArrayList();

    ObservableList<Pedido> listaPedido = FXCollections.observableArrayList();
    @FXML
    private ScrollPane scpane;
    private GridPane gpArticulo = new GridPane();
    private GridPane gpPedido = new GridPane();
    @FXML
    private Label lbDireccion;
    @FXML
    private ScrollPane scpane1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTablaEncabezado();

        gpArticulo.setAlignment(Pos.CENTER);
        gpArticulo.setScaleShape(true);

        gpArticulo.setHgap(2);
        gpArticulo.setVgap(5);
    }

    public void inicializarTablaEncabezado() {

        listaPedido.clear();

        listaPedido.addAll(ManejoPedido.getInstancia()
                .getPedidosPendiente(ManejoEstadoPedido
                        .getInstancia().getEstadoPedido(1).getCodigo()));

        tbPedidoPendiente.setItems(listaPedido);
        crearPedido();

        tbcNumPedidoPendiente.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNumCallePedidoPendiente.setCellValueFactory(new PropertyValueFactory<>("calle"));
        tbcNumCasaPedidoPendiente.setCellValueFactory(new PropertyValueFactory<>("casa"));
        tbcClientePedidoPendiente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        tbcTotalPedidoPendiente.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcFechaPedidoPendiente.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcHoraPedidoPendiente.setCellValueFactory(new PropertyValueFactory<>("horaCreacion"));

    }

    private void crearArticulo(int pedido) {

        ImageView img = null;

        List<DetallePedido> lista = ManejoPedido.getInstancia().getDetallePedido(pedido);
        System.out.println("cantidad de item " + lista.size());
        gpArticulo.getChildren().clear();

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
                gpArticulo.add(hb, y, x);// y indice de la columna,x indice de la fila 

                num++;

            }

            System.out.println("Valor x " + x);
        }

        scpane.setContent(gpArticulo);

        visualizarArticuloHb();
    }

    private void crearPedido() {

        ImageView img = null;

//        ManejoPedido.getInstancia()
//                .getPedidosPendiente(ManejoEstadoPedido
//                        .getInstancia().getEstadoPedido(1).getCodigo());
        List<Pedido> lista = ManejoPedido.getInstancia().getPedidosPendiente(1);
        System.out.println("cantidad de item " + lista.size());
        gpPedido.getChildren().clear();

        File imageFile;
        int num = 0;
        for (int x = 0; x < lista.size(); x++) {

            for (int y = 0; y < 1; y++) {

                VBox vb = new VBox();
                vb.setAlignment(Pos.BOTTOM_LEFT);
                HBox hb = new HBox();
                hb.setMaxWidth(Double.MAX_VALUE);
                vb.setMaxWidth(Double.MAX_VALUE);

                hb.setSpacing(10);

                hb.getChildren().add(new Label(lista.get(num).getCodigo().toString()));

                vb.setSpacing(5);
//                vb.setPadding(new Insets(5, 5, 5, 5));

                hb.setStyle("   -fx-text-fill:ffffff;\n"
                        + "    -fx-border-color:  -fx-primary;\n"
                        + "    -fx-border-radius: 5px;\n"
                        + "    -fx-background-radius: 10px;\n"
                        + "    -fx-font-size: 13pt;");

               
                hb.getChildren().add(new Label(lista.get(num).getCliente().getNombre()));
                hb.getChildren().add(new Label(lista.get(num).getCalle()));
                hb.getChildren().add(new Label(lista.get(num).getCasa()));
//
//                hb.getChildren().add(vb);

                hb.setId(Integer.toString(lista.get(num).getCodigo()));
                hb.setCursor(Cursor.HAND);

                GridPane.setConstraints(hb, y, x);
                gpPedido.add(hb, y, x);// y indice de la columna,x indice de la fila 

                num++;

            }

            System.out.println("Valor x " + x);
        }

        scpane1.setContent(gpPedido);

        visualizarPedido();
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

            });

        }

    }
    
      public void visualizarPedido() {

        gpPedido.getStylesheets().add(getClass().getResource("/css/btn_articulos.css").toExternalForm());

        for (int i = 0; i < gpPedido.getChildren().size(); i++) {

            HBox btn = (HBox) gpPedido.getChildren().get(i);
            btn.setCursor(Cursor.HAND);
//            btn.getStylesheets().add(getClass().getResource("/css/btn_articulos.css").toExternalForm());

            btn.setOnMouseClicked((event) -> {

                int codigo = Integer.parseInt(btn.getId().trim());
                System.out.println("CodArticulo " + btn.getId());

            });

        }

    }

    @FXML
    private void btnReimprimirActionEvent(ActionEvent event) {

        if (!(tbPedidoPendiente.getSelectionModel().getSelectedIndex() == -1)) {

            try {

                Pedido pedido = tbPedidoPendiente.getSelectionModel().getSelectedItem();

                pedido.setEstado(ManejoEstadoPedido.getInstancia().getEstadoPedido(2));
                pedido.setHoraEmpacado(new Date());
                ManejoPedido.getInstancia().actualizar(pedido);

                RptPedido.getInstancia().imprimirPedido(pedido.getCodigo());

                listaPedido.clear();
                listaPedido.addAll(ManejoPedido.getInstancia()
                        .getPedidosPendiente(ManejoEstadoPedido
                                .getInstancia().getEstadoPedido(1).getCodigo()));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void btnAnularActionEvent(ActionEvent event) {

    }

    @FXML
    private void tbPedidoPendienteMouseClicked(MouseEvent event) {

        if (!(tbPedidoPendiente.getSelectionModel().getSelectedIndex() == -1)) {

            Pedido pedido = tbPedidoPendiente.getSelectionModel().getSelectedItem();
            lbClinete.setText(pedido.getNombreCliente());
            lbDireccion.setText(pedido.getDireccion());

            lbArtPedido.setText("" + ManejoPedido.getInstancia().getDetallePedido(pedido.getCodigo()).size());
            lbArtEmpacado.setText("0");
            lbCalle.setText(pedido.getCalle());
            lbCasa.setText(pedido.getCasa());
            lbNumPedido.setText(pedido.getCodigo().toString());

            crearArticulo(pedido.getCodigo());

        }

    }

}
