/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.caja;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.caja.ManejoTurno;
import modelo.Turno;
import reporte.cajaChica.RptCajaChica;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class ReporteCuadreCajaController implements Initializable {

    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnSalir;

    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXComboBox<Turno> cbTurno;

    ObservableList<Turno> listaTurno = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFechaFinal.setValue(LocalDate.now());
        inicializarCombox();
    }

    private void inicializarCombox() {

        listaTurno.addAll(ManejoTurno.getInstancia().getListaTurno());
        cbTurno.setConverter(new StringConverter<Turno>() {

            @Override
            public String toString(Turno turno) {
                return turno.getNombre();
            }

            @Override
            public Turno fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTurno.setItems(listaTurno);
        cbTurno.getSelectionModel().select(0);

    }

    @FXML
    private void btnAceptarActionEvent(ActionEvent event) {

        try {

            Date fechaFinal = ClaseUtil.asDate(dpFechaFinal.getValue());
            int turno = cbTurno.getSelectionModel().getSelectedItem().getCodigo();
            RptCajaChica.getInstancia().imprimirCuadreCaja(fechaFinal, turno);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSalirActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();

    }

}
