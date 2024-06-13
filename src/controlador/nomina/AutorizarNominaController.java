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
import java.time.LocalDate;
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
import javafx.scene.control.cell.TextFieldTableCell;
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
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class AutorizarNominaController implements Initializable {

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
    private JFXButton btnARevision;
    @FXML
    private JFXButton btnExportar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablaDetalle();
        inicializarCombox();
        int ung = VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        SecuenciaDocumento sec = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(ung, 22);

        if (!(sec == null)) {
            txtNumeroNom.setText(Integer.toString(sec.getNumero()));
        }

        btnARevision.setDisable(true);
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
        cbEstado.getSelectionModel().select(2);

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
//            tbDetalle.setEditable(true);

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

            nomina = ManejoNomina.getInstancia().getNominaEnProceso(estado, tipoNom);

            if (!(nomina == null)) {

                dpFechaFinal.setValue(ClaseUtil.convertToLocalDateViaMilisecond(nomina.getFechaCorte()));
                dpFechaInicio.setValue(ClaseUtil.convertToLocalDateViaMilisecond(nomina.getFechaInicio()));
                cbEstado.setValue(nomina.getEstado());
                cbTipoNomina.setValue(nomina.getTipoNomina());
                txtObservacion.setText(nomina.getObservacion());
                listaDetalle.addAll(ManejoNomina.getInstancia().getLista(nomina.getCodigo()));
                txtMontoNomina.setText(totalNomina().toString());
                txtCantidadEmpleado.setText(cantidadEmpleado().toString());
                editar = true;

                btnARevision.setDisable(false);
                btnGuardar.setDisable(false);

            } else {

                btnARevision.setDisable(true);
                btnGuardar.setDisable(true);
                ClaseUtil.mensaje("No hay una nomina revisada para autorizar ");
            }

            txtCantidadEmpleado.setText(cantidadEmpleado().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void tbDetalleContratoMouseClicked(MouseEvent event) {

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        Optional<ButtonType> op = ClaseUtil.confirmarMensaje("Seguro que quiere autorizar esta nomina");

        System.out.println("op.get() " + op.get());

        if (op.get() == ButtonType.YES) {

            try {

                nomina.setAutorizadaPor(VariablesGlobales.USUARIO.getCodigo());
                cbEstado.getSelectionModel().select(3);
                nomina.setEstado(cbEstado.getSelectionModel().getSelectedItem());
                nomina.setFechaAutorizada(new Date());
                nomina.setDetalleNominaCollection(listaDetalle);

                nomina.setObservacion(
                        txtObservacion.getText().isEmpty() ? "na"
                        : txtObservacion.getText()
                );

                ManejoNomina.getInstancia().actualizar(nomina);

                listaDetalle.clear();
                txtObservacion.clear();
                txtMontoNomina.clear();
                txtNumeroNom.clear();
                txtCantidadEmpleado.clear();
                btnARevision.setDisable(true);
                btnGuardar.setDisable(true);
                ClaseUtil.mensaje(" Nomina autorizada exitosamente ");

            } catch (Exception e) {

                btnARevision.setDisable(false);
                btnGuardar.setDisable(false);
                ClaseUtil.mensaje("Hubo un problema autorizando la nomina ");
                e.printStackTrace();
            }

        }

    }

    @FXML
    private void btnARevisionActionEvent(ActionEvent event) {

        Optional<ButtonType> op = ClaseUtil.confirmarMensaje("Seguro que quiere enviar  esta nomina a revision ");

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
                        :txtObservacion.getText()
                );

                ManejoNomina.getInstancia().actualizar(nomina);

                listaDetalle.clear();
                txtObservacion.clear();
                txtMontoNomina.clear();
                txtNumeroNom.clear();
                txtCantidadEmpleado.clear();
                ClaseUtil.mensaje(" Nomina enviada a revision ");

            } catch (Exception e) {
                ClaseUtil.mensaje("Hubo un problema enviada la nomina a revision  ");
                e.printStackTrace();
            }

        }

    }

     private Double totalNomina() {

        double total = 0.00;
        String totalSrt=ClaseUtil.FormatearNumero(total);

        for (DetalleNomina det : listaDetalle) {

            total += det.getSueldoNeto();
        }
       
        
        return total;
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

                        } else {

                            btnHabilitar.setText("NO");
                            btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 10px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 12pt;");

                        }

                        HBox hbox = new HBox();

//                        hbox.getChildren().addAll(imageview);
                        hbox.getChildren().add(btnHabilitar);

                        hbox.setAlignment(Pos.CENTER);

                        setGraphic(btnHabilitar);
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

        tbcAvanceSueldoDet.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcComisionDet.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcDepeAdicionalesDet.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcOtrasRemuneracionesDet.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcHoraExtraDet.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcTotalOtroBeneficioDet.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

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
            Logger.getLogger(AutorizarNominaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
