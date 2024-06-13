/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.nomina;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.nomina.ManejoRegistroHoraExtra;
import modelo.DetalleRegistroHoraExtra;
import modelo.ReciboIngreso;
import modelo.RegistroHoraExtra;
import reporte.cxc.RptReciboIngreso;
import reporte.nomina.RpTHorasExtras;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class HoraExtraController implements Initializable {

    @FXML
    private TableColumn<DetalleRegistroHoraExtra, DetalleRegistroHoraExtra> tbcRevisado;

    /**
     * @return the rgHoraExtra
     */
    public RegistroHoraExtra getRgHoraExtra() {
        return rgHoraExtra;
    }

    /**
     * @param rgHoraExtra the rgHoraExtra to set
     */
    public void setRgHoraExtra(RegistroHoraExtra rgHoraExtra) {
        this.rgHoraExtra = rgHoraExtra;
    }

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private TabPane tabPane;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<RegistroHoraExtra> tbHoraExtra;
    @FXML
    private TableColumn<RegistroHoraExtra, String> tbcNumeroNom;
    @FXML
    private TableColumn<RegistroHoraExtra, String> tbcFechaInicio;
    @FXML
    private TableColumn<RegistroHoraExtra, String> tbcFechaFinal;
    @FXML
    private TableColumn<RegistroHoraExtra, Double> tbcMonto;
    @FXML
    private TableColumn<RegistroHoraExtra, String> tbcAnulada;
    @FXML
    private TableColumn<RegistroHoraExtra, RegistroHoraExtra> tbcVerDocumento;

    @FXML
    private TextArea txtObservacion;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TableView<DetalleRegistroHoraExtra> tbDetalle;
    @FXML
    private TableColumn<DetalleRegistroHoraExtra, String> tbcEmpleadoDet;
    @FXML
    private TableColumn<DetalleRegistroHoraExtra, Double> tbcHoraExtraDet;
    @FXML
    private TableColumn<DetalleRegistroHoraExtra, Double> tbcSueldoPorHora;
    @FXML
    private TableColumn<DetalleRegistroHoraExtra, Double> tbcTotalDet;
    @FXML
    private TableColumn<DetalleRegistroHoraExtra, String> tbcConceptoDet;
    @FXML
    private JFXTextField txtCantidadEmpleado;
    @FXML
    private JFXTextField txtMontoNomina;

    ObservableList<DetalleRegistroHoraExtra> listaDetalle = FXCollections.observableArrayList();
    ObservableList<RegistroHoraExtra> lista = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnEditar;

    private RegistroHoraExtra rgHoraExtra;
    Date ff, fi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Date fechaPrimerDiaMes = ClaseUtil.getFechaPrimerDiasDelMes(new Date());
        dpFechaInicio.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaPrimerDiaMes));
        dpFechaFinal.setValue(LocalDate.now());

        fi = ClaseUtil.asDate(dpFechaInicio.getValue());
        ff = ClaseUtil.asDate(dpFechaFinal.getValue());

        inicializarTabla();
        inicializarTablaDetalle();
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        try {
            buscar();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void inicializarTabla() {

        try {

            listaDetalle.clear();
            lista.addAll(ManejoRegistroHoraExtra.getInstancia().getLista(fi, ff));

            tbcNumeroNom.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            tbcFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
            tbcFechaFinal.setCellValueFactory(new PropertyValueFactory<>("fechaFinal"));
            tbcMonto.setCellValueFactory(new PropertyValueFactory<>("total"));

            tbcAnulada.setCellValueFactory(
                    cellData -> {

                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue() != null) {

                            if (cellData.getValue().getAnulada()) {
                                property.setValue("Si");
                            } else {
                                property.setValue("No");
                            }

                        }
                        return property;
                    });

            tbcFechaInicio.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaInicio() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaInicio()));
                        }
                        return property;
                    });

            tbcFechaFinal.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaFinal() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaFinal()));
                        }
                        return property;
                    });

            tbcVerDocumento.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcVerDocumento.setCellFactory((TableColumn<RegistroHoraExtra, RegistroHoraExtra> param) -> {

                TableCell<RegistroHoraExtra, RegistroHoraExtra> cellsc = new TableCell<RegistroHoraExtra, RegistroHoraExtra>() {
                    @Override
                    public void updateItem(RegistroHoraExtra item, boolean empty) {
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

                                RpTHorasExtras.getInstancia().verReporte(item.getCodigo(), " order by e.nombre ");
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

            tbHoraExtra.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTablaDetalle() {

        try {

            tbcEmpleadoDet.setCellValueFactory(new PropertyValueFactory<>("empleado"));
            tbcConceptoDet.setCellValueFactory(new PropertyValueFactory<>("concepto"));
            tbcSueldoPorHora.setCellValueFactory(new PropertyValueFactory<>("sueldoPorHora"));
            tbcMonto.setCellValueFactory(new PropertyValueFactory<>("total"));
            tbcHoraExtraDet.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

            tbcEmpleadoDet.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getEmpleado() != null) {
                            property.setValue(cellData.getValue().getEmpleado().getNombre());
                        }
                        return property;
                    });

            tbcRevisado.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcRevisado.setCellFactory((TableColumn<DetalleRegistroHoraExtra, DetalleRegistroHoraExtra> param) -> {

                TableCell<DetalleRegistroHoraExtra, DetalleRegistroHoraExtra> cellsc = new TableCell<DetalleRegistroHoraExtra, DetalleRegistroHoraExtra>() {
                    @Override
                    public void updateItem(DetalleRegistroHoraExtra item, boolean empty) {
                        super.updateItem(item, empty);

                        ImageView imageview;

                        if (item != null) {

                            imageview = new ImageView(new Image(getClass().getResource("/imagen/img_documento.jpg").toString(), 40, 20, false, false));
                            imageview.setFitWidth(40);
                            imageview.setFitHeight(20);

                            HBox hbox = new HBox();

                            hbox.getChildren().add(imageview);

                            hbox.setAlignment(Pos.CENTER);

                            //Evento de la fila 
                            hbox.setOnMouseClicked((event) -> {

                                String sqlParam = " and  drh.empleado=" + item.getEmpleado().getCodigo();
                                System.out.println("sql param " + sqlParam);
                                RpTHorasExtras.getInstancia().verReporte(item.getRegistroHoraExtra().getCodigo(), item.getEmpleado().getCodigo());

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

            tbDetalle.setItems(listaDetalle);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/nomina/RegistroHoraExtra.fxml"));

            ClaseUtil.crearStageModal(root);
            buscar();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {

        if (tbHoraExtra.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje(" Tiene que seleccionar un registro ");
            return;
        }

        Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("Seguro que quiere anular este regsitro ? ");

        if (ok.get() == ButtonType.OK || ok.get() == ButtonType.YES) {

            try {

                RegistroHoraExtra reg = tbHoraExtra.getSelectionModel().getSelectedItem();

                reg.setAnulada(true);
                ManejoRegistroHoraExtra.getInstancia().actualizar(reg);
                tbHoraExtra.refresh();
                ClaseUtil.mensaje("Registro anulado exitosamente  ");

            } catch (Exception e) {
                e.printStackTrace();
                ClaseUtil.mensaje("Hubo un error anulando el registro " + e.getMessage());

            }

        }

    }

    private void btnVerDocumentoActionEvent(ActionEvent event) {

        if (!(tbHoraExtra.getSelectionModel().getSelectedIndex() == -1)) {

            RegistroHoraExtra reg = tbHoraExtra.getSelectionModel().getSelectedItem();
            RpTHorasExtras.getInstancia().verReporte(reg.getCodigo(), " order by e.nombre ");

        }
    }

    @FXML
    private void tbContratoDeServicioClicked(MouseEvent event) {

        if (!(tbHoraExtra.getSelectionModel().getSelectedIndex() == -1)) {

            listaDetalle.clear();

            RegistroHoraExtra reg = tbHoraExtra.getSelectionModel().getSelectedItem();

            listaDetalle.addAll(ManejoRegistroHoraExtra
                    .getInstancia().getLista(reg.getCodigo()));

            txtCantidadEmpleado.setText(cantidadHora().toString());
            txtMontoNomina.setText(totalHora().toString());
            if (event.getClickCount() == 2) {

                tabPane.getSelectionModel().select(1);
            }
        }
    }

    @FXML
    private void tbDetalleContratoMouseClicked(MouseEvent event) {

    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) {

        try {

            if (tbHoraExtra.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN REGISTRO");

            } else {

                setRgHoraExtra(tbHoraExtra.getSelectionModel().getSelectedItem());

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/vista/nomina/RegistroHoraExtra.fxml"));
                loader.load();

                Parent root = loader.getRoot();

                RegistroHoraExtraController rgController = loader.getController();

                rgController.setEditar(true);
                rgController.setRgHoraExtra(getRgHoraExtra());
                rgController.llenarCampo();

                ClaseUtil.getStageModalcONTRATO(root);

                buscar();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Double totalHora() {

        double total = 0.00;

        for (DetalleRegistroHoraExtra det : listaDetalle) {

            total += det.getTotal();
        }

        return total;
    }

    private Double cantidadHora() {

        double total = 0.00;

        for (DetalleRegistroHoraExtra det : listaDetalle) {

            total += det.getCantidad();
        }

        return total;
    }

    private void buscar() {

        lista.clear();
        listaDetalle.clear();

        fi = ClaseUtil.asDate(dpFechaInicio.getValue());
        ff = ClaseUtil.asDate(dpFechaFinal.getValue());

        lista.addAll(ManejoRegistroHoraExtra.getInstancia().getLista(fi, ff));
    }

}
