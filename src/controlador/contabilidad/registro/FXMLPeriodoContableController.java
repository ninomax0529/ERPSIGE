/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.registro;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.SwingWorker;
import manejo.contabilidadd.EstadoPeriodoDao;
import manejo.contabilidadd.PeriodoContableDao;
import modelo.EstadoPeriodo;
import modelo.PeriodoContable;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class FXMLPeriodoContableController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private Button btnAceptar;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private Button btnSalir;
    @FXML
    private JFXTextField txtBuscarPeriodo;
    @FXML
    private TableView<PeriodoContable> tbPeriodoContable;
    @FXML
    private TableColumn<PeriodoContable, String> tcPeriodo;
    @FXML
    private TableColumn<PeriodoContable, String> tcEstado;
    @FXML
    private JFXProgressBar pgBar;
    Task task;
    @FXML
    private JFXComboBox<EstadoPeriodo> cbEstadoPeriodo;
    PeriodoContable periodoContable;

    ObservableList<PeriodoContable> listaPeriodo = FXCollections.observableArrayList();
    ObservableList<EstadoPeriodo> listaEstadoPeriodo = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
        inicializarTabla();
        inicializarComboBox();
        iniciazarFiltro();
        pgBar.setVisible(false);

    }

    private void inicializarComboBox() {

        listaEstadoPeriodo.setAll(EstadoPeriodoDao.getInstancia().getEstado());

        cbEstadoPeriodo.setItems(listaEstadoPeriodo);

        cbEstadoPeriodo.setConverter(new StringConverter<EstadoPeriodo>() {

            @Override
            public String toString(EstadoPeriodo u) {
                return u.getNombre();
            }

            @Override
            public EstadoPeriodo fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

    }

    private void iniciazarFiltro() {

        FilteredList<PeriodoContable> filteredData = new FilteredList<>(listaPeriodo, p -> true);

        txtBuscarPeriodo.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(periodo -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (periodo.getPeriodo().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (periodo.getPeriodo().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match

            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<PeriodoContable> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbPeriodoContable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbPeriodoContable.setItems(sortedData);
    }

    private void inicializarTabla() {

        try {

            tcPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));
            tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

            listaPeriodo.addAll(PeriodoContableDao.getInstancia().getPeriodoContable());

            tbPeriodoContable.setItems(listaPeriodo);

            tcEstado.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getEstado() != null) {
                            property.setValue(cellData.getValue().getEstado().getNombre());
                        }
                        return property;
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnAceptarActionEvent(ActionEvent event) {

        try {

            if (!(cbEstadoPeriodo.getSelectionModel().getSelectedIndex() == -1)) {

                if (!(periodoContable == null)) {

                    periodoContable = tbPeriodoContable.getSelectionModel().getSelectedItem();
                    periodoContable.setEstado(cbEstadoPeriodo.getValue());

                    PeriodoContableDao.getInstancia().salvar(periodoContable);

                    tbPeriodoContable.getSelectionModel().clearSelection();
                    cbEstadoPeriodo.getSelectionModel().clearSelection();

                    listaPeriodo.clear();
                    listaPeriodo.addAll(PeriodoContableDao.getInstancia().getPeriodoContable());
                    tbPeriodoContable.setItems(listaPeriodo);
                    iniciazarFiltro();
                }

                ClaseUtil.mensaje("Actualizacion Exitosa");
                return;
            }

            task = new Task();
            task.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void salvar() {

        try {

            Date fechaIni = ClaseUtil.asDate(dpFechaInicio.getValue());
            Date fechaFin = ClaseUtil.asDate(dpFechaFinal.getValue());
            int desde, hasta;

            desde = ClaseUtil.getMes(fechaIni);
            hasta = ClaseUtil.getMes(fechaFin);
            
            System.out.println("Desde " + desde + " hasta " + hasta);
            
            for (int i = desde; i <= hasta; i++) {

                periodoContable = PeriodoContableDao.getInstancia().getPeriodoContable(fechaIni);
                System.out.println("valor periodo" + periodoContable + " i " + i);

                if ((periodoContable == null)) {

//                    System.out.println("El periodo no existe " + fechaIni);
//
                    periodoContable = new PeriodoContable();
                    periodoContable.setEstado(EstadoPeriodoDao.getInstancia().getEstado().get(0));
                    periodoContable.setFechaInicio(fechaIni);
                    periodoContable.setFechaFinal(ClaseUtil.getFechaUltimoDiaDelMes(fechaIni));
                    periodoContable.setFechaRegistro(new Date());
                    periodoContable.setPeriodo(ClaseUtil.getMesAno(fechaIni));
//                    periodoContable.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
                    PeriodoContableDao.getInstancia().salvar(periodoContable);

                }
                fechaIni = ClaseUtil.FechaMesDespues(fechaIni, 1);

            }

            listaPeriodo.clear();
            listaPeriodo.addAll(PeriodoContableDao.getInstancia().getPeriodoContable());
           
            iniciazarFiltro();

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

    @FXML
    private void tbPeriodoContableMouseClick(MouseEvent event) {

        try {
            if (!(tbPeriodoContable.getSelectionModel().getSelectedIndex() == -1)) {

                periodoContable = tbPeriodoContable.getSelectionModel().getSelectedItem();
                txtBuscarPeriodo.setText(periodoContable.getPeriodo());

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    class Task extends SwingWorker<Void, Void> {

        @Override
        @SuppressWarnings("SleepWhileInLoop")
        public Void doInBackground() throws IOException {

            pgBar.setVisible(true);

            try {
                salvar();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        public void done() {

            pgBar.setVisible(false);

        }
    }

 

}
