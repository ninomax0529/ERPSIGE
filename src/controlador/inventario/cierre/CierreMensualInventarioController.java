/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.cierre;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import manejo.inventario.cierre.ManejoCierreMensualInventario;
import manejo.inventario.entrada.ManejoEntradaInventario;
import modelo.CierreMensualInventario;
import modelo.DetalleAjusteInventario;
import modelo.DetalleCierreMensualInventario;
import modelo.DetalleEntradaInventario;
import modelo.EntradaInventario;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class CierreMensualInventarioController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<CierreMensualInventario> tbCierreMensualInventario;
    @FXML
    private TableColumn<CierreMensualInventario, Integer> tbcCierreMensual;
    @FXML
    private TableColumn<CierreMensualInventario, Date> tbcFecha;
    @FXML
    private TableColumn<CierreMensualInventario, Date> tbcFechaRegistro;
    @FXML
    private TableColumn<CierreMensualInventario, String> tbcAnulado;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TableView<DetalleCierreMensualInventario> tbDetalleCierreMensualInventario;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, Double> tbcInventarioInicial;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, Double> tbcEntrada;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, Double> tbcSalida;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, Double> tbcInventarioFinal;
    @FXML
    private TableColumn<DetalleCierreMensualInventario, String> tbcUnidadSalida;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidadArticulo;
    @FXML
    private JFXTextField txtValorTotal;

    ObservableList<CierreMensualInventario> listaCierre = FXCollections.observableArrayList();
    ObservableList<DetalleCierreMensualInventario> listadetalle = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> tbcCostoUnitario;
    @FXML
    private TableColumn<?, ?> tbcValorInventario;
    @FXML
    private JFXButton btnExportar;

    Date fechaini, fechafin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaCierre();
        iniciarTablaDetalle();
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
        fechaini = ClaseUtil.asDate(dpFechaInicio.getValue());
        fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());

    }

    private void iniciarTablaCierre() {

        // listaEntrada.clear();
        listaCierre.addAll(ManejoCierreMensualInventario.getInstancia().getCierreMensualInventario());
        tbCierreMensualInventario.setItems(listaCierre);
        txtCantidad.setText(Integer.toString(listaCierre.size()));

        tbcCierreMensual.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        tbcAnulado.setCellValueFactory(new PropertyValueFactory<>("anulado"));

        tbcAnulado.setCellValueFactory(
                cellData -> {

                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {

                        if (cellData.getValue().getAnulado()) {
                            property.setValue("Si");
                        } else {
                            property.setValue("No");
                        }

                    }
                    return property;
                });

    }

    public void iniciarTablaDetalle() {

        listadetalle.clear();

        tbDetalleCierreMensualInventario.setItems(listadetalle);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcionArticulo"));
        tbcUnidadSalida.setCellValueFactory(new PropertyValueFactory<>("unidadSalida"));
        tbcInventarioInicial.setCellValueFactory(new PropertyValueFactory<>("inventarioInicial"));
        tbcInventarioFinal.setCellValueFactory(new PropertyValueFactory<>("inventarioFinal"));
        tbcSalida.setCellValueFactory(new PropertyValueFactory<>("cantidadSalida"));
        tbcEntrada.setCellValueFactory(new PropertyValueFactory<>("cantidadEntrada"));
        tbcCostoUnitario.setCellValueFactory(new PropertyValueFactory<>("costoUnitario"));
        tbcValorInventario.setCellValueFactory(new PropertyValueFactory<>("costo"));

        tbcCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });

        tbcUnidadSalida.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getUnidadSalida().getDescripcion());
                    }
                    return property;
                });

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/cierre/RegistroCierreMensualInventario.fxml"));

        ClaseUtil.crearStageModal(root);

        listaCierre.clear();
        listaCierre.addAll(ManejoCierreMensualInventario.getInstancia().getCierreMensualInventario());

    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        try {

            fechaini = ClaseUtil.asDate(dpFechaInicio.getValue());
            fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());

            listaCierre.clear();
            listadetalle.clear();
            listaCierre.addAll(ManejoCierreMensualInventario.getInstancia().getCierreMensualInventario(fechaini, fechafin));
            txtCantidadArticulo.clear();
            txtValorTotal.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {

        try {

            if (!(tbCierreMensualInventario.getSelectionModel().getSelectedIndex() == -1)) {

                CierreMensualInventario cierre = tbCierreMensualInventario.getSelectionModel().getSelectedItem();
                Optional<ButtonType> ok = util.ClaseUtil.confirmarMensaje(" Seguro que quiere anular el  cierre numero ->  " + cierre.getCodigo());

                if (ok.get() == ButtonType.OK) {

                    cierre.setAnulado(true);
                    cierre.setAnuladoPor(VariablesGlobales.USUARIO.getNombre());
                    cierre.setFechaAnulado(new Date());
                    ManejoCierreMensualInventario.getInstancia().actualizar(cierre);
                    ClaseUtil.mensaje(" El proceso se ejcuto exitosamente ");
                    listadetalle.clear();
                    listaCierre.setAll(ManejoCierreMensualInventario.getInstancia().getCierreMensualInventario(fechaini, fechafin));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            ClaseUtil.mensaje("Hubo un error  anulando el cierre ");
        }

    }

    @FXML
    private void tbEntradaInventarioMouseClicked(MouseEvent event) {

        if (!(tbCierreMensualInventario.getSelectionModel().getSelectedIndex() == -1)) {

            CierreMensualInventario cierre = tbCierreMensualInventario.getSelectionModel().getSelectedItem();

            if (cierre.getAnulado()) {

                btnAnular.setDisable(true);
                
            } else {

                btnAnular.setDisable(false);
            }

            listadetalle.clear();
            listadetalle.addAll(ManejoCierreMensualInventario.getInstancia().getDetalleInventario(cierre));
            txtCantidadArticulo.setText(Integer.toString(listadetalle.size()));
            txtValorTotal.setText(costoInventario().toString());
            txtComentario.setText(cierre.getComentario());
        }
    }

    @FXML
    private void tbDetalleEntradaInventarioMouseCliked(MouseEvent event) {

    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) {

        try {

            util.ClaseUtil.exportarDAtos(tbDetalleCierreMensualInventario);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Double costoInventario() {

        Double costo = 0.00;
        for (DetalleCierreMensualInventario det : listadetalle) {

            costo += det.getCosto();
        }
        return util.ClaseUtil.roundDouble(costo, 2);
    }

}
