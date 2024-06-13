/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cxp.factura;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.compra.consulta.FXMLBusOrdenCompraController;
import controlador.contabilidad.consulta.ConsultaConfigCuentaContableController;
import controlador.contabilidad.registro.FXMLCatalogoConsController;
import controlador.inventario.articulo.FXMLBuscarArticuloController;
import controlador.proyecto.BuscarProyectoController;
import controlador.suplidor.FXMLBusSuplidorController;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.ManejoConfiguracion;
import manejo.articulo.ManejoArticulo;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.contabilidadd.ConfiguracionCuentaContableDao;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.cxp.ManejoCostosGastos;
import manejo.cxp.ManejoFacturaSuplidor;
import manejo.cxp.ManejoFormaPagodgii;
import manejo.cxp.ManejoTipoDeIsr;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoRelacionNcf;
import manejo.factura.ManejoTipoNcf;
import manejo.ordenCompra.OrdenDeCompraDao;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import manejo.suplidor.ImpuestoDao;
import manejo.suplidor.SuplidorDao;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.Catalogo;
import modelo.ConfiguracionCuentaContable;
import modelo.CostosYGastos;
import modelo.DetalleEntradaDiario;
import modelo.DetalleFacturaSuplidor;
import modelo.DetalleOrdenCompra;
import modelo.EntradaDiario;
import modelo.FacturaSuplidor;
import modelo.FormaPagoDgii;
import modelo.OrdenCompra;
import modelo.Proyecto;
import modelo.RelacionNcf;
import modelo.SecuenciaDocumento;
import modelo.Suplidor;
import modelo.TipoDeRetencionIsr;
import modelo.TipoNcf;
import reporte.cxp.RptFacturaSuplidor;
import util.ClaseUtil;
import util.FormatNum;
import util.RetencionDGII;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class RegistrarFacturaSuplidorController implements Initializable {

    @FXML
    private HBox hbBtnGuardar;
    @FXML
    private JFXTextField txtPropinaLegal;
    @FXML
    private JFXTextField txtOtrosImpuesto;
    @FXML
    private JFXTextField txtImpuestoSelectivoalConsumo;

    @FXML
    private JFXTextField txtMontoPropina;
    @FXML
    private JFXButton btnEliminarCuenta;
    @FXML
    private Label lbNumEntrada;
    @FXML
    private ProgressBar progresBar;
    @FXML
    private JFXButton btnConfigCuenta;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, Double> tbcMontoExcento;
    @FXML
    private JFXTextField txtMontoExcento;
    @FXML
    private JFXTextField txtMontoGravado;
    @FXML
    private JFXComboBox<TipoNcf> cbTipoNcf;

    /**
     * @return the editar
     */
    public boolean isEditar() {
        return editar;
    }

    /**
     * @param editar the editar to set
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * @return the facturaSuplidor
     */
    public FacturaSuplidor getFacturaSuplidor() {
        return facturaSuplidor;
    }

    /**
     * @param facturaSuplidor the facturaSuplidor to set
     */
    public void setFacturaSuplidor(FacturaSuplidor facturaSuplidor) {
        this.facturaSuplidor = facturaSuplidor;
    }

    @FXML
    private JFXComboBox<CostosYGastos> cbCostoGastos;
    @FXML
    private JFXTextField txtItbis1;
    @FXML
    private JFXTextField txtTotalItbisRetendo1;
    @FXML
    private JFXTextField txtTotalItbisProporcional;
    @FXML
    private JFXTextField txtTotalItbisAlCosto;
    @FXML
    private JFXTextField txtTotalItbisPorAdelantar;
    @FXML
    private JFXComboBox<TipoDeRetencionIsr> cbTipoDeRetencion;
    @FXML
    private Label lbCantidadDigito;

    /**
     * @return the suplidorCxp
     */
    public Suplidor getSuplidorCxp() {
        return suplidorCxp;
    }

    /**
     * @param suplidorCxp the suplidorCxp to set
     */
    public void setSuplidorCxp(Suplidor suplidorCxp) {
        this.suplidorCxp = suplidorCxp;
    }

    @FXML
    private TableColumn<DetalleFacturaSuplidor, Double> tbcItbisRetenido;
    @FXML
    private JFXTextField txtTotalItbisRetendo;
    @FXML
    private TextArea txtComentario;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private JFXButton btnCatalogoCons;
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
    @FXML
    private JFXButton btnAgregarCuenta;
    @FXML
    private JFXTextField txtSuplidorCxp;
    @FXML
    private JFXButton btBuscarSupplidor1;
    @FXML
    private JFXDatePicker dpFechaVencimiento;
    @FXML
    private JFXComboBox<FormaPagoDgii> cbFormaDePago;

    /**
     * @return the proyecto
     */
    public Proyecto getProyecto() {
        return proyecto;
    }

    /**
     * @param proyecto the proyecto to set
     */
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @FXML
    private JFXDatePicker dpFecha;

    @FXML
    private JFXTextField txtSuplidor;
    @FXML
    private JFXButton btBuscarSupplidor;

    @FXML
    private TextArea txtConcepto;
    @FXML
    private JFXTextField txtArticulo;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private TableView<DetalleFacturaSuplidor> tbDetalleFactura;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, Double> tbcPrecio;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, Double> tbcCantidad;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, String> tbcUnidad;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, Double> tbcSubTotal;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, Double> tbcDescuento;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, Double> tbcTotal;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, Double> tbcItbis;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, Double> tbcIsr;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, Double> tbcTotalAPagar;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, Double> tbcPorCientoPropina;
    @FXML
    private JFXTextField txtDescuento;
    @FXML
    private JFXTextField txtItbis;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXTextField txtSubTotal;
    @FXML
    private JFXTextField txtTotal;
    ObservableList<DetalleFacturaSuplidor> listaDetalleFacturaSuplidor = FXCollections.observableArrayList();
    ObservableList<ArticuloUnidad> listaUnidads = FXCollections.observableArrayList();
    ObservableList<FormaPagoDgii> listaFormaPago = FXCollections.observableArrayList();
    ObservableList<CostosYGastos> listaCostoYGastos = FXCollections.observableArrayList();
    ObservableList<TipoDeRetencionIsr> listaTipoDeRetencionIsrs = FXCollections.observableArrayList();
    ObservableList<TipoNcf> listaTipoNcf = FXCollections.observableArrayList();

    private Suplidor suplidor;
    private Suplidor suplidorCxp;
    private Articulo articulo;
    OrdenCompra ordenCompra;
    RelacionNcf relacionNcf;
    @FXML
    private JFXTextField txtCantidad;

    DetalleFacturaSuplidor detFactSuplidor;
    private FacturaSuplidor facturaSuplidor;
    @FXML
    private JFXButton btnBuscarArticulo;
    @FXML
    private JFXTextField txtNumeroOc;
    @FXML
    private JFXButton btnBuscarOrdenCompra;
    @FXML
    private JFXTextField txtNcf;

    @FXML
    private JFXTextField txtISR;
    @FXML
    private JFXTextField txtTotal1;
    @FXML
    private JFXComboBox<ArticuloUnidad> cbUnidad;
    @FXML
    private JFXTextField txtNombreProyecto;
    @FXML
    private JFXButton btnBuscarProyecto;
    private Proyecto proyecto;
    DetalleEntradaDiario detalle;
    private boolean editar;
    private boolean editarEntrada;
    EntradaDiario entradaDiarioDb;
    ObservableList<DetalleEntradaDiario> listaDetalleEnt = FXCollections.observableArrayList();

    TaskGuardarFactura taskGuardarFactura;

    public Catalogo getCatalogo() {
        return catalogo;
    }

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
    private Catalogo catalogo;
    Double montoPropina = 0.00, otrosImpuesto = 0.00, impuestoSelectivoAlConsumo = 0.00;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        setFacturaSuplidor(new FacturaSuplidor());
        iniciarTablaDetalleFactura();
        inicializarTablaEntradaDiario();
        inicializarCombox();
        inicializarSecuencia();
        dpFecha.setValue(null);
        progresBar.setVisible(false);
        lbCantidadDigito.setVisible(false);
//        dpFecha.setValue(LocalDate.now());

        txtNcf.setOnKeyReleased((event) -> {
            lbCantidadDigito.setVisible(true);
            lbCantidadDigito.setText(Integer.toString(txtNcf.getText().length()));
        });

        txtCantidad.setOnKeyReleased((event) -> {

            if (event.getCode() == KeyCode.ENTER) {

                try {

                    if (txtArticulo.getText().isEmpty()) {
                        ClaseUtil.mensaje("Tiene que seleccionar un articulo");
                        return;
                    }

                    if (txtCantidad.getText().isEmpty()) {

                        ClaseUtil.mensaje(" Tiene que digital una cantidad ");
                        txtCantidad.requestFocus();
                        return;
                    }

                    agregarArticulo();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        txtTotalItbisAlCosto.setOnKeyReleased((event) -> {

            Double itbisPorAdelantar = 0.00;

            try {

                if (!txtTotalItbisAlCosto.getText().isEmpty()) {

                    itbisPorAdelantar = getTotalItbis() - Double.parseDouble(txtTotalItbisAlCosto.getText());
                    txtTotalItbisPorAdelantar.setText(itbisPorAdelantar.toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        txtPropinaLegal.setOnKeyReleased((event) -> {

            try {

                if (!txtPropinaLegal.getText().isEmpty()) {

                    montoPropina = Double.parseDouble(txtPropinaLegal.getText());
                    txtTotal.setText(getTotal().toString());
                    txtTotal1.setText(getTotalApagar().toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        txtOtrosImpuesto.setOnKeyReleased((event) -> {

            try {

                if (!txtOtrosImpuesto.getText().isEmpty()) {

                    otrosImpuesto = Double.parseDouble(txtOtrosImpuesto.getText());
                    txtTotal.setText(getTotal().toString());
                    txtTotal1.setText(getTotalApagar().toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        txtImpuestoSelectivoalConsumo.setOnKeyReleased((event) -> {

            try {

                if (!txtImpuestoSelectivoalConsumo.getText().isEmpty()) {

                    impuestoSelectivoAlConsumo = Double.parseDouble(txtImpuestoSelectivoalConsumo.getText());
                    txtTotal.setText(getTotal().toString());
                    txtTotal1.setText(getTotalApagar().toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    private void inicializarCombox() {

        listaTipoNcf.addAll(ManejoTipoNcf.getInstancia().getListaTipoNcf("compra"));

        cbTipoNcf.setItems(listaTipoNcf);

        cbTipoNcf.setConverter(new StringConverter<TipoNcf>() {

            @Override
            public String toString(TipoNcf unidad) {
                return unidad.getDescripcion();
            }

            @Override
            public TipoNcf fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

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

        cbCostoGastos.setConverter(new StringConverter<CostosYGastos>() {

            @Override
            public String toString(CostosYGastos costo) {
                return String.valueOf(costo.getNombre());
            }

            @Override
            public CostosYGastos fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoDeRetencion.setConverter(new StringConverter<TipoDeRetencionIsr>() {

            @Override
            public String toString(TipoDeRetencionIsr tipoIsr) {
                return String.valueOf(tipoIsr.getNombre());
            }

            @Override
            public TipoDeRetencionIsr fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbFormaDePago.setConverter(new StringConverter<FormaPagoDgii>() {

            @Override
            public String toString(FormaPagoDgii formaPago) {
                return String.valueOf(formaPago.getNombre());
            }

            @Override
            public FormaPagoDgii fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaFormaPago.addAll(ManejoFormaPagodgii.getInstancia().getListaFormaPagoDgii());
        listaCostoYGastos.addAll(ManejoCostosGastos.getInstancia().getListaCostosYGastos());
        listaTipoDeRetencionIsrs.addAll(ManejoTipoDeIsr.getInstancia().getListaTipoDeRetencionIsr());

        cbUnidad.setItems(listaUnidads);
        cbCostoGastos.setItems(listaCostoYGastos);
        cbFormaDePago.setItems(listaFormaPago);
        cbTipoDeRetencion.setItems(listaTipoDeRetencionIsrs);

    }

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) throws IOException {

        try {

            if (txtArticulo.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que seleccionar un articulo");
                return;
            }

            if (txtCantidad.getText().isEmpty()) {

                ClaseUtil.mensaje(" Tiene que digital una cantidad ");
                return;
            }

            agregarArticulo();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        try {

            if (tbDetalleFactura.getSelectionModel().getSelectedIndex() != -1) {

                listaDetalleFacturaSuplidor.remove(tbDetalleFactura.getSelectionModel().getSelectedIndex());
                tbDetalleFactura.refresh();
                totales();

            } else {
                ClaseUtil.mensaje("Tiene que selccionar un registro");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            getFacturaSuplidor().setUnidadDeNegocio(utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio());

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(getFacturaSuplidor().getUnidadDeNegocio().getCodigo(), 6);

            if (secDoc == null) {

                ClaseUtil.mensaje("LA SECUENCIA PARA LA ENTRADA DE DIARIO NO ESTA CONFIGURADA ");
                return;
            }

            if (!(cbTipoNcf.getSelectionModel().getSelectedIndex() == -1)) {
                validarNcf();
            } else {

                if (txtNcf.getText().isEmpty()) {

                    ClaseUtil.mensaje("Tiene que digitar el ncf ");
                    txtNcf.requestFocus();
                    return;
                }

                if (!validar().isEmpty()) {

                    ClaseUtil.mensaje(validar());
                    return;
                }
            }

            //Proceso para registrar el asiento contable
            if (listaDetalleEnt.size() > 0) {

                if (txtConcepto.getText().isEmpty()) {

                    ClaseUtil.mensaje("Tiene que digitar el Concepto");
                    txtConcepto.requestFocus();
                    return;
                }

                if (tblDetalleEnt.getItems().size() <= 0) {

                    ClaseUtil.mensaje(" No hay Cuentas contable Agregada ");

                    return;
                }

                if (getTotalDebito() <= 0) {

                    ClaseUtil.mensaje(" El valor del debito y el credito no pueden ser igual o menor que cero ");

                    return;
                }

                Double valor = Double.parseDouble(txtValorAContabilizar.getText());

                if (!(Double.compare(getTotalDebito(), valor) == 0)) {

                    ClaseUtil.mensaje("El valor del debito y el credito no pueden ser diferentes del valor de la factura");

                    return;
                }

                if ((getTotalDebito() - getTotalCredito()) != 0) {

                    ClaseUtil.mensaje("El debito y el credito tienen que ser iguales");

                    return;
                }
//
//                if (estado.equals("CERRADO")) {
//
//                    ClaseUtil.mensaje("El Periodo Contable ya Esta Cerrado en esta  Fecha : " + new SimpleDateFormat("yyyy-MM-dd").format(fecha));
//                    return;
//                }

                if (txtComentario.getText().isEmpty()) {

                    ClaseUtil.mensaje("El Concepto del asiento  no puede estar vacio");
                    txtComentario.requestFocus();
                    return;
                }

            }

            if (!(txtNcf.getText().length() >= 11 && txtNcf.getText().length() <= 13)) {

                ClaseUtil.mensaje(" El ncf tiene que tener 11 caracteres  si no es electronico y 13 caracteres si es electronico ");
                txtNcf.requestFocus();
                return;
            }

            if (txtConcepto.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar un concepto");
                return;
            }

            tareaGuardarFactura();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void txtCantidadKeyPressed(KeyEvent event) {

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
                    Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
                    txtDiferencia.setText(diferencia.toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
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
                    Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
                    txtDiferencia.setText(diferencia.toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tblDetalleEnt.refresh();
                    tblDetalleEnt.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    public void iniciarTablaDetalleFactura() {

        listaDetalleFacturaSuplidor.clear();

        tbDetalleFactura.setItems(listaDetalleFacturaSuplidor);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));

        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcionArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcDescuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        tbcItbis.setCellValueFactory(new PropertyValueFactory<>("itbis"));
        tbcIsr.setCellValueFactory(new PropertyValueFactory<>("isr"));
        tbcSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcTotalAPagar.setCellValueFactory(new PropertyValueFactory<>("totalAPagar"));
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        tbcItbisRetenido.setCellValueFactory(new PropertyValueFactory<>("itbisRetenido"));
        tbcPorCientoPropina.setCellValueFactory(new PropertyValueFactory<>("porcientoPropina"));
        tbcMontoExcento.setCellValueFactory(new PropertyValueFactory<>("montoExcento"));

        tbDetalleFactura.setEditable(true);

        tbcCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });

        tbcDescripcionArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getDescripcion());
                    }
                    return property;
                });

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getUnidadEntrada().getDescripcion());
                    }
                    return property;
                });

        tbcPrecio.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcMontoExcento.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object == null ? "0" : object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcPorCientoPropina.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object == null ? "0" : object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcItbisRetenido.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcItbis.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcCantidad.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcDescuento.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcIsr.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcPorCientoPropina.setOnEditCommit(data -> {

            try {

                DetalleFacturaSuplidor p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = p.getCantidad(),
                        descuento = p.getDescuento(), valorItbis = 0.00,
                        impuesto = 0.00, precio = p.getPrecioUnitario(),
                        montoExcento = 0.00, totalaPagar = 0.00, montoPropina = 0.00;

                setArticulo(p.getArticulo());
                if (p.getArticulo().getExentoItbis() == false
                        && !(ImpuestoDao.getInstancia().getImpuestoItbis(1) == null)) {

                    impuesto = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis().doubleValue();

                }

                if (data.getNewValue() >= 0) {

                    p.setPorcientoPropina(data.getNewValue());
                    subTotal = precio * cantidad;

                    montoPropina = (subTotal - descuento) * (p.getPorcientoPropina() / 100);
                    p.setMontoPropina(FormatNum.FormatearDouble(montoPropina, 2));

                    valorItbis = FormatNum.FormatearDouble((subTotal - descuento) * (impuesto / 100), 2);

                    p.setItbis(valorItbis - p.getItbisExcento());
                    montoExcento = (p.getItbisExcento() / valorItbis) * (subTotal - descuento);
                    p.setMontoExcento(FormatNum.FormatearDouble(montoExcento, 2));
                    p.setMontoGravado(FormatNum.FormatearDouble((subTotal - descuento) - montoExcento, 2));

                    valorItbis = FormatNum.FormatearDouble(p.getMontoGravado() * (impuesto / 100), 2);

                    p.setItbis(valorItbis);
                    p.setSubTotal(FormatNum.FormatearDouble(subTotal, 2));

                    total = (subTotal - p.getDescuento()) + valorItbis;
                    p.setTotal(FormatNum.FormatearDouble(total, 2));
                    totalaPagar = p.getTotal() - (p.getItbisRetenido() + p.getIsr());
                    p.setTotalAPagar(FormatNum.FormatearDouble(totalaPagar, 2));

                    calcularRetencion(p);

                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();

                    totales();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcItbisRetenido.setOnEditCommit(data -> {

            try {

                DetalleFacturaSuplidor p = data.getRowValue();
                Double subTotal = p.getSubTotal(), total = 0.00, cantidad = p.getCantidad(),
                        descuento = 0.00, impuesto = 0.00, precio = p.getPrecioUnitario(),
                        valorImpuesto = 0.00, valorRetenidoItbis = 0.00,
                        valorRetenidoIsr = 0.00, totalaPagar = 0.00;

                setArticulo(p.getArticulo());
                if (p.getArticulo().getExentoItbis() == false
                        && !(ImpuestoDao.getInstancia().getImpuestoItbis(1) == null)) {

                    impuesto = ImpuestoDao.getInstancia().getImpuestoItbis(1).getValor().doubleValue();
                }

                System.out.println("Impuesto " + impuesto);

                if (data.getNewValue() >= 0) {

                    p.setItbisRetenido(data.getNewValue());

                    p.setSubTotal(FormatNum.FormatearDouble(subTotal, 2));
                    total = (subTotal - p.getDescuento()) + valorImpuesto;
                    p.setTotal(FormatNum.FormatearDouble(total, 2));
                    totalaPagar = p.getTotal() - (p.getItbisRetenido() + p.getIsr());
                    p.setTotalAPagar(FormatNum.FormatearDouble(totalaPagar, 2));

                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();

                    totales();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcPrecio.setOnEditCommit(data -> {

            try {

                DetalleFacturaSuplidor p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = p.getCantidad(),
                        descuento = 0.00, impuesto = 0.00, precio = p.getPrecioUnitario(),
                        valorImpuesto = 0.00, valorRetenidoItbis = 0.00,
                        valorRetenidoIsr = 0.00, montoExcento = 0.00, totalaPagar = 0.00;

                setArticulo(p.getArticulo());
                if (p.getArticulo().getExentoItbis() == false
                        && !(ImpuestoDao.getInstancia().getImpuestoItbis(1) == null)) {

                    impuesto = ImpuestoDao.getInstancia().getImpuestoItbis(1).getValor().doubleValue();
                }

                System.out.println("Impuesto " + impuesto);

                if (data.getNewValue() >= 0) {

                    p.setPrecioUnitario(data.getNewValue());

                    subTotal = p.getPrecioUnitario() * cantidad;

                    montoPropina = (subTotal - descuento) * (p.getPorcientoPropina() / 100);
                    p.setMontoPropina(FormatNum.FormatearDouble(montoPropina, 2));
                    valorImpuesto = FormatNum.FormatearDouble((subTotal - p.getDescuento()) * (impuesto / 100), 2);

                    p.setItbis(valorImpuesto);

                    montoExcento = (p.getItbisExcento() / valorImpuesto) * (subTotal - descuento);
                    p.setMontoExcento(FormatNum.FormatearDouble(montoExcento, 2));
                    p.setMontoGravado(FormatNum.FormatearDouble((subTotal - descuento) - montoExcento, 2));

                    valorImpuesto = FormatNum.FormatearDouble(p.getMontoGravado() * (impuesto / 100), 2);
                    total = (subTotal - p.getDescuento()) + valorImpuesto;

                    p.setSubTotal(FormatNum.FormatearDouble(subTotal, 2));
                    p.setTotal(FormatNum.FormatearDouble(total, 2));
                    totalaPagar = p.getTotal() - (p.getItbisRetenido() + p.getIsr());
                    p.setTotalAPagar(FormatNum.FormatearDouble(totalaPagar, 2));
                    p.setSubTotalConDescuento(FormatNum.FormatearDouble(subTotal - descuento, 2));

                    calcularRetencion(p);

                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();

                    totales();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcCantidad.setOnEditCommit(data -> {

            try {

                DetalleFacturaSuplidor p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00,
                        descuento = p.getDescuento(), impuesto = 0.00,
                        precio = p.getPrecioUnitario(), valorImpuesto = 0.00,
                        valorRetenidoItbis = 0.00, valorRetenidoIsr = 0.00, montoExcento = 0.00, totalaPagar = 0.00;

                setArticulo(p.getArticulo());
                if (data.getNewValue() >= 0) {

                    if (p.getArticulo().getExentoItbis() == false
                            && !(ImpuestoDao.getInstancia().getImpuestoItbis(1) == null)) {

                        impuesto = ImpuestoDao.getInstancia().getImpuestoItbis(1).getValor().doubleValue();
                    }

                    p.setCantidad(data.getNewValue());
                    cantidad = p.getCantidad();
                    subTotal = precio * cantidad;

                    montoPropina = (subTotal - descuento) * (p.getPorcientoPropina() / 100);
                    p.setMontoPropina(FormatNum.FormatearDouble(montoPropina, 2));
                    valorImpuesto = FormatNum.FormatearDouble((subTotal - descuento) * (impuesto / 100), 2);

                    montoExcento = (p.getItbisExcento() / valorImpuesto) * (subTotal - descuento);
                    p.setMontoExcento(FormatNum.FormatearDouble(montoExcento, 2));
                    p.setMontoGravado(FormatNum.FormatearDouble((subTotal - descuento) - montoExcento, 2));

                    valorImpuesto = FormatNum.FormatearDouble(p.getMontoGravado() * (impuesto / 100), 2);

                    total = FormatNum.FormatearDouble((subTotal - descuento) + valorImpuesto, 2);

                    p.setSubTotal(FormatNum.FormatearDouble(subTotal, 2));
                    p.setTotal(total);
                    totalaPagar = p.getTotal() - (p.getItbisRetenido() + p.getIsr());
                    p.setTotalAPagar(FormatNum.FormatearDouble(totalaPagar, 2));
                    p.setSubTotalConDescuento(FormatNum.FormatearDouble(subTotal - descuento, 2));
                    p.setItbis(valorImpuesto);

                    calcularRetencion(p);

                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();

                    totales();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcDescuento.setOnEditCommit(data -> {

            try {

                DetalleFacturaSuplidor p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00,
                        descuento = 0.00, impuesto = 0.00, precio = p.getPrecioUnitario(),
                        valorImpuesto = 0.00, montoExcento = 0.00, totalaPagar = 0.00;

                setArticulo(p.getArticulo());

                if (data.getNewValue() >= 0) {

                    if (p.getArticulo().getExentoItbis() == false
                            && !(ImpuestoDao.getInstancia().getImpuestoItbis(1) == null)) {

                        impuesto = ImpuestoDao.getInstancia().getImpuestoItbis(1).getValor().doubleValue();
                    }

                    p.setDescuento(data.getNewValue());
                    descuento = p.getDescuento();
                    cantidad = p.getCantidad();
                    subTotal = cantidad * precio;

                    valorImpuesto = FormatNum.FormatearDouble((subTotal - descuento) * (impuesto / 100), 2);

                    montoExcento = (p.getItbisExcento() / valorImpuesto) * (subTotal - descuento);
                    p.setMontoExcento(FormatNum.FormatearDouble(montoExcento, 2));
                    p.setMontoGravado(FormatNum.FormatearDouble((subTotal - descuento) - montoExcento, 2));
                    valorImpuesto = FormatNum.FormatearDouble(p.getMontoGravado() * (impuesto / 100), 2);

                    total = FormatNum.FormatearDouble((subTotal - descuento) + valorImpuesto, 2);

                    p.setSubTotal(FormatNum.FormatearDouble(subTotal, 2));
                    p.setTotal(total);
                    totalaPagar = p.getTotal() - (p.getItbisRetenido() + p.getIsr());
                    p.setTotalAPagar(FormatNum.FormatearDouble(totalaPagar, 2));
                    p.setSubTotalConDescuento(FormatNum.FormatearDouble(subTotal - descuento, 2));
                    p.setItbis(valorImpuesto);

                    calcularRetencion(p);

                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();

                    totales();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcItbis.setOnEditCommit(data -> {

            try {

                DetalleFacturaSuplidor p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00,
                        descuento = 0.00, impuesto = 0.00,
                        precio = p.getPrecioUnitario(), totalaPagar = 0.00;

                setArticulo(p.getArticulo());
                if (data.getNewValue() >= 0) {

                    p.setItbis(data.getNewValue());
                    cantidad = p.getCantidad();
                    subTotal = precio * cantidad;
                    total = (subTotal - p.getDescuento()) + p.getItbis();

                    p.setSubTotal(FormatNum.FormatearDouble(subTotal, 2));
                    p.setTotal(FormatNum.FormatearDouble(total, 2));
                    totalaPagar = p.getTotal() - (p.getItbisRetenido() + p.getIsr());
                    p.setTotalAPagar(FormatNum.FormatearDouble(totalaPagar, 2));
                    p.setSubTotalConDescuento(FormatNum.FormatearDouble(subTotal - descuento, 2));

                    calcularRetencion(p);

                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();

                    totales();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcIsr.setOnEditCommit(data -> {

            try {

                DetalleFacturaSuplidor p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00,
                        descuento = 0.00, impuesto = 0.00,
                        precio = p.getPrecioUnitario(), totalaPagar = 0.00;

                setArticulo(p.getArticulo());
                if (data.getNewValue() >= 0) {

                    p.setIsr(data.getNewValue());
                    cantidad = p.getCantidad();
                    subTotal = cantidad * precio;

                    p.setSubTotal(FormatNum.FormatearDouble(subTotal, 2));
                    total = (subTotal - p.getDescuento()) + p.getItbis();
                    p.setTotal(FormatNum.FormatearDouble(total, 2));
                    totalaPagar = p.getTotal() - (p.getItbisRetenido() + p.getIsr());
                    p.setTotalAPagar(FormatNum.FormatearDouble(totalaPagar, 2));

                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();

                    totales();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcMontoExcento.setOnEditCommit(data -> {

            try {

                DetalleFacturaSuplidor p = data.getRowValue();
                Double subTotal = p.getSubTotal(), total = 0.00, cantidad = 0.00, precioCompraUnitario = 0.00,
                        descuento = 0.00, impuesto = 0.00,
                        valorImpuesto = 0.00, valorItbisExcento = 0.00, totalaPagar = 0.00;

                if (data.getNewValue() >= 0) {

                    if (p.getArticulo().getExentoItbis() == false
                            && !(ImpuestoDao.getInstancia().getImpuestoItbis(1) == null)) {

                        impuesto = ImpuestoDao.getInstancia().getImpuestoItbis(1).getValor().doubleValue();
                    }

                    p.setMontoExcento(data.getNewValue());
                    p.setMontoGravado(FormatNum.FormatearDouble(p.getSubTotal() - p.getMontoExcento(), 2));
                    valorImpuesto = FormatNum.FormatearDouble((p.getMontoGravado()) * (impuesto / 100), 2);
                    p.setItbis(valorImpuesto);
                    valorItbisExcento = FormatNum.FormatearDouble((p.getSubTotal()) * (impuesto / 100), 2);
                    valorItbisExcento = valorItbisExcento - valorImpuesto;
                    p.setItbisExcento(FormatNum.FormatearDouble(valorItbisExcento, 2));
                    total = (subTotal - p.getDescuento()) + (p.getItbis());
                    p.setTotal(FormatNum.FormatearDouble(total, 2));
                    totalaPagar = p.getTotal() - (p.getItbisRetenido() + p.getIsr());
                    p.setTotalAPagar(FormatNum.FormatearDouble(totalaPagar, 2));

                    calcularRetencion(p);

                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();

                    totales();

                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser  menor que cero");
                    tbDetalleFactura.refresh();
                    tbDetalleFactura.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    @FXML
    private void btnBuscarArticuloActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/inventario/articulo/FXMLBuscarArticulo.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        FXMLBuscarArticuloController articuloController = loader.getController();
        articuloController.setOrigen(1);//el valor  3 es para  buscar solo articulos de consumo

        articuloController.llenarCampo();

        utiles.ClaseUtil.getStageModal(root);

        System.out.println("ESPERANDOOO!!!");

//        FXMLBuscarArticuloController articuloController = loader.getController();
        articuloController.setOrigen(1);//el valor  3 es para  buscar solo articulos de consumo
        articuloController.llenarCampo();

        if (!(articuloController.getArticulo() == null)) {

            listaUnidads.clear();
//                String unidadES = getArticulo().getUnidadEntrada().getCodigo() + "," + getArticulo().getUnidadSalida().getCodigo();

//                listaUnidads.addAll(ManejoUnidad.getInstancia().getLista(unidadES));
            listaUnidads.addAll(ManejoArticuloUnidad.getInstancia().getListaUnidadSslida(articuloController.getArticulo().getCodigo()));
            cbUnidad.setItems(listaUnidads);

            System.out.println("Estado articulo " + articuloController.getArticulo().getActivo());
            if (articuloController.getArticulo().getActivo() == false) {

                ClaseUtil.mensaje("Este Articulo esta inactivo,no se le puede hacer orden de compra");
                return;
            }

            setArticulo(articuloController.getArticulo());
            txtArticulo.setText(getArticulo().getDescripcion());
            txtCantidad.requestFocus();
            if (listaUnidads.size() == 1) {
                cbUnidad.getSelectionModel().select(0);
                txtCantidad.requestFocus();
            }

        } else {

            txtArticulo.clear();
            cbUnidad.getSelectionModel().clearSelection();
            btnBuscarArticulo.setDisable(true);

        }
    }

    @FXML
    private void btBuscarSupplidorActionEvent(ActionEvent event) throws IOException {

        if (dpFecha.getValue() == null) {

            ClaseUtil.mensaje(" Tiene quwe seleccionar la fecha ");

            return;
        }

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/suplidor/FXMLBusSuplidor.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        System.out.println("ESPERANDOOO!!!");

        FXMLBusSuplidorController suplidorController = loader.getController();

        if (!(suplidorController.getSuplidor() == null)) {

            btnBuscarOrdenCompra.setDisable(true);
            setSuplidor(suplidorController.getSuplidor());
            txtSuplidor.setText(getSuplidor().getDescripcion() + " (" + getSuplidor().getTipoSuplidor().getNombre() + ")");
            dpFechaVencimiento.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaVencimiento()));
            btnBuscarArticulo.setDisable(false);

            getFacturaSuplidor().setSuplidor(getSuplidor());

            for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {
                calcularRetencion(det);
            }

            tbDetalleFactura.refresh();
            tbDetalleFactura.requestFocus();
            totales();

        } else {

            btnBuscarOrdenCompra.setDisable(false);
            btnBuscarArticulo.setDisable(true);
            limpiar();
        }

    }

    private void agregarArticulo() {

        try {

            detFactSuplidor = new DetalleFacturaSuplidor();

            Double subTotal = 0.00, total = 0.00, cantidad = Double.parseDouble(txtCantidad.getText()),
                    precio = FormatNum.FormatearDouble(getArticulo().getPrecioCompra(), 4), descuento = 0.00,
                    valorItbis = 0.00, impuesto = 0.00, subTotalConDescuento = 0.00, totalApagar = 0.00,
                    valorRetenidoItbis = 0.00,
                    valorRetenidoIsr = 0.00;

            System.out.println("Excento de itbis " + getArticulo().getExentoItbis() + "getArticulo().getExentoItbis()  " + getArticulo().getExentoItbis());

            if (getArticulo().getExentoItbis() == false
                    && !(ImpuestoDao.getInstancia().getImpuestoItbis(1) == null)) {

                impuesto = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis().doubleValue();

            }

            System.out.println("Impuesto " + impuesto);

            subTotal = FormatNum.FormatearDouble(precio * cantidad, 2);

            valorItbis = FormatNum.FormatearDouble((subTotal - descuento) * (impuesto / 100), 2);

            subTotalConDescuento = FormatNum.FormatearDouble(subTotal - descuento, 2);

            total = FormatNum.FormatearDouble((subTotal - descuento) + valorItbis, 2);

            totalApagar = FormatNum.FormatearDouble((total), 2);

            System.out.println("Total " + total);

            detFactSuplidor.setPorcientoPropina(0.00);
            detFactSuplidor.setArticulo(getArticulo());
            detFactSuplidor.setDescripcionArticulo(getArticulo().getDescripcion());
            detFactSuplidor.setCantidad(cantidad);
            detFactSuplidor.setPrecioUnitario(precio);
            detFactSuplidor.setSubTotal(subTotal);
            detFactSuplidor.setTotal(total);
            detFactSuplidor.setIsr(0.00);
            detFactSuplidor.setItbis(valorItbis);
            detFactSuplidor.setTotalAPagar(totalApagar);
            detFactSuplidor.setPendiente(totalApagar);
            detFactSuplidor.setSubTotalConDescuento(subTotalConDescuento);
            detFactSuplidor.setUnidad(cbUnidad.getSelectionModel().getSelectedItem().getUnidad());

            detFactSuplidor.setDescuento(descuento);
            detFactSuplidor.setItbisRetenido(0.00);
            detFactSuplidor.setItbisExcento(0.00);
            detFactSuplidor.setMontoGravado(subTotal);
            detFactSuplidor.setMontoExcento(subTotal - detFactSuplidor.getMontoGravado());

            detFactSuplidor = calcularRetencion(detFactSuplidor);

            detFactSuplidor.setFacturaSuplidor(getFacturaSuplidor());

            if (existe(detFactSuplidor)) {

                ClaseUtil.mensaje("Este Articulo ya Existe en el Detalle ");
                return;
            }

            listaDetalleFacturaSuplidor.add(detFactSuplidor);
            txtValorAContabilizar.setText(getTotal().toString());

            txtArticulo.clear();
            txtCantidad.clear();
            totales();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private String validar() {

        String msgError = "";
        int ung = getFacturaSuplidor().getUnidadDeNegocio().getCodigo();
        int codsuplidor = getFacturaSuplidor().getSuplidor().getCodigo();

        boolean existe = ManejoFacturaSuplidor.getInstancia().validarNcf(txtNcf.getText().trim(), ung, codsuplidor);

        System.out.println("existe : " + existe + " codsuplidor : " + codsuplidor + "  editar : " + editar + " ung ; " + ung);
        if (existe && editar == false) {
            msgError = "Este numero de NCF ya existe ";
            return msgError;
        }

        if (tbDetalleFactura.getItems().size() <= 0) {

            msgError = "El detalle de la orden esta vacio";

            return msgError;
        }
        if (cbFormaDePago.getSelectionModel().getSelectedIndex() == -1) {

            msgError = " Tiene que seleccionar la forma de pago ";

            return msgError;
        }

        if (cbTipoDeRetencion.getSelectionModel().getSelectedIndex() == -1 && getTotalIsr() > 0) {

            msgError = " Tiene que seleccionar el tipo de Retencion ";

            return msgError;
        }

        if (cbCostoGastos.getSelectionModel().getSelectedIndex() == -1) {

            msgError = " Tiene que seleccionar el costo y gastos al que corrresponde esta compra ";

            return msgError;
        }

        if (dpFecha.getValue() == null) {

            msgError = "La fecha esta vacia";
            dpFecha.requestFocus();
            return msgError;
        }

        if (txtSuplidor.getText().isEmpty()) {
            msgError = "El nombre del supliidor esta vacio";
            txtSuplidor.requestFocus();
            return msgError;
        }

        if (txtConcepto.getText().isEmpty()) {
            msgError = "El Concepto esta vacio";
            txtConcepto.requestFocus();
            return msgError;
        }

        return msgError;
    }

    private Double getTotal() {

        Double total = 0.00;

        for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {

            total += det.getTotal();
        }

        return FormatNum.FormatearDouble(total + montoPropina + impuestoSelectivoAlConsumo + otrosImpuesto, 2);
    }

    private Double getTotalApagar() {

        Double total = 0.00;

        for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {

            total += det.getTotalAPagar();
        }

        return FormatNum.FormatearDouble(total + montoPropina + impuestoSelectivoAlConsumo + otrosImpuesto, 2);
    }

    private Double getTotalItbisRetenido() {

        Double total = 0.00;

        for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {

            total += det.getItbisRetenido();
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getSubTotalConDescuento() {

        Double total = 0.00;

        for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {

            total += det.getSubTotalConDescuento();
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getSubTotal() {

        Double subTotal = 0.00;

        for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {

            subTotal += det.getSubTotal();
        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotalItbis() {

        Double totalItbis = 0.00;

        for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {

            totalItbis += det.getItbis();
        }

        return FormatNum.FormatearDouble(totalItbis, 2);
    }

    private Double getTotalItbisExcento() {

        Double totalItbis = 0.00;

        for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {

            totalItbis += det.getItbisExcento() == null ? 0 : det.getItbisExcento();
        }

        return FormatNum.FormatearDouble(totalItbis, 2);
    }

    private Double getMontoPropina() {

        Double totalItbis = 0.00;

        for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {

            totalItbis += det.getMontoPropina() == null ? 0 : det.getMontoPropina();
        }

        return FormatNum.FormatearDouble(totalItbis, 2);
    }

    private Double getTotalIsr() {

        Double totalIsr = 0.00;

        for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {

            totalIsr += det.getIsr();
        }

        return FormatNum.FormatearDouble(totalIsr, 2);
    }

    private Double getTotalDescuento() {

        Double totalDescuento = 0.00;

        for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {

            totalDescuento += det.getDescuento();
        }

        return FormatNum.FormatearDouble(totalDescuento, 2);
    }

    private void guardar() {

        try {

            for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {

                if (det.getPrecioUnitario() <= 0) {

                    ClaseUtil.mensaje("Este Articulo  " + det.getArticulo().getDescripcion() + " no tiene precio ");
                    return;
                }
            }

            //Actualizando precio de compra
            for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {

                det.getArticulo().setPrecioCompra(det.getPrecioUnitario());
                ManejoArticulo.getInstancia().actualizar(det.getArticulo());

            }

            getFacturaSuplidor().setUnidadDeNegocio(utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio());

            if (editar == false) {
                asignarSecuenciaDoc();
            }

            getFacturaSuplidor().setNoFactura(OrdenDeCompraDao.getInstancia().getNumero().toString());

            if (cbTipoNcf.getSelectionModel().getSelectedIndex() == -1) {
                getFacturaSuplidor().setNcf(txtNcf.getText().trim().toUpperCase());
            } else {
                getFacturaSuplidor().setNcf(relacionNcf.getActual().toUpperCase());
            }

            getFacturaSuplidor().setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            getFacturaSuplidor().setAnulada(false);
            getFacturaSuplidor().setConcepto(txtConcepto.getText().toUpperCase());
            getFacturaSuplidor().setNombreSuplidor(txtSuplidor.getText());
            getFacturaSuplidor().setSuplidor(getSuplidor());
            if (getFacturaSuplidor().getSuplidorCxp() == null) {
                getFacturaSuplidor().setSuplidorCxp(getSuplidor().getCodigo());
            }

            getFacturaSuplidor().setUsuario(VariablesGlobales.USUARIO);
            getFacturaSuplidor().setFechaVencimiento(ClaseUtil.asDate(dpFechaVencimiento.getValue()));

            getFacturaSuplidor().setSubTotal(getSubTotal());
            getFacturaSuplidor().setSubTotalConDescuento(getSubTotalConDescuento());
            getFacturaSuplidor().setTotal(getTotal());
            getFacturaSuplidor().setTotalAPagar(getTotalApagar());
            getFacturaSuplidor().setTotalDescuento(getTotalDescuento());
            getFacturaSuplidor().setItbis(getTotalItbis());
            getFacturaSuplidor().setMontoItbisExcento(getTotalItbisExcento());
            getFacturaSuplidor().setIsr(getTotalIsr());
            getFacturaSuplidor().setTotalItbisRetenido(getTotalItbisRetenido());
            getFacturaSuplidor().setTotalPagado(0.00);
            getFacturaSuplidor().setTotalPendiente(getTotal());
            getFacturaSuplidor().setPendiente(getTotal());
            getFacturaSuplidor().setAbono(0);
            getFacturaSuplidor().setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            getFacturaSuplidor().setFormaDePago(cbFormaDePago.getSelectionModel().getSelectedItem());

            if (!(getFacturaSuplidor().getFormaDePago().getCodigo() == 4)) {

                getFacturaSuplidor().setFechaPago(getFacturaSuplidor().getFecha());
            }

            getFacturaSuplidor().setNombreFormaDePago(cbFormaDePago.getSelectionModel().getSelectedItem().getNombre());

            getFacturaSuplidor().setProyecto(getProyecto());

            if (!(cbTipoDeRetencion.getSelectionModel().getSelectedIndex() == -1)) {
                getFacturaSuplidor().setTipoIsr(cbTipoDeRetencion.getSelectionModel().getSelectedItem());
            }

            getFacturaSuplidor().setCostosYGastos(cbCostoGastos.getSelectionModel().getSelectedItem());

            getFacturaSuplidor().setItbisLlevadoAlCosto(Double.parseDouble(txtTotalItbisAlCosto.getText().isEmpty() ? "0"
                    : txtTotalItbisAlCosto.getText())
            );

            getFacturaSuplidor().setPropinaLegal(Double.parseDouble(txtPropinaLegal.getText().isEmpty() ? "0"
                    : txtPropinaLegal.getText())
            );

            getFacturaSuplidor().setOtrosImpuestoYOTasa(Double.parseDouble(txtOtrosImpuesto.getText().isEmpty() ? "0"
                    : txtOtrosImpuesto.getText())
            );

            getFacturaSuplidor().setImpuestoSelectivoAlConsumo(Double.parseDouble(txtImpuestoSelectivoalConsumo.getText().isEmpty() ? "0"
                    : txtImpuestoSelectivoalConsumo.getText())
            );

            getFacturaSuplidor().setItbisPorAdelantar(Double.parseDouble(txtTotalItbisPorAdelantar.getText().isEmpty() ? "0"
                    : txtTotalItbisPorAdelantar.getText())
            );

            getFacturaSuplidor().setItbisSujetoAProporcionalidad(Double.parseDouble(txtTotalItbisProporcional.getText().isEmpty() ? "0"
                    : txtTotalItbisProporcional.getText())
            );

            getFacturaSuplidor().setDetalleFacturaSuplidorCollection(listaDetalleFacturaSuplidor);

            if (isEditar()) {
                getFacturaSuplidor().setFechaActualizacion(new Date());
                setFacturaSuplidor(ManejoFacturaSuplidor.getInstancia().actualizar(getFacturaSuplidor()));
            } else {
                getFacturaSuplidor().setFechaRegistro(new Date());
                setFacturaSuplidor(ManejoFacturaSuplidor.getInstancia().salvar(getFacturaSuplidor()));
            }

            if (!(ordenCompra == null) && !(getFacturaSuplidor() == null)) {

                ordenCompra.setFacturada(true);
                ordenCompra.setFechaFacturada(new Date());
                OrdenDeCompraDao.getInstancia().actualizar(ordenCompra);
            }

            if (!(getFacturaSuplidor() == null)) {

                Date fechaContable = getFacturaSuplidor().getFecha();
                if (listaDetalleEnt.size() > 0) {

                    System.out.println("editarEntrada : " + editarEntrada);
                    if (editarEntrada) {

                        EntradaDiarioDao.getInstancia()
                                .editarEntradaDiario(getFacturaSuplidor().getCodigo(), getFacturaSuplidor().getNumero().toString(),
                                        12, txtComentario.getText(), 10, listaDetalleEnt, fechaContable, entradaDiarioDb);
                    } else {
                        EntradaDiarioDao.getInstancia().registrarEntradaDiario(getFacturaSuplidor().getCodigo(), getFacturaSuplidor().getNumero().toString(),
                                12, txtComentario.getText(), 10, listaDetalleEnt, fechaContable);
                    }

                }
            }

        } catch (Exception e) {

            ClaseUtil.mensaje("Hubo un Error guardando la Orden ");

            e.printStackTrace();
        }

    }

    private boolean existe(DetalleFacturaSuplidor det) {

        for (DetalleFacturaSuplidor detalle : tbDetalleFactura.getItems()) {

            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())) {

                return true;

            }
        }

        return false;
    }

    private void limpiar() {

        txtArticulo.clear();
        txtCantidad.clear();
        txtConcepto.clear();
        txtNcf.clear();
        txtISR.clear();
        dpFechaVencimiento.setValue(null);
        dpFecha.setValue(null);
        txtSubTotal.clear();
        txtTotal.clear();
        txtTotal1.clear();
        txtItbis.clear();
        txtNumeroOc.clear();
        txtSuplidor.clear();
        txtSuplidorCxp.clear();
        txtDescuento.clear();
        listaDetalleFacturaSuplidor.clear();
        btnBuscarArticulo.setDisable(true);
        cbUnidad.getSelectionModel().clearSelection();
        cbFormaDePago.getSelectionModel().clearSelection();
        cbTipoDeRetencion.getSelectionModel().clearSelection();
        cbCostoGastos.getSelectionModel().clearSelection();
        listaDetalleEnt.clear();
        txtComentario.clear();
        txtTotalDebito.clear();
        txtTotalCredito.clear();
        txtTotalItbisProporcional.clear();
        txtTotalItbisRetendo1.clear();
        txtTotalItbisAlCosto.clear();
        txtTotalItbisPorAdelantar.clear();
        txtItbis1.clear();
        lbCantidadDigito.setText("");
        txtNombreProyecto.clear();
        txtImpuestoSelectivoalConsumo.clear();
        txtOtrosImpuesto.clear();
        txtPropinaLegal.clear();
        txtMontoPropina.clear();
        txtMontoGravado.clear();
        txtMontoExcento.clear();
        txtTotalItbisRetendo.clear();
        setProyecto(null);
        editar = false;
        editarEntrada = false;
        btnGuardar.setText("Guardar");
        lbNumEntrada.setText("");
        txtValorAContabilizar.clear();
        txtDescripcion.clear();
        cbTipoNcf.getSelectionModel().clearSelection();

        inicializarSecuencia();

    }

    private void inicializarSecuencia() {

//        txtNumeroFactura.setText(OrdenDeCompraDao.getInstancia().getNumero().toString());
    }

    void cbPlazoActionEvent(ActionEvent event) {

    }

    @FXML
    private void btnBuscarOrdenCompraActionEvent(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/compra/consulta/FXMLBuscarOrdenCompra.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            Scene scene = new Scene(root);

            Stage stage = new Stage();

            stage.setScene(scene);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();

            System.out.println("ESPERANDOOO!!!");

            FXMLBusOrdenCompraController fXMLBusOrdenCompraFacturaController = loader.getController();

            if (!(fXMLBusOrdenCompraFacturaController.getOrdenCompra() == null)) {

                btBuscarSupplidor.setDisable(true);
                btnBuscarArticulo.setDisable(true);
                ordenCompra = fXMLBusOrdenCompraFacturaController.getOrdenCompra();
                txtNumeroOc.setText(ordenCompra.getCodigo().toString());
                txtSuplidor.setText(ordenCompra.getNombreSuplidor());

                getFacturaSuplidor().setSuplidor(SuplidorDao.getInstancia().getSuplidor(ordenCompra.getSuplidor()));
                getFacturaSuplidor().setOrdenCompra(ordenCompra.getCodigo());
                setSuplidor(getFacturaSuplidor().getSuplidor());

                listaDetalleFacturaSuplidor.clear();

                for (DetalleOrdenCompra doc : OrdenDeCompraDao.getInstancia().getDetalleOrden(ordenCompra.getCodigo())) {

                    detFactSuplidor = new DetalleFacturaSuplidor();

                    detFactSuplidor.setArticulo(doc.getArticulo());
                    detFactSuplidor.setCantidad(doc.getCantidad());
                    detFactSuplidor.setDescripcionArticulo(doc.getDescripcionArticulo());
                    detFactSuplidor.setDescuento(doc.getDescuento());
                    detFactSuplidor.setSubTotal(doc.getSubTotal());
                    detFactSuplidor.setSubTotalConDescuento(doc.getSubTotalConDescuento());
                    detFactSuplidor.setFacturaSuplidor(getFacturaSuplidor());
                    detFactSuplidor.setIsr(doc.getIsr());
                    detFactSuplidor.setItbis(doc.getItbis());
                    detFactSuplidor.setPendiente(doc.getTotal());
                    detFactSuplidor.setPrecioUnitario(doc.getPrecioUnitario());
                    detFactSuplidor.setUnidad(doc.getUnidad());
                    detFactSuplidor.setTotal(doc.getTotal());
                    System.out.println("doc.getTotalAPagar() " + doc.getTotalAPagar());
                    detFactSuplidor.setTotalAPagar(doc.getTotalAPagar());

                    listaDetalleFacturaSuplidor.add(detFactSuplidor);
                }

                txtSubTotal.setText(getSubTotal().toString());
                txtDescuento.setText(getTotalDescuento().toString());
                txtItbis.setText(getTotalItbis().toString());
                txtISR.setText(getTotalIsr().toString());
                txtTotal.setText(getTotal().toString());
                txtTotal1.setText(getTotalApagar().toString());
                txtSubTotal.setText(getTotalApagar().toString());
                txtDescuento.setText(getTotalDescuento().toString());

            } else {

                btBuscarSupplidor.setDisable(false);
                btnBuscarArticulo.setDisable(false);
                limpiar();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Date fechaVencimiento() {

        Date fechaVencimiento = new Date(), fecha;
        fecha = ClaseUtil.asDate(dpFecha.getValue());
        if (!(getSuplidor() == null) && !(fecha == null)) {
            if (!(getSuplidor().getPlazo() == null)) {
                fechaVencimiento = ClaseUtil.Fechadiadespues(fecha, getSuplidor().getPlazo().getDias());
            }

        }

        return fechaVencimiento;
    }

    @FXML
    private void cbUnidadActionEvent(ActionEvent event) {

        if (!(cbUnidad.getSelectionModel().getSelectedIndex() == -1)) {
            txtCantidad.requestFocus();
        }
    }

    private void asignarSecuenciaDoc() {

        SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(getFacturaSuplidor().getUnidadDeNegocio().getCodigo(), 12);

        if (!(secDoc == null)) {

            boolean existe = ManejoFacturaSuplidor.getInstancia().existeSecuencia(secDoc.getNumero());

            if (existe) {

                System.out.println("existe " + secDoc.getNumero());

                while (ManejoFacturaSuplidor.getInstancia().existeSecuencia(secDoc.getNumero())) {

                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

                secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(getFacturaSuplidor().getUnidadDeNegocio().getCodigo(), 12);

                getFacturaSuplidor().setNumero(secDoc.getNumero());
                getFacturaSuplidor().setSecuenciaDocumento(secDoc);

            } else {

                System.out.println("No existe " + secDoc.getNumero());
                getFacturaSuplidor().setNumero(secDoc.getNumero());
                getFacturaSuplidor().setSecuenciaDocumento(secDoc);
                secDoc.setNumero(secDoc.getNumero() + 1);
                ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

            }

        }

    }

    @FXML
    private void btnBuscarProyectoActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/proyecto/BuscarProyecto.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        System.out.println("ESPERANDOOO!!!");

        BuscarProyectoController controller = loader.getController();

        if (!(controller.getProyecto() == null)) {

            btnBuscarOrdenCompra.setDisable(true);
            setProyecto(controller.getProyecto());
            txtNombreProyecto.setText(getProyecto().getNombre());

        } else {
            txtNombreProyecto.clear();
        }

    }

    private void totales() {

        txtSubTotal.setText(getSubTotal().toString());
        txtDescuento.setText(getTotalDescuento().toString());
        txtItbis.setText(getTotalItbis().toString());
        txtItbis1.setText(getTotalItbis().toString());
        txtISR.setText(getTotalIsr().toString());
        txtTotal.setText(getTotal().toString());
        txtTotal1.setText(getTotalApagar().toString());
        txtDescuento.setText(getTotalDescuento().toString());
        txtTotalItbisRetendo.setText(getTotalItbisRetenido().toString());
        txtTotalItbisRetendo1.setText(getTotalItbisRetenido().toString());
        txtMontoPropina.setText(getMontoPropina().toString());
        txtMontoExcento.setText(getMontoExcento().toString());
        txtMontoGravado.setText(getMontoGravado().toString());
        txtPropinaLegal.setText(getMontoPropina().toString());

    }

    private DetalleFacturaSuplidor calcularRetencion(DetalleFacturaSuplidor detFact) {

        int tipoServicio = 0, tiposuplidor;
        tiposuplidor = getSuplidor().getTipoSuplidor().getCodigo();
        if (detFact.getArticulo().getTipoArticulo().getCodigo() == 3) {
            tipoServicio = detFact.getArticulo().getTipoServicio();
        }

        Double valorRetenidoItbis = 0.00, valorRetenidoIsr = 0.00;

        if (detFact.getArticulo().getTipoArticulo().getCodigo() == 3) {//3 ES EL ARTICULO DE SERVICIO

            valorRetenidoItbis = RetencionDGII.retencionITBISPorServicio(detFact.getItbis(), tipoServicio, tiposuplidor);

            if (tiposuplidor == 2) {//2 ES SUPLIDOR INFORMAL

                valorRetenidoIsr = RetencionDGII.retencionISRPorServicio(detFact.getMontoGravado(), tipoServicio);
            }

        } else if (!(detFact.getArticulo().getTipoArticulo().getCodigo() == 3)) {

            valorRetenidoItbis = RetencionDGII.retencionITBISPorBienes(detFact.getItbis(), tiposuplidor);
        }

        detFact.setIsr(valorRetenidoIsr);
        detFact.setTotalAPagar(detFact.getTotal() - (valorRetenidoIsr + valorRetenidoItbis));
        detFact.setItbisRetenido(valorRetenidoItbis);


        /*
          if (detFactSuplidor.getArticulo().getTipoArticulo().getCodigo() == 3) {

                System.out.println("es un servicio " + valorItbis);
                int tiposuplidor = getSuplidor().getTipoSuplidor().getCodigo();
                int tipoServicio = detFactSuplidor.getArticulo().getCategoria().getCodigo();

                System.out.println("tipoServicio : " + tipoServicio);
                valorRetenidoItbis = RetencionDGII.retencionITBISPorServicio(valorItbis, tipoServicio, tiposuplidor);

                if (tiposuplidor == 2) {
                    valorRetenidoIsr = RetencionDGII.retencionISRPorServicio(subTotal, tipoServicio);
                }
                detFactSuplidor.setItbisRetenido(valorRetenidoItbis);
                detFactSuplidor.setIsr(valorRetenidoIsr);
                totalApagar = total - (valorRetenidoIsr + valorRetenidoItbis);

            } else {
                System.out.println("no es un servicio ");
            }
        
        
         */
        return detFact;
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

    public void changeDebitoCellEnvent(TableColumn.CellEditEvent edittedCell) {

        DetalleEntradaDiario detalleSelected = tblDetalleEnt.getSelectionModel().getSelectedItem();
        detalleSelected.setDebito(Double.valueOf(edittedCell.getNewValue().toString()));
        txtTotalDebito.setText(getTotalDebito().toString());
        Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
        txtDiferencia.setText(diferencia.toString());

    }

    public void changeCreditoCellEnvent(TableColumn.CellEditEvent edittedCell) {

        DetalleEntradaDiario detalleSelected = tblDetalleEnt.getSelectionModel().getSelectedItem();
        detalleSelected.setCredito(Double.valueOf(edittedCell.getNewValue().toString()));
        txtTotalCredito.setText(getTotalCredito().toString());
        Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
        txtDiferencia.setText(diferencia.toString());

    }

    @FXML
    private void tbDetalleEntActionEvent(KeyEvent event) {
    }

    private void agregarCuenta() {

        detalle = new DetalleEntradaDiario();

        detalle.setCatalogo(getCatalogo());
        detalle.setCuenta(getCatalogo().getCuenta());
        detalle.setDescripcionCuenta(getCatalogo().getDescripcion());
        detalle.setCredito(0.00);
        detalle.setDebito(0.00);
        detalle.setCuentaControl(catalogo.getCuentaControl());

        listaDetalleEnt.add(detalle);

        setCatalogo(null);

    }

    public Double getTotalCredito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detEntrada = tblDetalleEnt.getItems();

        for (int i = 0; i < detEntrada.size(); i++) {
            System.out.println("Size: " + detEntrada.size());
            total += detEntrada.get(i).getCredito();
            System.out.println("TDeB: " + total);
        }

        return ClaseUtil.roundDouble(total, 4);

    }

    public Double getTotalDebito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detentrada = tblDetalleEnt.getItems();

        for (int i = 0; i < detentrada.size(); i++) {
            total += detentrada.get(i).getDebito();
        }

        return ClaseUtil.roundDouble(total, 4);

    }

    @FXML
    private void btnAgregarCuentaActionEvent(ActionEvent event) {

        if (getCatalogo() == null) {

            ClaseUtil.mensaje("NO HAY CUENTA SELECCIONADA");
            return;
        }

        agregarCuenta();
    }

    @FXML
    private void cbFormaDePagoActionEvent(ActionEvent event) {
    }

    @FXML
    private void btBuscarSupplidorCxPActionEvent(ActionEvent event) throws IOException {

        if (dpFecha.getValue() == null) {

            ClaseUtil.mensaje(" Tiene quwe seleccionar la fecha ");

            return;
        }

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/suplidor/FXMLBusSuplidor.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        System.out.println("ESPERANDOOO!!!");

        FXMLBusSuplidorController suplidorController = loader.getController();

        if (!(suplidorController.getSuplidor() == null)) {

            btnBuscarOrdenCompra.setDisable(true);
            setSuplidorCxp(suplidorController.getSuplidor());
            txtSuplidorCxp.setText(getSuplidorCxp().getDescripcion() + " (" + getSuplidorCxp().getTipoSuplidor().getNombre() + ")");
            if (!(getSuplidorCxp() == null)) {
                dpFechaVencimiento.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaVencimiento()));
            }

            btnBuscarArticulo.setDisable(false);

            for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {
                calcularRetencion(det);
            }

            tbDetalleFactura.refresh();
            tbDetalleFactura.requestFocus();
            totales();

        } else {

            btnBuscarOrdenCompra.setDisable(false);
            btnBuscarArticulo.setDisable(true);
            limpiar();
        }

    }

    @FXML
    private void cbCostoGastosActionEvent(ActionEvent event) {
    }

    @FXML
    private void cbTipoDeRetencionActionEvent(ActionEvent event) {
    }

    public void llenarCampo() {

        btnBuscarArticulo.setDisable(false);
        btnGuardar.setText("Actualizar");
        setSuplidor(getFacturaSuplidor().getSuplidor());
        setSuplidorCxp(SuplidorDao.getInstancia().getSuplidor(getFacturaSuplidor().getSuplidorCxp()));

        listaDetalleFacturaSuplidor.clear();
        listaDetalleFacturaSuplidor.addAll(ManejoFacturaSuplidor.getInstancia().getDetalleFactura(getFacturaSuplidor().getCodigo()));
        listaDetalleEnt.clear();
        entradaDiarioDb = EntradaDiarioDao.getInstancia().getEntradaDiarioPorDocumento(facturaSuplidor.getCodigo(), 12);

        if (!(entradaDiarioDb == null)) {

            lbNumEntrada.setText(Integer.toString(entradaDiarioDb.getNumero()));
//            txtValorAContabilizar.setText(entradaDiarioDb.getMonto());
            txtComentario.setText(entradaDiarioDb.getConcepto());

            listaDetalleEnt.addAll(EntradaDiarioDao.getInstancia().getDetalleEntradaDiario(entradaDiarioDb));

            txtTotalCredito.setText(getTotalCredito().toString());
            txtTotalDebito.setText(getTotalDebito().toString());
            Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
            txtDiferencia.setText(diferencia.toString());

            editarEntrada = true;

        } else {
            editarEntrada = false;
        }

        dpFecha.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getFacturaSuplidor().getFecha()));
        dpFechaVencimiento.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getFacturaSuplidor().getFechaVencimiento()));
        txtNcf.setText(getFacturaSuplidor().getNcf());
        txtNombreProyecto.setText(getFacturaSuplidor().getProyecto() == null ? " "
                : getFacturaSuplidor().getProyecto().getNombre());

        txtConcepto.setText(getFacturaSuplidor().getConcepto());
        txtSuplidor.setText(getFacturaSuplidor().getSuplidor().getDescripcion());

        txtSuplidorCxp.setText(getSuplidorCxp().getDescripcion());

        cbFormaDePago.getSelectionModel().select(getFacturaSuplidor().getFormaDePago());

        if (!(getFacturaSuplidor().getTipoIsr() == null)) {
            cbTipoDeRetencion.getSelectionModel().select(getFacturaSuplidor().getTipoIsr());
        }

        cbCostoGastos.getSelectionModel().select(getFacturaSuplidor().getCostosYGastos());

        txtTotalItbisAlCosto.setText(getFacturaSuplidor().getItbisLlevadoAlCosto() == null ? "0"
                : getFacturaSuplidor().getItbisLlevadoAlCosto().toString());

        txtTotalItbisPorAdelantar.setText(getFacturaSuplidor().getItbisPorAdelantar() == null ? "0"
                : getFacturaSuplidor().getItbisPorAdelantar().toString());

        txtTotalItbisProporcional.setText(getFacturaSuplidor().getItbisSujetoAProporcionalidad() == null ? "0"
                : getFacturaSuplidor().getItbisSujetoAProporcionalidad().toString());

        txtPropinaLegal.setText(getFacturaSuplidor().getPropinaLegal() == null ? "0"
                : getFacturaSuplidor().getPropinaLegal().toString());

        montoPropina = Double.parseDouble(txtPropinaLegal.getText());

        txtOtrosImpuesto.setText(getFacturaSuplidor().getOtrosImpuestoYOTasa() == null ? "0"
                : getFacturaSuplidor().getOtrosImpuestoYOTasa().toString());

        otrosImpuesto = Double.parseDouble(txtOtrosImpuesto.getText());

        txtImpuestoSelectivoalConsumo.setText(getFacturaSuplidor().getImpuestoSelectivoAlConsumo() == null ? "0"
                : getFacturaSuplidor().getImpuestoSelectivoAlConsumo().toString());

        impuestoSelectivoAlConsumo = Double.parseDouble(txtImpuestoSelectivoalConsumo.getText());

        txtTotalItbisRetendo1.setText(getTotalItbisRetenido().toString());
        txtItbis1.setText(getTotalItbisRetenido().toString());

        txtValorAContabilizar.setText(getFacturaSuplidor().getTotal().toString());

        totales();

    }

    public void inicializarDatos() {

        setFacturaSuplidor(new FacturaSuplidor());
        setEditar(false);
    }

    @FXML
    private void btnEliminarCuentaActionEvent(ActionEvent event) {

        try {

            if (tblDetalleEnt.getSelectionModel().getSelectedIndex() != -1) {

                listaDetalleEnt.remove(tblDetalleEnt.getSelectionModel().getSelectedIndex());
                tblDetalleEnt.refresh();
                txtTotalCredito.setText(getTotalCredito().toString());
                txtTotalDebito.setText(getTotalDebito().toString());
                Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
                txtDiferencia.setText(diferencia.toString());

            } else {
                ClaseUtil.mensaje("Tiene que selccionar un registro");
            }

        } catch (Exception e) {
            e.printStackTrace();
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
    private void cbTipoNcfActionEvent(ActionEvent event) {

        if (!(cbTipoNcf.getSelectionModel().getSelectedIndex() == -1)) {

            TipoNcf tnf = cbTipoNcf.getSelectionModel().getSelectedItem();

            int ung = VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

            relacionNcf = ManejoRelacionNcf.getInstancia()
                    .getNCFUnidadDeNegocio(tnf, ung);

            if (relacionNcf == null) {

                ung = VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo();

                relacionNcf = ManejoRelacionNcf.getInstancia()
                        .getNCFEmpresaOGrupo(tnf, ung);//Comprobante fiscal del grupo 

            }

            if (relacionNcf == null) {
                ClaseUtil.mensaje("El tipo de comprobante seleccionado no tiene secuencia activa");
                cbTipoNcf.getSelectionModel().select(-1);
                return;
            }

            int secActual = Integer.parseInt(relacionNcf.getSecuenciaActual()) + 1;
            txtNcf.setText(relacionNcf.getTipoNcf().getPrefijo().substring(0, 2) + secActual);

        }

    }

    private void validarNcf() {

        int ung = facturaSuplidor.getUnidadDeNegocio().getCodigo();
        boolean empresa = false;

        boolean existe = ManejoFacturaSuplidor.getInstancia().validarNcf(relacionNcf.getActual(), ung);

        relacionNcf = ManejoRelacionNcf.getInstancia()
                .getNCFUnidadDeNegocio(cbTipoNcf.getValue(), ung);

        if (relacionNcf == null) {

            ung = facturaSuplidor.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo();

            relacionNcf = ManejoRelacionNcf.getInstancia()
                    .getNCFEmpresaOGrupo(cbTipoNcf.getValue(), ung);//Comprobante fiscal del grupo fs comercial
            empresa = true;

        }

        if (existe) {

            System.out.println("factura.getUnidadDeNegocio().getCodigo()"
                    + " " + facturaSuplidor.getUnidadDeNegocio().getCodigo() + " " + relacionNcf.getActual());

            while (ManejoFacturaSuplidor.getInstancia().validarNcf(relacionNcf.getActual(), ung)) {

                relacionNcf = utiles.ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo(), ung, empresa);

            }

        } else {

            relacionNcf = utiles.ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo(), ung, empresa);

        }

    }

    private class TaskGuardarFactura extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            guardar();
            return null;

        }

    }

    private void tareaGuardarFactura() {

        try {

            progresBar.setVisible(true);

            taskGuardarFactura = new TaskGuardarFactura();

            progresBar.progressProperty().unbind();

            progresBar.progressProperty().bind(taskGuardarFactura.progressProperty());

            taskGuardarFactura.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent t) -> {

                progresBar.setVisible(false);
                taskGuardarFactura.cancel();

                progresBar.progressProperty().unbind();

                if (!(relacionNcf == null)) {
                    ManejoRelacionNcf.getInstancia().actualizar(relacionNcf);
                }

                limpiar();
                RptFacturaSuplidor.getInstancia().verFactura(getFacturaSuplidor().getCodigo());
                inicializarDatos();

            });

            // Start the Task.
            new Thread(taskGuardarFactura).start();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private Double getMontoExcento() {

        Double total = 0.00;

        for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {

//            total += (det.getCantidad() * det.getPrecio()) + det.getItbis();
            total += det.getMontoExcento() == null ? 0.00 : det.getMontoExcento();
        }

        return utiles.FormatNum.FormatearDouble(total, 2);
    }

    private Double getMontoGravado() {

        Double total = 0.00;

        for (DetalleFacturaSuplidor det : listaDetalleFacturaSuplidor) {

//            total += (det.getCantidad() * det.getPrecio()) + det.getItbis();
            total += (det.getSubTotal() - (det.getMontoExcento() == null ? 0.00 : det.getMontoExcento()));
        }

        return utiles.FormatNum.FormatearDouble(total, 2);
    }

}
