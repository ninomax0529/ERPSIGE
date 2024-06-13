/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.gps;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import manejo.contrato.ManejoImeiGps;
import manejo.menuModulo.ManejoMenuModulo;
import modelo.Articulo;
import modelo.DetalleContratoDeServicio;
import modelo.RegistroDeImei;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class BuscarImeiGpsController implements Initializable {

    /**
     * @return the articulo
     */
    public Articulo getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    /**
     * @return the resgistro
     */
    public RegistroDeImei getRegistro() {
        return resgistro;
    }

    /**
     * @param registro the resgistro to set
     */
    public void setRegistro(RegistroDeImei registro) {
        this.resgistro = registro;
    }

    /**
     * @return the codigoMenuModulo
     */
    public Integer getCodigoMenuModulo() {
        return codigoMenuModulo;
    }

    /**
     * @param codigoMenuModulo the codigoMenuModulo to set
     */
    public void setCodigoMenuModulo(Integer codigoMenuModulo) {
        this.codigoMenuModulo = codigoMenuModulo;
    }

    @FXML
    private TabPane tabPane;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<RegistroDeImei> tbImeiGps;
    @FXML
    private TableColumn<RegistroDeImei, String> tbcFecha;
    @FXML
    private TableColumn<RegistroDeImei, String> tbcFechaRegistro;
    @FXML
    private TableColumn<RegistroDeImei, String> tbcArticulo;
    @FXML
    private TableColumn<RegistroDeImei, String> tbcNumeroImei;
    @FXML
    private JFXTextField txtCantidad;

    ObservableList<RegistroDeImei> listaImei = FXCollections.observableArrayList();
    ObservableList<DetalleContratoDeServicio> listaSimMovimiento = FXCollections.observableArrayList();
 
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    private Integer codigoMenuModulo;
    private RegistroDeImei resgistro;
    private Articulo articulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setCodigoMenuModulo(ManejoMenuModulo.getInstancia().getMenuModulo(9, "btnRegistroContrato"));
      
        inicializarTabla();
        iniciazarFiltro();
    }

    private void iniciazarFiltro() {

        FilteredList<RegistroDeImei> filteredData = new FilteredList<>(tbImeiGps.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(registro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (registro.getNumero().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (registro.getFechaEntrada().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<RegistroDeImei> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbImeiGps.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbImeiGps.setItems(sortedData);
    }

    public void inicializarTabla() {

        try {

//            listaImei.addAll(ManejoImeiGps.getInstancia().getListaImeiDisponible());

            tbcNumeroImei.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fechaEntrada"));

            tbcArticulo.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getArticulo() != null) {
                            property.setValue(cellData.getValue().getArticulo().getNumero().toString());
                        }
                        return property;
                    });

            tbcFecha.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaEntrada() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaEntrada()));
                        }
                        return property;
                    });

            tbcFechaRegistro.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaRegistro() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaRegistro()));
                        }
                        return property;
                    });

            tbImeiGps.setItems(listaImei);
//            lb.setText(Integer.toString(listaImei.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void tbSimCardMouseClicked(MouseEvent event) {

        if (!(tbImeiGps.getSelectionModel().getSelectedIndex() == -1)) {

            if (event.getClickCount() == 2) {
                setRegistro(tbImeiGps.getSelectionModel().getSelectedItem());

                Stage stage = (Stage) tbImeiGps.getScene().getWindow();
                stage.close();
            }

        }

    }

    public void llenarCampo() {

        listaImei.clear();
        listaImei.addAll(ManejoImeiGps.getInstancia().getImeiDisponiblePorArticulo(getArticulo().getCodigo()));

    }

}
