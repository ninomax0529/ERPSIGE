/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.unidadDeNegocio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import manejo.empresa.ManejoEmpresaOGrupo;
import manejo.unidadDeNegocio.ManejoUnidadDeNegocio;
import modelo.EmpresaOGrupo;
import modelo.UnidadDeNegocio;

import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroUnidadDeNegocioController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private JFXTextField txtEmail;
    boolean editar = false;
    UnidadDeNegocio unidadDeNegocio;
    @FXML
    private JFXComboBox<Integer> cbDiaDeFacturacion;

    ObservableList<Integer> listaDia = FXCollections.observableArrayList();
    ObservableList<Integer> listaDiaRenovacion = FXCollections.observableArrayList();
    ObservableList<Integer> listaMesRenovacion = FXCollections.observableArrayList();
    ObservableList<Integer> listaDiaAntesVencimiento = FXCollections.observableArrayList();
    ObservableList<EmpresaOGrupo> listaEmpresaOGrupos = FXCollections.observableArrayList();
    @FXML
    private JFXComboBox<Integer> cbDiaAntesVencimiento;
    @FXML
    private JFXTextField txtRnc;
    @FXML
    private JFXTextField txtPorcientoMontoGravado;
    @FXML
    private JFXTextField txtDireccion;
    @FXML
    private JFXTextField txtDescripcion;
    @FXML
    private JFXComboBox<EmpresaOGrupo> cbEmpresaOGrupo;
    @FXML
    private JFXComboBox<Integer> cbDiaDeRenovacion;
    @FXML
    private JFXComboBox<Integer> cbMesDeRenovacion;

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

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
    }

    private void inicializarCombox() {

        cbEmpresaOGrupo.setConverter(new StringConverter<EmpresaOGrupo>() {

            @Override
            public String toString(EmpresaOGrupo emp) {
                return emp.getNombre();
            }

            @Override
            public EmpresaOGrupo fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaEmpresaOGrupos.addAll(ManejoEmpresaOGrupo.getInstancia().getLista());
        cbEmpresaOGrupo.setItems(listaEmpresaOGrupos);

        for (int i = 1; i <= 31; i++) {

            listaDia.add(i);
        }

        for (int i = 1; i <= 31; i++) {

            listaDiaRenovacion.add(i);
        }

        for (int i = 1; i <= 12; i++) {

            listaMesRenovacion.add(i);
        }

        for (int i = 1; i <= 30; i++) {

            listaDiaAntesVencimiento.add(i);
        }

        cbDiaDeFacturacion.setItems(listaDia);
        cbDiaDeRenovacion.setItems(listaDiaRenovacion);
        cbMesDeRenovacion.setItems(listaMesRenovacion);
        cbDiaAntesVencimiento.setItems(listaDiaAntesVencimiento);

    }

    @FXML

    private void btnGuardarEventAction(ActionEvent event) {

        try {

            System.out.println("unidadDeNegocio " + unidadDeNegocio + " editar " + editar);

            if (cbEmpresaOGrupo.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje(" Tiene que seleccionar una empresa o grupo ");
                return;
            }

            if (unidadDeNegocio == null && !editar) {

                System.out.println("unidadDeNegocio == null && !editar");
                unidadDeNegocio = new UnidadDeNegocio();
            }

            if (txtNombre.getText().isEmpty()) {

                txtNombre.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar un nombre");

                return;
            }
            if (txtDescripcion.getText().isEmpty()) {

                txtDescripcion.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar una descripcion ");
                return;
            }

            if (txtTelefono.getText().isEmpty()) {

                txtTelefono.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar un telefono");

                return;
            }

            if (txtDireccion.getText().isEmpty()) {

                txtDireccion.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar una direccion ");

                return;
            }

            if (cbDiaAntesVencimiento.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar dias antes del vencimiento ");

                return;
            }

            if (!(cbDiaDeFacturacion.getSelectionModel().getSelectedIndex() == -1)) {

                unidadDeNegocio.setDiaDeFacturacion(cbDiaDeFacturacion.getSelectionModel().getSelectedItem());
            }

            if (!(cbDiaAntesVencimiento.getSelectionModel().getSelectedIndex() == -1)) {

                unidadDeNegocio.setDiaAntesDeVencer(cbDiaAntesVencimiento.getSelectionModel().getSelectedItem());
            }

            if (!(cbDiaDeRenovacion.getValue() == null) && !(cbMesDeRenovacion.getValue() == null)) {

                unidadDeNegocio.setDiaDeRenovacion(cbDiaDeRenovacion.getValue());
                unidadDeNegocio.setMesDeRenovacion(cbMesDeRenovacion.getValue());
            }

            unidadDeNegocio.setNombre(txtNombre.getText());
            unidadDeNegocio.setDescripcion(txtDescripcion.getText());
            unidadDeNegocio.setTelefono(txtTelefono.getText());
            unidadDeNegocio.setDireccion(txtDireccion.getText());
            unidadDeNegocio.setEmail(txtEmail.getText());
            unidadDeNegocio.setFechaRegistro(new Date());

            unidadDeNegocio.setUsuario(VariablesGlobales.USUARIO);
            unidadDeNegocio.setNombreUsuario(unidadDeNegocio.getUsuario().getNombre());
            unidadDeNegocio.setEmpresaOGrupo(cbEmpresaOGrupo.getValue());
            unidadDeNegocio.setNombreEmpresaOGrupo(unidadDeNegocio.getEmpresaOGrupo().getNombre());
            unidadDeNegocio.setRnc(txtRnc.getText().isEmpty() ? "na"
                    : txtRnc.getText()
            );

            unidadDeNegocio.setMontoGravado(Double.parseDouble((txtPorcientoMontoGravado.getText().isEmpty() ? "100"
                    : txtPorcientoMontoGravado.getText()))
            );

            if (editar) {

                ManejoUnidadDeNegocio.getInstancia().actualizar(unidadDeNegocio);
            } else {

                ManejoUnidadDeNegocio.getInstancia().salvar(unidadDeNegocio);
            }

            ClaseUtil.mensaje("Unidad  registrada Exitosamente");

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

    public void llenarCampo() {

        txtRnc.setText(getUnidadDeNegocio().getRnc());
        txtPorcientoMontoGravado.setText(getUnidadDeNegocio().getMontoGravado().toString());
        txtNombre.setText(getUnidadDeNegocio().getNombre());
        txtDescripcion.setText(getUnidadDeNegocio().getDescripcion());
        txtTelefono.setText(getUnidadDeNegocio().getTelefono());
        txtEmail.setText(getUnidadDeNegocio().getEmail());
        txtDireccion.setText(getUnidadDeNegocio().getDireccion());
        cbDiaAntesVencimiento.getSelectionModel().select(getUnidadDeNegocio().getDiaAntesDeVencer());
        cbDiaDeFacturacion.getSelectionModel().select(getUnidadDeNegocio().getDiaDeFacturacion());
        cbDiaDeRenovacion.getSelectionModel().select(getUnidadDeNegocio().getDiaDeRenovacion());
        cbMesDeRenovacion.getSelectionModel().select(getUnidadDeNegocio().getMesDeRenovacion());

        if (!(getUnidadDeNegocio().getEmpresaOGrupo() == null)) {

            cbEmpresaOGrupo.getSelectionModel().select(getUnidadDeNegocio().getEmpresaOGrupo());
        }

    }

    private void limpiar() {

        txtDescripcion.clear();
        txtDireccion.clear();
        txtNombre.clear();
        txtEmail.clear();
        txtTelefono.clear();
        txtRnc.clear();
        editar = false;
        unidadDeNegocio = null;

    }

    @FXML
    private void cbEmpresaOGrupoActionEvent(ActionEvent event) {

//        if (!(cbEmpresaOGrupo.getSelectionModel().getSelectedIndex() == -1)) {
//
//            txtRnc.setText(cbEmpresaOGrupo.getSelectionModel().getSelectedItem().getRnc());
//        }
    }

}
