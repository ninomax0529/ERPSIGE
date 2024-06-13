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
import controlador.inventario.articulo.FXMLBuscarArticuloController;
import controlador.venta.cliente.FXMLBusClienterController;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import manejo.ManejoConfiguracion;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.articulo.ManejoListaDePrecio;
import manejo.contrato.ManejoContratoDeServicio;
import manejo.contrato.ManejoDatosDeVehiculo;
import manejo.contrato.ManejoEstadoContrato;
import manejo.contrato.ManejoFrecuenciaDePago;
import manejo.contrato.ManejoImeiGps;
import manejo.contrato.ManejoModalidad;
import manejo.contrato.ManejoPlanDeServicio;
import manejo.contrato.ManejoSimCard;
import manejo.contrato.ManejoTipoDeServicio;
import manejo.contrato.ManejoTipoDeVehiculo;
import manejo.ejecutivoDeVenta.ManejoEjecutivoDeVenta;
import manejo.factura.ManejoFactura;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.Articulo;
import modelo.Cliente;
import modelo.ContratoDeServicio;
import modelo.DatosDeVehiculo;
import modelo.DetalleContratoDeServicio;
import modelo.EjecutivoDeVenta;
import modelo.EstadoContrato;
import modelo.FrecuenciaDePago;
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
public class ReinstalacionDeUnClienteAOtroController implements Initializable {

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
    private TableColumn<DetalleContratoDeServicio, String> tbcModalidad;
    @FXML
    private TableColumn<DetalleContratoDeServicio, Double> tbcPrecioAcordado;
    @FXML
    private TableColumn<DetalleContratoDeServicio, Double> tbcPrecioAbonado;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcObservacion;
    @FXML
    private TableColumn<DetalleContratoDeServicio, Integer> tbcCantidad;

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
    private TableColumn<DetalleContratoDeServicio, String> tbcAdicionalArticulo;
    @FXML
    private TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> tbcHabilitado;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcImei;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcSim;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcFechaUltimoPagoDesde;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcFechaUltimoPagoHasta;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcTipoServicio;

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
    private TableColumn<DatosDeVehiculo, String> tbcChasis;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcPlaca;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcColor;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcAdicional;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcServicio;
    @FXML
    private TableColumn<DatosDeVehiculo, DatosDeVehiculo> tbcVehiculoHabilitado;
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
    @FXML
    private TextArea txtConcepto;
    @FXML
    private JFXComboBox<ModalidadEquipo> cbModalidad;
    @FXML
    private JFXComboBox<FrecuenciaDePago> cbFrecuenciaDePagoDet;
    @FXML
    private JFXComboBox<RegistroDeSim> cbSimCard;

    @FXML
    private JFXComboBox<PlanDeServicio> cbPlanDeServicio;

    ObservableList<EjecutivoDeVenta> listaEjecutivoDeVenta = FXCollections.observableArrayList();
    ObservableList<FrecuenciaDePago> listaFrecuenciaDePago = FXCollections.observableArrayList();
    ObservableList<FrecuenciaDePago> listaFrecuenciaDePagoDet = FXCollections.observableArrayList();
    ObservableList<DetalleContratoDeServicio> listaDetalleContrato = FXCollections.observableArrayList();
    ObservableList<DatosDeVehiculo> listaDatosVehiculo = FXCollections.observableArrayList();
    ObservableList<ModalidadEquipo> listaModadlidad = FXCollections.observableArrayList();
    ObservableList<TipoVehiculo> listatIPOvEHICULO = FXCollections.observableArrayList();
    ObservableList<EstadoContrato> listaEstadoContrato = FXCollections.observableArrayList();
    ObservableList<PlanDeServicio> listaPlanDeServicio = FXCollections.observableArrayList();
    ObservableList<TipoDeServicio> listaTipoDeServicio = FXCollections.observableArrayList();
    ObservableList<RegistroDeSim> listaSimCard = FXCollections.observableArrayList();
    ObservableList<RegistroDeImei> listaImeis = FXCollections.observableArrayList();

    DetalleContratoDeServicio det;
    ContratoDeServicio contrato;
    @FXML
    private JFXTextField txtCantidadFrecuencia;
    @FXML
    private JFXDatePicker dpFechaVencimiento;
    @FXML
    private JFXCheckBox chAdicionalArticulo;

    @FXML
    private JFXComboBox<TipoDeServicio> cbTipoDeServicio;
    @FXML
    private JFXDatePicker dpFechaUltimoPagoDesde;
    @FXML
    private JFXDatePicker dpFechaUltimoPagoHasta;
    @FXML
    private JFXTextField txtImei;
    @FXML
    private JFXTextField txtPrecioRenovacion;
    Boolean editarDetalle = false;
    @FXML
    private JFXComboBox<RegistroDeImei> cbImei;

    public ContratoDeServicio getContrato() {
        return contrato;
    }

    public void setContrato(ContratoDeServicio contrato) {
        this.contrato = contrato;
    }
    Cliente cliente;
    DatosDeVehiculo datosDeVehiculo;
    @FXML
    private JFXTextField txtAño;
    @FXML
    private JFXCheckBox chAdicional;
    @FXML
    private JFXTextField txtMatricula;

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
        nuevo();
    }

    public void inicializarTablaDetalle() {

        try {

            listaDatosVehiculo.clear();
            listaDetalleContrato.clear();

            tbDetalleContrato.setItems(listaDetalleContrato);

            tbcEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));
            tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            tbcModalidad.setCellValueFactory(new PropertyValueFactory<>("modalidad"));
            tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tbcPrecioAbonado.setCellValueFactory(new PropertyValueFactory<>("precioAcordado"));
            tbcPrecioAcordado.setCellValueFactory(new PropertyValueFactory<>("precioAcordado"));
            tbcObservacion.setCellValueFactory(new PropertyValueFactory<>("observacion"));
            tbcFechaDesde.setCellValueFactory(new PropertyValueFactory<>("fechaDesde"));
            tbcFechaHasta.setCellValueFactory(new PropertyValueFactory<>("fechaHasta"));
            tbcFechaUltimoPagoDesde.setCellValueFactory(new PropertyValueFactory<>("fechaUltimoPagoDesde"));
            tbcFechaUltimoPagoHasta.setCellValueFactory(new PropertyValueFactory<>("fechaUltimoPagoHasta"));
            tbcImei.setCellValueFactory(new PropertyValueFactory<>("numeroImei"));
            tbcSim.setCellValueFactory(new PropertyValueFactory<>("numeroSim"));

            tbcHabilitado.setCellValueFactory(new PropertyValueFactory<>("habilitado"));

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

            tbcModalidad.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getModalidad() != null) {
                            property.setValue(cellData.getValue().getModalidad().getNombre());
                        } else {
                            property.setValue("n/a");
                        }
                        return property;
                    });

            tbcAdicionalArticulo.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getAdicional()) {
                            property.setValue("SI");
                        } else {
                            property.setValue("NO");
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
////                                if (txtConcepto.getText().isEmpty()) {
////                                    txtConcepto.setText("Tiene que digitar la razon ");
////                                 
////                                    txtConcepto.requestFocus();
////                                    return;
////                                }
//                                if (item.getHabilitado()) {
//
//                                    item.setHabilitado(false);
//                                    btnHabilitar.setText("NO");
//
//                                    item.setEstado("Desintalado");
//                                    item.setFechaDesinstalacion(new Date());
//                                    item.setUsuario(VariablesGlobales.USUARIO.getCodigo());
//                                    item.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());
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
//                                    item.setEstado("Instalado");
//                                    item.setFechaInstalacion(new Date());
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
            tbcChasis.setCellValueFactory(new PropertyValueFactory<>("chasis"));
            tbcColor.setCellValueFactory(new PropertyValueFactory<>("color"));
            tbcTipoVehiculo.setCellValueFactory(new PropertyValueFactory<>("tipoDeVehiculo"));

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

            tbcServicio.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getServicio() != null) {
                            property.setValue(cellData.getValue()
                                    .getServicio().getEquipo().getNumero().toString());
                        }
                        return property;
                    });

            tbcAdicional.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getAdicional()) {
                            property.setValue("SI");
                        } else {
                            property.setValue("NO");
                        }
                        return property;
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

        listaFrecuenciaDePago.addAll(ManejoFrecuenciaDePago.getInstancia().getLista());
        listaFrecuenciaDePagoDet.addAll(ManejoFrecuenciaDePago.getInstancia().getLista());
        listaEjecutivoDeVenta.addAll(ManejoEjecutivoDeVenta.getInstancia().getLista());
        listaModadlidad.addAll(ManejoModalidad.getInstancia().getLista());
        listatIPOvEHICULO.addAll(ManejoTipoDeVehiculo.getInstancia().getLista());
        listaEstadoContrato.addAll(ManejoEstadoContrato.getInstancia().getLista());
        listaPlanDeServicio.addAll(ManejoPlanDeServicio.getInstancia().getLista());
        listaTipoDeServicio.addAll(ManejoTipoDeServicio.getInstancia().getLista());
        listaSimCard.addAll(ManejoSimCard.getInstancia().getLista(true));

        cbSimCard.setConverter(new StringConverter<RegistroDeSim>() {

            @Override
            public String toString(RegistroDeSim ejecutivo) {
                return ejecutivo.getNumero();
            }

            @Override
            public RegistroDeSim fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbImei.setConverter(new StringConverter<RegistroDeImei>() {

            @Override
            public String toString(RegistroDeImei ejecutivo) {
                return ejecutivo.getNumero();
            }

            @Override
            public RegistroDeImei fromString(String string) {
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

        cbModalidad.setConverter(new StringConverter<ModalidadEquipo>() {

            @Override
            public String toString(ModalidadEquipo modadlidad) {
                return modadlidad.getNombre();
            }

            @Override
            public ModalidadEquipo fromString(String string) {
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

        cbEjecutivoDeVenta.setItems(listaEjecutivoDeVenta);
        cbFrecuenciaDePago.setItems(listaFrecuenciaDePago);
        cbFrecuenciaDePagoDet.setItems(listaFrecuenciaDePagoDet);
        cbModalidad.setItems(listaModadlidad);
        cbTipoVehiculo.setItems(listatIPOvEHICULO);
        cbEstadoContrato.setItems(listaEstadoContrato);
        cbPlanDeServicio.setItems(listaPlanDeServicio);
        cbTipoDeServicio.setItems(listaTipoDeServicio);
        cbSimCard.setItems(listaSimCard);
        cbImei.setItems(listaImeis);

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
        articuloController.setOrigen(1);//el valor  3 es para  buscar solo articulos de consumo

        articuloController.llenarCampo();

        utiles.ClaseUtil.getStageModal(root);

        System.out.println("ESPERANDOOO!!!");

        if (!(articuloController.getArticulo() == null)) {

            setArticulo(articuloController.getArticulo());
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
                cbSimCard.setDisable(false);
//                cbSimCard.getSelectionModel().select(g);
                txtImei.setText(getArticulo().getNumeroImei());
                txtArticulo.setText(getArticulo().getDescripcion() + " IMEI " + getArticulo().getNumeroImei());

            }

            txtCantidad.requestFocus();

        }

    }

    @FXML
    private void txtCantidadKeyPressed(KeyEvent event
    ) {
    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event
    ) {

        if (getArticulo().getTipoArticulo().getCodigo() == 3) {
            agregarServicio();
        } else {
            agregarArticulo();
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
//                ClaseUtil.mensaje("Tiene que digitar el año del vehiculo ");
//                txtAño.requestFocus();
                return;
            }

            if (txtChasis.getText().isEmpty()) {

                txtChasis.setText("na");
//                ClaseUtil.mensaje("Tiene que digitar un chasis ");
//                txtChasis.requestFocus();
//                return;
            }

            if (txtColor.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar un color ");
                txtColor.requestFocus();
                return;
            }

            if (txtMatricula.getText().isEmpty()) {
                txtMatricula.setText("na");
//
//                ClaseUtil.mensaje("Tiene que digitar una matricula ");
//                txtMatricula.requestFocus();
//                return;
            }

            if (tbDetalleContrato.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar un equipo");
                return;
            }

            datosDeVehiculo = new DatosDeVehiculo();
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
            datosDeVehiculo.setServicio(tbDetalleContrato.getSelectionModel().getSelectedItem());
            datosDeVehiculo.setFechaInstalacion(new Date());
            datosDeVehiculo.setUsuario(VariablesGlobales.USUARIO.getCodigo());
            datosDeVehiculo.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());            

            datosDeVehiculo.setVehiculo(cbTipoVehiculo.getSelectionModel().getSelectedItem().getNombre()
                    + " " + txtMarca.getText() + " " + txtModelo.getText() + " " + txtColor.getText() + " "
                    + txtplaca.getText() + " " + txtChasis.getText()
            );

            listaDatosVehiculo.add(datosDeVehiculo);

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
    private void btnGuardarActionEvent(ActionEvent event) {

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

        if (cbEjecutivoDeVenta.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje(" El ejecutivo de venta esta vacio ");
            cbEjecutivoDeVenta.requestFocus();
            return;
        }

        if (cbFrecuenciaDePago.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje(" La frecuencia de pago esta vacia ");
            cbFrecuenciaDePago.requestFocus();
            return;
        }

        if (listaDetalleContrato.size() <= 0) {
            ClaseUtil.mensaje(" El contrato no tiene detalle ");

            return;
        }

        if (listaDatosVehiculo.size() <= 0 && editar == false) {
            ClaseUtil.mensaje(" El contrato no tiene vehiculo agregado  ");

            return;
        }

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

                    if (!(contratoDb == null)) {

                        for (DetalleContratoDeServicio detcontrato : listaDetalleContrato) {

                            if (detcontrato.getEquipo().getTipoArticulo().getCodigo() != 3 && !(detcontrato.getEquipo().getNumeroSim() == null)) {

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
                        });
                    } else {

                        ClaseUtil.mensaje("Hubo un error actualizando el contrato");
                        return;
                    }

                } else {

                    contratoDb = ManejoContratoDeServicio.getInstancia().salvar(contrato);

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

                limpiar();
                limpiarDatosVehiculo();
                limpiarDetalleContrato();
                ClaseUtil.mensaje("Contrato guardado exitosamente");
                nuevo();

            } else {
                ClaseUtil.mensaje("No hay una secuencia configurada para los contrato");
            }

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error guardando el contrato");
            e.printStackTrace();
        }

    }

    private void nuevo() {

        contrato = new ContratoDeServicio();
        det = null;
        txtNumContrato.setText(ManejoContratoDeServicio.getInstancia().getNumero().toString());
        cbTipoDeServicio.setDisable(true);
        editarDetalle = false;
        editar = false;
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
        txtTotal.clear();

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
        listaDatosVehiculo.clear();

        txtNumContrato.setText(getContrato().getNumeroDeContrato());
        txtCanalDeVenta.setText(getContrato().getCanalDeVenta());
        txtCuentaDeMonitereo.setText(getContrato().getCuentaDeMonitoreo());
        txtRepresentante.setText(getContrato().getRepresentante());
        txtCedulaRepresentante.setText(getContrato().getCedulaRepresentante());
        cbEjecutivoDeVenta.getSelectionModel().select(getContrato().getEjecutivoDeVenta());
        cbFrecuenciaDePago.getSelectionModel().select(getContrato().getFrecuenciaDePago());
        cbPlanDeServicio.getSelectionModel().select(getContrato().getPlanDeServicio());
        cbEstadoContrato.getSelectionModel().select(getContrato().getEstado());
        txtTotal.setText(Double.toString(getContrato().getTotalAPagar()));

        dpFecha.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getContrato().getFecha()));
        dpFechaVencimiento.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getContrato().getFechaVencimiento()));
        setCliente(getContrato().getCliente());

        txtCliente.setText(getCliente().getNombre() + " " + getCliente().getApellido());

//        listaDatosVehiculo.addAll(ManejoContratoDeServicio.getInstancia().getDatosVehiculoo(getContrato().getCodigo()));
        listaDetalleContrato.addAll(ManejoContratoDeServicio.getInstancia().getDetalleContrato(getContrato().getCodigo()));

        listaDatosVehiculo.clear();
        for (DetalleContratoDeServicio det : listaDetalleContrato) {

            if (det.getEquipo().getTipoArticulo().getCodigo() == 3) {

                listaDatosVehiculo.addAll(ManejoContratoDeServicio
                        .getInstancia().getDatosVehiculo(det.getCodigo()));
            }

        }

        txtSubTotal.setText(getSubTotal().toString());
        txtItbis.setText(getItbis().toString());
        txtTotal.setText(getTotal().toString());

    }

    private Double getSubTotal() {

        Double subTotal = 0.00;

        for (DetalleContratoDeServicio det : listaDetalleContrato) {

//            subTotal += det.getCantidad() * det.getPrecio();
            if (det.getHabilitado()) {
                subTotal += det.getSubTotal();
            }

        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotal() {

        Double total = 0.00;

        for (DetalleContratoDeServicio det : listaDetalleContrato) {

//            total += (det.getCantidad() * det.getPrecio()) + det.getItbis();
            if (det.getHabilitado()) {

                total += det.getTotal();
            }

        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getItbis() {

        Double itbis = 0.00;

        for (DetalleContratoDeServicio det : listaDetalleContrato) {

            if (det.getHabilitado()) {
                itbis += det.getItbis();
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
                Double frecuencia = cantidadFecuenciaPago;

                int cantidadGPS = Integer.parseInt(txtCantidad.getText().isEmpty() ? "0"
                        : txtCantidad.getText()
                );

                if (cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem().getCodigo() == 1) {

                    cantidadFecuenciaPago *= 12;

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

                        desc = frecuencia.intValue() + " " + cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem().getDescripcion()
                                + " POR SERVICIO DE " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
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

            if (txtArticulo.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que seleccionar un articulo");
                return;
            }

            if (cbModalidad.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar una modalidad");
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
                ClaseUtil.mensaje("Tiene que digitar la cantidaden funcion de la frecuencia ");
                txtCantidadFrecuencia.requestFocus();
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

            if (cbPlanDeServicio.getSelectionModel().getSelectedItem().getCodigo() == 1) {

                if (txtPrecioRenovacion.getText().isEmpty()) {
                    ClaseUtil.mensaje("Tiene que digitar el precio de renovacion ");
                    txtPrecioRenovacion.requestFocus();
                    return;
                }
            }

            if (dpFechaUltimoPagoDesde.getValue() == null && dpFechaUltimoPagoHasta.getValue() == null) {
                contrato.setNuevo(true);
                Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("ESTE CONTRATO ES NUEVO");

                if (ok.get() == ButtonType.NO) {

                    ClaseUtil.mensaje("Tiene que seleccionar la fecha ultimo pago desde  y hasta ");
                    dpFechaUltimoPagoDesde.requestFocus();
                    return;

                }
            } else {
                contrato.setNuevo(false);
            }

            System.out.println("det " + det);
            if (editarDetalle == false) {
                det = new DetalleContratoDeServicio();
            }

            det.setHabilitado(true);

            Unidad un = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(getArticulo().getCodigo()).getUnidad();

            if (un == null) {

                ClaseUtil.mensaje("Este articulo no tiene una unidad configurada");
                return;
            }

            det.setPrecioPagado(ManejoListaDePrecio.getInstancia().getPrecioVentaDeArticulo(getArticulo().getCodigo(), un.getCodigo()));
            det.setContratoDeServicio(contrato);
            det.setEquipo(getArticulo());
            det.setCantidad(Integer.parseInt(txtCantidad.getText()));
            det.setModalidad(cbModalidad.getValue());
            det.setMensualidad(0);
            det.setPrecioAcordado(det.getPrecioPagado());
            det.setObservacion(txtConcepto.getText().isEmpty() ? "n/a" : txtConcepto.getText());

            det.setDescripcion(txtDescripcion.getText());
            det.setSubTotal(det.getCantidad() * det.getPrecioPagado());
            det.setFechaDesde(ClaseUtil.asDate(dpFechaDesde.getValue()));
            det.setFechaHasta(ClaseUtil.asDate(dpFechaHasta.getValue()));

            if (!(dpFechaUltimoPagoDesde.getValue() == null && dpFechaUltimoPagoHasta.getValue() == null)) {

                det.setFechaUltimoPagoDesde(ClaseUtil.asDate(dpFechaUltimoPagoDesde.getValue()));
                det.setFechaUltimoPagoHasta(ClaseUtil.asDate(dpFechaUltimoPagoHasta.getValue()));
            }

            /*else{
                det.setFacturado(false);
            } */
            det.setCantidadFrecuenciaDePago(Double.parseDouble(txtCantidadFrecuencia.getText()));
            det.setFrecuenciaDePago(cbFrecuenciaDePagoDet.getSelectionModel().getSelectedItem());
            det.setAdicional(chAdicionalArticulo.isSelected());
            det.setNumeroImei("na");
            det.setNumeroSim("na");
            det.setEstado("Instalado");
            det.setFechaInstalacion(new Date());
            det.setUsuario(VariablesGlobales.USUARIO.getCodigo());
            det.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());

            if (cbPlanDeServicio.getSelectionModel().getSelectedItem().getCodigo() == 1) {
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

            if (editarDetalle == false) {
                listaDetalleContrato.add(det);
            }

            tbDetalleContrato.refresh();

            txtSubTotal.setText(getSubTotal().toString());
            txtItbis.setText(getItbis().toString());
            txtTotal.setText(getTotal().toString());
            editarDetalle = false;

            limpiarDetalleContrato();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void agregarArticulo() {

        Double valorItbis = 0.00;
        try {

            if (txtArticulo.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que seleccionar un articulo");
                return;
            }

            if (txtCantidad.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar la cantidad ");
                txtCantidad.requestFocus();
                return;
            }

            if (txtImei.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar el IMEI ");
                txtImei.requestFocus();
                return;
            }

            if (cbSimCard.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que digitar el SIM ");
                cbSimCard.requestFocus();
                return;
            }

            System.out.println("det " + det);
            if (editarDetalle == false) {
                det = new DetalleContratoDeServicio();
            }

            det.setHabilitado(true);

            Unidad un = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(getArticulo().getCodigo()).getUnidad();

            if (un == null) {

                ClaseUtil.mensaje("Este articulo no tiene una unidad configurada");
                return;
            }

            if (dpFechaUltimoPagoDesde.getValue() == null && dpFechaUltimoPagoHasta.getValue() == null) {
                contrato.setNuevo(true);
                Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("ESTE CONTRATO ES NUEVO");

                if (ok.get() == ButtonType.OK || ok.get() == ButtonType.YES) {

                    Double existencia = ManejoExistenciaArticulo
                            .getInstancia()
                            .getExistenciaArticulos(getArticulo().getCodigo());

                    if (existencia <= 0.0 && !(getArticulo().getTipoArticulo().getCodigo() == 3)) {

                        ClaseUtil.mensaje("Este articulo no tiene existencia ");

                        return;
                    }

                }
            } else {

                contrato.setNuevo(false);
            }

            det.setPrecioPagado(ManejoListaDePrecio.getInstancia().getPrecioVentaDeArticulo(getArticulo().getCodigo(), un.getCodigo()));
            det.setContratoDeServicio(contrato);
            det.setEquipo(getArticulo());
            det.setCantidad(Integer.parseInt(txtCantidad.getText()));
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
            det.setNumeroImei(txtImei.getText());
            det.setNumeroSim(cbSimCard.getSelectionModel().getSelectedItem().getNumero());
            det.setSim(cbSimCard.getSelectionModel().getSelectedItem());
            det.setEstado("Instalado");

            if (cbImei.getSelectionModel().getSelectedIndex() == -1) {

//                det.setImei(new RegistroDeImei(1));
//                ClaseUtil.mensaje("Tiene que seleccionar el IMEI ");
//                cbImei.requestFocus();
//                return;
            } else {

                det.setImei(cbImei.getSelectionModel().getSelectedItem());
            }

            det.setFechaInstalacion(new Date());
            det.setUsuario(VariablesGlobales.USUARIO.getCodigo());
            det.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());

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

            if (editarDetalle == false) {
                listaDetalleContrato.add(det);
            }

            tbDetalleContrato.refresh();

            txtSubTotal.setText(getSubTotal().toString());
            txtItbis.setText(getItbis().toString());
            txtTotal.setText(getTotal().toString());

            txtArticulo.clear();
            cbModalidad.getSelectionModel().select(-1);
            txtCantidad.clear();
            txtImei.clear();
            cbSimCard.getSelectionModel().select(-1);
            txtDescripcion.clear();
            txtConcepto.clear();
            dpFechaDesde.setValue(null);
            dpFechaHasta.setValue(null);
            chAdicionalArticulo.setSelected(false);
            cbFrecuenciaDePagoDet.getSelectionModel().select(-1);
            txtCantidadFrecuencia.clear();
            det = null;
            editarDetalle = false;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void tbDetalleContratoMouseClicked(MouseEvent event) {

        if (!(tbDetalleContrato.getSelectionModel().getSelectedIndex() == -1)) {

            int servicio = 0;
            listaDatosVehiculo.clear();
            limpiarDetalleContrato();
            btnAgregar.setText("Actualizar");

            det = tbDetalleContrato.getSelectionModel().getSelectedItem();
            setArticulo(det.getEquipo());
            editarDetalle = true;

            if (det.getEquipo().getTipoArticulo().getCodigo() == 3) {

                txtCantidadFrecuencia.setText(Double.toString(det.getCantidadFrecuenciaDePago()));

                cbFrecuenciaDePagoDet.getSelectionModel().select(det.getFrecuenciaDePago());
                cbModalidad.getSelectionModel().select(det.getModalidad());
                cbTipoDeServicio.getSelectionModel().select(det.getTipoDeServicio());
                cbTipoDeServicio.setDisable(false);
                dpFechaDesde.setValue(ClaseUtil.convertToLocalDateViaMilisecond(det.getFechaDesde()));
                dpFechaHasta.setValue(ClaseUtil.convertToLocalDateViaMilisecond(det.getFechaHasta()));
                if (!(det.getFechaUltimoPagoDesde() == null)) {
                    dpFechaUltimoPagoDesde.setValue(ClaseUtil.convertToLocalDateViaMilisecond(det.getFechaUltimoPagoDesde()));
                    dpFechaUltimoPagoHasta.setValue(ClaseUtil.convertToLocalDateViaMilisecond(det.getFechaUltimoPagoHasta()));
                }

            } else {
//                cbSimCard.getSelectionModel().select(det.getNumeroSim());
                txtImei.setText(det.getNumeroImei());
            }

//            txtSin.setText(det.getNumeroSim());
            txtImei.setText(det.getNumeroImei());
            txtArticulo.setText(det.getEquipo().getDescripcion());
            txtDescripcion.setText(det.getDescripcion());
            txtConcepto.setText(det.getObservacion());
            txtPrecioRenovacion.setText(Double.toString(det.getPrecioRenovacion() == null ? 0
                    : det.getPrecioRenovacion()));
            txtCantidad.setText(Integer.toString(det.getCantidad()));

            System.out.println("tbDetalleContrato.getSelectionModel().getSelectedItem() " + tbDetalleContrato.getSelectionModel().getSelectedItem());

            if (editar) {
                servicio = tbDetalleContrato.getSelectionModel().getSelectedItem().getCodigo();
            }

            listaDatosVehiculo.addAll(ManejoContratoDeServicio.getInstancia().getDatosVehiculo(servicio));

            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(1);
            }
        }

    }

    @FXML
    private void dpFechaUltimoPagoDesdeActionEvent(ActionEvent event) {
    }

    @FXML
    private void cbModalidadActionEvent(ActionEvent event) {

        if (!(cbModalidad.getSelectionModel().getSelectedIndex() == -1)) {

            txtCantidadFrecuencia.requestFocus();

        }
    }

    private void limpiarDetalleContrato() {

        txtArticulo.clear();
        cbModalidad.getSelectionModel().select(-1);
        txtCantidad.clear();
        txtDescripcion.clear();
        txtConcepto.clear();
        txtImei.clear();
        cbSimCard.getSelectionModel().select(-1);
        txtPrecioRenovacion.clear();
        dpFechaDesde.setValue(null);
        dpFechaHasta.setValue(null);
        dpFechaUltimoPagoDesde.setValue(null);
        dpFechaUltimoPagoHasta.setValue(null);
        chAdicionalArticulo.setSelected(false);
        cbFrecuenciaDePagoDet.getSelectionModel().select(-1);
        txtCantidadFrecuencia.clear();
        cbTipoDeServicio.getSelectionModel().select(-1);
        btnAgregar.setText("Agregar");
        det = null;
    }

    @FXML
    private void ActionEventcbImei(ActionEvent event) {

        if (!(cbImei.getSelectionModel().getSelectedIndex() == -1)) {

            txtImei.setText(cbImei.getSelectionModel().getSelectedItem().getNumero());
        } else {

            txtImei.clear();
        }
    }

}
