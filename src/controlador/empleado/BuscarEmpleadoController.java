/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.empleado;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import manejo.empleado.ManejoEmpleado;
import modelo.Empleado;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class BuscarEmpleadoController implements Initializable {

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
    private TabPane tabCliente;
    @FXML
    private TableView<Empleado> tbcEmpleado;
    @FXML
    private TableColumn<Empleado, String> tbcCodigo;
    @FXML
    private TableColumn<Empleado, String> tbcNombre;
    @FXML
    private TableColumn<Empleado, String> tbcCelular;
    @FXML
    private TableColumn<Empleado, String> tbcCargo;
    @FXML
    private TableColumn<Empleado, String> tbcTipoNomina;
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
            filteredData.setPredicate(cliente -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String upperCaseFilter = newValue.toLowerCase();

                if (cliente.getNombre().toLowerCase().contains(upperCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (cliente.getCedula() != null && cliente.getCedula().toLowerCase().contains(upperCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (cliente.getCodigo() != null && cliente.getCodigo().toString().toLowerCase().contains(upperCaseFilter)) {
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

        tbcCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        tbcTipoNomina.setCellValueFactory(new PropertyValueFactory<>("tipo"));

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

        tbcEmpleado.setItems(lista);

    }

    @FXML
    private void tbClienteActionEvent(MouseEvent event) {

        if (!(tbcEmpleado.getSelectionModel().getSelectedIndex() == -1)) {

            setEmpleado(tbcEmpleado.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) tbcEmpleado.getScene().getWindow();
            stage.close();
        }
    }

}
