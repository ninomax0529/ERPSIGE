/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.empresa;

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
import manejo.empresa.ManejoEmpresaOGrupo;
import modelo.EmpresaOGrupo;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class EmpresaOGrupoController implements Initializable {

    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private TableView<EmpresaOGrupo> tbUnidadDeNegocio;
    @FXML
    private TableColumn<EmpresaOGrupo, String> tbcNombre;
    @FXML
    private TableColumn<EmpresaOGrupo, String> tbcDescripcion;
    @FXML
    private TableColumn<EmpresaOGrupo, String> tbcTelefono;
    @FXML
    private TableColumn<EmpresaOGrupo, String> tbcEmail;
    @FXML
    private TableColumn<EmpresaOGrupo, String> tbcRnc;

    EmpresaOGrupo empresa;

    ObservableList<EmpresaOGrupo> lista = FXCollections.observableArrayList();

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
            tbcRnc.setCellValueFactory(new PropertyValueFactory<>("rnc"));

            lista.addAll(ManejoEmpresaOGrupo.getInstancia().getLista());
            tbUnidadDeNegocio.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/configuracion/empresa/RegistroEmpresaOGrupo.fxml"));

        ClaseUtil.crearStageModal(root);
        lista.clear();
        lista.addAll(ManejoEmpresaOGrupo.getInstancia().getLista());
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbUnidadDeNegocio.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar una empresa ");

        } else {

            empresa = tbUnidadDeNegocio.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/configuracion/empresa/RegistroEmpresaOGrupo.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroEmpresaOGrupoController controller = loader.getController();

            controller.setEditar(true);
            controller.setEmpresaOGrupo(empresa);
            controller.llenarCampo();

            ClaseUtil.getStageModal(root);

            lista.clear();
            lista.addAll(ManejoEmpresaOGrupo.getInstancia().getLista());

        }

    }

    @FXML
    private void tbUsuarioMouseClicked(MouseEvent event) {
    }

}
