/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.principal;

import sige.FXMain;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javax.swing.SwingWorker;
import manejo.caja.ManejoTurnoPedido;
import manejo.unidadDeNegocio.ManejoUnidadDeNegocio;
import manejo.usuario.ManejoUsuario;
import modelo.TurnoPedido;
import modelo.UnidadDeNegocio;
import modelo.Usuario;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXPasswordField txtClave;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnCancelar;

    /**
     * Initializes the controller class.
     */
    Usuario usuario;
    @FXML
    private JFXProgressBar pgBar;
   
    @FXML
    private VBox vbProyecto;
    @FXML
    private Label lbProyecto;

    TaskLogin taskLogin;

    int codUnidadNegocio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pgBar.setVisible(false);

        txtClave.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {

                procesoLogin();

            }
        });
//        imgLogo.setImage(new Image(getClass().getResourceAsStream("/imagen/cibaoMix.png")));
    }

    @FXML
    private void btnLoginActionEvent(ActionEvent event) {

        procesoLogin();
    }

    @FXML
    private void btnCancelarActinEvent(ActionEvent event) {

        try {

            FXMain.primaryStage.close();
        } catch (Throwable ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void procesoLogin() {

        pgBar.setVisible(true);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText(null);

        try {

            if (txtNombre.getText().isEmpty() || txtClave.getText().isEmpty()) {

                alert.setContentText("Debe completar los datos de autenticación...");
                alert.showAndWait();

                pgBar.setVisible(false);
                return;
            }

            System.out.println("Valor " + txtNombre.getText() + " clave " + txtClave.getText());
            usuario = ManejoUsuario.getInstancia().getUsuario(txtNombre.getText(), txtClave.getText());

            if (usuario == null) {

                alert.setContentText("Nombre de usuario o contraseña inválidos...");
                alert.showAndWait();

                txtNombre.requestFocus();

                pgBar.setVisible(false);
                return;

            }

            boolean existeTurno = ManejoTurnoPedido.getInstancia().existeTurno(new Date());

            if (existeTurno == false) {

                TurnoPedido turnoPedido = new TurnoPedido();
                turnoPedido.setFecha(new Date());
                turnoPedido.setNumero(1);
                ManejoTurnoPedido.getInstancia().salvar(turnoPedido);

            }

            VariablesGlobales.USUARIO = usuario;
            FXMain.vistaPrincipal();

            pgBar.setVisible(false);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    class TaskLogin extends SwingWorker<Void, Void> {

        @Override
        @SuppressWarnings("SleepWhileInLoop")
        public Void doInBackground() throws IOException {

            pgBar.setVisible(true);

            procesoLogin();
            return null;

        }

        @Override
        public void done() {

            pgBar.setVisible(false);

        }
    }

    private void crearProyecto() {

        VBox btn;
        ImageView img = null;
        File imageFile = null;
        Label lbNombre;
        vbProyecto.getChildren().clear();
//        vbProyecto.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

        //listaProyectoo.addAll(BaseDeDato.getInsatancia().getBaseDatoProyecto());
        List<UnidadDeNegocio> lista = ManejoUnidadDeNegocio.getInstancia().getLista();

        for (UnidadDeNegocio ung : lista) {

            img = new ImageView(new Image(imageFile.toURI().toString(), 100, 50, false, false));
            img.setFitWidth(100);
            img.setFitHeight(50);
            btn = new VBox();
            btn.setPrefSize(100, 50);
            btn.setAlignment(Pos.CENTER);

            btn.setSpacing(15.0);

            btn.setStyle("   -fx-background-color: #FFFFFF;"
                    + "    -fx-border-color:  #00bb2d;\n"
                    + "    -fx-background-radius: 10px;\n");

            lbNombre = new Label(ung.getNombre());
            lbNombre.setUnderline(true);
            lbNombre.setPrefSize(90, 60);

            lbNombre.setStyle(" -fx-text-fill: #000000;"
                    + " -fx-font-size: 14px;");

            btn.getChildren().add(img);
            btn.getChildren().add(lbNombre);

            btn.setId(ung.getCodigo().toString());
            btn.setCursor(Cursor.HAND);

            vbProyecto.getChildren().add(btn);

        }

    }

    public void visualizarProyecto() {

//        vbProyecto.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());
        for (int i = 0; i < vbProyecto.getChildren().size(); i++) {

            VBox bt = (VBox) vbProyecto.getChildren().get(i);

//            bt.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());
            bt.setOnMouseClicked((event) -> {

                codUnidadNegocio = Integer.parseInt(bt.getId());

                lbProyecto.setText(bt.getId().substring(3).toUpperCase());
                txtNombre.requestFocus();

            });

        }

    }

}
