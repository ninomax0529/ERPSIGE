/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.venta.facturacion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controlador.venta.puntoVenta.FacturacionTochController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
public class FormaPagoController implements Initializable {

    @FXML
    private JFXTextField txtTotalFactura;
    @FXML
    private JFXTextField txtEfectivo;
    @FXML
    private JFXTextField txtTarjeta;
    @FXML
    private JFXTextField txtCheque;
    @FXML
    private JFXTextField txtDevuelta;
    BorderPane bpPrincipal;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn3;
    @FXML
    private Button btn6;
    @FXML
    private Button btn9;
    @FXML
    private TextField txtCAtidad;
    @FXML
    private Button btnPunto;
    @FXML
    private Button btn0;
    @FXML
    private Button btnTarjeta;
    @FXML
    private Button btnCheque;
    @FXML
    private Button btnEfectivo;
    @FXML
    private Button btnLimpiar;

    public BorderPane getBpPrincipal() {
        return bpPrincipal;
    }

    public void setBpPrincipal(BorderPane bpPrincipal) {
        this.bpPrincipal = bpPrincipal;
    }

    Double totalPago;
    Double devuelta, montoEfectivo, montoCheque, montoTarjeta, montoFactura;
    boolean aplicoFormaPago = false;
    Integer cantidad = 0;
    String numStr;

    public boolean isAplicoFormaPago() {
        return aplicoFormaPago;
    }

    public void setAplicoFormaPago(boolean aplicoFormaPago) {
        this.aplicoFormaPago = aplicoFormaPago;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }

    public Double getDevuelta() {
        return devuelta;
    }

    public void setDevuelta(Double devuelta) {
        this.devuelta = devuelta;
    }

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

    public JFXTextField getTxtTotalFactura() {
        return txtTotalFactura;
    }

    public void setTxtTotalFactura(JFXTextField txtTotalFactura) {
        this.txtTotalFactura = txtTotalFactura;
    }

    public JFXTextField getTxtEfectivo() {
        return txtEfectivo;
    }

    public void setTxtEfectivo(JFXTextField txtEfectivo) {
        this.txtEfectivo = txtEfectivo;
    }

    public JFXTextField getTxtTarjeta() {
        return txtTarjeta;
    }

    public void setTxtTarjeta(JFXTextField txtTarjeta) {
        this.txtTarjeta = txtTarjeta;
    }

    public JFXTextField getTxtCheque() {
        return txtCheque;
    }

    public void setTxtCheque(JFXTextField txtCheque) {
        this.txtCheque = txtCheque;
    }

    public JFXTextField getTxtDevuelta() {
        return txtDevuelta;
    }

    public void setTxtDevuelta(JFXTextField txtDevuelta) {
        this.txtDevuelta = txtDevuelta;
    }
    @FXML
    private JFXButton btnAplicar;

    FacturacionTochController facturacionTochController;

    public FacturacionTochController getFacturacionTochController() {
        return facturacionTochController;
    }

    public void setFacturacionTochController(FacturacionTochController facturacionTochController) {
        this.facturacionTochController = facturacionTochController;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtDevuelta.setText("0.00");
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
        totalPago = FormatNum.FormatearDouble(totalPago, 2);
        System.out.println("efectivo " + efectivo + " " + tarjeta + " " + cheque + " total pago " + totalPago);

        devuelta = FormatNum.FormatearDouble(totalPago - total, 2);

        if (getListaFormaPago().size() < 0) {

            ClaseUtil.mensaje("Tiene que aplicar una forma de pago");
            return;
        }

//        getFacturacionTochController().setListFormaPagoCollection(getListaFormaPago());
//        if (!(Double.compare(totalPago, total) == 0)) {
        if (totalPago < total) {

            ClaseUtil.mensaje("El pago no puede ser  menor que el monto de la factura ");
            return;
        }

        txtDevuelta.setText(devuelta.toString());
//        bpPrincipal.setRight(null);
//        aplicoFormaPago = true;
        Stage stage = (Stage) this.btnAplicar.getScene().getWindow();
        stage.close();

    }

    private void btnSalirActionEvent(ActionEvent event) {
        bpPrincipal.setRight(null);

    }

    @FXML
    private void btn1ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 1;
        txtCAtidad.setText(numStr);

    }

    @FXML
    private void btn2ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 2;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn4ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 4;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn5ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 5;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn7ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 7;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn8ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 8;
        txtCAtidad.setText(numStr);

    }

    @FXML
    private void btn3ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 3;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn6ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 6;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn9ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 9;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btnPuntoActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + ".";
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn0ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + "0";
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btnTarjetaActionEvent(ActionEvent event) {

        if (txtCAtidad.getText().isEmpty()) {

            utiles.ClaseUtil.mensaje("Tiene que digitar un valor ");

            return;
        }

        txtTarjeta.setText(txtCAtidad.getText());

        montoFactura = Double.parseDouble(txtTotalFactura.getText().isEmpty() ? "0" : txtTotalFactura.getText());
        montoEfectivo = Double.parseDouble(txtEfectivo.getText().isEmpty() ? "0" : txtEfectivo.getText());
        montoCheque = Double.parseDouble(txtCheque.getText().isEmpty() ? "0" : txtCheque.getText());
        montoTarjeta = Double.parseDouble(txtTarjeta.getText().isEmpty() ? "0" : txtTarjeta.getText());

        totalPago = montoEfectivo + montoTarjeta + montoCheque;

        devuelta = FormatNum.FormatearDouble(totalPago - montoFactura, 2);
        txtDevuelta.setText(devuelta.toString());
        txtCAtidad.clear();
    }

    @FXML
    private void btnChequeActionEvent(ActionEvent event) {

        if (txtCAtidad.getText().isEmpty()) {

            utiles.ClaseUtil.mensaje("Tiene que digitar  un valor ");

            return;
        }
        txtCheque.setText(txtCAtidad.getText());

        montoFactura = Double.parseDouble(txtTotalFactura.getText().isEmpty() ? "0" : txtTotalFactura.getText());
        montoEfectivo = Double.parseDouble(txtEfectivo.getText().isEmpty() ? "0" : txtEfectivo.getText());
        montoCheque = Double.parseDouble(txtCheque.getText().isEmpty() ? "0" : txtCheque.getText());
        montoTarjeta = Double.parseDouble(txtTarjeta.getText().isEmpty() ? "0" : txtTarjeta.getText());

        totalPago = montoEfectivo + montoTarjeta + montoCheque;

        devuelta = FormatNum.FormatearDouble(totalPago - montoFactura, 2);
        txtDevuelta.setText(devuelta.toString());
        txtCAtidad.clear();

    }

    @FXML
    private void btnEfectivoActionEvent(ActionEvent event) {

        if (txtCAtidad.getText().isEmpty()) {

            utiles.ClaseUtil.mensaje("Tiene que digitar un valor ");

            return;
        }

        txtEfectivo.setText(txtCAtidad.getText());

        montoFactura = Double.parseDouble(txtTotalFactura.getText().isEmpty() ? "0" : txtTotalFactura.getText());
        montoEfectivo = Double.parseDouble(txtEfectivo.getText().isEmpty() ? "0" : txtEfectivo.getText());
        montoCheque = Double.parseDouble(txtCheque.getText().isEmpty() ? "0" : txtCheque.getText());
        montoTarjeta = Double.parseDouble(txtTarjeta.getText().isEmpty() ? "0" : txtTarjeta.getText());

        totalPago = montoEfectivo + montoTarjeta + montoCheque;

        devuelta = FormatNum.FormatearDouble(totalPago - montoFactura, 2);
        txtDevuelta.setText(devuelta.toString());
        txtCAtidad.clear();
    }

    @FXML
    private void btnLimpiarActionEvent(ActionEvent event) {

        txtCAtidad.clear();
    }

}
