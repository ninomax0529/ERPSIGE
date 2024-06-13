/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cxp.avanceCliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.cxc.reciboIngreso.RegistroReciboDeIngresoController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import manejo.cxp.avanceCliente.ManejoAvanceDeCliente;
import modelo.AvanceDeCliente;
import modelo.DetalleAvanceASuplidor;
import modelo.DetalleAvanceDeCliente;
import modelo.EntradaInventario;
import reporte.cxc.RptReciboIngreso;
import reporte.pagos.RptPago;

import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class AvanceDeClienteController implements Initializable {

    @FXML
    private JFXButton btnBuscar;
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
    private TabPane tabPane;

    private JFXCheckBox chImprimir;
    @FXML
    private VBox vbVisorDeProgreso;
    @FXML
    private Label lbProgreso;
    @FXML
    private ProgressIndicator pgIndicador;
    @FXML
    private ProgressBar progresBar;

//    TaskImprimirRecibo taskImprimirRecibo;
    @FXML
    private JFXButton btnRuta;
    @FXML
    private JFXButton btnExportPdf;
    @FXML
    private Label lbRutaArchivoHeder;
    @FXML
    private TableView<AvanceDeCliente> tbAvanve;
    @FXML
    private TableColumn<AvanceDeCliente, AvanceDeCliente> tbcNumero;
    @FXML
    private TableColumn<AvanceDeCliente, String> tbcFechaUltimoAvance;
    @FXML
    private TableColumn<AvanceDeCliente, Double> tbcTotalAvance;
    @FXML
    private TableColumn<AvanceDeCliente, Double> tbcTotalPendiente;
    @FXML
    private TableColumn<AvanceDeCliente, String> tbcCliente;
    @FXML
    private TableView<DetalleAvanceDeCliente> tbDetalleAvanve;
    @FXML
    private TableColumn<DetalleAvanceDeCliente, String> tbcFechaAvanve;
    @FXML
    private TableColumn<DetalleAvanceDeCliente, Double> tbcAvanve;
    @FXML
    private TableColumn<DetalleAvanceDeCliente, String> tbcRecibo;
    @FXML
    private TableColumn<DetalleAvanceDeCliente, DetalleAvanceDeCliente> tbcVerDocumento;

    ObservableList<AvanceDeCliente> listaAvance = FXCollections.observableArrayList();
    ObservableList<DetalleAvanceDeCliente> listaDetaAvance = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtTotalAvance;
    @FXML
    private JFXTextField txtTotalDisponible;
    @FXML
    private JFXTextField txtAvance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTablaRecibo();
        iniciarTablaDetalle();
        iniciazarFiltro();

        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

        buscar();

    }

    private void iniciazarFiltro() {

        FilteredList<AvanceDeCliente> filteredData = new FilteredList<>(tbAvanve.getItems(), p -> true);
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
                } else if (filtro.getCodigo() != null && filtro.getCodigo().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filtro.getCliente() != null && filtro.getCliente().getNombre().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<AvanceDeCliente> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbAvanve.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbAvanve.setItems(sortedData);
    }

    private void iniciarTablaRecibo() {

        // listaEntrada.clear();
        tbAvanve.setItems(listaAvance);
        tbcNumero.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcFechaUltimoAvance.setCellValueFactory(new PropertyValueFactory<>("fechaActualizacion"));
        tbcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tbcTotalAvance.setCellValueFactory(new PropertyValueFactory<>("totalAvance"));
        tbcTotalPendiente.setCellValueFactory(new PropertyValueFactory<>("totalPendiente"));

        tbcCliente.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getCliente().getNombre());
                    }
                    return property;
                });

        totalAvance();
        totalDisponible();

    }

    public void iniciarTablaDetalle() {

        listaDetaAvance.clear();

        tbDetalleAvanve.setItems(listaDetaAvance);

        tbcRecibo.setCellValueFactory(new PropertyValueFactory<>("reciboIngreso"));
        tbcFechaAvanve.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcAvanve.setCellValueFactory(new PropertyValueFactory<>("avance"));

        tbcRecibo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getReciboIngreso().getNumero().toString());
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

        tbcVerDocumento.setCellFactory((TableColumn<DetalleAvanceDeCliente, DetalleAvanceDeCliente> param) -> {

            TableCell<DetalleAvanceDeCliente, DetalleAvanceDeCliente> cellsc = new TableCell<DetalleAvanceDeCliente, DetalleAvanceDeCliente>() {
                @Override
                public void updateItem(DetalleAvanceDeCliente item, boolean empty) {
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

                            RptReciboIngreso.getInstancia().imprimir(item.getReciboIngreso().getCodigo());
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

        if (!(tbAvanve.getSelectionModel().getSelectedIndex() == -1)) {

            listaDetaAvance.clear();
            txtAvance.clear();

            AvanceDeCliente avanve = tbAvanve.getSelectionModel().getSelectedItem();

            listaDetaAvance.addAll(ManejoAvanceDeCliente
                    .getInstancia()
                    .getDetalleAvance(avanve.getCodigo()));

            totalAvanceDetalle();

            if (event.getClickCount() == 2) {

                tabPane.getSelectionModel().select(1);
            }

        }
    }

    private void totalAvance() {

        Double total = 0.00;
        for (AvanceDeCliente det : listaAvance) {

            total += det.getTotalAvance();
        }

        total = ClaseUtil.roundDouble(total, 2);
        txtTotalAvance.setText(total.toString());
    }

    private void totalDisponible() {

        Double total = 0.00;
        for (AvanceDeCliente det : listaAvance) {

            total += det.getTotalPendiente();
        }

        total = ClaseUtil.roundDouble(total, 2);
        txtTotalDisponible.setText(total.toString());
    }

    private void totalAvanceDetalle() {

        Double total = 0.00;
        for (DetalleAvanceDeCliente det : listaDetaAvance) {

            total += det.getAvance();
        }

        total = ClaseUtil.roundDouble(total, 2);
        txtAvance.setText(total.toString());
    }

    @FXML
    private void tbDetalleReciboMouseClicked(MouseEvent event) {

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/cxc/reciboIngreso/RegistroReciboIngreso.fxml"));

        loader.load();

        Parent root = loader.getRoot();

        RegistroReciboDeIngresoController controller = loader.getController();

        controller.getHbDetallePago().setVisible(false);
        controller.getChAvanceCliente().setSelected(true);
        controller.getTxtMontoAvanceCliente().setVisible(true);

        ClaseUtil.getStageModal(root);

        listaAvance.clear();
        listaAvance.addAll(ManejoAvanceDeCliente.getInstancia().getLista());
    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {

//        try {
//
//            if (!(tbReciboIngreso.getSelectionModel().getSelectedIndex() == -1)) {
//
//                Date fechaInicio = ClaseUtil.asDate(dpFechaInicio.getValue()), fechaFinal = ClaseUtil.asDate(dpFechaFinal.getValue());
//                ReciboIngreso recibo = tbReciboIngreso.getSelectionModel().getSelectedItem();
//
//                if (recibo.getAnulado() == true) {
//
//                    util.ClaseUtil.mensaje("ESTE RECIBO YA  ESTA ANULADO");
//                    return;
//                }
//
//                EntradaDiario ent = EntradaDiarioDao.getInstancia()
//                        .getEntradaDiarioPorDocumento(recibo.getCodigo(), 8);
//
//                Optional<ButtonType> ok = util.ClaseUtil.confirmarMensaje("SEGURO QUE QUIERE ANULAR EL  RECIBO NUMERO  " + recibo.getNumero());
//                // + " TAMBIEN SE ANULARA LA ENTRADA DE DIARIO NUMERO  " + ent.getCodigo());
//
//                if (ok.get() == ButtonType.OK) {
//
//                    recibo.setAnulado(true);
//                    ReciboIngreso reciboIngresoDB = ManejoReciboIngreso.getInstancia().actualizar(recibo);
//
//                    if (!(reciboIngresoDB == null)) {
//
//                        List<DetalleReciboIngreso> listaDetalleRecibo = ManejoReciboIngreso
//                                .getInstancia().getDetalleRecibo(recibo.getCodigo());
//
//                        System.out.println("Cantidad " + listaDetalleRecibo.size());
//                        //Actualizando Detalle Recibo
//
//                        for (DetalleReciboIngreso det : listaDetalleRecibo) {
//
//                            Double abono = det.getFactura().getAbono() - det.getTotal();
//                            det.getFactura().setAbono(FormatNum.FormatearDouble(abono, 2));
//
//                            Double pendiente = det.getFactura().getTotal() - det.getFactura().getAbono();
//
//                            System.out.println("Pendiente " + pendiente);
//
//                            det.getFactura().setPendiente(FormatNum.FormatearDouble(pendiente, 2));
//                            det.getFactura().setPagada(false);
//
//                            ManejoFactura.getInstancia().actualizar(det.getFactura());
//
//                        }
//
//                        //Anular entrada de diario de cheque  
//                        if (!(ent == null)) {
//
//                            ent.setAnulada(true);
//                            EntradaDiarioDao.getInstancia().actualizar(ent);
//
//                            DocumentoAnuladoDao.getInstancia()
//                                    .registroDocumentoAnulado(ent.getCodigo(),
//                                            ent.getCodigo().toString(), 8, "ANULACION RECIBO NUMERO ");
//
//                        }
//
//                    }
//
//                    listaAvance.clear();
//                    listadetalle.clear();
//                    listaAvance.addAll(ManejoReciboIngreso.getInstancia()
//                            .getReciboPorFecha(fechaInicio, fechaFinal));
//
//                }
//
//            } else {
//
//                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN REGISTRO");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void buscar() {

        Date fechaini, fechafin;
        fechaini = ClaseUtil.asDate(dpFechaInicio.getValue());
        fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());
        listaAvance.clear();
        listaDetaAvance.clear();

        listaAvance.addAll(ManejoAvanceDeCliente.getInstancia().getLista(fechaini, fechafin));

        totalAvance();
        totalDisponible();
        totalAvanceDetalle();
    }

    @FXML
    private void btnVerDocumentoActionEvent(ActionEvent event) {

//        tareaImprimir();
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

//    private void chImprimirActionEvent(ActionEvent event) {
//
//        if (chImprimir.isSelected()) {
//            for (ReciboIngreso fact : listaAvance) {
//
//                fact.setImprimir(true);
//
//            }
//
//        } else {
//
//            for (ReciboIngreso ri : listaAvance) {
//
//                ri.setImprimir(false);
//
//            }
//        }
//
//        iniciarTablaRecibo();
//    }
//    private void tareaImprimir() {
//
//        try {
//
//            vbVisorDeProgreso.setVisible(true);
//
//            progresBar.setProgress(0);
//            pgIndicador.setProgress(0);
//            pgIndicador.setVisible(true);
//            progresBar.setVisible(true);
//
//            taskImprimirRecibo = new TaskImprimirRecibo();
//            progresBar.progressProperty().unbind();
//
//            progresBar.progressProperty().bind(taskImprimirRecibo.progressProperty());
//
//            pgIndicador.progressProperty().unbind();
//
//            pgIndicador.progressProperty().bind(taskImprimirRecibo.progressProperty());
////
//            lbProgreso.textProperty().unbind();
//            lbProgreso.textProperty().bind(taskImprimirRecibo.messageProperty());
//
//            taskImprimirRecibo.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent t) -> {
//
//                lbProgreso.textProperty().unbind();
//
//                vbVisorDeProgreso.setVisible(false);
//                txtCantidad.setText(Integer.toString(listaAvance.size()));
//
//                taskImprimirRecibo.cancel();
//
//                progresBar.progressProperty().unbind();
//
//                pgIndicador.progressProperty().unbind();
//
//            });
//
//            // Start the Task.
//            new Thread(taskImprimirRecibo).start();
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//    }
    @FXML
    private void btnRutaActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnExportPdfActionEvent(ActionEvent event) {

    }

//
//    private class TaskImprimirRecibo extends Task<List<Void>> {
//
//        @Override
//        protected List<Void> call() throws Exception {
//
//            try {
//
//                List<ReciboIngreso> listaImprimir = new ArrayList<>();
//
//                for (ReciboIngreso ri : listaAvance) {
//
//                    if (ri.getImprimir()) {
//
//                        listaImprimir.add(ri);
//                    }
//
//                }
//
//                int total = listaImprimir.size();
//                int i = 0;
//
//                for (ReciboIngreso fact : listaImprimir) {
//                    i++;
//
//                    imprimirRecibo(fact, i, total);
//                    this.updateProgress(i, total);
//
//                }
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//
//            return null;
//
//        }
//
//        private void imprimirRecibo(ReciboIngreso ri, int i, int total) throws InterruptedException {
//
//            RptReciboIngreso.getInstancia().imprimirPorLote(ri);
//            this.updateMessage(" Procesada  " + i + "  De " + total);
//            Thread.sleep(200);
//        }
//
//    }
}
