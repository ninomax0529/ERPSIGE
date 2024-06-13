/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.usuario;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import manejo.ManejoRol;
import manejo.unidad.ManejoUnidad;
import modelo.Rol;
import modelo.Unidad;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroRolesController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNombre;

    String mensaje;
    Rol rol;

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    @FXML
    private TextArea txtDescripcion;

    boolean editar = false;

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtNombre.requestFocus();
        rol = new Rol();
    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (validarCampo() == false) {

                ClaseUtil.mensaje(mensaje);
                return;
            }

            rol.setDescripcion(txtDescripcion.getText());
            rol.setNombre(txtNombre.getText());
            rol.setFecha(new Date());
            rol.setUsuario(VariablesGlobales.USUARIO);
            

            if (editar) {
                ManejoRol.getInstancia().actualizar(rol);
            } else {
                ManejoRol.getInstancia().salvar(rol);
            }

            editar = false;

            nuevo();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();

    }

    private Boolean validarCampo() {

        if (txtNombre.getText().isEmpty()) {

            mensaje = "Tiene que digital un Nombre";
            txtNombre.requestFocus();
            return false;

        }
        if (txtDescripcion.getText().isEmpty()) {

            mensaje = "Tiene que digital una Descripcion";
            txtDescripcion.requestFocus();
            return false;

        }

        return true;
    }

    private void nuevo() {

        if (editar == false) {

            rol = new Rol();
            limpiar();
        }

    }

    private void limpiar() {

        txtNombre.clear();
        txtDescripcion.clear();

    }

    public void llenarCampo() {

        txtNombre.setText(getRol().getNombre());
        txtDescripcion.setText(getRol().getDescripcion());

    }

}
