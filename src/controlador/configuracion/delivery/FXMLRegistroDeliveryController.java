/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.delivery;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import manejo.delivery.ManejoDelivery;
import modelo.Delivery;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLRegistroDeliveryController implements Initializable {

    @FXML
    private ImageView imgFoto;
    @FXML
    private JFXButton btnBuscarFoto;

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

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtApellido;
    @FXML
    private JFXTextField txtCelular;
    @FXML
    private JFXTextField txtDireccion;
    @FXML
    private JFXComboBox<String> cbEstado;

    private Delivery delivery;
    File file = null;

    boolean editar = false;

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    ObservableList<String> lista = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lista.addAll("ACTIVO", "INACTIVO");
        cbEstado.setItems(lista);
        txtNombre.requestFocus();

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (delivery == null) {

                delivery = new Delivery();
            }

            if (txtNombre.getText().isEmpty()) {

                txtNombre.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar un nombre");

                return;
            }

            if (txtApellido.getText().isEmpty()) {

                txtApellido.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar un apellido");
                return;
            }

            if (txtDireccion.getText().isEmpty()) {

                txtDireccion.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar una direccion");

                return;
            }

            if (txtCelular.getText().isEmpty()) {

                txtCelular.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar un celular");

                return;
            }

            if (cbEstado.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar un estado");
                return;

            }

            delivery.setNombre(txtNombre.getText());
            delivery.setApellido(txtApellido.getText());
            delivery.setDireccion(txtDireccion.getText());
            delivery.setCelular(txtCelular.getText());
            delivery.setEstado(cbEstado.getSelectionModel().getSelectedItem());
            delivery.setFecha(new Date());

            ManejoDelivery.getInstancia().salvar(delivery);
            ClaseUtil.mensaje("Delivery registrado Exitosamente");

            limpiar();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    public void llenarCampo() {

        txtNombre.setText(getDelivery().getNombre());
        txtApellido.setText(getDelivery().getApellido());
        txtDireccion.setText(getDelivery().getDireccion());
        txtCelular.setText(getDelivery().getCelular());
        cbEstado.getSelectionModel().select(delivery.getEstado());

        byte byteImage[] = null;

        // obtener la columna imagen, luego el arreglo de bytes 
        byteImage = delivery.getFoto();

        // crear el Image y mostrarlo en el ImageView
        Image img = new Image(new ByteArrayInputStream(byteImage));

//            Image image = SwingFXUtils.toFXImage(javax.imageio.ImageIO.read(blob.getBinaryStream()), null);
//            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        imgFoto.setImage(img);

    }

    private void limpiar() {

        txtNombre.clear();
        txtApellido.clear();
        txtDireccion.clear();
        txtCelular.clear();
        cbEstado.getSelectionModel().clearSelection();
        imgFoto=null;
    }

    @FXML
    private void btnBuscarFotoActionEvent(ActionEvent event) {

//        final FileChooser fileChooser = new FileChooser();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home")));

        fileChooser.getExtensionFilters().addAll(
                //  new ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        //new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
        //              ,  new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {

            file = selectedFile;

            try {

                System.out.println("Paren paht " + file.getParentFile() + "Paren paht " + file.getCanonicalFile());

                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imgFoto.setImage(image);

                //Procedimiento para guardar uma imagen en la base de datos 
//                    file = new File("C:\\NetBeansProjects\\javafx\\SistemaCompraVentas\\src\\imagen\\tanque origen200.png");
                byte[] bFile = new byte[(int) file.length()];

                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(bFile);
                fileInputStream.close();

                //Asignar imagen para la base de datos 
                delivery.setFoto(bFile);

            } catch (IOException ex) {

                Logger.getLogger(DeliveryController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
