/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.consulta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manejo.contabilidadd.EntradaDiarioDao;
import modelo.DetalleEntradaDiario;
import modelo.EntradaDiario;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author luis
 */
public class FXMLEntradaDiarioConsController implements Initializable {

    @FXML
    private JFXButton btnEntradaDiario;
    @FXML
    private TableColumn<EntradaDiario, String> tbcCodigo;
    @FXML
    private TableColumn<EntradaDiario, Date> tbcFecha;
    @FXML
    private TableColumn<EntradaDiario, String> tbcTipoEntrada;
    @FXML
    private TableColumn<DetalleEntradaDiario, String> tbcCuenta;
    @FXML
    private TableColumn<DetalleEntradaDiario, String> tbcDescripcion;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbcDebito;
    @FXML
    private TableColumn<DetalleEntradaDiario, Double> tbcCredito;

    ObservableList<EntradaDiario> listaEntrada = FXCollections.observableArrayList();
    ObservableList<DetalleEntradaDiario> listaDetalleEntrada = FXCollections.observableArrayList();
    @FXML
    private TableView<EntradaDiario> tbEntradaDiario;
    @FXML
    private TableView<DetalleEntradaDiario> tbDetalleEntrada;

    private EntradaDiario entradaDiario;
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
    private JFXTextArea txtConcepto;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXTextField txtDocumento;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXDatePicker dpFechaContable;
    @FXML
    private JFXButton btnVerDocumento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTabla();
        iniciazarFiltro();
        dpFechaIniccio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

        // TODO
    }

    private void iniciazarFiltro() {

        FilteredList<EntradaDiario> filteredData = new FilteredList<>(listaEntrada, p -> true);

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
//                } else {
//                    if (catalogo.getCuenta().toLowerCase().contains(lowerCaseFilter)) {
//                        return true; // Filter matches first name.
//                    } else if (catalogo.getCuenta().toLowerCase().contains(lowerCaseFilter)) {
//                        return true; // Filter matches last name.
//                    }
//                }
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

    @FXML
    private void btnEntradaDiarioActionEvent(ActionEvent event) throws IOException {

//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/vista/entradaDiario/FXMLEntradaDiario.fxml"));
//        loader.load();
        Parent root = FXMLLoader.load(getClass().getResource("/vista/contabilidad/proceso/FXMLEntradaDiario.fxml"));

//        Parent root = loader.getRoot();
        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setMaximized(false);
        stage.setResizable(false);

        stage.setScene(scene);
//         FXMLEntradaDiarioController entradaDiarioController = loader.getController();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

//        entradaDiarioController.setEntdiario(getEntradaDiario());
//        entradaDiarioController.getTxtDocumento().setText(entradaDiarioController.getEntdiario().getDocumento());
//        System.out.println("entrada iario "+entradaDiarioController.getEntdiario().getDocumento());
        listaEntrada.clear();
        listaEntrada.setAll(EntradaDiarioDao.getInstancia().getEntradasDiario());
    }

    public void iniciarTabla() {

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        tbcTipoEntrada.setCellValueFactory(new PropertyValueFactory<>("TipoDocumento"));

        tbcCuenta.setCellValueFactory(new PropertyValueFactory<>("Cuenta"));

        tbcDescripcion.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getDescripcionCuenta() != null) {
                        property.setValue(cellData.getValue().getDescripcionCuenta());
                    }
                    return property;
                });

        tbcTipoEntrada.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getTipoDocumento() != null) {
                        property.setValue(cellData.getValue().getTipoDocumento().getNombre());
                    }
                    return property;
                });
//        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        tbcCredito.setCellValueFactory(new PropertyValueFactory<>("Credito"));
        tbcDebito.setCellValueFactory(new PropertyValueFactory<>("Debito"));

        listaEntrada.addAll(EntradaDiarioDao.getInstancia().getEntradasDiario());
        tbEntradaDiario.setItems(listaEntrada);

    }

    @FXML
    private void tbEntradaDiarioActionEvent(MouseEvent event) {

        listaDetalleEntrada.clear();

        if (!(tbEntradaDiario.getSelectionModel().getSelectedIndex() == -1)) {
            btnEditar.setDisable(false);

            setEntradaDiario(tbEntradaDiario.getSelectionModel().getSelectedItem());
            txtConcepto.setText(getEntradaDiario().getConcepto());
            listaDetalleEntrada.addAll(EntradaDiarioDao.getInstancia().getDetalleEntradaDiario(entradaDiario));
            System.out.println("total " + listaDetalleEntrada.size());
            tbDetalleEntrada.setItems(listaDetalleEntrada);
            txtTotalCredito.setText(String.valueOf(getTotalCredito()));
            txtTotalDebito.setText(String.valueOf(getTotalDebito()));
            txtDocumento.setText(entradaDiario.getNumeroDocumento());
            dpFechaContable.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getEntradaDiario().getFecha()));

        } else {
            btnEditar.setDisable(true);

        }

    }

    public Double getTotalCredito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detalle = tbDetalleEntrada.getItems();

        for (int i = 0; i < detalle.size(); i++) {
            System.out.println("Size: " + detalle.size());
            total += detalle.get(i).getCredito();
            System.out.println("TDeB: " + total);
        }

        return total;

    }

    public Double getTotalDebito() {

        Double total = 0.00;

        List<DetalleEntradaDiario> detalle = tbDetalleEntrada.getItems();

        for (int i = 0; i < detalle.size(); i++) {
            total += detalle.get(i).getDebito();
        }

        return total;

    }

    public EntradaDiario getEntradaDiario() {
        return entradaDiario;
    }

    public void setEntradaDiario(EntradaDiario entradaDiario) {
        this.entradaDiario = entradaDiario;
    }

    @FXML
    private void btnBuscarEntradaActionEvent(ActionEvent event) {

        buscarEntrada();
        iniciazarFiltro();
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) {

        getEntradaDiario().setNumeroDocumento(txtDocumento.getText());
        getEntradaDiario().setFecha(ClaseUtil.asDate(dpFechaContable.getValue()));
        getEntradaDiario().setConcepto(txtConcepto.getText());
        EntradaDiarioDao.getInstancia().salvar(getEntradaDiario());

//        if (getEntradaDiario().getTipoDocumento().getCodigo() == 1) {
//
//            FacturaSuplidor facturaSuplidor = FacturaSuplidorDao.getInstancia().getFacturaSuplidor(getEntradaDiario().getDocumento());
//
//            facturaSuplidor.setNoFactura(getEntradaDiario().getNumeroDocumento());
//            facturaSuplidor.setFecha(getEntradaDiario().getFecha());
//            FacturaSuplidorDao.getInstancia().actualizar(facturaSuplidor);
//
//        } else if (getEntradaDiario().getTipoDocumento().getCodigo() == 2) {
//
//            Cheque cheque = ChequeDao.getInstancia().getCheque(getEntradaDiario().getDocumento());
//
//            cheque.setNumero(getEntradaDiario().getNumeroDocumento());
//            cheque.setFecha(getEntradaDiario().getFecha());
//            ChequeDao.getInstancia().salvar(cheque);
//
//        }

        txtConcepto.clear();
        txtDocumento.clear();
        listaDetalleEntrada.clear();
        tbDetalleEntrada.setItems(listaDetalleEntrada);
        buscarEntrada();

    }

    private void buscarEntrada() {

        listaEntrada.clear();
        tbEntradaDiario.setItems(listaEntrada);
        listaDetalleEntrada.clear();
        tbDetalleEntrada.setItems(listaDetalleEntrada);
        Date fechaini, fechafin;
        fechaini = ClaseUtil.asDate(dpFechaIniccio.getValue());
        fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());
        listaEntrada.addAll(EntradaDiarioDao.getInstancia().getEntradasDiario(fechaini, fechafin));
        System.out.println("cantidad " + listaEntrada.size());
        tbEntradaDiario.setItems(listaEntrada);
    }

    @FXML
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

}
