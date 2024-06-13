/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.asistenciaTecnica;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.venta.cliente.FXMLBusClienterController;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.asistenciaTecnica.ManejoAsistencaTecnica;
import manejo.asistenciaTecnica.ManejoEstadoAsistencia;
import manejo.asistenciaTecnica.ManejoTipoAsistencia;
import manejo.asistenciaTecnica.ManejoUbicacionAsistencia;
import manejo.contrato.ManejoInstalador;
import modelo.AsistenciaTecnica;
import modelo.Cliente;
import modelo.EstadoAsistenciaTecnica;
import modelo.Instalador;
import modelo.TipoDeAsistencia;
import modelo.UbicacionAsistencia;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroAsistenciaTecnicaController implements Initializable {

    @FXML
    private TabPane tabPane;
    @FXML
    private JFXTextField txtCAntidad;
    @FXML
    private JFXTextField txtPrecio;

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the asistenciaTecnica
     */
    public AsistenciaTecnica getAsistenciaTecnica() {
        return asistenciaTecnica;
    }

    /**
     * @param asistenciaTecnica the asistenciaTecnica to set
     */
    public void setAsistenciaTecnica(AsistenciaTecnica asistenciaTecnica) {
        this.asistenciaTecnica = asistenciaTecnica;
    }

    private JFXCheckBox chDuplicado;
    @FXML
    private JFXTextField txtCliente;
    @FXML
    private JFXButton btnBuscarCliente;
    @FXML
    private JFXDatePicker dpFechaCreacion;
    @FXML
    private TextArea txtMotivoAsistencia;
    @FXML
    private JFXTextField txtClienteCierre;
    @FXML
    private JFXComboBox<TipoDeAsistencia> cbTipoAsistencia;
    @FXML
    private JFXComboBox<UbicacionAsistencia> cbubicacionAsistencia;
    @FXML
    private JFXComboBox<EstadoAsistenciaTecnica> cbEstadoASistencia;
    @FXML
    private JFXDatePicker dpFEchaCierre;
    @FXML
    private JFXComboBox<Instalador> cbAsistidoPor;
    @FXML
    private TextArea txtDescripcionSolucion;
    private Cliente cliente;

    /**
     * @return the cargo
     */
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;

    boolean editar;
    final ToggleGroup group = new ToggleGroup();
    private AsistenciaTecnica asistenciaTecnica;
    ObservableList<TipoDeAsistencia> listaTipoAsistencia = FXCollections.observableArrayList();
    ObservableList<Instalador> listaInstalador = FXCollections.observableArrayList();
    ObservableList<UbicacionAsistencia> listaUbicacion = FXCollections.observableArrayList();

    ObservableList<EstadoAsistenciaTecnica> listaEStado = FXCollections.observableArrayList();

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarCombox();
        txtCAntidad.setDisable(true);
        txtPrecio.setDisable(true);

    }

    private void inicializarCombox() {

        cbEstadoASistencia.setConverter(new StringConverter<EstadoAsistenciaTecnica>() {

            @Override
            public String toString(EstadoAsistenciaTecnica instalador) {
                return String.valueOf(instalador.getNombre());
            }

            @Override
            public EstadoAsistenciaTecnica fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbubicacionAsistencia.setConverter(new StringConverter<UbicacionAsistencia>() {

            @Override
            public String toString(UbicacionAsistencia instalador) {
                return String.valueOf(instalador.getNombre());
            }

            @Override
            public UbicacionAsistencia fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbAsistidoPor.setConverter(new StringConverter<Instalador>() {

            @Override
            public String toString(Instalador instalador) {
                return String.valueOf(instalador.getNombre());
            }

            @Override
            public Instalador fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoAsistencia.setConverter(new StringConverter<TipoDeAsistencia>() {

            @Override
            public String toString(TipoDeAsistencia tipo) {
                return String.valueOf(tipo.getNombre());
            }

            @Override
            public TipoDeAsistencia fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaInstalador.addAll(ManejoInstalador.getInstancia().getLista());
        listaUbicacion.addAll(ManejoUbicacionAsistencia.getInstancia().getUbicacionAsistencia());
        listaEStado.addAll(ManejoEstadoAsistencia.getInstancia().getEstadoAsistenciaTecnica());
        listaTipoAsistencia.addAll(ManejoTipoAsistencia.getInstancia().getListaTipoAsistencia());

        cbAsistidoPor.setItems(listaInstalador);
        cbTipoAsistencia.setItems(listaTipoAsistencia);
        cbubicacionAsistencia.setItems(listaUbicacion);
        cbEstadoASistencia.setItems(listaEStado);
        cbEstadoASistencia.getSelectionModel().select(0);

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (txtMotivoAsistencia.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar el motivo de la asistencia");
                txtMotivoAsistencia.requestFocus();
                return;
            }

            if (txtCliente.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que seleccionar una cliente ");
                txtCliente.requestFocus();
                return;
            }

            if (!editar) {

                setAsistenciaTecnica(new AsistenciaTecnica());
            }

            getAsistenciaTecnica().setFechaRegistro(ClaseUtil.asDate(dpFechaCreacion.getValue()));
            getAsistenciaTecnica().setUsuario(VariablesGlobales.USUARIO);

            getAsistenciaTecnica().setCliente(getCliente());
            getAsistenciaTecnica().setNombreCliente(txtCliente.getText());
            getAsistenciaTecnica().setOrigenAsistencia(txtMotivoAsistencia.getText());
            getAsistenciaTecnica().setEstado(cbEstadoASistencia.getSelectionModel().getSelectedItem());

            if (editar) {

                if (cbTipoAsistencia.getSelectionModel().getSelectedIndex() == -1) {
                    ClaseUtil.mensaje(" Tiene que selecionar el tipo de asistencia");
                    cbTipoAsistencia.requestFocus();
                    return;
                }

                if (dpFEchaCierre.getValue() == null) {
                    ClaseUtil.mensaje("Tiene que selecionar la fecha de cierre ");
                    dpFEchaCierre.requestFocus();
                    return;
                }

                if (cbubicacionAsistencia.getSelectionModel().getSelectedIndex() == -1) {
                    ClaseUtil.mensaje(" Tiene que selecionar la ubicacion  de la asistencia ");
                    cbubicacionAsistencia.requestFocus();
                    return;
                }

                if (!(cbubicacionAsistencia.getSelectionModel().getSelectedIndex() == -1)) {

                    if (txtCAntidad.getText().isEmpty()) {
                        ClaseUtil.mensaje(" Tiene que digitar la cantidad de servisio ");
                        txtCAntidad.requestFocus();
                        return;
                    }

                    if (txtPrecio.getText().isEmpty()) {
                        ClaseUtil.mensaje(" Tiene que digitar el precio del servicio ");
                        txtPrecio.requestFocus();
                        return;
                    }

                }

                if (cbAsistidoPor.getSelectionModel().getSelectedIndex() == -1) {
                    ClaseUtil.mensaje("Tiene que selecionar la persona que asistio al cliente ");
                    cbAsistidoPor.requestFocus();
                    return;
                }

                if (txtDescripcionSolucion.getText().isEmpty()) {
                    ClaseUtil.mensaje(" Tiene que digitar la solucion  de la asistencia ");
                    txtDescripcionSolucion.requestFocus();
                    return;
                }

                getAsistenciaTecnica().setTecnico(cbAsistidoPor.getSelectionModel()
                        .getSelectedItem());
                getAsistenciaTecnica().setNombreTecnico(cbAsistidoPor.getSelectionModel()
                        .getSelectedItem().getNombre());

                getAsistenciaTecnica().setFechaCierre(ClaseUtil.asDate(dpFEchaCierre.getValue()));
                getAsistenciaTecnica().setSolucion(txtDescripcionSolucion.getText());
                getAsistenciaTecnica().setTipoDeAsistencia(cbTipoAsistencia.getSelectionModel().getSelectedItem());
                getAsistenciaTecnica().setUbicacion(cbubicacionAsistencia.getSelectionModel()
                        .getSelectedItem());
                getAsistenciaTecnica().setCantidad(Integer.parseInt(txtCAntidad.getText()));
                getAsistenciaTecnica().setPrecio(Double.parseDouble(txtPrecio.getText()));
                getAsistenciaTecnica().setNombreCliente(txtClienteCierre.getText());

                ManejoAsistencaTecnica.getInstancia().actualizar(getAsistenciaTecnica());

            } else {

                getAsistenciaTecnica().setFechaRegistro(new Date());
                ManejoAsistencaTecnica.getInstancia().salvar(getAsistenciaTecnica());
            }

            txtCliente.clear();
            txtMotivoAsistencia.clear();
            dpFechaCreacion.setValue(null);

            asistenciaTecnica = new AsistenciaTecnica();
            btnBuscarCliente.setDisable(false);
            ClaseUtil.mensaje(" Asistencia registrado correctamente ");
            Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error registrado la asistencia ");
            e.printStackTrace();
        }

    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();
    }

    private void limpiar() {

        txtCliente.clear();
        txtMotivoAsistencia.clear();

    }

    public void llenarCampo() {

        if (getAsistenciaTecnica().getCliente().getNombre().toLowerCase().contains("potenci")) {
            txtClienteCierre.setEditable(true);
            System.out.println("Potencial");
        } else {
            System.out.println("no potencial");
            txtClienteCierre.setEditable(false);
        }
        btnBuscarCliente.setDisable(true);
        tabPane.getSelectionModel().select(1);
        txtClienteCierre.setText(getAsistenciaTecnica().getNombreCliente());
        dpFechaCreacion.setValue(ClaseUtil
                .convertToLocalDateViaMilisecond(getAsistenciaTecnica().getFechaRegistro()));

        cbEstadoASistencia.setValue(getAsistenciaTecnica().getEstado());

        if (!(getAsistenciaTecnica().getFechaCierre() == null)) {

            dpFEchaCierre.setValue(ClaseUtil
                    .convertToLocalDateViaMilisecond(getAsistenciaTecnica().getFechaCierre()));

        }

        if (!(getAsistenciaTecnica().getTipoDeAsistencia() == null)) {

            cbTipoAsistencia.setValue(getAsistenciaTecnica().getTipoDeAsistencia());

        }

        if (!(getAsistenciaTecnica().getTecnico() == null)) {

            cbAsistidoPor.setValue(getAsistenciaTecnica().getTecnico());

        }

        if (!(getAsistenciaTecnica().getUbicacion() == null)) {

            cbubicacionAsistencia.setValue(getAsistenciaTecnica().getUbicacion());

            if (getAsistenciaTecnica().getUbicacion().getCodigo() == 2) {

                txtCAntidad.setText(getAsistenciaTecnica().getCantidad().toString());
                txtPrecio.setText(getAsistenciaTecnica().getPrecio().toString());

            } else {
                txtCAntidad.setDisable(true);
                txtPrecio.setDisable(true);
            }

        } else {
            txtCAntidad.clear();
            txtPrecio.clear();
        }

        txtMotivoAsistencia.setText(getAsistenciaTecnica().getOrigenAsistencia());
        txtDescripcionSolucion.setText(getAsistenciaTecnica().getSolucion() == null ? "na"
                : getAsistenciaTecnica().getSolucion()
        );
        setCliente(getAsistenciaTecnica().getCliente());
        txtCliente.setText(getAsistenciaTecnica().getCliente().getNombre());

    }

    @FXML
    private void btnBuscarClienteActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/cliente/FXMLBusCliente.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        FXMLBusClienterController articuloController = loader.getController();

        if (!(articuloController.getCliente() == null)) {

            setCliente(articuloController.getCliente());

            if (getCliente().getNombre().toLowerCase().contains("potenci")) {
                txtCliente.setEditable(true);
                System.out.println("Potencial");
            } else {
                System.out.println("no potencial");
                txtCliente.setEditable(false);
            }

            txtCliente.setText(getCliente().getNombre());

        }

    }

    @FXML
    private void cbubicacionAsistenciaActionEvent(ActionEvent event) {

        if (!(cbubicacionAsistencia.getSelectionModel().getSelectedIndex() == -1)) {

            if (cbubicacionAsistencia.getSelectionModel().getSelectedItem().getCodigo() == 2) {

                txtCAntidad.setDisable(false);
                txtPrecio.setDisable(false);

            } else {
                txtCAntidad.setText("1");
                txtPrecio.setText("0.00");
                txtCAntidad.setDisable(true);
                txtPrecio.setDisable(true);
            }

        }
    }

}
