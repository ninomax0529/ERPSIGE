/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.puntoVenta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.contabilidadd.DocumentoAnuladoDao;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.contrato.ManejoContratoDeServicio;
import manejo.factura.ManejoFactura;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import modelo.ContratoDeServicio;
import modelo.DetalleFactura;
import modelo.EntradaDiario;
import modelo.Factura;
import modelo.MenuModulo;
import modelo.RolMenuModulo;
import reporte.ightrack.RptFacturaIghTrack;
import reporte.factura.RptFacturaPinturaTriplea;
import reporte.unidadnegocio.RptFactura;
//import reporte.factura.RptFactura;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ConsultaFacturacionController implements Initializable {

    @FXML
    private JFXButton btnExportXLS;
    @FXML
    private TableColumn<Factura, String> tbcRnc;

    /**
     * @return the codigoRol
     */
    public int getCodigoRol() {
        return codigoRol;
    }

    /**
     * @param codigoRol the codigoRol to set
     */
    public void setCodigoRol(int codigoRol) {
        this.codigoRol = codigoRol;
    }

    /**
     * @return the codigoMenuModulo
     */
    public Integer getCodigoMenuModulo() {
        return codigoMenuModulo;
    }

    /**
     * @param codigoMenuModulo the codigoMenuModulo to set
     */
    public void setCodigoMenuModulo(Integer codigoMenuModulo) {
        this.codigoMenuModulo = codigoMenuModulo;
    }

    @FXML
    private JFXDatePicker dpFechaDesde;
    @FXML
    private JFXDatePicker dpFecgaHasta;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXTextField txtFiltrar;
    @FXML
    private TableView<Factura> tbFactura;
    @FXML
    private TableColumn<Factura, String> tbcFactura;
    @FXML
    private TableColumn<Factura, String> tbcNcf;
    @FXML
    private TableColumn<Factura, String> tbcCliente;
    @FXML
    private TableColumn<Factura, String> tbcAnulada;
    @FXML
    private TableColumn<Factura, Date> tbcFecha;
    @FXML
    private TableColumn<Factura, Double> tbcSubTotal;
    @FXML
    private TableColumn<Factura, Double> tbcItbis;
    @FXML
    private TableColumn<Factura, Double> tbcDescuento;
    @FXML
    private TableColumn<Factura, Double> tbcTotal;
    @FXML
    private TableView<DetalleFactura> tbDetalleFactura;
    @FXML
    private TableColumn<DetalleFactura, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleFactura, String> tbcArticulo;
    @FXML
    private TableColumn<DetalleFactura, String> tbcUnidad;
    @FXML
    private TableColumn<DetalleFactura, String> tbcCantidad;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcPrecioUnitario;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcsubTotalDet;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcDescuentoDet;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcItbisDet;

    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Label lbCantidad;

    ObservableList<DetalleFactura> listadetalleFactura = FXCollections.observableArrayList();
    ObservableList<Factura> listaFactura = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnAnular;
    @FXML
    private TabPane tabPane;
    @FXML
    private TableColumn<Factura, Factura> tbcVerFactura;
    @FXML
    private Label lbCantidadFactura;
    @FXML
    private JFXButton btnExportPdf;

    File file = null;
    @FXML
    private Label lbRutaArchivoHeder;
    @FXML
    private VBox vbVisorDeProgreso;
    @FXML
    private Label lbProgreso;
    @FXML
    private ProgressIndicator pgIndicador;
    @FXML
    private ProgressBar progresBar;
    TaskExportarFactura taskExportarFactura;
    @FXML
    private TableColumn<Factura, Factura> tbcImprimir;
    @FXML
    private JFXCheckBox chImprimir;
    TaskImprimirFactura taskImprimirFactura;
    @FXML
    private JFXButton btnRuta;
    @FXML
    private JFXButton btnImprimir;
    @FXML
    private HBox hbPermiso;

    private int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    private Integer codigoMenuModulo;
    int ung;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablaEncabezado();
        inicializarTablaDetalle();
        iniciazarFiltro();
        dpFechaDesde.setValue(LocalDate.now());
        dpFecgaHasta.setValue(LocalDate.now());
        vbVisorDeProgreso.setVisible(false);
        buscar();
        lbRutaArchivoHeder.setText("");
        btnExportPdf.setDisable(true);

        setCodigoMenuModulo(ManejoMenuModulo.getInstancia().getMenuModulo(3, "btnConsultaFactura"));
        agregarOpciones();
        activarOpciones();
        ung = VariablesGlobales.USUARIO.getCodigo();

    }

    public void inicializarTablaEncabezado() {

//        listaFactura.addAll(ManejoFactura.getInstancia().getLista());
        tbFactura.setItems(listaFactura);

        tbcFactura.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcNcf.setCellValueFactory(new PropertyValueFactory<>("ncf"));
        tbcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tbcRnc.setCellValueFactory(new PropertyValueFactory<>("cliente.rnc"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        tbcItbis.setCellValueFactory(new PropertyValueFactory<>("itbis"));
        tbcDescuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        tbcAnulada.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getAnulada() == true ? "SI" : "NO");
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

        tbcRnc.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getCliente().getRnc());
                    }
                    return property;
                });

        tbcVerFactura.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcVerFactura.setCellFactory((TableColumn<Factura, Factura> param) -> {

            TableCell<Factura, Factura> cellsc = new TableCell<Factura, Factura>() {
                @Override
                public void updateItem(Factura item, boolean empty) {
                    super.updateItem(item, empty);

                    ImageView imageview;
                    if (item != null) {

                        imageview = new ImageView(new Image(getClass().getResource("/imagen/img_documento.jpg").toString(), 40, 20, false, false));
                        imageview.setFitWidth(40);
                        imageview.setFitHeight(20);

                        VBox hbox = new VBox();

                        hbox.getChildren().addAll(imageview);

                        hbox.setAlignment(Pos.CENTER);

                        //Evento de la fila 
                        hbox.setOnMouseClicked((event) -> {

//                            if (!(tbFactura.getSelectionModel().getSelectedIndex() == -1)) {
                            int factura = item.getCodigo();

                            System.out.println("item.getFormato() : "+item.getFormato());
                            RptFactura.getInstancia().verFactura(factura,item.getUnidadDeNegocio().getCodigo(),item.getFormato());

//                            if (null != VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()) {
//                                switch (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()) {
//                                    case 1:
//                                        RptFacturaPinturaTriplea.getInstancia().verFactura(factura);
//                                        break;
////                            }
//                                    case 2:
//                                        System.out.println("ietem :  " + item.getNcf());
//                                        RptFacturaIghTrack.getInstancia().verFactura(factura);
//                                        break;
//                                    case 3:
//                                        System.out.println("ietem :  " + item.getNcf());
//                                        RptFacturaIghTrack.getInstancia().verFactura(factura);
//                                        break;
//                                    case 4:
//                                        System.out.println("ietem :  " + item.getNcf());
//                                        RptFacturaIghTrack.getInstancia().verFactura(factura);
//                                        break;
//
//                                    case 5:
//                                        System.out.println("ietem :  " + item.getNcf());
//                                        RptFacturaFsConstrucciones.getInstancia().verFactura(factura);
//                                        break;
//                                    default:
//                                        break;
//                                }
//                            }
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

        tbcImprimir.setCellFactory((TableColumn<Factura, Factura> param) -> {

            TableCell<Factura, Factura> cellsc = new TableCell<Factura, Factura>() {
                @Override
                public void updateItem(Factura item, boolean empty) {
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

    public void inicializarTablaDetalle() {

        listadetalleFactura.clear();

        tbDetalleFactura.setItems(listadetalleFactura);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcArticulo.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tbcsubTotalDet.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        tbcDescuentoDet.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        tbcItbisDet.setCellValueFactory(new PropertyValueFactory<>("itbis"));

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getUnidad() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

        tbcArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getNombreArticulo());
                    }
                    return property;
                });

        tbcCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });

    }

    private void iniciazarFiltro() {

        FilteredList<Factura> filteredData = new FilteredList<>(tbFactura.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltrar.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(factura -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (factura.getNumero().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (factura.getFecha() != null && factura.getFecha().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (factura.getCliente() != null && factura.getCliente().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (factura.getNcf() != null && factura.getNcf().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Factura> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbFactura.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbFactura.setItems(sortedData);
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        try {

            buscar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void btnReimprimirActionEvent(ActionEvent event) {

        try {

            if (!(tbFactura.getSelectionModel().getSelectedIndex() == -1)) {

                int factura = tbFactura.getSelectionModel().getSelectedItem().getCodigo();
                
//                     if (tieneMontoExcento) {
//                        RptFactura.getInstancia().verFactura(factura,ung,1);
//                    } else {
//                        RptFactura.getInstancia().verFactura(factura,ung);
//                    }

//                if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {
//
//                    RptFacturaPinturaTriplea.getInstancia().verFactura(factura);
//
//                } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {
//
//                    RptFacturaIghTrack.getInstancia().verFactura(factura);
//                } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 4) {
//
//                    RptFacturaIghTrack.getInstancia().verFactura(factura);
//                } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 3) {
//
//                    RptFacturaIghTrack.getInstancia().verFactura(factura);
//                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void tbFacturaMouseCliked(MouseEvent event) {

        if (!(tbFactura.getSelectionModel().getSelectedIndex() == -1)) {

            listadetalleFactura.clear();
            listadetalleFactura.addAll(ManejoFactura
                    .getInstancia().getDetalleFactura(tbFactura.getSelectionModel()
                            .getSelectedItem().getCodigo()));

            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(1);
            }

        }
    }

    @FXML
    private void btnAnularActionEvent(ActionEvent event) {

        try {

            if (!(tbFactura.getSelectionModel().getSelectedIndex() == -1)) {

                Date fechaDesde = ClaseUtil.asDate(dpFechaDesde.getValue());
                Date fechaHasta = ClaseUtil.asDate(dpFecgaHasta.getValue());
                Factura factura = tbFactura.getSelectionModel().getSelectedItem();

                if (factura.getAnulada() == true) {

                    ClaseUtil.mensaje(" ESTA FACTURA YA ESTA ANULADA ");
                    return;
                }

                List<EntradaDiario> listaentrada = EntradaDiarioDao.getInstancia()
                        .getListaEntradaDiarioPorDocumento(factura.getCodigo(), 7);

                String msg = "";

                if (listaentrada.size() > 0) {

                    msg = " TAMBIEN SE ANULARAN LAS ENTRADA DE DIARIO NUMERO  " + listaentrada.get(0).getCodigo() + " , " + listaentrada.get(1).getCodigo();
                }

                Optional<ButtonType> ok = ClaseUtil.confirmarMensaje(" SEGURO QUE QUIERE ANULAR LA FACTURA NUMERO " + factura.getNcf() + " \n " + msg);
//                        + );

                if (ok.get() == ButtonType.OK) {

                    factura.setAnulada(true);
                    factura.setFechaAnulada(new Date());
                    Factura facturadb = ManejoFactura.getInstancia().actualizar(factura);

                    if (!(facturadb == null)) {

                        try {

                            List<DetalleFactura> lista = ManejoFactura.getInstancia().getDetalleFactura(factura.getCodigo());

                            ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorAnulacion(lista);

                            DocumentoAnuladoDao.getInstancia()
                                    .registroDocumentoAnulado(facturadb.getCodigo(),
                                            facturadb.getCodigo().toString(), 7, "ERROR EN FACTURACCION ");

                            //Anular entrada de diario de cheque  
                            if (!(listaentrada == null)) {

                                for (EntradaDiario asiento : listaentrada) {

                                    asiento.setAnulada(true);
                                    EntradaDiarioDao.getInstancia().actualizar(asiento);

                                    DocumentoAnuladoDao.getInstancia()
                                            .registroDocumentoAnulado(asiento.getCodigo(),
                                                    asiento.getCodigo().toString(), 6, " ERROR EN FACTURACION ");
                                }

                            }

                        } catch (Exception e) {

                            e.printStackTrace();
                        }

                        listaFactura.clear();
                        listaFactura.addAll(ManejoFactura.getInstancia().getLista(fechaDesde, fechaHasta));

//                        if (!(facturadb.getNumeroContrato() == null)) {
//
//                            actualizarServicioDeContrato(facturadb);
//
//                        }
                        ClaseUtil.mensaje("Factura anulada Correctamente");
                    }

                }

            } else {

                ClaseUtil.mensaje("Tiene que seleccionar una Factura");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void buscar() {

        chImprimir.setSelected(false);
        listaFactura.clear();
        listadetalleFactura.clear();
        Date fechaDesde = ClaseUtil.asDate(dpFechaDesde.getValue());
        Date fechaHasta = ClaseUtil.asDate(dpFecgaHasta.getValue());
        listaFactura.addAll(ManejoFactura.getInstancia().getLista(fechaDesde, fechaHasta));
        lbCantidadFactura.setText(Integer.toString(listaFactura.size()));

    }

    @FXML
    private void tbDetalleFacturaMouseClicked(MouseEvent event) {
    }

    @FXML
    private void btnExportPdfActionEvent(ActionEvent event) {

        try {

//            if (!(lbRutaArchivoHeder.getText().isEmpty()) && listaFactura.size() > 0) {
//
//                for (Factura fact : listaFactura) {
            tareExportarAPdf();
//                }

//            }
//
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnImprimirActionEvent(ActionEvent event) {

        try {

            tareaImprimir();
//            DirectoryChooser directoryChooser = new DirectoryChooser();
//
//            File fileLocal = directoryChooser.showDialog(btnExportPdf.getScene().getWindow());
//            String path = fileLocal.getPath(); // La ruta de la carpeta seleccionada
//
//            lbRutaArchivoHeder.setText(fileLocal.getAbsolutePath());
//            System.out.println("ruta " + path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tareExportarAPdf() {

        try {

            vbVisorDeProgreso.setVisible(true);

//            btnGenerarFacturaRecurrente.setDisable(true);
            progresBar.setProgress(0);
            pgIndicador.setProgress(0);
            pgIndicador.setVisible(true);
            progresBar.setVisible(true);

            taskExportarFactura = new TaskExportarFactura();
            progresBar.progressProperty().unbind();

            progresBar.progressProperty().bind(taskExportarFactura.progressProperty());

            pgIndicador.progressProperty().unbind();

            pgIndicador.progressProperty().bind(taskExportarFactura.progressProperty());
//
            lbProgreso.textProperty().unbind();
            lbProgreso.textProperty().bind(taskExportarFactura.messageProperty());

            taskExportarFactura.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent t) -> {

                lbProgreso.textProperty().unbind();

                vbVisorDeProgreso.setVisible(false);
                lbCantidadFactura.setText(Integer.toString(listaFactura.size()));

                taskExportarFactura.cancel();

                progresBar.progressProperty().unbind();

                pgIndicador.progressProperty().unbind();
                lbRutaArchivoHeder.setText("");

            });

            // Start the Task.
            new Thread(taskExportarFactura).start();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @FXML
    private void chImprimirActionEvent(ActionEvent event) {

        if (chImprimir.isSelected()) {
            for (Factura fact : listaFactura) {

                fact.setImprimir(true);

            }
        } else {

            for (Factura fact : listaFactura) {

                fact.setImprimir(false);

            }
        }

        inicializarTablaEncabezado();
    }

    @FXML
    private void btnRutaActionEvent(ActionEvent event) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File fileLocal = directoryChooser.showDialog(btnExportPdf.getScene().getWindow());
        String path = fileLocal.getPath(); // La ruta de la carpeta seleccionada

        if (fileLocal.isDirectory()) {
            lbRutaArchivoHeder.setText(fileLocal.getAbsolutePath());
            System.out.println("ruta " + path);
            btnExportPdf.setDisable(false);
        } else {

            btnExportPdf.setDisable(true);
            ClaseUtil.mensaje("Tiene que seleccionar una carpeta");
        }

    }

    private void actualizarServicioDeContrato(Factura fact) {

        ContratoDeServicio contrato = ManejoContratoDeServicio.getInstancia().getContrato(fact.getNumeroContrato());

        if (!(contrato == null)) {

        }

    }

    @FXML
    private void btnExportFacturaXLSActionEvent(ActionEvent event) {

        try {

            util.ClaseUtil.exportarDAtos(tbFactura, 2);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class TaskExportarFactura extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            try {

                int total = listaFactura.size();
                int i = 0;

                for (Factura fact : listaFactura) {
                    i++;
                    exportarFactura(fact, i, total, lbRutaArchivoHeder.getText());
                    this.updateProgress(i, total);
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;

        }

        private void exportarFactura(Factura fact, int i, int total, String ruta) throws InterruptedException {

//                  RptFacturaIghTrack.getInstancia().exportarFacturaAPDF(fact, ruta);
                RptFactura.getInstancia().exportarFacturaAPDF(fact, ruta);

            this.updateMessage(" Procesada  " + i + "  De " + total);
            Thread.sleep(200);
        }

    }

    private void tareaImprimir() {

        try {

            vbVisorDeProgreso.setVisible(true);

//            btnGenerarFacturaRecurrente.setDisable(true);
            progresBar.setProgress(0);
            pgIndicador.setProgress(0);
            pgIndicador.setVisible(true);
            progresBar.setVisible(true);

            taskImprimirFactura = new TaskImprimirFactura();
            progresBar.progressProperty().unbind();

            progresBar.progressProperty().bind(taskImprimirFactura.progressProperty());

            pgIndicador.progressProperty().unbind();

            pgIndicador.progressProperty().bind(taskImprimirFactura.progressProperty());
//
            lbProgreso.textProperty().unbind();
            lbProgreso.textProperty().bind(taskImprimirFactura.messageProperty());

            taskImprimirFactura.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent t) -> {

                lbProgreso.textProperty().unbind();

                vbVisorDeProgreso.setVisible(false);
                lbCantidadFactura.setText(Integer.toString(listaFactura.size()));

                taskImprimirFactura.cancel();

                progresBar.progressProperty().unbind();

                pgIndicador.progressProperty().unbind();

            });

            // Start the Task.
            new Thread(taskImprimirFactura).start();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private class TaskImprimirFactura extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            try {

                List<Factura> listaImprimir = new ArrayList<>();

                for (Factura fa : listaFactura) {

                    if (fa.getImprimir()) {

                        listaImprimir.add(fa);
                    }

                }

                int total = listaImprimir.size();
                int i = 0;

                for (Factura fact : listaImprimir) {
                    i++;

                    imprimirFactura(fact, i, total);
                    this.updateProgress(i, total);

                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;

        }

        private void imprimirFactura(Factura fact, int i, int total) throws InterruptedException {

//            RptFacturaIghTrack.getInstancia().imprimirPorLote(fact);
            RptFactura.getInstancia().imprimirFactura(fact.getCodigo(), fact.getUnidadDeNegocio().getCodigo());
            this.updateMessage(" Procesada  " + i + "  De " + total);
            Thread.sleep(200);
        }

    }

    //Agrega las opciones de menu a la base de datos
    private void agregarOpciones() {

        MenuModulo menuModulo;

        List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(3);
        int codigoModulo = ManejoModulo.getInstancia().getModulo(3).getCodigo();
        String nombreModulo = ManejoModulo.getInstancia().getModulo(3).getNombre();

        boolean actualizando = false;

        for (Node n : hbPermiso.getChildren()) {

            if (!(n.getId() == null)) {

                menuModulo = new MenuModulo();

                menuModulo.setIdMenu(n.getId());
                menuModulo.setMenu(n.getAccessibleText());
                menuModulo.setModulo(codigoModulo);
                menuModulo.setNombreModulo(nombreModulo);
                menuModulo.setPermiso(getCodigoMenuModulo());
//                menuModulo.setTipoMenu(ManejoTipoMenu.getInstancia().getTipoMenu(codigoModulo));

                for (MenuModulo memu : listaMenuModulo) {

                    if (n.getId().equals(memu.getIdMenu())) {

                        menuModulo = memu;
                        actualizando = true;
                        break;
                    }
                }

                if (actualizando) {

                    ManejoMenuModulo.getInstancia().actualizar(menuModulo);

                } else {

                    ManejoMenuModulo.getInstancia().salvar(menuModulo);
                }

            }

        }
    }

    private void activarOpciones() {

        if (getCodigoRol() == 1) {//rol de administrador

            for (Node n : hbPermiso.getChildren()) {
                n.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(getCodigoRol(), 3);

            for (Node n : hbPermiso.getChildren()) {

                if (!(n.getId() == null)) {

                    for (RolMenuModulo memu : listaRolMenuModulos) {

                        if (n.getId().equals(memu.getMenuModulo().getIdMenu())) {
                            n.setDisable(!memu.getHabilitado());

                        }
                    }

                }

            }
        }

    }

}
