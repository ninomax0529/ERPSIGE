/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cxc;

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
public class ModuloCxCController implements Initializable, InicializarMenu {

    @FXML
    private JFXButton btnCliente;
    @FXML
    private BorderPane bpVenta;
    @FXML
    private Label lbModuloVenta;
    @FXML
    private JFXButton btnReciboCaja;
    @FXML
    private VBox vbMenu;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    @FXML
    private JFXButton btnGestionarCobros;

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

            Logger.getLogger(ModuloCxCController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnReciboCajaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/cxc/reciboIngreso/ReciboIngreso.fxml"));
            lbModuloVenta.setText("Modulo de Cuentas Por cobrar -> Recibo de Ingreso");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloCxCController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnGestionarCobrosActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/cxc/gestionDeCobro/GestionDeCobro.fxml"));
            lbModuloVenta.setText("Modulo de Cuentas por Cobrar -> Cliente");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloCxCController.class.getName()).log(Level.SEVERE, null, ex);
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

}
