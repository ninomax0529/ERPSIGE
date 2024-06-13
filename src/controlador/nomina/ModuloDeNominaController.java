/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.nomina;

import com.jfoenix.controls.JFXButton;
import interfaces.InicializarMenu;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import sistema.AdministrarMenu;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ModuloDeNominaController implements Initializable, InicializarMenu {

    @FXML
    private BorderPane bpVenta;
    @FXML
    private Label lbModuloVenta;
    @FXML
    private VBox vbMenu;
    @FXML
    private JFXButton btnRegistroEmpleado;
    @FXML
    private JFXButton btnNomina;
    @FXML
    private JFXButton btnHoraExtra;
    @FXML
    private JFXButton btnConsultaNomina;
    @FXML
    private JFXButton btnCargo;
    @FXML
    private JFXButton btnRevisarNomina;
    @FXML
    private JFXButton btnAutorizarNomina;
    @FXML
    private JFXButton btnPagarNomina;

    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    @FXML
    private JFXButton btnAnularNomina;
    @FXML
    private JFXButton btnRegistroFlota;
    @FXML
    private JFXButton btnAsignarFlota;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            inicializarMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void menuPrincipalMouseClick(MouseEvent event) {
    }

    @FXML
    private void btnRegistroEmpleadoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/empleado/Empleado.fxml"));

            bpVenta.setCenter(node);
            lbModuloVenta.setText("Modulo de Nomina -> Registro de Empleado ");

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeNominaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnNominaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/nomina/Nomina.fxml"));

            bpVenta.setCenter(node);
            lbModuloVenta.setText("Modulo de Nomina -> Registro de Nomina ");

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeNominaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnHoraExtraACtionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/nomina/HoraExtra.fxml"));

            bpVenta.setCenter(node);
            lbModuloVenta.setText("Modulo de Nomina -> Registro de Horas Extras ");

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeNominaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnConsultaNominaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/nomina/ConsultaNomina.fxml"));

            bpVenta.setCenter(node);
            lbModuloVenta.setText("Modulo de Nomina -> Consulta de Nomina");

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeNominaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnCargoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/empleado/Cargo.fxml"));

            bpVenta.setCenter(node);
            lbModuloVenta.setText("Modulo de Nomina -> Registro de Cargos");

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeNominaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnRevisarNominaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/nomina/RevisarNomina.fxml"));

            bpVenta.setCenter(node);
            lbModuloVenta.setText("Modulo de Nomina -> Revisión de Nomina ");

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeNominaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnAutorizarNominaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/nomina/AutorizarNomina.fxml"));

            bpVenta.setCenter(node);
            lbModuloVenta.setText("Modulo de Nomina -> Autorizacion de Nomina ");

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeNominaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnPagarNominaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/nomina/PagarNomina.fxml"));

            bpVenta.setCenter(node);
            lbModuloVenta.setText("Modulo de Nomina -> Pago de Nomina ");

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeNominaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnAnularNominaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/nomina/AnularNomina.fxml"));

            bpVenta.setCenter(node);
            lbModuloVenta.setText("Modulo de Nomina -> Anular Nomina");

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeNominaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void inicializarMenu() {

        AdministrarMenu.getInstancia().agregarOpciones(vbMenu, 11);
        AdministrarMenu.getInstancia().activarOpciones(vbMenu, 11, codigoRol);

        // agregarOpciones(vbMenu, 1) Le pasamos como algumentos el menu de la interface  y el codigo del modulo
        /* activarOpciones(vbMenu, 1, codigoRol) Le pasamos como algumento el menu de la interface  
           el codigo del modulo y el codigo del Rol de usaurio */
    }

    @FXML
    private void btnRegistroFlotaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/gestionDeFlota/Flota.fxml"));

            bpVenta.setCenter(node);
            lbModuloVenta.setText("Modulo de Nomina -> Registro de Flota ");

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeNominaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAsignarFlotaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/gestionDeFlota/AsignacionDeFlota.fxml"));

            bpVenta.setCenter(node);
            lbModuloVenta.setText("Modulo de Nomina -> Asignar Flota ");

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeNominaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
