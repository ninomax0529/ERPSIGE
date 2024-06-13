/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.direccion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.direccion.ManejoDireccion;
import manejo.direccion.ManejorRegion;
import modelo.Direccion;
import modelo.Region;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLRegistroDireccionController implements Initializable {

    /**
     * @return the direccion
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @FXML
    private JFXComboBox<Region> cbRegion;
    @FXML
    private JFXTextField txtProvincia;
    @FXML
    private JFXTextField txtMunicipio;
    @FXML
    private JFXTextField txtCiudad;
    @FXML
    private JFXTextField txtSector;
    @FXML
    private JFXTextField txtDireccion;
    private Direccion direccion;

    boolean editar = false;

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    ObservableList<Direccion> lista = FXCollections.observableArrayList();
    ObservableList<Region> listaRegion = FXCollections.observableArrayList();

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarCombox();

        if (ManejoDireccion.getInstancia().getLista().size()>0) {
            setDireccion(ManejoDireccion.getInstancia().getLista().get(0));
            llenarCampo();

        }

    }

    private void inicializarCombox() {

        listaRegion.addAll(ManejorRegion.getInstancia().getLista());
        cbRegion.setItems(listaRegion);

        cbRegion.setConverter(new StringConverter<Region>() {

            @Override
            public String toString(Region region) {
                return region.getNombre();
            }

            @Override
            public Region fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

    }

    private void btnLimpiarActionEvent(ActionEvent event) {

        limpiar();
    }

    private void limpiar() {

        txtCiudad.clear();
        txtDireccion.clear();
        txtMunicipio.clear();
        txtSector.clear();
        txtProvincia.clear();
        cbRegion.getSelectionModel().clearSelection();

    }

    public void llenarCampo() {

        Region region = ManejorRegion.getInstancia().getRegion(1);
        txtCiudad.setText(getDireccion().getCiudad());
        txtDireccion.setText(getDireccion().getDireccion());
        txtMunicipio.setText(getDireccion().getMunicipio());
        txtSector.setText(getDireccion().getSector());
        txtProvincia.setText(getDireccion().getProvincia());
        cbRegion.setValue(region);

        if (editar == false) {
            direccion = null;
        }

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (direccion == null) {

                direccion = new Direccion();
            }

            if (cbRegion.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar una region");
                return;

            }

            if (txtProvincia.getText().isEmpty()) {

                txtProvincia.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar una provincia");

                return;
            }
            if (txtMunicipio.getText().isEmpty()) {

                txtMunicipio.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar un municipio");
                return;
            }

            if (txtCiudad.getText().isEmpty()) {

                txtCiudad.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar una ciudad");

                return;
            }

            if (txtSector.getText().isEmpty()) {

                txtSector.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar un sector");

                return;
            }

            if (txtDireccion.getText().isEmpty()) {

                txtDireccion.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar una direccion");

                return;
            }

            direccion.setRegion(cbRegion.getSelectionModel().getSelectedItem().getNombre());
            direccion.setProvincia(txtProvincia.getText());
            direccion.setMunicipio(txtMunicipio.getText());
            direccion.setCiudad(txtCiudad.getText());
            direccion.setSector(txtSector.getText());
            direccion.setDireccion(txtDireccion.getText());
            direccion.setPais("REPUBLICA DOMINICANA");

            ManejoDireccion.getInstancia().salvar(direccion);

            ClaseUtil.mensaje("Direccion registrado Exitosamente");

            limpiar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) btnCerrar.getScene().getWindow();

        stage.close();

    }
}
