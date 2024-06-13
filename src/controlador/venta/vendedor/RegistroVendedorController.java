/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.vendedor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.ejecutivoDeVenta.ManejoCargo;
import manejo.ejecutivoDeVenta.ManejoEjecutivoDeVenta;
import manejo.ejecutivoDeVenta.ManejoTipoVendedor;
import modelo.Cargo;
import modelo.EjecutivoDeVenta;
import modelo.TipoVendedor;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroVendedorController implements Initializable {

    @FXML
    private JFXComboBox<Cargo> cbCargo;
    @FXML
    private JFXComboBox<TipoVendedor> cbTipoVendedor;
    @FXML
    private JFXTextField txtComisionVenta;
    @FXML
    private JFXTextField txtComisionCobros;
    @FXML
    private JFXCheckBox chSuplidorDeCliente;
    @FXML
    private JFXTextField txtComisionPorCompatibilidad;

    /**
     * @return the ejecutivoDeVenta
     */
    public EjecutivoDeVenta getEjecutivoDeVenta() {
        return ejecutivoDeVenta;
    }

    /**
     * @param ejecutivoDeVenta the ejecutivoDeVenta to set
     */
    public void setEjecutivoDeVenta(EjecutivoDeVenta ejecutivoDeVenta) {
        this.ejecutivoDeVenta = ejecutivoDeVenta;
    }

    public Boolean getEditar() {
        return editar;
    }

    public void setEditar(Boolean editar) {
        this.editar = editar;
    }

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtApellido;
    @FXML
    private JFXTextField txtRnc;
    @FXML
    private JFXTextField txtdireccion;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private JFXTextField txtCelular;

    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtCorreoElectronico;
    @FXML
    private TextArea txtObservacion;

    private EjecutivoDeVenta ejecutivoDeVenta;
    Boolean editar = false;

    ObservableList<Cargo> listaCargo = FXCollections.observableArrayList();
    ObservableList<TipoVendedor> listaTipoVendedor = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nuevo();
        inicializarCombox();
    }

    private void inicializarCombox() {

        listaCargo.clear();
        listaCargo.addAll(ManejoCargo.getInstancia().getLista());
        listaTipoVendedor.addAll(ManejoTipoVendedor.getInstancia().getLista());

        cbTipoVendedor.setConverter(new StringConverter<TipoVendedor>() {

            @Override
            public String toString(TipoVendedor estadoCliente) {
                return String.valueOf(estadoCliente.getNombre());
            }

            @Override
            public TipoVendedor fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbCargo.setConverter(new StringConverter<Cargo>() {

            @Override
            public String toString(Cargo cargo) {

                return String.valueOf(cargo.getNombre());
            }

            @Override
            public Cargo fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbCargo.setItems(listaCargo);
        cbTipoVendedor.setItems(listaTipoVendedor);

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (txtNombre.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar un nombre");
                txtNombre.requestFocus();
                return;
            }

            if (txtCelular.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar  el celular ");
                txtCelular.requestFocus();
                return;
            }
            if (txtComisionVenta.getText().isEmpty()) {

                txtComisionVenta.setText("0");
            }
            if (txtComisionCobros.getText().isEmpty()) {

                txtComisionCobros.setText("0");
            }

            if (cbTipoVendedor.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar un tipo de vendedor ");
                cbTipoVendedor.requestFocus();
                return;
            }

            if (cbCargo.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar un cargo ");
                txtCelular.requestFocus();
                return;
            }

            ejecutivoDeVenta.setCargo(cbCargo.getSelectionModel().getSelectedItem());
            ejecutivoDeVenta.setTipoEjecutivoVenta(cbTipoVendedor.getSelectionModel().getSelectedItem());
            ejecutivoDeVenta.setActivo(true);
            ejecutivoDeVenta.setNombre(txtNombre.getText());

            ejecutivoDeVenta.setComisionPorVenta(Double.parseDouble(txtComisionVenta.getText()));
            ejecutivoDeVenta.setComisionPorVentaCompatibilidad(Double.parseDouble(txtComisionPorCompatibilidad.getText().isEmpty() 
                    ? "0" :  txtComisionPorCompatibilidad.getText()
            ));
            ejecutivoDeVenta.setComisionPorCobros(Double.parseDouble(txtComisionCobros.getText().isEmpty()
                    ? "0" : txtComisionCobros.getText()
            
            ));

            ejecutivoDeVenta.setPorcientoComision(Double.parseDouble(txtComisionVenta.getText()));
            ejecutivoDeVenta.setCelular(txtCelular.getText());
            ejecutivoDeVenta.setApelldido(txtApellido.getText().isEmpty() ? "na" : txtApellido.getText());
            ejecutivoDeVenta.setDireccion(txtdireccion.getText().isEmpty() ? "na" : txtdireccion.getText());
            ejecutivoDeVenta.setTelefono(txtTelefono.getText().isEmpty() ? "na" : txtTelefono.getText());
            ejecutivoDeVenta.setObservacion(txtObservacion.getText().isEmpty() ? "na" : txtObservacion.getText());
            ejecutivoDeVenta.setCedula(txtRnc.getText().isEmpty() ? "na" : txtRnc.getText());
            ejecutivoDeVenta.setCorreo(txtCorreoElectronico.getText().isEmpty() ? "na" : txtCorreoElectronico.getText());
            ejecutivoDeVenta.setSuplidor(chSuplidorDeCliente.isSelected());

            ejecutivoDeVenta.setFecha(new Date());

            if (!(dpFecha.getValue() == null)) {

                ejecutivoDeVenta.setFechaCumpleaño(ClaseUtil.asDate(dpFecha.getValue()));
            } else {
                ejecutivoDeVenta.setFechaCumpleaño(null);
            }

            if (editar == false) {

                ManejoEjecutivoDeVenta.getInstancia().salvar(ejecutivoDeVenta);
                ClaseUtil.mensaje("Vendedor  creado correctamente");
            } else {
                ManejoEjecutivoDeVenta.getInstancia().actualizar(ejecutivoDeVenta);

                ClaseUtil.mensaje("Vendedor  actualizado correctamente");
            }

            nuevo();
            limpiar();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();

    }

    public void llenarCampo() {

        txtNombre.setText(getEjecutivoDeVenta().getNombre());

        txtComisionVenta.setText(getEjecutivoDeVenta().getPorcientoComision() == null
                ? "0.00" : getEjecutivoDeVenta().getPorcientoComision().toString());

        txtComisionCobros.setText(getEjecutivoDeVenta().getComisionPorCobros() == null
                ? "0.00" : getEjecutivoDeVenta().getComisionPorCobros().toString());

        txtComisionPorCompatibilidad.setText(getEjecutivoDeVenta().getComisionPorVentaCompatibilidad() == null
                ? "0.00" : getEjecutivoDeVenta().getComisionPorVentaCompatibilidad().toString());

        txtApellido.setText(getEjecutivoDeVenta().getApelldido());
        txtCelular.setText(getEjecutivoDeVenta().getCelular());
        txtRnc.setText(getEjecutivoDeVenta().getCedula());
        txtdireccion.setText(getEjecutivoDeVenta().getDireccion());
        txtCorreoElectronico.setText(getEjecutivoDeVenta().getCorreo());
        txtTelefono.setText(getEjecutivoDeVenta().getTelefono());
        txtObservacion.setText(getEjecutivoDeVenta().getObservacion());
        cbCargo.getSelectionModel().select(getEjecutivoDeVenta().getCargo());
        cbTipoVendedor.getSelectionModel().select(getEjecutivoDeVenta().getTipoEjecutivoVenta());

        if (!(getEjecutivoDeVenta().getFechaCumpleaño() == null)) {
            dpFecha.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getEjecutivoDeVenta().getFechaCumpleaño()));

        }

        chSuplidorDeCliente.setSelected(getEjecutivoDeVenta().getSuplidor());

    }

    private void nuevo() {

        ejecutivoDeVenta = new EjecutivoDeVenta();
    }

    private void limpiar() {

        txtNombre.clear();
        txtCelular.clear();
        txtApellido.clear();
        txtdireccion.clear();
        txtObservacion.clear();
        txtComisionCobros.clear();
        txtComisionPorCompatibilidad.clear();
        txtComisionVenta.clear();
        chSuplidorDeCliente.setSelected(false);
        txtTelefono.clear();
        txtCorreoElectronico.clear();
        txtRnc.clear();
        cbCargo.getSelectionModel().select(-1);
        cbTipoVendedor.getSelectionModel().select(-1);

    }

    @FXML
    private void cbTipoVendedorActionEvent(ActionEvent event) {

        if (cbTipoVendedor.getSelectionModel().getSelectedIndex() == -1) {
            return;
        }
        if (cbTipoVendedor.getSelectionModel().getSelectedItem().getCodigo() == 2) {

            cbCargo.setDisable(true);
            cbCargo.getSelectionModel().select(2);

        } else {

            cbCargo.setDisable(false);
        }

    }
}
