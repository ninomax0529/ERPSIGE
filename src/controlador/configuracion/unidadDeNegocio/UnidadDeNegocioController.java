/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.unidadDeNegocio;

import com.jfoenix.controls.JFXButton;
import controlador.configuracion.usuario.FXMLRegistroUsuarioController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.input.MouseEvent;
import manejo.unidadDeNegocio.ManejoUnidadDeNegocio;
import manejo.usuario.ManejoUsuario;
import modelo.UnidadDeNegocio;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class UnidadDeNegocioController implements Initializable {

    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private TableView<UnidadDeNegocio> tbUnidadDeNegocio;
    @FXML
    private TableColumn<UnidadDeNegocio, String> tbcNombre;
    @FXML
    private TableColumn<UnidadDeNegocio, String> tbcDescripcion;
    @FXML
    private TableColumn<UnidadDeNegocio, String> tbcTelefono;
    @FXML
    private TableColumn<UnidadDeNegocio, String> tbcEmail;
    @FXML
    private TableColumn<UnidadDeNegocio, String> tbcEmpresaOGrupo;

    UnidadDeNegocio unidad;

    ObservableList<UnidadDeNegocio> lista = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTabla();
    }

    private void inicializarTabla() {

        try {

            tbcNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
            tbcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            tbcEmpresaOGrupo.setCellValueFactory(new PropertyValueFactory<>("nombreEmpresaOGrupo"));

            lista.addAll(ManejoUnidadDeNegocio.getInstancia().getLista());
            tbUnidadDeNegocio.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/configuracion/unidadDeNegocio/RegistroUnidadDeNegocio.fxml"));

        ClaseUtil.crearStageModal(root);
        lista.clear();
        lista.addAll(ManejoUnidadDeNegocio.getInstancia().getLista());
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbUnidadDeNegocio.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar una unidad ");

        } else {

            unidad = tbUnidadDeNegocio.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/configuracion/unidadDeNegocio/RegistroUnidadDeNegocio.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroUnidadDeNegocioController registroUsuarioController = loader.getController();

            registroUsuarioController.setEditar(true);
            registroUsuarioController.setUnidadDeNegocio(unidad);
            registroUsuarioController.llenarCampo();

            ClaseUtil.getStageModal(root);

            lista.clear();
            lista.addAll(ManejoUnidadDeNegocio.getInstancia().getLista());

        }

    }

    @FXML
    private void tbUsuarioMouseClicked(MouseEvent event) {
    }

}
