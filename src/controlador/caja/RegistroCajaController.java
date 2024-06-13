/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.caja;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import manejo.caja.ManejoCaja;
import modelo.Caja;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroCajaController implements Initializable {

    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtNumero;
    @FXML
    private JFXTextField txtUbicacion;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private TableView<Caja> tbCaja;
    @FXML
    private TableColumn<Caja, String> tbcNumero;
    @FXML
    private TableColumn<Caja, String> tbcNombre;
    @FXML
    private TableColumn<Caja, String> tbcUbicacion;

    ObservableList<Caja> listaCaja = FXCollections.observableArrayList();

    Caja caja;
    String mensaje = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabla();
        nuevo();
    }

    public void iniciarTabla() {

        tbCaja.setItems(listaCaja);
        listaCaja.addAll(ManejoCaja.getInstancia().getListaCaja());

        tbcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcUbicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (validarCampo() == false) {

                ClaseUtil.mensaje(mensaje);
                return;
            }

            caja.setNombre(txtNombre.getText());
            caja.setNumero(txtNumero.getText());
            caja.setUbicacion(txtUbicacion.getText());
            caja.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            ManejoCaja.getInstancia().salvar(caja);
            listaCaja.clear();
            listaCaja.addAll(ManejoCaja.getInstancia().getListaCaja());
            nuevo();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @FXML
    private void tbAlmacenMouseClicked(MouseEvent event) {
    }

    private void nuevo() {

        caja = new Caja();

        txtNombre.clear();
        txtNumero.clear();
        txtUbicacion.clear();

    }

    private Boolean validarCampo() {

        if (txtNombre.getText().isEmpty()) {

            mensaje = "Tiene que digital un Nombre";
            txtNombre.requestFocus();
            return false;

        }

        if (txtNumero.getText().isEmpty()) {

            mensaje = "Tiene que digital un Numero";
            txtNumero.requestFocus();
            return false;
        }

        if (txtUbicacion.getText().isEmpty()) {
            mensaje = "Tiene que digital una Ubicacion";

            txtUbicacion.requestFocus();
            return false;
        }

        return true;
    }

}
