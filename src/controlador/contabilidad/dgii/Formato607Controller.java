/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.dgii;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoFormaPago;
import manejo.impuesto.ManejoFormato607;
import modelo.DetalleFormato607;
import modelo.Factura;
import modelo.FormaPago;
import modelo.Formato607;
//import reporte.factura.RptFactura;
import util.ClaseUtil;
import util.CrearFormatoDgii;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class Formato607Controller implements Initializable {

    @FXML
    private JFXDatePicker dpFecgaHasta;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXTextField txtFiltrar;

    ObservableList<DetalleFormato607> listaFactura = FXCollections.observableArrayList();

    Date fechaIni, fechaFin;

    @FXML
    private TableView<DetalleFormato607> tbFactura;
    @FXML
    private TableColumn<DetalleFormato607, String> tbcCedulaORnc;
    @FXML
    private TableColumn<DetalleFormato607, String> tbcTipoId;
    @FXML
    private TableColumn<DetalleFormato607, String> tbcNcf;
    @FXML
    private TableColumn<DetalleFormato607, String> tbcNcfDocumentoModificado;
    @FXML
    private TableColumn<DetalleFormato607, String> tbcFechaComprabante;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcTotalFacturado;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcxItbisFacturado;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcItbisRetenido;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcMontoRetencionRenta;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcImpuestoSelectivoalConsumo;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcOtrosImpuesto;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcMontoPropinaLegal;
    @FXML
    private TableColumn<DetalleFormato607, String> tbcTipoDeIngreso;
    @FXML
    private TableColumn<DetalleFormato607, String> tbcFechaRe;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcItbisPercibido;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcIsrPercibido;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcEfectivo;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcChequeTransDep;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcTarjetaDebCredito;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcVentasCredito;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcBonosCertificado;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcPermuta;
    @FXML
    private TableColumn<DetalleFormato607, Double> tbcOtrasFormaDeVenta;

    Formato607 formato607;
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
        formato607 = new Formato607();

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
            formato607.setPeriodo(anioMes);
            formato607.setFecha(fecha);
            formato607.setUnidadNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            formato607.setUsuario(VariablesGlobales.USUARIO);
            formato607.setNombreUsuario(formato607.getUsuario().getNombre());
            formato607.setAnio(anio);
            formato607.setMes(numMes);
            listaFactura.clear();
            btnBuscar.setDisable(false);

        });
    }

    public void inicializarTablaEncabezado() {

        tbFactura.setItems(listaFactura);

        tbcCedulaORnc.setCellValueFactory(new PropertyValueFactory<>("rncOCedula"));
        tbcTipoId.setCellValueFactory(new PropertyValueFactory<>("tipoId"));
        tbcNcf.setCellValueFactory(new PropertyValueFactory<>("ncf"));
        tbcNcfDocumentoModificado.setCellValueFactory(new PropertyValueFactory<>("ncfModificado"));
        tbcFechaComprabante.setCellValueFactory(new PropertyValueFactory<>("fechaComprobante"));
        tbcTotalFacturado.setCellValueFactory(new PropertyValueFactory<>("totalFacturado"));
        tbcxItbisFacturado.setCellValueFactory(new PropertyValueFactory<>("itbisFacturado"));
        tbcItbisRetenido.setCellValueFactory(new PropertyValueFactory<>("itbisRetenidoPorTercero"));
        tbcItbisPercibido.setCellValueFactory(new PropertyValueFactory<>("itbisPercibido"));
        tbcMontoRetencionRenta.setCellValueFactory(new PropertyValueFactory<>("isrRetenidoPorTercero"));
        tbcIsrPercibido.setCellValueFactory(new PropertyValueFactory<>("isrPercibido"));

        tbcImpuestoSelectivoalConsumo.setCellValueFactory(new PropertyValueFactory<>("impuestoSelectivoAlComsumo"));
        tbcOtrosImpuesto.setCellValueFactory(new PropertyValueFactory<>("otrosImpuestoTasa"));
        tbcMontoPropinaLegal.setCellValueFactory(new PropertyValueFactory<>("montoPropinaLegal"));
        tbcEfectivo.setCellValueFactory(new PropertyValueFactory<>("efectivo"));
        tbcChequeTransDep.setCellValueFactory(new PropertyValueFactory<>("chqTransfDeposito"));
        tbcTarjetaDebCredito.setCellValueFactory(new PropertyValueFactory<>("tarjetaDebitoCredito"));
        tbcVentasCredito.setCellValueFactory(new PropertyValueFactory<>("ventaACredito"));
        tbcBonosCertificado.setCellValueFactory(new PropertyValueFactory<>("bonosOCertificado"));
        tbcPermuta.setCellValueFactory(new PropertyValueFactory<>("permuta"));
        tbcOtrasFormaDeVenta.setCellValueFactory(new PropertyValueFactory<>("otrasFormaDeVenta"));

        tbcTipoDeIngreso.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getTipoDeIngreso() != null) {
                        property.setValue(cellData.getValue().getTipoDeIngreso().getNombre());
                    }
                    return property;
                });

    }

    private void iniciazarFiltro() {

        FilteredList<DetalleFormato607> filteredData = new FilteredList<>(tbFactura.getItems(), p -> true);
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
                } else if (factura.getNombreCliente() != null && factura.getNombreCliente().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<DetalleFormato607> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbFactura.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbFactura.setItems(sortedData);
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        Date fechaHasta, fechaDesde;
        fechaHasta = ClaseUtil.asDate(dpFecgaHasta.getValue());
        fechaDesde = ClaseUtil.getFechaPrimerDiasDelMes(fechaHasta);
        List<Factura> istaFact = ManejoFactura.getInstancia().getLista607(fechaDesde, fechaHasta, "B02");

        for (Factura fact : istaFact) {

            if (fact.getTipoDeIngreso() == null) {
                ClaseUtil.mensaje("La factura con ncf igual a  " + fact.getNcf() + "  no tiene un tipo de ingreso ");
                return;
            }
        }

        listaFactura.clear();

        generarFormato607();
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



    private Double getTotalFacturado() {

        Double total = 0.00;

        for (DetalleFormato607 dt : listaFactura) {

            if (!(dt.getTotalFacturado() == null)) {
                total += dt.getTotalFacturado();
            }

        }

        return ClaseUtil.roundDouble(total, 2);
    }

    private Double getItbisFacturado() {

        Double total = 0.00;

        for (DetalleFormato607 dt : listaFactura) {

            if (!(Double.toString(dt.getItbisFacturado()) == null)) {
                total += dt.getItbisFacturado();
            }

        }

        return ClaseUtil.roundDouble(total, 2);
    }

    private Double getItbisRetenido() {

        Double total = 0.00;

        for (DetalleFormato607 dt : listaFactura) {

            if (!(dt.getItbisRetenidoPorTercero() == null)) {
                total += dt.getItbisRetenidoPorTercero();
            }

        }

        return ClaseUtil.roundDouble(total, 2);
    }

    private Double getIsrRetenido() {

        Double total = 0.00;

        for (DetalleFormato607 dt : listaFactura) {

            if (!(dt.getIsrRetenidoPorTercero() == null)) {
                total += dt.getIsrRetenidoPorTercero();
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

                for (DetalleFormato607 dt : listaFactura) {
                    System.out.println("detalle " + dt);
                }

                CrearFormatoDgii.crearArchivo607(listaFactura, lbRuraArchivo.getText());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void generarFormato607() {

        if (formato607.getUnidadNegocio().getRnc() == null) {
            formato607.setRnc(ManejoConfiguracion.getInstancia().getConfiguracion().getRnc());
        } else {

            if (formato607.getUnidadNegocio().getRnc().equals("na")) {
                formato607.setRnc(ManejoConfiguracion.getInstancia().getConfiguracion().getRnc());
            } else {
                formato607.setRnc(formato607.getUnidadNegocio().getRnc());
            }
        }

        Date fechaHasta, fechaDesde;
        fechaHasta = ClaseUtil.asDate(dpFecgaHasta.getValue());
        fechaDesde = ClaseUtil.getFechaPrimerDiasDelMes(fechaHasta);

        List<Factura> istaFact = ManejoFactura.getInstancia().getLista607(fechaDesde, fechaHasta, "B02");

        DetalleFormato607 detFt607;

        for (Factura fact : istaFact) {

            detFt607 = new DetalleFormato607();

            detFt607.setFormato607(formato607);

            detFt607.setFactura(fact.getCodigo());

            detFt607.setRncOCedula(fact.getCliente().getRnc().replace("-", "").trim());

            if (detFt607.getRncOCedula().length() == 9) {

                detFt607.setTipoId(1);
            } else if (detFt607.getRncOCedula().length() == 11) {
                detFt607.setTipoId(2);
            } else if (detFt607.getRncOCedula().length() < 9 || detFt607.getRncOCedula().length() > 11) {
                ClaseUtil.mensaje("EL RNC O CUDULA DE ESTE  CLIENTE ESTA INCORRECTO  " + fact.getCliente().getNombre());
                btnExportarTxt.setDisable(true);
                btnGuardar.setDisable(true);
                return;
            }

            btnGuardar.setDisable(false);
            btnExportarTxt.setDisable(false);
            detFt607.setFechaComprobante(fact.getFecha());
            detFt607.setNcf(fact.getNcf());
            detFt607.setNcfModificado(null);

            detFt607.setTipoDeIngreso(fact.getTipoDeIngreso() == null ? null
                    : fact.getTipoDeIngreso());
            
            detFt607.setNombreTipoDeIngreso(detFt607.getTipoDeIngreso().getNombre());
            detFt607.setNombreCliente(fact.getNombreCliente());

            detFt607.setTotalFacturado(fact.getSubTotal());
            detFt607.setItbisFacturado(fact.getItbis());

            detFt607.setItbisRetenidoPorTercero(fact.getItbisRetenido() == null ? null
                    : ClaseUtil.roundDouble(fact.getItbisRetenido(), 2)
            );

            detFt607.setCliente(fact.getCliente().getCodigo());

            detFt607.setIsrRetenidoPorTercero(fact.getIsrRetenido() == null ? null
                    : fact.getIsrRetenido()
            );

            detFt607.setFechaDeRetension(fact.getFechaRetencion());

            detFt607.setMontoPropinaLegal(fact.getMontoPropinaLegal() == null ? null
                    : ClaseUtil.roundDouble(fact.getMontoPropinaLegal(), 2)
            );

            detFt607.setImpuestoSelectivoAlComsumo(fact.getImpuestoSelectivoAlComsumo() == null ? null
                    : ClaseUtil.roundDouble(fact.getImpuestoSelectivoAlComsumo(), 2)
            );

            detFt607.setOtrosImpuestoTasa(fact.getOtrosImpuestoTasa() == null ? null
                    : ClaseUtil.roundDouble(fact.getOtrosImpuestoTasa(), 2)
            );

            detFt607.setItbisPercibido(null);
            detFt607.setIsrPercibido(null);

            List<FormaPago> listaForma = ManejoFormaPago.getInstancia().getListaFormaPago(fact.getCodigo(), 7);

            System.out.println("forma pago : " + listaForma.size());
            /*
            
            1	EFECTIVO
            2	A CREDITO
            3	TARJETA
            4	CHEQUE
            5	DEPOSITO
            6	TRANSFERENCIA    
            
             */
            for (FormaPago fp : listaForma) {

                switch (fp.getTipoFormaPago().getCodigo()) {

                    case 1:
                        detFt607.setEfectivo(fp.getMonto());

                        System.out.println("forma pago : " + fp.getTipoFormaPago().getDescripcion());
                        System.out.println("fp.getMonto() : " + fp.getMonto());
                        break;
                    case 2:
                        detFt607.setVentaACredito(fp.getMonto());
                        System.out.println("forma pago : " + fp.getTipoFormaPago().getDescripcion());
                        System.out.println("fp.getMonto() : " + fp.getMonto());
                        break;
                    case 3:
                        System.out.println("forma pago : " + fp.getTipoFormaPago().getDescripcion());
                        detFt607.setTarjetaDebitoCredito(fp.getMonto());
                        break;
                    case 4:
                        detFt607.setChqTransfDeposito(fp.getMonto());
                        break;
                    case 5:
                        detFt607.setChqTransfDeposito(fp.getMonto());
                        break;
                    case 6:
                        detFt607.setChqTransfDeposito(fp.getMonto());

                        break;
                    default:
                        detFt607.setOtrasFormaDeVenta(fp.getMonto());
                        detFt607.setPermuta(0.0);
                        detFt607.setBonosOCertificado(0.00);

                }

            }

            listaFactura.add(detFt607);

        }

        formato607.setCantidadRegistro(listaFactura.size());
        formato607.setFechaRegistro(new Date());

        txtCantRegistro.setText(Integer.toString(formato607.getCantidadRegistro()));

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

            Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("SEGURO QUE QUIERE GUARDAR EL FORMATO 607 ");

            if (ok.get() == ButtonType.OK || ok.get() == ButtonType.YES) {

                Formato607 formatoDb = ManejoFormato607.getInstancia().getFormato607(formato607.getAnio(), formato607.getMes());

                if (!(formatoDb == null)) {

                    ManejoFormato607.getInstancia().eliminarfORMATO607(formatoDb.getCodigo());
                   
                }
                
                formato607.setDetalleFormato607Collection(listaFactura);

                ManejoFormato607.getInstancia().salvar(formato607);

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
                Logger.getLogger(Formato607Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ClaseUtil.mensaje("No hay datos para exportar");
        }
    }

}
