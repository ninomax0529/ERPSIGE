/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.venta.cliente.FXMLBusClienterController;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
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
import manejo.contrato.ManejoEstadoContrato;
import manejo.contrato.ManejoFrecuenciaDePago;
import manejo.contrato.ManejoPlanDeServicio;
import modelo.Articulo;
import modelo.Cliente;
import modelo.ContratoDeServicio;
import modelo.DatosDeVehiculo;
import modelo.DetalleContratoDeServicio;
import modelo.EstadoContrato;
import modelo.FrecuenciaDePago;
import modelo.PlanDeServicio;
import utiles.ClaseUtil;
import utiles.FormatNum;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class SuspensionContratoDeServicioController implements Initializable {

    @FXML
    private TextArea txtrRazonSupEquipo;
    @FXML
    private ProgressBar progresBar;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcServicioVehiculo;

    @FXML
    private TableColumn<DatosDeVehiculo, DatosDeVehiculo> tbcVehiculoHabilitado;

    private TabPane tabpaneEquipos;

    @FXML
    private JFXComboBox<EstadoContrato> cbEstadoContrato;
    @FXML
    private JFXTextField txtNumContrato;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtCliente;
    @FXML
    private JFXComboBox<FrecuenciaDePago> cbFrecuenciaDePago;
    @FXML
    private JFXTextField txtRepresentante;
    @FXML
    private JFXTextField txtCedulaRepresentante;
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
    private TableColumn<DetalleContratoDeServicio, String> tbcInstalador;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcEjecutivoDeVenta;

    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcDescripcion;
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

    @FXML
    private JFXComboBox<PlanDeServicio> cbPlanDeServicio;

    ObservableList<FrecuenciaDePago> listaFrecuenciaDePago = FXCollections.observableArrayList();
    ObservableList<FrecuenciaDePago> listaFrecuenciaDePagoDet = FXCollections.observableArrayList();
    ObservableList<DetalleContratoDeServicio> listaDetalleContrato = FXCollections.observableArrayList();
    ObservableList<DetalleContratoDeServicio> listaDetEquipos = FXCollections.observableArrayList();
    ObservableList<DetalleContratoDeServicio> listaDetEquipoYServicio = FXCollections.observableArrayList();
    ObservableList<DatosDeVehiculo> listaDatosVehiculo = FXCollections.observableArrayList();

    ObservableList<EstadoContrato> listaEstadoContrato = FXCollections.observableArrayList();
    ObservableList<PlanDeServicio> listaPlanDeServicio = FXCollections.observableArrayList();

    DetalleContratoDeServicio det;
    DatosDeVehiculo datosDeVehiculo;
    ContratoDeServicio contrato;

    @FXML
    private JFXDatePicker dpFechaVencimiento;
    Boolean editarDetalleServicio = false;
    Boolean editarDetalleEquipo = false;
    Boolean editarVehiculo = false;

    public ContratoDeServicio getContrato() {
        return contrato;
    }

    public void setContrato(ContratoDeServicio contrato) {
        this.contrato = contrato;
    }
    Cliente cliente;
//    DatosDeVehiculo datosDeVehiculo;

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

    }

    public void inicializarTablaDetalle() {

        try {

            listaDatosVehiculo.clear();
            listaDetalleContrato.clear();

            tbDetalleContrato.setItems(listaDetalleContrato);

            tbcEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));
            tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tbcPrecioAbonado.setCellValueFactory(new PropertyValueFactory<>("precioAcordado"));
            tbcFechaDesde.setCellValueFactory(new PropertyValueFactory<>("fechaDesde"));
            tbcFechaHasta.setCellValueFactory(new PropertyValueFactory<>("fechaHasta"));
            tbcFechaUltimoPagoDesde.setCellValueFactory(new PropertyValueFactory<>("fechaUltimoPagoDesde"));
            tbcFechaUltimoPagoHasta.setCellValueFactory(new PropertyValueFactory<>("fechaUltimoPagoHasta"));
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

                            btnHabilitar.setOnMouseClicked((event) -> {

                                if (txtrRazonSupEquipo.getText().isEmpty()) {
                                    ClaseUtil.mensaje("Tiene que escribir  la razon primero");
                                    return;
                                }
                                
                                String msg = " Seguro que quiere Suspender este servicio ";
                                if (item.getHabilitado() == false) {

                                    msg = " Seguro que quiere Habilitar este servicio ";
                                }

                                Optional<ButtonType> op = ClaseUtil.confirmarMensaje(msg);

                                if (!(op.get() == ButtonType.OK || op.get() == ButtonType.YES)) {

                                    return;
                                }

                                if (item.getHabilitado()) {
//

                                    item.setHabilitado(false);
                                    btnHabilitar.setText("NO");
                                    item.setRazonActualizacion(txtrRazonSupEquipo.getText());
                                    item.setFechaSuspendido(new Date());
                                    item.setEstado("Suspendido");
                                    item.setUsuario(VariablesGlobales.USUARIO.getCodigo());
                                    item.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());

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

                                    item.setEstado("Habilitado");
                                    item.setRazonActualizacion(txtrRazonSupEquipo.getText());
                                    item.setUsuario(VariablesGlobales.USUARIO.getCodigo());
                                    item.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());
                                    item.setFechaHabilitado(new Date());

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTablaEquipos() {

        try {

            listaDatosVehiculo.clear();
            listaDetEquipos.clear();

            tbDetalleContrato1.setItems(listaDetEquipos);

            tbcEquipo1.setCellValueFactory(new PropertyValueFactory<>("equipo"));
            tbcCantidad1.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            tbcDescripcion1.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tbcPrecioAbonado1.setCellValueFactory(new PropertyValueFactory<>("precioAcordado"));
            tbcFechaDesde1.setCellValueFactory(new PropertyValueFactory<>("fechaDesde"));
            tbcFechaHasta1.setCellValueFactory(new PropertyValueFactory<>("fechaHasta"));
            tbcHabilitado1.setCellValueFactory(new PropertyValueFactory<>("habilitado"));
            tbcImei1.setCellValueFactory(new PropertyValueFactory<>("numeroImei"));
            tbcSim1.setCellValueFactory(new PropertyValueFactory<>("numeroSim"));

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

                            btnHabilitar.setOnMouseClicked((event) -> {

                                if (txtrRazonSupEquipo.getText().isEmpty()) {
                                    ClaseUtil.mensaje("Tiene que escribir la razon primero");
                                    return;
                                }
                                String msg = " Seguro que quiere Suspender este equipo ";
                                if (item.getHabilitado() == false) {

                                    msg = " Seguro que quiere Habilitar este equipo ";
                                } else {
                                    item.setEstado("Suspendido");
                                }

                                Optional<ButtonType> op = ClaseUtil.confirmarMensaje(msg);

                                if (!(op.get() == ButtonType.OK || op.get() == ButtonType.YES)) {

                                    return;
                                }

                                if (item.getHabilitado()) {

                                    item.setHabilitado(false);
                                    btnHabilitar.setText("NO");
                                    item.getSim().setDisponible(false);
                                    item.getSim().setDuplicado(true);
                                    item.getSim().setFechaParaDuplicado(new Date());

                                    item.setEstado("Suspendido");
                                    item.setRazonActualizacion(txtrRazonSupEquipo.getText());
                                    item.setFechaSuspendido(new Date());
                                    item.setUsuario(VariablesGlobales.USUARIO.getCodigo());
                                    item.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());

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
                                    item.getSim().setDisponible(true);
                                    item.getSim().setFechaParaDuplicado(null);

                                    item.setEstado("Habilitado");
                                    item.setRazonActualizacion(txtrRazonSupEquipo.getText());
                                    item.setFechaHabilitado(new Date());
                                    item.setUsuario(VariablesGlobales.USUARIO.getCodigo());
                                    item.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());

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

                                String msg = " Seguro que quiere Suspender este vehiculo ";

                                if (item.getHabilitado() == false) {

                                    msg = " Seguro que quiere Habilitar este vehiculo ";
                                }

                                Optional<ButtonType> op = ClaseUtil.confirmarMensaje(msg);

                                if (!(op.get() == ButtonType.OK || op.get() == ButtonType.YES)) {

                                    return;
                                }

                                if (item.getHabilitado()) {

                                    item.setHabilitado(false);
                                    item.setRazonActualizacion(txtrRazonSupEquipo.getText());
                                    item.setUsuario(VariablesGlobales.USUARIO.getCodigo());
                                    item.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());
                                    btnHabilitar.setText("NO");
                                    item.setFechaSuspendido(new Date());

                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 15px;\n"
                                            + "    -fx-b-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 12pt;");

                                } else {

                                    item.setHabilitado(true);
                                    item.setRazonActualizacion(txtrRazonSupEquipo.getText());
                                    item.setUsuario(VariablesGlobales.USUARIO.getCodigo());
                                    item.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());
                                    btnHabilitar.setText("SI");
                                    item.setFechaHabilitado(new Date());

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
        listaEstadoContrato.addAll(ManejoEstadoContrato.getInstancia().getLista());
        listaPlanDeServicio.addAll(ManejoPlanDeServicio.getInstancia().getLista());

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

        cbFrecuenciaDePago.setItems(listaFrecuenciaDePago);

        cbEstadoContrato.setItems(listaEstadoContrato);
        cbPlanDeServicio.setItems(listaPlanDeServicio);

    }

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
    @SuppressWarnings("null")
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (txtrRazonSupEquipo.getText().isEmpty()) {
                ClaseUtil.mensaje(" Tiene que digitar la razon ");
                txtrRazonSupEquipo.requestFocus();
                return;
            }

//            guardarContrato();
            tareaContrato();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    private void nuevo() {
//
//        contrato = new ContratoDeServicio();
//        det = null;
//        txtNumContrato.setText(ManejoContratoDeServicio.getInstancia().getNumero().toString());
//
//        editarDetalleServicio = false;
//        editar = false;
//        listaDetEquipos.clear();
//        listaDetalleContrato.clear();
//        listaDatosVehiculo.clear();
//
//    }
    private void limpiar() {

        txtRepresentante.clear();
        txtCedulaRepresentante.clear();

        cbFrecuenciaDePago.getSelectionModel().select(-1);
        cbEstadoContrato.getSelectionModel().select(-1);
        cbPlanDeServicio.getSelectionModel().select(-1);
        listaDatosVehiculo.clear();
        listaDetalleContrato.clear();
        listaDetEquipos.clear();
        txtTotal.clear();

        txtCliente.clear();
        dpFecha.setValue(null);
        dpFechaVencimiento.setValue(null);
        txtTotal.clear();

        txtSubTotal.clear();
        txtItbis.clear();

    }

    private Double totalapagar() {

        Double total = 0.00;

        for (DetalleContratoDeServicio det : listaDetalleContrato) {

            total += det.getPrecioPagado();
        }

        return total;
    }

    public void llenarCampo() {

        listaDetalleContrato.clear();
        listaDetEquipos.clear();
        listaDatosVehiculo.clear();

        txtNumContrato.setText(getContrato().getNumero().toString());
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

            DetalleContratoDeServicio det = tbDetalleContrato.getSelectionModel().getSelectedItem();
            listaDatosVehiculo.clear();

            listaDatosVehiculo.addAll(ManejoContratoDeServicio.getInstancia().getDatosVehiculo(det.getCodigo()));
            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(2);
            }

        }
    }

    private void limpiarDetalleContrato() {

        det = null;
    }

    private void limpiarDetalleContratoEquipo() {

        det = null;
    }

    private void guardarContrato() {

        try {

//            listaDetEquipoYServicio.addAll(listaDetEquipos);
//
//            listaDetEquipoYServicio.addAll(listaDetalleContrato);
            ContratoDeServicio contratoDb = ManejoContratoDeServicio.getInstancia().actualizar(getContrato());

//            if (!(contratoDb == null)) {
//
//                ClaseUtil.mensaje("Contrato actualizado exitosamente ");
//                
//            }
        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error guardando el contrato");
            e.printStackTrace();
        }

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

            taskGuardarContrato = new TaskGuardar();
            System.out.println("hola 1");

            progresBar.progressProperty().unbind();

            progresBar.progressProperty().bind(taskGuardarContrato.progressProperty());

            taskGuardarContrato.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent t) -> {

                System.out.println("hola 2");
                progresBar.setVisible(false);
                taskGuardarContrato.cancel();

                progresBar.progressProperty().unbind();

                ClaseUtil.mensaje("Contrato actualizado exitosamente ");

            });

            // Start the Task.
            new Thread(taskGuardarContrato).start();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

}
