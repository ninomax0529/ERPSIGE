/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import manejo.unidad.ManejoUnidad;
import modelo.Unidad;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class UnidadControlle implements Initializable {

    @FXML
    private TableView<Unidad> tbUnidad;
    @FXML
    private TableColumn<Unidad, String> tbcCodigo;
    @FXML
    private TableColumn<Unidad, String> tbcNombre;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private TableColumn<Unidad, String> tbcabreviatura;

    ObservableList<Unidad> lista = FXCollections.observableArrayList();

    String mensaje;
    Unidad unidad;

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabla();

    }

    public void iniciarTabla() {

        tbUnidad.setItems(lista);
        lista.addAll(ManejoUnidad.getInstancia().getListaUnidad());

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tbcabreviatura.setCellValueFactory(new PropertyValueFactory<>("abreviatura"));

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/RegistroUnidad.fxml"));

        ClaseUtil.crearStageModal(root);

        lista.clear();
         lista.addAll(ManejoUnidad.getInstancia().getListaUnidad());
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbUnidad.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar una Unidad");

        } else {

            unidad = tbUnidad.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/inventario/articulo/RegistroUnidad.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroUnidadController registroUnidadController = loader.getController();

            registroUnidadController.setEditar(true);
            registroUnidadController.setUnidad(unidad);
            registroUnidadController.llenarCampo();

            ClaseUtil.getStageModal(root);

            lista.clear();
            lista.addAll(ManejoUnidad.getInstancia().getLista());

        }

    }

}
