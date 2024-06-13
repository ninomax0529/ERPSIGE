/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.proceso;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import controlador.contabilidad.registro.FXMLCatalogoConsController;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.contabilidadd.ModuloDao;
import manejo.contabilidadd.PeriodoContableDao;
import manejo.contabilidadd.TipoAsientoDao;
import manejo.contabilidadd.TipoEntradaDao;
import modelo.Catalogo;
import modelo.DetalleEntradaDiario;
import modelo.EntradaDiario;
import modelo.Modulo;
import modelo.TipoAsiento;
import modelo.TipoDocumento;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author luis
 */
public class FXMLEntradaDiarioControllerAnt implements Initializable {

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
    private Button btnAgregar;
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
    private boolean editar = false;

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    ObservableList<DetalleEntradaDiario> listaDetalleEnt = FXCollections.observableArrayList();

    ObservableList<Modulo> listaModulo = FXCollections.observableArrayList();
    ObservableList<TipoAsiento> listaTipoAsiento = FXCollections.observableArrayList();

    @FXML
    private Button btnGuardar;

    @FXML
    private JFXComboBox<TipoAsiento> cbTipoAsiento;
    DetalleEntradaDiario detalle;
    @FXML
    private TextField txtDiferencia;
    EntradaDiario entdiario;

    public EntradaDiario getEntdiario() {
        return entdiario;
    }

    public void setEntdiario(EntradaDiario entdiario) {
        this.entdiario = entdiario;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTabla();
        inicializarCombox();
        dpFecha.setValue(LocalDate.now());
        txtDescripcion.setEditable(false);

    }

    private void inicializarCombox() {

        listaModulo.addAll(ModuloDao.getInstancia().getModulo());

        listaTipoAsiento.addAll(TipoAsientoDao.getInstancia().getTipoAsiento());

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

        cbTipoAsiento.setItems(listaTipoAsiento);
        cbModulo.setItems(listaModulo);

    }

    @FXML
    private void btnCatalogoConsActionEvent(ActionEvent event) throws IOException {

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
        txtDescripcion.setText(getCatalogo().getDescripcion());
        agregar();

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

    }

    @FXML
    public void changeCreditoCellEnvent(CellEditEvent edittedCell) {

        DetalleEntradaDiario detalleSelected = tblDetalleEnt.getSelectionModel().getSelectedItem();
        detalleSelected.setCredito(Double.valueOf(edittedCell.getNewValue().toString()));
        txtTotalCredito.setText(getTotalCredito().toString());
        Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
        txtDiferencia.setText(diferencia.toString());

    }

    @FXML
    public void changeDebitoCellEnvent(CellEditEvent edittedCell) {

        DetalleEntradaDiario detalleSelected = tblDetalleEnt.getSelectionModel().getSelectedItem();
        detalleSelected.setDebito(Double.valueOf(edittedCell.getNewValue().toString()));
        txtTotalDebito.setText(getTotalDebito().toString());
        Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
        txtDiferencia.setText(diferencia.toString());

    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {

        agregar();

    }

    @FXML
    private void tbDetalleEntActionEvent(KeyEvent event) throws IOException {

//        System.out.println(event.getCode());
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("ENTROOOOO!!!");
            txtTotalCredito.setText(getTotalCredito().toString());
//            listaDetalleEnt.clear();
//            listaDetalleEnt.addAll(tblDetalleEnt.getItems());
        }
    }

    public Double getTotalCredito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detalle = tblDetalleEnt.getItems();

        for (int i = 0; i < detalle.size(); i++) {
            System.out.println("Size: " + detalle.size());
            total += detalle.get(i).getCredito();
            System.out.println("TDeB: " + total);
        }

        return ClaseUtil.roundDouble(total, 4);

    }

    public Double getTotalDebito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detalle = tblDetalleEnt.getItems();

        for (int i = 0; i < detalle.size(); i++) {
            total += detalle.get(i).getDebito();
        }

        return ClaseUtil.roundDouble(total, 4);

    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        tblDetalleEnt.setEditable(true);
        int selectedIndex = tblDetalleEnt.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tblDetalleEnt.getItems().remove(selectedIndex);
        }

        txtTotalDebito.setText(getTotalDebito().toString());
        txtTotalCredito.setText(getTotalCredito().toString());

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        Date fecha = ClaseUtil.asDate(dpFecha.getValue());
        String estado = PeriodoContableDao.getInstancia().getEstadoPeriodoContable(fecha);
        if ((getTotalDebito() - getTotalCredito()) != 0) {

            ClaseUtil.mensaje("El debito y el credito tienen que ser iguales");

            return;
        }

        if (cbModulo.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccional un tipo de entrada");
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

        entdiario = new EntradaDiario();

        entdiario.setConcepto(txtComentario.getText());
        entdiario.setNumeroDocumento(txtDocumento.getText());
        entdiario.setTipoDocumento(new TipoDocumento(6));
        entdiario.setDocumento(0);
        entdiario.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
        entdiario.setFechaRegistro(new Date());
        entdiario.setModulo(cbModulo.getSelectionModel().getSelectedItem());
        entdiario.setTipoAsiento(cbTipoAsiento.getSelectionModel().getSelectedItem());
        entdiario.setTipoEntrada(TipoEntradaDao.getInstancia().getTipoEntrada(1));
        entdiario.setUsuario(1);
        entdiario.setAnulada(false);

        for (int i = 0; i < listaDetalleEnt.size(); i++) {
            listaDetalleEnt.get(i).setEntradaDiario(entdiario);
        }
        entdiario.setDetalleEntradaDiarioCollection(listaDetalleEnt);

        EntradaDiarioDao.getInstancia().salvar(entdiario);

        System.out.println("Guardado!");
        limpiar();

    }

    public void limpiar() {

        txtComentario.setText("");
        txtTotalCredito.setText("");
        txtTotalDebito.setText("");
        listaDetalleEnt.clear();
        txtDocumento.clear();
        txtDescripcion.clear();
    }

    private void agregar() {

        if (getCatalogo() == null) {

            ClaseUtil.mensaje("NO HAY CUENTA SELECCIONADA");
            return;
        }

        detalle = new DetalleEntradaDiario();

        detalle.setCatalogo(getCatalogo());
        detalle.setCuenta(getCatalogo().getCuenta());
        detalle.setDescripcionCuenta(getCatalogo().getDescripcion());
        detalle.setCredito(0.00);
        detalle.setDebito(0.00);

        listaDetalleEnt.add(detalle);
//        tblDetalleEnt.setItems(listaDetalleEnt);
//        txtDescripcion.setText("");
        setCatalogo(null);

    }
    
      public void llenarCampo() {

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
//        cbTipoDocumento.setValue(getEntdiario().getTipoDocumento());

        if (!(getEntdiario() == null)) {

            System.out.println("No esta nula ");
//                if (!(getEntdiario().getConfiguracionCuentaContable() == null)) {
//
//                    System.out.println("Configuracion no esta nula ");
//                    configuracionCuentaContable = getEntdiario().getConfiguracionCuentaContable();
////                    cbTipoconcepto.getSelectionModel().select(configuracionCuentaContable.getTipoConcepto());
//
//                }
////                else {
//                    System.out.println("Configuracion  esta nula ");
//                    configuracionCuentaContable = new ConfiguracionCuentaContable();
//                }

        } else {
            System.out.println("Esta nula ");
        }

    }

}
