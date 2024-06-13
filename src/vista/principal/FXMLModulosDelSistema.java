/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.principal;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import manejo.contabilidadd.ModuloDao;
import modelo.Modulo;
import sige.FXMain;
import util.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLModulosDelSistema implements Initializable {

    ObservableList<Button> listaModulo = FXCollections.observableArrayList();

    FXMLModulosDelSistema facturacionTochController;

    @FXML
    private VBox vlModulos;

    public FXMLModulosDelSistema getFacturacionTochController() {
        return facturacionTochController;
    }

    public void setFacturacionTochController(FXMLModulosDelSistema facturacionTochController) {
        this.facturacionTochController = facturacionTochController;
    }

    @FXML
    private BorderPane bpPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        crearProyecto();
        visualizarProyecto();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

            }
        });

    }

    private void crearProyecto() {

        Button btn;
        ImageView img = null;
        File imageFile = null;
        URL ruta_img = null;
        vlModulos.getChildren().clear();
//        vbProyecto.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

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
            btn.setPrefSize(250, 100);
            btn.setMinWidth(200);
            btn.setMaxWidth(250);
//            imgProyecto.setImage((new Image(imageFile.toURI().toString(), 130, 100, false, false)));

            vlModulos.getChildren().add(btn);

        }

    }

    public void visualizarProyecto() {

        ImageView img = null;
        File imageFile;

//        vbProyecto.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());
        for (int i = 0; i < vlModulos.getChildren().size(); i++) {

            Button bt = (Button) vlModulos.getChildren().get(i);

//               imgProyecto.setImage((new Image((Image)bt.getGraphic(). .toURI().toString(), 130, 100, false, false)));
//            Button bt = (Button) hbCategoria.getChildren().get(i);
            bt.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

            bt.setOnMouseClicked((event) -> {

//                imagenProyecto(bt.getId());

                try {

                    if (event.getClickCount() == 2) {

                        String ruta =bt.getId();
                        System.out.println("Ruta " +ruta+" ruta buton "+bt.getId());
                        FXMain.vistaModulo(ruta);

//                        FXMain.vistaModulo("/vista/venta/facturacion/FacturacionTochHorizontal_1.fxml");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

        }
     
    }

}
