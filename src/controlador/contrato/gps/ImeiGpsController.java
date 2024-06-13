/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.gps;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import manejo.contrato.ManejoContratoDeServicio;
import manejo.contrato.ManejoImeiGps;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import modelo.DetalleContratoDeServicio;
import modelo.MenuModulo;
import modelo.RegistroDeImei;
import modelo.RolMenuModulo;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ImeiGpsController implements Initializable {

    @FXML
    private JFXButton btnExportar;

    /**
     * @return the resgistro
     */
    public RegistroDeImei getRegistro() {
        return resgistro;
    }

    /**
     * @param registro the resgistro to set
     */
    public void setRegistro(RegistroDeImei registro) {
        this.resgistro = registro;
    }

    @FXML
    private JFXButton btnEditar;

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
    private JFXButton btnNuevo;
    @FXML
    private TabPane tabPane;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<RegistroDeImei> tbImeiGps;
    @FXML
    private TableColumn<RegistroDeImei, String> tbcFecha;
    @FXML
    private TableColumn<RegistroDeImei, String> tbcFechaRegistro;
    @FXML
    private TableColumn<RegistroDeImei, RegistroDeImei> tbcDisponible;
    @FXML
    private TableColumn<RegistroDeImei, String> tbcArticulo;
    @FXML
    private TableColumn<RegistroDeImei, String> tbcNumeroImei;
    @FXML
    private TableColumn<RegistroDeImei, String> tbcNumeroSim;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TableView<DetalleContratoDeServicio> tbMovimiento;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcFechaMovimiento;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcContrato;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcGps;
    @FXML
    private TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> tbcEstado;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcImei;
    @FXML
    private TableColumn<DetalleContratoDeServicio, String> tbcCliente;
    @FXML
    private JFXTextField txtCantidadArticulo;

    ObservableList<RegistroDeImei> listaImei = FXCollections.observableArrayList();
    ObservableList<DetalleContratoDeServicio> listaSimMovimiento = FXCollections.observableArrayList();
    @FXML
    private HBox hbPermiso;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    private Integer codigoMenuModulo;
    private RegistroDeImei resgistro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setCodigoMenuModulo(ManejoMenuModulo.getInstancia().getMenuModulo(9, "btnRegistroContrato"));
        agregarOpciones();
        activarOpciones();
        inicializarTabla();
        inicializarTablaMovimiento();
        iniciazarFiltro();
    }

    private void iniciazarFiltro() {

        FilteredList<RegistroDeImei> filteredData = new FilteredList<>(tbImeiGps.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(registro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (registro.getNumero().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (registro.getFechaEntrada().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }  else if (registro.getSim()!=null && registro.getSim().getNumero().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } 

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<RegistroDeImei> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbImeiGps.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbImeiGps.setItems(sortedData);
    }

    public void inicializarTabla() {

        try {

            listaImei.addAll(ManejoImeiGps.getInstancia().getLista());

            tbcNumeroImei.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fechaEntrada"));

            tbcArticulo.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getArticulo() != null) {
                            property.setValue(cellData.getValue().getArticulo().getNumero().toString());
                        }
                        return property;
                    });

            tbcNumeroSim.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getSim() != null) {
                            property.setValue(cellData.getValue().getSim().getNumero());
                        } else {
                            property.setValue("na");
                        }

                        return property;
                    });

            tbcFecha.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaEntrada() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaEntrada()));
                        }
                        return property;
                    });

            tbcFechaRegistro.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaRegistro() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaRegistro()));
                        }
                        return property;
                    });

            tbcDisponible.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcDisponible.setCellFactory((TableColumn<RegistroDeImei, RegistroDeImei> param) -> {

                TableCell<RegistroDeImei, RegistroDeImei> cellsc = new TableCell<RegistroDeImei, RegistroDeImei>() {
                    @Override
                    public void updateItem(RegistroDeImei item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;

                        if (item != null) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(50.0, 20.0);

                            if (item.getDisponible()) {

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
            tbImeiGps.setItems(listaImei);
//            lb.setText(Integer.toString(listaImei.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTablaMovimiento() {

        try {

            tbcFechaMovimiento.setCellValueFactory(new PropertyValueFactory<>("fechaDesde"));
            tbcImei.setCellValueFactory(new PropertyValueFactory<>("numeroImei"));
//            tbcSim.setCellValueFactory(new PropertyValueFactory<>("numeroSim"));
            tbcFechaMovimiento.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaDesde() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaDesde()));
                        }
                        return property;
                    });

            tbcGps.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getEquipo() != null) {
                            property.setValue(cellData.getValue().getEquipo().getNombre());
                        }
                        return property;
                    });

            tbcContrato.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getEquipo() != null) {
                            property.setValue(cellData.getValue().getContratoDeServicio().getNumeroDeContrato());
                        }
                        return property;
                    });

            tbcCliente.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getEquipo() != null) {
                            property.setValue(cellData.getValue().getContratoDeServicio().getCliente().getNombre());
                        }
                        return property;
                    });

            tbcEstado.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcEstado.setCellFactory((TableColumn<DetalleContratoDeServicio, DetalleContratoDeServicio> param) -> {

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
            tbMovimiento.setItems(listaSimMovimiento);

//            lb.setText(Integer.toString(listaImei.size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/contrato/gps/RegistroImei.fxml"));

        ClaseUtil.getStageModal(root);

        listaImei.clear();

        listaImei.addAll(ManejoImeiGps.getInstancia().getLista());

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

                    if (!(menuModulo.getCodigo() == null)) {
                        ManejoMenuModulo.getInstancia().actualizar(menuModulo);
                    }

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
    private void tbSimCardMouseClicked(MouseEvent event) {

        if (!(tbImeiGps.getSelectionModel().getSelectedIndex() == -1)) {

            setRegistro(tbImeiGps.getSelectionModel().getSelectedItem());
            int codImei = getRegistro().getCodigo();
            listaSimMovimiento.clear();
            listaSimMovimiento.addAll(ManejoContratoDeServicio.getInstancia().getMovimientoImeiGps(codImei));
            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(1);
            }

        } else {
            listaSimMovimiento.clear();
        }

    }

    @FXML
    private void tbMovimiento(MouseEvent event) {

    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbImeiGps.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN REGISTRO ");

        } else {

            setRegistro(tbImeiGps.getSelectionModel().getSelectedItem());

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/contrato/gps/RegistroImei.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroDeImeiController controller = loader.getController();
            controller.setRegistroImei(getRegistro());
            controller.setEditar(true);
            controller.llenarCampo();

            ClaseUtil.getStageModal(root);

            listaImei.clear();

            listaImei.addAll(ManejoImeiGps.getInstancia().getLista());

        }

    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) throws IOException {

        util.ClaseUtil.exportarDAtos(tbImeiGps);
    }

}
