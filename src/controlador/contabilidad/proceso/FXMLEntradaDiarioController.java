/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.proceso;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import controlador.contabilidad.consulta.ConsultaConfigCuentaContableController;
import controlador.contabilidad.registro.FXMLCatalogoConsController;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.contabilidadd.ConfiguracionCuentaContableDao;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.contabilidadd.ModuloDao;
import manejo.contabilidadd.PeriodoContableDao;
import manejo.contabilidadd.TipoAsientoDao;
import manejo.contabilidadd.TipoEntradaDao;
import manejo.documento.ManejoTipodocumento;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.Catalogo;
import modelo.ConfiguracionCuentaContable;
import modelo.DetalleEntradaDiario;
import modelo.EntradaDiario;
import modelo.Modulo;
import modelo.SecuenciaDocumento;
import modelo.TipoAsiento;
import modelo.TipoDocumento;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author luis
 */
public class FXMLEntradaDiarioController implements Initializable {

    @FXML
    private HBox hbConfigCuenta;
    @FXML
    private Label lbConfigCuenta;
    @FXML
    private JFXButton btnReemplazar;
    @FXML
    private JFXButton btnNuevo;

    /**
     * @return the ConfigContable
     */
    public ConfiguracionCuentaContable getConfigContable() {
        return ConfigContable;
    }

    /**
     * @param ConfigContable the ConfigContable to set
     */
    public void setConfigContable(ConfiguracionCuentaContable ConfigContable) {
        this.ConfigContable = ConfigContable;
    }

    @FXML
    private JFXButton btnConfigCuenta;

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

    @FXML
    private Button btnCatalogoCons;

    private Catalogo catalogo;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtDocumento;

    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXComboBox<Modulo> cbModulo;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView<DetalleEntradaDiario> tblDetalleEnt;
    @FXML
    private TableColumn<DetalleEntradaDiario, String> tbrCuenta;
    @FXML
    private TableColumn<DetalleEntradaDiario, String> tbrDescripcion;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbrDebito;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbrCredito;
    @FXML
    private TextField txtTotalDebito;
    @FXML
    private TextField txtTotalCredito;
    @FXML
    private TextArea txtComentario;

    ObservableList<DetalleEntradaDiario> listaDetalleEnt = FXCollections.observableArrayList();

    ObservableList<Modulo> listaModulo = FXCollections.observableArrayList();
    ObservableList<TipoAsiento> listaTipoAsiento = FXCollections.observableArrayList();
    ObservableList<TipoDocumento> listaTipoDocumento = FXCollections.observableArrayList();

    @FXML
    private Button btnGuardar;

    @FXML
    private JFXComboBox<TipoAsiento> cbTipoAsiento;
    DetalleEntradaDiario detalle;
    @FXML
    private TextField txtDiferencia;
    EntradaDiario entdiario;
    @FXML
    private JFXComboBox<TipoDocumento> cbTipoDocumento;

    private boolean editar = false;
    private boolean editarDet = false;

    public EntradaDiario getEntdiario() {
        return entdiario;
    }

    public void setEntdiario(EntradaDiario entdiario) {
        this.entdiario = entdiario;
    }

    private ConfiguracionCuentaContable ConfigContable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTabla();
        inicializarCombox();
        dpFecha.setValue(LocalDate.now());
        txtDescripcion.setEditable(false);
        hbConfigCuenta.setVisible(false);
        nuevo();
        limpiar();

    }

    private void inicializarCombox() {

        listaModulo.addAll(ModuloDao.getInstancia().getModulo());

        listaTipoAsiento.addAll(TipoAsientoDao.getInstancia().getTipoAsiento());
        listaTipoDocumento.addAll(ManejoTipodocumento.getInstancia().getLista());

        cbModulo.setConverter(new StringConverter<Modulo>() {

            @Override
            public String toString(Modulo modulo) {
                return modulo.getNombre();
            }

            @Override
            public Modulo fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoAsiento.setConverter(new StringConverter<TipoAsiento>() {

            @Override
            public String toString(TipoAsiento tipoAsiento) {
                return tipoAsiento.getDescripcion();
            }

            @Override
            public TipoAsiento fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        cbTipoDocumento.setConverter(new StringConverter<TipoDocumento>() {

            @Override
            public String toString(TipoDocumento tipoDocumento) {
                return tipoDocumento.getNombre();
            }

            @Override
            public TipoDocumento fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoAsiento.setItems(listaTipoAsiento);
        cbTipoDocumento.setItems(listaTipoDocumento);
        cbModulo.setItems(listaModulo);

    }

    @FXML
    private void btnCatalogoConsActionEvent(ActionEvent event) throws IOException {

        try {

            buscarCuenta();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTabla() {

        tbrCuenta.setCellValueFactory(new PropertyValueFactory<>("Cuenta"));
        tbrDescripcion.setCellValueFactory(new PropertyValueFactory<>("DescripcionCuenta"));
        tbrDebito.setCellValueFactory(new PropertyValueFactory<>("Debito"));
        tbrCredito.setCellValueFactory(new PropertyValueFactory<>("Credito"));

        tblDetalleEnt.setEditable(true);
        tblDetalleEnt.setItems(listaDetalleEnt);
//          tbrCredito.setCellFactory(TextFieldTableCell.forTableColumn());
        tbrDebito.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbrCredito.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbrDebito.setOnEditCommit(data -> {

            try {

                DetalleEntradaDiario p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setDebito(data.getNewValue());
                    txtTotalDebito.setText(getTotalDebito().toString());
                    Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
                    txtDiferencia.setText(diferencia.toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tblDetalleEnt.refresh();
                    tblDetalleEnt.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbrCredito.setOnEditCommit(data -> {

            try {

                DetalleEntradaDiario p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setCredito(data.getNewValue());
                    txtTotalCredito.setText(getTotalCredito().toString());
                    Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
                    txtDiferencia.setText(diferencia.toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tblDetalleEnt.refresh();
                    tblDetalleEnt.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }
//
//    public void changeCreditoCellEnvent(CellEditEvent edittedCell) {
//
//        DetalleEntradaDiario detalleSelected = tblDetalleEnt.getSelectionModel().getSelectedItem();
//        detalleSelected.setCredito(Double.valueOf(edittedCell.getNewValue().toString()));
//        txtTotalCredito.setText(getTotalCredito().toString());
//        Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
//        txtDiferencia.setText(diferencia.toString());
//
//    }
//
//    public void changeDebitoCellEnvent(CellEditEvent edittedCell) {
//
//        DetalleEntradaDiario detalleSelected = tblDetalleEnt.getSelectionModel().getSelectedItem();
//        detalleSelected.setDebito(Double.valueOf(edittedCell.getNewValue().toString()));
//        txtTotalDebito.setText(getTotalDebito().toString());
//        Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
//        txtDiferencia.setText(diferencia.toString());
//
//    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Double getTotalCredito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> listaDetalle = tblDetalleEnt.getItems();

        for (int i = 0; i < listaDetalle.size(); i++) {
            System.out.println("Size: " + listaDetalle.size());
            total += listaDetalle.get(i).getCredito();
            System.out.println("TDeB: " + total);
        }

        return ClaseUtil.roundDouble(total, 4);

    }

    public Double getTotalDebito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> listaDetalleLocal = tblDetalleEnt.getItems();

        for (int i = 0; i < listaDetalleLocal.size(); i++) {
            total += listaDetalleLocal.get(i).getDebito();
        }

        return ClaseUtil.roundDouble(total, 4);

    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        tblDetalleEnt.setEditable(true);
        int selectedIndex = tblDetalleEnt.getSelectionModel().getSelectedIndex();
        editarDet = false;
        if (selectedIndex >= 0) {

            detalle = tblDetalleEnt.getSelectionModel().getSelectedItem();
            listaDetalleEnt.remove(detalle);
            tblDetalleEnt.refresh();

        }

        txtTotalDebito.setText(getTotalDebito().toString());
        txtTotalCredito.setText(getTotalCredito().toString());

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        Date fecha = ClaseUtil.asDate(dpFecha.getValue());
        String estado = PeriodoContableDao.getInstancia().getEstadoPeriodoContable(fecha);

        try {

            if ((getTotalDebito() - getTotalCredito()) != 0) {

                ClaseUtil.mensaje(" El debito y el credito tienen que ser iguales");

                return;
            }

            if (cbModulo.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar un tipo de entrada");
                cbModulo.requestFocus();
                return;
            }

            if (cbTipoAsiento.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que selecciona un tipo de asiento");
                cbTipoAsiento.requestFocus();
                return;
            }

            if (cbTipoDocumento.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que selecciona un tipo de documento");
                cbTipoDocumento.requestFocus();
                return;
            }

            if (txtDocumento.getText().isEmpty()) {

                ClaseUtil.mensaje("El numero de documento esta vacio");
                txtDocumento.requestFocus();
                return;
            }

            if (estado.equals("CERRADO")) {

                ClaseUtil.mensaje("El Periodo Contable ya Esta Cerrado en esta  Fecha : " + new SimpleDateFormat("yyyy-MM-dd").format(fecha));
                return;
            }

            if (txtComentario.getText().isEmpty()) {

                ClaseUtil.mensaje("El Concepto no puede estar vacio");
                txtComentario.requestFocus();
                return;
            }

            if (!isEditar()) {

//                entdiario = new EntradaDiario();
                entdiario.setUnidadDeNegocio(utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio());
                asignarSecuenciaDoc();
            }

            entdiario.setConcepto(txtComentario.getText());
            entdiario.setNumeroDocumento(txtDocumento.getText());
            entdiario.setTipoDocumento(cbTipoDocumento.getSelectionModel().getSelectedItem());
            entdiario.setDocumento(0);
            entdiario.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            entdiario.setFechaRegistro(new Date());
            entdiario.setModulo(cbModulo.getSelectionModel().getSelectedItem());
            entdiario.setTipoAsiento(cbTipoAsiento.getSelectionModel().getSelectedItem());
            entdiario.setTipoEntrada(TipoEntradaDao.getInstancia().getTipoEntrada(1));
            entdiario.setUsuario(utiles.VariablesGlobales.USUARIO.getCodigo());
            entdiario.setAnulada(false);
            entdiario.setMonto(getTotalDebito().toString());

            System.out.println("entdiario  : " + entdiario);
//            for (DetalleEntradaDiario ded : listaDetalleEnt) {
//
//                ded.setEntradaDiario(entdiario);
//            }

//            entdiario.setDetalleEntradaDiarioCollection(listaDetalleEnt);
            if (isEditar()) {

                entdiario.getDetalleEntradaDiarioCollection().clear();//para recibir la  lista nueva
                entdiario.getDetalleEntradaDiarioCollection().addAll(listaDetalleEnt);

                System.out.println("actualizando .. " + entdiario);
                entdiario = EntradaDiarioDao.getInstancia().actualizar(entdiario);
            } else {

                entdiario.setDetalleEntradaDiarioCollection(listaDetalleEnt);
                System.out.println("guardando  .. " + entdiario);
                entdiario = EntradaDiarioDao.getInstancia().salvar(entdiario);
            }

            if (editar) {
                ClaseUtil.mensaje(" Asiento actualizado exitosamente ");
            } else {

                nuevo();
                limpiar();
                ClaseUtil.mensaje(" Registro Guardado exitosamente ");
            }

            System.out.println("Guardado! " + entdiario.getCodigo());

        } catch (Exception e) {
            ClaseUtil.mensaje(" Hubo un error durante la transacion ");
            e.printStackTrace();
        }

    }

    public void limpiar() {

        txtComentario.setText("");
        txtTotalCredito.setText("");
        txtTotalDebito.setText("");
        listaDetalleEnt.clear();
        txtDocumento.clear();
        txtDescripcion.clear();
        cbTipoAsiento.getSelectionModel().select(1);
        cbModulo.setValue(null);
        cbTipoDocumento.setValue(null);

    }

    private void agregar() {

        if (getCatalogo() == null) {

            ClaseUtil.mensaje("NO HAY CUENTA SELECCIONADA");
            return;
        }

        if (editarDet == false) {
            detalle = new DetalleEntradaDiario();
            detalle.setCredito(0.00);
            detalle.setDebito(0.00);
        } else {
            detalle.setCredito(detalle.getCredito());
            detalle.setDebito(detalle.getDebito());
            listaDetalleEnt.remove(detalle);
            tblDetalleEnt.refresh();
        }

        detalle.setCatalogo(getCatalogo());
        detalle.setCuenta(getCatalogo().getCuenta());
        detalle.setDescripcionCuenta(getCatalogo().getDescripcion());

        detalle.setCuentaControl(catalogo.getCuentaControl());
        detalle.setEntradaDiario(entdiario);

        listaDetalleEnt.add(detalle);
        editarDet = false;
        setCatalogo(null);

    }

    public void llenarCampo() {

        System.out.println("getEntdiario()  " + getEntdiario());

        if (!(getEntdiario() == null)) {
            listaDetalleEnt.clear();

            listaDetalleEnt.addAll(EntradaDiarioDao.getInstancia()
                    .getDetalleEntradaDiario(getEntdiario()));

            txtTotalDebito.setText(getTotalDebito().toString());
            txtTotalCredito.setText(getTotalCredito().toString());
            Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
            txtDiferencia.setText(diferencia.toString());
            txtDocumento.setText(getEntdiario().getNumeroDocumento());
            txtComentario.setText(getEntdiario().getConcepto());
            cbModulo.setValue(getEntdiario().getModulo());
            cbTipoAsiento.setValue(getEntdiario().getTipoAsiento());
            cbTipoDocumento.setValue(getEntdiario().getTipoDocumento());
            dpFecha.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getEntdiario().getFecha()));

            System.out.println("No esta nula ");

        } else {
            System.out.println("Esta nula ");
        }

    }

    @FXML
    private void cbTipoDocumentoActionEvent(ActionEvent event) {

        txtDocumento.requestFocus();
    }

    @FXML
    private void cbModuloActionevent(ActionEvent event) {

        //Para implementaciones nueva
//        if (!(cbModulo.getSelectionModel().getSelectedIndex() == -1)) {
//            int codMod = cbModulo.getSelectionModel().getSelectedItem().getCodigo();
//            listaTipoDocumento.clear();
//            listaTipoDocumento.addAll(ManejoTipodocumento.getInstancia()
//                    .getTipoDocPorModulo(codMod));
//        }
    }

    private void asignarSecuenciaDoc() {

        SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(entdiario.getUnidadDeNegocio().getCodigo(), 6);

        if (!(secDoc == null)) {

            boolean existe = EntradaDiarioDao.getInstancia().existeSecuencia(secDoc.getNumero());

            if (existe) {

                System.out.println("existe " + secDoc.getNumero());

                while (EntradaDiarioDao.getInstancia().existeSecuencia(secDoc.getNumero())) {

                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

                secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(entdiario.getUnidadDeNegocio().getCodigo(), 6);

                entdiario.setNumero(secDoc.getNumero());
                entdiario.setSecuenciaDocumento(secDoc);

            } else {

                System.out.println("No existe " + secDoc.getNumero());
                entdiario.setNumero(secDoc.getNumero());
                entdiario.setSecuenciaDocumento(secDoc);
                secDoc.setNumero(secDoc.getNumero() + 1);
                ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

            }

        }

    }

    @FXML
    private void btnConfigCuentaActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/contabilidad/consulta/ConsultaConfgnCuentaContable.fxml"));

        loader.load();

        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        System.out.println("ESPERANDOOO!!!");

        ConsultaConfigCuentaContableController catalogoController = loader.getController();

        setConfigContable(catalogoController.getConfig());

        if (!(getConfigContable() == null)) {

            hbConfigCuenta.setVisible(true);
            lbConfigCuenta.setText(getConfigContable().getDescripcion());

            listaDetalleEnt.clear();
            listaDetalleEnt.addAll(ConfiguracionCuentaContableDao.getInstancia()
                    .generarConfiguracionCuenta(getConfigContable()));

        } else {
            hbConfigCuenta.setVisible(false);
        }
    }

    private void nuevo() {
        entdiario = new EntradaDiario();
        editar = false;
        editarDet = false;
    }

    @FXML
    private void btnReemplazarActionevent(ActionEvent event) throws IOException {

        try {

            tblDetalleEnt.setEditable(true);
            int selectedIndex = tblDetalleEnt.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {

                detalle = tblDetalleEnt.getSelectionModel().getSelectedItem();
                editarDet = true;
                buscarCuenta();

            } else {
                editarDet = false;
            }

        } catch (IOException ex) {
            Logger.getLogger(FXMLEntradaDiarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void buscarCuenta() throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/contabilidad/consulta/FXMLCatalogoCons.fxml"));

        loader.load();

        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        System.out.println("ESPERANDOOO!!!");

        FXMLCatalogoConsController catalogoController = loader.getController();

        setCatalogo(catalogoController.getCatalogo());

        if (!(getCatalogo() == null)) {

            txtDescripcion.setText(getCatalogo().getDescripcion());
            agregar();

        }

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {

        nuevo();
        limpiar();
    }

}
