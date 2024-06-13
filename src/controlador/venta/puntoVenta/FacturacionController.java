/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.puntoVenta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.inventario.articulo.FXMLBuscarArticuloController;
import controlador.venta.cliente.FXMLBusClienterController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manejo.ManejoConfiguracion;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.caja.ManejoCajaChica;
import manejo.caja.ManejoTipoMovimieto;
import manejo.cliente.ManejoCliente;
import manejo.cliente.ManejoPlazo;
import manejo.documento.ManejoTipodocumento;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoFormaPago;
import manejo.factura.ManejoRelacionNcf;
import manejo.factura.ManejoTipoFormaPago;
import manejo.factura.ManejoTipoNcf;
import modelo.Articulo;
import modelo.CajaChica;
import modelo.Cliente;
import modelo.CondicionPago;
import modelo.DetalleCajaChica;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.FormaPago;
import modelo.RelacionNcf;
import modelo.TipoFormaPago;
import modelo.TipoNcf;
import reporte.factura.RptFactura;
import utiles.ClaseUtil;
import utiles.FormatNum;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FacturacionController implements Initializable {

    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Label lbCantidad;
    @FXML
    private JFXButton btnBuscarCliente;
    @FXML
    private JFXButton btnFormaPago;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnImprimir;
    @FXML
    private JFXTextField txtNumeroFactura;
    @FXML
    private JFXTextField txtNcf;
    @FXML
    private JFXTextField txtCodigoCliente;
    @FXML
    private JFXTextField txtNombreCliente;
    @FXML
    private JFXTextField txtDireccion;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private JFXTextField txtRncCliente;
    @FXML
    private JFXTextField txtArticulo;
    @FXML
    private JFXButton btnBuscarArticulo;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXTextField txtSubTotal;
    @FXML
    private JFXTextField txtDescuento;
    @FXML
    private JFXTextField txtItbis;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private TableView<DetalleFactura> tbDetalleFactura;
    @FXML
    private TableColumn<DetalleFactura, String> tbcUnidad;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcTotal;
    @FXML
    private TableColumn<DetalleFactura, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleFactura, String> tbcArticulo;
    @FXML
    private TableColumn<DetalleFactura, Integer> tbcCantidad;
    @FXML
    private TableColumn<DetalleFactura, Double> tbcPrecioUnitario;
    Double devuelta;
    int CodigoFactura;
    @FXML
    private JFXButton btnCostoYGasto;
    @FXML
    private JFXButton btnConsumidorFinal;
    @FXML
    private JFXButton btnDeContado;
    @FXML
    private JFXButton btnCredito;
    @FXML
    private Label lbTipoVenta;

    public Double getDevuelta() {
        return devuelta;
    }

    public void setDevuelta(Double devuelta) {
        this.devuelta = devuelta;
    }

    Double totalPago;

    int tipoVenta = 1;// El valor uno es venta de contado y el dos es venta a credito ;
    int tipoNcf = 2;// el valor uno sustentan costo y gasto y el dos consumidor final;

    ObservableList<DetalleFactura> listadetalleFactura = FXCollections.observableArrayList();
    ObservableList<TipoNcf> listaTipoNcf = FXCollections.observableArrayList();
    ObservableList<String> listaTipoVenta = FXCollections.observableArrayList();

    List<FormaPago> listFormaPagoCollection;
    RelacionNcf relacionNcf;

    Cliente cliente;
    Factura factura;
    DetalleFactura detalleFactura;
    FormaPago formaPago;
    Articulo articulo;

    Stage stage;

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTabla();
        inicializarCombox();
        inicializarDatos();
        nuevo();

        txtCantidad.setOnKeyReleased((event) -> {

            if (event.getCode() == KeyCode.ENTER) {

                try {

                    if (txtArticulo.getText().isEmpty()) {

                        ClaseUtil.mensaje("Tiene que seleccionar un articulo");
                        return;
                    }

                    if (txtCantidad.getText().isEmpty()) {

                        ClaseUtil.mensaje(" Tiene que digital una cantidad ");
                        txtCantidad.requestFocus();
                        return;
                    }

                    if (getArticulo().getExistencia() < Double.parseDouble(txtCantidad.getText().trim())) {

                        ClaseUtil.mensaje("La cantidad a Despachar no puede ser mayor que la existencia ,Existencia igual a " + getArticulo().getExistencia());
                        txtCantidad.requestFocus();
                        return;
                    }

                    agregarArticulo();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }

    private void inicializarCombox() {

        listaTipoVenta.add("De Conatado");
        listaTipoVenta.add("A Credito");

//        cbTipoVenta.setItems(listaTipoVenta);
//        cbTipoVenta.getSelectionModel().select(0);
//        cbTipoNcf.setConverter(new StringConverter<TipoNcf>() {
//
//            @Override
//            public String toString(TipoNcf tipoNcf) {
//                return String.valueOf(tipoNcf.getDescripcion());
//            }
//
//            @Override
//            public TipoNcf fromString(String string) {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//        });
//
//        listaTipoNcf.addAll(ManejoTipoNcf.getInstancia().getListaTipoNcf());
////
//        cbTipoNcf.setItems(listaTipoNcf);
    }

    public void inicializarTabla() {

        listadetalleFactura.clear();

        tbDetalleFactura.setItems(listadetalleFactura);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcArticulo.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

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

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {

        nuevo();
    }

    @FXML
    private void btnBuscarClienteActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/cliente/FXMLBusCliente.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        FXMLBusClienterController articuloController = loader.getController();

        if (!(articuloController.getCliente() == null)) {

            setCliente(articuloController.getCliente());
            txtCodigoCliente.setText(getCliente().getCodigo().toString());
            txtNombreCliente.setText(getCliente().getNombre());
            txtRncCliente.setText(getCliente().getRnc());
            txtDireccion.setText(getCliente().getDireccion());
            txtTelefono.setText(getCliente().getTelefono());
            getCliente().setMontoDisponible(ManejoFactura.getInstancia().getMontoDisponible(getCliente()));

            if (getCliente().getEstadoCliente().getCodigo() == 3) {
                ClaseUtil.mensaje("ESTE CLIENTE ESTA BLOQUIADO  \n COMUNICARSE CON LA ADMINISTRACION");
                nuevo();
            }

        }

    }

    @FXML
    private void btnFormaPagoActionEvent(ActionEvent event) throws IOException {

        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/venta/facturacion/FormaDePago.fxml"));
            loader.load();

            Parent root = loader.getRoot();
            FormaDePagoController formaDePagoController = loader.getController();

            formaDePagoController.getTxtTotalFactura().setText(txtTotal.getText());
            formaDePagoController.getTxtEfectivo().setText(txtTotal.getText());

            stage = ClaseUtil.getStageModal(root);

            if (!(formaDePagoController.getFormaPago() == null)) {

                listFormaPagoCollection = formaDePagoController.getListaFormaPago();
                setDevuelta(formaDePagoController.getTotalPago());

            } else {

                ClaseUtil.mensaje("Tiene que aplicar una forma de pago");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnGuardarActionevent(ActionEvent event) {

        try {

            if (tbDetalleFactura.getItems().size() <= 0) {
                ClaseUtil.mensaje("No tiene Articulos agregado para esta Factura");
                return;
            }

            if (listFormaPagoCollection == null && tipoVenta == 1) {

                ClaseUtil.mensaje("Tiene que aplicar una forma de pago");
                return;
            }

            factura.setAnulada(false);
            factura.setCliente(getCliente());
            factura.setNombreCliente(getCliente().getNombre());
            factura.setNcf(txtNcf.getText());
            factura.setTotal(getTotal());
            factura.setSubTotal(getSubTotal());
            factura.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            factura.setDescuento(getDescuento());
            factura.setItbis(getItbis());

            factura.setFechaCreacion(new Date());

            Date fechaVencimiento = ClaseUtil.asDate(dpFecha.getValue());

            factura.setDetalleFacturaCollection(listadetalleFactura);

            if (tipoVenta == 1) { //Vanta de Contado

                factura.setPagada(true);
                factura.setPendiente(0.00);
                factura.setTipoVenta(1);
                factura.setAbono(factura.getTotal());
                factura.setCondicionPago(new CondicionPago(1));
                factura.setFechaVencimiento(fechaVencimiento);

                if (getDevuelta() < 0) {

                    ClaseUtil.mensaje(" El Monto A Pagado Tiene que ser igual al monto de la Factura ");
                    return;
                }

            } else if (tipoVenta == 2) { //Venta a Credito

                factura.setPagada(false);
                factura.setTipoVenta(2);
                factura.setAbono(0.0);
                factura.setPendiente(factura.getTotal());
                factura.setCondicionPago(new CondicionPago(2));
                factura.setPlazo(ManejoPlazo.getInstancia().getplazo(getCliente().getDiasCredito()));
                fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, getCliente().getDiasCredito());
                factura.setFechaVencimiento(fechaVencimiento);

                formaPago = new FormaPago();
                TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
                formaPago.setTipoFormaPago(tipoFormaPago);
                formaPago.setMonto(factura.getTotal());

            }

            //Comprobar limite de credito
            Double montoPendiente, montodisponible;

            montoPendiente = ManejoFactura.getInstancia().getMontoPendiente(getCliente());
            montodisponible = getCliente().getMontoCredito() - montoPendiente;

            if (tipoVenta == 2) {

                if (montodisponible <= factura.getTotal()) {

                    ClaseUtil.mensaje("EL CLIENTE NO TIENE CREDITO DISPONIBLE");
                    return;
                }
            }

            Optional<ButtonType> ok = ClaseUtil.confirmarMensaje(" Crear  Factura con Comprobante  Fiscal ");

            System.out.println("o.k "+ok.get());
            if (ok.get() == ButtonType.YES) {

                factura.setTipoNcf(ManejoTipoNcf.getInstancia().getListaTipoNcf().get(0));//Sustentan Costo y gastos

                relacionNcf = ManejoRelacionNcf.getInstancia().getNCF(factura.getTipoNcf());

                boolean existe = ManejoFactura.getInstancia().existeNCF(relacionNcf.getActual());

                if (existe) {

                    while (ManejoFactura.getInstancia().existeNCF(relacionNcf.getActual())) {

                        relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo());

                    }

                } else {

                    relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo());

                }

                factura.setNcf(relacionNcf.getActual());

                ManejoRelacionNcf.getInstancia().actualizar(relacionNcf);
                System.out.println("Sin ncf");

            } else {

                factura.setTipoNcf(null); //Consumidor Final
                factura.setNcf("n/a");
                System.out.println("Sin ncf");
            }

            factura = ManejoFactura.getInstancia().salvar(factura);

            //Actualizar caja chica
            CajaChica cajaChica = ManejoCajaChica.getInstancia().getCajaChica(new Date(), "abierta");

            if (!(cajaChica == null)) {

                List<DetalleCajaChica> listaDetalleCajaChica = new ArrayList<>();

                DetalleCajaChica detalleCajaChica = new DetalleCajaChica();
                detalleCajaChica.setCajaChica(cajaChica);
                detalleCajaChica.setTipoMovimiento(ManejoTipoMovimieto
                        .getInstancia()
                        .getTipoMovimiento(9));

                detalleCajaChica.setConcepto("Ventas");
                detalleCajaChica.setTipoDocumento(ManejoTipodocumento
                        .getInstancia()
                        .getTipoDocumento(7));
                detalleCajaChica.setDocumento(factura.getCodigo().toString());
                detalleCajaChica.setMonto(factura.getTotal());
                detalleCajaChica.setNombreMovimiento("INGRESO");
                listaDetalleCajaChica.add(detalleCajaChica);

                cajaChica.setDetalleCajaChicaCollection(listaDetalleCajaChica);
                ManejoCajaChica.getInstancia().actualizar(cajaChica);
            }
            //fin

            getCliente().setMontoDisponible(ManejoFactura.getInstancia().getMontoDisponible(cliente));
            ManejoCliente.getInstancia().actualizar(getCliente());

            CodigoFactura = factura.getCodigo();
            System.out.println("Codigo Factura " + CodigoFactura + " Factura " + factura);

            txtNcf.setText(relacionNcf.getActual());

            if (tipoVenta == 1) { //venta de contado

                for (FormaPago fp : listFormaPagoCollection) {

                    fp.setDocumento(factura.getCodigo());
                    fp.setTipoDocumento(1);
                    fp.setFecha(util.ClaseUtil.asDate(dpFecha.getValue()));
                    ManejoFormaPago.getInstancia().salvar(fp);

                }

            } else if (tipoVenta == 2) { //venta credito

                formaPago.setDocumento(factura.getCodigo());
                formaPago.setTipoDocumento(1);
                formaPago.setFecha(util.ClaseUtil.asDate(dpFecha.getValue()));
                ManejoFormaPago.getInstancia().salvar(formaPago);
            }

            ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorSalida(listadetalleFactura);

            nuevo();

            //nota : si no se guarda el detalle es porque generaste las entidades nuevamente y el atributo cascae se le fue
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @FXML
    private void btnImprimirActionEvent(ActionEvent event) {

        try {
            if (CodigoFactura > 0) {
                System.out.println("Codigo " + CodigoFactura);
                RptFactura.getInstancia().imprimir(CodigoFactura);
                CodigoFactura = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnBuscarArticuloActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/inventario/articulo/FXMLBuscarArticulo.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        System.out.println("ESPERANDOOO!!!");

        FXMLBuscarArticuloController articuloController = loader.getController();

        if (!(articuloController.getArticulo() == null)) {

            if (articuloController.getArticulo().getExistencia() <= 0) {
                ClaseUtil.mensaje("ESTE ARTICULO NO TIENE EXISTENCIA ");
                return;
            }

            if (articuloController.getArticulo().getActivo() == false) {
                ClaseUtil.mensaje("ESTE ARTICULO ESTA INACTIVO ,NO ESTA DISPONIBLE PARA LA VENTA");
                return;
            }

            setArticulo(articuloController.getArticulo());
            txtArticulo.setText(getArticulo().getDescripcion()
                    + " Existencia=" + articuloController.getArticulo().getExistencia() + " "
                    + articuloController.getArticulo().getUnidadSalida().getDescripcion());
            txtCantidad.requestFocus();

        }

    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {

        try {

            if (txtArticulo.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que seleccionar un articulo");
                return;
            }

            if (txtCantidad.getText().isEmpty()) {

                ClaseUtil.mensaje(" Tiene que digital una cantidad ");
                txtCantidad.requestFocus();
                return;
            }

            agregarArticulo();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        try {

            if (tbDetalleFactura.getSelectionModel().getSelectedIndex() != -1) {

                listadetalleFactura.remove(tbDetalleFactura.getSelectionModel().getSelectedIndex());
                tbDetalleFactura.refresh();
                txtSubTotal.setText(getSubTotal().toString());
                txtDescuento.setText(getDescuento().toString());
                txtItbis.setText(getItbis().toString());
                txtTotal.setText(getTotal().toString());

            } else {
                ClaseUtil.mensaje("Tiene que Selccionar un Registro");
            }

            if (getTotal() <= 0) {

                btnFormaPago.setDisable(true);

            } else {
                btnFormaPago.setDisable(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void agregarArticulo() {

        try {

            Double total = 0.00, subTotal = 0.00, valorItbis = 0.00, precioVenta = 0.00;

            switch (cliente.getPrecio()) {

                case 1:
                    precioVenta = getArticulo().getPrecioVenta1();
                    System.out.println("Precio v1 " + precioVenta);
                    break;
                case 2:
                    precioVenta = getArticulo().getPrecioVenta2();
                    System.out.println("Precio v2 " + precioVenta);
                    break;
                case 3:
                    System.out.println("Precio v3 " + precioVenta);
                    precioVenta = getArticulo().getPrecioVenta3();
                    break;
            }

            detalleFactura.setArticulo(getArticulo());
            detalleFactura.setCantidad(Double.parseDouble(txtCantidad.getText()));
            detalleFactura.setDescuento(Double.parseDouble(txtDescuento.getText().isEmpty() ? "0" : txtDescuento.getText()));
            detalleFactura.setExistencia(getArticulo().getExistencia());
            detalleFactura.setNombreArticulo(getArticulo().getNombre());
            detalleFactura.setNuevaExistencia(0.0);
            detalleFactura.setPrecio(precioVenta);

            subTotal = detalleFactura.getCantidad() * detalleFactura.getPrecio();

            if (getArticulo().getExentoItbis()) {

                valorItbis = 0.0;

            } else {

                double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                valorItbis = (subTotal - detalleFactura.getDescuento()) * (itbis / 100);
                System.out.println("Itbis " + (itbis / 100));
            }

            total = (subTotal - detalleFactura.getDescuento()) + valorItbis;
            detalleFactura.setItbis(valorItbis);
            detalleFactura.setSubTotal(subTotal);
            detalleFactura.setTotal(total);
            detalleFactura.setUnidad(getArticulo().getUnidadSalida());
            detalleFactura.setFactura(factura);

            listadetalleFactura.add(detalleFactura);

            txtSubTotal.setText(getSubTotal().toString());
            txtDescuento.setText(getDescuento().toString());
            txtItbis.setText(getItbis().toString());
            txtTotal.setText(getTotal().toString());

            txtCantidad.clear();
            txtArticulo.clear();
            detalleFactura = new DetalleFactura();

//            if (tipoVenta == 1) {
//                btnFormaPago.setDisable(false);
//            } else if (tipoVenta == 2) {
//                btnFormaPago.setDisable(true);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void calcularDescuento() {

        try {

            Double total = 0.00, subTotal = 0.00, valorItbis = 0.00, precioVenta = 0.00;

            detalleFactura.setArticulo(getArticulo());
            detalleFactura.setCantidad(Double.parseDouble(txtCantidad.getText()));
            detalleFactura.setDescuento(Double.parseDouble(txtDescuento.getText().isEmpty() ? "0" : txtDescuento.getText()));
            detalleFactura.setExistencia(getArticulo().getExistencia());
            detalleFactura.setNombreArticulo(getArticulo().getNombre());
            detalleFactura.setNuevaExistencia(0.0);
            detalleFactura.setPrecio(precioVenta);

            subTotal = detalleFactura.getCantidad() * detalleFactura.getPrecio();

            if (getArticulo().getExentoItbis()) {

                valorItbis = 0.0;

            } else {

                double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                valorItbis = (subTotal - detalleFactura.getDescuento()) * (itbis / 100);
                System.out.println("Itbis " + (itbis / 100));
            }

            total = (subTotal - detalleFactura.getDescuento()) + valorItbis;
            detalleFactura.setItbis(valorItbis);
            detalleFactura.setSubTotal(subTotal);
            detalleFactura.setTotal(total);
            detalleFactura.setUnidad(getArticulo().getUnidadSalida());
            detalleFactura.setFactura(factura);

            listadetalleFactura.add(detalleFactura);

            txtSubTotal.setText(getSubTotal().toString());
            txtDescuento.setText(getDescuento().toString());
            txtItbis.setText(getItbis().toString());
            txtTotal.setText(getTotal().toString());

            txtCantidad.clear();
            txtArticulo.clear();
            detalleFactura = new DetalleFactura();

            if (tipoVenta == 1) {
                btnFormaPago.setDisable(false);
            } else if (tipoVenta == 2) {
                btnFormaPago.setDisable(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Double getSubTotal() {

        Double subTotal = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

            subTotal += det.getSubTotal();
        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotal() {

        Double total = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

            total += det.getTotal();
        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getDescuento() {

        Double descuento = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

            descuento += det.getDescuento();
        }

        return FormatNum.FormatearDouble(descuento, 2);
    }

    private Double getItbis() {

        Double itbis = 0.00;

        for (DetalleFactura det : listadetalleFactura) {

            itbis += det.getItbis();
        }

        return FormatNum.FormatearDouble(itbis, 2);
    }

    private void limpiar() {

        txtCodigoCliente.clear();
        txtNombreCliente.clear();
        txtRncCliente.clear();
        txtDireccion.clear();
        txtTelefono.clear();
        txtArticulo.clear();
        txtSubTotal.clear();
        txtTotal.clear();
        txtDescuento.clear();
        txtDescuento.clear();
        txtItbis.clear();
        listadetalleFactura.clear();
        btnFormaPago.setDisable(false);

        llenarClientedeContado();

    }

    private void llenarClientedeContado() {

        setCliente(ManejoCliente.getInstancia().getCliente(1));
        txtCodigoCliente.setText(getCliente().getCodigo().toString());
        txtNombreCliente.setText(getCliente().getNombre());
        txtRncCliente.setText(getCliente().getRnc());
        txtDireccion.setText(getCliente().getDireccion());
        txtTelefono.setText(getCliente().getTelefono());

//        btnBuscarCliente.setDisable(true);
    }

    private void inicializarDatos() {

        llenarClientedeContado();
        dpFecha.setValue(LocalDate.now());
        TipoNcf tnf = ManejoTipoNcf.getInstancia().getListaTipoNcf().get(0);
        relacionNcf = ManejoRelacionNcf.getInstancia().getNCF(tnf);
        txtNcf.setText(relacionNcf.getActual());

//        btnBuscarCliente.setDisable(true);
        txtArticulo.requestFocus();

    }

    private void nuevo() {

        factura = new Factura();
        detalleFactura = new DetalleFactura();
        txtNumeroFactura.setText(ManejoFactura.getInstancia().getNumero().toString());
        limpiar();
        lbTipoVenta.setText("Tipo de Venta Seleccionado : " + " De Contado ");

    }

    private void validarCampo() {

    }

    @FXML
    private void btnCostoYGastoActionEvent(ActionEvent event) {

        TipoNcf tnf = ManejoTipoNcf.getInstancia().getListaTipoNcf().get(0);
        relacionNcf = ManejoRelacionNcf.getInstancia().getNCF(tnf);
        txtNcf.setText(relacionNcf.getActual());
    }

    @FXML
    private void btnConsumidorFinalActionEvent(ActionEvent event) {

        TipoNcf tnf = ManejoTipoNcf.getInstancia().getListaTipoNcf().get(1);
        relacionNcf = ManejoRelacionNcf.getInstancia().getNCF(tnf);
        txtNcf.setText(relacionNcf.getActual());
    }

    @FXML
    private void btnDeContadoActionEvent(ActionEvent event) {

        tipoVenta = 1;//Venta De Contado
        btnFormaPago.setDisable(false);
        llenarClientedeContado();
        lbTipoVenta.setText("Tipo de Venta Seleccionado : " + " De Contado ");
    }

    @FXML
    private void btnCreditoActionEvent(ActionEvent event) {
        tipoVenta = 2;//Venta a Credito
        txtCodigoCliente.clear();
        txtNombreCliente.clear();
        txtRncCliente.clear();
        txtDireccion.clear();
        txtTelefono.clear();
        btnFormaPago.setDisable(true);
        btnBuscarCliente.setDisable(false);
        lbTipoVenta.setText("Tipo de Venta Seleccionado : " + "Credito ");

    }

}
