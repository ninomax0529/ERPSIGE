/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario;

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
import reporte.inventario.RptInventario;
import sistema.AdministrarMenu;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ModuloInventarioController implements Initializable, InicializarMenu {

    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuItem imArticulo;
    @FXML
    private MenuItem imCategoria;
    @FXML
    private Label lbModulo;
    @FXML
    private JFXButton btnAlmacen;
    @FXML
    private JFXButton btnArticulo;
    @FXML
    private JFXButton btnEntradaInventario;
    private JFXButton button;
    @FXML
    private MenuItem imUnidad;
    @FXML
    private MenuItem imSubCategoria;
    @FXML
    private MenuItem menuRptInventario;
    @FXML
    private MenuItem menuRptValorInventario;
    @FXML
    private MenuItem menuRptEntradaInvetario;
    @FXML
    private MenuItem menuRptMovimientoInventario;
    @FXML
    private JFXButton btnSolicitudSalida;
    @FXML
    private JFXButton btnSalidaInventario;
    @FXML
    private JFXButton btnAjusteDeInventario;
    @FXML
    private JFXButton btnListaDePrecio;
    @FXML
    private MenuItem menuRptListaDeArticulo;
    @FXML
    private MenuItem menuRptPrecioDeLista;
    @FXML
    private VBox vbMenu;
    @FXML
    private MenuBar menuBar;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    @FXML
    private MenuItem imSubInvetarioInicial;
    @FXML
    private JFXButton btnCierreInventario;
    @FXML
    private JFXButton btnDevolucionInventario;
    @FXML
    private JFXButton btnTransferenciaAlmacen;
    @FXML
    private MenuItem imRegistroLoteProduccion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//
//        btnSolicitudSalida.setVisible(false);
//        btnSalidaInventario.setVisible(false);

        try {

            agregarOpcionesMenu();         
            activarOpcionesMenu();
            
            inicializarMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void imArticuloEventAction(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/Articulo_1.fxml"));
            lbModulo.setText("Modulo de Inventario -> Articulo");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void imCategoriaEventAction(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/Categoria.fxml"));
            lbModulo.setText("Modulo de Inventario -> Categoria");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menuPrincipalMouseClick(MouseEvent event) {

    }

    @FXML
    private void btnAlmacenActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/Almacen.fxml"));
            lbModulo.setText(" Modulo de Inventario -> Almacen ");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnRegistrarArticulo(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/Articulo_1.fxml"));

            lbModulo.setText(" Modulo de Inventario -> Articulo ");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnEntradaInventarioActionevent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/entrada/EntradaInventario.fxml"));
            lbModulo.setText(" Modulo de Inventario -> Entrada de Inventario ");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void imUnidadActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/Unidad.fxml"));
            lbModulo.setText(" Modulo de Inventario -> Unidad");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void imSubCategoriaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/SubCategoria.fxml"));
            lbModulo.setText(" Modulo de Inventario -> Sub Categoria");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void imSubInvetarioInicialActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/entrada/InventarioFinal.fxml"));
            lbModulo.setText(" Modulo de Inventario -> Inventario Inicial");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnSolicitudSalidaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/salida/SolicitudSalidaInventario.fxml"));
            lbModulo.setText(" Modulo de Inventario -> Solicitud  Salida de Inventario");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSalidaInventarioActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/salida/FXMLSalidaInventario.fxml"));
            lbModulo.setText(" Modulo de Inventario -> Salida de Inventario");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menuRptInventarioActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/reporte/FXMLInventarioInicial.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void menuRptListaPrecioActionEvent(ActionEvent event) {

        try {

            RptInventario.getInstancia().listaPrecio();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void menuRptValorInventarioActionEvent(ActionEvent event) {

        try {

            RptInventario.getInstancia().costoInventario();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void menuRptEntradaInvetarioActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/reporte/ReporteEntradaInventarioEntreFecha.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void menuRptMovimientoInventarioActionevent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/reporte/ReporteMovimientoInventario.fxml"));

            ClaseUtil.getStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAjusteDeInventarioActionevent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/ajuste/AjusteDeInventario.fxml"));
            lbModulo.setText(" Modulo de Inventario -> Ajuste de Inventario");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnListaDePrecioActionevent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/listaPrecio/listaDePrecio.fxml"));
            lbModulo.setText(" Modulo de Inventario -> Lista de Precio");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void menuRptListaDeArticuloActionEvent(ActionEvent event) {

        try {

            RptInventario.getInstancia().listaPrecio();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void menuRptPrecioDeListaActionEvent(ActionEvent event) {

        try {

            RptInventario.getInstancia().precioDeLista();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Agrega las opciones de menu a la base de datos
    private void agregarOpciones() {

        MenuModulo menuModulo;

        List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(2);
        int codigoModulo = ManejoModulo.getInstancia().getModulo(2).getCodigo();
        String nombreModulo = ManejoModulo.getInstancia().getModulo(2).getNombre();

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

    //Agrega las opciones de menu a la base de datos
    private void agregarOpcionesMenu() {

        MenuModulo menuModulo;

        boolean actualizando = false;

        int codTipoMenu = 0;

        try {

            List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(2);
            int codigoModulo = ManejoModulo.getInstancia().getModulo(2).getCodigo();
            String nombreModulo = ManejoModulo.getInstancia().getModulo(2).getNombre();

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
                        menuModulo.setTipoMenu(ManejoTipoMenu.getInstancia().getTipoMenu(1));

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
                        menuItem.setDisable(true);
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void activarOpciones() {

        if (codigoRol == 1) {//rol de administrador

            System.out.println("el rol es adm " + codigoRol);
            for (Node n : vbMenu.getChildren()) {
                n.setDisable(false);
            }

        } else {

            System.out.println("el rol es " + codigoRol);
            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, 2);

            for (Node n : vbMenu.getChildren()) {

                if (!(n.getId() == null)) {

                    System.out.println(" n.getId() " + n.getId() + " cantidad de menu " + listaRolMenuModulos.size());
                    for (RolMenuModulo memu : listaRolMenuModulos) {

                        System.out.println("memu.getMenuModulo().getIdMenu() " + memu.getMenuModulo().getIdMenu());

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
                    .getInstancia().getRolMenuModulo(codigoRol, 2);

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
    private void btnCierreInventarioActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/cierre/CierreMensualInventario.fxml"));
            lbModulo.setText("Modulo de Inventario -> Cierre Mensual de Inventario");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnDevolucionInventarioActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/devolucion/DevolucionDeInventario.fxml"));
            lbModulo.setText("Modulo de Inventario -> Devolucion de Inventario");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnTransferenciaAlmacenActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/transferencia/Transferencia.fxml"));
            lbModulo.setText("Modulo de Inventario -> Transferencia de Inventario");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void imRegistroLoteProduccionActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/produccion/lote/loteDeProduccion.fxml"));
            lbModulo.setText(" Modulo de Produccion -> Registro Lote de Produccion");
            borderPane.setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloInventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void inicializarMenu() {

        AdministrarMenu.getInstancia().agregarOpciones(vbMenu, 2);
        AdministrarMenu.getInstancia().activarOpciones(vbMenu, 2, codigoRol);

        // agregarOpciones(vbMenu, 1) Le pasamos como algumentos el menu de la interface  y el codigo del modulo
        /* activarOpciones(vbMenu, 1, codigoRol) Le pasamos como algumento el menu de la interface  
           el codigo del modulo y el codigo del Rol de usaurio */
    }

}
