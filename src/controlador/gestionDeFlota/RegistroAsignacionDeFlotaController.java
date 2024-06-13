/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.gestionDeFlota;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.contrato.gps.BuscarSimCardController;
import controlador.inventario.articulo.FXMLBuscarArticuloController;
import controlador.nomina.BuscarEmpleadoController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import manejo.gestionDeFlota.ManejoAsignacionDeFlota;
import manejo.gestionDeFlota.ManejoRegistroDeFlota;
import modelo.AsignacionDeFlota;
import modelo.Empleado;
import modelo.EstadoAsistenciaTecnica;
import modelo.Instalador;
import modelo.RegistroDeFlota;
import modelo.TipoDeAsistencia;
import modelo.UbicacionAsistencia;
import reporte.gestionDeFlota.RptFlota;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroAsignacionDeFlotaController implements Initializable {

    @FXML
    private JFXComboBox<String> cbEstado;

    /**
     * @return the registroDeFlota
     */
    public RegistroDeFlota getRegistroDeFlota() {
        return registroDeFlota;
    }

    /**
     * @param registroDeFlota the registroDeFlota to set
     */
    public void setRegistroDeFlota(RegistroDeFlota registroDeFlota) {
        this.registroDeFlota = registroDeFlota;
    }

    /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the asignacion
     */
    public AsignacionDeFlota getAsignacion() {
        return asignacion;
    }

    /**
     * @param asignacion the asignacion to set
     */
    public void setAsignacion(AsignacionDeFlota asignacion) {
        this.asignacion = asignacion;
    }

    @FXML
    private TabPane tabPane;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXButton btnBuscarEmpleado;
    @FXML
    private JFXTextField txtCargo;
    @FXML
    private JFXTextField txtUnidadNegocio;
    @FXML
    private JFXTextField txtDepartamento;
    @FXML
    private JFXTextField txtFlota;
    @FXML
    private JFXButton btnBuscarFlota;
    @FXML
    private JFXTextField txtImei;
    @FXML
    private JFXTextField txtSim;
    @FXML
    private JFXButton btnBuscarSim;
    @FXML
    private JFXTextField txtMarca;
    @FXML
    private JFXTextField txtModelo;
    @FXML
    private TextArea txtObservacion;

    private AsignacionDeFlota asignacion;
    private Empleado empleado;
    private RegistroDeFlota registroDeFlota;

    @FXML
    private JFXTextField txtResponsable;
    /**
     * @return the cargo
     */
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;

    boolean editar;
    final ToggleGroup group = new ToggleGroup();
    ObservableList<TipoDeAsistencia> listaTipoAsistencia = FXCollections.observableArrayList();
    ObservableList<Instalador> listaInstalador = FXCollections.observableArrayList();
    ObservableList<UbicacionAsistencia> listaUbicacion = FXCollections.observableArrayList();
    ObservableList<String> listaEstado = FXCollections.observableArrayList();

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
        dpFecha.setValue(LocalDate.now());

    }

    private void inicializarCombox() {

        listaEstado.addAll("ACTIVA", "INACTIVA");
        cbEstado.setItems(listaEstado);
        cbEstado.getSelectionModel().selectFirst();

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (getEmpleado() == null) {
                ClaseUtil.mensaje("Tiene que seleccionar un empleado ");

                return;
            }
            if (getRegistroDeFlota() == null) {
                ClaseUtil.mensaje("Tiene que seleccionar una flota ");

                return;
            }

            if (!editar) {

                setAsignacion(new AsignacionDeFlota());
            }

            getAsignacion().setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            getAsignacion().setFechaRegistro(ClaseUtil.asDate(dpFecha.getValue()));
            getAsignacion().setUsuario(VariablesGlobales.USUARIO);
            getAsignacion().setNombreUsuario(getAsignacion().getUsuario().getNombre());
            getAsignacion().setResponsable(getEmpleado());
            getAsignacion().setNombreResponsable(getEmpleado().getNombre());

            getAsignacion().setAnulado(false);
            getAsignacion().setNombreCargo(txtCargo.getText());
            getAsignacion().setCargo(getEmpleado().getCargo().getCodigo());
            getAsignacion().setNombreDepartamento(txtDepartamento.getText());
            getAsignacion().setDepartamento(getAsignacion().getResponsable().getUnidadDeNegocio().getCodigo());
            getAsignacion().setUnidadDeNegocio(getAsignacion().getResponsable().getUnidadDeNegocio());
            getAsignacion().setEmpresa(getAsignacion().getResponsable().getUnidadDeNegocio().getCodigo());
            getAsignacion().setNombreEmpresa(txtUnidadNegocio.getText());
            getAsignacion().setNombreFlota(getRegistroDeFlota().getNombreFlota());
            getAsignacion().setFlota(getRegistroDeFlota());
            getAsignacion().setImei(getRegistroDeFlota().getImei());
            getAsignacion().setSim(getRegistroDeFlota().getSim());
            getAsignacion().setModelo(getRegistroDeFlota().getModelo());
            getAsignacion().setMarca(getRegistroDeFlota().getMarca());
            getAsignacion().setEstado(cbEstado.getValue());
            getAsignacion().setFechaEstado(new Date());
            getAsignacion().setObservacion(txtObservacion.getText());

            if (editar) {

                ManejoAsignacionDeFlota.getInstancia().actualizar(getAsignacion());

                ClaseUtil.mensaje(" Asignacion actualizada correctamente ");
            } else {

                getAsignacion().setFechaRegistro(new Date());
                setAsignacion(ManejoAsignacionDeFlota.getInstancia().salvar(getAsignacion()));
            }

            if (getAsignacion().getEstado().equals("ACTIVA")) {
                System.out.println("ACTIVA");
                getRegistroDeFlota().setAsignada(true);
            } else {
                getRegistroDeFlota().setAsignada(false);
                System.out.println("INACTIVA");
            }

            ManejoRegistroDeFlota.getInstancia().actualizar(getAsignacion().getFlota());

            txtResponsable.clear();
            txtFlota.clear();
            txtCargo.clear();
            txtModelo.clear();
            txtMarca.clear();
            txtSim.clear();
            txtImei.clear();
            txtDepartamento.clear();
            txtUnidadNegocio.clear();

            RptFlota.getInstancia().verAsignacion(getAsignacion().getCodigo());
            
            asignacion = new AsignacionDeFlota();

//            ClaseUtil.mensaje(" Asignacion registrado correctamente ");
            Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error registrado la Asignacion ");
            e.printStackTrace();
        }

    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();
    }

    public void llenarCampo() {

        txtResponsable.setText(getAsignacion().getNombreResponsable());
        dpFecha.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getAsignacion().getFecha()));

        txtCargo.setText(getAsignacion().getNombreCargo());
        txtUnidadNegocio.setText(getAsignacion().getNombreEmpresa());
        txtDepartamento.setText(getAsignacion().getNombreDepartamento());

        txtSim.setText(getAsignacion().getSim());
        txtObservacion.setText(getAsignacion().getObservacion());
        txtImei.setText(getAsignacion().getImei());
        txtFlota.setText(getAsignacion().getNombreFlota());
        txtMarca.setText(getAsignacion().getMarca());
        txtModelo.setText(getAsignacion().getModelo());
        txtCargo.setText(getAsignacion().getNombreCargo());
        txtDepartamento.setText(getAsignacion().getNombreDepartamento());
        cbEstado.setValue(getAsignacion().getEstado());
        setRegistroDeFlota(getAsignacion().getFlota());
        setEmpleado(getAsignacion().getResponsable());

    }

    @FXML
    private void btnBuscarEmpleadoActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/nomina/BuscarEmpleado.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        BuscarEmpleadoController controller = loader.getController();

        System.out.println("controller.getEmpleado() " + controller);
        System.out.println("controller.getEmpleado() " + controller.getEmpleado());
        if (!(controller.getEmpleado() == null)) {

            setEmpleado(controller.getEmpleado());
            txtResponsable.setText(getEmpleado().getNombre());
            txtCargo.setText(getEmpleado().getCargo().getNombre());
            txtUnidadNegocio.setText(getEmpleado().getUnidadDeNegocio().getNombre());
            txtDepartamento.setText(getEmpleado().getUnidadDeNegocio().getNombre());

        }
    }

    @FXML
    private void btnBuscarFlotaActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/gestionDeFlota/BuscarFlota.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        BuscarFlotaController controller = loader.getController();

        utiles.ClaseUtil.getStageModal(root);

        if (!(controller.getRegistroDeFlota() == null)) {

            setRegistroDeFlota(controller.getRegistroDeFlota());

            txtFlota.setText(getRegistroDeFlota().getNombreFlota());
            txtModelo.setText(getRegistroDeFlota().getModelo());
            txtMarca.setText(getRegistroDeFlota().getMarca());
            txtImei.setText(getRegistroDeFlota().getImei());

            if (!(getRegistroDeFlota().getSim() == null)) {

                txtSim.setText(getRegistroDeFlota().getImei());
            }

        } else {
            System.out.println("no tiene una flota ");
        }

    }

    @FXML
    private void btnBuscarSimActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/contrato/gps/BuscarSimCard.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        BuscarSimCardController controller = loader.getController();

        utiles.ClaseUtil.getStageModal(root);

        System.out.println("ESPERANDOOO!!!");

        if (!(controller.getRegistro() == null)) {

            txtSim.setText(controller.getRegistro().getNumero());

        } else {
            ClaseUtil.mensaje("Tiene que seleccionar un sim");
        }

    }

}
