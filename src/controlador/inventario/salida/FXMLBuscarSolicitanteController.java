/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.salida;

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
import manejo.inventario.ManejoSolicitante;
import modelo.Solicitante;
import util.TooltippedTableCell;


/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLBuscarSolicitanteController implements Initializable {

    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<Solicitante> tbSolicitante;
    @FXML
    private TableColumn<Solicitante, String> tbcNombre;
    @FXML
    private TableColumn<Solicitante, String> tbcFicha;

    private Solicitante solicitante;

    ObservableList<Solicitante> listaSolicitante = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
        iniciazarFiltro();
    }

    private void inicializarTabla() {

        try {

            listaSolicitante.addAll(ManejoSolicitante.getInstancia().getListaSolicitante());
            tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
               tbcFicha.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            tbcNombre.setCellFactory(TooltippedTableCell.forTableColumn());
          

            tbSolicitante.setItems(listaSolicitante);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciazarFiltro() {

        FilteredList<Solicitante> filteredData = new FilteredList<>(tbSolicitante.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(solicitanteFiltro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return false;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (solicitanteFiltro.getNombre()!= null && solicitanteFiltro.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (solicitanteFiltro.getCodigo()!= null && solicitanteFiltro.getCodigo().toString().contains(lowerCaseFilter)) {

                    return true; // Filter matches first ficha.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Solicitante> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbSolicitante.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbSolicitante.setItems(sortedData);
    }

    /**
     * @return the solicitante
     */
    public Solicitante getSolicitante() {
        return solicitante;
    }

    /**
     * @param solicitante the solicitante to set
     */
    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    @FXML
    private void tbSolicitanteMouseClicked(MouseEvent event) {

        if (!(tbSolicitante.getSelectionModel().getSelectedIndex() == -1)) {

            if (event.getClickCount() == 2) {

                setSolicitante(tbSolicitante.getSelectionModel().getSelectedItem());
                System.out.println(getSolicitante().getNombre());

                Stage stage = (Stage) tbSolicitante.getScene().getWindow();
                stage.close();
            }

        }
    }

}
