/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.teclado;

import com.jfoenix.controls.JFXTextArea;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javax.annotation.PostConstruct;
import modelo.Articulo;
import utiles.ClaseUtil;
import utiles.FormatNum;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class TecladoDigitalGeneralController implements Initializable {

    @FXML
    private Button btnSalir;

    /**
     * @return the lbUnidadDespacho
     */
    public Label getLbUnidadDespacho() {
        return lbUnidadDespacho;
    }

    /**
     * @param lbUnidadDespacho the lbUnidadDespacho to set
     */
    public void setLbUnidadDespacho(Label lbUnidadDespacho) {
        this.lbUnidadDespacho = lbUnidadDespacho;
    }

    /**
     * @return the unidadDespacho
     */
    public int getUnidadDespacho() {
        return unidadDespacho;
    }

    /**
     * @param unidadDespacho the unidadDespacho to set
     */
    public void setUnidadDespacho(int unidadDespacho) {
        this.unidadDespacho = unidadDespacho;
    }

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn3;
    @FXML
    private Button btn6;
    @FXML
    private Button btn9;
    @FXML
    private TextField txtCAtidad;
    @FXML
    private Button btnLimpiar;
    @FXML
    private ImageView imgArt;
    @FXML
    private JFXTextArea lbNombreArt;

    @FXML
    private Label lbExistenciaArt;
    Articulo articulo;
    @FXML
    private Label lbArtPrecio;
    @FXML
    private Label lbUnidad;
    @FXML
    private Label lbCodigo;
    @FXML
    private Button btnUnidad;
    @FXML
    private Button btnPesos;
    @FXML
    private Label lbUnidadDespacho;
    private int unidadDespacho = 1;//uoo es Unidad despacho en unidad de medidas por defecto unidad ,2 es en pesos

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public TextField getTxtCAtidad() {
        return txtCAtidad;
    }

    public void setTxtCAtidad(TextField txtCAtidad) {
        this.txtCAtidad = txtCAtidad;
    }
    @FXML
    private Button btnPunto;
    @FXML
    private Button btn0;
    @FXML
    private Button btnAgregar;

    Integer cantidad = 0;
    String numStr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtCAtidad.setOnKeyReleased((event) -> {

            if (event.getCode() == KeyCode.ENTER) {

                try {

                    if (txtCAtidad.getText().isEmpty()) {

                        ClaseUtil.mensaje("Tiene que digitar una cantidad");

                        return;
                    }

                    Double cantidadArt = Double.parseDouble(txtCAtidad.getText());

                    if (cantidadArt <= 0) {

                        ClaseUtil.mensaje("La cantidad no puede ser igual a cero");
                        txtCAtidad.clear();

                        return;
                    }

                    Stage stage = (Stage) this.btnAgregar.getScene().getWindow();
                    stage.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });
    }

    @PostConstruct
    public void afterInitialize() {
        txtCAtidad.requestFocus();
    }

    @FXML
    private void btn1ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 1;
        txtCAtidad.setText(numStr);

    }

    @FXML
    private void btn2ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 2;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn4ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 4;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn5ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 5;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn7ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 7;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn8ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 8;
        txtCAtidad.setText(numStr);

    }

    @FXML
    private void btn3ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 3;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn6ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 6;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn9ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 9;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {

        if (txtCAtidad.getText().isEmpty()) {

            ClaseUtil.mensaje("Tiene que digitar una cantidad");

            return;
        }

        Double cantidadArt = Double.parseDouble(txtCAtidad.getText()),
                existencia=FormatNum.FormatearDouble(getArticulo().getExistencia(),2);

        if (cantidadArt <= 0) {

            ClaseUtil.mensaje("La cantidad no puede ser igual a cero");
            txtCAtidad.clear();

            return;
        }

//        if (true) {
//
//            cantidadArt = FormatNum
//                    .FormatearDouble(cantidadArt / getArticulo()
//                            .getPrecioVenta1(), 4);
//            System.out.println("Cantidad en peso " + cantidadArt);
//        }
//
//        if (existencia < cantidadArt) {
//
//            ClaseUtil.mensaje("La cantidad a Despachar ** " + cantidadArt
//                    + "  no puede ser mayor que la existencia ,Existencia igual a " + getArticulo().getExistencia());
//
//            return;
//        }

//        agregarArticulo();
        Stage stage = (Stage) this.btnAgregar.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void btnPuntoActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + ".";
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn0ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + "0";
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btnLimpiarActionEvent(ActionEvent event) {
        txtCAtidad.clear();
    }

    public void llenarCampo() {

        Double existencia = FormatNum.FormatearDouble(getArticulo().getExistencia(), 2);
        File imageFile;
        lbCodigo.setText(getArticulo().getCodigo().toString());
        lbNombreArt.setText(getArticulo().getNombre());
        lbUnidad.setText(getArticulo().getUnidadSalida().getDescripcion());
        lbExistenciaArt.setText(existencia.toString());
        lbArtPrecio.setText(Double.toString(getArticulo().getPrecioVenta1()));
        lbUnidadDespacho.setText(getArticulo().getUnidadSalida().getDescripcion());

        if (getArticulo().getRutaImg() == null) {

            imageFile = new File("foto/img_articulo.jpg");

        } else {

            imageFile = new File(getArticulo().getRutaImg());
        }

        if (getArticulo().getParaDetalle() == false) {
            btnPesos.setDisable(true);
        } else {
            btnPesos.setDisable(false);
        }

//        File imageFile = new File(getArticulo().getRutaImg());
//                    img = new ImageView(new Image(imageFile.toURI().toString(), 150, 130, false, false));
        imgArt.setImage(new Image(imageFile.toURI().toString(), 250, 200, false, false));
        imgArt.setFitWidth(250);
        imgArt.setFitHeight(200);

    }

    @FXML
    private void btnUnidadActionEvent(ActionEvent event) {
        setUnidadDespacho(1);// en unidad de  medidas
        lbUnidadDespacho.setText(lbUnidad.getText());
    }

    @FXML
    private void btnPesosActionEvent(ActionEvent event) {
        setUnidadDespacho(2);//Unidad en pesos;
        lbUnidadDespacho.setText("Pesos");
    }

    @FXML
    private void btnSalirActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnSalir.getScene().getWindow();

        stage.close();
    }

}
