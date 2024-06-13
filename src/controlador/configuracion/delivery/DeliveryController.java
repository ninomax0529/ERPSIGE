/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.delivery;

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
import manejo.delivery.ManejoDelivery;
import modelo.Delivery;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class DeliveryController implements Initializable {

    /**
     * @return the delivery
     */
    public Delivery getDelivery() {
        return delivery;
    }

    /**
     * @param delivery the delivery to set
     */
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private TableView<Delivery> tbDelivery;
    @FXML
    private TableColumn<Delivery, String> tbcNombre;
    @FXML
    private TableColumn<Delivery, String> tbcApelldido;
    @FXML
    private TableColumn<Delivery, String> tbcCelular;
    @FXML
    private TableColumn<Delivery, String> tbcDireccion;
    @FXML
    private TableColumn<Delivery, String> tbcEstado;

    ObservableList<Delivery> lista = FXCollections.observableArrayList();
    private Delivery delivery;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
    }

    private void inicializarTabla() {

        try {

            tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tbcApelldido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
            tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            tbcCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
            tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

            lista.addAll(ManejoDelivery.getInstancia().getLista());
            tbDelivery.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/configuracion/direccion/delivery/FXMLRegistroDelivery.fxml"));

        ClaseUtil.crearStageModal(root);
        lista.clear();
        lista.addAll(ManejoDelivery.getInstancia().getLista());

    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbDelivery.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un delivery");

        } else {

            setDelivery(tbDelivery.getSelectionModel().getSelectedItem());

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/configuracion/delivery/FXMLRegistroDelivery.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            FXMLRegistroDeliveryController registroDeliveryController = loader.getController();

            registroDeliveryController.setEditar(true);
            registroDeliveryController.setDelivery(delivery);
            registroDeliveryController.llenarCampo();

            ClaseUtil.getStageModal(root);

            lista.clear();
            lista.addAll(ManejoDelivery.getInstancia().getLista());

        }

    }

    @FXML
    private void tbUsuarioMouseClicked(MouseEvent event) {
    }

}
