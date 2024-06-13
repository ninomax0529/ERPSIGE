/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.comprobanteFiscales;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import manejo.empresa.ManejoEmpresaOGrupo;
import manejo.factura.ManejoRelacionNcf;
import manejo.factura.ManejoTipoNcf;
import manejo.unidadDeNegocio.ManejoUnidadDeNegocio;
import modelo.EmpresaOGrupo;
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
    private TableColumn<RelacionNcf, Integer> tbcNcfUsado;

    @FXML
    private JFXTextField txtSecuenciaInicial;

    RelacionNcf relacionNcf;
    boolean editar = false;

    ObservableList<RelacionNcf> listaRelacionNcf = FXCollections.observableArrayList();
    ObservableList<UnidadDeNegocio> listaUniDeNegocio = FXCollections.observableArrayList();
    ObservableList<EmpresaOGrupo> listaEmpresaGrupo = FXCollections.observableArrayList();

    ObservableList<TipoNcf> listaTipoNcf = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtCatidad;
    @FXML
    private JFXComboBox<UnidadDeNegocio> cbUnidadDeNegocio;
    @FXML
    private JFXDatePicker dpFecha1;
    @FXML
    private JFXDatePicker dpFecha11;
    @FXML
    private Label lbCantidadDigito;
    @FXML
    private Label lbMensaje;
    @FXML
    private JFXComboBox<EmpresaOGrupo> cbEmpresaOGrupo;
    @FXML
    private Label lbSerie;
    @FXML
    private Label lbTipo;
    @FXML
    private JFXTextField txtNcfInicial;
    @FXML
    private JFXTextField txtNcfFinal;
    @FXML
    private HBox hbNcf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
        inicializarCombox();
        nuevo();

        hbNcf.setVisible(false);

        lbCantidadDigito.setVisible(false);

        txtSecuenciaInicial.setOnKeyReleased((event) -> {

            if (txtCatidad.getText().isEmpty()) {

                ClaseUtil.mensaje(" Tiene que digitar la cantidad de NCF ");

                return;
            }

            int catidad;
            int ncfaumentar = 0;
            String ncfNuevo = "";

            if (!txtSecuenciaInicial.getText().isEmpty()) {
                ncfNuevo = txtSecuenciaInicial.getText();
            }

//            String prefijoNcf = txtSecuenciaInicial.getText().substring(0, 2);
            if (!txtSecuenciaInicial.getText().isEmpty()) {

//                if (ncfNuevo.length() > 3) {
                ncfaumentar = Integer.parseInt(txtSecuenciaInicial.getText());
//                }

            }

            catidad = Integer.parseInt(txtCatidad.getText());

            if (!txtSecuenciaInicial.getText().isEmpty()) {

                catidad += ncfaumentar;
                txtSecuenciaFinal.setText(txtSecuenciaInicial.getText().concat(("" + catidad).trim()));

            }

            lbCantidadDigito.setVisible(true);
            lbCantidadDigito.setText(Integer.toString(txtSecuenciaInicial.getText().length()));
        });

    }

    private void inicializarCombox() {

        cbTipoNcf.setConverter(new StringConverter<TipoNcf>() {

            @Override
            public String toString(TipoNcf tipoNcf) {
                return String.valueOf(tipoNcf.getDescripcion() + "  " + tipoNcf.getPrefijo());
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

        cbEmpresaOGrupo.setConverter(new StringConverter<EmpresaOGrupo>() {

            @Override
            public String toString(EmpresaOGrupo empresa) {
                return empresa.getNombre();
            }

            @Override
            public EmpresaOGrupo fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaTipoNcf.addAll(ManejoTipoNcf.getInstancia().getListaTipoNcf());

        cbTipoNcf.setItems(listaTipoNcf);

//        listaUniDeNegocio.addAll(ManejoUnidadDeNegocio.getInstancia().getListaConRnc());
        listaEmpresaGrupo.addAll(ManejoEmpresaOGrupo.getInstancia().getLista());

        cbEmpresaOGrupo.setItems(listaEmpresaGrupo);
        cbUnidadDeNegocio.setItems(listaUniDeNegocio);

    }

    public void inicializarTabla() {

        listaRelacionNcf.addAll(ManejoRelacionNcf.getInstancia().getLista());
        tbRelacionNcf.setItems(listaRelacionNcf);

        tbcSecuenciaInicial.setCellValueFactory(new PropertyValueFactory<>("rangoInicial"));
        tbcSecuenciaFinal.setCellValueFactory(new PropertyValueFactory<>("rangoFinal"));
        tbcNcfActual.setCellValueFactory(new PropertyValueFactory<>("actual"));
        tbcNcfEmitido.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcNcfUsado.setCellValueFactory(new PropertyValueFactory<>("secuencia"));
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

                    if (cellData.getValue().getUnidadDeNegocio() != null) {

                        property.setValue(cellData.getValue().getUnidadDeNegocio().getNombre());
                    } else {
                        ManejoEmpresaOGrupo.getInstancia().getEmpresaOGrupo(cellData.getValue().getEmpresaOGrupo());
                        property.setValue(ManejoEmpresaOGrupo.getInstancia().getEmpresaOGrupo(cellData.getValue().getEmpresaOGrupo()).getNombre());
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

            if (cbEmpresaOGrupo.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar el grupo ");
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

            if (txtSecuenciaInicial.getText().length() !=8  && lbSerie.getText().equals("B")) {

                ClaseUtil.mensaje(" La secuencia del  ncf tiene que tener 8 caracteres  ");
                txtSecuenciaInicial.requestFocus();
                return;
            }

            relacionNcf.setRangoInicial(lbSerie.getText() + lbTipo.getText() + txtSecuenciaInicial.getText());

            if (relacionNcf.getRangoInicial().length() != 11 && lbSerie.getText().equals("B")) {
                utiles.ClaseUtil.mensaje("El rango inicial es diferente de 11 caracteres con la serie B,Por favor revisar ");
                return;
            } else if (relacionNcf.getRangoInicial().length() != 11 && lbSerie.getText().equals("E")) {

                utiles.ClaseUtil.mensaje("El rango inicial es diferente de 13 caracteres con la serie E ,Por favor revisar  ");
            }

            if (txtCatidad.getText().length() > 9) {

                ClaseUtil.mensaje(" El ncf tiene que tener  caracteres  ");
                txtSecuenciaInicial.requestFocus();
                return;
            }

            int ung = 0;

            if (!(cbUnidadDeNegocio.getSelectionModel().getSelectedIndex() == -1)) {

                ung = cbUnidadDeNegocio.getSelectionModel().getSelectedItem().getCodigo();

                int tipoNcf = cbTipoNcf.getSelectionModel().getSelectedItem().getCodigo();

                RelacionNcf relaNcf = ManejoRelacionNcf.getInstancia()
                        .getNCFUnidadDeNegocio(tipoNcf, ung);

                if (!(relaNcf == null) && editar == false) {

                    relaNcf.setActivo(false);
                    ManejoRelacionNcf.getInstancia().actualizar(relaNcf);
                }

                relacionNcf.setUnidadDeNegocio(cbUnidadDeNegocio.getSelectionModel().getSelectedItem());

            } else {

                relacionNcf.setEmpresaOGrupo(cbEmpresaOGrupo.getSelectionModel().getSelectedItem().getCodigo());
                ung = cbEmpresaOGrupo.getValue().getCodigo();

                int tipoNcf = cbTipoNcf.getSelectionModel().getSelectedItem().getCodigo();
                RelacionNcf relaNcf = ManejoRelacionNcf.getInstancia()
                        .getNCFEmpresaOGrupo(tipoNcf, ung);

                System.out.println("relaNcf " + relaNcf + " editar " + editar);

                if (!(relaNcf == null) && editar == false) {

                    relaNcf.setActivo(false);
                    ManejoRelacionNcf.getInstancia().actualizar(relaNcf);
                }

            }

            relacionNcf.setActivo(true);

            relacionNcf.setTipoNcf(cbTipoNcf.getSelectionModel().getSelectedItem());
            relacionNcf.setSerie(lbSerie.getText());
            relacionNcf.setTipo(lbTipo.getText());

//            relacionNcf.setRangoInicial(lbSerie.getText() + lbTipo.getText() + txtSecuenciaInicial.getText());
            relacionNcf.setRangoFinal(lbSerie.getText() + lbTipo.getText() + txtSecuenciaFinal.getText());

            relacionNcf.setSecuenciaInicial(txtSecuenciaInicial.getText());
            relacionNcf.setSecuenciaFinal(txtSecuenciaFinal.getText());
            relacionNcf.setSecuenciaActual(relacionNcf.getSecuenciaInicial());

            relacionNcf.setEmpresaOGrupo(cbEmpresaOGrupo.getSelectionModel().getSelectedItem().getCodigo());
            relacionNcf.setCantidad(Integer.parseInt(txtCatidad.getText()));
            relacionNcf.setFechaValidoDesde(ClaseUtil.asDate(dpFecha1.getValue()));
            relacionNcf.setFechaValidoHasta(ClaseUtil.asDate(dpFecha11.getValue()));

//            if (editar) {
//
//                relacionNcf.setFechaActualizacion(new Date());
//                ManejoRelacionNcf.getInstancia().actualizar(relacionNcf);
//
//            } else {
//
                relacionNcf.setFechaRegistro(new Date());
                relacionNcf.setFechaActualizacion(new Date());
                relacionNcf.setActual(relacionNcf.getRangoInicial());
                relacionNcf.setSecuencia(0);

                ManejoRelacionNcf.getInstancia().salvar(relacionNcf);
//            }

            listaRelacionNcf.clear();
            listaRelacionNcf.addAll(ManejoRelacionNcf.getInstancia().getLista());
            lbCantidadDigito.setVisible(false);
            nuevo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void nuevo() {

        relacionNcf = new RelacionNcf();
        editar = false;
        lbMensaje.setVisible(false);

        limpiar();
    }

    private void limpiar() {

        txtSecuenciaInicial.clear();
        txtSecuenciaFinal.clear();
        dpFecha1.setValue(null);
        dpFecha11.setValue(null);
        txtCatidad.clear();
        cbUnidadDeNegocio.getSelectionModel().clearSelection();
        cbEmpresaOGrupo.getSelectionModel().clearSelection();
        lbMensaje.setVisible(false);

        cbTipoNcf.getSelectionModel().clearSelection();

    }

    @FXML
    private void tbRelacionNcfAction(MouseEvent event) {

//        if (!(tbRelacionNcf.getSelectionModel().getSelectedIndex() == -1)) {
//
//            editar = true;
//            relacionNcf = tbRelacionNcf.getSelectionModel().getSelectedItem();
//
//            txtCatidad.setText(relacionNcf.getCantidad().toString());
//            txtSecuenciaInicial.setText(relacionNcf.getSecuenciaInicial());
//            txtSecuenciaFinal.setText(relacionNcf.getSecuenciaFinal());
//
//            txtNcfInicial.setText(relacionNcf.getRangoInicial());
//            txtNcfFinal.setText(relacionNcf.getRangoFinal());
//
//            dpFecha1.setValue(ClaseUtil.convertToLocalDateViaMilisecond(relacionNcf.getFechaValidoDesde()));
//            dpFecha11.setValue(ClaseUtil.convertToLocalDateViaMilisecond(relacionNcf.getFechaValidoHasta()));
//            cbTipoNcf.setValue(relacionNcf.getTipoNcf());
//            lbSerie.setText(relacionNcf.getSerie());
//            lbTipo.setText(relacionNcf.getTipo());
//
//            cbUnidadDeNegocio.setValue(relacionNcf.getUnidadDeNegocio());
//
//            if (!(relacionNcf.getEmpresaOGrupo() == null)) {
//                cbEmpresaOGrupo.setValue(ManejoEmpresaOGrupo.getInstancia().getEmpresaOGrupo(relacionNcf.getEmpresaOGrupo()));
//            }
//
//        }
    }

    @FXML
    private void cbEmpresaOGrupoActionEvent(ActionEvent event) {

        if (!(cbEmpresaOGrupo.getSelectionModel().getSelectedIndex() == -1)) {

            lbMensaje.setVisible(true);
            String grupo = cbEmpresaOGrupo.getSelectionModel().getSelectedItem().getNombre();
            listaUniDeNegocio.clear();
            listaUniDeNegocio.addAll(ManejoUnidadDeNegocio.getInstancia().getLista(grupo));

            lbMensaje.setText(" LOS NCF SERAN DE  " + grupo.toUpperCase());

        } else {
            listaUniDeNegocio.clear();
            lbMensaje.setText("");
            lbMensaje.setVisible(false);
        }

    }

    @FXML
    private void cbUnidadDeNegocioActionEvent(ActionEvent event) {

        if (!(cbUnidadDeNegocio.getSelectionModel().getSelectedIndex() == -1)) {

            String unidad = cbUnidadDeNegocio.getSelectionModel().getSelectedItem().getNombre();

            lbMensaje.setText(" LOS NCF SERAN DE  " + unidad.toUpperCase());

        } else {

            lbMensaje.setText("");
        }

    }

    @FXML
    private void cbTipoNcfActionEvent(ActionEvent event) {

        if (!(cbTipoNcf.getSelectionModel().getSelectedIndex() == -1)) {

            lbSerie.setText(cbTipoNcf.getSelectionModel().getSelectedItem().getSerie());
            lbTipo.setText(cbTipoNcf.getSelectionModel().getSelectedItem().getTipo());
        } else {
            lbSerie.setText("");
            lbTipo.setText("");
        }

    }
}
