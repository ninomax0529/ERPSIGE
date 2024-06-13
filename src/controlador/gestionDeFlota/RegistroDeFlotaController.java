/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.gestionDeFlota;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import controlador.contrato.gps.BuscarSimCardController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import manejo.gestionDeFlota.ManejoRegistroDeFlota;
import modelo.RegistroDeFlota;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroDeFlotaController implements Initializable {

    @FXML
    private JFXTextField txtDescripcion;
    @FXML
    private JFXRadioButton rbNueva;
    @FXML
    private JFXRadioButton rbUsada;
    @FXML
    private JFXCheckBox chTieneGarantia;
    @FXML
    private JFXTextField txtTiempoDeGarantia;
    @FXML
    private JFXDatePicker dpFechaDesde;
    @FXML
    private JFXDatePicker dpFechaHasta;
    @FXML
    private JFXTextField txtPrecio;
    @FXML
    private JFXTextField txtTelefonica;

    /**
     * @return the registroDeFlota
     */
    public RegistroDeFlota getRegistroDeFlota() {
        return registroDeFlota;
    }

    /**
     * @param registroDeFlota the registroDeFlota to set
     */
    public void setRegistroDeFlota(RegistroDeFlota registroDeFlota) {
        this.registroDeFlota = registroDeFlota;
    }

    @FXML
    private TabPane tabPane;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtImei;
    @FXML
    private JFXTextField txtSim;
    @FXML
    private JFXButton btnBuscarSim;
    @FXML
    private JFXTextField txtMarca;
    @FXML
    private JFXTextField txtModelo;
    @FXML
    private TextArea txtObservacion;

    private RegistroDeFlota registroDeFlota;

    /**
     * @return the cargo
     */
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    Integer tiempoMeses = 0;
    boolean editar;
    final ToggleGroup group = new ToggleGroup();

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

        rbNueva.setToggleGroup(group);
        rbUsada.setToggleGroup(group);
        dpFecha.setValue(LocalDate.now());
        dpFechaDesde.setValue(LocalDate.now());
        dpFechaHasta.setValue(LocalDate.now());
        inicializarCombox();

        txtTiempoDeGarantia.setOnKeyReleased(e -> {

            tiempoMeses = Integer.parseInt(txtTiempoDeGarantia.getText().isEmpty() ? "0"
                    : txtTiempoDeGarantia.getText());

            Date fecha = ClaseUtil.asDate(dpFechaDesde.getValue());
            fecha = ClaseUtil.FechaMesDespues(fecha, tiempoMeses, "yyyy/MM/dd");
            dpFechaHasta.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fecha));

        });

    }

    private void inicializarCombox() {

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (dpFecha.getValue() == null) {
                ClaseUtil.mensaje("Tiene que seleccionar la fecha ");
                dpFecha.requestFocus();
                return;
            }

            if (txtDescripcion.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar el nombre de la flota ");
                txtDescripcion.requestFocus();
                return;
            }

            if (txtMarca.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que la marca de la flota ");
                txtMarca.requestFocus();
                return;
            }

            if (txtModelo.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que el modelo de la flota ");
                txtModelo.requestFocus();
                return;
            }

            if (txtImei.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que el Imei de la flota ");
                txtImei.requestFocus();
                return;
            }

            if (txtTelefonica.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar el nombre de la telefonica ");
                txtTelefonica.requestFocus();
                return;
            }

            if (getRegistroDeFlota().getTieneGarantia() && txtTiempoDeGarantia.getText().isEmpty()) {

                ClaseUtil.mensaje(" Tiene que digitar los meses de garantia ");
                txtTiempoDeGarantia.requestFocus();
                return;
            }

            if (!(rbNueva.isSelected() || rbUsada.isSelected())) {

                ClaseUtil.mensaje("Tiene que  seleccionar si la flota es nueva o  usada  ");
                return;
            }

            if (!editar) {

                setRegistroDeFlota(new RegistroDeFlota());
            }

            getRegistroDeFlota().setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            getRegistroDeFlota().setFechaRegistro(new Date());
            getRegistroDeFlota().setUsuario(VariablesGlobales.USUARIO);
            getRegistroDeFlota().setNombreUsuario(getRegistroDeFlota().getUsuario().getNombre());
            getRegistroDeFlota().setNombreFlota(txtDescripcion.getText());
            getRegistroDeFlota().setAnulado(false);
            getRegistroDeFlota().setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            getRegistroDeFlota().setAsignada(false);
            getRegistroDeFlota().setFechaEstado(new Date());
            getRegistroDeFlota().setNota(txtObservacion.getText().isEmpty() ? " "
                    : txtObservacion.getText());
            getRegistroDeFlota().setImei(txtImei.getText());
            getRegistroDeFlota().setSim(txtSim.getText().isEmpty() ? "na " : txtSim.getText());
            getRegistroDeFlota().setModelo(txtModelo.getText());
            getRegistroDeFlota().setMarca(txtMarca.getText());
            getRegistroDeFlota().setNueva(rbNueva.isSelected());
            getRegistroDeFlota().setUsada(rbUsada.isSelected());
            getRegistroDeFlota().setPrecio(Double.parseDouble(txtPrecio.getText().isEmpty() ? "0" : txtPrecio.getText()));
            getRegistroDeFlota().setEmpresaTelefonica(txtTelefonica.getText());
            getRegistroDeFlota().setTieneGarantia(chTieneGarantia.isSelected());

            if (getRegistroDeFlota().getTieneGarantia()) {

                getRegistroDeFlota().setFechaGarantiaDesde(ClaseUtil.asDate(dpFechaDesde.getValue()));
                getRegistroDeFlota().setFechaGarantiaHasta(ClaseUtil.asDate(dpFechaHasta.getValue()));
                getRegistroDeFlota().setMesesDeGarantia(Integer.parseInt(txtTiempoDeGarantia.getText().trim()));
            }

            if (editar) {

                ManejoRegistroDeFlota.getInstancia().actualizar(getRegistroDeFlota());

            } else {

                getRegistroDeFlota().setFechaRegistro(new Date());
                ManejoRegistroDeFlota.getInstancia().salvar(getRegistroDeFlota());
            }

            txtModelo.clear();
            txtMarca.clear();
            txtSim.clear();
            txtImei.clear();
            txtDescripcion.clear();
            txtTelefonica.clear();
            txtPrecio.clear();
            rbNueva.setSelected(false);
            rbUsada.setSelected(false);
            dpFecha.setValue(null);
            dpFechaDesde.setValue(null);
            dpFechaHasta.setValue(null);

            registroDeFlota = new RegistroDeFlota();

            ClaseUtil.mensaje(" Registro realizado correctamente ");
            Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error registrado la flota");
            e.printStackTrace();
        }

    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();
    }

    public void llenarCampo() {

        txtDescripcion.setText(getRegistroDeFlota().getNombreFlota());
        dpFecha.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getRegistroDeFlota().getFecha()));

        txtSim.setText(getRegistroDeFlota().getSim() == null ? ""
                : getRegistroDeFlota().getSim());

        txtObservacion.setText(getRegistroDeFlota().getNota());
        txtImei.setText(getRegistroDeFlota().getImei());
        txtMarca.setText(getRegistroDeFlota().getMarca());
        txtModelo.setText(getRegistroDeFlota().getModelo());
        txtPrecio.setText(Double.toString(getRegistroDeFlota().getPrecio()));
        txtTelefonica.setText(getRegistroDeFlota().getEmpresaTelefonica());
        rbNueva.setSelected(getRegistroDeFlota().getNueva());
        rbUsada.setSelected(getRegistroDeFlota().getUsada());

        if (getRegistroDeFlota().getTieneGarantia()) {

            chTieneGarantia.setSelected(true);
            txtTiempoDeGarantia.setText(Integer.toString(getRegistroDeFlota().getMesesDeGarantia()));
            dpFechaDesde.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getRegistroDeFlota().getFechaGarantiaDesde()));
            dpFechaHasta.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getRegistroDeFlota().getFechaGarantiaHasta()));
        }

    }

    @FXML
    private void btnBuscarSimActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/contrato/gps/BuscarSimCard.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        BuscarSimCardController controller = loader.getController();

        utiles.ClaseUtil.getStageModal(root);

        System.out.println("ESPERANDOOO!!!");

        if (!(controller.getRegistro() == null)) {

            txtSim.setText(controller.getRegistro().getNumero());

        } else {
            ClaseUtil.mensaje("Tiene que seleccionar un sim");
        }

    }

    @FXML
    private void chTieneGarantiaActionEvent(ActionEvent event) {

        if (chTieneGarantia.isSelected()) {
            txtTiempoDeGarantia.setEditable(true);
            txtTiempoDeGarantia.requestFocus();

        } else {
            txtTiempoDeGarantia.setEditable(false);
        }
    }

}
