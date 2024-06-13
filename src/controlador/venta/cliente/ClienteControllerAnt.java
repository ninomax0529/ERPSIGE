/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.cliente;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import manejo.cliente.ManejoCliente;
import modelo.Cliente;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ClienteControllerAnt implements Initializable {

    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<Cliente> tbCliente;
    @FXML
    private TableColumn<Cliente, String> tbcCodigo;
    @FXML
    private TableColumn<Cliente, String> tbcNombre;
    @FXML
    private TableColumn<Cliente, String> tbcDireccion;
    @FXML
    private TableColumn<Cliente, String> tbcTelefono;
    @FXML
    private TableColumn<Cliente, String> tbcCelular;
    @FXML
    private TableColumn<Cliente, String> tbcEstado;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Label lbCantidad;

    Cliente cliente;
    @FXML
    private JFXButton btnExportar;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabla();
        iniciazarFiltro();
    }

    public void iniciarTabla() {

        listaCliente.clear();

        listaCliente.addAll(ManejoCliente.getInstancia().getCliente());

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tbcCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tbCliente.setItems(listaCliente);

    }

    private void iniciazarFiltro() {

        FilteredList<Cliente> filteredData = new FilteredList<>(tbCliente.getItems(), p -> true);
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
        SortedList<Cliente> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbCliente.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbCliente.setItems(sortedData);
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/cliente/RegistroCliente.fxml"));

        ClaseUtil.crearStageModal(root);

        listaCliente.clear();
        listaCliente.addAll(ManejoCliente.getInstancia().getCliente());

    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) {

        try {

            if (tbCliente.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN CLIENTE");

            } else {

                setCliente(tbCliente.getSelectionModel().getSelectedItem());

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/vista/venta/cliente/RegistroCliente.fxml"));
                loader.load();

                Parent root = loader.getRoot();

                RegistroClienteController registroClienteController = loader.getController();

                registroClienteController.setEditar(true);
                registroClienteController.setCliente(getCliente());
                registroClienteController.llenarCampo();

                ClaseUtil.getStageModal(root);

                listaCliente.clear();
                listaCliente.addAll(ManejoCliente.getInstancia().getCliente());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) {
        try {

            util.ClaseUtil.exportarDAtos(tbCliente);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
