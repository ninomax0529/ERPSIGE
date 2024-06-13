/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.reporte;

import controlador.venta.cliente.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import manejo.cliente.ManejoCliente;
import manejo.factura.ManejoFactura;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.Factura;
import reporte.cliente.RptClientes;
import reporte.factura.RptFacturaIghTrack;
import reporte.factura.RptFacturaPinturaTriplea;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ReporteDeClienteController implements Initializable {

    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<Cliente> tbCliente;
    @FXML
    private TableColumn<Cliente, String> tbcCodigo;
    @FXML
    private TableColumn<Cliente, String> tbcNombre;
    @FXML
    private TableColumn<Cliente, String> tbcDireccion;
    @FXML
    private TableColumn<Cliente, String> tbcTelefono;
    @FXML
    private TableColumn<Cliente, String> tbcCelular;
    @FXML
    private TableColumn<Cliente, String> tbcEstado;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Label lbCantidad;

    Cliente cliente;
    @FXML
    private JFXButton btnExportar;
    @FXML
    private TabPane tabCliente;
    @FXML
    private JFXTextField txtFiltrar;
    @FXML
    private TableView<Factura> tbFactura;
    @FXML
    private TableColumn<Factura, String> tbcFactura;
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
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnVerReporte;
    @FXML
    private JFXRadioButton rbActivo;
    @FXML
    private JFXRadioButton rbInactitivo;
    @FXML
    private JFXRadioButton rbTodos;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();

    ObservableList<DetalleFactura> listadetalleFactura = FXCollections.observableArrayList();
    ObservableList<Factura> listaFactura = FXCollections.observableArrayList();

    final ToggleGroup group = new ToggleGroup();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabla();
        iniciazarFiltro();
        inicializarTablaDetalle();
        inicializarTablaEncabezado();
        rbActivo.setToggleGroup(group);
        rbInactitivo.setToggleGroup(group);
        rbTodos.setToggleGroup(group);
    }

    public void inicializarTablaEncabezado() {

        tbFactura.setItems(listaFactura);

        tbcFactura.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        tbcItbis.setCellValueFactory(new PropertyValueFactory<>("itbis"));
        tbcDescuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcMontoAbonado.setCellValueFactory(new PropertyValueFactory<>("abono"));
        tbcMontoPendiente.setCellValueFactory(new PropertyValueFactory<>("pendiente"));
        tbcCliente.setCellValueFactory(new PropertyValueFactory<>("factura.cliente.nombre"));

        tbcAnulada.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getAnulada() == true ? "SI" : "NO");
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
                        imageview = new ImageView(new Image(getClass().getResource("/imagen/img_documento.jpg").toString(), 40, 20, false, false));
                        imageview.setFitWidth(40);
                        imageview.setFitHeight(20);

                        VBox hbox = new VBox();

                        hbox.getChildren().addAll(imageview);

                        hbox.setAlignment(Pos.CENTER);

                        //Evento de la fila 
                        hbox.setOnMouseClicked((event) -> {

                            if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {

                                RptFacturaPinturaTriplea.getInstancia().verFactura(item.getCodigo());

                            } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {

                                RptFacturaIghTrack.getInstancia().verFactura(item.getCodigo());
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

    public void iniciarTabla() {

        listaCliente.clear();

        listaCliente.addAll(ManejoCliente.getInstancia().getCliente());

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tbcCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tbCliente.setItems(listaCliente);

    }

    private void iniciazarFiltro() {

        FilteredList<Cliente> filteredData = new FilteredList<>(tbCliente.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getNombre() != null && filtro.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getCodigo() != null && filtro.getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Cliente> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbCliente.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbCliente.setItems(sortedData);
    }

    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/cliente/RegistroCliente.fxml"));

        ClaseUtil.crearStageModal(root);

        listaCliente.clear();
        listaCliente.addAll(ManejoCliente.getInstancia().getCliente());

    }

    private void btnEditarActionEvent(ActionEvent event) {

        try {

            if (tbCliente.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN CLIENTE");

            } else {

                setCliente(tbCliente.getSelectionModel().getSelectedItem());

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/vista/venta/cliente/RegistroCliente.fxml"));
                loader.load();

                Parent root = loader.getRoot();

                RegistroClienteController registroClienteController = loader.getController();

                registroClienteController.setEditar(true);
                registroClienteController.setCliente(getCliente());
                registroClienteController.llenarCampo();

                ClaseUtil.getStageModal(root);

                listaCliente.clear();
                listaCliente.addAll(ManejoCliente.getInstancia().getCliente());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) {
        try {

            util.ClaseUtil.exportarDAtos(tbCliente);

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

        }
    }

    @FXML
    private void tbClienteActionEvent(MouseEvent event) {

        if (!(tbCliente.getSelectionModel().getSelectedIndex() == -1)) {

            setCliente(tbCliente.getSelectionModel().getSelectedItem());

            System.out.println("getCliente() " + getCliente().getNombre());
            listaFactura.clear();
            listadetalleFactura.clear();
            listaFactura.setAll(ManejoFactura.getInstancia().getFacturaPendiente(getCliente()));

            lbTotalFacturado.setText(totalFacturado().toString());
            lbTotalPendiente.setText(totalPendiente().toString());
            lbTotalabonado.setText(totalAbonado().toString());

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

        return total;

    }

    private Double totalAbonado() {

        Double total = 0.00;

        for (Factura fact : listaFactura) {

            total += fact.getAbono();
        }

        return total;

    }

    private Double totalPendiente() {

        Double total = 0.00;

        for (Factura fact : listaFactura) {

            total += fact.getPendiente();
        }

        return total;

    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnVerReporteActionEvent(ActionEvent event) throws IOException {

        RptClientes.getInstancia().imprimir();

    }

    @FXML
    private void rbActivoActionEvent(ActionEvent event) {
    }

    @FXML
    private void rbInactitivoActionEvent(ActionEvent event) {
    }

    @FXML
    private void rbTodosActionEvent(ActionEvent event) {
    }
}
