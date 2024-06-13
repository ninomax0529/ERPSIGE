/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta;

import com.jfoenix.controls.JFXButton;
import controlador.inventario.ModuloInventarioController;
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
import sistema.AdministrarMenu;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ModuloVentaController implements Initializable, InicializarMenu {

    @FXML
    private JFXButton btnCliente;
    @FXML
    private BorderPane bpVenta;
    @FXML
    private Label lbModuloVenta;
    @FXML
    private JFXButton btnConsultaFactura;
    @FXML
    private MenuItem rptDiarioDeVenta;
    @FXML
    private MenuItem rptCliente;
    @FXML
    private MenuItem rptDeVentaEntreFecha;
    @FXML
    private MenuItem rptMensualDeVenta;
    @FXML
    private MenuItem rptReporteUtilidadBruta;
    @FXML
    private VBox vbMenu;
    @FXML
    private MenuBar menuBar;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    @FXML
    private Menu menuReporte;
    @FXML
    private JFXButton btnListaDePrecio;
    @FXML
    private JFXButton btnCambiarPrecio;
    @FXML
    private JFXButton btnCotizacion;
    @FXML
    private JFXButton btnConsultaCotizacion;
    @FXML
    private JFXButton btnVendedor;
    @FXML
    private JFXButton btnConduce;
    @FXML
    private MenuItem rptReporteNcf;
    @FXML
    private JFXButton btnFacturaPorCorreo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        agregarOpcionesMenu();
//        agregarOpciones();
//        activarOpciones();
        activarOpcionesMenu();
        inicializarMenu();

    }

    @FXML
    private void menuPrincipalMouseClick(MouseEvent event) {
    }

    @FXML
    private void btnClienteActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/cliente/Cliente.fxml"));
            lbModuloVenta.setText("Modulo de Venta -> Cliente");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    private void btnPuntoVentaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/Facturacion.fxml"));
            lbModuloVenta.setText("Modulo de Venta -> Facturación");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void btnCajaAcionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/caja/RegistroCaja.fxml"));
            lbModuloVenta.setText("Modulo de Venta -> Caja");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnConsultaFacturaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/ConsultaFacturacion.fxml"));
            lbModuloVenta.setText("Modulo de Venta -> Consulta Factura");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void NcfActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/ConfiguracionNcf.fxml"));
            lbModuloVenta.setText("Modulo de Venta -> Configuracion Ncf");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rptDiarioDeVentaActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/ReporteDiarioDeVenta.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rptClienteActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/cliente/ReporteCliente.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rptDeVentaEntreFechaActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/ReporteDeVentaEntreFecha.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rptMensualDeVentaActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/ReporteMensualDeVenta.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rptReporteUtilidadBrutaActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/ReporteUtilidadBruta.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
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

            List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(3);
            int codigoModulo = ManejoModulo.getInstancia().getModulo(3).getCodigo();
            String nombreModulo = ManejoModulo.getInstancia().getModulo(3).getNombre();

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

    private static final Logger LOG = Logger.getLogger(ModuloVentaController.class.getName());

    private void activarOpciones() {

        if (codigoRol == 1) {//rol de administrador

            for (Node n : vbMenu.getChildren()) {
                n.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, 3);

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
                    .getInstancia().getRolMenuModulo(codigoRol, 3);

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
    private void btnListaDePrecioActionevent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/listaPrecio/listaDePrecio.fxml"));
            lbModuloVenta.setText(" Modulo de Venta -> Lista de Precio");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnCambiarPrecioActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/listaPrecio/CambiarPrecioPorLote.fxml"));
            lbModuloVenta.setText(" Modulo de Venta -> Cambiar Precio Por Lote ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnCotizacionActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/cotizacion/CotizacionDeVenta.fxml"));
            lbModuloVenta.setText(" Modulo de Venta -> Cotización de Venta ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnConsultaCotizacionActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/cotizacion/ConsultaCotizacionDeVenta.fxml"));
            lbModuloVenta.setText(" Modulo de Venta -> Cotización de Venta ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void btnDescuentoPorUsuarioActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/descuento/DescuentoPorUsuario.fxml"));
            lbModuloVenta.setText(" Modulo de Venta -> Descuento Por Usuario ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnVendedorActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/vendedor/Vendedor.fxml"));
            lbModuloVenta.setText(" Modulo de Venta -> Registro de Vendedor ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnConduceActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/conduce/Conduce.fxml"));
            lbModuloVenta.setText(" Modulo de Venta -> Crear Conduce ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rptReporteNcfActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/ReporteNcfEntreFecha.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void inicializarMenu() {

        AdministrarMenu.getInstancia().agregarOpciones(vbMenu, 3);
        AdministrarMenu.getInstancia().activarOpciones(vbMenu, 3, codigoRol);

        // agregarOpciones(vbMenu, 3) Le pasamos como algumentos el menu de la interface  y el codigo del modulo
        /* activarOpciones(vbMenu, 3, codigoRol) Le pasamos como algumento el menu de la interface  
           el codigo del modulo y el codigo del Rol de usaurio */
    }

    private void btnNotaCreditoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/notaCredito/NotaDeCredito.fxml"));
            lbModuloVenta.setText(" Modulo de Venta -> Crear Nota Credito ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnFacturaPorCorreoActionActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/facturar/FacturaParaEnvierPorCorreo.fxml"));
            lbModuloVenta.setText(" Modulo de Venta -> Enviar Factura Por Corrreo ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnNotaDebitoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/notaDebito/NotaDebito.fxml"));
              lbModuloVenta.setText(" Modulo de Venta -> Crear Nota de Debito ");
            bpVenta.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
