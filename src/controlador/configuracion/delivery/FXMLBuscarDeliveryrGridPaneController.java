/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.delivery;

import com.jfoenix.controls.JFXTextField;
import java.io.File;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;
import manejo.delivery.ManejoDelivery;
import manejo.pedido.ManejoPedido;
import modelo.Delivery;
import modelo.DetallePedido;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class FXMLBuscarDeliveryrGridPaneController implements Initializable {

    @FXML
    private ScrollPane scpane;

    /**
     * @return the delivery
     */
    public Delivery getDelivery() {
        return delivery;
    }

    /**
     * @param delivery the delivery to set
     */
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    private JFXTextField txtFiltro;

    ObservableList<Delivery> lista = FXCollections.observableArrayList();

    private TableView<Delivery> tbDelivery;
    private TableColumn<Delivery, String> tbcApellido;
    private TableColumn<Delivery, String> tbcCelular;
    private TableColumn<Delivery, String> tbcNombre;
    private Delivery delivery;

    private GridPane gpDelivery = new GridPane();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        gpDelivery.setHgap(8);
        gpDelivery.setVgap(5);

        gpDelivery.setAlignment(Pos.CENTER);
        gpDelivery.setScaleShape(true);
        vistaDelivery();
    }

    private void vistaDelivery() {

        ImageView img = null;

        List<Delivery> lista = ManejoDelivery.getInstancia().getLista();
        System.out.println("cantidad de item " + lista.size());
        gpDelivery.getChildren().clear();

        File imageFile;
        int num = 0;

        for (int x = 0; x < lista.size(); x++) {

            for (int y = 0; y < 2; y++) {

                if (num < lista.size()) {

                    VBox vbMain = new VBox();
                    VBox vb = new VBox();
                    vb.setAlignment(Pos.BOTTOM_LEFT);
                    vb.setPadding(new Insets(10, 10, 10, 10));
                    HBox hb = new HBox();
//
                    vb.setMaxWidth(Double.MAX_VALUE);

                    hb.setSpacing(10);
                    Label lbNombre = new Label(lista.get(num).getNombre());
                      Label lbTitulo = new Label("Delivery");

                    lbNombre.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 14pt;");

                    lbNombre.setStyle(" -fx-text-fill: #ffffff;"
                            + " -fx-font-size: 14pt;");

                    lbNombre.setAlignment(Pos.CENTER);

                    hb.getChildren().add(lbNombre);

                    hb.setStyle(" -fx-border-color:  -fx-primary;"
                            + "   -fx-border-radius: 20px;"
                            + "  -fx-background-radius: 20px;"
                            + "  -fx-background-color: #0096d2;");

                    hb.setAlignment(Pos.CENTER);

                    vb.setSpacing(1);
                    vb.setAlignment(Pos.CENTER);
                    vb.setMaxWidth(Double.MAX_VALUE);
                    vb.setMaxWidth(Double.MAX_VALUE);

                    vbMain.setStyle(
                            "    -fx-border-color: -fx-primary;\n"
                            + "    -fx-border-radius: 20px;\n"
                            + "    -fx-background-radius: 20px;\n"
                            + "    -fx-font-size: 12pt;");

                    vb.getChildren().add(new Label("Apellidos : " + lista.get(num).getApellido()));
                    vb.getChildren().add(new Label("Direccion : " + lista.get(num).getDireccion()));
                    vbMain.getChildren().addAll(hb, vb);

                    vbMain.setId(Integer.toString(lista.get(num).getCodigo()));
                    vbMain.setCursor(Cursor.HAND);

                    GridPane.setConstraints(vbMain, y, x);
                    gpDelivery.add(vbMain, y, x);// y indice de la columna,x indice de la fila 

                    num++;

                }
            }
        }

        scpane.setContent(gpDelivery);
        visualizarDelivery();
    }

    public void visualizarDelivery() {

        gpDelivery.getStylesheets().add(getClass().getResource("/css/btn_articulos.css").toExternalForm());
        for (int i = 0; i < gpDelivery.getChildren().size(); i++) {

            VBox btn = (VBox) gpDelivery.getChildren().get(i);

            btn.setOnMouseClicked((event) -> {

                int codigo = Integer.parseInt(btn.getId().trim());
                setDelivery(ManejoDelivery.getInstancia().getDelivery(codigo));
                System.out.println("CodArticulo " + btn.getId());

                Stage stage = (Stage) gpDelivery.getScene().getWindow();
                stage.close();

            });

        }

    }

    private void tbClienteMouseCliked(MouseEvent event) {

        if (!(tbDelivery.getSelectionModel().getSelectedIndex() == -1)) {

            setDelivery(tbDelivery.getSelectionModel().getSelectedItem());
            System.out.println(getDelivery().getNombre());

            Stage stage = (Stage) tbDelivery.getScene().getWindow();
            stage.close();
        }
    }

}
