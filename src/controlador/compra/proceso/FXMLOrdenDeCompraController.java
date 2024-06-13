/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.compra.proceso;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.inventario.articulo.FXMLBuscarArticuloController;
import controlador.suplidor.FXMLBusSuplidorController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.articulo.ManejoArticulo;
import manejo.ordenCompra.OrdenDeCompraDao;
import manejo.suplidor.ImpuestoDao;
import manejo.suplidor.MonedaDao;
import manejo.suplidor.PlazoDao;
import manejo.suplidor.TiempoDeEntregaDao;
import modelo.Articulo;
import modelo.DetalleOrdenCompra;
import modelo.Moneda;
import modelo.OrdenCompra;
import modelo.Plazo;
import modelo.Suplidor;
import modelo.TiempoEntrega;
import reporte.compra.RptOrdenCompra;
import reporte.compra.RptOrdenCompraPinturaTriplea;
import util.ClaseUtil;
import util.FormatNum;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class FXMLOrdenDeCompraController implements Initializable {

    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtNumeroOrden;
    @FXML
    private JFXTextField txtSuplidor;
    @FXML
    private JFXButton btBuscarSupplidor;
    @FXML
    private JFXTextField txtRnc;
    @FXML
    private JFXComboBox<Moneda> cbMoneda;
    @FXML
    private JFXComboBox<TiempoEntrega> cbTiempoDeEntrega;

    @FXML
    private JFXComboBox<Plazo> cbPlazo;
    @FXML
    private TextArea txtConcepto;
    @FXML
    private JFXTextField txtArticulo;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private TableView<DetalleOrdenCompra> tbDetalleOrden;
    @FXML
    private TableColumn<DetalleOrdenCompra, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleOrdenCompra, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcPrecio;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcCantidad;
    @FXML
    private TableColumn<DetalleOrdenCompra, String> tbcUnidad;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcSubTotal;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcDescuento;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcItbis;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcIsr;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcTotal;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcTotalAPagar;
    @FXML
    private JFXTextField txtDescuento;

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXTextField txtSubTotal;
    @FXML
    private JFXTextField txtTotal;

    ObservableList<DetalleOrdenCompra> listaDetalleOrden = FXCollections.observableArrayList();
    ObservableList<Plazo> listaPlazo = FXCollections.observableArrayList();
    ObservableList<Moneda> listaModena = FXCollections.observableArrayList();
    ObservableList<TiempoEntrega> listaTiempoEntrega = FXCollections.observableArrayList();

    private Suplidor suplidor;
    private Articulo articulo;
    @FXML
    private JFXTextField txtCantidad;

    DetalleOrdenCompra detalleOrdenCompra;
    OrdenCompra ordenCompra;
    @FXML
    private JFXButton btnBuscarArticulo;
    @FXML
    private JFXTextField txtItbis;
    @FXML
    private JFXTextField txtIsr;
    @FXML
    private JFXTextField txtTotalaPagar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ordenCompra = new OrdenCompra();
        iniciarTablaDetalleOrden();
        inicializarCombox();
        inicializarSecuencia();
        dpFecha.setValue(LocalDate.now());

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

                    agregarArticulo();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }

    private void inicializarCombox() {

        listaModena.addAll(MonedaDao.getInstancia().getMoneda());
        listaPlazo.addAll(PlazoDao.getInstancia().getListaPlazo());
        listaTiempoEntrega.addAll(TiempoDeEntregaDao.getInstancia().getListaTiempoEntrega());

        cbMoneda.setConverter(new StringConverter<Moneda>() {

            @Override
            public String toString(Moneda moneda) {
                return moneda.getNombre();
            }

            @Override
            public Moneda fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTiempoDeEntrega.setConverter(new StringConverter<TiempoEntrega>() {

            @Override
            public String toString(TiempoEntrega tiempoEntrega) {
                return tiempoEntrega.getDescripcion();
            }

            @Override
            public TiempoEntrega fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbPlazo.setConverter(new StringConverter<Plazo>() {

            @Override
            public String toString(Plazo plazo) {
                return plazo.getDescripcion();
            }

            @Override
            public Plazo fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbMoneda.setItems(listaModena);
        cbPlazo.setItems(listaPlazo);
        cbTiempoDeEntrega.setItems(listaTiempoEntrega);
        cbMoneda.getSelectionModel().select(0);

    }

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @FXML
    private void cbMonedaActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) throws IOException {

        try {

            if (txtArticulo.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que seleccionar un articulo");
                return;
            }

            if (txtCantidad.getText().isEmpty()) {

                ClaseUtil.mensaje(" Tiene que digital una cantidad ");
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

            if (tbDetalleOrden.getSelectionModel().getSelectedIndex() != -1) {

                listaDetalleOrden.remove(tbDetalleOrden.getSelectionModel().getSelectedIndex());
                tbDetalleOrden.refresh();
                txtSubTotal.setText(getSubTotal().toString());
                txtDescuento.setText(getTotalDescuento().toString());
                txtItbis.setText(getTotalItbis().toString());
                txtTotal.setText(getTotal().toString());

            } else {
                ClaseUtil.mensaje("Tiene que selccionar un registro");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (!validar().isEmpty()) {

                ClaseUtil.mensaje(validar());
                return;
            }

            guardar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void txtCantidadKeyPressed(KeyEvent event) {

    }

    public void iniciarTablaDetalleOrden() {

        listaDetalleOrden.clear();

        tbDetalleOrden.setItems(listaDetalleOrden);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));

        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcionArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcDescuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        tbcItbis.setCellValueFactory(new PropertyValueFactory<>("itbis"));
        tbcIsr.setCellValueFactory(new PropertyValueFactory<>("isr"));
        tbcSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcTotalAPagar.setCellValueFactory(new PropertyValueFactory<>("totalAPagar"));
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));

        tbDetalleOrden.setEditable(true);
        tbcCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });

        tbcDescripcionArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getDescripcion());
                    }
                    return property;
                });

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getUnidadEntrada().getDescripcion());
                    }
                    return property;
                });

        tbcPrecio.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcCantidad.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcDescuento.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcItbis.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcPrecio.setOnEditCommit(data -> {

            try {

                DetalleOrdenCompra p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = p.getCantidad(),
                        descuento = 0.00, impuesto = 0.00, precio = p.getPrecioUnitario(),
                        valorItbis = 0.00;

                if (p.getArticulo().getExentoItbis() == false) {

                    impuesto = ImpuestoDao.getInstancia().getImpuestoItbis(1).getValor().doubleValue();
                }

                if (data.getNewValue() >= 0) {

                    p.setPrecioUnitario(data.getNewValue());

                    subTotal = p.getPrecioUnitario() * cantidad;
                    valorItbis = FormatNum.FormatearDouble((subTotal - p.getDescuento()) * (impuesto / 100), 2);

                    total = (subTotal - p.getDescuento()) + valorItbis;

                    p.setSubTotal(subTotal);
                    p.setTotal(total);
                    p.setItbis(valorItbis);

                    tbDetalleOrden.refresh();
                    tbDetalleOrden.requestFocus();

                    txtSubTotal.setText(getSubTotal().toString());
                    txtDescuento.setText(getTotalDescuento().toString());
                    txtItbis.setText(getTotalItbis().toString());
                    txtIsr.setText(getTotalIsr().toString());
                    txtTotal.setText(getTotal().toString());
                    txtTotalaPagar.setText(getTotalAPagar().toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleOrden.refresh();
                    tbDetalleOrden.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcCantidad.setOnEditCommit(data -> {

            try {

                DetalleOrdenCompra p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00,
                        descuento = 0.00, impuesto = 0.00, precio = p.getPrecioUnitario(), valorImpuesto = 0.00;

                if (data.getNewValue() >= 0) {

                    p.setCantidad(data.getNewValue());
                    cantidad = p.getCantidad();

                    if (p.getArticulo().getExentoItbis() == false
                            && !(ImpuestoDao.getInstancia().getImpuestoItbis(1) == null)) {

                        impuesto = ImpuestoDao.getInstancia().getImpuestoItbis(1).getValor().doubleValue();
                    }

                    System.out.println("Impuesto " + impuesto);

                    subTotal = FormatNum.FormatearDouble(precio * cantidad, 2);
                    valorImpuesto = FormatNum.FormatearDouble((subTotal - descuento) * (impuesto / 100), 2);

                    p.setItbis(valorImpuesto);

                    total = (subTotal - p.getDescuento()) + p.getItbis();

                    p.setSubTotal(subTotal);
                    p.setTotal(total);

                    tbDetalleOrden.refresh();
                    tbDetalleOrden.requestFocus();

                    txtSubTotal.setText(getSubTotal().toString());
                    txtDescuento.setText(getTotalDescuento().toString());
                    txtItbis.setText(getTotalItbis().toString());
                    txtIsr.setText(getTotalIsr().toString());
                    txtTotal.setText(getTotal().toString());
                    txtTotalaPagar.setText(getTotalAPagar().toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleOrden.refresh();
                    tbDetalleOrden.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcDescuento.setOnEditCommit(data -> {

            try {

                DetalleOrdenCompra p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00,
                        descuento = 0.00, impuesto = 0.00, precio = p.getPrecioUnitario(), valorItbis = 0.00;

                if (data.getNewValue() >= 0) {

                    p.setDescuento(data.getNewValue());
                    cantidad = p.getCantidad();
                    subTotal = precio * cantidad;

                    if (p.getArticulo().getExentoItbis() == false
                            && !(ImpuestoDao.getInstancia().getImpuestoItbis(1) == null)) {

                        impuesto = ImpuestoDao.getInstancia().getImpuestoItbis(1).getValor().doubleValue();
                    }

                    System.out.println("Impuesto " + impuesto);

                    subTotal = FormatNum.FormatearDouble(precio * cantidad, 2);

                    valorItbis = FormatNum.FormatearDouble((subTotal - descuento) * (impuesto / 100), 2);
                    total = (subTotal - p.getDescuento()) + valorItbis;
                    p.setSubTotal(subTotal);
                    p.setTotal(total);
                    p.setItbis(valorItbis);

                    tbDetalleOrden.refresh();
                    tbDetalleOrden.requestFocus();

                    txtSubTotal.setText(getSubTotal().toString());
                    txtDescuento.setText(getTotalDescuento().toString());
                    txtItbis.setText(getTotalItbis().toString());
                    txtIsr.setText(getTotalIsr().toString());
                    txtTotal.setText(getTotal().toString());
                    txtTotalaPagar.setText(getTotalAPagar().toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleOrden.refresh();
                    tbDetalleOrden.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcItbis.setOnEditCommit(data -> {

            try {

                DetalleOrdenCompra p = data.getRowValue();
                Double subTotal = 0.00, total = 0.00, cantidad = 0.00,
                        descuento = 0.00, impuesto = 0.00, precio = p.getPrecioUnitario();

                if (data.getNewValue() >= 0) {

                    p.setItbis(data.getNewValue());
                    cantidad = p.getCantidad();
                    subTotal = precio * cantidad;
                    total = (subTotal - p.getDescuento()) + p.getItbis();

                    p.setSubTotal(subTotal);
                    p.setTotal(total);

                    tbDetalleOrden.refresh();
                    tbDetalleOrden.requestFocus();

                    txtSubTotal.setText(getSubTotal().toString());
                    txtDescuento.setText(getTotalDescuento().toString());
                    txtItbis.setText(getTotalItbis().toString());
                    txtIsr.setText(getTotalIsr().toString());
                    txtTotal.setText(getTotal().toString());
                    txtTotalaPagar.setText(getTotalAPagar().toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleOrden.refresh();
                    tbDetalleOrden.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    @FXML
    private void btnBuscarArticuloActionEvent(ActionEvent event) throws IOException {

//        FXMLLoader loader = new FXMLLoader();
//
//        loader.setLocation(getClass().getResource("/vista/inventario/articulo/FXMLBuscarArticulo.fxml"));
//        loader.load();
//
//        Parent root = loader.getRoot();
//
//        Scene scene = new Scene(root);
//
//        Stage stage = new Stage();
//
//        stage.setScene(scene);
//
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.showAndWait();
        FXMLLoader loader = new FXMLLoader();
//
        loader.setLocation(getClass().getResource("/vista/inventario/articulo/FXMLBuscarArticulo.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        FXMLBuscarArticuloController articuloController = loader.getController();
        articuloController.setOrigen(1);//el valor  3 es para  buscar solo articulos de consumo

        articuloController.llenarCampo();

        utiles.ClaseUtil.getStageModal(root);

        System.out.println("ESPERANDOOO!!!");

//        FXMLBuscarArticuloController articuloController = loader.getController();
        if (!(articuloController.getArticulo() == null)) {

            System.out.println("Estado articulo " + articuloController.getArticulo().getActivo());
            if (articuloController.getArticulo().getActivo() == false) {

                ClaseUtil.mensaje("Este Articulo esta inactivo,no se le puede hacer orden de compra");
                return;
            }

            setArticulo(articuloController.getArticulo());
            txtArticulo.setText(getArticulo().getDescripcion());
            txtCantidad.requestFocus();

        }
    }

    @FXML
    private void btBuscarSupplidorActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/suplidor/FXMLBusSuplidor.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        System.out.println("ESPERANDOOO!!!");

        FXMLBusSuplidorController suplidorController = loader.getController();

        if (!(suplidorController.getSuplidor() == null)) {

            setSuplidor(suplidorController.getSuplidor());
            txtSuplidor.setText(getSuplidor().getDescripcion() + " (" + getSuplidor().getTipoSuplidor().getNombre() + ")");
            txtRnc.setText(getSuplidor().getRnc());
        }

    }

    private void agregarArticulo() {

        try {

            detalleOrdenCompra = new DetalleOrdenCompra();

            Double subTotal = 0.00, subTotalConDescuento = 0.00, total = 0.00,
                    totalAPagar = 0.00, cantidad = Double.parseDouble(txtCantidad.getText()),
                    precio = getArticulo().getPrecioCompra(), descuento = 0.00, valorImpuesto = 0.00,
                    impuesto = 0.00;

            System.out.println("Excento de itbis " + getArticulo().getExentoItbis());

            if (getArticulo().getExentoItbis() == false
                    && !(ImpuestoDao.getInstancia().getImpuestoItbis(1) == null)) {

                impuesto = ImpuestoDao.getInstancia().getImpuestoItbis(1).getValor().doubleValue();
            }

            System.out.println("Impuesto " + impuesto);

            subTotal = FormatNum.FormatearDouble(precio * cantidad, 2);
            valorImpuesto = FormatNum.FormatearDouble((subTotal - descuento) * (impuesto / 100), 2);
            total = FormatNum.FormatearDouble((subTotal - descuento) + valorImpuesto, 2);
            totalAPagar = FormatNum.FormatearDouble((subTotal - descuento) + valorImpuesto, 2);
            subTotalConDescuento = FormatNum.FormatearDouble((subTotal - descuento), 2);

            detalleOrdenCompra.setArticulo(getArticulo());
            detalleOrdenCompra.setDescripcionArticulo(getArticulo().getDescripcion());
            detalleOrdenCompra.setCantidad(cantidad);
            detalleOrdenCompra.setPrecioUnitario(precio);
            detalleOrdenCompra.setSubTotal(subTotal);
            detalleOrdenCompra.setTotal(total);
            detalleOrdenCompra.setTotalAPagar(totalAPagar);
            detalleOrdenCompra.setIsr(0.00);
            detalleOrdenCompra.setDescuento(descuento);
            detalleOrdenCompra.setSubTotalConDescuento(subTotalConDescuento);
            detalleOrdenCompra.setUnidad(getArticulo().getUnidadEntrada());
            detalleOrdenCompra.setOrdenCompra(ordenCompra);
            detalleOrdenCompra.setItbis(valorImpuesto);

            if (existe(detalleOrdenCompra)) {
                ClaseUtil.mensaje("Este Articulo ya Existe en el Detalle ");
                return;
            }

            listaDetalleOrden.add(detalleOrdenCompra);

            txtArticulo.clear();
            txtCantidad.clear();

            txtSubTotal.setText(getSubTotal().toString());
            txtDescuento.setText(getTotalDescuento().toString());
            txtItbis.setText(getTotalItbis().toString());
            txtIsr.setText(getTotalIsr().toString());
            txtTotal.setText(getTotal().toString());
            txtTotalaPagar.setText(getTotalAPagar().toString());

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private String validar() {

        String msgError = "";

        if (tbDetalleOrden.getItems().size() <= 0) {

            msgError = "El detalle de la orden esta vacio";

            return msgError;
        }

        if (dpFecha.getValue() == null) {

            msgError = "La fecha esta vacia";
            dpFecha.requestFocus();
            return msgError;
        }
        if (txtNumeroOrden.getText().isEmpty()) {
            msgError = "La orden de compra esta vacio";
            txtNumeroOrden.requestFocus();
            return msgError;
        }

//      
//        if (cbMoneda.getSelectionModel().getSelectedIndex() == -1) {
//            msgError = "Tiene que seleccionar un Banco";
//            cbMoneda.requestFocus();
//            return msgError;
//        }
        if (txtSuplidor.getText().isEmpty()) {
            msgError = "El nombre del supliidor esta vacio";
            txtSuplidor.requestFocus();
            return msgError;
        }

        if (txtConcepto.getText().isEmpty()) {
            msgError = "El Concepto esta vacio";
            txtConcepto.requestFocus();
            return msgError;
        }

        return msgError;
    }

    private Double getTotal() {

        Double total = 0.00;

        for (DetalleOrdenCompra det : listaDetalleOrden) {

            total += det.getTotal();
        }

        return total;
    }

    private Double getTotalAPagar() {

        Double totalAPagar = 0.00;

        for (DetalleOrdenCompra det : listaDetalleOrden) {

            totalAPagar += det.getTotalAPagar();
        }

        return totalAPagar;
    }

    private Double getSubTotal() {

        Double subTotal = 0.00;

        for (DetalleOrdenCompra det : listaDetalleOrden) {

            subTotal += det.getSubTotal();
        }

        return subTotal;
    }

    private Double getSubTotalConDescuento() {

        Double subTotal = 0.00;

        for (DetalleOrdenCompra det : listaDetalleOrden) {

            subTotal += det.getSubTotalConDescuento();
        }

        return subTotal;
    }

    private Double getTotalItbis() {

        Double totalItbis = 0.00;

        for (DetalleOrdenCompra det : listaDetalleOrden) {

            totalItbis += det.getItbis();
        }

        return totalItbis;
    }

    private Double getTotalIsr() {

        Double totalIsr = 0.00;

        for (DetalleOrdenCompra det : listaDetalleOrden) {

            totalIsr += det.getIsr();
        }

        return totalIsr;
    }

    private Double getTotalDescuento() {

        Double totalDescuento = 0.00;

        for (DetalleOrdenCompra det : listaDetalleOrden) {

            totalDescuento += det.getDescuento();
        }

        return totalDescuento;
    }

    private void guardar() {

        try {

            if (cbPlazo.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar el plazo");
                return;
            }

            if (cbTiempoDeEntrega.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que seleccionar el tiempo de entrega");
                return;
            }

            if (txtConcepto.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar un concepto");
                return;
            }

            for (DetalleOrdenCompra det : listaDetalleOrden) {

                if (det.getPrecioUnitario() <= 0) {

                    ClaseUtil.mensaje("Ete Articulo  " + det.getArticulo().getDescripcion() + " no tiene precio ");
                    return;
                }
            }

            //Actualizando precio de compra
            for (DetalleOrdenCompra det : listaDetalleOrden) {

                det.getArticulo().setPrecioCompra(det.getPrecioUnitario());
                ManejoArticulo.getInstancia().actualizar(det.getArticulo());

            }

            ordenCompra.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            ordenCompra.setAnulada(false);
            ordenCompra.setComentario(txtConcepto.getText());
            ordenCompra.setEstado("Pendiente");
            ordenCompra.setCompletada("no");
            ordenCompra.setFechaRegistro(new Date());
            ordenCompra.setNombreSuplidor(txtSuplidor.getText());
            ordenCompra.setRnc(txtRnc.getText());
            ordenCompra.setSubtotal(getSubTotal());
            ordenCompra.setTotal(getTotal());
            ordenCompra.setSuplidor(getSuplidor().getCodigo());
            ordenCompra.setTotalDescuento(getTotalDescuento());
            ordenCompra.setTotalItbis(getTotalItbis());
            ordenCompra.setTotalIsr(getTotalItbis());
            ordenCompra.setUsuario(VariablesGlobales.USUARIO.getCodigo());
            ordenCompra.setAutorizada(false);
            ordenCompra.setMoneda(cbMoneda.getSelectionModel().getSelectedItem());
            ordenCompra.setTiempoEntrega(cbTiempoDeEntrega.getSelectionModel().getSelectedItem());
            ordenCompra.setTotalAPagar(getTotalAPagar());
            ordenCompra.setTotalDescuento(getTotalDescuento());
            ordenCompra.setSubTotal(getSubTotal());
            ordenCompra.setSubTotalConDescuento(getSubTotalConDescuento());
            ordenCompra.setFacturada(false);
            //Autorizar orden
            ordenCompra.setEstado("Abierta");
            ordenCompra.setAutorizada(true);
            ordenCompra.setFechaAutorizacion(new Date());
            ordenCompra.setAutorizador(VariablesGlobales.USUARIO.getCodigo());
            ordenCompra.setNombreAutorizador(VariablesGlobales.USUARIO.getNombre());
            ordenCompra.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            ordenCompra.setDetalleOrdenCompraCollection(listaDetalleOrden);
//            ordenCompra.setDetalleOrdenCompraCollection(tbDetalleOrden.getItems());

            ordenCompra = OrdenDeCompraDao.getInstancia().salvar(ordenCompra);

            if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {
                
                RptOrdenCompraPinturaTriplea.getInstancia().imprimir(ordenCompra.getCodigo());
                
            } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {
                
                RptOrdenCompra.getInstancia().imprimir(ordenCompra.getCodigo());
                
            }

            ordenCompra = new OrdenCompra();
            limpiar();

//            ClaseUtil.mensaje("Registro guardado Exitosamente");
        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un Error guardando la Orden ");
            e.printStackTrace();
        }

    }

    private boolean existe(DetalleOrdenCompra det) {

        for (DetalleOrdenCompra detalle : tbDetalleOrden.getItems()) {

            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())) {

                return true;

            }
        }

        return false;
    }

    private void limpiar() {

        txtNumeroOrden.clear();
        txtArticulo.clear();
        txtCantidad.clear();
        txtConcepto.clear();
        txtRnc.clear();
        txtSubTotal.clear();
        txtTotal.clear();
        txtItbis.clear();
        txtIsr.clear();
        txtSuplidor.clear();
        txtTotalaPagar.clear();
        txtDescuento.clear();
        listaDetalleOrden.clear();
        cbPlazo.getSelectionModel().clearSelection();
        cbTiempoDeEntrega.getSelectionModel().clearSelection();
        inicializarSecuencia();

    }

    private void inicializarSecuencia() {

        txtNumeroOrden.setText(OrdenDeCompraDao.getInstancia().getNumero().toString());
    }

    @FXML
    void cbPlazoActionEvent(ActionEvent event) {

    }

}
