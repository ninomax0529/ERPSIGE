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
import reporte.nomina.RptVolanteDePago;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class PagarNominaController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnNuevo;
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
    @FXML
    private JFXTextField txtNumeroNom;
    @FXML
    private JFXComboBox<TipoDeNomina> cbTipoNomina;
    @FXML
    private JFXComboBox<EstadoNomina> cbEstado;
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
    private JFXButton btnExportar;
    @FXML
    private JFXButton btnAutorizacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTablaDetalle();
        inicializarCombox();
//        dpFechaInicio.setValue(LocalDate.now());
//        dpFechaFinal.setValue(LocalDate.now());
//        nomina = new Nomina();
        int ung = VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
        SecuenciaDocumento sec = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(ung, 22);

        if (!(sec == null)) {
            txtNumeroNom.setText(Integer.toString(sec.getNumero()));
        }

        btnGuardar.setDisable(true);

    }

    private void inicializarCombox() {

        listaTipoNomina.addAll(ManejoTipoNomina.getInstancia().getLista());
        listaEstadoNom.addAll(ManejoEstadoNomina.getInstancia().getLista());

        cbEstado.setConverter(new StringConverter<EstadoNomina>() {

            @Override
            public String toString(EstadoNomina ejecutivo) {
                return ejecutivo.getNombre();
            }

            @Override
            public EstadoNomina fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoNomina.setConverter(new StringConverter<TipoDeNomina>() {

            @Override
            public String toString(TipoDeNomina tipoServicio) {
                return tipoServicio.getNombre();
            }

            @Override
            public TipoDeNomina fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbEstado.setItems(listaEstadoNom);
        cbTipoNomina.setItems(listaTipoNomina);
        cbEstado.getSelectionModel().select(3);

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
    private void btnNuevoActionEvent(ActionEvent event) {

        try {

            listaDetalle.clear();

            if (cbTipoNomina.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar un tipo de nomina ");
                cbTipoNomina.requestFocus();
                return;
            }
            if (cbEstado.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar un estado ");
                cbTipoNomina.requestFocus();
                return;
            }

            int tipoNom = cbTipoNomina.getSelectionModel().getSelectedItem().getCodigo();
            int estado = cbEstado.getSelectionModel().getSelectedItem().getCodigo();

            nomina = ManejoNomina.getInstancia().getNominasinPagar(estado, tipoNom);

            System.out.println("numero de nomina "+nomina.getCodigo());
            if (!(nomina == null)) {

                dpFechaFinal.setValue(ClaseUtil.convertToLocalDateViaMilisecond(nomina.getFechaCorte()));
                dpFechaInicio.setValue(ClaseUtil.convertToLocalDateViaMilisecond(nomina.getFechaInicio()));
                cbEstado.setValue(nomina.getEstado());
                cbTipoNomina.setValue(nomina.getTipoNomina());
                txtObservacion.setText(nomina.getObservacion());
                listaDetalle.addAll(ManejoNomina.getInstancia().getLista(nomina.getCodigo()));
                System.out.println("listaDetalle "+listaDetalle.size());

                txtMontoNomina.setText(totalNomina().toString());
                txtCantidadEmpleado.setText(cantidadEmpleado().toString());
                editar = true;

                btnGuardar.setDisable(false);

            } else {

                btnGuardar.setDisable(true);
                ClaseUtil.mensaje("No hay una nomina autorizada para pagar ");
            }

            txtCantidadEmpleado.setText(cantidadEmpleado().toString());

        } catch (Exception e) {

            ClaseUtil.mensaje("Hubo un problema pagando  la nomina ");
            e.printStackTrace();
        }

    }

    @FXML
    private void tbDetalleContratoMouseClicked(MouseEvent event) {

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        Optional<ButtonType> op = ClaseUtil.confirmarMensaje("Seguro que quiere pagar esta nomina");

        System.out.println("op.get() " + op.get());

        if (op.get() == ButtonType.YES) {

            try {

                nomina.setPagada(true);
                nomina.setPagadaPor(VariablesGlobales.USUARIO.getCodigo());
                nomina.setFechaPagada(new Date());
                nomina.setDetalleNominaCollection(listaDetalle);

                nomina.setObservacion(
                        txtObservacion.getText().isEmpty() ? "na"
                        : txtObservacion.getText()
                );

                ManejoNomina.getInstancia().actualizar(nomina);

                op = ClaseUtil.confirmarMensaje(" Quiere imprimir los recibo de pago ");

                System.out.println("op.get() " + op.get());

                if (op.get() == ButtonType.YES) {

                    for (DetalleNomina dep : listaDetalle) {

                        RptVolanteDePago.getInstancia()
                                .imprimir(dep.getNomina().getCodigo(), dep.getEmpleado().getCodigo());
                    }
                }

                listaDetalle.clear();
                txtObservacion.clear();
                txtMontoNomina.clear();
                txtNumeroNom.clear();
                txtCantidadEmpleado.clear();

                btnGuardar.setDisable(true);

            } catch (Exception e) {

                btnGuardar.setDisable(false);
                ClaseUtil.mensaje("Hubo un problema pagando la nomina ");
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

    @FXML
    private void dpFechaFinalActionEvent(ActionEvent event) {

        Date fechaPrimerDiaMes, fechaCorte;

        Long diasTranscurrido = 0l;
        String mes = "", strDia = "";

        if (!(dpFechaFinal.getValue() == null)) {

            fechaCorte = ClaseUtil.asDate(dpFechaFinal.getValue());
            fechaPrimerDiaMes = ClaseUtil.getFechaPrimerDiasDelMes(fechaCorte);
            diasTranscurrido = ClaseUtil.diferenciaDiasEntreDosFecha(fechaPrimerDiaMes, fechaCorte);

            mes = ClaseUtil.getNombreDelMes(fechaCorte);
            strDia = ClaseUtil.getNombreDia(fechaCorte);

            int anio = ClaseUtil.getAno(fechaCorte);

            if (diasTranscurrido.intValue() <= 15) {

                lbNumeroNomina.setText("1RA. QUINCENA " + anio);
                dpFechaInicio.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaPrimerDiaMes));

            } else if (diasTranscurrido.intValue() > 15) {

                fechaPrimerDiaMes = ClaseUtil.Fechadiadespues(fechaPrimerDiaMes, 15);
                dpFechaInicio.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaPrimerDiaMes));
                lbNumeroNomina.setText("2DA. QUINCENA " + anio);

            }

        } else {
            lbNumeroNomina.setText("");
        }
    }

    private void editarTabla() {

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

    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) {

        try {

            util.ClaseUtil.exportarDAtos(tbDetalle);

        } catch (IOException ex) {
            Logger.getLogger(PagarNominaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnARevisionActionEvent(ActionEvent event) {

        Optional<ButtonType> op = ClaseUtil.confirmarMensaje(" Seguro que quiere enviar  esta nomina para revision ");

        System.out.println("op.get() " + op.get());

        if (op.get() == ButtonType.YES) {

            try {

                for (DetalleNomina det : listaDetalle) {

                    det.setRevisado(false);
                }

                nomina.setAutorizadaPor(null);
                cbEstado.getSelectionModel().select(1);//estado 1 es  preparada
                nomina.setEstado(cbEstado.getSelectionModel().getSelectedItem());
                nomina.setFechaAutorizada(null);
                nomina.setDetalleNominaCollection(listaDetalle);
                nomina.setObservacion(txtObservacion.getText().isEmpty() ? "na"
                        : txtObservacion.getText()
                );

                ManejoNomina.getInstancia().actualizar(nomina);

                listaDetalle.clear();
                txtObservacion.clear();
                txtMontoNomina.clear();
                txtNumeroNom.clear();
                txtCantidadEmpleado.clear();
                ClaseUtil.mensaje(" Nomina enviada a revision ");

            } catch (Exception e) {
                ClaseUtil.mensaje("Hubo un problema enviando la nomina a revision  ");
                e.printStackTrace();
            }

        }

    }

}
