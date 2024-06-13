/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.principal;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.contabilidadd.ModuloDao;
import modelo.Modulo;
import sige.FXMain;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLModulosController_ED implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private JFXButton btnInventario;
    @FXML
    private JFXButton btnCxc;
    @FXML
    private JFXButton btnConfiguracion;
    @FXML
    private JFXButton btnPuntoVenta;
    @FXML
    private JFXButton btnOrdenCompra;
    @FXML
    private JFXButton btnContabilidad;
    @FXML
    private JFXButton btnConsultaVenta;
    @FXML
    private VBox vlModulos;
    @FXML
    private HBox hbModulos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        crearProyecto();
        visualizarProyecto();

    }

    public void visualizarProyecto() {

        ImageView img = null;
        File imageFile;

//        vbProyecto.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());
        for (int i = 0; i < hbModulos.getChildren().size(); i++) {

            Button bt = (Button) hbModulos.getChildren().get(i);

//               imgProyecto.setImage((new Image((Image)bt.getGraphic(). .toURI().toString(), 130, 100, false, false)));
//            Button bt = (Button) hbCategoria.getChildren().get(i);
//            bt.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());
            bt.setOnMouseClicked((event) -> {

//                imagenProyecto(bt.getId());
                try {
//
//                    if (event.getClickCount() == 2) {

                    String ruta = bt.getId();
                    System.out.println("Ruta " + ruta + " ruta buton " + bt.getId());
                    FXMain.vistaModulo(ruta);

//                        FXMain.vistaModulo("/vista/venta/facturacion/FacturacionTochHorizontal_1.fxml");
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

        }

    }

    private void crearProyecto() {

        Button btn;
        ImageView img = null;
        File imageFile = null;
        URL ruta_img = null;
        hbModulos.getChildren().clear();
        hbModulos.getStylesheets().add(getClass().getResource("/css/btn_articulos.css").toExternalForm());

        //listaProyectoo.addAll(BaseDeDato.getInsatancia().getBaseDatoProyecto());
        List<Modulo> lista = ModuloDao.getInstancia().getModulo();

        for (int i = 0; i < lista.size(); i++) {

//            if (lista.get(i).equalsIgnoreCase("db_iki_1")) {
//
//                imageFile = new File("config/ikiuno.png");
//
//            } else if (lista.get(i).equalsIgnoreCase("db_misereor")) {
//
//                imageFile = new File("config/misereor.png");
//
//            } else if (lista.get(i).equalsIgnoreCase("db_iki_2")) {
//
//                imageFile = new File("config/iki_2.png");
//
//            } else if (lista.get(i).equalsIgnoreCase("db_comercializacion")) {
//
//                imageFile = new File("config/comercializacion.png");
//
//            } else {
//
//                imageFile = new File("config/naturaleza.png");
//            }
//            img = new ImageView(new Image(imageFile.toURI().toString(), 130, 100, false, false));
//            img.setFitWidth(120);
//            img.setFitHeight(100);
//            btn = new Button(lista.get(i), img);
            btn = new Button(lista.get(i).getNombre());

            btn.setId(lista.get(i).getRuta());
//            btn.setCursor(Cursor.HAND);
            btn.setPrefSize(150, 50);
            btn.setMinWidth(150);
            btn.setMaxWidth(150);
//            imgProyecto.setImage((new Image(imageFile.toURI().toString(), 130, 100, false, false)));

            hbModulos.getChildren().add(btn);

        }

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.

    }

    private void btnRegistrarCliente(ActionEvent event) throws IOException {
        FXMain.vistaModuloVenta();
    }

    @FXML
    private void btnInventarioActionEvent(ActionEvent event) throws IOException {

        FXMain.vistaModuloInventario();

    }

    @FXML
    private void btnCxcActionEvent(ActionEvent event) throws IOException {

        FXMain.vistaModuloCxC();
    }

    private void btnCajaChicaActionEvent(ActionEvent event) throws IOException {

        FXMain.vistaModuloCajaChica();
    }

    @FXML
    private void btnConfiguracionAcionEvent(ActionEvent event) throws IOException {

        FXMain.vistaPrincipal1();

    }

    @FXML
    private void btnPuntoVentaActionEvent(ActionEvent event) throws IOException {

        FXMain.vistaModuloPuntoVenta();

    }

    @FXML
    private void btnOrdenCompraActionevent(ActionEvent event) throws IOException {

        FXMain.vistaModuloDeCompra();

    }

    @FXML
    private void btnContabilidadActionEvent(ActionEvent event) throws IOException {

        FXMain.vistaModuloContabilidad();

    }

    @FXML
    private void btnConsultaVentaActionEvent(ActionEvent event) throws IOException {
        FXMain.vistaModuloVenta();
    }

}
