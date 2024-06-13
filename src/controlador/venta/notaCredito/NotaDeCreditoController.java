/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.notaCredito;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.scene.control.ButtonType;
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
import javafx.scene.layout.VBox;
import manejo.contabilidadd.DocumentoAnuladoDao;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.notaCredito.ManejoNotaDeCredito;
import modelo.DetalleNotaCredito;
import modelo.EntradaDiario;
import modelo.EntradaInventario;
import modelo.NotaCredito;
import reporte.factura.RptFactura;
import reporte.notaCredito.RptNotaCredito;

import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class NotaDeCreditoController implements Initializable {

    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidadArticulo;
    @FXML
    private JFXTextField txtValorTotal;
    @FXML
    private TableColumn<EntradaInventario, Date> tbcFecha;
    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private TableView<NotaCredito> tbNotaCredito;
    @FXML
    private TableColumn<NotaCredito, String> tbcConcepto;

    @FXML
    private TableColumn<NotaCredito, String> tbcClienteoSuplidor;
    @FXML
    private TableColumn<NotaCredito, String> tbcAnulada;

    @FXML
    private TableColumn<NotaCredito, NotaCredito> tbcVerNotaCredito;
    @FXML
    private TableColumn<NotaCredito, Double> tbcMontoNc;
    @FXML
    private TableColumn<NotaCredito, String> tbcTipoNc;
    @FXML
    private TableColumn<NotaCredito, Double> tbcMontoFactura;

    @FXML
    private TableColumn<NotaCredito, String> tbcNcf;
    @FXML
    private TableColumn<NotaCredito, String> tbcNcfAfectado;

    @FXML
    private TableView<DetalleNotaCredito> tbDetalleNotaCredito;
    @FXML
    private TableColumn<DetalleNotaCredito, String> tbcNotaCredito;
    @FXML
    private TableColumn<DetalleNotaCredito, String> tbcArticulo;
    @FXML
    private TableColumn<DetalleNotaCredito, Double> tbcCantidad;
    @FXML
    private TableColumn<DetalleNotaCredito, String> tbcUnidad;
    @FXML
    private TableColumn<DetalleNotaCredito, Double> tbcValor;
    @FXML
    private TableColumn<DetalleNotaCredito, Double> tbcMonto;

    ObservableList<NotaCredito> lista = FXCollections.observableArrayList();
    ObservableList<DetalleNotaCredito> listadetalle = FXCollections.observableArrayList();
    @FXML
    private TabPane tabPane;

    @FXML
    private VBox vbVisorDeProgreso;
    @FXML
    private Label lbProgreso;
    @FXML
    private ProgressIndicator pgIndicador;
    @FXML
    private ProgressBar progresBar;

    @FXML
    private JFXButton btnRuta;
    @FXML
    private JFXButton btnExportPdf;
    @FXML
    private Label lbRutaArchivoHeder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTablaNc();
        iniciarTablaDetalle();
        iniciazarFiltro();

        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

    }

    private void iniciazarFiltro() {

        FilteredList<NotaCredito> filteredData = new FilteredList<>(tbNotaCredito.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toUpperCase();

                if (filtro.getFecha() != null && filtro.getFecha().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getNumero() != null && filtro.getNumero().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filtro.getNombreSocioNegocio() != null && filtro.getNombreSocioNegocio().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<NotaCredito> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbNotaCredito.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbNotaCredito.setItems(sortedData);
    }

    private void iniciarTablaNc() {

        // listaEntrada.clear();
        lista.addAll(ManejoNotaDeCredito.getInstancia().getLista());
        tbNotaCredito.setItems(lista);
        txtCantidad.setText(Integer.toString(lista.size()));

        tbcNotaCredito.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcMontoFactura.setCellValueFactory(new PropertyValueFactory<>("montoFactura"));
        tbcMontoNc.setCellValueFactory(new PropertyValueFactory<>("monto"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcNcfAfectado.setCellValueFactory(new PropertyValueFactory<>("ncfAfectado"));
        tbcNcf.setCellValueFactory(new PropertyValueFactory<>("ncf"));

        tbcClienteoSuplidor.setCellValueFactory(new PropertyValueFactory<>("clinete"));
        tbcAnulada.setCellValueFactory(new PropertyValueFactory<>("anulada"));
        tbcTipoNc.setCellValueFactory(new PropertyValueFactory<>("tipoNcf"));

        tbcAnulada.setCellValueFactory(
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

        tbcAnulada.setCellValueFactory(
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

        tbcClienteoSuplidor.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getNombreSocioNegocio());
                    }
                    return property;
                });

        tbcTipoNc.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getTipoNc() != null) {
                        property.setValue(cellData.getValue().getTipoNc().getNombre());
                    }
                    return property;
                });

        tbcConcepto.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getConcepto() != null) {
                        property.setValue(cellData.getValue().getConcepto());
                    } else {
                        property.setValue(cellData.getValue().getConcepto());
                    }
                    return property;
                });

        tbcVerNotaCredito.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcVerNotaCredito.setCellFactory((TableColumn<NotaCredito, NotaCredito> param) -> {

            TableCell<NotaCredito, NotaCredito> cellsc = new TableCell<NotaCredito, NotaCredito>() {
                @Override
                public void updateItem(NotaCredito item, boolean empty) {
                    super.updateItem(item, empty);
                    File imageFile;
                    Image img;
                    ImageView imageview;
                    Label label;

                    if (item != null) {

                        imageFile = new File(getClass().getResource("/imagen/img_documento.jpg").toString());
                        label = new Label("Componente");
                        imageview = new ImageView(new Image(getClass().getResource("/imagen/img_documento.jpg").toString(), 40, 20, false, false));
                        imageview.setFitWidth(40);
                        imageview.setFitHeight(20);

                        VBox hbox = new VBox();

                        hbox.getChildren().addAll(imageview);

                        hbox.setAlignment(Pos.CENTER);

                        //Evento de la fila 
                        hbox.setOnMouseClicked((event) -> {

                            if (item.getTipoNc().getCodigo() == 1) {
                                RptNotaCredito.getInstancia().verNotaCreditoCliente(item.getCodigo());
                            } else if (item.getTipoNc().getCodigo() == 2) {
                                RptNotaCredito.getInstancia().verNotaCreditoSuplidor(item.getCodigo());
                            }
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

    public void iniciarTablaDetalle() {

        listadetalle.clear();

        tbDetalleNotaCredito.setItems(listadetalle);

        tbcArticulo.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadDevuelta"));
        tbcMonto.setCellValueFactory(new PropertyValueFactory<>("pendiente"));

    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        buscar();

    }

    @FXML
    private void tbReciboMouseClicked(MouseEvent event) {

        if (!(tbNotaCredito.getSelectionModel().getSelectedIndex() == -1)) {

            listadetalle.clear();
            txtCantidadArticulo.clear();
            txtValorTotal.clear();
            NotaCredito recibo = tbNotaCredito.getSelectionModel().getSelectedItem();

            txtComentario.setText(recibo.getConcepto());

            listadetalle.addAll(ManejoNotaDeCredito
                    .getInstancia()
                    .getDetalleNotaDeCredito(recibo.getCodigo()));

            valorTotal();
            txtCantidadArticulo.setText(Integer.toString(listadetalle.size()));
            if (event.getClickCount() == 2) {

                tabPane.getSelectionModel().select(1);
            }

        }
    }

    private void valorTotal() {

        Double total = 0.00;
        for (DetalleNotaCredito det : listadetalle) {

            total += det.getNotaCredito().getMonto();
        }

        txtValorTotal.setText(total.toString());
    }

    @FXML
    private void tbDetalleReciboMouseClicked(MouseEvent event) {

        try {

            if (!(tbDetalleNotaCredito.getSelectionModel().getSelectedIndex() == -1)) {

                if (event.getClickCount() == 2) {

                    int nota = tbDetalleNotaCredito.getSelectionModel().getSelectedItem().getNotaCredito().getCodigo();
                    RptFactura.getInstancia().imprimir(nota);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/notaCredito/RegistroNotaDeCredito.fxml"));

        ClaseUtil.getStageModal(root);

        lista.clear();
        listadetalle.clear();
        lista.addAll(ManejoNotaDeCredito.getInstancia().getLista());

    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {

        try {

            if (!(tbNotaCredito.getSelectionModel().getSelectedIndex() == -1)) {

                Date fechaInicio = ClaseUtil.asDate(dpFechaInicio.getValue()), fechaFinal = ClaseUtil.asDate(dpFechaFinal.getValue());
                NotaCredito nota = tbNotaCredito.getSelectionModel().getSelectedItem();

                if (nota.getAnulada() == true) {

                    util.ClaseUtil.mensaje("ESTA NOTA DE CREDITO YA  ESTA ANULADA");
                    return;
                }

                EntradaDiario ent = EntradaDiarioDao.getInstancia()
                        .getEntradaDiarioPorDocumento(nota.getCodigo(), 8);

                Optional<ButtonType> ok = util.ClaseUtil.confirmarMensaje("SEGURO QUE QUIERE ANULAR LA NOTA DE CREDITO NUMERO  " + nota.getNumero());
                // + " TAMBIEN SE ANULARA LA ENTRADA DE DIARIO NUMERO  " + ent.getCodigo());

                if (ok.get() == ButtonType.OK) {

                    nota.setAnulada(true);
                    nota.setFechaAnulada(new Date());

                    NotaCredito notaCreditoDB = ManejoNotaDeCredito.getInstancia().actualizar(nota);

                    if (!(notaCreditoDB == null)) {

                        //Anular entrada de diario de nota de credito  
                        if (!(ent == null)) {

                            ent.setAnulada(true);
                            EntradaDiarioDao.getInstancia().actualizar(ent);

                            DocumentoAnuladoDao.getInstancia()
                                    .registroDocumentoAnulado(ent.getCodigo(),
                                            ent.getCodigo().toString(), 8, "ANULACION NOTA DE CREDITO NUMERO ");

                        }

                        ClaseUtil.mensaje("PROCESO TERMINADO CON EXITO");
                    }

                    lista.clear();
                    lista.addAll(ManejoNotaDeCredito.getInstancia().getLista());

                }
            } else {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UNA NOTA DE CREDITO");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buscar() {

        Date fechaini, fechafin;
        fechaini = ClaseUtil.asDate(dpFechaInicio.getValue());
        fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());
        lista.clear();
        listadetalle.clear();

        lista.addAll(ManejoNotaDeCredito.getInstancia().getLista(fechaini, fechafin));
        txtCantidad.setText(Integer.toString(lista.size()));
        txtCantidadArticulo.clear();
        txtValorTotal.clear();
        iniciazarFiltro();
    }

    @FXML
    private void btnRutaActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnExportPdfActionEvent(ActionEvent event) {

    }

}
