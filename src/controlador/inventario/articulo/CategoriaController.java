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
import manejo.articulo.ManejoAlmacen;
import manejo.articulo.ManejoCategoria;
import modelo.Almacen;
import modelo.Categoria;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class CategoriaController implements Initializable {

    @FXML
    private TableView<Categoria> tbCategoria;
    @FXML
    private TableColumn<Categoria, String> tbcCodigo;
    @FXML
    private TableColumn<Categoria, String> tbcNombre;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;

    ObservableList<Categoria> lista = FXCollections.observableArrayList();

    String mensaje;
    Categoria categoria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabla();
        nuevo();
    }

    public void iniciarTabla() {

        tbCategoria.setItems(lista);
        lista.addAll(ManejoCategoria.getInstancia().getCategoria());

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

    }

    private void nuevo() {

        categoria = new Categoria();

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/RegistroCategoria.fxml"));

        ClaseUtil.crearStageModal(root);

        lista.clear();
        lista.addAll(ManejoCategoria.getInstancia().getCategoria());
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbCategoria.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar una Categoria");

        } else {

            categoria = tbCategoria.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/inventario/articulo/RegistroCategoria.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroCategoriaController registroCategoriaController = loader.getController();

            registroCategoriaController.setEditar(true);
            registroCategoriaController.setCategoria(categoria);
            registroCategoriaController.llenarCampo();

            ClaseUtil.getStageModal(root);

            lista.clear();
            lista.addAll(ManejoCategoria.getInstancia().getCategoria());

        }

    }

}
