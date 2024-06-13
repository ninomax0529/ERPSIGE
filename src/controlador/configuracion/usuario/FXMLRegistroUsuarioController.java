/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.usuario;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.ManejoRol;
import manejo.unidadDeNegocio.ManejoUnidadDeNegocio;
import manejo.usuario.ManejoUsuario;
import modelo.Rol;
import modelo.UnidadDeNegocio;
import modelo.Usuario;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLRegistroUsuarioController implements Initializable {

    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXPasswordField txtClave;
    @FXML
    private JFXPasswordField txtConfirmarClave;
    @FXML
    private JFXComboBox<Rol> cbRol;

    Usuario usuario = null;
    @FXML
    private JFXComboBox<UnidadDeNegocio> cbUnidadDeNegocio;
    @FXML
    private JFXCheckBox chHabilitado;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    boolean editar = false;

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    ObservableList<Usuario> lista = FXCollections.observableArrayList();
    ObservableList<Rol> listaRol = FXCollections.observableArrayList();
    ObservableList<UnidadDeNegocio> listaUniDeNegocio = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarCombox();

    }

    private void btnGuardarActionEvent(ActionEvent event) {

    }

    private void inicializarCombox() {

        listaRol.addAll(ManejoRol.getInstancia().getRol());
        listaUniDeNegocio.addAll(ManejoUnidadDeNegocio.getInstancia().getLista());
        cbRol.setItems(listaRol);
        cbUnidadDeNegocio.setItems(listaUniDeNegocio);

        cbRol.setConverter(new StringConverter<Rol>() {

            @Override
            public String toString(Rol rol) {
                return rol.getNombre();
            }

            @Override
            public Rol fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbUnidadDeNegocio.setConverter(new StringConverter<UnidadDeNegocio>() {

            @Override
            public String toString(UnidadDeNegocio unidad) {
                return unidad.getNombre();
            }

            @Override
            public UnidadDeNegocio fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

    }

//    private void tbUsuarioMouseClicked(MouseEvent event) {
//
//        if (!(tbUsuario.getSelectionModel().getSelectedIndex() == -1)) {
//
//            usuario = tbUsuario.getSelectionModel().getSelectedItem();
//
//            txtClave.setText(usuario.getContrasena());
//            txtNombre.setText(usuario.getNombre());
//            txtUsuario.setText(usuario.getUsuario());
//            txtConfirmarClave.setText(usuario.getContrasena());
//            cbRol.getSelectionModel().select(usuario.getRol());
//        }
//    }
    private void btnLimpiarActionEvent(ActionEvent event) {

        limpiar();
    }

    private void limpiar() {

        txtClave.clear();
        txtNombre.clear();
        txtConfirmarClave.clear();
        txtUsuario.clear();
        cbRol.getSelectionModel().clearSelection();
        cbUnidadDeNegocio.getSelectionModel().clearSelection();
        editar = false;
        usuario = null;
        chHabilitado.setSelected(false);

    }

    public void llenarCampo() {

        txtNombre.setText(getUsuario().getNombre());
        txtClave.setText(getUsuario().getContrasena());
        txtUsuario.setText(getUsuario().getUsuario());
        txtConfirmarClave.setText(getUsuario().getContrasena());
        cbRol.getSelectionModel().select(getUsuario().getRol());
        cbUnidadDeNegocio.getSelectionModel().select(getUsuario().getUnidadDeNegocio());
        chHabilitado.setSelected(usuario.getActivo());

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            System.out.println("editar " + editar + " usuario  " + usuario);

            if (usuario == null) {
                System.out.println("unidadDeNegocio == null && !editar");
                usuario = new Usuario();
            }

            if (txtNombre.getText().isEmpty()) {

                txtNombre.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar un nombre");

                return;
            }
            if (txtClave.getText().isEmpty()) {

                txtClave.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar una clave");
                return;
            }

            if (txtConfirmarClave.getText().isEmpty()) {

                txtConfirmarClave.requestFocus();
                ClaseUtil.mensaje("Tiene que confirmar la  clave");
                txtConfirmarClave.requestFocus();
                return;
            }

            if (!(txtConfirmarClave.getText().equals(txtClave.getText()))) {

                txtConfirmarClave.requestFocus();
                ClaseUtil.mensaje("Confirmacion de Clave incorrecta ");
                txtConfirmarClave.requestFocus();
                return;
            }

            if (cbRol.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar un rol");
                return;

            }

            if (cbUnidadDeNegocio.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar una unidad de negocio ");
                return;

            }

            usuario.setUsuario(txtUsuario.getText());
            usuario.setNombre(txtNombre.getText());
            usuario.setContrasena(txtClave.getText());
            usuario.setRol(cbRol.getSelectionModel().getSelectedItem());
            usuario.setUnidadDeNegocio(cbUnidadDeNegocio.getSelectionModel().getSelectedItem());
            usuario.setActivo(chHabilitado.isSelected());

            if (editar) {

                ManejoUsuario.getInstancia().actualizar(usuario);

            } else {

                ManejoUsuario.getInstancia().salvar(usuario);
            }

            ClaseUtil.mensaje("Usuario registrado Exitosamente ");

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
}
