/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.articulo.ManejoCategoria;
import manejo.articulo.ManejoSubCategoria;
import modelo.Categoria;
import modelo.SubCategoria;
import modelo.TipoSolicitud;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroSubCategoriaController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNombre;

    String mensaje;
    SubCategoria subCategoria;

    ObservableList<Categoria> lista = FXCollections.observableArrayList();

    @FXML
    private JFXComboBox<Categoria> cbCategoria;

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
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
        inicializarCombox();
    }

    private void inicializarCombox() {

        lista.addAll(ManejoCategoria.getInstancia().getCategoria());

        cbCategoria.setConverter(new StringConverter<Categoria>() {

            @Override
            public String toString(Categoria tipoSolicitud) {
                return tipoSolicitud.getNombre();
            }

            @Override
            public Categoria fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbCategoria.setItems(lista);

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (validarCampo() == false) {

                ClaseUtil.mensaje(mensaje);
                return;
            }

            subCategoria.setNombre(txtNombre.getText());
            subCategoria.setDescripcion(txtNombre.getText());
            subCategoria.setFechaCreacion(new Date());
            subCategoria.setCategoria(cbCategoria.getSelectionModel().getSelectedItem());

            if (editar) {
                ManejoSubCategoria.getInstancia().actualizar(subCategoria);
            } else {
                ManejoSubCategoria.getInstancia().salvar(subCategoria);
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

            mensaje = "Tiene que selecionar una categoria";
            cbCategoria.requestFocus();
            return false;

        }

        if (txtNombre.getText().isEmpty()) {

            mensaje = "Tiene que digital un Nombre";
            txtNombre.requestFocus();
            return false;

        }

        return true;
    }

    private void nuevo() {

        if (editar == false) {

            subCategoria = new SubCategoria();
            limpiar();
        }

    }

    private void limpiar() {

        txtNombre.clear();
        cbCategoria.getSelectionModel().clearSelection();

    }

    public void llenarCampo() {

        txtNombre.setText(subCategoria.getDescripcion());
        cbCategoria.getSelectionModel().select(subCategoria.getCategoria());
        cbCategoria.getSelectionModel().selectFirst();

    }

}
