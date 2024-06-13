/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo;

import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
import manejo.articulo.ManejoArticulo;
import modelo.Articulo;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLBuscarArticuloController implements Initializable {

    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<Articulo> tbArticulo;
    @FXML
    private TableColumn<Articulo, Integer> tbcCodigo;
    @FXML
    private TableColumn<Articulo, String> tbcDescripcion;
    private Articulo articulo;
    String tipoArticulo;

    public String getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(String tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }
    int origen = 1;//entrada de mercancia,2 salida de mercancia por venta y 3 salida por consumo

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    ObservableList<Articulo> listaArticulo = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
        iniciazarFiltro();
    }

    @FXML
    private void tbSuplidorActionEvent(MouseEvent event) {

        if (!(tbArticulo.getSelectionModel().getSelectedIndex() == -1)) {

            setArticulo(tbArticulo.getSelectionModel().getSelectedItem());
            System.out.println(getArticulo().getDescripcion());

            Stage stage = (Stage) tbArticulo.getScene().getWindow();
            stage.close();
        }
    }

    private void inicializarTabla() {

        try {

//            listaArticulo.addAll(ManejoArticulo.getInstancia().getListaArticulo());
            tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));

            tbcDescripcion.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        String desc = "";
                        if (cellData.getValue() != null) {

                            if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {

                                if (!(cellData.getValue().getTipoArticulo().getCodigo() == 3)) {
                                    desc = cellData.getValue().getDescripcion()
                                   ;
                                } else {
                                    desc = cellData.getValue().getDescripcion();
                                }
                            } else {

                                desc = cellData.getValue().getDescripcion();

                            }

                            property.setValue(desc);
                        }
                        return property;
                    });

//            tbcDescripcion.setCellValueFactory(
//                    cellData -> {
//                        SimpleStringProperty property = new SimpleStringProperty();
//                        if (cellData.getValue() != null) {
//                            property.setValue(cellData.getValue().getCalidadPintura() == null ? cellData.getValue().getNombre() + " "
//                                    : cellData.getValue().getNombre() + " " + cellData.getValue().getCalidadPintura().getNombre());
//                        }
//                        return property;
//                    });
            tbArticulo.setItems(listaArticulo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciazarFiltro() {

        FilteredList<Articulo> filteredData = new FilteredList<>(tbArticulo.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(articuloFiltro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (articuloFiltro.getDescripcion() != null && articuloFiltro.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (articuloFiltro.getNombre()!= null && articuloFiltro.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (articuloFiltro.getNumero() != null && articuloFiltro.getNumero().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Articulo> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbArticulo.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbArticulo.setItems(sortedData);
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public void llenarCampo() {

        System.out.println("Origen " + getOrigen());

        if (getOrigen() == 3) {//desde contrato
            listaArticulo.addAll(ManejoArticulo.getInstancia().getListaAticuloPorTipo(getTipoArticulo()));

        }
        if (getOrigen() == 4) {//desde contrato
            listaArticulo.addAll(ManejoArticulo.getInstancia().getListaEquipoGps(getTipoArticulo()));
        } else {
            listaArticulo.addAll(ManejoArticulo.getInstancia().getListaArticulos());
        }

//          listaArticulo.addAll(ManejoArticulo.getInstancia().getListaArticuloPorUnidadDeNegocio());
    }
}
