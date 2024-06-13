/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.usuario;

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
import javafx.scene.input.MouseEvent;
import manejo.ManejoRol;
import modelo.Almacen;
import modelo.Rol;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RolesController implements Initializable {

    @FXML
    private TableView<Rol> tbRol;
    @FXML
    private TableColumn<Rol, String> tbcNombre;
    @FXML
    private TableColumn<Rol, String> tbcDescripcion;

    ObservableList<Rol> listaRol = FXCollections.observableArrayList();
    String mensaje;
    Almacen almacen;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;

    Rol rol = null;
    boolean editar = false;

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

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
            tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

            listaRol.addAll(ManejoRol.getInstancia().getRol());
            tbRol.setItems(listaRol);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/configuracion/usuario/RegistroRoles.fxml"));

        ClaseUtil.crearStageModal(root);
        listaRol.clear();
        listaRol.addAll(ManejoRol.getInstancia().getRol());
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbRol.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un Rol");

        } else {

            rol = tbRol.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/configuracion/usuario/RegistroRoles.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroRolesController registroRolesController = loader.getController();

            registroRolesController.setEditar(true);
            registroRolesController.setRol(rol);
            registroRolesController.llenarCampo();

            ClaseUtil.getStageModal(root);

            listaRol.clear();
            listaRol.addAll(ManejoRol.getInstancia().getRol());

        }

    }

    @FXML
    private void tbUsuarioMouseClicked(MouseEvent event) {
    }

}
