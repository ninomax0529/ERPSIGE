/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.banco.registro;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import manejo.banco.ManejoBanco;
import modelo.Banco;
import modelo.CuentaBanco;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class BancoController implements Initializable {

    /**
     * @return the banco
     */
    public Banco getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @FXML
    private VBox vbVisorDeProgreso;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private TabPane tabPane;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private Label lbRutaArchivoHeder;
    @FXML
    private TableView<Banco> tbBanco;
    @FXML
    private TableColumn<Banco, String> tbcNombre;
    @FXML
    private TableColumn<Banco, String> tbcSucursal;
    @FXML
    private TableColumn<Banco, String> tbcDireccion;
    @FXML
    private TableColumn<Banco, String> tbcContacto;
    @FXML
    private TableColumn<Banco, String> tbcTelefonoContacto;
    @FXML
    private TableView<CuentaBanco> tbCuentaBanco;
    @FXML
    private TableColumn<CuentaBanco, String> tbcNombreBanco;
    @FXML
    private TableColumn<CuentaBanco, String> tbcTipoCuenta;
    @FXML
    private TableColumn<CuentaBanco, String> tbcNumeroCuenta;
    @FXML
    private TableColumn<CuentaBanco, String> tbcCuentaContable;
    @FXML
    private TableColumn<CuentaBanco, Double> tbcSaldo;

    ObservableList<CuentaBanco> listaCuentaBanco = FXCollections.observableArrayList();
    ObservableList<Banco> listaBanco = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnEditar;
    private Banco banco;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabla();
        iniciarTablaCuentaBanco();

    }

    public void iniciarTabla() {

        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcSucursal.setCellValueFactory(new PropertyValueFactory<>("sucursal"));

        tbcContacto.setCellValueFactory(new PropertyValueFactory<>("contacto"));
        tbcTelefonoContacto.setCellValueFactory(new PropertyValueFactory<>("telefonoContacto"));
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tbcSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));

        listaBanco.clear();
        listaBanco.addAll(ManejoBanco.getInstancia().getLista());
        tbBanco.setItems(listaBanco);

    }

    public void iniciarTablaCuentaBanco() {

        tbcNombreBanco.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        tbcNumeroCuenta.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));

        tbcCuentaContable.setCellValueFactory(new PropertyValueFactory<>("cuentaContable"));

        tbcNombreBanco.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getBanco() != null) {
                        property.setValue(cellData.getValue().getBanco().getNombre());
                    }
                    return property;
                });

        tbcTipoCuenta.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getTipoCuenta() != null) {
                        property.setValue(cellData.getValue().getTipoCuenta().getNombre());
                    }
                    return property;
                });

        listaCuentaBanco.clear();

        tbCuentaBanco.setItems(listaCuentaBanco);

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/banco/registro/FXMLRegistroBanco.fxml"));

        ClaseUtil.getStageModal(root);

        listaBanco.clear();
        listaCuentaBanco.clear();

        listaBanco.addAll(ManejoBanco.getInstancia().getLista());

    }

    @FXML
    private void tbBancoMouseClicked(MouseEvent event) {

        if (!(tbBanco.getSelectionModel().getFocusedIndex() == -1)) {

            Banco b = tbBanco.getSelectionModel().getSelectedItem();
            listaCuentaBanco.clear();

            listaCuentaBanco.addAll(ManejoBanco.getInstancia().getCuentaBanco(b));

            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(1);
            }

        }
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbBanco.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un banco ");

        } else {

            setBanco(tbBanco.getSelectionModel().getSelectedItem());

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/banco/registro/FXMLRegistroBanco.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            FXMLRegistroBancoController controller = loader.getController();

            controller.setEditar(true);
            controller.setBanco(getBanco());

            controller.llenarCampo();

            ClaseUtil.getStageModal(root);

            listaBanco.clear();
            listaCuentaBanco.clear();
            listaBanco.addAll(ManejoBanco.getInstancia().getLista());

        }

    }

}
