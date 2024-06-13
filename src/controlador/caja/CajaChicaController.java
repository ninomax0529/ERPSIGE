/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.caja;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import manejo.ManejoConfiguracion;
import manejo.ReciboIngreso.ManejoReciboIngreso;
import manejo.caja.ManejoCajaChica;
import manejo.caja.ManejoDetalleCajaChica;
import manejo.caja.ManejoTipoMovimieto;
import manejo.documento.ManejoTipodocumento;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoTipoFormaPago;
import modelo.CajaChica;
import modelo.DetalleCajaChica;
import modelo.TipoDocumento;
import modelo.TipoFormaPago;
import reporte.cajaChica.RptCajaChica;
import reporte.cxc.RptReciboIngreso;
import reporte.factura.RptFactura;
import util.ClaseUtil;
import util.FormatNum;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class CajaChicaController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXTextField txtMonto;
    @FXML
    
    private JFXComboBox<TipoFormaPago> cbFormaPago;
    @FXML
    private JFXTextField txtFechaapertura;
    @FXML
    private JFXTextField txtNumeroApertura;
    @FXML
    private JFXTextField txtSaldoInicial;
    @FXML
    private JFXTextField txtTotalIngreso;
    @FXML
    private JFXTextField txtTotalegreso;
    @FXML
    private JFXTextField txtSaldoFinal;
    @FXML
    private JFXButton btnIngreso;
    @FXML
    private JFXComboBox<TipoDocumento> cbDocumento;
    @FXML
    private TextArea txtConcepto;
    @FXML
    private JFXTextField txtNumdocumento;
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
    private TableColumn<DetalleCajaChica, String> tbcAnulado;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Label lbCantidad;
    @FXML
    private JFXButton btnEgreso;

    CajaChica cajaChica;
    DetalleCajaChica detalleCajaChica;
    int tipoMovimiento;

    ObservableList<DetalleCajaChica> listaDetalleCajaChica = FXCollections.observableArrayList();
    ObservableList<TipoFormaPago> listaTipoFormaPago = FXCollections.observableArrayList();
    ObservableList<TipoDocumento> listaTipoDocumento = FXCollections.observableArrayList();
    @FXML
    private Label lbMovimiento;
    @FXML
    private JFXButton btnImprimir;
    @FXML
    private JFXButton btnanular;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
        inicializarCombox();
        nuevo();
        datosApertura();

    }

    private void inicializarCombox() {

        cbDocumento.setConverter(new StringConverter<TipoDocumento>() {

            @Override
            public String toString(TipoDocumento tipoDocumento) {
                return String.valueOf(tipoDocumento.getNombre());
            }

            @Override
            public TipoDocumento fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbFormaPago.setConverter(new StringConverter<TipoFormaPago>() {

            @Override
            public String toString(TipoFormaPago tipoFormaPago) {
                return String.valueOf(tipoFormaPago.getDescripcion());
            }

            @Override
            public TipoFormaPago fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaTipoFormaPago.addAll(ManejoTipoFormaPago.getInstancia().getListaTipoFormaPago());
        cbDocumento.setItems(listaTipoDocumento);
        cbFormaPago.setItems(listaTipoFormaPago);

    }

    public void inicializarTabla() {

        listaDetalleCajaChica.clear();

        tbMovimientoCajaChica.setItems(listaDetalleCajaChica);

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

        tbcAnulado.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();

                    property.setValue(cellData.getValue().getAnulado() == true ? "SI" : "NO");

                    return property;
                });

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (lbMovimiento.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que selecionar un movimiento");
                return;
            }

            if (cbDocumento.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que selecionar un documento");
                return;
            }
            if (txtNumdocumento.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar  el numero de documento");
                txtNumdocumento.requestFocus();
                return;
            }

            if (cbFormaPago.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que selecionar una forma de pago");
                return;
            }

            if (txtMonto.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar un monto");
                txtMonto.requestFocus();
                return;
            }

            listaDetalleCajaChica.clear();
            detalleCajaChica.setCajaChica(cajaChica);
            detalleCajaChica.setTipoMovimiento(ManejoTipoMovimieto.getInstancia().getTipoMovimiento(tipoMovimiento));
            detalleCajaChica.setConcepto(txtConcepto.getText());
            detalleCajaChica.setTipoDocumento(cbDocumento.getSelectionModel().getSelectedItem());
            detalleCajaChica.setDocumento(txtNumdocumento.getText());
            detalleCajaChica.setMonto(Double.parseDouble(txtMonto.getText()));
            detalleCajaChica.setNombreMovimiento(tipoMovimiento == 9 ? "INGRESO" : "EGRESO");
            detalleCajaChica.setAnulado(false);
            listaDetalleCajaChica.add(detalleCajaChica);

            cajaChica.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            cajaChica.setDetalleCajaChicaCollection(listaDetalleCajaChica);

            ManejoCajaChica.getInstancia().actualizar(cajaChica);

            int codigoComprabante = ManejoCajaChica.getInstancia().getUltimoRegistroDetalleCajaChica(cajaChica).getCodigo();

            System.out.println("codigoComprabante " + codigoComprabante);
            RptCajaChica.getInstancia().imprimirComprobanteEgreso(codigoComprabante);

            listaDetalleCajaChica.clear();
            listaDetalleCajaChica.addAll(ManejoCajaChica.getInstancia().getDetalleCajaChica(cajaChica));

            datosApertura();
            nuevo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cbFormaPagoActionevent(ActionEvent event) {
    }

    @FXML
    private void cbDocumentoActionEvent(ActionEvent event) {
    }

    @FXML
    private void tbMovimientoCajaChicaMouseClicked(MouseEvent event) {

    }

    private void datosApertura() {

        cajaChica = ManejoCajaChica.getInstancia().getCajaChica(new Date(), "abierta");

        if (!(cajaChica == null)) {

            listaDetalleCajaChica.clear();
            listaDetalleCajaChica.addAll(ManejoCajaChica.getInstancia().getDetalleCajaChica(cajaChica));

            txtFechaapertura.setText(cajaChica.getFechaApertura().toString());
            txtSaldoInicial.setText(Double.toString(cajaChica.getSaldoInicial()));
            txtSaldoFinal.setText(saldoFinal().toString());
            txtNumeroApertura.setText(cajaChica.getCodigo().toString());
            txtTotalIngreso.setText(totalIngreso().toString());
            txtTotalegreso.setText(totalEgreso().toString());
        }

    }

    @FXML
    private void btnIngresoActionEvent(ActionEvent event) {

        try {

            Date fecha = new Date();

            lbMovimiento.setText("Ingreso");
            tipoMovimiento = 9;

            cajaChica = ManejoCajaChica.getInstancia().getCajaChica(fecha, "abierta");

            if (cajaChica == null) {

                ClaseUtil.mensaje("No hay una caja abierta en esta fecha " + fecha);

                return;
            }

            listaTipoDocumento.clear();

            listaTipoDocumento.addAll(ManejoTipodocumento.getInstancia().getLista("ingreso"));
            datosApertura();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnEgresoActionEvent(ActionEvent event) {

        try {

            Date fecha = new Date();
            lbMovimiento.setText("Egreso");
            tipoMovimiento = 10;

            cajaChica = ManejoCajaChica.getInstancia().getCajaChica(fecha, "abierta");

            if (cajaChica == null) {

                ClaseUtil.mensaje("No hay una caja abierta en esta fecha " + fecha);

                return;
            }

            listaTipoDocumento.clear();

            listaTipoDocumento.addAll(ManejoTipodocumento.getInstancia().getLista("egreso"));
            datosApertura();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void nuevo() {
        detalleCajaChica = new DetalleCajaChica();
        limpiar();
    }

    private void limpiar() {

        txtConcepto.clear();
        txtMonto.clear();
        txtNumdocumento.clear();
        listaTipoDocumento.clear();
        cbFormaPago.getSelectionModel().clearSelection();

    }

    private Double totalIngreso() {

        Double total = 0.00;

        for (DetalleCajaChica det : listaDetalleCajaChica) {

            if (det.getTipoMovimiento().getCodigo() == 9) {

                if (det.getAnulado() == false) {
                    total += det.getMonto();
                }

            }
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double totalEgreso() {

        Double total = 0.00;

        for (DetalleCajaChica det : listaDetalleCajaChica) {

            if (det.getTipoMovimiento().getCodigo() == 10) {

                if (det.getAnulado() == false) {
                    total += det.getMonto();
                }

            }

        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double saldoFinal() {

        Double total = totalIngreso() - totalEgreso();

        return FormatNum.FormatearDouble(total, 2);
    }

    @FXML
    private void btnImprimirActionEvent(ActionEvent event) {

        try {

            if (!(tbMovimientoCajaChica.getSelectionModel().getSelectedIndex() == -1)) {

                DetalleCajaChica detalleCajaChica = tbMovimientoCajaChica.getSelectionModel().getSelectedItem();

                int formato = ManejoConfiguracion.getInstancia().getConfiguracion().getFormatoDocumento();

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

    @FXML
    private void btnanularActionEvent(ActionEvent event) {

        if (!(tbMovimientoCajaChica.getSelectionModel().getSelectedIndex() == -1)) {

            DetalleCajaChica detCaja = tbMovimientoCajaChica.getSelectionModel().getSelectedItem();

            if (detCaja.getAnulado() == true) {

                util.ClaseUtil.mensaje(" ESTE COMPROBANTE YA  ESTA ANULADO ");
                return;
            }

            if (detCaja.getTipoMovimiento().getCodigo() == 9) {

                util.ClaseUtil.mensaje(" TIENE QUE ANULARPRIMERO EL DOCUMENTO  QUE LO GENERO ");
                return;
            }

            Optional<ButtonType> ok = util.ClaseUtil.confirmarMensaje("SEGURO QUE QUIERE ANULAR EL  COMPROBANTE NUMERO  " + detCaja.getCodigo());

            if (ok.get() == ButtonType.OK) {

                detCaja.setAnulado(true);

                ManejoDetalleCajaChica.getInstancia().actualizar(detCaja);

                listaDetalleCajaChica.clear();
                listaDetalleCajaChica.addAll(ManejoCajaChica.getInstancia().getDetalleCajaChica(detCaja.getCajaChica()));

                datosApertura();

            }
        }

    }

}
