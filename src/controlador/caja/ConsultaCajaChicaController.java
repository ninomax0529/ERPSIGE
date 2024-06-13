/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.caja;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import manejo.ManejoConfiguracion;
import manejo.ReciboIngreso.ManejoReciboIngreso;
import manejo.caja.ManejoCajaChica;
import manejo.factura.ManejoFactura;
import modelo.Caja;
import modelo.CajaChica;
import modelo.DetalleCajaChica;
import reporte.cajaChica.RptCajaChica;
import reporte.cxc.RptReciboIngreso;
import reporte.factura.RptFactura;
import util.ClaseUtil;
import util.FormatNum;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ConsultaCajaChicaController implements Initializable {

    @FXML
    private JFXTextField txtSaldoInicial;
    @FXML
    private TableColumn<CajaChica, String> tbcNumero;
    @FXML
    private TableColumn<CajaChica, Double> tbcSaldoInicial;
    @FXML
    private TableColumn<CajaChica, Date> tbcFecha;

    @FXML
    private TableColumn<CajaChica, String> tbcCaja;
    @FXML
    private TableColumn<CajaChica, String> tbcEstado;
    @FXML
    private TableColumn<CajaChica, String> tbcCajero;
    @FXML
    private TableColumn<CajaChica, String> tbcTurno;

    ObservableList<CajaChica> listaCajachica = FXCollections.observableArrayList();
    ObservableList<Caja> listaCaja = FXCollections.observableArrayList();
    ObservableList<DetalleCajaChica> listaDetalleCajaChica = FXCollections.observableArrayList();

    CajaChica cajaChica;
    @FXML
    private TableView<CajaChica> tbCierreCajaChica;
    @FXML
    private JFXTextField txtFechaapertura;
    @FXML
    private JFXTextField txtNumeroApertura;
    @FXML
    private JFXTextField txtTotalIngreso;
    @FXML
    private JFXTextField txtTotalegreso;
    @FXML
    private JFXTextField txtSaldoFinal;
    @FXML
    private JFXTextField txtFechaCierre;
    @FXML
    private JFXButton btnImprimir;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private JFXTextField txtIngresosNetos;
    @FXML
    private JFXDatePicker dpFechaDesde;
    @FXML
    private JFXDatePicker dpFecgaHasta;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private TableView<DetalleCajaChica> tbMovimientoCajaChica;
    @FXML
    private TableColumn<DetalleCajaChica, String> tbcMovimiento;
    @FXML
    private TableColumn<DetalleCajaChica, String> tbcDocumento;
    @FXML
    private TableColumn<DetalleCajaChica, String> tbcNumdocumento;
    @FXML
    private TableColumn<DetalleCajaChica, String> tbcConcepto;
    @FXML
    private TableColumn<DetalleCajaChica, Double> tbcMonto;
    @FXML
    private TableColumn<DetalleCajaChica, Integer> tbcNumComprobante;
    @FXML
    private TabPane tabCajaChica;
    Date fechaInicio, fechaFinal = new Date();
    @FXML
    private JFXButton btnVerComprobante;
    @FXML
    private JFXTextField txtCantidadCierre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fechaInicio = ClaseUtil.getFechaPrimerDiasDelMes(new Date());
        dpFechaDesde.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaInicio));
        dpFecgaHasta.setValue(LocalDate.now());

        inicializarTabla();
        inicializarTablaDetalle();
        iniciazarFiltro();

    }

    private void iniciazarFiltro() {

        FilteredList<CajaChica> filteredData = new FilteredList<>(tbCierreCajaChica.getItems(), p -> true);
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
                } else if (filtro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
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
        sortedData.comparatorProperty().bind(tbCierreCajaChica.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbCierreCajaChica.setItems(sortedData);
    }

    public void inicializarTablaDetalle() {

        listaDetalleCajaChica.clear();

        tbMovimientoCajaChica.setItems(listaDetalleCajaChica);

        tbcNumComprobante.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcMovimiento.setCellValueFactory(new PropertyValueFactory<>("tipoMovimiento"));
        tbcDocumento.setCellValueFactory(new PropertyValueFactory<>("tipoDocumento"));
        tbcNumdocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        tbcConcepto.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        tbcMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));

        tbcDocumento.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getTipoDocumento().getNombre());
                    }
                    return property;
                });

        tbcMovimiento.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getTipoMovimiento().getNombre());
                    }
                    return property;
                });

    }

    public void inicializarTabla() {

        listaCajachica.addAll(ManejoCajaChica.getInstancia().getCajaChica(fechaFinal, fechaFinal));
        tbCierreCajaChica.setItems(listaCajachica);

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

        txtCantidadCierre.setText(Integer.toString(listaCajachica.size()));

    }

    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (tbCierreCajaChica.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar un registro");
                return;
            }

            List<DetalleCajaChica> lista = ManejoCajaChica.getInstancia().getDetalleCajaChica(cajaChica);

            Double ingresoNetos = totalIngreso(lista) - cajaChica.getSaldoInicial();

            cajaChica.setEstado("cerrarda");
            cajaChica.setFechaCierre(new Date());
            cajaChica.setSaldoFinal(saldoFinal(lista));
            cajaChica.setHoraCierre(new Date());
            cajaChica.setIngresosNeto(Double.NaN);
            cajaChica.setIngresosNeto(ingresoNetos);
            ManejoCajaChica.getInstancia().actualizar(cajaChica);
            listaCajachica.clear();
            listaCajachica.addAll(ManejoCajaChica.getInstancia().getListaCajaChica());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void limpiar() {

        txtSaldoInicial.clear();

    }

    @FXML
    private void tbCierreCajaChicaMouseClicked(MouseEvent event) {

        if (!(tbCierreCajaChica.getSelectionModel().getSelectedIndex() == -1)) {

            Date fecha = new Date();
            String fechaSrt = new SimpleDateFormat("yyyy-MM-dd").format(fecha);
            String strHoraCierre = new SimpleDateFormat("hh ss").format(fecha);

            cajaChica = tbCierreCajaChica.getSelectionModel().getSelectedItem();
            txtFechaapertura.setText(cajaChica.getFechaApertura() + " " + cajaChica.getHoraApertura());

            fechaSrt = cajaChica.getHoraCierre() == null ? fechaSrt + "" + strHoraCierre : fechaSrt + " " + cajaChica.getHoraCierre();
            txtFechaCierre.setText(fechaSrt);
            txtNumeroApertura.setText(cajaChica.getCodigo().toString());

            listaDetalleCajaChica.clear();
            listaDetalleCajaChica.addAll(ManejoCajaChica.getInstancia().getDetalleCajaChica(cajaChica));

            txtSaldoInicial.setText(Double.toString(cajaChica.getSaldoInicial()));

            txtTotalIngreso.setText(totalIngreso(listaDetalleCajaChica).toString());

            txtTotalegreso.setText(totalEgreso(listaDetalleCajaChica).toString());

            txtSaldoFinal.setText(saldoFinal(listaDetalleCajaChica).toString());
            Double ingresoNetos = totalIngreso(listaDetalleCajaChica) - cajaChica.getSaldoInicial();
            txtIngresosNetos.setText(ingresoNetos.toString());

            if (event.getClickCount() == 2) {

                tabCajaChica.getSelectionModel().select(1);
            }

        }
    }

    private Double totalIngreso(List<DetalleCajaChica> listaDetalleCajaChica) {

        Double total = 0.00;

        for (DetalleCajaChica det : listaDetalleCajaChica) {

            if (det.getTipoMovimiento().getCodigo() == 9) {
                total += det.getMonto();
            }
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double totalEgreso(List<DetalleCajaChica> listaDetalleCajaChica) {

        Double total = 0.00;

        for (DetalleCajaChica det : listaDetalleCajaChica) {

            if (det.getTipoMovimiento().getCodigo() == 10) {
                total += det.getMonto();
            }

        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double saldoFinal(List<DetalleCajaChica> listaDetalleCajaChica) {

        Double total = totalIngreso(listaDetalleCajaChica) - totalEgreso(listaDetalleCajaChica);

        return FormatNum.FormatearDouble(total, 2);
    }

    @FXML
    private void btnImprimirActionEvent(ActionEvent event) {

        try {

            if (!(tbCierreCajaChica.getSelectionModel().getSelectedIndex() == -1)) {

                int codigo = tbCierreCajaChica.getSelectionModel().getSelectedItem().getCodigo();
                RptCajaChica.getInstancia().imprimirCuadreCaja(codigo);
            } else {
                ClaseUtil.mensaje("Tiene que seleccionar un cuadre de caja");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        listaCajachica.clear();

        fechaInicio = ClaseUtil.asDate(dpFechaDesde.getValue());
        fechaFinal = ClaseUtil.asDate(dpFecgaHasta.getValue());
        listaCajachica.addAll(ManejoCajaChica.getInstancia().getCajaChica(fechaInicio, fechaFinal));
        txtCantidadCierre.setText(Integer.toString(listaCajachica.size()));

    }

    @FXML
    private void tbMovimientoCajaChicaMouseClicked(MouseEvent event) {
    }

    @FXML
    private void btnVerComprobanteActionEvent(ActionEvent event) {

        try {

            if (!(tbMovimientoCajaChica.getSelectionModel().getSelectedIndex() == -1)) {

                int formato = ManejoConfiguracion.getInstancia().getConfiguracion().getFormatoDocumento();
                DetalleCajaChica detalleCajaChica = tbMovimientoCajaChica.getSelectionModel().getSelectedItem();

                int codigo = detalleCajaChica.getCodigo();
                int codigoRecibo = Integer.parseInt(detalleCajaChica.getDocumento().trim());

                if (detalleCajaChica.getTipoMovimiento().getCodigo() == 10) {

                    RptCajaChica.getInstancia().imprimirComprobanteEgreso(codigo);

                } else if (detalleCajaChica.getTipoMovimiento().getCodigo() == 9
                        && detalleCajaChica.getTipoDocumento().getCodigo() == 10
                        && detalleCajaChica.getConcepto().equalsIgnoreCase("Apertura de caja chica")) {

                    RptCajaChica.getInstancia().imprimirComprobanteAperturaCaja(codigoRecibo);

                } else if (detalleCajaChica.getTipoDocumento().getCodigo() == 10) {

                    if (ManejoReciboIngreso.getInstancia().getDetalleRecibo(codigoRecibo).size() <= 0) {

                        RptReciboIngreso.getInstancia().imprimirRcSinFactura(codigoRecibo, formato);

                    } else {

                        RptReciboIngreso.getInstancia().verRecibo(codigoRecibo, formato);
                    }

                } else if (detalleCajaChica.getTipoDocumento().getCodigo() == 7) {

                    int codFactura = Integer.parseInt(detalleCajaChica.getDocumento().trim());

                    Double montoPendiente = ManejoFactura.getInstancia().getFactura(codFactura).getPendiente();

                    String estadoFactura = montoPendiente == 0 ? "SALDADA" : "PENDIENTE";

                    RptFactura.getInstancia().verFactura(codFactura, formato, estadoFactura);
                }

            } else {

                ClaseUtil.mensaje("Tiene que seleccionar un registro");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
