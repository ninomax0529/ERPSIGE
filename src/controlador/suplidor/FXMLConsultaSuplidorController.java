/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.suplidor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controlador.venta.cliente.RegistroClienteController;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import manejo.cliente.ManejoCliente;
import manejo.cxp.ManejoFacturaSuplidor;
import manejo.suplidor.SuplidorDao;
import modelo.Suplidor;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class FXMLConsultaSuplidorController implements Initializable {

    @FXML
    private TableView<Suplidor> tbSuplidores;
    @FXML
    private TableColumn<Suplidor, Integer> tbcCodigo;
    @FXML
    private TableColumn<Suplidor, String> tbcDescripcion;
    @FXML
    private TableColumn<Suplidor, String> tbcRNC;
    @FXML
    private TableColumn<Suplidor, String> tbcTelefono;
    @FXML
    private TableColumn<Suplidor, String> tbcContacto;
    @FXML
    private JFXTextField txtFiltro;
    ObservableList<Suplidor> listaSuplidores = FXCollections.observableArrayList();

    private Suplidor suplidor;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnExportar;

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

        FilteredList<Suplidor> filteredData = new FilteredList<>(tbSuplidores.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(suplidorFiltro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (suplidorFiltro.getDescripcion() != null && suplidorFiltro.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (suplidorFiltro.getRnc() != null && suplidorFiltro.getRnc().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (suplidorFiltro.getCodigo() != null && suplidorFiltro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Suplidor> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbSuplidores.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbSuplidores.setItems(sortedData);
    }

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }

    @FXML
    private void tbSuplidorActionEvent(MouseEvent event) {

        if (!(tbSuplidores.getSelectionModel().getSelectedIndex() == -1)) {

            suplidor = tbSuplidores.getSelectionModel().getSelectedItem();
            System.out.println(suplidor.getDescripcion());
            setSuplidor(suplidor);
//            Stage stage = (Stage) tbSuplidores.getScene().getWindow();
//            stage.close();
        }
    }

    private void inicializarTabla() {
        try {

            tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
            tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
            tbcRNC.setCellValueFactory(new PropertyValueFactory<>("Rnc"));
            tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
            tbcContacto.setCellValueFactory(new PropertyValueFactory<>("Contacto"));

            listaSuplidores.addAll(SuplidorDao.getInstancia().getSuplidores());
            tbSuplidores.setItems(listaSuplidores);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionevent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/suplidor/FXMLRegistroSuplidor.fxml"));

        utiles.ClaseUtil.crearStageModal(root);

        listaSuplidores.clear();
        listaSuplidores.addAll(SuplidorDao.getInstancia().getSuplidores());
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) {

        try {

            if (tbSuplidores.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN SUPLIDOR ");

            } else {

                setSuplidor(tbSuplidores.getSelectionModel().getSelectedItem());

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/vista/suplidor/FXMLRegistroSuplidor.fxml"));
                loader.load();

                Parent root = loader.getRoot();

                FXMLRegistroSuplidorController controller = loader.getController();

                controller.setEditar(true);
                controller.setSuplidor(getSuplidor());
                controller.llenarCampo();

                ClaseUtil.getStageModal(root);

                listaSuplidores.clear();
                listaSuplidores.addAll(SuplidorDao.getInstancia().getSuplidores());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) {

        try {

            util.ClaseUtil.exportarDAtos(tbSuplidores);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
