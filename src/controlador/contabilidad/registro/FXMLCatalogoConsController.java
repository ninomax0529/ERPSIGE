/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.registro;

import controlador.contabilidad.proceso.FXMLEntradaDiarioControllerAnt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import manejo.contabilidadd.CatalogoDao;
import modelo.Catalogo;

/**
 * FXML Controller class
 *
 * @author maximiliano
 */
public class FXMLCatalogoConsController implements Initializable {

    @FXML
    private TextField txBuscar;
    @FXML
    private TableView<Catalogo> tbCatalogo;
    @FXML
    private TableColumn<Catalogo, String> tcCuenta;
    @FXML
    private TableColumn<Catalogo, String> tcDescripcion;

    public FXMLEntradaDiarioControllerAnt entradaDiarioController;

    private Catalogo catalogo;

    ObservableList<Catalogo> listaCatalogo = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializarTabla();
        iniciazarFiltro();

        txBuscar.requestFocus();

    }

    @FXML
    private void tbCatalogoActionEvent(MouseEvent event) throws IOException {

        if (!(tbCatalogo.getSelectionModel().getSelectedIndex() == -1)) {

            System.out.println(tbCatalogo.getSelectionModel().getSelectedItem().getDescripcion());
            setCatalogo(tbCatalogo.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) tbCatalogo.getScene().getWindow();
            stage.close();
        }

    }

    private void inicializarTabla() {

        try {

            tcCuenta.setCellValueFactory(new PropertyValueFactory<>("Cuenta"));
            tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
            listaCatalogo.addAll(CatalogoDao.getInstancia().getCatalogo());
            tbCatalogo.setItems(listaCatalogo);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    private void cbBuscarActionEvent(ActionEvent event) {
        txBuscar.requestFocus();
    }

    private void iniciazarFiltro() {

        FilteredList<Catalogo> filteredData = new FilteredList<>(tbCatalogo.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txBuscar.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(contrato -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (contrato.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (contrato.getCuenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Catalogo> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbCatalogo.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbCatalogo.setItems(sortedData);
    }
}
