/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.consulta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controlador.contabilidad.proceso.FXMLEntradaDiarioController;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.contabilidadd.TipoAsientoDao;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.Cheque;
import modelo.DetalleEntradaDiario;
import modelo.EntradaDiario;
import modelo.SecuenciaDocumento;
import modelo.TipoAsiento;
import reporte.contabilidad.RptLibroContable;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author luis
 */
public class FXMLConsultaEntradaDiarioController implements Initializable {

    @FXML
    private TableColumn<EntradaDiario, Integer> tbcCodigo;
    @FXML
    private TableColumn<EntradaDiario, String> tbcDocumento;
    @FXML
    private TableColumn<EntradaDiario, String> tbcAnulada;
    @FXML
    private TableColumn<EntradaDiario, Date> tbcFecha;
    @FXML
    private TableColumn<EntradaDiario, Date> tbcFechaRegistro;
    @FXML
    private TableColumn<EntradaDiario, String> tbcTipoEntrada;
    @FXML
    private TableColumn<EntradaDiario, String> tbcTipoAsiento;
    @FXML
    private TableColumn<EntradaDiario, String> tbcTipoDocumento;
    @FXML
    private TableColumn<EntradaDiario, String> tbcConcepto;
    @FXML
    private TableColumn<EntradaDiario, EntradaDiario> tbcVerEntrada;

    @FXML
    private TableColumn<DetalleEntradaDiario, String> tbcCuenta;
    @FXML
    private TableColumn<DetalleEntradaDiario, String> tbcDescripcion;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbcDebito;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbcCredito;
    @FXML
    private TableView<EntradaDiario> tbEntradaDiario;
    @FXML
    private TableView<DetalleEntradaDiario> tbDetalleEntrada;

    @FXML
    private TextField txtTotalDebito;
    @FXML
    private TextField txtTotalCredito;
    @FXML
    private DatePicker dpFechaIniccio;
    @FXML
    private DatePicker dpFechaFinal;
    @FXML
    private JFXTextField txtBuscarAsiento;
    @FXML
    private JFXComboBox<TipoAsiento> cbTipoAsiento;
    @FXML
    private JFXButton btnBuscar;

    private EntradaDiario entradaDiario;
    ObservableList<TipoAsiento> listaTipoAsiento = FXCollections.observableArrayList();
    ObservableList<EntradaDiario> listaEntrada = FXCollections.observableArrayList();
    ObservableList<DetalleEntradaDiario> listaDetalleEntrada = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtDocumento;
    @FXML
    private JFXTextField txtTipoDocumento;
    private Cheque cheque;
    private String sqlParam;
    @FXML
    private JFXTextField txtConcepto;
    @FXML
    private JFXTextField txtFiltrarConcepto;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private JFXButton btnExportXLS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTabla();
        inicializarCombox();
        dpFechaIniccio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

        buscar();
        tbEntradaDiario.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                detalleEntrada();
            }
        });

        // TODO
    }

    private void iniciazarFiltro() {

        FilteredList<EntradaDiario> filteredData = new FilteredList<>(listaEntrada, p -> true);

        txtDocumento.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(catalogo -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (cbBuscar.getSelectionModel().getSelectedIndex() == 0) {
//
                if (catalogo.getNumeroDocumento().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (catalogo.getNumeroDocumento().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match

            });
        });

        txtBuscarAsiento.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(catalogo -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (cbBuscar.getSelectionModel().getSelectedIndex() == 0) {
//
                if (catalogo.getCodigo().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (catalogo.getCodigo().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match

            });
        });

        txtTipoDocumento.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(catalogo -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (cbBuscar.getSelectionModel().getSelectedIndex() == 0) {
//
                if (catalogo.getTipoDocumento().getNombre().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (catalogo.getTipoDocumento().getNombre().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match

            });
        });

        txtFiltrarConcepto.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(catalogo -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (cbBuscar.getSelectionModel().getSelectedIndex() == 0) {
//
                if (catalogo.getConcepto().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (catalogo.getConcepto().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match

            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<EntradaDiario> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbEntradaDiario.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbEntradaDiario.setItems(sortedData);
    }

    private void inicializarCombox() {

        listaTipoAsiento.addAll(TipoAsientoDao.getInstancia().getTipoAsiento());

        cbTipoAsiento.setConverter(new StringConverter<TipoAsiento>() {

            @Override
            public String toString(TipoAsiento tipoAsiento) {
                return tipoAsiento.getDescripcion();
            }

            @Override
            public TipoAsiento fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoAsiento.setItems(listaTipoAsiento);
    }

    private void btnEntradaDiarioActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/entradaDiario/FXMLEntradaDiario.fxml"));

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.showAndWait();

        listaEntrada.clear();
        listaEntrada.setAll(EntradaDiarioDao.getInstancia().getEntradasDiario());
    }

    public void iniciarTabla() {

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        tbcTipoEntrada.setCellValueFactory(new PropertyValueFactory<>("TipoEntrada"));

        tbcDocumento.setCellValueFactory(new PropertyValueFactory<>("numeroDocumento"));
        tbcFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("FechaRegistro"));
        tbcTipoAsiento.setCellValueFactory(new PropertyValueFactory<>("TipoAsiento"));
        tbcConcepto.setCellValueFactory(new PropertyValueFactory<>("Concepto"));

        tbcCuenta.setCellValueFactory(new PropertyValueFactory<>("Cuenta"));

        tbEntradaDiario.setEditable(true);
        tbcDescripcion.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getDescripcionCuenta() != null) {
                        property.setValue(cellData.getValue().getDescripcionCuenta());
                    }
                    return property;
                });
        tbcTipoAsiento.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getTipoAsiento() != null) {
                        property.setValue(cellData.getValue().getTipoAsiento().getDescripcion());
                    }
                    return property;
                });
        tbcTipoEntrada.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getTipoEntrada() != null) {
                        property.setValue(cellData.getValue().getTipoEntrada().getDescripcion());
                    }
                    return property;
                });

        tbcTipoDocumento.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getTipoDocumento() != null) {
                        property.setValue(cellData.getValue().getTipoDocumento().getNombre());
                    }
                    return property;
                });

        tbcConcepto.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getConcepto() != null) {
                        property.setValue(cellData.getValue().getConcepto());
                    }
                    return property;
                });

        tbcAnulada.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getAnulada() != null) {
                        if (cellData.getValue().getAnulada() == true) {
                            property.setValue("Si");
                        } else {
                            property.setValue("No");
                        }

                    }
                    return property;
                });

        tbcVerEntrada.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcVerEntrada.setCellFactory((TableColumn<EntradaDiario, EntradaDiario> param) -> {

            TableCell<EntradaDiario, EntradaDiario> cellsc = new TableCell<EntradaDiario, EntradaDiario>() {
                @Override
                public void updateItem(EntradaDiario item, boolean empty) {
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

                            System.out.println("item.getCodigo() : " + item.getCodigo());
                            RptLibroContable.getInstancia().entradaDeDiario(item.getCodigo());

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

//        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        tbcCredito.setCellValueFactory(new PropertyValueFactory<>("Credito"));
        tbcDebito.setCellValueFactory(new PropertyValueFactory<>("Debito"));

        listaEntrada.addAll(EntradaDiarioDao.getInstancia().getEntradasDiario());
        tbEntradaDiario.setItems(listaEntrada);

    }

    @FXML
    private void tbEntradaDiarioActionEvent(MouseEvent event) {

        detalleEntrada();

    }

    public Double getTotalCredito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detalle = tbDetalleEntrada.getItems();

        for (int i = 0; i < detalle.size(); i++) {
            System.out.println("Size: " + detalle.size());
            total += detalle.get(i).getCredito();
            System.out.println("TDeB: " + total);
        }

        return ClaseUtil.roundDouble(total, 2);

    }

    public Double getTotalDebito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detalle = tbDetalleEntrada.getItems();

        for (int i = 0; i < detalle.size(); i++) {
            total += detalle.get(i).getDebito();
        }

        return ClaseUtil.roundDouble(total, 2);

    }

    public EntradaDiario getEntradaDiario() {
        return entradaDiario;
    }

    public void setEntradaDiario(EntradaDiario entradaDiario) {
        this.entradaDiario = entradaDiario;
    }

    private void btnAnularActionEvent(ActionEvent event) {

        try {

            if (!(tbEntradaDiario.getSelectionModel().getFocusedIndex() == -1)) {
                System.out.println("Entro");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("FalconERP");
                alert.setHeaderText("FalconERP");
                alert.setContentText("Esta seguro que quiere anular la entrada de diario " + getEntradaDiario().getCodigo() + " ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    getEntradaDiario().setAnulada(true);
                    EntradaDiarioDao.getInstancia().salvar(getEntradaDiario());
                    listaEntrada.clear();
                    listaEntrada.addAll(EntradaDiarioDao.getInstancia().getEntradasDiario());
                    ClaseUtil.mensaje("El Proceso se Ejecuto Correctamente");
                } else {
                    ClaseUtil.mensaje("Tiene que Seleccionar un Registro");
                }

            }
        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo Problema en El Proceso");
            e.printStackTrace();
        }

    }

    private void btnActualizarActionEvent(ActionEvent event) {

        if (!(getEntradaDiario() == null)) {

            EntradaDiarioDao.getInstancia().salvar(getEntradaDiario());
            tbEntradaDiario.getSelectionModel().clearSelection();

        }
    }

    @FXML
    private void actualizarEntradaActionEvent(CellEditEvent edittedCell) {

//        setEntradaDiario(tbEntradaDiario.getSelectionModel().getSelectedItem());
//
//        getEntradaDiario().setDocumento(edittedCell.getNewValue());
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {
        buscar();
    }

    private void btnLimpiarActionEvent(ActionEvent event) {

        txtBuscarAsiento.clear();
        txtTipoDocumento.clear();
        txtTotalCredito.clear();
        txtTotalDebito.clear();
        listaDetalleEntrada.clear();
        listaEntrada.clear();
        listaTipoAsiento.clear();
        dpFechaFinal.setValue(LocalDate.now());
        dpFechaIniccio.setValue(LocalDate.now());
    }

    @FXML
    private void tbEntradaDiarioKeyPressed(KeyEvent event) {

//        detalleEntrada();
    }

    private void detalleEntrada() {

        listaDetalleEntrada.clear();

        if (!(tbEntradaDiario.getSelectionModel().getSelectedIndex() == -1)) {

            setEntradaDiario(tbEntradaDiario.getSelectionModel().getSelectedItem());
            txtConcepto.setText(getEntradaDiario().getConcepto());

            listaDetalleEntrada.addAll(EntradaDiarioDao.getInstancia().getDetalleEntradaDiario(entradaDiario));
            System.out.println("total " + listaDetalleEntrada.size());
            tbDetalleEntrada.setItems(listaDetalleEntrada);
            txtTotalCredito.setText(String.valueOf(getTotalCredito()));
            txtTotalDebito.setText(String.valueOf(getTotalDebito()));

        }
    }

    public void inicializarEntradaDiario(String sql) {

        listaEntrada.clear();

        listaEntrada.addAll(EntradaDiarioDao.getInstancia().getEntradasDiario(sql));

        tbEntradaDiario.setItems(listaEntrada);
        tbEntradaDiario.getSelectionModel().select(0);
        detalleEntrada();

    }

    public Cheque getCheque() {
        return cheque;
    }

    public void setCheque(Cheque cheque) {
        this.cheque = cheque;
    }

    public String getSqlParam() {
        return sqlParam;
    }

    public void setSqlParam(String sqlParam) {
        this.sqlParam = sqlParam;
    }

    private void btnVerDocumentoActionEvent(ActionEvent event) throws IOException {

        String sqlParam = "";

        if (!(tbEntradaDiario.getSelectionModel().getSelectedIndex() == -1)) {

            FXMLLoader loader = new FXMLLoader();
            EntradaDiario entradaDiario = tbEntradaDiario.getSelectionModel().getSelectedItem();

            if (entradaDiario.getTipoDocumento().getCodigo() == 1) {

                loader.setLocation(getClass().getResource("/vista/cxp/consulta/FXMLConsultaFacturaSuplidor.fxml"));
                loader.load();
                sqlParam = " select * from factura_suplidor  where codigo=" + entradaDiario.getDocumento();

                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.initModality(Modality.WINDOW_MODAL);

//                FXMLConsultaFacturaSuplidorController fXMLConsultaFacturaSuplidorController = loader.getController();
//                fXMLConsultaFacturaSuplidorController.inicializarDocumento(sqlParam);
                stage.show();

            } else if (entradaDiario.getTipoDocumento().getCodigo() == 2) {

                loader.setLocation(getClass().getResource("/vista/banco/consulta/FXMLConsultaDeCheque.fxml"));
                loader.load();
                sqlParam = " select * from cheque  where codigo=" + entradaDiario.getDocumento();

                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.initModality(Modality.WINDOW_MODAL);

//                FXMLConsultaDeChequeController fXMLConsultaDeChequeController = loader.getController();
//                fXMLConsultaDeChequeController.inicializarDocumento(sqlParam);
                stage.show();

            }

        }

    }

    @FXML
    private void btnNuevoActionevent(ActionEvent event) throws IOException {

        if (!validarSecuencia()) {
            utiles.ClaseUtil.mensaje("La secuencia para la entrada de diario de la unidad de negocio "
                    + "" + utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
            return;
        }

        Parent root = FXMLLoader.load(getClass().getResource("/vista/contabilidad/proceso/FXMLEntradaDiario.fxml"));

        ClaseUtil.crearStageModal(root, false);
//
        listaEntrada.clear();
        buscar();
//        listaEntrada.addAll(EntradaDiarioDao.getInstancia().getEntradasDiario());

    }

    private void editarEntrada() throws IOException {

        try {

            listaDetalleEntrada.clear();

            txtTotalDebito.setText(getTotalDebito().toString());
            txtTotalCredito.setText(getTotalCredito().toString());
//        Double diferencia = util.ClaseUtil.roundDouble(getTotalDebito() - getTotalCredito(), 0);

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/contabilidad/proceso/FXMLEntradaDiario.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            FXMLEntradaDiarioController controller = loader.getController();

            controller.setEditar(true);
            controller.setEntdiario(tbEntradaDiario.getSelectionModel().getSelectedItem());

            controller.llenarCampo();

            ClaseUtil.getStageModal(root);
            listaEntrada.clear();
            listaDetalleEntrada.clear();
            buscar();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) {

        try {

            if (!(tbEntradaDiario.getSelectionModel().getSelectedIndex() == -1)) {

                editarEntrada();
            } else {
                ClaseUtil.mensaje("Tiene que selccionar una entrada ");
            }

        } catch (Exception e) {
        }
    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {

        try {

            if (!(tbEntradaDiario.getSelectionModel().getSelectedIndex() == -1)) {

                EntradaDiario ent = tbEntradaDiario.getSelectionModel().getSelectedItem();

                if (ent.getAnulada()) {

                    ClaseUtil.mensaje("ESTA ENTRADA YA ESTA ANULADA");
                    return;
                }

                Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("SEGURO QUE QUIERE ANULAR LA ENTRADA NUMERO " + ent.getCodigo());

                if (ok.get() == ButtonType.OK || ok.get() == ButtonType.YES) {

                    ent.setAnulada(true);

                    EntradaDiarioDao.getInstancia().actualizar(ent);
                    tbEntradaDiario.refresh();

                }

            } else {

                ClaseUtil.mensaje("TIENE QUE SELECCIONAR UN REGISTRO");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Boolean validarSecuencia() {

        Boolean existe = false;
        SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                .getSecuenciaDocumento(VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo(), 6);

        if (!(secDoc == null)) {

            existe = true;
        }
        return existe;
    }

    private void buscar() {

        String parametro;
        listaEntrada.clear();
        tbEntradaDiario.setItems(listaEntrada);
        Date fechaini, fechafin;
        fechaini = ClaseUtil.asDate(dpFechaIniccio.getValue());
        fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());

        parametro = "  where  fecha between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaini) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fechafin) + "'";

        if (cbTipoAsiento.getSelectionModel().getSelectedIndex() != -1) {

            parametro += " and tipo_asiento=" + cbTipoAsiento.getSelectionModel().getSelectedItem().getCodigo();
        }

        listaEntrada.addAll(EntradaDiarioDao.getInstancia().getEntradasDiario(parametro));
        System.out.println("cantidad " + listaEntrada.size());
        tbEntradaDiario.setItems(listaEntrada);
        iniciazarFiltro();
    }

    @FXML
    private void btnExportXLSActionEvent(ActionEvent event) {
        
           try {

            util.ClaseUtil.exportarDAtos(tbEntradaDiario, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
