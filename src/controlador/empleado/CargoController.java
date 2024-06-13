/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.empleado;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import manejo.ejecutivoDeVenta.ManejoCargo;
import modelo.Cargo;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class CargoController implements Initializable {

    /**
     * @return the cargo
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private TabPane tabCliente;
    @FXML
    private TableView<Cargo> tbCargo;
    @FXML
    private TableColumn<Cargo, Integer> tbcCodigo;
    @FXML
    private TableColumn<Cargo, String> tbcNombre;
    @FXML
    private TableColumn<Cargo, Double> tbcSueldo;
    @FXML
    private TableColumn<Cargo, String> tbcDescripcion;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Label lbCantidad;
    private Cargo cargo;

    ObservableList<Cargo> listaCargo = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTabla();
    }

    public void iniciarTabla() {

        listaCargo.clear();

        listaCargo.addAll(ManejoCargo.getInstancia().getLista());

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tbcSueldo.setCellValueFactory(new PropertyValueFactory<>("sueldo"));

        tbCargo.setItems(listaCargo);

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/empleado/RegistroCargo.fxml"));

            ClaseUtil.crearStageModal(root);

            listaCargo.clear();
            listaCargo.addAll(ManejoCargo.getInstancia().getLista());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) {

        try {

            if (tbCargo.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN REGISTRO");

            } else {

                setCargo(tbCargo.getSelectionModel().getSelectedItem());

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/vista/empleado/RegistroCargo.fxml"));
                loader.load();

                Parent root = loader.getRoot();

                RegistroCargoController rgController = loader.getController();

                rgController.setEditar(true);
                rgController.setCargo(getCargo());
                rgController.llenarCampo();

                ClaseUtil.getStageModal(root);

                listaCargo.clear();
                listaCargo.addAll(ManejoCargo.getInstancia().getLista());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void tbClienteActionEvent(MouseEvent event) {
    }

}
