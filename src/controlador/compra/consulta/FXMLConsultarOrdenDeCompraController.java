/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.compra.consulta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import manejo.ordenCompra.OrdenDeCompraDao;
import modelo.DetalleOrdenCompra;
import modelo.OrdenCompra;
import reporte.compra.RptOrdenCompra;
import reporte.compra.RptOrdenCompraPinturaTriplea;

import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class FXMLConsultarOrdenDeCompraController implements Initializable {

    @FXML
    private JFXTextField txtConcepto;
    @FXML
    private JFXRadioButton rbAbierta;
    @FXML
    private JFXRadioButton rbCerrada;
    final ToggleGroup group = new ToggleGroup();
    @FXML
    private JFXButton btnVerDocumento1;
    @FXML
    private TableView<OrdenCompra> tbOrdenCompraAutorizada;
    @FXML
    private TableView<DetalleOrdenCompra> tbDetalleOrdenAutorizada;

    @FXML
    private JFXTextField txtCantidadArticulo1;

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
    private TableColumn<OrdenCompra, Double> tbcOrdenAutorizadaItbis;
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
    private TableColumn<DetalleOrdenCompra, String> tbcDetalleOrdenAutorizadaUnidad;
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
    private JFXTextField txtCantidadOrdenAutorizada;
    @FXML
    private JFXTextField txtTotalOrdenAutorizada;
    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private Tab tabDetalleOrden;
    @FXML
    private TabPane tabPaneOrden;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaOrdenCompraAutorizada();

        iniciarTablaDetalleOrdenAutorizada();

        rbAbierta.setToggleGroup(group);
        rbCerrada.setToggleGroup(group);
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

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

        tbcDetalleOrdenAutorizadaDescripcionArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getDescripcion());
                    }
                    return property;
                });

        tbcDetalleOrdenAutorizadaUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getArticulo() != null) {
                        property.setValue(cellData.getValue().getArticulo().getUnidadEntrada().getDescripcion());
                    }
                    return property;
                });

    }

    public void iniciarTablaOrdenCompraAutorizada() {

        tbOrdenCompraAutorizada.setItems(listaOrdeAutorizada);

        tbcOrdenAutorizadaNoOrden.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcOrdenAutorizadaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcOrdenAutorizadaSubTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        tbcOrdenAutorizadaTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcOrdenAutorizadaDescuento.setCellValueFactory(new PropertyValueFactory<>("totalDescuento"));
        tbcOrdenAutorizadaItbis.setCellValueFactory(new PropertyValueFactory<>("totalItbis"));

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
    private void btnVerDocumento1ActionEvent(ActionEvent event) {

        try {

            if (!(tbOrdenCompraAutorizada.getSelectionModel().getSelectedIndex() == -1)) {

                if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {

                    RptOrdenCompraPinturaTriplea.getInstancia().imprimir(tbOrdenCompraAutorizada.getSelectionModel().getSelectedItem().getCodigo());

                } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {

                    RptOrdenCompra.getInstancia().imprimir(tbOrdenCompraAutorizada.getSelectionModel().getSelectedItem().getCodigo());

                }

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
    private void tbOrdenCompraAutorizadaMouseClicked(MouseEvent event) {

        try {

            if (tbOrdenCompraAutorizada.getSelectionModel().getSelectedIndex() != -1) {

                OrdenCompra ordenCompra = tbOrdenCompraAutorizada.getSelectionModel().getSelectedItem();

                listaDetalleOrdenAutorizada.clear();
                listaDetalleOrden.clear();

                listaDetalleOrdenAutorizada.addAll(OrdenDeCompraDao.getInstancia().getDetalleOrden(ordenCompra.getCodigo()));

                txtCantidadArticulo1.setText(cantidadArticulo(true).toString());
                txtConcepto.setText(ordenCompra.getComentario());

                if (event.getClickCount() == 2) {
                    tabPaneOrden.getSelectionModel().select(1);
                }

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

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        listaOrdeAutorizada.clear();
        Date fechaini = ClaseUtil.asDate(dpFechaInicio.getValue()), fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());
        listaOrdeAutorizada.addAll(OrdenDeCompraDao.getInstancia().getListaOrdenCompra(fechaini, fechafin));
    }

    @FXML
    private void btnNuevoActionevent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/compra/proceso/FXMLOrdenDeCompra.fxml"));

        utiles.ClaseUtil.crearStageModal(root);

        listaOrdeAutorizada.clear();
        listaOrdeAutorizada.addAll(OrdenDeCompraDao.getInstancia().getListOrdenDeCompraAutorizada());
    }

}
