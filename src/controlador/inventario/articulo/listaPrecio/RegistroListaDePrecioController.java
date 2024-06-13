/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo.listaPrecio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
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
import manejo.articulo.ManejoListaDePrecio;
import manejo.articulo.ManejoTipoListaPrecio;
import manejo.articulo.ManejoTipoVenta;
import modelo.ListaDePrecio;
import modelo.TipoListaDePrecio;
import modelo.TipoVenta;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroListaDePrecioController implements Initializable {

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
     * @return the listaDePrecio
     */
    public ListaDePrecio getListaDePrecio() {
        return listaDePrecio;
    }

    /**
     * @param listaDePrecio the listaDePrecio to set
     */
    public void setListaDePrecio(ListaDePrecio listaDePrecio) {
        this.listaDePrecio = listaDePrecio;
    }

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXCheckBox chHabilitada;
    @FXML
    private JFXComboBox<TipoListaDePrecio> cbTipoLista;
    @FXML
    private JFXComboBox<TipoVenta> cbTipoVenta;
    @FXML
    private TextArea txtComentario;
    private ListaDePrecio listaDePrecio;
    private boolean editar = false;

    /**
     * Initializes the controller class.
     */
    ObservableList<TipoListaDePrecio> listaTipolista = FXCollections.observableArrayList();
    ObservableList<TipoVenta> listaTipoVenta = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nuevo();
        inicializarCombox();
    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            String comentario = "";
            if (txtNombre.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar un nombre ");
                txtNombre.requestFocus();
                return;
            }
            if (cbTipoLista.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que selccionar un tipo de Lista");
                return;
            }

            if (cbTipoVenta.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que selccionar un tipo de Venta");
                return;
            }

            comentario = txtComentario.getText().isEmpty() ? "no hay comentario" : txtComentario.getText();

            getListaDePrecio().setNombre(txtNombre.getText());
            getListaDePrecio().setTipoLista(cbTipoLista.getSelectionModel().getSelectedItem());
            getListaDePrecio().setTipoVenta(cbTipoVenta.getSelectionModel().getSelectedItem());
            getListaDePrecio().setFecha(new Date());
            getListaDePrecio().setComentario(comentario);
            getListaDePrecio().setHabilitada(chHabilitada.isSelected());
            getListaDePrecio().setUsuario(VariablesGlobales.USUARIO);
            getListaDePrecio().setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            ManejoListaDePrecio.getInstancia().salvar(getListaDePrecio());

            ClaseUtil.mensaje("Registro guardado exitosamente");
            nuevo();

        } catch (Exception e) {
            e.printStackTrace();

            ClaseUtil.mensaje("Hubo un error  guardado el registro ");
        }

    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();

    }

    private void inicializarCombox() {

        cbTipoLista.setConverter(new StringConverter<TipoListaDePrecio>() {

            @Override
            public String toString(TipoListaDePrecio tipoListaDePrecio) {
                return String.valueOf(tipoListaDePrecio.getNombre());
            }

            @Override
            public TipoListaDePrecio fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoVenta.setConverter(new StringConverter<TipoVenta>() {

            @Override
            public String toString(TipoVenta tipoVenta) {
                return String.valueOf(tipoVenta.getNombre());
            }

            @Override
            public TipoVenta fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaTipoVenta.addAll(ManejoTipoVenta.getInstancia().getListaTipoVenta());
        listaTipolista.addAll(ManejoTipoListaPrecio.getInstancia().getListaTipoListaDePrecio());

        cbTipoVenta.setItems(listaTipoVenta);
        cbTipoLista.setItems(listaTipolista);

    }

    private void nuevo() {

        setListaDePrecio(new ListaDePrecio());
        txtComentario.clear();
        txtNombre.clear();

    }

    public void llenarCampo() {

        txtNombre.setText(getListaDePrecio().getNombre());
        cbTipoLista.setValue(getListaDePrecio().getTipoLista());
        cbTipoVenta.setValue(getListaDePrecio().getTipoVenta());

        txtComentario.setText(getListaDePrecio().getComentario());

    }

}
