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
import manejo.gestionDeFlota.ManejoAsignacionDeFlota;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import modelo.AsignacionDeFlota;
import modelo.MenuModulo;
import modelo.RolMenuModulo;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class AsignacionDeFlotaController implements Initializable {

    /**
     * @return the asignacionDeFlota
     */
    public AsignacionDeFlota getAsignacionDeFlota() {
        return asignacionDeFlota;
    }

    /**
     * @param asignacionDeFlota the asignacionDeFlota to set
     */
    public void setAsignacionDeFlota(AsignacionDeFlota asignacionDeFlota) {
        this.asignacionDeFlota = asignacionDeFlota;
    }

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private TableColumn<AsignacionDeFlota, String> tbcNumeroSim;
    @FXML
    private TableView<AsignacionDeFlota> tbAsignacionFlota;
    @FXML
    private TableColumn<AsignacionDeFlota, String> tbcResponsable;
    @FXML
    private TableColumn<AsignacionDeFlota, String> tbcFecha;
    @FXML
    private TableColumn<AsignacionDeFlota, String> tbcAsignadoPor;
    @FXML
    private TableColumn<AsignacionDeFlota, String> tbcMarca;
    @FXML
    private TableColumn<AsignacionDeFlota, String> tbcModelo;
    @FXML
    private TableColumn<AsignacionDeFlota, String> tbcEStado;

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

    ObservableList<AsignacionDeFlota> lista = FXCollections.observableArrayList();

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

        FilteredList<AsignacionDeFlota> filteredData = new FilteredList<>(tbAsignacionFlota.getItems(), p -> true);
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
                } else if (!(registro.getNombreResponsable() == null) && registro.getNombreResponsable().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (registro.getMarca().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (registro.getFecha().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<AsignacionDeFlota> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbAsignacionFlota.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbAsignacionFlota.setItems(sortedData);
    }

    public void inicializarTabla() {

        try {

            lista.addAll(ManejoAsignacionDeFlota.getInstancia().getLista());

            tbcNumeroSim.setCellValueFactory(new PropertyValueFactory<>("sim"));
            tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
            tbcResponsable.setCellValueFactory(new PropertyValueFactory<>("nombreResponsable"));
            tbcAsignadoPor.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
            tbcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
            tbcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

            tbcFecha.setCellValueFactory(
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
                        if (cellData.getValue().getEstado() != null) {
                            property.setValue(cellData.getValue().getEstado());
                        }
                        return property;
                    });

            tbcFecha.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFecha() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy")
                                    .format(cellData.getValue().getFecha()));
                        }
                        return property;
                    });
         

            tbAsignacionFlota.setItems(lista);
            txtCantidadSim.setText(Integer.toString(lista.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/gestionDeFlota/RegistroAsignacionDeFlota.fxml"));
        ClaseUtil.getStageModal(root);

        lista.clear();

        lista.addAll(ManejoAsignacionDeFlota.getInstancia().getLista());

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

        if (tbAsignacionFlota.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN REGISTRO ");

        } else {

            setAsignacionDeFlota(tbAsignacionFlota.getSelectionModel().getSelectedItem());

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/gestionDeFlota/RegistroAsignacionDeFlota.fxml"));

            loader.load();

            Parent root = loader.getRoot();

            RegistroAsignacionDeFlotaController controller = loader.getController();
            controller.setAsignacion(getAsignacionDeFlota());
            controller.setEditar(true);
            controller.llenarCampo();

            ClaseUtil.getStageModal(root);

            lista.clear();

            lista.addAll(ManejoAsignacionDeFlota.getInstancia().getLista());

        }

    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) throws IOException {

        util.ClaseUtil.exportarDAtos(tbAsignacionFlota);
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
        lista.addAll(ManejoAsignacionDeFlota.getInstancia().getListaEntreFecha(fechaini, fechafin));

        iniciazarFiltro();
    }

}
