/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cxc.reciboIngreso;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.venta.cliente.FXMLBusClienterController;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import manejo.ReciboIngreso.ManejoConceptoPorCobro;
import manejo.ReciboIngreso.ManejoReciboIngreso;
import manejo.caja.ManejoCajaChica;
import manejo.caja.ManejoTipoMovimieto;
import manejo.contabilidadd.ConfiguracionCuentaContableDao;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.contabilidadd.TipoDocumentoDao;
import manejo.cxp.avanceCliente.ManejoAvanceDeCliente;
import manejo.documento.ManejoTipodocumento;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoFormaPago;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.AvanceDeCliente;
import modelo.CajaChica;
import modelo.Cliente;
import modelo.ConceptoPorCobro;
import modelo.ConfiguracionCuentaContable;
import modelo.DetalleAvanceDeCliente;
import modelo.DetalleCajaChica;
import modelo.DetalleEntradaDiario;
import modelo.DetalleReciboIngreso;
import modelo.Factura;
import modelo.FormaPago;
import modelo.ReciboIngreso;
import modelo.SecuenciaDocumento;
import reporte.cxc.RptReciboIngreso;
import utiles.ClaseUtil;
import utiles.FormatNum;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroReciboDeIngresoController implements Initializable {

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
     * @return the txtMontoAvance
     */
    public JFXTextField getTxtMontoAvance() {
        return txtMontoAvance;
    }

    /**
     * @param txtMontoAvance the txtMontoAvance to set
     */
    public void setTxtMontoAvance(JFXTextField txtMontoAvance) {
        this.txtMontoAvance = txtMontoAvance;
    }

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
     * @return the hbDetallePago
     */
    public HBox getHbDetallePago() {
        return hbDetallePago;
    }

    /**
     * @param hbDetallePago the hbDetallePago to set
     */
    public void setHbDetallePago(HBox hbDetallePago) {
        this.hbDetallePago = hbDetallePago;
    }

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
    private TableView<Factura> tbFacturaPendiente;
    @FXML
    private TableColumn<Factura, String> tbcFacturaPendiente;
    @FXML
    private TableColumn<Factura, Date> tbcFechaPendiente;
    @FXML
    private TableColumn<Factura, Double> tbcMontoPendiente;
    @FXML
    private TableColumn<Factura, Double> tbcMonto;
    @FXML
    private TableColumn<Factura, Double> tbcAbonoPendiente;
    @FXML
    private JFXTextField txtMontoPendiente;
    @FXML
    private TableView<DetalleReciboIngreso> tbFacturaaPagar;
    @FXML
    private TableColumn<DetalleReciboIngreso, String> tbcFacturaAPagar;
    @FXML
    private TableColumn<DetalleReciboIngreso, Double> tbcMontoPendienteAPagar;
    @FXML
    private TableColumn<DetalleReciboIngreso, Double> tbcMontoAPagar;
    @FXML
    private TableColumn<DetalleReciboIngreso, String> tbcMontoAplicar;
  
    @FXML
    private JFXTextField txtMontoAPagar;
    @FXML
    private TextArea txtConcepto;

    @FXML
    private JFXDatePicker dpFecha;

    ObservableList<Factura> listaFacturaPendiente = FXCollections.observableArrayList();
    ObservableList<Factura> listaFacturaAPagar = FXCollections.observableArrayList();
    ObservableList<DetalleReciboIngreso> listaDetalleRecibo = FXCollections.observableArrayList();
    ObservableList<ConceptoPorCobro> listaConceptoPorCobro = FXCollections.observableArrayList();

    Cliente cliente;
    ReciboIngreso reciboIngreso;
    DetalleReciboIngreso detalleReciboIngreso;
    List<FormaPago> listFormaPagoCollection;
    int codigoRecibo;

    Double devuelta;
    @FXML
    private JFXTextField txtRecibo;
    @FXML
    private Button btnTodos;
    @FXML
    private Button btnEliminarTodos;
    @FXML
    private JFXComboBox<ConceptoPorCobro> cbConceptoPorCobro;
    @FXML
    private JFXTextField txtMontoAvance;

    Double avanceDisponible = 0.00;
    @FXML
    private JFXCheckBox chAvanceCliente;
    @FXML
    private HBox hbDetallePago;
    @FXML
    private JFXTextField txtMontoAvanceCliente;

    public Double getDevuelta() {
        return devuelta;
    }

    public void setDevuelta(Double devuelta) {
        this.devuelta = devuelta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaFacturaAPagar();
        iniciarTablaFacturaPendiente();
        inicializarCombox();

        nuevo();

    }

    private void inicializarCombox() {

        listaConceptoPorCobro.addAll(ManejoConceptoPorCobro.getInstancia().getLista());

        cbConceptoPorCobro.setConverter(new StringConverter<ConceptoPorCobro>() {

            @Override
            public String toString(ConceptoPorCobro concepto) {
                return concepto.getNombre();
            }

            @Override
            public ConceptoPorCobro fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbConceptoPorCobro.setItems(listaConceptoPorCobro);

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

        tbFacturaaPagar.setItems(listaDetalleRecibo);

        tbcFacturaAPagar.setCellValueFactory(new PropertyValueFactory<>("factura"));
//        tbcFFechaAPagar.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcMontoAPagar.setCellValueFactory(new PropertyValueFactory<>("montoPagado"));
        tbcMontoAplicar.setCellValueFactory(new PropertyValueFactory<>("DetalleReciboIngreso.factura.total"));
        tbcMontoPendienteAPagar.setCellValueFactory(new PropertyValueFactory<>("pendiente"));      
        tbFacturaaPagar.setEditable(true);

        tbcMontoAplicar.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getFactura().getTotal().toString());
                    }
                    return property;
                });

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

                DetalleReciboIngreso p = data.getRowValue();
                Double subTotal = 0.00, montoPagado = 0.00, cantidad = 0.00,
                        descuento = 0.00, impuesto = 0.00;

                if (data.getNewValue() >= 1) {

                    montoPagado = data.getNewValue();

                    p.setMontoPagado(montoPagado);

                    if (Double.compare(p.getFactura().getPendiente(), montoPagado) == 0) {

                        p.setPagoConAvance(0.00);
                        p.setMontoAvance(0.00);

                    } else if (montoPagado > p.getFactura().getPendiente()) {
                        p.setPagoConAvance(0.00);
                    }

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
        nuevo();
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
            formaDePagoController.setCliente(getCliente().getCodigo());

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
    @SuppressWarnings("UnusedAssignment")
    private void btnGuardarActionevent(ActionEvent event) {

        try {

            if (asignarSecuencia() == false) {

                ClaseUtil.mensaje(" La secuencia para el recibo de ingreso de la unidad de negocio "
                        + "" + utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return;
            }

            if (getCliente() == null) {
                ClaseUtil.mensaje("Tiene que seleccionar un cliente ");
                return;
            }

            if (getChAvanceCliente().isSelected()) {

                if (getTxtMontoAvanceCliente().getText().isEmpty()) {

                    ClaseUtil.mensaje("Tiene que digitar el monto de avance ");

                    getTxtMontoAvanceCliente().requestFocus();
                    return;
                }

                if (cbConceptoPorCobro.getSelectionModel().getSelectedIndex() == -1) {

                    ClaseUtil.mensaje(" Tiene que seleccionar un concepto de cobro ");
                    cbConceptoPorCobro.requestFocus();
                    return;
                }

                if (txtConcepto.getText().isEmpty()) {

                    ClaseUtil.mensaje(" El concepto no puede estar vacio ");
                    txtConcepto.requestFocus();
                    return;
                }

                guardarAvance();

            } else {

                if (tbFacturaaPagar.getItems().size() <= 0) {

                    ClaseUtil.mensaje("Tiene que agregar factura para el pago ");
                    return;
                }

                if (txtMontoAPagar.getText().isEmpty()) {

                    ClaseUtil.mensaje(" El Monto Pagado Tiene que ser igual al monto de la Factura ");
                    return;
                }

                if (cbConceptoPorCobro.getSelectionModel().getSelectedIndex() == -1) {

                    ClaseUtil.mensaje(" Tiene que seleccionar un concepto de cobro ");
                    cbConceptoPorCobro.requestFocus();
                    return;
                }

                if (txtConcepto.getText().isEmpty()) {

                    ClaseUtil.mensaje(" El concepto no puede estar vacio ");
                    txtConcepto.requestFocus();
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

                guardarReciboPagoFactura();

            }

        } catch (Exception e) {
            ClaseUtil.mensaje(" Hubo un error creando el recibo ");
            e.printStackTrace();
        }
    }

    @FXML
    private void btnImprimirActionEvent(ActionEvent event) {

        try {

            if (codigoRecibo > 0) {
                RptReciboIngreso.getInstancia().imprimir(codigoRecibo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnBuscarClienteActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        listaDetalleRecibo.clear();

        loader.setLocation(getClass().getResource("/vista/venta/cliente/FXMLBusCliente.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        FXMLBusClienterController articuloController = loader.getController();

        if (!(articuloController.getCliente() == null)) {

            setCliente(articuloController.getCliente());
            txtCodigoCliente.setText(getCliente().getCodigo().toString());
            txtNombreCliente.setText(getCliente().getNombre());
            txtRncCliente.setText(getCliente().getRnc());
            txtTelefono.setText(getCliente().getTelefono());
            listaFacturaPendiente.clear();
            listaFacturaPendiente.addAll(ManejoFactura.getInstancia().getListaFacturaPendiente(getCliente()));

            Double montoPagado = 0.00, montoPendiente = 0.00;

            for (Factura fcObj : listaFacturaPendiente) {

                montoPagado = ManejoFactura.getInstancia().getMontoPagado(fcObj.getCodigo());
                montoPendiente = fcObj.getTotal() - montoPagado;

                fcObj.setPendiente(ClaseUtil.roundDouble(montoPendiente, 2));
                fcObj.setAbono(montoPagado);
            }

            txtMontoPendiente.setText(getTotalPendienteFactura().toString());

            avanceDisponible = ManejoAvanceDeCliente.getInstancia().getMontoDisponible(getCliente().getCodigo());
            getTxtMontoAvance().setText(avanceDisponible.toString());
        }
    }

    @FXML
    private void btnagregarActionEvent(ActionEvent event) {

        try {

            Double pendiente = 0.00;
            if (!(tbFacturaPendiente.getSelectionModel().getSelectedIndex() == -1)) {

                Factura fact = tbFacturaPendiente.getSelectionModel().getSelectedItem();
                detalleReciboIngreso = new DetalleReciboIngreso();
                detalleReciboIngreso.setFactura(fact);

                pendiente = ManejoFactura.getInstancia().getMontoPendiente(fact.getCodigo());

                detalleReciboIngreso.setTotal(pendiente);
                detalleReciboIngreso.setPendiente(pendiente);
                detalleReciboIngreso.setMontoFactura(fact.getTotal());

//                if (avanceDisponible > 0) {
//
//                    if (avanceDisponible > pendiente) {
//
//                        detalleReciboIngreso.setPagoConAvance(pendiente);
//                        avanceDisponible -= pendiente;
//                        avanceDisponible = ClaseUtil.roundDouble(avanceDisponible, 4);
//
//                    } else {
//
//                        detalleReciboIngreso.setPagoConAvance(avanceDisponible);
//                        avanceDisponible = 0.00;
//                    }
//
//                    detalleReciboIngreso.setMontoPagado(0.00);
//
//                } else {
//
                    detalleReciboIngreso.setPagoConAvance(0.00);
                    detalleReciboIngreso.setMontoPagado(fact.getPendiente());
//                }

                detalleReciboIngreso.setRecibo(reciboIngreso);
                listaFacturaAPagar.add(fact);
                listaDetalleRecibo.add(detalleReciboIngreso);
                int index = tbFacturaPendiente.getSelectionModel().getSelectedIndex();
                listaFacturaPendiente.remove(index);
                txtMontoAPagar.setText(getTotalPago().toString());
                txtMontoPendiente.setText(getTotalPendienteFactura().toString());
                btnFormaPago.setDisable(false);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnTodosActionEvent(ActionEvent event) {

        try {

            if (tbFacturaPendiente.getItems().size() > 0) {

                for (Factura fact : tbFacturaPendiente.getItems()) {

                    detalleReciboIngreso = new DetalleReciboIngreso();
                    detalleReciboIngreso.setFactura(fact);
                    detalleReciboIngreso.setPendiente(fact.getPendiente());
                    detalleReciboIngreso.setTotal(fact.getPendiente());

                    if (avanceDisponible > 0) {

                        if (avanceDisponible > fact.getPendiente()) {
                            detalleReciboIngreso.setPagoConAvance(fact.getPendiente());
                            avanceDisponible -= fact.getPendiente();
                            avanceDisponible = ClaseUtil.roundDouble(avanceDisponible, 4);
                        } else {
                            detalleReciboIngreso.setPagoConAvance(avanceDisponible);
                            avanceDisponible = 0.00;
                        }

                        detalleReciboIngreso.setMontoPagado(0.00);

                    } else {

                        detalleReciboIngreso.setPagoConAvance(0.00);
                        detalleReciboIngreso.setMontoPagado(fact.getPendiente());
                    }

                    detalleReciboIngreso.setRecibo(reciboIngreso);
                    listaFacturaAPagar.add(fact);

                    listaDetalleRecibo.add(detalleReciboIngreso);

                }

                listaFacturaPendiente.clear();
                txtMontoAPagar.setText(getTotalPago().toString());
                txtMontoPendiente.setText(getTotalPendienteFactura().toString());
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

                DetalleReciboIngreso detRecibo = tbFacturaaPagar.getSelectionModel().getSelectedItem();
                listaFacturaPendiente.add(detRecibo.getFactura());
                int index = tbFacturaaPagar.getSelectionModel().getSelectedIndex();
                listaDetalleRecibo.remove(index);
                txtMontoAPagar.setText(getTotalPago().toString());
                txtMontoPendiente.setText(getTotalPendienteFactura().toString());

                Double montoPagado = 0.00, montoPendiente = 0.00;

                for (Factura fcObj : listaFacturaPendiente) {

                    montoPagado = ManejoFactura.getInstancia().getMontoPagado(fcObj.getCodigo());
                    montoPendiente = fcObj.getTotal() - montoPagado;

                    fcObj.setPendiente(ClaseUtil.roundDouble(montoPendiente, 2));
                    fcObj.setAbono(montoPagado);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnEliminarTodosActionEvent(ActionEvent event) {

        if (tbFacturaaPagar.getItems().size() > 0) {

            listaFacturaPendiente.clear();
            listaFacturaPendiente.addAll(ManejoFactura.getInstancia().getLista(getCliente()));

            Double montoPagado = 0.00, montoPendiente = 0.00;

            for (Factura fcObj : listaFacturaPendiente) {

                montoPagado = ManejoFactura.getInstancia().getMontoPagado(fcObj.getCodigo());
                montoPendiente = fcObj.getTotal() - montoPagado;

                fcObj.setPendiente(ClaseUtil.roundDouble(montoPendiente, 2));
                fcObj.setAbono(montoPagado);
            }

            txtMontoPendiente.setText(getTotalPendienteFactura().toString());

            txtMontoAPagar.clear();
            listaDetalleRecibo.clear();

        }

    }

    private void llenarClientedeContado() {

        txtCodigoCliente.setText(getCliente().getCodigo().toString());
        txtNombreCliente.setText(getCliente().getNombre());
        txtRncCliente.setText(getCliente().getRnc());
        txtTelefono.setText(getCliente().getTelefono());
        btnBuscarCliente.setDisable(true);
    }

    private void nuevo() {

        reciboIngreso = new ReciboIngreso();
        inicializarSecuencia();
        limpiar();

    }

    private void limpiar() {

        listaFacturaAPagar.clear();
        listaFacturaPendiente.clear();
        listaDetalleRecibo.clear();
        dpFecha.setValue(LocalDate.now());
        txtCodigoCliente.clear();
        txtConcepto.clear();
        txtRncCliente.clear();
        txtTelefono.clear();
        getTxtMontoAvanceCliente().clear();
        txtNombreCliente.clear();
        txtMontoAPagar.clear();
        txtMontoPendiente.clear();
        getTxtMontoAvance().clear();
        btnFormaPago.setDisable(true);
        cbConceptoPorCobro.getSelectionModel().select(-1);
        getChAvanceCliente().setSelected(false);
        getHbDetallePago().setVisible(true);

    }

    private Double getTotalPago() {

        Double total = 0.00;

        for (DetalleReciboIngreso det : tbFacturaaPagar.getItems()) {

            total += det.getMontoPagado() + det.getPagoConAvance();
            System.out.println("Valor " + total);
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getTotalPagado() {

        Double total = 0.00;

        for (DetalleReciboIngreso det : tbFacturaaPagar.getItems()) {

            total += det.getTotal();
            System.out.println("Valor " + total);
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getTotalPagoConAvance() {

        Double total = 0.00;

        for (DetalleReciboIngreso det : tbFacturaaPagar.getItems()) {

            total += det.getPagoConAvance();

        }

        System.out.println("det.getPagoConAvance() : " + total);
        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getTotalavanve() {

        Double total = 0.00;

        for (DetalleReciboIngreso det : tbFacturaaPagar.getItems()) {

            total += det.getMontoAvance();

        }

        System.out.println("det.getMontoAvance() : " + total);
        return FormatNum.FormatearDouble(total, 2);
    }
    
    private Double getTotalPendienteFactura() {

        Double total = 0.00;

//        
        for (Factura fact : tbFacturaPendiente.getItems()) {

            total += fact.getPendiente();
        }

        return FormatNum.FormatearDouble(total, 2);
    }
    
    private Double getTotalPendiente() {

        Double total = 0.00;

//        
        for (DetalleReciboIngreso fact : tbFacturaaPagar.getItems()) {

            total += fact.getFactura().getPendiente();
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private void inicializarSecuencia() {

        txtRecibo.setText(ManejoReciboIngreso.getInstancia().getNumero().toString());
    }

    @SuppressWarnings("null")
    private void registraravanceDeCliente(int recibo) {

        Double montoAvance = 0.00, totalPagoConAvance = 0.00, montoAvanceRecibo = 0.00;
        Boolean actualizar = false;
        AvanceDeCliente avanceCliente = null;
        List<DetalleAvanceDeCliente> lsitaDetAvance = new ArrayList<>();
        List<DetalleReciboIngreso> listaDetalleReci = ManejoReciboIngreso.getInstancia().getDetalleRecibo(recibo);

        avanceCliente = ManejoAvanceDeCliente.getInstancia().getAvanceCliente(getCliente().getCodigo());

        if (avanceCliente == null) {

            avanceCliente = new AvanceDeCliente();
            avanceCliente.setFecha(new Date());
            avanceCliente.setCliente(getCliente());
            avanceCliente.setNombreCliente(getCliente().getNombre());
            avanceCliente.setUsuario(utiles.VariablesGlobales.USUARIO);
            actualizar = false;
        } else {
            actualizar = true;
        }

        avanceCliente.setFechaActualizacion(new Date());

        DetalleAvanceDeCliente detAvence = new DetalleAvanceDeCliente();

        for (DetalleReciboIngreso detReci : listaDetalleReci) {

            detAvence.setAvanceDeCliente(avanceCliente);
            detAvence.setAvance(detReci.getMontoAvance());
            detAvence.setPendiente(detReci.getMontoAvance());
            detAvence.setAnulado(false);
            detAvence.setFecha(new Date());
            detAvence.setReciboIngreso(reciboIngreso);
            if (detReci.getMontoAvance() > 0) {

                lsitaDetAvance.add(detAvence);
            }

            montoAvance += detAvence.getAvance();
            detAvence = new DetalleAvanceDeCliente();
        }

        montoAvance = ClaseUtil.roundDouble(montoAvance, 2);
        montoAvanceRecibo = montoAvance;
        avanceCliente.setDetalleAvanceDeClienteCollection(lsitaDetAvance);

        totalPagoConAvance = ManejoAvanceDeCliente.getInstancia().getTotalPagadoConavance(getCliente().getCodigo());

        System.out.println("totalPagoConAvance : " + totalPagoConAvance);
        if (actualizar == false) {

            avanceCliente.setTotalPendiente(montoAvance);
            avanceCliente.setTotalAvance(montoAvance);
            avanceCliente = ManejoAvanceDeCliente.getInstancia().salvar(avanceCliente);

        } else {

            montoAvance += ManejoAvanceDeCliente.getInstancia().getTotalAvance(avanceCliente.getCodigo());
            avanceCliente.setTotalAvance(montoAvance);
            avanceCliente.setTotalPendiente(ClaseUtil.roundDouble(montoAvance - totalPagoConAvance, 2));
            ManejoAvanceDeCliente.getInstancia().actualizar(avanceCliente);
        }

        if (avanceCliente == null) {

            ClaseUtil.mensaje(" Hubo un problema registrando el avance de cliente ");

        } else {

            if (montoAvanceRecibo > 0) {

                crearReciboDeAvance(avanceCliente, montoAvanceRecibo);
            }

        }

    }

    @FXML
    private void chAvanceClienteActionEvent(ActionEvent event) {

        if (getChAvanceCliente().isSelected()) {
            getTxtMontoAvanceCliente().setVisible(true);
            getHbDetallePago().setVisible(false);
        } else {

            getTxtMontoAvanceCliente().setVisible(false);
            getHbDetallePago().setVisible(true);
        }
    }

    private void guardarAvance() {

        reciboIngreso.setUnidadDeNegocio(utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio());

        SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(reciboIngreso.getUnidadDeNegocio().getCodigo(), 8);

        if (!(secDoc == null)) {

            boolean existe = ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero());

            if (existe) {

                System.out.println("existe " + secDoc.getNumero());

                while (ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero())) {

                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

                secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(reciboIngreso.getUnidadDeNegocio().getCodigo(), 8);

                reciboIngreso.setNumero(secDoc.getNumero());
                reciboIngreso.setSecuenciaDocumento(secDoc);

            } else {

                System.out.println("No existe " + secDoc.getNumero());
                reciboIngreso.setNumero(secDoc.getNumero());
                reciboIngreso.setSecuenciaDocumento(secDoc);
                secDoc.setNumero(secDoc.getNumero() + 1);
                ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

            }

        } else {

            ClaseUtil.mensaje(" La secuencia para el recibo de ingreso de la unidad de negocio "
                    + "" + utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");

            return;
        }

        reciboIngreso.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
        reciboIngreso.setFechaRegistro(new Date());
        reciboIngreso.setAnulado(false);
        reciboIngreso.setBalancePendiente(0.00);
        reciboIngreso.setCliente(getCliente());
        reciboIngreso.setConcepto(txtConcepto.getText());
        reciboIngreso.setUsuario(utiles.VariablesGlobales.USUARIO);
        reciboIngreso.setConceptoDeCobro(cbConceptoPorCobro.getSelectionModel().getSelectedItem());

        //Si no se guarda el detalle del recibo,esporque en la entidad recibo al que agregarle el campo cascade
        reciboIngreso.setMonto(Double.parseDouble(getTxtMontoAvanceCliente().getText()));
        reciboIngreso.setTipoRecibo("AV");
        reciboIngreso = ManejoReciboIngreso.getInstancia().salvar(reciboIngreso);

        if (reciboIngreso == null) {
            util.ClaseUtil.mensaje("Hubo un error creando el recibo de avance");
            return;

        }

        try {

            registraravanceDeCliente(reciboIngreso);
            RptReciboIngreso.getInstancia().imprimir(reciboIngreso.getCodigo());
            nuevo();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void guardarReciboPagoFactura() {

        reciboIngreso.setUnidadDeNegocio(utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio());
        reciboIngreso.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
        reciboIngreso.setFechaRegistro(new Date());
        reciboIngreso.setAnulado(false);
        reciboIngreso.setBalancePendiente(0.00);
        reciboIngreso.setCliente(getCliente());
        reciboIngreso.setConcepto(txtConcepto.getText());
        reciboIngreso.setUsuario(utiles.VariablesGlobales.USUARIO);
        reciboIngreso.setConceptoDeCobro(cbConceptoPorCobro.getSelectionModel().getSelectedItem());

        Double nuevoPendiente = 0.00, totalAbono = 0.00, montoAvance = 0.00, abonoActual = 0.00,
                pendienteActual = 0.00;
        //Actualizando Detalle Recibo
        for (DetalleReciboIngreso det : listaDetalleRecibo) {

            abonoActual = ManejoFactura.getInstancia().getMontoPagado(det.getFactura().getCodigo());
            pendienteActual = ManejoFactura.getInstancia().getMontoPendiente(det.getFactura().getCodigo());

            System.out.println("abonoActual : " + abonoActual + " pendienteActual : " + pendienteActual
                    + " de fact : " + det.getFactura().getCodigo());

            System.out.println("det.getMontoAPagar() " + det.getMontoPagado() + "  det.getPagoConAvance() " + det.getPagoConAvance());
            det.setTotal(det.getMontoPagado() + det.getPagoConAvance());

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

        }

        //Si no se guarda el detalle del recibo,esporque en la entidad recibo al que agregarle el campo cascade
        reciboIngreso.setMonto(getTotalPagado());
        reciboIngreso.setTipoRecibo("PF");
        reciboIngreso.setDetalleReciboIngresoCollection(listaDetalleRecibo);

        ReciboIngreso reciboIngresoDB = ManejoReciboIngreso.getInstancia().salvar(reciboIngreso);

        if (reciboIngresoDB == null) {
            ClaseUtil.mensaje("Hubo un error guardando el recibo");
            return;
        }

        //Actualizando factura
        System.out.println("Actualizando factura ***** ");
        for (DetalleReciboIngreso detRecib : listaDetalleRecibo) {

            System.out.println("Actualizando factura ***** :  " + detRecib.getFactura().getCodigo());
            abonoActual = ManejoFactura.getInstancia().getMontoPagado(detRecib.getFactura().getCodigo());
            System.out.println("abonoActual : " + abonoActual);
            nuevoPendiente = detRecib.getFactura().getTotal() - abonoActual;
            System.out.println("nuevoPendiente :" + nuevoPendiente);
            detRecib.getFactura().setAbono(FormatNum.FormatearDouble(abonoActual, 2));

            detRecib.getFactura().setPendiente(FormatNum.FormatearDouble(nuevoPendiente, 2));

            if (Double.compare(detRecib.getFactura().getTotal(), detRecib.getFactura().getAbono()) == 0) {

                detRecib.getFactura().setPagada(true);
            }

            ManejoFactura.getInstancia().actualizar(detRecib.getFactura());

        }

        System.out.println("getTotalavanve() " + getTotalavanve());

        if (getTotalavanve() > 0 || getTotalPagoConAvance() > 0) {

            System.out.println("tiene avance ");

            registraravanceDeCliente(reciboIngresoDB.getCodigo());

        } else {
            System.out.println("no tiene avance ");
        }
        //********************ACTUALIZAR CAJA CHICA*********************
        System.out.println("actualizando caja chica ... ");
        actualizarCajaChica(reciboIngresoDB);

        //****************fin*********************
        codigoRecibo = reciboIngresoDB.getCodigo();

        for (FormaPago fp : listFormaPagoCollection) {

            fp.setDocumento(reciboIngresoDB.getCodigo());
            fp.setTipoDocumento(8);
            fp.setFecha(util.ClaseUtil.asDate(dpFecha.getValue()));
            ManejoFormaPago.getInstancia().salvar(fp);

        }

        //***************Registrar entrada de diario
        System.out.println("registrando entrada de diario ... ");

        registrarEntradaDeDiario(reciboIngresoDB);

        RptReciboIngreso.getInstancia().imprimir(reciboIngresoDB.getCodigo());

        nuevo();

    }

    private void registraravanceDeCliente(ReciboIngreso recibo) {

        Double montoAvance = 0.00, totalPagoConAvance = 0.00, montoDisponible = 0.00;
        Boolean actualizar = false;
        AvanceDeCliente avance = null;
        List<DetalleAvanceDeCliente> lsitaDet = new ArrayList<>();

        avance = ManejoAvanceDeCliente.getInstancia().getAvanceCliente(recibo.getCliente().getCodigo());

        if (avance == null) {

            avance = new AvanceDeCliente();
            avance.setFecha(new Date());
            avance.setCliente(getCliente());
            avance.setNombreCliente(getCliente().getNombre());
            avance.setUsuario(utiles.VariablesGlobales.USUARIO);
            actualizar = false;
        } else {
            actualizar = true;
        }

        avance.setFechaActualizacion(new Date());

        DetalleAvanceDeCliente detAvence = new DetalleAvanceDeCliente();

        detAvence.setAvanceDeCliente(avance);
        detAvence.setAvance(recibo.getMonto());
        detAvence.setPendiente(recibo.getMonto());
        detAvence.setAnulado(false);
        detAvence.setFecha(new Date());
        detAvence.setReciboIngreso(recibo);

        lsitaDet.add(detAvence);

        montoAvance += detAvence.getAvance();

        montoAvance = ClaseUtil.roundDouble(montoAvance, 2);
        avance.setDetalleAvanceDeClienteCollection(lsitaDet);

        totalPagoConAvance = ManejoAvanceDeCliente.getInstancia().getTotalPagadoConavance(recibo.getCliente().getCodigo());

        if (totalPagoConAvance == null) {
            totalPagoConAvance = 0.00;
        }
        System.out.println("totalPagoConAvance : " + totalPagoConAvance);
        if (actualizar == false) {

            avance.setTotalPendiente(montoAvance);
            avance.setTotalAvance(montoAvance);
            avance = ManejoAvanceDeCliente.getInstancia().salvar(avance);

        } else {

            montoAvance += ManejoAvanceDeCliente.getInstancia().getTotalAvance(avance.getCodigo());
            avance.setTotalAvance(ClaseUtil.roundDouble(montoAvance, 2));

            montoDisponible = ManejoAvanceDeCliente.getInstancia().getMontoDisponible(avance.getCliente().getCodigo());
            avance.setTotalPendiente(montoDisponible);
            avance = ManejoAvanceDeCliente.getInstancia().actualizar(avance);
        }

        if (avance == null) {

            ClaseUtil.mensaje(" Hubo un problema registrando el avance de cliente ");

        }

    }

    private void crearReciboDeAvance(AvanceDeCliente avanceDeCliente, double monto) {

        ReciboIngreso reciboavance = new ReciboIngreso();
        reciboavance.setUnidadDeNegocio(utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio());

        SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(reciboavance.getUnidadDeNegocio().getCodigo(), 8);

        if (!(secDoc == null)) {

            boolean existe = ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero());

            if (existe) {

                System.out.println("existe " + secDoc.getNumero());

                while (ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero())) {

                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

                secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(reciboIngreso.getUnidadDeNegocio().getCodigo(), 8);

                reciboavance.setNumero(secDoc.getNumero());
                reciboavance.setSecuenciaDocumento(secDoc);

            } else {

                System.out.println("No existe " + secDoc.getNumero());
                reciboavance.setNumero(secDoc.getNumero());
                reciboavance.setSecuenciaDocumento(secDoc);
                secDoc.setNumero(secDoc.getNumero() + 1);
                ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

            }

        } else {

            ClaseUtil.mensaje(" La secuencia para el recibo de ingreso de la unidad de negocio "
                    + "" + utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");

            return;
        }

        reciboavance.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
        reciboavance.setFechaRegistro(new Date());
        reciboavance.setAnulado(false);
        reciboavance.setBalancePendiente(0.00);
        reciboavance.setCliente(avanceDeCliente.getCliente());
        reciboavance.setConcepto("Avance de Cliente");
        reciboavance.setUsuario(utiles.VariablesGlobales.USUARIO);
        reciboavance.setConceptoDeCobro(ManejoConceptoPorCobro.getInstancia().getConceptoPorCobro(8));

        //Si no se guarda el detalle del recibo,esporque en la entidad recibo al que agregarle el campo cascade
        reciboavance.setMonto(monto);
        reciboavance.setTipoRecibo("AV");
        reciboavance = ManejoReciboIngreso.getInstancia().salvar(reciboavance);

        if (reciboavance == null) {
            util.ClaseUtil.mensaje("Hubo un error creando el recibo de avance");
            return;

        }

        try {

//            registraravanceDeCliente(reciboIngreso);
            RptReciboIngreso.getInstancia().imprimir(reciboavance.getCodigo());
            nuevo();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void actualizarCajaChica(ReciboIngreso reciboParam) {

        //Actualizar caja chica
        CajaChica cajaChica = ManejoCajaChica.getInstancia().getCajaChica(new Date(), "abierta");

        if (!(cajaChica == null)) {

            List<DetalleCajaChica> listaDetalleCajaChica = new ArrayList<>();

            DetalleCajaChica detalleCajaChica = new DetalleCajaChica();
            detalleCajaChica.setCajaChica(cajaChica);
            detalleCajaChica.setTipoMovimiento(ManejoTipoMovimieto
                    .getInstancia()
                    .getTipoMovimiento(9));

            detalleCajaChica.setConcepto("Cobros");
            detalleCajaChica.setTipoDocumento(ManejoTipodocumento
                    .getInstancia()
                    .getTipoDocumento(10));

            detalleCajaChica.setDocumento(reciboParam.getCodigo().toString());
            detalleCajaChica.setMonto(reciboParam.getMonto());
            detalleCajaChica.setNombreMovimiento("INGRESO");
            listaDetalleCajaChica.add(detalleCajaChica);

            cajaChica.setDetalleCajaChicaCollection(listaDetalleCajaChica);
            ManejoCajaChica.getInstancia().actualizar(cajaChica);
        }
        //fin

    }

    private void registrarEntradaDeDiario(ReciboIngreso reciboParam) {

        ConfiguracionCuentaContable config = ConfiguracionCuentaContableDao.getInstancia().getConfigCuentaPorTipoConcepto(5, 4);

        if (!(config == null)) {

            List<DetalleEntradaDiario> listaDetEntradaDiario = EntradaDiarioDao.getInstancia()
                    .generarConfiguracionCuenta(config, reciboParam);

            System.out.println("lista " + listaDetEntradaDiario);

            EntradaDiarioDao.getInstancia()
                    .registrarEntradaDiario(reciboParam.getCodigo(), reciboParam.getCodigo().toString(), TipoDocumentoDao
                            .getInstancia().getTipoDocumento(8).getCodigo(),
                            config.getDescripcion(), 4, listaDetEntradaDiario, reciboParam.getFecha());
        }
    }

    private Boolean asignarSecuencia() {

        Boolean existeSec = false;

        reciboIngreso.setUnidadDeNegocio(utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio());

        SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(reciboIngreso.getUnidadDeNegocio().getCodigo(), 8);

        if (!(secDoc == null)) {

            existeSec = true;
            boolean existe = ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero());

            if (existe) {

                System.out.println("existe " + secDoc.getNumero());

                while (ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero())) {

                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

                secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(reciboIngreso.getUnidadDeNegocio().getCodigo(), 8);

                reciboIngreso.setNumero(secDoc.getNumero());
                reciboIngreso.setSecuenciaDocumento(secDoc);

            } else {

                System.out.println("No existe " + secDoc.getNumero());
                reciboIngreso.setNumero(secDoc.getNumero());
                reciboIngreso.setSecuenciaDocumento(secDoc);
                secDoc.setNumero(secDoc.getNumero() + 1);
                ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

            }

        } else {

            existeSec = false;
        }

        return existeSec;
    }

    public void llenarCampo() {

        if (!(getCliente() == null)) {

            txtCodigoCliente.setText(getCliente().getCodigo().toString());
            txtNombreCliente.setText(getCliente().getNombre());
            txtRncCliente.setText(getCliente().getRnc());
            txtTelefono.setText(getCliente().getTelefono());
            listaFacturaPendiente.clear();
            listaFacturaPendiente.addAll(ManejoFactura.getInstancia().getListaFacturaPendiente(getCliente()));

            Double montoPagado = 0.00, montoPendiente = 0.00;

            for (Factura fcObj : listaFacturaPendiente) {

                montoPagado = ManejoFactura.getInstancia().getMontoPagado(fcObj.getCodigo());
                montoPendiente = fcObj.getTotal() - montoPagado;

                fcObj.setPendiente(ClaseUtil.roundDouble(montoPendiente, 2));
                fcObj.setAbono(montoPagado);
            }

            txtMontoPendiente.setText(getTotalPendiente().toString());

            avanceDisponible = ManejoAvanceDeCliente.getInstancia().getMontoDisponible(getCliente().getCodigo());
            getTxtMontoAvance().setText(avanceDisponible.toString());
        }
        
        
    }

}
