/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cxc.reciboIngreso;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import manejo.cliente.ManejoCreditoCliente;
import manejo.factura.ManejoTipoFormaPago;
import modelo.FormaPago;
import modelo.TipoFormaPago;
import util.ClaseUtil;
import utiles.FormatNum;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FormaDePagoController implements Initializable {

    @FXML
    private JFXTextField txtEfectivo;
    @FXML
    private JFXButton btnBuscarTarjeta;

    public JFXTextField getTxtEfectivo() {
        return txtEfectivo;
    }

    public void setTxtEfectivo(JFXTextField txtEfectivo) {
        this.txtEfectivo = txtEfectivo;
    }
    @FXML
    private JFXTextField txtTarjeta;
    @FXML
    private JFXTextField txtCheque;
    @FXML
    private JFXTextField txtTotalFactura;

    Double totalPago;
    Double devuelta;
    int cliente;

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }
   
    public Double getDevuelta() {
        return devuelta;
    }

    public void setDevuelta(Double devuelta) {
        this.devuelta = devuelta;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }

    public JFXTextField getTxtTotalFactura() {
        return txtTotalFactura;
    }

    public void setTxtTotalFactura(JFXTextField txtTotalFactura) {
        this.txtTotalFactura = txtTotalFactura;
    }
    @FXML
    private JFXTextField txtDevuelta;
    @FXML
    private JFXButton btnAplicar;
    @FXML
    private JFXButton btnSalir;

    FormaPago formaPago;
    List<FormaPago> listaFormaPago = new ArrayList<>();

    TipoFormaPago tipoFormaPago;

    public List<FormaPago> getListaFormaPago() {
        return listaFormaPago;
    }

    public void setListaFormaPago(List<FormaPago> listaFormaPago) {
        this.listaFormaPago = listaFormaPago;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//
//        txtEfectivo.setText("0");
//        txtCheque.setText("0");
//        txtDevuelta.setText("0");
//        txtTarjeta.setText("0");
//        txtTotalFactura.setText("0");
//        tipoFormaPago = new TipoFormaPago();
    }

    @FXML
    private void btnAplicarActionEvent(ActionEvent event) {

        Double efectivo = Double.parseDouble(txtEfectivo.getText().isEmpty() ? "0" : txtEfectivo.getText());
        Double tarjeta = Double.parseDouble(txtTarjeta.getText().isEmpty() ? "0" : txtTarjeta.getText());
        Double cheque = Double.parseDouble(txtCheque.getText().isEmpty() ? "0" : txtCheque.getText());
        Double total = Double.parseDouble(txtTotalFactura.getText());

        if (efectivo > 0) {

            formaPago = new FormaPago();
            tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(1);
            formaPago.setTipoFormaPago(tipoFormaPago);
            formaPago.setMonto(efectivo);
            getListaFormaPago().add(formaPago);

        }

        if (tarjeta > 0) {

            formaPago = new FormaPago();
            tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(3);
            formaPago.setTipoFormaPago(tipoFormaPago);
            formaPago.setMonto(tarjeta);
            getListaFormaPago().add(formaPago);
        }

        if (cheque > 0) {

            formaPago = new FormaPago();
            tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(4);
            formaPago.setTipoFormaPago(tipoFormaPago);
            formaPago.setMonto(cheque);
            getListaFormaPago().add(formaPago);

        }

        totalPago = efectivo + tarjeta + cheque;

        devuelta = FormatNum.FormatearDouble(totalPago - total, 2);

//        if (!(Double.compare(totalPago, total) == 0)) {
        if (totalPago < total) {

            ClaseUtil.mensaje("El pago no puede ser  menor que el monto de la factura ");
            return;
        }

        txtDevuelta.setText(devuelta.toString());

    }

    @FXML
    private void btnSalirActionEvent(ActionEvent event) throws Throwable {

        Stage stage = (Stage) this.btnAplicar.getScene().getWindow();
        stage.close();

//        Node source = (Node) event.getSource();
//        Stage stage = (Stage) source.getScene().getWindow();
//        stage.close();
//        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
//        stage.close();
    }

    @FXML
    private void btnBuscarTarjetaActionEvent(ActionEvent event) {

        Double monto = ManejoCreditoCliente.getInstancia().getMontoCreditoCliente(getCliente());
        txtTarjeta.setText(monto.toString());
        txtEfectivo.setText("0.00");
    }

}
