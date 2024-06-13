/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.cierre;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import manejo.articulo.ManejoAlmacen;
import manejo.inventario.cierre.ManejoCierreMensualInventario;
import manejo.unidad.ManejoUnidad;
import modelo.Almacen;
import modelo.CierreMensualInventario;
import modelo.DetalleCierreMensualInventario;
import modelo.Unidad;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroCierreMensualInventarioController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaCierre;
    @FXML
    private JFXButton btnProcesar;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<DetalleCierreMensualInventario> tbDetalleCierreMensualInventario;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, Double> tbcInventarioInicial;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, Double> tbcEntrada;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, Double> tbcSalida;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, Double> tbcInventarioFinal;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, String> tbcUnidadSalida;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, Double> tbcCostoUnitario;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, Double> tbcCostoInventario;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidadArticulo;
    @FXML
    private JFXTextField txtValorTotal;
    Date fechaini, fechafin;

    ObservableList<DetalleCierreMensualInventario> listadetalle = FXCollections.observableArrayList();
    ObservableList<Unidad> listaUnidad = FXCollections.observableArrayList();
    ObservableList<Almacen> listaalmacen = FXCollections.observableArrayList();
    @FXML
    private VBox vbDetalleTabla;

    CierreMensualInventario cierreMensualInventario;

    String mensaje = "";
    @FXML
    private JFXButton btnExportar;
    @FXML
    private JFXTextField txtNumeroCierre;
    @FXML
    private JFXComboBox<Almacen> cbalmacen;
    @FXML
    private JFXComboBox<Unidad> cbUnidad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaDetalle();
        iniciazarFiltro();
        inicializarCombox();
        dpFechaCierre.setValue(LocalDate.now());
        nuevo();
    }

    private void inicializarCombox() {

        cbUnidad.setConverter(new StringConverter<Unidad>() {

            @Override
            public String toString(Unidad unidad) {
                return String.valueOf(unidad.getDescripcion());
            }

            @Override
            public Unidad fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbalmacen.setConverter(new StringConverter<Almacen>() {

            @Override
            public String toString(Almacen almacen) {
                return String.valueOf(almacen.getNombre());
            }

            @Override
            public Almacen fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaUnidad.addAll(ManejoUnidad.getInstancia().getLista());
        listaalmacen.addAll(ManejoAlmacen.getInstancia().getLista());

        cbUnidad.setItems(listaUnidad);
        cbalmacen.setItems(listaalmacen);

    }

    private void iniciazarFiltro() {

        FilteredList<DetalleCierreMensualInventario> filteredData = new FilteredList<>(tbDetalleCierreMensualInventario.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(cierre -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
////                System.out.println("lowerCaseFilter "+lowerCaseFilter);

                if (cierre.getArticulo().getCodigo().toString().contains(lowerCaseFilter)) {
                    System.out.println("lowerCaseFilter " + lowerCaseFilter);
                    return true; // Filter matches first name.
                } else if (cierre.getDescripcionArticulo().contains(lowerCaseFilter)) {
                    System.out.println("lowerCaseFilter " + lowerCaseFilter);
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<DetalleCierreMensualInventario> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbDetalleCierreMensualInventario.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbDetalleCierreMensualInventario.setItems(sortedData);
    }

    public void iniciarTablaDetalle() {

        listadetalle.clear();

        tbDetalleCierreMensualInventario.setItems(listadetalle);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcionArticulo"));
        tbcUnidadSalida.setCellValueFactory(new PropertyValueFactory<>("unidadSalida"));
        tbcInventarioInicial.setCellValueFactory(new PropertyValueFactory<>("inventarioInicial"));
        tbcInventarioFinal.setCellValueFactory(new PropertyValueFactory<>("inventarioFinal"));
        tbcSalida.setCellValueFactory(new PropertyValueFactory<>("cantidadSalida"));
        tbcEntrada.setCellValueFactory(new PropertyValueFactory<>("cantidadEntrada"));
        tbcCostoUnitario.setCellValueFactory(new PropertyValueFactory<>("costoUnitario"));
        tbcCostoInventario.setCellValueFactory(new PropertyValueFactory<>("costo"));

        tbcCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });

        tbcDescripcionArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getDescripcionArticulo());
                    }
                    return property;
                });

        tbcUnidadSalida.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                 
                    
                    if (cellData.getValue() != null) {
                        property.setValue(ManejoUnidad.getInstancia().getUnidad(cellData.getValue().getUnidadSalida()).getDescripcion());
                    }
                    return property;
                });
    }

    @FXML
    private void btnProcesarActionEvent(ActionEvent event) {

        try {

            if (cbalmacen.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que  seleccioanr un  almacen");
                cbalmacen.requestFocus();
                return;

            }

            if (cbUnidad.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que  seleccioanr una unidad ");
                cbUnidad.requestFocus();
                return;

            }

//            procesar();
            runTaskBuscarArticulo();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (listadetalle.size() <= 0) {
                ClaseUtil.mensaje("No hay articulo para el cierre");
                return;
            }

            Optional<ButtonType> ok = ClaseUtil.confirmarMensaje(" Seguro que quiere guardar el cierre");

            if (ok.get() == ButtonType.OK) {

                cierreMensualInventario.setFecha(fechafin);
                cierreMensualInventario.setCantidadArticulo(Integer.parseInt(txtCantidadArticulo.getText()));
                cierreMensualInventario.setEstado(0);
                cierreMensualInventario.setHora(new Date());
                cierreMensualInventario.setUsuario(VariablesGlobales.USUARIO);
                cierreMensualInventario.setFechaRegistro(new Date());
                cierreMensualInventario.setComentario(txtComentario.getText().isEmpty() ? "n/a" : txtComentario.getText());
                cierreMensualInventario.setValorInventario(costoInventario());
                cierreMensualInventario.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

                cierreMensualInventario.setDetalleCierreMensualInventarioCollection(listadetalle);

                ManejoCierreMensualInventario.getInstancia().salvar(cierreMensualInventario);
                ClaseUtil.mensaje("Cierre Guardado exitosamente");

                nuevo();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tbDetalleEntradaInventarioMouseCliked(MouseEvent event) {
    }

    private void runTaskBuscarArticulo() {

        ProgressBar progress = new ProgressBar();
        progress.setMaxWidth(vbDetalleTabla.getPrefWidth());

        vbDetalleTabla.getChildren().add(progress);
//
        btnProcesar.setDisable(true);

        Task<Void> longTask = new Task<Void>() {
            boolean existe = false;

            @Override
            protected Void call() throws Exception {

                try {

                    System.out.println("Entre");

                    fechafin = ClaseUtil.asDate(dpFechaCierre.getValue());
                    String fechafinstr = new SimpleDateFormat("dd-MM-YYY").format(fechafin);

//            fechafin = new SimpleDateFormat().parse(fechafinstr);
                    System.out.println("Fecha cierre " + fechafin);
                    existe = ManejoCierreMensualInventario.getInstancia().existeCierreMensualInventario(fechafin);

                    if (existe) {

                        mensaje = " Existe un cierre de mes en  esta fecha " + fechafinstr;
                        btnGuardar.setDisable(true);
                        return null;
                    }

                    procesar();
                    mensaje = " Poceso Realizado exitosamente al " + fechafinstr;
                    btnGuardar.setDisable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        };

        //When long task ends
        longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {

                vbDetalleTabla.getChildren().remove(progress);
                btnProcesar.setDisable(false);
                txtCantidadArticulo.setText(Integer.toString(listadetalle.size()));
                txtValorTotal.setText(costoInventario().toString());

                ClaseUtil.mensaje(mensaje);

            }
        });

        progress.progressProperty().bind(longTask.progressProperty());
        new Thread(longTask).start();

    }

    private void procesar() {

        try {

            System.out.println("Procesando");

            listadetalle.clear();
            int unidad = cbUnidad.getSelectionModel().getSelectedItem().getCodigo();
            int almacen = cbalmacen.getSelectionModel().getSelectedItem().getCodigo();

            fechafin = ClaseUtil.asDate(dpFechaCierre.getValue());

            List<DetalleCierreMensualInventario> lista = ManejoCierreMensualInventario.getInstancia()
                    .getMovimientoArticulo(fechafin, fechafin, almacen, unidad, cierreMensualInventario);

            listadetalle.addAll(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void nuevo() {

        cierreMensualInventario = new CierreMensualInventario();
        listadetalle.clear();
        txtFiltro.clear();
        btnGuardar.setDisable(true);
        txtValorTotal.clear();
        txtCantidadArticulo.clear();
        txtNumeroCierre.setText(ManejoCierreMensualInventario.getInstancia().getNumero().toString());

    }

    private Double costoInventario() {

        Double costo = 0.00;
        for (DetalleCierreMensualInventario det : listadetalle) {

            costo += det.getCosto();
        }
        return ClaseUtil.roundDouble(costo, 2);
    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) {

        try {

            util.ClaseUtil.exportarDAtos(tbDetalleCierreMensualInventario);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cbalmacenActionEvent(ActionEvent event) {
    }

    @FXML
    private void cbUnidadAtionevent(ActionEvent event) {
    }
}
