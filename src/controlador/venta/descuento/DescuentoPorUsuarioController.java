/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.descuento;

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
import manejo.descuento.ManejoDescuentoPorUsuario;
import modelo.DescuentoPorUsuario;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class DescuentoPorUsuarioController implements Initializable {

    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private TableView<DescuentoPorUsuario> tbDescuento;
    @FXML
    private TableColumn<DescuentoPorUsuario, Integer> tbcCodigo;
    @FXML
    private TableColumn<DescuentoPorUsuario, String> tbcNombre;
    @FXML
    private TableColumn<DescuentoPorUsuario, Double> tbcPorcientoMaximo;
    @FXML
    private TableColumn<DescuentoPorUsuario, Double> tbcPorcientoMinimo;
    @FXML
    private TableColumn<DescuentoPorUsuario, String> tbcHabilitado;

    ObservableList<DescuentoPorUsuario> lista = FXCollections.observableArrayList();

    DescuentoPorUsuario descuentoPorUsuario;

    public DescuentoPorUsuario getDescuentoPorUsuario() {
        return descuentoPorUsuario;
    }

    public void setDescuentoPorUsuario(DescuentoPorUsuario descuentoPorUsuario) {
        this.descuentoPorUsuario = descuentoPorUsuario;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTabla();
    }

    public void iniciarTabla() {

        tbDescuento.setItems(lista);
        lista.addAll(ManejoDescuentoPorUsuario.getInstancia().getLista());

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcPorcientoMinimo.setCellValueFactory(new PropertyValueFactory<>("minimo"));
        tbcPorcientoMaximo.setCellValueFactory(new PropertyValueFactory<>("maximo"));

        tbcNombre.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getUsuario() != null) {
                        property.setValue(cellData.getValue().getUsuario().getNombre());
                    }
                    return property;
                });

        tbcHabilitado.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getHabilitado() == true ? "SI" : "NO");
                    }
                    return property;
                });
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/descuento/RegistroDescuentoPorUsuario.fxml"));

        ClaseUtil.crearStageModal(root);
        lista.clear();
        lista.addAll(ManejoDescuentoPorUsuario.getInstancia().getLista());
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {
        
           if (tbDescuento.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un descuento");

        } else {

               setDescuentoPorUsuario(tbDescuento.getSelectionModel().getSelectedItem());

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/venta/descuento/RegistroDescuentoPorUsuario.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroDescuentoPorUsuarioController registroDesc=loader.getController();

            registroDesc.setEditar(true);
            registroDesc.setDescuentoPorUsuario(getDescuentoPorUsuario());
            registroDesc.llenarCampo();

            ClaseUtil.getStageModal(root);

            lista.clear();
            lista.addAll(ManejoDescuentoPorUsuario.getInstancia().getLista());

        }
    }

}
