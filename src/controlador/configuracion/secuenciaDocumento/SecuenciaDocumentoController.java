/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.secuenciaDocumento;

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
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.SecuenciaDocumento;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class SecuenciaDocumentoController implements Initializable {

    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private TableView<SecuenciaDocumento> tbSecuenciaDocumento;
    @FXML
    private TableColumn<SecuenciaDocumento, String> tbcUnidadDeNegocio;
    @FXML
    private TableColumn<SecuenciaDocumento, String> tbcTipodocumento;
    @FXML
    private TableColumn<SecuenciaDocumento, Integer> tbcNumero;

    ObservableList<SecuenciaDocumento> lista = FXCollections.observableArrayList();

    SecuenciaDocumento secuenciaDocumento=null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTabla();
    }

    private void inicializarTabla() {

        try {

            tbcUnidadDeNegocio.setCellValueFactory(new PropertyValueFactory<>("unidadDeNegocio"));
            tbcTipodocumento.setCellValueFactory(new PropertyValueFactory<>("tipoDocumento"));
            tbcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));

            tbcTipodocumento.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getTipoDocumento() != null) {
                            property.setValue(cellData.getValue().getTipoDocumento().getNombre());
                        }
                        return property;
                    });

            tbcUnidadDeNegocio.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getUnidadDeNegocio() != null) {
                            property.setValue(cellData.getValue().getUnidadDeNegocio().getNombre());
                        }
                        return property;
                    });

            lista.addAll(ManejoSecuenciaDocumento.getInstancia().getLista());
            tbSecuenciaDocumento.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/configuracion/secuenciaDocumento/FXMLRegistroSecuenciaDocumento.fxml"));

        ClaseUtil.crearStageModal(root);
        lista.clear();
        lista.addAll(ManejoSecuenciaDocumento.getInstancia().getLista());

    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbSecuenciaDocumento.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar una secuencia");

        } else {

            secuenciaDocumento = tbSecuenciaDocumento.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/configuracion/secuenciaDocumento/FXMLRegistroSecuenciaDocumento.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            FXMLRegistroSecuenciaDocumentoController registroSecuenciaDocumentoController = loader.getController();

            registroSecuenciaDocumentoController.setEditar(true);
            registroSecuenciaDocumentoController.setSecuenciaDocumento(secuenciaDocumento);
            registroSecuenciaDocumentoController.llenarCampo();

            ClaseUtil.getStageModal(root);

            lista.clear();
            lista.addAll(ManejoSecuenciaDocumento.getInstancia().getLista());

        }

    }

    @FXML
    private void tbUsuarioMouseClicked(MouseEvent event) {
    }

}
