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
import javafx.stage.Stage;
import manejo.usuario.ManejoUsuario;
import modelo.Almacen;
import modelo.Rol;
import modelo.Usuario;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class UsuarioController implements Initializable {

    @FXML
    private TableView<Usuario> tbUsuario;
    @FXML
    private TableColumn<Usuario, String> tbcNombre;
    @FXML
    private TableColumn<Usuario, String> tbcUsuario;
    @FXML
    private TableColumn<Usuario, String> tbcRol;

    @FXML
    private TableColumn<Usuario, String> tbcUnidadDeNegocio;

    ObservableList<Usuario> lista = FXCollections.observableArrayList();
    ObservableList<Rol> listaRol = FXCollections.observableArrayList();
    String mensaje;
    Almacen almacen;
 
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;

    Usuario usuario = null;
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
            tbcUsuario.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
            tbcRol.setCellValueFactory(new PropertyValueFactory<>("Rol"));

            tbcRol.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getRol() != null) {
                            property.setValue(cellData.getValue().getRol().getNombre());
                        }
                        return property;
                    });
            
              tbcUnidadDeNegocio.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getRol() != null) {
                            property.setValue(cellData.getValue().getUnidadDeNegocio().getNombre());
                        }
                        return property;
                    });

            lista.addAll(ManejoUsuario.getInstancia().getLista());
            tbUsuario.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/configuracion/usuario/FXMLRegistroUsuario.fxml"));

        ClaseUtil.crearStageModal(root);
        lista.clear();
        lista.addAll(ManejoUsuario.getInstancia().getLista());
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbUsuario.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un usuario");

        } else {

            usuario = tbUsuario.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/configuracion/usuario/FXMLRegistroUsuario.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            FXMLRegistroUsuarioController registroUsuarioController = loader.getController();

            registroUsuarioController.setEditar(true);
            registroUsuarioController.setUsuario(usuario);
            registroUsuarioController.llenarCampo();

            ClaseUtil.getStageModal(root);

            lista.clear();
            lista.addAll(ManejoUsuario.getInstancia().getLista());

        }

    }

    @FXML
    private void tbUsuarioMouseClicked(MouseEvent event) {
        
        if(event.getClickCount()==2){
            setUsuario(tbUsuario.getSelectionModel().getSelectedItem());
            
            Stage stage=(Stage)tbUsuario.getScene().getWindow();
            
            stage.close();
        }
    }

}
