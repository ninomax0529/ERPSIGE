/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.principal;

import com.jfoenix.controls.JFXButton;
import controlador.contrato.ModuloDeContratoController;
import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.ManejoConfiguracion;
import manejo.contabilidadd.ModuloDao;
import manejo.factura.ManejoRelacionNcf;
import manejo.factura.ManejoTipoNcf;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import modelo.Modulo;
import modelo.RelacionNcf;
import modelo.RolMenuModulo;
import modelo.TipoNcf;
import sige.FXMain;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLModulosController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private JFXButton btnInventario;
    @FXML
    private JFXButton btnCxc;
    @FXML
    private JFXButton btnConfiguracion;
    @FXML
    private JFXButton btnPuntoVenta;
    @FXML
    private JFXButton btnOrdenCompra;
    @FXML
    private JFXButton btnContabilidad;
    @FXML
    private JFXButton btnConsultaVenta;
    @FXML
    private VBox vlModulos;
    @FXML
    private JFXButton btnCajaChica;
    @FXML
    private AnchorPane achMenu;
    int codigoRol;
    @FXML
    private JFXButton btnPedidos;
    @FXML
    private JFXButton btnCxP;
    @FXML
    private JFXButton btnContrato;
    @FXML
    private Label lbUnidadNegocio;
    @FXML
    private Label lbUsuario;
    @FXML
    private HBox hbMenuModulo;
    @FXML
    private JFXButton btnNomina;
    @FXML
    private JFXButton btnReportes;
    @FXML
    private JFXButton btnBanco;
    @FXML
    private JFXButton btnModuloAlquilerEquipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lbUnidadNegocio.setText(VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre());
        lbUsuario.setText("Usuario : " + VariablesGlobales.USUARIO.getNombre());

        int ung = VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        RelacionNcf relacionNcf = null;

        codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
        System.out.println("Codigo ROl " + codigoRol);

        int disponible = 0;
        int avisoNcf = ManejoConfiguracion.getInstancia().getConfiguracion().getAvisoNcfDisponible();
        StringBuilder stb = new StringBuilder();

        System.out.println("1- avisoNcf : " + avisoNcf + " disponible : " + disponible);

        //  Cuando el codigoRol  es igual a 1 es el administrador; entonce se activan todos los modulos
        if (!(codigoRol == 1)) {

            activarModulo();
        } else {

            List<TipoNcf> lista = ManejoTipoNcf.getInstancia().getListaTipoNcf();

            for (TipoNcf t : lista) {

                relacionNcf = ManejoRelacionNcf.getInstancia().getNCFUnidadDeNegocio(t.getCodigo(), ung);

                if (relacionNcf == null && (ung == 1 || ung == 2)) {

                    ung = 3;
                }

                disponible = ManejoRelacionNcf.getInstancia().getDisponibilidad(t.getCodigo(), ung) == null ? 0
                        : ManejoRelacionNcf.getInstancia().getDisponibilidad(t.getCodigo(), ung);

                System.out.println("2- avisoNcf : " + avisoNcf + " disponible : " + disponible);

                if (disponible > 0) {
                    
                    if (disponible <= avisoNcf) {
                        stb.append(" QUEDAN  " + disponible + "  NCF DISPONIBLE DE LA SERIE   ")
                                .append(t.getPrefijo())
                                .append(" \n ")
                                .append(" \n ");
                    }
                }

            }

            System.out.println("3- avisoNcf : " + avisoNcf + " disponible : " + disponible);
//            if (disponible > 0) {
//
//                if (disponible <= avisoNcf) {
            ClaseUtil.mensaje(stb.toString(), " NCF DISPONIBLE  DE LA  UNIDAD DE NEGOCIO \n \n " + lbUnidadNegocio.getText().toUpperCase());
//                }
//            }

            activarModulo(codigoRol);
        }

        contratoVencido();

    }

    public void visualizarProyecto() {

        ImageView img = null;
        File imageFile;

//        vbProyecto.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());
        for (int i = 0; i < vlModulos.getChildren().size(); i++) {

            Button bt = (Button) vlModulos.getChildren().get(i);

//               imgProyecto.setImage((new Image((Image)bt.getGraphic(). .toURI().toString(), 130, 100, false, false)));
//            Button bt = (Button) hbCategoria.getChildren().get(i);
            bt.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

            bt.setOnMouseClicked((event) -> {

//                imagenProyecto(bt.getId());
                try {

                    if (event.getClickCount() == 2) {

                        String ruta = bt.getId();
                        System.out.println("Ruta " + ruta + " ruta buton " + bt.getId());
                        FXMain.vistaModulo(ruta);

//                        FXMain.vistaModulo("/vista/venta/facturacion/FacturacionTochHorizontal_1.fxml");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

        }

    }

    private void crearProyecto() {

        Button btn;
        ImageView img = null;
        File imageFile = null;
        URL ruta_img = null;
        vlModulos.getChildren().clear();
//        vbProyecto.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

        //listaProyectoo.addAll(BaseDeDato.getInsatancia().getBaseDatoProyecto());
        List<Modulo> lista = ModuloDao.getInstancia().getModulo();

        for (int i = 0; i < lista.size(); i++) {

//            if (lista.get(i).equalsIgnoreCase("db_iki_1")) {
//
//                imageFile = new File("config/ikiuno.png");
//
//            } else if (lista.get(i).equalsIgnoreCase("db_misereor")) {
//
//                imageFile = new File("config/misereor.png");
//
//            } else if (lista.get(i).equalsIgnoreCase("db_iki_2")) {
//
//                imageFile = new File("config/iki_2.png");
//
//            } else if (lista.get(i).equalsIgnoreCase("db_comercializacion")) {
//
//                imageFile = new File("config/comercializacion.png");
//
//            } else {
//
//                imageFile = new File("config/naturaleza.png");
//            }
//            img = new ImageView(new Image(imageFile.toURI().toString(), 130, 100, false, false));
//            img.setFitWidth(120);
//            img.setFitHeight(100);
//            btn = new Button(lista.get(i), img);
            btn = new Button(lista.get(i).getNombre());

            btn.setId(lista.get(i).getRuta());
//            btn.setCursor(Cursor.HAND);
            btn.setPrefSize(250, 100);
            btn.setMinWidth(200);
            btn.setMaxWidth(250);
//            imgProyecto.setImage((new Image(imageFile.toURI().toString(), 130, 100, false, false)));

            vlModulos.getChildren().add(btn);

        }

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.

    }

    private void btnRegistrarCliente(ActionEvent event) throws IOException {
        FXMain.vistaModuloVenta();
    }

    @FXML
    private void btnInventarioActionEvent(ActionEvent event) throws IOException {

        FXMain.vistaModuloInventario();

    }

    @FXML
    private void btnCxcActionEvent(ActionEvent event) throws IOException {

        FXMain.vistaModuloCxC();
    }

    @FXML
    private void btnConfiguracionAcionEvent(ActionEvent event) throws IOException {

        FXMain.vistaModuloConfiguracion();

    }

    @FXML
    private void btnPuntoVentaActionEvent(ActionEvent event) throws IOException {

        if (ManejoConfiguracion.getInstancia().getConfiguracion().getFacturarSinApertuaraDeCaja() == false) {

            ClaseUtil.mensaje(" Para Empezar ha facturar debes abrir la caja chica ");

            return;
        }

        FXMain.vistaModuloPuntoVenta();

    }

    @FXML
    private void btnOrdenCompraActionevent(ActionEvent event) throws IOException {

        FXMain.vistaModuloDeCompra();

    }

    @FXML
    private void btnContabilidadActionEvent(ActionEvent event) throws IOException {

        FXMain.vistaModuloContabilidad();

    }

    @FXML
    private void btnConsultaVentaActionEvent(ActionEvent event) throws IOException {
        FXMain.vistaModuloVenta();
    }

    @FXML
    private void btnCajaChicaActionevent(ActionEvent event) throws IOException {

        FXMain.vistaModuloCajaChica();
    }

    private void activarModulo() {

        List<Modulo> listaModulo = ManejoModulo.getInstancia().getLista(codigoRol);

        for (Node n : hbMenuModulo.getChildren()) {

            if (!(n.getId() == null)) {

                System.out.println("n.getId() " + n.getId());
                for (Modulo modulo : listaModulo) {

                    System.out.println("modulo.getIdModulo() " + modulo.getIdModulo());
                    if (n.getId().equals(modulo.getIdModulo())) {

                        n.setDisable(false);

                    }
                }

            }

        }
//Modulos desactivado por el desarrollador
//        btnOrdenCompra.setDisable(true);
//        btnContabilidad.setDisable(true);
//        btnCxP.setDisable(true);
//        btnPedidos.setDisable(true);
//
//        btnOrdenCompra.setVisible(false);
//        btnContabilidad.setVisible(false);
//        btnCxP.setVisible(false);
//        btnPedidos.setVisible(false);
    }

    private void activarModulo(int codigoRol) {

//        List<Modulo> listaModulo = ManejoModulo.getInstancia().getLista(codigoRol);
        for (Node n : hbMenuModulo.getChildren()) {

            if (!(n.getId() == null)) {

//                for (Modulo modulo : listaModulo) {
//
//                    if (n.getId().equals(modulo.getIdModulo())) {
//
                n.setDisable(false);
//
//                    }
//                }

            }

        }

        //Modulo desactivado por el desarrolador
//        btnOrdenCompra.setDisable(true);
//        btnContabilidad.setDisable(true);
//        btnCxP.setDisable(true);
//        btnPedidos.setDisable(true);
//
//        btnOrdenCompra.setVisible(false);
//        btnContabilidad.setVisible(false);
//        btnCxP.setVisible(false);
//        btnPedidos.setVisible(false);
    }

    private void activarOpciones() {

        int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();

        List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                .getInstancia().getRolMenuModulo(codigoRol, 3);

        for (Node n : vlModulos.getChildren()) {

            if (!(n.getId() == null)) {

                System.out.println("Menu " + n.getId());

                for (RolMenuModulo memu : listaRolMenuModulos) {

                    if (n.getId().equals(memu.getMenuModulo().getIdMenu())) {

                        System.out.println("Rol menu " + memu.getMenuModulo().getIdMenu());
                        n.setDisable(!memu.getHabilitado());

                    }
                }

            }

        }
    }

    @FXML
    private void btnPedidosActionEvent(ActionEvent event) throws IOException {

        FXMain.vistaModuloPedido();
    }

    @FXML
    private void btnCxPActionEvent(ActionEvent event) throws IOException {

        FXMain.vistaModuloCxP();
    }

    @FXML
    private void btnContratoActionEvent(ActionEvent event) throws IOException {

        FXMain.vistaModuloContratoDeServicio();
    }

    @FXML
    private void btnNominaActionEvent(ActionEvent event) throws IOException {

        FXMain.vistaModuloNomina();
    }

    private void contratoVencido() {

        if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {

            try {

                Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/contrato/ContratoDeServicioPorVencer.fxml"));

                bp.setCenter(node);

            } catch (IOException ex) {

                Logger.getLogger(ModuloDeContratoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() != 2) {
            btnContrato.setDisable(true);
        }

    }

    @FXML
    private void lbUnidadNegocioMouseClicked(MouseEvent event) {

        contratoVencido();
    }

    @FXML
    private void btnReportesActionEvent(ActionEvent event) throws IOException {
        FXMain.vistaModuloReportes();
    }

    @FXML
    private void btnBancoActionEvent(ActionEvent event) throws IOException {
        FXMain.vistaModuloBanco();
    }

    @FXML
    private void btnModuloAlquilerEquipoActionEvent(ActionEvent event) throws IOException {
        
          FXMain.vistaModuloAlquilerEquipos();
    }

}
