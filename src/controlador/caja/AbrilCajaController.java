/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.caja;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import manejo.caja.ManejoCaja;
import manejo.caja.ManejoCajaChica;
import manejo.caja.ManejoTipoMovimieto;
import manejo.caja.ManejoTurno;
import manejo.documento.ManejoTipodocumento;
import modelo.Caja;
import modelo.CajaChica;
import modelo.DetalleCajaChica;
import modelo.Turno;
import modelo.Usuario;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class AbrilCajaController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXTextField txtSaldoInicial;
    @FXML
    private JFXComboBox<Caja> cbCaja;
    @FXML
    private JFXComboBox<Turno> cbTurno;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private TableView<CajaChica> tbAperturaCajaChica;
    @FXML
    private TableColumn<CajaChica, String> tbcNumero;
    @FXML
    private TableColumn<CajaChica, Double> tbcSaldoInicial;
    @FXML
    private TableColumn<CajaChica, Date> tbcFecha;
    @FXML
    private TableColumn<CajaChica, String> tbcCaja;
    @FXML
    private TableColumn<CajaChica, String> tbcTurno;
    @FXML
    private TableColumn<CajaChica, String> tbcEstado;
    @FXML
    private TableColumn<CajaChica, String> tbcCajero;

    ObservableList<CajaChica> listaCajachica = FXCollections.observableArrayList();
    ObservableList<Caja> listaCaja = FXCollections.observableArrayList();
    ObservableList<Turno> listaTurno = FXCollections.observableArrayList();

    CajaChica cajaChica;
    @FXML
    private JFXTextField txtFiltro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
        inicializarCombox();
        iniciazarFiltro();
        nuevo();
    }

    private void iniciazarFiltro() {

        FilteredList<CajaChica> filteredData = new FilteredList<>(tbAperturaCajaChica.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getFechaApertura() != null && filtro.getFechaApertura().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getCodigo() != null && filtro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.

                } else if (filtro.getTurno() != null && filtro.getNombreTurno().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filtro.getCaja() != null && filtro.getCaja().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filtro.getCaja() != null && filtro.getCajero().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<CajaChica> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbAperturaCajaChica.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbAperturaCajaChica.setItems(sortedData);
    }

    private void inicializarCombox() {

        cbCaja.setConverter(new StringConverter<Caja>() {

            @Override
            public String toString(Caja caja) {
                return String.valueOf(caja.getNombre());
            }

            @Override
            public Caja fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTurno.setConverter(new StringConverter<Turno>() {

            @Override
            public String toString(Turno turno) {
                return String.valueOf(turno.getNombre());
            }

            @Override
            public Turno fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaCaja.addAll(ManejoCaja.getInstancia().getListaCaja());
        listaTurno.addAll(ManejoTurno.getInstancia().getListaTurno());

        cbCaja.setItems(listaCaja);
        cbTurno.setItems(listaTurno);

    }

    public void inicializarTabla() {

        listaCajachica.addAll(ManejoCajaChica.getInstancia().getListaCajaChica());
        tbAperturaCajaChica.setItems(listaCajachica);

        tbcNumero.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcCaja.setCellValueFactory(new PropertyValueFactory<>("caja"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fechaApertura"));
        tbcSaldoInicial.setCellValueFactory(new PropertyValueFactory<>("saldoInicial"));
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tbcTurno.setCellValueFactory(new PropertyValueFactory<>("nombreTurno"));
        tbcCajero.setCellValueFactory(new PropertyValueFactory<>("cajero"));

        tbcCaja.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getCaja().getNombre());
                    }
                    return property;
                });

        tbcCajero.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getCajero().getNombre());
                    }
                    return property;
                });

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            DetalleCajaChica detalleCajaChica = new DetalleCajaChica();
            List<DetalleCajaChica> lista = new ArrayList<>();

            if (cbCaja.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar una caja");
                return;
            }

            if (cbTurno.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar un turno");
                return;
            }

            if (txtSaldoInicial.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar un saldo inicial");
                txtSaldoInicial.requestFocus();
                return;
            }

            Date fecha = ClaseUtil.asDate(dpFecha.getValue());

            CajaChica conCajaChica = ManejoCajaChica.getInstancia().getCajaChica(fecha, "abierta");

            if (!(conCajaChica == null)) {

                ClaseUtil.mensaje("Hay una caja abierta en esta fecha " + conCajaChica.getFechaApertura());
                txtSaldoInicial.requestFocus();
                return;
            }

            cajaChica.setCaja(cbCaja.getSelectionModel().getSelectedItem());
            cajaChica.setCajero(new Usuario(1));
            cajaChica.setFechaRegistro(new Date());
            cajaChica.setFechaApertura(fecha);
            cajaChica.setSaldoInicial((Double.parseDouble(txtSaldoInicial.getText())));
            cajaChica.setSaldoFinal(Double.parseDouble(txtSaldoInicial.getText()));
            cajaChica.setIngresosNeto(0.00);
            cajaChica.setEstado("abierta");
            cajaChica.setHoraApertura(new Date());
            cajaChica.setTurno(cbTurno.getSelectionModel().getSelectedItem().getCodigo());
            cajaChica.setNombreTurno(cbTurno.getSelectionModel().getSelectedItem().getNombre());
            cajaChica.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            detalleCajaChica.setCajaChica(cajaChica);
            detalleCajaChica.setTipoMovimiento(ManejoTipoMovimieto.getInstancia()
                    .getTipoMovimiento(9));

            String numDocumento = new SimpleDateFormat("ddmmss").format(new Date());

            detalleCajaChica.setConcepto("Apertura de caja chica");
            detalleCajaChica.setTipoDocumento(ManejoTipodocumento.getInstancia().getTipoDocumento(10));
            detalleCajaChica.setDocumento(numDocumento.trim());

            detalleCajaChica.setMonto(Double.parseDouble(txtSaldoInicial.getText()));
            detalleCajaChica.setNombreMovimiento("ingreso");
            detalleCajaChica.setIngresosNeto(0.00);

            lista.add(detalleCajaChica);
            cajaChica.setDetalleCajaChicaCollection(lista);
            ManejoCajaChica.getInstancia().salvar(cajaChica);
            listaCajachica.clear();
            listaCajachica.addAll(ManejoCajaChica.getInstancia().getListaCajaChica());

            nuevo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void nuevo() {

        cajaChica = new CajaChica();
        dpFecha.setValue(LocalDate.now());
        limpiar();
    }

    private void limpiar() {

        txtSaldoInicial.clear();
        cbCaja.getSelectionModel().clearSelection();
    }
}
