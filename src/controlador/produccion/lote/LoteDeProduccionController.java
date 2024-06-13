/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion.lote;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import manejo.produccion.ManejoRegistroLote;
import modelo.RegistroLote;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class LoteDeProduccionController implements Initializable {

    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private TableView<RegistroLote> tbLoteDeProduccion;
    @FXML
    private TableColumn<RegistroLote, String> tbcLote;
    @FXML
    private TableColumn<RegistroLote, String> tbcProduto;
    @FXML
    private TableColumn<RegistroLote, String> tbccodProducto;
    @FXML
    private TableColumn<RegistroLote, Date> tbcFechaProduccion;
    @FXML
    private TableColumn<RegistroLote, String> tbcProduccion;
    @FXML
    private TableColumn<RegistroLote, String> tbcUnidad;

    ObservableList<RegistroLote> lista = FXCollections.observableArrayList();
    RegistroLote registroLote;

    public RegistroLote getRegistroLote() {
        return registroLote;
    }

    public void setRegistroLote(RegistroLote registroLote) {
        this.registroLote = registroLote;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabla();
    }

    public void iniciarTabla() {

        tbLoteDeProduccion.setItems(lista);
        lista.addAll(ManejoRegistroLote.getInstancia().getLista());

        tbcFechaProduccion.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcLote.setCellValueFactory(new PropertyValueFactory<>("numeroDeLote"));
        tbcProduto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        tbcProduccion.setCellValueFactory(new PropertyValueFactory<>("produccion"));
        tbccodProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));

        tbcProduto.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getProducto().getDescripcion());
                    }
                    return property;
                });
        tbccodProducto.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getProducto().getCodigoBarra());
                    }
                    return property;
                });

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/produccion/lote/RegistroLoteDeProduccion.fxml"));

        ClaseUtil.crearStageModal(root);

        lista.clear();
        lista.addAll(ManejoRegistroLote.getInstancia().getLista());
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbLoteDeProduccion.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un lote");

        } else {

            setRegistroLote(tbLoteDeProduccion.getSelectionModel().getSelectedItem());

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/produccion/lote/RegistroLoteDeProduccion.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroLoteDeProduccionController registroArticuloController = loader.getController();

            registroArticuloController.setEditar(true);
            registroArticuloController.setRegistroLote(getRegistroLote());

            registroArticuloController.llenarCampo();

            ClaseUtil.getStageModal(root);

            lista.clear();
            lista.addAll(ManejoRegistroLote.getInstancia().getLista());

        }

    }

    @FXML
    private void tbLoteDeProduccionMouseClicked(MouseEvent event) {
//
//        if (!(tbLoteDeProduccion.getSelectionModel().getSelectedIndex() == -1)) {
//
//            setRegistroLote(tbLoteDeProduccion.getSelectionModel().getSelectedItem());
//
//            if (event.getClickCount() == 1) {
//
//                Stage st = (Stage) (tbLoteDeProduccion.getScene().getWindow());
//                st.close();
//            }
//        }
    }

}
