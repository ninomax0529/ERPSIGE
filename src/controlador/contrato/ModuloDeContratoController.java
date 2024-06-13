/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato;

import com.jfoenix.controls.JFXButton;
import controlador.venta.ModuloVentaController;
import interfaces.InicializarMenu;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import modelo.MenuModulo;
import sistema.AdministrarMenu;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ModuloDeContratoController implements Initializable, InicializarMenu {

    private Label lbModulo;
    @FXML
    private VBox vbMenu;
    @FXML
    private JFXButton btnRegistroContrato;
    @FXML
    private Label lbModuloVenta;
    @FXML
    private JFXButton btnFacturarContrato;
    @FXML
    private BorderPane bpVenta;
    @FXML
    private JFXButton btnFacturasRecurrentesContrato;
    @FXML
    private JFXButton btnContratoPorVencer;
    @FXML
    private JFXButton btnFacturaPorCorreo;
    @FXML
    private JFXButton btnFacturasAnualesContrato;
    @FXML
    private JFXButton btnFacturaContrato;
    @FXML
    private JFXButton btnFacturaAvanceContrato;
    @FXML
    private JFXButton btnRenovarContrato;
    @FXML
    private JFXButton btnReporteContrato;
    @FXML
    private JFXButton btnContratoVencido;
    @FXML
    private JFXButton btnRegistrarSim;
    @FXML
    private JFXButton btnRegistrarImei;
    @FXML
    private JFXButton btnAsistenciaTecnica;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();

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
    private void btnRegistroContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/ContratoDeServicio.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeContratoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnFacturarContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FacturarContrato.fxml"));
//            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FacturarContratoDeServicio.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeContratoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnFacturasRecurrentesContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FactursRecurrentesrContrato.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeContratoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnContratoPorVencerActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/ContratoDeServicioPorVencer.fxml"));

            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeContratoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnFacturaPorCorreoActionActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FacturaParaEnvierPorCorreo.fxml"));

            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeContratoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Agrega las opciones de menu a la base de datos
    private void agregarOpciones() {

        MenuModulo menuModulo;

        List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(9);
        int codigoModulo = ManejoModulo.getInstancia().getModulo(9).getCodigo();
        String nombreModulo = ManejoModulo.getInstancia().getModulo(9).getNombre();

        boolean actualizando = false;

        for (Node n : vbMenu.getChildren()) {

            if (!(n.getId() == null)) {

                menuModulo = new MenuModulo();

                menuModulo.setIdMenu(n.getId());
                menuModulo.setMenu(n.getAccessibleText());
                menuModulo.setModulo(codigoModulo);
                menuModulo.setNombreModulo(nombreModulo);
//                menuModulo.setTipoMenu(ManejoTipoMenu.getInstancia().getTipoMenu(codigoModulo));

                for (MenuModulo memu : listaMenuModulo) {

                    if (n.getId().equals(memu.getIdMenu())) {

                        menuModulo = memu;
                        actualizando = true;
                        break;
                    }
                }

                if (actualizando) {

                    ManejoMenuModulo.getInstancia().actualizar(menuModulo);

                } else {

                    ManejoMenuModulo.getInstancia().salvar(menuModulo);
                }

            }

        }
    }

    @FXML
    private void btnFacturasAnualesContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FacturaAnualesDeContrato.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeContratoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnFacturaContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/ConsultaFacturacion.fxml"));
            lbModuloVenta.setText("Modulo de Venta -> Consulta Factura");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnFacturaAvanceContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FacturarAvancerDeContrato_1.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeContratoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnRenovarContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FacturarRenovacionrDeContrato_2.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeContratoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnReporteContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/reporte/ModuloReporteIghtrack.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeContratoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnContratoVencidoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/consulta/ContratoDeServicioVencido.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeContratoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnRegistrarSimACtionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/gps/SimCard.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeContratoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnRegistrarImeiActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/gps/ImeiGps.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeContratoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnAsistenciaTecnicaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/asistenciaTecnica/AsistenciaTecnica.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloDeContratoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inicializarMenu() {

        AdministrarMenu.getInstancia().agregarOpciones(vbMenu, 9);
        AdministrarMenu.getInstancia().activarOpciones(vbMenu, 9, codigoRol);

        // agregarOpciones(vbMenu, 9) Le pasamos como algumentos el menu de la interface  y el codigo del modulo
        /* activarOpciones(vbMenu, 9, codigoRol) Le pasamos como algumento el menu de la interface  
           el codigo del modulo y el codigo del Rol de usaurio */
    }

}
