/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.reportes.venta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import controlador.venta.cliente.FXMLBusClienterController;
import dto.cobro.ReciboIngresoDTO;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import manejo.ReciboIngreso.ManejoReciboIngreso;
import manejo.factura.ManejoFactura;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.Factura;
import plantilla.ExportarDato;
import plantilla.cobros.ExportarCobro;
import plantilla.ventas.ExportarVenta;
import reporte.ventas.RptVentas;
//import reporte.factura.RptFactura;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ReporteDeVentasController implements Initializable {

    ObservableList<DetalleFactura> listadetalleFactura = FXCollections.observableArrayList();
    ObservableList<Factura> listaFactura = FXCollections.observableArrayList();

    ObservableList<DetalleFactura> listadetalleFacturaPendiente = FXCollections.observableArrayList();
    ObservableList<Factura> listaFacturaPendiente = FXCollections.observableArrayList();

    @FXML
    private JFXCheckBox chEntreFecha;
    @FXML
    private JFXCheckBox chAlCorte;
    @FXML
    private JFXCheckBox chDetallado;
    @FXML
    private JFXCheckBox chResumido;
    @FXML
    private JFXButton btnBuscarCliente;
    Cliente cliente;
    @FXML
    private VBox vbEntreFecha;
    @FXML
    private JFXDatePicker dpFechaDesde;
    @FXML
    private JFXDatePicker dpFechaHasta;
    @FXML
    private JFXButton btnVerReporte;
    @FXML
    private JFXRadioButton rbDeContado;
    @FXML
    private JFXRadioButton rbACredito;
    @FXML
    private JFXRadioButton rbTodas;
    @FXML
    private JFXButton btnExportarXLs;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    ToggleGroup toggleGroup = new ToggleGroup();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFechaDesde.setValue(LocalDate.now());
        dpFechaHasta.setValue(LocalDate.now());
        rbACredito.setToggleGroup(toggleGroup);
        rbDeContado.setToggleGroup(toggleGroup);
        rbTodas.setToggleGroup(toggleGroup);
        rbTodas.setSelected(true);

    }

    @FXML
    private void chEntreFechaActionEvent(ActionEvent event) {

        if (chEntreFecha.isSelected()) {

            chAlCorte.setSelected(false);
            dpFechaDesde.setDisable(false);
            dpFechaHasta.setDisable(false);
        } else {
            chEntreFecha.setSelected(false);
            dpFechaDesde.setDisable(true);
            dpFechaHasta.setDisable(false);
        }
    }

    @FXML
    private void chAlCorteActionEvent(ActionEvent event) {

        if (chAlCorte.isSelected()) {

            dpFechaDesde.setDisable(true);
            chEntreFecha.setSelected(false);
            dpFechaHasta.setDisable(false);
        } else {
            chAlCorte.setSelected(false);
            dpFechaDesde.setDisable(false);
            dpFechaHasta.setDisable(false);
        }
    }

    @FXML
    private void chDetalladoActionEvent(ActionEvent event) {

        if (chDetallado.isSelected()) {

            chResumido.setSelected(false);

        }
    }

    @FXML
    private void chResumidoActionevent(ActionEvent event) {

        if (chResumido.isSelected()) {

            chDetallado.setSelected(false);

        }
    }

    @FXML
    private void btnBuscarClienteActionEvent(ActionEvent event) throws IOException {

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

    @FXML
    private void btnVerReporteActionEvent(ActionEvent event) {

        Date fi = ClaseUtil.asDate(dpFechaDesde.getValue()),
                ff = ClaseUtil.asDate(dpFechaHasta.getValue());

        String tipoVenta = "";
        String sqlParam = " and  f.unidad_de_negocio="
                + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        if (!(getCliente() == null)) {
            sqlParam += " and c.codigo=" + getCliente().getCodigo();

        }

        System.out.println("sqlParam detallado " + sqlParam);

        if (rbACredito.isSelected()) {

            sqlParam += " and tipo_venta=2 ";
            tipoVenta = "Tipo de Venta : A Credito ";
        } else if (rbDeContado.isSelected()) {

            sqlParam += " and f.tipo_venta=1 ";
            tipoVenta = "Tipo de Venta : De Contado ";
        } else if (rbTodas.isSelected()) {

            sqlParam += " and tipo_venta in(1,2) ";

            tipoVenta = "";
        }

        RptVentas.getInstancia().ventasPorDia(fi, ff, sqlParam, tipoVenta);

//        if (chDetallado.isSelected()) {
//
//            if (chAlCorte.isSelected()) {
//
//                sqlParam += "  and fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' ";
//
//                sqlCliente += "  and f.fecha<='" + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "' "
//                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
//
//                fi = null;
//            } else if (chEntreFecha.isSelected()) {
//
//                sqlParam += " and  ri.fecha between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and '"
//                        + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  ";
//
//                sqlCliente += " and  f.fecha between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and '"
//                        + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  "
//                        + "   and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
//
//            }
//
//            System.out.println("sqlParam detallado " + sqlParam);
//
//            System.out.println("sqlCliente detallado " + sqlCliente);
//
//            RptFacturaPendiente.getInstancia().facturaPendiente(fi, ff, sqlParam, sqlCliente);
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
//                RptResumenCxc.getInstancia().resumenCxc(ff, sqlParam, sqlCliente);
//
//            } else if (chEntreFecha.isSelected()) {
//
//                sqlParam += " and  ri.fecha between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and '"
//                        + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  ";
//
//                sqlCliente += " and  f.fecha between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and '"
//                        + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  "
//                        + " and  f.unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
//
//                System.out.println("sqlParam resumido chEntreFecha " + sqlParam);
//
//                System.out.println("sqlCliente resumido chEntreFecha " + sqlCliente);
//
//                RptResumenCxc.getInstancia().resumenCxc(ff, sqlParam, sqlCliente);
//            }
//        }
        setCliente(null);

    }

    @FXML
    private void btnExportarXLsActionEvent(ActionEvent event) {

        try {

          
            Date fi = ClaseUtil.asDate(dpFechaDesde.getValue()),
                ff = ClaseUtil.asDate(dpFechaHasta.getValue());

            List<Factura> lista = ManejoFactura.getInstancia().getLista(fi, ff);

            System.out.println("lista " + lista.size());
            ExportarDato.exportarFacturaXLs(lista,fi,ff);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
