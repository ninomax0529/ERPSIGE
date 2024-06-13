/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.proyecto;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import manejo.proyecto.ManejoProyecto;
import modelo.Proyecto;
import modelo.RegistroHoraExtra;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class BuscarProyectoController implements Initializable {

    @FXML
    private JFXTextField txtCantidad;

    /**
     * @return the proyecto
     */
    public Proyecto getProyecto() {
        return proyecto;
    }

    /**
     * @param proyecto the proyecto to set
     */
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @FXML
    private TabPane tabPane;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<Proyecto> tbHoraExtra;
    @FXML
    private TableColumn<Proyecto, String> tbcNumeroNom;
    @FXML
    private TableColumn<Proyecto, String> tbcNombre;
    @FXML
    private TableColumn<Proyecto, String> tbcEstado;

    ObservableList<Proyecto> lista = FXCollections.observableArrayList();

    private Proyecto proyecto;

    private RegistroHoraExtra rgHoraExtra;
    Date ff, fi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTabla();
        iniciazarFiltro();

    }

    public void inicializarTabla() {

        try {

            lista.addAll(ManejoProyecto.getInstancia().getLista());

            tbcNumeroNom.setCellValueFactory(new PropertyValueFactory<>("codigo"));

            tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

            tbcEstado.setCellValueFactory(
                    cellData -> {

                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue() != null) {

                            property.setValue(cellData.getValue().getEstado().getNombre());

                        }
                        return property;
                    });

            tbHoraExtra.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciazarFiltro() {

        FilteredList<Proyecto> filteredData = new FilteredList<>(tbHoraExtra.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getNombre() != null && filtro.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getCodigo()!= null && filtro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                  return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Proyecto> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbHoraExtra.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbHoraExtra.setItems(sortedData);
    }

    @FXML
    private void tbContratoDeServicioClicked(MouseEvent event) {

        if (!(tbHoraExtra.getSelectionModel().getSelectedIndex() == -1)) {

            setProyecto(tbHoraExtra.getSelectionModel().getSelectedItem());

            if (event.getClickCount() == 2) {
                Stage stage = (Stage) tbHoraExtra.getScene().getWindow();
                stage.close();
            }
        }
    }

}
