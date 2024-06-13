/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.direccion;

import controlador.configuracion.usuario.*;
import com.jfoenix.controls.JFXButton;

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
import manejo.direccion.ManejoDireccion;
import manejo.usuario.ManejoUsuario;
import modelo.Almacen;
import modelo.Direccion;
import modelo.Rol;
import modelo.Usuario;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class DireccionController implements Initializable {

    ObservableList<Direccion> lista = FXCollections.observableArrayList();
    ObservableList<Rol> listaRol = FXCollections.observableArrayList();
    String mensaje;
    Almacen almacen;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;

    Direccion direccion;
    boolean editar = false;
    @FXML
    private TableView<Direccion> tbDireccion;
    @FXML
    private TableColumn<Direccion, String> tbcRegion;
    @FXML
    private TableColumn<Direccion, String> tbcProvincia;
    @FXML
    private TableColumn<Direccion, String> tbcMunicipio;
    @FXML
    private TableColumn<Direccion, String> tbcCiudad;
    @FXML
    private TableColumn<Direccion, String> tbcSector;
    @FXML
    private TableColumn<Direccion, String> tbcDireccion;

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

            tbcCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
            tbcProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));
            tbcMunicipio.setCellValueFactory(new PropertyValueFactory<>("municipio"));
            tbcRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
            tbcSector.setCellValueFactory(new PropertyValueFactory<>("sector"));
            tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

            lista.addAll(ManejoDireccion.getInstancia().getLista());
            tbDireccion.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/configuracion/direccion/FXMLRegistroDireccion.fxml"));

        ClaseUtil.crearStageModal(root);
        lista.clear();
        lista.addAll(ManejoDireccion.getInstancia().getLista());
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbDireccion.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar una direccion");

        } else {

            direccion = tbDireccion.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/configuracion/direccion/FXMLRegistroDireccion.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            FXMLRegistroDireccionController registroDireccionController = loader.getController();

            registroDireccionController.setEditar(true);
            registroDireccionController.setDireccion(direccion);
            registroDireccionController.llenarCampo();

            ClaseUtil.getStageModal(root);

            lista.clear();
            lista.addAll(ManejoDireccion.getInstancia().getLista());

        }

    }

    @FXML
    private void tbUsuarioMouseClicked(MouseEvent event) {
    }

}
