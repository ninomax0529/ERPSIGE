/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.delivery;

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
import manejo.delivery.ManejoDelivery;
import modelo.Delivery;

/**
 * FXML Controller class
 *
 * @author maximiliano
 */
public class FXMLBuscarDeliveryrController implements Initializable {

    /**
     * @return the delivery
     */
    public Delivery getDelivery() {
        return delivery;
    }

    /**
     * @param delivery the delivery to set
     */
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @FXML
    private JFXTextField txtFiltro;

    ObservableList<Delivery> lista = FXCollections.observableArrayList();

    @FXML
    private TableView<Delivery> tbDelivery;
    @FXML
    private TableColumn<Delivery, String> tbcApellido;
    @FXML
    private TableColumn<Delivery, String> tbcCelular;
    @FXML
    private TableColumn<Delivery, String> tbcNombre;
    private Delivery delivery;

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

        FilteredList<Delivery> filteredData = new FilteredList<>(tbDelivery.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(suplidorFiltro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (suplidorFiltro.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (suplidorFiltro.getApellido() != null && suplidorFiltro.getApellido().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (suplidorFiltro.getCodigo() != null && suplidorFiltro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Delivery> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbDelivery.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbDelivery.setItems(sortedData);
    }

    private void inicializarTabla() {

        try {

            tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tbcApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
            tbcCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));

            lista.addAll(ManejoDelivery.getInstancia().getLista());
            tbDelivery.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tbClienteMouseCliked(MouseEvent event) {

        if (!(tbDelivery.getSelectionModel().getSelectedIndex() == -1)) {

            setDelivery(tbDelivery.getSelectionModel().getSelectedItem());
            System.out.println(getDelivery().getNombre());

            Stage stage = (Stage) tbDelivery.getScene().getWindow();
            stage.close();
        }
    }

}
