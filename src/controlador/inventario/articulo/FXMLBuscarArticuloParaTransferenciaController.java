/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo;

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
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import manejo.articulo.ManejoArticulo;
import manejo.articulo.ManejoExistenciaArticulo;
import modelo.Articulo;
import modelo.ExistenciaArticulo;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLBuscarArticuloParaTransferenciaController implements Initializable {

    ExistenciaArticulo existenciaArticulo;
    @FXML
    private JFXTextField txtFiltraralmOrigen;
    @FXML
    private TableView<ExistenciaArticulo> tbArticuloAlmacenOrigen;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcCodalmOrigen;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcNomAlmOrigen;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcCodArtAlmOrigen;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcDescArtalmOrigen;
    @FXML
    private JFXTextField txtFiltraralmDestino;
    @FXML
    private TableView<ExistenciaArticulo> tbArticuloAlmacenDestino;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcCodalmDestino;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcNomAlmDestino;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcCodArtAlmDestino;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcDescArtalmDestino;
    @FXML
    private JFXButton btnAgregar;

    public ExistenciaArticulo getExistenciaArticulo() {
        return existenciaArticulo;
    }

    public void setExistenciaArticulo(ExistenciaArticulo existenciaArticulo) {
        this.existenciaArticulo = existenciaArticulo;
    }
    int origen = 1;//entrada de mercancia,2 salida de mercancia por venta y 3 salida por consumo

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    ObservableList<ExistenciaArticulo> listaAlmDestino = FXCollections.observableArrayList();
    ObservableList<ExistenciaArticulo> listaAlmOrigen = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablaalmDestino();
        inicializarTablaalmorigen();
        iniciazarFiltroAlmDestino();
        iniciazarFiltroAlmOrigen();
    }

    private void inicializarTablaalmDestino() {

        try {

            listaAlmOrigen.addAll(ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo());

            tbcCodalmOrigen.setCellValueFactory(new PropertyValueFactory<>("almacen"));
            tbcNomAlmOrigen.setCellValueFactory(new PropertyValueFactory<>("almacen"));

            tbcCodArtAlmOrigen.setCellValueFactory(new PropertyValueFactory<>("articulo"));
            tbcDescArtalmOrigen.setCellValueFactory(new PropertyValueFactory<>("articulo"));

            tbArticuloAlmacenOrigen.setItems(listaAlmOrigen);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inicializarTablaalmorigen() {

        try {

            listaAlmDestino.addAll(ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo());

            tbcCodalmDestino.setCellValueFactory(new PropertyValueFactory<>("almacen"));
            tbcNomAlmDestino.setCellValueFactory(new PropertyValueFactory<>("almacen"));

            tbcCodArtAlmDestino.setCellValueFactory(new PropertyValueFactory<>("articulo"));
            tbcDescArtalmDestino.setCellValueFactory(new PropertyValueFactory<>("articulo"));

            tbArticuloAlmacenOrigen.setItems(listaAlmOrigen);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciazarFiltroAlmOrigen() {

        FilteredList<ExistenciaArticulo> filteredData = new FilteredList<>(tbArticuloAlmacenOrigen.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltraralmOrigen.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(articuloFiltro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (articuloFiltro.getAlmacen() != null && articuloFiltro.getAlmacen().getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (articuloFiltro.getArticulo() != null && articuloFiltro.getArticulo().getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (articuloFiltro.getAlmacen() != null && articuloFiltro.getAlmacen().getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (articuloFiltro.getArticulo() != null && articuloFiltro.getArticulo().getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ExistenciaArticulo> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbArticuloAlmacenOrigen.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbArticuloAlmacenOrigen.setItems(sortedData);
    }

    private void iniciazarFiltroAlmDestino() {

        FilteredList<ExistenciaArticulo> filteredData = new FilteredList<>(tbArticuloAlmacenDestino.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltraralmDestino.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(articuloFiltro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (articuloFiltro.getAlmacen() != null && articuloFiltro.getAlmacen().getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (articuloFiltro.getArticulo() != null && articuloFiltro.getArticulo().getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (articuloFiltro.getAlmacen() != null && articuloFiltro.getAlmacen().getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (articuloFiltro.getArticulo() != null && articuloFiltro.getArticulo().getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ExistenciaArticulo> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbArticuloAlmacenDestino.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbArticuloAlmacenDestino.setItems(sortedData);
    }

    public void llenarCampo() {

        System.out.println("Origen " + getOrigen());

    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {
        
    }
}
