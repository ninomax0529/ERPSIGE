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
public class TecladoDigitalNumericoController implements Initializable {

    @FXML
    private Button btnSalir;
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
    private Button btnPunto;
    @FXML
    private Button btn0;
    @FXML
    private Button btnAgregar;

    Articulo articulo;

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

        Double cantidadArt = Double.parseDouble(txtCAtidad.getText());

        if (cantidadArt <= 0) {

            ClaseUtil.mensaje("La cantidad no puede ser igual a cero");
            txtCAtidad.clear();

            return;
        }

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

    @FXML
    private void btnSalirActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnSalir.getScene().getWindow();

        stage.close();
    }

}
