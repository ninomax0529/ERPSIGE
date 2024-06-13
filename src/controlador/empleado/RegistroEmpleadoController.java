/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.empleado;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.ejecutivoDeVenta.ManejoCargo;
import manejo.empleado.ManejoCondicionEmpleado;
import manejo.empleado.ManejoEmpleado;
import manejo.empleado.ManejoEstadoCivil;
import manejo.empleado.ManejoEstadoEmpleado;
import manejo.nomina.ManejoTipoNomina;
import modelo.Cargo;
import modelo.CondicionEmpleado;
import modelo.Empleado;
import modelo.EstadoCivil;
import modelo.EstadoEmpleado;
import modelo.TipoDeNomina;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroEmpleadoController implements Initializable {

    @FXML
    private JFXTextField txtCedula;
    @FXML
    private JFXDatePicker dpFechaIngreso;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

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

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtApellido;
    @FXML
    private JFXTextField txtdireccion;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private JFXTextField txtCelular;
    @FXML
    private JFXComboBox<Cargo> cbCargo;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtCorreoElectronico;
    @FXML
    private JFXTextField txtSueldo;
    @FXML
    private TextArea txtObservacion;
    @FXML
    private JFXComboBox<TipoDeNomina> cbTipoNomina;
    @FXML
    private JFXComboBox<EstadoEmpleado> cbEstado;
    @FXML
    private JFXComboBox<EstadoCivil> cbEstadoCivil;
    @FXML
    private JFXRadioButton rbMasculino;
    @FXML
    private JFXRadioButton rbFemenino;
    @FXML
    private JFXComboBox<CondicionEmpleado> cbCondicion;
    Empleado empleado;
    private boolean editar = false;
    ToggleGroup grupoRadioButtton = new ToggleGroup();

    ObservableList<Cargo> listaCargo = FXCollections.observableArrayList();
    ObservableList<TipoDeNomina> listaTipoNomina = FXCollections.observableArrayList();
    ObservableList<CondicionEmpleado> listaCondicion = FXCollections.observableArrayList();
    ObservableList<EstadoEmpleado> listaEstado = FXCollections.observableArrayList();
    ObservableList<EstadoCivil> listaEstadoCivil = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarCombox();
        rbFemenino.setToggleGroup(grupoRadioButtton);
        rbMasculino.setToggleGroup(grupoRadioButtton);

    }

    private void inicializarCombox() {

//        listaCargo.clear();
        listaCargo.addAll(ManejoCargo.getInstancia().getLista());
        listaTipoNomina.addAll(ManejoTipoNomina.getInstancia().getLista());
        listaCondicion.addAll(ManejoCondicionEmpleado.getInstancia().getLista());
        listaEstadoCivil.addAll(ManejoEstadoCivil.getInstancia().getLista());
        listaEstado.addAll(ManejoEstadoEmpleado.getInstancia().getLista());

        cbTipoNomina.setConverter(new StringConverter<TipoDeNomina>() {

            @Override
            public String toString(TipoDeNomina tipo) {
                return String.valueOf(tipo.getNombre());
            }

            @Override
            public TipoDeNomina fromString(String string) {
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

        cbCondicion.setConverter(new StringConverter<CondicionEmpleado>() {

            @Override
            public String toString(CondicionEmpleado condicion) {
                return String.valueOf(condicion.getNombre());
            }

            @Override
            public CondicionEmpleado fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbEstado.setConverter(new StringConverter<EstadoEmpleado>() {

            @Override
            public String toString(EstadoEmpleado estado) {
                return String.valueOf(estado.getNombre());
            }

            @Override
            public EstadoEmpleado fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbEstadoCivil.setConverter(new StringConverter<EstadoCivil>() {

            @Override
            public String toString(EstadoCivil estado) {
                return String.valueOf(estado.getNombre());
            }

            @Override
            public EstadoCivil fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbCargo.setItems(listaCargo);
        cbTipoNomina.setItems(listaTipoNomina);
        cbEstado.setItems(listaEstado);
        cbEstadoCivil.setItems(listaEstadoCivil);
        cbCondicion.setItems(listaCondicion);

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (dpFechaIngreso.getValue() == null) {
                ClaseUtil.mensaje(" Tiene que seleccionar la fecha de ingreso ");
                dpFechaIngreso.requestFocus();
                return;
            }
            if (dpFecha.getValue() == null) {
                ClaseUtil.mensaje(" Tiene que seleccionar la fecha de cumpleaño ");
                dpFecha.requestFocus();
                return;
            }

            if (cbCondicion.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar la condicion ");
                cbCondicion.requestFocus();
                return;
            }

            if (cbEstado.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje(" Tiene que seleccionar un estado ");
                cbEstado.requestFocus();
                return;
            }

            if (txtNombre.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar un nombre");
                txtNombre.requestFocus();
                return;
            }

            if (txtSueldo.getText().isEmpty() || txtSueldo.getText().length() == 0) {
                ClaseUtil.mensaje(" Tiene que digitar el sueldo ");
                txtSueldo.requestFocus();
                return;
            }

            if (txtApellido.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar el apellido");
                txtApellido.requestFocus();
                return;
            }

            if (txtdireccion.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar la direccion ");
                txtdireccion.requestFocus();
                return;
            }

            if (txtCelular.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar  el celular ");
                txtCelular.requestFocus();
                return;
            }
            if (txtCedula.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar la cedula ");
                txtCedula.requestFocus();
                return;
            }

            if (rbFemenino.isSelected() == false && rbMasculino.isSelected() == false) {
                ClaseUtil.mensaje("Tiene que seleccionar el sexo ");

                return;
            }

            if (cbEstadoCivil.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar el estado civil ");
                cbEstadoCivil.requestFocus();
                return;
            }

            if (cbCargo.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar un cargo ");
                cbCargo.requestFocus();
                return;
            }

            if (cbTipoNomina.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje(" Tiene que seleccionar un tipo de nomina ");
                cbTipoNomina.requestFocus();
                return;
            }

            if (!isEditar()) {
                empleado = new Empleado();
            }

            Double pagoPorHora = 0.00, pagoPorDia, suldoPromedio = 0.00;
            empleado.setUsuario(VariablesGlobales.USUARIO);
            empleado.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            Date fechaCumpl = ClaseUtil.asDate(dpFecha.getValue());
            Date fechaIngreso = ClaseUtil.asDate(dpFechaIngreso.getValue());

            empleado.setApellido(txtApellido.getText());
            empleado.setCargo(cbCargo.getSelectionModel().getSelectedItem());
            empleado.setCelular(txtCelular.getText());
            empleado.setCedula(txtCedula.getText());
            empleado.setCondicion(cbCondicion.getSelectionModel().getSelectedItem());
            empleado.setCorreo(txtCorreoElectronico.getText());
            empleado.setEstado(cbEstado.getSelectionModel().getSelectedItem());
            empleado.setEstadoCivil(cbEstadoCivil.getSelectionModel().getSelectedItem());
            empleado.setTipoNomina(cbTipoNomina.getSelectionModel().getSelectedItem());

            empleado.setFecha(fechaIngreso);
            empleado.setFechaCumpleanio(fechaCumpl);
            empleado.setNombre(txtNombre.getText());
            empleado.setObservacion(txtObservacion.getText());
            empleado.setDireccion(txtdireccion.getText());
            empleado.setTelefono(txtTelefono.getText().isEmpty() ? "na" : txtTelefono.getText());
            empleado.setSueldoMensual(Double.parseDouble(txtSueldo.getText()));

            if (empleado.getTipoNomina().getCodigo() == 1) {

                empleado.setSueldoSemanal(empleado.getSueldoMensual() / 4);

            } else if (empleado.getTipoNomina().getCodigo() == 2) {

                empleado.setSueldoQuincenal(empleado.getSueldoMensual() / 2);
            }

            pagoPorDia = ClaseUtil.roundDouble(empleado.getSueldoMensual() / 23.83, 4);
            pagoPorHora = ClaseUtil.roundDouble(pagoPorDia / 8, 2);

            empleado.setSueldoPorHora(pagoPorHora);
            empleado.setSueldoPorDia(pagoPorDia);

            if (rbFemenino.isSelected()) {
                empleado.setSexo("f");
            }

            if (rbMasculino.isSelected()) {
                empleado.setSexo("m");
            }

            System.out.println("editar " + editar + "getEdi " + isEditar());
            if (editar) {

                ManejoEmpleado.getInstancia().actualizar(empleado);
                ClaseUtil.mensaje(" Empleado actaulizado exitosamente ");

            } else {

                empleado.setFecha(new Date());
                ManejoEmpleado.getInstancia().salvar(empleado);

                ClaseUtil.mensaje("Empleado guardado exitosamente");

            }

            limpiar();

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error guardando el empleado ");
            e.printStackTrace();
        }

    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cbCargoActionEvent(ActionEvent event) {

        if (!(cbCargo.getSelectionModel().getSelectedIndex() == -1)) {

            Double sueldo = cbCargo.getSelectionModel().getSelectedItem().getSueldo();

            txtSueldo.setText(sueldo.toString());
        } else {

            txtSueldo.clear();
        }
    }

    private void limpiar() {

        dpFecha.setValue(null);
        dpFechaIngreso.setValue(null);
        rbFemenino.setSelected(false);
        rbMasculino.setSelected(false);
        txtNombre.clear();
        txtCelular.clear();
        txtdireccion.clear();
        txtApellido.clear();
        txtSueldo.clear();
        txtObservacion.clear();
        txtTelefono.clear();
        txtCorreoElectronico.clear();
        txtCedula.clear();
        cbCargo.getSelectionModel().select(-1);
        cbTipoNomina.getSelectionModel().select(-1);
        cbEstado.getSelectionModel().select(-1);
        cbEstadoCivil.getSelectionModel().select(-1);
        cbCondicion.getSelectionModel().select(-1);
        editar = false;

    }

    public void llenarCampo() {

        txtNombre.setText(getEmpleado().getNombre());
        txtSueldo.setText(Double.toString(getEmpleado().getSueldoMensual()));

        txtApellido.setText(getEmpleado().getApellido());
        txtCelular.setText(getEmpleado().getCelular());
        txtCedula.setText(getEmpleado().getCedula());
        txtdireccion.setText(getEmpleado().getDireccion());
        txtCorreoElectronico.setText(getEmpleado().getCorreo());
        txtTelefono.setText(getEmpleado().getTelefono());
        txtObservacion.setText(getEmpleado().getObservacion());
        cbCargo.getSelectionModel().select(getEmpleado().getCargo());
        cbTipoNomina.getSelectionModel().select(getEmpleado().getTipoNomina());
        cbCondicion.getSelectionModel().select(getEmpleado().getCondicion());
        cbEstadoCivil.getSelectionModel().select(getEmpleado().getEstadoCivil());
        cbEstado.getSelectionModel().select(getEmpleado().getEstado());

        if (!(getEmpleado().getFechaCumpleanio() == null)) {
            dpFecha.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getEmpleado().getFechaCumpleanio()));
        }

        if (!(getEmpleado().getFecha() == null)) {
            dpFechaIngreso.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getEmpleado().getFecha()));
        }

        if (getEmpleado().getSexo().equals("m")) {
            rbMasculino.setSelected(true);
        }

        if (getEmpleado().getSexo().equals("f")) {
            rbFemenino.setSelected(true);
        }

    }

    @FXML
    private void cbEstadoCivilActionEvent(ActionEvent event) {
    }

}
