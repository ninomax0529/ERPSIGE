/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.consulta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;

import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import manejo.contabilidadd.ConfiguracionCuentaContableDao;
import modelo.ConfiguracionCuentaContable;
import modelo.DetalleConfiguaracionCuentaContable;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLConsultaConfiguracionCuentaContableController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private TableColumn<ConfiguracionCuentaContable, Integer> tbcCodigo;
    @FXML
    private TableView<ConfiguracionCuentaContable> tbConfiguracionCuenta;
    @FXML
    private TableColumn<ConfiguracionCuentaContable, Date> tbcFecha;
    @FXML
    private TableColumn<ConfiguracionCuentaContable, String> tbcModulo;
    @FXML
    private TableColumn<ConfiguracionCuentaContable, String> tbcTipoConcepto;
    @FXML
    private TableColumn<ConfiguracionCuentaContable, String> tbcHabilitada;
    @FXML
    private JFXTextField txtCantidadConfiguracion;
    @FXML
    private JFXTextField txtFiltrar;
    @FXML
    private TableView<DetalleConfiguaracionCuentaContable> tblDetalleConfiguracionCuenta;
    @FXML
    private TableColumn<DetalleConfiguaracionCuentaContable, String> tbrCuenta;
    @FXML
    private TableColumn<DetalleConfiguaracionCuentaContable, String> tbrDescripcion;
    @FXML
    private TableColumn<DetalleConfiguaracionCuentaContable, String> tbcDebitar;
    @FXML
    private TableColumn<DetalleConfiguaracionCuentaContable, String> tbcAcreditar;

    @FXML
    private JFXTextField txtCantidadDetalleConfiguracion;

    ObservableList<DetalleConfiguaracionCuentaContable> listaDetalleConfiguracionCuenta = FXCollections.observableArrayList();
    ObservableList<ConfiguracionCuentaContable> listaConfiguracionCuenta = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnNuevo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTablaConfiguracion();
        inicializarTablaConfigCuenta();
        btnGuardar.setDisable(true);
    }

    private void inicializarTablaConfiguracion() {

        try {

             tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            tbcFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
            tbcHabilitada.setCellValueFactory(new PropertyValueFactory<>("Habilitada"));

            tbcModulo.setCellValueFactory(new PropertyValueFactory<>("NombreModulo"));
            tbcTipoConcepto.setCellValueFactory(new PropertyValueFactory<>("NombreTipoConcepto"));

            tbcHabilitada.setCellValueFactory(
                    cellData -> {

                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue() != null) {

                            property.setValue(cellData.getValue().getHabilitada() == true ? "SI" : "NO");
                        }
                        return property;
                    });

            listaConfiguracionCuenta.addAll(ConfiguracionCuentaContableDao.getInstancia().getConfiguracionCuentaContable());
            tbConfiguracionCuenta.setItems(listaConfiguracionCuenta);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void inicializarTablaConfigCuenta() {

        tbrCuenta.setCellValueFactory(new PropertyValueFactory<>("Cuenta"));
        tbrDescripcion.setCellValueFactory(new PropertyValueFactory<>("NombreCuenta"));
        tbcDebitar.setCellValueFactory(new PropertyValueFactory<>("Debitar"));
        tbcAcreditar.setCellValueFactory(new PropertyValueFactory<>("ACreditar"));

        tbcAcreditar.setCellValueFactory(
                cellData -> {

                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {

                        property.setValue(cellData.getValue().getAcreditar() == true ? "SI" : "NO");
                    }
                    return property;
                });

        tbcDebitar.setCellValueFactory(
                cellData -> {

                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {

                        property.setValue(cellData.getValue().getDebitar() == true ? "SI" : "NO");
                    }
                    return property;
                });

        tblDetalleConfiguracionCuenta.setItems(listaDetalleConfiguracionCuenta);

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        Optional<ButtonType> result = ClaseUtil.confirmarMensaje("Seguro que quiere desabilitar esta configuracion");

        if (result.get() == ButtonType.OK) {

            if (!(tbConfiguracionCuenta.getSelectionModel().getSelectedIndex() == -1)) {

                ConfiguracionCuentaContable configuracionCuentaContable = tbConfiguracionCuenta.getSelectionModel().getSelectedItem();

                configuracionCuentaContable.setHabilitada(false);

                ConfiguracionCuentaContableDao.getInstancia().salvar(configuracionCuentaContable);

                ClaseUtil.mensaje("La configuracion fue desabilitada exitosamente");
                listaConfiguracionCuenta.clear();
                listaConfiguracionCuenta.addAll(ConfiguracionCuentaContableDao.getInstancia().getConfiguracionCuentaContable());
                tbConfiguracionCuenta.setItems(listaConfiguracionCuenta);
            }
        }
    }

    @FXML
    private void tbDetalleEntActionEvent(KeyEvent event) {
    }

    @FXML
    private void tbConfiguracionCuentaMouseClicked(MouseEvent event) {

        listaDetalleConfiguracionCuenta.clear();

        if (!(tbConfiguracionCuenta.getSelectionModel().getSelectedIndex() == -1)) {

            ConfiguracionCuentaContable configuracionCuentaContable = tbConfiguracionCuenta.getSelectionModel().getSelectedItem();

            listaDetalleConfiguracionCuenta.addAll(ConfiguracionCuentaContableDao.getInstancia()
                    .getDetalleConfiguracionCuentaContable(configuracionCuentaContable.getCodigo()));

            tblDetalleConfiguracionCuenta.setItems(listaDetalleConfiguracionCuenta);

            if (configuracionCuentaContable.getHabilitada() == true) {

                btnGuardar.setDisable(false);
            }

        } else {

            btnGuardar.setDisable(true);
        }
    }

    @FXML
    private void btnNuevoActionevent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/contabilidad/registro/FXMLConfiguracionCuentaContable.fxml"));

        utiles.ClaseUtil.crearStageModal(root);
        listaDetalleConfiguracionCuenta.clear();

        listaConfiguracionCuenta.addAll(ConfiguracionCuentaContableDao.getInstancia().getConfiguracionCuentaContable());
        tbConfiguracionCuenta.setItems(listaConfiguracionCuenta);
    }

}
