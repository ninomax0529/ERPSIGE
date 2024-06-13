/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.reconciliacion.reconciliacionCliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.ReciboIngreso.ManejoReciboIngreso;
import manejo.reconciliacion.ManejoReconciliacionInternaCliente;
import modelo.DetalleReconciliacionInternaCliente;
import modelo.ReciboIngreso;
import modelo.ReconciliacionInternaCliente;
import reporte.cxc.RptReciboIngreso;
import reporte.reconciliacion.RptReconciliacionInterna;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ReconciliacionClienteController implements Initializable {

    @FXML
    private VBox vbVisorDeProgreso;
    @FXML
    private Label lbProgreso;
    @FXML
    private ProgressIndicator pgIndicador;
    @FXML
    private ProgressBar progresBar;
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
    private JFXButton btnVerDocumento;
    @FXML
    private JFXButton btnRuta;
    @FXML
    private JFXButton btnExportPdf;
    @FXML
    private TabPane tabPane;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private Label lbRutaArchivoHeder;
    @FXML
    private TableColumn<ReconciliacionInternaCliente, Integer> tbcNumero;
    @FXML
    private TableColumn<ReconciliacionInternaCliente, String> tbcFecha;
    @FXML
    private TableColumn<ReconciliacionInternaCliente, Double> tbcMonto;
    @FXML
    private TableColumn<ReconciliacionInternaCliente, String> tbcCliente;
    @FXML
    private TableColumn<ReconciliacionInternaCliente, String> tbcAnulado;
    @FXML
    private TableColumn<ReconciliacionInternaCliente, ReconciliacionInternaCliente> tbcVerDocumento;
    @FXML
    private TableColumn<ReconciliacionInternaCliente, ReconciliacionInternaCliente> tbcImprimir;
    @FXML
    private JFXCheckBox chImprimir;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXButton btnExportXLS;
    @FXML
    private JFXTextField txtValorTotal;
    @FXML
    private TableView<ReconciliacionInternaCliente> tbReconciliacionCliente;
    @FXML
    private TableView<DetalleReconciliacionInternaCliente> tbDetalleReconciliacion;
    @FXML
    private TableColumn<DetalleReconciliacionInternaCliente, String> tbcTipoDoc;
    @FXML
    private TableColumn<DetalleReconciliacionInternaCliente, String> tbcNumeroDoc;
    @FXML
    private TableColumn<DetalleReconciliacionInternaCliente, Double> tbcDebito;
    @FXML
    private TableColumn<DetalleReconciliacionInternaCliente, Double> tbcCredito;
    @FXML
    private TableColumn<DetalleReconciliacionInternaCliente, DetalleReconciliacionInternaCliente> tbcVerRecibo;

    ObservableList<ReconciliacionInternaCliente> lista = FXCollections.observableArrayList();
    ObservableList<DetalleReconciliacionInternaCliente> listaDetalle = FXCollections.observableArrayList();

    Date fechaIni, fechaFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaReconciliacion();
        iniciarTablaDetalle();

        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
        buscar();

    }

    private void iniciarTablaReconciliacion() {

        // listaEntrada.clear();
        tbReconciliacionCliente.setItems(lista);
        txtCantidad.setText(Integer.toString(lista.size()));

        tbcNumero.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcCliente.setCellValueFactory(new PropertyValueFactory<>("clinete"));
        tbcAnulado.setCellValueFactory(new PropertyValueFactory<>("anulada"));

        tbcAnulado.setCellValueFactory(
                cellData -> {

                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {

                        if (cellData.getValue().getAnulada()) {
                            property.setValue("Si");
                        } else {
                            property.setValue("No");
                        }

                    }
                    return property;
                });

        tbcCliente.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getCliente().getNombre());
                    }
                    return property;
                });

        tbcVerDocumento.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcVerDocumento.setCellFactory((TableColumn<ReconciliacionInternaCliente, ReconciliacionInternaCliente> param) -> {

            TableCell<ReconciliacionInternaCliente, ReconciliacionInternaCliente> cellsc = new TableCell<ReconciliacionInternaCliente, ReconciliacionInternaCliente>() {
                @Override
                public void updateItem(ReconciliacionInternaCliente item, boolean empty) {
                    super.updateItem(item, empty);
                    File imageFile;
                    Image img;
                    ImageView imageview;
                    Label label;

                    if (item != null) {

//                        imageFile = new File(getClass().getResource("/imagen/img_documento.jpg").toString());
                        label = new Label("Componente");
                        imageview = new ImageView(new Image(getClass().getResource("/imagen/img_documento.jpg").toString(), 40, 20, false, false));
                        imageview.setFitWidth(40);
                        imageview.setFitHeight(20);

                        VBox hbox = new VBox();

                        hbox.getChildren().addAll(imageview);

                        hbox.setAlignment(Pos.CENTER);

                        //Evento de la fila 
                        hbox.setOnMouseClicked((event) -> {

                            RptReconciliacionInterna.getInstancia().verReporte(item.getCodigo());
                        });

                        setGraphic(hbox);
                        setText(null);

                    } else {
                        setGraphic(null);
                        setText(null);
                    }
                }
            };

            return cellsc;
        });

        tbcImprimir.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcImprimir.setCellFactory((TableColumn<ReconciliacionInternaCliente, ReconciliacionInternaCliente> param) -> {

            TableCell<ReconciliacionInternaCliente, ReconciliacionInternaCliente> cellsc = new TableCell<ReconciliacionInternaCliente, ReconciliacionInternaCliente>() {
                @Override
                public void updateItem(ReconciliacionInternaCliente item, boolean empty) {
                    super.updateItem(item, empty);

                    Button btnHabilitar;

                    if (item != null) {

                        btnHabilitar = new Button();
                        btnHabilitar.setPrefSize(40.0, 15.0);

                        if (item.getImprimir()) {

                            btnHabilitar.setText("SI");

                            btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 10px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 9pt;");

                        } else {

                            btnHabilitar.setText("NO");
                            btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 10px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 9pt;");

                        }

                        HBox hbox = new HBox();

                        hbox.getChildren().add(btnHabilitar);

                        hbox.setAlignment(Pos.CENTER);

                        btnHabilitar.setOnMouseClicked((event) -> {

                            if (item.getImprimir()) {

                                item.setImprimir(false);
                                btnHabilitar.setText("NO");
                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 15px;\n"
                                        + "    -fx-b-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 9pt;");

                            } else {

                                item.setImprimir(true);
                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 9pt;");
                            }

                        });

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

    }

    public void iniciarTablaDetalle() {

        listaDetalle.clear();

        tbDetalleReconciliacion.setItems(listaDetalle);
        tbcTipoDoc.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tbcNumeroDoc.setCellValueFactory(new PropertyValueFactory<>("numeroDocumento"));
        tbcDebito.setCellValueFactory(new PropertyValueFactory<>("debito"));
        tbcCredito.setCellValueFactory(new PropertyValueFactory<>("credito"));

        tbcTipoDoc.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getTipoDocumento().getNombre());
                    }
                    return property;
                });

        tbcVerRecibo.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcVerRecibo.setCellFactory((TableColumn<DetalleReconciliacionInternaCliente, DetalleReconciliacionInternaCliente> param) -> {

            TableCell<DetalleReconciliacionInternaCliente, DetalleReconciliacionInternaCliente> cellsc = new TableCell<DetalleReconciliacionInternaCliente, DetalleReconciliacionInternaCliente>() {
                @Override
                public void updateItem(DetalleReconciliacionInternaCliente item, boolean empty) {
                    super.updateItem(item, empty);
                    File imageFile;
                    Image img;
                    ImageView imageview;
                    Label label;

                    if (item != null) {

//                        imageFile = new File(getClass().getResource("/imagen/img_documento.jpg").toString());
                        label = new Label("Componente");
                        imageview = new ImageView(new Image(getClass().getResource("/imagen/img_documento.jpg").toString(), 40, 20, false, false));
                        imageview.setFitWidth(40);
                        imageview.setFitHeight(20);

                        VBox hbox = new VBox();

                        hbox.getChildren().addAll(imageview);

                        hbox.setAlignment(Pos.CENTER);

                        //Evento de la fila 
                        hbox.setOnMouseClicked((event) -> {

                            if (item.getTipoDocumento().getCodigo() == 7) {

                                reporte.unidadnegocio.RptFactura.getInstancia().verFactura(item.getDocumento(),
                                        VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
                            }
                            if (item.getTipoDocumento().getCodigo() == 8) {

                                ReciboIngreso reci = ManejoReciboIngreso.getInstancia().getReciboIngreso(item.getDocumento());

                                String montoRec = " MONTO RECONCILIADO   " + item.getDebito() + "   DE   " + reci.getMonto();

                                RptReciboIngreso.getInstancia().imprimir(item.getDocumento(),montoRec);
                            }
//                          
                        });

                        setGraphic(hbox);
                        setText(null);

                    } else {
                        setGraphic(null);
                        setText(null);
                    }
                }
            };

            return cellsc;
        });

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/reconciliacion/reconciliacionCliente/RegistroReconciliacionCliente.fxml"));

        ClaseUtil.getStageModal(root);

    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        try {

            buscar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {
    }

    @FXML
    private void btnVerDocumentoActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnRutaActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnExportPdfActionEvent(ActionEvent event) {
    }

    @FXML
    private void chImprimirActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnExportXLSActionEvent(ActionEvent event) {
    }

    private void buscar() {

        Date fechaini, fechafin;
        fechaini = ClaseUtil.asDate(dpFechaInicio.getValue());
        fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());
        lista.clear();
        listaDetalle.clear();

        lista.addAll(ManejoReconciliacionInternaCliente.getInstancia().getLista(fechaini, fechafin));
        txtCantidad.setText(Integer.toString(lista.size()));

        txtValorTotal.clear();
        iniciazarFiltro();
    }

    private void iniciazarFiltro() {

        FilteredList<ReconciliacionInternaCliente> filteredData = new FilteredList<>(tbReconciliacionCliente.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getFecha() != null && filtro.getFecha().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getCliente() != null && filtro.getNombreCliente().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ReconciliacionInternaCliente> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbReconciliacionCliente.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbReconciliacionCliente.setItems(sortedData);
    }

    @FXML
    private void tbReconciliacionClienteMouseClick(MouseEvent event) {

        if (!(tbReconciliacionCliente.getSelectionModel().getSelectedIndex() == -1)) {

            int codigo = tbReconciliacionCliente.getSelectionModel().getSelectedItem().getCodigo();

            listaDetalle.clear();
            listaDetalle.addAll(ManejoReconciliacionInternaCliente.getInstancia().getDetalleReconciliacion(codigo));
            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(1);
            }
        }
    }

}
