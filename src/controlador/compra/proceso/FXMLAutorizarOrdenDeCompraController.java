/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.compra.proceso;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import manejo.ordenCompra.OrdenDeCompraDao;
import modelo.DetalleOrdenCompra;
import modelo.OrdenCompra;
import reporte.compra.RptOrdenCompra;
import util.ClaseUtil;
import util.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class FXMLAutorizarOrdenDeCompraController implements Initializable {

    @FXML
    private JFXButton btnRechazar;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXTextField txtConcepto;
    @FXML
    private JFXTextField txtConcepto1;
    @FXML
    private JFXRadioButton rbAbierta;
    @FXML
    private JFXRadioButton rbCerrada;
    final ToggleGroup group = new ToggleGroup();
    @FXML
    private JFXButton btnVerDocumento;
    @FXML
    private JFXButton btnVerDocumento1;

    @FXML
    private TableView<OrdenCompra> tbOrdenCompraPendiente;
    @FXML
    private TableView<DetalleOrdenCompra> tbDetalleOrden;
    @FXML
    private JFXTextField txtCantidadArticulo;
    @FXML
    private TableView<OrdenCompra> tbOrdenCompraAutorizada;
    @FXML
    private TableView<DetalleOrdenCompra> tbDetalleOrdenAutorizada;

    @FXML
    private JFXTextField txtCantidadArticulo1;
    @FXML
    private TableColumn<OrdenCompra, String> tbcOrdenCompraNoOrden;
    @FXML
    private TableColumn<OrdenCompra, Date> tbcOrdenCompraFecha;
    @FXML
    private TableColumn<OrdenCompra, String> tbcOrdenCompraSuplidor;
    @FXML
    private TableColumn<OrdenCompra, Double> tbcOrdenCompraSubTotal;
    @FXML
    private TableColumn<OrdenCompra, Double> tbcOrdenCompraDescuento;
    @FXML
    private TableColumn<OrdenCompra, Double> tbcOrdenCompraImpuesto;
    @FXML
    private TableColumn<OrdenCompra, Double> tbcOrdenCompraTotal;
    @FXML
    private TableColumn<DetalleOrdenCompra, String> tbcDetalleOrdenCodigoArticulo;
    @FXML
    private TableColumn<DetalleOrdenCompra, String> tbcDetalleOrdenDescripcionArticulo;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcDetalleOrdenPrecio;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcDetalleOrdenCantidad;
    @FXML
    private TableColumn<DetalleOrdenCompra, String> tbcDetalleOrdenUnidad;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcDetalleOrdenSubTotal;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcDetalleOrdenDescuento;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcDetalleOrdenImpuesto;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcDetalleOrdenTotal;
    @FXML
    private TableColumn<OrdenCompra, String> tbcOrdenAutorizadaNoOrden;
    @FXML
    private TableColumn<OrdenCompra, Date> tbcOrdenAutorizadaFecha;
    @FXML
    private TableColumn<OrdenCompra, String> tbcOrdenAutorizadaSuplidor;
    @FXML
    private TableColumn<OrdenCompra, Double> tbcOrdenAutorizadaSubTotal;
    @FXML
    private TableColumn<OrdenCompra, Double> tbcOrdenAutorizadaDescuento;
    @FXML
    private TableColumn<OrdenCompra, Double> tbcOrdenAutorizadaImpuesto;
    @FXML
    private TableColumn<OrdenCompra, Double> tbcOrdenAutorizadaTotal;
    @FXML
    private TableColumn<DetalleOrdenCompra, String> tbcDetalleOrdenAutorizadaCodigoArticulo;
    @FXML
    private TableColumn<DetalleOrdenCompra, String> tbcDetalleOrdenAutorizadaDescripcionArticulo;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcDetalleOrdenAutorizadaPrecio;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcDetalleOrdenAutorizadaCantidad;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcDetalleOrdenAutorizadaUnidad;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcDetalleOrdenAutorizadaSubTotal;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcDetalleOrdenAutorizadaDescuento;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcDetalleOrdenAutorizadaImpuesto;
    @FXML
    private TableColumn<DetalleOrdenCompra, Double> tbcDetalleOrdenAutorizadaTotal;

    ObservableList<OrdenCompra> listaOrdeAutorizada = FXCollections.observableArrayList();
    ObservableList<OrdenCompra> listaOrdenCompra = FXCollections.observableArrayList();
    ObservableList<DetalleOrdenCompra> listaDetalleOrdenAutorizada = FXCollections.observableArrayList();
    ObservableList<DetalleOrdenCompra> listaDetalleOrden = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtCantidadOrden;
    @FXML
    private JFXTextField txtTotalOrden;
    @FXML
    private JFXTextField txtCantidadOrdenAutorizada;
    @FXML
    private JFXTextField txtTotalOrdenAutorizada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaOrdenCompra();
        iniciarTablaOrdenCompraAutorizada();
        iniciarTablaDetalleOrden();
        iniciarTablaDetalleOrdenAutorizada();

        rbAbierta.setToggleGroup(group);
        rbCerrada.setToggleGroup(group);

    }

    public void iniciarTablaDetalleOrden() {

        listaDetalleOrden.clear();
        tbDetalleOrden.setItems(listaDetalleOrden);

        tbcDetalleOrdenCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcDetalleOrdenDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcionArticulo"));
        tbcDetalleOrdenCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcDetalleOrdenDescuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        tbcDetalleOrdenImpuesto.setCellValueFactory(new PropertyValueFactory<>("impuesto"));
        tbcDetalleOrdenSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        tbcDetalleOrdenTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcDetalleOrdenPrecio.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));

        tbcDetalleOrdenCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });

        tbcDetalleOrdenDescripcionArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getDescripcion());
                    }
                    return property;
                });

        tbcDetalleOrdenUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getUnidadEntrada().getDescripcion());
                    }
                    return property;
                });

    }

    public void iniciarTablaDetalleOrdenAutorizada() {

        listaDetalleOrdenAutorizada.clear();
        tbDetalleOrdenAutorizada.setItems(listaDetalleOrdenAutorizada);

        tbcDetalleOrdenAutorizadaCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcDetalleOrdenAutorizadaDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcionArticulo"));
        tbcDetalleOrdenAutorizadaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcDetalleOrdenAutorizadaDescuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        tbcDetalleOrdenAutorizadaImpuesto.setCellValueFactory(new PropertyValueFactory<>("impuesto"));
        tbcDetalleOrdenAutorizadaSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        tbcDetalleOrdenAutorizadaTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcDetalleOrdenAutorizadaPrecio.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));

        tbcDetalleOrdenAutorizadaCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });

        tbcDetalleOrdenDescripcionArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getDescripcion());
                    }
                    return property;
                });

        tbcDetalleOrdenUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getUnidadEntrada().getDescripcion());
                    }
                    return property;
                });

    }

    public void iniciarTablaOrdenCompra() {

        tbOrdenCompraPendiente.setItems(listaOrdenCompra);

        tbcOrdenCompraNoOrden.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcOrdenCompraFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcOrdenCompraSubTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        tbcOrdenCompraTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcOrdenCompraDescuento.setCellValueFactory(new PropertyValueFactory<>("totalDescuento"));
        tbcOrdenCompraImpuesto.setCellValueFactory(new PropertyValueFactory<>("totalImpuesto"));

        tbcOrdenCompraSuplidor.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getSuplidor() != null) {
                        property.setValue(cellData.getValue().getNombreSuplidor());
                    }
                    return property;
                });

        listaOrdenCompra.addAll(OrdenDeCompraDao.getInstancia().getListOrdenDeCompraPendiente());
        txtCantidadOrden.setText(cantidadOrden(false).toString());
        txtTotalOrden.setText(totalOrden(false).toString());

    }

    public void iniciarTablaOrdenCompraAutorizada() {

        tbOrdenCompraAutorizada.setItems(listaOrdeAutorizada);

        tbcOrdenAutorizadaNoOrden.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcOrdenAutorizadaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcOrdenAutorizadaSubTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        tbcOrdenAutorizadaTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcOrdenAutorizadaDescuento.setCellValueFactory(new PropertyValueFactory<>("totalDescuento"));
        tbcOrdenAutorizadaImpuesto.setCellValueFactory(new PropertyValueFactory<>("totalImpuesto"));

        tbcOrdenAutorizadaSuplidor.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getSuplidor() != null) {
                        property.setValue(cellData.getValue().getNombreSuplidor());
                    }
                    return property;
                });

        listaOrdeAutorizada.addAll(OrdenDeCompraDao.getInstancia().getListOrdenDeCompraAutorizada());
        txtCantidadOrdenAutorizada.setText(cantidadOrden(true).toString());
        txtTotalOrdenAutorizada.setText(totalOrden(true).toString());

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (!validar().isEmpty()) {

                ClaseUtil.mensaje(validar());
                return;
            }

            OrdenCompra ordenCompra = tbOrdenCompraPendiente.getSelectionModel().getSelectedItem();
            ordenCompra.setEstado("Abierta");
            ordenCompra.setAutorizada(true);
            ordenCompra.setFechaAutorizacion(new Date());
            ordenCompra.setAutorizador(VariablesGlobales.USUARIO.getCodigo());
            ordenCompra.setNombreAutorizador(VariablesGlobales.USUARIO.getNombre());

            OrdenDeCompraDao.getInstancia().salvar(ordenCompra);

            listaOrdenCompra.clear();
            listaOrdeAutorizada.clear();
            listaDetalleOrden.clear();
            listaDetalleOrdenAutorizada.clear();
            listaOrdenCompra.addAll(OrdenDeCompraDao.getInstancia().getListOrdenDeCompraPendiente());
            listaOrdeAutorizada.addAll(OrdenDeCompraDao.getInstancia().getListOrdenDeCompraAutorizada());

            txtConcepto.clear();

            ClaseUtil.mensaje(" Orden autorizada correctamente");

        } catch (Exception e) {

            ClaseUtil.mensaje(" Hubo un problema autorizando la orden");
            e.printStackTrace();
        }

    }

    private String validar() {

        String msgError = "";

        if (tbOrdenCompraPendiente.getSelectionModel().getSelectedIndex() == -1) {
            msgError = "Tiene que Seleccionar una  Orden";
            return msgError;
        }

        return msgError;
    }

    @FXML
    private void btnRechazarActionEvent(ActionEvent event) {

        if (!validar().isEmpty()) {
            ClaseUtil.mensaje(validar());
            return;
        }

        Optional<ButtonType> op = ClaseUtil.confirmarMensaje("Seguaro que quiere rechazar esta solicitud");

        if (op.get() == ButtonType.OK) {

            if (!(tbOrdenCompraPendiente.getSelectionModel().getSelectedIndex() == -1)) {

                OrdenCompra sc = tbOrdenCompraPendiente.getSelectionModel().getSelectedItem();

            }

            listaDetalleOrdenAutorizada.clear();
            listaOrdenCompra.clear();

        }
    }

    @FXML
    private void rbAbiertaActionEvent(ActionEvent event) {

        try {

            listaOrdeAutorizada.clear();
            listaDetalleOrdenAutorizada.clear();
            listaOrdeAutorizada.addAll(OrdenDeCompraDao.getInstancia().getListOrdenDeCompraAutorizada("Abierta"));
            txtCantidadOrdenAutorizada.setText(cantidadOrden(true).toString());
            txtTotalOrdenAutorizada.setText(totalOrden(true).toString());
            txtCantidadArticulo1.setText(cantidadArticulo(true).toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void rbCerradaActionEvent(ActionEvent event) {

        try {

            listaOrdeAutorizada.clear();
            listaDetalleOrdenAutorizada.clear();

            listaOrdeAutorizada.addAll(OrdenDeCompraDao.getInstancia().getListOrdenDeCompraAutorizada("Cerrada"));
            txtCantidadOrdenAutorizada.setText(cantidadOrden(true).toString());
            txtTotalOrdenAutorizada.setText(totalOrden(true).toString());
            txtCantidadArticulo1.setText(cantidadArticulo(true).toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnVerDocumentoActionEvent(ActionEvent event) {

        try {

            if (!(tbOrdenCompraPendiente.getSelectionModel().getSelectedIndex() == -1)) {

                RptOrdenCompra.getInstancia().imprimir(tbOrdenCompraPendiente.getSelectionModel().getSelectedItem().getCodigo());

            } else {
                ClaseUtil.mensaje("Tiene que seleccionar una orden");

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void btnVerDocumento1ActionEvent(ActionEvent event) {

        try {

            if (!(tbOrdenCompraAutorizada.getSelectionModel().getSelectedIndex() == -1)) {

                RptOrdenCompra.getInstancia().imprimir(tbOrdenCompraAutorizada.getSelectionModel().getSelectedItem().getCodigo());
            } else {
                ClaseUtil.mensaje("Tiene que seleccionar una orden");

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private Double getTotalOrden() {

        Double total = 0.00;

        for (OrdenCompra orden : listaOrdenCompra) {

            total += orden.getTotal();
        }

        return total;
    }

    private Double getTotalDetalleOrden() {

        Double total = 0.00;

        for (DetalleOrdenCompra detalleOrdenCompra : listaDetalleOrden) {

            total += detalleOrdenCompra.getTotal();
        }

        return total;
    }

    private Double getTotalDetalleOrdenAutorizada() {

        Double total = 0.00;

        for (DetalleOrdenCompra detalleOrdenCompra : listaDetalleOrdenAutorizada) {

            total += detalleOrdenCompra.getTotal();
        }

        return total;
    }

    private Integer getCantidadOrden() {

        Integer cantidad = 0;

        cantidad = listaOrdenCompra.size();

        return cantidad;
    }

    private Integer getCantidadDetalleOrden() {

        Integer cantidad = 0;

        cantidad = listaDetalleOrden.size();

        return cantidad;
    }

    @FXML
    private void tbOrdenCompraPendienteMouseClicked(MouseEvent event) {

        try {

            if (tbOrdenCompraPendiente.getSelectionModel().getSelectedIndex() != -1) {

                OrdenCompra ordenCompra = tbOrdenCompraPendiente.getSelectionModel().getSelectedItem();

                listaDetalleOrden.clear();
                listaDetalleOrdenAutorizada.clear();

                listaDetalleOrden.addAll(OrdenDeCompraDao.getInstancia().getDetalleOrden(ordenCompra.getCodigo()));
                txtCantidadArticulo.setText(cantidadArticulo(false).toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tbOrdenCompraAutorizadaMouseClicked(MouseEvent event) {

        try {

            if (tbOrdenCompraAutorizada.getSelectionModel().getSelectedIndex() != -1) {

                OrdenCompra ordenCompra = tbOrdenCompraAutorizada.getSelectionModel().getSelectedItem();

                listaDetalleOrdenAutorizada.clear();
                listaDetalleOrden.clear();

                listaDetalleOrdenAutorizada.addAll(OrdenDeCompraDao.getInstancia().getDetalleOrden(ordenCompra.getCodigo()));

                txtCantidadArticulo1.setText(cantidadArticulo(true).toString());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Double totalOrden(boolean autorizada) {

        Double total = 0.00;

        if (autorizada) {

            for (OrdenCompra ordenCompra : listaOrdeAutorizada) {

                total += ordenCompra.getTotal();

            }

        } else {

            for (OrdenCompra ordenCompra : listaOrdenCompra) {

                total += ordenCompra.getTotal();

            }
        }

        return total;
    }

    private Integer cantidadOrden(boolean autorizada) {

        Integer cantidad = 0;

        if (autorizada) {
            cantidad = listaOrdeAutorizada.size();
        } else {
            cantidad = listaOrdenCompra.size();
        }

        return cantidad;
    }

    private Integer cantidadArticulo(boolean autorizada) {

        Integer cantidad = 0;

        if (autorizada) {
            cantidad = listaDetalleOrdenAutorizada.size();
        } else {
            cantidad = listaDetalleOrden.size();
        }

        return cantidad;
    }

}
