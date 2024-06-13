/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.proyecto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
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
import manejo.proyecto.ManejoEstadoProyecto;
import manejo.proyecto.ManejoProyecto;
import modelo.EstadoProyecto;
import modelo.Proyecto;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroProyectoController implements Initializable {

    /**
     * @return the proyecto
     */
    public Proyecto getProyecto() {
        return proyecto;
    }

    /**
     * @param proyecto the proyecto to set
     */
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @FXML
    private TextArea txtDescripcion;
    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXComboBox<EstadoProyecto> cbEstado;

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;

    @FXML
    private JFXTextField txtNombre;
    private Proyecto proyecto;
    boolean editar;

    ObservableList<EstadoProyecto> listaEstado = FXCollections.observableArrayList();

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
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
    }

    private void inicializarCombox() {

        listaEstado.addAll(ManejoEstadoProyecto.getInstancia().getLista());
        cbEstado.setConverter(new StringConverter<EstadoProyecto>() {

            @Override
            public String toString(EstadoProyecto unidad) {
                return String.valueOf(unidad.getNombre());
            }

            @Override
            public EstadoProyecto fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbEstado.setItems(listaEstado);

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        if (txtNombre.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar un nombre");
            txtNombre.requestFocus();
            return;
        }

        if (cbEstado.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje("Tiene que seleccionar un estado ");

            return;
        }

        if (txtDescripcion.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar la descripcion del proyecto ");
            txtDescripcion.requestFocus();
            return;
        }

        if (!editar) {

            System.out.println("crear nuevo");
            setProyecto(new Proyecto());
        } else {
            System.out.println("editar");
        }

        Date fi = ClaseUtil.asDate(dpFechaInicio.getValue());
        Date ff = ClaseUtil.asDate(dpFechaFinal.getValue());

        getProyecto().setNombre(txtNombre.getText());
        getProyecto().setDescripcion(txtDescripcion.getText());
        getProyecto().setEstado(cbEstado.getValue());
        getProyecto().setFechaInicio(fi);
        getProyecto().setFechaFinal(ff);
        getProyecto().setFechaRegistro(new Date());
        getProyecto().setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        getProyecto().setUsuario(VariablesGlobales.USUARIO);

        if (editar) {

            ManejoProyecto.getInstancia().actualizar(getProyecto());

        } else {

            getProyecto().setFechaRegistro(new Date());
            ManejoProyecto.getInstancia().salvar(getProyecto());
        }

        cbEstado.getSelectionModel().select(-1);
        setProyecto(new Proyecto());
        txtNombre.clear();
        txtDescripcion.clear();

    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();
    }

    private void limpiar() {

        txtNombre.clear();
        txtDescripcion.clear();

    }

    public void llenarCampo() {

        txtNombre.setText(getProyecto().getNombre());
        txtDescripcion.setText(getProyecto().getDescripcion());
        cbEstado.getSelectionModel().select(getProyecto().getEstado());

    }

    @FXML
    private void dpFechaFinalActionEvent(ActionEvent event) {
    }

    @FXML
    private void cbEstadoActinEvent(ActionEvent event) {
    }
}
