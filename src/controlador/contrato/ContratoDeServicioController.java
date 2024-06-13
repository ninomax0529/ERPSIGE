/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.inventario.articulo.ArticulosController;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import manejo.contrato.ManejoContratoDeServicio;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import modelo.ContratoDeServicio;
import modelo.DatosDeVehiculo;
import modelo.DetalleContratoDeServicio;
import modelo.MenuModulo;
import modelo.RolMenuModulo;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ContratoDeServicioController implements Initializable {

    @FXML
    private JFXCheckBox chEntreFecha;
    @FXML
    private JFXButton btnDesintalar;
    @FXML
    private JFXButton btnReinstalar;
    @FXML
    private JFXButton btnTraspaso;
    @FXML
    private TableColumn<?, ?> tbcAdicional1;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcPagoDesdeVh;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcFechaHastaVh;
    @FXML
    private JFXTextField txtFiltro1;
    @FXML
    private JFXButton btnSuspenderServicio;
    @FXML
    private JFXTextField txtRazonSuspension;

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

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private TabPane tabPane;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<ContratoDeServicio> tbContratoDeServicio;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcNumContrato;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcFecha;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcCliente;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcTipoDeContrato;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcAnulado;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcEjecutivoDeVenta;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcRepresentante;
    @FXML
    private TableColumn<ContratoDeServicio, Double> tbcSubTotal;
    @FXML
    private TableColumn<ContratoDeServicio, Double> tbcItbis;
    @FXML
    private TableColumn<ContratoDeServicio, Double> tbcTotal;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcEstado;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TableView<DetalleContratoDeServicio> tbDetalleContrato;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcEquipo;
    @FXML
    private TableColumn<DetalleContratoDeServicio, Double> tbcPrecioAcordado;
    @FXML
    private TableColumn<DetalleContratoDeServicio, Double> tbcMensualidad;
    @FXML
    private TableColumn<DetalleContratoDeServicio, Integer> tbcCantidad;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcMatricula;
    @FXML
    private TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> tbcHabilitado;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcImei;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcSim;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcPagoDesde;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcPagoHasta;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcTipoServicio;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcInstalador;
    @FXML
    private JFXTextField txtCantidadArticulo;
    @FXML
    private JFXTextField txtValorTotal;
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
    private TableColumn<DatosDeVehiculo, DatosDeVehiculo> tbcVehiculoHabilitado;
    @FXML
    private JFXTextField txtCantidadArticulo1;
    @FXML
    private JFXTextField txtValorTotal1;

    ObservableList<ContratoDeServicio> listaContrato = FXCollections.observableArrayList();
    ObservableList<DatosDeVehiculo> listaDatosVehiculo = FXCollections.observableArrayList();
    ObservableList<DetalleContratoDeServicio> listaDetalleContrato = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnEditar;
    Date fechaIni, fechaFin;
    @FXML
    private HBox hbPermiso;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    private Integer codigoMenuModulo;

    TaskBuscarContrato taskBuscarContrato;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

        btnDesintalar.setVisible(false);
        btnReinstalar.setVisible(false);
        setCodigoMenuModulo(ManejoMenuModulo.getInstancia().getMenuModulo(9, "btnRegistroContrato"));
        inicializarTablaDatosVehiculo();
        inicializarTablaDetalle();
        inicializarTablaContrato();
        iniciazarFiltro();
        iniciazarFiltroDetContrato();

        agregarOpciones();
        activarOpciones();

        txtFiltro.setOnKeyPressed(value -> {

            if (value.getCode() == KeyCode.ENTER) {

                try {

                    taskBuscarContrato = new TaskBuscarContrato();
                    taskBuscarContrato.run();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void inicializarTablaDetalle() {

        try {

            listaDatosVehiculo.clear();
            listaDetalleContrato.clear();

            tbcEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));
            tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            tbcMensualidad.setCellValueFactory(new PropertyValueFactory<>("mensualidad"));
            tbcPrecioAcordado.setCellValueFactory(new PropertyValueFactory<>("precioAcordado"));
            tbcEjecutivoDeVenta.setCellValueFactory(new PropertyValueFactory<>("ejecutivoDeVenta"));
            tbcImei.setCellValueFactory(new PropertyValueFactory<>("numeroImei"));
            tbcSim.setCellValueFactory(new PropertyValueFactory<>("numeroSim"));
            tbcPagoDesde.setCellValueFactory(new PropertyValueFactory<>("fechaUltimoPagoDesde"));
            tbcPagoHasta.setCellValueFactory(new PropertyValueFactory<>("fechaUltimoPagoHasta"));

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

            tbcHabilitado.setCellValueFactory(new PropertyValueFactory<>("habilitado"));

            tbcHabilitado.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
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

    public void inicializarTablaContrato() {

        try {

            tbcNumContrato.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tbcCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
            tbcTipoDeContrato.setCellValueFactory(new PropertyValueFactory<>("frecuenciaDePago"));
            tbcRepresentante.setCellValueFactory(new PropertyValueFactory<>("representante"));
            tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

            tbcSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
            tbcItbis.setCellValueFactory(new PropertyValueFactory<>("itbis"));
            tbcTotal.setCellValueFactory(new PropertyValueFactory<>("totalAPagar"));

            tbcFecha.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFecha() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFecha()));
                        }
                        return property;
                    });

            tbcEstado.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getCliente() != null) {
                            property.setValue(cellData.getValue().getEstado().getNombre());
                        }
                        return property;
                    });

            tbcAnulado.setCellValueFactory(
                    cellData -> {

                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue() != null) {

                            if (cellData.getValue().getAnulado()) {
                                property.setValue("Si");
                            } else {
                                property.setValue("No");
                            }

                        }
                        return property;
                    });

//            tbcCliente.setCellValueFactory(
//                    cellData -> {
//                        SimpleStringProperty property = new SimpleStringProperty();
//                        if (cellData.getValue().getCliente() != null) {
//                            property.setValue(cellData.getValue().getCliente().getNombre());
//                        }
//                        return property;
//                    });
            tbcTipoDeContrato.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFrecuenciaDePago() != null) {
                            property.setValue(cellData.getValue().getPlanDeServicio().getNombre());
                        }
                        return property;
                    });

//            listaContrato.addAll(ManejoContratoDeServicio.getInstancia().getLista());
            txtCantidad.setText(Integer.toString(listaContrato.size()));
            iniciazarFiltro();

            tbContratoDeServicio.setItems(listaContrato);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTablaDatosVehiculo() {

        try {

            listaDatosVehiculo.clear();

            tbcMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
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

            tbcPagoDesdeVh.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getServicio().getFechaUltimoPagoDesde() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getServicio().getFechaUltimoPagoDesde()));
                        }
                        return property;
                    });

            tbcFechaHastaVh.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getServicio().getFechaUltimoPagoHasta() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getServicio().getFechaUltimoPagoHasta()));
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

            tbDatosDeVehiculo.setItems(listaDatosVehiculo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciazarFiltro() {

        FilteredList<ContratoDeServicio> filteredData = new FilteredList<>(tbContratoDeServicio.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

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
//                } else if (contrato.getEjecutivoDeVenta().getNombre().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
                } else if (contrato.getPlanDeServicio().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (contrato.getRepresentante()!= null && contrato.getRepresentante().toLowerCase().contains(lowerCaseFilter)) {
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

    private void iniciazarFiltroDetContrato() {

        FilteredList<DetalleContratoDeServicio> filteredData = new FilteredList<>(tbDetalleContrato.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro1.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(contrato -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (contrato.getEquipo().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (contrato.getDescripcion().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
//                } else if (contrato.getFechaUltimoPagoHasta().toString().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
                } else if (contrato.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (!(contrato.getTipoDeServicio() == null) && contrato.getTipoDeServicio().getNombre().contains(lowerCaseFilter)) {

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

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

//        Parent root = FXMLLoader.load(getClass().getResource("/vista/contrato/RegistroContratoDeServicio_2.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/vista/contrato/RegistroContratoDeServicio.fxml"));

        ClaseUtil.getStageModalcONTRATO(root);

        listaContrato.clear();
        listaDatosVehiculo.clear();
        listaDetalleContrato.clear();
        buscarContrato();
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        try {

            taskBuscarContrato = new TaskBuscarContrato();
            taskBuscarContrato.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tbDetalleReciboMouseClicked(MouseEvent event) {

    }

    @FXML
    private void tbContratoDeServicioClicked(MouseEvent event) {

        if (!(tbContratoDeServicio.getSelectionModel().getSelectedIndex() == -1)) {

            listaDetalleContrato.clear();
            listaDatosVehiculo.clear();
            int codigocontrato = tbContratoDeServicio.getSelectionModel().getSelectedItem().getCodigo();
            listaDetalleContrato.addAll(ManejoContratoDeServicio.getInstancia().getDetalleContrato(codigocontrato));
//            listaDatosVehiculo.addAll(ManejoContratoDeServicio.getInstancia().getDatosVehiculoo(codigocontrato));

            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(1);
            }
        }
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) {

        try {

            if (tbContratoDeServicio.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que selccionar un contrato");
                return;
            }
//            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/movimiento/EntradaArticulo.fxml"));
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/contrato/RegistroContratoDeServicio.fxml"));
//            loader.setLocation(getClass().getResource("/vista/contrato/RegistroContratoDeServicio_2.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroContratoDeServicioController controller = loader.getController();
//            RegistroContratoDeServicioController2 controller = loader.getController();

            controller.setContrato(tbContratoDeServicio.getSelectionModel().getSelectedItem());
            controller.setEditar(true);
            controller.llenarCampo();

            ClaseUtil.getStageModalcONTRATO(root);

            buscarContrato();

        } catch (IOException ex) {

            Logger.getLogger(ArticulosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void buscarContrato() {

        fechaIni = ClaseUtil.asDate(dpFechaInicio.getValue());
        fechaFin = ClaseUtil.asDate(dpFechaFinal.getValue());

        listaContrato.clear();

        if (chEntreFecha.isSelected()) {

            listaContrato.addAll(ManejoContratoDeServicio.getInstancia().getLista(fechaIni, fechaFin));
        } else {
            listaContrato.addAll(ManejoContratoDeServicio.getInstancia().getLista());
        }

        txtCantidad.setText(Integer.toString(listaContrato.size()));
        iniciazarFiltro();

    }

    @FXML
    private void tbDetalleContratoMouseClicked(MouseEvent event) {

        if (!(tbDetalleContrato.getSelectionModel().getSelectedIndex() == -1)) {
            int contrato = 0;
            DetalleContratoDeServicio detCot = tbDetalleContrato.getSelectionModel().getSelectedItem();
            System.out.println(" tbDetalleContrato.getSelectionModel().getSelectedItem() "
                    + tbDetalleContrato.getSelectionModel().getSelectedItem());
            listaDatosVehiculo.clear();

            contrato = tbDetalleContrato.getSelectionModel().getSelectedItem().getCodigo();

            if (detCot.getEquipo().getTipoArticulo().getCodigo() == 3) {
                contrato = detCot.getCodigo();
                listaDatosVehiculo.addAll(ManejoContratoDeServicio.getInstancia().getDatosVehiculo(contrato));
            } else {
                contrato = detCot.getCodigo();
                listaDatosVehiculo.addAll(ManejoContratoDeServicio.getInstancia().getDatosVehiculoPorEquipo(contrato));
            }

            if (listaDatosVehiculo.size() <= 0) {

                if (!(tbContratoDeServicio.getSelectionModel().getSelectedIndex() == -1)) {
                    contrato = 0;
                    System.out.println(" tbDetalleContrato.getSelectionModel().getSelectedItem() "
                            + tbDetalleContrato.getSelectionModel().getSelectedItem());
                    listaDatosVehiculo.clear();

                    contrato = tbContratoDeServicio.getSelectionModel().getSelectedItem().getCodigo();

                    System.out.println("servicio " + contrato);

                    listaDatosVehiculo.addAll(ManejoContratoDeServicio.getInstancia().getVehiculos(contrato));

                }
            }

            txtRazonSuspension.clear();
            String razon = detCot.getRazonActualizacion() == null ? " " : detCot.getRazonActualizacion();
            if (!(detCot.getFechaSuspendido() == null)) {
                txtRazonSuspension.setVisible(true);
                txtRazonSuspension.setText("Razon Suspension : " + razon + "\n "
                        + " Fecha Suspension :" + detCot.getFechaSuspendido());
            } else {
                txtRazonSuspension.setVisible(false);
                txtRazonSuspension.clear();
            }

            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(2);
            }

        }

    }

    //Agrega las opciones de menu a la base de datos
    private void agregarOpciones() {

        MenuModulo menuModulo;

        List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(9);
        int codigoModulo = ManejoModulo.getInstancia().getModulo(9).getCodigo();
        String nombreModulo = ManejoModulo.getInstancia().getModulo(9).getNombre();

        boolean actualizando = false;

        for (Node n : hbPermiso.getChildren()) {

            if (!(n.getId() == null)) {

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

            for (Node n : hbPermiso.getChildren()) {
                n.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, 9);

            for (Node n : hbPermiso.getChildren()) {

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

    @FXML
    private void chEntreFechaActionEvent(ActionEvent event) {

        if (chEntreFecha.isSelected()) {
            dpFechaInicio.setDisable(false);
            dpFechaFinal.setDisable(false);
        } else {
            dpFechaInicio.setDisable(true);
            dpFechaFinal.setDisable(true);
        }

    }

    @FXML
    private void btnDesintalarActionEvent(ActionEvent event) {

        try {

            if (tbContratoDeServicio.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que selccionar un contrato");
                return;
            }

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/contrato/DesintalarContratoDeServicio.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            DesintalarContratoDeServicioController controller = loader.getController();

            controller.setContrato(tbContratoDeServicio.getSelectionModel().getSelectedItem());
            controller.setEditar(true);
            controller.llenarCampo();

            ClaseUtil.getStageModalcONTRATO(root);

            listaContrato.clear();
            listaContrato.addAll(ManejoContratoDeServicio.getInstancia().getLista());

        } catch (IOException ex) {

            Logger.getLogger(ArticulosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnReinstalarActionEvent(ActionEvent event) {

        try {

            if (tbContratoDeServicio.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje(" Tiene que selccionar un contrato ");
                return;
            }

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/contrato/ReinstalarContratoDeServicio.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            ReinstalarContratoDeServicioController controller = loader.getController();

            controller.setContrato(tbContratoDeServicio.getSelectionModel().getSelectedItem());
            controller.setEditar(true);
            controller.llenarCampo();

            ClaseUtil.getStageModalcONTRATO(root);

            listaContrato.clear();
            listaContrato.addAll(ManejoContratoDeServicio.getInstancia().getLista());

        } catch (IOException ex) {

            Logger.getLogger(ArticulosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnTraspasoActionEvent(ActionEvent event) {

        try {

            if (tbContratoDeServicio.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que selccionar un contrato");
                return;
            }

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/contrato/RegistroTraspasoVehiculo.fxml"));

            loader.load();

            Parent root = loader.getRoot();

            RegistroTraspasoVehiculoController controller = loader.getController();
            controller.setContrato(tbContratoDeServicio.getSelectionModel().getSelectedItem());
            controller.setEditar(true);
            controller.llenarCampo();

            ClaseUtil.getStageModalcONTRATO(root);

            listaContrato.clear();
            listaContrato.addAll(ManejoContratoDeServicio.getInstancia().getLista());
            txtFiltro.clear();
            iniciazarFiltro();

        } catch (IOException ex) {

            Logger.getLogger(ArticulosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnSuspenderServicioActionEvent(ActionEvent event) {

        try {

            if (tbContratoDeServicio.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que selccionar un contrato");
                return;
            }

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/contrato/SuspensionContratoDeServicio.fxml"));

            loader.load();

            Parent root = loader.getRoot();

            SuspensionContratoDeServicioController controller = loader.getController();
            controller.setContrato(tbContratoDeServicio.getSelectionModel().getSelectedItem());
            controller.setEditar(true);
            controller.llenarCampo();

            ClaseUtil.getStageModalcONTRATO(root);

            listaContrato.clear();
            listaContrato.addAll(ManejoContratoDeServicio.getInstancia().getLista());

        } catch (IOException ex) {

            Logger.getLogger(ArticulosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private class TaskBuscarContrato extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            try {

                buscarContrato();

//                listaContrato.clear();
//                listaContrato.addAll(ManejoContratoDeServicio.getInstancia().getLista());
//                txtCantidad.setText(Integer.toString(listaContrato.size()));
            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;

        }

//        private void buscar(ContratoDeServicio ri, int i, int total) throws InterruptedException {
//
//            this.updateMessage(" Procesada  " + i + "  De " + total);
//            Thread.sleep(200);
//        }
    }

}
