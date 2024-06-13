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
import controlador.contrato.consulta.ConsultaContratoDeServicioController;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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
import modelo.ExistenciaArticulo;
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
public class RegistroTraspasoVehiculoController implements Initializable {

    /**
     * @return the contratoDestino
     */
    public ContratoDeServicio getContratoOrigen() {
        return contratoDestino;
    }

    /**
     * @param contratoOrigen the contratoDestino to set
     */
    public void setContratoDestino(ContratoDeServicio contratoOrigen) {
        this.contratoDestino = contratoOrigen;
    }

    @FXML
    private TextArea txtrRazonSupEquipo;
    @FXML
    private ProgressBar progresBar;
    @FXML
    private JFXButton btnBuscarClienteDestino;
    @FXML
    private JFXTextField txtNumContrato1;

    @FXML
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
    private TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> tbcEditarEquipo;

    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcDescripcion;

    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcFechaDesde;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcFechaHasta;

    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcFechaUltimoPagoDesde;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcFechaUltimoPagoHasta;

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
    ObservableList<DetalleContratoDeServicio> listaDetalleServicioContrato = FXCollections.observableArrayList();
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
    ContratoDeServicio contrato;
    private ContratoDeServicio contratoDestino;

    @FXML
    private JFXDatePicker dpFechaVencimiento;
    Boolean editarDetalleServicio = false;
    Boolean editarDetalleEquipo = false;
    @FXML
    private JFXTextField txtClienteDestino;

    public ContratoDeServicio getContrato() {
        return contrato;
    }

    public void setContrato(ContratoDeServicio contrato) {
        this.contrato = contrato;
    }
    Cliente cliente;
    DatosDeVehiculo datosDeVehiculo;
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
            listaDetalleServicioContrato.clear();

            tbDetalleContrato.setItems(listaDetalleServicioContrato);

            tbcEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));
            tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tbcPrecioAbonado.setCellValueFactory(new PropertyValueFactory<>("precioAcordado"));
            tbcFechaDesde.setCellValueFactory(new PropertyValueFactory<>("fechaDesde"));
            tbcFechaHasta.setCellValueFactory(new PropertyValueFactory<>("fechaHasta"));
            tbcFechaUltimoPagoDesde.setCellValueFactory(new PropertyValueFactory<>("fechaUltimoPagoDesde"));
            tbcFechaUltimoPagoHasta.setCellValueFactory(new PropertyValueFactory<>("fechaUltimoPagoHasta"));

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

            tbcEditar.setCellFactory((TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> param) -> {

                TableCell<DetalleContratoDeServicio, DetalleContratoDeServicio> cellsc = new TableCell<DetalleContratoDeServicio, DetalleContratoDeServicio>() {
                    @Override
                    public void updateItem(DetalleContratoDeServicio item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;

                        if (item != null) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(50.0, 20.0);

                            if (item.getTraspasado()) {

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

                                if (item.getTraspasado()) {

                                    item.setTraspasado(false);
                                    item.setHabilitado(true);
                                    btnHabilitar.setText("NO");

                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 15px;\n"
                                            + "    -fx-b-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 12pt;");

                                } else {

                                    item.setTraspasado(true);
                                    item.setHabilitado(false);

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

            tbcEquipo1.setCellValueFactory(new PropertyValueFactory<>("equipo"));
            tbcCantidad1.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            tbcDescripcion1.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tbcPrecioAbonado1.setCellValueFactory(new PropertyValueFactory<>("precioAcordado"));
            tbcFechaDesde1.setCellValueFactory(new PropertyValueFactory<>("fechaDesde"));
            tbcFechaHasta1.setCellValueFactory(new PropertyValueFactory<>("fechaHasta"));

            tbcImei1.setCellValueFactory(new PropertyValueFactory<>("numeroImei"));
            tbcSim1.setCellValueFactory(new PropertyValueFactory<>("numeroSim"));

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

            tbcEditarEquipo.setCellFactory((TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> param) -> {

                TableCell<DetalleContratoDeServicio, DetalleContratoDeServicio> cellsc = new TableCell<DetalleContratoDeServicio, DetalleContratoDeServicio>() {
                    @Override
                    public void updateItem(DetalleContratoDeServicio item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;

                        if (item != null) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(50.0, 20.0);

                            if (item.getTraspasado()) {

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

                                if (item.getTraspasado()) {

                                    item.setTraspasado(false);
                                    item.setHabilitado(true);
                                    btnHabilitar.setText("NO");

                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 15px;\n"
                                            + "    -fx-b-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 12pt;");

                                } else {

                                    item.setTraspasado(true);
                                    item.setHabilitado(false);

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

                            if (item.getTraspasasado()) {

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

                                if (item.getTraspasasado()) {

                                    item.setTraspasasado(false);
                                    item.setHabilitado(true);
                                    btnHabilitar.setText("NO");

                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 15px;\n"
                                            + "    -fx-b-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 12pt;");

                                } else {

                                    item.setTraspasasado(true);
                                    item.setHabilitado(false);

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
        listaModadlidad.addAll(ManejoModalidad.getInstancia().getLista());
        listatIPOvEHICULO.addAll(ManejoTipoDeVehiculo.getInstancia().getLista());
        listaEstadoContrato.addAll(ManejoEstadoContrato.getInstancia().getLista());
        listaPlanDeServicio.addAll(ManejoPlanDeServicio.getInstancia().getLista());
        listaTipoDeServicio.addAll(ManejoTipoDeServicio.getInstancia().getLista());
        listaSimCard.addAll(ManejoSimCard.getInstancia().getLista(true));

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

    @FXML
    private void tbDetalleReciboMouseClicked(MouseEvent event) {
    }

    @FXML
    @SuppressWarnings("null")
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (txtClienteDestino.getText().isEmpty()) {
                ClaseUtil.mensaje(" Tiene que seleccionar el cliente a traspasar el  vehiculo  ");
                txtClienteDestino.requestFocus();
                return;
            }

//            boolean traspaso = false;
//            for (DetalleContratoDeServicio det : listaDetalleServicioContrato) {
//
//                if (det.getTraspasado() == true) {
//                    traspaso = true;
//                    return;
//                }
//            }
//            if (traspaso) {
            guardarContrato();
//            tareaContrato();
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void nuevo() {

        contrato = new ContratoDeServicio();
        det = null;
        txtNumContrato.setText(ManejoContratoDeServicio.getInstancia().getNumero().toString());

        editarDetalleServicio = false;
        editar = false;
        listaDetEquipos.clear();
        listaDetalleServicioContrato.clear();
        listaDatosVehiculo.clear();

    }

    private Double totalapagar() {

        Double total = 0.00;

        for (DetalleContratoDeServicio det : listaDetalleServicioContrato) {

            total += det.getPrecioPagado();
        }

        return total;
    }

    public void llenarCampo() {

        listaDetalleServicioContrato.clear();
        listaDetEquipos.clear();
        listaDatosVehiculo.clear();

        txtNumContrato.setText(getContrato().getNumeroDeContrato());

        cbFrecuenciaDePago.getSelectionModel().select(getContrato().getFrecuenciaDePago());
        cbPlanDeServicio.getSelectionModel().select(getContrato().getPlanDeServicio());
        cbEstadoContrato.getSelectionModel().select(getContrato().getEstado());
        txtTotal.setText(Double.toString(getContrato().getTotalAPagar()));

        dpFecha.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getContrato().getFecha()));
        dpFechaVencimiento.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getContrato().getFechaVencimiento()));
        setCliente(getContrato().getCliente());

        txtCliente.setText(getCliente().getNombre() + " " + getCliente().getApellido());

        listaDetalleServicioContrato.addAll(ManejoContratoDeServicio.getInstancia().getDetalleContratoServicio(getContrato().getCodigo(), false));
        listaDetEquipos.addAll(ManejoContratoDeServicio.getInstancia().getDetalleContratoEquipo(getContrato().getCodigo(), false));

        listaDatosVehiculo.clear();
        System.out.println(" listaDetEquipos : " + listaDetEquipos.size());

        for (DetalleContratoDeServicio det : listaDetEquipos) {

            System.out.println("equipos : " + det.getDescripcion());
            listaDatosVehiculo.addAll(ManejoContratoDeServicio
                    .getInstancia().getDatosVehiculoPorContrato(det.getContratoDeServicio().getCodigo(), false));

        }

        txtSubTotal.setText(getSubTotal().toString());
        txtItbis.setText(getItbis().toString());
        txtTotal.setText(getTotal().toString());

    }

    private Double getSubTotal() {

        Double subTotal = 0.00;

        for (DetalleContratoDeServicio detcT : listaDetalleServicioContrato) {

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

        for (DetalleContratoDeServicio detcT : listaDetalleServicioContrato) {

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

        for (DetalleContratoDeServicio detcT : listaDetalleServicioContrato) {

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

    }

    private void guardarContrato() {

        try {

            List<DatosDeVehiculo> listaDatoVehiculo = new ArrayList<>();
            List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();
            DetalleContratoDeServicio detNuevo = null;
            DatosDeVehiculo datosDeVehiculoNuevo = null;

            for (DetalleContratoDeServicio det2 : listaDetEquipos) {

                if (det2.getTraspasado()) {
                    detNuevo = new DetalleContratoDeServicio();

                    detNuevo.setAdicional(det2.getAdicional());
                    detNuevo.setAlmacen(det2.getAlmacen());
                    detNuevo.setAvanve(det2.getAvanve());
                    detNuevo.setCambioDeModalidad(det2.getCambioDeModalidad());
                    detNuevo.setCantidad(det2.getCantidad());
                    detNuevo.setCantidadAnio(det2.getCantidadAnio());
                    detNuevo.setCantidadMeses(det2.getCantidadMeses());
                    detNuevo.setCantidadFrecuenciaDePago(det2.getCantidadFrecuenciaDePago());
                    detNuevo.setCondicion(det2.getCondicion());
                    detNuevo.setDescripcion(det2.getDescripcion());
                    detNuevo.setDesintalador(det2.getDesintalador());
                    detNuevo.setEjecutivoDeVenta(det2.getEjecutivoDeVenta());
                    detNuevo.setEquipo(det2.getEquipo());
                    detNuevo.setEstado(det2.getEstado());
                    detNuevo.setFacturado(det2.getFacturado());
                    detNuevo.setFechaDesde(det2.getFechaDesde());
                    detNuevo.setFechaHasta(det2.getFechaHasta());
                    detNuevo.setFechaUltimoPagoDesde(det2.getFechaUltimoPagoDesde());
                    detNuevo.setFechaUltimoPagoHasta(det2.getFechaUltimoPagoHasta());
                    detNuevo.setFechaInstalacion(det2.getFechaInstalacion());
                    detNuevo.setNombreEjecutivoDeVenta(det2.getNombreEjecutivoDeVenta());
                    detNuevo.setNombreInstalador(det2.getNombreInstalador());
                    detNuevo.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());
                    detNuevo.setUsuario(VariablesGlobales.USUARIO.getCodigo());
                    detNuevo.setHabilitado(true);
                    detNuevo.setImei(det2.getImei());
                    detNuevo.setSim(det2.getSim());
                    detNuevo.setNumeroImei(det2.getNumeroImei());
                    detNuevo.setNumeroSim(det2.getNumeroSim());
                    detNuevo.setUnidad(det2.getUnidad());
                    detNuevo.setGpsDelCliente(det2.getGpsDelCliente());
                    detNuevo.setItbis(det2.getItbis());
                    detNuevo.setMensualidad(det2.getMensualidad());
                    detNuevo.setModalidad(det2.getModalidad());
                    detNuevo.setCambioDeModalidad(det2.getCambioDeModalidad());
                    detNuevo.setPrecioAcordado(det2.getPrecioAcordado());
                    detNuevo.setPrecioDeOferta(det2.getPrecioDeOferta());
                    detNuevo.setPrecioInstalacion(det2.getPrecioInstalacion());
                    detNuevo.setPrecioPagado(det2.getPrecioPagado());
                    detNuevo.setPrecioRenovacion(det2.getPrecioRenovacion());
                    detNuevo.setTasaItbis(det2.getTasaItbis());
                    detNuevo.setTotal(det2.getTotal());
                    detNuevo.setTipoDeServicio(det2.getTipoDeServicio());
                    detNuevo.setSubTotal(det2.getSubTotal());
                    detNuevo.setReinstaladoPor(det2.getReinstaladoPor());
                    detNuevo.setRecurrente(det2.getRecurrente());
                    detNuevo.setReactivacion(det2.getReactivacion());
                    detNuevo.setReemplazo(det2.getReemplazo());
                    detNuevo.setTraspasado(false);
//                    detNuevo.setFrecuenciaDePago(det2.getFrecuenciaDePago());

                    detNuevo.setContratoDeServicio(contratoDestino);
                    listaDetContrato.add(detNuevo);
                }
            }

            detNuevo = null;
            for (DetalleContratoDeServicio det2 : listaDetalleServicioContrato) {

                if (det2.getTraspasado()) {
                    detNuevo = new DetalleContratoDeServicio();

                    detNuevo.setAdicional(det2.getAdicional());
                    detNuevo.setAlmacen(det2.getAlmacen());
                    detNuevo.setAvanve(det2.getAvanve());
                    detNuevo.setCambioDeModalidad(det2.getCambioDeModalidad());
                    detNuevo.setCantidad(det2.getCantidad());
                    detNuevo.setCantidadAnio(det2.getCantidadAnio());
                    detNuevo.setCantidadMeses(det2.getCantidadMeses());
                    detNuevo.setCantidadFrecuenciaDePago(det2.getCantidadFrecuenciaDePago());
                    detNuevo.setCondicion(det2.getCondicion());
                    detNuevo.setDescripcion(det2.getDescripcion());
                    detNuevo.setDesintalador(det2.getDesintalador());
                    detNuevo.setEjecutivoDeVenta(det2.getEjecutivoDeVenta());
                    detNuevo.setEquipo(det2.getEquipo());
                    detNuevo.setEstado(det2.getEstado());
                    detNuevo.setFacturado(det2.getFacturado());
                    detNuevo.setFechaDesde(det2.getFechaDesde());
                    detNuevo.setFechaHasta(det2.getFechaHasta());
                    detNuevo.setFechaUltimoPagoDesde(det2.getFechaUltimoPagoDesde());
                    detNuevo.setFechaUltimoPagoHasta(det2.getFechaUltimoPagoHasta());
                    detNuevo.setFechaInstalacion(det2.getFechaInstalacion());
                    detNuevo.setNombreEjecutivoDeVenta(det2.getNombreEjecutivoDeVenta());
                    detNuevo.setNombreInstalador(det2.getNombreInstalador());
                    detNuevo.setNombreUsuario(VariablesGlobales.USUARIO.getNombre());
                    detNuevo.setUsuario(VariablesGlobales.USUARIO.getCodigo());
                    detNuevo.setHabilitado(true);
                    detNuevo.setImei(det2.getImei());
                    detNuevo.setSim(det2.getSim());
                    detNuevo.setNumeroImei(det2.getNumeroImei());
                    detNuevo.setNumeroSim(det2.getNumeroSim());
                    detNuevo.setUnidad(det2.getUnidad());
                    detNuevo.setGpsDelCliente(det2.getGpsDelCliente());
                    detNuevo.setItbis(det2.getItbis());
                    detNuevo.setMensualidad(det2.getMensualidad());
                    detNuevo.setModalidad(det2.getModalidad());
                    detNuevo.setCambioDeModalidad(det2.getCambioDeModalidad());
                    detNuevo.setPrecioAcordado(det2.getPrecioAcordado());
                    detNuevo.setPrecioDeOferta(det2.getPrecioDeOferta());
                    detNuevo.setPrecioInstalacion(det2.getPrecioInstalacion());
                    detNuevo.setPrecioPagado(det2.getPrecioPagado());
                    detNuevo.setPrecioRenovacion(det2.getPrecioRenovacion());
                    detNuevo.setTasaItbis(det2.getTasaItbis());
                    detNuevo.setTotal(det2.getTotal());
                    detNuevo.setTipoDeServicio(det2.getTipoDeServicio());
                    detNuevo.setSubTotal(det2.getSubTotal());
                    detNuevo.setReinstaladoPor(det2.getReinstaladoPor());
                    detNuevo.setRecurrente(det2.getRecurrente());
                    detNuevo.setReactivacion(det2.getReactivacion());
                    detNuevo.setReemplazo(det2.getReemplazo());
                    detNuevo.setTraspasado(false);
                    detNuevo.setFrecuenciaDePago(det2.getFrecuenciaDePago());
                    detNuevo.setContratoDeServicio(contratoDestino);
                    listaDetContrato.add(detNuevo);
                }
            }

            contratoDestino.setDetalleContratoDeServicioCollection(listaDetContrato);

            ContratoDeServicio contratoDb = null;

            contratoDb = ManejoContratoDeServicio.getInstancia().actualizar(contratoDestino);

            for (DatosDeVehiculo v : tbDatosDeVehiculo.getItems()) {

                if (v.getTraspasasado()) {

                    datosDeVehiculoNuevo = v;
                    datosDeVehiculoNuevo.setTraspasasado(false);
                    datosDeVehiculoNuevo.setHabilitado(true);
                    datosDeVehiculoNuevo.setContrato(contratoDb.getCodigo());
                    listaDatoVehiculo.add(datosDeVehiculoNuevo);
                }

            }

            listaDatoVehiculo.forEach((dvh) -> {

                ManejoDatosDeVehiculo.getInstancia().salvar(dvh);

            });

            listaDetEquipoYServicio.clear();
            listaDetEquipoYServicio.addAll(listaDetalleServicioContrato);
            listaDetEquipoYServicio.addAll(listaDetEquipos);

            contrato.setDetalleContratoDeServicioCollection(listaDetEquipoYServicio);

            ManejoContratoDeServicio.getInstancia().actualizar(contrato);

            ClaseUtil.mensaje(" Traspaso realizado exitosamente ");

            Stage stage = (Stage) btnGuardar.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error guardando el contrato");
            e.printStackTrace();
        }

    }

    @FXML
    private void tbDetalleContratoEquiposMouseClicked(MouseEvent event) {

    }

    @FXML
    private void btnBuscarClienteDestinoActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/contrato/consulta/ConsultaContratoDeServicio.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        ConsultaContratoDeServicioController controller = loader.getController();

        if (!(controller.getContrato() == null)) {

            setContratoDestino(controller.getContrato());

            txtClienteDestino.setText(getContratoOrigen().getCliente().getNombre());
            txtNumContrato1.setText(getContratoOrigen().getNumero().toString());

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

            taskGuardarContrato = new TaskGuardar();

            progresBar.progressProperty().unbind();

            progresBar.progressProperty().bind(taskGuardarContrato.progressProperty());

            taskGuardarContrato.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent t) -> {

                progresBar.setVisible(false);
                taskGuardarContrato.cancel();

                ClaseUtil.mensaje("Traspaso realizado exitosamente");
                nuevo();

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
