/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.notaDebito;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.suplidor.FXMLBusSuplidorController;
import controlador.venta.cliente.FXMLBusClienterController;
import controlador.venta.puntoVenta.FormaDePagoController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import manejo.contabilidadd.ConfiguracionCuentaContableDao;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.contabilidadd.TipoDocumentoDao;
import manejo.cxp.ManejoFacturaSuplidor;
import manejo.cxp.avanceCliente.ManejoAvanceDeCliente;
import manejo.documento.ManejoTipodocumento;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoRelacionNcf;
import manejo.factura.ManejoTipoNcf;
import manejo.notaCredito.ManejoNotaDeCredito;
import manejo.notaDebito.ManejoNotaDebito;
import manejo.notaDebito.ManejoRazonNotaDebito;
import manejo.notaDebito.ManejoTipoNotaDebito;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.Cliente;
import modelo.ConfiguracionCuentaContable;
import modelo.DetalleEntradaDiario;
import modelo.DetalleReciboIngreso;
import modelo.Factura;
import modelo.FacturaSuplidor;
import modelo.FormaPago;
import modelo.NotaDebito;
import modelo.RazonNotaDebito;
import modelo.ReciboIngreso;
import modelo.RelacionNcf;
import modelo.SecuenciaDocumento;
import modelo.Suplidor;
import modelo.TipoNcf;

import utiles.ClaseUtil;
import utiles.FormatNum;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroNotaDebitoController implements Initializable {

    @FXML
    private JFXButton btnBuscarSuplidor;

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

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }

    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnGuardar;
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
    private TableView<NotaDebito> tbFacturaaPagar;
    @FXML
    private TableColumn<NotaDebito, String> tbcFacturaAPagar;
    @FXML
    private TableColumn<NotaDebito, Double> tbcMontoPendienteAPagar;
    @FXML
    private TableColumn<NotaDebito, Double> tbcMontoAPagar;
    @FXML
    private TableColumn<NotaDebito, String> tbcMontoAplicar;
    @FXML
    private JFXTextField txtMontoAPagar;
    @FXML
    private TextArea txtConcepto;

    @FXML
    private JFXDatePicker dpFecha;

    ObservableList<Factura> listaFacturaPendiente = FXCollections.observableArrayList();
    ObservableList<Factura> listaFacturaAPagar = FXCollections.observableArrayList();
    ObservableList<DetalleReciboIngreso> listaDetalleRecibo = FXCollections.observableArrayList();
    ObservableList<RazonNotaDebito> listaRazonNotaDebito = FXCollections.observableArrayList();
    ObservableList<NotaDebito> listaNotaDebito = FXCollections.observableArrayList();

    Cliente cliente;
    NotaDebito notaDebito;
    DetalleReciboIngreso detalleReciboIngreso;
    RelacionNcf relacionNcf;
    List<FormaPago> listFormaPagoCollection;
    Suplidor suplidor;
    int codigoRecibo;
    int tipoNc = 0;

    Double devuelta;
    @FXML
    private JFXTextField txtRecibo;
    @FXML
    private JFXComboBox<RazonNotaDebito> cbConceptoPorCobro;

    Double avanceDisponible = 0.00;
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

        listaRazonNotaDebito.addAll(ManejoRazonNotaDebito.getInstancia().getRazonNotaDebito());

        cbConceptoPorCobro.setConverter(new StringConverter<RazonNotaDebito>() {

            @Override
            public String toString(RazonNotaDebito razon) {
                return razon.getNombre();
            }

            @Override
            public RazonNotaDebito fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbConceptoPorCobro.setItems(listaRazonNotaDebito);

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

        tbFacturaaPagar.setItems(listaNotaDebito);

        tbcFacturaAPagar.setCellValueFactory(new PropertyValueFactory<>("factura"));
//        tbcFFechaAPagar.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcMontoAPagar.setCellValueFactory(new PropertyValueFactory<>("monto"));
        tbcMontoAplicar.setCellValueFactory(new PropertyValueFactory<>("montoFactura"));
        tbcMontoPendienteAPagar.setCellValueFactory(new PropertyValueFactory<>("montoFactura"));
        tbFacturaaPagar.setEditable(true);

        tbcMontoAplicar.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getMontoFactura().toString());
                    }
                    return property;
                });

        tbcFacturaAPagar.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getNcfAfectado());
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

                NotaDebito p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00,
                        descuento = 0.00, impuesto = 0.00;

                if (data.getNewValue() >= 1) {

                    if (data.getNewValue() > p.getMontoFactura()) {

                        ClaseUtil.mensaje("El valor no puede ser mayor que el de la factura ");
                        p.setMonto(0.00);
                        tbFacturaaPagar.refresh();
                        tbFacturaaPagar.requestFocus();
                        return;
                    }

                    total = data.getNewValue();

                    p.setMonto(total);

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

                ClaseUtil.mensaje(" La secuencia para la Nota de debito  de la unidad de negocio "
                        + "" + utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return;
            }

            if (getCliente() == null && getSuplidor() == null) {
                ClaseUtil.mensaje("Tiene que seleccionar un socio de negocio ");
                return;
            }

            if (txtRecibo.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar el numero de comprobante ");
                txtRecibo.requestFocus();
                return;
            }

            if (tbFacturaaPagar.getItems().size() <= 0) {

                ClaseUtil.mensaje("Tiene que agregar factura para el pago con nota de creedito ");
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

            if (notaDebito.getMonto() <= 0) {

                ClaseUtil.mensaje(" El Monto aplicar de la nota de credito no puede ser igual o menor que cero ");

                return;
            }

//            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
//                    .getSecuenciaDocumento(VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo(), 23);
//
//            if (secDoc == null) {
//
//                ClaseUtil.mensaje(" La secuencia para la nota de credito de la unidad de negocio "
//                        + "" + utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
//
//                return;
//            }
            notaDebito = guardar();

            if (!(notaDebito == null)) {

                //Actualizar el ncf 
                if (!(relacionNcf == null)) {
                    ManejoRelacionNcf.getInstancia().actualizar(relacionNcf);
                }

                limpiar();
                nuevo();
                ClaseUtil.mensaje("Nota de debito  creado exitosamente");
            } else {

                ClaseUtil.mensaje("Hubo un error creando la Nota de Debito");
            }

        } catch (Exception e) {
            ClaseUtil.mensaje(" Hubo un error creando la Nota de Debito ");
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

            tipoNc = 1;
            setCliente(articuloController.getCliente());
            txtCodigoCliente.setText(getCliente().getCodigo().toString());
            txtNombreCliente.setText(getCliente().getNombre());
            txtRncCliente.setText(getCliente().getRnc());
            txtTelefono.setText(getCliente().getTelefono());
            listaFacturaPendiente.clear();
            listaFacturaPendiente.addAll(ManejoFactura.getInstancia().getListaFacturaPendienteSinNotaDeDebito(getCliente()));
            txtMontoPendiente.setText(getTotalPendiente().toString());

            avanceDisponible = ManejoAvanceDeCliente.getInstancia().getMontoDisponible(getCliente().getCodigo());

            inicializarSecuencia();
        } else {
            txtRecibo.clear();
        }
    }

    @FXML
    private void btnagregarActionEvent(ActionEvent event) {

        try {

            if (!(tbFacturaPendiente.getSelectionModel().getSelectedIndex() == -1)) {

                Factura fact = tbFacturaPendiente.getSelectionModel().getSelectedItem();
                notaDebito = new NotaDebito();
                notaDebito.setFactura(fact.getCodigo());
                notaDebito.setNumeroFactura(fact.getNumero());
                notaDebito.setNcfAfectado(fact.getNcf());
                notaDebito.setMonto(0.00);
                notaDebito.setMontoFactura(fact.getTotal());
                listaNotaDebito.clear();
                listaNotaDebito.add(notaDebito);

                txtMontoAPagar.setText(getTotalPago().toString());
                txtMontoPendiente.setText(getTotalPendiente().toString());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        try {

            if (!(tbFacturaaPagar.getSelectionModel().getSelectedIndex() == -1)) {

                int index = tbFacturaaPagar.getSelectionModel().getSelectedIndex();
                listaNotaDebito.remove(index);
                txtMontoAPagar.setText(getTotalPago().toString());
                txtMontoPendiente.setText(getTotalPendiente().toString());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void btnEliminarTodosActionEvent(ActionEvent event) {

        if (tbFacturaaPagar.getItems().size() > 0) {

            listaFacturaPendiente.clear();
            listaFacturaPendiente.addAll(ManejoFactura.getInstancia().getLista(getCliente()));
            txtMontoPendiente.setText(getTotalPendiente().toString());

            txtMontoAPagar.clear();
            listaDetalleRecibo.clear();

        }

    }

    private void nuevo() {

        notaDebito = new NotaDebito();
        inicializarSecuencia();
        limpiar();

    }

    private void limpiar() {

        listaFacturaAPagar.clear();
        listaFacturaPendiente.clear();
        listaNotaDebito.clear();
        dpFecha.setValue(LocalDate.now());
        txtCodigoCliente.clear();
        txtConcepto.clear();
        txtRecibo.clear();
        txtRncCliente.clear();
        txtTelefono.clear();
        getTxtMontoAvanceCliente().clear();
        txtNombreCliente.clear();
        txtMontoAPagar.clear();
        txtMontoPendiente.clear();

        cbConceptoPorCobro.getSelectionModel().select(-1);
        getHbDetallePago().setVisible(true);

    }

    private Double getTotalPago() {

        Double total = 0.00;

        for (NotaDebito det : tbFacturaaPagar.getItems()) {

            total += det.getMonto();
            System.out.println("Valor " + total);
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getTotalPagado() {

        Double total = 0.00;

        for (NotaDebito det : tbFacturaaPagar.getItems()) {

            total += det.getMonto();
            System.out.println("Valor " + total);
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getTotalPendiente() {

        Double total = 0.00;

//        
        for (NotaDebito nc : tbFacturaaPagar.getItems()) {

            total += nc.getMontoFactura();
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private void inicializarSecuencia() {

        relacionNcf = ManejoRelacionNcf.getInstancia().getNCFUnidadDeNegocio("B03", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        if (!(relacionNcf == null)) {
            relacionNcf = ManejoRelacionNcf.getInstancia().getNCFUnidadDeNegocio("B03", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
            String ncf = ClaseUtil.getProximoNcf(relacionNcf, relacionNcf.getTipoNcf().getCodigo());
            txtRecibo.setText(ncf);
        }

    }

    private NotaDebito guardar() {

        notaDebito.setUnidadDeNegocio(utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio());

        SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(notaDebito.getUnidadDeNegocio().getCodigo(), 24);

        if (!(secDoc == null)) {

            boolean existe = ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero());

            if (existe) {

                System.out.println("existe " + secDoc.getNumero());

                while (ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero())) {

                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

                secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(notaDebito.getUnidadDeNegocio().getCodigo(), 24);

                notaDebito.setNumero(secDoc.getNumero());
                notaDebito.setSecuenciaDocumento(secDoc);

            } else {

                System.out.println("No existe " + secDoc.getNumero());
                notaDebito.setNumero(secDoc.getNumero());
                notaDebito.setSecuenciaDocumento(secDoc);
                secDoc.setNumero(secDoc.getNumero() + 1);
                ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

            }

        }

        if (tipoNc == 1) {

            TipoNcf tipoNcf = ManejoTipoNcf.getInstancia().getTipoNcf(15);//CODIGO 15 NOTA DE CREDITO

            relacionNcf = ManejoRelacionNcf.getInstancia()
                    .getNCFUnidadDeNegocio(tipoNcf, notaDebito.getUnidadDeNegocio().getCodigo());

            if (relacionNcf == null) {

                relacionNcf = ManejoRelacionNcf.getInstancia()
                        .getNCFUnidadDeNegocio(tipoNcf, 3);//Comprobante fiscal del grupo fs comercial

            }

            boolean existe = ManejoNotaDeCredito.getInstancia().existeNCF(relacionNcf.getActual());

            if (existe) {

                while (ManejoNotaDeCredito.getInstancia().existeNCF(relacionNcf.getActual())) {

                    relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo());

                }

            } else {

                relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo());

            }
            notaDebito.setNcf(relacionNcf.getActual());
            notaDebito.setNcfFechaValidoHasta(relacionNcf.getFechaValidoHasta());

        } else {

            notaDebito.setNcfFechaValidoHasta(null);
            notaDebito.setNcf(txtRecibo.getText());
        }

        notaDebito.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
        notaDebito.setFechaRegistro(new Date());
        notaDebito.setAnulada(false);
        notaDebito.setConcepto(txtConcepto.getText());

        if (tipoNc == 1) {//factura de venta
            notaDebito.setTipoDocumento(ManejoTipodocumento.getInstancia().getTipoDocumento(7));
            notaDebito.setTipoNd(ManejoTipoNotaDebito.getInstancia().getTipoNotaDebito(tipoNc));
            notaDebito.setClienteoOSuplidor(getCliente().getCodigo());
            notaDebito.setNombreSocioNegocio(getCliente().getNombre());

        } else if (tipoNc == 2) {//factura de compra

            notaDebito.setClienteoOSuplidor(getSuplidor().getCodigo());
            notaDebito.setNombreSocioNegocio(getSuplidor().getDescripcion());
            notaDebito.setTipoDocumento(ManejoTipodocumento.getInstancia().getTipoDocumento(12));
            notaDebito.setTipoNd(ManejoTipoNotaDebito.getInstancia().getTipoNotaDebito(tipoNc));
        }

        notaDebito.setUsuario(utiles.VariablesGlobales.USUARIO);
        notaDebito.setRazonNotaDebito(cbConceptoPorCobro.getSelectionModel().getSelectedItem());

        //Si no se guarda el detalle del recibo,esporque en la entidad recibo al que agregarle el campo cascade
//        notaDebito.setMonto(Double.parseDouble(getTxtMontoAvanceCliente().getText()));
        notaDebito = ManejoNotaDebito.getInstancia().salvar(notaDebito);

        return notaDebito;
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

        notaDebito.setUnidadDeNegocio(utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio());

        SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(notaDebito.getUnidadDeNegocio().getCodigo(), 24);

        if (!(secDoc == null)) {

            existeSec = true;
            boolean existe = ManejoNotaDeCredito.getInstancia().existeSecuencia(secDoc.getNumero());

            if (existe) {

                System.out.println("existe " + secDoc.getNumero());

                while (ManejoNotaDeCredito.getInstancia().existeSecuencia(secDoc.getNumero())) {

                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

                secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(notaDebito.getUnidadDeNegocio().getCodigo(), 23);

                notaDebito.setNumero(secDoc.getNumero());
                notaDebito.setSecuenciaDocumento(secDoc);

            } else {

                System.out.println("No existe " + secDoc.getNumero());
                notaDebito.setNumero(secDoc.getNumero());
                notaDebito.setSecuenciaDocumento(secDoc);
                secDoc.setNumero(secDoc.getNumero() + 1);
                ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

            }

        } else {

            existeSec = false;
        }

        return existeSec;
    }

    @FXML
    private void btnBuscarSuplidorActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        List<FacturaSuplidor> listaFacturaSuplidor = null;
        listaFacturaAPagar.clear();
        Factura factura = new Factura();
        loader.setLocation(getClass().getResource("/vista/suplidor/FXMLBusSuplidor.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        FXMLBusSuplidorController articuloController = loader.getController();

        if (!(articuloController.getSuplidor() == null)) {

            tipoNc = 2;
            setSuplidor(articuloController.getSuplidor());
            txtCodigoCliente.setText(getSuplidor().getCodigo().toString());
            txtNombreCliente.setText(getSuplidor().getDescripcion());
            txtRncCliente.setText(getSuplidor().getRnc());
            txtTelefono.setText(getSuplidor().getTelefono());
            txtRecibo.setEditable(true);
            txtRecibo.setDisable(false);

            listaFacturaSuplidor = ManejoFacturaSuplidor.getInstancia().getFacturaPendienteSinNotaDeDebito(getSuplidor());

            System.out.println("cantidad : " + listaFacturaSuplidor.size());
            for (FacturaSuplidor fas : listaFacturaSuplidor) {

                factura.setNcf(fas.getNcf());
                factura.setNumero(fas.getNumero());
                factura.setFecha(fas.getFecha());
                factura.setCodigo(fas.getCodigo());
                factura.setMonto(fas.getTotal());
                factura.setTotal(fas.getTotal());
                factura.setPendiente(fas.getPendiente());
                factura.setAbono(fas.getAbono());
                factura.setSecConComp(fas.getSuplidor().getCodigo());//codigo de suplidor
                listaFacturaPendiente.add(factura);
                factura = new Factura();
            }

            txtMontoPendiente.setText(getTotalPendiente().toString());

        } else {
            txtRecibo.setEditable(false);
            txtRecibo.setDisable(true);
        }
    }

}
