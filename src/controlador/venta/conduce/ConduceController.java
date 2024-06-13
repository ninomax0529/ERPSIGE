/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.conduce;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
import manejo.conduce.ManejoConduce;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.Conduce;
import modelo.CotizacionDeVenta;
import modelo.DetalleConduce;
import modelo.SecuenciaDocumento;
import reporte.conduce.RpConducePinturaTriplea;
import reporte.cotizacion.RpCotizacionDeVentaIghTrack;
import reporte.cotizacion.RpCotizacionDeVentaPinturaTriplea;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ConduceController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TabPane tabPaneEntradaInventario;
    @FXML
    private TableView<Conduce> tbConduce;
    @FXML
    private TableColumn<Conduce, String> tbcConduce;
    @FXML
    private TableColumn<Conduce, String> tbcFactura;
    @FXML
    private TableColumn<Conduce, Date> tbcFechaConduce;
    @FXML
    private TableColumn<Conduce, String> tbcDestino;
    @FXML
    private TableColumn<Conduce, String> tbcCliente;
    @FXML
    private TableColumn<Conduce, String> tbcAnulado;
    @FXML
    private TableColumn<Conduce, String> tbcChofer;
    @FXML
    private TableColumn<Conduce, Conduce> tbcVer;
    @FXML
    private TableView<DetalleConduce> tbDetalleConduce;
    @FXML
    private TableColumn<DetalleConduce, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleConduce, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleConduce, Double> tbcCantidad;
    @FXML
    private TableColumn<DetalleConduce, String> tbcUnidad;
    @FXML
    private JFXTextField txtCantidadArticulo;
    @FXML
    private JFXTextField txtValorTotal;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidad;
    Conduce conduce;

    public Conduce getConduce() {
        return conduce;
    }

    public void setConduce(Conduce conduce) {
        this.conduce = conduce;
    }

    ObservableList<Conduce> lista = FXCollections.observableArrayList();

    ObservableList<DetalleConduce> listaDetalle = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTablaDetalle();
        inicializarTablaEncabezado();
        dpFechaFinal.setValue(LocalDate.now());
        dpFechaInicio.setValue(LocalDate.now());
    }

    public void inicializarTablaEncabezado() {

        lista.addAll(ManejoConduce.getInstancia().getLista());
        tbConduce.setItems(lista);

        tbcConduce.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcFactura.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tbcFechaConduce.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        tbcDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));

        tbcAnulado.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getAnulado() == true ? "SI" : "NO");
                    }
                    return property;
                });

        tbcFactura.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getFactura().getNumero().toString());
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

        tbcChofer.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getChofer().getNombre());
                    }
                    return property;
                });

        tbcVer.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcVer.setCellFactory((TableColumn<Conduce, Conduce> param) -> {

            TableCell<Conduce, Conduce> cellsc = new TableCell<Conduce, Conduce>() {
                @Override
                public void updateItem(Conduce item, boolean empty) {
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

                            int conduce = item.getCodigo();
                            RpConducePinturaTriplea.getInstancia().verConduce(conduce);

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

        listaDetalle.clear();

        tbDetalleConduce.setItems(listaDetalle);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getUnidad() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

        tbcDescripcionArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getNombre());
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

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        if (secuenciaDocumento() == null) {

            ClaseUtil.mensaje(" Tiene que configurar una secuencia para los conduce de esta unidad de negocio");
            return;
        }

        Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/conduce/RegistroConduce.fxml"));

        utiles.ClaseUtil.crearStageModal(root);

        lista.clear();
        lista.addAll(ManejoConduce.getInstancia().getLista());
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
    private void tbEntradaInventarioMouseClicked(MouseEvent event) {

        listaDetalle.clear();

        setConduce(tbConduce.getSelectionModel().getSelectedItem());
        listaDetalle.addAll(ManejoConduce.getInstancia().getDetalleConduce(getConduce().getCodigo()));
        txtComentario.setText(getConduce().getObservacion());

        if (event.getClickCount() == 2) {

            tabPaneEntradaInventario.getSelectionModel().select(1);
        }

    }

    @FXML
    private void tbDetalleEntradaInventarioMouseCliked(MouseEvent event) {
    }

    private void buscar() {

        lista.clear();
        listaDetalle.clear();
        Date fechaDesde = ClaseUtil.asDate(dpFechaInicio.getValue());
        Date fechaHasta = ClaseUtil.asDate(dpFechaFinal.getValue());
        lista.addAll(ManejoConduce.getInstancia().getLista(fechaDesde, fechaHasta));
    }

    private SecuenciaDocumento secuenciaDocumento() {

        int cod_ung = VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
        SecuenciaDocumento sec = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(cod_ung, 15);

        return sec;
    }

}
