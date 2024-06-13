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
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import manejo.contrato.ManejoInstalador;
import manejo.ejecutivoDeVenta.ManejoEjecutivoDeVenta;
import modelo.EjecutivoDeVenta;
import modelo.Instalador;
import reporte.comisiones.RptComisionPorCobro;
import reporte.comisiones.RptComisionPorVenta;

import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class RptComisionesController implements Initializable {

    @FXML
    private JFXCheckBox chEmpleado;
    @FXML
    private JFXCheckBox chCliente;
    @FXML
    private JFXCheckBox chResumido;
    @FXML
    private HBox hbComPorVenta;
    @FXML
    private JFXTextField txtFiltroEjVenta;
    @FXML
    private JFXTextField txtFiltroInstalador;
    @FXML
    private JFXCheckBox chTodos;
    @FXML
    private JFXCheckBox chSuplidor;

    /**
     * @return the instalador
     */
    public Instalador getInstalador() {
        return instalador;
    }

    /**
     * @param instalador the instalador to set
     */
    public void setInstalador(Instalador instalador) {
        this.instalador = instalador;
    }

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;

    ObservableList<EjecutivoDeVenta> lista = FXCollections.observableArrayList();
    ObservableList<Instalador> listaInstalador = FXCollections.observableArrayList();

    @FXML
    private JFXButton btnVerReporte;
    @FXML
    private JFXCheckBox chPorVentas;
    @FXML
    private JFXCheckBox chPorServicio;
    @FXML
    private JFXCheckBox chPorCobros;
    @FXML
    private TableView<EjecutivoDeVenta> tbVendedor;
    @FXML
    private TableColumn<EjecutivoDeVenta, String> tbcCodigo;
    @FXML
    private TableColumn<EjecutivoDeVenta, String> tbcNombre;

    EjecutivoDeVenta ejecutivoDeVenta;
    @FXML
    private TableView<Instalador> tbInstalador;
    @FXML
    private TableColumn<Instalador, String> tbcCodInstalador;
    @FXML
    private TableColumn<Instalador, String> tbcNomInstalador;

    public EjecutivoDeVenta getEjecutivoDeVenta() {
        return ejecutivoDeVenta;
    }

    public void setEjecutivoDeVenta(EjecutivoDeVenta ejecutivoDeVenta) {
        this.ejecutivoDeVenta = ejecutivoDeVenta;
    }

    private Instalador instalador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabla();
        iniciarTablaInstalador();
        iniciazarFiltroInstaldor();
        iniciazarFiltro();
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
        hbComPorVenta.setDisable(true);

    }

    public void iniciarTabla() {

        lista.clear();

        lista.addAll(ManejoEjecutivoDeVenta.getInstancia().getLista());

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        tbVendedor.setItems(lista);

    }

    public void iniciarTablaInstalador() {

        listaInstalador.clear();

        listaInstalador.addAll(ManejoInstalador.getInstancia().getLista());

        tbcCodInstalador.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNomInstalador.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        tbInstalador.setItems(listaInstalador);

    }

    @FXML
    private void btnVerReporteActionEvent(ActionEvent event) {

        Date fi, ff;
        fi = ClaseUtil.asDate(dpFechaInicio.getValue());
        ff = ClaseUtil.asDate(dpFechaFinal.getValue());
        String codigoVendedor = " from  factura f INNER JOIN ejecutivo_de_venta ej  "
                + " on  ej.codigo=f.vendedor  INNER JOIN cargo cg on ej.cargo=cg.codigo ";

        String sqlParam = " ";

        if (chPorVentas.isSelected()) {

            if (chEmpleado.isSelected()) {

                sqlParam = " and  tipo_ejecutivo_venta=1 ";

                if (!(getEjecutivoDeVenta() == null)) {
                    sqlParam += " and vendedor=" + getEjecutivoDeVenta().getCodigo();
                }
                System.out.println("sql " + sqlParam);

                if (chResumido.isSelected()) {

                    RptComisionPorVenta.getInstancia().resumenComisionPorVenta(fi, ff, sqlParam, codigoVendedor);

                } else {
                    RptComisionPorVenta.getInstancia().imprimir(fi, ff, sqlParam, codigoVendedor);
                }

            } else if (chCliente.isSelected()) {

                sqlParam = " and  tipo_ejecutivo_venta=2 ";

                if (!(getEjecutivoDeVenta() == null)) {
                    sqlParam += " and vendedor=" + getEjecutivoDeVenta().getCodigo();
                }

                System.out.println("sql " + sqlParam);

                if (chResumido.isSelected()) {

                    RptComisionPorVenta.getInstancia().resumenComisionPorVenta(fi, ff, sqlParam, codigoVendedor);

                } else {
                    RptComisionPorVenta.getInstancia().imprimir(fi, ff, sqlParam, codigoVendedor);
                }

            } else if (chTodos.isSelected()) {

//                sqlParam = " and tipo_ejecutivo_venta in(2,1)  ";
                System.out.println("sqlParam : " + sqlParam);
                if (chResumido.isSelected()) {

                    RptComisionPorVenta.getInstancia().resumenComisionPorVenta(fi, ff, sqlParam, codigoVendedor);

                } else {
                    RptComisionPorVenta.getInstancia().imprimir(fi, ff, sqlParam, codigoVendedor);
                }

            } else if (chSuplidor.isSelected()) {

                codigoVendedor = " from  factura f INNER JOIN ejecutivo_de_venta ej  "
                        + " on  ej.codigo=suplidor_de_venta  INNER JOIN cargo cg on ej.cargo=cg.codigo ";

                if (!(getEjecutivoDeVenta() == null)) {

                    sqlParam += " and suplidor_de_venta=" + getEjecutivoDeVenta().getCodigo();
                }

                System.out.println("sql " + sqlParam);

                if (chResumido.isSelected()) {

                    RptComisionPorVenta.getInstancia().resumenComisionPorVenta(fi, ff, sqlParam, codigoVendedor);

                } else {
                    RptComisionPorVenta.getInstancia().imprimir(fi, ff, sqlParam, codigoVendedor);
                }

            } else {

                ClaseUtil.mensaje("Tiene que seleccionar una opcion");
            }

        } else if (chPorServicio.isSelected()) {

            if (!(getInstalador() == null)) {
                sqlParam = " and isnt.codigo=" + getInstalador().getCodigo();
            }
            RptComisionPorVenta.getInstancia().comisionPorServicio(fi, ff, sqlParam);

        } else if (chPorCobros.isSelected()) {

            if (getEjecutivoDeVenta() == null) {
                ClaseUtil.mensaje("Tiene que seleccionar un ejecutivo de venta ");
                return;
            }
            boolean isSuplidor = false;

            if (chSuplidor.isSelected()) {
                isSuplidor = true;
                System.out.println("isSuplidor "+isSuplidor);
                RptComisionPorCobro.getInstancia().imprimir(fi, ff, getEjecutivoDeVenta(), isSuplidor);

            } else {
                  System.out.println("isSuplidor "+isSuplidor);
                RptComisionPorCobro.getInstancia().imprimir(fi, ff);
            }

        }

    }

    @FXML
    private void chPorVentasActionEvent(ActionEvent event) {

        if (chPorVentas.isSelected()) {

            hbComPorVenta.setDisable(false);
            btnVerReporte.setDisable(false);
            chPorServicio.setSelected(false);
            chPorCobros.setSelected(false);
            chCliente.setDisable(false);
            chEmpleado.setDisable(false);
            chCliente.setSelected(false);
            chEmpleado.setSelected(false);
            chResumido.setSelected(false);
            chResumido.setDisable(false);

        } else {

            chCliente.setDisable(true);
            chEmpleado.setDisable(true);
            chCliente.setSelected(false);
            chEmpleado.setSelected(false);
            chResumido.setSelected(false);
            chResumido.setDisable(true);
        }
    }

    @FXML
    private void chPorServicioActionEvent(ActionEvent event
    ) {
        if (chPorServicio.isSelected()) {
            btnVerReporte.setDisable(false);
            chPorVentas.setSelected(false);
            chPorCobros.setSelected(false);
            hbComPorVenta.setDisable(true);

            chCliente.setDisable(false);
            chEmpleado.setDisable(false);
            chCliente.setSelected(false);
            chEmpleado.setSelected(false);
        }
    }

    @FXML
    private void chPorCobrosActionEvent(ActionEvent event) {

        if (chPorCobros.isSelected()) {

            btnVerReporte.setDisable(false);
            chPorVentas.setSelected(false);
            chPorServicio.setSelected(false);
            hbComPorVenta.setDisable(true);
            hbComPorVenta.setDisable(false);
            chResumido.setSelected(false);
            chResumido.setDisable(true);

        }
    }

    @FXML
    private void tbClienteActionEvent(MouseEvent event) {

        if (!(tbVendedor.getSelectionModel().getSelectedIndex() == -1)) {

            setEjecutivoDeVenta(tbVendedor.getSelectionModel().getSelectedItem());
        }

    }

    @FXML
    private void tbInstaladorMouseClicked(MouseEvent event) {

        if (!(tbInstalador.getSelectionModel().getSelectedIndex() == -1)) {

            setInstalador(tbInstalador.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void chEmpleadoActionEvent(ActionEvent event) {

        if (chPorVentas.isSelected() || chPorCobros.isSelected()) {

            lista.clear();
            lista.addAll(ManejoEjecutivoDeVenta.getInstancia().getLista(false));
            if (chEmpleado.isSelected()) {
                chCliente.setSelected(false);
                chTodos.setSelected(false);
                chSuplidor.setSelected(false);

            }
        }
    }

    @FXML
    private void chClienteActionEvent(ActionEvent event) {

        if (chPorVentas.isSelected() || chPorCobros.isSelected()) {

            lista.clear();
            lista.addAll(ManejoEjecutivoDeVenta.getInstancia().getLista(false));

            if (chCliente.isSelected()) {
                chEmpleado.setSelected(false);
                chTodos.setSelected(false);
                chSuplidor.setSelected(false);
            }
        }
    }

    @FXML
    private void chResumidoActionEvent(ActionEvent event
    ) {

//        if (chPorVentas.isSelected()) {
//
//            if (chResumido.isSelected()) {
//                chEmpleado.setSelected(false);
//                chCliente.setSelected(false);
//            }
//        }
    }

    private void iniciazarFiltroInstaldor() {

        FilteredList<Instalador> filteredData = new FilteredList<>(tbInstalador.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltroInstalador.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toUpperCase();

                if (filtro.getCodigo() != null && filtro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getNombre() != null && filtro.getNombre().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Instalador> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbInstalador.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbInstalador.setItems(sortedData);
    }

    private void iniciazarFiltro() {

        FilteredList<EjecutivoDeVenta> filteredData = new FilteredList<>(tbVendedor.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltroEjVenta.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toUpperCase();

                if (filtro.getCodigo() != null && filtro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getNombre() != null && filtro.getNombre().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<EjecutivoDeVenta> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbVendedor.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbVendedor.setItems(sortedData);
    }

    @FXML
    private void chTodosActionEvent(ActionEvent event) {

        if (chPorVentas.isSelected()) {

            if (chTodos.isSelected()) {
                chEmpleado.setSelected(false);
                chCliente.setSelected(false);
                chSuplidor.setSelected(false);
            }
        }
    }

    @FXML
    private void chSuplidorActionEvent(ActionEvent event) {

        if (chSuplidor.isSelected()) {

            chCliente.setSelected(false);
            chTodos.setSelected(false);
            chEmpleado.setSelected(false);
            lista.clear();
            lista.addAll(ManejoEjecutivoDeVenta.getInstancia().getLista(true));

        }
    }

}
