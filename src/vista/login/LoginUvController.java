/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.caja.ManejoTurnoPedido;
import manejo.cliente.ManejoCliente;
import manejo.unidadDeNegocio.ManejoUnidadDeNegocio;
import manejo.usuario.ManejoUsuario;
import modelo.Cliente;
import modelo.TurnoPedido;
import modelo.UnidadDeNegocio;
import modelo.Usuario;
import sige.FXMain;
import utiles.VariablesGlobales;
import vista.principal.LoginController;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class LoginUvController implements Initializable {

    @FXML
    private VBox vbProyecto;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXPasswordField txtClave;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXProgressBar pgBar;
    @FXML
    private ImageView imgProyecto;
    @FXML
    private Label lbProyecto;

    int codUnidadNegocio;

    Usuario usuario;
    @FXML
    private TableView<Cliente> tbCliente;
    @FXML
    private TableColumn<Cliente, String> tbcClienteContra;
    @FXML
    private TableColumn<Cliente, String> tbcContacto;
    @FXML
    private Label lbCantidadContrato;

    ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();
    @FXML
    private VBox vbCliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pgBar.setVisible(false);

        crearProyecto();
        visualizarProyecto();

        iniciarTabla();
        txtClave.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {

                procesoLogin();

            }
        });

        if (listaCliente.size() > 0) {
            vbCliente.setVisible(true);
        } else {
            vbCliente.setVisible(false);
        }
    }

    public void iniciarTabla() {

        listaCliente.clear();

        listaCliente.addAll(ManejoCliente.getInstancia().getClienteCumpleaNio());

        tbcClienteContra.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcContacto.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        tbCliente.setItems(listaCliente);
        lbCantidadContrato.setText(Integer.toString(listaCliente.size()));

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

    private void crearProyecto() {

        VBox vb;
        ImageView imgViewer = null;
        File imageFile = null;
        Label lbNombre;
        Label lbDependencia;
        HBox hbContenedor;
        vbProyecto.getChildren().clear();
        vbProyecto.setAlignment(Pos.TOP_CENTER);
        vbProyecto.setPrefWidth(3000);
//        vbProyecto.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

        //listaProyectoo.addAll(BaseDeDato.getInsatancia().getBaseDatoProyecto());
        List<UnidadDeNegocio> lista = ManejoUnidadDeNegocio.getInstancia().getLista();

        for (UnidadDeNegocio ung : lista) {

//            byte byteImage[] = null;
            // obtener la columna imagen, luego el arreglo de bytes 
//            Blob blob =ung.getLogo();
//            byteImage = blob.getBytes(1, (int) blob.length());
            // crear el Image y mostrarlo en el ImageView
            Image img = new Image(new ByteArrayInputStream(ung.getLogo()));
            imgViewer = new ImageView(img);

//            imageFile = new File(getClass().getResource("foto/articulo 10.png").toString());
//            imgViewer = new ImageView(new Image(imageFile.toURI().toString(), 100, 50, false, false));
            imgViewer.setFitWidth(120);
            imgViewer.setFitHeight(90);

            hbContenedor = new HBox();
//            hbContenedor.setPrefSize(260, 100);
            hbContenedor.setPrefSize(950, 120);
            hbContenedor.setAlignment(Pos.TOP_CENTER);
            vb = new VBox();
            vb.setAlignment(Pos.TOP_CENTER);
            vb.setPrefSize(150, 115);

            hbContenedor.setSpacing(5.0);

            hbContenedor.setStyle("   -fx-background-color: #FFFFFF;"
                    + "    -fx-border-color:  #00bb2d;\n"
                    + "    -fx-background-radius: 10px;\n");

            lbNombre = new Label(ung.getNombre());

            if (ung.getSucursal()) {
                lbDependencia = new Label("SUCURSAL");
            } else {
                lbDependencia = new Label("PRINCIPAL");
            }

//            lbNombre.setUnderline(true);
            lbNombre.setPrefSize(160, 60);

            lbNombre.setStyle(" -fx-text-fill: #000000;"
                    + " -fx-font-size: 14px;");

            lbDependencia.setPrefSize(160, 60);

            lbDependencia.setStyle(" -fx-text-fill: #000000;"
                    + " -fx-font-size: 14px;");
            lbDependencia.setUnderline(true);

            vb.getChildren().add(lbNombre);
            vb.getChildren().add(lbDependencia);

            hbContenedor.setId(ung.getCodigo().toString());
            vb.setCursor(Cursor.HAND);
            hbContenedor.getChildren().addAll(imgViewer, vb);

            vbProyecto.getChildren().add(hbContenedor);

        }

    }

    public void visualizarProyecto() {

//        vbProyecto.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());
        for (int i = 0; i < vbProyecto.getChildren().size(); i++) {

            HBox vb = (HBox) vbProyecto.getChildren().get(i);

//            bt.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());
            vb.setOnMouseClicked((event) -> {

                codUnidadNegocio = Integer.parseInt(vb.getId());

                VBox vbox = ((VBox) vb.getChildren().get(1));
                String nombre = ((Label) vbox.getChildren().get(0)).getText();
                lbProyecto.setText(nombre);
                txtNombre.requestFocus();

            });

        }

    }

    private void procesoLogin() {

        pgBar.setVisible(true);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText(null);

        try {

            if (txtNombre.getText().isEmpty() || txtClave.getText().isEmpty()) {

                alert.setContentText("Debe completar los datos de autenticación...");
                alert.showAndWait();

                pgBar.setVisible(false);
                return;
            }

            System.out.println("Valor " + txtNombre.getText() + " clave " + txtClave.getText() + " codUnidadNegocio " + codUnidadNegocio);
            usuario = ManejoUsuario.getInstancia()
                    .getUsuario(txtNombre.getText(), txtClave.getText(), codUnidadNegocio);

            if (usuario == null) {

                alert.setContentText(" Nombre de usuario o contraseña o Unidad de negocio inválidos...");
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
//                ManejoTurnoPedido.getInstancia().salvar(turnoPedido);

            }

            VariablesGlobales.USUARIO = usuario;
            FXMain.vistaPrincipal();

            pgBar.setVisible(false);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @FXML
    private void tbContratoDeServicioClicked(MouseEvent event) {
    }

}