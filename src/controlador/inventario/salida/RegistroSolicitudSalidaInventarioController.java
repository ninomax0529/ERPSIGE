/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.salida;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.inventario.articulo.FXMLBuscarArticuloController;
import controlador.inventario.articulo.RegistroArticuloController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.articulo.ManejoAlmacen;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.inventario.salida.ManejoSolicitudSalida;
import modelo.Adjunto;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.DetalleSolicitudSalidaInventario;
import modelo.ExistenciaArticulo;
import modelo.Solicitante;
import modelo.SolicitudSalidaInventario;
import util.CopiarArchivo;
import util.GestionArchivo;
import util.TooltippedTableCell;

//import util.Utilvale;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroSolicitudSalidaInventarioController implements Initializable {

    @FXML
    private JFXComboBox<ArticuloUnidad> cbUnidad;
    @FXML
    private JFXComboBox<ExistenciaArticulo> cbAlmacen;

    /**
     * @return the solicitante
     */
    public Solicitante getSolicitante() {
        return solicitante;
    }

    /**
     * @param solicitante the solicitante to set
     */
    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

//    private JFXProgressBar progreBar;
    @FXML
    private HBox hbProgresBar;
    @FXML
    private VBox vbBntBuscarArticulo;

    /**
     * @return the articuloAlmacen
     */
    public ExistenciaArticulo getArticuloAlmacen() {
        return articuloAlmacen;
    }

    /**
     * @param articuloAlmacen the articuloAlmacen to set
     */
    public void setArticuloAlmacen(ExistenciaArticulo articuloAlmacen) {
        this.articuloAlmacen = articuloAlmacen;
    }

    @FXML
    private JFXTextField txtNumeroSolicitud;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtArticulo;
    @FXML
    private JFXButton btBuscarArticulo;
    @FXML
    private JFXTextField txtCantidadPedida;
    @FXML
    private JFXButton btnAgregarArticulo;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private TableView<DetalleSolicitudSalidaInventario> tbDetalleSolicitudSalida;
    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, Double> tbcCantidad;
    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, String> tbcUnidad;
    @FXML
    private JFXButton btnBuscarSoliciitante;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXTextField txtSolicitante;
    @FXML
    private TableView<Adjunto> tbAdjunto;
    @FXML
    private TableColumn<Adjunto, String> tbcNombreAdjunto;
    @FXML
    private TableColumn<Adjunto, String> tbcUrlAdjunto;
    @FXML
    private JFXButton btnAgregarAdjunto;
    @FXML
    private JFXButton btnEliminarAdjunto;

    private ExistenciaArticulo articuloAlmacen;
    SolicitudSalidaInventario solicitudSalidaInventario;
    DetalleSolicitudSalidaInventario detalleSolicitudSalidaInventario;
    ObservableList<DetalleSolicitudSalidaInventario> listaDetalle = FXCollections.observableArrayList();
    ObservableList<ArticuloUnidad> listaUnidads = FXCollections.observableArrayList();
    ObservableList<Adjunto> listaAdjunto = FXCollections.observableArrayList();
    ObservableList<ExistenciaArticulo> listaExistenciaArticulo = FXCollections.observableArrayList();

    List<Adjunto> listaAdjuntos;
    Articulo articulo;
    private Solicitante solicitante;
    boolean montada = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nuevo();

        iniciaTablaDetalle();
        iniciaTablaAdjunto();
        inicializarCombox();

        txtCantidadPedida.setOnKeyReleased((event) -> {

            if (event.getCode() == KeyCode.ENTER) {

                try {

                    agregarArticulo();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }

    private void inicializarCombox() {

        cbUnidad.setConverter(new StringConverter<ArticuloUnidad>() {

            @Override
            public String toString(ArticuloUnidad unidad) {
                return String.valueOf(unidad.getUnidad().getDescripcion());
            }

            @Override
            public ArticuloUnidad fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbAlmacen.setConverter(new StringConverter<ExistenciaArticulo>() {

            @Override
            public String toString(ExistenciaArticulo unidad) {
                return String.valueOf(unidad.getAlmacen().getNombre());
            }

            @Override
            public ExistenciaArticulo fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbAlmacen.setItems(listaExistenciaArticulo);
        cbUnidad.setItems(listaUnidads);

    }

    public void iniciaTablaDetalle() {

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcCodigoArticulo.setCellFactory(TooltippedTableCell.forTableColumn());
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcionArticulo"));
        tbcDescripcionArticulo.setCellFactory(TooltippedTableCell.forTableColumn());
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadSolicitada"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcUnidad.setCellFactory(TooltippedTableCell.forTableColumn());
        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });
        tbcCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });

        tbDetalleSolicitudSalida.setItems(listaDetalle);
    }

    private void runTask() {

        ProgressBar progress = new ProgressBar();

        Label lbVerificando = new Label("Verificando Unidad de Archivos Adjuntos");
        lbVerificando.setStyle("  -fx-font-size: 12pt;");
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(progress, lbVerificando);

        hbProgresBar.autosize();
        progress.setPrefWidth(700);
        hbProgresBar.getChildren().add(0, vb);
        btnAgregarAdjunto.setDisable(true);

        Task<Void> longTask = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                //Invoke method that execute long duration tasks 

                montada = GestionArchivo.existeUnidadAdjunto();

                return null;
            }
        };

        //When long task ends
        longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {

                btnAgregarAdjunto.setDisable(false);

//                 montada=true;
                if (montada == false) {

                    ClaseUtil.mensaje("Mensaje");

                } else {

                    agregarAdjunto();
                }
                //Remove progress bar from main pane
                hbProgresBar.getChildren().remove(vb);
            }
        });

        progress.progressProperty().bind(longTask.progressProperty());
        new Thread(longTask).start();

    }

    private void runTaskBuscarArticulo() {

        ProgressBar progress = new ProgressBar();
        FXMLLoader loader = new FXMLLoader();

        vbBntBuscarArticulo.setAlignment(Pos.CENTER);
        vbBntBuscarArticulo.getChildren().add(progress);

        btBuscarArticulo.setDisable(true);

        Task<Void> longTask = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                try {

                    System.out.println("Entre");

                    loader.setLocation(getClass().getResource("/vista/inventario/articulo/FXMLBuscarArticulo.fxml"));
                    loader.load();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        };

        //When long task ends
        longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {

                Parent root = loader.getRoot();

                System.out.println("ESPERANDOOO!!!");

                FXMLBuscarArticuloController articuloController = loader.getController();
                articuloController.setOrigen(3);//el valor  3 es para  buscar solo articulos de consumo

                articuloController.llenarCampo();

                btBuscarArticulo.setDisable(false);

                ClaseUtil.getStageModal(root);

                if (!(articuloController.getArticulo() == null)) {

                    setArticulo(articuloController.getArticulo());

                    txtArticulo.setText(getArticulo().getDescripcion());
                    txtCantidadPedida.requestFocus();
                    listaUnidads.clear();
                    listaExistenciaArticulo.clear();
                    listaUnidads.addAll(ManejoArticuloUnidad.getInstancia().getListaUnidadSslida(getArticulo().getCodigo()));
                    listaExistenciaArticulo.addAll(ManejoExistenciaArticulo.getInstancia()
                            .getExistenciaArticulo(getArticulo().getCodigo()));
                    cbUnidad.setItems(listaUnidads);

                }

                //Remove progress bar from main pane
                vbBntBuscarArticulo.getChildren().remove(progress);
            }
        });

        progress.progressProperty().bind(longTask.progressProperty());
        new Thread(longTask).start();

    }

    private void agregarAdjunto() {

        try {

            Adjunto adjunto = new Adjunto();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.setInitialDirectory(
                    new File(System.getProperty("user.home")));

            fileChooser.getExtensionFilters().addAll(
                    //                      new ExtensionFilter("Text Files", "*.txt"),
                    //                                        new FileChooser.ExtensionFilter("XLS Files", "*.xls")
                    //                    new FileChooser.ExtensionFilter("Imagen Files", "*.jpg", "*.png", "*.jpg")
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );

            try {

                File selectedFile = fileChooser.showOpenDialog(new Stage());

                if (selectedFile != null) {

                    CopiarArchivo.getExtencionArchivo(selectedFile.getAbsolutePath());

                    if (CopiarArchivo.getExtencionArchivo(selectedFile.getAbsolutePath()).equals("exe")) {

                        ClaseUtil.mensaje("Este tipo de archivo  con extencion " + "exe" + " no es valido para ser adjuntado ");
                        return;

                    }

                    adjunto.setUrlOrigen(selectedFile.getAbsolutePath());
                    adjunto.setComentario("adjuntando archivo");
                    adjunto.setFecha(new Date());
                    adjunto.setNombreDocumentoOrigen(selectedFile.getName());
                    adjunto.setTipoDocumento(1);
                    adjunto.setDocumento(1);

                    listaAdjuntos.add(adjunto);
                    listaAdjunto.clear();
                    listaAdjunto.addAll(listaAdjuntos);

                    System.out.println("Paren paht " + selectedFile.getParentFile() + " Paren paht " + selectedFile.getCanonicalFile());
//
//                    Image image = new Image(selectedFile.getCanonicalFile().toURI().toString(), 150, 100, false, false);
//
//                    imgFoto.setImage(image);

                }

            } catch (IOException ex) {

                Logger.getLogger(RegistroArticuloController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void iniciaTablaAdjunto() {

        tbcNombreAdjunto.setCellValueFactory(new PropertyValueFactory<>("nombreDocumentoOrigen"));
        tbcUrlAdjunto.setCellValueFactory(new PropertyValueFactory<>("urlOrigen"));

        tbAdjunto.setItems(listaAdjunto);
    }

    @FXML
    private void btBuscarArticuloActionEvent(ActionEvent event) {

        try {
            runTaskBuscarArticulo();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnAgregarArticuloActionEvent(ActionEvent event) {

        try {

            agregarArticulo();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        if (tbDetalleSolicitudSalida.getSelectionModel().getSelectedIndex() != -1) {

            listaDetalle.remove(tbDetalleSolicitudSalida.getSelectionModel().getFocusedIndex());
            txtCantidad.setText(Integer.toString(listaDetalle.size()));

        }
    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        if (txtSolicitante.getText().isEmpty()) {

            ClaseUtil.mensaje("Tiene que seleccionar un solicitante");
            txtSolicitante.requestFocus();
            return;
        }

        if (listaDetalle.size() <= 0) {

            ClaseUtil.mensaje("No hay articulos agregado para guardar esta solicitud");
            return;
        }

        try {

            solicitudSalidaInventario.setComentario(txtComentario.getText().isEmpty() ? "na" : txtComentario.getText());
            solicitudSalidaInventario.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            solicitudSalidaInventario.setAnulada(false);
            solicitudSalidaInventario.setAutorizada(true);
            solicitudSalidaInventario.setFechaAutorizacion(new Date());
            solicitudSalidaInventario.setCondicion("na");
            solicitudSalidaInventario.setEstado("pendiente");
            solicitudSalidaInventario.setFechaEstado(new Date());
            solicitudSalidaInventario.setFechaRegistro(new Date());
            solicitudSalidaInventario.setNombreAutorizador("");
            solicitudSalidaInventario.setNumeroSolicitud(0);
            solicitudSalidaInventario.setSolicitante(txtSolicitante.getText());
            solicitudSalidaInventario.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            solicitudSalidaInventario.setUsuario(VariablesGlobales.USUARIO);
            solicitudSalidaInventario.setDetalleSolicitudSalidaInventarioCollection(listaDetalle);

            SolicitudSalidaInventario solicituddb = ManejoSolicitudSalida.getInstancia().salvar(solicitudSalidaInventario);

//
            if (!(solicituddb == null)) {

                nuevo();
                ClaseUtil.mensaje("Solicitud guardada correctamente");
            }
//
//                for (Adjunto adjuntoList : listaAdjunto) {
////
//                    try {
//
//                        String ficheroOriginal = adjuntoList.getUrlOrigen(), extensionDocumento, nombreArchivo;
//
//                        extensionDocumento = CopiarArchivo.getExtencionArchivo(ficheroOriginal);
//
//                        nombreArchivo = "SolicitudSalida-" + new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
//
//                        //Esta configuracion esta en sap en la ruta modulo/gestion/utilidades/ConfiguracionCC
////                        String carpetaAdjunto = GestionArchivo.getConfiguracion(3).getNombre();
////                        String ipAdjunto = Utilvale.getConfiguracion(4).getNombre();
//                        String rutaAdjunto = "";
////                         rutaAdjunto = "//" + ipAdjunto + carpetaAdjunto;
////                        System.out.println("Ruta de Adjunto " + rutaAdjunto);
//
//                        File ficheroDestino = new File(rutaAdjunto + nombreArchivo + "." + extensionDocumento);
//
////                        File ficheroDestino = new File("//172.20.1.187/compartido/Adjuntos/" + nombreArchivo + "." + extensionDocumento);
//                        System.out.println("Adjunto " + adjuntoList.getUrlDestino() + " ficheroDestino " + ficheroDestino.getAbsolutePath()
//                                .replace("\\", "/"));
//
//                        adjuntoList.setDocumento(solicituddb.getCodigo());
//                        adjuntoList.setUrlDestino(ficheroDestino.getAbsolutePath().replace("\\", "/"));
//                        adjuntoList.setNombreDocumentoDestino(nombreArchivo);
//                        adjuntoList.setExtensionDocumento(extensionDocumento);
//
//                        Adjunto adjuntoDb = ManejoAdjunto.getInstancia().salvar(adjuntoList);
//
//                        if (!(adjuntoDb == null)) {
//
//                            CopiarArchivo.copiarArchivo(ficheroOriginal.replace("\\", "/"), ficheroDestino.getAbsolutePath().replace("\\", "/"));
//                        }
//
////
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
////                RptSolicitudSalidaInventario.getInstancia().imprimir(solicitudSalidaInventario.getCodigo());
//
//            }
//         

        } catch (Exception e) {

            ClaseUtil.mensaje("Hubo un error guardando el registro");
            e.printStackTrace();
        }

    }

    private boolean existe(DetalleSolicitudSalidaInventario detSolicitud) {

        for (DetalleSolicitudSalidaInventario det : listaDetalle) {

            if (detSolicitud.getArticulo().getCodigo().equals(det.getArticulo().getCodigo())) {

                return true;
            }

        }

        return false;
    }

    private void nuevo() {

        solicitudSalidaInventario = new SolicitudSalidaInventario();
        dpFecha.setValue(LocalDate.now());
        listaDetalle.clear();
        txtComentario.clear();
        txtSolicitante.clear();

        listaAdjuntos = new ArrayList();
        listaAdjunto.clear();

        txtNumeroSolicitud.setText(ManejoSolicitudSalida.getInstancia().getNumero().toString());
    }

    /**
     * @return the articulo
     */
    public Articulo getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @FXML
    private void btnBuscarSoliciitanteActionEvent(ActionEvent event) {

        try {

            System.out.println("Entre");
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/inventario/salida/FXMLBuscarSolicitante.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            System.out.println("ESPERANDOOO!!!");

            FXMLBuscarSolicitanteController articuloController = loader.getController();

            ClaseUtil.getStageModal(root);

            if (!(articuloController.getSolicitante() == null)) {

                setSolicitante(articuloController.getSolicitante());
                txtSolicitante.setText(getSolicitante().getNombre());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void agregarArticulo() {

        if (getArticulo() == null) {

            ClaseUtil.mensaje("Tiene que seleccionar un Articulo");
            return;
        }

        if (cbAlmacen.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un almacen");
            cbAlmacen.requestFocus();
            return;
        }

        if (cbUnidad.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar una unidad");
            cbUnidad.requestFocus();
            return;
        }
        if (txtCantidadPedida.getText().isEmpty()) {

            ClaseUtil.mensaje("Tiene que digital  una cantidad");
            return;
        }

        Double cantidad = Double.parseDouble(txtCantidadPedida.getText());

        Double existencia = 0.00;
        int almacen =cbAlmacen.getSelectionModel().getSelectedItem().getAlmacen().getCodigo();
        int unidadNegocio=VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
        System.out.println("almacen " + almacen);
        existencia = ManejoExistenciaArticulo.getInstancia()
                .getExistenciaArticulo(getArticulo().getCodigo(), almacen,unidadNegocio).getExistencia();

        if (cantidad > existencia) {

            ClaseUtil.mensaje(" La cantidad no puede ser mayor que la existencia .Existencia igual a "
                    + existencia + " " + getArticulo().getUnidadSalida());
            return;
        }

        detalleSolicitudSalidaInventario = new DetalleSolicitudSalidaInventario();

        detalleSolicitudSalidaInventario.setArticulo(getArticulo());
        detalleSolicitudSalidaInventario.setCantidadDespachada(0);
        detalleSolicitudSalidaInventario.setCantidadSolicitada(Double.parseDouble(txtCantidadPedida.getText()));
        detalleSolicitudSalidaInventario.setSolicitudSalidaInventario(solicitudSalidaInventario);
        detalleSolicitudSalidaInventario.setDescripcionArticulo(getArticulo().getDescripcion());
        detalleSolicitudSalidaInventario.setUnidad(cbUnidad.getSelectionModel().getSelectedItem().getUnidad());
        detalleSolicitudSalidaInventario.setAlmacen(cbAlmacen.getSelectionModel().getSelectedItem().getAlmacen());

        if (existe(detalleSolicitudSalidaInventario)) {

            ClaseUtil.mensaje("Este Articulo ya fue agregado");
            return;
        }

        listaDetalle.add(detalleSolicitudSalidaInventario);
        txtCantidadPedida.clear();
        txtArticulo.clear();
        txtCantidad.setText(Integer.toString(listaDetalle.size()));

    }

    public void addValidator(JFXTextField textField) {
        final String pattern = "^(\\d)+(\\.?\\d*)?$";
        final int maxValue = 5;

        textField.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.V) {// Esto es para impedir el Ctrl + V de texto en los campos numéricos
                String textToPaste = Clipboard.getSystemClipboard().getString();

                if (textToPaste != null && !textToPaste.isEmpty()) {
                    String newText = textField.getText() + textToPaste;

                    if (!newText.matches(pattern) || Double.parseDouble(newText) > maxValue) {
                        event.consume();
                    }
                } else {
                    event.consume();
                }
            }
        });

        textField.setOnKeyTyped(event -> {
            String newText = textField.getText() + event.getCharacter();
            if (!newText.matches(pattern) || Double.parseDouble(newText) > maxValue) {
                event.consume();
            }
        });
    }

    @FXML
    private void btnAgregarAdjuntoActionEvent(ActionEvent event) {

        try {
            runTask();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnEliminarAdjuntoActionevent(ActionEvent event) {

        if (!(tbAdjunto.getSelectionModel().getFocusedIndex() == -1)) {

            int indice = tbAdjunto.getSelectionModel().getFocusedIndex();
            listaAdjunto.remove(indice);

        }
    }

    @FXML
    private void cbUnidadActionEvent(ActionEvent event) {
    }

    @FXML
    private void cbAlmacenActionEvent(ActionEvent event) {
    }

}
