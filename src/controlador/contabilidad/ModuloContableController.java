/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad;

import com.jfoenix.controls.JFXButton;
import controlador.venta.ModuloVentaController;
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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import manejo.menuModulo.ManejoTipoMenu;
import modelo.MenuModulo;
import modelo.RolMenuModulo;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ModuloContableController implements Initializable {

    @FXML
    private BorderPane bpVenta;
    @FXML
    private MenuItem Ncf;
    @FXML
    private Label lbModuloVenta;
    @FXML
    private JFXButton btnEntradaDiario;
    @FXML
    private MenuItem rptDiarioGeneral;
    @FXML
    private MenuItem rptMayorGeneral;
    @FXML
    private MenuItem rptBalanzaDeCompraobacion;
    @FXML
    private JFXButton btnCatalogo;
    @FXML
    private JFXButton btnConfiguracionCuenta;
    @FXML
    private MenuItem rptBalanceGeneral;
    @FXML
    private MenuItem rptEstadoDeResultados;
    @FXML
    private VBox vbMenu;
    @FXML
    private MenuBar menuBar;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    @FXML
    private JFXButton btnRegistrarProyecto;
    @FXML
    private JFXButton btnFormato606;
    @FXML
    private JFXButton btnFormato607;
    @FXML
    private MenuItem rptFormato606;
    @FXML
    private JFXButton btnNotaCredito;
    @FXML
    private JFXButton btnNotaDebito;
    @FXML
    private JFXButton btnReconciliacionCliente;
    @FXML
    private JFXButton btnReconciliacionSuplidor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        agregarOpcionesMenu();
        agregarOpciones();
        activarOpciones();
        activarOpcionesMenu();
    }

    @FXML
    private void NcfActionEvent(ActionEvent event) {
    }

    @FXML
    private void menuPrincipalMouseClick(MouseEvent event) {
    }

    @FXML
    private void btnEntradaDiarioActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contabilidad/consulta/FXMLConsultaEntradaDiario.fxml"));
            lbModuloVenta.setText("Modulo de Contabilidad -> Entrada Diario");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rptDiarioGeneralActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/contabilidad/reporte/libroContable/FXMLLibroDiario.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rptMayorGeneralActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/contabilidad/reporte/libroContable/FXMLLibroMayor.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rptBalanzaDeCompraobacionActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/contabilidad/reporte/libroContable/FXMBalanzaDeComprobacion.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnCatalogoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contabilidad/registro/FXMLRegistroCatalogoContable.fxml"));
            lbModuloVenta.setText("Modulo de Contabilidad -> Catalogo de Cuenta");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnConfiguracionCuentaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contabilidad/consulta/FXMLConsultaConfiguracionCuentaContable.fxml"));
            lbModuloVenta.setText("Modulo de Contabilidad -> Configuracion de Cuenta");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rptBalanceGeneralActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/contabilidad/reporte/estadoFinaciero/FXMLBalanceGeneral.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloContableController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void rptEstadoDeResultadosActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/contabilidad/reporte/estadoFinaciero/FXMLEstadoDeResultado.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarOpciones() {

        MenuModulo menuModulo;

        List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(5);
        int codigoModulo = ManejoModulo.getInstancia().getModulo(5).getCodigo();
        String nombreModulo = ManejoModulo.getInstancia().getModulo(5).getNombre();

        boolean actualizando = false;

        for (Node n : vbMenu.getChildren()) {

            if (!(n.getId() == null)) {

                menuModulo = new MenuModulo();

                menuModulo.setIdMenu(n.getId());
                menuModulo.setMenu(n.getAccessibleText());
                menuModulo.setModulo(codigoModulo);
                menuModulo.setNombreModulo(nombreModulo);

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

                n.setDisable(true);
            }

        }
    }

    private void agregarOpcionesMenu() {

        MenuModulo menuModulo;

        boolean actualizando = false;

        int codTipoMenu = 0;

        try {

            List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(5);
            int codigoModulo = ManejoModulo.getInstancia().getModulo(5).getCodigo();
            String nombreModulo = ManejoModulo.getInstancia().getModulo(5).getNombre();

            for (Menu menuBar : menuBar.getMenus()) {

                System.out.println("Menu   " + menuBar.getId());

                for (MenuItem menuItem : menuBar.getItems()) {

                    System.out.println("Menu Item " + menuItem.getId());

                    if (!(menuItem.getId() == null)) {

                        menuModulo = new MenuModulo();

                        menuModulo.setIdMenu(menuItem.getId());
                        menuModulo.setMenu(menuItem.getText());
                        menuModulo.setModulo(codigoModulo);
                        menuModulo.setNombreModulo(nombreModulo);

//                        switch (menuBar.getId()) {
//
//                            case "menuReporte":
//                                codTipoMenu = 3;
//                                System.out.println(" caso 3  " + menuBar.getId());
//                                break;
//                            case "menuConsulta":
//                                codTipoMenu = 2;
//                                System.out.println("caso 2 " + menuBar.getId());
//                                break;
//                            case "menuRegistro":
//                                codTipoMenu = 1;
//                                System.out.println("caso 1 " + menuBar.getId());
//                                break;
//                            default:
//                                System.out.println("caso  0 " + menuBar.getId());
//                                break;
//                        }
//
//                        System.out.println("codTipoMenu " + ManejoTipoMenu.getInstancia().getTipoMenu(codTipoMenu).getNombre());
                        menuModulo.setTipoMenu(ManejoTipoMenu.getInstancia().getTipoMenu(codTipoMenu));

                        for (MenuModulo memuModuloObj : listaMenuModulo) {

                            if (menuItem.getId().equals(memuModuloObj.getIdMenu())) {

                                menuModulo = memuModuloObj;
                                System.out.println("Actualizar " + memuModuloObj.getIdMenu() + " codTipoMenu " + codTipoMenu);
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

                    menuItem.setDisable(true);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void activarOpciones() {

        if (codigoRol == 1) {//rol de administrador

            for (Node n : vbMenu.getChildren()) {
                n.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, 5);

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

    private void activarOpcionesMenu() {

        if (codigoRol == 1) {//rol de administrador

            for (Menu menuBar : menuBar.getMenus()) {

                for (MenuItem menuItem : menuBar.getItems()) {

                    menuItem.setDisable(false);
                }
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, 5);

            for (Menu menu : menuBar.getMenus()) {

                for (MenuItem menuItem : menu.getItems()) {

                    if (!(menuItem.getId() == null)) {

                        System.out.println("Menu " + menuItem.getId());

                        for (RolMenuModulo rolMenuModulo : listaRolMenuModulos) {

                            if (menuItem.getId().equals(rolMenuModulo.getMenuModulo().getIdMenu())) {

                                System.out.println("Rol menu " + rolMenuModulo.getMenuModulo().getIdMenu());
                                menuItem.setDisable(!rolMenuModulo.getHabilitado());

                            }
                        }

                    }
                }

            }
        }
    }

    @FXML
    private void btnRegistrarProyectoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/proyecto/Proyecto.fxml"));
            lbModuloVenta.setText("Modulo de Contabilidad -> Registrar Proyecto ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloContableController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnFormato606ActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contabilidad/dgii/Formato606.fxml"));
            lbModuloVenta.setText("Modulo de Contabilidad -> Formato 606 ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnFormato607ActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contabilidad/dgii/Formato607.fxml"));
            lbModuloVenta.setText("Modulo de Contabilidad -> Formato 607 ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rptFormato606ActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/contabilidad/reporte/dgii/ReporteFormato606.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnNotaCreditoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/notaCredito/NotaDeCredito.fxml"));
            lbModuloVenta.setText(" Modulo de Contabilidad -> Crear Nota Credito ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnNotaDebitoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/notaDebito/NotaDebito.fxml"));
            lbModuloVenta.setText("Modulo de Contabilidad -> Registrar Nota de Debito ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnReconciliacionClienteActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/reconciliacion/reconciliacionCliente/ReconciliacionCliente.fxml"));
            lbModuloVenta.setText("Modulo de Contabilidad -> Reconciliacion de Cliente ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnReconciliacionSuplidorActionEvent(ActionEvent event) {

//        try {
//
//            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/reconciliacion/reconciliacionCliente/ReconciliacionCliente.fxml"));
//            lbModuloVenta.setText("Modulo de Contabilidad -> Reconciliacion de Cliente ");
//            bpVenta.setCenter(node);
//
//        } catch (IOException ex) {
//
//            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

}
