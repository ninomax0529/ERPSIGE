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
import controlador.venta.cliente.FXMLBusClienterController;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import manejo.factura.ManejoFactura;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.Factura;
import reporte.ightrack.RptFacturaPendiente;
import reporte.ightrack.cxc.RptResumenCxc;
//import reporte.factura.RptFactura;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ReporteFacturaContratoController implements Initializable {

    @FXML
    private TableColumn<Factura, Double> tbcAbono;
    @FXML
    private TableColumn<Factura, Double> tbcPendiente;

    ObservableList<DetalleFactura> listadetalleFactura = FXCollections.observableArrayList();
    ObservableList<Factura> listaFactura = FXCollections.observableArrayList();

    ObservableList<DetalleFactura> listadetalleFacturaPendiente = FXCollections.observableArrayList();
    ObservableList<Factura> listaFacturaPendiente = FXCollections.observableArrayList();

    private TabPane tabPane;

    @FXML
    private JFXDatePicker dpFechaDesde1;
    @FXML
    private JFXDatePicker dpFecgaHasta1;
    @FXML
    private JFXButton btnBuscarFactPendiente;
    @FXML
    private JFXButton btnVerReporteFactPendiente;
    @FXML
    private JFXTextField txtFiltrar1;
    @FXML
    private TabPane tabPane1;
    @FXML
    private TableView<Factura> tbFacturaPendiente;
    @FXML
    private TableColumn<Factura, String> tbcFacturaPendiente;
    @FXML
    private TableColumn<Factura, String> tbcNcfFactPendiente;
    @FXML
    private TableColumn<Factura, Date> tbcFechaFactPendiente;
    @FXML
    private TableColumn<Factura, String> tbcClienteFactPendiente;
    @FXML
    private TableColumn<Factura, Double> tbcTotalFactPendiente;
    @FXML
    private TableColumn<Factura, Factura> tbcVerFacturaPendiente;

    @FXML
    private Label lbCantidadFactura1;
    @FXML
    private TableView<DetalleFactura> tbDetalleFacturaPendiente;
    @FXML
    private TableColumn<DetalleFactura, String> tbcCodigoArticuloFactPendiente;
    @FXML
    private TableColumn<DetalleFactura, String> tbcArticuloFactPendiente;
    @FXML
    private TableColumn<DetalleFactura, String> tbcUnidadFactPendiente;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcCantidadFactPendiente;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcPrecioUnitarioFactPendiente;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcsubTotalDetFactPendiente;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcDescuentoDetFactPendiente;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcItbisDetFactPendiente;
    @FXML
    private Color x41;
    @FXML
    private Font x31;
    @FXML
    private Label lbCantidad1;
    @FXML
    private JFXCheckBox chEntreFecha;
    @FXML
    private JFXCheckBox chAlCorte;
    @FXML
    private JFXCheckBox chDetallado;
    @FXML
    private JFXCheckBox chResumido;
    @FXML
    private JFXCheckBox chContratoVencido;
    @FXML
    private JFXButton btnBuscarCliente;
    Cliente cliente;
    @FXML
    private JFXCheckBox chIncluirPendienteCero;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTablaEncabezadoFactPendiente();

        dpFechaDesde1.setValue(LocalDate.now());
        dpFecgaHasta1.setValue(LocalDate.now());

    }

    public void inicializarTablaEncabezadoFactPendiente() {

//        listaFacturaPendiente.addAll(ManejoFactura.getInstancia().getLista());
        tbFacturaPendiente.setItems(listaFacturaPendiente);

        tbcFacturaPendiente.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcNcfFactPendiente.setCellValueFactory(new PropertyValueFactory<>("ncf"));
        tbcClienteFactPendiente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tbcFechaFactPendiente.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcAbono.setCellValueFactory(new PropertyValueFactory<>("abono"));
        tbcPendiente.setCellValueFactory(new PropertyValueFactory<>("pendiente"));
        tbcTotalFactPendiente.setCellValueFactory(new PropertyValueFactory<>("total"));

        tbcClienteFactPendiente.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getCliente().getNombre());
                    }
                    return property;
                });

    }

    public void inicializarTablaDetalleFactPendiente() {

        listadetalleFacturaPendiente.clear();

        tbDetalleFacturaPendiente.setItems(listadetalleFacturaPendiente);

        tbcCodigoArticuloFactPendiente.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcArticuloFactPendiente.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        tbcCantidadFactPendiente.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcUnidadFactPendiente.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcPrecioUnitarioFactPendiente.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tbcsubTotalDetFactPendiente.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        tbcDescuentoDetFactPendiente.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        tbcItbisDetFactPendiente.setCellValueFactory(new PropertyValueFactory<>("itbis"));

        tbcUnidadFactPendiente.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getUnidad() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

        tbcArticuloFactPendiente.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getNombreArticulo());
                    }
                    return property;
                });

        tbcCodigoArticuloFactPendiente.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });

    }

    @FXML
    private void tbFacturaMouseCliked(MouseEvent event) {

        if (!(tbFacturaPendiente.getSelectionModel().getSelectedIndex() == -1)) {

            listadetalleFactura.clear();
            listadetalleFactura.addAll(ManejoFactura
                    .getInstancia().getDetalleFactura(tbFacturaPendiente.getSelectionModel()
                            .getSelectedItem().getCodigo()));

            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(1);
            }

        }
    }

    private void buscarFactPendiente() {

        listaFacturaPendiente.clear();
        listadetalleFacturaPendiente.clear();
        Date fechaDesde = ClaseUtil.asDate(dpFechaDesde1.getValue());
        Date fechaHasta = ClaseUtil.asDate(dpFecgaHasta1.getValue());
        listaFacturaPendiente.addAll(ManejoFactura.getInstancia().getLista(fechaDesde, fechaHasta));
    }

    @FXML
    private void tbDetalleFacturaMouseClicked(MouseEvent event) {

    }

    @FXML
    private void btnBuscarFactPendienteActionEvent(ActionEvent event) {

        try {

            buscarFactPendiente();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnVerReporteFactPendienteAcionEvent(ActionEvent event) {

        Date fi = ClaseUtil.asDate(dpFechaDesde1.getValue()),
                ff = ClaseUtil.asDate(dpFecgaHasta1.getValue());

        String sqlParam = "  and unidad_de_negocio="
                + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        String sqlCliente = " ", sqlPendienteCero = " where r.pendiente>0 ", sqlFecha = null;

        if (!(getCliente() == null)) {
            sqlCliente = " and c.codigo=" + getCliente().getCodigo();

        }

        if (chIncluirPendienteCero.isSelected()) {

            sqlPendienteCero = " where r.pendiente>=0 ";
        }

        if (chDetallado.isSelected()) {

            if (chAlCorte.isSelected()) {

                sqlParam += "  and  fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' ";

                sqlCliente += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

                sqlFecha = "Al : " + new SimpleDateFormat("yyyy-MM-dd").format(ff);
                fi = null;
            } else if (chEntreFecha.isSelected()) {

                sqlParam += "  and  fecha  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and   '"
                        + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  ";

                sqlCliente += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

                sqlFecha = " Desde : " + new SimpleDateFormat("yyyy-MM-dd").format(fi) + " Hasta : " + new SimpleDateFormat("yyyy-MM-dd").format(ff);
            }

            System.out.println("sqlParam detallado " + sqlParam);

            System.out.println("sqlCliente detallado " + sqlCliente);

            RptFacturaPendiente.getInstancia().facturaPendiente(fi, ff, sqlParam, sqlCliente, sqlPendienteCero, sqlFecha);

        } else if (chResumido.isSelected()) {

            if (!(getCliente() == null)) {
                sqlParam += " and c.codigo=" + getCliente().getCodigo();

            }

            if (chAlCorte.isSelected()) {

                sqlParam += "  and fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' ";

                sqlCliente += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

                fi = null;

                System.out.println("sqlParam resumido chAlCorte " + sqlParam);
                System.out.println("sqlCliente resumido chAlCorte " + sqlCliente);

                sqlFecha = "Al : " + new SimpleDateFormat("yyyy-MM-dd").format(ff);
                RptResumenCxc.getInstancia().resumenCxc(ff, sqlParam, sqlCliente, sqlPendienteCero, sqlFecha);

            } else if (chEntreFecha.isSelected()) {

                sqlParam += " and  fecha between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and '"
                        + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  ";

                sqlCliente += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

                System.out.println("sqlParam resumido chEntreFecha " + sqlParam);

                System.out.println("sqlCliente resumido chEntreFecha " + sqlCliente);

                sqlFecha = " Desde : " + new SimpleDateFormat("yyyy-MM-dd").format(fi) + " Hasta : " + new SimpleDateFormat("yyyy-MM-dd").format(ff);
                RptResumenCxc.getInstancia().resumenCxc(ff, sqlParam, sqlCliente, sqlPendienteCero, sqlFecha);
            }
        } else if (chContratoVencido.isSelected()) {

            if (chAlCorte.isSelected()) {
                RptResumenCxc.getInstancia().resumenCxcContratoAlCorte(ff);
            }
        }

            setCliente(null);

//        Date fi = ClaseUtil.asDate(dpFechaDesde1.getValue()),
//                ff = ClaseUtil.asDate(dpFecgaHasta1.getValue());
//
//        String sqlParam = "  and  ri.unidad_de_negocio="
//                + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
//
//        String sqlCliente = "", sqlPendienteCero = " where r.pendiente>0 ", sqlFecha;
//
//        if (!(getCliente() == null)) {
//            sqlCliente = " and c.codigo=" + getCliente().getCodigo();
//
//        }
//
//        if (chIncluirPendienteCero.isSelected()) {
//
//            sqlPendienteCero = " where r.pendiente>=0 ";
//        }
//
//        if (chDetallado.isSelected()) {
//
//            if (chAlCorte.isSelected()) {
//
//                sqlParam += "  and  ri.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' ";
//
//                sqlCliente += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
//                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
//
//                sqlFecha = "Al : " +new SimpleDateFormat("yyyy-MM-dd").format(ff);
//                fi = null;
//            } else if (chEntreFecha.isSelected()) {
//
//                sqlParam += "  and  ri.fecha  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and   '"
//                        + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  ";
//
//                sqlCliente += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
//                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
//
//                sqlFecha = " Desde : " + new SimpleDateFormat("yyyy-MM-dd").format(fi)+ " Hasta : " + new SimpleDateFormat("yyyy-MM-dd").format(ff);
//            }
//
//            System.out.println("sqlParam detallado " + sqlParam);
//
//            System.out.println("sqlCliente detallado " + sqlCliente);
//
//            RptFacturaPendiente.getInstancia().facturaPendiente(fi, ff, sqlParam, sqlCliente, sqlPendienteCero);
//
//        } else if (chResumido.isSelected()) {
//
//            if (!(getCliente() == null)) {
//                sqlParam += " and c.codigo=" + getCliente().getCodigo();
//
//            }
//
//            if (chAlCorte.isSelected()) {
//
//                sqlParam += "  and fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' ";
//
//                sqlCliente += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
//                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
//
//                fi = null;
//
//                System.out.println("sqlParam resumido chAlCorte " + sqlParam);
//                System.out.println("sqlCliente resumido chAlCorte " + sqlCliente);
//
//                sqlFecha = "Al : " +new SimpleDateFormat("yyyy-MM-dd").format(ff);
//                RptResumenCxc.getInstancia().resumenCxc(ff, sqlParam, sqlCliente, sqlPendienteCero, sqlFecha);
//
//            } else if (chEntreFecha.isSelected()) {
//
//                sqlParam += " and  ri.fecha between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and '"
//                        + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  ";
//
//                sqlCliente += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
//                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
//
//                System.out.println("sqlParam resumido chEntreFecha " + sqlParam);
//
//                System.out.println("sqlCliente resumido chEntreFecha " + sqlCliente);
//
//              sqlFecha = " Desde : " + new SimpleDateFormat("yyyy-MM-dd").format(fi)+ " Hasta : " + new SimpleDateFormat("yyyy-MM-dd").format(ff);
//                RptResumenCxc.getInstancia().resumenCxc(ff, sqlParam, sqlCliente, sqlPendienteCero,sqlFecha);
//            }
//        } else if (chContratoVencido.isSelected()) {
//
//            if (chAlCorte.isSelected()) {
//                RptResumenCxc.getInstancia().resumenCxcContratoAlCorte(ff);
//            }
//        }
//
//        setCliente(null);
        }

        @FXML
        private void chEntreFechaActionEvent
        (ActionEvent event
        
            ) {

        if (chEntreFecha.isSelected()) {

                chAlCorte.setSelected(false);
                dpFechaDesde1.setDisable(false);
                dpFecgaHasta1.setDisable(false);
            } else {
                chEntreFecha.setSelected(false);
                dpFechaDesde1.setDisable(true);
                dpFecgaHasta1.setDisable(false);
            }
        }

        @FXML
        private void chAlCorteActionEvent
        (ActionEvent event
        
            ) {

        if (chAlCorte.isSelected()) {

                dpFechaDesde1.setDisable(true);
                chEntreFecha.setSelected(false);
                dpFecgaHasta1.setDisable(false);
            } else {
                chAlCorte.setSelected(false);
                dpFechaDesde1.setDisable(false);
                dpFecgaHasta1.setDisable(false);
            }
        }

        @FXML
        private void chDetalladoActionEvent
        (ActionEvent event
        
            ) {

        if (chDetallado.isSelected()) {

                chResumido.setSelected(false);
                chContratoVencido.setSelected(false);
            }
        }

        @FXML
        private void chResumidoActionevent
        (ActionEvent event
        
            ) {

        if (chResumido.isSelected()) {

                chDetallado.setSelected(false);
                chContratoVencido.setSelected(false);
            }
        }

        @FXML
        private void chContratoVencidoActionEvent
        (ActionEvent event
        
            ) {

        if (chContratoVencido.isSelected()) {

                chResumido.setSelected(false);
                chDetallado.setSelected(false);
            }

        }

        @FXML
        private void btnBuscarClienteActionEvent
        (ActionEvent event) throws IOException {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/venta/cliente/FXMLBusCliente.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            utiles.ClaseUtil.getStageModal(root);

            FXMLBusClienterController articuloController = loader.getController();

            if (!(articuloController.getCliente() == null)) {

                setCliente(articuloController.getCliente());

            }

        }

    }
