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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import manejo.ManejoConfiguracion;
import manejo.empleado.ManejoEmpleado;
import manejo.nomina.ManejoDeducionesTss;
import manejo.nomina.ManejoEstadoNomina;
import manejo.nomina.ManejoNomina;
import manejo.nomina.ManejoTipoNomina;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.DeducionesTss;
import modelo.DetalleNomina;
import modelo.Empleado;
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
public class NominaController implements Initializable {

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
    private TableColumn<DetalleNomina, Double> tbcCuentasPorCobrar;
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
        dpFechaInicio.setValue(LocalDate.now());
//        dpFechaFinal.setValue(LocalDate.now());

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
        cbEstado.getSelectionModel().select(0);

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
            tbcCuentasPorCobrar.setCellValueFactory(new PropertyValueFactory<>("cxcEmpleado"));

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

            nomina = ManejoNomina.getInstancia().getNominaEnProceso(estado, tipoNom);//estado en proceso

            tipoNom = cbTipoNomina.getSelectionModel().getSelectedItem().getCodigo();

            List<Empleado> listaEmp = ManejoEmpleado.getInstancia().getLista(tipoNom);

            if (!(nomina == null)) {

                System.out.println("no es null nomina " + nomina);
                dpFechaFinal.setValue(ClaseUtil.convertToLocalDateViaMilisecond(nomina.getFechaCorte()));
                dpFechaInicio.setValue(ClaseUtil.convertToLocalDateViaMilisecond(nomina.getFechaInicio()));
                cbEstado.setValue(nomina.getEstado());
                cbTipoNomina.setValue(nomina.getTipoNomina());
                txtObservacion.setText(nomina.getObservacion());
                listaDetalle.addAll(ManejoNomina.getInstancia().getLista(nomina.getCodigo()));
                txtMontoNomina.setText(totalNomina().toString());
                txtCantidadEmpleado.setText(cantidadEmpleado().toString());
                editar = true;

            } else {

                nomina = new Nomina();
                System.out.println(" new Nomina() " + nomina);
            }

            if (dpFechaInicio.getValue() == null) {

                ClaseUtil.mensaje("Tiene que seleccionar una fecha inicio de nomina");
                dpFechaInicio.requestFocus();
                return;
            }

            if (dpFechaFinal.getValue() == null) {

                ClaseUtil.mensaje("Tiene que seleccionar una fecha final de nomina");
                dpFechaFinal.requestFocus();
                return;
            }

            Date fecha = ClaseUtil.asDate(dpFechaFinal.getValue());
            int anio = ClaseUtil.getAno(fecha);
            int mes = ClaseUtil.getMes(fecha);
            boolean existeNomina = ManejoNomina.getInstancia()
                    .existeNomina(tipoNom, anio, mes, lbNumeroNomina.getText());

            System.out.println("tipoNom " + tipoNom + " anio " + anio + "  mes " + mes + "  periodo " + lbNumeroNomina.getText());

            if (existeNomina == true && editar == false) {
                ClaseUtil.mensaje(" Ya existe una nomina de este tipo en este periodo ");
//                lbNumeroNomina.setText(" ");
                return;
            }

            btnGuardar.setDisable(false);

            txtCantidadEmpleado.setText(cantidadEmpleado().toString());

            if (editar) {
                return;
            }

            txtMontoNomina.clear();
            txtCantidadEmpleado.clear();
            boolean calculoUltNomina = ManejoConfiguracion.getInstancia().getConfiguracion().getCalcularUltimaNomina();
            Double sueldoCalUltNomina = 0.00, ortasRemuneracionCalUltNom = 0.00, montoDepAdicionales, montoDep;
            int tipoNomina = cbTipoNomina.getValue().getCodigo();
            int ung = VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();
            for (Empleado emp : listaEmp) {

                det = new DetalleNomina();

                det.setNomina(nomina);
                det.setEmpleado(emp);

                if (calculoUltNomina) {

                    sueldoCalUltNomina = ManejoNomina.getInstancia().getUltimoSueldoQuincenal(emp.getCodigo(), tipoNomina, ung);
                    ortasRemuneracionCalUltNom = ManejoNomina.getInstancia().getUltimaOtraRemuneraciones(emp.getCodigo(), tipoNomina, ung);
                    montoDep = ManejoNomina.getInstancia().getUltimoMontoDependiente(emp.getCodigo(), tipoNomina, ung);
                    montoDepAdicionales = ManejoNomina.getInstancia().getUltimoMontoDependienteAdicionales(emp.getCodigo(), tipoNomina, ung);

                    det.setSueldoQuincenal(sueldoCalUltNomina);
                    det.setOtrasRemuneraciones(ortasRemuneracionCalUltNom);
                    det.setMontoDependiente(montoDep);
                    det.setMontoDependienteAdicional(montoDepAdicionales);

                } else {
                    det.setSueldoQuincenal(emp.getSueldoQuincenal());
                    det.setOtrasRemuneraciones(0);
                    det.setMontoDependiente(0);
                    det.setMontoDependienteAdicional(0);

                }

                det.setSueldo(emp.getSueldoMensual());
                det.setAvanceASueldo(0.0);
                det.setCxcEmpleado(0.0);
                det.setCedula(emp.getCedula());
                det.setComision(0);
                det.setHorasExtra(0);
                det.setIr3(0);
                det.setMontoAfp(totalAFP(det));
                det.setMontoSfs(totalSFS(det));

                det.setRevisado(false);
                det.setTotalBruto(totalBruto(det));
                det.setTotalDescuento(totalDescuento(det));
                det.setTotalOtrasBeneficio(0);
                det.setSueldoNeto(sueldoNeto(det));
                txtMontoNomina.setText(sueldoNeto(det).toString());

                listaDetalle.add(det);

            }

            txtCantidadEmpleado.setText(Integer.toString(listaDetalle.size()));
            txtMontoNomina.setText(totalNomina().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void tbDetalleContratoMouseClicked(MouseEvent event) {

    }

    @FXML
    private void btnARevisionActionEvent(ActionEvent event) {

        Optional<ButtonType> op = ClaseUtil.confirmarMensaje(" Seguro que quiere enviar  esta nomina a revision ");

        System.out.println("op.get() " + op.get());

        if (op.get() == ButtonType.YES) {

            try {

                cbEstado.getSelectionModel().select(1);//estado 1 es  preparada
                nomina.setEstado(cbEstado.getSelectionModel().getSelectedItem());
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
                btnARevision.setDisable(true);
                btnGuardar.setDisable(true);
                ClaseUtil.mensaje(" Nomina enviada a revision exitosamente ");

            } catch (Exception e) {
                ClaseUtil.mensaje("Hubo un problema enviada la nomina a revision  ");
                e.printStackTrace();
            }

        }

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        Optional<ButtonType> op = ClaseUtil.confirmarMensaje("Seguro que quiere guardar  esta nomina ");

        System.out.println("op.get() " + op.get());

        if (op.get() == ButtonType.YES) {

            try {

                nomina.setUsuario(VariablesGlobales.USUARIO);
                nomina.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
                nomina.setTipoNomina(cbTipoNomina.getSelectionModel().getSelectedItem());
                nomina.setEstado(cbEstado.getSelectionModel().getSelectedItem());
                nomina.setFechaPreparada(new Date());
                nomina.setPreparadaPor(VariablesGlobales.USUARIO.getCodigo());
                nomina.setAnulada(false);
                nomina.setCantidadEmpleado(cantidadEmpleado());
                nomina.setDetalleNominaCollection(listaDetalle);
                nomina.setFechaCorte(ClaseUtil.asDate(dpFechaFinal.getValue()));
                nomina.setFechaInicio((ClaseUtil.asDate(dpFechaInicio.getValue())));
                nomina.setFechaRegistro(new Date());
                nomina.setMes(ClaseUtil.getMes(nomina.getFechaCorte()));
                nomina.setAnio(ClaseUtil.getAno(nomina.getFechaCorte()));
                nomina.setMesAnio(ClaseUtil.getMesAno(nomina.getFechaCorte()));
                nomina.setNumeroNomina(0);
                nomina.setTotalNomina(totalNomina());
                nomina.setObservacion(txtObservacion.getText().isEmpty() ? "na"
                        : txtObservacion.getText()
                );

                String mes = ClaseUtil.getNombreDelMes(nomina.getFechaCorte()).toUpperCase();
                nomina.setNombreMes(mes);
                nomina.setPeriodo(lbNumeroNomina.getText());

                if (nomina.getTipoNomina().getCodigo() == 1) {

                    nomina.setNumeroSemana(lbNumeroNomina.getText());

                } else if (nomina.getTipoNomina().getCodigo() == 2) {

                    nomina.setNumeroQuincena(lbNumeroNomina.getText());

                }

                SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                        .getSecuenciaDocumento(nomina.getUnidadDeNegocio().getCodigo(), 22);

                if (editar == false) {

                    if (!(secDoc == null)) {

                        boolean existe = ManejoNomina.getInstancia().existeSecuencia(secDoc.getNumero());

                        if (existe) {

                            System.out.println("existe " + secDoc.getNumero());

                            while (ManejoNomina.getInstancia().existeSecuencia(secDoc.getNumero())) {

                                secDoc.setNumero(secDoc.getNumero() + 1);
                                ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                            }

                            secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(nomina.getUnidadDeNegocio().getCodigo(), 22);

                            nomina.setNumero(secDoc.getNumero());
                            nomina.setSecuenciaDocumento(secDoc);

                        } else {

                            System.out.println("No existe " + secDoc.getNumero());
                            nomina.setNumero(secDoc.getNumero());
                            nomina.setSecuenciaDocumento(secDoc);
                            secDoc.setNumero(secDoc.getNumero() + 1);
                            ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                        }

                    } else {

                        ClaseUtil.mensaje("La secuencia para la nomina de la unidad de negocio "
                                + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                        return;
                    }
                }

                if (editar == false) {
                    ManejoNomina.getInstancia().salvar(nomina);
                } else {
                    ManejoNomina.getInstancia().actualizar(nomina);
                }

                listaDetalle.clear();
                txtObservacion.clear();
                txtMontoNomina.clear();
                txtNumeroNom.clear();
                txtCantidadEmpleado.clear();

                btnGuardar.setDisable(true);

                ClaseUtil.mensaje("Nomina guardada exitosamente");

                btnARevision.setDisable(false);
                nomina = new Nomina();

            } catch (Exception e) {

                btnGuardar.setDisable(false);
                btnARevision.setDisable(true);

                ClaseUtil.mensaje("Hubo un problema guardando la nomina ");
                e.printStackTrace();
            }

        }
    }

    private Double totalNomina() {

        double total = 0.00;

        for (DetalleNomina det : listaDetalle) {

            total += det.getSueldoNeto();
        }

        return ClaseUtil.roundDouble(total, 2);
    }

    private Double totalNominaFormat() {

        double total = 0.00;

        for (DetalleNomina det : listaDetalle) {

            total += det.getSueldoNeto();
        }
        String totalSrt = ClaseUtil.FormatearNumero(total);
        return Double.parseDouble(totalSrt);
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
                + det.getMontoSfs() + det.getMontoDependienteAdicional() + det.getCxcEmpleado();

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

            strDia = ClaseUtil.getNombreDia(fechaCorte);

            if (diasTranscurrido.intValue() <= 15) {

                lbNumeroNomina.setText("PRIMERA QUINCENA");
                dpFechaInicio.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaPrimerDiaMes));

            } else if (diasTranscurrido.intValue() > 15) {

                fechaPrimerDiaMes = ClaseUtil.Fechadiadespues(fechaPrimerDiaMes, 15);
                dpFechaInicio.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaPrimerDiaMes));
                lbNumeroNomina.setText("SEGUNDA QUINCENA");

            }

        } else {
            lbNumeroNomina.setText("");
        }
    }

    private void editarTabla() {

        tbcSueldoDet.setOnEditCommit(data -> {

            try {

                DetalleNomina p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setSueldoQuincenal(data.getNewValue());

                    tbDetalle.refresh();
                    tbDetalle.requestFocus();

                    p.setMontoAfp(totalAFP(p));
                    p.setMontoSfs(totalSFS(p));
                    p.setTotalBruto(totalBruto(p));
                    p.setTotalDescuento(totalDescuento(p));
                    p.setSueldoNeto(sueldoNeto(p));
                    txtMontoNomina.setText(totalNomina().toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser menor que cero");
                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcCuentasPorCobrar.setOnEditCommit(data -> {

            try {

                DetalleNomina p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setCxcEmpleado(data.getNewValue());

                    tbDetalle.refresh();
                    tbDetalle.requestFocus();

                    p.setMontoAfp(totalAFP(p));
                    p.setMontoSfs(totalSFS(p));
                    p.setTotalBruto(totalBruto(p));
                    p.setTotalDescuento(totalDescuento(p));
                    p.setSueldoNeto(sueldoNeto(p));
                    txtMontoNomina.setText(totalNomina().toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser menor que cero");
                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcComisionDet.setOnEditCommit(data -> {

            try {

                DetalleNomina p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setComision(data.getNewValue());
                    p.setTotalOtrasBeneficio(p.getTotalOtrasBeneficio() + p.getComision());
                    tbDetalle.refresh();
                    tbDetalle.requestFocus();

                    p.setMontoAfp(totalAFP(p));
                    p.setMontoSfs(totalSFS(p));
                    p.setTotalBruto(totalBruto(p));
                    p.setTotalDescuento(totalDescuento(p));
                    p.setSueldoNeto(sueldoNeto(p));
                    txtMontoNomina.setText(totalNomina().toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser menor que cero");
                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcAvanceSueldoDet.setOnEditCommit(data -> {

            try {

                DetalleNomina p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setAvanceASueldo(data.getNewValue());

                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                    p.setTotalDescuento(totalDescuento(p));
                    p.setSueldoNeto(sueldoNeto(p));
                    txtMontoNomina.setText(totalNomina().toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser menor que cero");
                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcTotalOtroBeneficioDet.setOnEditCommit(data -> {

            try {

                DetalleNomina p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setTotalOtrasBeneficio(data.getNewValue());
                    p.setTotalOtrasBeneficio(p.getTotalOtrasBeneficio() + p.getComision());

                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                    p.setTotalBruto(totalBruto(p));
                    p.setTotalDescuento(totalDescuento(p));
                    p.setSueldoNeto(sueldoNeto(p));
                    txtMontoNomina.setText(totalNomina().toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser  menor que cero");
                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcDepeAdicionalesDet.setOnEditCommit(data -> {

            try {

                DetalleNomina p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setMontoDependienteAdicional(data.getNewValue());

                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                    p.setTotalDescuento(totalDescuento(p));
                    p.setSueldoNeto(sueldoNeto(p));
                    txtMontoNomina.setText(totalNomina().toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser  menor que cero");
                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tbcHoraExtraDet.setOnEditCommit(data -> {

            try {

                DetalleNomina p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setHorasExtra(data.getNewValue());

                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                    p.setTotalBruto(totalBruto(p));
                    p.setSueldoNeto(sueldoNeto(p));
                    txtMontoNomina.setText(totalNomina().toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser  menor que cero");
                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tbcOtrasRemuneracionesDet.setOnEditCommit(data -> {

            try {

                DetalleNomina p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setOtrasRemuneraciones(data.getNewValue());

                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                    p.setTotalBruto(totalBruto(p));
                    p.setTotalDescuento(totalDescuento(p));
                    p.setSueldoNeto(sueldoNeto(p));
                    txtMontoNomina.setText(totalNomina().toString());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser  menor que cero");
                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tbcSFSDet.setOnEditCommit(data -> {

            try {

                DetalleNomina p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setMontoSfs(data.getNewValue());

                    p.setTotalBruto(totalBruto(p));
                    p.setTotalDescuento(totalDescuento(p));
                    p.setSueldoNeto(sueldoNeto(p));

                    txtMontoNomina.setText(totalNomina().toString());

                    tbDetalle.refresh();
                    tbDetalle.requestFocus();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser  menor que cero");
                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tbcAfpDet.setOnEditCommit(data -> {

            try {

                DetalleNomina p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setMontoAfp(data.getNewValue());

                    p.setTotalBruto(totalBruto(p));
                    p.setTotalDescuento(totalDescuento(p));
                    p.setSueldoNeto(sueldoNeto(p));
                    txtMontoNomina.setText(totalNomina().toString());

                    tbDetalle.refresh();
                    tbDetalle.requestFocus();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser  menor que cero");
                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void convertirColumnaSrtANum() {

        tbcSueldoDet.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcCuentasPorCobrar.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

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

        tbcSFSDet.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcAfpDet.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) {

        try {

            util.ClaseUtil.exportarDAtos(tbDetalle);

        } catch (IOException ex) {
            Logger.getLogger(NominaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
