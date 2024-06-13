/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.reportes.flota;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import modelo.Cliente;
import reporte.gestionDeFlota.RptFlota;
//import reporte.factura.RptFactura;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ReporteDeFlotaController implements Initializable {

    @FXML
    private JFXCheckBox chEntreFecha;
    Cliente cliente;
    @FXML
    private VBox vbEntreFecha;
    @FXML
    private JFXDatePicker dpFechaDesde;
    @FXML
    private JFXDatePicker dpFechaHasta;
    @FXML
    private JFXButton btnVerReporte;

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
        vbEntreFecha.setDisable(true);

    }

    @FXML
    private void chEntreFechaActionEvent(ActionEvent event) {

        if (chEntreFecha.isSelected()) {
            vbEntreFecha.setDisable(false);
        } else {
            vbEntreFecha.setDisable(true);
        }
    }

    @FXML
    private void btnVerReporteActionEvent(ActionEvent event) {

        Date fi = ClaseUtil.asDate(dpFechaDesde.getValue()),
                ff = ClaseUtil.asDate(dpFechaHasta.getValue());
        String sqlCriterio = "", sqlParam = "";

        if (chEntreFecha.isSelected()) {

            sqlParam = " and  af.fecha between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  ";
            sqlCriterio=" Fecha desde: "+ new SimpleDateFormat("yyyy-MM-dd").format(fi)+" \n\n Fecha hasta: "+ new SimpleDateFormat("yyyy-MM-dd").format(ff);
        }

//
        RptFlota.getInstancia().imprimir(sqlParam, sqlCriterio);

    }

}
