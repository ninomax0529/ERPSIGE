/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.contrato.gps.BuscarImeiGpsController;
import controlador.contrato.gps.BuscarSimCardController;
import controlador.inventario.articulo.FXMLBuscarArticuloController;
import controlador.venta.cliente.FXMLBusClienterController;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import manejo.ManejoConfiguracion;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.articulo.ManejoListaDePrecio;
import manejo.asistenciaTecnica.ManejoAsistencaTecnica;
import manejo.asistenciaTecnica.ManejoEstadoAsistencia;
import manejo.asistenciaTecnica.ManejoTipoAsistencia;
import manejo.asistenciaTecnica.ManejoUbicacionAsistencia;
import manejo.contrato.ManejoContratoDeServicio;
import manejo.contrato.ManejoDatosDeVehiculo;
import manejo.contrato.ManejoEstadoContrato;
import manejo.contrato.ManejoFrecuenciaDePago;
import manejo.contrato.ManejoImeiGps;
import manejo.contrato.ManejoInstalador;
import manejo.contrato.ManejoModalidad;
import manejo.contrato.ManejoPlanDeServicio;
import manejo.contrato.ManejoSimCard;
import manejo.contrato.ManejoTipoDeServicio;
import manejo.contrato.ManejoTipoDeVehiculo;
import manejo.ejecutivoDeVenta.ManejoEjecutivoDeVenta;
import manejo.factura.ManejoFactura;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.Articulo;
import modelo.AsistenciaTecnica;
import modelo.Cliente;
import modelo.ContratoDeServicio;
import modelo.DatosDeVehiculo;
import modelo.DetalleContratoDeServicio;
import modelo.EjecutivoDeVenta;
import modelo.EstadoContrato;
import modelo.ExistenciaArticulo;
import modelo.FrecuenciaDePago;
import modelo.Instalador;
import modelo.ModalidadEquipo;
import modelo.PlanDeServicio;
import modelo.RegistroDeImei;
import modelo.RegistroDeSim;
import modelo.SecuenciaDocumento;
import modelo.TipoDeServicio;
import modelo.TipoVehiculo;
import modelo.Unidad;
import utiles.ClaseUtil;
import utiles.FormatNum;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroContratoDeServicioController1 implements Initializable {

    @FXML
    private JFXCheckBox chReemplazo;
    @FXML
    private JFXTextField txtPrecioInstalacion;
    @FXML
    private TextArea txtrRazonSupEquipo;
    @FXML
    private JFXComboBox<ExistenciaArticulo> cbAlmacen;
    @FXML
    private ProgressBar progresBar;
    @FXML
    private JFXCheckBox chCambioDeModalidad;
    @FXML
    private JFXCheckBox chGpsDelCliente;
    @FXML
    private JFXCheckBox chReactivacion;
    @FXML
    private JFXCheckBox chCompatibilidad1;
    @FXML
    private JFXComboBox<EjecutivoDeVenta> cbSuplidorVentas;
    @FXML
    private JFXCheckBox chCompatibilidad;
    @FXML
    private JFXComboBox<EjecutivoDeVenta> cbSuplidorVentas1;
    @FXML
    private JFXDatePicker dpFechaUltPagoDesde;
    @FXML
    private JFXDatePicker dpFechaUltPagoHasta;
    @FXML
    private TableView<DetalleContratoDeServicio> tbEquipoGps;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcEquipoGpsImei;
    @FXML
    private TableView<DetalleContratoDeServicio> tbServicioGps;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbServicioGpsNombre;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcServicioVehiculo;
    @FXML
    private TableColumn<DatosDeVehiculo, DatosDeVehiculo> tbcEditarVehiculo;
    @FXML
    private TableColumn<DatosDeVehiculo, DatosDeVehiculo> tbcVehiculoHabilitado;
    @FXML
    private JFXCheckBox chReactivacionEquipo;
    @FXML
    private JFXTextField txtCantidadDias;
    @FXML
    private JFXCheckBox chNuevaModalidad;

    /**
     * @return the registroDeImei
     */
    public RegistroDeImei getRegistroDeImei() {
        return registroDeImei;
    }

    /**
     * @param registroDeImei the registroDeImei to set
     */
    public void setRegistroDeImei(RegistroDeImei registroDeImei) {
        this.registroDeImei = registroDeImei;
    }

    private TabPane tabpaneEquipos;
    @FXML
    private JFXButton btnBuscarImei;

    /**
     * @return the registroSim
     */
    public RegistroDeSim getRegistroSim() {
        return registroSim;
    }

    /**
     * @param registroSim the registroSim to set
     */
    public void setRegistroSim(RegistroDeSim registroSim) {
        this.registroSim = registroSim;
    }

    @FXML
    private JFXComboBox<EstadoContrato> cbEstadoContrato;
    @FXML
    private JFXTextField txtNumContrato;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtCliente;
    @FXML
    private JFXButton btnBuscarCliente;
    @FXML
    private JFXComboBox<FrecuenciaDePago> cbFrecuenciaDePago;
    @FXML
    private JFXTextField txtRepresentante;
    @FXML
    private JFXTextField txtCedulaRepresentante;
    @FXML
    private JFXTextField txtCuentaDeMonitereo;
    @FXML
    private JFXComboBox<EjecutivoDeVenta> cbEjecutivoDeVenta;
    @FXML
    private JFXTextField txtCanalDeVenta;
    @FXML
    private JFXTextField txtArticulo;
    @FXML
    private JFXButton btnBuscarArticulo;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private TabPane tabPane;
    @FXML
    private TableView<DetalleContratoDeServicio> tbDetalleContrato;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcEquipo;
    @FXML
    private TableColumn<DetalleContratoDeServicio, Double> tbcPrecioAbonado;
    @FXML
    private TableColumn<DetalleContratoDeServicio, Integer> tbcCantidad;
    @FXML
    private TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> tbcEditar;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcInstalador;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcEjecutivoDeVenta;
    @FXML
    private TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> tbcEditarEquipo;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcDescripcion;
    @FXML
    private JFXDatePicker dpFechaDesde;
    @FXML
    private JFXDatePicker dpFechaHasta;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcFechaDesde;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcFechaHasta;
    @FXML
    private TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> tbcHabilitado;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcFechaUltimoPagoDesde;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcFechaUltimoPagoHasta;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcTipoServicio;

    @FXML
    private TableView<DetalleContratoDeServicio> tbDetalleContrato1;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcEquipo1;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcDescripcion1;
    @FXML
    private TableColumn<DetalleContratoDeServicio, Double> tbcCantidad1;
    @FXML
    private TableColumn<DetalleContratoDeServicio, Double> tbcPrecioAbonado1;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcFechaDesde1;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcFechaHasta1;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcImei1;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcSim1;
    @FXML
    private TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> tbcHabilitado1;
    @FXML
    private JFXComboBox<TipoVehiculo> cbTipoVehiculo;
    @FXML
    private JFXTextField txtMarca;
    @FXML
    private JFXTextField txtModelo;
    @FXML
    private JFXTextField txtplaca;
    @FXML
    private JFXTextField txtChasis;
    @FXML
    private JFXTextField txtColor;
    @FXML
    private JFXButton btnAgregarVehiculo;
    @FXML
    private JFXButton btnEliminarVehiculo;
    @FXML
    private TableView<DatosDeVehiculo> tbDatosDeVehiculo;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcTipoVehiculo;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcMarca;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcModelo;

    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcPlaca;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcColor;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcdetEquipoVehiculo;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcAño;
    @FXML
    private JFXTextField txtSubTotal;
    @FXML
    private JFXTextField txtDescuento;
    @FXML
    private JFXTextField txtItbis;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXButton btnGuardar;
    private TextArea txtConcepto;
    @FXML
    private JFXComboBox<FrecuenciaDePago> cbFrecuenciaDePagoDet;

    @FXML
    private JFXComboBox<PlanDeServicio> cbPlanDeServicio;

    ObservableList<EjecutivoDeVenta> listaEjecutivoDeVenta = FXCollections.observableArrayList();
    ObservableList<EjecutivoDeVenta> listaSuplidoresDeVenta = FXCollections.observableArrayList();
    ObservableList<FrecuenciaDePago> listaFrecuenciaDePago = FXCollections.observableArrayList();
    ObservableList<FrecuenciaDePago> listaFrecuenciaDePagoDet = FXCollections.observableArrayList();
    ObservableList<DetalleContratoDeServicio> listaDetalleContrato = FXCollections.observableArrayList();
    ObservableList<DetalleContratoDeServicio> listaDetEquipos = FXCollections.observableArrayList();
    ObservableList<DetalleContratoDeServicio> listaDetEquipoYServicio = FXCollections.observableArrayList();
    ObservableList<DatosDeVehiculo> listaDatosVehiculo = FXCollections.observableArrayList();
    ObservableList<ModalidadEquipo> listaModadlidad = FXCollections.observableArrayList();
    ObservableList<TipoVehiculo> listatIPOvEHICULO = FXCollections.observableArrayList();
    ObservableList<EstadoContrato> listaEstadoContrato = FXCollections.observableArrayList();
    ObservableList<PlanDeServicio> listaPlanDeServicio = FXCollections.observableArrayList();
    ObservableList<TipoDeServicio> listaTipoDeServicio = FXCollections.observableArrayList();
    ObservableList<RegistroDeSim> listaSimCard = FXCollections.observableArrayList();
    ObservableList<RegistroDeImei> listaImeis = FXCollections.observableArrayList();
    ObservableList<Instalador> listaInstaladores = FXCollections.observableArrayList();
    ObservableList<ExistenciaArticulo> listaExistenciaArticulo = FXCollections.observableArrayList();

    DetalleContratoDeServicio det;
    DatosDeVehiculo datosDeVehiculo;
    ContratoDeServicio contrato;
    Double precioPorDia = 0.00;
    @FXML
    private JFXTextField txtCantidadFrecuencia;
    @FXML
    private JFXDatePicker dpFechaVencimiento;
    @FXML
    private JFXCheckBox chAdicionalArticulo;

    @FXML
    private JFXComboBox<TipoDeServicio> cbTipoDeServicio;
    @FXML
    private JFXTextField txtImei;
    @FXML
    private JFXTextField txtPrecioRenovacion;
    Boolean editarDetalleServicio = false;
    Boolean editarDetalleEquipo = false;
    Boolean editarVehiculo = false;
    @FXML
    private JFXComboBox<Instalador> cbInstalador;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXTextField txtArticulo1;
    @FXML
    private JFXTextField txtCantidad1;
    @FXML
    private JFXComboBox<EjecutivoDeVenta> cbEjecutivoDeVenta1;
    @FXML
    private JFXComboBox<Instalador> cbInstalador1;
    @FXML
    private JFXButton btnAgregar1;
    @FXML
    private JFXButton btnEliminar1;
    @FXML
    private JFXTextField txtPrecioRenovacion1;
    @FXML
    private JFXButton btnBuscarEquipo;
    @FXML
    private JFXTextField txtNumSim;
    @FXML
    private JFXButton btnBuscarSm;

    public ContratoDeServicio getContrato() {
        return contrato;
    }

    public void setContrato(ContratoDeServicio contrato) {
        this.contrato = contrato;
    }
    Cliente cliente;
//    DatosDeVehiculo datosDeVehiculo;
    @FXML
    private JFXTextField txtAño;
    @FXML
    private JFXCheckBox chAdicional;
    @FXML
    private JFXTextField txtMatricula;
    private RegistroDeSim registroSim;
    private RegistroDeImei registroDeImei;
    TaskGuardar taskGuardarContrato;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
    Articulo articulo;

    private boolean editar = false;

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarCombox();
        inicializarTablaDatosVehiculo();
        inicializarTablaDetalle();
        inicializarTablaEquipos();
        progresBar.setVisible(false);
        nuevo();
    }

    public void inicializarTablaDetalle() {

        try {

            listaDatosVehiculo.clear();
            listaDetalleContrato.clear();

            tbDetalleContrato.setItems(listaDetalleContrato);
            tbServicioGps.setItems(listaDetalleContrato);

            tbcEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));
            tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tbcPrecioAbonado.setCellValueFactory(new PropertyValueFactory<>("precioAcordado"));
            tbcFechaDesde.setCellValueFactory(new PropertyValueFactory<>("fechaDesde"));
            tbcFechaHasta.setCellValueFactory(new PropertyValueFactory<>("fechaHasta"));
            tbcFechaUltimoPagoDesde.setCellValueFactory(new PropertyValueFactory<>("fechaUltimoPagoDesde"));
            tbcFechaUltimoPagoHasta.setCellValueFactory(new PropertyValueFactory<>("fechaUltimoPagoHasta"));
            tbcHabilitado.setCellValueFactory(new PropertyValueFactory<>("habilitado"));

            tbServicioGpsNombre.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

            tbcFechaDesde.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaDesde() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy")
                                    .format(cellData.getValue().getFechaDesde()));
                        }
                        return property;
                    });

            tbcFechaHasta.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaHasta() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy")
                                    .format(cellData.getValue().getFechaHasta()));
                        }
                        return property;
                    });

            tbcFechaUltimoPagoDesde.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaUltimoPagoDesde() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy")
                                    .format(cellData.getValue().getFechaUltimoPagoDesde()));
                        }
                        return property;
                    });

            tbcFechaUltimoPagoHasta.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaUltimoPagoHasta() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy")
                                    .format(cellData.getValue().getFechaUltimoPagoHasta()));
                        }
                        return property;
                    });

            tbcHabilitado.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcEditar.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcEquipo.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getEquipo() != null) {
                            property.setValue(cellData.getValue().getEquipo().getNombre());
                        }
                        return property;
                    });

            tbcTipoServicio.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getTipoDeServicio() != null) {
                            property.setValue(cellData.getValue().getTipoDeServicio().getNombre());
                        }
                        return property;
                    });

            tbcHabilitado.setCellFactory((TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> param) -> {

                TableCell<DetalleContratoDeServicio, DetalleContratoDeServicio> cellsc = new TableCell<DetalleContratoDeServicio, DetalleContratoDeServicio>() {
                    @Override
                    public void updateItem(DetalleContratoDeServicio item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;

                        if (item != null) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(50.0, 20.0);

                            if (item.getHabilitado()) {

                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            } else {

                                btnHabilitar.setText("NO");
                                btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            }

                            HBox hbox = new HBox();

//                        hbox.getChildren().addAll(imageview);
                            hbox.getChildren().add(btnHabilitar);

                            hbox.setAlignment(Pos.CENTER);

//                            btnHabilitar.setOnMouseClicked((event) -> {
//
//                                if (item.getHabilitado()) {
//
////                                    Optional<ButtonType> op = ClaseUtil.confirmarMensaje(" Seguro que quiere Suspender este servicio ");
////
////                                    if (op.get() == ButtonType.OK || op.get() == ButtonType.YES) {
////
////                                        if (txtConcepto.getText().isEmpty()) {
////                                            ClaseUtil.mensaje(" Tiene que digitar la razon de la suspension ");
////
////                                            txtConcepto.requestFocus();
////                                            return;
////                                        }
////
////                                    } else {
////                                        return;
////                                    }
//
//                                    item.setHabilitado(false);
//                                    btnHabilitar.setText("NO");
//                                    item.setRazonDesinstalacion(txtConcepto.getText());
//                                    item.setEstado("Suspendido");
//                                    item.setUsuario(VariablesGlobales.USUARIO.getCodigo());
//                                    item.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());
//                                    txtConcepto.clear();
//
//                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
//                                            + "    -fx-border-color: -fx-secondary;\n"
//                                            + "    -fx-border-radius: 15px;\n"
//                                            + "    -fx-background-radius: 15px;\n"
//                                            + "    -fx-b-radius: 10px;\n"
//                                            + " -fx-text-fill: white;"
//                                            + "    -fx-font-size: 12pt;");
//
//                                } else {
//
//                                    item.setHabilitado(true);
//                                    btnHabilitar.setText("SI");
//
//                                    item.setEstado("Habilitado");
//                                    item.setUsuario(VariablesGlobales.USUARIO.getCodigo());
//                                    item.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());
//
//                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
//                                            + "    -fx-border-color: -fx-secondary;\n"
//                                            + "    -fx-border-radius: 15px;\n"
//                                            + "    -fx-background-radius: 10px;\n"
//                                            + " -fx-text-fill: white;"
//                                            + "    -fx-font-size: 12pt;");
//
//                                }
//
//                                txtSubTotal.setText(getSubTotal().toString());
//                                txtItbis.setText(getItbis().toString());
//                                txtTotal.setText(getTotal().toString());
//
//                            });
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

            tbcEditar.setCellFactory((TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> param) -> {

                TableCell<DetalleContratoDeServicio, DetalleContratoDeServicio> cellsc = new TableCell<DetalleContratoDeServicio, DetalleContratoDeServicio>() {
                    @Override
                    public void updateItem(DetalleContratoDeServicio item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;
                        ImageView imageview;
                        if (item != null) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(50.0, 20.0);

                            imageview = new ImageView(new Image(getClass().getResource("/imagen/img_editar.jpg").toString(), 50, 40, false, false));
                            imageview.setFitWidth(50);
                            imageview.setFitHeight(40);

                            HBox hbox = new HBox();

                            hbox.getChildren().addAll(imageview);
                            hbox.setAlignment(Pos.CENTER);

                            hbox.setOnMouseClicked((event) -> {

                                editarServicio(item);

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTablaEquipos() {

        try {

            listaDatosVehiculo.clear();
            listaDetEquipos.clear();

            tbDetalleContrato1.setItems(listaDetEquipos);
            tbEquipoGps.setItems(listaDetEquipos);

            tbcEquipo1.setCellValueFactory(new PropertyValueFactory<>("equipo"));
            tbcCantidad1.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            tbcDescripcion1.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tbcPrecioAbonado1.setCellValueFactory(new PropertyValueFactory<>("precioAcordado"));
            tbcFechaDesde1.setCellValueFactory(new PropertyValueFactory<>("fechaDesde"));
            tbcFechaHasta1.setCellValueFactory(new PropertyValueFactory<>("fechaHasta"));
            tbcHabilitado1.setCellValueFactory(new PropertyValueFactory<>("habilitado"));
            tbcImei1.setCellValueFactory(new PropertyValueFactory<>("numeroImei"));
            tbcSim1.setCellValueFactory(new PropertyValueFactory<>("numeroSim"));

            tbcEquipoGpsImei.setCellValueFactory(new PropertyValueFactory<>("numeroImei"));

            tbcEjecutivoDeVenta.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getEjecutivoDeVenta() != null) {
                            property.setValue(cellData.getValue().getNombreEjecutivoDeVenta());
                        }
                        return property;
                    });

            tbcInstalador.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getEjecutivoDeVenta() != null) {
                            property.setValue(cellData.getValue().getNombreInstalador());
                        }
                        return property;
                    });

            tbcFechaDesde1.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaDesde() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy")
                                    .format(cellData.getValue().getFechaDesde()));
                        }
                        return property;
                    });

            tbcFechaHasta1.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaHasta() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy")
                                    .format(cellData.getValue().getFechaHasta()));
                        }
                        return property;
                    });

            tbcHabilitado1.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcEditarEquipo.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcEquipo1.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getEquipo() != null) {
                            property.setValue(cellData.getValue().getEquipo().getNombre());
                        }
                        return property;
                    });

            tbcHabilitado1.setCellFactory((TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> param) -> {

                TableCell<DetalleContratoDeServicio, DetalleContratoDeServicio> cellsc = new TableCell<DetalleContratoDeServicio, DetalleContratoDeServicio>() {
                    @Override
                    public void updateItem(DetalleContratoDeServicio item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;

                        if (item != null) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(50.0, 20.0);

                            if (item.getHabilitado()) {

                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            } else {

                                btnHabilitar.setText("NO");
                                btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            }

                            HBox hbox = new HBox();

//                        hbox.getChildren().addAll(imageview);
                            hbox.getChildren().add(btnHabilitar);

                            hbox.setAlignment(Pos.CENTER);

//                            btnHabilitar.setOnMouseClicked((event) -> {
//
//                                if (item.getHabilitado()) {
//
//                                    Optional<ButtonType> op = ClaseUtil.confirmarMensaje(" Seguro que quiere Suspender este equipo ");
//
//                                    if (op.get() == ButtonType.OK || op.get() == ButtonType.YES) {
//
//                                        if (txtrRazonSupEquipo.getText().isEmpty()) {
//                                            ClaseUtil.mensaje(" Tiene que digitar la razon de la Suspension ");
//
//                                            txtrRazonSupEquipo.requestFocus();
//                                            return;
//                                        }
//
//                                    } else {
//                                        return;
//                                    }
//
//                                    item.setHabilitado(false);
//                                    btnHabilitar.setText("NO");
//
//                                    item.setEstado("Suspendido");
//                                    item.setRazonDesinstalacion(txtrRazonSupEquipo.getText());
//                                    item.setUsuario(VariablesGlobales.USUARIO.getCodigo());
//                                    item.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());
//                                    txtrRazonSupEquipo.clear();
//
//                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
//                                            + "    -fx-border-color: -fx-secondary;\n"
//                                            + "    -fx-border-radius: 15px;\n"
//                                            + "    -fx-background-radius: 15px;\n"
//                                            + "    -fx-b-radius: 10px;\n"
//                                            + " -fx-text-fill: white;"
//                                            + "    -fx-font-size: 12pt;");
//
//                                } else {
//
//                                    item.setHabilitado(true);
//                                    btnHabilitar.setText("SI");
//
//                                    item.setEstado("Habilitado");
//                                    item.setUsuario(VariablesGlobales.USUARIO.getCodigo());
//                                    item.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());
//
//                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
//                                            + "    -fx-border-color: -fx-secondary;\n"
//                                            + "    -fx-border-radius: 15px;\n"
//                                            + "    -fx-background-radius: 10px;\n"
//                                            + " -fx-text-fill: white;"
//                                            + "    -fx-font-size: 12pt;");
//
//                                }
//
//                                txtSubTotal.setText(getSubTotal().toString());
//                                txtItbis.setText(getItbis().toString());
//                                txtTotal.setText(getTotal().toString());
//
//                            });
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

            tbcEditarEquipo.setCellFactory((TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> param) -> {

                TableCell<DetalleContratoDeServicio, DetalleContratoDeServicio> cellsc = new TableCell<DetalleContratoDeServicio, DetalleContratoDeServicio>() {
                    @Override
                    public void updateItem(DetalleContratoDeServicio item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;
                        ImageView imageview;
                        if (item != null) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(50.0, 20.0);

                            imageview = new ImageView(new Image(getClass().getResource("/imagen/img_editar.jpg").toString(), 50, 40, false, false));
                            imageview.setFitWidth(50);
                            imageview.setFitHeight(40);

                            HBox hbox = new HBox();

                            hbox.getChildren().addAll(imageview);
                            hbox.setAlignment(Pos.CENTER);

                            hbox.setOnMouseClicked((event) -> {

                                editarEquipo(item);

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTablaDatosVehiculo() {

        try {

            listaDatosVehiculo.clear();

            tbcAño.setCellValueFactory(new PropertyValueFactory<>("anio"));
            tbcPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
            tbcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
            tbcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            tbcColor.setCellValueFactory(new PropertyValueFactory<>("color"));
            tbcTipoVehiculo.setCellValueFactory(new PropertyValueFactory<>("tipoDeVehiculo"));

            tbcEditarVehiculo.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcVehiculoHabilitado.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcTipoVehiculo.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getTipoVehiculo() != null) {
                            property.setValue(cellData.getValue().getTipoVehiculo().getNombre());
                        }
                        return property;
                    });

            tbcdetEquipoVehiculo.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getEquipoGps() != null) {
                            property.setValue(cellData.getValue().getEquipoGps().getNumeroImei());
                        }
                        return property;
                    });

            tbcServicioVehiculo.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getServicio() != null) {
                            property.setValue(cellData.getValue().getServicio().getDescripcion());

                        }
                        return property;
                    });

            tbcEditarVehiculo.setCellFactory((TableColumn<DatosDeVehiculo, DatosDeVehiculo> param) -> {

                TableCell<DatosDeVehiculo, DatosDeVehiculo> cellsc = new TableCell<DatosDeVehiculo, DatosDeVehiculo>() {
                    @Override
                    public void updateItem(DatosDeVehiculo item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;
                        ImageView imageview;
                        if (item != null) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(50.0, 20.0);

                            imageview = new ImageView(new Image(getClass().getResource("/imagen/img_editar.jpg").toString(), 50, 40, false, false));
                            imageview.setFitWidth(50);
                            imageview.setFitHeight(40);

                            HBox hbox = new HBox();

                            hbox.getChildren().addAll(imageview);
                            hbox.setAlignment(Pos.CENTER);

                            hbox.setOnMouseClicked((event) -> {

                                editarVehiculo(item);

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

            tbcVehiculoHabilitado.setCellFactory((TableColumn<DatosDeVehiculo, DatosDeVehiculo> param) -> {

                TableCell<DatosDeVehiculo, DatosDeVehiculo> cellsc = new TableCell<DatosDeVehiculo, DatosDeVehiculo>() {
                    @Override
                    public void updateItem(DatosDeVehiculo item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;

                        if (item != null) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(50.0, 20.0);

                            if (item.getHabilitado()) {

                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            } else {

                                btnHabilitar.setText("NO");
                                btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            }

                            HBox hbox = new HBox();

//                        hbox.getChildren().addAll(imageview);
                            hbox.getChildren().add(btnHabilitar);

                            hbox.setAlignment(Pos.CENTER);

                            btnHabilitar.setOnMouseClicked((event) -> {

                                if (item.getHabilitado()) {

                                    item.setHabilitado(false);
                                    btnHabilitar.setText("NO");

                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 15px;\n"
                                            + "    -fx-b-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 12pt;");

                                } else {

                                    item.setHabilitado(true);

                                    btnHabilitar.setText("SI");

                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 12pt;");

                                }

                                txtSubTotal.setText(getSubTotal().toString());
                                txtItbis.setText(getItbis().toString());
                                txtTotal.setText(getTotal().toString());

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
            tbDatosDeVehiculo.setItems(listaDatosVehiculo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inicializarCombox() {

        listaInstaladores.addAll(ManejoInstalador.getInstancia().getLista());
        listaFrecuenciaDePago.addAll(ManejoFrecuenciaDePago.getInstancia().getLista());
        listaFrecuenciaDePagoDet.addAll(ManejoFrecuenciaDePago.getInstancia().getLista());
        listaEjecutivoDeVenta.addAll(ManejoEjecutivoDeVenta.getInstancia().getLista());
        listaSuplidoresDeVenta.addAll(ManejoEjecutivoDeVenta.getInstancia().getLista(true));
        listaModadlidad.addAll(ManejoModalidad.getInstancia().getLista());
        listatIPOvEHICULO.addAll(ManejoTipoDeVehiculo.getInstancia().getLista());
        listaEstadoContrato.addAll(ManejoEstadoContrato.getInstancia().getLista());
        listaPlanDeServicio.addAll(ManejoPlanDeServicio.getInstancia().getLista());
        listaTipoDeServicio.addAll(ManejoTipoDeServicio.getInstancia().getLista());
        listaSimCard.addAll(ManejoSimCard.getInstancia().getLista(true));

        cbInstalador.setConverter(new StringConverter<Instalador>() {

            @Override
            public String toString(Instalador instalador) {
                return instalador.getNombre();
            }

            @Override
            public Instalador fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbInstalador1.setConverter(new StringConverter<Instalador>() {

            @Override
            public String toString(Instalador instalador) {
                return instalador.getNombre();
            }

            @Override
            public Instalador fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbEjecutivoDeVenta.setConverter(new StringConverter<EjecutivoDeVenta>() {

            @Override
            public String toString(EjecutivoDeVenta ejecutivo) {
                return ejecutivo.getNombre();
            }

            @Override
            public EjecutivoDeVenta fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbEjecutivoDeVenta1.setConverter(new StringConverter<EjecutivoDeVenta>() {

            @Override
            public String toString(EjecutivoDeVenta ejecutivo) {
                return ejecutivo.getNombre();
            }

            @Override
            public EjecutivoDeVenta fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbSuplidorVentas.setConverter(new StringConverter<EjecutivoDeVenta>() {

            @Override
            public String toString(EjecutivoDeVenta ejecutivo) {
                return ejecutivo.getNombre();
            }

            @Override
            public EjecutivoDeVenta fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbSuplidorVentas1.setConverter(new StringConverter<EjecutivoDeVenta>() {

            @Override
            public String toString(EjecutivoDeVenta ejecutivo) {
                return ejecutivo.getNombre();
            }

            @Override
            public EjecutivoDeVenta fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoDeServicio.setConverter(new StringConverter<TipoDeServicio>() {

            @Override
            public String toString(TipoDeServicio tipoServicio) {
                return tipoServicio.getNombre();
            }

            @Override
            public TipoDeServicio fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbFrecuenciaDePago.setConverter(new StringConverter<FrecuenciaDePago>() {

            @Override
            public String toString(FrecuenciaDePago ejecutivo) {
                return ejecutivo.getFrecuencia();
            }

            @Override
            public FrecuenciaDePago fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbFrecuenciaDePagoDet.setConverter(new StringConverter<FrecuenciaDePago>() {

            @Override
            public String toString(FrecuenciaDePago ejecutivo) {
                return ejecutivo.getFrecuencia();
            }

            @Override
            public FrecuenciaDePago fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbPlanDeServicio.setConverter(new StringConverter<PlanDeServicio>() {

            @Override
            public String toString(PlanDeServicio planDeServicio) {
                return planDeServicio.getNombre();
            }

            @Override
            public PlanDeServicio fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoVehiculo.setConverter(new StringConverter<TipoVehiculo>() {

            @Override
            public String toString(TipoVehiculo tipoVehiculo) {
                return tipoVehiculo.getNombre();
            }

            @Override
            public TipoVehiculo fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbEstadoContrato.setConverter(new StringConverter<EstadoContrato>() {

            @Override
            public String toString(EstadoContrato estado) {
                return estado.getNombre();
            }

            @Override
            public EstadoContrato fromString(String string) {
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

        cbAlmacen.setItems(listaExistenciaArticulo);

        cbEjecutivoDeVenta.setItems(listaEjecutivoDeVenta);
        cbEjecutivoDeVenta1.setItems(listaEjecutivoDeVenta);
        cbSuplidorVentas.setItems(listaSuplidoresDeVenta);
        cbSuplidorVentas1.setItems(listaSuplidoresDeVenta);
        cbFrecuenciaDePago.setItems(listaFrecuenciaDePago);
        cbFrecuenciaDePagoDet.setItems(listaFrecuenciaDePagoDet);
        cbTipoVehiculo.setItems(listatIPOvEHICULO);
        cbEstadoContrato.setItems(listaEstadoContrato);
        cbPlanDeServicio.setItems(listaPlanDeServicio);
        cbTipoDeServicio.setItems(listaTipoDeServicio);

        cbInstalador.setItems(listaInstaladores);
        cbInstalador1.setItems(listaInstaladores);

    }

    @FXML
    private void btnBuscarClienteActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/cliente/FXMLBusCliente.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        FXMLBusClienterController articuloController = loader.getController();

        if (!(articuloController.getCliente() == null)) {

            setCliente(articuloController.getCliente());

            txtCliente.setText(getCliente().getNombre());

            if (getCliente().getTipoCliente().getCodigo() == 1) {
                txtRepresentante.setText(getCliente().getPersonaResponsable());
                txtCedulaRepresentante.setText(getCliente().getCedulaPasaportePersonaResponsable());

            } else {

                txtRepresentante.setText("N/A");
                txtCedulaRepresentante.setText("N/A");
            }

//            getCliente().setMontoDisponible(ManejoFactura.getInstancia().getMontoDisponible(getCliente()));
        }

    }

    @FXML
    private void btnBuscarArticuloActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/inventario/articulo/FXMLBuscarArticulo.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        FXMLBuscarArticuloController articuloController = loader.getController();
        articuloController.setTipoArticulo("3");
        articuloController.setOrigen(3);//el valor  3 es para  buscar solo articulos de consumo

        articuloController.llenarCampo();

        utiles.ClaseUtil.getStageModal(root);

        System.out.println("ESPERANDOOO!!!");

        if (!(articuloController.getArticulo() == null)) {

            setArticulo(articuloController.getArticulo());
            txtCantidad1.setText("1");
            System.out.println("Estado articulo " + articuloController.getArticulo().getActivo());
            if (articuloController.getArticulo().getActivo() == false) {

                util.ClaseUtil.mensaje("ESTE ARTICULO ESTA INACTIVO,NO SE LE PUEDE HACER MOVIMIENTO ");
                return;
            }

            if (getArticulo().getTipoArticulo().getCodigo() == 3) {//servicio es codigo 3

                if (getArticulo().getNombre().contains("MENSUAL")) {
                    cbFrecuenciaDePagoDet.getSelectionModel().select(1);
                } else if (getArticulo().getNombre().contains("ANUAL")) {

                    cbFrecuenciaDePagoDet.getSelectionModel().select(0);
                } else {
                    cbFrecuenciaDePagoDet.getSelectionModel().select(-1);
                }

                cbTipoDeServicio.setDisable(false);
                txtImei.setDisable(true);

                txtArticulo.setText(getArticulo().getDescripcion());

            } else {

                listaImeis.clear();
                listaImeis.addAll(ManejoImeiGps.getInstancia().getListaPorArticulo(true, getArticulo().getCodigo()));
                cbFrecuenciaDePagoDet.getSelectionModel().select(-1);
                cbTipoDeServicio.setDisable(true);
                txtImei.setDisable(false);
                txtImei.setText(getArticulo().getNumeroImei());
                txtArticulo.setText(getArticulo().getDescripcion() + " IMEI " + getArticulo().getNumeroImei());

            }

            txtCantidad.requestFocus();

        }

    }

    @FXML
    private void txtCantidadKeyPressed(KeyEvent event) {
    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {

        agregarServicio();

    }

    @FXML
    private void btnAgregarEquipoActionEvent(ActionEvent event) {

        try {

            if (txtArticulo1.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que seleccionar un articulo");
                return;
            }

            if (cbAlmacen.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar un almacen");
                cbAlmacen.requestFocus();
                return;
            }

            if (txtCantidad1.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar la cantidad ");
                txtCantidad1.requestFocus();
                return;
            }

            if (txtImei.getText().isEmpty()) {
                ClaseUtil.mensaje(" El IMEI no puede estar vacio ");
                txtImei.requestFocus();
                return;
            }

            if (cbEjecutivoDeVenta1.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje(" El ejecutivo de venta esta vacio ");
                cbEjecutivoDeVenta1.requestFocus();
                return;
            }

            if (cbInstalador1.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar un instalador ");
                cbInstalador1.requestFocus();
                return;
            }

            if (txtNumSim.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar el SIM ");
                txtNumSim.requestFocus();
                return;
            }

            if (txtPrecioInstalacion.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar el precio de instalacion ");
                txtPrecioInstalacion.requestFocus();
                return;
            }

            agregarEquipo();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event
    ) {

        if (tbDetalleContrato.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje("Tiene que selccionar un registro");
            return;
        }

        listaDetalleContrato.remove(tbDetalleContrato.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void tbDetalleReciboMouseClicked(MouseEvent event
    ) {
    }

    @FXML
    private void btnAgregarVehiculoActionEvent(ActionEvent event) {

        try {

            if (tbServicioGps.getSelectionModel().getSelectedIndex() == -1
                    && tbEquipoGps.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje(" Tiene que seleccionar un equipo y un servicio para este vehiculo  ");
                return;
            }

            DetalleContratoDeServicio dets = tbServicioGps.getSelectionModel().getSelectedItem();
            DetalleContratoDeServicio detEqu = tbEquipoGps.getSelectionModel().getSelectedItem();

            if (detEqu.getCodigo() == null && dets.getCodigo() == null) {

                ClaseUtil.mensaje(" Tiene que guardar el equipo y el servicio  primero \n  y despues  agregar el equipo y servicio al vehiculo  ");
                return;
            }

            if (cbTipoVehiculo.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar un tipo de vehiculo ");
                return;
            }

            if (txtModelo.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar un modelo ");
                txtModelo.requestFocus();
                return;
            }

            if (txtMarca.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar una marca ");
                txtMarca.requestFocus();
                return;
            }

            if (txtplaca.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar una placa ");
                txtplaca.requestFocus();
                return;
            }

            if (txtAño.getText().isEmpty()) {

                txtAño.setText("0");

                return;
            }

            if (txtChasis.getText().isEmpty()) {

                txtChasis.setText("na");

            }

            if (txtColor.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar un color ");
                txtColor.requestFocus();
                return;
            }

            if (txtMatricula.getText().isEmpty()) {
                txtMatricula.setText("na");

            }

            if (!(editarVehiculo)) {
                datosDeVehiculo = new DatosDeVehiculo();
            }

            datosDeVehiculo.setChasis(txtChasis.getText());
            datosDeVehiculo.setMarca(txtMarca.getText());
            datosDeVehiculo.setModelo(txtModelo.getText());
            datosDeVehiculo.setColor(txtColor.getText());
            datosDeVehiculo.setTipoVehiculo(cbTipoVehiculo.getSelectionModel().getSelectedItem());
            datosDeVehiculo.setAnio(Integer.parseInt(txtAño.getText()));
            datosDeVehiculo.setAdicional(chAdicional.isSelected());
            datosDeVehiculo.setMatricula(txtMatricula.getText());
            datosDeVehiculo.setPlaca(txtplaca.getText());
            datosDeVehiculo.setHabilitado(true);
            datosDeVehiculo.setServicio(tbServicioGps.getSelectionModel().getSelectedItem());
            datosDeVehiculo.setEquipoGps(tbEquipoGps.getSelectionModel().getSelectedItem());
            datosDeVehiculo.setFechaInstalacion(new Date());
            datosDeVehiculo.setUsuario(VariablesGlobales.USUARIO.getCodigo());
            datosDeVehiculo.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());
            datosDeVehiculo.setRenovar(false);
            datosDeVehiculo.setContrato(contrato.getCodigo());

            datosDeVehiculo.setVehiculo(cbTipoVehiculo.getSelectionModel().getSelectedItem().getNombre()
                    + " " + txtMarca.getText() + " " + txtModelo.getText() + " " + txtColor.getText() + " "
                    + txtplaca.getText() + " " + txtChasis.getText()
            );

            if (editarVehiculo == false) {

                listaDatosVehiculo.add(datosDeVehiculo);
            }

            tbDatosDeVehiculo.refresh();
            tbEquipoGps.refresh();
            tbServicioGps.refresh();

            limpiarDatosVehiculo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEliminarVehiculoActionEvent(ActionEvent event) {

        if (tbDatosDeVehiculo.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje("Tiene que selccionar un registro");
            return;
        }

        listaDatosVehiculo.remove(tbDatosDeVehiculo.getSelectionModel().getSelectedIndex());
    }

    @FXML
    @SuppressWarnings("null")
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (txtNumContrato.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar un numero de contrato ");
                txtNumContrato.requestFocus();
                return;
            }

            if (dpFecha.getValue() == null) {
                ClaseUtil.mensaje("Tiene que seleccionar una fecha");
                dpFecha.requestFocus();
                return;
            }

            if (cbPlanDeServicio.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje(" Tiene que selccionar un plan de servicio ");
                cbPlanDeServicio.requestFocus();
                return;
            }

            if (cbEstadoContrato.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje(" Tiene que selccionar un estado ");
                cbEstadoContrato.requestFocus();
                return;
            }

            if (txtCliente.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que seleccionar un cliente");
                return;
            }

            if (cbFrecuenciaDePago.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje(" La frecuencia de pago esta vacia ");
                cbFrecuenciaDePago.requestFocus();
                return;
            }

//            if (listaDetalleContrato.size() <= 0) {
//                ClaseUtil.mensaje(" El contrato no tiene servicios ");
//
//                return;
//            }
//            if (listaDetEquipos.size() <= 0 && listaDetalleContrato.get(0).getTipoDeServicio().getCodigo() == 1) {
//                ClaseUtil.mensaje(" El contrato no tiene equipos ");
//
//                return;
//            }
//
//            if (listaDatosVehiculo.size() <= 0 && editar == false) {
//                ClaseUtil.mensaje(" El contrato no tiene vehiculo agregado  ");
//
//                return;
//            }
            tareaContrato();
//            
//
//            taskGuardarContrato = new TaskGuardarContrato();
//            taskGuardarContrato.run();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void nuevo() {

        contrato = new ContratoDeServicio();
        det = null;
        txtNumContrato.setText(ManejoContratoDeServicio.getInstancia().getNumero().toString());
        cbTipoDeServicio.setDisable(true);
        editarDetalleServicio = false;
        editar = false;
        cbInstalador.setValue(null);
        cbEjecutivoDeVenta.setValue(null);
        listaDetEquipos.clear();
        listaDetalleContrato.clear();
        listaDatosVehiculo.clear();

    }

    private void limpiar() {

        txtCanalDeVenta.clear();
        txtCuentaDeMonitereo.clear();
        txtRepresentante.clear();
        txtCedulaRepresentante.clear();
        cbEjecutivoDeVenta.getSelectionModel().select(-1);
        cbFrecuenciaDePago.getSelectionModel().select(-1);
        cbEstadoContrato.getSelectionModel().select(-1);
        cbPlanDeServicio.getSelectionModel().select(-1);
        listaDatosVehiculo.clear();
        listaDetalleContrato.clear();
        listaDetEquipos.clear();
        txtTotal.clear();
        txtCantidad1.clear();

        txtCliente.clear();
        dpFecha.setValue(null);
        dpFechaVencimiento.setValue(null);
        txtTotal.clear();

        txtSubTotal.clear();
        txtItbis.clear();
        listaImeis.clear();
        listaSimCard.clear();
        listaSimCard.addAll(ManejoSimCard.getInstancia().getLista(true));

    }

    private Double totalapagar() {

        Double total = 0.00;

        for (DetalleContratoDeServicio det : listaDetalleContrato) {

            total += det.getPrecioPagado();
        }

        return total;
    }

    private void limpiarDatosVehiculo() {

        txtMatricula.clear();
        txtMarca.clear();
        txtMarca.clear();
        txtModelo.clear();
        txtChasis.clear();
        txtColor.clear();
        txtplaca.clear();
        cbTipoVehiculo.getSelectionModel().select(-1);
        chAdicional.setSelected(false);
        txtAño.clear();

    }

    public void llenarCampo() {

        listaDetalleContrato.clear();
        listaDetEquipos.clear();
        listaDatosVehiculo.clear();

        txtNumContrato.setText(getContrato().getNumeroDeContrato());
        txtCanalDeVenta.setText(getContrato().getCanalDeVenta());
        txtCuentaDeMonitereo.setText(getContrato().getCuentaDeMonitoreo());
        txtRepresentante.setText(getContrato().getRepresentante());
        txtCedulaRepresentante.setText(getContrato().getCedulaRepresentante());

        cbFrecuenciaDePago.getSelectionModel().select(getContrato().getFrecuenciaDePago());
        cbPlanDeServicio.getSelectionModel().select(getContrato().getPlanDeServicio());
        cbEstadoContrato.getSelectionModel().select(getContrato().getEstado());
        txtTotal.setText(Double.toString(getContrato().getTotalAPagar()));

        dpFecha.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getContrato().getFecha()));
        dpFechaVencimiento.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getContrato().getFechaVencimiento()));
        setCliente(getContrato().getCliente());

        txtCliente.setText(getCliente().getNombre() + " " + getCliente().getApellido());

        listaDetalleContrato.addAll(ManejoContratoDeServicio.getInstancia().getDetalleContratoServicio(getContrato().getCodigo()));
        listaDetEquipos.addAll(ManejoContratoDeServicio.getInstancia().getDetalleContratoEquipo(getContrato().getCodigo()));

        System.out.println(" listaDetEquipos : " + listaDetEquipos.size());

        listaDatosVehiculo.addAll(ManejoContratoDeServicio
                .getInstancia().getDatosVehiculoPorContrto(getContrato().getCodigo()));
//        for (DetalleContratoDeServicio det : listaDetEquipos) {
//
//            System.out.println("equipos : " + det.getDescripcion());
//            listaDatosVehiculo.addAll(ManejoContratoDeServicio
//                    .getInstancia().getDatosVehiculo(det.getCodigo()));
//
//        }

        txtSubTotal.setText(getSubTotal().toString());
        txtItbis.setText(getItbis().toString());
        txtTotal.setText(getTotal().toString());

    }

    private Double getSubTotal() {

        Double subTotal = 0.00;

        for (DetalleContratoDeServicio detcT : listaDetalleContrato) {

//            subTotal += det.getCantidad() * det.getPrecio();
            if (detcT.getHabilitado()) {
                subTotal += detcT.getSubTotal();
            }

        }

        for (DetalleContratoDeServicio detcT : listaDetEquipos) {

            if (detcT.getHabilitado()) {
                subTotal += detcT.getSubTotal();
            }

        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotal() {

        Double total = 0.00;

        for (DetalleContratoDeServicio detcT : listaDetalleContrato) {

            if (detcT.getHabilitado()) {

                total += detcT.getTotal();
            }

        }

        for (DetalleContratoDeServicio detcT : listaDetEquipos) {

            if (detcT.getHabilitado()) {

                total += detcT.getTotal();
            }

        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getItbis() {

        Double itbis = 0.00;

        for (DetalleContratoDeServicio detcT : listaDetalleContrato) {

            if (detcT.getHabilitado()) {
                itbis += detcT.getItbis();
            }

        }

        for (DetalleContratoDeServicio detcT : listaDetEquipos) {

            if (detcT.getHabilitado()) {
                itbis += detcT.getItbis();
            }

        }

        return FormatNum.FormatearDouble(itbis, 2);
    }

    @FXML
    private void cbFrecuenciaDePagoDetActionEvent(ActionEvent event) {

        if (!(cbFrecuenciaDePagoDet.getSelectionModel().getSelectedIndex() == -1)) {

//            if (cbPlanDeServicio.getSelectionModel().getSelectedItem().getCodigo() == 1
//                    && !(cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem().getCodigo() == 1)) {
//
//                ClaseUtil.mensaje(" El plan es anual necesita  que la frecuencia de pago sea anual ");
//
//            } else if (cbPlanDeServicio.getSelectionModel().getSelectedItem().getCodigo() == 2
//                    && !(cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem().getCodigo() == 2)) {
//
//                ClaseUtil.mensaje(" El plan es mensual necesita  que la frecuencia de pago sea mensual ");
//
//            } else {
//
            txtCantidadFrecuencia.requestFocus();
            dpFechaDesde.setDisable(false);
//            }

        } else {

            dpFechaDesde.setDisable(true);
        }
    }

    @FXML
    private void dpFechaActionEvent(ActionEvent event) {

        Date fechaVencimiento, fecha;

        if (!(dpFecha.getValue() == null)) {

            fecha = ClaseUtil.asDate(dpFecha.getValue());

            fechaVencimiento = ClaseUtil.Fechadiadespues(fecha, 365);

            dpFechaVencimiento.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaVencimiento));

        }
    }

    @FXML
    private void dpFechaDesdeActionEvent(ActionEvent event) {

        try {

            Date fechaVencimiento, fecha;
            String desc = "";
            Double dias = 30.00;
            txtDescripcion.clear();

            if (cbFrecuenciaDePagoDet.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar una frecuencia de pago ");
                cbFrecuenciaDePagoDet.requestFocus();
                return;
            }

            if (txtCantidadFrecuencia.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar una cantidad ");
                txtCantidadFrecuencia.requestFocus();
                return;
            }

            if (!(dpFechaDesde.getValue() == null) && !txtCantidadFrecuencia.getText().isEmpty()) {

                fecha = ClaseUtil.asDate(dpFechaDesde.getValue());

                System.out.println("txtCantidadFrecuencia " + txtCantidadFrecuencia.getText());
                Double cantidadFecuenciaPago = Double.parseDouble(txtCantidadFrecuencia.getText());
                Double cantidadFecuenciaPagoDias = Double.parseDouble(
                        txtCantidadDias.getText().isEmpty() ? "0" : txtCantidadDias.getText()
                );

                Double frecuencia = cantidadFecuenciaPago;

                int cantidadGPS = Integer.parseInt(txtCantidad.getText().isEmpty() ? "0"
                        : txtCantidad.getText()
                );

                if (cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem().getCodigo() == 1) {

                    cantidadFecuenciaPago *= 12;
                    dias = 360.00;

                    System.out.println("cantidadFecuenciaPago " + cantidadFecuenciaPago);

                } else if (cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem().getCodigo() == 3) {
                    cantidadFecuenciaPago *= 3;
                }

                fechaVencimiento = ClaseUtil.FechaMesDespues(fecha, cantidadFecuenciaPago.intValue());

                if (cbTipoDeServicio.getSelectionModel().getSelectedItem().getCodigo() == 1) {//GPS

                    if (getArticulo().getNombre().contains("EQUIPO")) {

                        desc = frecuencia.intValue() + "  " + getArticulo().getNombre()
                                + " POR SERVICIO DE " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
                    } else {

                        if (cantidadFecuenciaPagoDias > 0) {

                            Unidad un = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(getArticulo().getCodigo()).getUnidad();

                            if (un == null) {

                                ClaseUtil.mensaje("Este articulo no tiene una unidad configurada");
                                return;
                            }
                            Double precioPagado = ManejoListaDePrecio.getInstancia().getPrecioVentaDeArticulo(getArticulo().getCodigo(), un.getCodigo());

                            precioPorDia = ClaseUtil.roundDouble(precioPagado / dias, 2);
                            precioPorDia = precioPorDia * cantidadFecuenciaPagoDias;

                            desc = frecuencia.intValue() + " " + cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem().getDescripcion()
                                    + "  Y " + cantidadFecuenciaPagoDias.intValue() + " DIAS  "
                                    + " POR SERVICIO DE " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                    + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
                        } else {

                            desc = frecuencia.intValue() + " " + cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem().getDescripcion()
                                    + " POR SERVICIO DE " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                    + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
                        }

                    }

                } else if (cbTipoDeServicio.getSelectionModel().getSelectedItem().getCodigo() == 2) {//flota

                    if (getArticulo().getNombre().contains("EQUIPO")) {

                        desc = frecuencia.intValue() + "  " + getArticulo().getNombre()
                                + " POR SERVICIO DE " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
                    } else {

                        desc = frecuencia.intValue() + " " + cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem().getDescripcion()
                                + " POR " + cantidadGPS + " SERVICIO DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
                    }
                } else {

                    if (getArticulo().getNombre().contains("EQUIPO")) {

                        desc = frecuencia.intValue() + "  " + getArticulo().getNombre()
                                + " POR SERVICIO DE " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
                    } else {

                        desc = frecuencia.intValue() + " " + cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem().getDescripcion()
                                + " POR 1 SERVICIO DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
                    }
                }

//            desc = cantidad + " " + cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem().getDescripcion()
//                    + " POR SERVICIO DE " + cantidadgPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
//                    + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
                dpFechaHasta.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaVencimiento));
                txtDescripcion.setText(desc);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void cbPlanDeServicioActionevent(ActionEvent event) {

        if (!(cbPlanDeServicio.getSelectionModel().getSelectedIndex() == -1)) {

            if (cbPlanDeServicio.getSelectionModel().getSelectedItem().getCodigo() == 1) {

                cbFrecuenciaDePago.getSelectionModel().select(0);

            } else if (cbPlanDeServicio.getSelectionModel().getSelectedItem().getCodigo() == 2) {

                cbFrecuenciaDePago.getSelectionModel().select(1);

            } else {

                cbFrecuenciaDePago.getSelectionModel().select(2);
            }

        }
    }

    @FXML
    private void cbTipoDeServicioActionEvent(ActionEvent event) {

        if (!(cbTipoDeServicio.getSelectionModel().getSelectedIndex() == -1)) {

            txtCantidad.requestFocus();
        }
    }

    private void agregarServicio() {

        Double valorItbis = 0.00;
        try {

//            if (cbSuplidorVentas1.getSelectionModel().getSelectedIndex() == -1
//                    && (chCompatibilidad.isSelected() || chCompatibilidad1.isSelected())) {
//
//                ClaseUtil.mensaje("Tiene que seleccionar el suplidor de venta ");
//                return;
//            }
            if (txtArticulo.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que seleccionar un articulo");
                return;
            }

            if (txtCantidad.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar la cantida de Gps ");
                txtCantidad.requestFocus();
                return;
            }

            if (txtDescripcion.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar la descripcion ");
                txtDescripcion.requestFocus();
                return;
            }

            if (cbFrecuenciaDePagoDet.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar una frecuencia de pago");
                cbFrecuenciaDePagoDet.requestFocus();
                return;
            }

            if (txtCantidadFrecuencia.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar la cantida en funcion de la frecuencia ");
                txtCantidadFrecuencia.requestFocus();
                return;
            }

            if (cbEjecutivoDeVenta.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje(" El ejecutivo de venta esta vacio ");
                cbEjecutivoDeVenta.requestFocus();
                return;
            }

            if (cbInstalador.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar un instalador ");
                cbInstalador.requestFocus();
                return;
            }

            if (dpFechaDesde.getValue() == null) {
                ClaseUtil.mensaje("Tiene que seleccionar la fecha desde ");
                dpFechaDesde.requestFocus();
                return;
            }

            if (dpFechaHasta.getValue() == null) {
                ClaseUtil.mensaje("Tiene que seleccionar la fecha hasta ");
                dpFechaHasta.requestFocus();
                return;
            }

            if (cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem().getCodigo() == 1) {

                if (txtPrecioRenovacion.getText().isEmpty()) {
                    ClaseUtil.mensaje("Tiene que digitar el precio de renovacion ");
                    txtPrecioRenovacion.requestFocus();
                    return;
                }
            }

            contrato.setNuevo(true);

            System.out.println("det " + det);
            if (editarDetalleServicio == false) {
                det = new DetalleContratoDeServicio();
            }

            det.setHabilitado(true);

            Unidad un = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(getArticulo().getCodigo()).getUnidad();

            if (un == null) {

                ClaseUtil.mensaje("Este articulo no tiene una unidad configurada");
                return;
            }

            det.setPrecioPagado(ManejoListaDePrecio.getInstancia().getPrecioVentaDeArticulo(getArticulo().getCodigo(), un.getCodigo()));

            System.out.println("Precio pagado : "+det.getPrecioPagado());
            if (precioPorDia > 0) {

                det.setPrecioPagado(det.getPrecioPagado() + precioPorDia);

                    System.out.println("Precio pagado mas dias : "+det.getPrecioPagado());
            }

            det.setContratoDeServicio(contrato);
            det.setEquipo(getArticulo());
            det.setCantidad(Integer.parseInt(txtCantidad.getText()));
            det.setModalidad(ManejoModalidad.getInstancia().getModalidad(1));
            det.setMensualidad(0);
            det.setPrecioAcordado(det.getPrecioPagado());

            System.out.println(" txtConcepto.getText() " + txtConcepto.getText());

            if (!(txtConcepto.getText() == null)) {
                det.setObservacion(txtConcepto.getText());
            } else {

                det.setObservacion("na");
            }

            det.setDescripcion(txtDescripcion.getText());
            det.setSubTotal(det.getCantidad() * det.getPrecioPagado());
            det.setFechaDesde(ClaseUtil.asDate(dpFechaDesde.getValue()));
            det.setFechaHasta(ClaseUtil.asDate(dpFechaHasta.getValue()));

            if (editarDetalleServicio) {
                System.out.println("ediatando fecha ult pago .... ");

                if (!(dpFechaUltPagoDesde.getValue() == null && dpFechaUltPagoHasta.getValue() == null)) {
//                if (!(det.getFechaUltimoPagoDesde() == null && det.getFechaUltimoPagoHasta() == null)) {

                    System.out.println("ediatando fecha ult pago no son nulo ");
                    det.setFechaUltimoPagoDesde(ClaseUtil.asDate(dpFechaUltPagoDesde.getValue()));
                    det.setFechaUltimoPagoHasta(ClaseUtil.asDate(dpFechaUltPagoHasta.getValue()));
                } else {
                    System.out.println("ediatando fecha ult pago  son nulo ");
                }

            }

            det.setCantidadFrecuenciaDePago(Double.parseDouble(txtCantidadFrecuencia.getText()));
            det.setFrecuenciaDePago(cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem());
            det.setAdicional(chAdicionalArticulo.isSelected());
            det.setNumeroImei("na");
            det.setNumeroSim("na");
            det.setEstado("Instalado");
            det.setFechaInstalacion(new Date());
            det.setUsuario(VariablesGlobales.USUARIO.getCodigo());
            det.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());
            det.setInstalador(cbInstalador.getSelectionModel().getSelectedItem());
            det.setNombreInstalador(cbInstalador.getSelectionModel().getSelectedItem().getNombre());
            det.setEjecutivoDeVenta(cbEjecutivoDeVenta.getSelectionModel().getSelectedItem());
            det.setNombreEjecutivoDeVenta(cbEjecutivoDeVenta.getSelectionModel().getSelectedItem().getNombre());

            det.setCambioDeModalidad(chCambioDeModalidad.isSelected());//si es que si no entra en comision
            det.setReactivacion(chReactivacion.isSelected());//si es que si no entra en comision

            if (chCompatibilidad.isSelected() == true || chCompatibilidad1.isSelected() == true) {
                det.setCompatibilidad(true);//si es que si no entra en comision
            } else {
                det.setCompatibilidad(false);//si es que si no entra en comision
            }

            if (!(cbSuplidorVentas1.getSelectionModel().getSelectedIndex() == -1)) {
                det.setSuplidor(cbSuplidorVentas1.getSelectionModel().getSelectedItem().getCodigo());
            }

            if (cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem().getCodigo() == 1) {
                det.setPrecioRenovacion(Double.parseDouble(txtPrecioRenovacion.getText()));
            }

            if (det.getEquipo().getTipoArticulo().getCodigo() == 3 && cbTipoDeServicio.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar un tipo de servicio ");
                cbTipoDeServicio.requestFocus();
                return;
            }

            if (det.getEquipo().getTipoArticulo().getCodigo() == 3) {
                det.setTipoDeServicio(cbTipoDeServicio.getSelectionModel().getSelectedItem());
            }

            if (det.getCantidadFrecuenciaDePago() > 1) {
                det.setRecurrente(false);
            } else {
                det.setRecurrente(true);
            }

            if (getArticulo().getExentoItbis()) {

                valorItbis = 0.0;
                det.setTasaItbis(0.00);

            } else {

                double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                valorItbis = ClaseUtil.roundDouble((det.getSubTotal()) * (itbis / 100), 2); //esto es cuando el precio ya tiene el itbis
                det.setTasaItbis(itbis);
            }

            det.setItbis(valorItbis);
            det.setTotal(det.getItbis() + det.getSubTotal());

            System.out.println("editarDetalle : " + editarDetalleServicio);
            if (editarDetalleServicio == false) {
                listaDetalleContrato.add(det);
            }

            tbDetalleContrato.refresh();

            txtSubTotal.setText(getSubTotal().toString());
            txtItbis.setText(getItbis().toString());
            txtTotal.setText(getTotal().toString());
            chCompatibilidad.setSelected(false);
            chCambioDeModalidad.setSelected(false);
            chReactivacion.setSelected(false);
            precioPorDia = 0.00;

            editarDetalleServicio = false;

            limpiarDetalleContrato();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void agregarEquipo() {

        Double valorItbis = 0.00;
        try {

            System.out.println("det " + det);

            if (editarDetalleEquipo == false) {
                det = new DetalleContratoDeServicio();
            }

            det.setHabilitado(true);

            Unidad un = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(getArticulo().getCodigo()).getUnidad();

            if (un == null) {

                ClaseUtil.mensaje("Este articulo no tiene una unidad configurada");
                return;
            }

            contrato.setNuevo(true);

            det.setPrecioPagado(ManejoListaDePrecio.getInstancia().getPrecioVentaDeArticulo(getArticulo().getCodigo(), un.getCodigo()));
            det.setContratoDeServicio(contrato);
            det.setEquipo(getArticulo());
            det.setAlmacen(cbAlmacen.getSelectionModel().getSelectedItem().getAlmacen().getCodigo());
            det.setUnidad(cbAlmacen.getSelectionModel().getSelectedItem().getUnidad());
            det.setCantidad(Integer.parseInt(txtCantidad1.getText()));

            det.setModalidad(ManejoModalidad.getInstancia().getModalidad(2));
            det.setMensualidad(0);
            det.setPrecioAcordado(det.getPrecioPagado());
            det.setObservacion(txtConcepto.getText().isEmpty() ? "n/a" : txtConcepto.getText());

            det.setDescripcion(getArticulo().getNombre());
            det.setSubTotal(det.getCantidad() * det.getPrecioPagado());
            det.setFechaDesde(new Date());
            det.setFechaHasta(new Date());
            det.setCantidadFrecuenciaDePago(1.0);
            det.setFrecuenciaDePago(null);
            det.setAdicional(false);
            det.setNumeroSim(getRegistroSim().getNumero());
            det.setSim(getRegistroSim());
            det.setEstado("Instalado");
            det.setEjecutivoDeVenta(cbEjecutivoDeVenta1.getSelectionModel().getSelectedItem());
            det.setInstalador(cbInstalador1.getSelectionModel().getSelectedItem());
            det.setNombreEjecutivoDeVenta(cbEjecutivoDeVenta1.getSelectionModel().getSelectedItem().getNombre());

            if (!(getRegistroDeImei() == null)) {

                det.setImei(getRegistroDeImei());
                det.setNumeroImei(getRegistroDeImei().getNumero());
            }

            det.setUsuario(VariablesGlobales.USUARIO.getCodigo());
            det.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());
            det.setNombreInstalador(cbInstalador1.getSelectionModel().getSelectedItem().getNombre());
            det.setGpsDelCliente(chGpsDelCliente.isSelected());
            det.setReemplazo(chReemplazo.isSelected());
            det.setReactivacion(chReactivacionEquipo.isSelected());

            if (chCompatibilidad.isSelected() == true || chCompatibilidad1.isSelected() == true) {
                det.setCompatibilidad(true);//si es que si no entra en comision
            } else {
                det.setCompatibilidad(false);//si es que si no entra en comision
            }

            if (!(cbSuplidorVentas.getSelectionModel().getSelectedIndex() == -1)) {

                det.setSuplidor(cbSuplidorVentas.getSelectionModel().getSelectedItem().getCodigo());
            }

            if (det.getGpsDelCliente()) {

                det.setPrecioAcordado(0);
                det.setPrecioPagado(0);
                det.setCantidad(0);

            }

            if (det.getReemplazo()) {

                System.out.println("reeemplazo ");
                det.setFechaUltimoPagoDesde(new Date());
                det.setFechaUltimoPagoHasta(new Date());
                det.setFacturado(true);

            }

            det.setPrecioInstalacion(Double.parseDouble(txtPrecioInstalacion.getText()));

            if (getArticulo().getExentoItbis()) {

                valorItbis = 0.0;
                det.setTasaItbis(0.00);

            } else {

                double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                valorItbis = ClaseUtil.roundDouble((det.getSubTotal()) * (itbis / 100), 2); //esto es cuando el precio ya tiene el itbis
                det.setTasaItbis(itbis);
            }

            det.setItbis(valorItbis);
            det.setTotal(det.getItbis() + det.getSubTotal());

            if (editarDetalleEquipo == false) {
                det.setFechaInstalacion(new Date());
                listaDetEquipos.add(det);
            }

            tbDetalleContrato1.refresh();

            txtSubTotal.setText(getSubTotal().toString());
            txtItbis.setText(getItbis().toString());
            txtTotal.setText(getTotal().toString());

            chCompatibilidad1.setSelected(false);
            chGpsDelCliente.setSelected(false);
            chReemplazo.setSelected(false);

            editarDetalleEquipo = false;
            limpiarDetalleContratoEquipo();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void tbDetalleContratoMouseClicked(MouseEvent event) {

        if (!(tbDetalleContrato.getSelectionModel().getSelectedIndex() == -1)) {

            DetalleContratoDeServicio det = tbDetalleContrato.getSelectionModel().getSelectedItem();
            listaDatosVehiculo.clear();
            if (!(det.getCodigo() == null)) {
                listaDatosVehiculo.addAll(ManejoContratoDeServicio.getInstancia().getDatosVehiculo(det.getCodigo()));
            }

            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(2);
            }

        }
    }

    private void limpiarDetalleContrato() {

        txtArticulo.clear();
        txtCantidad.clear();
        txtDescripcion.clear();
        txtConcepto.clear();
        txtPrecioRenovacion.clear();
        dpFechaDesde.setValue(null);
        dpFechaHasta.setValue(null);

        dpFechaUltPagoDesde.setValue(null);
        dpFechaUltPagoHasta.setValue(null);

        chAdicionalArticulo.setSelected(false);
        cbFrecuenciaDePagoDet.getSelectionModel().select(-1);
        txtCantidadFrecuencia.clear();
        cbTipoDeServicio.getSelectionModel().select(-1);
        cbInstalador.getSelectionModel().select(-1);
        cbEjecutivoDeVenta.getSelectionModel().select(-1);

        det = null;
    }

    private void limpiarDetalleContratoEquipo() {

        txtArticulo1.clear();
        txtArticulo1.clear();
        txtCantidad1.clear();
        txtPrecioInstalacion.clear();
        txtImei.clear();
        txtNumSim.clear();
        chAdicionalArticulo.setSelected(false);
        chReemplazo.setSelected(false);

        cbInstalador1.getSelectionModel().select(-1);
        cbEjecutivoDeVenta1.getSelectionModel().select(-1);
        setRegistroDeImei(null);
        setRegistroSim(null);
        det = null;
    }

    private void guardarContrato() {

        try {

            listaDetEquipoYServicio.addAll(listaDetEquipos);

            listaDetEquipoYServicio.addAll(listaDetalleContrato);

            contrato.setNumeroDeContrato(txtNumContrato.getText());
            contrato.setCliente(getCliente());
            contrato.setEjecutivoDeVenta(cbEjecutivoDeVenta.getSelectionModel().getSelectedItem());
            contrato.setCuentaDeMonitoreo(txtCuentaDeMonitereo.getText().isEmpty() ? "NA" : txtCuentaDeMonitereo.getText());
            contrato.setRepresentante(txtRepresentante.getText());
            contrato.setEstado(cbEstadoContrato.getSelectionModel().getSelectedItem());
            contrato.setNombreCliente(getCliente().getNombre());
            contrato.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            contrato.setCanalDeVenta(txtCanalDeVenta.getText().isEmpty() ? "n/a" : txtCanalDeVenta.getText());
            contrato.setFrecuenciaDePago(cbFrecuenciaDePago.getSelectionModel().getSelectedItem());

            contrato.setDetalleContratoDeServicioCollection(listaDetEquipoYServicio);
            contrato.setFechaVencimiento(ClaseUtil.asDate(dpFechaVencimiento.getValue()));

            contrato.setSubTotal(getSubTotal());
            contrato.setTotalAPagar(getTotal());
            contrato.setItbis(getItbis());

            contrato.setUsuario(VariablesGlobales.USUARIO);
            contrato.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            contrato.setCedulaRepresentante(txtCedulaRepresentante.getText());
            contrato.setPlanDeServicio(cbPlanDeServicio.getSelectionModel().getSelectedItem());
            contrato.setAvisoDiasParaVencer(true);
            contrato.setCuandiaAntesDeVencer(VariablesGlobales.USUARIO.getUnidadDeNegocio().getDiaAntesDeVencer());
            contrato.setCantidadMes(0);

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(contrato.getUnidadDeNegocio().getCodigo(), 16);

            if (!(secDoc == null)) {

                if (editar == false) {

                    boolean existe = ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero());

                    if (existe) {

                        System.out.println("existe " + secDoc.getNumero());

                        while (ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero())) {

                            secDoc.setNumero(secDoc.getNumero() + 1);
                            ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                        }

                        secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(contrato.getUnidadDeNegocio().getCodigo(), 16);

                        contrato.setNumero(secDoc.getNumero());
                        contrato.setSecuenciaDocumento(secDoc);

                    } else {

                        System.out.println("No existe este no.Contrato  " + secDoc.getNumero());
                        contrato.setNumero(secDoc.getNumero());
                        contrato.setFechaRegistro(new Date());

                        if (cbEstadoContrato.getSelectionModel().getSelectedItem().getCodigo() == 1) {
                            contrato.setFechaDeActivacion(new Date());
                        }
                        contrato.setSecuenciaDocumento(secDoc);
                        secDoc.setNumero(secDoc.getNumero() + 1);
                        ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                    }
                }

                ContratoDeServicio contratoDb = null;

                if (editar) {

                    contratoDb = ManejoContratoDeServicio.getInstancia().actualizar(contrato);
                    listaDetEquipos.clear();
                    listaDetEquipos.addAll(ManejoContratoDeServicio.getInstancia().getDetalleContratoEquipo(contratoDb.getCodigo()));

                    if (!(contratoDb == null)) {
//                        
                        for (DetalleContratoDeServicio detcontrato : listaDetEquipos) {

                            if (detcontrato.getEquipo().getTipoArticulo().getCodigo() != 3
                                    && !(detcontrato.getEquipo().getNumeroSim() == null)
                                    && !(detcontrato.getSim() == null)) {

                                detcontrato.getSim().setDisponible(false);

                                if (!(detcontrato.getImei() == null)) {
                                    detcontrato.getImei().setDisponible(false);
                                    ManejoImeiGps.getInstancia().actualizar(detcontrato.getImei());
                                }

                                ManejoSimCard.getInstancia().actualizar(detcontrato.getSim());

                            }
                        }

                        System.out.println("editando listaDatosVehiculo " + tbDatosDeVehiculo.getItems().size());

                        for (DatosDeVehiculo v : tbDatosDeVehiculo.getItems()) {
                            v.setContrato(contratoDb.getCodigo());
                        }

                        tbDatosDeVehiculo.getItems().forEach((dvh) -> {

                            ManejoDatosDeVehiculo.getInstancia().actualizar(dvh);

                        });

                        listaDatosVehiculo.clear();
                        listaDatosVehiculo.addAll(ManejoContratoDeServicio.getInstancia().getDatosVehiculoPorContrto(contratoDb.getCodigo()));

                        try {

                            resgistrarInstalacion(listaDetEquipos);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {

                        ClaseUtil.mensaje("Hubo un error actualizando el contrato");
                        return;
                    }

                } else {

                    contratoDb = ManejoContratoDeServicio.getInstancia().salvar(contrato);
                    listaDetEquipos.clear();

                    listaDetEquipos.addAll(ManejoContratoDeServicio.getInstancia().getDetalleContratoEquipo(getContrato().getCodigo()));

                    if (!(contratoDb == null)) {

                        for (DetalleContratoDeServicio detcontrato : listaDetEquipos) {

                            if (detcontrato.getEquipo().getTipoArticulo().getCodigo() != 3 && !(detcontrato.getEquipo().getNumeroSim() == null)) {

                                detcontrato.getSim().setDisponible(false);
                                detcontrato.getSim().setFechaParaDuplicado(new Date());

                                if (!(detcontrato.getImei() == null)) {
                                    detcontrato.getImei().setDisponible(false);

                                    ManejoImeiGps.getInstancia().actualizar(detcontrato.getImei());
                                }

                                ManejoSimCard.getInstancia().actualizar(detcontrato.getSim());

                            }
                        }

                        for (DatosDeVehiculo v : listaDatosVehiculo) {
                            v.setContrato(contratoDb.getCodigo());
                        }

                        System.out.println("guardando listaDatosVehiculo " + listaDatosVehiculo);
                        listaDatosVehiculo.forEach((dvh) -> {

                            ManejoDatosDeVehiculo.getInstancia().salvar(dvh);

                        });

                        try {

                            resgistrarInstalacion(listaDetEquipos);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        ClaseUtil.mensaje("Hubo un error guardando el contrato");
                        return;
                    }

                }

            } else {
                ClaseUtil.mensaje("No hay una secuencia configurada para los contrato");
            }

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error guardando el contrato");
            e.printStackTrace();
        }

    }

    private void actualizarContrato() {

        try {

            contrato.setNumeroDeContrato(txtNumContrato.getText());
            contrato.setCliente(getCliente());
            contrato.setEjecutivoDeVenta(cbEjecutivoDeVenta.getSelectionModel().getSelectedItem());
            contrato.setCuentaDeMonitoreo(txtCuentaDeMonitereo.getText().isEmpty() ? "NA" : txtCuentaDeMonitereo.getText());
            contrato.setRepresentante(txtRepresentante.getText());
            contrato.setEstado(cbEstadoContrato.getSelectionModel().getSelectedItem());
            contrato.setNombreCliente(getCliente().getNombre());
            contrato.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            contrato.setCanalDeVenta(txtCanalDeVenta.getText().isEmpty() ? "n/a" : txtCanalDeVenta.getText());
            contrato.setFechaRegistro(new Date());
            contrato.setFrecuenciaDePago(cbFrecuenciaDePago.getSelectionModel().getSelectedItem());

            contrato.setDetalleContratoDeServicioCollection(listaDetalleContrato);
            contrato.setFechaVencimiento(ClaseUtil.asDate(dpFechaVencimiento.getValue()));

            contrato.setSubTotal(getSubTotal());
            contrato.setTotalAPagar(getTotal());
            contrato.setItbis(getItbis());

            contrato.setUsuario(VariablesGlobales.USUARIO);
            contrato.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            contrato.setCedulaRepresentante(txtCedulaRepresentante.getText());
            contrato.setPlanDeServicio(cbPlanDeServicio.getSelectionModel().getSelectedItem());
            contrato.setAvisoDiasParaVencer(true);
            contrato.setCuandiaAntesDeVencer(VariablesGlobales.USUARIO.getUnidadDeNegocio().getDiaAntesDeVencer());
            contrato.setCantidadMes(0);

            if (cbEstadoContrato.getSelectionModel().getSelectedItem().getCodigo() == 1) {
                contrato.setFechaDeActivacion(new Date());
            }

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(contrato.getUnidadDeNegocio().getCodigo(), 16);

            if (!(secDoc == null)) {

                if (editar == false) {

                    boolean existe = ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero());

                    if (existe) {

                        System.out.println("existe " + secDoc.getNumero());

                        while (ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero())) {

                            secDoc.setNumero(secDoc.getNumero() + 1);
                            ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                        }

                        secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(contrato.getUnidadDeNegocio().getCodigo(), 16);

                        contrato.setNumero(secDoc.getNumero());
                        contrato.setSecuenciaDocumento(secDoc);

                    } else {

                        System.out.println("No existe este no.Contrato  " + secDoc.getNumero());
                        contrato.setNumero(secDoc.getNumero());
                        contrato.setSecuenciaDocumento(secDoc);
                        secDoc.setNumero(secDoc.getNumero() + 1);
                        ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                    }
                }

                ContratoDeServicio contratoDb = null;

                if (editar) {

                    contratoDb = ManejoContratoDeServicio.getInstancia().actualizar(contrato);
                    listaDetalleContrato.clear();
                    listaDetalleContrato.addAll(ManejoContratoDeServicio.getInstancia().getDetalleContrato(contratoDb.getCodigo()));

                    if (!(contratoDb == null)) {
//                        
                        for (DetalleContratoDeServicio detcontrato : listaDetalleContrato) {

                            if (detcontrato.getEquipo().getTipoArticulo().getCodigo() != 3
                                    && !(detcontrato.getEquipo().getNumeroSim() == null)
                                    && !(detcontrato.getSim() == null)) {

                                detcontrato.getSim().setDisponible(false);

                                if (!(detcontrato.getImei() == null)) {
                                    detcontrato.getImei().setDisponible(false);
                                    ManejoImeiGps.getInstancia().actualizar(detcontrato.getImei());
                                }

                                ManejoSimCard.getInstancia().actualizar(detcontrato.getSim());

                            }
                        }

                        System.out.println("editando listaDatosVehiculo " + tbDatosDeVehiculo.getItems().size());

                        tbDatosDeVehiculo.getItems().forEach((dvh) -> {
                            ManejoDatosDeVehiculo.getInstancia().actualizar(dvh);
//                            ManejoDatosDeVehiculo.getInstancia().salvar(dvh);
                        });
                    } else {

                        ClaseUtil.mensaje("Hubo un error actualizando el contrato");
                        return;
                    }

                } else {

                    contratoDb = ManejoContratoDeServicio.getInstancia().salvar(contrato);
                    listaDetalleContrato.clear();
                    listaDetalleContrato.addAll(ManejoContratoDeServicio.getInstancia().getDetalleContrato(contratoDb.getCodigo()));

                    if (!(contratoDb == null)) {

                        for (DetalleContratoDeServicio detcontrato : listaDetalleContrato) {

                            if (detcontrato.getEquipo().getTipoArticulo().getCodigo() != 3 && !(detcontrato.getEquipo().getNumeroSim() == null)) {

                                detcontrato.getSim().setDisponible(false);
                                detcontrato.getSim().setFechaParaDuplicado(new Date());

                                if (!(detcontrato.getImei() == null)) {
                                    detcontrato.getImei().setDisponible(false);

                                    ManejoImeiGps.getInstancia().actualizar(detcontrato.getImei());
                                }

                                ManejoSimCard.getInstancia().actualizar(detcontrato.getSim());

                            }
                        }

                        System.out.println("guardando listaDatosVehiculo " + listaDatosVehiculo);
                        listaDatosVehiculo.forEach((dvh) -> {
                            ManejoDatosDeVehiculo.getInstancia().salvar(dvh);
                        });
                    } else {
                        ClaseUtil.mensaje("Hubo un error guardando el contrato");
                        return;
                    }

                }

                if (editar == true) {

                    llenarCampo();
                }

            } else {
                ClaseUtil.mensaje("No hay una secuencia configurada para los contrato");
            }

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error guardando el contrato");
            e.printStackTrace();
        }

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {

        limpiar();
        limpiarDatosVehiculo();
        limpiarDetalleContrato();
        nuevo();
    }

    @FXML
    private void btnBuscarEquipoActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/inventario/articulo/FXMLBuscarArticulo.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        FXMLBuscarArticuloController articuloController = loader.getController();
        articuloController.setTipoArticulo("1,2");
        articuloController.setOrigen(4);//el valor  3 es para  buscar solo articulos de consumo

        articuloController.llenarCampo();

        utiles.ClaseUtil.getStageModal(root);

        System.out.println("ESPERANDOOO!!!");

        if (!(articuloController.getArticulo() == null)) {

            setArticulo(articuloController.getArticulo());

            listaExistenciaArticulo.clear();
            listaExistenciaArticulo.addAll(ManejoExistenciaArticulo.getInstancia().getAlmacenExistenciaArticulo(getArticulo().getCodigo()));

            txtCantidad1.setText("1");
            System.out.println("Estado articulo " + articuloController.getArticulo().getActivo());
            if (articuloController.getArticulo().getActivo() == false) {

                util.ClaseUtil.mensaje(" ESTE ARTICULO ESTA INACTIVO,NO SE LE PUEDE HACER MOVIMIENTO ");
                return;
            }

            listaImeis.clear();
            listaImeis.addAll(ManejoImeiGps.getInstancia().getListaPorArticulo(true, getArticulo().getCodigo()));
            cbFrecuenciaDePagoDet.getSelectionModel().select(-1);
            cbTipoDeServicio.setDisable(true);
            txtImei.setDisable(false);
//            txtImei.setText(getArticulo().getNumeroImei());
            txtArticulo1.setText(getArticulo().getDescripcion());

//            if (getArticulo().getTipoArticulo().getCodigo() == 3) {//servicio es codigo 3
//
//                if (getArticulo().getNombre().contains("MENSUAL")) {
//                    cbFrecuenciaDePagoDet.getSelectionModel().select(1);
//                } else if (getArticulo().getNombre().contains("ANUAL")) {
//
//                    cbFrecuenciaDePagoDet.getSelectionModel().select(0);
//                } else {
//                    cbFrecuenciaDePagoDet.getSelectionModel().select(-1);
//                }
//
//                cbTipoDeServicio.setDisable(false);
//                txtImei.setDisable(true);
//
//                txtArticulo1.setText(getArticulo().getDescripcion());
//
//            } else {
//
//                listaImeis.clear();
//                listaImeis.addAll(ManejoImeiGps.getInstancia().getListaPorArticulo(true, getArticulo().getCodigo()));
//                cbFrecuenciaDePagoDet.getSelectionModel().select(-1);
//                cbTipoDeServicio.setDisable(true);
//                txtImei.setDisable(false);
//                txtImei.setText(getArticulo().getNumeroImei());
//                txtArticulo1.setText(getArticulo().getDescripcion() + " IMEI " + getArticulo().getNumeroImei());
//
//            }
            txtCantidad1.requestFocus();

        } else {
            System.out.println("no tiene articulo ");
        }
    }

    @FXML
    private void cbEjecutivoDeVenta1ActionEvent(ActionEvent event) {

//        if (!(cbEjecutivoDeVenta1.getSelectionModel().getSelectedIndex() == -1)) {
//
//            cbEjecutivoDeVenta.getSelectionModel().select(cbEjecutivoDeVenta1.getSelectionModel().getSelectedItem());
//        }
    }

    @FXML
    private void cbInstalador1ActionEvent(ActionEvent event) {

//        if (!(cbInstalador1.getSelectionModel().getSelectedIndex() == -1)) {
//
//            cbInstalador.getSelectionModel().select(cbInstalador1.getSelectionModel().getSelectedItem());
//        }
    }

    @FXML
    private void tbDetalleContratoEquiposMouseClicked(MouseEvent event) {

        if (!(tbDetalleContrato1.getSelectionModel().getSelectedIndex() == -1)) {

            DetalleContratoDeServicio det = tbDetalleContrato1.getSelectionModel().getSelectedItem();
            listaDatosVehiculo.clear();

            listaDatosVehiculo.addAll(ManejoContratoDeServicio.getInstancia().getDatosVehiculoPorEquipo(det.getCodigo()));
            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(2);
            }

        }

    }

    @FXML
    private void btnBuscarSmActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/contrato/gps/BuscarSimCard.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        BuscarSimCardController controller = loader.getController();

        utiles.ClaseUtil.getStageModal(root);

        System.out.println("ESPERANDOOO!!!");

        if (!(controller.getRegistro() == null)) {

            setRegistroSim(controller.getRegistro());

            txtNumSim.setText(getRegistroSim().getNumero());

        }

    }

    @FXML
    private void btnBuscarImeiActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/contrato/gps/BuscarImeiGps.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        BuscarImeiGpsController controller = loader.getController();
        controller.setArticulo(getArticulo());
        controller.llenarCampo();

        utiles.ClaseUtil.getStageModal(root);

        System.out.println("ESPERANDOOO!!!");

        if (!(controller.getRegistro() == null)) {

            setRegistroDeImei(controller.getRegistro());

            if (!(controller.getRegistro().getSim() == null)) {
                setRegistroSim(controller.getRegistro().getSim());
                txtNumSim.setText(getRegistroSim().getNumero());
                btnBuscarSm.setDisable(true);
            } else {

                txtNumSim.clear();
                btnBuscarSm.setDisable(false);
            }

            txtImei.setText(getRegistroDeImei().getNumero());

        }

    }

    private void editarServicio(DetalleContratoDeServicio detC) {

        limpiarDetalleContrato();

        det = detC;
        setArticulo(det.getEquipo());
        System.out.println("det asignacion " + det);
        editarDetalleServicio = true;

        try {

            if (!(det.getEjecutivoDeVenta() == null)) {
                cbEjecutivoDeVenta.getSelectionModel().select(det.getEjecutivoDeVenta());
            }

            if (!(det.getInstalador() == null)) {
                cbInstalador.getSelectionModel().select(det.getInstalador());
            }

            if (det.getEquipo().getTipoArticulo().getCodigo() == 3) { //tipo servicio

                txtCantidadFrecuencia.setText(Double.toString(det.getCantidadFrecuenciaDePago()));

                cbFrecuenciaDePagoDet.getSelectionModel().select(det.getFrecuenciaDePago());
                cbTipoDeServicio.getSelectionModel().select(det.getTipoDeServicio());
                cbTipoDeServicio.setDisable(false);
                dpFechaDesde.setValue(ClaseUtil.convertToLocalDateViaMilisecond(det.getFechaDesde()));
                dpFechaHasta.setValue(ClaseUtil.convertToLocalDateViaMilisecond(det.getFechaHasta()));

                if (!(det.getFechaUltimoPagoDesde() == null && det.getFechaUltimoPagoHasta() == null)) {

                    dpFechaUltPagoDesde.setValue(ClaseUtil.convertToLocalDateViaMilisecond(det.getFechaUltimoPagoDesde()));
                    dpFechaUltPagoHasta.setValue(ClaseUtil.convertToLocalDateViaMilisecond(det.getFechaUltimoPagoHasta()));
                }

            }

            txtArticulo.setText(det.getEquipo().getDescripcion());
            txtDescripcion.setText(det.getDescripcion());
            txtConcepto.setText(det.getObservacion());
            txtPrecioRenovacion.setText(Double.toString(det.getPrecioRenovacion() == null ? 0
                    : det.getPrecioRenovacion()));
            txtCantidad.setText(Integer.toString(det.getCantidad()));

            txtSubTotal.setText(getSubTotal().toString());
            txtItbis.setText(getItbis().toString());
            txtTotal.setText(getTotal().toString());

            System.out.println("tbDetalleContrato.getSelectionModel().getSelectedItem() " + tbDetalleContrato.getSelectionModel().getSelectedItem());

        } catch (Exception e) {
            System.out.println(" error " + e.getMessage());
        }

    }

    private void editarEquipo(DetalleContratoDeServicio deC) {

        int equipo = 0;
        listaDatosVehiculo.clear();
        limpiarDetalleContratoEquipo();
        det = deC;
        setArticulo(det.getEquipo());
        System.out.println("det asignacion " + det);
        editarDetalleEquipo = true;

        if (!(det.getEjecutivoDeVenta() == null)) {
            cbEjecutivoDeVenta1.getSelectionModel().select(det.getEjecutivoDeVenta());
        }

        if (!(det.getInstalador() == null)) {
            cbInstalador1.getSelectionModel().select(det.getInstalador());
        }

        setRegistroSim(det.getSim());

        if (getArticulo().getAlmacen() == null) {
            listaExistenciaArticulo.clear();
            listaExistenciaArticulo.add(ManejoExistenciaArticulo.getInstancia().getAlmacenExistencia(4));
            cbAlmacen.getSelectionModel().select(0);
        } else {

            listaExistenciaArticulo.clear();
            listaExistenciaArticulo.add(ManejoExistenciaArticulo.getInstancia()
                    .getAlmacenExistencia(getArticulo().getAlmacen()));
            cbAlmacen.getSelectionModel().select(0);
        }

        txtImei.setText(det.getNumeroImei() == null ? "" : det.getNumeroImei());
        txtPrecioInstalacion.setText(det.getPrecioInstalacion() == null ? "0" : det.getPrecioInstalacion().toString());

        if (!(getRegistroSim() == null)) {
            txtNumSim.setText(getRegistroSim().getNumero());
        }

        txtArticulo1.setText(det.getEquipo().getDescripcion());
        txtCantidad1.setText(Integer.toHexString(det.getCantidad()));
        chGpsDelCliente.setSelected(det.getGpsDelCliente());
        chCompatibilidad.setSelected(det.getCompatibilidad());
        chReemplazo.setSelected(det.getReemplazo());
        chReactivacion.setSelected(det.getReactivacion());

        System.out.println("det " + det);
        if (editarDetalleEquipo && !(det.getCodigo() == null)) {
            equipo = det.getCodigo();
            listaDatosVehiculo.addAll(ManejoContratoDeServicio.getInstancia().getDatosVehiculo(equipo));
        }

        txtSubTotal.setText(getSubTotal().toString());
        txtItbis.setText(getItbis().toString());
        txtTotal.setText(getTotal().toString());

    }

    private void editarVehiculo(DatosDeVehiculo dVh) {

        limpiarDatosVehiculo();
        datosDeVehiculo = dVh;
        System.out.println("det asignacion " + det);
        editarVehiculo = true;

        txtplaca.setText(datosDeVehiculo.getPlaca() == null ? "na" : datosDeVehiculo.getPlaca());
        txtColor.setText(datosDeVehiculo.getColor());
        txtMarca.setText(datosDeVehiculo.getMarca() == null ? "na" : datosDeVehiculo.getMarca());
        txtModelo.setText(datosDeVehiculo.getModelo() == null ? "na" : datosDeVehiculo.getModelo());
        txtChasis.setText(datosDeVehiculo.getChasis() == null ? "na" : datosDeVehiculo.getChasis());
        txtMatricula.setText(datosDeVehiculo.getMatricula() == null ? "na" : datosDeVehiculo.getMatricula());
        txtAño.setText(datosDeVehiculo.getAnio() == null ? "na" : datosDeVehiculo.getAnio().toString());

        tbServicioGps.getSelectionModel().select(datosDeVehiculo.getServicio());
        tbEquipoGps.getSelectionModel().select(datosDeVehiculo.getEquipoGps());

        cbTipoVehiculo.getSelectionModel().select(datosDeVehiculo.getTipoVehiculo());

    }

    private void resgistrarInstalacion(List<DetalleContratoDeServicio> listaGps) {

        AsistenciaTecnica ast = new AsistenciaTecnica();

        for (DetalleContratoDeServicio detCont : listaGps) {

            AsistenciaTecnica astBd = ManejoAsistencaTecnica.getInstancia().existeInstalacion(detCont.getCodigo());
            System.out.println("astBd : " + astBd);

            if (detCont.getFechaUltimoPagoDesde() == null && astBd == null) {

                System.out.println("detCont.getReemplazo() : " + detCont.getReemplazo());
                ast.setCantidad(detCont.getCantidad());
                ast.setCliente(detCont.getContratoDeServicio().getCliente());
                ast.setTecnico(detCont.getInstalador());
                ast.setEstado(ManejoEstadoAsistencia.getInstancia().getEstadoAsistenciaTecnica().get(1));
                ast.setFechaRegistro(detCont.getFechaDesde());
                ast.setFechaCierre(detCont.getFechaDesde());
                ast.setNombreCliente(getCliente().getNombre());
                ast.setNombreTecnico(detCont.getInstalador() == null ? "na" : detCont.getInstalador().getNombre());
                ast.setOrigenAsistencia(" INSTALACION DE EQUIPO DE GPS ");
                ast.setPrecio(detCont.getPrecioInstalacion());

                if (detCont.getCompatibilidad()) {

                    System.out.println("COMPATIBILIDAD : ");
                    ast.setSolucion("COMPATIBILIDAD ");
                    ast.setCantidad(1);
                    ast.setTipoDeAsistencia(ManejoTipoAsistencia.getInstancia().getTipoAsistencia(9));

                } else if (detCont.getReactivacion()) {

                    System.out.println("REACTIVACION : ");
                    ast.setSolucion("REACTIVACION");
                    ast.setCantidad(1);
                    ast.setTipoDeAsistencia(ManejoTipoAsistencia.getInstancia().getTipoAsistencia(10));

                } else if (detCont.getReemplazo()) {

                    System.out.println("REEMPLAZO : ");
                    ast.setSolucion("REEMPLAZO");
                    ast.setCantidad(1);
                    ast.setTipoDeAsistencia(ManejoTipoAsistencia.getInstancia().getTipoAsistencia(11));
                } else {

                    ast.setSolucion(" INSTALACION NUEVO GPS ");
                    ast.setTipoDeAsistencia(ManejoTipoAsistencia.getInstancia().getTipoAsistencia(4));
                }

                ast.setUbicacion(ManejoUbicacionAsistencia.getInstancia().getUbicacionAsistencia().get(1));
                ast.setUsuario(VariablesGlobales.USUARIO);
                ast.setDetalleContrato(detCont.getCodigo());

                ManejoAsistencaTecnica.getInstancia().salvar(ast);
                ast = new AsistenciaTecnica();

            }

        }

    }

    @FXML
    private void cbAlmacenActionEvent(ActionEvent event) {
    }

    @FXML
    private void cbTipoVehiculoActionEvent(ActionEvent event) {

        if (!(cbTipoVehiculo.getSelectionModel().getSelectedIndex() == -1)) {
            txtMarca.requestFocus();
        }
    }

    @FXML
    private void chReemplazoActionEvent(ActionEvent event) {
        chGpsDelCliente.setSelected(false);
    }

    @FXML
    private void chGpsDelClienteActionEvent(ActionEvent event) {

        chReemplazo.setSelected(false);
    }

    @FXML
    private void chCambioDeModalidadActionEvent(ActionEvent event) {
    }

    @FXML
    private void chReactivacionActionEvent(ActionEvent event) {

        if (chReactivacion.isSelected()) {
            chReactivacionEquipo.setSelected(true);
            chReemplazo.setSelected(false);
            chCompatibilidad.setSelected(false);
            chCompatibilidad1.setSelected(false);
        } else {

            chReactivacionEquipo.setSelected(false);
        }
    }

    @FXML
    private void chCompatibilidadActionEvent(ActionEvent event) {
        chReactivacionEquipo.setSelected(false);
        chReactivacion.setSelected(false);
        chReemplazo.setSelected(false);
    }

    @FXML
    private void cbSuplidorVentasActionEvent(ActionEvent event) {
    }

    @FXML
    private void cbSuplidorVentasActionEvent1(ActionEvent event) {
    }

    @FXML
    private void dpFechaUltPagoDesdeActionEvent(ActionEvent event) {
    }

    @FXML
    private void chReactivacionEquipoActionevent(ActionEvent event) {

        if (chReactivacionEquipo.isSelected()) {
            chReactivacion.setSelected(true);
            chReemplazo.setSelected(false);
            chCompatibilidad.setSelected(false);
            chCompatibilidad1.setSelected(false);
        } else {
            chReactivacion.setSelected(false);
        }
    }

    private class TaskGuardarContrato extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            try {

                guardar(contrato, 0, 0);
            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;

        }

        private void guardar(ContratoDeServicio ri, int i, int total) throws InterruptedException {

            guardarContrato();
            this.updateMessage(" Procesada  " + i + "  De " + total);
            Thread.sleep(200);
        }

    }

    private class TaskGuardar extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            guardarContrato();
            return null;

        }

    }

    private void tareaContrato() {

        try {

            progresBar.setVisible(true);
            btnGuardar.setDisable(true);

            taskGuardarContrato = new TaskGuardar();

            progresBar.progressProperty().unbind();

            progresBar.progressProperty().bind(taskGuardarContrato.progressProperty());

            taskGuardarContrato.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent t) -> {

                progresBar.setVisible(false);
                taskGuardarContrato.cancel();

                if (editar == true) {

                    ClaseUtil.mensaje(" Contrato Actualizado exitosamente ");
                    btnGuardar.setDisable(false);
                    llenarCampo();
                } else {

                    btnGuardar.setDisable(false);
                    llenarCampo();
                    editar = true;
//                    limpiar();
//                    limpiarDatosVehiculo();
//                    limpiarDetalleContrato();
//                    limpiarDetalleContratoEquipo();
                    ClaseUtil.mensaje("Contrato guardado exitosamente");
//                    nuevo();
                }

                listaDetEquipoYServicio.clear();

                progresBar.progressProperty().unbind();

            });

            // Start the Task.
            new Thread(taskGuardarContrato).start();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

}
