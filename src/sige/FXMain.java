package sige;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author maximilianoa-te
 */
public class FXMain extends Application {

    @Override
    public void stop() throws Exception {
        try {
            super.stop(); //To change body of generated methods, choose Tools | Templates.
            System.out.println("Saliendo del sistema ");
            System.exit(0);
        } catch (Throwable ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static FXMain fXMain;
    public static Stage primaryStage;
    static BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {

        try {

            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Sistema Integrado Para la Gestion Empresarial");
            this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/imagen/img_comtabilidad.png")));

            this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.getButtonTypes().remove(ButtonType.OK);
                    alert.getButtonTypes().add(ButtonType.CANCEL);
                    alert.getButtonTypes().add(ButtonType.YES);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/imagen/img_comtabilidad.png")));
                    alert.setHeaderText("Sistema");
                    alert.setTitle("Salir del Sistema");
                    alert.setContentText(String.format("¿Salir del Sistema ?"));
                    alert.initOwner(primaryStage.getOwner());
                    Optional<ButtonType> res = alert.showAndWait();

                    if (res.isPresent()) {

                        if (res.get().equals(ButtonType.CANCEL)) {
                            event.consume();
                        }
                    }
                }
            });

//        vistaPrincipal();
            vistaLogin();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//     fXMain.setOnCloseRequest(evt -> {
//        // allow user to decide between yes and no
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to close this application?", ButtonType.YES, ButtonType.NO);
//
//        // clicking X also means no
//        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
//        
//        if (ButtonType.NO.equals(result)) {
//            // consume event i.e. ignore close request 
//            evt.consume();
//        }
//    });
    public static void vistaMenu() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/principal/FXMLModulosDelSistema.fxml"));

        Pane vistaInventarioFinal = fXMLLoader.load();

        mainLayout.setCenter(vistaInventarioFinal);

    }

    public static void vistaPrincipal() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
//        fXMLLoader.setLocation(FXMain.class.getResource("/vista/principal/FXMLModulosDelSistema.fxml"));
//        fXMLLoader.setLocation(FXMain.class.getResource("/vista/principal/FXMLModulos_1.fxml"));
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/principal/FXMLModulos.fxml"));
        mainLayout = fXMLLoader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
//        primaryStage.setMaximized(false);
        primaryStage.setResizable(true);
        primaryStage.show();

    }

    public static void vistaPrincipal1() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
//        fXMLLoader.setLocation(FXMain.class.getResource("/vista/principal/FXMLModulosDelSistema.fxml"));
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/principal/FXMLModulos.fxml"));
        mainLayout = fXMLLoader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
//        primaryStage.setMaximized(true);
//        primaryStage.setMaximized(false);
//        primaryStage.setResizable(true);
        primaryStage.show();

    }

    public static void vistaModulo() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/principal/FXMLModulosDelSistema.fxml"));
//        fXMLLoader.setLocation(FXMain.class.getResource("/vista/principal/FXMLModulos.fxml"));
        mainLayout = fXMLLoader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
//        primaryStage.setMaximized(false);
        primaryStage.setResizable(true);
        primaryStage.show();

    }

    public void vistaLogin() throws IOException {

        try {

            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(FXMain.class.getResource("/vista/login/loginUv_1.fxml"));
//        fXMLLoader.setLocation(FXMain.class.getResource("/vista/principal/login.fxml"));
//        mainLayout = fXMLLoader.load();
            VBox anchorPane = fXMLLoader.load();
            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(false);
            primaryStage.setResizable(false);
            primaryStage.show();
            
           

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

       public  static void vistaLoginP() throws IOException {

        try {

            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(FXMain.class.getResource("/vista/login/loginUv.fxml"));
//        fXMLLoader.setLocation(FXMain.class.getResource("/vista/principal/login.fxml"));
//        mainLayout = fXMLLoader.load();
            VBox anchorPane = fXMLLoader.load();
            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(false);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void vistaCliente() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/cliente/pnRegistroCliente.fxml"));
        BorderPane vistaCliente = fXMLLoader.load();
        mainLayout.setCenter(vistaCliente);

    }

    public static void vistaModuloVenta() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/venta/ModuloVenta.fxml"));
        BorderPane vistaCliente = fXMLLoader.load();
        mainLayout.setCenter(vistaCliente);

    }

    public static void vistaModulo(String modulo) throws IOException {

        System.out.println("Modulo " + modulo);

        URL ruta = FXMain.class.getResource(modulo);
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(ruta);
        BorderPane vistaCliente = fXMLLoader.load();
        mainLayout.setCenter(vistaCliente);

    }

    public static void vistaModuloCxC() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/cxc/ModuloCxC.fxml"));
        BorderPane vistaCxc = fXMLLoader.load();
        mainLayout.setCenter(vistaCxc);

    }

    public static void vistaModuloPedido() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/venta/pedido/ModuloPedido.fxml"));
        BorderPane vistaCliente = fXMLLoader.load();
        mainLayout.setCenter(vistaCliente);

    }

    public static void vistaModuloCxP() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/cxp/ModuloCxP.fxml"));
        BorderPane vistaCliente = fXMLLoader.load();
        mainLayout.setCenter(vistaCliente);

    }

    public static void vistaModuloContratoDeServicio() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/contrato/ModuloDeContrato.fxml"));
        BorderPane vistaContrato = fXMLLoader.load();
        mainLayout.setCenter(vistaContrato);

    }

    public static void vistaModuloNomina() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/nomina/ModuloDeNomina.fxml"));
        BorderPane vistaContrato = fXMLLoader.load();
        mainLayout.setCenter(vistaContrato);

    }

    public static void vistaModuloReportes() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/reportes/ModuloReportes.fxml"));
        BorderPane vistaModulo = fXMLLoader.load();
        mainLayout.setCenter(vistaModulo);

    }

    public static void vistaModuloBanco() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/banco/ModuloBanco.fxml"));
        BorderPane vistaModulo = fXMLLoader.load();
        mainLayout.setCenter(vistaModulo);

    }

    public static void vistaModuloCajaChica() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/caja/ModuloCajaChica.fxml"));
        BorderPane vistaCajaChica = fXMLLoader.load();
        mainLayout.setCenter(vistaCajaChica);

    }

    public static void vistaModuloInventario() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/inventario/ModuloInventario.fxml"));
        BorderPane vistaCliente = fXMLLoader.load();
        mainLayout.setCenter(vistaCliente);

    }

    public static void vistaModuloVentaFacturacion() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/venta/Facturacion.fxml"));
        BorderPane vistaCliente = fXMLLoader.load();
        mainLayout.setCenter(vistaCliente);

    }

    public static void vistaModuloPuntoVenta() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/venta/facturacion/FacturacionTochHorizontalAct.fxml"));
//            fXMLLoader.setLocation(FXMain.class.getResource("/vista/venta/facturacion/FacturacionTochHorizontal_1.fxml"));
        VBox vistaCliente = fXMLLoader.load();
        mainLayout.setCenter(vistaCliente);

    }

    public static void vistaModuloContabilidad() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/contabilidad/ModuloContable.fxml"));
        BorderPane vistaCliente = fXMLLoader.load();
        mainLayout.setCenter(vistaCliente);

    }

    public static void vistaModuloDeCompra() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/compra/ModuloDeComprafxml.fxml"));
        BorderPane vistaCliente = fXMLLoader.load();
        mainLayout.setCenter(vistaCliente);

    }

    public static void vistaArticulo() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/articulo/pnRegistroArticulo.fxml"));

        Pane vistaArticulo = fXMLLoader.load();

        mainLayout.setCenter(vistaArticulo);

    }

    public static void vistaPrestamo() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/prestamo/pnRegistroPrestamo_1.fxml"));
//    fXMLLoader.setLocation(FXMain.class.getResource("/vista/prestamo/pnConsultaPrestamo.fxml"));

        Pane vistaPrestamo = fXMLLoader.load();

        mainLayout.setCenter(vistaPrestamo);

    }

    public static void vistaRegistrarPago() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/pago/pnRegistroPago.fxml"));
//    fXMLLoader.setLocation(FXMain.class.getResource("/vista/prestamo/pnConsultaPrestamo.fxml"));

        Pane vistaPrestamo = fXMLLoader.load();

        mainLayout.setCenter(vistaPrestamo);

    }

    public static void vistaConsultaPago() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/pago/pnConsultaPago.fxml"));
//    fXMLLoader.setLocation(FXMain.class.getResource("/vista/prestamo/pnConsultaPrestamo.fxml"));

        Pane vistaConsultaPago = fXMLLoader.load();

        mainLayout.setCenter(vistaConsultaPago);

    }

    public static void vistaCategoria() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/categoria/pnRegistroCategoria.fxml"));

        Pane vistaCategoria = fXMLLoader.load();

        mainLayout.setCenter(vistaCategoria);

    }

    public static void vistaModuloConfiguracion() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/configuracion/ModuloConfiguracion.fxml"));

        Pane moduloconfig = fXMLLoader.load();

        mainLayout.setCenter(moduloconfig);

    }

    public static void vistaConsultaPrestamo() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/consulta/prestamo/pnConsultaPrestamos.fxml"));

        Pane vistaConsultaPrestamo = fXMLLoader.load();

        mainLayout.setCenter(vistaConsultaPrestamo);

    }

    public static void vistaConsultaCliente() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();

        fXMLLoader.setLocation(FXMain.class.getResource("/vista/consulta/cliente/pnConsultaCliente.fxml"));

        Pane vistaConsultaCliente = fXMLLoader.load();

        mainLayout.setCenter(vistaConsultaCliente);

    }

    public static void vistaConsultaArticulo() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();

        fXMLLoader.setLocation(FXMain.class.getResource("/vista/consulta/articulo/pnConsultaArticulo.fxml"));

        Pane vistaConsultaArticulo = fXMLLoader.load();

        mainLayout.setCenter(vistaConsultaArticulo);

    }

    public static void vistaReporteCliente() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/reporte/cliente/pnReporteCliente.fxml"));

        Pane vistaReporteCliente = fXMLLoader.load();

        mainLayout.setCenter(vistaReporteCliente);

    }

    public static void vistaReportePrestamo() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(FXMain.class.getResource("/vista/reporte/prestamo/pnReportePrestamo.fxml"));

        Pane vistaReportePrestamo = fXMLLoader.load();

        mainLayout.setCenter(vistaReportePrestamo);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void runTask() {

        final double wndwWidth = 300.0d;
        Label updateLabel = new Label("Running tasks...");
        updateLabel.setPrefWidth(wndwWidth);
        ProgressBar progress = new ProgressBar();
        progress.setPrefWidth(wndwWidth);

        VBox updatePane = new VBox();
//        updatePane.setPadding(new Insets(10));
        updatePane.setSpacing(5.0d);
        updatePane.getChildren().addAll(updateLabel, progress);

        Stage taskUpdateStage = new Stage(StageStyle.UTILITY);
        taskUpdateStage.setScene(new Scene(updatePane));
        taskUpdateStage.show();

        Task longTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                int max = 50;

                for (int i = 1; i <= max; i++) {
                    if (isCancelled()) {
                        break;
                    }
                    updateProgress(i, max);
                    updateMessage("Task part " + String.valueOf(i) + " complete");

                    Thread.sleep(100);
                }

                return null;
            }
        };

        longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                taskUpdateStage.hide();
            }
        });

        progress.progressProperty().bind(longTask.progressProperty());
        updateLabel.textProperty().bind(longTask.messageProperty());

        taskUpdateStage.show();
        new Thread(longTask).start();
    }

}
