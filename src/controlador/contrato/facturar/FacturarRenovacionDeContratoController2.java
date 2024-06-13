/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.facturar;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.cxc.reciboIngreso.RegistroReciboDeIngresoController;
import controlador.venta.cliente.FXMLBusClienterController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoListaDePrecio;
import manejo.cliente.ManejoPlazo;
import manejo.contrato.ManejoContratoDeServicio;
import manejo.contrato.ManejoCorteDeFacturacion;
import manejo.contrato.ManejoFacturaDatosDeVehiculo;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoFacturaTemporal;
import manejo.factura.ManejoFormaPago;
import manejo.factura.ManejoHistoricoBalancePendiente;
import manejo.factura.ManejoOrigenFactura;
import manejo.factura.ManejoRelacionNcf;
import manejo.factura.ManejoTipoFormaPago;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import manejo.unidadDeNegocio.ManejoUnidadDeNegocio;
import modelo.Cliente;
import modelo.CondicionPago;
import modelo.ContratoDeServicio;
import modelo.CorteDeFacturacion;
import modelo.DatosDeVehiculo;
import modelo.DetalleContratoDeServicio;
import modelo.DetalleCorteDeFacturacion;
import modelo.DetalleFactura;
import modelo.DetalleFacturaTemporal;
import modelo.Factura;
import modelo.FacturaDatosDeVehiculo;
import modelo.FacturaTemporal;
import modelo.FormaPago;
import modelo.HistoricoBalancePendiente;
import modelo.MenuModulo;
import modelo.RelacionNcf;
import modelo.RolMenuModulo;
import modelo.SecuenciaDocumento;
import modelo.TipoFormaPago;
import modelo.Unidad;
import reporte.unidadnegocio.RptFactura;
import utiles.ClaseUtil;
import utiles.FormatNum;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FacturarRenovacionDeContratoController2 implements Initializable {

    @FXML
    private JFXDatePicker dpFechaFacturacion;
    @FXML
    private TableView<DetalleContratoDeServicio> tbDetalleContrato;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcEquipo;
    @FXML
    private TableColumn<DetalleContratoDeServicio, Double> tbcPrecioAcordado;
    @FXML
    private TableColumn<DetalleContratoDeServicio, Double> tbcPrecioRenovacion;
    @FXML
    private TableColumn<DetalleContratoDeServicio, Integer> tbcCantidad;
    @FXML
    private TableColumn<DetalleContratoDeServicio, Integer> tbcMeses;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcPagoDesde;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcPagoHasta;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcTipoServicio;
    @FXML
    private TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> tbcAvance;

    @FXML
    private TabPane tabPane;
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
    private TableColumn<DatosDeVehiculo, DatosDeVehiculo> tbcVehiculoHabilitado;
    @FXML
    private TableColumn<DatosDeVehiculo, DatosDeVehiculo> tbcAgregar;
    @FXML
    private JFXTextField txtFiltroServicio;
    @FXML
    private JFXTextField txtFiltroVehiculo;
    @FXML
    private JFXButton btnReciboIngreso;
    @FXML
    private JFXCheckBox chNuevaModalidad;
    @FXML
    private JFXCheckBox chNuevaModalidRnv;

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
    private JFXButton btnGenerarFacturaRecurrente;
    @FXML
    private JFXButton btnGuardarRecurrente;
    @FXML
    private JFXTextField txtFiltroContrato;
    @FXML
    private JFXTextField txtCliente;
    @FXML
    private JFXButton btnBuscarCliente;

    /**
     * @return the codigoMenuModulo
     */
    public Integer getCodigoMenuModulo() {
        return codigoMenuModulo;
    }

    /**
     * @param codigoMenuModulo the codigoMenuModulo to set
     */
    public void setCodigoMenuModulo(Integer codigoMenuModulo) {
        this.codigoMenuModulo = codigoMenuModulo;
    }

    private Date fechaFacturacion = new Date();

    @FXML
    private TableView<ContratoDeServicio> tbContratoDeServicio;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcNumContratoContra;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcClienteContra;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcTipoDeContrato;
    @FXML
    private TableColumn<ContratoDeServicio, Double> tbcMontoContra;
    @FXML
    private Label lbCantidadContrato;
    @FXML
    private TableView<FacturaTemporal> tbFacturaDeContrato;
    @FXML
    private TableColumn<FacturaTemporal, String> tbcNoContratoFact;
    @FXML
    private TableColumn<FacturaTemporal, Integer> tbcFacturaFact;
    @FXML
    private TableColumn<FacturaTemporal, String> tbcClienteFact;
    @FXML
    private TableColumn<FacturaTemporal, Double> tbcMontoFact;
    @FXML
    private TableColumn<FacturaTemporal, FacturaTemporal> tbcVerFactura;

    ObservableList<ContratoDeServicio> listaContrato = FXCollections.observableArrayList();
    ObservableList<DatosDeVehiculo> listaDatosVehiculo = FXCollections.observableArrayList();
    ObservableList<DetalleContratoDeServicio> listaDetalleContrato = FXCollections.observableArrayList();
    ObservableList<Factura> listaFactura = FXCollections.observableArrayList();

    ObservableList<DetalleFactura> listaDetalleFactura = FXCollections.observableArrayList();
    ObservableList<FacturaDatosDeVehiculo> listaDetalleFacDdatosVehiculo = FXCollections.observableArrayList();

    ObservableList<FacturaTemporal> listaFacturaTemporal = FXCollections.observableArrayList();

    ObservableList<DetalleFacturaTemporal> listaDetalleFacturaTemporal = FXCollections.observableArrayList();

    RelacionNcf relacionNcf = null;
    @FXML
    private HBox hbPermisos;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    private Integer codigoMenuModulo;
    TaskGenerarFacturaTemporales taskGenerarFacturaTemporales;
    TaskGenerarFactura taskGenerarFactura;
    private Cliente cliente;
    Factura factDb = null;
    Double precioPorDia = 0.00;
    Date fechacorteAnio = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablaContrato();
        inicializarTablaDetalle();
        inicializarTablaFactura();
        iniciazarFiltroContrato();
        iniciazarFiltroServicio();
        iniciazarFiltroVehiculo();
        inicializarTablaDatosVehiculo();
        dpFechaFacturacion.setDisable(true);
        dpFechaFacturacion.setValue(LocalDate.now());

        setCodigoMenuModulo(ManejoMenuModulo.getInstancia().getMenuModulo(9, "btnFacturaAvanceContrato"));
        agregarOpciones();
        activarOpciones();

        btnGenerarFacturaRecurrente.setDisable(true);

//        fechaFacturacion = ClaseUtil.getFechaFacturacion();
    }

    private void iniciazarFiltroContrato() {

        FilteredList<ContratoDeServicio> filteredData = new FilteredList<>(tbContratoDeServicio.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltroContrato.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(contrato -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (contrato.getNombreCliente().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (contrato.getNumeroDeContrato().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (contrato.getFecha().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (contrato.getEjecutivoDeVenta().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (contrato.getPlanDeServicio().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ContratoDeServicio> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbContratoDeServicio.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbContratoDeServicio.setItems(sortedData);
    }

    private void iniciazarFiltroServicio() {

        FilteredList<DetalleContratoDeServicio> filteredData = new FilteredList<>(tbDetalleContrato.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltroServicio.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(detalle -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (detalle.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (detalle.getFechaUltimoPagoDesde().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (detalle.getFechaUltimoPagoHasta().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (Double.toString(detalle.getPrecioAcordado()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<DetalleContratoDeServicio> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbDetalleContrato.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbDetalleContrato.setItems(sortedData);
    }

    private void iniciazarFiltroVehiculo() {

        FilteredList<DatosDeVehiculo> filteredData = new FilteredList<>(tbDatosDeVehiculo.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltroVehiculo.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(detalle -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (detalle.getMarca().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (detalle.getModelo().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (detalle.getPlaca().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (detalle.getChasis().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (detalle.getColor().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<DatosDeVehiculo> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbcVehiculoHabilitado.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbDatosDeVehiculo.setItems(sortedData);
    }

    public void inicializarTablaDetalle() {

        try {

            listaDatosVehiculo.clear();
            listaDetalleContrato.clear();

            tbDetalleContrato.setEditable(true);
            tbcEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));
            tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            tbcPrecioAcordado.setCellValueFactory(new PropertyValueFactory<>("precioAcordado"));
            tbcPrecioRenovacion.setCellValueFactory(new PropertyValueFactory<>("precioRenovacion"));
            tbcMeses.setCellValueFactory(new PropertyValueFactory<>("cantidadAnio"));

            tbcPagoDesde.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaUltimoPagoDesde() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaUltimoPagoDesde()));
                        }
                        return property;
                    });

            tbcPagoHasta.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaUltimoPagoHasta() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaUltimoPagoHasta()));
                        }
                        return property;
                    });

            tbcMeses.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
                @Override
                public String toString(Integer object) {

                    return null == object ? "0" : object.toString();
                }

                @Override
                public Integer fromString(String string) {

                    return Integer.parseInt(string);

                }

            }));

            tbcMeses.setOnEditCommit(data -> {

                try {

                    Double costo = 0.00;
                    DetalleContratoDeServicio p = data.getRowValue();

                    if (data.getNewValue() >= 0) {

                        p.setCantidadAnio(data.getNewValue());

                        tbFacturaDeContrato.refresh();
                        tbFacturaDeContrato.requestFocus();

                    } else {

                        util.ClaseUtil.mensaje("El valor no puede ser menor o igual  que cero");
                        tbFacturaDeContrato.refresh();
                        tbFacturaDeContrato.requestFocus();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

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

            tbcAvance.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcAvance.setCellFactory((TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> param) -> {

                TableCell<DetalleContratoDeServicio, DetalleContratoDeServicio> cellsc = new TableCell<DetalleContratoDeServicio, DetalleContratoDeServicio>() {
                    @Override
                    public void updateItem(DetalleContratoDeServicio item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;

                        if (item != null && item.getEquipo().getTipoArticulo().getCodigo() == 3) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(50.0, 20.0);
                            item.setAvanve(false);

                            if (item.getAvanve()) {
                                btnHabilitar.setText("SI");
                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 9pt;");

                            } else {

                                btnHabilitar.setText("NO");
                                btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 9pt;");

                            }

                            HBox hbox = new HBox();

                            hbox.getChildren().add(btnHabilitar);

                            hbox.setAlignment(Pos.CENTER);

                            btnHabilitar.setOnMouseClicked((event) -> {

                                if (item.getAvanve() && item.getHabilitado()) {

                                    item.setAvanve(false);
                                    btnHabilitar.setText("NO");
                                    btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 9pt;");
                                } else if (item.getHabilitado()) {

                                    item.setAvanve(true);
                                    btnHabilitar.setText("SI");
                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 9pt;");
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

            tbDetalleContrato.setItems(listaDetalleContrato);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTablaDatosVehiculo() {

        try {

            listaDatosVehiculo.clear();
            tbcPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
            tbcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
            tbcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            tbcChasis.setCellValueFactory(new PropertyValueFactory<>("chasis"));
            tbcColor.setCellValueFactory(new PropertyValueFactory<>("color"));
            tbcTipoVehiculo.setCellValueFactory(new PropertyValueFactory<>("tipoDeVehiculo"));

            tbcTipoVehiculo.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getTipoVehiculo() != null) {
                            property.setValue(cellData.getValue().getTipoVehiculo().getNombre());
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

            tbcVehiculoHabilitado.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
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

            tbcAgregar.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcAgregar.setCellFactory((TableColumn<DatosDeVehiculo, DatosDeVehiculo> param) -> {

                TableCell<DatosDeVehiculo, DatosDeVehiculo> cellsc = new TableCell<DatosDeVehiculo, DatosDeVehiculo>() {
                    @Override
                    public void updateItem(DatosDeVehiculo item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;

                        if (item != null) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(50.0, 20.0);

                            if (item.getRenovar()) {

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

                                if (item.getRenovar()) {

                                    item.setRenovar(false);
                                    btnHabilitar.setText("NO");
                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 15px;\n"
                                            + "    -fx-b-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 12pt;");

                                } else {

                                    item.setRenovar(true);
                                    btnHabilitar.setText("SI");

                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 12pt;");
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

            tbDatosDeVehiculo.setItems(listaDatosVehiculo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTablaContrato() {

        try {

            tbContratoDeServicio.setEditable(true);
            tbcNumContratoContra.setCellValueFactory(new PropertyValueFactory<>("numeroDeContrato"));
            tbcClienteContra.setCellValueFactory(new PropertyValueFactory<>("cliente"));

            tbcTipoDeContrato.setCellValueFactory(new PropertyValueFactory<>("frecuenciaDePago"));
            tbcMontoContra.setCellValueFactory(new PropertyValueFactory<>("totalAPagar"));

            tbcClienteContra.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getCliente() != null) {
                            property.setValue(cellData.getValue().getCliente().getNombre());
                        }
                        return property;
                    });

            tbcTipoDeContrato.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFrecuenciaDePago() != null) {
                            property.setValue(cellData.getValue().getFrecuenciaDePago().getFrecuencia());
                        }
                        return property;
                    });

            tbContratoDeServicio.setItems(listaContrato);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTablaFactura() {

        try {

            tbFacturaDeContrato.setEditable(true);

            tbcNoContratoFact.setCellValueFactory(new PropertyValueFactory<>("numeroContrato"));
            tbcClienteFact.setCellValueFactory(new PropertyValueFactory<>("cliente"));
            tbcFacturaFact.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tbcMontoFact.setCellValueFactory(new PropertyValueFactory<>("total"));

            tbcClienteFact.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getCliente() != null) {
                            property.setValue(cellData.getValue().getCliente().getNombre());
                        }
                        return property;
                    });

            tbcVerFactura.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcVerFactura.setCellFactory((TableColumn<FacturaTemporal, FacturaTemporal> param) -> {

                TableCell<FacturaTemporal, FacturaTemporal> cellsc = new TableCell<FacturaTemporal, FacturaTemporal>() {
                    @Override
                    public void updateItem(FacturaTemporal item, boolean empty) {
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

                                RptFactura.getInstancia().verFacturaTemporal(item.getCodigo(),
                                        VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

//                                RptFacturaIghTrack.getInstancia().verFacturaTemporal(item.getCodigo());
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

            tbFacturaDeContrato.setItems(listaFacturaTemporal);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {

        if (fechaFacturacion == null) {

            ClaseUtil.mensaje("Tiene que seleccionar una fecha");
            return;
        }

        try {

            for (DetalleContratoDeServicio detCnt : listaDetalleContrato) {

                if (detCnt.getAvanve() && detCnt.getCantidadAnio() <= 0) {

                    ClaseUtil.mensaje(" La cantidad de años no puede ser cero o menor que cero ");
                    return;

                }
            }

            for (ContratoDeServicio contrato : listaContrato) {

                if (contrato.getCliente().getTipoNcf() == null) {
                    ClaseUtil.mensaje("EL CLIENTE " + contrato.getCliente().getNombre()
                            + " DEL CONTRATO " + contrato.getNumero() + " TIENE QUE CONFIGUARLE EL TIPO DE FACTURACION ");
                    listaFacturaTemporal.clear();
                    return;
                }
            }

            ManejoFacturaTemporal.getInstancia().eliminarFacturaTemporal();

            listaFacturaTemporal.clear();
            List<DetalleContratoDeServicio> listaAvance = new ArrayList();
//            for (ContratoDeServicio contrato : listaContrato) {

            for (DetalleContratoDeServicio detCnt : listaDetalleContrato) {

                if (detCnt.getAvanve()) {

                    listaAvance.add(detCnt);
                }
            }

            if (listaAvance.size() <= 0) {
                ClaseUtil.mensaje("No hay servicio seleccionado para generar la factura");
                return;
            }

            generarFactTemporales(listaAvance.get(0).getContratoDeServicio(), listaAvance);

            //}
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void tbContratoDeServicioClicked(MouseEvent event) {

        if (!(tbContratoDeServicio.getSelectionModel().getSelectedIndex() == -1)) {
            int contrato = 0;
            System.out.println(" tbDetalleContrato.getSelectionModel().getSelectedItem() "
                    + tbDetalleContrato.getSelectionModel().getSelectedItem());
            listaDatosVehiculo.clear();
            listaDetalleContrato.clear();

            contrato = tbContratoDeServicio.getSelectionModel().getSelectedItem().getCodigo();
            System.out.println("servicio " + contrato);

            listaDetalleContrato.addAll(ManejoContratoDeServicio.getInstancia()
                    .getDetalleContratoHabilitado(contrato, 1));

            listaDatosVehiculo.addAll(ManejoContratoDeServicio.getInstancia().getDatosVehiculoPorContrto(contrato));

            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(1);
            }
        }
    }

    private Integer cantidadContrato() {

        return listaContrato.size();
    }

    private Integer cantidadFactura() {

        return listaFacturaTemporal.size();
    }

    @FXML
    private void tbFacturaDeContratoMouseClicked(MouseEvent event) {

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (listaFacturaTemporal.size() <= 0) {

                ClaseUtil.mensaje("No hay factura para guardar ");
                return;
            }

            for (DetalleContratoDeServicio detCnt : listaDetalleContrato) {

                if (detCnt.getAvanve() && detCnt.getCantidadAnio() <= 0) {

                    ClaseUtil.mensaje("La cantidad de años no puede ser cero o menor que cero ");
                    return;

                }
            }

            int canVehiculo = 0;
            for (DatosDeVehiculo dv : listaDatosVehiculo) {

                if (dv.getRenovar()) {
                    canVehiculo++;
                }
            }

            if (canVehiculo <= 0) {

                ClaseUtil.mensaje("Tiene que seleccionar el o los vehiculos a renovar ");
                return;

            }

            Optional<ButtonType> ok = ClaseUtil.confirmarMensaje(" Seguro que quiere guardar esta factura ");

            System.out.println("listaContrato " + listaContrato.size());
            if (ok.get() == ButtonType.OK || ok.get() == ButtonType.YES) {

                List<DetalleContratoDeServicio> listaAvance = new ArrayList();

                for (DetalleContratoDeServicio detCnt : listaDetalleContrato) {

                    if (detCnt.getAvanve()) {

                        listaAvance.add(detCnt);
                    }
                }

                if (listaAvance.size() <= 0) {
                    ClaseUtil.mensaje("No hay servicio seleccionado para generar la factura");
                    return;
                }

                generarFactura(listaAvance.get(0).getContratoDeServicio(), listaAvance);

                listaContrato.clear();
                listaFacturaTemporal.clear();
                listaDetalleContrato.clear();
                listaDatosVehiculo.clear();
                txtCliente.clear();
                setCliente(null);
                ManejoFacturaTemporal.getInstancia().eliminarFacturaTemporal();
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
        }

    }

    private Factura guardar(Factura fac) {

        Factura facturaDb = null;

        try {

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(fac.getUnidadDeNegocio().getCodigo(), 7);

            if (!(secDoc == null)) {

                boolean existe = ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero());

                if (existe) {

                    System.out.println("existe " + secDoc.getNumero());

                    while (ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero())) {

                        secDoc.setNumero(secDoc.getNumero() + 1);
                        ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                    }

                    secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(fac.getUnidadDeNegocio().getCodigo(), 7);

                    fac.setNumero(secDoc.getNumero());
                    fac.setSecuenciaDocumento(secDoc);

                } else {

                    System.out.println("No existe " + secDoc.getNumero());
                    fac.setNumero(secDoc.getNumero());
                    fac.setSecuenciaDocumento(secDoc);
                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

            } else {

                ClaseUtil.mensaje("La secuencia para la factura de la unidad de negocio "
                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return facturaDb;
            }

            System.out.println("");
            fac.setTipoNcf(fac.getCliente().getTipoNcf());//ponemos el tipo de ncf
            fac.setNombreTipoNcf(fac.getCliente().getTipoNcf().getNombre());
//                factura.setTipoNcf(cbTipoNcf.getSelectionModel().getSelectedItem());

            System.out.println("fac.getCliente().getTipoNcf() " + fac.getCliente().getTipoNcf() + " fac.getTipoNcf() " + fac.getTipoNcf());

            int ung = fac.getUnidadDeNegocio().getCodigo();
            boolean empresa = false;
            relacionNcf = ManejoRelacionNcf.getInstancia()
                    .getNCFUnidadDeNegocio(fac.getCliente().getTipoNcf(), ung);

            if (relacionNcf == null) {//SI ES IGUAL A NULL LA UNIDAD DE NEGOCIO NO TIENE COMPROBANTE FISCALES CONFIGURADO

                ung = fac.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo();

                relacionNcf = ManejoRelacionNcf.getInstancia()
                        .getNCFEmpresaOGrupo(fac.getTipoNcf(), ung);//Comprobante fiscal del grupo fs comercial
                empresa = true;
//
//                System.out.println(" EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL  " + relacionNcf);
////
////                ClaseUtil.mensaje(" La secuencia de ncf para el tipo " + fac.getCliente().getTipoNcf().getDescripcion() + " de la unidad de negocio "
////                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
//
////                System.out.println("relacionNcf  " + relacionNcf);
//                relacionNcf = ManejoRelacionNcf.getInstancia()
//                        .getNCFUnidadDeNegocio(fac.getCliente().getTipoNcf(), 3);//EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL
//                System.out.println(" EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL  " + relacionNcf);
//               return null;
            }

            System.out.println("relacionNcf  " + relacionNcf);
            boolean existe = ManejoFactura.getInstancia().existeNCF(relacionNcf.getActual(), fac.getUnidadDeNegocio().getCodigo());

            if (existe) {

                while (ManejoFactura.getInstancia().existeNCF(relacionNcf.getActual(), fac.getUnidadDeNegocio().getCodigo())) {

                    relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo(), ung, empresa);

                }

            } else {

                relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo(), ung, empresa);

            }

            fac.setNcf(relacionNcf.getActual());
            fac.setNcfFechaValidoHasta(relacionNcf.getFechaValidoHasta());
            fac.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            System.out.println("Sin ncf");

            facturaDb = ManejoFactura.getInstancia().salvar(fac);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return facturaDb;
    }

    private Double getSubTotal(List<DetalleFactura> lista) {

        Double subTotal = 0.00;

        for (DetalleFactura det : lista) {

            subTotal += det.getSubTotal();

        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotal(List<DetalleFactura> lista) {

        Double total = 0.00;

        for (DetalleFactura det : lista) {

            total += det.getTotal();

        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getItbis(List<DetalleFactura> lista) {

        Double itbis = 0.00;

        for (DetalleFactura det : lista) {

            itbis += det.getItbis();

        }

        return FormatNum.FormatearDouble(itbis, 2);
    }

    private void btnExportarPdfActionEvent(ActionEvent event) {

        try {

            Date fecha = fechaFacturacion;
            ManejoFactura.getInstancia().enviarFacturaPorCorreoEnPdf(fecha);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Agrega las opciones de menu a la base de datos
    private void agregarOpciones() {

        MenuModulo menuModulo;

        List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(9);
        int codigoModulo = ManejoModulo.getInstancia().getModulo(9).getCodigo();
        String nombreModulo = ManejoModulo.getInstancia().getModulo(9).getNombre();

        boolean actualizando = false;

        for (Node n : hbPermisos.getChildren()) {

            System.out.println("n.getId() " + n.getId());
            if (!(n.getId() == null)) {

                System.out.println("n.getId() no es null " + n.getId());
                menuModulo = new MenuModulo();

                menuModulo.setIdMenu(n.getId());
                menuModulo.setMenu(n.getAccessibleText());
                menuModulo.setModulo(codigoModulo);
                menuModulo.setNombreModulo(nombreModulo);
                menuModulo.setPermiso(getCodigoMenuModulo());
//                menuModulo.setTipoMenu(ManejoTipoMenu.getInstancia().getTipoMenu(codigoModulo));

                for (MenuModulo memu : listaMenuModulo) {

                    if (n.getId().equals(memu.getIdMenu())) {

                        menuModulo = memu;
                        actualizando = true;
                        break;
                    }
                }

                if (actualizando) {

                    ManejoMenuModulo.getInstancia().actualizar(menuModulo);

                } else {

                    ManejoMenuModulo.getInstancia().salvar(menuModulo);
                }

            }

        }
    }

    private void activarOpciones() {

        if (codigoRol == 1) {//rol de administrador

            for (Node n : hbPermisos.getChildren()) {
                n.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, 9);

            for (Node n : hbPermisos.getChildren()) {

                if (!(n.getId() == null)) {

                    for (RolMenuModulo memu : listaRolMenuModulos) {

                        if (n.getId().equals(memu.getMenuModulo().getIdMenu())) {
                            n.setDisable(!memu.getHabilitado());

                        }
                    }

                }

            }
        }

    }
//
//    private void generarFactTemporales() {
//
//        try {
//
//            CorteDeFacturacion corteFact = new CorteDeFacturacion();
//
//            FacturaTemporal factura;
//            DetalleFacturaTemporal detalleFactura;
//
//            List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();
//
//            corteFact.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
//            corteFact.setFechaRegistro(new Date());
//            corteFact.setUsuario(VariablesGlobales.USUARIO);
//
//            int num = 0;
//
//            vbVisorDeProgreso.setVisible(true);
//            for (ContratoDeServicio contrato : listaContrato) {
//
//                factura = new FacturaTemporal();
//
//                //****************************************
//                System.out.println("contrato.getCliente() " + contrato.getCliente().getTipoNcf());
//                factura.setAbono(0.00);
//                factura.setCliente(contrato.getCliente());
//                factura.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
//                factura.setMonto(contrato.getTotalAPagar());
//                factura.setAnulada(false);
//                factura.setNumeroContrato(contrato.getNumeroDeContrato());
//                factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
//
//                SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
//                        .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
//                factura.setNumero(secDoc.getNumero() + num);
//
//                factura.setNcf("na");
//                factura.setTotal(factura.getMonto());
//                factura.setSubTotal(contrato.getSubTotal());
//                factura.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
//                factura.setDescuento(0.00);
//                factura.setItbis(contrato.getItbis());
//                factura.setUsuario(VariablesGlobales.USUARIO);
//                factura.setVendedor(contrato.getEjecutivoDeVenta());
//
//                factura.setFechaCreacion(new Date());
//
//                Date fechaVencimiento = ClaseUtil.asDate(dpFechaFacturacion.getValue());
//                factura.setFechaVencimiento(fechaVencimiento);
//
//                factura.setPagada(false);
//                factura.setTipoVenta(2);
//                factura.setAbono(0.0);
//                factura.setPendiente(factura.getTotal());
//                factura.setCondicionPago(new CondicionPago(2));
//                factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
//                fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, factura.getCliente().getDiasCredito());
//                factura.setFechaVencimiento(fechaVencimiento);
//                factura.setDespachada(false);
//
//                FormaPago formaPago = new FormaPago();
//                TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
//                formaPago.setTipoFormaPago(tipoFormaPago);
//                formaPago.setMonto(factura.getTotal());
//
//                //agregando detalle a la factura
//                listaDetContrato.clear();
//                listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
//                        .getDetalleContratoVencido(contrato.getCodigo(), fechaVencimiento));
//
//                System.out.println("NO HUBO CLIENTE CON NCF NULO ");
//
//                Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;
//
//                listaDetalleFacturaTemporal.clear();
//                listaDetalleFacDdatosVehiculo.clear();
//                for (DetalleContratoDeServicio detCto : listaDetContrato) {
//
//                    System.out.println("detCto.getEquipo().getTipoArticulo().getCodigo() " + detCto.getEquipo().getTipoArticulo().getCodigo());
////                    if (detCto.getEquipo().getTipoArticulo().getCodigo() == 3) {//solo se creanran factura por servicio 
//
//                    detalleFactura = new DetalleFacturaTemporal();
//                    // *****************************************
//
//                    long dias = ClaseUtil.diferenciaDiasEntreDosFecha(detCto.getFechaUltimoPagoHasta(), fechaVencimiento);
//
//                    Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();
//
//                    int diasInt = Integer.parseInt(Long.toString(dias));
//
//                    if (dias < 30) {
//
//                        System.out.println("dias MENOR A 30" + dias);
//                        Double precioPorDia = ClaseUtil.roundDouble(detCto.getPrecioPagado() / 30, 2);
//                        precioPorDia = precioPorDia * dias;
//                        detalleFactura.setPrecio(precioPorDia);
//
//                    } else {
//
//                        System.out.println("dias mayor de 30 " + dias);
//                        detalleFactura.setPrecio(detCto.getPrecioPagado());
//                    }
//
//                    int cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();
//                    int cantidadGPS = detCto.getCantidad();
//                    String desc = "";
//                    Date fechaVencimientoCal = null;
//
//                    if (dias < 30) {
//
//                        fechaVencimientoCal = ClaseUtil.Fechadiadespues(detCto.getFechaUltimoPagoHasta(), diasInt);//Calculamos la fecha de vencimiento
//
//                    } else {
//
//                        fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadFrecuenciaPago);//Calculamos la fecha de vencimiento
//                        // en base a la cantidad de pago por adelantado
//                    }
//
//                    detCto.setFechaUltimoPagoDesde(detCto.getFechaUltimoPagoHasta());//Factura  que cubre el pago desde esta fecha hasta la fecha calculada
//
//                    detCto.setFechaUltimoPagoHasta(fechaVencimientoCal); //fechaVencimientoCal   a hora es el setFechaUltimoPagoHasta    
//
//                    desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
//                            + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoDesde())
//                            + " AL  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta());
//
//                    System.out.println("descripcion pago " + desc);
//                    detalleFactura.setDescripcionPago(desc);
//                    detalleFactura.setFactura(factura);
//                    detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
//                    detalleFactura.setArticulo(detCto.getEquipo());
//                    cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();//CAntidad facturado
//
//                    detalleFactura.setCantidad(cantidad);
//
//                    subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
//                    detalleFactura.setSubTotal(subTotal);
//
//                    valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);
//
//                    detalleFactura.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));
//
//                    detalleFactura.setDescuento(0.00);
//                    detalleFactura.setExistencia(0.00);
//                    detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
//                    detalleFactura.setNuevaExistencia(0.0);
//
//                    detalleFactura.setItbis(valorItbis);
//                    detalleFactura.setTasaItbis(detCto.getTasaItbis());
//
//                    detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
//                    detalleFactura.setUnidad(unidad);
//
//                    listaDetalleFacturaTemporal.add(detalleFactura);
//                }
//
//                try {
//
//                    System.out.println(" listaDetalleFactura " + listaDetalleFacturaTemporal + " listaDetalleFactura.size() " + listaDetalleFacturaTemporal.size());
//
//                    if (listaDetalleFacturaTemporal.size() > 0) {
//
//                        factura.setTotal(getTotalTemp(listaDetalleFacturaTemporal));
//                        factura.setSubTotal(getSubTotalTewmp(listaDetalleFacturaTemporal));
//                        factura.setItbis(getItbisTemp(listaDetalleFacturaTemporal));
//                        factura.setMonto(getTotalTemp(listaDetalleFacturaTemporal));
//                        factura.setPendiente(getTotalTemp(listaDetalleFacturaTemporal));
//
//                        factura.setDetalleFacturaTemporalCollection(listaDetalleFacturaTemporal);
//
//                        FacturaTemporal factDb = guardarFacturaTempotal(factura);
//
//                        listaFacturaTemporal.add(factDb);
//
//                        if (factDb == null) {
//
//                            ClaseUtil.mensaje(" Hubo Error creando la factura ");
//                            return;
//
//                        }
//                    }
//
//                } catch (Exception e) {
//
//                    e.printStackTrace();
//                }
//
//                num++;
//
//            }
//
//            listaContrato.clear();
//            lbCantidadContrato.setText("");
//            lbCantidadFactura.setText("");//
//            lbCantidadFactura.setText(cantidadFactura().toString());
//            vbVisorDeProgreso.setVisible(false);
//
//        } catch (Exception e) {
//
//            ClaseUtil.mensaje("Hubo un error guardando la factura ");
//            e.printStackTrace();
//        }
//
//    }

    private Double getSubTotalTewmp(List<DetalleFacturaTemporal> lista) {

        Double subTotal = 0.00;

        for (DetalleFacturaTemporal det : lista) {

            subTotal += det.getSubTotal();

        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotalTemp(List<DetalleFacturaTemporal> lista) {

        Double total = 0.00;

        for (DetalleFacturaTemporal det : lista) {

            total += det.getTotal();

        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getItbisTemp(List<DetalleFacturaTemporal> lista) {

        Double itbis = 0.00;

        for (DetalleFacturaTemporal det : lista) {

            itbis += det.getItbis();

        }

        return FormatNum.FormatearDouble(itbis, 2);
    }

    private FacturaTemporal guardarFacturaTempotal(FacturaTemporal fac) {

        FacturaTemporal facturaDb = null;

        try {

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(fac.getUnidadDeNegocio().getCodigo(), 7);

            if (!(secDoc == null)) {

                fac.setNumero(secDoc.getNumero());
                fac.setSecuenciaDocumento(secDoc);

            } else {

                ClaseUtil.mensaje("La secuencia para la factura de la unidad de negocio "
                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return facturaDb;
            }

            System.out.println("");
            fac.setTipoNcf(fac.getCliente().getTipoNcf());//ponemos el tipo de ncf
            fac.setNombreTipoNcf(fac.getCliente().getTipoNcf().getNombre());

            System.out.println("fac.getCliente().getTipoNcf() " + fac.getCliente().getTipoNcf() + " fac.getTipoNcf() " + fac.getTipoNcf());

//            relacionNcf = ManejoRelacionNcf.getInstancia()
//                    .getNCFUnidadDeNegocio(fac.getCliente().getTipoNcf(), fac.getUnidadDeNegocio().getCodigo());
//
//            if (relacionNcf == null) {//SI ES IGUAL A NULL LA UNIDAD DE NEGOCIO NO TIENE COMPROBANTE FISCALES CONFIGURADO
//
//                System.out.println(" EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL  " + relacionNcf);
////
////                ClaseUtil.mensaje(" La secuencia de ncf para el tipo " + fac.getCliente().getTipoNcf().getDescripcion() + " de la unidad de negocio "
////                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
//
////                System.out.println("relacionNcf  " + relacionNcf);
//                relacionNcf = ManejoRelacionNcf.getInstancia()
//                        .getNCFUnidadDeNegocio(fac.getCliente().getTipoNcf(), 3);//EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL
//                System.out.println(" EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL  " + relacionNcf);
//
////               return null;
//            }
            fac.setNcf("B0000001");
            fac.setNcfFechaValidoHasta(new Date());
            fac.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            System.out.println("Sin ncf");

            facturaDb = ManejoFacturaTemporal.getInstancia().salvar(fac);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return facturaDb;
    }

    @FXML
    private void btnBuscarClienteActionEvent(ActionEvent event) throws IOException {

        listaContrato.clear();
        listaDatosVehiculo.clear();
        listaDetalleContrato.clear();
        listaFacturaTemporal.clear();

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/cliente/FXMLBusCliente.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        FXMLBusClienterController articuloController = loader.getController();

        if (!(articuloController.getCliente() == null)) {

            setCliente(articuloController.getCliente());

            txtCliente.setText(getCliente().getNombre());

            listaContrato.addAll(ManejoContratoDeServicio.getInstancia().getListaContratoPorCliente(getCliente(), 1));

            tabPane.getSelectionModel().selectFirst();
            for (ContratoDeServicio c : listaContrato) {
                c.setFacturado(false);
                c.setCantidadMes(0);
            }

            btnGenerarFacturaRecurrente.setDisable(false);

        } else {
            btnGenerarFacturaRecurrente.setDisable(true);
        }

    }

    @FXML
    private void dpFechaFacturacionActionEvent(ActionEvent event) {
    }

    @FXML
    private void tbDetalleContratoMouseClicked(MouseEvent event) {
//
//        if (!(tbContratoDeServicio.getSelectionModel().getSelectedIndex() == -1)) {
//            int controto = 0;
//            System.out.println(" tbDetalleContrato.getSelectionModel().getSelectedItem() "
//                    + tbDetalleContrato.getSelectionModel().getSelectedItem());
//            listaDatosVehiculo.clear();
//            if (!(tbDetalleContrato.getSelectionModel().getSelectedItem() == null)) {
//                controto = tbContratoDeServicio.getSelectionModel().getSelectedItem().getCodigo();
//            }
//
//            System.out.println("contrto " + controto);
//
//            listaDatosVehiculo.addAll(ManejoContratoDeServicio.getInstancia().getDatosVehiculoPorContrto(controto));
//
//            if (event.getClickCount() == 3) {
//                tabPane.getSelectionModel().select(2);
//            }
//        }

    }

    @FXML
    private void tbDetalleReciboMouseClicked(MouseEvent event) {
    }

    @FXML
    private void btnReciboIngresoActionEvent(ActionEvent event) {

        try {

            System.out.println("factDb recibo: " + factDb);
            if (factDb == null) {

                ClaseUtil.mensaje("Tiene que guardar una factura para poder crearle el recibo ");
                return;
            }

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/cxc/reciboIngreso/RegistroReciboIngreso.fxml"));

            loader.load();

            Parent root = loader.getRoot();

            RegistroReciboDeIngresoController controller = loader.getController();
            controller.setCliente(factDb.getCliente());

            controller.llenarCampo();

            ClaseUtil.getStageModalcONTRATO(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class TaskGenerarFacturaTemporales extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            try {

                listaFacturaTemporal.clear();
                int total = listaContrato.size();
                int i = 0;

                for (ContratoDeServicio contrato : listaContrato) {
                    i++;
                    generarFactTemporales(contrato, i, total);
                    this.updateProgress(i, total);
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;

        }

        private void generarFactTemporales(ContratoDeServicio contrato, int i, int total) throws InterruptedException {

            try {

                CorteDeFacturacion corteFact = new CorteDeFacturacion();

                FacturaTemporal factura;
                DetalleFacturaTemporal detalleFactura;

                List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();

                corteFact.setFecha(fechaFacturacion);
                corteFact.setFechaRegistro(new Date());
                corteFact.setUsuario(VariablesGlobales.USUARIO);

                int num = 0;

                factura = new FacturaTemporal();

                //****************************************
                System.out.println("contrato.getCliente() " + contrato.getCliente().getTipoNcf());
                factura.setAbono(0.00);
                factura.setCliente(contrato.getCliente());
                factura.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
                factura.setMonto(contrato.getTotalAPagar());
                factura.setAnulada(false);
                factura.setNumeroContrato(contrato.getNumeroDeContrato());
                factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

                SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                        .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
                factura.setNumero(secDoc.getNumero() + num);

                factura.setNcf("na");
                factura.setTotal(factura.getMonto());
                factura.setSubTotal(contrato.getSubTotal());
                factura.setFecha(fechaFacturacion);
                factura.setDescuento(0.00);
                factura.setItbis(contrato.getItbis());
                factura.setUsuario(VariablesGlobales.USUARIO);
                factura.setVendedor(contrato.getEjecutivoDeVenta());

                factura.setFechaCreacion(new Date());

                Date fechaVencimiento = fechaFacturacion;
                factura.setFechaVencimiento(fechaVencimiento);

                factura.setPagada(false);
                factura.setTipoVenta(2);
                factura.setAbono(0.0);
                factura.setPendiente(factura.getTotal());
                factura.setCondicionPago(new CondicionPago(2));
                factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
                fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, factura.getCliente().getDiasCredito());
                factura.setFechaVencimiento(fechaVencimiento);
                factura.setDespachada(false);

                FormaPago formaPago = new FormaPago();
                TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
                formaPago.setTipoFormaPago(tipoFormaPago);
                formaPago.setMonto(factura.getTotal());

                //agregando detalle a la factura
                listaDetContrato.clear();
                listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
                        .getDetalleContratoVencido(contrato.getCodigo(), fechaVencimiento));

                System.out.println("NO HUBO CLIENTE CON NCF NULO ");

                Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;

                listaDetalleFacturaTemporal.clear();
                listaDetalleFacDdatosVehiculo.clear();
                for (DetalleContratoDeServicio detCto : listaDetContrato) {

                    System.out.println("detCto.getEquipo().getTipoArticulo().getCodigo() " + detCto.getEquipo().getTipoArticulo().getCodigo());
//                    if (detCto.getEquipo().getTipoArticulo().getCodigo() == 3) {//solo se creanran factura por servicio 

                    detalleFactura = new DetalleFacturaTemporal();
                    // *****************************************

                    long dias = ClaseUtil.diferenciaDiasEntreDosFecha(detCto.getFechaHasta(), fechaVencimiento);

                    Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();

                    int diasInt = Integer.parseInt(Long.toString(dias));

                    if (contrato.getNuevo()) {

                        if (dias < 30) {

                            System.out.println("dias MENOR A 30" + dias + "CONTRATO " + contrato.getCodigo());
                            Double precioPorDia = ClaseUtil.roundDouble(detCto.getPrecioPagado() / 30, 2);
                            precioPorDia = precioPorDia * dias;
                            detalleFactura.setPrecio(precioPorDia);

                        } else {

                            System.out.println("dias mayor de 30 " + dias);
                            detalleFactura.setPrecio(detCto.getPrecioPagado());
                        }
                    } else {
                        detalleFactura.setPrecio(detCto.getPrecioPagado());
                    }

                    int cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();

                    int cantidadGPS = detCto.getCantidad();
                    String desc = "";
                    Date fechaVencimientoCal = null;

//                    if (dias < 30) {
//
//                        System.out.println("dias menor de 30 " + dias);
//                        fechaVencimientoCal = ClaseUtil.Fechadiadespues(detCto.getFechaUltimoPagoHasta(), diasInt);//Calculamos la fecha de vencimiento
//
//                    } else {
                    System.out.println("dias mayor de 30 " + dias);
                    fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadFrecuenciaPago);//Calculamos la fecha de vencimiento
                    // en base a la cantidad de pago por a delantado

                    System.out.println("fechaVencimientoCal " + fechaVencimientoCal);
//                    }

//                    detCto.setFechaUltimoPagoDesde(detCto.getFechaUltimoPagoHasta());//Factura  que cubre el pago desde esta fecha hasta la fecha calculada
//
//                    detCto.setFechaUltimoPagoHasta(fechaVencimientoCal); //fechaVencimientoCal   a hora es el setFechaUltimoPagoHasta   
                    if (detCto.getTipoDeServicio().getCodigo() == 1) {

                        desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                                + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                                + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);

                    } else if (detCto.getTipoDeServicio().getCodigo() == 2) {

                        desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR " + cantidadGPS + " SERVICIO "
                                + "  DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                                + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);
                    } else {

                        desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                                + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                                + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);
                    }

                    System.out.println("descripcion pago " + desc);

                    detalleFactura.setDescripcionPago(desc);
                    detalleFactura.setFactura(factura);
                    detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
                    detalleFactura.setArticulo(detCto.getEquipo());
                    cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();//CAntidad facturado

                    detalleFactura.setCantidad(cantidad);

                    subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
                    detalleFactura.setSubTotal(subTotal);

                    valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);

                    detalleFactura.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));

                    detalleFactura.setDescuento(0.00);
                    detalleFactura.setExistencia(0.00);
                    detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
                    detalleFactura.setNuevaExistencia(0.0);

                    detalleFactura.setItbis(valorItbis);
                    detalleFactura.setTasaItbis(detCto.getTasaItbis());

                    detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
                    detalleFactura.setUnidad(unidad);

                    listaDetalleFacturaTemporal.add(detalleFactura);
                }

                try {

                    System.out.println(" listaDetalleFactura " + listaDetalleFacturaTemporal + " listaDetalleFactura.size() " + listaDetalleFacturaTemporal.size());

                    if (listaDetalleFacturaTemporal.size() > 0) {

                        factura.setTotal(getTotalTemp(listaDetalleFacturaTemporal));
                        factura.setSubTotal(getSubTotalTewmp(listaDetalleFacturaTemporal));
                        factura.setItbis(getItbisTemp(listaDetalleFacturaTemporal));
                        factura.setMonto(getTotalTemp(listaDetalleFacturaTemporal));
                        factura.setPendiente(getTotalTemp(listaDetalleFacturaTemporal));

                        factura.setDetalleFacturaTemporalCollection(listaDetalleFacturaTemporal);

                        FacturaTemporal factDb = guardarFacturaTempotal(factura);

                        if (factDb == null) {

                            ClaseUtil.mensaje(" Hubo Error creando la factura ");
                            return;

                        }

                        listaFacturaTemporal.add(factDb);

                    }

                    this.updateMessage(" Procesada  " + i + "  De " + total);

                } catch (Exception e) {

                    e.printStackTrace();
                }

                num++;

            } catch (Exception e) {

                System.out.println("Hubo un error guardando la factura ");
//                ClaseUtil.mensaje("Hubo un error guardando la factura ");
                e.printStackTrace();
            }

            Thread.sleep(200);
        }

    }

    private class TaskGenerarFactura extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            try {

                int total = listaContrato.size();
                int i = 0;

                for (ContratoDeServicio contrato : listaContrato) {
                    i++;
                    generarFactura(contrato, i, total);
                    this.updateProgress(i, total);
                }

                listaContrato.clear();
                listaFacturaTemporal.clear();
                ManejoFacturaTemporal.getInstancia().eliminarFacturaTemporal();

            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;

        }

        private void generarFactura(ContratoDeServicio contrato, int i, int total) throws InterruptedException {

            CorteDeFacturacion corteFact = new CorteDeFacturacion();
            DetalleCorteDeFacturacion detaCorte;
            List<DetalleCorteDeFacturacion> listaDetCorte = new ArrayList<>();
            Factura factura;
            DetalleFactura detalleFactura;
            FacturaDatosDeVehiculo facturaDatosDeVehiculo;

            List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();
            List<DatosDeVehiculo> listaDetVehiculo = new ArrayList<>();

            corteFact.setFecha(fechaFacturacion);
            corteFact.setFechaRegistro(new Date());
            corteFact.setUsuario(VariablesGlobales.USUARIO);

            int num = 0;

            factura = new Factura();

            //****************************************
            System.out.println("contrato.getCliente() " + contrato.getCliente().getTipoNcf());
            factura.setAbono(0.00);
            factura.setCliente(contrato.getCliente());
            factura.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
            factura.setMonto(contrato.getTotalAPagar());
            factura.setAnulada(false);
            factura.setNumeroContrato(contrato.getNumeroDeContrato());
            factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
            factura.setNumero(secDoc.getNumero() + num);

            factura.setNcf("na");
            factura.setTotal(factura.getMonto());
            factura.setSubTotal(contrato.getSubTotal());
            factura.setFecha(fechaFacturacion);
            factura.setDescuento(0.00);
            factura.setItbis(contrato.getItbis());
            factura.setUsuario(VariablesGlobales.USUARIO);
            factura.setVendedor(contrato.getEjecutivoDeVenta());

            factura.setFechaCreacion(new Date());

            Date fechaVencimiento = fechaFacturacion;
            factura.setFechaVencimiento(fechaVencimiento);

            factura.setPagada(false);
            factura.setTipoVenta(2);
            factura.setAbono(0.0);
            factura.setPendiente(factura.getTotal());
            factura.setCondicionPago(new CondicionPago(2));
            factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
            fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, factura.getCliente().getDiasCredito());
            factura.setFechaVencimiento(fechaVencimiento);
            factura.setDespachada(false);

            FormaPago formaPago = new FormaPago();
            TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
            formaPago.setTipoFormaPago(tipoFormaPago);
            formaPago.setMonto(factura.getTotal());

            //agregando detalle a la factura
            listaDetContrato.clear();
            listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
                    .getDetalleContratoVencido(contrato.getCodigo(), fechaVencimiento));

            System.out.println("NO HUBO CLIENTE CON NCF NULO ");

            Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;

            listaDetalleFactura.clear();
            listaDetalleFacDdatosVehiculo.clear();
            for (DetalleContratoDeServicio detCto : listaDetContrato) {

                System.out.println("detCto.getEquipo().getTipoArticulo().getCodigo() " + detCto.getEquipo().getTipoArticulo().getCodigo());
//                    if (detCto.getEquipo().getTipoArticulo().getCodigo() == 3) {//solo se creanran factura por servicio 

                detalleFactura = new DetalleFactura();
                // *****************************************

                long dias = ClaseUtil.diferenciaDiasEntreDosFecha(detCto.getFechaHasta(), fechaVencimiento);

                Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();

                int diasInt = Integer.parseInt(Long.toString(dias));

                if (contrato.getNuevo()) {

                    if (dias < 30) {

                        System.out.println("dias MENOR A 30" + dias);
                        Double precioPorDia = ClaseUtil.roundDouble(detCto.getPrecioPagado() / 30, 2);
                        precioPorDia = precioPorDia * dias;
                        detalleFactura.setPrecio(precioPorDia);

                    } else {

                        System.out.println("dias mayor de 30 " + dias);
                        detalleFactura.setPrecio(detCto.getPrecioPagado());
                    }
                } else {
                    detalleFactura.setPrecio(detCto.getPrecioPagado());
                }

                int cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();
                int cantidadGPS = detCto.getCantidad();
                String desc = "";
                Date fechaVencimientoCal = null;

                System.out.println("dias mayor de 30 " + dias);
                fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadFrecuenciaPago);//Calculamos la fecha de vencimiento
                // en base a la cantidad de pago por a delantado

                System.out.println("fechaVencimientoCal " + fechaVencimientoCal);

//                if (dias < 30) {
//
//                    fechaVencimientoCal = ClaseUtil.Fechadiadespues(detCto.getFechaUltimoPagoHasta(), diasInt);//Calculamos la fecha de vencimiento
//
//                } else {
//
//                    fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadFrecuenciaPago);//Calculamos la fecha de vencimiento
//                    // en base a la cantidad de pago por adelantado
//                }
                //*************************************************************************
                detCto.setFechaUltimoPagoDesde(detCto.getFechaUltimoPagoHasta());//Factura  que cubre el pago desde esta fecha hasta la fecha calculada

                detCto.setFechaUltimoPagoHasta(fechaVencimientoCal); //fechaVencimientoCal   a hora es el setFechaUltimoPagoHasta    

                if (detCto.getTipoDeServicio().getCodigo() == 1) {

                    desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                            + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                            + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);

                } else if (detCto.getTipoDeServicio().getCodigo() == 2) {

                    desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR " + cantidadGPS + " SERVICIO "
                            + "  DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                            + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);
                } else {

                    desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                            + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                            + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);
                }

                //***********************************************************************
//                detCto.setFechaUltimoPagoDesde(detCto.getFechaUltimoPagoHasta());//Factura  que cubre el pago desde esta fecha hasta la fecha calculada
//
//                detCto.setFechaUltimoPagoHasta(fechaVencimientoCal); //fechaVencimientoCal   a hora es el setFechaUltimoPagoHasta    
//
//                desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
//                        + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoDesde())
//                        + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta());
                System.out.println("descripcion pago " + desc);
                detalleFactura.setDescripcionPago(desc);
                detalleFactura.setFactura(factura);
                detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
                detalleFactura.setArticulo(detCto.getEquipo());
                cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();//CAntidad facturado

                detalleFactura.setCantidad(cantidad);

                subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
                detalleFactura.setSubTotal(subTotal);

                valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);

                detalleFactura.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));

                detalleFactura.setDescuento(0.00);
                detalleFactura.setExistencia(0.00);
                detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
                detalleFactura.setNuevaExistencia(0.0);

                detalleFactura.setItbis(valorItbis);
                detalleFactura.setTasaItbis(detCto.getTasaItbis());

                detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
                detalleFactura.setUnidad(unidad);

                //agregando detalle a la factura de los vehiculos 
                listaDetVehiculo.clear();
                listaDetVehiculo.addAll(ManejoContratoDeServicio.getInstancia()
                        .getDatosVehiculoo(detCto.getCodigo()));

                System.out.println(" datos vehiculo " + listaDetVehiculo.size() + " detCto.getCodigo() " + detCto.getCodigo());

                for (DatosDeVehiculo detVeh : listaDetVehiculo) {

                    System.out.println("configurando  factura datos de vehiculo ");
                    facturaDatosDeVehiculo = new FacturaDatosDeVehiculo();
                    // *****************************************

                    facturaDatosDeVehiculo.setFactura(detalleFactura);
                    facturaDatosDeVehiculo.setAdicional(detVeh.getAdicional());
                    facturaDatosDeVehiculo.setAnio(detVeh.getAnio());
                    facturaDatosDeVehiculo.setChasis(detVeh.getChasis());
                    facturaDatosDeVehiculo.setColor(detVeh.getColor());
                    facturaDatosDeVehiculo.setMarca(detVeh.getMarca());
                    facturaDatosDeVehiculo.setMatricula(detVeh.getMatricula());
                    facturaDatosDeVehiculo.setModelo(detVeh.getModelo());
                    facturaDatosDeVehiculo.setPlaca(detVeh.getPlaca());
                    facturaDatosDeVehiculo.setTipoVehiculo(detVeh.getTipoVehiculo());
                    facturaDatosDeVehiculo.setVehiculo(detVeh.getVehiculo());

                    listaDetalleFacDdatosVehiculo.add(facturaDatosDeVehiculo);

                }

                listaDetalleFactura.add(detalleFactura);

            }

            try {

                System.out.println(" listaDetalleFactura " + listaDetalleFactura + " listaDetalleFactura.size() " + listaDetalleFactura.size());

                if (listaDetalleFactura.size() > 0) {

                    factura.setTotal(getTotal(listaDetalleFactura));
                    factura.setSubTotal(getSubTotal(listaDetalleFactura));
                    factura.setItbis(getItbis(listaDetalleFactura));
                    factura.setMonto(getTotal(listaDetalleFactura));
                    factura.setPendiente(getTotal(listaDetalleFactura));

                    factura.setDetalleFacturaCollection(listaDetalleFactura);

                    Double balancePendiente = ManejoFactura.getInstancia().getMontoPendiente(factura.getCliente());

                    Factura factDb = guardar(factura);

                    if (factDb == null) {

                        ClaseUtil.mensaje(" Hubo Error creando la factura ");
                        return;

                    } else {

                        listaDetalleFacDdatosVehiculo.forEach((fctdvh) -> {
                            System.out.println("item " + fctdvh);
                            ManejoFacturaDatosDeVehiculo.getInstancia().salvar(fctdvh);
                        });
                        //Actualizar el ncf 
                        ManejoRelacionNcf.getInstancia().actualizar(relacionNcf);

                        //Generando el historico de corte de facturacion 
                        detaCorte = new DetalleCorteDeFacturacion();

                        detaCorte.setFactura(factura);
                        detaCorte.setNumeroFactura(factura.getNumero());
                        detaCorte.setContrato(contrato);
                        detaCorte.setNumeroContrato(contrato.getNumeroDeContrato());
                        detaCorte.setCorteDeFacturacion(corteFact);
                        detaCorte.setTotalFactura(factura.getTotal());
                        listaDetCorte.add(detaCorte);

                        contrato.setDetalleContratoDeServicioCollection(listaDetContrato);
                        ManejoContratoDeServicio.getInstancia().actualizar(contrato);

                        HistoricoBalancePendiente hBalance = new HistoricoBalancePendiente();

                        hBalance.setCliente(factura.getCliente().getCodigo());
                        hBalance.setFactura(factura.getCodigo());
                        hBalance.setFechaVencimiento(factura.getFecha());
                        hBalance.setTotal(balancePendiente);

                        ManejoHistoricoBalancePendiente.getInstancia().salvar(hBalance);

                        this.updateMessage(" Procesada  " + i + "  De " + total);
                    }
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            num++;

            corteFact.setCantidadFactura(num);
            corteFact.setDetalleCorteDeFacturacionCollection(listaDetCorte);

            ManejoCorteDeFacturacion.getInstancia().salvar(corteFact);

            Thread.sleep(200);
        }

    }

    private void generarFactTemporales(ContratoDeServicio contrato, List<DetalleContratoDeServicio> listaDet) throws InterruptedException {

        try {

            CorteDeFacturacion corteFact = new CorteDeFacturacion();

            FacturaTemporal factura;
            DetalleFacturaTemporal detalleFactura, detalleFactura2 = null;

            List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();

            fechaFacturacion = ClaseUtil.asDate(dpFechaFacturacion.getValue());
            corteFact.setFecha(fechaFacturacion);
            corteFact.setFechaRegistro(new Date());
            corteFact.setUsuario(VariablesGlobales.USUARIO);

            int num = 0;

            factura = new FacturaTemporal();

            //****************************************
            System.out.println("contrato.getCliente() " + contrato.getCliente().getTipoNcf());
            factura.setAbono(0.00);
            factura.setCliente(contrato.getCliente());
            factura.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
            factura.setMonto(contrato.getTotalAPagar());
            factura.setAnulada(false);
            factura.setNumeroContrato(contrato.getNumeroDeContrato());
            factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            factura.setSecConComp(contrato.getCantidadMes());

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
            factura.setNumero(secDoc.getNumero() + num);

            factura.setNcf("na");
            factura.setTotal(factura.getMonto());
            factura.setSubTotal(contrato.getSubTotal());
            factura.setFecha(new Date());
            factura.setDescuento(0.00);
            factura.setItbis(contrato.getItbis());
            factura.setUsuario(VariablesGlobales.USUARIO);
            factura.setVendedor(contrato.getEjecutivoDeVenta());

            factura.setFechaCreacion(new Date());

            Date fechaVencimiento = fechaFacturacion;
            factura.setFechaVencimiento(fechaVencimiento);

            factura.setPagada(false);
            factura.setTipoVenta(2);
            factura.setAbono(0.0);
            factura.setPendiente(factura.getTotal());
            factura.setCondicionPago(new CondicionPago(2));
            factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
            fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, factura.getCliente().getDiasCredito());
            factura.setFechaVencimiento(fechaVencimiento);
            factura.setDespachada(false);

            FormaPago formaPago = new FormaPago();
            TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
            formaPago.setTipoFormaPago(tipoFormaPago);
            formaPago.setMonto(factura.getTotal());

            System.out.println("NO HUBO CLIENTE CON NCF NULO ");

            Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;

            listaDetalleFacturaTemporal.clear();
            listaDetalleFacDdatosVehiculo.clear();

            if (listaDet.size() <= 0) {
                ClaseUtil.mensaje("No hay servicio  para crear renovacion ");
                return;
            }

//            Double dias = 30.00;
            int diaDeRenovacion = 0, mesDeRenovacion = 0;
            diaDeRenovacion = ManejoUnidadDeNegocio.getInstancia().getUnidad(2).getDiaDeRenovacion();
            mesDeRenovacion = ManejoUnidadDeNegocio.getInstancia().getUnidad(2).getMesDeRenovacion();

            for (DetalleContratoDeServicio detCto : listaDet) {

                System.out.println("detCto.getEquipo().getTipoArticulo().getCodigo() " + detCto.getEquipo().getTipoArticulo().getCodigo());
//                    if (detCto.getEquipo().getTipoArticulo().getCodigo() == 3) {//solo se creanran factura por servicio 

                detalleFactura = new DetalleFacturaTemporal();
                detalleFactura2 = new DetalleFacturaTemporal();
                String canAnio = String.valueOf(detCto.getCantidadAnio());
                detCto.setCantidadFrecuenciaDePago(Double.valueOf(canAnio));
                // *****************************************
//
//                long dias = ClaseUtil.diferenciaDiasEntreDosFecha(detCto.getFechaHasta(), fechaVencimiento);
//
                Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();
                Double dias = 360.00;

                if (!(detCto.getPrecioRenovacion() == null)) {
                    System.out.println("detCto.getPrecioRenovacion() " + detCto.getPrecioRenovacion());

                    detalleFactura.setPrecio(detCto.getPrecioRenovacion());
                } else {
                    System.out.println("detCto.getPrecioPagado() " + detCto.getPrecioPagado());
                    detalleFactura.setPrecio(detCto.getPrecioPagado());

                }

                int cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();//en años

                int cantidadGPS = detCto.getCantidad();
                String desc = "", desc2 = "";
                Date fechaVencimientoCal = null;
                Date fecha = detCto.getFechaUltimoPagoHasta();

                System.out.println("(detCto.getFechaUltimoPagoHasta() " + detCto.getFechaUltimoPagoHasta() + " cantidadFrecuenciaPago " + cantidadFrecuenciaPago);
                fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadFrecuenciaPago * 12);//Calculamos la fecha de vencimiento en meses
                // en base a la cantidad de pago por a delantado

                System.out.println("fechaVencimientoCal " + fechaVencimientoCal);
                Double precioPagado = detalleFactura.getPrecio();

                if (detCto.getTipoDeServicio().getCodigo() == 1) {

                    if (chNuevaModalidad.isSelected()) {

                        Double cantidadFecuenciaPago = detCto.getCantidadFrecuenciaDePago();
                        Double cantidadFecuenciaPagoDias = 0.00;
                        Double frecuencia = cantidadFecuenciaPago;

                        cantidadFecuenciaPagoDias = util.ClaseUtil.getDiasCorteContrato(fecha).doubleValue();
                        Date fechacorte = util.ClaseUtil.getFechaCorteContrato(fecha, cantidadFecuenciaPagoDias.intValue());
                        System.out.println("fechacorte : " + fechacorte);

                        fechacorteAnio = util.ClaseUtil.getConstruirFecha(fechacorte, diaDeRenovacion, mesDeRenovacion);

                        frecuencia = util.ClaseUtil.getMesesEntreFecha(fecha, fechacorteAnio).doubleValue();

                        fechaVencimiento = ClaseUtil.FechaMesDespues(fechacorteAnio, cantidadFecuenciaPago.intValue() * 12);

                        if (frecuencia > 1) {

                            desc = frecuencia.intValue() + "  MESES "
                                    + "  Y " + cantidadFecuenciaPagoDias.intValue() + " DIAS  "
                                    + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion()
                                    + " POR SERVICIO DE " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                    + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio);
                        } else {
                            desc = frecuencia.intValue() + "  MES "
                                    + "  Y " + cantidadFecuenciaPagoDias.intValue() + " DIAS  "
                                    + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion()
                                    + " POR SERVICIO DE " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                    + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio);
                        }

                        System.out.println("precioPagado : " + precioPagado);
                        precioPorDia = ClaseUtil.roundDouble(precioPagado / dias, 2);
                        System.out.println("precioPorDia : " + precioPorDia);
                        cantidadFecuenciaPagoDias += frecuencia * 30;
                        precioPorDia = precioPorDia * cantidadFecuenciaPagoDias;
                        detalleFactura.setPrecio(precioPorDia);

                        if (chNuevaModalidRnv.isSelected()) {

                            desc2 = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                                    + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio)
                                    + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
                        }

                    } else {

                        desc = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                                + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                                + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);
                    }

                } else if (detCto.getTipoDeServicio().getCodigo() == 2) {

                    if (chNuevaModalidad.isSelected()) {

                        precioPagado = detalleFactura.getPrecio();
                        Double cantidadFecuenciaPago = detCto.getCantidadFrecuenciaDePago();
                        Double cantidadFecuenciaPagoDias = 0.00;
                        Double frecuencia = cantidadFecuenciaPago;

                        cantidadFecuenciaPagoDias = util.ClaseUtil.getDiasCorteContrato(fecha).doubleValue();
                        Date fechacorte = util.ClaseUtil.getFechaCorteContrato(fecha, cantidadFecuenciaPagoDias.intValue());
                        System.out.println("fechacorte : " + fechacorte);

                        fechacorteAnio = util.ClaseUtil.getConstruirFecha(fechacorte, diaDeRenovacion, mesDeRenovacion);

                        frecuencia = util.ClaseUtil.getMesesEntreFecha(fecha, fechacorteAnio).doubleValue();

                        fechaVencimiento = ClaseUtil.FechaMesDespues(fechacorteAnio, cantidadFecuenciaPago.intValue() * 12);
                        if (frecuencia > 1) {

                            desc = frecuencia.intValue() + "  MESES "
                                    + "  Y " + cantidadFecuenciaPagoDias.intValue() + " DIAS  "
                                    + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR " + cantidadGPS + " SERVICIO "
                                    + "DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                    + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio);
                        } else {

                            desc = frecuencia.intValue() + "  MES "
                                    + "  Y " + cantidadFecuenciaPagoDias.intValue() + " DIAS  "
                                    + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR " + cantidadGPS + " SERVICIO "
                                    + "DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                    + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio);
                        }

                        precioPorDia = ClaseUtil.roundDouble(precioPagado / dias, 2);
                        cantidadFecuenciaPagoDias += frecuencia * 30;
                        precioPorDia = precioPorDia * cantidadFecuenciaPagoDias;
                        detalleFactura.setPrecio(precioPorDia);

                        if (chNuevaModalidRnv.isSelected()) {

                            desc2 = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR " + cantidadGPS + " SERVICIO "
                                    + "  DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio)
                                    + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
                        }

                    } else {

                        desc = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR " + cantidadGPS + " SERVICIO "
                                + "  DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                                + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);
                    }

                } else {

                    if (chNuevaModalidad.isSelected()) {

                        precioPagado = detalleFactura.getPrecio();
                        Double cantidadFecuenciaPago = detCto.getCantidadFrecuenciaDePago();
                        Double cantidadFecuenciaPagoDias = 0.00;
                        Double frecuencia = cantidadFecuenciaPago;

                        cantidadFecuenciaPagoDias = util.ClaseUtil.getDiasCorteContrato(fecha).doubleValue();
                        Date fechacorte = util.ClaseUtil.getFechaCorteContrato(fecha, cantidadFecuenciaPagoDias.intValue());
                        System.out.println("fechacorte : " + fechacorte);

                        fechacorteAnio = util.ClaseUtil.getConstruirFecha(fechacorte, diaDeRenovacion, mesDeRenovacion);

                        frecuencia = util.ClaseUtil.getMesesEntreFecha(fecha, fechacorteAnio).doubleValue();

                        fechaVencimiento = ClaseUtil.FechaMesDespues(fechacorteAnio, cantidadFecuenciaPago.intValue() * 12);

                        if (frecuencia > 1) {

                            desc = frecuencia.intValue() + "  MESES "
                                    + "  Y " + cantidadFecuenciaPagoDias.intValue() + " DIAS  " + "  RENOVACION DE "
                                    + detCto.getFrecuenciaDePago().getDescripcion()
                                    + " POR SERVICIO DE " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                    + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio);
                        } else {

                            desc = frecuencia.intValue() + "  MES "
                                    + "  Y " + cantidadFecuenciaPagoDias.intValue() + " DIAS  " + "  RENOVACION DE "
                                    + detCto.getFrecuenciaDePago().getDescripcion()
                                    + " POR SERVICIO DE " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                    + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio);
                        }

                        precioPorDia = ClaseUtil.roundDouble(precioPagado / dias, 2);
                        cantidadFecuenciaPagoDias += frecuencia * 30;
                        precioPorDia = precioPorDia * cantidadFecuenciaPagoDias;
                        detalleFactura.setPrecio(precioPorDia);

                        if (chNuevaModalidRnv.isSelected()) {

                            desc2 = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                                    + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio)
                                    + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);

                        }

                    } else {

                        desc = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                                + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                                + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);
                    }

                }

                System.out.println("descripcion pago " + desc);

                detalleFactura.setDescripcionPago(desc);
                detalleFactura.setFactura(factura);
                detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
                detalleFactura.setArticulo(detCto.getEquipo());
                cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();//CAntidad facturado

                detalleFactura.setCantidad(cantidad);

                subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
                detalleFactura.setSubTotal(subTotal);

                valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);

                detalleFactura.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));

                detalleFactura.setDescuento(0.00);
                detalleFactura.setExistencia(0.00);
                detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
                detalleFactura.setNuevaExistencia(0.0);

                detalleFactura.setItbis(valorItbis);
                detalleFactura.setTasaItbis(detCto.getTasaItbis());

                detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
                detalleFactura.setUnidad(unidad);
                listaDetalleFacturaTemporal.add(detalleFactura);

                if (chNuevaModalidRnv.isSelected()) {

                    detalleFactura2 = new DetalleFacturaTemporal();

                    detalleFactura2.setPrecio(precioPagado);
                    detalleFactura2.setDescripcionPago(desc2);
                    detalleFactura2.setFactura(factura);
                    detalleFactura2.setCantidad(Double.valueOf(detCto.getCantidad()));
                    detalleFactura2.setArticulo(detCto.getEquipo());
                    cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura2.getCantidad();//Cantidad facturado

                    detalleFactura2.setCantidad(cantidad);

                    subTotal = ClaseUtil.roundDouble(detalleFactura2.getCantidad() * detalleFactura2.getPrecio(), 2);
                    detalleFactura2.setSubTotal(subTotal);

                    valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura2.getSubTotal(), 2);

                    detalleFactura2.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));

                    detalleFactura2.setDescuento(0.00);
                    detalleFactura2.setExistencia(0.00);
                    detalleFactura2.setNombreArticulo(detCto.getEquipo().getNombre());
                    detalleFactura2.setNuevaExistencia(0.0);

                    detalleFactura2.setItbis(valorItbis);
                    detalleFactura2.setTasaItbis(detCto.getTasaItbis());

                    detalleFactura2.setTotal(detalleFactura2.getItbis() + detalleFactura2.getSubTotal());
                    detalleFactura2.setUnidad(unidad);
                    listaDetalleFacturaTemporal.add(detalleFactura2);
                }

            }

            try {

                System.out.println(" listaDetalleFactura " + listaDetalleFacturaTemporal + " listaDetalleFactura.size() " + listaDetalleFacturaTemporal.size());

                if (listaDetalleFacturaTemporal.size() > 0) {

                    factura.setTotal(getTotalTemp(listaDetalleFacturaTemporal));
                    factura.setSubTotal(getSubTotalTewmp(listaDetalleFacturaTemporal));
                    factura.setItbis(getItbisTemp(listaDetalleFacturaTemporal));
                    factura.setMonto(getTotalTemp(listaDetalleFacturaTemporal));
                    factura.setPendiente(getTotalTemp(listaDetalleFacturaTemporal));

                    factura.setDetalleFacturaTemporalCollection(listaDetalleFacturaTemporal);

                    FacturaTemporal factDb = guardarFacturaTempotal(factura);

                    if (factDb == null) {

                        ClaseUtil.mensaje(" Hubo Error creando la factura ");
                        return;

                    }

                    listaFacturaTemporal.add(factDb);
                    tabPane.getSelectionModel().selectLast();

                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            num++;

        } catch (Exception e) {

            System.out.println("Hubo un error guardando la factura ");
//                ClaseUtil.mensaje("Hubo un error guardando la factura ");
            e.printStackTrace();
        }

    }

    private void generarFactura(ContratoDeServicio contrato, List<DetalleContratoDeServicio> listaDet) throws InterruptedException {

        CorteDeFacturacion corteFact = new CorteDeFacturacion();
        DetalleCorteDeFacturacion detaCorte;
        List<DetalleCorteDeFacturacion> listaDetCorte = new ArrayList<>();
        Factura factura;
        DetalleFactura detalleFactura, detalleFactura2;
        FacturaDatosDeVehiculo facturaDatosDeVehiculo;

        List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();
        List<DatosDeVehiculo> listaDetVehiculo = new ArrayList<>();

        fechaFacturacion = ClaseUtil.asDate(dpFechaFacturacion.getValue());
        corteFact.setFecha(fechaFacturacion);
        corteFact.setFechaRegistro(new Date());
        corteFact.setUsuario(VariablesGlobales.USUARIO);

        int num = 0;

        factura = new Factura();

        //****************************************
        System.out.println("contrato.getCliente() " + contrato.getCliente().getTipoNcf());
        factura.setAbono(0.00);
        factura.setCliente(contrato.getCliente());
        factura.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
        factura.setMonto(contrato.getTotalAPagar());
        factura.setAnulada(false);
        factura.setNumeroContrato(contrato.getNumeroDeContrato());
        factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
        factura.setOrigenFactura(ManejoOrigenFactura.getInstancia().getOrigenFactura(4));
        factura.setVendedor(contrato.getEjecutivoDeVenta());

        SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
        factura.setNumero(secDoc.getNumero() + num);

        factura.setNcf("na");
        factura.setTotal(factura.getMonto());
        factura.setSubTotal(contrato.getSubTotal());
        factura.setFecha(new Date());
//        factura.setFecha(fechaFacturacion);
        factura.setDescuento(0.00);
        factura.setItbis(contrato.getItbis());
        factura.setUsuario(VariablesGlobales.USUARIO);

        factura.setFechaCreacion(new Date());

        Date fechaVencimiento = fechaFacturacion;
        factura.setFechaVencimiento(fechaVencimiento);

        factura.setPagada(false);
        factura.setTipoVenta(2);
        factura.setAbono(0.0);
        factura.setPendiente(factura.getTotal());
        factura.setCondicionPago(new CondicionPago(2));
        factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
        fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, factura.getCliente().getDiasCredito());
        factura.setFechaVencimiento(fechaVencimiento);
        factura.setDespachada(false);
        factura.setSecConComp(contrato.getCantidadMes());
        factura.setFormato(1);

        FormaPago formaPago = new FormaPago();
        TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
        formaPago.setTipoFormaPago(tipoFormaPago);
        formaPago.setMonto(factura.getTotal());
        formaPago.setUnidadDeNegocio(factura.getUnidadDeNegocio().getCodigo());

        //agregando detalle a la factura
//        listaDetContrato.clear();
//        listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
//                .getDetalleContratoHabilitado(contrato.getCodigo(), param));
        System.out.println("NO HUBO CLIENTE CON NCF NULO ");

        Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00, dias = 360.00;

        listaDetalleFactura.clear();
        listaDetalleFacDdatosVehiculo.clear();
        String desc = "", desc2 = "";
        for (DetalleContratoDeServicio detCto : listaDet) {

            System.out.println("detCto.getEquipo().getTipoArticulo().getCodigo() " + detCto.getEquipo().getTipoArticulo().getCodigo());

            detalleFactura = new DetalleFactura();
            detalleFactura.setDetalleContrato(detCto.getCodigo());
            String cantAnio = String.valueOf(detCto.getCantidadAnio());
            detCto.setCantidadFrecuenciaDePago(Double.valueOf(cantAnio));

            // *****************************************
            Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();

            if (!(detCto.getEjecutivoDeVenta() == null)) {

                factura.setVendedor(detCto.getEjecutivoDeVenta());

            }

            if (!(detCto.getPrecioRenovacion() == null)) {
                System.out.println("detCto.getPrecioRenovacion() " + detCto.getPrecioRenovacion());

                detalleFactura.setPrecio(detCto.getPrecioRenovacion());
            } else {
                System.out.println("detCto.getPrecioPagado() " + detCto.getPrecioPagado());
                detalleFactura.setPrecio(detCto.getPrecioPagado());

            }

            if (detCto.getCompatibilidad()) {

                factura.setCompatibilidad(true);

            } else {
                factura.setCompatibilidad(false);
            }

            if (!(detCto.getSuplidor() == null)) {

                factura.setSuplidorDeVenta(detCto.getSuplidor());

            }

            int cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();
            int cantidadGPS = detCto.getCantidad();

            Date fechaVencimientoCal = null;
            Date fecha = detCto.getFechaUltimoPagoHasta();

            System.out.println("cantidadFrecuenciaPago  " + cantidadFrecuenciaPago);
            fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadFrecuenciaPago * 12);//Calculamos la fecha de vencimiento en meses

            System.out.println("fechaVencimientoCal " + fechaVencimientoCal);

            Double precioPagado = detalleFactura.getPrecio();

            int diaDeRenovacion = 0, mesDeRenovacion = 0;
            diaDeRenovacion = ManejoUnidadDeNegocio.getInstancia().getUnidad(2).getDiaDeRenovacion();
            mesDeRenovacion = ManejoUnidadDeNegocio.getInstancia().getUnidad(2).getMesDeRenovacion();

            if (detCto.getTipoDeServicio().getCodigo() == 1) {

                if (chNuevaModalidad.isSelected()) {

                    Double cantidadFecuenciaPago = detCto.getCantidadFrecuenciaDePago();
                    Double cantidadFecuenciaPagoDias = 0.00;
                    Double frecuencia = cantidadFecuenciaPago;

                    cantidadFecuenciaPagoDias = util.ClaseUtil.getDiasCorteContrato(fecha).doubleValue();
                    Date fechacorte = util.ClaseUtil.getFechaCorteContrato(fecha, cantidadFecuenciaPagoDias.intValue());
                    System.out.println("fechacorte : " + fechacorte);

                    fechacorteAnio = util.ClaseUtil.getConstruirFecha(fechacorte, diaDeRenovacion, mesDeRenovacion);

                    frecuencia = util.ClaseUtil.getMesesEntreFecha(fecha, fechacorteAnio).doubleValue();

                    fechaVencimiento = ClaseUtil.FechaMesDespues(fechacorteAnio, cantidadFecuenciaPago.intValue() * 12);

                    if (frecuencia > 1) {

                        desc = frecuencia.intValue() + "  MESES "
                                + "  Y " + cantidadFecuenciaPagoDias.intValue() + " DIAS  "
                                + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion()
                                + " POR SERVICIO DE " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio);
                    } else {
                        desc = frecuencia.intValue() + "  MES "
                                + "  Y " + cantidadFecuenciaPagoDias.intValue() + " DIAS  "
                                + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion()
                                + " POR SERVICIO DE " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio);
                    }

                    System.out.println("precioPagado : " + precioPagado);
                    precioPorDia = ClaseUtil.roundDouble(precioPagado / dias, 2);
                    System.out.println("precioPorDia : " + precioPorDia);
                    cantidadFecuenciaPagoDias += frecuencia * 30;
                    precioPorDia = precioPorDia * cantidadFecuenciaPagoDias;
                    detalleFactura.setPrecio(precioPorDia);

                    if (chNuevaModalidRnv.isSelected()) {

                        desc2 = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                                + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio)
                                + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
                    }

                } else {

                    desc = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                            + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                            + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);
                }

            } else if (detCto.getTipoDeServicio().getCodigo() == 2) {

                if (chNuevaModalidad.isSelected()) {

                    precioPagado = detalleFactura.getPrecio();
                    Double cantidadFecuenciaPago = detCto.getCantidadFrecuenciaDePago();
                    Double cantidadFecuenciaPagoDias = 0.00;
                    Double frecuencia = cantidadFecuenciaPago;

                    cantidadFecuenciaPagoDias = util.ClaseUtil.getDiasCorteContrato(fecha).doubleValue();
                    Date fechacorte = util.ClaseUtil.getFechaCorteContrato(fecha, cantidadFecuenciaPagoDias.intValue());
                    System.out.println("fechacorte : " + fechacorte);

                    fechacorteAnio = util.ClaseUtil.getConstruirFecha(fechacorte, diaDeRenovacion, mesDeRenovacion);

                    frecuencia = util.ClaseUtil.getMesesEntreFecha(fecha, fechacorteAnio).doubleValue();

                    fechaVencimiento = ClaseUtil.FechaMesDespues(fechacorteAnio, cantidadFecuenciaPago.intValue() * 12);
                    if (frecuencia > 1) {

                        desc = frecuencia.intValue() + "  MESES "
                                + "  Y " + cantidadFecuenciaPagoDias.intValue() + " DIAS  "
                                + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR " + cantidadGPS + " SERVICIO "
                                + "DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio);
                    } else {

                        desc = frecuencia.intValue() + "  MES "
                                + "  Y " + cantidadFecuenciaPagoDias.intValue() + " DIAS  "
                                + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR " + cantidadGPS + " SERVICIO "
                                + "DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio);
                    }

                    precioPorDia = ClaseUtil.roundDouble(precioPagado / dias, 2);
                    cantidadFecuenciaPagoDias += frecuencia * 30;
                    precioPorDia = precioPorDia * cantidadFecuenciaPagoDias;
                    detalleFactura.setPrecio(precioPorDia);

                    if (chNuevaModalidRnv.isSelected()) {

                        desc2 = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR " + cantidadGPS + " SERVICIO "
                                + "  DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio)
                                + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
                    }

                } else {

                    desc = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR " + cantidadGPS + " SERVICIO "
                            + "  DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                            + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);
                }

            } else {

                if (chNuevaModalidad.isSelected()) {

                    precioPagado = detalleFactura.getPrecio();
                    Double cantidadFecuenciaPago = detCto.getCantidadFrecuenciaDePago();
                    Double cantidadFecuenciaPagoDias = 0.00;
                    Double frecuencia = cantidadFecuenciaPago;

                    cantidadFecuenciaPagoDias = util.ClaseUtil.getDiasCorteContrato(fecha).doubleValue();
                    Date fechacorte = util.ClaseUtil.getFechaCorteContrato(fecha, cantidadFecuenciaPagoDias.intValue());
                    System.out.println("fechacorte : " + fechacorte);

                    fechacorteAnio = util.ClaseUtil.getConstruirFecha(fechacorte, diaDeRenovacion, mesDeRenovacion);

                    frecuencia = util.ClaseUtil.getMesesEntreFecha(fecha, fechacorteAnio).doubleValue();

                    fechaVencimiento = ClaseUtil.FechaMesDespues(fechacorteAnio, cantidadFecuenciaPago.intValue() * 12);

                    if (frecuencia > 1) {

                        desc = frecuencia.intValue() + "  MESES "
                                + "  Y " + cantidadFecuenciaPagoDias.intValue() + " DIAS  " + "  RENOVACION DE "
                                + detCto.getFrecuenciaDePago().getDescripcion()
                                + " POR SERVICIO DE " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio);
                    } else {

                        desc = frecuencia.intValue() + "  MES "
                                + "  Y " + cantidadFecuenciaPagoDias.intValue() + " DIAS  " + "  RENOVACION DE "
                                + detCto.getFrecuenciaDePago().getDescripcion()
                                + " POR SERVICIO DE " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                                + " AL " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio);
                    }

                    precioPorDia = ClaseUtil.roundDouble(precioPagado / dias, 2);
                    cantidadFecuenciaPagoDias += frecuencia * 30;
                    precioPorDia = precioPorDia * cantidadFecuenciaPagoDias;
                    detalleFactura.setPrecio(precioPorDia);

                    if (chNuevaModalidRnv.isSelected()) {

                        desc2 = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                                + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(fechacorteAnio)
                                + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);

                    }

                } else {

                    desc = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                            + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                            + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);
                }

            }

            //*************************************************************************
//            if (detCto.getTipoDeServicio().getCodigo() == 1) {
//
//                desc = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
//                        + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
//                        + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);
//
//            } else if (detCto.getTipoDeServicio().getCodigo() == 2) {
//
//                desc = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR " + cantidadGPS + " SERVICIO "
//                        + "  DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
//                        + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);
//            } else {
//
//                desc = cantidadFrecuenciaPago + "  RENOVACION DE " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
//                        + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
//                        + "  AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);
//            }
            //***********************************************************************
            detCto.setFechaUltimoPagoDesde(detCto.getFechaUltimoPagoHasta());//Factura  que cubre el pago desde esta fecha hasta la fecha calculada
            detCto.setFechaUltimoPagoHasta(fechaVencimientoCal); //fechaVencimientoCal   a hora es el setFechaUltimoPagoHasta

            System.out.println("descripcion pago " + desc);
            detalleFactura.setDescripcionPago(desc);
            detalleFactura.setFactura(factura);
            detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
            detalleFactura.setArticulo(detCto.getEquipo());
            cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();//CAntidad facturado

            detalleFactura.setCantidad(cantidad);

            subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
            detalleFactura.setSubTotal(subTotal);

            valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);

            detalleFactura.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));

            detalleFactura.setDescuento(0.00);
            detalleFactura.setExistencia(0.00);
            detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
            detalleFactura.setNuevaExistencia(0.0);

            detalleFactura.setItbis(valorItbis);
            detalleFactura.setTasaItbis(detCto.getTasaItbis());

            detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
            detalleFactura.setUnidad(unidad);

            if (chNuevaModalidRnv.isSelected()) {

                detalleFactura2 = new DetalleFactura();

                detalleFactura2.setPrecio(precioPagado);
                detalleFactura2.setDescripcionPago(desc2);
                detalleFactura2.setFactura(factura);
                detalleFactura2.setCantidad(Double.valueOf(detCto.getCantidad()));
                detalleFactura2.setArticulo(detCto.getEquipo());
                cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura2.getCantidad();//Cantidad facturado

                detalleFactura2.setCantidad(cantidad);

                subTotal = ClaseUtil.roundDouble(detalleFactura2.getCantidad() * detalleFactura2.getPrecio(), 2);
                detalleFactura2.setSubTotal(subTotal);

                valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura2.getSubTotal(), 2);

                detalleFactura2.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));

                detalleFactura2.setDescuento(0.00);
                detalleFactura2.setExistencia(0.00);
                detalleFactura2.setNombreArticulo(detCto.getEquipo().getNombre());
                detalleFactura2.setNuevaExistencia(0.0);

                detalleFactura2.setItbis(valorItbis);
                detalleFactura2.setTasaItbis(detCto.getTasaItbis());

                detalleFactura2.setTotal(detalleFactura2.getItbis() + detalleFactura2.getSubTotal());
                detalleFactura2.setUnidad(unidad);
                listaDetalleFactura.add(detalleFactura2);
            }

            //agregando detalle a la factura de los vehiculos 
            listaDetVehiculo.clear();

            for (DatosDeVehiculo detVeh : listaDatosVehiculo) {

                if (detVeh.getRenovar()) {
                    listaDetVehiculo.add(detVeh);
                }
            }

//            listaDetVehiculo.addAll(ManejoContratoDeServicio.getInstancia()
//                    .getDatosVehiculoo(detCto.getCodigo()));
            detCto.setCantidadAnio(0);
            detCto.setCantidadMeses(0);

            System.out.println(" datos vehiculo " + listaDetVehiculo.size() + " detCto.getCodigo() " + detCto.getCodigo());

            for (DatosDeVehiculo detVeh : listaDetVehiculo) {

                System.out.println("configurando  factura datos de vehiculo ");
                facturaDatosDeVehiculo = new FacturaDatosDeVehiculo();
                // *****************************************

                facturaDatosDeVehiculo.setFactura(detalleFactura);
                facturaDatosDeVehiculo.setAdicional(detVeh.getAdicional());
                facturaDatosDeVehiculo.setAnio(detVeh.getAnio());
                facturaDatosDeVehiculo.setChasis(detVeh.getChasis());
                facturaDatosDeVehiculo.setColor(detVeh.getColor());
                facturaDatosDeVehiculo.setMarca(detVeh.getMarca());
                facturaDatosDeVehiculo.setMatricula(detVeh.getMatricula());
                facturaDatosDeVehiculo.setModelo(detVeh.getModelo());
                facturaDatosDeVehiculo.setPlaca(detVeh.getPlaca());
                facturaDatosDeVehiculo.setTipoVehiculo(detVeh.getTipoVehiculo());
                facturaDatosDeVehiculo.setVehiculo(detVeh.getVehiculo());

                listaDetalleFacDdatosVehiculo.add(facturaDatosDeVehiculo);

            }

            listaDetalleFactura.add(detalleFactura);

        }

        try {

            System.out.println(" listaDetalleFactura " + listaDetalleFactura + " listaDetalleFactura.size() " + listaDetalleFactura.size());

            if (listaDetalleFactura.size() > 0) {

                factura.setTotal(getTotal(listaDetalleFactura));
                factura.setSubTotal(getSubTotal(listaDetalleFactura));
                factura.setItbis(getItbis(listaDetalleFactura));
                factura.setMonto(getTotal(listaDetalleFactura));
                factura.setPendiente(getTotal(listaDetalleFactura));

                factura.setDetalleFacturaCollection(listaDetalleFactura);

                Double balancePendiente = ManejoFactura.getInstancia().getMontoPendiente(factura.getCliente());

                factDb = guardar(factura);

                if (factDb == null) {

                    ClaseUtil.mensaje(" Hubo Error creando la factura ");
                    return;

                } else {

                    listaDetalleFacDdatosVehiculo.forEach((fctdvh) -> {
                        System.out.println("item " + fctdvh);
                        ManejoFacturaDatosDeVehiculo.getInstancia().salvar(fctdvh);
                    });
                    //Actualizar el ncf 
                    ManejoRelacionNcf.getInstancia().actualizar(relacionNcf);

                    //Generando el historico de corte de facturacion 
                    detaCorte = new DetalleCorteDeFacturacion();

                    detaCorte.setFactura(factura);
                    detaCorte.setNumeroFactura(factura.getNumero());
                    detaCorte.setContrato(contrato);
                    detaCorte.setNumeroContrato(contrato.getNumeroDeContrato());
                    detaCorte.setCorteDeFacturacion(corteFact);
                    detaCorte.setTotalFactura(factura.getTotal());
                    listaDetCorte.add(detaCorte);

                    contrato.setDetalleContratoDeServicioCollection(listaDet);
                    ManejoContratoDeServicio.getInstancia().actualizar(contrato);

                    HistoricoBalancePendiente hBalance = new HistoricoBalancePendiente();

                    hBalance.setCliente(factura.getCliente().getCodigo());
                    hBalance.setFactura(factura.getCodigo());
                    hBalance.setFechaVencimiento(factura.getFecha());
                    hBalance.setTotal(balancePendiente);

                    ManejoHistoricoBalancePendiente.getInstancia().salvar(hBalance);

                    formaPago.setDocumento(factDb.getCodigo());
                    formaPago.setTipoDocumento(7);
                    formaPago.setFecha(factDb.getFecha());
                    ManejoFormaPago.getInstancia().salvar(formaPago);

                    RptFactura.getInstancia().verFactura(factDb.getCodigo(), factDb.getUnidadDeNegocio().getCodigo());

                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        num++;

        corteFact.setCantidadFactura(num);
        corteFact.setDetalleCorteDeFacturacionCollection(listaDetCorte);

        ManejoCorteDeFacturacion.getInstancia().salvar(corteFact);

    }

}
