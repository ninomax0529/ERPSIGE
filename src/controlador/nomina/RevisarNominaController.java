/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.nomina;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
import javafx.util.StringConverter;
import manejo.nomina.ManejoDeducionesTss;
import manejo.nomina.ManejoEstadoNomina;
import manejo.nomina.ManejoNomina;
import manejo.nomina.ManejoTipoNomina;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.DeducionesTss;
import modelo.DetalleNomina;
import modelo.EstadoNomina;
import modelo.Nomina;
import modelo.SecuenciaDocumento;
import modelo.TipoDeNomina;
import reporte.nomina.RptNomina;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RevisarNominaController implements Initializable {

    @FXML
    private TabPane tabPane;
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

    ObservableList<DetalleNomina> listaDetalle = FXCollections.observableArrayList();
    ObservableList<TipoDeNomina> listaTipoNomina = FXCollections.observableArrayList();
    ObservableList<EstadoNomina> listaEstadoNom = FXCollections.observableArrayList();
    ObservableList<Nomina> listaNomina = FXCollections.observableArrayList();

    @FXML
    private JFXButton btnGuardar;

    Nomina nomina;
    DetalleNomina det;
    @FXML
    private TextArea txtObservacion;
    @FXML
    private Label lbNumeroNomina;
    boolean editar;
    @FXML
    private JFXButton btnAPreparacion;
    @FXML
    private Label lbProcesado;
    @FXML
    private Label lbCantEmpleado;
    @FXML
    private JFXButton btnExportar;
    @FXML
    private TableView<Nomina> tbNomina;
    @FXML
    private TableColumn<Nomina, Integer> tbcNumero;
    @FXML
    private TableColumn<Nomina, String> tbcFecha;
    @FXML
    private TableColumn<Nomina, String> tbcTipoNom;
    @FXML
    private TableColumn<Nomina, String> tbcEstado;
    @FXML
    private TableColumn<Nomina, String> tbcMes;
    @FXML
    private TableColumn<Nomina, String> tbcAnio;
    @FXML
    private TableColumn<Nomina, Nomina> tbcVerNomina;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTabla();
        inicializarTablaDetalle();

        btnAPreparacion.setDisable(true);
        btnGuardar.setDisable(true);

    }

    public void inicializarTabla() {

        try {

            listaNomina.addAll(ManejoNomina.getInstancia().getNominasinPagar(2));
            tbcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tbcAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
            tbcMes.setCellValueFactory(new PropertyValueFactory<>("mes"));
            tbcTipoNom.setCellValueFactory(new PropertyValueFactory<>("tipoNomina"));
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

            tbNomina.setItems(listaNomina);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
            tbDetalle.setEditable(true);

            convertirColumnaSrtANum();

            editarTabla();

            cantidadEmpleado();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAPreparacionActionEvent(ActionEvent event) {

        Optional<ButtonType> op = ClaseUtil.confirmarMensaje("Seguro que quiere enviar esta nomina a preparacion ");

        System.out.println("op.get() " + op.get());

        if (op.get() == ButtonType.YES) {

            try {

                for (DetalleNomina det : listaDetalle) {

                    det.setRevisado(false);
                }
//
                nomina.setRevisadaPor(null);
               
                nomina.setEstado(ManejoEstadoNomina.getInstancia().getEstadoNomina(1));
                nomina.setFechaRevisada(null);
                nomina.setDetalleNominaCollection(listaDetalle);
                nomina.setObservacion(txtObservacion.getText().isEmpty() ? "na"
                        : txtObservacion.getText()
                );

                ManejoNomina.getInstancia().actualizar(nomina);

                listaDetalle.clear();
                txtObservacion.clear();
                txtMontoNomina.clear();
                txtCantidadEmpleado.clear();
                btnGuardar.setDisable(true);
                btnAPreparacion.setDisable(true);
                ClaseUtil.mensaje(" Nomina enviada a preparacion ");

            } catch (Exception e) {
                btnGuardar.setDisable(true);
                btnAPreparacion.setDisable(true);
                ClaseUtil.mensaje("Hubo un problema guardando la nomina ");
                e.printStackTrace();
            }

        }else{
             ClaseUtil.mensaje(" NO SE PUDO ENVIAR A PREPARACION ");
        }

    }

    @FXML
    private void tbDetalleContratoMouseClicked(MouseEvent event) {

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        Optional<ButtonType> op = ClaseUtil.confirmarMensaje("Seguro que quiere guardar esta nomina como revisada ");

        System.out.println("op.get() " + op.get());

        if (op.get() == ButtonType.YES) {

            try {

                nomina.setRevisadaPor(VariablesGlobales.USUARIO.getCodigo());
//                cbEstado.getSelectionModel().select(ManejoEstadoNomina.getInstancia().getEstadoNomina(3));//estado 3 es  revisada
                nomina.setEstado(ManejoEstadoNomina.getInstancia().getEstadoNomina(3));
                nomina.setFechaRevisada(new Date());
                nomina.setDetalleNominaCollection(listaDetalle);
                nomina.setObservacion(txtObservacion.getText().isEmpty() ? "na"
                        : txtObservacion.getText()
                );

                ManejoNomina.getInstancia().actualizar(nomina);

                listaDetalle.clear();
                txtObservacion.clear();
                txtMontoNomina.clear();
                txtCantidadEmpleado.clear();
                ClaseUtil.mensaje(" Nomina revisada exitosamente ");

            } catch (Exception e) {
                ClaseUtil.mensaje("Hubo un problema actualizar la nomina ");
                e.printStackTrace();
            }

        }
    }

    private Double totalNomina() {

        double total = 0.00;
        String totalSrt = ClaseUtil.FormatearNumero(total);

        for (DetalleNomina det : listaDetalle) {

            total += det.getSueldoNeto();
        }

        return ClaseUtil.roundDouble(total, 2);
    }

    private double totalBruto(DetalleNomina det) {

        double totalBruto = 0.00;

        totalBruto = det.getSueldoQuincenal() + det.getHorasExtra()
                + det.getOtrasRemuneraciones() + det.getTotalOtrasBeneficio();

        return ClaseUtil.roundDouble(totalBruto, 2);
    }

    private double totalDescuento(DetalleNomina det) {

        double totalDescuento = 0.00;

        totalDescuento = det.getAvanceASueldo() + det.getIr3() + det.getMontoAfp()
                + det.getMontoSfs() + det.getMontoDependienteAdicional();

        return ClaseUtil.roundDouble(totalDescuento, 2);
    }

    private double totalAFP(DetalleNomina det) {

        double totalAfp = 0.00, afpPorciento = 0.00;

        DeducionesTss dtss = ManejoDeducionesTss.getInstancia().getDeducionesTss(1);

        afpPorciento = dtss.getAfp() / 100;

        totalAfp = (det.getSueldoQuincenal() + det.getComision()) * afpPorciento;

        return ClaseUtil.roundDouble(totalAfp, 2);
    }

    private double totalSFS(DetalleNomina det) {

        double totalSFS = 0.00, sfsPorciento = 0.00;

        DeducionesTss dtss = ManejoDeducionesTss.getInstancia().getDeducionesTss(1);

        sfsPorciento = dtss.getSfs() / 100;

        totalSFS = (det.getSueldoQuincenal() + det.getComision()) * sfsPorciento;

        return ClaseUtil.roundDouble(totalSFS, 2);
    }

    private Double sueldoNeto(DetalleNomina det) {

        double sueldoNeto = 0.00;

        sueldoNeto = totalBruto(det) - totalDescuento(det);

        return ClaseUtil.roundDouble(sueldoNeto, 2);
    }

    private Integer cantidadEmpleado() {

        return listaDetalle.size();
    }

    private void editarTabla() {

        tbcRevisado.setCellFactory((TableColumn<DetalleNomina, DetalleNomina> param) -> {

            TableCell<DetalleNomina, DetalleNomina> cellsc = new TableCell<DetalleNomina, DetalleNomina>() {
                @Override
                public void updateItem(DetalleNomina item, boolean empty) {
                    super.updateItem(item, empty);

                    Button btnHabilitar;

                    if (item != null) {

                        btnHabilitar = new Button();
                        btnHabilitar.setPrefSize(50.0, 20.0);

                        if (item.getRevisado()) {

                            btnHabilitar.setText("SI");

                            btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 10px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 12pt;");

                            actualizarRevisado();

                        } else {

                            btnHabilitar.setText("NO");
                            btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 10px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 12pt;");
                            actualizarRevisado();

                        }

                        HBox hbox = new HBox();

//                        hbox.getChildren().addAll(imageview);
                        hbox.getChildren().add(btnHabilitar);

                        hbox.setAlignment(Pos.CENTER);

                        btnHabilitar.setOnMouseClicked((event) -> {

                            if (item.getRevisado()) {

                                item.setRevisado(false);
                                btnHabilitar.setText("NO");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 15px;\n"
                                        + "    -fx-b-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                                actualizarRevisado();

                            } else {

                                item.setRevisado(true);

                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                                actualizarRevisado();

                            }

                        });

                        setGraphic(btnHabilitar);
                        setText(null);
                        tbDetalle.refresh();
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

    private void actualizarRevisado() {

        int catidadProcesada = 0;
        for (DetalleNomina det : listaDetalle) {

            if (det.getRevisado()) {
                catidadProcesada++;
            }

        }

        lbProcesado.setText(Integer.toString(catidadProcesada));

        if (catidadProcesada == listaDetalle.size()) {

            btnGuardar.setDisable(false);
        } else {
            btnGuardar.setDisable(true);
        }

    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) {

        try {

            util.ClaseUtil.exportarDAtos(tbDetalle);

        } catch (IOException ex) {
            Logger.getLogger(RevisarNominaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tbContratoDeServicioClicked(MouseEvent event) {

        if (!(tbNomina.getSelectionModel().getSelectedIndex() == -1)) {

            nomina = tbNomina.getSelectionModel().getSelectedItem();
            Date fechaPrimerDiaMes, fechaCorte;

            Long diasTranscurrido = 0l;
            String mes = "", strDia = "";

            fechaCorte = nomina.getFechaCorte();
            fechaPrimerDiaMes = ClaseUtil.getFechaPrimerDiasDelMes(fechaCorte);
            diasTranscurrido = ClaseUtil.diferenciaDiasEntreDosFecha(fechaPrimerDiaMes, fechaCorte);

            mes = ClaseUtil.getNombreDelMes(fechaCorte);
            strDia = ClaseUtil.getNombreDia(fechaCorte);

            int anio = ClaseUtil.getAno(fechaCorte);

            if (diasTranscurrido.intValue() <= 15) {

                lbNumeroNomina.setText("1RA. QUINCENA " + anio);

            } else if (diasTranscurrido.intValue() > 15) {

                fechaPrimerDiaMes = ClaseUtil.Fechadiadespues(fechaPrimerDiaMes, 15);

                lbNumeroNomina.setText("2DA. QUINCENA " + anio);

            }

            btnAPreparacion.setDisable(false);

            listaDetalle.clear();
            txtObservacion.setText(nomina.getObservacion());
            listaDetalle.addAll(ManejoNomina.getInstancia().getLista(nomina.getCodigo()));
            txtMontoNomina.setText(totalNomina().toString());
            txtCantidadEmpleado.setText(cantidadEmpleado().toString());
            editar = true;
            txtCantidadEmpleado.setText(cantidadEmpleado().toString());
            lbCantEmpleado.setText(Integer.toString(listaDetalle.size()));

            lbProcesado.setText("0");

        } else {

            btnAPreparacion.setDisable(true);
            lbCantEmpleado.setText("");
            lbProcesado.setText("");
            lbNumeroNomina.setText("");

        }
    }

}
