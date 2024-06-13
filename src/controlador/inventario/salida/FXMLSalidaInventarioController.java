package controlador.inventario.salida;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.inventario.salida.ManejoSalidaInventario;
import modelo.Adjunto;
import modelo.DetalleSalidaInventario;
import modelo.SalidaInventario;
import reporte.inventario.salida.RptSalidaDeInventario;
//import reporte.inventario.salida.RptSalidaInventariofxml;
import utiles.ClaseUtil;
import util.TooltippedTableCell;

public class FXMLSalidaInventarioController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnVerEntrada;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<SalidaInventario> tbSalidaInventario;
    @FXML
    private TableColumn<SalidaInventario, Date> tbcFecha;
    @FXML
    private TableColumn<SalidaInventario, String> tbcAnulada;
    @FXML
    private TableColumn<SalidaInventario, String> tbcNoSalida;
    @FXML
    private TableColumn<SalidaInventario, String> tbcNoSolicitudSalida;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TableView<DetalleSalidaInventario> tbDetalleSolicitudSalida;
    @FXML
    private TableColumn<DetalleSalidaInventario, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleSalidaInventario, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleSalidaInventario, String> tbcCantidad;
    @FXML
    private TableColumn<DetalleSalidaInventario, String> tbcUnidad;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidadArticulo;
    @FXML
    private JFXTextField txtValorTotal;

    ObservableList<SalidaInventario> listaSalidaInventario = FXCollections.observableArrayList();
    ObservableList<DetalleSalidaInventario> listaDetalle = FXCollections.observableArrayList();
    ObservableList<Adjunto> listaAdjunto = FXCollections.observableArrayList();
    @FXML
    private TableColumn<SalidaInventario, String> tbcSolicitante;
    @FXML
    private TableColumn<DetalleSalidaInventario, String> tbcPrecio;
    @FXML
    private TableColumn<DetalleSalidaInventario, String> tbcTotal;
    private Double valValor = 0.0;
    @FXML
    private JFXCheckBox CKCheckTodo;
    @FXML
    private JFXCheckBox CKCheckActivo;
    @FXML
    private TableView<Adjunto> tbAdjunto;
    @FXML
    private TableColumn<Adjunto, String> tbcNombreAdjunto;
    @FXML
    private TableColumn<Adjunto, String> tbcUrlAdjunto;
    @FXML
    private JFXButton btnVerAdjunto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //agregamos la fecha primero a los controles
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
        dpFechaInicio.setDisable(true);
        dpFechaFinal.setDisable(true);
        CKCheckActivo.setSelected(true);
        CKCheckTodo.setSelected(false);
        btnAnular.setDisable(true);
        btnVerEntrada.setDisable(true);
        //cargamos la data a los tableview con dichas configuraciones
        iniciarTabla();
        iniciarTablaDetalle();
        iniciaTablaAdjunto();

        //filtro
        txtFiltro.setOnKeyReleased(event -> {

        });

        //controles de fecha activos
        CKCheckTodo.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    try {
                        txtFiltro.clear();
                        if (new_val) {
                            dpFechaInicio.setValue(LocalDate.now());
                            dpFechaFinal.setValue(LocalDate.now());
                            dpFechaInicio.setDisable(false);
                            dpFechaFinal.setDisable(false);
                            filtrarPorFechaInicioFinal();
                        } else {
                            dpFechaInicio.setValue(LocalDate.now());
                            dpFechaFinal.setValue(LocalDate.now());
                            dpFechaInicio.setDisable(true);
                            dpFechaFinal.setDisable(true);
                            filtrarPorFechaInicioFinal();
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
        CKCheckActivo.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    try {
                        if (new_val) {
                            CKCheckActivo.setText("Activo");
                        } else {
                            CKCheckActivo.setText("Inactivo");
                        }
                        filtrarPorFechaInicioFinal();

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });

        dpFechaInicio.valueProperty().addListener((ov, oldValue, newValue) -> {
            try {
                filtrarPorFechaInicioFinal();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        dpFechaInicio.valueProperty().addListener((ov, oldValue, newValue) -> {
            try {
                filtrarPorFechaInicioFinal();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

    }

    public void iniciaTablaAdjunto() {

        tbcNombreAdjunto.setCellValueFactory(new PropertyValueFactory<>("nombreDocumentoDestino"));
        tbcUrlAdjunto.setCellValueFactory(new PropertyValueFactory<>("urlDestino"));

        tbAdjunto.setItems(listaAdjunto);
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/salida/FXMLRegistroSalidaInventario.fxml"));

            ClaseUtil.crearStageModal(root);
            Date fi, ff;
            fi = ClaseUtil.asDate(dpFechaInicio.getValue());
            ff = ClaseUtil.asDate(dpFechaFinal.getValue());
            listaSalidaInventario.clear();
            listaSalidaInventario.addAll(ManejoSalidaInventario.getInstancia().getListabyFecha(fi, ff, false));

//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("/vista/inventario/salida/FXMLRegistroSalidaInventario.fxml"));
//            
//            pnAbrirVentana(fxmlLoader, "REGISTRO SALIDA DE INVENTARIO");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {
        try {
            filtrarPorFechaInicioFinal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void filtrarPorFechaInicioFinal() {
        try {
            Date fi, ff;
            fi = ClaseUtil.asDate(dpFechaInicio.getValue());
            ff = ClaseUtil.asDate(dpFechaFinal.getValue());
            listaDetalle.clear();
            listaSalidaInventario.clear();
            if (CKCheckTodo.isSelected()) {
                listaSalidaInventario.addAll(ManejoSalidaInventario.getInstancia().getListabyFecha(fi, ff, !CKCheckActivo.isSelected()));
                txtCantidad.setText(String.valueOf(listaSalidaInventario.size()));
            } else {
                listaSalidaInventario.addAll(ManejoSalidaInventario.getInstancia().getListaSalidaInventario(!CKCheckActivo.isSelected()));
                txtCantidad.setText(String.valueOf(listaSalidaInventario.size()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnVerEntradaActionEvent(ActionEvent event) {
        try {
            if (tbSalidaInventario.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tienes que selccionar una salida");
                return;
            } else {
                RptSalidaDeInventario.getInstancia().imprimir(tbSalidaInventario.getSelectionModel().getSelectedItem().getCodigo());

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {
        try {
            if (!(tbSalidaInventario.getSelectionModel().getSelectedIndex() == -1)) {

                Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("SEGURO QUE QUIERE ANULAR LA SALIDA DE INVENTARIO #No.:" + tbSalidaInventario.getSelectionModel().getSelectedItem().getCodigo() + " SOLICITADA POR: " + tbSalidaInventario.getSelectionModel().getSelectedItem().getSolicitante());
                System.out.println("valor button " + ok.get());
                if (ok.get() == ButtonType.YES) {

                    SalidaInventario salidaInventario = ManejoSalidaInventario.getInstancia().getSalidaInventario(tbSalidaInventario.getSelectionModel().getSelectedItem().getCodigo());
                    if (salidaInventario.getAnulada() == true) {
                        ClaseUtil.mensaje("Esta Salida de Inventario esta anulada ");
                        return;
                    }

                    salidaInventario.getSolicitudSalida().setCondicion("Pendiente");
                    salidaInventario.getSolicitudSalida().setEstado("Pendiente");
                    salidaInventario.setAnulada(true);
                    salidaInventario.setFechaAnulada(new Date());
                    boolean val = true;
                    try {

                        val = ManejoSalidaInventario.getInstancia().anularSalidaInventario(salidaInventario);
                        ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorAnulacionDeConsumo(listaDetalle);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        val = false;
                    }

                    System.out.println("val " + val);

                    if (val) {

                        listaDetalle.clear();
                        listaSalidaInventario.clear();
                        listaSalidaInventario.addAll(ManejoSalidaInventario.getInstancia().getListaSalidaInventario(!CKCheckActivo.isSelected()));
                        txtCantidad.setText(String.valueOf(listaSalidaInventario.size()));

                        ClaseUtil.mensaje("Salida de Inventario anulada de forma exitosamente ");
                    } else {
                        ClaseUtil.mensaje("Salida de Inventario no fue anulada...");
                    }
                }
            } else {
                ClaseUtil.mensaje("Tiene que seleccionar una Salida de Inventario para poder Continuar con el proceso. ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void iniciazarFiltro() {
        try {
            FilteredList<SalidaInventario> filteredData = new FilteredList<>(tbSalidaInventario.getItems(), p -> true);
            txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(conteofisico -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase().trim();
                    if (String.valueOf(conteofisico.getCodigo()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(conteofisico.getSolicitudSalida().getCodigo()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(conteofisico.getFecha()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(conteofisico.getSolicitante()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(conteofisico.getComentario()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<SalidaInventario> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tbSalidaInventario.comparatorProperty());
            tbSalidaInventario.setItems(sortedData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void tbDetalleEntradaInventarioMouseCliked(MouseEvent event) {

    }

    public void iniciarTabla() {

        try {
            valValor = 0.0;
            listaDetalle.clear();

            if (CKCheckTodo.isSelected()) {
                Date fi, ff;
                fi = ClaseUtil.asDate(dpFechaInicio.getValue());
                ff = ClaseUtil.asDate(dpFechaFinal.getValue());
                listaSalidaInventario.addAll(ManejoSalidaInventario.getInstancia().getListabyFecha(fi, ff, !CKCheckActivo.isSelected()));
                txtCantidad.setText(String.valueOf(listaSalidaInventario.size()));
            } else {
                listaSalidaInventario.addAll(ManejoSalidaInventario.getInstancia().getListaSalidaInventario(!CKCheckActivo.isSelected()));
                txtCantidad.setText(String.valueOf(listaSalidaInventario.size()));
            }

            tbSalidaInventario.setItems(listaSalidaInventario);

            txtCantidad.setText(String.valueOf(listaSalidaInventario.size()));
            tbcNoSalida.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            tbcNoSolicitudSalida.setCellValueFactory(new PropertyValueFactory<>("solicitudSalida"));
            tbcNoSolicitudSalida.setCellValueFactory(cellData -> {
                SimpleStringProperty property = new SimpleStringProperty();
                if (cellData.getValue() != null) {
                    try {
                        property.setValue(String.valueOf(cellData.getValue().getSolicitudSalida().getCodigo()));

                    } catch (Exception e) {
                        property.setValue(String.valueOf(0));
                    }
                }
                return property;
            });
            tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tbcFecha.setCellFactory(new Callback<TableColumn<SalidaInventario, Date>, TableCell<SalidaInventario, Date>>() {
                @Override
                public TableCell<SalidaInventario, Date> call(TableColumn<SalidaInventario, Date> param) {
                    TableCell<SalidaInventario, Date> cellsc = new TableCell<SalidaInventario, Date>() {

                        @Override
                        public void updateItem(Date item, boolean empty) {
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            if (item != null) {
                                String val = "";
                                val = format.format(item);
                                //System.out.println(item.toString());
                                setText(val);
                            } else {
                                setText("");
                            }
                        }
                    };
                    return cellsc;
                }
            });

            tbcAnulada.setCellValueFactory(
                    cellData
                    -> {
                SimpleStringProperty property = new SimpleStringProperty();
                if (cellData.getValue() != null) {
                    try {

                        if (!cellData.getValue().getAnulada()) {
                            property.set("va");
                        } else {
                            property.set("");
                        }

                    } catch (Exception e) {
                    }
                }
                return property;
            });

            tbcAnulada.setCellFactory((TableColumn<SalidaInventario, String> param) -> {
                TableCell<SalidaInventario, String> cellsc = new TableCell<SalidaInventario, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null) {
                            Image image;
                            Label label;
                            if (item.isEmpty()) {
                                image = new Image("/imagen/inactive.png");
                                label = new Label("Inactivo  ".toUpperCase());
                            } else {
                                image = new Image("/imagen/active.png");
                                label = new Label("Activo  ".toUpperCase());
                            }

                            HBox box = new HBox();
                            VBox vbox = new VBox();
                            vbox.setAlignment(Pos.CENTER);
                            box.setAlignment(Pos.CENTER);
                            ImageView imageview = new ImageView();
                            imageview.setFitHeight(16);
                            imageview.setFitWidth(17);
                            imageview.setImage(image);
                            box.getChildren().addAll(label, imageview, vbox);
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

            tbcSolicitante.setCellValueFactory(new PropertyValueFactory<>("solicitante"));
            tbcSolicitante.setCellFactory(TooltippedTableCell.forTableColumn());

            iniciazarFiltro();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void iniciarTablaDetalle() {

        try {

            valValor = 0.0;
            tbDetalleSolicitudSalida.setItems(listaDetalle);

            tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
            tbcCodigoArticulo.setCellFactory(TooltippedTableCell.forTableColumn());
            tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcionArticulo"));
            tbcDescripcionArticulo.setCellFactory(TooltippedTableCell.forTableColumn());
            tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
            tbcUnidad.setCellFactory(TooltippedTableCell.forTableColumn());
//
            tbcCodigoArticulo.setCellValueFactory(cellData -> {

                SimpleStringProperty property = new SimpleStringProperty();
                if (cellData.getValue() != null) {
                    try {

                        property.setValue(cellData.getValue().getArticulo().getNumero().toString());

                    } catch (Exception e) {
                        property.setValue("");
                    }
                }
                return property;
            });

            tbcUnidad.setCellValueFactory(cellData -> {

                SimpleStringProperty property = new SimpleStringProperty();
                if (cellData.getValue() != null) {
                    try {

                        property.setValue(cellData.getValue().getUnidad().getDescripcion().toUpperCase());

                    } catch (Exception e) {
                        property.setValue("");
                    }
                }
                return property;
            });

            tbcCantidad.setCellValueFactory(cellData -> {
                SimpleStringProperty property = new SimpleStringProperty();
                if (cellData.getValue() != null) {
                    try {

                        property.setValue(ClaseUtil.FormatearNumero(cellData.getValue().getCantidad()));

                    } catch (Exception e) {
                        property.setValue(String.valueOf(cellData.getValue().getCantidad()));
                    }
                }
                return property;
            });

//            tbcPrecio.setCellFactory(TooltippedTableCell.forTableColumn());
            tbcTotal.setCellValueFactory(cellData -> {
                SimpleStringProperty property = new SimpleStringProperty();
                if (cellData.getValue() != null) {
                    try {
                        property.setValue(ClaseUtil.FormatearNumero(cellData.getValue().getValor()));
                    } catch (Exception e) {
                        property.setValue(String.valueOf(cellData.getValue().getValor()));
                    }
                }
                return property;
            });

            listaDetalle.forEach((detalle) -> {
                valValor = valValor + detalle.getValor();
            });
            txtValorTotal.setText(ClaseUtil.FormatearNumero(valValor));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void pnAbrirVentana(FXMLLoader fxmlLoader, String msg) {
        try {

            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle(msg);

            stage.getIcons().add(new Image(getClass().getResourceAsStream("/imagen/logocc.png")));
            //   stage.initModality(Modality.NONE);
            stage.initStyle(StageStyle.DECORATED);
            // stage.initOwner(FXMain.primaryStage);
            stage.setOnCloseRequest((WindowEvent t) -> {
                filtrarPorFechaInicioFinal();
            });

            FXMLRegistroSalidaInventarioController childController = fxmlLoader.getController();

            childController.setCustomerSelectCallback(customer -> {
                filtrarPorFechaInicioFinal();
            });

            stage.showAndWait();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void tbSalidaInventarioMouseClicked(MouseEvent event) {
        try {

            if (!(tbSalidaInventario.getSelectionModel().getSelectedIndex() == -1)) {

                btnAnular.setDisable(false);
                btnVerEntrada.setDisable(false);

                SalidaInventario salidaInventario = tbSalidaInventario.getSelectionModel().getSelectedItem();
                valValor = 0.0;
                listaDetalle.clear();

                listaDetalle.addAll(ManejoSalidaInventario.getInstancia().getDetalleSalidaInventario(salidaInventario));

                txtComentario.setText(salidaInventario.getComentario());
                txtCantidadArticulo.setText(Integer.toString(listaDetalle.size()));

                listaDetalle.forEach((detalle) -> {

                    valValor = valValor + detalle.getValor();
                });

                txtValorTotal.setText(ClaseUtil.FormatearNumero(valValor));
//                listaAdjunto.clear();

//                listaAdjunto.addAll(ManejoAdjunto.getInstancia().getListaAdjunto(salidaInventario.getCodigo(), 2));
            } else {
                btnAnular.setDisable(true);
                btnVerEntrada.setDisable(true);
                System.out.println("No se ha seleccionado ningun elemento de la lista...");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnVerAdjuntoActionevent(ActionEvent event) {

        if (!(tbAdjunto.getSelectionModel().getSelectedIndex() == -1)) {

            Adjunto adjunto = tbAdjunto.getSelectionModel().getSelectedItem();

            String url = adjunto.getUrlDestino();
            System.out.println("URL " + url);

            try {

                File fileUrl = new File(url);

                if (fileUrl.exists()) {

                    ClaseUtil.abrirArchivo(fileUrl, adjunto);

                } else {

                    ClaseUtil.mensaje("Este archivo " + adjunto.getNombreDocumentoDestino() + " no existen en esta rura " + adjunto.getUrlDestino());
                }

            } catch (Exception ex) {

                Logger.getLogger(FXMLSalidaInventarioController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            ClaseUtil.mensaje("tiene que selccionar un registro ");
        }
    }

    public static void abrirArchivo(File file) throws Exception {

        if (Desktop.isDesktopSupported()) {

            new Thread(() -> {

                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
