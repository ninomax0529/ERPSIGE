/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import manejo.articulo.ManejoAlmacen;
import modelo.Almacen;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroAlmacenController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtUbicacion;

    String mensaje;
    Almacen almacen;
    boolean editar = false;
    @FXML
    private JFXCheckBox chParaLaVenta;

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nuevo();
        txtNombre.requestFocus();
    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (validarCampo() == false) {

                ClaseUtil.mensaje(mensaje);
                return;
            }

            almacen.setNombre(txtNombre.getText());
            almacen.setDespacho(chParaLaVenta.isSelected());

            almacen.setUbicacion(txtUbicacion.getText());
            almacen.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            if (editar) {
                ManejoAlmacen.getInstancia().actualizar(almacen);
            } else {
                ManejoAlmacen.getInstancia().salvar(almacen);
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

        if (txtUbicacion.getText().isEmpty()) {
            mensaje = "Tiene que digital una Ubicacion";

            txtUbicacion.requestFocus();
            return false;
        }

        return true;
    }

    private void nuevo() {

        if (editar == false) {

            almacen = new Almacen();
            limpiar();
        }

    }

    private void limpiar() {

        txtNombre.clear();
        txtUbicacion.clear();
    }

    public void llenarCampo() {

        txtNombre.setText(getAlmacen().getNombre());
        txtUbicacion.setText(getAlmacen().getUbicacion());
        chParaLaVenta.setSelected(getAlmacen().getDespacho());
    }

}
