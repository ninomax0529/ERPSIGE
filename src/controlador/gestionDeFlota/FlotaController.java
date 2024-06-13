/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.gestionDeFlota;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import manejo.gestionDeFlota.ManejoRegistroDeFlota;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import modelo.AsignacionDeFlota;
import modelo.MenuModulo;
import modelo.RegistroDeFlota;
import modelo.RolMenuModulo;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FlotaController implements Initializable {

    /**
     * @return the registroDeFlota
     */
    public RegistroDeFlota getRegistroDeFlota() {
        return registroDeFlota;
    }

    /**
     * @param registroDeFlota the registroDeFlota to set
     */
    public void setRegistroDeFlota(RegistroDeFlota registroDeFlota) {
        this.registroDeFlota = registroDeFlota;
    }

    @FXML
    private TableView<RegistroDeFlota> tbFlota;
    @FXML
    private TableColumn<RegistroDeFlota, String> tbcImei;
    @FXML
    private TableColumn<RegistroDeFlota, String> tbcAsignada;
    @FXML
    private TableColumn<RegistroDeFlota, String> tbcCondicion;
    @FXML
    private TableColumn<RegistroDeFlota, String> tbcFecha;
    @FXML
    private TableColumn<RegistroDeFlota, String> tbcMarca;
    @FXML
    private TableColumn<RegistroDeFlota, String> tbcModelo;
    @FXML
    private TableColumn<RegistroDeFlota, String> tbcNumeroSim;

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnBuscar;

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
    private JFXButton btnExportar;
    @FXML
    private JFXTextField txtCantidadSim;
    private RegistroDeFlota registroDeFlota;

    ObservableList<RegistroDeFlota> lista = FXCollections.observableArrayList();

    @FXML
    private HBox hbPermiso;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    private Integer codigoMenuModulo;

    private AsignacionDeFlota asignacionDeFlota;

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

        FilteredList<RegistroDeFlota> filteredData = new FilteredList<>(tbFlota.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(registro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toUpperCase();

                if (registro.getSim().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (!(registro.getImei() == null) && registro.getImei().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (registro.getMarca().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (registro.getModelo().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<RegistroDeFlota> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbFlota.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbFlota.setItems(sortedData);
    }

    public void inicializarTabla() {

        try {

            lista.addAll(ManejoRegistroDeFlota.getInstancia().getLista());

            tbcNumeroSim.setCellValueFactory(new PropertyValueFactory<>("sim"));
            tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tbcImei.setCellValueFactory(new PropertyValueFactory<>("imei"));
            tbcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
            tbcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

            tbcCondicion.setCellValueFactory(
                    cellData -> {

                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue() != null) {

                            if (cellData.getValue().getUsada()) {
                                property.setValue("Usada");
                            } else if (cellData.getValue().getNueva()) {
                                property.setValue("Nueva");
                            }

                        }
                        return property;
                    });

            tbcAsignada.setCellValueFactory(
                    cellData -> {

                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue() != null) {

                            if (cellData.getValue().getAsignada()) {
                                property.setValue("SI");
                            } else  {
                                property.setValue("NO");
                            }

                        }
                        return property;
                    });

            tbcFecha.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaRegistro() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy")
                                    .format(cellData.getValue().getFechaRegistro()));
                        }
                        return property;
                    });
         

            tbFlota.setItems(lista);
            txtCantidadSim.setText(Integer.toString(lista.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/gestionDeFlota/RegistroDeFlota.fxml"));
        ClaseUtil.getStageModal(root);

        lista.clear();

        lista.addAll(ManejoRegistroDeFlota.getInstancia().getLista());

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

    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbFlota.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN REGISTRO ");

        } else {

            setRegistroDeFlota(tbFlota.getSelectionModel().getSelectedItem());

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/gestionDeFlota/RegistroDeFlota.fxml"));

            loader.load();

            Parent root = loader.getRoot();

            RegistroDeFlotaController controller = loader.getController();
            controller.setRegistroDeFlota(getRegistroDeFlota());
            controller.setEditar(true);
            controller.llenarCampo();
            ClaseUtil.getStageModal(root);

            lista.clear();

            lista.addAll(ManejoRegistroDeFlota.getInstancia().getLista());

        }

    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) throws IOException {

        util.ClaseUtil.exportarDAtos(tbFlota);
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
        lista.addAll(ManejoRegistroDeFlota.getInstancia().getListaEntreFecha(fechaini, fechafin));

        iniciazarFiltro();
    }

}
