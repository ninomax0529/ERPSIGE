/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.nomina;

import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import manejo.empleado.ManejoEmpleado;
import modelo.Empleado;

/**
 * FXML Controller class
 *
 * @author Luis
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
    private TableView<Empleado> tbEmpleado;
    @FXML
    private TableColumn<Empleado, Integer> tbcCodigo;
    @FXML
    private TableColumn<Empleado, String> tbcNombre;
    @FXML
    private TableColumn<Empleado, String> tbcRNC;
    @FXML
    private TableColumn<Empleado, String> tbcTelefono;

    @FXML
    private JFXTextField txtFiltro;

    ObservableList<Empleado> lista = FXCollections.observableArrayList();

    private Empleado empleado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializarTabla();
        iniciazarFiltro();

    }

    private void iniciazarFiltro() {

        FilteredList<Empleado> filteredData = new FilteredList<>(tbEmpleado.getItems(), p -> true);
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
        sortedData.comparatorProperty().bind(tbEmpleado.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbEmpleado.setItems(sortedData);
    }

    private void inicializarTabla() {

        try {

            tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
            tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tbcRNC.setCellValueFactory(new PropertyValueFactory<>("Cedula"));
            tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));

            lista.addAll(ManejoEmpleado.getInstancia().getLista());
            tbEmpleado.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tbClienteMouseCliked(MouseEvent event) {

        if (!(tbEmpleado.getSelectionModel().getSelectedIndex() == -1)) {

            setEmpleado(tbEmpleado.getSelectionModel().getSelectedItem());
            System.out.println(getEmpleado().getNombre());

            Stage stage = (Stage) tbEmpleado.getScene().getWindow();
            stage.close();
        }
    }

}
