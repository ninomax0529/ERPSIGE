/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cxp.comprobanteDePago;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import manejo.contabilidadd.DocumentoAnuladoDao;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.cxp.ManejoComprobanteDePago;
import modelo.ComprobanteDePago;
import modelo.DetalleComprobanteDePago;
import modelo.DetalleEntradaDiario;
import modelo.DetalleReciboIngreso;
import modelo.EntradaDiario;
import modelo.EntradaInventario;
import reporte.contabilidad.RptLibroContable;
import reporte.cxp.RptFacturaSuplidor;
import reporte.factura.RptFactura;
import reporte.pagos.RptPago;

import utiles.ClaseUtil;
import utiles.FormatNum;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class ComprobanteDePagoController implements Initializable {

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
    private TableView<ComprobanteDePago> tbComprobante;
    @FXML
    private TableColumn<ComprobanteDePago, Integer> tbcComprobante;
    @FXML
    private TableColumn<ComprobanteDePago, String> tbcSuplidor;
    @FXML
    private TableColumn<ComprobanteDePago, String> tbcAnulado;
    @FXML
    private TableColumn<ComprobanteDePago, String> tbcTipoComprobante;
    @FXML
    private TableColumn<ComprobanteDePago, ComprobanteDePago> tbcVerDocumento;
    @FXML
    private TableView<DetalleComprobanteDePago> tbDetalleComprobante;

    @FXML
    private TableColumn<DetalleComprobanteDePago, String> tbcFActura;
    @FXML
    private TableColumn<DetalleComprobanteDePago, Double> tbcPagado;
    @FXML
    private TableColumn<DetalleComprobanteDePago, String> tbcPendiente;
    @FXML
    private TableColumn<DetalleComprobanteDePago, Double> tbcMontoFactura;
    @FXML
    private TableColumn<DetalleComprobanteDePago, Double> tbcPagoConAvance;
    @FXML
    private TableColumn<DetalleComprobanteDePago, DetalleComprobanteDePago> tbcVerFactura;

    ObservableList<ComprobanteDePago> listaReibo = FXCollections.observableArrayList();
    ObservableList<DetalleComprobanteDePago> listadetalle = FXCollections.observableArrayList();
    ObservableList<DetalleEntradaDiario> listaDetalleEnt = FXCollections.observableArrayList();
    @FXML
    private TabPane tabPane;
    @FXML
    private JFXTextField txtNoAsiento;
    @FXML
    private JFXTextField txtNoFactura;
    @FXML
    private JFXDatePicker dpFechaContabilidad;
    @FXML
    private JFXTextField txtConcepto;
    @FXML
    private TableColumn<DetalleEntradaDiario, String> tbcCuenta;
    @FXML
    private TableColumn<DetalleEntradaDiario, String> tbcDescripcion;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbcDebito;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbcCredito;
    @FXML
    private TableView<DetalleEntradaDiario> tbDetalleEntrada;
    EntradaDiario entdiario = null;

    @FXML
    private TextField txtTotalDebito;
    @FXML
    private TextField txtTotalCredito;
    @FXML
    private JFXButton btnVerAsiento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTablaRecibo();
        iniciarTablaDetalle();
        inicializarTablaEntradaDiario();
        iniciazarFiltro();

        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

        buscar();

    }

    public void inicializarTablaEntradaDiario() {

        tbcCuenta.setCellValueFactory(new PropertyValueFactory<>("Cuenta"));
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("DescripcionCuenta"));
        tbcDebito.setCellValueFactory(new PropertyValueFactory<>("Debito"));
        tbcCredito.setCellValueFactory(new PropertyValueFactory<>("Credito"));

        tbDetalleEntrada.setItems(listaDetalleEnt);

    }

    private void iniciazarFiltro() {

        FilteredList<ComprobanteDePago> filteredData = new FilteredList<>(tbComprobante.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(suplidorFiltrocatalogoFiltro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (suplidorFiltrocatalogoFiltro.getFecha() != null && suplidorFiltrocatalogoFiltro.getFecha().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (suplidorFiltrocatalogoFiltro.getCodigo() != null && suplidorFiltrocatalogoFiltro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ComprobanteDePago> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbComprobante.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbComprobante.setItems(sortedData);
    }

    private void iniciarTablaRecibo() {

        // listaEntrada.clear();
        tbComprobante.setItems(listaReibo);
        txtCantidad.setText(Integer.toString(listaReibo.size()));

        tbcComprobante.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcSuplidor.setCellValueFactory(new PropertyValueFactory<>("suplidor"));
        tbcAnulado.setCellValueFactory(new PropertyValueFactory<>("anulado"));

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

        tbcTipoComprobante.setCellValueFactory(
                cellData -> {

                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null && cellData.getValue().getTipoComprobante()!=null ) {

                        if (cellData.getValue().getTipoComprobante().equals("PF")) {
                            property.setValue("Pago Factura");
                        } else if (cellData.getValue().getTipoComprobante().equals("AV")) {
                            property.setValue("Avance a Suplidor");
                        }

                    }else{
                         property.setValue("NA");
                    }
                    return property;
                });

        tbcSuplidor.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getSuplidor().getDescripcion());
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

        tbcVerDocumento.setCellFactory((TableColumn<ComprobanteDePago, ComprobanteDePago> param) -> {

            TableCell<ComprobanteDePago, ComprobanteDePago> cellsc = new TableCell<ComprobanteDePago, ComprobanteDePago>() {
                @Override
                public void updateItem(ComprobanteDePago item, boolean empty) {
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

                            RptPago.getInstancia().imprimir(item.getCodigo());
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

    public void iniciarTablaDetalle() {

        listadetalle.clear();

        tbDetalleComprobante.setItems(listadetalle);

        tbcFActura.setCellValueFactory(new PropertyValueFactory<>("ncf"));
        tbcPagado.setCellValueFactory(new PropertyValueFactory<>("montoPagado"));
        tbcPagoConAvance.setCellValueFactory(new PropertyValueFactory<>("pagoConAvance"));
        tbcMontoFactura.setCellValueFactory(new PropertyValueFactory<>("montoFactura"));

        tbcMontoFactura.setCellValueFactory(new PropertyValueFactory<>("factura.total"));
        tbcPendiente.setCellValueFactory(new PropertyValueFactory<>("pendiente"));

        tbcFActura.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getFactura().getNcf());
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

        tbcVerFactura.setCellFactory((TableColumn<DetalleComprobanteDePago, DetalleComprobanteDePago> param) -> {

            TableCell<DetalleComprobanteDePago, DetalleComprobanteDePago> cellsc = new TableCell<DetalleComprobanteDePago, DetalleComprobanteDePago>() {
                @Override
                public void updateItem(DetalleComprobanteDePago item, boolean empty) {
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

                            int factura = item.getFactura().getCodigo();

                            RptFacturaSuplidor.getInstancia().verFactura(factura);
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

        if (!(tbComprobante.getSelectionModel().getSelectedIndex() == -1)) {

            listadetalle.clear();
            txtCantidadArticulo.clear();
            txtValorTotal.clear();
            ComprobanteDePago recibo = tbComprobante.getSelectionModel().getSelectedItem();

            entdiario = EntradaDiarioDao.getInstancia().getEntradaDiarioPorDocumento(recibo.getCodigo(), 9);
            txtComentario.setText(recibo.getConcepto());

            listadetalle.addAll(ManejoComprobanteDePago
                    .getInstancia()
                    .getDetalleRecibo(recibo.getCodigo()));

            valorTotal();
            txtCantidadArticulo.setText(Integer.toString(listadetalle.size()));
            if (event.getClickCount() == 2) {

                tabPane.getSelectionModel().select(1);
            }

            if (!(entdiario == null)) {

                txtNoAsiento.setText(entdiario.getNumero().toString());
                txtNoFactura.setText(entdiario.getNumeroDocumento());
                dpFechaContabilidad.setValue(util.ClaseUtil.convertToLocalDateViaMilisecond(entdiario.getFecha()));
                txtConcepto.setText(entdiario.getConcepto());
                listaDetalleEnt.clear();
                listaDetalleEnt.addAll(EntradaDiarioDao.getInstancia().getDetalleEntradaDiario(entdiario));
                txtTotalCredito.setText(getTotalCredito().toString());
                txtTotalDebito.setText(getTotalDebito().toString());

            } else {
                txtNoAsiento.clear();
                txtNoFactura.clear();
                dpFechaContabilidad.setValue(null);
                txtConcepto.clear();
                txtTotalCredito.clear();
                txtTotalDebito.clear();
                listaDetalleEnt.clear();

            }

        }
    }

    private void valorTotal() {

        Double total = 0.00;
        for (DetalleComprobanteDePago detalleEntradaInventario : listadetalle) {

            total += detalleEntradaInventario.getTotal();
        }

        txtValorTotal.setText(total.toString());
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/cxp/comprabanteDePago/RegistroComprobanteDePago.fxml"));

        ClaseUtil.getStageModal(root);

        listaReibo.clear();
        listadetalle.clear();
        buscar();
//        listaReibo.addAll(ManejoComprobanteDePago.getInstancia().getReciboIngreso());

    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {

        try {

            if (!(tbComprobante.getSelectionModel().getSelectedIndex() == -1)) {

                Date fechaInicio = ClaseUtil.asDate(dpFechaInicio.getValue()), fechaFinal = ClaseUtil.asDate(dpFechaFinal.getValue());
                ComprobanteDePago recibo = tbComprobante.getSelectionModel().getSelectedItem();

                if (recibo.getAnulado() == true) {

                    util.ClaseUtil.mensaje(" ESTE COMPROBANTE YA  ESTA ANULADO ");
                    return;
                }

                Optional<ButtonType> ok = util.ClaseUtil.confirmarMensaje(" SEGURO QUE QUIERE ANULAR EL  COMPROBANTE NUMERO  " + recibo.getCodigo());

                if (ok.get() == ButtonType.OK) {

                    EntradaDiario ent = EntradaDiarioDao.getInstancia()
                            .getEntradaDiarioPorDocumento(recibo.getCodigo(), 8);

                    if (!(ent == null)) {

                        ok = util.ClaseUtil.confirmarMensaje(" TAMBIEN SE ANULARA LA ENTRADA DE DIARIO NUMERO  " + ent.getCodigo());

                        if (ok.get() == ButtonType.OK) {

                            ent.setAnulada(true);
                            EntradaDiarioDao.getInstancia().actualizar(ent);

                            DocumentoAnuladoDao.getInstancia()
                                    .registroDocumentoAnulado(ent.getCodigo(),
                                            ent.getCodigo().toString(), 8, " ANULACION COMPROBANTE NUMERO ");

                        } else {

                            return;
                        }

                    }

                    recibo.setAnulado(true);
                    ComprobanteDePago reciboIngresoDB = ManejoComprobanteDePago.getInstancia().actualizar(recibo);

                    if (!(reciboIngresoDB == null)) {

                        ent = EntradaDiarioDao.getInstancia()
                                .getEntradaDiarioPorDocumento(recibo.getCodigo(), 8);

                        //Anular entrada de diario de cheque  
                        ent.setAnulada(true);
                        EntradaDiarioDao.getInstancia().actualizar(ent);

                        DocumentoAnuladoDao.getInstancia()
                                .registroDocumentoAnulado(ent.getCodigo(),
                                        ent.getCodigo().toString(), 8, " ANULACION COMPROBANTE NUMERO ");

                        List<DetalleComprobanteDePago> listaDetalleRecibo = ManejoComprobanteDePago
                                .getInstancia().getDetalleRecibo(recibo.getCodigo());

                        System.out.println("Cantidad " + listaDetalleRecibo.size());
                        //Actualizando Detalle Recibo

                        for (DetalleComprobanteDePago det : listaDetalleRecibo) {

                            Double abono = det.getFactura().getAbono() - det.getTotal();
                            det.getFactura().setAbono(FormatNum.FormatearDouble(abono, 2));

                            Double pendiente = det.getFactura().getTotal() - det.getFactura().getAbono();

                            System.out.println("Pendiente " + pendiente);

                            det.getFactura().setPendiente(FormatNum.FormatearDouble(pendiente, 2));
                            det.getFactura().setPagada(false);

                        }

                    }

                    listaReibo.clear();
                    listadetalle.clear();
                    listaReibo.addAll(ManejoComprobanteDePago.getInstancia()
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

        listaReibo.addAll(ManejoComprobanteDePago.getInstancia().getReciboPorFecha(fechaini, fechafin));
        txtCantidad.setText(Integer.toString(listaReibo.size()));
        txtCantidadArticulo.clear();
        txtValorTotal.clear();
    }

    private void btnVerDocumentoActionEvent(ActionEvent event) {

        if (!(tbComprobante.getSelectionModel().getSelectedIndex() == -1)) {

            int codigoRecibo = tbComprobante.getSelectionModel().getSelectedItem().getCodigo();
            RptPago.getInstancia().imprimir(codigoRecibo);

        } else {
            ClaseUtil.mensaje("Tiene que seleccionar un registro");
        }

    }

    public Double getTotalCredito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detEntrada = tbDetalleEntrada.getItems();

        for (int i = 0; i < detEntrada.size(); i++) {

            total += detEntrada.get(i).getCredito();

        }

        return util.ClaseUtil.roundDouble(total, 4);

    }

    public Double getTotalDebito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detentrada = tbDetalleEntrada.getItems();

        for (int i = 0; i < detentrada.size(); i++) {
            total += detentrada.get(i).getDebito();
        }

        return util.ClaseUtil.roundDouble(total, 4);

    }

    @FXML
    private void btnVerAsientoActionEvent(ActionEvent event) {

        if (!(entdiario == null)) {
            RptLibroContable.getInstancia().entradaDeDiario(entdiario.getCodigo());
        }

    }

}
