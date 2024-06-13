/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cxp;

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
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ModuloCxPController implements Initializable {

    @FXML
    private BorderPane bpVenta;
    @FXML
    private Label lbModuloVenta;
    @FXML
    private VBox vbMenu;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuRegistro;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    @FXML
    private Menu menuReporte;
    @FXML
    private MenuItem rptFactura;
    @FXML
    private MenuItem rptSuplidores;
    @FXML
    private JFXButton btnCSuplidor;
    @FXML
    private JFXButton btnPagarFactura;
    @FXML
    private JFXButton btnAvanceDeCliente;
    @FXML
    private MenuItem rptCompPorProyecto;
    @FXML
    private JFXButton btnConsultaFacturaSuplidor;
    @FXML
    private JFXButton btnAvanceASuplidor;

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
    private void menuPrincipalMouseClick(MouseEvent event) {
    }

    @FXML
    private void btnConsultaFacturaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/cxp/factura/ConsultaFacturacionSuplidor.fxml"));
            lbModuloVenta.setText("Modulo de CXP -> Consulta Factura Suplidor");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloCxPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Agrega las opciones de menu a la base de datos
    private void agregarOpciones() {

        MenuModulo menuModulo;

        List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(3);
        int codigoModulo = ManejoModulo.getInstancia().getModulo(3).getCodigo();
        String nombreModulo = ManejoModulo.getInstancia().getModulo(3).getNombre();

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

    //Agrega las opciones de menu a la base de datos
    private void agregarOpcionesMenu() {

        MenuModulo menuModulo;

        boolean actualizando = false;

        int codTipoMenu = 0;

        try {

            List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(10);
            int codigoModulo = ManejoModulo.getInstancia().getModulo(10).getCodigo();
            String nombreModulo = ManejoModulo.getInstancia().getModulo(10).getNombre();

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

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static final Logger LOG = Logger.getLogger(ModuloCxPController.class.getName());

    private void activarOpciones() {

        if (codigoRol == 1) {//rol de administrador

            for (Node n : vbMenu.getChildren()) {
                n.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, 10);

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
                    .getInstancia().getRolMenuModulo(codigoRol, 10);

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
    private void rptFacturaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/compra/consulta/FXMLConsultarOrdenDeCompra.fxml"));
            lbModuloVenta.setText(" Modulo de CXP -> Factura Suplidor");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloCxPController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void rptSuplidoresActionEvent(ActionEvent event) {

    }

    @FXML
    private void btnCSuplidorActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/suplidor/FXMLConsultaSuplidor.fxml"));
            lbModuloVenta.setText("Modulo de CXP -> Suplidor");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloCxPController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnPagarFacturaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/cxp/comprabanteDePago/ComprabanteDePago.fxml"));
            lbModuloVenta.setText("Modulo de CXP -> Pagar Factura");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloCxPController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnAvanceDeClienteActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/cxp/avanceCliente/AvanceDeCliente.fxml"));
            lbModuloVenta.setText(" Modulo de CXP -> Avance de Cliente ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloCxPController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void rptrptCompPorProyecto(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/cxp/reporte/ReporteCompraPorProyecto.fxml"));
            lbModuloVenta.setText(" Modulo de CXP -> Cuenta Por Pagar ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloCxPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnNotaCreditoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/notaCredito/NotaDeCredito.fxml"));
            lbModuloVenta.setText(" Modulo de CXP -> Crear Nota Credito ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnNotaDebitoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/notaDebito/NotaDebito.fxml"));
            lbModuloVenta.setText(" Modulo de CXP -> Crear Nota de Debito ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnAvanceASuplidorActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/cxp/avanceSuplidor/AvanceASuplidor.fxml"));
            lbModuloVenta.setText(" Modulo de CXP -> Avance a Suplidor ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloCxPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
