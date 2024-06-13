/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cxc.reciboIngreso;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
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
import manejo.ReciboIngreso.ManejoReciboIngreso;
import manejo.contabilidadd.DocumentoAnuladoDao;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.factura.ManejoFactura;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import modelo.DetalleReciboIngreso;
import modelo.EntradaDiario;
import modelo.EntradaInventario;
import modelo.MenuModulo;
import modelo.ReciboIngreso;
import modelo.RolMenuModulo;
import reporte.cxc.RptReciboIngreso;

import utiles.ClaseUtil;
import utiles.FormatNum;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class ReciboIngresoController implements Initializable {

    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidadArticulo;
    @FXML
    private JFXTextField txtValorTotal;
    @FXML
    private TableColumn<EntradaInventario, Date> tbcFecha;
    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private JFXButton btnVerDocumento;
    @FXML
    private TableView<ReciboIngreso> tbReciboIngreso;
    @FXML
    private TableColumn<ReciboIngreso, String> tbcRecibo;
    @FXML
    private TableColumn<ReciboIngreso, String> tbcConcepto;

    @FXML
    private TableColumn<ReciboIngreso, String> tbcCliente;
    @FXML
    private TableColumn<ReciboIngreso, String> tbcAnulado;
    @FXML
    private TableColumn<ReciboIngreso, String> tbcTipoRecibo;
    @FXML
    private TableColumn<ReciboIngreso, ReciboIngreso> tbcImprimir;
    @FXML
    private TableColumn<ReciboIngreso, ReciboIngreso> tbcVerDocumento;
    @FXML
    private TableView<DetalleReciboIngreso> tbDetalleRecibo;
    @FXML
    private TableColumn<DetalleReciboIngreso, String> tbcFActura;
    @FXML
    private TableColumn<DetalleReciboIngreso, String> tbcNcf;
    @FXML
    private TableColumn<DetalleReciboIngreso, DetalleReciboIngreso> tbcVerFactura;

    @FXML
    private TableColumn<DetalleReciboIngreso, Double> tbcPagado;
    @FXML
    private TableColumn<DetalleReciboIngreso, Double> tbcPendiente;
    @FXML
    private TableColumn<DetalleReciboIngreso, Double> tbcMontoFactura;
    @FXML
    private TableColumn<DetalleReciboIngreso, String> tbcFechaFactura;
    @FXML
    private TableColumn<DetalleReciboIngreso, Double> tbcPagoConAvance;

    ObservableList<ReciboIngreso> listaReibo = FXCollections.observableArrayList();
    ObservableList<DetalleReciboIngreso> listadetalle = FXCollections.observableArrayList();
    @FXML
    private TabPane tabPane;

    @FXML
    private JFXCheckBox chImprimir;
    @FXML
    private VBox vbVisorDeProgreso;
    @FXML
    private Label lbProgreso;
    @FXML
    private ProgressIndicator pgIndicador;
    @FXML
    private ProgressBar progresBar;

    TaskImprimirRecibo taskImprimirRecibo;
    @FXML
    private JFXButton btnRuta;
    @FXML
    private JFXButton btnExportPdf;
    @FXML
    private Label lbRutaArchivoHeder;
    @FXML
    private HBox hbPermiso;
    private Integer codigoMenuModulo;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    @FXML
    private TableColumn<?, ?> tbcPagado1;
    @FXML
    private TableColumn<ReciboIngreso, Double> tbcMonto;
    @FXML
    private JFXButton btnExportXLS;

    /**
     * @return the codigoMenuModulo
     */
    public Integer getCodigoMenuModulo() {
        return codigoMenuModulo;
    }

    /**
     * @param codigoMenuModulo the codigoMenuModulo to set
     */
    public void setCodigoMenuModulo(Integer codigoMenuModulo) {
        this.codigoMenuModulo = codigoMenuModulo;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTablaRecibo();
        iniciarTablaDetalle();
        iniciazarFiltro();
        setCodigoMenuModulo(ManejoMenuModulo.getInstancia().getMenuModulo(4, "btnReciboCaja"));

        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

        agregarOpciones();
        activarOpciones();

        buscar();

    }

    private void iniciazarFiltro() {

        FilteredList<ReciboIngreso> filteredData = new FilteredList<>(tbReciboIngreso.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getFecha() != null && filtro.getFecha().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getNumero() != null && filtro.getNumero().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filtro.getCliente() != null && filtro.getCliente().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ReciboIngreso> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbReciboIngreso.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbReciboIngreso.setItems(sortedData);
    }

    private void iniciarTablaRecibo() {

        // listaEntrada.clear();
        tbReciboIngreso.setItems(listaReibo);
        txtCantidad.setText(Integer.toString(listaReibo.size()));

        tbcRecibo.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcCliente.setCellValueFactory(new PropertyValueFactory<>("clinete"));
        tbcAnulado.setCellValueFactory(new PropertyValueFactory<>("anulado"));
        tbcTipoRecibo.setCellValueFactory(new PropertyValueFactory<>("tipoRecibo"));

        tbcAnulado.setCellValueFactory(
                cellData -> {

                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {

                        if (cellData.getValue().getAnulado()) {
                            property.setValue("Si");
                        } else {
                            property.setValue("No");
                        }

                    }
                    return property;
                });

        tbcTipoRecibo.setCellValueFactory(
                cellData -> {

                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {

                        if (cellData.getValue().getTipoRecibo().equals("PF")) {
                            property.setValue("Pago Factura");
                        } else if (cellData.getValue().getTipoRecibo().equals("AV")) {
                            property.setValue("Avance de Cliente");
                        }

                    }
                    return property;
                });

        tbcCliente.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getCliente().getNombre());
                    }
                    return property;
                });

        tbcConcepto.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getConceptoDeCobro() != null) {
                        property.setValue(cellData.getValue().getConceptoDeCobro().getNombre());
                    } else {
                        property.setValue(cellData.getValue().getConcepto());
                    }
                    return property;
                });

        tbcVerDocumento.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcVerDocumento.setCellFactory((TableColumn<ReciboIngreso, ReciboIngreso> param) -> {

            TableCell<ReciboIngreso, ReciboIngreso> cellsc = new TableCell<ReciboIngreso, ReciboIngreso>() {
                @Override
                public void updateItem(ReciboIngreso item, boolean empty) {
                    super.updateItem(item, empty);
                    File imageFile;
                    Image img;
                    ImageView imageview;
                    Label label;

                    if (item != null) {

//                        imageFile = new File(getClass().getResource("/imagen/img_documento.jpg").toString());
                        label = new Label("Componente");
                        imageview = new ImageView(new Image(getClass().getResource("/imagen/img_documento.jpg").toString(), 40, 20, false, false));
                        imageview.setFitWidth(40);
                        imageview.setFitHeight(20);

                        VBox hbox = new VBox();

                        hbox.getChildren().addAll(imageview);

                        hbox.setAlignment(Pos.CENTER);

                        //Evento de la fila 
                        hbox.setOnMouseClicked((event) -> {

                            RptReciboIngreso.getInstancia().imprimir(item.getCodigo());
                        });

                        setGraphic(hbox);
                        setText(null);

                    } else {
                        setGraphic(null);
                        setText(null);
                    }
                }
            };

            return cellsc;
        });

        tbcImprimir.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcImprimir.setCellFactory((TableColumn<ReciboIngreso, ReciboIngreso> param) -> {

            TableCell<ReciboIngreso, ReciboIngreso> cellsc = new TableCell<ReciboIngreso, ReciboIngreso>() {
                @Override
                public void updateItem(ReciboIngreso item, boolean empty) {
                    super.updateItem(item, empty);

                    Button btnHabilitar;

                    if (item != null) {

                        btnHabilitar = new Button();
                        btnHabilitar.setPrefSize(40.0, 15.0);

                        if (item.getImprimir()) {

                            btnHabilitar.setText("SI");

                            btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 10px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 9pt;");

                        } else {

                            btnHabilitar.setText("NO");
                            btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 10px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 9pt;");

                        }

                        HBox hbox = new HBox();

                        hbox.getChildren().add(btnHabilitar);

                        hbox.setAlignment(Pos.CENTER);

                        btnHabilitar.setOnMouseClicked((event) -> {

                            if (item.getImprimir()) {

                                item.setImprimir(false);
                                btnHabilitar.setText("NO");
                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 15px;\n"
                                        + "    -fx-b-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 9pt;");

                            } else {

                                item.setImprimir(true);
                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 9pt;");
                            }

                        });

                        setGraphic(btnHabilitar);
                        setText(null);
                    } else {
                        setGraphic(null);
                        setText(null);
                    }
                }
            };
            return cellsc;
        });

    }

    public void iniciarTablaDetalle() {

        listadetalle.clear();

        tbDetalleRecibo.setItems(listadetalle);

        tbcFActura.setCellValueFactory(new PropertyValueFactory<>("factura"));
//        tbcPagado.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcPendiente.setCellValueFactory(new PropertyValueFactory<>("pendiente"));
        tbcMontoFactura.setCellValueFactory(new PropertyValueFactory<>("montoFactura"));
        tbcPagado.setCellValueFactory(new PropertyValueFactory<>("montoPagado"));
        tbcPagoConAvance.setCellValueFactory(new PropertyValueFactory<>("pagoConAvance"));

        tbcNcf.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getFactura().getNcf());
                    }
                    return property;
                });

        tbcFActura.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getFactura().getNumero().toString());
                    }
                    return property;
                });

        tbcFechaFactura.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getFactura() != null) {
                        property.setValue(new SimpleDateFormat("dd-MM-yyyy")
                                .format(cellData.getValue().getFactura().getFecha()));
                    }
                    return property;
                });

        tbcVerFactura.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcVerFactura.setCellFactory((TableColumn<DetalleReciboIngreso, DetalleReciboIngreso> param) -> {

            TableCell<DetalleReciboIngreso, DetalleReciboIngreso> cellsc = new TableCell<DetalleReciboIngreso, DetalleReciboIngreso>() {
                @Override
                public void updateItem(DetalleReciboIngreso item, boolean empty) {
                    super.updateItem(item, empty);
                    File imageFile;
                    Image img;
                    ImageView imageview;
                    Label label;

                    if (item != null) {

//                        imageFile = new File(getClass().getResource("/imagen/img_documento.jpg").toString());
                        label = new Label("Componente");
                        imageview = new ImageView(new Image(getClass().getResource("/imagen/img_documento.jpg").toString(), 40, 20, false, false));
                        imageview.setFitWidth(40);
                        imageview.setFitHeight(20);

                        VBox hbox = new VBox();

                        hbox.getChildren().addAll(imageview);

                        hbox.setAlignment(Pos.CENTER);

                        //Evento de la fila 
                        hbox.setOnMouseClicked((event) -> {

                            reporte.unidadnegocio.RptFactura.getInstancia().verFactura(item.getFactura().getCodigo(),
                                    item.getFactura().getUnidadDeNegocio().getCodigo());
                        });

                        setGraphic(hbox);
                        setText(null);

                    } else {
                        setGraphic(null);
                        setText(null);
                    }
                }
            };

            return cellsc;
        });

    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        buscar();

    }

    @FXML
    private void tbReciboMouseClicked(MouseEvent event) {

        if (!(tbReciboIngreso.getSelectionModel().getSelectedIndex() == -1)) {

            listadetalle.clear();
            txtCantidadArticulo.clear();
            txtValorTotal.clear();
            ReciboIngreso recibo = tbReciboIngreso.getSelectionModel().getSelectedItem();

            txtComentario.setText(recibo.getConcepto());

            listadetalle.addAll(ManejoReciboIngreso
                    .getInstancia()
                    .getDetalleRecibo(recibo.getCodigo()));

            valorTotal();
            txtCantidadArticulo.setText(Integer.toString(listadetalle.size()));
            if (event.getClickCount() == 2) {

                tabPane.getSelectionModel().select(1);
            }

        }
    }

    private void valorTotal() {

        Double total = 0.00;
        for (DetalleReciboIngreso detalleEntradaInventario : listadetalle) {

            total += detalleEntradaInventario.getTotal();
        }

        txtValorTotal.setText(total.toString());
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/cxc/reciboIngreso/RegistroReciboIngreso.fxml"));

        ClaseUtil.getStageModal(root);

        listaReibo.clear();
        listadetalle.clear();
        buscar();
//        listaReibo.addAll(ManejoReciboIngreso.getInstancia().getReciboIngreso());

    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {

        try {

            if (!(tbReciboIngreso.getSelectionModel().getSelectedIndex() == -1)) {

                Date fechaInicio = ClaseUtil.asDate(dpFechaInicio.getValue()), fechaFinal = ClaseUtil.asDate(dpFechaFinal.getValue());
                ReciboIngreso recibo = tbReciboIngreso.getSelectionModel().getSelectedItem();

                if (recibo.getAnulado() == true) {

                    util.ClaseUtil.mensaje("ESTE RECIBO YA  ESTA ANULADO");
                    return;
                }

                EntradaDiario ent = EntradaDiarioDao.getInstancia()
                        .getEntradaDiarioPorDocumento(recibo.getCodigo(), 8);

                Optional<ButtonType> ok = util.ClaseUtil.confirmarMensaje("SEGURO QUE QUIERE ANULAR EL  RECIBO NUMERO  " + recibo.getNumero());
                // + " TAMBIEN SE ANULARA LA ENTRADA DE DIARIO NUMERO  " + ent.getCodigo());

                if (ok.get() == ButtonType.OK || ok.get() == ButtonType.YES) {

                    recibo.setAnulado(true);
                    ReciboIngreso reciboIngresoDB = ManejoReciboIngreso.getInstancia().actualizar(recibo);

                    if (!(reciboIngresoDB == null)) {

                        List<DetalleReciboIngreso> listaDetalleRecibo = ManejoReciboIngreso
                                .getInstancia().getDetalleRecibo(recibo.getCodigo());

                        System.out.println("Cantidad " + listaDetalleRecibo.size());
                        //Actualizando Detalle Recibo

                        for (DetalleReciboIngreso det : listaDetalleRecibo) {

                            Double abono = det.getFactura().getAbono() - det.getTotal();
                            det.getFactura().setAbono(FormatNum.FormatearDouble(abono, 2));

                            Double pendiente = det.getFactura().getTotal() - det.getFactura().getAbono();

                            System.out.println("Pendiente " + pendiente);

                            det.getFactura().setPendiente(FormatNum.FormatearDouble(pendiente, 2));
                            det.getFactura().setPagada(false);

                            ManejoFactura.getInstancia().actualizar(det.getFactura());

                        }

                        //Anular entrada de diario de cheque  
                        if (!(ent == null)) {

                            ent.setAnulada(true);
                            EntradaDiarioDao.getInstancia().actualizar(ent);

                            DocumentoAnuladoDao.getInstancia()
                                    .registroDocumentoAnulado(ent.getCodigo(),
                                            ent.getCodigo().toString(), 8, "ANULACION RECIBO NUMERO ");

                        }

                    }

                    listaReibo.clear();
                    listadetalle.clear();
                    listaReibo.addAll(ManejoReciboIngreso.getInstancia()
                            .getReciboPorFecha(fechaInicio, fechaFinal));

                }

            } else {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN REGISTRO");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void buscar() {

        Date fechaini, fechafin;
        fechaini = ClaseUtil.asDate(dpFechaInicio.getValue());
        fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());
        listaReibo.clear();
        listadetalle.clear();

        listaReibo.addAll(ManejoReciboIngreso.getInstancia().getReciboPorFecha(fechaini, fechafin));
        txtCantidad.setText(Integer.toString(listaReibo.size()));
        txtCantidadArticulo.clear();
        txtValorTotal.clear();
        iniciazarFiltro();
    }

    @FXML
    private void btnVerDocumentoActionEvent(ActionEvent event) {

        tareaImprimir();
//
//        if (!(tbReciboIngreso.getSelectionModel().getSelectedIndex() == -1)) {
//
//            int codigoRecibo = tbReciboIngreso.getSelectionModel().getSelectedItem().getCodigo();
//            RptReciboIngreso.getInstancia().imprimir(codigoRecibo);
//
//        } else {
//
//            ClaseUtil.mensaje("Tiene que seleccionar un registro");
//        }

    }

    @FXML
    private void chImprimirActionEvent(ActionEvent event) {

        if (chImprimir.isSelected()) {
            for (ReciboIngreso fact : listaReibo) {

                fact.setImprimir(true);

            }

        } else {

            for (ReciboIngreso ri : listaReibo) {

                ri.setImprimir(false);

            }
        }

        iniciarTablaRecibo();
    }

    private void tareaImprimir() {

        try {

            vbVisorDeProgreso.setVisible(true);

            progresBar.setProgress(0);
            pgIndicador.setProgress(0);
            pgIndicador.setVisible(true);
            progresBar.setVisible(true);

            taskImprimirRecibo = new TaskImprimirRecibo();
            progresBar.progressProperty().unbind();

            progresBar.progressProperty().bind(taskImprimirRecibo.progressProperty());

            pgIndicador.progressProperty().unbind();

            pgIndicador.progressProperty().bind(taskImprimirRecibo.progressProperty());
//
            lbProgreso.textProperty().unbind();
            lbProgreso.textProperty().bind(taskImprimirRecibo.messageProperty());

            taskImprimirRecibo.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent t) -> {

                lbProgreso.textProperty().unbind();

                vbVisorDeProgreso.setVisible(false);
                txtCantidad.setText(Integer.toString(listaReibo.size()));

                taskImprimirRecibo.cancel();

                progresBar.progressProperty().unbind();

                pgIndicador.progressProperty().unbind();

            });

            // Start the Task.
            new Thread(taskImprimirRecibo).start();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @FXML
    private void btnRutaActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnExportPdfActionEvent(ActionEvent event) {

    }

    @FXML
    private void btnExportXLSActionEvent(ActionEvent event) {

        try {

            util.ClaseUtil.exportarDAtos(tbReciboIngreso,2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class TaskImprimirRecibo extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            try {

                List<ReciboIngreso> listaImprimir = new ArrayList<>();

                for (ReciboIngreso ri : listaReibo) {

                    if (ri.getImprimir()) {

                        listaImprimir.add(ri);
                    }

                }

                int total = listaImprimir.size();
                int i = 0;

                for (ReciboIngreso fact : listaImprimir) {
                    i++;

                    imprimirRecibo(fact, i, total);
                    this.updateProgress(i, total);

                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;

        }

        private void imprimirRecibo(ReciboIngreso ri, int i, int total) throws InterruptedException {

            RptReciboIngreso.getInstancia().imprimirPorLote(ri);
            this.updateMessage(" Procesada  " + i + "  De " + total);
            Thread.sleep(200);
        }

    }

    //Agrega las opciones de menu a la base de datos
    private void agregarOpciones() {

        MenuModulo menuModulo;

        List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(4);
        int codigoModulo = ManejoModulo.getInstancia().getModulo(4).getCodigo();
        String nombreModulo = ManejoModulo.getInstancia().getModulo(4).getNombre();

        boolean actualizando = false;

        for (Node n : hbPermiso.getChildren()) {

            if (!(n.getId() == null)) {

                menuModulo = new MenuModulo();

                menuModulo.setIdMenu(n.getId());
                menuModulo.setMenu(n.getAccessibleText());
                menuModulo.setModulo(codigoModulo);
                menuModulo.setNombreModulo(nombreModulo);
                menuModulo.setPermiso(getCodigoMenuModulo());
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

    private void activarOpciones() {

        if (codigoRol == 1) {//rol de administrador

            for (Node n : hbPermiso.getChildren()) {
                n.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, 4);

            for (Node n : hbPermiso.getChildren()) {

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

}
