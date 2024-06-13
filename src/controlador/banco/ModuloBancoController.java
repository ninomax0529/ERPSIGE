/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.banco;

import controlador.cxc.*;
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
public class ModuloBancoController implements Initializable, InicializarMenu {

    @FXML
    private BorderPane bpVenta;
    @FXML
    private Label lbModuloVenta;
    @FXML
    private VBox vbMenu;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    @FXML
    private JFXButton btnRegistrarBanco;
    @FXML
    private JFXButton btnMovimientoBanco;
    @FXML
    private JFXButton btnConciliacion;

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

    @Override
    public void inicializarMenu() {

        AdministrarMenu.getInstancia().agregarOpciones(vbMenu, 4);
        AdministrarMenu.getInstancia().activarOpciones(vbMenu, 4, codigoRol);

        //agregarOpciones(vbMenu, 4) Le pasamos como algumentos el menu de la interface  y el codigo del modulo
        /* activarOpciones(vbMenu, 4, codigoRol)  Le pasamos como algumento el menu de la interface  
          el codigo del modulo y el codigo del Rol de usaurio */
    }

    @FXML
    private void btnRegistrarBancoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/banco/registro/Banco.fxml"));
            lbModuloVenta.setText("Modulo de Banco -> Registro de Banco ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloBancoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnMovimientoBancoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/banco/movimiento/Movimiento.fxml"));
            lbModuloVenta.setText("Modulo de Banco -> Movimiento de Banco  ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloBancoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnConciliacionActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/banco/conciliacion/ConciliacionBancaria.fxml"));
            lbModuloVenta.setText("Modulo de Banco -> Conciliacion Bancaria  ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloBancoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
