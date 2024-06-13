/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cxp.comprobanteDePago;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.contabilidad.consulta.ConsultaConfigCuentaContableController;
import controlador.contabilidad.registro.FXMLCatalogoConsController;
import controlador.suplidor.FXMLBusSuplidorController;
import controlador.venta.puntoVenta.FormaDePagoController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.contabilidadd.ConfiguracionCuentaContableDao;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.cxp.ManejoComprobanteDePago;
import manejo.cxp.ManejoFacturaSuplidor;
import manejo.cxp.avanceASuplidor.ManejoAvanceASuplidor;
import manejo.factura.ManejoFormaPago;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.AvanceASuplidor;
import modelo.Catalogo;
import modelo.ComprobanteDePago;
import modelo.ConfiguracionCuentaContable;
import modelo.DetalleAvanceASuplidor;
import modelo.DetalleComprobanteDePago;
import modelo.DetalleEntradaDiario;
import modelo.FacturaSuplidor;
import modelo.FormaPago;
import modelo.SecuenciaDocumento;
import modelo.Suplidor;
import reporte.pagos.RptPago;
import util.NumeroALetra;
import utiles.ClaseUtil;
import utiles.FormatNum;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroComprobanteDePagoController implements Initializable {

    /**
     * @return the chAvanceCliente
     */
    public JFXCheckBox getChAvanceCliente() {
        return chAvanceCliente;
    }

    /**
     * @param chAvanceCliente the chAvanceCliente to set
     */
    public void setChAvanceCliente(JFXCheckBox chAvanceCliente) {
        this.chAvanceCliente = chAvanceCliente;
    }

    /**
     * @return the txtMontoAvanceCliente
     */
    public JFXTextField getTxtMontoAvanceCliente() {
        return txtMontoAvanceCliente;
    }

    /**
     * @param txtMontoAvanceCliente the txtMontoAvanceCliente to set
     */
    public void setTxtMontoAvanceCliente(JFXTextField txtMontoAvanceCliente) {
        this.txtMontoAvanceCliente = txtMontoAvanceCliente;
    }

    /**
     * @return the hbDetPago
     */
    public HBox getHbDetPago() {
        return hbDetPago;
    }

    /**
     * @param hbDetPago the hbDetPago to set
     */
    public void setHbDetPago(HBox hbDetPago) {
        this.hbDetPago = hbDetPago;
    }

    @FXML
    private Label lbNumEntrada;
    @FXML
    private JFXButton btnConfigCuenta;
    @FXML
    private JFXButton btnEliminarCuenta;
    @FXML
    private JFXCheckBox chAvanceCliente;
    @FXML
    private JFXTextField txtMontoAvance;
    @FXML
    private JFXTextField txtMontoAvanceCliente;
    @FXML
    private HBox hbDetPago;

    /**
     * @return the catalogo
     */
    public Catalogo getCatalogo() {
        return catalogo;
    }

    /**
     * @param catalogo the catalogo to set
     */
    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    /**
     * @return the ConfigContable
     */
    public ConfiguracionCuentaContable getConfigContable() {
        return ConfigContable;
    }

    /**
     * @param ConfigContable the ConfigContable to set
     */
    public void setConfigContable(ConfiguracionCuentaContable ConfigContable) {
        this.ConfigContable = ConfigContable;
    }

    ConfiguracionCuentaContable ConfigContable;

    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnFormaPago;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnImprimir;
    @FXML
    private JFXTextField txtCodigoCliente;
    @FXML
    private JFXTextField txtNombreCliente;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private JFXTextField txtRncCliente;
    @FXML
    private JFXButton btnBuscarCliente;
    @FXML
    private Button btnagregar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView<FacturaSuplidor> tbFacturaPendiente;
    @FXML
    private TableColumn<FacturaSuplidor, String> tbcFacturaPendiente;
    @FXML
    private TableColumn<FacturaSuplidor, Date> tbcFechaPendiente;
    @FXML
    private TableColumn<FacturaSuplidor, Double> tbcMontoPendiente;
    @FXML
    private TableColumn<FacturaSuplidor, Double> tbcMonto;
    @FXML
    private TableColumn<FacturaSuplidor, Double> tbcAbonoPendiente;
    @FXML
    private JFXTextField txtMontoPendiente;
    @FXML
    private TableView<DetalleComprobanteDePago> tbFacturaaPagar;
    @FXML
    private TableColumn<DetalleComprobanteDePago, String> tbcFacturaAPagar;
    @FXML
    private TableColumn<DetalleComprobanteDePago, Double> tbcMontoPendienteAPagar;
    @FXML
    private TableColumn<DetalleComprobanteDePago, Double> tbcMontoAPagar;
    @FXML
    private TableColumn<DetalleComprobanteDePago, Double> tbcMontoAplicar;
    @FXML
    private TableColumn<DetalleComprobanteDePago, Double> tbcPagoPorAvance;
    @FXML
    private JFXTextField txtMontoAPagar;
    @FXML
    private TextArea txtConcepto;

    @FXML
    private JFXDatePicker dpFecha;

    ObservableList<FacturaSuplidor> listaFacturaPendiente = FXCollections.observableArrayList();
    ObservableList<FacturaSuplidor> listaFacturaAPagar = FXCollections.observableArrayList();
    ObservableList<DetalleComprobanteDePago> detalleComprobanteDePago = FXCollections.observableArrayList();

    DetalleEntradaDiario detalle;
    ObservableList<DetalleEntradaDiario> listaDetalleEnt = FXCollections.observableArrayList();

    Suplidor suplidor;
    @FXML
    private TextArea txtComentario;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private JFXButton btnCatalogoCons;
    @FXML
    private JFXButton btnAgregarCuenta;
    @FXML
    private JFXTextField txtValorAContabilizar;
    @FXML
    private TableView<DetalleEntradaDiario> tblDetalleEnt;
    @FXML
    private TableColumn<DetalleEntradaDiario, String> tbrCuenta;
    @FXML
    private TableColumn<DetalleEntradaDiario, String> tbrDescripcion;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbrDebito;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbrCredito;
    @FXML
    private TextField txtTotalDebito;
    @FXML
    private TextField txtTotalCredito;
    @FXML
    private TextField txtDiferencia;
    private Catalogo catalogo;
    Double avanceDisponible = 0.00;

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }
    ComprobanteDePago comprobanteDePago;
    DetalleComprobanteDePago detalleReciboIngreso;
    List<FormaPago> listFormaPagoCollection;
    int codigoRecibo;

    Double devuelta;
    @FXML
    private JFXTextField txtRecibo;

    public Double getDevuelta() {
        return devuelta;
    }

    public void setDevuelta(Double devuelta) {
        this.devuelta = devuelta;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaFacturaAPagar();
        iniciarTablaFacturaPendiente();
        inicializarTablaEntradaDiario();

        nuevo();

    }

    public void inicializarTablaEntradaDiario() {

        tbrCuenta.setCellValueFactory(new PropertyValueFactory<>("Cuenta"));
        tbrDescripcion.setCellValueFactory(new PropertyValueFactory<>("DescripcionCuenta"));
        tbrDebito.setCellValueFactory(new PropertyValueFactory<>("Debito"));
        tbrCredito.setCellValueFactory(new PropertyValueFactory<>("Credito"));

        tblDetalleEnt.setEditable(true);
        tblDetalleEnt.setItems(listaDetalleEnt);

        tbrDebito.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbrCredito.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbrDebito.setOnEditCommit(data -> {

            try {

                DetalleEntradaDiario p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setDebito(data.getNewValue());
                    txtTotalDebito.setText(getTotalDebito().toString());
                    Double diferencia = util.ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
                    txtDiferencia.setText(diferencia.toString());

                } else {

                    util.ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tblDetalleEnt.refresh();
                    tblDetalleEnt.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbrCredito.setOnEditCommit(data -> {

            try {

                DetalleEntradaDiario p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setCredito(data.getNewValue());
                    txtTotalCredito.setText(getTotalCredito().toString());
                    Double diferencia = util.ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
                    txtDiferencia.setText(diferencia.toString());

                } else {

                    util.ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tblDetalleEnt.refresh();
                    tblDetalleEnt.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    public void iniciarTablaFacturaPendiente() {

        listaFacturaPendiente.clear();

        tbFacturaPendiente.setItems(listaFacturaPendiente);

        tbcFacturaPendiente.setCellValueFactory(new PropertyValueFactory<>("ncf"));
        tbcFechaPendiente.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcMonto.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcMontoPendiente.setCellValueFactory(new PropertyValueFactory<>("pendiente"));
        tbcAbonoPendiente.setCellValueFactory(new PropertyValueFactory<>("abono"));

    }

    public void iniciarTablaFacturaAPagar() {

        listaFacturaAPagar.clear();

        tbFacturaaPagar.setItems(detalleComprobanteDePago);

        tbcFacturaAPagar.setCellValueFactory(new PropertyValueFactory<>("factura"));

        tbcMontoAPagar.setCellValueFactory(new PropertyValueFactory<>("montoPagado"));
        tbcMontoAplicar.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcMontoPendienteAPagar.setCellValueFactory(new PropertyValueFactory<>("pendiente"));
        tbcPagoPorAvance.setCellValueFactory(new PropertyValueFactory<>("pagoConAvance"));

        tbFacturaaPagar.setEditable(true);
        tbcFacturaAPagar.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getFactura().getNcf());
                    }
                    return property;
                });

        tbcMontoAPagar.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcMontoAPagar.setOnEditCommit(data -> {

            try {

                DetalleComprobanteDePago p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00,
                        descuento = 0.00, impuesto = 0.00;

                if (data.getNewValue() >= 1) {

                    total = data.getNewValue();

                    if ((p.getPagoConAvance() + total) > p.getPendiente()) {

                        ClaseUtil.mensaje("El monto pagado no puede ser mayor que el monto pendiente");

                        tbFacturaaPagar.refresh();
                        tbFacturaaPagar.requestFocus();
                        return;

                    }

                    p.setMontoPagado(total);
                    p.setTotal(total + p.getPagoConAvance());

                    txtMontoAPagar.setText(getTotalPago().toString());
                    txtValorAContabilizar.setText(getTotalPago().toString());
                    txtMontoAPagar.setText(getTotalPago().toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbFacturaaPagar.refresh();
                    tbFacturaaPagar.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnFormaPagoActionEvent(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/venta/facturacion/FormaDePago.fxml"));
            loader.load();

            Parent root = loader.getRoot();
            FormaDePagoController formaDePagoController = loader.getController();

            formaDePagoController.getTxtTotalFactura().setText(txtMontoAPagar.getText());
            formaDePagoController.getTxtEfectivo().setText(txtMontoAPagar.getText());

            ClaseUtil.getStageModal(root);

            if (!(formaDePagoController.getFormaPago() == null)) {

                listFormaPagoCollection = formaDePagoController.getListaFormaPago();
                setDevuelta(formaDePagoController.getTotalPago());

            } else {

                ClaseUtil.mensaje("Tiene que aplicar una forma de pago");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnGuardarActionevent(ActionEvent event) {

        try {

            if (asignarSecuencia() == false) {

                ClaseUtil.mensaje(" La secuencia para el comprobante de pago de la unidad de negocio "
                        + "" + utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return;
            }

            if (getSuplidor() == null) {
                ClaseUtil.mensaje(" Tiene que seleccionar un suplidor ");
                return;
            }

            if (getChAvanceCliente().isSelected()) {

                if (getTxtMontoAvanceCliente().getText().isEmpty()) {

                    ClaseUtil.mensaje("Tiene que digitar el monto de avance ");

                    getTxtMontoAvanceCliente().requestFocus();
                    return;
                }

                if (txtConcepto.getText().isEmpty()) {

                    ClaseUtil.mensaje(" El concepto no puede estar vacio ");
                    txtConcepto.requestFocus();
                    return;
                }

                guardarAvance();
                codigoRecibo = comprobanteDePago.getCodigo();

            } else {

                guardarComprobanteConFactura();
            }

            RptPago.getInstancia().imprimir(codigoRecibo);
            nuevo();

        } catch (Exception e) {
            ClaseUtil.mensaje(" Hubo un error guardando el comprobante ");
            e.printStackTrace();
        }
    }

    @FXML
    private void btnImprimirActionEvent(ActionEvent event
    ) {

        try {

            if (codigoRecibo > 0) {
//                RptReciboIngreso.getInstancia().imprimir(codigoRecibo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnBuscarClienteActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        detalleComprobanteDePago.clear();

        loader.setLocation(getClass().getResource("/vista/suplidor/FXMLBusSuplidor.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        FXMLBusSuplidorController controller = loader.getController();

        if (!(controller.getSuplidor() == null)) {

            setSuplidor(controller.getSuplidor());
            txtCodigoCliente.setText(getSuplidor().getCodigo().toString());
            txtNombreCliente.setText(getSuplidor().getDescripcion());
            txtRncCliente.setText(getSuplidor().getRnc());
            txtTelefono.setText(getSuplidor().getTelefono());
            listaFacturaPendiente.clear();
            listaFacturaPendiente.addAll(ManejoFacturaSuplidor.getInstancia().getFacturaPendiente(getSuplidor()));
            txtMontoPendiente.setText(getTotalPendiente().toString());

            Double montoPagado = 0.00, montoPendiente = 0.00;

            for (FacturaSuplidor fcObj : listaFacturaPendiente) {

                montoPagado = ManejoFacturaSuplidor.getInstancia().getMontoPagado(fcObj.getCodigo());
                montoPendiente = fcObj.getTotal() - montoPagado;

                fcObj.setPendiente(ClaseUtil.roundDouble(montoPendiente, 2));
                fcObj.setAbono(montoPagado);
            }

            txtMontoPendiente.setText(getTotalPendiente().toString());

            avanceDisponible = ManejoAvanceASuplidor.getInstancia().getMontoDisponible(getSuplidor().getCodigo());
            txtMontoAvance.setText(avanceDisponible.toString());

        }
    }

    @FXML
    private void btnagregarActionEvent(ActionEvent event) {

        try {

            Double pendiente = 0.00;
            avanceDisponible = Double.parseDouble(txtMontoAvance.getText().trim());
            if (!(tbFacturaPendiente.getSelectionModel().getSelectedIndex() == -1)) {

                FacturaSuplidor fact = tbFacturaPendiente.getSelectionModel().getSelectedItem();

                pendiente = ManejoFacturaSuplidor.getInstancia().getMontoPendiente(fact.getCodigo());

                detalleReciboIngreso = new DetalleComprobanteDePago();
                detalleReciboIngreso.setFactura(fact);
                detalleReciboIngreso.setPendiente(pendiente);
                detalleReciboIngreso.setMontoFactura(fact.getTotal());

                System.out.println("pendiente : " + pendiente + " tota fact : " + fact.getTotal()
                        + " avanceDisponible : " + avanceDisponible);
                if (avanceDisponible > 0) {

                    if (avanceDisponible > pendiente) {
                        detalleReciboIngreso.setPagoConAvance(pendiente);
                        avanceDisponible -= pendiente;
                        avanceDisponible = ClaseUtil.roundDouble(avanceDisponible, 4);
                    } else {
                        detalleReciboIngreso.setPagoConAvance(avanceDisponible);
                        avanceDisponible = 0.00;
                    }

                    detalleReciboIngreso.setMontoPagado(0.00);

                } else {

                    detalleReciboIngreso.setPagoConAvance(0.00);
                    detalleReciboIngreso.setMontoPagado(pendiente);
                }

                detalleReciboIngreso.setTotal(pendiente);
                detalleReciboIngreso.setComprobanteDePago(comprobanteDePago);

                listaFacturaAPagar.add(fact);
                detalleComprobanteDePago.add(detalleReciboIngreso);
                int index = tbFacturaPendiente.getSelectionModel().getSelectedIndex();
                listaFacturaPendiente.remove(index);
                txtMontoAPagar.setText(getTotalPago().toString());
                txtValorAContabilizar.setText(getTotalPago().toString());
                txtMontoPendiente.setText(getTotalPendiente().toString());
                btnFormaPago.setDisable(false);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        try {

            if (!(tbFacturaaPagar.getSelectionModel().getSelectedIndex() == -1)) {

                DetalleComprobanteDePago detRecibo = tbFacturaaPagar.getSelectionModel().getSelectedItem();
                listaFacturaPendiente.add(detRecibo.getFactura());
                int index = tbFacturaaPagar.getSelectionModel().getSelectedIndex();
                detalleComprobanteDePago.remove(index);
                txtMontoAPagar.setText(getTotalPago().toString());
                txtValorAContabilizar.setText(getTotalPago().toString());
                txtMontoPendiente.setText(getTotalPendiente().toString());
                tbFacturaPendiente.refresh();
                tbFacturaaPagar.refresh();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void nuevo() {

        comprobanteDePago = new ComprobanteDePago();
        inicializarSecuencia();
        limpiar();

    }

    private void limpiar() {

        listaFacturaAPagar.clear();
        listaFacturaPendiente.clear();
        detalleComprobanteDePago.clear();
        dpFecha.setValue(LocalDate.now());
        txtCodigoCliente.clear();
        txtConcepto.clear();
        txtRncCliente.clear();
        txtTelefono.clear();
        txtNombreCliente.clear();
        txtMontoAPagar.clear();
        txtMontoPendiente.clear();
        btnFormaPago.setDisable(true);
        listaDetalleEnt.clear();
        txtComentario.clear();
        txtValorAContabilizar.clear();
        txtDiferencia.clear();
        txtTotalCredito.clear();
        txtTotalDebito.clear();
        txtMontoAvanceCliente.clear();

    }

    private Double getTotalPago() {

        Double total = 0.00;

        for (DetalleComprobanteDePago det : tbFacturaaPagar.getItems()) {

            total += det.getMontoPagado() + det.getPagoConAvance();
            System.out.println("Valor " + total);
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getTotalPendiente() {

        Double total = 0.00;

//        
        for (FacturaSuplidor fact : tbFacturaPendiente.getItems()) {

            total += fact.getPendiente();
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private void inicializarSecuencia() {

        Integer numero = ManejoComprobanteDePago.getInstancia().getNumero().intValue();
        txtRecibo.setText(numero.toString());
    }

    private Boolean asignarSecuencia() {

        Boolean existeSec = false;

        comprobanteDePago.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

        SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(comprobanteDePago.getUnidadDeNegocio().getCodigo(), 9);

        if (!(secDoc == null)) {

            existeSec = true;
            boolean existe = ManejoComprobanteDePago.getInstancia().existeSecuencia(secDoc.getNumero());

            if (existe) {

                System.out.println("existe " + secDoc.getNumero());

                while (ManejoComprobanteDePago.getInstancia().existeSecuencia(secDoc.getNumero())) {

                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

                secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(comprobanteDePago.getUnidadDeNegocio().getCodigo(), 9);

                comprobanteDePago.setNumero(secDoc.getNumero());
                comprobanteDePago.setSecuenciaDocumento(secDoc);

            } else {

                System.out.println("No existe " + secDoc.getNumero());
                comprobanteDePago.setNumero(secDoc.getNumero());
                comprobanteDePago.setSecuenciaDocumento(secDoc);
                secDoc.setNumero(secDoc.getNumero() + 1);
                ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

            }

        } else {

            existeSec = false;
        }

        return existeSec;
    }

    @FXML
    private void btnCatalogoConsActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/contabilidad/consulta/FXMLCatalogoCons.fxml"));

        loader.load();

        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        System.out.println("ESPERANDOOO!!!");

        FXMLCatalogoConsController catalogoController = loader.getController();

        setCatalogo(catalogoController.getCatalogo());

        System.out.println("catalogo : " + getCatalogo());
        if (!(getCatalogo() == null)) {

            txtDescripcion.setText(getCatalogo().getDescripcion());
//            agregarCuenta();

        }

    }

    @FXML
    private void btnAgregarCuentaActionEvent(ActionEvent event) {

        if (getCatalogo() == null) {

            util.ClaseUtil.mensaje("NO HAY CUENTA SELECCIONADA");
            return;
        }

        agregarCuenta();
    }

    public void changeDebitoCellEnvent(TableColumn.CellEditEvent edittedCell) {

        DetalleEntradaDiario detalleSelected = tblDetalleEnt.getSelectionModel().getSelectedItem();
        detalleSelected.setDebito(Double.valueOf(edittedCell.getNewValue().toString()));
        txtTotalDebito.setText(getTotalDebito().toString());
        Double diferencia = util.ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
        txtDiferencia.setText(diferencia.toString());

    }

    public void changeCreditoCellEnvent(TableColumn.CellEditEvent edittedCell) {

        DetalleEntradaDiario detalleSelected = tblDetalleEnt.getSelectionModel().getSelectedItem();
        detalleSelected.setCredito(Double.valueOf(edittedCell.getNewValue().toString()));
        txtTotalCredito.setText(getTotalCredito().toString());
        Double diferencia = util.ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
        txtDiferencia.setText(diferencia.toString());

    }

    @FXML
    private void tbDetalleEntActionEvent(KeyEvent event) {
    }

    public Double getTotalCredito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detEntrada = tblDetalleEnt.getItems();

        for (int i = 0; i < detEntrada.size(); i++) {
            System.out.println("Size: " + detEntrada.size());
            total += detEntrada.get(i).getCredito();
            System.out.println("TDeB: " + total);
        }

        return util.ClaseUtil.roundDouble(total, 4);

    }

    public Double getTotalDebito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detentrada = tblDetalleEnt.getItems();

        for (int i = 0; i < detentrada.size(); i++) {
            total += detentrada.get(i).getDebito();
        }

        return util.ClaseUtil.roundDouble(total, 4);

    }

    private void agregarCuenta() {

        detalle = new DetalleEntradaDiario();

        detalle.setCatalogo(getCatalogo());
        detalle.setCuenta(getCatalogo().getCuenta());
        detalle.setDescripcionCuenta(getCatalogo().getDescripcion());
        detalle.setCredito(0.00);
        detalle.setDebito(0.00);
        detalle.setCuentaControl(getCatalogo().getCuentaControl());

        listaDetalleEnt.add(detalle);

        setCatalogo(null);

    }

    private void guardarEntradaDiario() {

        //Proceso para registrar el asiento contable
        if (listaDetalleEnt.size() > 0) {

            if (txtConcepto.getText().isEmpty()) {

                util.ClaseUtil.mensaje("Tiene que digitar el Concepto");
                txtConcepto.requestFocus();
                return;
            }

            if (tblDetalleEnt.getItems().size() <= 0) {

                util.ClaseUtil.mensaje("No hay Cuentas contable Agregada");

                return;
            }

            if (getTotalDebito() <= 0) {

                util.ClaseUtil.mensaje("El valor del debito y el credito no pueden ser igual o menor que cero");

                return;
            }

            Double valor = Double.parseDouble(txtValorAContabilizar.getText());

            if (!(Double.compare(getTotalDebito(), valor) == 0)) {

                util.ClaseUtil.mensaje("El valor del debito y el credito no pueden ser diferentes del valor de la factura");

                return;
            }

            if ((getTotalDebito() - getTotalCredito()) != 0) {

                util.ClaseUtil.mensaje("El debito y el credito tienen que ser iguales");

                return;
            }
//
//                if (estado.equals("CERRADO")) {
//
//                    ClaseUtil.mensaje("El Periodo Contable ya Esta Cerrado en esta  Fecha : " + new SimpleDateFormat("yyyy-MM-dd").format(fecha));
//                    return;
//                }

            if (txtComentario.getText().isEmpty()) {

                util.ClaseUtil.mensaje("El Concepto del asiento  no puede estar vacio");
                txtComentario.requestFocus();
                return;
            }

        }
    }

    @FXML
    private void btnConfigCuentaActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/contabilidad/consulta/ConsultaConfgnCuentaContable.fxml"));

        loader.load();

        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        System.out.println("ESPERANDOOO!!!");

        ConsultaConfigCuentaContableController catalogoController = loader.getController();

        setConfigContable(catalogoController.getConfig());

        if (!(getConfigContable() == null)) {

            txtDescripcion.setText(getConfigContable().getDescripcion());

            listaDetalleEnt.clear();
            listaDetalleEnt.addAll(ConfiguracionCuentaContableDao.getInstancia()
                    .generarConfiguracionCuenta(getConfigContable()));

        }
    }

    @FXML
    private void btnEliminarCuentaActionEvent(ActionEvent event) {

        try {

            if (tblDetalleEnt.getSelectionModel().getSelectedIndex() != -1) {

                listaDetalleEnt.remove(tblDetalleEnt.getSelectionModel().getSelectedIndex());
                tblDetalleEnt.refresh();
                txtTotalCredito.setText(getTotalCredito().toString());
                txtTotalDebito.setText(getTotalDebito().toString());
                Double diferencia = util.ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
                txtDiferencia.setText(diferencia.toString());

            } else {
                util.ClaseUtil.mensaje("Tiene que selccionar un registro");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void chAvanceClienteActionEvent(ActionEvent event) {

        if (getChAvanceCliente().isSelected()) {
            getTxtMontoAvanceCliente().setVisible(true);
            getHbDetPago().setVisible(false);
        } else {

            getTxtMontoAvanceCliente().setVisible(false);
            getHbDetPago().setVisible(true);
        }

    }

    private void guardarAvance() {

        comprobanteDePago.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
        comprobanteDePago.setFechaRegistro(new Date());
        comprobanteDePago.setAnulado(false);
        comprobanteDePago.setBalancePendiente(0.00);
        comprobanteDePago.setSuplidor(getSuplidor());
        comprobanteDePago.setConcepto(txtConcepto.getText());
        comprobanteDePago.setUsuario(utiles.VariablesGlobales.USUARIO);
        comprobanteDePago.setConcepto(txtConcepto.getText());
//        comprobanteDePago.setConceptoDeCobro(cbConceptoPorCobro.getSelectionModel().getSelectedItem());

        //Si no se guarda el detalle del recibo,es porque en la entidad recibo al que agregarle el campo cascade
        comprobanteDePago.setMonto(Double.parseDouble(getTxtMontoAvanceCliente().getText()));
        comprobanteDePago.setTipoComprobante("AV");
        NumeroALetra nel = new NumeroALetra();
        comprobanteDePago.setMontoEnLetra(nel.Convertir(comprobanteDePago.getMonto().toString(), true));
        comprobanteDePago = ManejoComprobanteDePago.getInstancia().salvar(comprobanteDePago);

        if (comprobanteDePago == null) {
            util.ClaseUtil.mensaje("Hubo un error creando el recibo de avance");
            return;

        }

        try {

            registraravanceAsuplidor(comprobanteDePago);
            Date fechaContable = comprobanteDePago.getFecha();
            if (listaDetalleEnt.size() > 0) {

                EntradaDiarioDao.getInstancia().registrarEntradaDiario(comprobanteDePago.getCodigo(), comprobanteDePago.getNumero().toString(),
                        9, txtComentario.getText(), 10, listaDetalleEnt, fechaContable);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void registraravanceAsuplidor(ComprobanteDePago recibo) {

        Double montoAvance = 0.00, totalPagoConAvance = 0.00, montoDisponible = 0.00;
        Boolean actualizar = false;
        AvanceASuplidor avanceASuplidor = null;
        List<DetalleAvanceASuplidor> lsitaDet = new ArrayList<>();

        avanceASuplidor = ManejoAvanceASuplidor.getInstancia().getAvanceASuplidore(recibo.getSuplidor().getCodigo());

        if (avanceASuplidor == null) {

            avanceASuplidor = new AvanceASuplidor();
            avanceASuplidor.setFecha(new Date());
            avanceASuplidor.setSuplidor(getSuplidor());
            avanceASuplidor.setNombreSuplidor(getSuplidor().getDescripcion());
            avanceASuplidor.setUsuario(utiles.VariablesGlobales.USUARIO);

            actualizar = false;
            avanceASuplidor.setFechaActualizacion(new Date());
        } else {
            actualizar = true;
            avanceASuplidor.setFechaActualizacion(new Date());
        }

        DetalleAvanceASuplidor detAvence = new DetalleAvanceASuplidor();

        detAvence.setAvanceASuplidor(avanceASuplidor);

        detAvence.setPendiente(recibo.getMonto());
        detAvence.setAnulado(false);
        detAvence.setFecha(new Date());
        detAvence.setComprobanteDePago(recibo);

        if (recibo.getTipoComprobante().equals("PF")) {
            detAvence.setCredito(recibo.getMonto());
            detAvence.setDebito(0.00);
            detAvence.setAvance(recibo.getMonto());

        } else if (recibo.getTipoComprobante().equals("AV")) {

            detAvence.setDebito(recibo.getMonto());
            detAvence.setCredito(0.00);
            detAvence.setAvance(recibo.getMonto());

        }

        lsitaDet.add(detAvence);

        if (detAvence.getAvance() > 0) {
            montoAvance += detAvence.getAvance();
        }

        montoAvance = ClaseUtil.roundDouble(montoAvance, 2);
        avanceASuplidor.setDetalleAvanceASuplidorCollection(lsitaDet);

        totalPagoConAvance = ManejoAvanceASuplidor.getInstancia().getTotalPagadoConavance(recibo.getSuplidor().getCodigo());

        if (totalPagoConAvance == null) {
            totalPagoConAvance = 0.00;
        }

        System.out.println("totalPagoConAvance : " + totalPagoConAvance);
        if (actualizar == false) {

            avanceASuplidor.setTotalPendiente(montoAvance);
            avanceASuplidor.setTotalAvance(montoAvance);
            avanceASuplidor = ManejoAvanceASuplidor.getInstancia().salvar(avanceASuplidor);

        } else {

            montoAvance += ManejoAvanceASuplidor.getInstancia().getTotalAvance(avanceASuplidor.getCodigo());
            avanceASuplidor.setTotalAvance(ClaseUtil.roundDouble(montoAvance, 2));

            montoDisponible = ManejoAvanceASuplidor.getInstancia().getMontoDisponible(avanceASuplidor.getSuplidor().getCodigo());
            avanceASuplidor.setTotalPendiente(montoDisponible);
            avanceASuplidor = ManejoAvanceASuplidor.getInstancia().actualizar(avanceASuplidor);
        }

        if (avanceASuplidor == null) {

            ClaseUtil.mensaje(" Hubo un problema registrando el avance a suplidor ");

        }

    }

    private void guardarComprobanteConFactura() {

        if (tbFacturaaPagar.getItems().size() <= 0) {

            ClaseUtil.mensaje("Tiene que agregar factura para el pago");
            return;
        }

        if (txtMontoAPagar.getText().isEmpty()) {

            ClaseUtil.mensaje(" El Monto Pagado Tiene que ser igual al monto de la Factura ");
            return;
        }

        if (listFormaPagoCollection == null) {

            ClaseUtil.mensaje(" Tiene que seleccionar una forma de pago");
            return;
        }

        if (getDevuelta() < 0) {

            ClaseUtil.mensaje(" El Monto  Pagado Tiene que ser igual al monto de la Factura ");
            return;
        }

        //Proceso para registrar el asiento contable
        if (listaDetalleEnt.size() > 0) {

            if (txtConcepto.getText().isEmpty()) {

                util.ClaseUtil.mensaje("Tiene que digitar el Concepto");
                txtConcepto.requestFocus();
                return;
            }

            if (tblDetalleEnt.getItems().size() <= 0) {

                util.ClaseUtil.mensaje("No hay Cuentas contable Agregada");

                return;
            }

            if (getTotalDebito() <= 0) {

                util.ClaseUtil.mensaje("El valor del debito y el credito no pueden ser igual o menor que cero");

                return;
            }

            Double valor = Double.parseDouble(txtValorAContabilizar.getText());

            if (!(Double.compare(getTotalDebito(), valor) == 0)) {

                util.ClaseUtil.mensaje("El valor del debito y el credito no pueden ser diferentes del valor de la factura");

                return;
            }

            if ((getTotalDebito() - getTotalCredito()) != 0) {

                util.ClaseUtil.mensaje("El debito y el credito tienen que ser iguales");

                return;
            }
//
//                if (estado.equals("CERRADO")) {
//
//                    ClaseUtil.mensaje("El Periodo Contable ya Esta Cerrado en esta  Fecha : " + new SimpleDateFormat("yyyy-MM-dd").format(fecha));
//                    return;
//                }

            if (txtComentario.getText().isEmpty()) {

                util.ClaseUtil.mensaje("El Concepto del asiento  no puede estar vacio");
                txtComentario.requestFocus();
                return;
            }

        }

        NumeroALetra nel = new NumeroALetra();

        comprobanteDePago.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
        comprobanteDePago.setAnulado(false);
        comprobanteDePago.setBalancePendiente(0.00);
        comprobanteDePago.setSuplidor(getSuplidor());
        comprobanteDePago.setConcepto(txtConcepto.getText());
        comprobanteDePago.setMonto(getTotalPago());
        comprobanteDePago.setUsuario(VariablesGlobales.USUARIO);
        comprobanteDePago.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
        comprobanteDePago.setMontoEnLetra(nel.Convertir(comprobanteDePago.getMonto().toString(), true));
        comprobanteDePago.setFechaRegistro(new Date());
        comprobanteDePago.setTipoComprobante("PF");

        Double nuevoPendiente = 0.00, totalAbono = 0.00, montoAvance = 0.00, abonoActual = 0.00, pendienteActual = 0.00;
        //Actualizando Detalle Recibo
        for (DetalleComprobanteDePago det : detalleComprobanteDePago) {

            abonoActual = ManejoFacturaSuplidor.getInstancia().getMontoPagado(det.getFactura().getCodigo());
            pendienteActual = ManejoFacturaSuplidor.getInstancia().getMontoPendiente(det.getFactura().getCodigo());

            System.out.println("abonoActual : " + abonoActual + " pendienteActual : " + pendienteActual
                    + " de fact : " + det.getFactura().getCodigo());

//                Double pendiente = det.getFactura().getTotal() - det.getFactura().getAbono();
//                det.getFactura().setPendiente(FormatNum.FormatearDouble(pendiente, 2));
            totalAbono = abonoActual + det.getTotal();
//            det.getFactura().setAbono(FormatNum.FormatearDouble(totalAbono, 2));

            nuevoPendiente = det.getFactura().getTotal() - totalAbono;
            nuevoPendiente = ClaseUtil.roundDouble(nuevoPendiente, 4);
            montoAvance = det.getTotal() - pendienteActual;

            if (Double.compare(det.getFactura().getTotal(), totalAbono) == 0) {

                det.setPendiente(0.0);
                System.out.println(" nuevoPendiente  : " + nuevoPendiente + " de fact : " + det.getFactura().getCodigo());

            } else if (nuevoPendiente > 0) {

                det.setPendiente(nuevoPendiente);
                System.out.println("nuevoPendiente > 0  : " + nuevoPendiente);

            } else if (nuevoPendiente <= 0) {

                System.out.println("nuevoPendiente<= 0  : " + nuevoPendiente);
                det.setPendiente(0.00);

            }

            if (montoAvance > 0) {
                det.setTotal(det.getFactura().getPendiente());//pago el pendiente de la factura
            } else {
                montoAvance = 0.00;
            }

            det.setMontoAvance(ClaseUtil.roundDouble(montoAvance, 4));

            if (Double.compare(det.getFactura().getTotal(), det.getFactura().getAbono()) == 0) {

                det.getFactura().setPagada(true);
            }

//
//            Double abono = det.getFactura().getAbono() + det.getTotal();
//            det.getFactura().setAbono(FormatNum.FormatearDouble(abono, 2));
//            det.getFactura().setFechaPago(comprobanteDePago.getFecha());
//
//            Double pendiente = det.getFactura().getTotal() - det.getFactura().getAbono();
//
//            if (Double.compare(det.getFactura().getTotal(), det.getFactura().getAbono()) == 0) {
//
//                det.setPendiente(0.0);
//            } else {
//
//                det.setPendiente(FormatNum.FormatearDouble(pendiente, 2));
//            }
            ManejoFacturaSuplidor.getInstancia().actualizar(det.getFactura());

        }

        //Si no se guarda el detalle del recibo,esporque en la entidad recibo al que agregarle el campo cascade
        comprobanteDePago.setDetalleComprobanteDePagoCollection(detalleComprobanteDePago);

        comprobanteDePago = ManejoComprobanteDePago.getInstancia().salvar(comprobanteDePago);

        if (!(comprobanteDePago == null)) {

            codigoRecibo = comprobanteDePago.getCodigo();

            for (FormaPago fp : listFormaPagoCollection) {

                fp.setDocumento(comprobanteDePago.getCodigo());
                fp.setTipoDocumento(9);
                fp.setFecha(util.ClaseUtil.asDate(dpFecha.getValue()));
                ManejoFormaPago.getInstancia().salvar(fp);

            }

            //Actualizando factura
            for (DetalleComprobanteDePago det : detalleComprobanteDePago) {

                System.out.println("Actualizando factura ***** :  " + det.getFactura().getCodigo());
                abonoActual = ManejoFacturaSuplidor.getInstancia().getMontoPagado(det.getFactura().getCodigo());
                System.out.println("abonoActual : " + abonoActual);
                nuevoPendiente = det.getFactura().getTotal() - abonoActual;
                System.out.println("nuevoPendiente :" + nuevoPendiente);
                det.getFactura().setAbono(FormatNum.FormatearDouble(abonoActual, 2));

                det.getFactura().setPendiente(FormatNum.FormatearDouble(nuevoPendiente, 2));

                if (Double.compare(det.getFactura().getTotal(), det.getFactura().getAbono()) == 0) {

                    det.getFactura().setPagada(true);
                }

                ManejoFacturaSuplidor.getInstancia().actualizar(det.getFactura());

            }

//            System.out.println("getTotalavanve() " + getTotalavanve());
//            if (getTotalavanve() > 0 || getTotalPagoConAvance() > 0) {
////        if (getTotalavanve() > 0) {
//
//                System.out.println("tiene avance ");
//
//                registraravanceDeCliente(reciboIngresoDB.getCodigo());
//
//            } else {
//                System.out.println("no tiene avance ");
//            }
            //********************ACTUALIZAR CAJA CHICA*********************
//                abonoActual = ManejoFacturaSuplidor.getInstancia().getMontoPagado(det.getFactura().getCodigo());
//                pendienteActual = ManejoFacturaSuplidor.getInstancia().getMontoPendiente(det.getFactura().getCodigo());
//
//                System.out.println("abonoActual : " + abonoActual + " pendienteActual : " + pendienteActual
//                        + " de fact : " + det.getFactura().getCodigo());
//
////                Double pendiente = det.getFactura().getTotal() - det.getFactura().getAbono();
////                det.getFactura().setPendiente(FormatNum.FormatearDouble(pendiente, 2));
//                totalAbono = abonoActual + det.getTotal();
////            det.getFactura().setAbono(FormatNum.FormatearDouble(totalAbono, 2));
//
//                nuevoPendiente = det.getFactura().getTotal() - totalAbono;
//                nuevoPendiente = ClaseUtil.roundDouble(nuevoPendiente, 4);
//                montoAvance = det.getTotal() - pendienteActual;
//
//                if (Double.compare(det.getFactura().getTotal(), totalAbono) == 0) {
//
//                    det.setPendiente(0.0);
//                    System.out.println(" nuevoPendiente  : " + nuevoPendiente + " de fact : " + det.getFactura().getCodigo());
//
//                } else if (nuevoPendiente > 0) {
//
//                    det.setPendiente(nuevoPendiente);
//                    System.out.println("nuevoPendiente > 0  : " + nuevoPendiente);
//
//                } else if (nuevoPendiente <= 0) {
//
//                    System.out.println("nuevoPendiente<= 0  : " + nuevoPendiente);
//                    det.setPendiente(0.00);
//
//                }
//
//                if (montoAvance > 0) {
//                    det.setTotal(det.getFactura().getPendiente());//pago el pendiente de la factura
//                } else {
//                    montoAvance = 0.00;
//                }
//
//                det.setMontoAvance(ClaseUtil.roundDouble(montoAvance, 4));
//
//                if (Double.compare(det.getFactura().getTotal(), det.getFactura().getAbono()) == 0) {
//
//                    det.getFactura().setPagada(true);
//                }
//
//                ManejoFacturaSuplidor.getInstancia().actualizar(det.getFactura());
//
//            }
//
//            Date fechaContable = comprobanteDePago.getFecha();
//            if (listaDetalleEnt.size() > 0) {
//
//                EntradaDiarioDao.getInstancia().registrarEntradaDiario(comprobanteDePago.getCodigo(), comprobanteDePago.getNumero().toString(),
//                        9, txtComentario.getText(), 10, listaDetalleEnt, fechaContable);
//
//            }
//
            System.out.println("getTotalavanve() " + getTotalavanve());

            if (getTotalavanve() > 0 || getTotalPagoConAvance() > 0) {

                System.out.println("tiene avance ");

                registraravanceAsuplidor(comprobanteDePago);

            } else {
                System.out.println("no tiene avance ");
            }

        }
    }

    private Double getTotalPagoConAvance() {

        Double total = 0.00;

        for (DetalleComprobanteDePago det : tbFacturaaPagar.getItems()) {

            total += det.getPagoConAvance();

        }

        System.out.println("det.getPagoConAvance() : " + total);
        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getTotalavanve() {

        Double total = 0.00;

        for (DetalleComprobanteDePago det : tbFacturaaPagar.getItems()) {

            total += det.getMontoAvance();

        }

        System.out.println("det.getMontoAvance() : " + total);
        return FormatNum.FormatearDouble(total, 2);
    }

}
