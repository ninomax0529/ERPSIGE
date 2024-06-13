/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.banco.movimiento;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.contabilidad.registro.FXMLCatalogoConsController;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.banco.ManejoBanco;
import manejo.banco.ManejoMovimientoBanco;
import manejo.caja.ManejoTipoMovimieto;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.contabilidadd.PeriodoContableDao;
import manejo.documento.ManejoTipodocumento;
import manejo.inventario.entrada.ManejoEntradaInventario;
import modelo.Banco;
import modelo.Catalogo;
import modelo.CuentaBanco;
import modelo.DetalleEntradaDiario;
import modelo.EntradaDiario;
import modelo.MovimientoBanco;
import modelo.TipoConcepto;
import modelo.TipoMovimiento;
import modelo.TipoOperacion;
import modelo.TransferenciaBanco;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximiliano
 */
public class RegistroMovimientoBancoController implements Initializable {

    /**
     * @return the movimientoBanco
     */
    public MovimientoBanco getMovimientoBanco() {
        return movimientoBanco;
    }

    /**
     * @param movimientoBanco the movimientoBanco to set
     */
    public void setMovimientoBanco(MovimientoBanco movimientoBanco) {
        this.movimientoBanco = movimientoBanco;
    }

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
    private JFXDatePicker dpFecha;
    @FXML
    private JFXComboBox<Banco> cbBanco;

    @FXML
    private TextArea txtConcepto;
    @FXML
    private JFXComboBox<TipoMovimiento> cbTipoMovimiento;
    @FXML
    private RadioButton rbDeposito;
    @FXML
    private RadioButton rbRetiro;
    @FXML
    private JFXTextField txtValor;
    @FXML
    private JFXTextField txtNumeroOperacion;
    @FXML
    private JFXTextField txtReferencia;
    @FXML
    private Button btnGuardar;

    @FXML
    private JFXComboBox<CuentaBanco> cbCuentaBanco;

    @FXML
    private TextArea txtComentario;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private JFXButton btnCatalogoCons;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXTextField txtValorAContabilizar;
    @FXML
    private TableView<DetalleEntradaDiario> tblDetalleEnt;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbrCuenta;
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
    private TextField txtDiferencia;

    private MovimientoBanco movimientoBanco;
    TransferenciaBanco transferenciaBanco;
    ObservableList<DetalleEntradaDiario> listaDetalleEnt = FXCollections.observableArrayList();
    EntradaDiario entdiario;
    private boolean editarEntrada;

    DetalleEntradaDiario detalle;

    @FXML
    private RadioButton rbTransfencia;

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    private Catalogo catalogo;

    final ToggleGroup group = new ToggleGroup();

    ObservableList<TipoMovimiento> listaTipoMovimiento = FXCollections.observableArrayList();
    ObservableList<Banco> listaBanco = FXCollections.observableArrayList();
    ObservableList<TipoConcepto> listaTipoConcepto = FXCollections.observableArrayList();
    ObservableList<MovimientoBanco> listaMovimientoBanco = FXCollections.observableArrayList();

    ObservableList<CuentaBanco> listaCuentaBanco = FXCollections.observableArrayList();
    ObservableList<CuentaBanco> listaCuentaOrigen = FXCollections.observableArrayList();
    ObservableList<CuentaBanco> listaCuentaDestino = FXCollections.observableArrayList();
    ObservableList<TransferenciaBanco> listaTransferenciaBanco = FXCollections.observableArrayList();
    @FXML
    private Tab tabRegistrocontable;
//    private MovimientoBanco movimientoBanco;
    private boolean editar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablaEntradaDiario();
        inicializarCombox();
        rbDeposito.setToggleGroup(group);
        rbRetiro.setToggleGroup(group);
        rbTransfencia.setToggleGroup(group);

        transferenciaBanco = new TransferenciaBanco();

    }

    @FXML
    public void changeDebitoCellEnvent(TableColumn.CellEditEvent edittedCell) {

        DetalleEntradaDiario detalleSelected = tblDetalleEnt.getSelectionModel().getSelectedItem();
        detalleSelected.setDebito(Double.valueOf(edittedCell.getNewValue().toString()));
        txtTotalDebito.setText(getTotalDebito().toString());
        Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
        txtDiferencia.setText(diferencia.toString());

    }

    @FXML
    public void changeCreditoCellEnvent(TableColumn.CellEditEvent edittedCell) {

        DetalleEntradaDiario detalleSelected = tblDetalleEnt.getSelectionModel().getSelectedItem();
        detalleSelected.setCredito(Double.valueOf(edittedCell.getNewValue().toString()));
        txtTotalCredito.setText(getTotalCredito().toString());
        Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
        txtDiferencia.setText(diferencia.toString());

    }

    private void inicializarCombox() {

        listaBanco.addAll(ManejoBanco.getInstancia().getLista());

        cbTipoMovimiento.setConverter(new StringConverter<TipoMovimiento>() {

            @Override
            public String toString(TipoMovimiento tipoMovimiento) {
                return tipoMovimiento.getNombre();
            }

            @Override
            public TipoMovimiento fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbBanco.setConverter(new StringConverter<Banco>() {

            @Override
            public String toString(Banco banco) {
                return banco.getNombre();
            }

            @Override
            public Banco fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbCuentaBanco.setConverter(new StringConverter<CuentaBanco>() {

            @Override
            public String toString(CuentaBanco banco) {
                return banco.getNumeroCuenta();
            }

            @Override
            public CuentaBanco fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbBanco.setItems(listaBanco);

        listaTipoMovimiento.addAll(ManejoTipoMovimieto.getInstancia().getTipoMovimiento());
        cbTipoMovimiento.setItems(listaTipoMovimiento);
        cbCuentaBanco.setItems(listaCuentaBanco);

    }

    public void inicializarTablaEntradaDiario() {

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
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (isEditar() == false) {
                System.out.println("editando = " + isEditar());
                setMovimientoBanco(new MovimientoBanco());
                getMovimientoBanco().setFechaRegistro(new Date());
            } else {
                System.out.println("editando = " + isEditar());
            }

            if (dpFecha.getValue() == null) {
                ClaseUtil.mensaje("Tiene que seleccionar una fecha");
                return;
            }

            if (cbBanco.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje(" Tiene que seleccionar un  banco ");
                cbBanco.requestFocus();
                return;
            }

            if (cbCuentaBanco.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje(" Tiene que seleccionar una cuenta ");
                cbCuentaBanco.requestFocus();
                return;
            }

            if (cbTipoMovimiento.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje(" Tiene que seleccionar un tipo de movimiento ");
                cbTipoMovimiento.requestFocus();
                return;
            }

            if (rbDeposito.isSelected() == false && rbRetiro.isSelected() && rbTransfencia.isSelected()) {
                ClaseUtil.mensaje("Tiene que seleccionar una operacion  ");
                return;
            }

            if (txtConcepto.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar un concepto ");
                txtConcepto.requestFocus();
                return;
            }

            if (txtValor.getText().isEmpty()) {
                ClaseUtil.mensaje(" Tiene que digitar un monto ");
                txtValor.requestFocus();
                return;
            }

            System.out.println("cbBanco.getSelectionModel().getSelectedItem() " + cbBanco.getSelectionModel().getSelectedItem());
            getMovimientoBanco().setBanco(cbBanco.getSelectionModel().getSelectedItem());
            getMovimientoBanco().setConcepto(txtConcepto.getText());
            getMovimientoBanco().setTipoMovimiento(cbTipoMovimiento.getSelectionModel().getSelectedItem());

            getMovimientoBanco().setFechaOperacion(ClaseUtil.asDate(dpFecha.getValue()));

            getMovimientoBanco().setUsuario(VariablesGlobales.USUARIO);
            getMovimientoBanco().setNumeroReferencia(txtReferencia.getText());
            getMovimientoBanco().setNumeroOperacion(txtNumeroOperacion.getText());
            getMovimientoBanco().setNumeroCuenta(cbCuentaBanco.getSelectionModel().getSelectedItem().getNumeroCuenta());
            getMovimientoBanco().setCuenta(cbCuentaBanco.getSelectionModel().getSelectedItem());
            getMovimientoBanco().setCodigoOperacion(0);
            getMovimientoBanco().setBalanceActual(0);
            getMovimientoBanco().setBalanceAnterior(0);
            getMovimientoBanco().setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            getMovimientoBanco().setCodigoDocumento(ManejoTipodocumento.getInstancia().getTipoDocumento(25).getCodigo());

            if (rbRetiro.isSelected() == true) {

                getMovimientoBanco().setCredito(Double.parseDouble(txtValor.getText()));
                getMovimientoBanco().setMovimiento("Retiro");
                getMovimientoBanco().setTipoOperacion(new TipoOperacion(2));

            }

            if (rbDeposito.isSelected() == true) {

                getMovimientoBanco().setDebito(Double.parseDouble(txtValor.getText()));
                getMovimientoBanco().setMovimiento("Deposito");
                getMovimientoBanco().setTipoOperacion(new TipoOperacion(1));
            }

            if (rbTransfencia.isSelected() == true) {

                getMovimientoBanco().setCredito(Double.parseDouble(txtValor.getText()));
                getMovimientoBanco().setMovimiento("Transferencia");
                getMovimientoBanco().setTipoOperacion(new TipoOperacion(3));
            }

            if (cbTipoMovimiento.getSelectionModel().getSelectedItem().getTipoOperacion().getCodigo() == 1) {

                getMovimientoBanco().setDebito(Double.parseDouble(txtValor.getText()));
                getMovimientoBanco().setMovimiento("Deposito");
                getMovimientoBanco().setTipoOperacion(new TipoOperacion(1));

            }

            if (cbTipoMovimiento.getSelectionModel().getSelectedItem().getTipoOperacion().getCodigo() == 2) {

                getMovimientoBanco().setCredito(Double.parseDouble(txtValor.getText()));
                getMovimientoBanco().setMovimiento("Retiro");
                getMovimientoBanco().setTipoOperacion(new TipoOperacion(2));

            }

            if (tblDetalleEnt.getItems().size() <= 0) {

                ClaseUtil.mensaje("No hay Cuentas contable Agregada");

                return;
            }

            if (getTotalDebito() <= 0) {

                ClaseUtil.mensaje("El valor del debito y el credito no pueden ser igual o menor que cero");

                return;
            }

            if (txtValorAContabilizar.getText().isEmpty()) {

                txtValorAContabilizar.setText(txtValor.getText());
            }

            Double valor = Double.parseDouble(txtValorAContabilizar.getText());

            Date fecha = ClaseUtil.asDate(dpFecha.getValue());

            String estado = PeriodoContableDao.getInstancia().getEstadoPeriodoContable(fecha);

            if (!(Double.compare(getTotalDebito(), valor) == 0)) {

                ClaseUtil.mensaje("El valor del debito y el credito no pueden ser diferentes del valor de la factura");

                return;
            }

            if ((getTotalDebito() - getTotalCredito()) != 0) {

                ClaseUtil.mensaje("El debito y el credito tienen que ser iguales");

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
            Date fechaContable = ClaseUtil.asDate(dpFecha.getValue());
            if (isEditar()) {
                System.out.println("editando : " + getMovimientoBanco());
                setMovimientoBanco(ManejoMovimientoBanco.getInstancia().actualizar(getMovimientoBanco()));
            } else {
                System.out.println("guardando : " + getMovimientoBanco());
                setMovimientoBanco(ManejoMovimientoBanco.getInstancia().salvar(getMovimientoBanco()));
            }

            if (!(getMovimientoBanco()== null)) {

                 fechaContable = getMovimientoBanco().getFechaOperacion();
                if (listaDetalleEnt.size() > 0) {

                    System.out.println("editarEntrada : " + editarEntrada);
                    if (editarEntrada) {

                        EntradaDiarioDao.getInstancia()
                                .editarEntradaDiario(getMovimientoBanco().getCodigo(), getMovimientoBanco().getNumeroOperacion(),
                                        25, txtComentario.getText(), 13, listaDetalleEnt, fechaContable, entdiario);
                    } else {
                        EntradaDiarioDao.getInstancia().registrarEntradaDiario(getMovimientoBanco().getCodigo(), getMovimientoBanco().getNumeroOperacion(),
                                25, txtComentario.getText(), 13, listaDetalleEnt, fechaContable);
                    }

                }
            }

            listaMovimientoBanco.clear();

            listaMovimientoBanco.setAll(ManejoMovimientoBanco.getInstancia().getMovimientoBanco());
            limpiar();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void cbBancoActionEvent(ActionEvent event) {

        if (!(cbBanco.getSelectionModel().getSelectedIndex() == -1)) {

//            if (editar == false) {
            listaCuentaBanco.clear();
            Banco banco = cbBanco.getSelectionModel().getSelectedItem();
            listaCuentaBanco.addAll(ManejoBanco.getInstancia().getCuentaBanco(banco));

//            }
        }
    }

    @FXML
    private void rbDepositoActionEvent(ActionEvent event) {
        txtValor.requestFocus();

        agregarDetalleAsiento(1);
    }

    @FXML
    private void rbRetiroActionEvent(ActionEvent event) {

        txtValor.requestFocus();
        agregarDetalleAsiento(2);

    }

    @FXML
    private void rbTransfenciaActionEvent(ActionEvent event) {
        agregarDetalleAsiento(3);
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

    private void limpiar() {

        txtNumeroOperacion.clear();
        txtConcepto.clear();
        txtReferencia.clear();
        txtValor.clear();
        txtTotalCredito.clear();
        txtTotalDebito.clear();
        listaDetalleEnt.clear();
        txtComentario.clear();
        cbBanco.getSelectionModel().select(-1);
        cbCuentaBanco.getSelectionModel().select(-1);
//        cbTipoConcepto.getSelectionModel().select(-1);
        cbTipoMovimiento.getSelectionModel().select(-1);
        txtValorAContabilizar.clear();
        txtDescripcion.clear();
        editar = false;

    }

    @FXML
    private void cbTipoMovimientoActionEvent(ActionEvent event) {

        if (!(cbTipoMovimiento.getSelectionModel().getSelectedIndex() == -1)) {

            if (cbTipoMovimiento.getSelectionModel().getSelectedItem().getTipoOperacion().getCodigo() == 1) {

                rbDeposito.setSelected(true);

            } else {

                rbRetiro.setSelected(true);
            }
        }
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

        if (!(catalogoController.getCatalogo() == null)) {

            setCatalogo(catalogoController.getCatalogo());
            txtDescripcion.setText(getCatalogo().getDescripcion());
            agregar();

        }

    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {
        agregar();
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

    private void agregarDetalleAsiento(int operacion) {

        if (getCatalogo() == null) {

            return;
        }

        Double valor = Double.parseDouble(txtValor.getText().isEmpty() ? "0" : txtValor.getText());
        detalle = new DetalleEntradaDiario();

        detalle.setCatalogo(getCatalogo());
        detalle.setCuenta(getCatalogo().getCuenta());
        detalle.setDescripcionCuenta(getCatalogo().getDescripcion());
        switch (operacion) {
            case 1://es deposito
                detalle.setCredito(0.00);
                detalle.setDebito(valor);
                break;
            case 2://es retiro
                detalle.setCredito(valor);
                detalle.setDebito(0.00);
                break;
            case 3://es tranferencia
                detalle.setCredito(valor);
                detalle.setDebito(0.00);
                break;
            default:
                break;
        }

        listaDetalleEnt.add(detalle);

        txtTotalDebito.setText(getTotalDebito().toString());
        txtTotalCredito.setText(getTotalCredito().toString());
        Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
        txtDiferencia.setText(diferencia.toString());

        setCatalogo(null);

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
    private void tabRegistrocontableActionEvent(Event event) {

        txtValorAContabilizar.setText(txtValor.getText());
    }

    @FXML
    private void cbCuentaBancoActionEvent(ActionEvent event) {

        if (!(cbCuentaBanco.getSelectionModel().getSelectedIndex() == -1)) {

            setCatalogo(cbCuentaBanco.getSelectionModel().getSelectedItem().getCatalogo());

            txtValor.requestFocus();
        } else {
            System.out.println(" no tiene cuenta ");
        }
    }

    public void llenarCampo() {

        txtConcepto.setText(getMovimientoBanco().getConcepto());
        cbBanco.getSelectionModel().select(getMovimientoBanco().getBanco());
        listaCuentaBanco.add(getMovimientoBanco().getCuenta());
        cbCuentaBanco.getSelectionModel().select(getMovimientoBanco().getCuenta());
        cbTipoMovimiento.getSelectionModel().select(getMovimientoBanco().getTipoMovimiento());
        txtNumeroOperacion.setText(getMovimientoBanco().getNumeroOperacion());
        txtReferencia.setText(getMovimientoBanco().getNumeroReferencia());
        dpFecha.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getMovimientoBanco().getFechaOperacion()));

        listaDetalleEnt.clear();
        entdiario = EntradaDiarioDao.getInstancia().getEntradaDiarioPorDocumento(getMovimientoBanco().getCodigo(), 25);
  
        if (!(entdiario == null)) {
            
            listaDetalleEnt.addAll(EntradaDiarioDao.getInstancia().getDetalleEntradaDiario(entdiario));
            txtTotalCredito.setText(getTotalCredito().toString());
            txtTotalDebito.setText(getTotalDebito().toString());
            Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
            txtDiferencia.setText(diferencia.toString());

            editarEntrada = true;
        } else {
            editarEntrada = false;
        }
        if (getMovimientoBanco().getCredito() > 0) {
            txtValor.setText(Double.toString(getMovimientoBanco().getCredito()));

        } else if (getMovimientoBanco().getDebito() > 0) {
            txtValor.setText(Double.toString(getMovimientoBanco().getDebito()));
        }

        switch (getMovimientoBanco().getTipoOperacion().getCodigo()) {
            case 1:
                rbDeposito.setSelected(true);
                break;
            case 2:
                rbRetiro.setSelected(true);
                break;
            case 3:
                rbTransfencia.setSelected(true);
                break;
        }

        entdiario = EntradaDiarioDao.getInstancia().getEntradaDiarioPorDocumento(getMovimientoBanco().getCodigo(), 25);
        txtComentario.setText(entdiario == null ? "" : entdiario.getConcepto());
        listaDetalleEnt.clear();

        if (!(entdiario == null)) {
            listaDetalleEnt.addAll(EntradaDiarioDao.getInstancia().getDetalleEntradaDiario(entdiario));
        }

        txtTotalDebito.setText(getTotalDebito().toString());
        txtTotalCredito.setText(getTotalCredito().toString());
        Double diferencia = ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);
        txtDiferencia.setText(diferencia.toString());

    }
}
