/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.reporte;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import manejo.ReciboIngreso.ManejoReciboIngreso;
import modelo.DetalleReciboIngreso;
import modelo.EntradaInventario;
import modelo.ReciboIngreso;
import reporte.cobros.RptCobrosPorDias;
import reporte.cxc.reciboIngreso.RptCobros;
import reporte.factura.RptFactura;

import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class RptReciboDeIngresoController implements Initializable {

    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidadArticulo;
    @FXML
    private JFXTextField txtValorTotal;
    @FXML
    private TableColumn<EntradaInventario, Date> tbcFecha;
    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<ReciboIngreso> tbReciboIngreso;
    @FXML
    private TableColumn<ReciboIngreso, String> tbcRecibo;
    @FXML
    private TableColumn<ReciboIngreso, String> tbcCliente;
    @FXML
    private TableColumn<ReciboIngreso, String> tbcAnulado;
    @FXML
    private TableView<DetalleReciboIngreso> tbDetalleRecibo;
    @FXML
    private TableColumn<DetalleReciboIngreso, String> tbcFActura;
    @FXML
    private TableColumn<DetalleReciboIngreso, Double> tbcPagado;
    @FXML
    private TableColumn<DetalleReciboIngreso, Double> tbcPendiente;
    @FXML
    private TableColumn<DetalleReciboIngreso, String> tbcFechaFactura;

    ObservableList<ReciboIngreso> listaReibo = FXCollections.observableArrayList();
    ObservableList<DetalleReciboIngreso> listadetalle = FXCollections.observableArrayList();
    @FXML
    private TabPane tabPane;
    @FXML
    private JFXButton btnVerReporte;
    @FXML
    private JFXCheckBox chPorDias;
    @FXML
    private JFXCheckBox chAvanceCliente;
    @FXML
    private VBox vbRptPorDias;
    @FXML
    private JFXCheckBox chPagoFactura;
    @FXML
    private JFXCheckBox chTodos;
    @FXML
    private JFXCheckBox chDetallado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTablaRecibo();
        iniciarTablaDetalle();
        iniciazarFiltro();

        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

    }

    private void iniciazarFiltro() {

        FilteredList<ReciboIngreso> filteredData = new FilteredList<>(tbReciboIngreso.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getFecha() != null && filtro.getFecha().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getNumero() != null && filtro.getNumero().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filtro.getCliente() != null && filtro.getCliente().getNombre().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ReciboIngreso> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbReciboIngreso.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbReciboIngreso.setItems(sortedData);
    }

    private void iniciarTablaRecibo() {

        // listaEntrada.clear();
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

        Date fechaini, fechafin;
        fechaini = ClaseUtil.asDate(dpFechaInicio.getValue());
        fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());
        listaReibo.clear();
        listadetalle.clear();

        listaReibo.addAll(ManejoReciboIngreso.getInstancia().getReciboPorFecha(fechaini, fechafin));

        tbReciboIngreso.setItems(listaReibo);
        txtCantidad.setText(Integer.toString(listaReibo.size()));

        tbcRecibo.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tbcAnulado.setCellValueFactory(new PropertyValueFactory<>("anulado"));

        tbcAnulado.setCellValueFactory(
                cellData -> {

                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {

                        if (cellData.getValue().getAnulado()) {
                            property.setValue("Si");
                        } else {
                            property.setValue("No");
                        }

                    }
                    return property;
                });

        tbcCliente.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getCliente().getNombre());
                    }
                    return property;
                });

        iniciazarFiltro();

    }

    public void iniciarTablaDetalle() {

        listadetalle.clear();

        tbDetalleRecibo.setItems(listadetalle);

        tbcFActura.setCellValueFactory(new PropertyValueFactory<>("factura"));
        tbcPagado.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcPendiente.setCellValueFactory(new PropertyValueFactory<>("pendiente"));

        tbcFActura.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getFactura().getNcf());
                    }
                    return property;
                });

        tbcFechaFactura.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getFactura() != null) {
                        property.setValue(new SimpleDateFormat("dd-MM-yyyy")
                                .format(cellData.getValue().getFactura().getFecha()));
                    }
                    return property;
                });

    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        buscar();

    }

    @FXML
    private void tbReciboMouseClicked(MouseEvent event) {

        if (!(tbReciboIngreso.getSelectionModel().getSelectedIndex() == -1)) {

            listadetalle.clear();
            txtCantidadArticulo.clear();
            txtValorTotal.clear();
            ReciboIngreso recibo = tbReciboIngreso.getSelectionModel().getSelectedItem();

            txtComentario.setText(recibo.getConcepto());

            listadetalle.addAll(ManejoReciboIngreso
                    .getInstancia()
                    .getDetalleRecibo(recibo.getCodigo()));

            valorTotal();
            txtCantidadArticulo.setText(Integer.toString(listadetalle.size()));
            if (event.getClickCount() == 2) {

                tabPane.getSelectionModel().select(1);
            }

        }
    }

    private void valorTotal() {

        Double total = 0.00;
        for (DetalleReciboIngreso detalleEntradaInventario : listadetalle) {

            total += detalleEntradaInventario.getTotal();
        }

        txtValorTotal.setText(total.toString());
    }

    @FXML
    private void tbDetalleReciboMouseClicked(MouseEvent event) {

        try {

            if (!(tbDetalleRecibo.getSelectionModel().getSelectedIndex() == -1)) {

                if (event.getClickCount() == 2) {

                    int factura = tbDetalleRecibo.getSelectionModel().getSelectedItem().getFactura().getCodigo();
                    RptFactura.getInstancia().imprimir(factura);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void buscar() {

        Date fechaini, fechafin;
        fechaini = ClaseUtil.asDate(dpFechaInicio.getValue());
        fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());
        listaReibo.clear();
        listadetalle.clear();

        listaReibo.addAll(ManejoReciboIngreso.getInstancia().getReciboPorFecha(fechaini, fechafin));
        txtCantidad.setText(Integer.toString(listaReibo.size()));
        txtCantidadArticulo.clear();
        txtValorTotal.clear();
    }

    @FXML
    private void btnVerReporteActionEvent(ActionEvent event) {

        Date fi, ff;
        fi = ClaseUtil.asDate(dpFechaInicio.getValue());
        ff = ClaseUtil.asDate(dpFechaFinal.getValue());
        String sqlParam = " and tipo_recibo in('PF','AV') ", tipoRecibo = "TODOS";

        if (chPorDias.isSelected()) {

            if (chAvanceCliente.isSelected()) {

                sqlParam = " and tipo_recibo='AV' ";
                tipoRecibo = "Avance de Cliente";
            } else if (chPagoFactura.isSelected()) {

                sqlParam = " and tipo_recibo='PF' ";
                tipoRecibo = "Pago de Factura";
            } else {
                sqlParam = " and tipo_recibo in('PF','AV')";
            }

            sqlParam += " order by ri.fecha ";

            RptCobrosPorDias.getInstancia().imprimir(fi, ff, sqlParam, tipoRecibo);
        } else if (chDetallado.isSelected()) {
            RptCobros.getInstancia().imprimir(fi, ff);
        }

    }

    @FXML
    private void chPorDiasActionEvent(ActionEvent event) {

        if (chPorDias.isSelected()) {
            chAvanceCliente.setSelected(false);
            vbRptPorDias.setDisable(false);
            chDetallado.setSelected(false);
        } else {
            vbRptPorDias.setDisable(true);
            chAvanceCliente.setSelected(false);
            chTodos.setSelected(false);
            chPagoFactura.setSelected(false);
        }
    }

    @FXML
    private void chPagoFacturaActionEvent(ActionEvent event) {

        if (chPagoFactura.isSelected()) {
            chAvanceCliente.setSelected(false);
            chTodos.setSelected(false);
        } else {
            chPagoFactura.setSelected(false);
        }
    }

    @FXML
    private void chAvanceClienteActionEvent(ActionEvent event) {

        if (chAvanceCliente.isSelected()) {
            chPagoFactura.setSelected(false);
            chTodos.setSelected(false);
        } else {
            chAvanceCliente.setSelected(false);
        }
    }

    @FXML
    private void chTodosActionEvent(ActionEvent event) {

        if (chTodos.isSelected()) {
            chPagoFactura.setSelected(false);
            chAvanceCliente.setSelected(false);
        } else {
            chTodos.setSelected(false);
        }
    }

    @FXML
    private void chDetalladoActionEvent(ActionEvent event) {

        if (chDetallado.isSelected()) {
            chPorDias.setSelected(false);
        } else {
            chDetallado.setSelected(false);
        }
    }

}
