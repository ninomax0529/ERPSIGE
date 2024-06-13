/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion;

import com.jfoenix.controls.JFXButton;
import controlador.venta.ModuloVentaController;
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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import sistema.AdministrarMenu;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ModuloConfiguracionController implements Initializable, InicializarMenu {

    @FXML
    private BorderPane bpCajaChica;
    @FXML
    private Label lbModulo;
    @FXML
    private JFXButton btnUsuario;
    @FXML
    private JFXButton btnEmpresa;
    @FXML
    private VBox vbMenu;
    @FXML
    private JFXButton btnGestionarRol;
    @FXML
    private JFXButton btnAsignarPermiso;
//    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    @FXML
    private JFXButton btnDireccion;
    @FXML
    private JFXButton btnDelivery;
    @FXML
    private JFXButton btnUnidadDeNegocio;
    @FXML
    private JFXButton btnSecuenciaDoc;
    @FXML
    private JFXButton btnNcf;
    @FXML
    private JFXButton btnDescuentoPorUsuario;
    @FXML
    private JFXButton btnConfiguracion;

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
    private void btnUsuarioActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/configuracion/usuario/Usuario.fxml"));
            lbModulo.setText("Modulo de Configuracion -> Crear Usuario");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnEmpresaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/configuracion/empresa/EmpresaOGrupo.fxml"));
            lbModulo.setText("Modulo de Configuracion -> Empresa o Grupo ");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnGestionarRolACtionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/configuracion/usuario/Roles.fxml"));
            lbModulo.setText("Modulo de Configuracion -> Gestionar Rol ");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAsignarPermisoActionEvent(ActionEvent event) {
        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/configuracion/autorizacion/AutorizacionSistema.fxml"));
            lbModulo.setText("Modulo de Configuracion -> Autorizaciones ");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnDireccionActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/configuracion/direccion/direccion.fxml"));
            lbModulo.setText("Modulo de Configuracion -> Crear Direccion");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnDeliveryActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/configuracion/direccion/delivery/delivery.fxml"));
            lbModulo.setText("Modulo de Configuracion -> Crear Delivery");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnUnidadDeNegocioActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/configuracion/unidadDeNegocio/UnidadDeNegocio.fxml"));
            lbModulo.setText("Modulo de Configuracion -> Crear Unidad de Negocio ");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnSecuenciaDocActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/configuracion/secuenciaDocumento/SecuenciaDocumento.fxml"));
            lbModulo.setText("Modulo de Configuracion -> Crear Secuencia de Documento  ");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnNcfActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/configuracion/comprobanteFical/ConfiguracionNcf.fxml"));
            lbModulo.setText("Modulo de Configuracion -> Registro de Comprabantes Fiscales ");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void inicializarMenu() {

        int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();

        AdministrarMenu.getInstancia().agregarOpciones(vbMenu, 1);
        AdministrarMenu.getInstancia().activarOpciones(vbMenu, 1, codigoRol);

        // agregarOpciones(vbMenu, 1) Le pasamos como algumentos el menu de la interface  y el codigo del modulo
        /* activarOpciones(vbMenu, 1, codigoRol) Le pasamos como algumento el menu de la interface  
           el codigo del modulo y el codigo del Rol de usaurio */
    }

    @FXML
    private void btnDescuentoPorUsuarioActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/descuento/DescuentoPorUsuario.fxml"));
            lbModulo.setText("Modulo de Configuracion -> Descuento Por Usuario ");
            bpCajaChica.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnConfiguracionActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/configuracion/empresa/configuracion.fxml"));

            ClaseUtil.crearStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
