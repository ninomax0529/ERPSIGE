/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion.lote;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import manejo.produccion.ManejoRegistroLote;
import modelo.Articulo;
import modelo.RegistroLote;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class BusacrLoteDeProduccionController implements Initializable {

    @FXML
    private TableView<RegistroLote> tbLoteDeProduccion;
    @FXML
    private TableColumn<RegistroLote, String> tbcLote;
    @FXML
    private TableColumn<RegistroLote, String> tbcProduto;
    @FXML
    private TableColumn<RegistroLote, String> tbccodProducto;
    @FXML
    private TableColumn<RegistroLote, Date> tbcFechaProduccion;
    @FXML
    private TableColumn<RegistroLote, String> tbcProduccion;
    @FXML
    private TableColumn<RegistroLote, String> tbcUnidad;

    ObservableList<RegistroLote> lista = FXCollections.observableArrayList();
    RegistroLote registroLote;
    @FXML
    private JFXTextField txtFiltro;

    Articulo articulo;

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public RegistroLote getRegistroLote() {
        return registroLote;
    }

    public void setRegistroLote(RegistroLote registroLote) {
        this.registroLote = registroLote;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabla();
        iniciazarFiltro();
    }

    public void iniciarTabla() {

        tbLoteDeProduccion.setItems(lista);
        lista.addAll(ManejoRegistroLote.getInstancia().getLista());

        tbcFechaProduccion.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcLote.setCellValueFactory(new PropertyValueFactory<>("numeroDeLote"));
        tbcProduto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        tbcProduccion.setCellValueFactory(new PropertyValueFactory<>("produccion"));
        tbccodProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));

        tbcProduto.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getProducto().getDescripcion());
                    }
                    return property;
                });
        tbccodProducto.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getProducto().getCodigoBarra());
                    }
                    return property;
                });

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

    }

    private void iniciazarFiltro() {

        FilteredList<RegistroLote> filteredData = new FilteredList<>(tbLoteDeProduccion.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getProducto() != null && filtro.getProducto().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (Integer.toString(filtro.getNumeroDeLote()).contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<RegistroLote> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbLoteDeProduccion.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbLoteDeProduccion.setItems(sortedData);
    }

    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/produccion/lote/RegistroLoteDeProduccion.fxml"));

        ClaseUtil.crearStageModal(root);

        lista.clear();
        lista.addAll(ManejoRegistroLote.getInstancia().getLista());
    }

    @FXML
    private void tbLoteDeProduccionMouseClicked(MouseEvent event) {

        if (!(tbLoteDeProduccion.getSelectionModel().getSelectedIndex() == -1)) {

            setRegistroLote(tbLoteDeProduccion.getSelectionModel().getSelectedItem());

            if (event.getClickCount() == 1) {

                Stage st = (Stage) (tbLoteDeProduccion.getScene().getWindow());
                st.close();
            }
        }
    }

    public void buscarLote() {

        lista.clear();

        lista.addAll(ManejoRegistroLote.getInstancia().getLoteArticulo(getArticulo().getCodigo()));
    }

}
