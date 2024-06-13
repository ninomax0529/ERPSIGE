/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cxc.gestionDeCobro;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import manejo.cxc.gestionDeCobro.ManejoGestionDeCobro;
import manejo.factura.ManejoFactura;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.GestionDeCobro;
import reporte.cxc.gestionDeCobro.RptGestionDeCobro;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class GestionDeCobroController implements Initializable {

    @FXML
    private TableView<GestionDeCobro> tbGestionDeCobro;
    @FXML
    private TableColumn<GestionDeCobro, String> tbcClienteGestionado;
    @FXML
    private TableColumn<GestionDeCobro, String> tbcFacturaGestionada;
    @FXML
    private TableColumn<GestionDeCobro, String> tbcNota;
    @FXML
    private TableColumn<GestionDeCobro, String> tbcFechaGestionada;
    @FXML
    private TableColumn<GestionDeCobro, String> tbcTelefonoGestionCobro;
    @FXML
    private JFXTextField txtFiltroFactura;
    @FXML
    private JFXTextField txtFiltroGestionCobro;
    @FXML
    private JFXDatePicker dpFechaDesde;
    @FXML
    private JFXDatePicker dpFecgaHasta;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnVerReporte1;

    /**
     * @return the gestionDeCobro
     */
    public GestionDeCobro getGestionDeCobro() {
        return gestionDeCobro;
    }

    /**
     * @param gestionDeCobro the gestionDeCobro to set
     */
    public void setGestionDeCobro(GestionDeCobro gestionDeCobro) {
        this.gestionDeCobro = gestionDeCobro;
    }

    Cliente cliente;
    @FXML
    private JFXButton btnExportar;
    @FXML
    private TabPane tabCliente;
    @FXML
    private TableView<Factura> tbFactura;
    @FXML
    private TableColumn<Factura, String> tbcFactura;
    @FXML
    private TableColumn<Factura, String> tbcCliente;
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
    private TableColumn<Factura, Double> tbcMontoAbonado;
    @FXML
    private TableColumn<Factura, Double> tbcMontoPendiente;
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
    private TableColumn<DetalleFactura, Double> tbcDetSubTotal;
    @FXML
    private TableColumn<Factura, String> tbcTelefono;
    @FXML
    private Color x41;
    @FXML
    private Font x31;
    @FXML
    private Label lbCantidad1;
    @FXML
    private Label lbTotalFacturado;
    @FXML
    private Label lbTotalabonado;
    @FXML
    private Label lbTotalPendiente;
    @FXML
    private TableColumn<Factura, Factura> tbcVerFactura;
    Date fi, ff;

    private GestionDeCobro gestionDeCobro;

    ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();

    ObservableList<DetalleFactura> listadetalleFactura = FXCollections.observableArrayList();
    ObservableList<Factura> listaFactura = FXCollections.observableArrayList();
    ObservableList<GestionDeCobro> listaGestionCobro = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFechaDesde.setValue(LocalDate.now());
        dpFecgaHasta.setValue(LocalDate.now());
        inicializarTablaDetalle();
        inicializarTablaEncabezado();
        inicializarTablaGestionCobro();
        iniciazarFiltroFactura();
        iniciazarFiltroGestionCobro();

    }

    public void inicializarTablaEncabezado() {

        tbcFactura.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        tbcItbis.setCellValueFactory(new PropertyValueFactory<>("itbis"));
        tbcDescuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcMontoAbonado.setCellValueFactory(new PropertyValueFactory<>("abono"));
        tbcMontoPendiente.setCellValueFactory(new PropertyValueFactory<>("pendiente"));

        tbcTelefono.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getCliente() != null) {
                        property.setValue(cellData.getValue().getCliente().getTelefono());
                    }
                    return property;
                });

        tbcCliente.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getCliente() != null) {
                        property.setValue(cellData.getValue().getNombreCliente());
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
                    File imageFile;
                    Image img;
                    ImageView imageview;
                    Label label;

                    if (item != null) {

//                        imageFile = new File(getClass().getResource("/imagen/img_documento.jpg").toString());
                        label = new Label("Componente");
                        imageview = new ImageView(new Image(getClass().getResource("/imagen/img_nota.png").toString(), 40, 20, false, false));
                        imageview.setFitWidth(70);
                        imageview.setFitHeight(50);

                        VBox hbox = new VBox();

                        hbox.getChildren().addAll(imageview);

                        hbox.setAlignment(Pos.CENTER);

                        //Evento de la fila 
                        hbox.setOnMouseClicked((event) -> {

                            try {

                                FXMLLoader loader = new FXMLLoader();

                                loader.setLocation(getClass().getResource("/vista/cxc/gestionDeCobro/RegistroGestionCobro.fxml"));
                                loader.load();
                                Parent root = loader.getRoot();
                                setGestionDeCobro(new GestionDeCobro());
                                gestionDeCobro.setCliente(item.getCliente());
                                gestionDeCobro.setCobrador(VariablesGlobales.USUARIO.getCodigo());
                                gestionDeCobro.setFactura(item);
                                gestionDeCobro.setFechaCobro(new Date());
                                gestionDeCobro.setMontoPendiente(item.getPendiente());
                                gestionDeCobro.setNombreCobrador(VariablesGlobales.USUARIO.getNombre());
                                gestionDeCobro.setNombreCliente(item.getCliente().getNombre());
                                gestionDeCobro.setNumeroFactura(item.getNcf());

                                RegistroGestionCobroController controller = loader.getController();

                                controller.setGestionDeCobro(gestionDeCobro);
                                controller.llenarCampo();

                                ClaseUtil.getStageModal(root);

                                listaGestionCobro.clear();
                                listaGestionCobro.addAll(ManejoGestionDeCobro.getInstancia().getLista());

                            } catch (IOException ex) {
                                Logger.getLogger(GestionDeCobroController.class.getName()).log(Level.SEVERE, null, ex);
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

        listaFactura.addAll(ManejoFactura.getInstancia().getFacturaPendiente());

        tbFactura.setItems(listaFactura);

        lbTotalFacturado.setText(totalFacturado().toString());
        lbTotalPendiente.setText(totalPendiente().toString());
        lbTotalabonado.setText(totalAbonado().toString());

    }

    public void inicializarTablaGestionCobro() {

        tbcFacturaGestionada.setCellValueFactory(new PropertyValueFactory<>("numeroFactura"));
        tbcFechaGestionada.setCellValueFactory(new PropertyValueFactory<>("fechaCobro"));
        tbcClienteGestionado.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        tbcNota.setCellValueFactory(new PropertyValueFactory<>("nota"));

        tbcTelefonoGestionCobro.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getCliente() != null) {
                        property.setValue(cellData.getValue().getCliente().getTelefono());
                    }
                    return property;
                });

        tbcFechaGestionada.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getFechaCobro() != null) {
                        property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaCobro()));
                    }
                    return property;
                });

        fi = util.ClaseUtil.asDate(dpFechaDesde.getValue());
        ff = util.ClaseUtil.asDate(dpFecgaHasta.getValue());

        listaGestionCobro.addAll(ManejoGestionDeCobro.getInstancia().getLista(fi, ff));

        tbGestionDeCobro.setItems(listaGestionCobro);

    }

    public void inicializarTablaDetalle() {

        listadetalleFactura.clear();

        tbDetalleFactura.setItems(listadetalleFactura);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcArticulo.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tbcDetSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
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

    private void iniciazarFiltroFactura() {

        FilteredList<Factura> filteredData = new FilteredList<>(tbFactura.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltroFactura.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getCliente().getNombre() != null && filtro.getCliente().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getCodigo() != null && filtro.getNcf().toLowerCase().contains(lowerCaseFilter)) {
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

    private void iniciazarFiltroGestionCobro() {

        FilteredList<GestionDeCobro> filteredData = new FilteredList<>(tbGestionDeCobro.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltroGestionCobro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getCliente().getNombre() != null && filtro.getCliente().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getNumeroFactura() != null && filtro.getNumeroFactura().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<GestionDeCobro> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbGestionDeCobro.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbGestionDeCobro.setItems(sortedData);
    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) {
        try {

            util.ClaseUtil.exportarDAtos(tbFactura);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tbFacturaMouseCliked(MouseEvent event) {

        if (!(tbFactura.getSelectionModel().getSelectedIndex() == -1)) {

            Factura fact = tbFactura.getSelectionModel().getSelectedItem();
            listadetalleFactura.clear();
            listadetalleFactura.setAll(ManejoFactura.getInstancia().getDetalleFactura(fact.getCodigo()));

            if (event.getClickCount() == 2) {
                tabCliente.getSelectionModel().select(1);
            }

        }
    }

    private Double totalFacturado() {

        Double total = 0.00;

        for (Factura fact : listaFactura) {

            total += fact.getTotal();
        }

        return ClaseUtil.roundDouble(total, 2);

    }

    private Double totalAbonado() {

        Double total = 0.00;

        for (Factura fact : listaFactura) {

            total += fact.getAbono();
        }

        return ClaseUtil.roundDouble(total, 2);

    }

    private Double totalPendiente() {

        Double total = 0.00;

        for (Factura fact : listaFactura) {

            total += fact.getPendiente();
        }

        return ClaseUtil.roundDouble(total, 2);

    }

    @FXML
    private void btnVerReporteActionEvent(ActionEvent event) {

        try {

            fi = util.ClaseUtil.asDate(dpFechaDesde.getValue());
            ff = util.ClaseUtil.asDate(dpFecgaHasta.getValue());

            RptGestionDeCobro.getInstancia().imprimir(fi, ff);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        try {

            fi = util.ClaseUtil.asDate(dpFechaDesde.getValue());
            ff = util.ClaseUtil.asDate(dpFecgaHasta.getValue());

            listaGestionCobro.clear();
            listaGestionCobro.addAll(ManejoGestionDeCobro.getInstancia().getLista(fi, ff));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
