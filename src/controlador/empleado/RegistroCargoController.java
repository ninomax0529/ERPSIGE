/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.empleado;

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
import manejo.ejecutivoDeVenta.ManejoCargo;
import modelo.Cargo;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroCargoController implements Initializable {

    @FXML
    private TextArea txtDescripcion;

    /**
     * @return the cargo
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtSueldo;
    @FXML
    private JFXTextField txtNombre;
    private Cargo cargo;
    boolean editar;

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
        // TODO
    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        if (txtNombre.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar un nombre");
            txtNombre.requestFocus();
            return;
        }

        if (txtSueldo.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar un sueldo ");
            txtSueldo.requestFocus();
            return;
        }

        if (txtDescripcion.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar una descripcion ");
            txtDescripcion.requestFocus();
            return;
        }

        if (!editar) {

            cargo = new Cargo();
        }

        cargo.setNombre(txtNombre.getText());
        cargo.setSueldo(Double.parseDouble(txtSueldo.getText()));
        cargo.setDescripcion(txtDescripcion.getText());

        cargo.setUsuario(VariablesGlobales.USUARIO.getCodigo());

        if (editar) {

            ManejoCargo.getInstancia().actualizar(cargo);

        } else {

            cargo.setFechaRegistro(new Date());
            ManejoCargo.getInstancia().salvar(cargo);
        }

        txtNombre.clear();
        txtDescripcion.clear();
        txtSueldo.clear();

    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();
    }

    private void limpiar() {

        txtNombre.clear();
        txtSueldo.clear();

    }

    public void llenarCampo() {

        txtNombre.setText(getCargo().getNombre());
        txtSueldo.setText(Double.toString(getCargo().getSueldo()));
        txtDescripcion.setText(getCargo().getDescripcion());

    }
}
