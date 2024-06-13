package controlador.inventario.salida;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controlador.inventario.articulo.FXMLBuscarArticuloController;
import controlador.inventario.articulo.RegistroArticuloController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.factura.ManejoFactura;
import manejo.inventario.salida.ManejoSalidaInventario;
import manejo.inventario.salida.ManejoSolicitudSalida;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.Adjunto;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.ExistenciaArticulo;
import modelo.DetalleSalidaInventario;
import modelo.DetalleSolicitudSalidaInventario;
import modelo.SalidaInventario;
import modelo.SecuenciaDocumento;
import modelo.Solicitante;
import modelo.SolicitudSalidaInventario;
import reporte.inventario.salida.RptSalidaDeInventario;
//import reporte.inventario.salida.RptSalidaInventariofxml;
import util.CopiarArchivo;
import util.GestionArchivo;

import utiles.ClaseUtil;
import utiles.VariablesGlobales;

public class FXMLRegistroSalidaInventarioController implements Initializable {

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

    @FXML
    private JFXTextField txtNumeroSolicitud;
    @FXML
    private Label dpFecha;
    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, String> tbcCantidad;
    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, String> tbcUnidad;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXTextField txtSolicitante;

    SolicitudSalidaInventario soliSaliInventa = null;
    DetalleSolicitudSalidaInventario detalleSolicitudSalidaInventario = null;
    ObservableList<DetalleSolicitudSalidaInventario> listaDetalle = FXCollections.observableArrayList();
    ObservableList<DetalleSalidaInventario> listaDetalleSalida = FXCollections.observableArrayList();
    ObservableList<DetalleSalidaInventario> listaDetalleSalidaAgregar = FXCollections.observableArrayList();
    ObservableList<Adjunto> listaDetalleAdjunto = FXCollections.observableArrayList();
//    ObservableList<Adjunto> listaAdjunto = FXCollections.observableArrayList();

    SalidaInventario salidaInventario = null;
    DetalleSalidaInventario detalleSalidaInventario = null;

    @FXML
    private VBox vbBntBuscarArticulo;
    @FXML
    private JFXTextField txtNumeroSalida;
    @FXML
    private TableView<DetalleSolicitudSalidaInventario> tbDetalleSalida;
    @FXML
    private JFXButton btBuscarSolicitud;

    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, String> tbcCheck;
    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, String> tbcExistencia;

    Boolean ValExistencia = false;
    @FXML
    private JFXButton btnLimpiar;
    @FXML
    private JFXButton btnGuardar1;
    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, String> tbcAlmacen;
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
    @FXML
    private HBox hbProgresBar;

    boolean montada = false;
    @FXML
    private JFXTextField txtArticulo;
    @FXML
    private JFXButton btBuscarArticulo;
    @FXML
    private JFXComboBox<ArticuloUnidad> cbUnidad;
    @FXML
    private JFXTextField txtCantidadPedida;
    @FXML
    private JFXButton btnAgregarArticulo;
    @FXML
    private JFXButton btnEliminar;

    Articulo articulo;
    @FXML
    private JFXButton btnBuscarSoliciitante;

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    ObservableList<ArticuloUnidad> listaUnidads = FXCollections.observableArrayList();
    ObservableList<ExistenciaArticulo> listaExistenciaArticulo = FXCollections.observableArrayList();

    private Solicitante solicitante;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarCombox();
        salidaInventario = new SalidaInventario();
//        solicitudSalidaInventario = new SolicitudSalidaInventario();
        detalleSolicitudSalidaInventario = new DetalleSolicitudSalidaInventario();
        detalleSalidaInventario = new DetalleSalidaInventario();
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            dpFecha.setText(format.format(LocalDate.now()).toString());
        } catch (Exception e) {
            dpFecha.setText(LocalDate.now().toString());
        }
        dpFecha.setText(LocalDate.now().toString());
        txtNumeroSalida.setText(ManejoSalidaInventario.getInstancia().getNumero().toString());

        dpFecha.setStyle("-fx-color:Black;");
        iniciaTablaDetalle();
        iniciaTablaAdjunto();

        txtCantidadPedida.setOnKeyReleased((event) -> {

            if (event.getCode() == KeyCode.ENTER) {

                try {

                    if (txtArticulo.getText().isEmpty()) {
                        util.ClaseUtil.mensaje("Tiene que seleccionar un articulo");
                        return;
                    }

                    if (txtCantidadPedida.getText().isEmpty()) {

                        util.ClaseUtil.mensaje(" Tiene que digital una cantidad ");
                        txtCantidadPedida.requestFocus();
                        return;
                    }

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

        cbUnidad.setItems(listaUnidads);
        cbAlmacen.setItems(listaExistenciaArticulo);

    }

    public void iniciaTablaDetalle() {

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcionArticulo"));
        // tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadSolicitada"));

        tbcCantidad.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            if (cellData.getValue() != null) {
                try {
                    property.setValue(ClaseUtil.FormatearNumero(cellData.getValue().getCantidadSolicitada()));
                } catch (Exception e) {
                    property.setValue(String.valueOf(cellData.getValue().getCantidadSolicitada()));
                }
            }
            return property;
        });

        tbcExistencia.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            if (cellData.getValue() != null) {
                try {
                    ExistenciaArticulo articuloAlmacen = ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo(cellData.getValue().getArticulo().getCodigo(), 1);
                    property.setValue(ClaseUtil.FormatearNumero(articuloAlmacen.getExistencia()));

                } catch (Exception e) {
                    property.setValue(String.valueOf(0.00));
                }
            }
            return property;
        });

        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });
        tbcAlmacen.setCellValueFactory(
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
                        property.setValue(cellData.getValue().getArticulo().getNumero().toString());
                    }
                    return property;
                });

        tbcCheck.setCellValueFactory(
                cellData
                -> {
            SimpleStringProperty property = new SimpleStringProperty();
            if (cellData.getValue() != null) {
                try {
                    Boolean vb = false;

                    for (DetalleSolicitudSalidaInventario detalleSolicitudSalidaInventario1 : listaDetalle) {

                        ExistenciaArticulo articuloAlmacen = ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo(cellData.getValue().getArticulo().getCodigo(), 1);

                        if (cellData.getValue().getCantidadSolicitada() <= articuloAlmacen.getExistencia()) {
                            vb = true;
                            break;
                        }
                    }
                    if (vb) {
                        property.set("va");
                    } else {
                        property.set("");
                    }

                } catch (Exception e) {
                }
            }
            return property;
        });

        tbcCheck.setCellFactory((TableColumn<DetalleSolicitudSalidaInventario, String> param) -> {
            TableCell<DetalleSolicitudSalidaInventario, String> cellsc = new TableCell<DetalleSolicitudSalidaInventario, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item != null) {
                        Image image;
                        if (item.isEmpty()) {

                            image = new Image("/imagen/inactive.png");
                        } else {
                            image = new Image("/imagen/active.png");
                        }

                        HBox box = new HBox();
                        VBox vbox = new VBox();
                        vbox.setAlignment(Pos.CENTER);
                        box.setAlignment(Pos.CENTER);
                        ImageView imageview = new ImageView();
                        imageview.setFitHeight(22);
                        imageview.setFitWidth(23);
                        imageview.setImage(image);
                        box.getChildren().addAll(imageview, vbox);
                        setGraphic(box);
                        setText(null);
                    } else {
                        setGraphic(null);
                        setText(null);
                    }
                }
            };
            return cellsc;
        });
        tbDetalleSalida.setItems(listaDetalle);
    }

    public void iniciaTablaAdjunto() {

        tbcNombreAdjunto.setCellValueFactory(new PropertyValueFactory<>("nombreDocumentoOrigen"));
        tbcUrlAdjunto.setCellValueFactory(new PropertyValueFactory<>("urlOrigen"));

        tbAdjunto.setItems(listaDetalleAdjunto);
    }

    @FXML
    public void btnGuardarActionEvent(ActionEvent event) {

        Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("¿Guardar la Salida de Inventario ?");

        if (ok.get() == ButtonType.NO) {
            return;
        }

        if (tbDetalleSalida.getItems().size() <= 0) {

            ClaseUtil.mensaje("Tiene que seleccionar una solicitud ");

            return;
        }
        
        int unidadNegocio=VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        List<DetalleSolicitudSalidaInventario> listDetSoli = null;

        listaDetalleSalida = FXCollections.observableArrayList();
        ValExistencia = false;

        if (!(soliSaliInventa == null)) {

            soliSaliInventa = ManejoSolicitudSalida.getInstancia().getSolicitud(soliSaliInventa.getCodigo());

            listDetSoli = ManejoSolicitudSalida.getInstancia().getDetalleSolicitud(soliSaliInventa);

            listDetSoli.forEach((soliDet) -> {

                ExistenciaArticulo articuloAlmacen = ManejoExistenciaArticulo.getInstancia()
                        .getExistenciaArticulo(soliDet.getArticulo().getCodigo(),
                               soliDet.getAlmacen().getCodigo() ,unidadNegocio);
                
                if (soliDet.getCantidadSolicitada() > articuloAlmacen.getExistencia()) {
                    ValExistencia = true;
                    return;
                }

            });

            if (soliSaliInventa.getEstado().equalsIgnoreCase("Procesado")) {

                ClaseUtil.mensaje("la solicitud fue procesada por otro usuario.");
                txtSolicitante.requestFocus();
                return;
            }
        }

        if (txtSolicitante.getText().isEmpty()) {

            ClaseUtil.mensaje("Tiene que digital un solicitante");
            txtSolicitante.requestFocus();
            return;
        }
//        if (txtNumeroSolicitud.getText().isEmpty()) {
//
//            ClaseUtil.mensaje("Tiene que Buscar un número de solicitud");
//            txtSolicitante.requestFocus();
//            return;
//        }

        if (listaDetalle.size() <= 0) {

            ClaseUtil.mensaje("No hay articulos agregados");
            return;
        }
//
//        if (listaDetalleAdjunto.size() <= 0) {
//
//            ClaseUtil.mensaje("Tiene que agregar un adjunto ");
//            return;
//        }

        //Recargamos el Objecto para obtener los valores mas actualizado posible. 
//           salidaInventario.getDetalleSalidaInventarioCollection().forEach((solicitudDetalle) -> {
//
//            ExistenciaArticulo articuloAlmacen = ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo(solicitudDetalle.getArticulo().getCodigo(), 1);
//            if (solicitudDetalle.getCantidadSolicitada() > articuloAlmacen.getExistencia()) {
//                ValExistencia = true;
//                return;
//            }
//
//        });
        if (ValExistencia) {

            ClaseUtil.mensaje("Hay un Articulo que no tiene la cantidad solicitada en existencia.");
            if (listDetSoli.size() > 0) {
                listaDetalle.clear();
                listaDetalle.addAll(listDetSoli);
            }
            return;
        }

        try {

            salidaInventario = new SalidaInventario();

            salidaInventario.setFecha(ClaseUtil.asDate(LocalDate.now()));

            salidaInventario.setFechaRegistro(new Date());
            salidaInventario.setComentario(txtComentario.getText().isEmpty() ? "NO SE ESTABLECIO UN COMENTARIO" : txtComentario.getText());
            salidaInventario.setFechaContabilizacion(new Date());
            salidaInventario.setMoneda(1);
            salidaInventario.setSolicitante(txtSolicitante.getText());
            salidaInventario.setTipoSalida(1);
            salidaInventario.setProyecto("No se Especifico".toUpperCase());
            salidaInventario.setAnulada(false);
            salidaInventario.setFechaAnulada(new Date());
            salidaInventario.setUsuario(VariablesGlobales.USUARIO);
            salidaInventario.setAnuladaPor(1);
            salidaInventario.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(salidaInventario.getUnidadDeNegocio().getCodigo(), 17);

            if (!(secDoc == null)) {

                boolean existe = ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero());

                if (existe) {

                    System.out.println("existe " + secDoc.getNumero());

                    while (ManejoSalidaInventario.getInstancia().existeSecuencia(secDoc.getNumero())) {

                        secDoc.setNumero(secDoc.getNumero() + 1);
                        ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                    }

                    secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(salidaInventario.getUnidadDeNegocio().getCodigo(), 17);

                    salidaInventario.setNumero(secDoc.getNumero());
                    salidaInventario.setSecuenciaDocumento(secDoc);

                } else {

                    System.out.println("No existe " + secDoc.getNumero());
                    salidaInventario.setNumero(secDoc.getNumero());
                    salidaInventario.setSecuenciaDocumento(secDoc);
                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

            } else {

                ClaseUtil.mensaje("La secuencia para la salida de inventario de la unidad de negocio "
                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return;
            }

            if (soliSaliInventa == null) {

                System.out.println("es nula  solicitudSalidaInventario " + soliSaliInventa);
                listDetSoli = listaDetalle;
//                salidaInventario.setSolicitudSalida(null);

            } else {

                System.out.println("no es solicitudSalidaInventario " + soliSaliInventa);
                salidaInventario.setSolicitudSalida(soliSaliInventa);
            }

            for (DetalleSolicitudSalidaInventario detSoli : listDetSoli) {

                detalleSalidaInventario = new DetalleSalidaInventario();

                ExistenciaArticulo articuloAlmacen = ManejoExistenciaArticulo
                        .getInstancia().getExistenciaArticulo(detSoli.getArticulo().getCodigo(), detSoli.getAlmacen().getCodigo());

                ArticuloUnidad au = ManejoArticuloUnidad.getInstancia().getArticuloUnidadEntrada(detSoli.getArticulo().getCodigo(), detSoli.getUnidad().getCodigo());

                //detalleSalidaInventario.setCodigo(0);
                detalleSalidaInventario.setSalidaInventario(salidaInventario);
                detalleSalidaInventario.setArticulo(detSoli.getArticulo());
                detalleSalidaInventario.setDescripcionArticulo(detSoli.getDescripcionArticulo());
                detalleSalidaInventario.setUnidad(detSoli.getUnidad());
                detalleSalidaInventario.setCantidad(detSoli.getCantidadSolicitada());
                detalleSalidaInventario.setExistencia(articuloAlmacen.getExistencia() - detSoli.getCantidadSolicitada());

                detalleSalidaInventario.setPrecio(au.getCostoUnitario());

                detalleSalidaInventario.setValor(detSoli.getCantidadSolicitada()
                        * au.getCostoUnitario());

                detalleSalidaInventario.setExistenciaAnterior(articuloAlmacen.getExistencia());
                detalleSalidaInventario.setCantidadSolicitada(detSoli.getCantidadSolicitada());
                detalleSalidaInventario.setAlmacen(detSoli.getAlmacen());

                listaDetalleSalida.add(detalleSalidaInventario);
            }

            if (!(soliSaliInventa == null)) {

                salidaInventario.getSolicitudSalida().setCondicion("Procesado");
                salidaInventario.getSolicitudSalida().setEstado("Procesado");
            }

            salidaInventario.setDetalleSalidaInventarioCollection(listaDetalleSalida);

            SalidaInventario salidaDB = ManejoSalidaInventario.getInstancia().salvar(salidaInventario);

            if (!(salidaDB == null)) {

                List<DetalleSalidaInventario> listaDetalleSalida = ManejoSalidaInventario.getInstancia().getDetalleSalidaInventario(salidaDB);

                System.out.println("listaDetalleSalida ... " + listaDetalleSalida);
                //Actualizar inventario 
                if (listaDetalleSalida.size() > 0) {

                    System.out.println("Actualizando ... ");
                    ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorConsumo(listaDetalleSalida);
                    
                } else {
                    System.out.println("no Actualizando ... ");
                }

                RptSalidaDeInventario.getInstancia().imprimir(salidaDB.getCodigo());

                listaDetalle.clear();
                listaDetalleSalida.clear();
                txtNumeroSolicitud.clear();
                listaDetalleAdjunto.clear();
                txtComentario.clear();
                txtSolicitante.clear();

                txtNumeroSalida.setText(ManejoSalidaInventario.getInstancia().getNumero().toString());
                //  ClaseUtil.mensaje("Salida de Inventario guardada correctamente");
//                RptSalidaInventariofxml.getInstancia().imprimir(id);

//                customerSelectCallback.accept(0);
                soliSaliInventa = new SolicitudSalidaInventario();
                detalleSolicitudSalidaInventario = new DetalleSolicitudSalidaInventario();
                salidaInventario = new SalidaInventario();
                detalleSalidaInventario = new DetalleSalidaInventario();

            }
//            } else {
//                
//                ClaseUtil.mensaje("No se ha guardado...");
//            }
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());
            ClaseUtil.mensaje("Hubo un error guardando el registro");

        }

//        Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("¿Guardar la Salida de Inventario ?");
//
//        if (ok.get() == ButtonType.NO) {
//            return;
//        }
//
//        if (tbDetalleSalida.getItems().size() <= 0) {
//
//            ClaseUtil.mensaje("Tiene que seleccionar una solicitud ");
//
//            return;
//        }
//
//        listaDetalleSalida = FXCollections.observableArrayList();
//        ValExistencia = false;
//
//        if (!(solicitudSalidaInventario == null)) {
//
//            solicitudSalidaInventario = ManejoSolicitudSalida.getInstancia().getSolicitud(solicitudSalidaInventario.getCodigo());
//
//            if (solicitudSalidaInventario.getEstado().equalsIgnoreCase("Procesado")) {
//
//                ClaseUtil.mensaje("la solicitud fue procesada por otro usuario.");
//                txtSolicitante.requestFocus();
//                return;
//            }
//        }
//
//        if (txtSolicitante.getText().isEmpty()) {
//
//            ClaseUtil.mensaje("Tiene que digital un solicitante");
//            txtSolicitante.requestFocus();
//            return;
//        }
////        if (txtNumeroSolicitud.getText().isEmpty()) {
////
////            ClaseUtil.mensaje("Tiene que Buscar un número de solicitud");
////            txtSolicitante.requestFocus();
////            return;
////        }
//        if (listaDetalle.size() <= 0) {
//
//            ClaseUtil.mensaje("No hay articulos agregados");
//            return;
//        }
//
//        List<DetalleSolicitudSalidaInventario> listaDetalleSolicitudSalida = null;
//        List<DetalleSalidaInventario> listaDetalleSalida = null;
//
//        if (!(solicitudSalidaInventario == null)) {
//
//            listaDetalleSolicitudSalida = ManejoSolicitudSalida.getInstancia().getDetalleSolicitud(solicitudSalidaInventario);
//
//            listaDetalleSolicitudSalida.forEach((solicitudDetalle) -> {
//
//                ExistenciaArticulo articuloAlmacen = ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo(solicitudDetalle.getArticulo().getCodigo(), 1);
//                if (solicitudDetalle.getCantidadSolicitada() > articuloAlmacen.getExistencia()) {
//                    ValExistencia = true;
//                    return;
//                }
//
//            });
//
//        } else {
//
//            listaDetalleSalida = ManejoSalidaInventario.getInstancia().getDetalleSalidaInventario(salidaInventario);
//
//            listaDetalleSalida.forEach((detSalida) -> {
//
//                ExistenciaArticulo articuloAlmacen = ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo(detSalida.getArticulo().getCodigo(), 1);
//                if (detSalida.getCantidadSolicitada() > articuloAlmacen.getExistencia()) {
//                    ValExistencia = true;
//                    return;
//                }
//
//            });
//        }
//
//        if (ValExistencia) {
//
//            ClaseUtil.mensaje("Hay un Articulo que no tiene la cantidad solicitada en existencia.");
//            if (listaDetalleSolicitudSalida.size() > 0) {
//                listaDetalle.clear();
//                listaDetalle.addAll(listaDetalleSolicitudSalida);
//            }
//            return;
//        }
//
//        try {
//
//            salidaInventario = new SalidaInventario();
//            //  salidaInventario.setCodigo(ManejoSalidaInventario.getInstancia().getNumero().intValue()+1);
//            salidaInventario.setFecha(ClaseUtil.asDate(LocalDate.now()));
//
//            salidaInventario.setFechaRegistro(new Date());
//            salidaInventario.setComentario(txtComentario.getText().isEmpty() ? "NO SE ESTABLECIO UN COMENTARIO" : txtComentario.getText());
//            salidaInventario.setFechaContabilizacion(new Date());
//            salidaInventario.setMoneda(1);
//            salidaInventario.setSolicitante(txtSolicitante.getText());
//            salidaInventario.setTipoSalida(1);
//            salidaInventario.setProyecto("No se Especifico".toUpperCase());
//            salidaInventario.setAnulada(false);
//            salidaInventario.setFechaAnulada(new Date());
//            salidaInventario.setUsuario(VariablesGlobales.USUARIO);
//            salidaInventario.setAnuladaPor(1);
//            //            salidaInventario.set(solicitudSalidaInventario.getNombreAutorizador());
//
//            if (!(solicitudSalidaInventario == null)) {
//
//                salidaInventario.setSolicitudSalida(solicitudSalidaInventario);
//
//                for (DetalleSolicitudSalidaInventario detalleSolicitudSalidaInventario : listaDetalleSolicitudSalida) {
//
//                    detalleSalidaInventario = new DetalleSalidaInventario();
//
//                    ExistenciaArticulo articuloAlmacen = ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo(detalleSolicitudSalidaInventario.getArticulo().getCodigo(), 1);
//
//                    //detalleSalidaInventario.setCodigo(0);
//                    detalleSalidaInventario.setSalidaInventario(salidaInventario);
//                    detalleSalidaInventario.setArticulo(detalleSolicitudSalidaInventario.getArticulo());
//                    detalleSalidaInventario.setDescripcionArticulo(detalleSolicitudSalidaInventario.getDescripcionArticulo());
//                    detalleSalidaInventario.setUnidad(detalleSolicitudSalidaInventario.getUnidad());
//                    detalleSalidaInventario.setCantidad(detalleSolicitudSalidaInventario.getCantidadSolicitada());
//                    detalleSalidaInventario.setExistencia(articuloAlmacen.getExistencia() - detalleSolicitudSalidaInventario.getCantidadSolicitada());
//                    detalleSalidaInventario.setPrecio(detalleSolicitudSalidaInventario.getArticulo().getPrecioCompraUnitario());
//                    detalleSalidaInventario.setValor(detalleSolicitudSalidaInventario.getCantidadSolicitada()
//                            * detalleSolicitudSalidaInventario.getArticulo().getPrecioCompraUnitario());
//
//                    detalleSalidaInventario.setExistenciaAnterior(articuloAlmacen.getExistencia());
//                    detalleSalidaInventario.setCantidadSolicitada(detalleSolicitudSalidaInventario.getCantidadSolicitada());
////                detalleSalidaInventario.setAlmacen(detalleSolicitudSalidaInventario.getAlmacen());
////                detalleSalidaInventario.setUbicacion("ALTICULOS OBSOLETOS");
//                    listaDetalleSalida.add(detalleSalidaInventario);
//                }
//
//                salidaInventario.getSolicitudSalida().setCondicion("Procesado");
//                salidaInventario.getSolicitudSalida().setEstado("Procesado");
//
//                salidaInventario.setDetalleSalidaInventarioCollection(listaDetalleSalida);
//                
//            } else {
//
//                salidaInventario.setSolicitudSalida(new SolicitudSalidaInventario(1));
//
//                 System.out.println(" listaDetalle "+listaDetalle.size());
//                for (DetalleSolicitudSalidaInventario detalleSolicitudSalidaInventario : listaDetalle) {
//
//                    detalleSalidaInventario = new DetalleSalidaInventario();
//
//                    ExistenciaArticulo articuloAlmacen = ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo(detalleSolicitudSalidaInventario.getArticulo().getCodigo(), 1);
//
//                    //detalleSalidaInventario.setCodigo(0);
//                    detalleSalidaInventario.setSalidaInventario(salidaInventario);
//                    detalleSalidaInventario.setArticulo(detalleSolicitudSalidaInventario.getArticulo());
//                    detalleSalidaInventario.setDescripcionArticulo(detalleSolicitudSalidaInventario.getDescripcionArticulo());
//                    detalleSalidaInventario.setUnidad(detalleSolicitudSalidaInventario.getUnidad());
//                    detalleSalidaInventario.setCantidad(detalleSolicitudSalidaInventario.getCantidadSolicitada());
//                    detalleSalidaInventario.setExistencia(articuloAlmacen.getExistencia() - detalleSolicitudSalidaInventario.getCantidadSolicitada());
//                    detalleSalidaInventario.setPrecio(detalleSolicitudSalidaInventario.getArticulo().getPrecioCompraUnitario());
//                    detalleSalidaInventario.setValor(detalleSolicitudSalidaInventario.getCantidadSolicitada()
//                            * detalleSolicitudSalidaInventario.getArticulo().getPrecioCompraUnitario());
//
//                    detalleSalidaInventario.setExistenciaAnterior(articuloAlmacen.getExistencia());
//                    detalleSalidaInventario.setCantidadSolicitada(detalleSolicitudSalidaInventario.getCantidadSolicitada());
////                detalleSalidaInventario.setAlmacen(detalleSolicitudSalidaInventario.getAlmacen());
////                detalleSalidaInventario.setUbicacion("ALTICULOS OBSOLETOS");
//                    listaDetalleSalida.add(detalleSalidaInventario);
//                }
//
//                salidaInventario.setDetalleSalidaInventarioCollection(listaDetalleSalida);
//            }
//
//            SalidaInventario salidaDB = ManejoSalidaInventario.getInstancia().salvar(salidaInventario);
//
//            if (!(salidaDB == null)) {
//
//                List<DetalleSalidaInventario> listaDetalleSalidas = ManejoSalidaInventario.getInstancia().getDetalleSalidaInventario(salidaDB);
//
//                //Actualizar inventario 
//                if (listaDetalleSalidas.size() > 0) {
//
//                    ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorConsumo(listaDetalleSalidas);
//                }
//
//                ClaseUtil.mensaje("Salida guardada correctamente ");
//
////            int id = salidaInventario.getCodigo();//ManejoSalidaInventario.getInstancia().guardarSalidaInventario(salidaInventario);;   
////            if (id > 0) {
//                System.out.println("Entro a adjunto ");
//
//                //Guardando Adjunto
////                for (Adjunto adjuntoList : listaDetalleAdjunto) {
////
////                    System.out.println("Entro a adjunto 1 ");
////
////                    try {
////
////                        // Aqui obtenemos la ip local de la maquina
////                        InetAddress address = InetAddress.getLocalHost();
////                        System.out.println("IP Local :" + address.getHostAddress());
////
////                        String ficheroOriginal = adjuntoList.getUrlOrigen(), extensionDocumento, nombreArchivo;
////
////                        extensionDocumento = CopiarArchivo.getExtencionArchivo(ficheroOriginal);
////
////                        nombreArchivo = "SalidaInventario-" + new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
////
////                        //Esta configuracion esta en sap en la ruta modulo/gestion/utilidades/ConfiguracionCC
//////                        String carpetaAdjunto = Utilvale.getConfiguracion(3).getNombre();
//////                        String ipAdjunto = Utilvale.getConfiguracion(4).getNombre();
////                        String rutaAdjunto = "";
////                        System.out.println("Ruta de Adjunto " + rutaAdjunto);
////
////                        File ficheroDestino = new File(rutaAdjunto + nombreArchivo + "." + extensionDocumento);
////
//////                        File ficheroDestino = new File("//172.20.1.187/compartido/Adjuntos/" + nombreArchivo + "." + extensionDocumento);
////                        System.out.println("SalidaInventario " + adjuntoList.getUrlDestino() + " ficheroDestino " + ficheroDestino.getAbsolutePath()
////                                .replace("\\", "/"));
////
////                        adjuntoList.setDocumento(id);
////                        adjuntoList.setUrlDestino(ficheroDestino.getAbsolutePath().replace("\\", "/"));
////                        adjuntoList.setNombreDocumentoDestino(nombreArchivo);
////                        adjuntoList.setIpComputadorOrigen(address.getHostAddress());
////                        adjuntoList.setExtensionDocumento(extensionDocumento);
////
////                        Adjunto adjuntoDb = ManejoAdjunto.getInstancia().salvar(adjuntoList);
////
////                        if (!(adjuntoDb == null)) {
////
////                            CopiarArchivo.copiarArchivo(ficheroOriginal.replace("\\", "/"), ficheroDestino.getAbsolutePath().replace("\\", "/"));
////                        }
////
//////
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////
////                }
//                listaDetalle.clear();
//                listaDetalleSalidas.clear();
//                txtNumeroSolicitud.clear();
//                listaDetalleAdjunto.clear();
//                txtComentario.clear();
//                txtSolicitante.clear();
//
//                txtNumeroSalida.setText(ManejoSalidaInventario.getInstancia().getNumero().toString());
//                //  ClaseUtil.mensaje("Salida de Inventario guardada correctamente");
////                RptSalidaInventariofxml.getInstancia().imprimir(id);
//
////                customerSelectCallback.accept(0);
//                solicitudSalidaInventario = new SolicitudSalidaInventario();
//                detalleSolicitudSalidaInventario = new DetalleSolicitudSalidaInventario();
//                salidaInventario = new SalidaInventario();
//                detalleSalidaInventario = new DetalleSalidaInventario();
//
//            }
////            } else {
////                
////                ClaseUtil.mensaje("No se ha guardado...");
////            }
//        } catch (Exception e) {
//
//            System.out.println(e.getMessage());
//            ClaseUtil.mensaje("Hubo un error guardando el registro \n " + e.getMessage());
//
//        }
    }

    @FXML
    private void btBuscarSolicitudActionEvent(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/inventario/salida/FXMLBuscarSolicitudDeSalida.fxml"));
            loader.load();

            Parent root = loader.getRoot();
            ClaseUtil.getStageModal(root);

            FXMLBuscarSolicitudDeSalidaController salidaController = loader.getController();

            if (!(salidaController.getsolicitudSalidaInventario() == null)) {

                System.out.println("Solicitud salida " + salidaController.getsolicitudSalidaInventario() + " codigo " + salidaController.getsolicitudSalidaInventario().getCodigo());
                soliSaliInventa = new SolicitudSalidaInventario();

                detalleSolicitudSalidaInventario = new DetalleSolicitudSalidaInventario();
                salidaInventario = new SalidaInventario();
                detalleSalidaInventario = new DetalleSalidaInventario();

                soliSaliInventa = salidaController.getsolicitudSalidaInventario();

                listaDetalle.clear();
                listaDetalleSalida.clear();
                listaDetalleAdjunto.clear();

                txtNumeroSolicitud.setText(String.valueOf(salidaController.getsolicitudSalidaInventario().getCodigo()));

                try {

                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    dpFecha.setText(format.format(LocalDate.now()));

                } catch (Exception e) {
                    dpFecha.setText(LocalDate.now().toString());
                }

                List<DetalleSolicitudSalidaInventario> listaDetalleSolicitudSalida = ManejoSolicitudSalida
                        .getInstancia().getDetalleSolicitud(salidaController.getsolicitudSalidaInventario());

                txtSolicitante.setText(salidaController.getsolicitudSalidaInventario().getSolicitante());
                txtComentario.setText(salidaController.getsolicitudSalidaInventario().getComentario());
                listaDetalle.addAll(listaDetalleSolicitudSalida);
//                listaDetalle.addAll(salidaController.getsolicitudSalidaInventario().getDetalleSolicitudSalidaInventarioCollection());

                txtCantidad.setText(String.valueOf(listaDetalle.size()));
                txtNumeroSalida.setText(ManejoSalidaInventario.getInstancia().getNumero().toString());
                btBuscarArticulo.setDisable(true);
                btnBuscarSoliciitante.setDisable(true);

            } else {

                txtNumeroSolicitud.clear();
                btnBuscarSoliciitante.setDisable(false);

                try {

                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    dpFecha.setText(format.format(LocalDate.now()));
                } catch (Exception e) {
                    dpFecha.setText(LocalDate.now().toString());
                }
                txtComentario.clear();
                listaDetalle.clear();
                listaDetalleSalida.clear();
                listaDetalleAdjunto.clear();
                soliSaliInventa = null;
                txtSolicitante.clear();
                txtNumeroSalida.setText(ManejoSalidaInventario.getInstancia().getNumero().toString());
                btBuscarArticulo.setDisable(false);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnLimpiar(ActionEvent event) {
        txtNumeroSolicitud.clear();
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            dpFecha.setText(format.format(LocalDate.now()));
        } catch (Exception e) {
            dpFecha.setText(LocalDate.now().toString());
        }

        txtComentario.clear();
        listaDetalle.clear();
        listaDetalleSalida.clear();
        listaDetalleAdjunto.clear();
        soliSaliInventa = null;
        txtSolicitante.clear();
        txtNumeroSolicitud.setText(ManejoSalidaInventario.getInstancia().getNumero().toString());
        soliSaliInventa = new SolicitudSalidaInventario();
        detalleSolicitudSalidaInventario = new DetalleSolicitudSalidaInventario();
        salidaInventario = new SalidaInventario();
        detalleSalidaInventario = new DetalleSalidaInventario();
    }

    private Consumer<Integer> customerSelectCallback;

    public void setCustomerSelectCallback(Consumer<Integer> callback) {
        this.customerSelectCallback = callback;
    }

    private void runTask() {

        ProgressBar progress = new ProgressBar();

        Label lbVerificando = new Label(" Verificando Unidad de Archivos Adjuntos ");
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
            listaDetalleAdjunto.remove(indice);

        }

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
                    adjunto.setTipoDocumento(2);
                    adjunto.setDocumento(1);

                    listaDetalleAdjunto.add(adjunto);

                    System.out.println("Paren paht " + selectedFile.getParentFile() + "Paren paht " + selectedFile.getCanonicalFile());

                }

            } catch (IOException ex) {

                Logger.getLogger(RegistroArticuloController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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
    private void cbUnidadActionEvent(ActionEvent event) {

        txtCantidadPedida.requestFocus();
    }

    @FXML
    private void btnAgregarArticuloActionEvent(ActionEvent event) {

        if (getArticulo() == null) {

            ClaseUtil.mensaje("Tiene que seleccionar un Articulo");
            return;
        }
        if (cbAlmacen.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un almacen ");
            cbUnidad.requestFocus();
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
        int almacen = cbAlmacen.getSelectionModel().getSelectedItem().getAlmacen().getCodigo();
        System.out.println("almacen " + almacen);
        existencia = ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo(getArticulo().getCodigo(), almacen).getExistencia();

        if (cantidad > existencia) {

            ClaseUtil.mensaje(" La cantidad no puede ser mayor que la existencia .Existencia igual a "
                    + existencia + " " + getArticulo().getUnidadSalida());
            return;
        }

        detalleSolicitudSalidaInventario = new DetalleSolicitudSalidaInventario();

        detalleSolicitudSalidaInventario.setArticulo(getArticulo());
        detalleSolicitudSalidaInventario.setCantidadDespachada(0);
        detalleSolicitudSalidaInventario.setCantidadSolicitada(Double.parseDouble(txtCantidadPedida.getText()));
        detalleSolicitudSalidaInventario.setSolicitudSalidaInventario(soliSaliInventa);
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

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        if (!(tbDetalleSalida.getSelectionModel().getSelectedIndex() == -1)) {

            listaDetalle.remove(tbDetalleSalida.getSelectionModel().getSelectedIndex());
            tbDetalleSalida.refresh();
            txtCantidad.setText(Integer.toString(listaDetalle.size()));
        }
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
                articuloController.setOrigen(1);//el valor  3 es para  buscar solo articulos de consumo

                articuloController.llenarCampo();

                btBuscarArticulo.setDisable(false);
                articuloController.llenarCampo();

                ClaseUtil.getStageModal(root);

                if (!(articuloController.getArticulo() == null)) {

                    setArticulo(articuloController.getArticulo());

                    txtArticulo.setText(getArticulo().getDescripcion());
                    txtCantidadPedida.requestFocus();
                    listaUnidads.clear();
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
        int almacen = cbAlmacen.getSelectionModel().getSelectedItem().getAlmacen().getCodigo();

        System.out.println("almacen " + almacen);
          existencia = ManejoExistenciaArticulo.getInstancia().getExistenciaArticulosPorMovimiento(getArticulo().getCodigo(), almacen);
//        existencia = ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo(getArticulo().getCodigo(), almacen).getExistencia();

        if (cantidad > existencia) {

            ClaseUtil.mensaje(" La cantidad no puede ser mayor que la existencia . Existencia igual a "
                    + existencia + " " + getArticulo().getUnidadSalida());
            return;
        }

        detalleSolicitudSalidaInventario = new DetalleSolicitudSalidaInventario();

        detalleSolicitudSalidaInventario.setArticulo(getArticulo());
        detalleSolicitudSalidaInventario.setCantidadSolicitada(Double.parseDouble(txtCantidadPedida.getText()));
        detalleSolicitudSalidaInventario.setCantidadDespachada(Double.parseDouble(txtCantidadPedida.getText()));
        detalleSolicitudSalidaInventario.setAlmacen(cbAlmacen.getSelectionModel().getSelectedItem().getAlmacen());
        detalleSolicitudSalidaInventario.setDescripcionArticulo(getArticulo().getDescripcion());
        detalleSolicitudSalidaInventario.setUnidad(cbUnidad.getSelectionModel().getSelectedItem().getUnidad());
               

        if (existe(detalleSolicitudSalidaInventario)) {

            ClaseUtil.mensaje("Este Articulo ya fue agregado");
            return;
        }

        listaDetalle.add(detalleSolicitudSalidaInventario);
        txtCantidadPedida.clear();
        txtArticulo.clear();
        txtCantidad.setText(Integer.toString(listaDetalle.size()));

    }

    private boolean existe(DetalleSolicitudSalidaInventario detSolicitud) {

        for (DetalleSolicitudSalidaInventario det : listaDetalle) {

            if (detSolicitud.getArticulo().getCodigo().equals(det.getArticulo().getCodigo())) {

                return true;
            }

        }

        return false;
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

                btBuscarSolicitud.setDisable(true);
            } else {
                btBuscarSolicitud.setDisable(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cbAlmacenActionEvent(ActionEvent event) {
    }

}
