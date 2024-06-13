/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.puntoVenta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import manejo.factura.ManejoRelacionNcf;
import manejo.factura.ManejoTipoNcf;
import manejo.unidadDeNegocio.ManejoUnidadDeNegocio;
import modelo.RelacionNcf;
import modelo.TipoNcf;
import modelo.UnidadDeNegocio;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ConfiguaracionNcfController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXTextField txtSecuenciaFinal;
    @FXML
    private JFXComboBox<TipoNcf> cbTipoNcf;
    @FXML
    private TableView<RelacionNcf> tbRelacionNcf;
    @FXML
    private TableColumn<RelacionNcf, String> tbcTipoNcf;
    @FXML
    private TableColumn<RelacionNcf, String> tbcSecuenciaInicial;
    @FXML
    private TableColumn<RelacionNcf, String> tbcSecuenciaFinal;
    @FXML
    private TableColumn<RelacionNcf, String> tbcNcfActual;
    @FXML
    private TableColumn<RelacionNcf, String> tbcNcfEmitido;
    @FXML
    private TableColumn<RelacionNcf, String> tbcActivo;
    @FXML
    private TableColumn<RelacionNcf, String> tbcUnidadNegocio;
    @FXML
    private TableColumn<RelacionNcf, Date> tbcValidoDesde;
    @FXML
    private TableColumn<RelacionNcf, Date> tbcValidoHasta;

    @FXML
    private JFXTextField txtSecuenciaInicial;

    RelacionNcf relacionNcf;

    ObservableList<RelacionNcf> listaRelacionNcf = FXCollections.observableArrayList();
    ObservableList<UnidadDeNegocio> listaUniDeNegocio = FXCollections.observableArrayList();

    ObservableList<TipoNcf> listaTipoNcf = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtCatidad;
    @FXML
    private JFXComboBox<UnidadDeNegocio> cbUnidadDeNegocio;
    @FXML
    private JFXDatePicker dpFecha1;
    @FXML
    private JFXDatePicker dpFecha11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
        inicializarCombox();
        nuevo();

        txtSecuenciaInicial.setOnKeyReleased((event) -> {
            int catidad;
            int ncfaumentar = 0;

            String ncfNuevo = txtSecuenciaInicial.getText();
//            String prefijoNcf = txtSecuenciaInicial.getText().substring(0, 2);

            if (!txtSecuenciaInicial.getText().isEmpty()) {
                
                ncfaumentar = Integer.parseInt(txtSecuenciaInicial.getText().substring(2, ncfNuevo.length()));
                
            }

            catidad = Integer.parseInt(txtCatidad.getText());

            if (!txtSecuenciaInicial.getText().isEmpty()) {

                catidad += ncfaumentar;
                txtSecuenciaFinal.setText(Integer.toString(catidad));

            }
        });
    }

    private void inicializarCombox() {

        cbTipoNcf.setConverter(new StringConverter<TipoNcf>() {

            @Override
            public String toString(TipoNcf tipoNcf) {
                return String.valueOf(tipoNcf.getDescripcion());
            }

            @Override
            public TipoNcf fromString(String string) {
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

        listaTipoNcf.addAll(ManejoTipoNcf.getInstancia().getListaTipoNcf());

        cbTipoNcf.setItems(listaTipoNcf);

        listaUniDeNegocio.addAll(ManejoUnidadDeNegocio.getInstancia().getLista());

        cbUnidadDeNegocio.setItems(listaUniDeNegocio);

    }

    public void inicializarTabla() {

        listaRelacionNcf.addAll(ManejoRelacionNcf.getInstancia().getLista());
        tbRelacionNcf.setItems(listaRelacionNcf);

        tbcSecuenciaInicial.setCellValueFactory(new PropertyValueFactory<>("rangoInicial"));
        tbcSecuenciaFinal.setCellValueFactory(new PropertyValueFactory<>("rangoFinal"));
        tbcNcfActual.setCellValueFactory(new PropertyValueFactory<>("actual"));
        tbcNcfEmitido.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcValidoDesde.setCellValueFactory(new PropertyValueFactory<>("fechaValidoDesde"));
        tbcValidoHasta.setCellValueFactory(new PropertyValueFactory<>("fechaValidoHasta"));

        tbcTipoNcf.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getTipoNcf().getDescripcion());
                    }
                    return property;
                });
//        
        tbcActivo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getActivo() == true ? "SI" : "NO");
                    }
                    return property;
                });

        tbcUnidadNegocio.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();

                    if (cellData.getValue() != null) {

                        property.setValue(cellData.getValue().getUnidadDeNegocio().getNombre());
                    }
                    return property;
                });

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (dpFecha1.getValue() == null && dpFecha11.getValue() == null) {

                ClaseUtil.mensaje(" La fecha de validadcion estan vacia ");
                return;
            }

            if (cbTipoNcf.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar Tipo de Ncf");
                return;
            }

            if (cbUnidadDeNegocio.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar una unidad de negocio ");
                return;
            }

            if (txtSecuenciaInicial.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar  la Secuencia Inicial");
                txtSecuenciaInicial.requestFocus();
                return;
            }

            if (txtSecuenciaFinal.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar la Secuencia Final");
                txtSecuenciaFinal.requestFocus();
                return;
            }

            RelacionNcf relaNcf = ManejoRelacionNcf.getInstancia()
                    .getNCF(cbTipoNcf.getSelectionModel().getSelectedItem().getCodigo());

            if (!(relaNcf == null)) {

                relaNcf.setActivo(false);
                ManejoRelacionNcf.getInstancia().actualizar(relaNcf);
            }

            relacionNcf.setActivo(true);
            relacionNcf.setRangoInicial(txtSecuenciaInicial.getText());
            relacionNcf.setRangoFinal(txtSecuenciaFinal.getText());
            relacionNcf.setActual(relacionNcf.getRangoInicial());
            relacionNcf.setTipoNcf(cbTipoNcf.getSelectionModel().getSelectedItem());
            relacionNcf.setSecuencia(0);
            relacionNcf.setUnidadDeNegocio(cbUnidadDeNegocio.getSelectionModel().getSelectedItem());
            relacionNcf.setCantidad(Integer.parseInt(txtCatidad.getText()));

            relacionNcf.setFechaValidoDesde(ClaseUtil.asDate(dpFecha1.getValue()));
            relacionNcf.setFechaValidoHasta(ClaseUtil.asDate(dpFecha11.getValue()));

            ManejoRelacionNcf.getInstancia().salvar(relacionNcf);
            listaRelacionNcf.clear();
            listaRelacionNcf.addAll(ManejoRelacionNcf.getInstancia().getLista());

            nuevo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void nuevo() {

        relacionNcf = new RelacionNcf();

        limpiar();
    }

    private void limpiar() {

        txtSecuenciaInicial.clear();
        txtSecuenciaFinal.clear();
        dpFecha1.setValue(null);
        dpFecha11.setValue(null);
        txtCatidad.clear();
        cbUnidadDeNegocio.getSelectionModel().clearSelection();

        cbTipoNcf.getSelectionModel().clearSelection();
    }
}
