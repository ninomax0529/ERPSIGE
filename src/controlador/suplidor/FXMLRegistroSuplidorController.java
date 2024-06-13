/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.suplidor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.suplidor.PlazoDao;
import manejo.suplidor.SuplidorDao;
import manejo.suplidor.TipoSuplidorDao;
import modelo.Plazo;
import modelo.Suplidor;
import modelo.TipoSuplidor;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class FXMLRegistroSuplidorController implements Initializable {

    @FXML
    private JFXTextField txtTelefonoContacto;
    @FXML
    private JFXButton btnCerrar;

    /**
     * @return the editar
     */
    public Boolean getEditar() {
        return editar;
    }

    /**
     * @param editar the editar to set
     */
    public void setEditar(Boolean editar) {
        this.editar = editar;
    }

    /**
     * @return the suplidor
     */
    public Suplidor getSuplidor() {
        return suplidor;
    }

    /**
     * @param suplidor the suplidor to set
     */
    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }

    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtRnc;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private JFXTextField txtContacto;
    @FXML
    private JFXTextField txtDireccion;
    @FXML
    private Button btnGuardar;
    @FXML
    private JFXComboBox<Plazo> cbPlazo;
    @FXML
    private JFXTextField txtCelular;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXComboBox<TipoSuplidor> cbTipoSuplidor;

    ObservableList<Plazo> listaPlazo = FXCollections.observableArrayList();

    ObservableList<TipoSuplidor> listaTipoSuplidor = FXCollections.observableArrayList();

    private Suplidor suplidor;

    private Boolean editar = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarCombox();

        dpFecha.setValue(LocalDate.now());

    }

    private void inicializarCombox() {

        listaPlazo.addAll(PlazoDao.getInstancia().getListaPlazo());
        listaTipoSuplidor.addAll(TipoSuplidorDao.getInstancia().getListaTipoSuplidor());
        cbPlazo.setConverter(new StringConverter<Plazo>() {

            @Override
            public String toString(Plazo plazo) {
                return plazo.getDescripcion();
            }

            @Override
            public Plazo fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoSuplidor.setConverter(new StringConverter<TipoSuplidor>() {

            @Override
            public String toString(TipoSuplidor tipoSuplidor) {
                return tipoSuplidor.getNombre();
            }

            @Override
            public TipoSuplidor fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbPlazo.setItems(listaPlazo);
        cbTipoSuplidor.setItems(listaTipoSuplidor);

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (getEditar() == false) {
                setSuplidor(new Suplidor());
            }

            if (txtNombre.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar el nombre ");
                txtNombre.requestFocus();
                return;
            }

            if (cbPlazo.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que Seleccionar los dias de Plazo");
                return;
            }

            if (cbTipoSuplidor.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que Seleccionar El tipo de Suplidor ");
                return;
            }

            getSuplidor().setDescripcion(txtNombre.getText());
            getSuplidor().setRnc(txtRnc.getText());
            getSuplidor().setTelefono(txtTelefono.getText());
            getSuplidor().setTelefonoDeContacto(txtTelefonoContacto.getText());
            getSuplidor().setDireccion(txtDireccion.getText());
            getSuplidor().setContacto(txtContacto.getText());
            getSuplidor().setPlazo(cbPlazo.getSelectionModel().getSelectedItem());
            getSuplidor().setEmail(txtEmail.getText());
            getSuplidor().setCelular(txtCelular.getText());
            getSuplidor().setTipoSuplidor(cbTipoSuplidor.getSelectionModel().getSelectedItem());
            getSuplidor().setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            if (getEditar() == false) {
                SuplidorDao.getInstancia().salvar(getSuplidor());
            } else {
                SuplidorDao.getInstancia().actualizar(getSuplidor());
            }

            if (editar) {
                ClaseUtil.mensaje(" Resgistro actualizado exitosamente ");
            } else {
                ClaseUtil.mensaje(" Resgistro guardado exitosamente ");
            }

//            limpiar();
            setEditar((Boolean) false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void limpiar() {

        txtContacto.clear();
        txtDireccion.clear();
        txtNombre.clear();
        txtRnc.clear();
        txtTelefono.clear();
        txtCelular.clear();
        txtEmail.clear();
        txtTelefonoContacto.clear();
        cbPlazo.getSelectionModel().clearSelection();
        cbTipoSuplidor.getSelectionModel().clearSelection();

    }

    public void llenarCampo() {

        setEditar((Boolean) true);

        txtContacto.setText(getSuplidor().getContacto());
        txtDireccion.setText(getSuplidor().getDireccion());
        txtNombre.setText(getSuplidor().getDescripcion());
        txtRnc.setText(getSuplidor().getRnc());
        txtTelefono.setText(getSuplidor().getTelefono());
        txtTelefonoContacto.setText(getSuplidor().getTelefonoDeContacto());
        txtCelular.setText(getSuplidor().getCelular());
        txtEmail.setText(getSuplidor().getEmail());
        cbPlazo.getSelectionModel().select(getSuplidor().getPlazo());
        cbTipoSuplidor.getSelectionModel().select(getSuplidor().getTipoSuplidor());

    }

    private void btNuevoActionEvent(ActionEvent event) {

        setSuplidor(new Suplidor());
        limpiar();
    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();
    }

}
