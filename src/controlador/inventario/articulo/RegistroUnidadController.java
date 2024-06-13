/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import manejo.unidad.ManejoUnidad;
import modelo.Unidad;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroUnidadController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNombre;

    String mensaje;
    Unidad unidad;
    @FXML
    private JFXTextField txtAbreviatura;

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }
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
        unidad = new Unidad();
    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (validarCampo() == false) {

                ClaseUtil.mensaje(mensaje);
                return;
            }

            unidad.setDescripcion(txtNombre.getText());
            unidad.setAbreviatura(txtAbreviatura.getText().isEmpty() ? "na" : txtAbreviatura.getText());
            unidad.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            if (editar) {
                ManejoUnidad.getInstancia().actualizar(unidad);
            } else {
                ManejoUnidad.getInstancia().salvar(unidad);
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

        return true;
    }

    private void nuevo() {

        if (editar == false) {

            unidad = new Unidad();
            limpiar();
        }

    }

    private void limpiar() {

        txtNombre.clear();
        txtAbreviatura.clear();

    }

    public void llenarCampo() {

        txtNombre.setText(getUnidad().getDescripcion());
        txtAbreviatura.setText(getUnidad().getAbreviatura());

    }

}
