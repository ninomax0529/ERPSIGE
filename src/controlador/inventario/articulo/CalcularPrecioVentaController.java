/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import util.ClaseUtil;
import utiles.FormatNum;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class CalcularPrecioVentaController implements Initializable {

    @FXML
    private JFXTextField txtPrecioCompra;
    @FXML
    private JFXTextField txtPorciento;
    @FXML
    private JFXTextField txtPrecioVenta;
    @FXML
    private JFXTextField txtMargenUtilidad;
    @FXML
    private JFXButton btnAplicar;
    @FXML
    private JFXButton btnSalir;
    Double precioVenta;
    Double precioCompra;
    Double porcientoUtilidad;
    Double margenUtilidad;
    int precioVentaSeleccionado = 1;
    final ToggleGroup group = new ToggleGroup();

    public int getPrecioVentaSeleccionado() {
        return precioVentaSeleccionado;
    }

    public void setPrecioVentaSeleccionado(int precioVentaSeleccionado) {
        this.precioVentaSeleccionado = precioVentaSeleccionado;
    }

    public Double getMargenUtilidad() {
        return margenUtilidad;
    }

    public void setMargenUtilidad(Double margenUtilidad) {
        this.margenUtilidad = margenUtilidad;
    }
    @FXML
    private JFXRadioButton rbPrecioVentata1;
    @FXML
    private JFXRadioButton rbPrecioVentata2;
    @FXML
    private JFXRadioButton rbPrecioVentata3;

    public Double getPorcientoUtilidad() {
        return porcientoUtilidad;
    }

    public void setPorcientoUtilidad(Double porcientoUtilidad) {
        this.porcientoUtilidad = porcientoUtilidad;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rbPrecioVentata1.setToggleGroup(group);
        rbPrecioVentata2.setToggleGroup(group);
        rbPrecioVentata3.setToggleGroup(group);
        rbPrecioVentata1.setSelected(true);
        txtPorciento.requestFocus();
    }

    @FXML
    private void btnAplicarActionEvent(ActionEvent event) {

        if (txtPorciento.getText().isEmpty()) {

            ClaseUtil.mensaje("Tiene que digitar un  la utilidad");
            txtPorciento.requestFocus();
            return;
        }

        Double precioCompraLocal = Double.parseDouble(txtPrecioCompra.getText()), precioVentaCal = 0.00,
                porciento = Double.parseDouble(txtPorciento.getText()), margenUtilidadLocal = 0.00;

        precioVentaCal = ClaseUtil.getPreciodeVenta(precioCompraLocal, porciento);
        margenUtilidadLocal = ClaseUtil.getMargenUtilidad(precioCompraLocal, precioVentaCal);

        precioVentaCal = FormatNum.FormatearDouble(precioVentaCal, 0);
        margenUtilidadLocal = FormatNum.FormatearDouble(margenUtilidadLocal, 0);
        txtPrecioVenta.setText(precioVentaCal.toString());

        txtMargenUtilidad.setText(margenUtilidadLocal.toString());

        if (rbPrecioVentata1.isSelected()) {
            precioVentaSeleccionado = 1;
        } else if (rbPrecioVentata2.isSelected()) {
            precioVentaSeleccionado = 2;
        } else if (rbPrecioVentata3.isSelected()) {
            precioVentaSeleccionado = 3;
        }

        setPrecioVenta(precioVentaCal);
        setPorcientoUtilidad(porciento);
        setMargenUtilidad(margenUtilidadLocal);

    }

    public void llenarCampo() {

        txtPrecioCompra.setText(getPrecioCompra().toString());

    }

    @FXML
    private void btnSalirActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();

    }

//    @FXML public void limpiarContenidos(){
// 
//        ObservableList<Node> elementosNumeros = layoutNumeros.getChildren();
//         for (Node n: elementosNumeros
//              ) {
//             ((TextField)n).setText("");
//         }
// 
//         ObservableList<Node> elementosLetras = layoutLetras.getChildren();
//         for (Node n: elementosLetras
//              ) {
//             ((TextField)n).setText("");
//         }
// 
//         elementosLetras = null;
//         elementosNumeros = null;
//    }
}
