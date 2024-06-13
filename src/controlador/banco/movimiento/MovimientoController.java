/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.banco.movimiento;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.banco.ManejoMovimientoBanco;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.nomina.ManejoRegistroHoraExtra;
import modelo.DetalleContratoDeServicio;
import modelo.DetalleEntradaDiario;
import modelo.Empleado;
import modelo.EntradaDiario;
import modelo.MovimientoBanco;
import modelo.RegistroHoraExtra;
import reporte.contabilidad.RptLibroContable;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class MovimientoController implements Initializable {

    @FXML
    private JFXTextField txtNoAsiento;
    @FXML
    private JFXTextField txtNoFactura;
    @FXML
    private JFXDatePicker dpFechaContabilidad;
    @FXML
    private JFXTextField txtConcepto;
    @FXML
    private JFXButton btnVerAsiento;
    @FXML
    private TableColumn<DetalleEntradaDiario, String> tbcCuenta;
    @FXML
    private TableColumn<DetalleEntradaDiario, String> tbcDescripcion;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbcDebito;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbcCredito;
    @FXML
    private TableView<DetalleEntradaDiario> tbDetalleEntrada;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbcDebitoEnt;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbcCreditoCr;

    @FXML
    private TextField txtTotalDebito;
    @FXML
    private TextField txtTotalCredito;

    /**
     * @return the MovimientoBanco
     */
    public MovimientoBanco getMovimientoBanco() {
        return MovimientoBanco;
    }

    /**
     * @param MovimientoBanco the MovimientoBanco to set
     */
    public void setMovimientoBanco(MovimientoBanco MovimientoBanco) {
        this.MovimientoBanco = MovimientoBanco;
    }

    @FXML
    private VBox vbVisorDeProgreso;
    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private HBox hbPermiso;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private JFXButton btnExportPdf;
    @FXML
    private TabPane tabPane;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<MovimientoBanco> tbMovimientoBanco;
    @FXML
    private TableColumn<MovimientoBanco, Integer> tbcCodigoMovimiento;
    @FXML
    private TableColumn<MovimientoBanco, String> tbcOperacion;
    @FXML
    private TableColumn<MovimientoBanco, String> tbcFecha;
    @FXML
    private TableColumn<MovimientoBanco, String> tbcBanco;
    @FXML
    private TableColumn<MovimientoBanco, String> tbcTipoMovimiento;
    @FXML
    private TableColumn<MovimientoBanco, String> tbcMovimiento;
    @FXML
    private TableColumn<MovimientoBanco, String> tbcTipoConcepto;
    @FXML
    private TableColumn<MovimientoBanco, String> tbcNumCuenta;
    @FXML
    private TableColumn<MovimientoBanco, MovimientoBanco> tbcAnulado;

    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXButton btnEditar;
    private MovimientoBanco MovimientoBanco;
    EntradaDiario entdiario = null;

    ObservableList<MovimientoBanco> listaMovimiento = FXCollections.observableArrayList();
    ObservableList<DetalleEntradaDiario> listaDetalleEnt = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTabla();
        iniciazarFiltro();
        inicializarTablaEntradaDiario();
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
        buscar();
    }

    private void iniciazarFiltro() {

        FilteredList<MovimientoBanco> filteredData = new FilteredList<>(tbMovimientoBanco.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(movimiento -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String upperCaseFilter = newValue.toLowerCase();

                if (movimiento.getNumeroCuenta().toLowerCase().contains(upperCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (movimiento.getConcepto().toLowerCase().contains(upperCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (movimiento.getCodigo().toString().toLowerCase().contains(upperCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (movimiento.getNumeroOperacion().toLowerCase().contains(upperCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (movimiento.getBanco().getNombre().toLowerCase().contains(upperCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (movimiento.getFechaOperacion().toString().toLowerCase().contains(upperCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<MovimientoBanco> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbMovimientoBanco.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbMovimientoBanco.setItems(sortedData);
    }

    public void inicializarTablaEntradaDiario() {

        tbcCuenta.setCellValueFactory(new PropertyValueFactory<>("cuenta"));
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("DescripcionCuenta"));
        tbcDebitoEnt.setCellValueFactory(new PropertyValueFactory<>("Debito"));
        tbcCreditoCr.setCellValueFactory(new PropertyValueFactory<>("Credito"));

        tbDetalleEntrada.setItems(listaDetalleEnt);

    }

    public void iniciarTabla() {

        tbcCodigoMovimiento.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        tbcBanco.setCellValueFactory(new PropertyValueFactory<>("banco"));

        tbcTipoConcepto.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        tbcTipoMovimiento.setCellValueFactory(new PropertyValueFactory<>("tipoMovimiento"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fechaOperacion"));
        tbcNumCuenta.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
        tbcOperacion.setCellValueFactory(new PropertyValueFactory<>("numeroOperacion"));
        tbcMovimiento.setCellValueFactory(new PropertyValueFactory<>("movimiento"));
        tbcDebito.setCellValueFactory(new PropertyValueFactory<>("debito"));
        tbcCredito.setCellValueFactory(new PropertyValueFactory<>("credito"));

        tbcBanco.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getBanco() != null) {
                        property.setValue(cellData.getValue().getBanco().getNombre());
                    }
                    return property;
                });

        tbcTipoMovimiento.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getTipoMovimiento() != null) {
                        property.setValue(cellData.getValue().getTipoMovimiento().getNombre());
                    }
                    return property;
                });

        tbcAnulado.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcAnulado.setCellFactory((TableColumn<MovimientoBanco, MovimientoBanco> param) -> {

            TableCell<MovimientoBanco, MovimientoBanco> cellsc = new TableCell<MovimientoBanco, MovimientoBanco>() {
                @Override
                public void updateItem(MovimientoBanco item, boolean empty) {
                    super.updateItem(item, empty);

                    Button btnHabilitar;

                    if (item != null) {

                        btnHabilitar = new Button();
                        btnHabilitar.setPrefSize(50.0, 20.0);

                        if (item.getAnulado()) {

                            btnHabilitar.setText("SI");

                            btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 10px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 12pt;");

                        } else {

                            btnHabilitar.setText("NO");
                            btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 10px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 12pt;");

                        }

                        HBox hbox = new HBox();

                        hbox.getChildren().add(btnHabilitar);

                        hbox.setAlignment(Pos.CENTER);

                        setGraphic(btnHabilitar);
                        setText(null);
                    } else {
                        setGraphic(null);
                        setText(null);
                    }
                }
            };
            return cellsc;
        });

        listaMovimiento.clear();

        tbMovimientoBanco.setItems(listaMovimiento);

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/banco/movimiento/RegistroMovimientoBanco.fxml"));

        ClaseUtil.getStageModal(root);

        buscar();

    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {
        buscar();
    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {

        if (tbMovimientoBanco.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje(" Tiene que seleccionar un registro ");
            return;
        }

        Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("Seguro que quiere anular este regsitro ? ");

        if (ok.get() == ButtonType.OK || ok.get() == ButtonType.YES) {

            try {

                MovimientoBanco reg = tbMovimientoBanco.getSelectionModel().getSelectedItem();

                reg.setAnulado(true);
                ManejoMovimientoBanco.getInstancia().actualizar(reg);
                tbMovimientoBanco.refresh();
                ClaseUtil.mensaje("Registro anulado exitosamente  ");

            } catch (Exception e) {
                e.printStackTrace();
                ClaseUtil.mensaje("Hubo un error anulando el registro " + e.getMessage());

            }

        }
    }

    @FXML
    private void btnExportPdfActionEvent(ActionEvent event) {
        
        try {
            
          ClaseUtil.exportarDAtos(tbMovimientoBanco);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbMovimientoBanco.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un banco ");

        } else {

            setMovimientoBanco(tbMovimientoBanco.getSelectionModel().getSelectedItem());

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/banco/movimiento/RegistroMovimientoBanco.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroMovimientoBancoController controller = loader.getController();

            controller.setEditar(true);
            controller.setMovimientoBanco(getMovimientoBanco());

            controller.llenarCampo();

            ClaseUtil.getStageModal(root);

            buscar();

        }

    }

    private void buscar() {

        Date fechaini, fechafin;
        fechaini = ClaseUtil.asDate(dpFechaInicio.getValue());
        fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());
        listaMovimiento.clear();

        listaMovimiento.addAll(ManejoMovimientoBanco.getInstancia().getMovimientoBanco(fechaini, fechafin));
        txtCantidad.setText(Integer.toString(listaMovimiento.size()));

    }

    @FXML
    private void btnVerAsientoActionEvent(ActionEvent event) {

        if (!(entdiario == null)) {
            RptLibroContable.getInstancia().entradaDeDiario(entdiario.getCodigo());
        }
    }

    @FXML
    private void tbMovimientoBancoMouseClick(MouseEvent event) {

        if (!(tbMovimientoBanco.getSelectionModel().getSelectedIndex() == -1)) {

            int codigoMov = tbMovimientoBanco.getSelectionModel().getSelectedItem().getCodigo();
            entdiario = EntradaDiarioDao.getInstancia().getEntradaDiarioPorDocumento(codigoMov, 25);

            System.out.println("entdiario : " + entdiario);
            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(1);
            }

            if (!(entdiario == null)) {

                txtNoAsiento.setText(entdiario.getNumero().toString());
                txtNoFactura.setText(entdiario.getNumeroDocumento());
                dpFechaContabilidad.setValue(util.ClaseUtil.convertToLocalDateViaMilisecond(entdiario.getFecha()));
                txtConcepto.setText(entdiario.getConcepto());
                listaDetalleEnt.clear();
                listaDetalleEnt.addAll(EntradaDiarioDao.getInstancia().getDetalleEntradaDiario(entdiario));
                txtTotalCredito.setText(getTotalCredito().toString());
                txtTotalDebito.setText(getTotalDebito().toString());
            } else {
                txtNoAsiento.clear();
                txtNoFactura.clear();
                dpFechaContabilidad.setValue(null);
                txtConcepto.clear();
                txtTotalCredito.clear();
                txtTotalDebito.clear();
                listaDetalleEnt.clear();

            }

        }
    }

    public Double getTotalCredito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detEntrada = tbDetalleEntrada.getItems();

        for (int i = 0; i < detEntrada.size(); i++) {

            total += detEntrada.get(i).getCredito();

        }

        return util.ClaseUtil.roundDouble(total, 4);

    }

    public Double getTotalDebito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detentrada = tbDetalleEntrada.getItems();

        for (int i = 0; i < detentrada.size(); i++) {
            total += detentrada.get(i).getDebito();
        }

        return util.ClaseUtil.roundDouble(total, 4);

    }
}
