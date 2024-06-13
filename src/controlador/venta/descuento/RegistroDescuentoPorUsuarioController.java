/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.descuento;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controlador.configuracion.usuario.UsuarioController;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manejo.descuento.ManejoDescuentoPorUsuario;
import modelo.DescuentoPorUsuario;
import modelo.Usuario;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroDescuentoPorUsuarioController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtMinimo;
    @FXML
    private JFXButton btnBuscarUsuario;
    @FXML
    private JFXTextField txtMaximo;

    DescuentoPorUsuario descuentoPorUsuario;
    Boolean editar = false;
    @FXML
    private JFXTextField txtClave;

    public Boolean getEditar() {
        return editar;
    }

    public void setEditar(Boolean editar) {
        this.editar = editar;
    }

    public DescuentoPorUsuario getDescuentoPorUsuario() {
        return descuentoPorUsuario;
    }

    public void setDescuentoPorUsuario(DescuentoPorUsuario descuentoPorUsuario) {
        this.descuentoPorUsuario = descuentoPorUsuario;
    }
    Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nuevo();
    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (txtNombre.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que seleccionar un usuario");
                return;
            }

            if (txtClave.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar una clave ");
                txtClave.requestFocus();
                return;
            }

            if (txtMinimo.getText().isEmpty()) {
                ClaseUtil.mensaje("El descuento minimo esta vacio");
                txtMinimo.requestFocus();
                return;
            }

            if (txtMaximo.getText().isEmpty()) {
                ClaseUtil.mensaje("El descuento maximo esta vacio");
                txtMaximo.requestFocus();
                return;
            }

            descuentoPorUsuario.setUsuario(getUsuario());
            descuentoPorUsuario.setNombreUsuario(getUsuario().getUsuario());
            descuentoPorUsuario.setMinimo(Double.parseDouble(txtMinimo.getText().isEmpty() ? "0" : txtMinimo.getText()));
            descuentoPorUsuario.setMaximo(Double.parseDouble(txtMaximo.getText().isEmpty() ? "0" : txtMaximo.getText()));
            descuentoPorUsuario.setFecha(new Date());
            descuentoPorUsuario.setHabilitado(true);
            descuentoPorUsuario.setClave(txtClave.getText());

            ManejoDescuentoPorUsuario.getInstancia().salvar(descuentoPorUsuario);
            limpiar();
            nuevo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    private void nuevo() {

        descuentoPorUsuario = new DescuentoPorUsuario();

    }

    private void limpiar() {

        txtNombre.clear();
        txtMaximo.clear();
        txtMinimo.clear();
    }

    @FXML
    private void btnBuscarUsuarioActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/configuracion/usuario/Usuario.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        System.out.println("ESPERANDOOO!!!");

        UsuarioController usuarioControler = loader.getController();

        if (!(usuarioControler.getUsuario() == null)) {

            txtNombre.setText(usuarioControler.getUsuario().getUsuario());

            setUsuario(usuarioControler.getUsuario());

        }

    }

    public void llenarCampo() {

        txtNombre.setText(getDescuentoPorUsuario().getUsuario().getNombre());
        txtMinimo.setText(Double.toString(getDescuentoPorUsuario().getMinimo()));
        txtMaximo.setText(Double.toString(getDescuentoPorUsuario().getMaximo()));
        setUsuario(getDescuentoPorUsuario().getUsuario());

    }
}
