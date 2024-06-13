/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cxp.reporte;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import manejo.proyecto.ManejoProyecto;
import modelo.Proyecto;
import reporte.cxp.RptFacturaSuplidor;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class ReporteCompraPorProyectoController implements Initializable {

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
    private JFXButton btnBuscar;

    /**
     * @return the cliente
     */
    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private TabPane tabPane;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<Proyecto> tbProyecto;
    @FXML
    private TableColumn<Proyecto, String> tbcNumeroNom;
    @FXML
    private TableColumn<Proyecto, String> tbcNombre;
    @FXML
    private TableColumn<Proyecto, String> tbcEstado;

    ObservableList<Proyecto> lista = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtCantidad;
    private Proyecto proyecto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
        inicializarTabla();
        iniciazarFiltro();
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        try {

            Date fechaInicio = ClaseUtil.asDate(dpFechaInicio.getValue());
            Date fechaFinal = ClaseUtil.asDate(dpFechaFinal.getValue());
            String sql = " ";
            if (!(getProyecto() == null)) {

                sql=" and p.codigo="+getProyecto().getCodigo();
            }

            RptFacturaSuplidor.getInstancia().compraEntreFecha(fechaInicio, fechaFinal,sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
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

            tbProyecto.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciazarFiltro() {

        FilteredList<Proyecto> filteredData = new FilteredList<>(tbProyecto.getItems(), p -> true);
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
                } else if (filtro.getCodigo() != null && filtro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Proyecto> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbProyecto.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbProyecto.setItems(sortedData);
    }

    @FXML
    private void tbProyectoClicked(MouseEvent event) {

        if (!(tbProyecto.getSelectionModel().getSelectedIndex() == -1)) {

            setProyecto(tbProyecto.getSelectionModel().getSelectedItem());
        }
    }

}
