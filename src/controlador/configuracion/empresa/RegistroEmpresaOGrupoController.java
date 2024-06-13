/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.empresa;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import manejo.empresa.ManejoEmpresaOGrupo;
import modelo.EmpresaOGrupo;

import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroEmpresaOGrupoController implements Initializable {

    /**
     * @return the empresaOGrupo
     */
    public EmpresaOGrupo getEmpresaOGrupo() {
        return empresaOGrupo;
    }

    /**
     * @param empresaOGrupo the empresaOGrupo to set
     */
    public void setEmpresaOGrupo(EmpresaOGrupo empresaOGrupo) {
        this.empresaOGrupo = empresaOGrupo;
    }

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private JFXTextField txtEmail;   
    boolean editar = false;
    private EmpresaOGrupo empresaOGrupo;

    @FXML
    private JFXTextField txtRnc;
    @FXML
    private JFXTextField txtDireccion;
    @FXML
    private JFXTextField txtDescripcion;


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
      
    }


    @FXML

    private void btnGuardarEventAction(ActionEvent event) {

        try {

            System.out.println("unidadDeNegocio " + getEmpresaOGrupo() + " editar " + editar);
            if (getEmpresaOGrupo() == null && !editar) {

                System.out.println("unidadDeNegocio == null && !editar");
                setEmpresaOGrupo(new EmpresaOGrupo());
            }

            if (txtNombre.getText().isEmpty()) {

                txtNombre.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar un nombre");

                return;
            }
            if (txtDescripcion.getText().isEmpty()) {

                txtDescripcion.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar una descripcion ");
                return;
            }

            if (txtTelefono.getText().isEmpty()) {

                txtTelefono.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar un telefono");

                return;
            }

            if (txtDireccion.getText().isEmpty()) {

                txtDireccion.requestFocus();
                ClaseUtil.mensaje("Tiene que digitar una direccion ");

                return;
            }


            getEmpresaOGrupo().setNombre(txtNombre.getText());
            getEmpresaOGrupo().setDescripcion(txtDescripcion.getText());
            getEmpresaOGrupo().setTelefono(txtTelefono.getText());
            getEmpresaOGrupo().setDireccion(txtDireccion.getText());
            getEmpresaOGrupo().setEmail(txtEmail.getText());
            getEmpresaOGrupo().setFechaRegistro(new Date());
            getEmpresaOGrupo().setUsuario(VariablesGlobales.USUARIO);
            getEmpresaOGrupo().setNombreUsuario(getEmpresaOGrupo().getUsuario().getNombre());
            getEmpresaOGrupo().setRnc(txtRnc.getText().isEmpty() ? "na"
                    : txtRnc.getText()
            );           
        

            if (editar) {

                ManejoEmpresaOGrupo.getInstancia().actualizar(getEmpresaOGrupo());
            } else {

                ManejoEmpresaOGrupo.getInstancia().salvar(getEmpresaOGrupo());
            }

            ClaseUtil.mensaje("Empresa  registrada Exitosamente");

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

        txtRnc.setText(getEmpresaOGrupo().getRnc());  
        txtNombre.setText(getEmpresaOGrupo().getNombre());
        txtDescripcion.setText(getEmpresaOGrupo().getDescripcion());
        txtTelefono.setText(getEmpresaOGrupo().getTelefono());
        txtEmail.setText(getEmpresaOGrupo().getEmail());
        txtDireccion.setText(getEmpresaOGrupo().getDireccion());
      

    }

    private void limpiar() {

        txtDescripcion.clear();
        txtDireccion.clear();
        txtNombre.clear();
        txtEmail.clear();
        txtTelefono.clear();
        txtRnc.clear();
        editar = false;
        setEmpresaOGrupo(null);

    }

}
