/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo.movimiento;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.articulo.EntradaArticulo;
import dto.articulo.SalidaArticulo;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import manejo.articulo.ManejoArticulo;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class SalidaArticuloController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaDesde;
    @FXML
    private JFXDatePicker dpFecgaHasta;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnReimprimir;
    @FXML
    private JFXTextField txtFiltrar;
    @FXML
    private TableView<SalidaArticulo> tbEntradaArticulo;

    @FXML
    private TableColumn<?, ?> tbcFecha;
    @FXML
    private TableColumn<?, ?> tbcCantidad;
    @FXML
    private TableColumn<?, ?> tbcUnidad;
    @FXML
    private TableColumn<?, ?> tbcArticulo;
    @FXML
    private TableColumn<?, ?> tbcFactura;
    @FXML
    private TableColumn<?, ?> tbcPrecioVenta;
    @FXML
    private TableColumn<?, ?> tbcAnulada;
    @FXML
    private TableColumn<EntradaArticulo, Button> btnVerDocumento;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Label lbCantidad;
    private int codigoArticuulo;

    ObservableList<SalidaArticulo> listaArticulo = FXCollections.observableArrayList();

    Date fechaini, fechafin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFecgaHasta.setValue(LocalDate.now());
        dpFechaDesde.setValue(LocalDate.now());
        iniciarTabla();
        iniciazarFiltro();
        
        System.out.println("Codigo Articulo "+codigoArticuulo);

    }

    public void iniciarTabla() {
//
        fechaini = ClaseUtil.asDate(dpFechaDesde.getValue());
        fechafin = ClaseUtil.asDate(dpFecgaHasta.getValue());

        listaArticulo.clear();

        tbEntradaArticulo.setItems(listaArticulo);
   
        tbcFactura.setCellValueFactory(new PropertyValueFactory<>("factura"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcAnulada.setCellValueFactory(new PropertyValueFactory<>("anulada"));
        tbcArticulo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));

        tbcPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precio"));
        btnVerDocumento.setCellValueFactory(new PropertyValueFactory<>("button"));

    }

    private void iniciazarFiltro() {

        FilteredList<SalidaArticulo> filteredData = new FilteredList<>(tbEntradaArticulo.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltrar.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getNombre() != null && filtro.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getFactura() != null && filtro.getFactura().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<SalidaArticulo> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbEntradaArticulo.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbEntradaArticulo.setItems(sortedData);
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        listaArticulo.clear();

        fechaini = ClaseUtil.asDate(dpFechaDesde.getValue());
        fechafin = ClaseUtil.asDate(dpFecgaHasta.getValue());
        listaArticulo.addAll(ManejoArticulo.getInstancia().getListaSalidaArticuloDTO(getCodigoArticuulo(), fechaini, fechafin));

          lbCantidad.setText(""+tbEntradaArticulo.getItems().size());
    }

    @FXML
    private void btnReimprimirActionEvent(ActionEvent event) {

        try {

            if (listaArticulo.size() > 0) {

                util.ClaseUtil.exportarDAtos(tbEntradaArticulo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCodigoArticuulo() {
        return codigoArticuulo;
    }

    public void setCodigoArticuulo(int codigoArticuulo) {
        this.codigoArticuulo = codigoArticuulo;
    }
    
      public void llenarCampo() {

        listaArticulo.clear();
     
        listaArticulo.addAll(ManejoArticulo.getInstancia().getListaSalidaArticuloDTO(getCodigoArticuulo()));

        System.out.println("CodArticulo " + getCodigoArticuulo());
        lbCantidad.setText(""+tbEntradaArticulo.getItems().size());
    }

      
}
