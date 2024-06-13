/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cxc.gestionDeCobro;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import manejo.cxc.gestionDeCobro.ManejoGestionDeCobro;
import modelo.GestionDeCobro;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroGestionCobroController implements Initializable {

    /**
     * @return the gestionDeCobro
     */
    public GestionDeCobro getGestionDeCobro() {
        return gestionDeCobro;
    }

    /**
     * @param gestionDeCobro the gestionDeCobro to set
     */
    public void setGestionDeCobro(GestionDeCobro gestionDeCobro) {
        this.gestionDeCobro = gestionDeCobro;
    }

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private TextArea txtNota;
    @FXML
    private Label lbCliente;
    @FXML
    private JFXButton btnCerrar;

    private GestionDeCobro gestionDeCobro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {
            getGestionDeCobro().setNota(txtNota.getText());

            ManejoGestionDeCobro.getInstancia().salvar(gestionDeCobro);

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

        lbCliente.setText(getGestionDeCobro().getNombreCliente());
        
    }

    private void limpiar() {

    }

}
