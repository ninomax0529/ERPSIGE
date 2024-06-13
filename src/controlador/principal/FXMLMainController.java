package controlador.principal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXButton;
import sige.FXMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLMainController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private JFXButton btnPrestamo;

    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;

    @FXML
    private MenuItem imConsultaPrestamo;
    @FXML
    private MenuItem imArticulo;
    @FXML
    private MenuItem imCategoria;
    @FXML
    private MenuItem itCliente;
    @FXML
    private MenuItem itConsultaArticulo;
    @FXML
    private MenuItem itRptCliente;
    @FXML
    private MenuItem itRptPrestamo;
    @FXML
    private JFXButton btnCliente;
    @FXML
    private JFXButton btnArticulo;
    @FXML
    private JFXButton tbcReciboDePago;
    @FXML
    private JFXButton btnCategoria;
    @FXML
    private MenuItem itConsultaPago;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        img1.setImage(new Image(getClass().getResourceAsStream("/imagen/compraventa.jpg")));
        img2.setImage(new Image(getClass().getResourceAsStream("/imagen/compraventa.jpg")));
        img3.setImage(new Image(getClass().getResourceAsStream("/imagen/compraventa.jpg")));
        
        
    }

    @FXML
    void menuPrincipalMouseClick(MouseEvent event) throws IOException {

        FXMain.vistaMenu();
    }

    @FXML
    private void btnRegistrarCliente(ActionEvent event) throws IOException {

        FXMain.vistaCliente();
    }

    @FXML
    void btnRegistrarArticulo(ActionEvent event) throws IOException {

        FXMain.vistaArticulo();
    }

    @FXML
    void btnRegistrarPrestamo(ActionEvent event) throws IOException {

        FXMain.vistaPrestamo();
    }

    @FXML
    void btnRegistrarCategoria(ActionEvent event) throws IOException {
        FXMain.vistaCategoria();
    }

    @FXML
    void consultaEstadoPrestamo(ActionEvent event) throws IOException {

        FXMain.vistaConsultaPrestamo();
    }

    @FXML
    void consultaClienteEvent(ActionEvent event) throws IOException {

        FXMain.vistaConsultaCliente();
    }

    @FXML
    void actionEventConsultaArticulo(ActionEvent event) throws IOException {

        FXMain.vistaConsultaArticulo();
    }

    @FXML
    void reporteClienteEvent(ActionEvent event) throws IOException {

        FXMain.vistaReporteCliente();
    }

    @FXML
    void reportePrestamoEvent(ActionEvent event) throws IOException {

        FXMain.vistaReportePrestamo();
    }

    @FXML
    void imArticuloEventAction(ActionEvent event) throws IOException {

        FXMain.vistaArticulo();
    }

    @FXML
    void imCategoriaEventAction(ActionEvent event) throws IOException {

        FXMain.vistaCategoria();
    }

    void btnImprimirEvent(ActionEvent event) throws IOException {

        FXMain.vistaReportePrestamo();
    }

    @FXML
    private void btnReciboDePago(ActionEvent event) throws IOException {

        FXMain.vistaRegistrarPago();
    }

    @FXML
    private void actionEventConsultaPago(ActionEvent event) throws IOException {

        FXMain.vistaConsultaPago();
    }
}
