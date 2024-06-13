/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.cliente;

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
import manejo.cliente.ManejoCliente;
import modelo.Cliente;
import modelo.Suplidor;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class FXMLBusClienterController implements Initializable {

    @FXML
    private TableView<Cliente> tbCliente;
    @FXML
    private TableColumn<Cliente, Integer> tbcCodigo;
    @FXML
    private TableColumn<Cliente, String> tbcNombre;
    @FXML
    private TableColumn<Cliente, String> tbcRNC;
    @FXML
    private TableColumn<Cliente, String> tbcTelefono;

    @FXML
    private JFXTextField txtFiltro;

    ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();

    Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

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

        FilteredList<Cliente> filteredData = new FilteredList<>(tbCliente.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(cliente -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String upperCaseFilter = newValue.toUpperCase();

                if (cliente.getNombre().toUpperCase().contains(upperCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (cliente.getRnc() != null && cliente.getRnc().toLowerCase().contains(upperCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (cliente.getCodigo() != null && cliente.getCodigo().toString().toLowerCase().contains(upperCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Cliente> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbCliente.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbCliente.setItems(sortedData);
    }

    private void inicializarTabla() {

        try {

            tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
            tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tbcRNC.setCellValueFactory(new PropertyValueFactory<>("Rnc"));
            tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));

            listaCliente.addAll(ManejoCliente.getInstancia().getCliente());
            tbCliente.setItems(listaCliente);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tbClienteMouseCliked(MouseEvent event) {

        if (!(tbCliente.getSelectionModel().getSelectedIndex() == -1)) {

            setCliente(tbCliente.getSelectionModel().getSelectedItem());
            System.out.println(getCliente().getNombre());

            Stage stage = (Stage) tbCliente.getScene().getWindow();
            stage.close();
        }
    }

}
