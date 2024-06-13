/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.nomina;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
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
import manejo.nomina.ManejoNomina;
import modelo.DetalleNomina;
import modelo.Nomina;
import reporte.nomina.RptNomina;
import reporte.nomina.RptVolanteDePago;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class AnularNominaController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private TabPane tabPane;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<Nomina> tbNomina;
    @FXML
    private TableColumn<Nomina, Integer> tbcNumeroNom;
    @FXML
    private TableColumn<Nomina, Nomina> tbcVerNomina;
    @FXML
    private TableColumn<Nomina, String> tbcFecha;
    @FXML
    private TableColumn<Nomina, String> tbcTipoNom;
    @FXML
    private TableColumn<Nomina, String> tbcEstado;
    @FXML
    private TableColumn<Nomina, Integer> tbcCantEmpleado;
    @FXML
    private TableColumn<Nomina, Double> tbcMonto;
    @FXML
    private TableColumn<Nomina, String> tbcPreparadoPor;
    @FXML
    private TableColumn<Nomina, String> tbcAnulada;
    @FXML
    private TableColumn<Nomina, String> tbcMes;
    @FXML
    private TableColumn<Nomina, Integer> tbcAnio;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TableView<DetalleNomina> tbDetalle;
    @FXML
    private TableColumn<DetalleNomina, String> tbcEmpleadoDet;
    @FXML
    private TableColumn<DetalleNomina, Double> tbcSueldoDet;
    @FXML
    private TableColumn<DetalleNomina, Double> tbcComisionDet;
    @FXML
    private TableColumn<DetalleNomina, Double> tbcHoraExtraDet;
    @FXML
    private TableColumn<DetalleNomina, Double> tbcTotalOtroBeneficioDet;
    @FXML
    private TableColumn<DetalleNomina, Double> tbcOtrasRemuneracionesDet;
    @FXML
    private TableColumn<DetalleNomina, Double> tbcTotalBruto;
    @FXML
    private TableColumn<DetalleNomina, Double> tbcAfpDet;
    @FXML
    private TableColumn<DetalleNomina, Double> tbcSFSDet;
    @FXML
    private TableColumn<DetalleNomina, Double> tbcDepeAdicionalesDet;
    @FXML
    private TableColumn<DetalleNomina, Double> tbcAvanceSueldoDet;
    @FXML
    private TableColumn<DetalleNomina, Double> tbcTotalDescDet;
    @FXML
    private TableColumn<DetalleNomina, Double> tbcSueldoNetoDet;
    @FXML
    private TableColumn<DetalleNomina, DetalleNomina> tbcRevisado;
    @FXML
    private JFXTextField txtCantidadEmpleado;
    @FXML
    private JFXTextField txtMontoNomina;
    Date ff, fi;

    ObservableList<Nomina> lista = FXCollections.observableArrayList();
    ObservableList<DetalleNomina> listaDetalle = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnAnular;
    @FXML
    private TextArea txtRazon;

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
        inicializarTablaDetalle();
        convertirColumnaSrtANum();
        editarTabla();

        inicializarTabla();
    }

    public void inicializarTablaDetalle() {

        try {

            listaDetalle.clear();

            tbcEmpleadoDet.setCellValueFactory(new PropertyValueFactory<>("empleado"));
            tbcSueldoDet.setCellValueFactory(new PropertyValueFactory<>("sueldoQuincenal"));
            tbcAvanceSueldoDet.setCellValueFactory(new PropertyValueFactory<>("avanceASueldo"));
            tbcHoraExtraDet.setCellValueFactory(new PropertyValueFactory<>("horasExtra"));
            tbcSFSDet.setCellValueFactory(new PropertyValueFactory<>("montoSfs"));
            tbcSueldoNetoDet.setCellValueFactory(new PropertyValueFactory<>("sueldoNeto"));
            tbcComisionDet.setCellValueFactory(new PropertyValueFactory<>("comision"));
            tbcOtrasRemuneracionesDet.setCellValueFactory(new PropertyValueFactory<>("otrasRemuneraciones"));
            tbcAfpDet.setCellValueFactory(new PropertyValueFactory<>("montoAfp"));
            tbcTotalOtroBeneficioDet.setCellValueFactory(new PropertyValueFactory<>("totalOtrasBeneficio"));

//              tbcFechaInicio.setCellValueFactory(
//                    cellData -> {
//                        SimpleStringProperty property = new SimpleStringProperty();
//                        if (cellData.getValue().getFechaInicio()!= null) {
//                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaInicio()));
//                        }
//                        return property;
//                    });
            tbcTotalDescDet.setCellValueFactory(new PropertyValueFactory<>("totalDescuento"));
            tbcDepeAdicionalesDet.setCellValueFactory(new PropertyValueFactory<>("montoDependienteAdicional"));
            tbcTotalBruto.setCellValueFactory(new PropertyValueFactory<>("totalBruto"));

            tbcEmpleadoDet.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getEmpleado() != null) {
                            property.setValue(cellData.getValue().getEmpleado().getNombre());
                        }
                        return property;
                    });

            tbDetalle.setItems(listaDetalle);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editarTabla() {

        tbcVerNomina.setCellFactory((TableColumn<Nomina, Nomina> param) -> {

            TableCell<Nomina, Nomina> cellsc = new TableCell<Nomina, Nomina>() {
                @Override
                public void updateItem(Nomina item, boolean empty) {
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

                            RptNomina.getInstancia().verReporte(item.getCodigo());

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

        tbcRevisado.setCellFactory((TableColumn<DetalleNomina, DetalleNomina> param) -> {

            TableCell<DetalleNomina, DetalleNomina> cellsc = new TableCell<DetalleNomina, DetalleNomina>() {
                @Override
                public void updateItem(DetalleNomina item, boolean empty) {
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

                            RptVolanteDePago.getInstancia().verReriboDePago(item.getNomina().getCodigo(), item.getEmpleado().getCodigo());

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

    private void convertirColumnaSrtANum() {

        tbcRevisado.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcVerNomina.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

    }

    public void inicializarTabla() {

        try {

            lista.addAll(ManejoNomina.getInstancia().getLista(fi, ff));

            tbcNumeroNom.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tbcCantEmpleado.setCellValueFactory(new PropertyValueFactory<>("cantidadEmpleado"));
            tbcAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
            tbcMes.setCellValueFactory(new PropertyValueFactory<>("mes"));
            tbcTipoNom.setCellValueFactory(new PropertyValueFactory<>("tipoNomina"));
            tbcMonto.setCellValueFactory(new PropertyValueFactory<>("totalNomina"));
            tbcPreparadoPor.setCellValueFactory(new PropertyValueFactory<>("preparadaPor"));
            tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

            tbcFecha.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaCorte() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaCorte()));
                        }
                        return property;
                    });

            tbcEstado.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getEstado() != null) {
                            property.setValue(cellData.getValue().getEstado().getNombre());
                        }
                        return property;
                    });

            tbcTipoNom.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getTipoNomina() != null) {
                            property.setValue(cellData.getValue().getTipoNomina().getNombre());
                        }
                        return property;
                    });

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

            tbcPreparadoPor.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getPreparadaPor() != null) {
                            property.setValue(cellData.getValue().getUsuario().getNombre());
                        }
                        return property;
                    });

            tbNomina.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private void tbDetalleContratoMouseClicked(MouseEvent event) {

    }

    private Double totalNomina() {

        double total = 0.00;
        String totalSrt = ClaseUtil.FormatearNumero(total);

        for (DetalleNomina det : listaDetalle) {

            total += det.getSueldoNeto();
        }

        return ClaseUtil.roundDouble(total, 2);
    }

    @FXML
    private void btnAnularActionEvent(ActionEvent event) {

        if (tbNomina.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar una nomina para anular ");
            return;
        }

        if (txtRazon.getText().isEmpty()) {

            ClaseUtil.mensaje("Tiene que digitar la razon de anulacion ");
            return;
        }

        Nomina nomina = tbNomina.getSelectionModel().getSelectedItem();

        Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("Seguro que quiere anular esta nomina ");

        if (ok.get() == ButtonType.YES) {

            nomina.setAnulada(true);
            nomina.setFechaAnulada(new Date());
            nomina.setObservacion(nomina.getObservacion());
            nomina.setAnuladaPor(VariablesGlobales.USUARIO.getCodigo());
            nomina.setRazonDeAnulacion(txtRazon.getText());
            ManejoNomina.getInstancia().actualizar(nomina);

            buscar();

        }

    }

    private void buscar() {

        lista.clear();
        listaDetalle.clear();

        fi = ClaseUtil.asDate(dpFechaInicio.getValue());
        ff = ClaseUtil.asDate(dpFechaFinal.getValue());

        lista.addAll(ManejoNomina.getInstancia().getLista(fi, ff));
    }

    @FXML
    private void tbNominaMouseClicked(MouseEvent event) {

        if (!(tbNomina.getSelectionModel().getSelectedIndex() == -1)) {

            if (tbNomina.getSelectionModel().getSelectedItem().getAnulada()) {
                btnAnular.setDisable(true);
            } else {
                btnAnular.setDisable(false);
            }
            
            listaDetalle.clear();

            listaDetalle.addAll(ManejoNomina
                    .getInstancia().getLista(tbNomina.getSelectionModel()
                            .getSelectedItem().getCodigo()));

            txtMontoNomina.setText(totalNomina().toString());
            if (event.getClickCount() == 2) {

                tabPane.getSelectionModel().select(1);
                txtMontoNomina.setText(totalNomina().toString());
                txtCantidadEmpleado.setText(Integer.toString(listaDetalle.size()));
            }

        }


    }

}
