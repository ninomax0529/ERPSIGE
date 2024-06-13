/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.proyecto;

import controlador.nomina.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import manejo.nomina.ManejoRegistroHoraExtra;
import manejo.proyecto.ManejoProyecto;
import modelo.DetalleRegistroHoraExtra;
import modelo.Proyecto;
import modelo.RegistroHoraExtra;
import reporte.nomina.RpTHorasExtras;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ProyectoController implements Initializable {

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
    private JFXTextArea txtDescripcion;

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private TabPane tabPane;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<Proyecto> tbHoraExtra;
    @FXML
    private TableColumn<Proyecto, String> tbcNumeroNom;
    @FXML
    private TableColumn<Proyecto, String> tbcFechaInicio;
    @FXML
    private TableColumn<Proyecto, String> tbcFechaFinal;
    @FXML
    private TableColumn<Proyecto, Proyecto> tbcNombre;
    @FXML
    private TableColumn<Proyecto, String> tbcEstado;
    @FXML
    ObservableList<DetalleRegistroHoraExtra> listaDetalle = FXCollections.observableArrayList();
    ObservableList<Proyecto> lista = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnEditar;

    private Proyecto proyecto;

    private RegistroHoraExtra rgHoraExtra;
    Date ff, fi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

        fi = ClaseUtil.asDate(dpFechaInicio.getValue());
        ff = ClaseUtil.asDate(dpFechaFinal.getValue());

        inicializarTabla();
        iniciazarFiltro();

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
                } else if (filtro.getCodigo() != null && filtro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
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
    private void btnBuscarActionEvent(ActionEvent event) {

        try {
            buscar();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void inicializarTabla() {

        try {

            listaDetalle.clear();
            lista.addAll(ManejoProyecto.getInstancia().getLista());

            tbcNumeroNom.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            tbcFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
            tbcFechaFinal.setCellValueFactory(new PropertyValueFactory<>("fechaFinal"));
            tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

            tbcFechaInicio.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaInicio() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaInicio()));
                        }
                        return property;
                    });

            tbcFechaFinal.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaFinal() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaFinal()));
                        }
                        return property;
                    });

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

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/proyecto/RegistroProyecto.fxml"));

            ClaseUtil.crearStageModal(root);

            lista.clear();
            lista.addAll(ManejoProyecto.getInstancia().getLista());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void tbContratoDeServicioClicked(MouseEvent event) {

        if (!(tbHoraExtra.getSelectionModel().getSelectedIndex() == -1)) {

            listaDetalle.clear();

            Proyecto reg = tbHoraExtra.getSelectionModel().getSelectedItem();
            txtDescripcion.setText(reg.getDescripcion());

            if (event.getClickCount() == 2) {

                tabPane.getSelectionModel().select(1);
            }
        }
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) {

        try {

            if (tbHoraExtra.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN PROYECTO");

            } else {

                setProyecto(tbHoraExtra.getSelectionModel().getSelectedItem());

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/vista/proyecto/RegistroProyecto.fxml"));
                loader.load();

                Parent root = loader.getRoot();

                RegistroProyectoController rgController = loader.getController();

                rgController.setEditar(true);
                rgController.setProyecto(getProyecto());
                rgController.llenarCampo();

                ClaseUtil.getStageModalcONTRATO(root);

                lista.clear();
                lista.addAll(ManejoProyecto.getInstancia().getLista());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void buscar() {

        lista.clear();
        listaDetalle.clear();

        fi = ClaseUtil.asDate(dpFechaInicio.getValue());
        ff = ClaseUtil.asDate(dpFechaFinal.getValue());

        lista.addAll(ManejoProyecto.getInstancia().getLista(fi, ff));
    }

}
