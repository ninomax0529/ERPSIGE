/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.secuenciaDocumento;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.documento.ManejoTipodocumento;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import manejo.unidadDeNegocio.ManejoUnidadDeNegocio;
import modelo.SecuenciaDocumento;
import modelo.TipoDocumento;
import modelo.UnidadDeNegocio;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLRegistroSecuenciaDocumentoController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXComboBox<UnidadDeNegocio> cbUnidadDeNegocio;
    @FXML
    private JFXComboBox<TipoDocumento> cbTipoDocumento;
    @FXML
    private TextField txtPrefijo;
    @FXML
    private TextField txtsufijo;
    @FXML
    private TextField txtNumero;

    SecuenciaDocumento secuenciaDocumento;

    ObservableList<UnidadDeNegocio> listaUniDeNegocio = FXCollections.observableArrayList();
    ObservableList<TipoDocumento> listaTipoDocumento = FXCollections.observableArrayList();

    public SecuenciaDocumento getSecuenciaDocumento() {
        return secuenciaDocumento;
    }

    public void setSecuenciaDocumento(SecuenciaDocumento secuenciaDocumento) {
        this.secuenciaDocumento = secuenciaDocumento;
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

        inicializarCombox();

        txtsufijo.setOnKeyReleased(value -> {

            txtNumero.clear();
            txtNumero.setText(txtPrefijo.getText() + txtsufijo.getText());

        });
    }

    private void inicializarCombox() {

        listaTipoDocumento.addAll(ManejoTipodocumento.getInstancia().getLista());
        listaUniDeNegocio.addAll(ManejoUnidadDeNegocio.getInstancia().getLista());

        cbTipoDocumento.setItems(listaTipoDocumento);
        cbUnidadDeNegocio.setItems(listaUniDeNegocio);

        cbTipoDocumento.setConverter(new StringConverter<TipoDocumento>() {

            @Override
            public String toString(TipoDocumento tipoDoc) {
                return tipoDoc.getNombre();
            }

            @Override
            public TipoDocumento fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbUnidadDeNegocio.setConverter(new StringConverter<UnidadDeNegocio>() {

            @Override
            public String toString(UnidadDeNegocio unidadNegocio) {
                return unidadNegocio.getNombre();
            }

            @Override
            public UnidadDeNegocio fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            System.out.println("editar " + editar + " secuenciaDocumento  " + secuenciaDocumento);

            if (secuenciaDocumento == null && !editar) {

                System.out.println("unidadDeNegocio == null && !editar");
                secuenciaDocumento = new SecuenciaDocumento();
            }

            if (cbUnidadDeNegocio.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar una unidad de negocio ");
                return;

            }

            if (cbTipoDocumento.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar un tipo de documento ");
                return;

            }

            if (txtsufijo.getText().isEmpty()) {

                txtsufijo.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar un nombre");

                return;
            }

            if (txtNumero.getText().isEmpty()) {

                txtNumero.requestFocus();
                ClaseUtil.mensaje("El numero no esta completo ");
                return;
            }

            secuenciaDocumento.setUsuario(VariablesGlobales.USUARIO.getCodigo());

            secuenciaDocumento.setNumero(Integer.parseInt(txtNumero.getText().trim()));
            secuenciaDocumento.setTipoDocumento(cbTipoDocumento.getSelectionModel().getSelectedItem());
            secuenciaDocumento.setUnidadDeNegocio(cbUnidadDeNegocio.getSelectionModel().getSelectedItem());
            secuenciaDocumento.setFechaRegistro(new Date());
            secuenciaDocumento.setPrefijo(txtPrefijo.getText());
            secuenciaDocumento.setSufijo(txtsufijo.getText());
            boolean existe = ManejoSecuenciaDocumento.getInstancia().existeSecuencia(secuenciaDocumento);

            if (existe && editar == false) {

                ClaseUtil.mensaje("La secuencia ya existe para esta unidad de negocio ");
                return;
            }

            if (editar) {

                ManejoSecuenciaDocumento.getInstancia().actualizar(secuenciaDocumento);

            } else {

                ManejoSecuenciaDocumento.getInstancia().salvar(secuenciaDocumento);
            }

            ClaseUtil.mensaje("Secuencia  registrado Exitosamente");

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

    private void limpiar() {

        txtNumero.clear();
        txtPrefijo.clear();
        txtsufijo.clear();

        cbTipoDocumento.getSelectionModel().clearSelection();
        cbUnidadDeNegocio.getSelectionModel().clearSelection();
        editar = false;
        secuenciaDocumento = null;

    }

    public void llenarCampo() {

        txtNumero.setText(Integer.toString(getSecuenciaDocumento().getNumero()));
        txtsufijo.setText(getSecuenciaDocumento().getSufijo());
        txtPrefijo.setText(getSecuenciaDocumento().getPrefijo());

        cbUnidadDeNegocio.getSelectionModel().select(getSecuenciaDocumento().getUnidadDeNegocio());
        cbTipoDocumento.getSelectionModel().select(getSecuenciaDocumento().getTipoDocumento());
        editar = true;
    }

    @FXML
    private void cbUnidadDeNegocioActionEvent(ActionEvent event) {

        if (!(cbUnidadDeNegocio.getSelectionModel().getSelectedIndex() == -1)) {

            txtPrefijo.clear();
            txtsufijo.clear();
            txtNumero.clear();
            cbTipoDocumento.getSelectionModel().clearSelection();
            txtPrefijo.setText(Integer.toString(cbUnidadDeNegocio.getSelectionModel().getSelectedItem().getCodigo()));
        }
    }

    @FXML
    private void cbTipoDocumentoActionEvent(ActionEvent event) {

        if (!(cbTipoDocumento.getSelectionModel().getSelectedIndex() == -1)) {

            txtsufijo.clear();
            txtNumero.clear();

            Integer tipodoc = cbTipoDocumento.getSelectionModel().getSelectedItem().getCodigo();

            if (!(cbUnidadDeNegocio.getSelectionModel().getSelectedIndex() == -1)) {
                txtPrefijo.setText(Integer.toString(cbUnidadDeNegocio.getSelectionModel().getSelectedItem().getCodigo()) + tipodoc);
            }

        }
    }

}
