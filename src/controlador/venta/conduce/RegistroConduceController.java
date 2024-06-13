/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.conduce;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.venta.chofer.BuscarChoferController;
import controlador.venta.puntoVenta.BuscarFacturaController;
import controlador.venta.transporte.BuscarTransporteController;
import controlador.venta.vehiculo.BuscarVehiculoController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import manejo.conduce.ManejoConduce;
import manejo.factura.ManejoFactura;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.Chofer;
import modelo.Conduce;
import modelo.DetalleConduce;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.SecuenciaDocumento;
import modelo.Transporte;
import modelo.Vehiculo;
import reporte.conduce.RpConducePinturaTriplea;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroConduceController implements Initializable {

    @FXML
    private JFXTextField txtNumeroConduce;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtNoFactura;
    @FXML
    private JFXButton btnBuscarFactura;
    @FXML
    private JFXTextField txtChofer;
    @FXML
    private JFXButton btnBuscarChofer;
    @FXML
    private JFXTextField txtVehiculo;
    @FXML
    private JFXButton btnBuscarVehiculo;
    @FXML
    private JFXTextField txtDestino;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private TableView<DetalleConduce> tbDetalleConduce;
    @FXML
    private TableColumn<DetalleConduce, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleConduce, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleConduce, String> tbcOrden;
    @FXML
    private TableColumn<DetalleConduce, String> tbcUnidad;

    @FXML
    private JFXTextField txtTransporte;
    @FXML
    private JFXButton btnBuscarTransporte;

    ObservableList<DetalleConduce> listaDetalle = FXCollections.observableArrayList();

    ObservableList<Chofer> listaChofer = FXCollections.observableArrayList();

    ObservableList<Transporte> listaTrnasporte = FXCollections.observableArrayList();
    ObservableList<Vehiculo> listaVehiculo = FXCollections.observableArrayList();

    Factura factura;
    Conduce conduce;
    DetalleConduce detalleConduce;
    Vehiculo vehiculo;

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }
    Transporte transporte;

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    Chofer chofer;

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    @FXML
    private JFXTextField txtCliente;

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();

        nuevo();
        dpFecha.setValue(LocalDate.now());

    }

    public void inicializarTabla() {

        listaDetalle.clear();

        tbDetalleConduce.setItems(listaDetalle);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcOrden.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));

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
                        property.setValue(cellData.getValue().getArticulo().getNombre());
                    }
                    return property;
                });

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

    }

    @FXML
    private void btnBuscarFacturaActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/facturacion/BuscarFactura.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        BuscarFacturaController buscarFacturaController = loader.getController();

        ClaseUtil.getStageModal(root);

        if (!(buscarFacturaController.getFactura() == null)) {

            setFactura(buscarFacturaController.getFactura());
            txtCliente.setText(getFactura().getNombreCliente());
            txtNoFactura.setText(getFactura().getNumero().toString());
            listaDetalle.clear();

//            btnBuscarCliente.setDisable(true);
//            btnDescuento.setDisable(true);
//            listadetalleFactura.clear();
//            setContiDeVenta(articuloController.getCotizacionDeVenta());
//            factura.setCotizacionDeVenta(getContiDeVenta());
//
//            setCliente(getContiDeVenta().getCliente());
////            setDescuentoPorUsuario(ManejoDescuentoPorUsuario.getInstancia().getDescuentoPorUsuario(getContiDeVenta().getAutorizadorDescuento()));
//            setEjecutivoDeVenta(getContiDeVenta().getVendedor());
//            txtNombreCliente.setText(getContiDeVenta().getCliente().getNombre());
//            txtVendedor.setText(getEjecutivoDeVenta().getNombre());
//
            getFactura().getDetalleFacturaCollection().forEach((det) -> {
                crearDetalleConduce(det);
            });
        }

    }

    @FXML
    private void btnBuscarChoferActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/chofer/BuscarChofer.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        BuscarChoferController controller = loader.getController();

        ClaseUtil.getStageModal(root);

        if (!(controller.getChofer() == null)) {

            setChofer(controller.getChofer());
            txtChofer.setText(getChofer().getNombre());

        }

    }

    @FXML
    private void btnBuscarVehiculoActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/vehiculo/BuscarVehiculo.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        BuscarVehiculoController controller = loader.getController();

        ClaseUtil.getStageModal(root);

        if (!(controller.getVehiculo() == null)) {

            setVehiculo(controller.getVehiculo());
            txtVehiculo.setText(getVehiculo().getNombre());

        }

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (txtNoFactura.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que seleccionar una factura");

                return;

            }
            if (txtTransporte.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que seleccionar un transporte ");

                return;

            }

            if (txtVehiculo.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que seleccionar un vehiculo ");

                return;

            }

            if (txtChofer.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que seleccionar un chofer ");

                return;

            }

            if (txtDestino.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar un destino");
                txtDestino.requestFocus();
                return;

            }

            if (txtComentario.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar una observacion ");
                txtComentario.requestFocus();
                return;

            }

            conduce.setCliente(getFactura().getCliente());
            conduce.setChofer(getChofer());
            conduce.setTransporte(getTransporte());
            conduce.setVehiculo(getVehiculo());
            conduce.setFactura(getFactura());
            conduce.setDestino(txtDestino.getText());
            conduce.setObservacion(txtComentario.getText());
            conduce.setVendedor(getFactura().getVendedor());

            conduce.setNombreCliente(conduce.getCliente().getNombre());
            conduce.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            conduce.setFechaCreacion(new Date());
            conduce.setPedido(null);
            conduce.setAnulado(false);
            conduce.setUsuario(VariablesGlobales.USUARIO);
            conduce.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            //************************
            getSecuenciaDocumento();

            //*************************
            conduce.setDetalleConduceCollection(listaDetalle);

            Conduce conduceDb = ManejoConduce.getInstancia().salvar(conduce);

            if (!(conduceDb == null)) {

                limpiar();
                nuevo();
                getFactura().setDespachada(true);
                ManejoFactura.getInstancia().actualizar(getFactura());

                RpConducePinturaTriplea.getInstancia().verConduce(conduceDb.getCodigo());
            }

//            ClaseUtil.mensaje("Conduce generaado exitosamente");
        } catch (Exception e) {

            ClaseUtil.mensaje("Hubo un error generaado el conduce ");
            e.printStackTrace();
        }
    }

    @FXML
    private void tbDetalleEntradaInventarioMouseCliked(MouseEvent event) {
    }

    @FXML
    private void btnBuscarTransporteActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/transporte/BuscarTransporte.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        BuscarTransporteController controller = loader.getController();

        ClaseUtil.getStageModal(root);

        if (!(controller.getTransporte() == null)) {

            setTransporte(controller.getTransporte());
            txtTransporte.setText(getTransporte().getNombre());

        }

    }

    private void nuevo() {
        conduce = new Conduce();
        secuenciaDocumento();
    }

    private void limpiar() {

        txtChofer.clear();
        txtVehiculo.clear();
        txtCliente.clear();
        txtTransporte.clear();
        txtDestino.clear();
        txtComentario.clear();
        listaDetalle.clear();
        txtNumeroConduce.clear();
        conduce = null;
    }

    private void crearDetalleConduce(DetalleFactura detFac) {

        detalleConduce = new DetalleConduce();
        detalleConduce.setArticulo(detFac.getArticulo());
        detalleConduce.setCantidad(detFac.getCantidad());
        detalleConduce.setUnidad(detFac.getUnidad());
        detalleConduce.setConduce(conduce);
        listaDetalle.add(detalleConduce);

    }

    private void getSecuenciaDocumento() {

        SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(conduce.getUnidadDeNegocio().getCodigo(), 15);

        if (!(secDoc == null)) {

            boolean existe = ManejoConduce.getInstancia().existeSecuencia(secDoc.getNumero());

            if (existe) {

                System.out.println("existe " + secDoc.getNumero());

                while (ManejoConduce.getInstancia().existeSecuencia(secDoc.getNumero())) {

                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

                secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(conduce.getUnidadDeNegocio().getCodigo(), 15);

                conduce.setNumero(secDoc.getNumero());
                conduce.setSecuenciaDocumento(secDoc.getCodigo());

            } else {

                System.out.println("No existe " + secDoc.getNumero());
                conduce.setNumero(secDoc.getNumero());
                conduce.setSecuenciaDocumento(secDoc.getCodigo());
                secDoc.setNumero(secDoc.getNumero() + 1);
                ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

            }

        } else {

            ClaseUtil.mensaje("La secuencia para el conduce de la unidad de negocio "
                    + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
            return;
        }

    }

    private void secuenciaDocumento() {

        String numero = "";
        int cod_ung = VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
        SecuenciaDocumento sec = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(cod_ung, 15);

        if (!(sec == null)) {

            numero = Integer.toString(ManejoSecuenciaDocumento
                    .getInstancia().getSecuenciaDocumento(cod_ung, 15).getNumero());
            txtNumeroConduce.setText(numero);

        }

    }

}
