/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.asistenciaTecnica;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import manejo.asistenciaTecnica.ManejoAsistencaTecnica;
import modelo.AsistenciaTecnica;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RazonanulacionAsistenciaController implements Initializable {

    @FXML
    private Label lbNumAsistencia;

    /**
     * @return the asistenciaTecnica
     */
    public AsistenciaTecnica getAsistenciaTecnica() {
        return asistenciaTecnica;
    }

    /**
     * @param asistenciaTecnica the asistenciaTecnica to set
     */
    public void setAsistenciaTecnica(AsistenciaTecnica asistenciaTecnica) {
        this.asistenciaTecnica = asistenciaTecnica;
    }

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private TextArea txtNota;
    @FXML
    private Label lbCliente;
    @FXML
    private JFXButton btnCerrar;

    private AsistenciaTecnica asistenciaTecnica;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (txtNota.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar la razon de la anulacion");
                return;
            }

            getAsistenciaTecnica().setRazonAnulada(txtNota.getText());
            getAsistenciaTecnica().setAnuladaPor(VariablesGlobales.USUARIO.getNombre());
            getAsistenciaTecnica().setAnulada(true);
            getAsistenciaTecnica().setFechaAnulada(new Date());

//
            ManejoAsistencaTecnica.getInstancia().salvar(getAsistenciaTecnica());

            Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
            stage.close();

        } catch (Exception e) {

            e.printStackTrace();

            ClaseUtil.mensaje("Hubo un error guardando el registro");
            return;
        }
    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {
        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();
    }

    public void llenarCampo() {

        lbCliente.setText(getAsistenciaTecnica().getNombreTecnico());
        lbNumAsistencia.setText(Integer.toString(getAsistenciaTecnica().getCodigo()));

    }

    private void limpiar() {

    }

}
