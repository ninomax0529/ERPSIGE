/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.descuento;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import manejo.descuento.ManejoDescuentoPorUsuario;
import modelo.DescuentoPorUsuario;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class UsuarioDescuentoController implements Initializable {

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

    public DescuentoPorUsuario getDescuentoPorUsuario() {
        return descuentoPorUsuario;
    }

    public void setDescuentoPorUsuario(DescuentoPorUsuario descuentoPorUsuario) {
        this.descuentoPorUsuario = descuentoPorUsuario;
    }

    DescuentoPorUsuario descuentoPorUsuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        txtClave.setOnAction(e->{
        
            validarUsuario();
        });
        
    }

    @FXML
    private void btnLoginActionEvent(ActionEvent event) {

        
        try {
            
            validarUsuario();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnCancelarActinEvent(ActionEvent event) {

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        setDescuentoPorUsuario(null);
        stage.close();
    }

    private void validarUsuario() {

        try {

            if (txtNombre.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar un usuario");
                txtNombre.requestFocus();
                return;
            }

            if (txtClave.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar una clave");
                txtClave.requestFocus();
                return;
            }

            DescuentoPorUsuario desc = ManejoDescuentoPorUsuario.getInstancia()
                    .getDescuentoPorUsuario(txtNombre.getText(), txtClave.getText());

            if (desc == null) {
                ClaseUtil.mensaje("Usuario o Clave invalido");
                return;
            }

            setDescuentoPorUsuario(desc);

            Stage stage = (Stage) btnLogin.getScene().getWindow();

            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
