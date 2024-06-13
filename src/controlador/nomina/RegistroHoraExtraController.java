/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.nomina;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.empleado.BuscarEmpleadoController;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import manejo.ManejoConfiguracion;
import manejo.nomina.ManejoDiasNoLaborable;
import manejo.nomina.ManejoRegistroHoraExtra;
import modelo.DetalleRegistroHoraExtra;
import modelo.Empleado;
import modelo.RegistroHoraExtra;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroHoraExtraController implements Initializable {

    @FXML
    private JFXTextField txtTotalHora;
    @FXML
    private Label lbNumeroNomina;
    @FXML
    private HBox hbDetaHoextra;
    @FXML
    private Label lbDiaFeriado;

    /**
     * @return the editar
     */
    public boolean isEditar() {
        return editar;
    }

    /**
     * @param editar the editar to set
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

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
    private JFXTextField txtNumeroOrden;
    @FXML
    private JFXDatePicker dpFechaHoraExtra;
    @FXML
    private JFXButton btnGuardar;

    /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private TextArea txtObservacion;
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
    private TableColumn<DetalleRegistroHoraExtra, Double> tbcSueldoPorHoraExtra;
    @FXML
    private TableColumn<DetalleRegistroHoraExtra, Double> tbcPorciento;
    @FXML
    private TableColumn<DetalleRegistroHoraExtra, String> tbcConceptoDet;
    @FXML
    private TableColumn<DetalleRegistroHoraExtra, String> tbcFecha;
    @FXML
    private JFXTextField txtCantidadEmpleado;
    @FXML
    private JFXTextField txtMontoNomina;
    @FXML
    private JFXTextField txtEmpleado;
    @FXML
    private JFXButton btBuscarSupplidor;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXButton btnEliminar;

    ObservableList<DetalleRegistroHoraExtra> listaDetalle = FXCollections.observableArrayList();
    ObservableList<RegistroHoraExtra> lista = FXCollections.observableArrayList();

    private Empleado empleado;
    private RegistroHoraExtra rgHoraExtra;
    DetalleRegistroHoraExtra det;
    private boolean editar = false;
    boolean diaFeriado = false;
    int diaHrsExtraCienPorCiento = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTablaDetalle();
        setRgHoraExtra(new RegistroHoraExtra());
    }

    public void inicializarTablaDetalle() {

        try {

            tbcEmpleadoDet.setCellValueFactory(new PropertyValueFactory<>("empleado"));
            tbcConceptoDet.setCellValueFactory(new PropertyValueFactory<>("concepto"));
            tbcSueldoPorHora.setCellValueFactory(new PropertyValueFactory<>("sueldoPorHora"));
            tbcSueldoPorHoraExtra.setCellValueFactory(new PropertyValueFactory<>("sueldoPorHoraExtra"));
            tbcTotalDet.setCellValueFactory(new PropertyValueFactory<>("total"));
            tbcHoraExtraDet.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            tbcPorciento.setCellValueFactory(new PropertyValueFactory<>("porCientoHoraExtra"));
            tbcFecha.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFecha() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFecha()));
                        }
                        return property;
                    });

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tbDetalleContratoMouseClicked(MouseEvent event) {
    }

    @FXML
    private void btBuscarSupplidorActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/empleado/BuscarEmpleado.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        BuscarEmpleadoController articuloController = loader.getController();

        if (!(articuloController.getEmpleado() == null)) {

            setEmpleado(articuloController.getEmpleado());

            txtEmpleado.setText(getEmpleado().getNombre());
            txtCantidad.requestFocus();

        }

    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {

        try {

            if (txtEmpleado.getText().isEmpty()) {

                ClaseUtil.mensaje(" Tiene que seleccionar un empleado");
                return;
            }

            if (txtCantidad.getText().isEmpty()) {

                ClaseUtil.mensaje(" Tiene que digitar cantidad de hora extra ");
                txtCantidad.requestFocus();
                return;
            }

            if (dpFechaHoraExtra.getValue() == null) {

                ClaseUtil.mensaje(" Tiene que seleccionar fecha de la hora extra ");
                dpFechaHoraExtra.requestFocus();
                return;
            }

            if (txtObservacion.getText().isEmpty()) {

                ClaseUtil.mensaje(" Tiene que digitar el concepto de hora extra ");
                txtObservacion.requestFocus();
                return;
            }

            if (validarFecha()) {

                ClaseUtil.mensaje(" La fecha de hora extra no puede ser mayor que la fecha fin de hora extra ");

                return;
            }

            agregar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("Seguro que quiere eliminar este regsitro ? ");

        if (ok.get() == ButtonType.OK || ok.get() == ButtonType.YES) {

            int selectedIndex = tbDetalle.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {

                listaDetalle.remove(tbDetalle.getSelectionModel().getSelectedItem());
                tbDetalle.refresh();

            }

            txtCantidadEmpleado.setText(totalEmpleado().toString());
            txtMontoNomina.setText(total().toString());
            txtTotalHora.setText(totalHoraExtra().toString());
        }

    }

    private void agregar() {

        double totalGanado = 0.00, totalHora = 0.00,
                porciento = 0.00, SueldoPorHoraExtra = 0.00;

        det = new DetalleRegistroHoraExtra();

        det.setCantidad(Double.parseDouble(txtCantidad.getText()));
        det.setConcepto(txtObservacion.getText());
        det.setFecha(ClaseUtil.asDate(dpFechaHoraExtra.getValue()));
        det.setEmpleado(getEmpleado());
        det.setRegistroHoraExtra(getRgHoraExtra());
        det.setSueldoPorHora(getEmpleado().getSueldoPorHora());

        totalHora = totalHoraExtra(det.getEmpleado().getCodigo(), det.getCantidad());

        totalHora += 44.00;

        if (totalHora <= 68) {

            porciento = (ManejoConfiguracion.getInstancia().getConfiguracion().getHoraExtraMenor69()) / 100;

            SueldoPorHoraExtra = det.getSueldoPorHora() * (1 + porciento);
            totalGanado = det.getCantidad() * SueldoPorHoraExtra;

        } else if (totalHora > 68) {

            porciento = (ManejoConfiguracion.getInstancia().getConfiguracion().getHoraExtraMayor68()) / 100;

            SueldoPorHoraExtra = det.getSueldoPorHora() * (1 + porciento);
            totalGanado = det.getCantidad() * SueldoPorHoraExtra;
        }

        if (diaFeriado || (diaHrsExtraCienPorCiento == 1 || diaHrsExtraCienPorCiento == 7)) {// 1 es domingo y 7 es sabado

            porciento = 100.00;
            SueldoPorHoraExtra = det.getSueldoPorHora() * 2;
            totalGanado = det.getCantidad() * SueldoPorHoraExtra;
            det.setPorCientoHoraExtra(porciento);

        } else if (diaHrsExtraCienPorCiento > 1 && diaHrsExtraCienPorCiento < 7) {

            porciento = 0.35;
            SueldoPorHoraExtra =det.getSueldoPorHora() * (1 + porciento);
            totalGanado = det.getCantidad() * SueldoPorHoraExtra;
            det.setPorCientoHoraExtra(porciento*100);
        }

        System.out.println(" porciento : " + porciento + "  SueldoPorHoraExtra : " + SueldoPorHoraExtra);

        det.setTotal(totalGanado);
        det.setSueldoPorHoraExtra(SueldoPorHoraExtra);

        listaDetalle.add(det);
        txtCantidadEmpleado.setText(totalEmpleado().toString());
        txtMontoNomina.setText(total().toString());
        txtTotalHora.setText(totalHoraExtra().toString());
        txtCantidad.clear();
        dpFechaHoraExtra.setValue(null);
        diaFeriado = false;
        lbDiaFeriado.setText("");
//        setEmpleado(null);
//        txtEmpleado.clear();
        txtObservacion.clear();

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (dpFechaFinal.getValue() == null) {

                ClaseUtil.mensaje("Tiene que seleccionar el periodo de corte ");
                dpFechaFinal.requestFocus();
                return;
            }

            if (dpFechaInicio.getValue() == null) {

                ClaseUtil.mensaje("Tiene que seleccionar el periodo de cierre ");
                dpFechaInicio.requestFocus();
                return;
            }

            if (listaDetalle.size() <= 0) {

                ClaseUtil.mensaje("No hay hora extra agregada ");

                return;
            }

//            if (isEditar()) {
//
//                getRgHoraExtra().getDetalleRegistroHoraExtraCollection().clear();//para recibir la  lista nueva
//                 getRgHoraExtra().getDetalleRegistroHoraExtraCollection().addAll(listaDetalle);
//
//                System.out.println("actualizando .. " + entdiario);
//                entdiario = EntradaDiarioDao.getInstancia().actualizar(entdiario);
//            } else {
//
//                entdiario.setDetalleEntradaDiarioCollection(listaDetalleEnt);
//                System.out.println("guardando  .. " + entdiario);
//                entdiario = EntradaDiarioDao.getInstancia().salvar(entdiario);
//            }
            Date fi = ClaseUtil.asDate(dpFechaInicio.getValue());
            Date ff = ClaseUtil.asDate(dpFechaFinal.getValue());

            getRgHoraExtra().setAnio(ClaseUtil.getAno(fi));
            getRgHoraExtra().setFechaRegistro(new Date());
            getRgHoraExtra().setMes(ClaseUtil.getMes(fi));
            getRgHoraExtra().setMesAnio(ClaseUtil.getMesAno(fi));
            getRgHoraExtra().setTotal(total());
            getRgHoraExtra().setUsuario(VariablesGlobales.USUARIO);
            getRgHoraExtra().setDetalleRegistroHoraExtraCollection(listaDetalle);
            getRgHoraExtra().setFechaFinal(ff);
            getRgHoraExtra().setFechaInicio(fi);
            getRgHoraExtra().setPeriodo(lbNumeroNomina.getText());
            getRgHoraExtra().setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            System.out.println("nombre mes " + ClaseUtil.getNombreDelMes(fi));
            getRgHoraExtra().setNombreMes(ClaseUtil.getNombreDelMes(fi).toUpperCase());

            if (editar) {

//                getRgHoraExtra().getDetalleRegistroHoraExtraCollection().clear();//para recibir la  lista nueva
//                getRgHoraExtra().getDetalleRegistroHoraExtraCollection().addAll(listaDetalle);
                ManejoRegistroHoraExtra.getInstancia().actualizar(getRgHoraExtra());
                ClaseUtil.mensaje(" Horas  extra actualizada exitosamente ");

            } else {

                getRgHoraExtra().setDetalleRegistroHoraExtraCollection(listaDetalle);
                ManejoRegistroHoraExtra.getInstancia().salvar(getRgHoraExtra());
                ClaseUtil.mensaje(" Horas  extra guardada exitosamente ");
            }

            dpFechaFinal.setValue(null);
            dpFechaInicio.setValue(null);
            listaDetalle.clear();
            txtObservacion.clear();
            lbNumeroNomina.setText("");
            txtCantidadEmpleado.clear();
            txtMontoNomina.clear();
            txtNumeroOrden.clear();
            txtTotalHora.clear();
            editar = false;

        } catch (Exception e) {
            ClaseUtil.mensaje(" Hubo un error guardando la hora extra ");
            e.printStackTrace();
        }
    }

    private Double total() {

        double total = 0.00;

        for (DetalleRegistroHoraExtra det : listaDetalle) {
            total += det.getTotal();
        }

        return ClaseUtil.roundDouble(total, 2);
    }

    private Integer totalEmpleado() {

        Integer total = listaDetalle.size();

        return total;

    }

    private double totalHoraExtra(int codEmpleado, double cantHora) {

        double totalHora = 0.00;

        for (DetalleRegistroHoraExtra detH : listaDetalle) {

            if (codEmpleado == detH.getEmpleado().getCodigo()) {
                totalHora += detH.getTotal();
            }

        }

        return ClaseUtil.roundDouble(totalHora + cantHora, 2);
    }

    private Double totalHoraExtra() {

        double totalHora = 0.00;

        for (DetalleRegistroHoraExtra detHex : listaDetalle) {

            totalHora += detHex.getCantidad();

        }

        return ClaseUtil.roundDouble(totalHora, 2);
    }

    public void llenarCampo() {

        listaDetalle.clear();

        if (!(getRgHoraExtra().getDetalleRegistroHoraExtraCollection() == null)) {

            listaDetalle.addAll(ManejoRegistroHoraExtra.getInstancia()
                    .getLista(getRgHoraExtra().getCodigo()));

        }

        txtMontoNomina.setText(Double.toString(total()));
        lbNumeroNomina.setText(getRgHoraExtra().getPeriodo());
        dpFechaFinal.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getRgHoraExtra().getFechaFinal()));
        dpFechaInicio.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getRgHoraExtra().getFechaInicio()));
        txtNumeroOrden.setText(getRgHoraExtra().getCodigo().toString());
        txtCantidadEmpleado.setText(totalEmpleado().toString());
        txtMontoNomina.setText(total().toString());
        txtTotalHora.setText(totalHoraExtra().toString());

        editar = true;

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

            mes = ClaseUtil.getNombreDelMes(fechaCorte).toUpperCase();
            strDia = ClaseUtil.getNombreDia(fechaCorte);

            int anio = ClaseUtil.getAno(fechaCorte);

            if (diasTranscurrido.intValue() <= 15) {

                lbNumeroNomina.setText(mes + " 1RA. QUINCENA " + anio);
                dpFechaInicio.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaPrimerDiaMes));

            } else if (diasTranscurrido.intValue() > 15) {

                fechaPrimerDiaMes = ClaseUtil.Fechadiadespues(fechaPrimerDiaMes, 15);
                dpFechaInicio.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fechaPrimerDiaMes));
                lbNumeroNomina.setText(mes + " 2DA. QUINCENA " + anio);

            }

            hbDetaHoextra.setDisable(false);

        } else {
            hbDetaHoextra.setDisable(true);
            lbNumeroNomina.setText("");
        }

    }

    private void convertirColumnaSrtANum() {

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

    }

    private void editarTabla() {

        tbcHoraExtraDet.setEditable(true);
        tbcHoraExtraDet.setOnEditCommit(data -> {

            double totalGanado = 0.00, totalHora = 0.00,
                    porciento = 0.00, SueldoPorHoraExtra = 0.00;
            try {

                DetalleRegistroHoraExtra p = data.getRowValue();

                if (data.getNewValue() >= 1) {

                    p.setCantidad(data.getNewValue());

                    totalHora = totalHoraExtra(p.getEmpleado().getCodigo(), p.getCantidad());

                    totalHora += 44.00;

                    if (totalHora <= 68) {

                        porciento = (ManejoConfiguracion.getInstancia().getConfiguracion().getHoraExtraMenor69()) / 100;

                        SueldoPorHoraExtra = p.getSueldoPorHora() * (1 + porciento);
                        totalGanado = p.getCantidad() * SueldoPorHoraExtra;

                    } else if (totalHora > 68) {

                        porciento = (ManejoConfiguracion.getInstancia().getConfiguracion().getHoraExtraMayor68()) / 100;

                        SueldoPorHoraExtra = p.getSueldoPorHora() * (1 + porciento);
                        totalGanado = p.getCantidad() * SueldoPorHoraExtra;
                    }

                    p.setTotal(totalGanado);
                    p.setSueldoPorHoraExtra(SueldoPorHoraExtra);
                    p.setPorCientoHoraExtra(porciento * 100);

                    txtMontoNomina.setText(total().toString());
                    txtTotalHora.setText(totalHoraExtra().toString());
                    tbDetalle.refresh();
                    tbDetalle.requestFocus();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser menor que cero");
                    tbDetalle.refresh();
                    tbDetalle.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    @FXML
    private void dpFechaHoraExtraActionEvent(ActionEvent event) {

        lbDiaFeriado.setText("");

        if (dpFechaHoraExtra.getValue() == null) {

            return;
        }

        if (validarFecha()) {

            ClaseUtil.mensaje(" La fecha de hora extra no puede ser mayor que la fecha final de hora extra ");

            return;
        }

        Date fecha = ClaseUtil.asDate(dpFechaHoraExtra.getValue());

        diaFeriado = ManejoDiasNoLaborable.getInstancia().getEsDiasFeriado(fecha);

        if (diaFeriado) {

            lbDiaFeriado.setText(" FERIADO " + ClaseUtil.getNombreDia(fecha).toUpperCase() + " AL 100 %");
        } else {

            diaHrsExtraCienPorCiento = ClaseUtil.getDiaDeLaSemana(fecha);

            if (diaHrsExtraCienPorCiento == 7 || diaHrsExtraCienPorCiento == 1) {

                lbDiaFeriado.setText(" DIA " + ClaseUtil.getNombreDia(fecha).toUpperCase() + " AL 100 %");
            } else {

                lbDiaFeriado.setText("");
            }

        }

    }

    private Boolean validarFecha() {

        boolean rp = false;
        if (!(dpFechaHoraExtra.getValue() == null)) {

            rp = dpFechaHoraExtra.getValue().isAfter(dpFechaFinal.getValue());
        }

        return rp;
    }

}
