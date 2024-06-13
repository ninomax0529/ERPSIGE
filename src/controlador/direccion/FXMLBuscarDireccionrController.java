/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.direccion;

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
import manejo.direccion.ManejoDireccion;
import modelo.Cliente;
import modelo.Direccion;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class FXMLBuscarDireccionrController implements Initializable {

    /**
     * @return the direccion
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<Direccion> tbDireccion;
    @FXML
    private TableColumn<Direccion, String> tbcCiudad;
    @FXML
    private TableColumn<Direccion, String> tbcSector;
    @FXML
    private TableColumn<Direccion, String> tbcDireccion;

    ObservableList<Direccion> listaDireccion = FXCollections.observableArrayList();

    private Direccion direccion;

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

        FilteredList<Direccion> filteredData = new FilteredList<>(tbDireccion.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(suplidorFiltro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (suplidorFiltro.getDireccion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (suplidorFiltro.getSector()!= null && suplidorFiltro.getSector().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } 

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Direccion> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbDireccion.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbDireccion.setItems(sortedData);
    }

    private void inicializarTabla() {

        try {

            tbcCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
            tbcSector.setCellValueFactory(new PropertyValueFactory<>("sector"));
            tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

            listaDireccion.addAll(ManejoDireccion.getInstancia().getLista());
            tbDireccion.setItems(listaDireccion);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tbClienteMouseCliked(MouseEvent event) {

        if (!(tbDireccion.getSelectionModel().getSelectedIndex() == -1)) {

            setDireccion(tbDireccion.getSelectionModel().getSelectedItem());
            System.out.println(getDireccion().getDireccion());

            Stage stage = (Stage) tbDireccion.getScene().getWindow();
            stage.close();
        }
    }

}
