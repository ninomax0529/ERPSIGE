/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.empresa;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import manejo.ManejoConfiguracion;
import modelo.Configuracion;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroEmpresaController implements Initializable {

    @FXML
    private JFXButton btnGuardar;

    @FXML
    private JFXButton btnCerrar;

    Configuracion configuracion = null;

    boolean editar = false;
    @FXML
    private JFXTextField txtItbis;
    @FXML
    private JFXRadioButton rbCarta;
    @FXML
    private JFXRadioButton rbTicket;
    ToggleGroup toggleGroupformatoDocumento = new ToggleGroup();
    @FXML
    private JFXCheckBox chImprimirDirecto;
    @FXML
    private JFXCheckBox chActivarAbonoInicial;
    @FXML
    private JFXCheckBox chFacturarSinAperturaDeCaja;
    @FXML
    private JFXCheckBox chFacturarSinImprimir;
    @FXML
    private JFXTextField txtHoraExtraMenor69;
    @FXML
    private JFXTextField txtHoraExtraMayor68;
    @FXML
    private JFXTextField txtNcfDisponible;
    @FXML
    private JFXCheckBox chUltimaNomina;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicailizarEmpresa();

    }

    private void inicailizarEmpresa() {

        configuracion = ManejoConfiguracion.getInstancia().getConfiguracion();

        if (!(configuracion == null)) {

            txtItbis.setText(configuracion.getItbis().toString());
            txtHoraExtraMayor68.setText(configuracion.getHoraExtraMayor68().toString());
            txtHoraExtraMenor69.setText(configuracion.getHoraExtraMenor69().toString());
            txtNcfDisponible.setText(configuracion.getAvisoNcfDisponible().toString());
        }

        rbCarta.setToggleGroup(toggleGroupformatoDocumento);
        rbTicket.setToggleGroup(toggleGroupformatoDocumento);

        if (configuracion.getFormatoDocumento() == 1) {

            rbCarta.setSelected(true);
            rbTicket.setSelected(false);

        } else if (configuracion.getFormatoDocumento() == 2) {

            rbTicket.setSelected(true);
            rbCarta.setSelected(false);
        }

        chImprimirDirecto.setSelected(configuracion.getImpresionDirecta());
        chActivarAbonoInicial.setSelected(configuracion.getActivarAbonoInicial());
        chFacturarSinAperturaDeCaja.setSelected(configuracion.getFacturarSinApertuaraDeCaja());
        chFacturarSinImprimir.setSelected(configuracion.getFacturarSinImprimir());
        chUltimaNomina.setSelected(configuracion.getCalcularUltimaNomina());

    }

    private void btnLimpiarActionEvent(ActionEvent event) {

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (configuracion == null) {

                configuracion = new Configuracion();
            }

            if (txtItbis.getText().isEmpty()) {

                txtItbis.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar el Itbis");

                return;
            }

            Integer formatoDocumento = 1;

            if (rbCarta.isSelected()) {

                formatoDocumento = 1;

            } else if (rbTicket.isSelected()) {

                formatoDocumento = 2;
            }

            configuracion.setEmpresa("na");
            configuracion.setDireccion("na");
            configuracion.setTelefono("na");
            configuracion.setRnc("na");
            configuracion.setSlogan("na");
            configuracion.setItbis(Long.parseLong(txtItbis.getText()));
            configuracion.setFormatoDocumento(formatoDocumento);
            configuracion.setImpresionDirecta(chImprimirDirecto.isSelected());
            configuracion.setActivarAbonoInicial(chActivarAbonoInicial.isSelected());
            configuracion.setCalcularUltimaNomina(chUltimaNomina.isSelected());
            configuracion.setFacturarSinApertuaraDeCaja(chFacturarSinAperturaDeCaja.isSelected());
            configuracion.setFacturarSinImprimir(chFacturarSinImprimir.isSelected());
            configuracion.setAvisoNcfDisponible(Integer.parseInt(txtNcfDisponible.getText().isEmpty() ? "0"
                    : txtNcfDisponible.getText().trim()
            ));

            configuracion.setHoraExtraMayor68(Double.parseDouble(txtHoraExtraMayor68.getText().isEmpty() ? "0"
                    : txtHoraExtraMayor68.getText())
            );

            configuracion.setHoraExtraMenor69(Double.parseDouble(txtHoraExtraMenor69.getText().isEmpty() ? "0"
                    : txtHoraExtraMenor69.getText())
            );

            ManejoConfiguracion.getInstancia().actualizar(configuracion);
            ClaseUtil.mensaje("Empresa registrada Exitosamente");

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
