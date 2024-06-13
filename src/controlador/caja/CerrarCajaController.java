/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.caja;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import manejo.caja.ManejoCajaChica;
import modelo.Caja;
import modelo.CajaChica;
import modelo.DetalleCajaChica;
import reporte.cajaChica.RptCajaChica;
import util.ClaseUtil;
import util.FormatNum;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class CerrarCajaController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
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
    private JFXTextField txtSaldoFinal1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
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

    public void inicializarTabla() {

        listaCajachica.addAll(ManejoCajaChica.getInstancia().getListaCajaChica());
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

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (tbCierreCajaChica.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar un registro");
                return;
            }

            if (!(ClaseUtil.confirmarMensaje("Seguro que quire cerar la caja ").get() == ButtonType.OK)) {
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
            cajaChica.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
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

            if (cajaChica.getEstado().equals("cerrada")) {
                btnGuardar.setDisable(true);

            } else {

                btnGuardar.setDisable(false);
            }

            List<DetalleCajaChica> lista = ManejoCajaChica.getInstancia().getDetalleCajaChica(cajaChica);

            txtSaldoInicial.setText(Double.toString(cajaChica.getSaldoInicial()));

            txtTotalIngreso.setText(totalIngreso(lista).toString());

            txtTotalegreso.setText(totalEgreso(lista).toString());

            txtSaldoFinal.setText(saldoFinal(lista).toString());
            Double ingresoNetos = totalIngreso(lista) - cajaChica.getSaldoInicial();
            txtIngresosNetos.setText(ingresoNetos.toString());
        }
    }

    private Double totalIngreso(List<DetalleCajaChica> listaDetalleCajaChica) {

        Double total = 0.00;

        for (DetalleCajaChica det : listaDetalleCajaChica) {

            if (det.getTipoMovimiento().getCodigo() == 9 && det.getAnulado() == false) {
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

}
