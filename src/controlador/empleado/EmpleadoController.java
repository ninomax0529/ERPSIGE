/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.empleado;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import manejo.empleado.ManejoEmpleado;
import modelo.Empleado;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class EmpleadoController implements Initializable {

    /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnExportar;
    @FXML
    private TabPane tabCliente;
    @FXML
    private TableView<Empleado> tbcEmpleado;
    @FXML
    private TableColumn<Empleado, String> tbcCodigo;
    @FXML
    private TableColumn<Empleado, String> tbcNombre;
    @FXML
    private TableColumn<Empleado, String> tbcDireccion;
    @FXML
    private TableColumn<Empleado, String> tbcTelefono;
    @FXML
    private TableColumn<Empleado, String> tbcCelular;
    @FXML
    private TableColumn<Empleado, String> tbcEstado;
    @FXML
    private TableColumn<Empleado, String> tbcCondicion;
    @FXML
    private TableColumn<Empleado, String> tbcCargo;
    @FXML
    private TableColumn<Empleado, String> tbcSueldo;
    @FXML
    private TableColumn<Empleado, String> tbcTipoNomina;
    @FXML
    private TableColumn<Empleado, String> tbcEstadoCivil;

    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Label lbCantidad;
    private Empleado empleado;

    ObservableList<Empleado> lista = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTabla();
        iniciazarFiltro();

    }

    
    private void iniciazarFiltro() {

        FilteredList<Empleado> filteredData = new FilteredList<>(tbcEmpleado.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(empleado -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String upperCaseFilter = newValue.toLowerCase();

                if (empleado.getNombre().toLowerCase().contains(upperCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (empleado.getCelular()!= null && empleado.getCelular().toLowerCase().contains(upperCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (empleado.getCodigo() != null && empleado.getCodigo().toString().toLowerCase().contains(upperCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Empleado> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbcEmpleado.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbcEmpleado.setItems(sortedData);
    }
    
    public void iniciarTabla() {

        lista.clear();

        lista.addAll(ManejoEmpleado.getInstancia().getLista());

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tbcCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tbcTipoNomina.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        tbcSueldo.setCellValueFactory(new PropertyValueFactory<>("sueldoQuincenal"));

        tbcEstado.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getEstado().getNombre());
                    }
                    return property;
                });

        tbcEstadoCivil.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getEstadoCivil().getNombre());
                    }
                    return property;
                });

        tbcTipoNomina.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getTipoNomina().getNombre());
                    }
                    return property;
                });

        tbcCargo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getCargo().getNombre());
                    }
                    return property;
                });

        tbcCondicion.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getCondicion().getNombre());
                    }
                    return property;
                });

        tbcEmpleado.setItems(lista);

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/empleado/RegistroEmpleado.fxml"));

            ClaseUtil.crearStageModal(root);

            lista.clear();
            lista.addAll(ManejoEmpleado.getInstancia().getLista());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) {

        try {

            if (tbcEmpleado.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN EMPLEADO ");

            } else {

                setEmpleado(tbcEmpleado.getSelectionModel().getSelectedItem());

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/vista/empleado/RegistroEmpleado.fxml"));
                loader.load();

                Parent root = loader.getRoot();

                RegistroEmpleadoController registroEmpleadoController = loader.getController();

                registroEmpleadoController.setEditar(true);
                registroEmpleadoController.setEmpleado(getEmpleado());
                registroEmpleadoController.llenarCampo();

                ClaseUtil.getStageModal(root);

                lista.clear();
                lista.addAll(ManejoEmpleado.getInstancia().getLista());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) {
    }

    @FXML
    private void tbClienteActionEvent(MouseEvent event) {
    }

}
