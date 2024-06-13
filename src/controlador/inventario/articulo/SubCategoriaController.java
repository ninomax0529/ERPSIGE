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
import manejo.articulo.ManejoCategoria;
import manejo.articulo.ManejoSubCategoria;
import modelo.Categoria;
import modelo.SubCategoria;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class SubCategoriaController implements Initializable {

    @FXML
    private TableView<SubCategoria> tbSubCategoria;
    @FXML
    private TableColumn<SubCategoria, String> tbcCodigo;
    @FXML
    private TableColumn<SubCategoria, String> tbcNombre;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;

    ObservableList<SubCategoria> lista = FXCollections.observableArrayList();

    String mensaje;
    SubCategoria subCategoria;
    @FXML
    private TableColumn<SubCategoria, String> tbcCategoria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabla();
        nuevo();
    }

    public void iniciarTabla() {

        tbSubCategoria.setItems(lista);
        lista.addAll(ManejoSubCategoria.getInstancia().getSubCategoria());

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        
        
        tbcCategoria.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    
                    if (cellData.getValue().getCategoria()!= null) {
                        property.setValue(cellData.getValue().getCategoria().getNombre());
                    } else {
                        property.setValue("n/a");
                    }
                    return property;
                });
       

    }

    private void nuevo() {

        subCategoria = new SubCategoria();

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/RegistroSubCategoria.fxml"));

        ClaseUtil.crearStageModal(root);

        lista.clear();
        lista.addAll(ManejoSubCategoria.getInstancia().getSubCategoria());
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbSubCategoria.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar una Sub Categoria");

        } else {

            subCategoria = tbSubCategoria.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/inventario/articulo/RegistroSubCategoria.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroSubCategoriaController registroSubCategoriaController = loader.getController();

            registroSubCategoriaController.setEditar(true);
            registroSubCategoriaController.setSubCategoria(subCategoria);
            registroSubCategoriaController.llenarCampo();

            ClaseUtil.getStageModal(root);

            lista.clear();
            lista.addAll(ManejoSubCategoria.getInstancia().getSubCategoria());

        }

    }

}
