/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.banco.movimiento;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import manejo.banco.ManejoBanco;
import modelo.Catalogo;
import modelo.CuentaBanco;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ConsultaCuentaBancoController implements Initializable {

    /**
     * @return the cuenta
     */
    public CuentaBanco getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(CuentaBanco cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the catalogo
     */
    public Catalogo getCatalogo() {
        return catalogo;
    }

    /**
     * @param catalogo the catalogo to set
     */
    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

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
    private TableColumn<CuentaBanco, String> tbcMoneda;

    ObservableList<CuentaBanco> listaCuentaBanco = FXCollections.observableArrayList();

    private Catalogo catalogo;
     private CuentaBanco cuenta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTabla();
    }

    public void iniciarTabla() {

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

        tbcMoneda.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getMoneda() != null) {
                        property.setValue(cellData.getValue().getMoneda().getNombre());
                    }
                    return property;
                });

        listaCuentaBanco.addAll(ManejoBanco.getInstancia().getCuentaBanco());
        tbCuentaBanco.setItems(listaCuentaBanco);

    }

    @FXML
    private void cuentaBancoMouseClicked(MouseEvent event) {

        try {

            if (!(tbCuentaBanco.getSelectionModel().getSelectedIndex() == -1)) {
                CuentaBanco cuenta = tbCuentaBanco.getSelectionModel().getSelectedItem();
                setCuenta(cuenta);
                System.out.println(cuenta.getBanco().getNombre());
                setCatalogo(cuenta.getCatalogo());
                Stage stage = (Stage) tbCuentaBanco.getScene().getWindow();
                stage.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
