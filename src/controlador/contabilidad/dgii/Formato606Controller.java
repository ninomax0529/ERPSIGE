/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.dgii;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import manejo.ManejoConfiguracion;
import manejo.cxp.ManejoFacturaSuplidor;
import manejo.impuesto.ManejoFormato606;
import modelo.DetalleFacturaSuplidor;
import modelo.DetalleFormato606;
import modelo.FacturaSuplidor;
import modelo.Formato606;
//import reporte.factura.RptFactura;
import util.ClaseUtil;
import util.CrearFormatoDgii;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class Formato606Controller implements Initializable {

    @FXML
    private JFXDatePicker dpFecgaHasta;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXTextField txtFiltrar;

    ObservableList<DetalleFormato606> listaFactura = FXCollections.observableArrayList();

    Date fechaIni, fechaFin;

    @FXML
    private TableView<DetalleFormato606> tbFactura;
    @FXML
    private TableColumn<DetalleFormato606, String> tbcCedulaORnc;
    @FXML
    private TableColumn<DetalleFormato606, String> tbcTipoId;
    @FXML
    private TableColumn<DetalleFormato606, String> tbcTipoBienesYServicios;
    @FXML
    private TableColumn<DetalleFormato606, String> tbcNcf;
    @FXML
    private TableColumn<DetalleFormato606, String> tbcNcfDocumentoModificado;
    @FXML
    private TableColumn<DetalleFormato606, String> tbcFechaComprabante;
    @FXML
    private TableColumn<DetalleFormato606, String> tbcFechadePago;
    @FXML
    private TableColumn<DetalleFormato606, Double> tbcMontoEnServicios;
    @FXML
    private TableColumn<DetalleFormato606, Double> tbcMontoEnBienes;
    @FXML
    private TableColumn<DetalleFormato606, Double> tbcTotalFacturado;
    @FXML
    private TableColumn<DetalleFormato606, Double> tbcxItbisFacturado;
    @FXML
    private TableColumn<DetalleFormato606, Double> tbcItbisRetenido;
    @FXML
    private TableColumn<DetalleFormato606, Double> tbcItbisSujetoAProporcionalidad;
    @FXML
    private TableColumn<DetalleFormato606, Double> tbcItbisLlevadoAlCosto;
    @FXML
    private TableColumn<DetalleFormato606, Double> tbcItbisPorAdelantar;
    @FXML
    private TableColumn<DetalleFormato606, Double> tbcItbisPercibidoEnCompra;
    @FXML
    private TableColumn<DetalleFormato606, String> tbcTipoRetencionIsr;
    @FXML
    private TableColumn<DetalleFormato606, Double> tbcMontoRetencionRenta;
    @FXML
    private TableColumn<DetalleFormato606, Double> tbcIsrPercibidoEnCompras;
    @FXML
    private TableColumn<DetalleFormato606, Double> tbcImpuestoSelectivoalConsumo;
    @FXML
    private TableColumn<DetalleFormato606, Double> tbcOtrosImpuesto;
    @FXML
    private TableColumn<DetalleFormato606, Double> tbcMontoPropinaLegal;
    @FXML
    private TableColumn<DetalleFormato606, String> tbcFormaPago;

    Formato606 formato606;
    @FXML
    private JFXButton btnExportarTxt;
    @FXML
    private HBox hbPeriodo;
    @FXML
    private Label txtMes;
    @FXML
    private Label txtAnio;
    @FXML
    private Label txtRnc;
    @FXML
    private Label txtCantRegistro;
    @FXML
    private JFXTextField txtTotalFacturado;
    @FXML
    private JFXTextField txtItbisFacturado;
    @FXML
    private JFXTextField txtTotalItbisRetendo;
    @FXML
    private JFXTextField txtIsrRetenido;
    @FXML
    private JFXButton btnRutaTxt;
    @FXML
    private Label lbRuraArchivo;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnExportarXLS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablaEncabezado();
        txtRnc.setText(ManejoConfiguracion.getInstancia().getConfiguracion().getRnc());

        iniciazarFiltro();
        hbPeriodo.setVisible(false);
        btnBuscar.setDisable(true);

        dpFecgaHasta.setValue(LocalDate.now());
        formato606 = new Formato606();

        dpFecgaHasta.setOnAction(event -> {

            Date fecha = ClaseUtil.getFechaDesdeLocalDate(dpFecgaHasta.getValue());
            fecha = ClaseUtil.getFechaUltimoDiaDelMes(fecha);
            dpFecgaHasta.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fecha));

            String mes = ClaseUtil.getNombreDelMes(fecha);
            int anio = ClaseUtil.getAno(fecha);
            int numMes = ClaseUtil.getMes(fecha);

            int anioMes = Integer.parseInt(ClaseUtil.getAnioMes(fecha).trim());
            txtMes.setText(mes.toUpperCase());
            txtAnio.setText(Integer.toString(anio));
            hbPeriodo.setVisible(true);
            formato606.setPeriodo(anioMes);
            formato606.setFecha(fecha);
            formato606.setUnidadNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            formato606.setUsuario(VariablesGlobales.USUARIO);
            formato606.setNombreUsuario(formato606.getUsuario().getNombre());
            formato606.setAnio(anio);
            formato606.setMes(numMes);
            listaFactura.clear();
            btnBuscar.setDisable(false);

        });
    }

    public void inicializarTablaEncabezado() {

        tbFactura.setItems(listaFactura);

        tbcCedulaORnc.setCellValueFactory(new PropertyValueFactory<>("rncOCedula"));
        tbcTipoId.setCellValueFactory(new PropertyValueFactory<>("tipoId"));
        tbcTipoBienesYServicios.setCellValueFactory(new PropertyValueFactory<>("tipoBienesYServicios"));
        tbcNcf.setCellValueFactory(new PropertyValueFactory<>("ncf"));
        tbcNcfDocumentoModificado.setCellValueFactory(new PropertyValueFactory<>("ncfModificado"));
        tbcFechaComprabante.setCellValueFactory(new PropertyValueFactory<>("fechaComprobante"));
        tbcFechadePago.setCellValueFactory(new PropertyValueFactory<>("fechaPago"));
        tbcMontoEnServicios.setCellValueFactory(new PropertyValueFactory<>("montoEnServicio"));
        tbcMontoEnBienes.setCellValueFactory(new PropertyValueFactory<>("montoEnBienes"));
        tbcTotalFacturado.setCellValueFactory(new PropertyValueFactory<>("totalFacturado"));
        tbcxItbisFacturado.setCellValueFactory(new PropertyValueFactory<>("itbisFacturado"));
        tbcItbisRetenido.setCellValueFactory(new PropertyValueFactory<>("itbisRetenido"));
        tbcItbisSujetoAProporcionalidad.setCellValueFactory(new PropertyValueFactory<>("itbisSujetoAProporcionalidad"));
        tbcItbisLlevadoAlCosto.setCellValueFactory(new PropertyValueFactory<>("itbisLlevadoAlCosto"));
        tbcItbisPorAdelantar.setCellValueFactory(new PropertyValueFactory<>("itbisPorAdelantar"));

        tbcItbisPercibidoEnCompra.setCellValueFactory(new PropertyValueFactory<>("itbisPercibidoEnCompra"));
        tbcIsrPercibidoEnCompras.setCellValueFactory(new PropertyValueFactory<>("isrPercibidoEnCompra"));
        tbcTipoRetencionIsr.setCellValueFactory(new PropertyValueFactory<>("tipoRetencionIsr"));
        tbcMontoRetencionRenta.setCellValueFactory(new PropertyValueFactory<>("isrRetenido"));

        tbcImpuestoSelectivoalConsumo.setCellValueFactory(new PropertyValueFactory<>("impuestoSelectivoAlComsumo"));
        tbcOtrosImpuesto.setCellValueFactory(new PropertyValueFactory<>("otrosImpuestoTasa"));
        tbcMontoPropinaLegal.setCellValueFactory(new PropertyValueFactory<>("montoPropinaLegal"));
        tbcFormaPago.setCellValueFactory(new PropertyValueFactory<>("formaDePago"));

        tbcFormaPago.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getFormaDePago() != null) {
                        property.setValue(cellData.getValue().getFormaDePago().getNombre());
                    }
                    return property;
                });

        tbcTipoBienesYServicios.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getTipoBienesYServicios() != null) {
                        property.setValue(cellData.getValue().getTipoBienesYServicios().getNombre());
                    }
                    return property;
                });

        tbcTipoRetencionIsr.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getTipoRetencionIsr() != null) {
                        property.setValue(cellData.getValue().getTipoRetencionIsr().getNombre());
                    }
                    return property;
                });

    }

    private void iniciazarFiltro() {

        FilteredList<DetalleFormato606> filteredData = new FilteredList<>(tbFactura.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltrar.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(factura -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (factura.getNcf().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (factura.getRncOCedula() != null && factura.getRncOCedula().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (factura.getNombreSuplidor() != null && factura.getNombreSuplidor().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<DetalleFormato606> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbFactura.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbFactura.setItems(sortedData);
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        listaFactura.clear();
        generarFormato606();
    }

    @FXML
    private void tbFacturaMouseCliked(MouseEvent event) {

//        if (!(tbFactura.getSelectionModel().getSelectedIndex() == -1)) {
//
//            int codFact = tbFactura.getSelectionModel().getSelectedItem().getCodigo();
//            EntradaDiario entdiario = EntradaDiarioDao.getInstancia().getEntradaDiarioPorDocumento(codFact, 12);
//            listadetalleFactura.clear();
//            listadetalleFactura.addAll(ManejoFacturaSuplidor
//                    .getInstancia().getDetalleFactura(codFact));
//
//            if (event.getClickCount() == 2) {
//                tabPane.getSelectionModel().select(1);
//            }
//
//
//        }
    }

    private Double getMontoEnBienes(FacturaSuplidor factS) {

        Double total = 0.00;
        List<DetalleFacturaSuplidor> detFs = ManejoFacturaSuplidor.getInstancia().getDetalleFactura(factS.getCodigo());

        for (DetalleFacturaSuplidor dt : detFs) {

            if (dt.getArticulo().getTipoArticulo().getCodigo() != 3) {

                total += dt.getSubTotal();
            }

        }

        return ClaseUtil.roundDouble(total, 2);
    }

    private Double getMontoEnServicio(FacturaSuplidor factS) {

        Double total = 0.00;
        List<DetalleFacturaSuplidor> detFs = ManejoFacturaSuplidor.getInstancia().getDetalleFactura(factS.getCodigo());

        for (DetalleFacturaSuplidor dt : detFs) {

            if (dt.getArticulo().getTipoArticulo().getCodigo() == 3) {

                total += dt.getSubTotal();
            }

        }

        return ClaseUtil.roundDouble(total, 2);
    }

    private Double getTotalFacturado() {

        Double total = 0.00;

        for (DetalleFormato606 dt : listaFactura) {

            if (!(dt.getTotalFacturado() == null)) {
                total += dt.getTotalFacturado();
            }

        }

        return ClaseUtil.roundDouble(total, 2);
    }

    private Double getItbisFacturado() {

        Double total = 0.00;

        for (DetalleFormato606 dt : listaFactura) {

            if (!(Double.toString(dt.getItbisFacturado()) == null)) {
                total += dt.getItbisFacturado();
            }

        }

        return ClaseUtil.roundDouble(total, 2);
    }

    private Double getItbisRetenido() {

        Double total = 0.00;

        for (DetalleFormato606 dt : listaFactura) {

            if (!(dt.getItbisRetenido() == null)) {
                total += dt.getItbisRetenido();
            }

        }

        return ClaseUtil.roundDouble(total, 2);
    }

    private Double getIsrRetenido() {

        Double total = 0.00;

        for (DetalleFormato606 dt : listaFactura) {

            if (!(dt.getIsrRetenido() == null)) {
                total += dt.getIsrRetenido();
            }

        }

        return ClaseUtil.roundDouble(total, 2);
    }

    @FXML
    private void btnExportarTxtActionEvent(ActionEvent event) {

        try {

            if (listaFactura.size() > 0) {

                if (lbRuraArchivo.getText().isEmpty()) {
                    ClaseUtil.mensaje(" TIENE QUE SELECCIONAR LA RUATA DEL ARCHIVO TXT ");
                    return;
                }

                for (DetalleFormato606 dt : listaFactura) {
                    System.out.println("detalle " + dt);
                }

                CrearFormatoDgii.crearArchivo606(listaFactura, lbRuraArchivo.getText());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void generarFormato606() {

        if (formato606.getUnidadNegocio().getRnc() == null) {
            formato606.setRnc(ManejoConfiguracion.getInstancia().getConfiguracion().getRnc());
        } else {

            if (formato606.getUnidadNegocio().getRnc().equals("na")) {
                formato606.setRnc(ManejoConfiguracion.getInstancia().getConfiguracion().getRnc());
            } else {
                formato606.setRnc(formato606.getUnidadNegocio().getRnc());
            }
        }

        Date fechaHasta, fechaDesde;
        fechaHasta = ClaseUtil.asDate(dpFecgaHasta.getValue());
        fechaDesde = ClaseUtil.getFechaPrimerDiasDelMes(fechaHasta);

        List<FacturaSuplidor> istaFact = ManejoFacturaSuplidor.getInstancia().getLista(fechaDesde, fechaHasta, "B02");

        DetalleFormato606 detFt606;

        for (FacturaSuplidor fact : istaFact) {

            detFt606 = new DetalleFormato606();

            detFt606.setFormato606(formato606);

            detFt606.setFactura(fact.getCodigo());

            detFt606.setRncOCedula(fact.getSuplidor().getRnc().replace("-", "").trim());

            if (detFt606.getRncOCedula().length() == 9) {

                detFt606.setTipoId(1);
            } else if (detFt606.getRncOCedula().length() == 11) {
                detFt606.setTipoId(2);
            } else if (detFt606.getRncOCedula().length() < 9 || detFt606.getRncOCedula().length() > 11) {
                ClaseUtil.mensaje("EL RNC O CUDULA DE ESTE  SUPLIDOR ESTA INCORRECTO  " + fact.getSuplidor().getDescripcion());
               // btnExportarTxt.setDisable(true);
                btnGuardar.setDisable(true);
                return;
            }

            btnGuardar.setDisable(false);
            btnExportarTxt.setDisable(false);
            detFt606.setFechaComprobante(fact.getFecha());
            detFt606.setNcf(fact.getNcf());
            detFt606.setNcfModificado(null);

            detFt606.setTipoBienesYServicios(fact.getCostosYGastos() == null ? null
                    : fact.getCostosYGastos());

            detFt606.setMontoEnServicio(getMontoEnServicio(fact) <= 0 ? null
                    : ClaseUtil.roundDouble(getMontoEnServicio(fact), 2)
            );

            detFt606.setMontoEnBienes(getMontoEnBienes(fact) <= 0 ? null
                    : ClaseUtil.roundDouble(getMontoEnBienes(fact), 2)
            );

            detFt606.setTotalFacturado(fact.getSubTotal());
            detFt606.setItbisFacturado(fact.getItbis());
            detFt606.setItbisRetenido(fact.getTotalItbisRetenido() == null ? null
                    : ClaseUtil.roundDouble(fact.getTotalItbisRetenido(), 2)
            );

            detFt606.setSuplidor(fact.getSuplidor().getCodigo());

            detFt606.setTipoRetencionIsr(fact.getTipoIsr() == null ? null
                    : fact.getTipoIsr());

            detFt606.setIsrRetenido(fact.getIsr() <= 0 ? null
                    : fact.getIsr()
            );

            if (!(fact.getFormaDePago() == null)) {

//                if (!(fact.getFormaDePago().getCodigo() == 4)) {
                fact.setFechaPago(fact.getFecha());
                detFt606.setFormaDePago(fact.getFormaDePago());
                detFt606.setNombreFormaDePago(fact.getFormaDePago().getNombre());
//                }
            }

            detFt606.setFechaPago(fact.getFechaPago());
//
//            if (!(fact.getFechaPago() == null)) {
//
//                fact.setFechaPago(fact.getFecha());
//                detFt606.setFormaDePago(fact.getFormaDePago());
//                detFt606.setNombreFormaDePago(fact.getFormaDePago().getNombre());
//            }

            detFt606.setItbisPorAdelantar(fact.getItbisPorAdelantar() == null ? 0
                    : ClaseUtil.roundDouble(fact.getItbisPorAdelantar(), 2));

            detFt606.setItbisLlevadoAlCosto(fact.getItbisLlevadoAlCosto() == null ? 0
                    : ClaseUtil.roundDouble(fact.getItbisLlevadoAlCosto(), 2));

            detFt606.setItbisSujetoAProporcionalidad(fact.getItbisSujetoAProporcionalidad() == null ? null
                    : ClaseUtil.roundDouble(fact.getItbisSujetoAProporcionalidad(), 2)
            );

            detFt606.setMontoPropinaLegal(fact.getPropinaLegal() == null ? null
                    : ClaseUtil.roundDouble(fact.getPropinaLegal(), 2)
            );

            detFt606.setImpuestoSelectivoAlComsumo(fact.getImpuestoSelectivoAlConsumo() == null ? null
                    : ClaseUtil.roundDouble(fact.getImpuestoSelectivoAlConsumo(), 2)
            );

            detFt606.setOtrosImpuestoTasa(fact.getOtrosImpuestoYOTasa() == null ? null
                    : ClaseUtil.roundDouble(fact.getOtrosImpuestoYOTasa(), 2)
            );

            detFt606.setItbisPercibidoEnCompra(null);
            detFt606.setIsrPercibidoEnCompra(null);

            listaFactura.add(detFt606);

        }

        formato606.setCantidadRegistro(listaFactura.size());
        formato606.setFechaRegistro(new Date());

        txtCantRegistro.setText(Integer.toString(formato606.getCantidadRegistro()));

        txtTotalFacturado.setText(getTotalFacturado().toString());
        txtItbisFacturado.setText(getItbisFacturado().toString());
        txtTotalItbisRetendo.setText(getItbisRetenido().toString());
        txtIsrRetenido.setText(getIsrRetenido().toString());
        btnBuscar.setDisable(true);

    }

    @FXML
    private void btnRutaTxtActionEvent(ActionEvent event) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File fileLocal = directoryChooser.showDialog(btnRutaTxt.getScene().getWindow());

        if (fileLocal == null) {
            return;
        }

        if (!(fileLocal.getPath() == null)) {

            String path = fileLocal.getPath(); // La ruta de la carpeta seleccionada

            if (fileLocal.isDirectory()) {
                lbRuraArchivo.setText(fileLocal.getAbsolutePath());
                System.out.println("ruta " + path);
//            btnExportPdf.setDisable(false);
            } else {

//            btnExportPdf.setDisable(true);
                ClaseUtil.mensaje("Tiene que seleccionar una carpeta");
            }

        }

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (listaFactura.size() <= 0) {
                ClaseUtil.mensaje("NO HAY REGISTRO PARA GUARDAR");
                return;
            }

            Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("SEGURO QUE QUIERE GUARDAR EL FORMATO 606 ");

            if (ok.get() == ButtonType.OK || ok.get() == ButtonType.YES) {

                Formato606 formatoDb = ManejoFormato606.getInstancia().getFormato606(formato606.getAnio(), formato606.getMes());

                if (!(formatoDb == null)) {

                    ManejoFormato606.getInstancia().eliminarfORMATO606(formatoDb.getCodigo());
                }

                formato606.setDetalleFormato606Collection(listaFactura);

                ManejoFormato606.getInstancia().salvar(formato606);

                listaFactura.clear();
                hbPeriodo.setVisible(false);

                txtTotalFacturado.setText(getTotalFacturado().toString());
                txtItbisFacturado.setText(getItbisFacturado().toString());
                txtTotalItbisRetendo.setText(getItbisRetenido().toString());
                txtIsrRetenido.setText(getIsrRetenido().toString());

                utiles.ClaseUtil.mensaje("FORMATO GUARDADO CORRECTAMENTE");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnExportarXLSActionEvent(ActionEvent event) {

        if (listaFactura.size() > 0) {
            try {
                ClaseUtil.exportarDAtos(tbFactura);
            } catch (IOException ex) {
                Logger.getLogger(Formato606Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ClaseUtil.mensaje("No hay datos para exportar");
        }
    }

}
