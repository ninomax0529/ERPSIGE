/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cxp.factura;

import com.jfoenix.controls.JFXButton;
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
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.contabilidadd.DocumentoAnuladoDao;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.cxp.ManejoFacturaSuplidor;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.DetalleEntradaDiario;
import modelo.DetalleFacturaSuplidor;
import modelo.EntradaDiario;
import modelo.FacturaSuplidor;
import modelo.MenuModulo;
import modelo.RolMenuModulo;
import modelo.SecuenciaDocumento;
import reporte.contabilidad.RptLibroContable;
import reporte.cxp.RptFacturaSuplidor;
//import reporte.factura.RptFactura;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ConsultaFacturaSuplidorController implements Initializable {

    @FXML
    private JFXButton btnExportXLS;

    /**
     * @return the codigoRol
     */
    public int getCodigoRol() {
        return codigoRol;
    }

    /**
     * @param codigoRol the codigoRol to set
     */
    public void setCodigoRol(int codigoRol) {
        this.codigoRol = codigoRol;
    }

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
    private JFXDatePicker dpFechaDesde;
    @FXML
    private JFXDatePicker dpFecgaHasta;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXTextField txtFiltrar;
    @FXML
    private TableView<FacturaSuplidor> tbFactura;
    @FXML
    private TableColumn<FacturaSuplidor, String> tbcFactura;
    @FXML
    private TableColumn<FacturaSuplidor, String> tbcSuplidor;
    @FXML
    private TableColumn<FacturaSuplidor, String> tbcAnulada;
    @FXML
    private TableColumn<FacturaSuplidor, Date> tbcFecha;
    @FXML
    private TableColumn<FacturaSuplidor, String> tbcRNc;
    @FXML
    private TableColumn<FacturaSuplidor, Double> tbcSubTotalHeader;
    @FXML
    private TableColumn<FacturaSuplidor, Double> tbcItbis;
    @FXML
    private TableColumn<FacturaSuplidor, Double> tbcDescuento;
    @FXML
    private TableColumn<FacturaSuplidor, Double> tbcTotal;
    @FXML
    private TableColumn<FacturaSuplidor, String> tbcProyecto;
    @FXML
    private TableColumn<FacturaSuplidor, FacturaSuplidor> tbcVerFactura;
    @FXML
    private TableColumn<FacturaSuplidor, String> tbcCostoYGastos;
    @FXML
    private TableColumn<FacturaSuplidor, String> tbcFormaPago;
    @FXML
    private TableColumn<FacturaSuplidor, String> tbcConcepto;
    @FXML
    private TableView<DetalleFacturaSuplidor> tbDetalleFactura;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, String> tbcArticulo;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, String> tbcUnidad;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, String> tbcCantidad;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, Double> tbcPrecioUnitario;
    @FXML
    private TableColumn<DetalleFacturaSuplidor, Double> tbcSubTotal;
    @FXML
    private TableColumn<DetalleEntradaDiario, String> tbcCuenta;
    @FXML
    private TableColumn<DetalleEntradaDiario, String> tbcDescripcion;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbcDebito;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbcCredito;
    @FXML
    private TableView<DetalleEntradaDiario> tbDetalleEntrada;

    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Label lbCantidad;

    ObservableList<DetalleFacturaSuplidor> listadetalleFactura = FXCollections.observableArrayList();
    ObservableList<FacturaSuplidor> listaFactura = FXCollections.observableArrayList();
    ObservableList<DetalleEntradaDiario> listaDetalleEnt = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnAnular;
    @FXML
    private TabPane tabPane;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private TextField txtTotalCredito;
    @FXML
    private JFXTextField txtNoAsiento;
    @FXML
    private JFXTextField txtNoFactura;
    @FXML
    private JFXDatePicker dpFechaContabilidad;

    @FXML
    private JFXTextField txtConcepto;
    @FXML
    private TextField txtTotalDebito;
    @FXML
    private JFXButton btnActualizar;
    Date fechaIni, fechaFin;

    @FXML
    private JFXButton btnVerAsiento;
    EntradaDiario entdiario = null;
    @FXML
    private HBox hbPermiso;

    private int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    private Integer codigoMenuModulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablaEncabezado();
        inicializarTablaDetalle();
        inicializarTablaEntradaDiario();
        iniciazarFiltro();
        dpFechaDesde.setValue(LocalDate.now());
        dpFecgaHasta.setValue(LocalDate.now());
        buscar();

//        setCodigoMenuModulo(ManejoMenuModulo.getInstancia().getMenuModulo(10, "btnConsultaFacturaSuplidor"));
//        agregarOpciones();
        activarOpciones();

    }

    public void inicializarTablaEncabezado() {

        tbFactura.setItems(listaFactura);

        tbcFactura.setCellValueFactory(new PropertyValueFactory<>("ncf"));
        tbcSuplidor.setCellValueFactory(new PropertyValueFactory<>("nombreSuplidor"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcSubTotalHeader.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        tbcItbis.setCellValueFactory(new PropertyValueFactory<>("itbis"));
        tbcDescuento.setCellValueFactory(new PropertyValueFactory<>("totalDescuento"));
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbcFormaPago.setCellValueFactory(new PropertyValueFactory<>("formadePago"));
        tbcConcepto.setCellValueFactory(new PropertyValueFactory<>("concepto"));

        tbcAnulada.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getAnulada() == true ? "SI" : "NO");
                    }
                    return property;
                });

        tbcCostoYGastos.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getCostosYGastos() != null) {
                        property.setValue(cellData.getValue().getCostosYGastos().getNombre());
                    } else {
                        property.setValue("N/A");
                    }
                    return property;
                });

        tbcFormaPago.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getFormaDePago() != null) {
                        property.setValue(cellData.getValue().getNombreFormaDePago());
                    } else {
                        property.setValue("N/A");
                    }
                    return property;
                });

        tbcSuplidor.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getSuplidor() != null) {
                        property.setValue(cellData.getValue().getSuplidor().getDescripcion());
                    } else {
                        property.setValue("na");
                    }
                    return property;
                });

        tbcRNc.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getSuplidor() != null) {
                        property.setValue(cellData.getValue().getSuplidor().getRnc());
                    } else {
                        property.setValue("na");
                    }
                    return property;
                });

        tbcProyecto.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getProyecto() != null) {
                        property.setValue(cellData.getValue().getProyecto().getNombre());

                    } else {
                        property.setValue("N/A");
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

        tbcVerFactura.setCellFactory((TableColumn<FacturaSuplidor, FacturaSuplidor> param) -> {

            TableCell<FacturaSuplidor, FacturaSuplidor> cellsc = new TableCell<FacturaSuplidor, FacturaSuplidor>() {
                @Override
                public void updateItem(FacturaSuplidor item, boolean empty) {
                    super.updateItem(item, empty);

                    ImageView imageview;
                    if (item != null) {

                        imageview = new ImageView(new Image(getClass().getResource("/imagen/img_documento.jpg").toString(), 40, 20, false, false));
                        imageview.setFitWidth(40);
                        imageview.setFitHeight(20);

                        VBox hbox = new VBox();

                        hbox.getChildren().addAll(imageview);

                        hbox.setAlignment(Pos.CENTER);

                        //Evento de la fila 
                        hbox.setOnMouseClicked((event) -> {

                            int factura = item.getCodigo();

                            RptFacturaSuplidor.getInstancia().verFactura(factura);

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

    public void inicializarTablaDetalle() {

        listadetalleFactura.clear();

        tbDetalleFactura.setItems(listadetalleFactura);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcArticulo.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        tbcSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

        tbcArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getDescripcion());
                    }
                    return property;
                });

        tbcCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });

    }

    public void inicializarTablaEntradaDiario() {

        tbcCuenta.setCellValueFactory(new PropertyValueFactory<>("Cuenta"));
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("DescripcionCuenta"));
        tbcDebito.setCellValueFactory(new PropertyValueFactory<>("Debito"));
        tbcCredito.setCellValueFactory(new PropertyValueFactory<>("Credito"));

        tbDetalleEntrada.setItems(listaDetalleEnt);

    }

    private void iniciazarFiltro() {

        FilteredList<FacturaSuplidor> filteredData = new FilteredList<>(tbFactura.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltrar.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(factura -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (factura.getNcf().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (factura.getFecha() != null && factura.getFecha().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (factura.getSuplidor() != null && factura.getNombreSuplidor().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<FacturaSuplidor> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbFactura.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbFactura.setItems(sortedData);
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        buscar();
    }

    @FXML
    private void tbFacturaMouseCliked(MouseEvent event) {

        if (!(tbFactura.getSelectionModel().getSelectedIndex() == -1)) {

            int codFact = tbFactura.getSelectionModel().getSelectedItem().getCodigo();
            entdiario = EntradaDiarioDao.getInstancia().getEntradaDiarioPorDocumento(codFact, 12);
            listadetalleFactura.clear();
            listadetalleFactura.addAll(ManejoFacturaSuplidor
                    .getInstancia().getDetalleFactura(codFact));

            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(1);
            }

            if (!(entdiario == null)) {

                txtNoAsiento.setText(entdiario.getNumero().toString());
                txtNoFactura.setText(entdiario.getNumeroDocumento());
                dpFechaContabilidad.setValue(ClaseUtil.convertToLocalDateViaMilisecond(entdiario.getFecha()));
                txtConcepto.setText(entdiario.getConcepto());
                listaDetalleEnt.clear();
                listaDetalleEnt.addAll(EntradaDiarioDao.getInstancia().getDetalleEntradaDiario(entdiario));
                txtTotalCredito.setText(getTotalCredito().toString());
                txtTotalDebito.setText(getTotalDebito().toString());
            } else {
                txtNoAsiento.clear();
                txtNoFactura.clear();
                dpFechaContabilidad.setValue(null);
                txtConcepto.clear();
                txtTotalCredito.clear();
                txtTotalDebito.clear();
                listaDetalleEnt.clear();

            }

        }
    }

    @FXML
    private void btnAnularActionEvent(ActionEvent event) {

        try {

            if (!(tbFactura.getSelectionModel().getSelectedIndex() == -1)) {

                Date fechaDesde = ClaseUtil.asDate(dpFechaDesde.getValue());
                Date fechaHasta = ClaseUtil.asDate(dpFecgaHasta.getValue());
                FacturaSuplidor factura = tbFactura.getSelectionModel().getSelectedItem();

                if (factura.getAnulada() == true) {

                    ClaseUtil.mensaje(" ESTA FACTURA YA ESTA ANULADA ");
                    return;
                }

                List<EntradaDiario> listaentrada = EntradaDiarioDao.getInstancia()
                        .getListaEntradaDiarioPorDocumento(factura.getCodigo(), 7);

                String msg = "";

                if (listaentrada.size() > 0) {

                    msg = " TAMBIEN SE ANULARAN LAS ENTRADA DE DIARIO NUMERO  " + listaentrada.get(0).getCodigo() + " , " + listaentrada.get(1).getCodigo();
                }

                Optional<ButtonType> ok = ClaseUtil.confirmarMensaje(" SEGURO QUE QUIERE ANULAR LA FACTURA NUMERO " + factura.getCodigo() + " \n " + msg);
//                        + );

                if (ok.get() == ButtonType.OK) {

                    factura.setAnulada(true);
                    factura.setFechaAnulada(new Date());
                    FacturaSuplidor facturadb = ManejoFacturaSuplidor.getInstancia().actualizar(factura);

                    if (!(facturadb == null)) {

                        try {

                            List<DetalleFacturaSuplidor> lista = ManejoFacturaSuplidor.getInstancia().getDetalleFactura(factura.getCodigo());

                            ManejoExistenciaArticulo.getInstancia().actualizarExistenciaPorAnulacionFactsupli(lista);

                            DocumentoAnuladoDao.getInstancia()
                                    .registroDocumentoAnulado(facturadb.getCodigo(),
                                            facturadb.getCodigo().toString(), 7, "ERROR EN FACTURACCION ");

                            //Anular entrada de diario de cheque  
                            if (!(listaentrada == null)) {

                                for (EntradaDiario asiento : listaentrada) {

                                    asiento.setAnulada(true);
                                    EntradaDiarioDao.getInstancia().actualizar(asiento);

                                    DocumentoAnuladoDao.getInstancia()
                                            .registroDocumentoAnulado(asiento.getCodigo(),
                                                    asiento.getCodigo().toString(), 6, " ERROR EN FACTURACION ");
                                }

                            }

                        } catch (Exception e) {

                            e.printStackTrace();
                        }

                        listaFactura.clear();
                        listaFactura.addAll(ManejoFacturaSuplidor.getInstancia().getLista(fechaDesde, fechaHasta));

                        ClaseUtil.mensaje("Factura anulada Correctamente");
                    }

                }

            } else {

                ClaseUtil.mensaje("Tiene que seleccionar una Factura");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void buscar() {
        listaFactura.clear();
        listadetalleFactura.clear();
        Date fechaDesde = ClaseUtil.asDate(dpFechaDesde.getValue());
        Date fechaHasta = ClaseUtil.asDate(dpFecgaHasta.getValue());
        listaFactura.addAll(ManejoFacturaSuplidor.getInstancia().getListaFactura(fechaDesde, fechaHasta));
    }

    @FXML
    private void tbDetalleFacturaMouseClicked(MouseEvent event) {
    }

    @FXML
    private void btnNuevoActionevent(ActionEvent event) throws IOException {

        if (!validarSecuencia()) {
            utiles.ClaseUtil.mensaje("La secuencia para la factura de suplidor de la unidad de negocio "
                    + "" + utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
            return;
        }

//        Parent root = FXMLLoader.load(getClass().getResource("/vista/cxp/factura/RegistrarFacturaSuplidor.fxml"));
//
//        utiles.ClaseUtil.crearStageModal(root);
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/cxp/factura/RegistrarFacturaSuplidor.fxml"));

        loader.load();

        Parent root = loader.getRoot();

        RegistrarFacturaSuplidorController controller = loader.getController();

        controller.setFacturaSuplidor(tbFactura.getSelectionModel().getSelectedItem());
        controller.inicializarDatos();

//          utiles.ClaseUtil.getStageModal(root);
        utiles.ClaseUtil.getStageModalcONTRATO(root);
        buscar();
    }

    private Boolean validarSecuencia() {

        Boolean existe = false;
        SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo(), 12);

        if (!(secDoc == null)) {

            existe = true;
        }
        return existe;
    }

    public Double getTotalCredito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detEntrada = tbDetalleEntrada.getItems();

        for (int i = 0; i < detEntrada.size(); i++) {

            total += detEntrada.get(i).getCredito();

        }

        return ClaseUtil.roundDouble(total, 4);

    }

    public Double getTotalDebito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detentrada = tbDetalleEntrada.getItems();

        for (int i = 0; i < detentrada.size(); i++) {
            total += detentrada.get(i).getDebito();
        }

        return ClaseUtil.roundDouble(total, 4);

    }

    @FXML
    private void btnActualizarActionEvent(ActionEvent event) throws IOException {

        try {

            if (tbFactura.getSelectionModel().getSelectedIndex() == -1) {
                utiles.ClaseUtil.mensaje("Tiene que selccionar una factura");
                return;
            }

            if (tbFactura.getSelectionModel().getSelectedItem().getAnulada()) {

                utiles.ClaseUtil.mensaje("ESTA FACTURA ESTA ANULADA ,NO SE PUEDE EDITAR ");
                return;
            }

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/cxp/factura/RegistrarFacturaSuplidor.fxml"));

            loader.load();

            Parent root = loader.getRoot();

            RegistrarFacturaSuplidorController controller = loader.getController();

            controller.setFacturaSuplidor(tbFactura.getSelectionModel().getSelectedItem());
            controller.setEditar(true);
            controller.llenarCampo();

            utiles.ClaseUtil.getStageModalcONTRATO(root);

            buscar();

        } catch (IOException ex) {

            Logger.getLogger(FacturaSuplidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnVerAsientoActionEvent(ActionEvent event) {

        if (!(entdiario == null)) {
            RptLibroContable.getInstancia().entradaDeDiario(entdiario.getCodigo());
        }
    }

    //Agrega las opciones de menu a la base de datos
    private void agregarOpciones() {

        MenuModulo menuModulo;

        List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(10);
        int codigoModulo = ManejoModulo.getInstancia().getModulo(10).getCodigo();
        String nombreModulo = ManejoModulo.getInstancia().getModulo(10).getNombre();

        boolean actualizando = false;

        for (Node n : hbPermiso.getChildren()) {

            if (!(n.getId() == null)) {

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

        if (getCodigoRol() == 1) {//rol de administrador

            for (Node n : hbPermiso.getChildren()) {
                n.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(getCodigoRol(), 10);

            for (Node n : hbPermiso.getChildren()) {

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

    @FXML
    private void btnExportXLSActionEvent(ActionEvent event) {

        try {

            util.ClaseUtil.exportarDAtos(tbFactura, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
