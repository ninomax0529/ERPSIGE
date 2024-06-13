/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.reconciliacion.reconciliacionCliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.venta.cliente.FXMLBusClienterController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import manejo.ReciboIngreso.ManejoReciboIngreso;
import manejo.contabilidadd.TipoDocumentoDao;
import manejo.contrato.ManejoContratoDeServicio;
import manejo.factura.ManejoFactura;
import manejo.reconciliacion.ManejoReconciliacionInternaCliente;
import modelo.Cliente;
import modelo.DetalleReconciliacionInternaCliente;
import modelo.Factura;
import modelo.ReciboIngreso;
import modelo.ReconciliacionInternaCliente;
import reporte.cxc.RptReciboIngreso;
import reporte.reconciliacion.RptReconciliacionInterna;
import utiles.VariablesGlobales;
import utiles.ClaseUtil;
import utiles.FormatNum;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroReconciliacionClienteController implements Initializable {

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    private TableColumn<Factura, Factura> tbcSeleccionarFact;
    @FXML
    private JFXTextField txtMontoAReconciliarFact;
    @FXML
    private TableColumn<ReciboIngreso, ReciboIngreso> tbcSeleccionarRecibo;
    @FXML
    private JFXTextField txtMontoAReconciliarRecib;
    @FXML
    private JFXTextField txtTotalReconciliado;
    @FXML
    private TableView<DetalleReconciliacionInternaCliente> tbDetalleReconciliacion;
    @FXML
    private TableColumn<DetalleReconciliacionInternaCliente, DetalleReconciliacionInternaCliente> tbcVerRecibo;
    @FXML
    private TableColumn<DetalleReconciliacionInternaCliente, String> tbcTipoDoc;
    @FXML
    private TableColumn<DetalleReconciliacionInternaCliente, String> tbcNumeroDoc;
    @FXML
    private TableColumn<DetalleReconciliacionInternaCliente, Double> tbcDebito;
    @FXML
    private TableColumn<DetalleReconciliacionInternaCliente, Double> tbcCredito;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtNumero;
    @FXML
    private JFXTextField txtCodigoCliente;
    @FXML
    private JFXButton btnBuscarCliente;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private HBox hbDetallePago;
    @FXML
    private TableView<Factura> tbFacturaPendiente;
    @FXML
    private TableColumn<Factura, String> tbcFechaFact;
    @FXML
    private TableColumn<Factura, String> tbcFactura;
    @FXML
    private TableColumn<Factura, Double> tbcMontoFact;
    @FXML
    private TableColumn<Factura, Double> tbcAbonoFact;
    @FXML
    private TableColumn<Factura, Double> tbcMontoPendienteFact;
    @FXML
    private TableView<ReciboIngreso> tbReciboAvance;
    @FXML
    private TableColumn<ReciboIngreso, String> tbcRecibo;
    @FXML
    private TableColumn<ReciboIngreso, Double> tbcMontoAvance;
    @FXML
    private TableColumn<ReciboIngreso, String> tbcFechaAvance;
    @FXML
    private TableColumn<ReciboIngreso, Double> tbcMontoDisponibleAvance;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnAgregar;

    private Cliente cliente;
    ReconciliacionInternaCliente reconciliacion;

    ObservableList<Factura> listaFacturaPendiente = FXCollections.observableArrayList();
    ObservableList<ReciboIngreso> listaReciboAvance = FXCollections.observableArrayList();
    ObservableList<DetalleReconciliacionInternaCliente> listaDetalleReconciliacion = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaReciboAvance();
        iniciarTablaDetalleReconc();
        iniciarTablaFacturaPendiente();
        nuevo();
    }

    public void iniciarTablaFacturaPendiente() {

        listaFacturaPendiente.clear();

        tbFacturaPendiente.setItems(listaFacturaPendiente);

        tbFacturaPendiente.setEditable(true);

        tbcFactura.setCellValueFactory(new PropertyValueFactory<>("ncf"));
        tbcFechaFact.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcMontoFact.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcMontoPendienteFact.setCellValueFactory(new PropertyValueFactory<>("pendiente"));
        tbcAbonoFact.setCellValueFactory(new PropertyValueFactory<>("abono"));

        tbcMontoPendienteFact.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcSeleccionarFact.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcSeleccionarFact.setCellFactory((TableColumn<Factura, Factura> param) -> {

            TableCell<Factura, Factura> cellsc = new TableCell<Factura, Factura>() {
                @Override
                public void updateItem(Factura item, boolean empty) {
                    super.updateItem(item, empty);

                    Button btnHabilitar;

                    if (item != null) {

                        btnHabilitar = new Button();
                        btnHabilitar.setPrefSize(40.0, 5.0);
//                            btnHabilitar.setDisable(true);

                        if (item.getImprimir()) {

                            btnHabilitar.setText("SI");

                            btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 10px;\n"
                                    + "    -fx-background-radius: 10px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 8pt;");

                        } else {

                            btnHabilitar.setText("NO");
                            btnHabilitar.setStyle("   -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 10px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 8pt;");

                        }

                        HBox hbox = new HBox();

                        hbox.getChildren().add(btnHabilitar);

                        btnHabilitar.setAlignment(Pos.CENTER);

                        btnHabilitar.setOnMouseClicked((event) -> {

                            if (item.getImprimir()) {

                                item.setImprimir(false);
                                txtMontoAReconciliarFact.setText(getMontoAReconciliarFactura().toString());

                                btnHabilitar.setText("NO");
                                btnHabilitar.setStyle("   -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 8pt;");

                            } else {

                                item.setImprimir(true);
                                txtMontoAReconciliarFact.setText(getMontoAReconciliarFactura().toString());

                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 10px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 8pt;");

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

        tbcMontoPendienteFact.setOnEditCommit(data -> {

            Factura p = data.getRowValue();

            if (data.getNewValue() > 0) {

                if (data.getNewValue() > (p.getTotal() - p.getAbono())) {
                    tbFacturaPendiente.refresh();
                    tbFacturaPendiente.requestFocus();
                    ClaseUtil.mensaje(" El valor no puede ser mayor que el monto pendiente ");
                    return;
                }

                p.setPendiente(data.getNewValue());

            } else {

                ClaseUtil.mensaje(" El valor no puede ser igual o menor que cero ");

            }

            tbFacturaPendiente.refresh();
            tbFacturaPendiente.requestFocus();
            txtMontoAReconciliarFact.setText(getMontoAReconciliarFactura().toString());

        });

    }

    public void iniciarTablaReciboAvance() {

        listaReciboAvance.clear();

        tbReciboAvance.setItems(listaReciboAvance);
        tbReciboAvance.setEditable(true);

        tbcRecibo.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcFechaAvance.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcMontoAvance.setCellValueFactory(new PropertyValueFactory<>("monto"));
        tbcMontoDisponibleAvance.setCellValueFactory(new PropertyValueFactory<>("avancePendiente"));

        tbcMontoDisponibleAvance.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcMontoDisponibleAvance.setOnEditCommit(data -> {

            ReciboIngreso p = data.getRowValue();

            if (data.getNewValue() > 0) {

                if (data.getNewValue() > (p.getAvancePendiente())) {
                    tbFacturaPendiente.refresh();
                    tbFacturaPendiente.requestFocus();
                    ClaseUtil.mensaje(" El valor no puede ser mayor que el monto pendiente ");
                    return;
                }

                p.setAvancePendiente(data.getNewValue());

            } else {

                ClaseUtil.mensaje(" El valor no puede ser igual o menor que cero ");

            }

            tbReciboAvance.refresh();
            tbReciboAvance.requestFocus();
            txtMontoAReconciliarRecib.setText(getMontoAReconciliarRecibo().toString());

        });

        tbcSeleccionarRecibo.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcSeleccionarRecibo.setCellFactory((TableColumn<ReciboIngreso, ReciboIngreso> param) -> {

            TableCell<ReciboIngreso, ReciboIngreso> cellsc = new TableCell<ReciboIngreso, ReciboIngreso>() {
                @Override
                public void updateItem(ReciboIngreso item, boolean empty) {
                    super.updateItem(item, empty);

                    Button btnHabilitar;

                    if (item != null) {

                        btnHabilitar = new Button();
                        btnHabilitar.setPrefSize(40.0, 5.0);
//                            btnHabilitar.setDisable(true);

                        if (item.getImprimir()) {

                            btnHabilitar.setText("SI");

                            btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 10px;\n"
                                    + "    -fx-background-radius: 10px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 8pt;");

                        } else {

                            btnHabilitar.setText("NO");
                            btnHabilitar.setStyle("   -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 10px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 8pt;");

                        }

                        HBox hbox = new HBox();

                        hbox.getChildren().add(btnHabilitar);

                        btnHabilitar.setAlignment(Pos.CENTER);

                        btnHabilitar.setOnMouseClicked((event) -> {

                            if (item.getImprimir()) {

                                item.setImprimir(false);

                                txtMontoAReconciliarRecib.setText(getMontoAReconciliarRecibo().toString());

                                btnHabilitar.setText("NO");
                                btnHabilitar.setStyle("   -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 8pt;");

                            } else {

                                item.setImprimir(true);

                                txtMontoAReconciliarRecib.setText(getMontoAReconciliarRecibo().toString());

                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 10px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 8pt;");

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

    public void iniciarTablaDetalleReconc() {

        listaDetalleReconciliacion.clear();

        tbDetalleReconciliacion.setItems(listaDetalleReconciliacion);

        tbcTipoDoc.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tbcNumeroDoc.setCellValueFactory(new PropertyValueFactory<>("numeroDocumento"));
        tbcDebito.setCellValueFactory(new PropertyValueFactory<>("debito"));
        tbcCredito.setCellValueFactory(new PropertyValueFactory<>("credito"));

        tbcTipoDoc.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getTipoDocumento().getNombre());
                    }
                    return property;
                });

        tbcVerRecibo.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcVerRecibo.setCellFactory((TableColumn<DetalleReconciliacionInternaCliente, DetalleReconciliacionInternaCliente> param) -> {

            TableCell<DetalleReconciliacionInternaCliente, DetalleReconciliacionInternaCliente> cellsc = new TableCell<DetalleReconciliacionInternaCliente, DetalleReconciliacionInternaCliente>() {
                @Override
                public void updateItem(DetalleReconciliacionInternaCliente item, boolean empty) {
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

                            if (item.getTipoDocumento().getCodigo() == 7) {

                                reporte.unidadnegocio.RptFactura.getInstancia().verFactura(item.getDocumento(),
                                        VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
                            }
                            if (item.getTipoDocumento().getCodigo() == 8) {

                                RptReciboIngreso.getInstancia().imprimir(item.getDocumento());
                            }
//                          
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
    private void btnBuscarClienteActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        limpiar();

        loader.setLocation(getClass().getResource("/vista/venta/cliente/FXMLBusCliente.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        FXMLBusClienterController articuloController = loader.getController();

        if (!(articuloController.getCliente() == null)) {

            setCliente(articuloController.getCliente());
            txtCodigoCliente.setText(getCliente().getNombre());

            listaFacturaPendiente.addAll(ManejoFactura.getInstancia().getListaFacturaPendiente(getCliente()));
            listaReciboAvance.addAll(ManejoReciboIngreso.getInstancia().getReciboAvancePorCliente(getCliente().getCodigo()));

            Double montoPagado = 0.00, montoPendiente = 0.00,
                    montoPagadoRec = 0.00, montoPendienteRec = 0.00;

            for (Factura fcObj : listaFacturaPendiente) {

                montoPagado = ManejoFactura.getInstancia().getMontoPagado(fcObj.getCodigo());
                montoPendiente = fcObj.getTotal() - montoPagado;

                fcObj.setPendiente(ClaseUtil.roundDouble(montoPendiente, 2));
                fcObj.setAbono(montoPagado);
            }

            for (ReciboIngreso rcOj : listaReciboAvance) {

                montoPagadoRec = ManejoReciboIngreso.getInstancia().getAvanceUsado(rcOj.getCodigo());
                montoPendienteRec = rcOj.getMonto() - montoPagadoRec;
                rcOj.setAvancePendiente(ClaseUtil.roundDouble(montoPendienteRec, 2));

            }

            tbFacturaPendiente.refresh();

            tbReciboAvance.refresh();

        }

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (getCliente() == null) {
                ClaseUtil.mensaje("Tiene que seleccionar un cliente");
                return;
            }
            if (listaDetalleReconciliacion.size() <= 0) {
                ClaseUtil.mensaje("No hay datos para conciliar ");
                return;
            }

            reconciliacion.setAnulada(false);
            reconciliacion.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            reconciliacion.setCliente(getCliente());
            reconciliacion.setImprimir(false);
            reconciliacion.setMonto(getTotalReconciliado());
            reconciliacion.setNombreCliente(getCliente().getNombre());
            reconciliacion.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            reconciliacion.setUsuario(VariablesGlobales.USUARIO);
            reconciliacion.setFechaRegistro(new Date());

            reconciliacion.setDetalleReconciliacionInternaClienteCollection(listaDetalleReconciliacion);

            reconciliacion = ManejoReconciliacionInternaCliente.getInstancia().salvar(reconciliacion);

            if (!(reconciliacion == null)) {
             
                RptReconciliacionInterna.getInstancia().verReporte(reconciliacion.getCodigo());
            }

//            ClaseUtil.mensaje("Reconciliacion guardada exitosamente ");
            nuevo();

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error guardando la Reconciliacion ");

            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {

        nuevo();
    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("Seguro que quiere eliminarlo ");

        if (ok.get() == ButtonType.OK || ok.get() == ButtonType.YES) {
            listaDetalleReconciliacion.clear();
            txtTotalReconciliado.clear();
        }

    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {

        Double diferencia = util.ClaseUtil.roundDouble(getMontoAReconciliarRecibo() - getMontoAReconciliarFactura(), 0);

        if (diferencia != 0) {
            ClaseUtil.mensaje(" Los montos a reconciliar tiene que ser iguales ");
            return;
        }

        listaDetalleReconciliacion.clear();
        List<DetalleReconciliacionInternaCliente> lisitaDet = new ArrayList<>();

        DetalleReconciliacionInternaCliente det = new DetalleReconciliacionInternaCliente();

        for (Factura fact : tbFacturaPendiente.getItems()) {

            if (fact.getImprimir()) {
                det.setCredito(fact.getPendiente());
                det.setDebito(0);
                det.setTipoDocumento(TipoDocumentoDao.getInstancia().getTipoDocumento(7));
                det.setNombreDocumento(det.getTipoDocumento().getNombre());
                det.setDocumento(fact.getCodigo());
                det.setNumeroDocumento(fact.getNumero());
                det.setReconciliacion(reconciliacion);
                lisitaDet.add(det);
                det = new DetalleReconciliacionInternaCliente();
            }
        }

        for (ReciboIngreso recib : tbReciboAvance.getItems()) {

            if (recib.getImprimir()) {
                det.setDebito(recib.getAvancePendiente());
                det.setCredito(0);
                det.setTipoDocumento(TipoDocumentoDao.getInstancia().getTipoDocumento(8));
                det.setNombreDocumento(det.getTipoDocumento().getNombre());
                det.setDocumento(recib.getCodigo());
                det.setNumeroDocumento(recib.getNumero());
                det.setReconciliacion(reconciliacion);
                lisitaDet.add(det);
                det = new DetalleReconciliacionInternaCliente();
            }
        }

        listaDetalleReconciliacion.addAll(lisitaDet);

        txtTotalReconciliado.setText(getTotalReconciliado().toString());

    }

    private Double getTotalReconciliado() {

        Double total = 0.00;

//        
        for (DetalleReconciliacionInternaCliente detr : tbDetalleReconciliacion.getItems()) {

            total += detr.getDebito();
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getMontoAReconciliarRecibo() {

        Double total = 0.00;

//        
        for (ReciboIngreso recib : tbReciboAvance.getItems()) {

            if (recib.getImprimir()) {
                total += recib.getAvancePendiente();
            }

        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getMontoAReconciliarFactura() {

        Double total = 0.00;

//        
        for (Factura fact : tbFacturaPendiente.getItems()) {

            if (fact.getImprimir()) {
                total += fact.getPendiente();
            }

        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private void nuevo() {

        reconciliacion = new ReconciliacionInternaCliente();
        limpiar();
    }

    private void limpiar() {

        setCliente(null);
        txtCodigoCliente.clear();
        txtNumero.clear();
        dpFecha.setValue(LocalDate.now());
        listaDetalleReconciliacion.clear();
        listaFacturaPendiente.clear();
        listaReciboAvance.clear();
        txtMontoAReconciliarFact.clear();
        txtMontoAReconciliarRecib.clear();
        txtTotalReconciliado.clear();
        txtNumero.setText(ManejoReconciliacionInternaCliente.getInstancia().getNumero().toString());
    }

}
