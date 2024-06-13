/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.facturar;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.cliente.ManejoPlazo;
import manejo.contrato.ManejoContratoDeServicio;
import manejo.contrato.ManejoCorteDeFacturacion;
import manejo.contrato.ManejoFacturaDatosDeVehiculo;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoFacturaTemporal;
import manejo.factura.ManejoHistoricoBalancePendiente;
import manejo.factura.ManejoRelacionNcf;
import manejo.factura.ManejoTipoFormaPago;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.CondicionPago;
import modelo.ContratoDeServicio;
import modelo.CorteDeFacturacion;
import modelo.DatosDeVehiculo;
import modelo.DetalleContratoDeServicio;
import modelo.DetalleCorteDeFacturacion;
import modelo.DetalleFactura;
import modelo.DetalleFacturaTemporal;
import modelo.Factura;
import modelo.FacturaDatosDeVehiculo;
import modelo.FacturaTemporal;
import modelo.FormaPago;
import modelo.HistoricoBalancePendiente;
import modelo.MenuModulo;
import modelo.RelacionNcf;
import modelo.RolMenuModulo;
import modelo.SecuenciaDocumento;
import modelo.TipoFormaPago;
import modelo.Unidad;
import reporte.factura.RptFacturaIghTrack;
import utiles.ClaseUtil;
import utiles.FormatNum;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FacturasAnualesDeContratoController implements Initializable {

    @FXML
    private VBox vbVisorDeProgreso;
    @FXML
    private Label lbProgreso;
    @FXML
    private ProgressIndicator pgIndicador;
    @FXML
    private ProgressBar progresBar;
    @FXML
    private JFXButton btnBuscarContratoAnuales;
    @FXML
    private JFXButton btnGenerarFacturaAnuales;
    @FXML
    private JFXButton btnGuardarFacturaAnuales;
    @FXML
    private JFXTextField txtFiltroContrato;
    @FXML
    private JFXTextField txtFiltroFactura;

    /**
     * @return the codigoMenuModulo
     */
    public Integer getCodigoMenuModulo() {
        return codigoMenuModulo;
    }

    /**
     * @param codigoMenuModulo the codigoMenuModulo to set
     */
    public void setCodigoMenuModulo(Integer codigoMenuModulo) {
        this.codigoMenuModulo = codigoMenuModulo;
    }

    @FXML
    private JFXDatePicker dpFechaFacturacion;

    @FXML
    private TableView<ContratoDeServicio> tbContratoDeServicio;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcNumContratoContra;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcClienteContra;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcTipoDeContrato;
    @FXML
    private TableColumn<ContratoDeServicio, Double> tbcMontoContra;
    @FXML
    private Label lbCantidadContrato;
    @FXML
    private TableView<FacturaTemporal> tbFacturaDeContrato;
    @FXML
    private TableColumn<FacturaTemporal, String> tbcNoContratoFact;
    @FXML
    private TableColumn<FacturaTemporal, Integer> tbcFacturaFact;
    @FXML
    private TableColumn<FacturaTemporal, String> tbcClienteFact;
    @FXML
    private TableColumn<FacturaTemporal, Double> tbcMontoFact;
    @FXML
    private TableColumn<FacturaTemporal, String> tbcNcf;
    @FXML
    private TableColumn<FacturaTemporal, FacturaTemporal> tbcVerFactura;

    @FXML
    private Label lbCantidadFactura;

    ObservableList<ContratoDeServicio> listaContrato = FXCollections.observableArrayList();
    ObservableList<Factura> listaFactura = FXCollections.observableArrayList();

    ObservableList<DetalleFactura> listaDetalleFactura = FXCollections.observableArrayList();
    ObservableList<FacturaDatosDeVehiculo> listaDetalleFacDdatosVehiculo = FXCollections.observableArrayList();

    ObservableList<FacturaTemporal> listaFacturaTemporal = FXCollections.observableArrayList();

    ObservableList<DetalleFacturaTemporal> listaDetalleFacturaTemporal = FXCollections.observableArrayList();

    RelacionNcf relacionNcf = null;

    @FXML
    private HBox hbPermisos;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    private Integer codigoMenuModulo;
    TaskGenerarFacturaTemporales taskGenerarFacturaTemporales;
    TaskGenerarFactura taskGenerarFactura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablaContrato();
        inicializarTablaFactura();
        iniciazarFiltroFactura();
        iniciazarFiltroContrato();

        setCodigoMenuModulo(ManejoMenuModulo.getInstancia().getMenuModulo(9, "btnFacturasAnualesContrato"));
        agregarOpciones();
        activarOpciones();

        btnGenerarFacturaAnuales.setDisable(true);
        vbVisorDeProgreso.setVisible(false);
        dpFechaFacturacion.setValue(LocalDate.now());

//        dpFechaFacturacion.setValue(ClaseUtil.convertToLocalDateViaMilisecond(ClaseUtil.getFechaFacturacion()));
    }

    public void inicializarTablaContrato() {

        try {

            tbcNumContratoContra.setCellValueFactory(new PropertyValueFactory<>("numeroDeContrato"));
            tbcClienteContra.setCellValueFactory(new PropertyValueFactory<>("cliente"));

            tbcTipoDeContrato.setCellValueFactory(new PropertyValueFactory<>("frecuenciaDePago"));
            tbcMontoContra.setCellValueFactory(new PropertyValueFactory<>("totalAPagar"));

            tbcClienteContra.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getCliente() != null) {
                            property.setValue(cellData.getValue().getCliente().getNombre());
                        }
                        return property;
                    });

            tbcTipoDeContrato.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFrecuenciaDePago() != null) {
                            property.setValue(cellData.getValue().getFrecuenciaDePago().getFrecuencia());
                        }
                        return property;
                    });

            tbContratoDeServicio.setItems(listaContrato);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTablaFactura() {

        try {

            tbcNoContratoFact.setCellValueFactory(new PropertyValueFactory<>("numeroContrato"));
            tbcClienteFact.setCellValueFactory(new PropertyValueFactory<>("cliente"));
            tbcNcf.setCellValueFactory(new PropertyValueFactory<>("ncf"));
            tbcFacturaFact.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tbcMontoFact.setCellValueFactory(new PropertyValueFactory<>("total"));

            tbcClienteFact.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getCliente() != null) {
                            property.setValue(cellData.getValue().getCliente().getNombre());
                        }
                        return property;
                    });

            tbcVerFactura.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcVerFactura.setCellFactory((TableColumn<FacturaTemporal, FacturaTemporal> param) -> {

                TableCell<FacturaTemporal, FacturaTemporal> cellsc = new TableCell<FacturaTemporal, FacturaTemporal>() {
                    @Override
                    public void updateItem(FacturaTemporal item, boolean empty) {
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

                                RptFacturaIghTrack.getInstancia().verFacturaTemporal(item.getCodigo());

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

            tbFacturaDeContrato.setItems(listaFacturaTemporal);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciazarFiltroContrato() {

        FilteredList<ContratoDeServicio> filteredData = new FilteredList<>(tbContratoDeServicio.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltroContrato.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(contrato -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (contrato.getNombreCliente().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (contrato.getNumeroDeContrato().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (contrato.getFecha().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (contrato.getEjecutivoDeVenta().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (contrato.getPlanDeServicio().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ContratoDeServicio> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbContratoDeServicio.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbContratoDeServicio.setItems(sortedData);
    }

    private void iniciazarFiltroFactura() {

        FilteredList<FacturaTemporal> filteredData = new FilteredList<>(tbFacturaDeContrato.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltroFactura.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(factura -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (factura.getNombreCliente().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (factura.getNumeroContrato().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<FacturaTemporal> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbFacturaDeContrato.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbFacturaDeContrato.setItems(sortedData);
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        try {

            if (dpFechaFacturacion.getValue() == null) {

                ClaseUtil.mensaje(" Tiene que seleccionar una fecha ");
                return;
            }

//            Date fecha = ClaseUtil.asDate(dpFechaFacturacion.getValue());
//
//            if (ManejoCorteDeFacturacion.getInstancia().existeCortedeMensualDeFacturacion(fecha)) {
//
//                ClaseUtil.mensaje("Existe un corte de facturacion en esta fecha");
//
//                return;
//            }
            listaContrato.clear();
            lbCantidadContrato.setText("");
            listaContrato.addAll(ManejoContratoDeServicio.getInstancia()
                    .getContratoVencido(ClaseUtil.asDate(dpFechaFacturacion.getValue()), 1));//contrato activo y no facturado en la fecha de corte

            lbCantidadContrato.setText(cantidadContrato().toString());

            if (listaContrato.size() <= 0) {

                btnGenerarFacturaAnuales.setDisable(true);
                ClaseUtil.mensaje("No hay  contrato para generarle factura en esta fecha ");

            } else {

                for (ContratoDeServicio contrato : listaContrato) {

                    if (contrato.getCliente().getTipoNcf() == null) {
                        ClaseUtil.mensaje("EL CLIENTE " + contrato.getCliente().getNombre()
                                + " DEL CONTRATO " + contrato.getNumero() + " TIENE QUE CONFIGUARLE EL TIPO DE FACTURACION ");

                        return;
                    }
                }

                btnGenerarFacturaAnuales.setDisable(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {

        if (dpFechaFacturacion.getValue() == null) {

            ClaseUtil.mensaje("Tiene que seleccionar una fecha");
            return;
        }

        try {

            for (ContratoDeServicio contrato : listaContrato) {

                if (contrato.getCliente().getTipoNcf() == null) {
                    ClaseUtil.mensaje("EL CLIENTE " + contrato.getCliente().getNombre()
                            + " DEL CONTRATO " + contrato.getNumero() + " TIENE QUE CONFIGUARLE EL TIPO DE FACTURACION ");
                    listaFacturaTemporal.clear();
                    return;
                }
            }

            ManejoFacturaTemporal.getInstancia().eliminarFacturaTemporal();

            listaFacturaTemporal.clear();

            for (ContratoDeServicio contrato : listaContrato) {

                generarFactTemporales(contrato);

//                tareFacturaTemporal();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void tbContratoDeServicioClicked(MouseEvent event) {
    }

    private Integer cantidadContrato() {

        return listaContrato.size();
    }

    private Integer cantidadFactura() {

        return listaFacturaTemporal.size();
    }

    @FXML
    private void tbFacturaDeContratoMouseClicked(MouseEvent event) {

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (listaFacturaTemporal.size() <= 0) {

                ClaseUtil.mensaje("No hay factura para guardar");
                return;
            }

            Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("Seguro que quiere guardar esta facturas ");

            if (ok.get() == ButtonType.OK) {

                for (ContratoDeServicio contrato : listaContrato) {

                    generarFactura(contrato);

                }

                listaContrato.clear();
                listaFacturaTemporal.clear();
                ManejoFacturaTemporal.getInstancia().eliminarFacturaTemporal();

                ClaseUtil.mensaje("Factura guardada exitosamente ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//
//            if (listaFacturaTemporal.size() <= 0) {
//
//                ClaseUtil.mensaje("No hay factura para guardar");
//                return;
//            }
//
//            CorteDeFacturacion corteFact = new CorteDeFacturacion();
//            DetalleCorteDeFacturacion detaCorte;
//            List<DetalleCorteDeFacturacion> listaDetCorte = new ArrayList<>();
//            Factura factura;
//            DetalleFactura detalleFactura;
//            FacturaDatosDeVehiculo facturaDatosDeVehiculo;
//
//            List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();
//            List<DatosDeVehiculo> listaDetVehiculo = new ArrayList<>();
//
//            corteFact.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
//            corteFact.setFechaRegistro(new Date());
//            corteFact.setUsuario(VariablesGlobales.USUARIO);
//
//            int num = 0;
//
//            for (ContratoDeServicio contrato : listaContrato) {
//
//                factura = new Factura();
//
//                //****************************************
//                System.out.println("contrato.getCliente() " + contrato.getCliente().getTipoNcf());
//                factura.setAbono(0.00);
//                factura.setCliente(contrato.getCliente());
//                factura.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
//                factura.setMonto(contrato.getTotalAPagar());
//                factura.setAnulada(false);
//                factura.setNumeroContrato(contrato.getNumeroDeContrato());
//                factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
//
//                SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
//                        .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
//                factura.setNumero(secDoc.getNumero() + num);
//
//                factura.setNcf("na");
//                factura.setTotal(factura.getMonto());
//                factura.setSubTotal(contrato.getSubTotal());
//                factura.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
//                factura.setDescuento(0.00);
//                factura.setItbis(contrato.getItbis());
//                factura.setUsuario(VariablesGlobales.USUARIO);
//                factura.setVendedor(contrato.getEjecutivoDeVenta());
//
//                factura.setFechaCreacion(new Date());
//
//                Date fechaVencimiento = ClaseUtil.asDate(dpFechaFacturacion.getValue());
//                factura.setFechaVencimiento(fechaVencimiento);
//
//                factura.setPagada(false);
//                factura.setTipoVenta(2);
//                factura.setAbono(0.0);
//                factura.setPendiente(factura.getTotal());
//                factura.setCondicionPago(new CondicionPago(2));
//                factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
//                fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, factura.getCliente().getDiasCredito());
//                factura.setFechaVencimiento(fechaVencimiento);
//                factura.setDespachada(false);
//
//                FormaPago formaPago = new FormaPago();
//                TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
//                formaPago.setTipoFormaPago(tipoFormaPago);
//                formaPago.setMonto(factura.getTotal());
//
//                //agregando detalle a la factura
//                listaDetContrato.clear();
//                listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
//                        .getDetalleContratoVencido(contrato.getCodigo(), fechaVencimiento));
//
//                System.out.println("NO HUBO CLIENTE CON NCF NULO ");
//
//                Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;
//
//                listaDetalleFactura.clear();
//                listaDetalleFacDdatosVehiculo.clear();
//                for (DetalleContratoDeServicio detCto : listaDetContrato) {
//
//                    System.out.println("detCto.getEquipo().getTipoArticulo().getCodigo() " + detCto.getEquipo().getTipoArticulo().getCodigo());
////                    if (detCto.getEquipo().getTipoArticulo().getCodigo() == 3) {//solo se creanran factura por servicio 
//
//                    detalleFactura = new DetalleFactura();
//                    // *****************************************
//
//                    long dias = ClaseUtil.diferenciaDiasEntreDosFecha(detCto.getFechaUltimoPagoHasta(), fechaVencimiento);
//
//                    Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();
//
//                    int diasInt = Integer.parseInt(Long.toString(dias));
//
//                    if (dias < 30) {
//
//                        System.out.println("dias MENOR A 30" + dias);
//                        Double precioPorDia = ClaseUtil.roundDouble(detCto.getPrecioPagado() / 30, 2);
//                        precioPorDia = precioPorDia * dias;
//                        detalleFactura.setPrecio(precioPorDia);
//
//                    } else {
//
//                        System.out.println("dias mayor de 30 " + dias);
//                        detalleFactura.setPrecio(detCto.getPrecioPagado());
//                    }
//
//                    int cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();
//                    int cantidadGPS = detCto.getCantidad();
//                    String desc = "";
//                    Date fechaVencimientoCal = null;
//
//                    if (dias < 30) {
//
//                        fechaVencimientoCal = ClaseUtil.Fechadiadespues(detCto.getFechaUltimoPagoHasta(), diasInt);//Calculamos la fecha de vencimiento
//
//                    } else {
//
//                        fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadFrecuenciaPago);//Calculamos la fecha de vencimiento
//                        // en base a la cantidad de pago por adelantado
//                    }
//
//                    detCto.setFechaUltimoPagoDesde(detCto.getFechaUltimoPagoHasta());//Factura  que cubre el pago desde esta fecha hasta la fecha calculada
//
//                    detCto.setFechaUltimoPagoHasta(fechaVencimientoCal); //fechaVencimientoCal   a hora es el setFechaUltimoPagoHasta    
//
//                    desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
//                            + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoDesde())
//                            + " AL  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta());
//
//                    System.out.println("descripcion pago " + desc);
//                    detalleFactura.setDescripcionPago(desc);
//                    detalleFactura.setFactura(factura);
//                    detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
//                    detalleFactura.setArticulo(detCto.getEquipo());
//                    cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();//CAntidad facturado
//
//                    detalleFactura.setCantidad(cantidad);
//
//                    subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
//                    detalleFactura.setSubTotal(subTotal);
//
//                    valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);
//
//                    detalleFactura.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));
//
//                    detalleFactura.setDescuento(0.00);
//                    detalleFactura.setExistencia(0.00);
//                    detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
//                    detalleFactura.setNuevaExistencia(0.0);
//
//                    detalleFactura.setItbis(valorItbis);
//                    detalleFactura.setTasaItbis(detCto.getTasaItbis());
//
//                    detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
//                    detalleFactura.setUnidad(unidad);
//
//                    //agregando detalle a la factura de los vehiculos 
//                    listaDetVehiculo.clear();
//                    listaDetVehiculo.addAll(ManejoContratoDeServicio.getInstancia()
//                            .getDatosVehiculoo(detCto.getCodigo()));
//
//                    System.out.println(" datos vehiculo " + listaDetVehiculo.size() + " detCto.getCodigo() " + detCto.getCodigo());
//
//                    for (DatosDeVehiculo detVeh : listaDetVehiculo) {
//
//                        System.out.println("configurando  factura datos de vehiculo ");
//                        facturaDatosDeVehiculo = new FacturaDatosDeVehiculo();
//                        // *****************************************
//
//                        facturaDatosDeVehiculo.setFactura(detalleFactura);
//                        facturaDatosDeVehiculo.setAdicional(detVeh.getAdicional());
//                        facturaDatosDeVehiculo.setAnio(detVeh.getAnio());
//                        facturaDatosDeVehiculo.setChasis(detVeh.getChasis());
//                        facturaDatosDeVehiculo.setColor(detVeh.getColor());
//                        facturaDatosDeVehiculo.setMarca(detVeh.getMarca());
//                        facturaDatosDeVehiculo.setMatricula(detVeh.getMatricula());
//                        facturaDatosDeVehiculo.setModelo(detVeh.getModelo());
//                        facturaDatosDeVehiculo.setPlaca(detVeh.getPlaca());
//                        facturaDatosDeVehiculo.setTipoVehiculo(detVeh.getTipoVehiculo());
//                        facturaDatosDeVehiculo.setVehiculo(detVeh.getVehiculo());
//
//                        listaDetalleFacDdatosVehiculo.add(facturaDatosDeVehiculo);
//
//                    }
//
////                        detalleFactura.setFacturaDatosDeVehiculoCollection(listaDetalleFacDdatosVehiculo);
//                    listaDetalleFactura.add(detalleFactura);
//
//                }
//
//                try {
//
//                    System.out.println(" listaDetalleFactura " + listaDetalleFactura + " listaDetalleFactura.size() " + listaDetalleFactura.size());
//
//                    if (listaDetalleFactura.size() > 0) {
//
//                        factura.setTotal(getTotal(listaDetalleFactura));
//                        factura.setSubTotal(getSubTotal(listaDetalleFactura));
//                        factura.setItbis(getItbis(listaDetalleFactura));
//                        factura.setMonto(getTotal(listaDetalleFactura));
//                        factura.setPendiente(getTotal(listaDetalleFactura));
//
//                        factura.setDetalleFacturaCollection(listaDetalleFactura);
//
//                        Double balancePendiente = ManejoFactura.getInstancia().getMontoPendiente(factura.getCliente());
//
//                        Factura factDb = guardar(factura);
//
//                        if (factDb == null) {
//
//                            ClaseUtil.mensaje(" Hubo Error creando la factura ");
//                            return;
//
//                        } else {
//
//                            listaDetalleFacDdatosVehiculo.forEach((fctdvh) -> {
//                                System.out.println("item " + fctdvh);
//                                ManejoFacturaDatosDeVehiculo.getInstancia().salvar(fctdvh);
//                            });
//                            //Actualizar el ncf 
//                            ManejoRelacionNcf.getInstancia().actualizar(relacionNcf);
//
//                            //Generando el historico de corte de facturacion 
//                            detaCorte = new DetalleCorteDeFacturacion();
//
//                            detaCorte.setFactura(factura);
//                            detaCorte.setNumeroFactura(factura.getNumero());
//                            detaCorte.setContrato(contrato);
//                            detaCorte.setNumeroContrato(contrato.getNumeroDeContrato());
//                            detaCorte.setCorteDeFacturacion(corteFact);
//                            detaCorte.setTotalFactura(factura.getTotal());
//                            listaDetCorte.add(detaCorte);
//
//                            contrato.setDetalleContratoDeServicioCollection(listaDetContrato);
//                            ManejoContratoDeServicio.getInstancia().actualizar(contrato);
//
//                            HistoricoBalancePendiente hBalance = new HistoricoBalancePendiente();
//
//                            hBalance.setCliente(factura.getCliente().getCodigo());
//                            hBalance.setFactura(factura.getCodigo());
//                            hBalance.setFechaVencimiento(factura.getFecha());
//                            hBalance.setTotal(balancePendiente);
//
//                            ManejoHistoricoBalancePendiente.getInstancia().salvar(hBalance);
//
//                        }
//                    }
//
//                } catch (Exception e) {
//
//                    e.printStackTrace();
//                }
//
//                num++;
//
//            }
//
//            corteFact.setCantidadFactura(num);
//            corteFact.setDetalleCorteDeFacturacionCollection(listaDetCorte);
//
//            ManejoCorteDeFacturacion.getInstancia().salvar(corteFact);
//
//            listaFactura.clear();
//            listaContrato.clear();
//            listaDetCorte.clear();
//            lbCantidadContrato.setText("");
//            lbCantidadFactura.setText("");
//
////            ClaseUtil.mensaje("Facturas guarda exitosamente");
//        } catch (Exception e) {
//
//            ClaseUtil.mensaje("Hubo un error guardando la factura ");
//            e.printStackTrace();
//        }
    }

    private Factura guardar(Factura fac) {

        Factura facturaDb = null;

        try {

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(fac.getUnidadDeNegocio().getCodigo(), 7);

            if (!(secDoc == null)) {

                boolean existe = ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero());

                if (existe) {

                    System.out.println("existe " + secDoc.getNumero());

                    while (ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero())) {

                        secDoc.setNumero(secDoc.getNumero() + 1);
                        ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                    }

                    secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(fac.getUnidadDeNegocio().getCodigo(), 7);

                    fac.setNumero(secDoc.getNumero());
                    fac.setSecuenciaDocumento(secDoc);

                } else {

                    System.out.println("No existe " + secDoc.getNumero());
                    fac.setNumero(secDoc.getNumero());
                    fac.setSecuenciaDocumento(secDoc);
                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

            } else {

                ClaseUtil.mensaje("La secuencia para la factura de la unidad de negocio "
                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return facturaDb;
            }

            System.out.println("");
            fac.setTipoNcf(fac.getCliente().getTipoNcf());//ponemos el tipo de ncf
//                factura.setTipoNcf(cbTipoNcf.getSelectionModel().getSelectedItem());

            System.out.println("fac.getCliente().getTipoNcf() " + fac.getCliente().getTipoNcf() + " fac.getTipoNcf() " + fac.getTipoNcf());

            relacionNcf = ManejoRelacionNcf.getInstancia()
                    .getNCFUnidadDeNegocio(fac.getCliente().getTipoNcf(), fac.getUnidadDeNegocio().getCodigo());

            if (relacionNcf == null) {//SI ES IGUAL A NULL LA UNIDAD DE NEGOCIO NO TIENE COMPROBANTE FISCALES CONFIGURADO

                System.out.println(" EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL  " + relacionNcf);
//
//                ClaseUtil.mensaje(" La secuencia de ncf para el tipo " + fac.getCliente().getTipoNcf().getDescripcion() + " de la unidad de negocio "
//                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");

//                System.out.println("relacionNcf  " + relacionNcf);
                relacionNcf = ManejoRelacionNcf.getInstancia()
                        .getNCFUnidadDeNegocio(fac.getCliente().getTipoNcf(), 3);//EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL
                System.out.println(" EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL  " + relacionNcf);

//               return null;
            }

            System.out.println("relacionNcf  " + relacionNcf);
            boolean existe = ManejoFactura.getInstancia().existeNCF(relacionNcf.getActual());

            if (existe) {

                while (ManejoFactura.getInstancia().existeNCF(relacionNcf.getActual())) {

                    relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo());

                }

            } else {

                relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo());

            }

            fac.setNcf(relacionNcf.getActual());
            fac.setNcfFechaValidoHasta(relacionNcf.getFechaValidoHasta());
            fac.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            System.out.println("Sin ncf");

            facturaDb = ManejoFactura.getInstancia().salvar(fac);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return facturaDb;
    }

    private Double getSubTotal(List<DetalleFactura> lista) {

        Double subTotal = 0.00;

        for (DetalleFactura det : lista) {

            subTotal += det.getSubTotal();

        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotal(List<DetalleFactura> lista) {

        Double total = 0.00;

        for (DetalleFactura det : lista) {

            total += det.getTotal();

        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getItbis(List<DetalleFactura> lista) {

        Double itbis = 0.00;

        for (DetalleFactura det : lista) {

            itbis += det.getItbis();

        }

        return FormatNum.FormatearDouble(itbis, 2);
    }

    private void btnExportarPdfActionEvent(ActionEvent event) {

        try {

            Date fecha = ClaseUtil.asDate(dpFechaFacturacion.getValue());
            ManejoFactura.getInstancia().enviarFacturaPorCorreoEnPdf(fecha);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Agrega las opciones de menu a la base de datos
    private void agregarOpciones() {

        MenuModulo menuModulo;

        List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(9);
        int codigoModulo = ManejoModulo.getInstancia().getModulo(9).getCodigo();
        String nombreModulo = ManejoModulo.getInstancia().getModulo(9).getNombre();

        boolean actualizando = false;

        for (Node n : hbPermisos.getChildren()) {

            System.out.println("n.getId() " + n.getId());
            if (!(n.getId() == null)) {

                System.out.println("n.getId() no es null " + n.getId());
                menuModulo = new MenuModulo();

                menuModulo.setIdMenu(n.getId());
                menuModulo.setMenu(n.getAccessibleText());
                menuModulo.setModulo(codigoModulo);
                menuModulo.setNombreModulo(nombreModulo);
                menuModulo.setPermiso(getCodigoMenuModulo());
//                menuModulo.setTipoMenu(ManejoTipoMenu.getInstancia().getTipoMenu(codigoModulo));

                for (MenuModulo memu : listaMenuModulo) {

                    if (n.getId().equals(memu.getIdMenu())) {

                        menuModulo = memu;
                        actualizando = true;
                        break;
                    }
                }

                if (actualizando) {

                    ManejoMenuModulo.getInstancia().actualizar(menuModulo);

                } else {

                    ManejoMenuModulo.getInstancia().salvar(menuModulo);
                }

            }

        }
    }

    private void activarOpciones() {

        if (codigoRol == 1) {//rol de administrador

            for (Node n : hbPermisos.getChildren()) {
                n.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, 9);

            for (Node n : hbPermisos.getChildren()) {

                if (!(n.getId() == null)) {

                    for (RolMenuModulo memu : listaRolMenuModulos) {

                        if (n.getId().equals(memu.getMenuModulo().getIdMenu())) {
                            n.setDisable(!memu.getHabilitado());

                        }
                    }

                }

            }
        }

    }
//
//    private void generarFactTemporales() {
//
//        try {
//
//            CorteDeFacturacion corteFact = new CorteDeFacturacion();
//
//            FacturaTemporal factura;
//            DetalleFacturaTemporal detalleFactura;
//
//            List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();
//
//            corteFact.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
//            corteFact.setFechaRegistro(new Date());
//            corteFact.setUsuario(VariablesGlobales.USUARIO);
//
//            int num = 0;
//
//            vbVisorDeProgreso.setVisible(true);
//            for (ContratoDeServicio contrato : listaContrato) {
//
//                factura = new FacturaTemporal();
//
//                //****************************************
//                System.out.println("contrato.getCliente() " + contrato.getCliente().getTipoNcf());
//                factura.setAbono(0.00);
//                factura.setCliente(contrato.getCliente());
//                factura.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
//                factura.setMonto(contrato.getTotalAPagar());
//                factura.setAnulada(false);
//                factura.setNumeroContrato(contrato.getNumeroDeContrato());
//                factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
//
//                SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
//                        .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
//                factura.setNumero(secDoc.getNumero() + num);
//
//                factura.setNcf("na");
//                factura.setTotal(factura.getMonto());
//                factura.setSubTotal(contrato.getSubTotal());
//                factura.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
//                factura.setDescuento(0.00);
//                factura.setItbis(contrato.getItbis());
//                factura.setUsuario(VariablesGlobales.USUARIO);
//                factura.setVendedor(contrato.getEjecutivoDeVenta());
//
//                factura.setFechaCreacion(new Date());
//
//                Date fechaVencimiento = ClaseUtil.asDate(dpFechaFacturacion.getValue());
//                factura.setFechaVencimiento(fechaVencimiento);
//
//                factura.setPagada(false);
//                factura.setTipoVenta(2);
//                factura.setAbono(0.0);
//                factura.setPendiente(factura.getTotal());
//                factura.setCondicionPago(new CondicionPago(2));
//                factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
//                fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, factura.getCliente().getDiasCredito());
//                factura.setFechaVencimiento(fechaVencimiento);
//                factura.setDespachada(false);
//
//                FormaPago formaPago = new FormaPago();
//                TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
//                formaPago.setTipoFormaPago(tipoFormaPago);
//                formaPago.setMonto(factura.getTotal());
//
//                //agregando detalle a la factura
//                listaDetContrato.clear();
//                listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
//                        .getDetalleContratoVencido(contrato.getCodigo(), fechaVencimiento));
//
//                System.out.println("NO HUBO CLIENTE CON NCF NULO ");
//
//                Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;
//
//                listaDetalleFacturaTemporal.clear();
//                listaDetalleFacDdatosVehiculo.clear();
//                for (DetalleContratoDeServicio detCto : listaDetContrato) {
//
//                    System.out.println("detCto.getEquipo().getTipoArticulo().getCodigo() " + detCto.getEquipo().getTipoArticulo().getCodigo());
////                    if (detCto.getEquipo().getTipoArticulo().getCodigo() == 3) {//solo se creanran factura por servicio 
//
//                    detalleFactura = new DetalleFacturaTemporal();
//                    // *****************************************
//
//                    long dias = ClaseUtil.diferenciaDiasEntreDosFecha(detCto.getFechaUltimoPagoHasta(), fechaVencimiento);
//
//                    Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();
//
//                    int diasInt = Integer.parseInt(Long.toString(dias));
//
//                    if (dias < 30) {
//
//                        System.out.println("dias MENOR A 30" + dias);
//                        Double precioPorDia = ClaseUtil.roundDouble(detCto.getPrecioPagado() / 30, 2);
//                        precioPorDia = precioPorDia * dias;
//                        detalleFactura.setPrecio(precioPorDia);
//
//                    } else {
//
//                        System.out.println("dias mayor de 30 " + dias);
//                        detalleFactura.setPrecio(detCto.getPrecioPagado());
//                    }
//
//                    int cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();
//                    int cantidadGPS = detCto.getCantidad();
//                    String desc = "";
//                    Date fechaVencimientoCal = null;
//
//                    if (dias < 30) {
//
//                        fechaVencimientoCal = ClaseUtil.Fechadiadespues(detCto.getFechaUltimoPagoHasta(), diasInt);//Calculamos la fecha de vencimiento
//
//                    } else {
//
//                        fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadFrecuenciaPago);//Calculamos la fecha de vencimiento
//                        // en base a la cantidad de pago por adelantado
//                    }
//
//                    detCto.setFechaUltimoPagoDesde(detCto.getFechaUltimoPagoHasta());//Factura  que cubre el pago desde esta fecha hasta la fecha calculada
//
//                    detCto.setFechaUltimoPagoHasta(fechaVencimientoCal); //fechaVencimientoCal   a hora es el setFechaUltimoPagoHasta    
//
//                    desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
//                            + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoDesde())
//                            + " AL  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta());
//
//                    System.out.println("descripcion pago " + desc);
//                    detalleFactura.setDescripcionPago(desc);
//                    detalleFactura.setFactura(factura);
//                    detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
//                    detalleFactura.setArticulo(detCto.getEquipo());
//                    cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();//CAntidad facturado
//
//                    detalleFactura.setCantidad(cantidad);
//
//                    subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
//                    detalleFactura.setSubTotal(subTotal);
//
//                    valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);
//
//                    detalleFactura.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));
//
//                    detalleFactura.setDescuento(0.00);
//                    detalleFactura.setExistencia(0.00);
//                    detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
//                    detalleFactura.setNuevaExistencia(0.0);
//
//                    detalleFactura.setItbis(valorItbis);
//                    detalleFactura.setTasaItbis(detCto.getTasaItbis());
//
//                    detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
//                    detalleFactura.setUnidad(unidad);
//
//                    listaDetalleFacturaTemporal.add(detalleFactura);
//                }
//
//                try {
//
//                    System.out.println(" listaDetalleFactura " + listaDetalleFacturaTemporal + " listaDetalleFactura.size() " + listaDetalleFacturaTemporal.size());
//
//                    if (listaDetalleFacturaTemporal.size() > 0) {
//
//                        factura.setTotal(getTotalTemp(listaDetalleFacturaTemporal));
//                        factura.setSubTotal(getSubTotalTewmp(listaDetalleFacturaTemporal));
//                        factura.setItbis(getItbisTemp(listaDetalleFacturaTemporal));
//                        factura.setMonto(getTotalTemp(listaDetalleFacturaTemporal));
//                        factura.setPendiente(getTotalTemp(listaDetalleFacturaTemporal));
//
//                        factura.setDetalleFacturaTemporalCollection(listaDetalleFacturaTemporal);
//
//                        FacturaTemporal factDb = guardarFacturaTempotal(factura);
//
//                        listaFacturaTemporal.add(factDb);
//
//                        if (factDb == null) {
//
//                            ClaseUtil.mensaje(" Hubo Error creando la factura ");
//                            return;
//
//                        }
//                    }
//
//                } catch (Exception e) {
//
//                    e.printStackTrace();
//                }
//
//                num++;
//
//            }
//
//            listaContrato.clear();
//            lbCantidadContrato.setText("");
//            lbCantidadFactura.setText("");//
//            lbCantidadFactura.setText(cantidadFactura().toString());
//            vbVisorDeProgreso.setVisible(false);
//
//        } catch (Exception e) {
//
//            ClaseUtil.mensaje("Hubo un error guardando la factura ");
//            e.printStackTrace();
//        }
//
//    }

    private Double getSubTotalTewmp(List<DetalleFacturaTemporal> lista) {

        Double subTotal = 0.00;

        for (DetalleFacturaTemporal det : lista) {

            subTotal += det.getSubTotal();

        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotalTemp(List<DetalleFacturaTemporal> lista) {

        Double total = 0.00;

        for (DetalleFacturaTemporal det : lista) {

            total += det.getTotal();

        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getItbisTemp(List<DetalleFacturaTemporal> lista) {

        Double itbis = 0.00;

        for (DetalleFacturaTemporal det : lista) {

            itbis += det.getItbis();

        }

        return FormatNum.FormatearDouble(itbis, 2);
    }

    private FacturaTemporal guardarFacturaTempotal(FacturaTemporal fac) {

        FacturaTemporal facturaDb = null;

        try {

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(fac.getUnidadDeNegocio().getCodigo(), 7);

            if (!(secDoc == null)) {

                fac.setNumero(secDoc.getNumero());
                fac.setSecuenciaDocumento(secDoc);

            } else {

                ClaseUtil.mensaje("La secuencia para la factura de la unidad de negocio "
                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return facturaDb;
            }

            System.out.println("");
            fac.setTipoNcf(fac.getCliente().getTipoNcf());//ponemos el tipo de ncf

            System.out.println("fac.getCliente().getTipoNcf() " + fac.getCliente().getTipoNcf() + " fac.getTipoNcf() " + fac.getTipoNcf());

//            relacionNcf = ManejoRelacionNcf.getInstancia()
//                    .getNCFUnidadDeNegocio(fac.getCliente().getTipoNcf(), fac.getUnidadDeNegocio().getCodigo());
//
//            if (relacionNcf == null) {//SI ES IGUAL A NULL LA UNIDAD DE NEGOCIO NO TIENE COMPROBANTE FISCALES CONFIGURADO
//
//                System.out.println(" EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL  " + relacionNcf);
////
////                ClaseUtil.mensaje(" La secuencia de ncf para el tipo " + fac.getCliente().getTipoNcf().getDescripcion() + " de la unidad de negocio "
////                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
//
////                System.out.println("relacionNcf  " + relacionNcf);
//                relacionNcf = ManejoRelacionNcf.getInstancia()
//                        .getNCFUnidadDeNegocio(fac.getCliente().getTipoNcf(), 3);//EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL
//                System.out.println(" EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL  " + relacionNcf);
//
////               return null;
//            }
            fac.setNcf("B0000001");
            fac.setNcfFechaValidoHasta(new Date());
            fac.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            System.out.println("Sin ncf");

            facturaDb = ManejoFacturaTemporal.getInstancia().salvar(fac);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return facturaDb;
    }

    private void tareFacturaTemporal() {

        try {

            btnGenerarFacturaAnuales.setDisable(true);
            progresBar.setProgress(0);
            pgIndicador.setProgress(0);
            pgIndicador.setVisible(true);
            progresBar.setVisible(true);

            taskGenerarFacturaTemporales = new TaskGenerarFacturaTemporales();

            progresBar.progressProperty().unbind();

            progresBar.progressProperty().bind(taskGenerarFacturaTemporales.progressProperty());

            pgIndicador.progressProperty().unbind();

            pgIndicador.progressProperty().bind(taskGenerarFacturaTemporales.progressProperty());
//
            lbProgreso.textProperty().unbind();
            lbProgreso.textProperty().bind(taskGenerarFacturaTemporales.messageProperty());

            taskGenerarFacturaTemporales.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent t) -> {

                lbProgreso.textProperty().unbind();

                vbVisorDeProgreso.setVisible(false);
                lbCantidadFactura.setText(Integer.toString(listaFacturaTemporal.size()));

                taskGenerarFacturaTemporales.cancel();

                progresBar.progressProperty().unbind();

                pgIndicador.progressProperty().unbind();

            });

            // Start the Task.
            new Thread(taskGenerarFacturaTemporales).start();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private void tareFactura() {

        try {

            vbVisorDeProgreso.setVisible(true);

            progresBar.setProgress(0);
            pgIndicador.setProgress(0);
            pgIndicador.setVisible(true);
            progresBar.setVisible(true);

            taskGenerarFactura = new TaskGenerarFactura();

            progresBar.progressProperty().unbind();

            progresBar.progressProperty().bind(taskGenerarFactura.progressProperty());

            pgIndicador.progressProperty().unbind();

            pgIndicador.progressProperty().bind(taskGenerarFactura.progressProperty());
//
            lbProgreso.textProperty().unbind();
            lbProgreso.textProperty().bind(taskGenerarFactura.messageProperty());

            taskGenerarFactura.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent t) -> {

                lbProgreso.textProperty().unbind();

                vbVisorDeProgreso.setVisible(false);
                lbCantidadFactura.setText(Integer.toString(listaFactura.size()));

                taskGenerarFactura.cancel();

                progresBar.progressProperty().unbind();

                pgIndicador.progressProperty().unbind();
                vbVisorDeProgreso.setVisible(false);
                lbCantidadContrato.setText("");

                ClaseUtil.mensaje("Factura guardada correctamente");

            });

            // Start the Task.
            new Thread(taskGenerarFactura).start();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private class TaskGenerarFacturaTemporales extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            try {

                listaFacturaTemporal.clear();
                int total = listaContrato.size();
                int i = 0;

                for (ContratoDeServicio contrato : listaContrato) {
                    i++;
                    generarFactTemporales(contrato, i, total);
                    this.updateProgress(i, total);
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;

        }

        private void generarFactTemporales(ContratoDeServicio contrato, int i, int total) throws InterruptedException {

            try {

                CorteDeFacturacion corteFact = new CorteDeFacturacion();

                FacturaTemporal factura;
                DetalleFacturaTemporal detalleFactura;

                List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();

                corteFact.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
                corteFact.setFechaRegistro(new Date());
                corteFact.setUsuario(VariablesGlobales.USUARIO);

                int num = 0;

                vbVisorDeProgreso.setVisible(true);

                factura = new FacturaTemporal();

                //****************************************
                System.out.println("contrato.getCliente() " + contrato.getCliente().getTipoNcf());
                factura.setAbono(0.00);
                factura.setCliente(contrato.getCliente());
                factura.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
                factura.setMonto(contrato.getTotalAPagar());
                factura.setAnulada(false);
                factura.setNumeroContrato(contrato.getNumeroDeContrato());
                factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

                SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                        .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
                factura.setNumero(secDoc.getNumero() + num);

                factura.setNcf("na");
                factura.setTotal(factura.getMonto());
                factura.setSubTotal(contrato.getSubTotal());
                factura.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
                factura.setDescuento(0.00);
                factura.setItbis(contrato.getItbis());
                factura.setUsuario(VariablesGlobales.USUARIO);
                factura.setVendedor(contrato.getEjecutivoDeVenta());

                factura.setFechaCreacion(new Date());

                Date fechaVencimiento = ClaseUtil.asDate(dpFechaFacturacion.getValue());
                factura.setFechaVencimiento(fechaVencimiento);

                factura.setPagada(false);
                factura.setTipoVenta(2);
                factura.setAbono(0.0);
                factura.setPendiente(factura.getTotal());
                factura.setCondicionPago(new CondicionPago(2));
                factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
                fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, factura.getCliente().getDiasCredito());
                factura.setFechaVencimiento(fechaVencimiento);
                factura.setDespachada(false);

                FormaPago formaPago = new FormaPago();
                TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
                formaPago.setTipoFormaPago(tipoFormaPago);
                formaPago.setMonto(factura.getTotal());

                //agregando detalle a la factura
                listaDetContrato.clear();
                listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
                        .getDetalleContratoVencido(contrato.getCodigo(), fechaVencimiento));

                System.out.println("NO HUBO CLIENTE CON NCF NULO ");

                Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;

                listaDetalleFacturaTemporal.clear();
                listaDetalleFacDdatosVehiculo.clear();
                for (DetalleContratoDeServicio detCto : listaDetContrato) {

                    System.out.println("detCto.getEquipo().getTipoArticulo().getCodigo() " + detCto.getEquipo().getTipoArticulo().getCodigo());
//                    if (detCto.getEquipo().getTipoArticulo().getCodigo() == 3) {//solo se creanran factura por servicio 

                    detalleFactura = new DetalleFacturaTemporal();
                    // *****************************************

//                    long dias = ClaseUtil.diferenciaDiasEntreDosFecha(detCto.getFechaUltimoPagoHasta(), fechaVencimiento);
                    Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();

//                    int diasInt = Integer.parseInt(Long.toString(dias));
//
//                    if (dias < 30) {
//
//                        System.out.println("dias MENOR A 30" + dias + " contrato " + contrato.getNumeroDeContrato());
//                        Double precioPorDia = ClaseUtil.roundDouble(detCto.getPrecioPagado() / 30, 2);
//                        precioPorDia = precioPorDia * dias;
//                        detalleFactura.setPrecio(precioPorDia);
//
//                    } else {
//
//                        System.out.println("dias mayor de 30 " + dias + " contrato " + contrato.getNumeroDeContrato());
//                        detalleFactura.setPrecio(detCto.getPrecioPagado());
//                    }
                    int cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();
//
//                    if (contrato.getFrecuenciaDePago().getCodigo() == 1) {
                    cantidadFrecuenciaPago *= 12;
//                    }

                    int cantidadGPS = detCto.getCantidad();
                    String desc = "";
                    Date fechaVencimientoCal = null;

//                    if (dias < 30) {
//
//                        System.out.println("dias menor de 30 " + dias);
//                        fechaVencimientoCal = ClaseUtil.Fechadiadespues(detCto.getFechaUltimoPagoHasta(), diasInt);//Calculamos la fecha de vencimiento
//
//                    } else {
//                    System.out.println("dias mayor de 30 " + dias);
                    fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadFrecuenciaPago);//Calculamos la fecha de vencimiento
                    // en base a la cantidad de pago por a delantado

                    System.out.println("fechaVencimientoCal " + fechaVencimientoCal);
//                    }

//                    detCto.setFechaUltimoPagoDesde(detCto.getFechaUltimoPagoHasta());//Factura  que cubre el pago desde esta fecha hasta la fecha calculada
//
//                    detCto.setFechaUltimoPagoHasta(fechaVencimientoCal); //fechaVencimientoCal   a hora es el setFechaUltimoPagoHasta    
                    desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                            + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                            + " AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);

                    System.out.println("descripcion pago " + desc);

                    detalleFactura.setDescripcionPago(desc);
                    detalleFactura.setFactura(factura);
                    detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
                    detalleFactura.setArticulo(detCto.getEquipo());
                    cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();//CAntidad facturado

                    detalleFactura.setCantidad(cantidad);

                    subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
                    detalleFactura.setSubTotal(subTotal);

                    valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);

                    detalleFactura.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));

                    detalleFactura.setDescuento(0.00);
                    detalleFactura.setExistencia(0.00);
                    detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
                    detalleFactura.setNuevaExistencia(0.0);

                    detalleFactura.setItbis(valorItbis);
                    detalleFactura.setTasaItbis(detCto.getTasaItbis());

                    detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
                    detalleFactura.setUnidad(unidad);

                    listaDetalleFacturaTemporal.add(detalleFactura);
                }

                try {

                    System.out.println(" listaDetalleFactura " + listaDetalleFacturaTemporal + " listaDetalleFactura.size() " + listaDetalleFacturaTemporal.size());

                    if (listaDetalleFacturaTemporal.size() > 0) {

                        factura.setTotal(getTotalTemp(listaDetalleFacturaTemporal));
                        factura.setSubTotal(getSubTotalTewmp(listaDetalleFacturaTemporal));
                        factura.setItbis(getItbisTemp(listaDetalleFacturaTemporal));
                        factura.setMonto(getTotalTemp(listaDetalleFacturaTemporal));
                        factura.setPendiente(getTotalTemp(listaDetalleFacturaTemporal));

                        factura.setDetalleFacturaTemporalCollection(listaDetalleFacturaTemporal);

                        FacturaTemporal factDb = guardarFacturaTempotal(factura);

                        if (factDb == null) {

                            ClaseUtil.mensaje(" Hubo Error creando la factura ");
                            return;

                        }

                        listaFacturaTemporal.add(factDb);

                    }

                    this.updateMessage(" Procesada  " + i + "  De " + total);

                } catch (Exception e) {

                    e.printStackTrace();
                }

                num++;

            } catch (Exception e) {

                ClaseUtil.mensaje("Hubo un error guardando la factura ");
                e.printStackTrace();
            }

            Thread.sleep(200);
        }

    }

    private class TaskGenerarFactura extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            try {

                int total = listaContrato.size();
                int i = 0;

                for (ContratoDeServicio contrato : listaContrato) {
                    i++;
                    generarFactura(contrato, i, total);
                    this.updateProgress(i, total);
                }

                listaContrato.clear();
                listaFacturaTemporal.clear();
                ManejoFacturaTemporal.getInstancia().eliminarFacturaTemporal();

            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;

        }

        private void generarFactura(ContratoDeServicio contrato, int i, int total) throws InterruptedException {

            CorteDeFacturacion corteFact = new CorteDeFacturacion();
            DetalleCorteDeFacturacion detaCorte;
            List<DetalleCorteDeFacturacion> listaDetCorte = new ArrayList<>();
            Factura factura;
            DetalleFactura detalleFactura;
            FacturaDatosDeVehiculo facturaDatosDeVehiculo;

            List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();
            List<DatosDeVehiculo> listaDetVehiculo = new ArrayList<>();

            corteFact.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
            corteFact.setFechaRegistro(new Date());
            corteFact.setUsuario(VariablesGlobales.USUARIO);

            int num = 0;

            factura = new Factura();

            //****************************************
            System.out.println("contrato.getCliente() " + contrato.getCliente().getTipoNcf());
            factura.setAbono(0.00);
            factura.setCliente(contrato.getCliente());
            factura.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
            factura.setMonto(contrato.getTotalAPagar());
            factura.setAnulada(false);
            factura.setNumeroContrato(contrato.getNumeroDeContrato());
            factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
            factura.setNumero(secDoc.getNumero() + num);

            factura.setNcf("na");
            factura.setTotal(factura.getMonto());
            factura.setSubTotal(contrato.getSubTotal());
            factura.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
            factura.setDescuento(0.00);
            factura.setItbis(contrato.getItbis());
            factura.setUsuario(VariablesGlobales.USUARIO);
            factura.setVendedor(contrato.getEjecutivoDeVenta());

            factura.setFechaCreacion(new Date());

            Date fechaVencimiento = ClaseUtil.asDate(dpFechaFacturacion.getValue());
            factura.setFechaVencimiento(fechaVencimiento);

            factura.setPagada(false);
            factura.setTipoVenta(2);
            factura.setAbono(0.0);
            factura.setPendiente(factura.getTotal());
            factura.setCondicionPago(new CondicionPago(2));
            factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
            fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, factura.getCliente().getDiasCredito());
            factura.setFechaVencimiento(fechaVencimiento);
            factura.setDespachada(false);

            FormaPago formaPago = new FormaPago();
            TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
            formaPago.setTipoFormaPago(tipoFormaPago);
            formaPago.setMonto(factura.getTotal());

            //agregando detalle a la factura
            listaDetContrato.clear();
            listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
                    .getDetalleContratoVencido(contrato.getCodigo(), fechaVencimiento));

            System.out.println("NO HUBO CLIENTE CON NCF NULO ");

            Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;

            listaDetalleFactura.clear();
            listaDetalleFacDdatosVehiculo.clear();
            for (DetalleContratoDeServicio detCto : listaDetContrato) {

                System.out.println("detCto.getEquipo().getTipoArticulo().getCodigo() " + detCto.getEquipo().getTipoArticulo().getCodigo());
//                    if (detCto.getEquipo().getTipoArticulo().getCodigo() == 3) {//solo se creanran factura por servicio 

                detalleFactura = new DetalleFactura();
                // *****************************************

//                long dias = ClaseUtil.diferenciaDiasEntreDosFecha(detCto.getFechaUltimoPagoHasta(), fechaVencimiento);
                Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();

//                int diasInt = Integer.parseInt(Long.toString(dias));
//
//                if (dias < 30) {
//
//                    System.out.println("dias MENOR A 30" + dias);
//                    Double precioPorDia = ClaseUtil.roundDouble(detCto.getPrecioPagado() / 30, 2);
//                    precioPorDia = precioPorDia * dias;
//                    detalleFactura.setPrecio(precioPorDia);
//
//                } else {
//
//                    System.out.println("dias mayor de 30 " + dias);
//                    detalleFactura.setPrecio(detCto.getPrecioPagado());
//                }
                int cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();
                int cantidadGPS = detCto.getCantidad();
                String desc = "";
                Date fechaVencimientoCal = null;

//                if (contrato.getFrecuenciaDePago().getCodigo() == 1) {
                cantidadFrecuenciaPago *= 12;
//                }

//                System.out.println("dias mayor de 30 " + dias);
                fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadFrecuenciaPago);//Calculamos la fecha de vencimiento
                // en base a la cantidad de pago por a delantado

                System.out.println("fechaVencimientoCal " + fechaVencimientoCal);

//                if (dias < 30) {
//
//                    fechaVencimientoCal = ClaseUtil.Fechadiadespues(detCto.getFechaUltimoPagoHasta(), diasInt);//Calculamos la fecha de vencimiento
//
//                } else {
//
//                    fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadFrecuenciaPago);//Calculamos la fecha de vencimiento
//                    // en base a la cantidad de pago por adelantado
//                }
                detCto.setFechaUltimoPagoDesde(detCto.getFechaUltimoPagoHasta());//Factura  que cubre el pago desde esta fecha hasta la fecha calculada

                detCto.setFechaUltimoPagoHasta(fechaVencimientoCal); //fechaVencimientoCal   a hora es el setFechaUltimoPagoHasta    

                desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                        + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoDesde())
                        + " AL  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta());

                System.out.println("descripcion pago " + desc);
                detalleFactura.setDescripcionPago(desc);
                detalleFactura.setFactura(factura);
                detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
                detalleFactura.setArticulo(detCto.getEquipo());
                cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();//CAntidad facturado

                detalleFactura.setCantidad(cantidad);

                subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
                detalleFactura.setSubTotal(subTotal);

                valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);

                detalleFactura.setPrecioItbis(detCto.getPrecioPagado() + ((detCto.getTasaItbis() / 100) * detCto.getPrecioPagado()));

                detalleFactura.setDescuento(0.00);
                detalleFactura.setExistencia(0.00);
                detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
                detalleFactura.setNuevaExistencia(0.0);

                detalleFactura.setItbis(valorItbis);
                detalleFactura.setTasaItbis(detCto.getTasaItbis());

                detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
                detalleFactura.setUnidad(unidad);

                //agregando detalle a la factura de los vehiculos 
                listaDetVehiculo.clear();
                listaDetVehiculo.addAll(ManejoContratoDeServicio.getInstancia()
                        .getDatosVehiculoo(detCto.getCodigo()));

                System.out.println(" datos vehiculo " + listaDetVehiculo.size() + " detCto.getCodigo() " + detCto.getCodigo());

                for (DatosDeVehiculo detVeh : listaDetVehiculo) {

                    System.out.println("configurando  factura datos de vehiculo ");
                    facturaDatosDeVehiculo = new FacturaDatosDeVehiculo();
                    // *****************************************

                    facturaDatosDeVehiculo.setFactura(detalleFactura);
                    facturaDatosDeVehiculo.setAdicional(detVeh.getAdicional());
                    facturaDatosDeVehiculo.setAnio(detVeh.getAnio());
                    facturaDatosDeVehiculo.setChasis(detVeh.getChasis());
                    facturaDatosDeVehiculo.setColor(detVeh.getColor());
                    facturaDatosDeVehiculo.setMarca(detVeh.getMarca());
                    facturaDatosDeVehiculo.setMatricula(detVeh.getMatricula());
                    facturaDatosDeVehiculo.setModelo(detVeh.getModelo());
                    facturaDatosDeVehiculo.setPlaca(detVeh.getPlaca());
                    facturaDatosDeVehiculo.setTipoVehiculo(detVeh.getTipoVehiculo());
                    facturaDatosDeVehiculo.setVehiculo(detVeh.getVehiculo());

                    listaDetalleFacDdatosVehiculo.add(facturaDatosDeVehiculo);

                }

                listaDetalleFactura.add(detalleFactura);

            }

            try {

                System.out.println(" listaDetalleFactura " + listaDetalleFactura + " listaDetalleFactura.size() " + listaDetalleFactura.size());

                if (listaDetalleFactura.size() > 0) {

                    factura.setTotal(getTotal(listaDetalleFactura));
                    factura.setSubTotal(getSubTotal(listaDetalleFactura));
                    factura.setItbis(getItbis(listaDetalleFactura));
                    factura.setMonto(getTotal(listaDetalleFactura));
                    factura.setPendiente(getTotal(listaDetalleFactura));

                    factura.setDetalleFacturaCollection(listaDetalleFactura);

                    Double balancePendiente = ManejoFactura.getInstancia().getMontoPendiente(factura.getCliente());

                    Factura factDb = guardar(factura);

                    if (factDb == null) {

                        ClaseUtil.mensaje(" Hubo Error creando la factura ");
                        return;

                    } else {

                        listaDetalleFacDdatosVehiculo.forEach((fctdvh) -> {
                            System.out.println("item " + fctdvh);
                            ManejoFacturaDatosDeVehiculo.getInstancia().salvar(fctdvh);
                        });
                        //Actualizar el ncf 
                        ManejoRelacionNcf.getInstancia().actualizar(relacionNcf);

                        //Generando el historico de corte de facturacion 
                        detaCorte = new DetalleCorteDeFacturacion();

                        detaCorte.setFactura(factura);
                        detaCorte.setNumeroFactura(factura.getNumero());
                        detaCorte.setContrato(contrato);
                        detaCorte.setNumeroContrato(contrato.getNumeroDeContrato());
                        detaCorte.setCorteDeFacturacion(corteFact);
                        detaCorte.setTotalFactura(factura.getTotal());
                        listaDetCorte.add(detaCorte);

                        contrato.setDetalleContratoDeServicioCollection(listaDetContrato);
                        ManejoContratoDeServicio.getInstancia().actualizar(contrato);

                        HistoricoBalancePendiente hBalance = new HistoricoBalancePendiente();

                        hBalance.setCliente(factura.getCliente().getCodigo());
                        hBalance.setFactura(factura.getCodigo());
                        hBalance.setFechaVencimiento(factura.getFecha());
                        hBalance.setTotal(balancePendiente);

                        ManejoHistoricoBalancePendiente.getInstancia().salvar(hBalance);

                        this.updateMessage(" Procesada  " + i + "  De " + total);
                    }
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            num++;

            corteFact.setCantidadFactura(num);
            corteFact.setDetalleCorteDeFacturacionCollection(listaDetCorte);

            ManejoCorteDeFacturacion.getInstancia().salvar(corteFact);

            Thread.sleep(200);
        }
    }

    private void generarFactTemporales(ContratoDeServicio contrato) throws InterruptedException {

        try {

            CorteDeFacturacion corteFact = new CorteDeFacturacion();

            FacturaTemporal factura;
            DetalleFacturaTemporal detalleFactura;

            List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();

            corteFact.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
            corteFact.setFechaRegistro(new Date());
            corteFact.setUsuario(VariablesGlobales.USUARIO);

            int num = 0;

            vbVisorDeProgreso.setVisible(true);

            factura = new FacturaTemporal();

            //****************************************
            System.out.println("contrato.getCliente() " + contrato.getCliente().getTipoNcf());
            factura.setAbono(0.00);
            factura.setCliente(contrato.getCliente());
            factura.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
            factura.setMonto(contrato.getTotalAPagar());
            factura.setAnulada(false);
            factura.setNumeroContrato(contrato.getNumeroDeContrato());
            factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
            factura.setNumero(secDoc.getNumero() + num);

            factura.setNcf("na");
            factura.setTotal(factura.getMonto());
            factura.setSubTotal(contrato.getSubTotal());
            factura.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
            factura.setDescuento(0.00);
            factura.setItbis(contrato.getItbis());
            factura.setUsuario(VariablesGlobales.USUARIO);
            factura.setVendedor(contrato.getEjecutivoDeVenta());

            factura.setFechaCreacion(new Date());

            Date fechaVencimiento = ClaseUtil.asDate(dpFechaFacturacion.getValue());
            factura.setFechaVencimiento(fechaVencimiento);

            factura.setPagada(false);
            factura.setTipoVenta(2);
            factura.setAbono(0.0);
            factura.setPendiente(factura.getTotal());
            factura.setCondicionPago(new CondicionPago(2));
            factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
            fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, factura.getCliente().getDiasCredito());
            factura.setFechaVencimiento(fechaVencimiento);
            factura.setDespachada(false);

            FormaPago formaPago = new FormaPago();
            TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
            formaPago.setTipoFormaPago(tipoFormaPago);
            formaPago.setMonto(factura.getTotal());

            //agregando detalle a la factura
            listaDetContrato.clear();
            listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
                    .getDetalleContratoVencido(contrato.getCodigo(), fechaVencimiento));

            System.out.println("NO HUBO CLIENTE CON NCF NULO ");

            Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;

            listaDetalleFacturaTemporal.clear();
            listaDetalleFacDdatosVehiculo.clear();
            for (DetalleContratoDeServicio detCto : listaDetContrato) {

                System.out.println("detCto.getEquipo().getTipoArticulo().getCodigo() " + detCto.getEquipo().getTipoArticulo().getCodigo());
//                    if (detCto.getEquipo().getTipoArticulo().getCodigo() == 3) {//solo se creanran factura por servicio 

                detalleFactura = new DetalleFacturaTemporal();
                // *****************************************

//                    long dias = ClaseUtil.diferenciaDiasEntreDosFecha(detCto.getFechaUltimoPagoHasta(), fechaVencimiento);
                Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();

                if (!(detCto.getPrecioRenovacion() == null)) {

                    System.out.println("detCto.getPrecioRenovacion() " + detCto.getPrecioRenovacion());
                    detalleFactura.setPrecio(detCto.getPrecioRenovacion());

                } else {

                    System.out.println("detCto.getPrecioPagado() " + detCto.getPrecioPagado());
                    detalleFactura.setPrecio(detCto.getPrecioPagado());

                }

                int cantidadFrecuenciaPago = 0, cantidadDeMeses = 0;

                cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();

                cantidadDeMeses = cantidadFrecuenciaPago * 12;

                int cantidadGPS = detCto.getCantidad();
                String desc = "";
                Date fechaVencimientoCal = null;

                fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadDeMeses);//Calculamos la fecha de vencimiento
                // en base a la cantidad de pago por a delantado

                System.out.println("fechaVencimientoCal " + fechaVencimientoCal);

                desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE "
                        + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta())
                        + " AL  " + new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimientoCal);

                System.out.println("descripcion pago " + desc);

                detalleFactura.setDescripcionPago(desc);
                detalleFactura.setFactura(factura);
                detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
                detalleFactura.setArticulo(detCto.getEquipo());
                cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();//CAntidad facturada

                detalleFactura.setCantidad(cantidad);

                System.out.println("detalleFactura.getCantidad() " + detalleFactura.getCantidad() + " detalleFactura.getPrecio() " + detalleFactura.getPrecio());
                subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
                detalleFactura.setSubTotal(subTotal);

                valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);

                detalleFactura.setPrecioItbis(detalleFactura.getPrecio() + ((detCto.getTasaItbis() / 100) * detalleFactura.getPrecio()));

                detalleFactura.setDescuento(0.00);
                detalleFactura.setExistencia(0.00);
                detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
                detalleFactura.setNuevaExistencia(0.0);

                detalleFactura.setItbis(valorItbis);
                detalleFactura.setTasaItbis(detCto.getTasaItbis());

                detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
                detalleFactura.setUnidad(unidad);

                listaDetalleFacturaTemporal.add(detalleFactura);
            }

            try {

                System.out.println(" listaDetalleFactura " + listaDetalleFacturaTemporal + " listaDetalleFactura.size() " + listaDetalleFacturaTemporal.size());

                if (listaDetalleFacturaTemporal.size() > 0) {

                    factura.setTotal(getTotalTemp(listaDetalleFacturaTemporal));
                    factura.setSubTotal(getSubTotalTewmp(listaDetalleFacturaTemporal));
                    factura.setItbis(getItbisTemp(listaDetalleFacturaTemporal));
                    factura.setMonto(getTotalTemp(listaDetalleFacturaTemporal));
                    factura.setPendiente(getTotalTemp(listaDetalleFacturaTemporal));

                    factura.setDetalleFacturaTemporalCollection(listaDetalleFacturaTemporal);

                    FacturaTemporal factDb = guardarFacturaTempotal(factura);

                    if (factDb == null) {

                        ClaseUtil.mensaje(" Hubo Error creando la factura ");
                        return;

                    }

                    listaFacturaTemporal.add(factDb);

                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            num++;

        } catch (Exception e) {

            ClaseUtil.mensaje("Hubo un error guardando la factura ");
            e.printStackTrace();
        }

    }

    private void generarFactura(ContratoDeServicio contrato) throws InterruptedException {

        CorteDeFacturacion corteFact = new CorteDeFacturacion();
        DetalleCorteDeFacturacion detaCorte;
        List<DetalleCorteDeFacturacion> listaDetCorte = new ArrayList<>();
        Factura factura;
        DetalleFactura detalleFactura;
        FacturaDatosDeVehiculo facturaDatosDeVehiculo;

        List<DetalleContratoDeServicio> listaDetContrato = new ArrayList<>();
        List<DatosDeVehiculo> listaDetVehiculo = new ArrayList<>();

        corteFact.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
        corteFact.setFechaRegistro(new Date());
        corteFact.setUsuario(VariablesGlobales.USUARIO);

        int num = 0;

        factura = new Factura();

        //****************************************
        System.out.println("contrato.getCliente() " + contrato.getCliente().getTipoNcf());
        factura.setAbono(0.00);
        factura.setCliente(contrato.getCliente());
        factura.setNombreCliente(contrato.getCliente().getNombre() + contrato.getCliente().getApellido());
        factura.setMonto(contrato.getTotalAPagar());
        factura.setAnulada(false);
        factura.setNumeroContrato(contrato.getNumeroDeContrato());
        factura.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

        SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(factura.getUnidadDeNegocio().getCodigo(), 7);
        factura.setNumero(secDoc.getNumero() + num);

        factura.setNcf("na");
        factura.setTotal(factura.getMonto());
        factura.setSubTotal(contrato.getSubTotal());
        factura.setFecha(ClaseUtil.asDate(dpFechaFacturacion.getValue()));
        factura.setDescuento(0.00);
        factura.setItbis(contrato.getItbis());
        factura.setUsuario(VariablesGlobales.USUARIO);
        factura.setVendedor(contrato.getEjecutivoDeVenta());

        factura.setFechaCreacion(new Date());

        Date fechaVencimiento = ClaseUtil.asDate(dpFechaFacturacion.getValue());
        factura.setFechaVencimiento(fechaVencimiento);

        factura.setPagada(false);
        factura.setTipoVenta(2);
        factura.setAbono(0.0);
        factura.setPendiente(factura.getTotal());
        factura.setCondicionPago(new CondicionPago(2));
        factura.setPlazo(ManejoPlazo.getInstancia().getplazo(factura.getCliente().getDiasCredito()));
        fechaVencimiento = ClaseUtil.Fechadiadespues(fechaVencimiento, factura.getCliente().getDiasCredito());
        factura.setFechaVencimiento(fechaVencimiento);
        factura.setDespachada(false);

        FormaPago formaPago = new FormaPago();
        TipoFormaPago tipoFormaPago = ManejoTipoFormaPago.getInstancia().getTipoFormaPago(2);
        formaPago.setTipoFormaPago(tipoFormaPago);
        formaPago.setMonto(factura.getTotal());

        //agregando detalle a la factura
        listaDetContrato.clear();
        listaDetContrato.addAll(ManejoContratoDeServicio.getInstancia()
                .getDetalleContratoVencido(contrato.getCodigo(), fechaVencimiento));

        System.out.println("NO HUBO CLIENTE CON NCF NULO ");

        Double cantidad = 0.00, valorItbis = 0.00, subTotal = 0.00;

        listaDetalleFactura.clear();
        listaDetalleFacDdatosVehiculo.clear();
        for (DetalleContratoDeServicio detCto : listaDetContrato) {

            System.out.println("detCto.getEquipo().getTipoArticulo().getCodigo() " + detCto.getEquipo().getTipoArticulo().getCodigo());
//                    if (detCto.getEquipo().getTipoArticulo().getCodigo() == 3) {//solo se creanran factura por servicio 

            detalleFactura = new DetalleFactura();
            // *****************************************

//                long dias = ClaseUtil.diferenciaDiasEntreDosFecha(detCto.getFechaUltimoPagoHasta(), fechaVencimiento);
            Unidad unidad = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(detCto.getEquipo().getCodigo()).getUnidad();

            if (!(detCto.getPrecioRenovacion() == null)) {
                detalleFactura.setPrecio(detCto.getPrecioRenovacion());
            } else {
                detalleFactura.setPrecio(detCto.getPrecioPagado());
            }

//                }
//            int cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();
            int cantidadGPS = detCto.getCantidad();
            String desc = "";
            Date fechaVencimientoCal = null;
            int cantidadFrecuenciaPago = 0, cantidadDeMeses = 0;

            cantidadFrecuenciaPago = detCto.getCantidadFrecuenciaDePago().intValue();
            cantidadDeMeses = cantidadFrecuenciaPago * 12;

            fechaVencimientoCal = ClaseUtil.FechaMesDespues(detCto.getFechaUltimoPagoHasta(), cantidadDeMeses);//Calculamos la fecha de vencimiento
            // en base a la cantidad de pago por a delantado

            System.out.println("fechaVencimientoCal " + fechaVencimientoCal);

            detCto.setFechaUltimoPagoDesde(detCto.getFechaUltimoPagoHasta());//Factura  que cubre el pago desde esta fecha hasta la fecha calculada

            detCto.setFechaUltimoPagoHasta(fechaVencimientoCal); //fechaVencimientoCal   a hora es el setFechaUltimoPagoHasta    

            desc = cantidadFrecuenciaPago + " " + detCto.getFrecuenciaDePago().getDescripcion() + " POR SERVICIO DE"
                    + " " + cantidadGPS + " GPS DESDE  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoDesde())
                    + " AL  " + new SimpleDateFormat("dd/MM/yyyy").format(detCto.getFechaUltimoPagoHasta());
            System.out.println("descripcion pago " + desc);

//              detCto.setFechaUltimoPagoDesde(detCto.getFechaUltimoPagoHasta());//Factura  que cubre el pago desde esta fecha hasta la fecha calculada
//              detCto.setFechaUltimoPagoHasta(fechaVencimientoCal); //fechaVencimientoCal   a hora es el setFechaUltimoPagoHasta
//            
            detalleFactura.setDescripcionPago(desc);
            detalleFactura.setFactura(factura);
            detalleFactura.setCantidad(Double.valueOf(detCto.getCantidad()));
            detalleFactura.setArticulo(detCto.getEquipo());
            cantidad = detCto.getCantidadFrecuenciaDePago() * detalleFactura.getCantidad();//CAntidad facturado

            detalleFactura.setCantidad(cantidad);

            subTotal = ClaseUtil.roundDouble(detalleFactura.getCantidad() * detalleFactura.getPrecio(), 2);
            detalleFactura.setSubTotal(subTotal);

            valorItbis = ClaseUtil.roundDouble((detCto.getTasaItbis() / 100) * detalleFactura.getSubTotal(), 2);

            detalleFactura.setPrecioItbis(detalleFactura.getPrecio() + ((detCto.getTasaItbis() / 100) * detalleFactura.getPrecio()));

            detalleFactura.setDescuento(0.00);
            detalleFactura.setExistencia(0.00);
            detalleFactura.setNombreArticulo(detCto.getEquipo().getNombre());
            detalleFactura.setNuevaExistencia(0.0);

            detalleFactura.setItbis(valorItbis);
            detalleFactura.setTasaItbis(detCto.getTasaItbis());

            detalleFactura.setTotal(detalleFactura.getItbis() + detalleFactura.getSubTotal());
            detalleFactura.setUnidad(unidad);

            //agregando detalle a la factura de los vehiculos 
            listaDetVehiculo.clear();
            listaDetVehiculo.addAll(ManejoContratoDeServicio.getInstancia()
                    .getDatosVehiculoo(detCto.getCodigo()));

            System.out.println(" datos vehiculo " + listaDetVehiculo.size() + " detCto.getCodigo() " + detCto.getCodigo());

            for (DatosDeVehiculo detVeh : listaDetVehiculo) {

                System.out.println("configurando  factura datos de vehiculo ");
                facturaDatosDeVehiculo = new FacturaDatosDeVehiculo();
                // *****************************************

                facturaDatosDeVehiculo.setFactura(detalleFactura);
                facturaDatosDeVehiculo.setAdicional(detVeh.getAdicional());
                facturaDatosDeVehiculo.setAnio(detVeh.getAnio());
                facturaDatosDeVehiculo.setChasis(detVeh.getChasis());
                facturaDatosDeVehiculo.setColor(detVeh.getColor());
                facturaDatosDeVehiculo.setMarca(detVeh.getMarca());
                facturaDatosDeVehiculo.setMatricula(detVeh.getMatricula());
                facturaDatosDeVehiculo.setModelo(detVeh.getModelo());
                facturaDatosDeVehiculo.setPlaca(detVeh.getPlaca());
                facturaDatosDeVehiculo.setTipoVehiculo(detVeh.getTipoVehiculo());
                facturaDatosDeVehiculo.setVehiculo(detVeh.getVehiculo());

                listaDetalleFacDdatosVehiculo.add(facturaDatosDeVehiculo);

            }

            listaDetalleFactura.add(detalleFactura);

        }

        try {

            System.out.println(" listaDetalleFactura " + listaDetalleFactura + " listaDetalleFactura.size() " + listaDetalleFactura.size());

            if (listaDetalleFactura.size() > 0) {

                factura.setTotal(getTotal(listaDetalleFactura));
                factura.setSubTotal(getSubTotal(listaDetalleFactura));
                factura.setItbis(getItbis(listaDetalleFactura));
                factura.setMonto(getTotal(listaDetalleFactura));
                factura.setPendiente(getTotal(listaDetalleFactura));

                factura.setDetalleFacturaCollection(listaDetalleFactura);

                Double balancePendiente = ManejoFactura.getInstancia().getMontoPendiente(factura.getCliente());

                Factura factDb = guardar(factura);

                if (factDb == null) {

                    ClaseUtil.mensaje(" Hubo Error creando la factura ");
                    return;

                } else {

                    listaDetalleFacDdatosVehiculo.forEach((fctdvh) -> {
                        System.out.println("item " + fctdvh);
                        ManejoFacturaDatosDeVehiculo.getInstancia().salvar(fctdvh);
                    });
                    //Actualizar el ncf 
                    ManejoRelacionNcf.getInstancia().actualizar(relacionNcf);

                    //Generando el historico de corte de facturacion 
                    detaCorte = new DetalleCorteDeFacturacion();

                    detaCorte.setFactura(factura);
                    detaCorte.setNumeroFactura(factura.getNumero());
                    detaCorte.setContrato(contrato);
                    detaCorte.setNumeroContrato(contrato.getNumeroDeContrato());
                    detaCorte.setCorteDeFacturacion(corteFact);
                    detaCorte.setTotalFactura(factura.getTotal());
                    listaDetCorte.add(detaCorte);

                    contrato.setDetalleContratoDeServicioCollection(listaDetContrato);
                    ManejoContratoDeServicio.getInstancia().actualizar(contrato);

                    HistoricoBalancePendiente hBalance = new HistoricoBalancePendiente();

                    hBalance.setCliente(factura.getCliente().getCodigo());
                    hBalance.setFactura(factura.getCodigo());
                    hBalance.setFechaVencimiento(factura.getFecha());
                    hBalance.setTotal(balancePendiente);

                    ManejoHistoricoBalancePendiente.getInstancia().salvar(hBalance);

                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        num++;

        corteFact.setCantidadFactura(num);
        corteFact.setDetalleCorteDeFacturacionCollection(listaDetCorte);

        ManejoCorteDeFacturacion.getInstancia().salvar(corteFact);

    }

}
