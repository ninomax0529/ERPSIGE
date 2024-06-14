/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.alquilerEquipo;

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
public class ModuloAlquilerEquipoController implements Initializable, InicializarMenu {

    @FXML
    private JFXButton btnCliente;
    @FXML
    private BorderPane bpVenta;
    @FXML
    private Label lbModuloVenta;
    @FXML
    private VBox vbMenu;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    @FXML
    private JFXButton btnCotizacion;
    @FXML
    private JFXButton btnOrdenAlquiler;
    @FXML
    private JFXButton btnEntregaEquipo;
    @FXML
    private JFXButton btnConduce;
    @FXML
    private JFXButton btnGestionarCobros111;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarMenu();

    }

    @FXML
    private void menuPrincipalMouseClick(MouseEvent event) {
    }

    @FXML
    private void btnClienteActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/cliente/Cliente.fxml"));
            lbModuloVenta.setText("Modulo de Cuentas por Cobrar -> Cliente");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloAlquilerEquipoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inicializarMenu() {

        AdministrarMenu.getInstancia().agregarOpciones(vbMenu, 4);
        AdministrarMenu.getInstancia().activarOpciones(vbMenu, 4, codigoRol);

        //agregarOpciones(vbMenu, 4) Le pasamos como algumentos el menu de la interface  y el codigo del modulo
        /* activarOpciones(vbMenu, 4, codigoRol)  Le pasamos como algumento el menu de la interface  
          el codigo del modulo y el codigo del Rol de usaurio */
    }

    @FXML
    private void btnCotizacionActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnOrdenAlquilerActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnEntregaEquipoActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnConduceActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnGestionarCobrosActionEvent(ActionEvent event) {
    }

}
