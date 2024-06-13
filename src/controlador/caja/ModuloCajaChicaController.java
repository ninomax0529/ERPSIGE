/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.caja;

import com.jfoenix.controls.JFXButton;
import controlador.venta.ModuloVentaController;
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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ModuloCajaChicaController implements Initializable {

    @FXML
    private BorderPane bpCajaChica;
    @FXML
    private Label lbModuloVenta;
    @FXML
    private JFXButton btnMovCajaChica;
    @FXML
    private JFXButton btnAperturaCaja;
    @FXML
    private JFXButton btnCerrarCaja;
    @FXML
    private MenuItem menuRptCuadreCaja;
    @FXML
    private MenuItem mRegistroCajaChica;
    @FXML
    private JFXButton btnConsultaCajaChica;

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

    private void btnCajaAcionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/caja/RegistroCaja.fxml"));
//            lbModulo.setText("Modulo de Inventario -> Almacen");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnMovCajaChicaActionevent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/caja/CajaChica.fxml"));
            lbModuloVenta.setText("Modulo de Caja Chica -> Movimiento Caja Chica");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAperturaCajaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/caja/AbrilCaja.fxml"));
            lbModuloVenta.setText("Modulo de Caja Chica -> abrir Caja");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnCerrarCajaActionevent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/caja/CerrarCaja.fxml"));
            lbModuloVenta.setText("Modulo de Caja Chica -> Cerrar Caja");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menuRptCuadreCajaActionevent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/caja/ReporteCuadreCaja.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void mRegistroCajaChicaOnAction(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/caja/RegistroCaja.fxml"));
            lbModuloVenta.setText("Modulo de Caja Chica -> Registrar Caja");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnConsultaCajaChicaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/caja/ConsultarCajaChica.fxml"));
            lbModuloVenta.setText("Modulo de Caja Chica -> Registrar Caja");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
