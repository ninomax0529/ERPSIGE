/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.gps;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import manejo.contrato.ManejoSimCard;
import modelo.ContratoDeServicio;
import modelo.RegistroDeSim;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroDeSimController implements Initializable {

    @FXML
    private JFXCheckBox chDuplicado;
    @FXML
    private JFXComboBox<String> cbEmpresa;

    /**
     * @return the registroSim
     */
    public RegistroDeSim getRegistroSim() {
        return registroSim;
    }

    /**
     * @param registroSim the registroSim to set
     */
    public void setRegistroSim(RegistroDeSim registroSim) {
        this.registroSim = registroSim;
    }

    @FXML
    private JFXRadioButton rbDisponibleSi;
    @FXML
    private JFXRadioButton rbDisponibleNo;
    @FXML
    private JFXTextField txtNumero;
    @FXML
    private JFXDatePicker dpFechaCompra;

    /**
     * @return the cargo
     */
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    private RegistroDeSim registroSim;
    boolean editar;
    final ToggleGroup group = new ToggleGroup();

    ObservableList<String> listaEmpresa = FXCollections.observableArrayList();

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

        rbDisponibleNo.setToggleGroup(group);
        rbDisponibleSi.setToggleGroup(group);
        inicializarCombox();

//        MaskFormatter formatter = new MaskFormatter(txtNumero);
//        formatter.setMask(MaskFormatter.CPF);
//        formatter.showMask();
    }

    private void inicializarCombox() {

        listaEmpresa.addAll("CLARO", "ALTICE", "VIVA");

        cbEmpresa.setItems(listaEmpresa);
//     
//        cbEmpresa.setConverter(new StringConverter<String>() {
//
//            @Override
//            public String toString(Instalador instalador) {
//                return instalador.getNombre();
//            }
//
//            @Override
//            public Instalador fromString(String string) {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//        });

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (txtNumero.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar un numero");
                txtNumero.requestFocus();
                return;
            }

            ContratoDeServicio cont = ManejoSimCard.getInstancia().estaDisponibleEsteSim(txtNumero.getText().trim());

            if (cont != null) {

                ClaseUtil.mensaje(" Este numero de Sim esta habilitado en el contrato numero " + cont.getNumero()
                        + " \n  del cliente  " + cont.getNombreCliente());
                return;
            }

            Boolean existe = ManejoSimCard.getInstancia().existeNumeroDeSim(txtNumero.getText().trim());

            if (existe == true && editar == false) {
                ClaseUtil.mensaje("Este numero de Sim ya existe ");
                txtNumero.requestFocus();
                return;
            }

            if (dpFechaCompra.getValue() == null) {
                ClaseUtil.mensaje("Tiene que seleccionar una fecha ");
                dpFechaCompra.requestFocus();
                return;
            }

            if (rbDisponibleNo.isSelected() == false && rbDisponibleSi.isSelected() == false) {
                ClaseUtil.mensaje("Tiene que seleccionar si esta disponible ");

                return;
            }

            if (cbEmpresa.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que SELECCIONAR unA empresa de servicio  ");
                cbEmpresa.requestFocus();
                return;
            }

            if (!editar) {

                setRegistroSim(new RegistroDeSim());
            }

            Boolean disponible = rbDisponibleSi.isSelected() ? true : false;
            getRegistroSim().setNumero(txtNumero.getText());
            getRegistroSim().setEmpresa(cbEmpresa.getSelectionModel().getSelectedItem());
            getRegistroSim().setDisponible(disponible);
            getRegistroSim().setFechaRegistro(new Date());
            getRegistroSim().setFechaDeCompra(ClaseUtil.asDate(dpFechaCompra.getValue()));
            getRegistroSim().setUsuario(VariablesGlobales.USUARIO.getCodigo());

            if (chDuplicado.isSelected()) {

                getRegistroSim().setDuplicado(true);

                if (getRegistroSim().getFechaParaDuplicado() == null) {
                    getRegistroSim().setFechaParaDuplicado(new Date());
                }

            } else {

                getRegistroSim().setDuplicado(false);

                getRegistroSim().setFechaParaDuplicado(null);
            }

            if (editar) {

                ManejoSimCard.getInstancia().actualizar(getRegistroSim());

            } else {

                getRegistroSim().setFechaRegistro(new Date());
                ManejoSimCard.getInstancia().salvar(getRegistroSim());
            }

            txtNumero.clear();
            cbEmpresa.getSelectionModel().clearSelection();
            dpFechaCompra.setValue(null);
            rbDisponibleNo.setSelected(false);
            rbDisponibleSi.setSelected(false);
            editar = false;
            registroSim = new RegistroDeSim();
            txtNumero.requestFocus();
            ClaseUtil.mensaje("Sim registrado correctamente ");

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error registrado el Sim ");
            e.printStackTrace();
        }

    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();
    }

    private void limpiar() {

        cbEmpresa.getSelectionModel().clearSelection();
        txtNumero.clear();

    }

    public void llenarCampo() {

        txtNumero.setText(getRegistroSim().getNumero());
        cbEmpresa.setValue(getRegistroSim().getEmpresa());
        chDuplicado.setSelected(getRegistroSim().getDuplicado());
        dpFechaCompra.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getRegistroSim().getFechaDeCompra()));

    }

    @FXML
    private void rbDisponibleSiActionEvent(ActionEvent event) {
    }

    @FXML
    private void rbDisponibleNoActionEvent(ActionEvent event) {
    }

    @FXML
    private void chDuplicadoActionEvent(ActionEvent event) {

    }

    @FXML
    private void cbEmpresaActionEvent(ActionEvent event) {
    }
}
