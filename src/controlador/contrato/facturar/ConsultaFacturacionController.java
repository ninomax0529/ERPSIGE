/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.facturar;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.contabilidadd.DocumentoAnuladoDao;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.factura.ManejoFactura;
import modelo.DetalleFactura;
import modelo.EntradaDiario;
import modelo.Factura;
import reporte.ightrack.RptFacturaIghTrack;
import reporte.factura.RptFacturaPinturaTriplea;
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
    private JFXDatePicker dpFechaDesde;
    @FXML
    private JFXDatePicker dpFecgaHasta;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnReimprimir;
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
        buscar();

    }

    public void inicializarTablaEncabezado() {

        listaFactura.addAll(ManejoFactura.getInstancia().getLista());
        tbFactura.setItems(listaFactura);

        tbcFactura.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcNcf.setCellValueFactory(new PropertyValueFactory<>("ncf"));
        tbcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
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

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getNumero().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getFecha() != null && filtro.getFecha().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filtro.getCliente() != null && filtro.getCliente().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filtro.getNcf() != null && filtro.getNcf().toLowerCase().contains(lowerCaseFilter)) {
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

    @FXML
    private void btnReimprimirActionEvent(ActionEvent event) {

        try {

            if (!(tbFactura.getSelectionModel().getSelectedIndex() == -1)) {

                int factura = tbFactura.getSelectionModel().getSelectedItem().getCodigo();

                if(VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()==1) {

                    RptFacturaPinturaTriplea.getInstancia().verFactura(factura);

                } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {

                    RptFacturaIghTrack.getInstancia().verFactura(factura);
                } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 4) {

                    RptFacturaIghTrack.getInstancia().verFactura(factura);
                } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 3) {

                    RptFacturaIghTrack.getInstancia().verFactura(factura);
                }

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

        listaFactura.clear();
        listadetalleFactura.clear();
        Date fechaDesde = ClaseUtil.asDate(dpFechaDesde.getValue());
        Date fechaHasta = ClaseUtil.asDate(dpFecgaHasta.getValue());
        listaFactura.addAll(ManejoFactura.getInstancia().getLista(fechaDesde, fechaHasta));
    }

    @FXML
    private void tbDetalleFacturaMouseClicked(MouseEvent event) {
    }

}
