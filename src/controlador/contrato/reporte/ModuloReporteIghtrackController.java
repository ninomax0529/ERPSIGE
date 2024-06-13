/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.reporte;

import com.jfoenix.controls.JFXButton;
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
import manejo.menuModulo.ManejoRolMenuModulo;
import modelo.MenuModulo;
import modelo.RolMenuModulo;
import sistema.AdministrarMenu;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ModuloReporteIghtrackController implements Initializable, InicializarMenu {

    @FXML
    private VBox vbMenu;
    @FXML
    private Label lbModuloVenta;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    @FXML
    private JFXButton btnRptClientes;
    @FXML
    private JFXButton btnRptContrato;
    @FXML
    private JFXButton btnRptFacturaPendiente;
    @FXML
    private JFXButton btnRptGps;
    @FXML
    private JFXButton btnRptSim;
    @FXML
    private JFXButton btnRptCobros;
    @FXML
    private JFXButton btnComisiones;
    @FXML
    private BorderPane bpVenta;

    public Integer getCodigoMenuModulo() {
        return codigoMenuModulo;
    }

    /**
     * @param codigoMenuModulo the codigoMenuModulo to set
     */
    public void setCodigoMenuModulo(Integer codigoMenuModulo) {
        this.codigoMenuModulo = codigoMenuModulo;
    }

    Integer codigoMenuModulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        setCodigoMenuModulo(ManejoMenuModulo.getInstancia().getMenuModulo(9, "btnFacturarContrato").getCodigo());
//        agregarOpciones();
//        activarOpciones();
        for (Node n : vbMenu.getChildren()) {
            n.setDisable(false);
        }

    }

    @FXML
    private void menuPrincipalMouseClick(MouseEvent event) {
    }

    private void btnRegistroContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/ContratoDeServicio.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void btnFacturarContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FacturarContrato.fxml"));
//            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FacturarContratoDeServicio.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnFacturasRecurrentesContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FactursRecurrentesrContrato.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void btnContratoPorVencerActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/ContratoDeServicioPorVencer.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void btnFacturaPorCorreoActionActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FacturaParaEnvierPorCorreo.fxml"));

            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void activarOpciones() {

        if (codigoRol == 1) {//rol de administrador

            for (Node n : vbMenu.getChildren()) {
                n.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, 9);

            for (Node n : vbMenu.getChildren()) {

                if (!(n.getId() == null)) {

                    for (RolMenuModulo memu : listaRolMenuModulos) {

                        if (n.getId().equals(memu.getMenuModulo().getIdMenu())) {
                            n.setDisable(!memu.getHabilitado());

                        }
                    }

                }

            }
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

    private void btnFacturasRecurrentesPreviaContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FactursRecurrentesrUVContrato.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnFacturasAnualesContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FacturaAnualesDeContrato.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnFacturaContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/ConsultaFacturacion.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void btnFacturaAvanceContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FacturarAvancerDeContrato.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnRenovarContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FacturarRenovacionrDeContrato.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnReporteContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/reporte/ReporteFacturaContrato.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnRptClientesActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/reporte/ReporteDeCliente.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnRptContratoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/reporte/RptEstadoContrato.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnRptFacturaPendienteActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/reporte/ReporteFacturaContrato.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnRptGpsActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/reporte/ReporteImeiGps.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnRptSimActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/reporte/ReporteSimCard.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnRptCobrosActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/reporte/RptReciboDeIngreso.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnComisionesActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/reporte/RptComisiones.fxml"));
//            lbModulo.setText("Modulo Contrato de Servicio -> Rgistro de Contrato");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloReporteIghtrackController.class.getName()).log(Level.SEVERE, null, ex);
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

}
