/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion.lote;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.inventario.articulo.FXMLBuscarArticuloController;
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
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.articulo.ManejoArticulo;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.produccion.ManejoRegistroLote;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.RegistroLote;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroLoteDeProduccionController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNumeroDeLote;

    @FXML
    private JFXDatePicker dpFecha;
    ObservableList<Articulo> listaArticulo = FXCollections.observableArrayList();
    ObservableList<ArticuloUnidad> listaUnidad = FXCollections.observableArrayList();
    ObservableList<String> listaCapacidad = FXCollections.observableArrayList();
    RegistroLote registroLote;
    @FXML
    private JFXComboBox<ArticuloUnidad> cbUnidad;
    @FXML
    private JFXTextField txtProduccion;
    @FXML
    private JFXTextField txtArticulo;
    @FXML
    private JFXButton btnBuscarArticulo;
    Articulo articulo;
    @FXML
    private JFXComboBox<String> cbCapacidad;
    boolean editar;

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public RegistroLote getRegistroLote() {
        return registroLote;
    }

    public void setRegistroLote(RegistroLote registroLote) {
        this.registroLote = registroLote;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarCombox();
        nuevo();
    }

    private void inicializarCombox() {

        listaCapacidad.addAll("1 GALON", "5 GALONES", "1/4 DE GALON");
        cbUnidad.setConverter(new StringConverter<ArticuloUnidad>() {

            @Override
            public String toString(ArticuloUnidad articulo) {
                return String.valueOf(articulo.getUnidad().getDescripcion());
            }

            @Override
            public ArticuloUnidad fromString(String string) {

                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaArticulo.addAll(ManejoArticulo.getInstancia().getArticulos());

        cbUnidad.setItems(listaUnidad);
        cbCapacidad.setItems(listaCapacidad);

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (txtArticulo.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que seleccionar un producto");
                return;
            }

            if (dpFecha.getValue() == null) {

                ClaseUtil.mensaje("Tiene que seleccionar una fecha");
                return;
            }

            if (txtNumeroDeLote.getText().isEmpty()) {

                ClaseUtil.mensaje("El numero de lote esta vacio");
                txtNumeroDeLote.requestFocus();
                return;
            }

            if (txtProduccion.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digital la produccion ");
                txtProduccion.requestFocus();
                return;
            }

            if (cbCapacidad.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar una capacidad ");
                cbCapacidad.requestFocus();
                return;
            }

            registroLote.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            registroLote.setFechaRegistro(new Date());
            registroLote.setProduccion(Double.parseDouble(txtProduccion.getText()));
            registroLote.setProducto(getArticulo());
            registroLote.setUnidad(cbUnidad.getSelectionModel().getSelectedItem().getUnidad());
            registroLote.setCapacidad(cbCapacidad.getSelectionModel().getSelectedItem());
            registroLote.setNumeroDeLote(Integer.parseInt(txtNumeroDeLote.getText()));
            registroLote.setUsuario(VariablesGlobales.USUARIO);

            Boolean rlt = ManejoRegistroLote.getInstancia().existe(registroLote.getProducto().getCodigo(), registroLote.getNumeroDeLote());

            if (rlt && editar == false) {

                ClaseUtil.mensaje(" Este numero de lote con este producto ya existe ");
                return;
            }

            if (editar == false) {
                ManejoRegistroLote.getInstancia().salvar(registroLote);
            } else {
                ManejoRegistroLote.getInstancia().actualizar(registroLote);
            }

            limpiar();
            nuevo();

        } catch (Exception e) {

            e.printStackTrace();

            ClaseUtil.mensaje("Hubo un error guardando el registro");
            return;
        }
    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {
        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();
    }

    public void llenarCampo() {

        dpFecha.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getRegistroLote().getFecha()));
        txtNumeroDeLote.setText(Integer.toString(getRegistroLote().getNumeroDeLote()));
        txtArticulo.setText(getRegistroLote().getProducto().getNombre());
        txtProduccion.setText(Double.toString(getRegistroLote().getProduccion()));
        setArticulo(getRegistroLote().getProducto());
        listaUnidad.clear();
        listaUnidad.addAll(ManejoArticuloUnidad.getInstancia()
                .getArticuloUnidadSslida(getArticulo().getCodigo(), getRegistroLote().getUnidad().getCodigo()));
        cbUnidad.getSelectionModel().select(0);

    }

    private void nuevo() {

        if (editar == false) {
            System.out.println("Esta creando un registro nuevo");
            setRegistroLote(new RegistroLote());
        }

    }

    private void limpiar() {

        dpFecha.setValue(null);
        txtNumeroDeLote.clear();
        cbUnidad.getSelectionModel().select(-1);
        txtProduccion.clear();
        txtArticulo.clear();
        setArticulo(null);

    }

    @FXML
    private void dpFechaActionEvent(ActionEvent event) {

        if (!(dpFecha.getValue() == null)) {

            String numLote = dpFecha.getValue().getDayOfMonth() + "" + dpFecha.getValue().getMonthValue() + "" + dpFecha.getValue().getYear();

//            String numLote = dpFecha.getValue().getYear() + "" + dpFecha.getValue().getMonthValue() + "" + dpFecha.getValue().getDayOfMonth();
            txtNumeroDeLote.setText(numLote);
        }
    }

    @FXML
    private void btnBuscarArticuloActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
//
        loader.setLocation(getClass().getResource("/vista/inventario/articulo/FXMLBuscarArticulo.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        FXMLBuscarArticuloController articuloController = loader.getController();
        articuloController.setOrigen(1);//el valor  3 es para  buscar solo articulos de consumo

        articuloController.llenarCampo();

        utiles.ClaseUtil.getStageModal(root);

        if (!(articuloController.getArticulo() == null)) {

            setArticulo(articuloController.getArticulo());
            txtArticulo.setText(getArticulo().getDescripcion());
            cbUnidad.requestFocus();
            listaUnidad.clear();

            listaUnidad.addAll(ManejoArticuloUnidad.getInstancia().getListaUnidadSslida(getArticulo().getCodigo()));

            cbUnidad.setItems(listaUnidad);

        }

    }

}
