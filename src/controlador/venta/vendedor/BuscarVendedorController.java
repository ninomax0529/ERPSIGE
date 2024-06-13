/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.vendedor;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import manejo.ejecutivoDeVenta.ManejoEjecutivoDeVenta;
import modelo.EjecutivoDeVenta;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class BuscarVendedorController implements Initializable {

    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TabPane tabCliente;
    @FXML
    private TableView<EjecutivoDeVenta> tbVendedor;
    @FXML
    private TableColumn<EjecutivoDeVenta, String> tbcCodigo;
    @FXML
    private TableColumn<EjecutivoDeVenta, String> tbcNombre;
    @FXML
    private TableColumn<EjecutivoDeVenta, String> tbcDireccion;
    @FXML
    private TableColumn<EjecutivoDeVenta, String> tbcTelefono;
    @FXML
    private TableColumn<EjecutivoDeVenta, String> tbcCelular;

    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Label lbCantidad;

    ObservableList<EjecutivoDeVenta> lista = FXCollections.observableArrayList();

    EjecutivoDeVenta ejecutivoDeVenta;

    public EjecutivoDeVenta getEjecutivoDeVenta() {
        return ejecutivoDeVenta;
    }

    public void setEjecutivoDeVenta(EjecutivoDeVenta ejecutivoDeVenta) {
        this.ejecutivoDeVenta = ejecutivoDeVenta;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabla();
        iniciazarFiltro();
        
    }
    
    
        
    private void iniciazarFiltro() {

        FilteredList<EjecutivoDeVenta> filteredData = new FilteredList<>(tbVendedor.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toUpperCase();

                if (filtro.getCodigo() != null && filtro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getNombre() != null && filtro.getNombre().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<EjecutivoDeVenta> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbVendedor.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbVendedor.setItems(sortedData);
    }

    public void iniciarTabla() {

        lista.clear();

        lista.addAll(ManejoEjecutivoDeVenta.getInstancia().getLista());

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tbcCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tbVendedor.setItems(lista);

    }

    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/vendedor/RegistroVendedor.fxml"));

        ClaseUtil.crearStageModal(root);

        lista.clear();
        lista.addAll(ManejoEjecutivoDeVenta.getInstancia().getLista());
    }

    private void btnEditarActionEvent(ActionEvent event) {

        try {

            if (tbVendedor.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN VENDEDOR");

            } else {

                setEjecutivoDeVenta(tbVendedor.getSelectionModel().getSelectedItem());

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/vista/venta/vendedor/RegistroVendedor.fxml"));
                loader.load();

                Parent root = loader.getRoot();

                RegistroVendedorController registroVendedorController = loader.getController();

                registroVendedorController.setEditar(true);
                registroVendedorController.setEjecutivoDeVenta(getEjecutivoDeVenta());
                registroVendedorController.llenarCampo();

                ClaseUtil.getStageModal(root);

                lista.clear();
                lista.addAll(ManejoEjecutivoDeVenta.getInstancia().getLista());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void tbClienteActionEvent(MouseEvent event) {

        setEjecutivoDeVenta(tbVendedor.getSelectionModel().getSelectedItem());
        Stage stage = (Stage) tbVendedor.getScene().getWindow();
        stage.close();

    }

}
