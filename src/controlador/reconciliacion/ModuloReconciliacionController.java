/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.reconciliacion;

import com.jfoenix.controls.JFXButton;
import controlador.cxc.ModuloCxCController;
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

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ModuloReconciliacionController implements Initializable {

    @FXML
    private BorderPane bpVenta;
    @FXML
    private Label lbModuloVenta;
    @FXML
    private VBox vbMenu;
    @FXML
    private JFXButton btnRecCliente;
    @FXML
    private JFXButton btnRecSuplidor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void menuPrincipalMouseClick(MouseEvent event) {
    }

    @FXML
    private void btnRecClienteActionEvent(ActionEvent event) {
        
           try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/reconciliacion/reconciliacionCliente/ReconciliacionCliente.fxml"));
            lbModuloVenta.setText("Modulo de Reconciliacion -> Reconciliacion Cliente ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloCxCController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void btnRecSuplidorActionEvent(ActionEvent event) {
    }
    
}
