/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.compra.consulta;

import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.Date;
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
import manejo.ordenCompra.OrdenDeCompraDao;
import modelo.OrdenCompra;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class FXMLBusOrdenCompraController implements Initializable {

    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<OrdenCompra> tbOrdenComppra;
    @FXML
    private TableColumn<OrdenCompra, Integer> tbcOrden;
    @FXML
    private TableColumn<OrdenCompra, Date> tbcFecha;
    @FXML
    private TableColumn<OrdenCompra, String> tbcSuplidor;
    @FXML
    private TableColumn<OrdenCompra, String> tbcRnc;

    ObservableList<OrdenCompra> listaOrdenCompra = FXCollections.observableArrayList();

    private OrdenCompra ordenCompra;
    int suplidor;

    public int getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(int suplidor) {
        this.suplidor = suplidor;
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

        FilteredList<OrdenCompra> filteredData = new FilteredList<>(tbOrdenComppra.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getCodigo() != null && filtro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getRnc() != null && filtro.getRnc().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filtro.getCodigo() != null && filtro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<OrdenCompra> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbOrdenComppra.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbOrdenComppra.setItems(sortedData);
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    @FXML
    private void tbOrdenComppraMouseClicked(MouseEvent event) {

        if (!(tbOrdenComppra.getSelectionModel().getSelectedIndex() == -1)) {

            ordenCompra = tbOrdenComppra.getSelectionModel().getSelectedItem();
            System.out.println(ordenCompra.getNombreSuplidor());
            setOrdenCompra(ordenCompra);
            Stage stage = (Stage) tbOrdenComppra.getScene().getWindow();
            stage.close();
        }
    }

    public void inicializarTabla() {

        try {

            listaOrdenCompra.clear();
            tbcOrden.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tbcRnc.setCellValueFactory(new PropertyValueFactory<>("Rnc"));
            tbcSuplidor.setCellValueFactory(new PropertyValueFactory<>("nombreSuplidor"));

            System.out.println("Codigo suplidor  "+getSuplidor());
            
             listaOrdenCompra.addAll(OrdenDeCompraDao.getInstancia()
                    .getListOrdenDeCompraAutorizada("Abierta"));
           

            tbOrdenComppra.setItems(listaOrdenCompra);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
