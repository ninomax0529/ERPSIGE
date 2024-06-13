/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import manejo.articulo.ManejoCategoria;
import modelo.Categoria;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroCategoriaController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNombre;

    String mensaje;
    Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
        nuevo();
    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (validarCampo() == false) {

                ClaseUtil.mensaje(mensaje);
                return;
            }

            categoria.setNombre(txtNombre.getText());
            categoria.setDescripcion(txtNombre.getText());
            categoria.setFechaCreacion(new Date());

            if (editar) {
                ManejoCategoria.getInstancia().actualizar(categoria);
            } else {
                ManejoCategoria.getInstancia().salvar(categoria);
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

            categoria = new Categoria();
            limpiar();
        }

    }

    private void limpiar() {

        txtNombre.clear();

    }

    public void llenarCampo() {

        txtNombre.setText(getCategoria().getDescripcion());

    }

}
