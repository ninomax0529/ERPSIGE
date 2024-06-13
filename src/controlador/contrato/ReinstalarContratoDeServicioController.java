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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import manejo.contrato.ManejoContratoDeServicio;
import manejo.contrato.ManejoDatosDeVehiculo;
import manejo.contrato.ManejoEstadoContrato;
import manejo.contrato.ManejoFrecuenciaDePago;
import manejo.contrato.ManejoInstalador;
import manejo.contrato.ManejoModalidad;
import manejo.contrato.ManejoPlanDeServicio;
import manejo.contrato.ManejoSimCard;
import manejo.contrato.ManejoTipoDeServicio;
import manejo.contrato.ManejoTipoDeVehiculo;
import manejo.ejecutivoDeVenta.ManejoEjecutivoDeVenta;
import modelo.Articulo;
import modelo.Cliente;
import modelo.ContratoDeServicio;
import modelo.DatosDeVehiculo;
import modelo.DetalleContratoDeServicio;
import modelo.EjecutivoDeVenta;
import modelo.EstadoContrato;
import modelo.FrecuenciaDePago;
import modelo.Instalador;
import modelo.ModalidadEquipo;
import modelo.PlanDeServicio;
import modelo.RegistroDeImei;
import modelo.RegistroDeSim;
import modelo.TipoDeServicio;
import modelo.TipoVehiculo;
import utiles.ClaseUtil;
import utiles.FormatNum;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ReinstalarContratoDeServicioController implements Initializable {

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
    private TableColumn<DetalleContratoDeServicio, String> tbcDescripcion;
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
    ObservableList<Instalador> listaInstaladores = FXCollections.observableArrayList();

    DetalleContratoDeServicio det;
    ContratoDeServicio contrato;
    @FXML
    private JFXDatePicker dpFechaVencimiento;
    Boolean editarDetalle = false;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private JFXComboBox<Instalador> cbInstalador;
    @FXML
    private JFXDatePicker dpFechaReintalacion;

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

                            hbox.getChildren().add(btnHabilitar);

                            hbox.setAlignment(Pos.CENTER);

                            btnHabilitar.setOnMouseClicked((event) -> {

                                if (item.getHabilitado()) {

                                    Optional<ButtonType> op = ClaseUtil.confirmarMensaje(" Seguro que quiere Desintalar este Vehiculo ");

                                    if (op.get() == ButtonType.OK || op.get() == ButtonType.YES) {

                                        if (txtDescripcion.getText().isEmpty()) {
                                            ClaseUtil.mensaje(" Tiene que digitar la razon de la desintalacion ");

                                            return;
                                        }

                                        item.setHabilitado(false);
                                        item.setRazonDesinstalacion(txtDescripcion.getText());
                                        btnHabilitar.setText("NO");
                                        item.setEstado("Desinstalado");
                                        item.getServicio().setEstado("Reinstalado");
//                                        item.getServicio().setFechaReinstalacion(new Date());

                                        item.setFechaDesinstalacion(new Date());
                                        item.setUsuario(VariablesGlobales.USUARIO.getCodigo());
                                        item.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());

                                        btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                                + "    -fx-border-color: -fx-secondary;\n"
                                                + "    -fx-border-radius: 15px;\n"
                                                + "    -fx-background-radius: 15px;\n"
                                                + "    -fx-b-radius: 10px;\n"
                                                + " -fx-text-fill: white;"
                                                + "    -fx-font-size: 12pt;");

                                    }

                                } else {

                                    ClaseUtil.mensaje(" Este articulo ya esta desintalacion ");

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

        cbEjecutivoDeVenta.setItems(listaEjecutivoDeVenta);
        cbFrecuenciaDePago.setItems(listaFrecuenciaDePago);
        cbTipoVehiculo.setItems(listatIPOvEHICULO);
        cbEstadoContrato.setItems(listaEstadoContrato);
        cbPlanDeServicio.setItems(listaPlanDeServicio);
        cbInstalador.setItems(listaInstaladores);

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
    private void tbDetalleReciboMouseClicked(MouseEvent event) {
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

            if (cbInstalador.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje(" Tiene que seleccionar un Reinstalador ");
                cbInstalador.requestFocus();
                return;
            }

            if (dpFechaReintalacion.getValue() == null) {
                ClaseUtil.mensaje(" Tiene que seleccionar  la fecha de reinstalacion ");
                dpFechaReintalacion.requestFocus();
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

            tbDetalleContrato.getSelectionModel().getSelectedItem().setReinstaladoPor(cbInstalador.getSelectionModel().getSelectedItem().getCodigo());
            tbDetalleContrato.getSelectionModel().getSelectedItem().setNombreReinstalador(cbInstalador.getSelectionModel().getSelectedItem().getNombre());
            tbDetalleContrato.getSelectionModel().getSelectedItem().setFechaReinstalacion(ClaseUtil.asDate(dpFechaReintalacion.getValue()));
            tbDetalleContrato.getSelectionModel().getSelectedItem().setRazonDesinstalacion(txtDescripcion.getText());

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

        if (listaDetalleContrato.size() <= 0) {
            ClaseUtil.mensaje(" El contrato no tiene detalle ");

            return;
        }

        if (listaDatosVehiculo.size() <= 0) {
            ClaseUtil.mensaje(" El contrato no tiene vehiculo agregado  ");

            return;
        }

        ContratoDeServicio contratoDb = null;

        contrato.setDetalleContratoDeServicioCollection(listaDetalleContrato);

        contratoDb = ManejoContratoDeServicio.getInstancia().actualizar(contrato);

        if (!(contratoDb == null)) {

            System.out.println("editando listaDatosVehiculo " + listaDatosVehiculo.size());

            listaDatosVehiculo.forEach((dvh) -> {
                ManejoDatosDeVehiculo.getInstancia().actualizar(dvh);
            });

            ClaseUtil.mensaje(" Reinstalacion realizada exitosamente ");
            limpiar();
            limpiarDatosVehiculo();

        } else {

            ClaseUtil.mensaje("Hubo un error actualizando el contrato");
            return;
        }

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
        dpFechaReintalacion.setValue(null);
        txtTotal.clear();
        txtSubTotal.clear();
        txtItbis.clear();
        listaImeis.clear();
        listaSimCard.clear();
        cbInstalador.getSelectionModel().select(-1);
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
        txtDescripcion.clear();

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
    private void dpFechaActionEvent(ActionEvent event) {

        Date fechaVencimiento, fecha;

        if (!(dpFecha.getValue() == null)) {

            fecha = ClaseUtil.asDate(dpFecha.getValue());

            fechaVencimiento = ClaseUtil.Fechadiadespues(fecha, 365);

            dpFechaVencimiento.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaVencimiento));

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
    private void tbDetalleContratoMouseClicked(MouseEvent event) {

        if (!(tbDetalleContrato.getSelectionModel().getSelectedIndex() == -1)) {

            int servicio = 0;
            listaDatosVehiculo.clear();

            det = tbDetalleContrato.getSelectionModel().getSelectedItem();
            setArticulo(det.getEquipo());
            editarDetalle = true;

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

}
