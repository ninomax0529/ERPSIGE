/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.reporte.libroContable;

import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.SwingWorker;
import manejo.contabilidadd.CatalogoDao;
import manejo.contabilidadd.PeriodoContableDao;
import modelo.Catalogo;
import modelo.PeriodoContable;
import reporte.contabilidad.RptLibroContable;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLLibroMayorCController implements Initializable {

    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private Button btnAceptar;
    @FXML
    private DatePicker dpFechaFinal;
    @FXML
    private Button btnSalir;

    @FXML
    private JFXProgressBar pgBar;
    @FXML
    private TableView<Catalogo> tbCatalogo;
    @FXML
    private TableColumn<Catalogo, String> tcCodigo;
    @FXML
    private TableColumn<Catalogo, String> tcDscripcion;

    @FXML
    private JFXTextField txtBuscarCuenta;

    Task task;

    ObservableList<PeriodoContable> listaPeriodo = FXCollections.observableArrayList();
    ObservableList<Catalogo> listaCatalogo = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
        iniciazarFiltro();
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
        pgBar.setVisible(false);

    }

    private void inicializarTabla() {

        try {

            listaPeriodo.addAll(PeriodoContableDao.getInstancia().getPeriodoContable());

            tcCodigo.setCellValueFactory(new PropertyValueFactory<>("cuenta"));
            tcDscripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

            listaCatalogo.addAll(CatalogoDao.getInstancia().getCatalogo());
            tbCatalogo.setItems(listaCatalogo);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void iniciazarFiltro() {

        FilteredList<Catalogo> filteredData = new FilteredList<>(listaCatalogo, p -> true);

        txtBuscarCuenta.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(catalogo -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (catalogo.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (catalogo.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match

            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Catalogo> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbCatalogo.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbCatalogo.setItems(sortedData);
    }

    private void imprimir() throws ParseException {

        try {

            Date fecha = ClaseUtil.asDate(dpFechaInicio.getValue());

            Date fechaFin = ClaseUtil.asDate(dpFechaFinal.getValue());
            String cuenta = "";

            if (tbCatalogo.getSelectionModel().getSelectedIndex() != -1) {

                cuenta = " and c.cuenta like '"
                        + tbCatalogo.getSelectionModel().getSelectedItem().getCuenta() + "%' ";
                System.out.println("Imprimir mayor de " + cuenta);
                RptLibroContable.getInstancia().imprimirLibroMayorPorCuenta(fecha, fechaFin, cuenta);

            } else {

                RptLibroContable.getInstancia().imprimirLibroMayor(fecha, fechaFin);
            }

            System.out.println("Imprimir mayor de fuera " + cuenta);

//        dpFecha.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaPeriodo));
//        RptLibroContable.getInstancia().imprimirLibroMayor(fecha, fechaFin);
            tbCatalogo.getSelectionModel().clearSelection();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnAceptarActionEvent(ActionEvent event) {

        try {
            task = new Task();
            task.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnSalirActionEvent(ActionEvent event) {

        // get a handle to the stage
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    class Task extends SwingWorker<Void, Void> {

        @Override
        @SuppressWarnings("SleepWhileInLoop")
        public Void doInBackground() throws IOException {

            pgBar.setVisible(true);

            try {

                imprimir();

            } catch (ParseException ex) {

                Logger.getLogger(FXMLLibroMayorCController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;

        }

        @Override
        public void done() {

            pgBar.setVisible(false);

        }
    }

}
