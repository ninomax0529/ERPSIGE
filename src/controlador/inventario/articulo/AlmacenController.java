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
import modelo.Almacen;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class AlmacenController implements Initializable {

    @FXML
    private TableView<Almacen> tbAlmacen;
    @FXML
    private TableColumn<Almacen, String> tbcCodigo;
    @FXML
    private TableColumn<Almacen, String> tbcNombre;
    @FXML
    private TableColumn<Almacen, String> tbcUbicacion;

    ObservableList<Almacen> listaAlmacen = FXCollections.observableArrayList();

    String mensaje;
    Almacen almacen;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabla();
        nuevo();
    }

    public void iniciarTabla() {

        tbAlmacen.setItems(listaAlmacen);
        listaAlmacen.addAll(ManejoAlmacen.getInstancia().getAlmacenPorUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()));

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcUbicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));

    }

    private void nuevo() {

        almacen = new Almacen();

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/RegistroAlmacen.fxml"));

        ClaseUtil.crearStageModal(root);

        listaAlmacen.clear();
        listaAlmacen.addAll(ManejoAlmacen.getInstancia().getAlmacenPorUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()));
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbAlmacen.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un Almacen");

        } else {

            almacen = tbAlmacen.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/inventario/articulo/RegistroAlmacen.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroAlmacenController registroAlmacenController = loader.getController();

            registroAlmacenController.setEditar(true);
            registroAlmacenController.setAlmacen(almacen);
            registroAlmacenController.llenarCampo();

            ClaseUtil.getStageModal(root);
            listaAlmacen.clear();
            listaAlmacen.addAll(ManejoAlmacen.getInstancia().getAlmacenPorUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()));

        }

    }

}
