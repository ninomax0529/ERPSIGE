/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.banco.registro;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controlador.contabilidad.registro.FXMLCatalogoConsController;

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
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.banco.ManejoBanco;
import manejo.banco.ManejoTipoCuentaBanco;
import manejo.cxp.ManejoMoneda;
import modelo.Banco;
import modelo.Catalogo;
import modelo.CuentaBanco;
import modelo.Moneda;
import modelo.TipoCuentaBanco;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximiliano
 */
public class FXMLRegistroBancoController implements Initializable {

    /**
     * @return the editar
     */
    public boolean isEditar() {
        return editar;
    }

    /**
     * @param editar the editar to set
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

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
    private JFXTextField txtNombre;
    @FXML
    private JFXComboBox<TipoCuentaBanco> cbTipouenta;
    @FXML
    private JFXTextField txtNumeroCuenta;
    @FXML
    private JFXButton btnGuardar;
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

    @FXML
    private JFXComboBox<Moneda> cbMoneda;
    @FXML
    private JFXTextField txtSaldo;

    private Banco banco;
    CuentaBanco cuentaBanco;
    private boolean editar = false;
    private boolean editarCuenta = false;

    ObservableList<CuentaBanco> listaCuentaBanco = FXCollections.observableArrayList();
    ObservableList<Banco> listaBanco = FXCollections.observableArrayList();
    ObservableList<TipoCuentaBanco> listaTipoCuenta = FXCollections.observableArrayList();
    ObservableList<Moneda> listaMoneda = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXTextField txtCuenta;
    @FXML
    private JFXButton btnCatalogo;
    Catalogo catalogo;
    @FXML
    private JFXTextField txtSucursal;
    @FXML
    private JFXTextField txtContacto;
    @FXML
    private JFXTextField txtTelefonoContacto;
    @FXML
    private JFXTextField txtDireccion;

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarCombox();
        nuevo();
        iniciarTablaCuentaBanco();

    }

    public void iniciarTablaCuentaBanco() {

        tbcNombreBanco.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        tbcNumeroCuenta.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));

        tbcCuentaContable.setCellValueFactory(new PropertyValueFactory<>("cuentaContable"));
        tbcSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));

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

    private void inicializarCombox() {

        listaTipoCuenta.addAll(ManejoTipoCuentaBanco.getInstancia().getLista());
        cbTipouenta.setItems(listaTipoCuenta);
        listaMoneda.addAll(ManejoMoneda.getInstancia().getListaMoneda());
        cbMoneda.setItems(listaMoneda);

        cbTipouenta.setConverter(new StringConverter<TipoCuentaBanco>() {

            @Override
            public String toString(TipoCuentaBanco u) {
                return u.getNombre();
            }

            @Override
            public TipoCuentaBanco fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbMoneda.setConverter(new StringConverter<Moneda>() {

            @Override
            public String toString(Moneda u) {
                return u.getNombre();
            }

            @Override
            public Moneda fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {

        if (editarCuenta == false) {
            cuentaBanco = new CuentaBanco();
        }

        if (!(getCatalogo() == null)) {
            cuentaBanco.setCatalogo(getCatalogo());
            cuentaBanco.setCuentaContable(getCatalogo().getCuenta());
        }

        cuentaBanco.setSaldo(Double.parseDouble(txtSaldo.getText().isEmpty() ? "0.00" : txtSaldo.getText()));
        cuentaBanco.setDebito(0.00);
        cuentaBanco.setCredito(0.00);
        cuentaBanco.setMoneda(cbMoneda.getSelectionModel().getSelectedItem());
        cuentaBanco.setNumeroCuenta(txtNumeroCuenta.getText());
        cuentaBanco.setTipoCuenta(cbTipouenta.getSelectionModel().getSelectedItem());

        listaCuentaBanco.add(cuentaBanco);
        txtNumeroCuenta.clear();
        txtCuenta.clear();
        cbMoneda.getSelectionModel().clearSelection();
        cbTipouenta.getSelectionModel().clearSelection();

    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        if (tbCuentaBanco.getSelectionModel().getFocusedIndex() != -1) {

            cuentaBanco = tbCuentaBanco.getSelectionModel().getSelectedItem();
            listaCuentaBanco.remove(tbCuentaBanco.getSelectionModel().getSelectedIndex());
            cbMoneda.getSelectionModel().select(cuentaBanco.getMoneda());
            cbTipouenta.getSelectionModel().select(cuentaBanco.getTipoCuenta());
            txtNumeroCuenta.setText(cuentaBanco.getNumeroCuenta());
            if (!(cuentaBanco.getCatalogo() == null)) {
                setCatalogo(cuentaBanco.getCatalogo());
                txtCuenta.setText(getCatalogo().getCuenta() + " - " + getCatalogo().getDescripcion());
            }
            tbCuentaBanco.refresh();

//            listaCuentaBanco.remove(tbCuentaBanco.getSelectionModel().getFocusedIndex());
//            tbCuentaBanco.refresh();
        }
    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (editar == false) {
                nuevo();
            }

            if (txtNombre.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar un nombre");
                txtNombre.requestFocus();
                return;
            }

            if (listaCuentaBanco.size() <= 0) {
                ClaseUtil.mensaje(" Tiene que agregar una cuenta ");
                return;
            }

            getBanco().setNombre(txtNombre.getText());
            getBanco().setSucursal(txtSucursal.getText().isEmpty() ? "na" : txtSucursal.getText());
            getBanco().setContacto(txtContacto.getText().isEmpty() ? "na" : txtContacto.getText());
            getBanco().setTelefonoContacto(txtTelefonoContacto.getText().isEmpty() ? "na" : txtTelefonoContacto.getText());
            getBanco().setDireccion(txtDireccion.getText().isEmpty() ? " na " : txtDireccion.getText());
            getBanco().setEstado(true);
            getBanco().setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            listaCuentaBanco.forEach((cb) -> {
                cb.setBanco(getBanco());
            });

            getBanco().setCuentaBancoCollection(listaCuentaBanco);

            if (isEditar()) {

                ManejoBanco.getInstancia().actualizar(getBanco());

            } else {

                ManejoBanco.getInstancia().salvar(getBanco());
            }

            nuevo();
            limpiarCampo();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnCatalogoActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/contabilidad/consulta/FXMLCatalogoCons.fxml"));

        loader.load();

        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        System.out.println("ESPERANDOOO!!!");

        FXMLCatalogoConsController catalogoController = loader.getController();

        if (!(catalogoController.getCatalogo() == null)) {

            setCatalogo(catalogoController.getCatalogo());
            txtCuenta.setText(getCatalogo().getCuenta() + " - " + getCatalogo().getDescripcion());
        } else {
            System.out.println("catalogo : " + getCatalogo());
        }

    }

    private void nuevo() {

        setBanco(new Banco());
        setEditar(false);

    }

    private void limpiarCampo() {

        listaCuentaBanco.clear();

        btnGuardar.setText("Guardar");
        txtNombre.clear();
        txtDireccion.clear();
        txtSucursal.clear();
        txtContacto.clear();
        txtTelefonoContacto.clear();

    }

    public void llenarCampo() {

        txtNombre.setText(getBanco().getNombre());
        txtDireccion.setText(getBanco().getDireccion());
        txtSucursal.setText(getBanco().getSucursal());
        txtContacto.setText(getBanco().getContacto());
        txtTelefonoContacto.setText(getBanco().getTelefonoContacto());
        listaCuentaBanco.clear();
        listaCuentaBanco.addAll(ManejoBanco.getInstancia().getCuentaBanco(getBanco()));

    }

    @FXML
    private void tbCuentaBancoMouseClick(MouseEvent event) {

        if (!(tbCuentaBanco.getSelectionModel().getSelectedIndex() == -1)) {

            editarCuenta = true;
//            cuentaBanco = tbCuentaBanco.getSelectionModel().getSelectedItem();
//            listaCuentaBanco.remove(tbCuentaBanco.getSelectionModel().getSelectedIndex());
//            cbMoneda.getSelectionModel().select(cuentaBanco.getMoneda());
//            cbTipouenta.getSelectionModel().select(cuentaBanco.getTipoCuenta());
//            txtNumeroCuenta.setText(cuentaBanco.getNumeroCuenta());
//            if (!(cuentaBanco.getCatalogo() == null)) {
//                setCatalogo(cuentaBanco.getCatalogo());
//                txtCuenta.setText(getCatalogo().getCuenta() + " - " + getCatalogo().getDescripcion());
//            }
//            tbCuentaBanco.refresh();

        }
    }
}
