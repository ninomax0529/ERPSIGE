/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.asistenciaTecnica;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.cxc.gestionDeCobro.GestionDeCobroController;
import controlador.cxc.gestionDeCobro.RegistroGestionCobroController;
import java.io.File;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.asistenciaTecnica.ManejoAsistencaTecnica;
import manejo.cxc.gestionDeCobro.ManejoGestionDeCobro;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import modelo.AsistenciaTecnica;
import modelo.Factura;
import modelo.GestionDeCobro;
import modelo.MenuModulo;
import modelo.RegistroDeSim;
import modelo.RolMenuModulo;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class AsistenciaTecnicaController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private TableColumn<AsistenciaTecnica, AsistenciaTecnica> tbcAnular;
    @FXML
    private TableColumn<AsistenciaTecnica, String> tbcAnulado;

    /**
     * @return the asistenciaTecnica
     */
    public AsistenciaTecnica getAsistenciaTecnica() {
        return asistenciaTecnica;
    }

    /**
     * @param asistenciaTecnica the asistenciaTecnica to set
     */
    public void setAsistenciaTecnica(AsistenciaTecnica asistenciaTecnica) {
        this.asistenciaTecnica = asistenciaTecnica;
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
    private TableColumn<RegistroDeSim, String> tbcNumeroSim;
    @FXML
    private JFXButton btnExportar;
    @FXML
    private JFXTextField txtCantidadSim;
    @FXML
    private TableView<AsistenciaTecnica> tbAsistenciaTecnica;
    @FXML
    private TableColumn<AsistenciaTecnica, String> tbcFechaCreacion;
    @FXML
    private TableColumn<AsistenciaTecnica, String> tbcFechaCierre;
    @FXML
    private TableColumn<AsistenciaTecnica, String> tbcEStado;
    @FXML
    private TableColumn<AsistenciaTecnica, String> tbcAsistidoPor;
    @FXML
    private TextArea txtDEtalleSolicitud;
    @FXML
    private TableColumn<AsistenciaTecnica, String> tbcCliente;

    ObservableList<AsistenciaTecnica> lista = FXCollections.observableArrayList();

    @FXML
    private HBox hbPermiso;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    private Integer codigoMenuModulo;

    private AsistenciaTecnica asistenciaTecnica;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        setCodigoMenuModulo(ManejoMenuModulo.getInstancia().getMenuModulo(9, "btnRegistroContrato").getCodigo());
//        agregarOpciones();
//        activarOpciones();
        inicializarTabla();
        iniciazarFiltro();

        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

    }

    private void iniciazarFiltro() {

        FilteredList<AsistenciaTecnica> filteredData = new FilteredList<>(tbAsistenciaTecnica.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(registro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toUpperCase();

                if (registro.getCodigo().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (!(registro.getNombreTecnico() == null) && registro.getNombreTecnico().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (registro.getNombreCliente().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (registro.getFechaRegistro().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<AsistenciaTecnica> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbAsistenciaTecnica.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbAsistenciaTecnica.setItems(sortedData);
    }

    public void inicializarTabla() {

        try {

            lista.addAll(ManejoAsistencaTecnica.getInstancia().getLista());

            tbcNumeroSim.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            tbcFechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
            tbcCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
            tbcAsistidoPor.setCellValueFactory(new PropertyValueFactory<>("nombreTecnico"));

            tbcAnulado.setCellValueFactory(
                    cellData -> {

                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue() != null) {

                            if (cellData.getValue().getAnulada()) {
                                property.setValue("Si");
                            } else {
                                property.setValue("No");
                            }

                        }
                        return property;
                    });

            tbcFechaCreacion.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaRegistro() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy")
                                    .format(cellData.getValue().getFechaRegistro()));
                        }
                        return property;
                    });

            tbcEStado.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getCliente() != null) {
                            property.setValue(cellData.getValue().getEstado().getNombre());
                        }
                        return property;
                    });

            tbcFechaCierre.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaCierre() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy")
                                    .format(cellData.getValue().getFechaCierre()));
                        }
                        return property;
                    });

            tbcAnular.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcAnular.setCellFactory((TableColumn<AsistenciaTecnica, AsistenciaTecnica> param) -> {
                TableCell<AsistenciaTecnica, AsistenciaTecnica> cellsc = new TableCell<AsistenciaTecnica, AsistenciaTecnica>() {
                    @Override
                    public void updateItem(AsistenciaTecnica item, boolean empty) {
                        super.updateItem(item, empty);
                        File imageFile;
                        Image img;
                        ImageView imageview;
                        Label label;

                        if (item != null) {

//                        imageFile = new File(getClass().getResource("/imagen/img_documento.jpg").toString());
                            label = new Label("Componente");
                            imageview = new ImageView(new Image(getClass().getResource("/imagen/img_anular.jpg").toString(), 40, 20, false, false));
                            imageview.setFitWidth(50);
                            imageview.setFitHeight(40);

                            VBox hbox = new VBox();

                            hbox.getChildren().addAll(imageview);

                            hbox.setAlignment(Pos.CENTER);

                            //Evento de la fila 
                            hbox.setOnMouseClicked((event) -> {

                                if (item.getAnulada()) {
                                    return;
                                }

                                try {

                                    FXMLLoader loader = new FXMLLoader();

                                    loader.setLocation(getClass().getResource("/vista/asistenciaTecnica/RazonulacionAsistencia.fxml"));
                                    loader.load();
                                    Parent root = loader.getRoot();

                                    RazonanulacionAsistenciaController controller = loader.getController();
                                    controller.setAsistenciaTecnica(item);
                                    controller.llenarCampo();

                                    ClaseUtil.getStageModal(root);

//                                    lista.clear();
                                    lista.setAll(ManejoAsistencaTecnica.getInstancia().getLista());
                                    txtCantidadSim.setText(Integer.toString(lista.size()));

                                } catch (IOException ex) {
                                    Logger.getLogger(GestionDeCobroController.class.getName()).log(Level.SEVERE, null, ex);
                                }

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

            tbAsistenciaTecnica.setItems(lista);
            txtCantidadSim.setText(Integer.toString(lista.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/asistenciaTecnica/RegistroAsistenciaTecnica.fxml"));
        ClaseUtil.getStageModal(root);

        lista.clear();

        lista.addAll(ManejoAsistencaTecnica.getInstancia().getLista());

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
    private void tbSimCardMouseClicked(MouseEvent event) {

        if (!(tbAsistenciaTecnica.getSelectionModel().getSelectedIndex() == -1)) {
//
            setAsistenciaTecnica(tbAsistenciaTecnica.getSelectionModel().getSelectedItem());
            txtDEtalleSolicitud.clear();
            txtDEtalleSolicitud.setText("MOTIVO DE LA ASISTENCIA :\n"
                    + getAsistenciaTecnica().getOrigenAsistencia() + "\n \n "
                    + "SOLUCION :\n" + getAsistenciaTecnica().getSolucion());
            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(1);
            }

        } else {
            lista.clear();
        }

    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbAsistenciaTecnica.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN REGISTRO ");

        } else {

            setAsistenciaTecnica(tbAsistenciaTecnica.getSelectionModel().getSelectedItem());

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/asistenciaTecnica/RegistroAsistenciaTecnica.fxml"));

            loader.load();

            Parent root = loader.getRoot();

            RegistroAsistenciaTecnicaController controller = loader.getController();
            controller.setAsistenciaTecnica(getAsistenciaTecnica());
            controller.setEditar(true);
            controller.llenarCampo();

            ClaseUtil.getStageModal(root);

            lista.clear();

            lista.addAll(ManejoAsistencaTecnica.getInstancia().getLista());

        }

    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) throws IOException {

        util.ClaseUtil.exportarDAtos(tbAsistenciaTecnica);
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        buscar();
    }

    private void buscar() {

        Date fechaini, fechafin;
        fechaini = ClaseUtil.asDate(dpFechaInicio.getValue());
        fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());
        lista.clear();
        lista.addAll(ManejoAsistencaTecnica.getInstancia().getListaEntreFecha(fechaini, fechafin));

        iniciazarFiltro();
    }

}
