/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.banco.conciliacion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.banco.movimiento.ConsultaCuentaBancoController;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.banco.ManejoConceptoConciliacionBanco;
import manejo.banco.ManejoConciliacionBancaria;
import modelo.Catalogo;
import modelo.ConceptoConciliacionBancaria;
import modelo.ConciliacionBancaria;
import modelo.CuentaBanco;
import modelo.DetalleConciliacionBancariaBanco;
import modelo.DetalleConciliacionBancariaLibro;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistrarConciliacionBancariaController implements Initializable {

    /**
     * @return the cuentaBanco
     */
    public CuentaBanco getCuentaBanco() {
        return cuentaBanco;
    }

    /**
     * @param cuentaBanco the cuentaBanco to set
     */
    public void setCuentaBanco(CuentaBanco cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }

    /**
     * @return the catalogo
     */
    public Catalogo getCatalogo() {
        return catalogo;
    }

    /**
     * @param catalogo the catalogo to set
     */
    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXTextField txtCuenta;
    @FXML
    private JFXButton btnCatalogo;
    @FXML
    private TextField txtSaldoLibro;
    @FXML
    private JFXComboBox<ConceptoConciliacionBancaria> cbConceptoLibroMas;
    @FXML
    private TextField txtNumeroDocLibroMas;
    @FXML
    private TextField txtMontoLibroMas;
    @FXML
    private JFXButton btnAgrgarMasLibro;
    @FXML
    private TextField txtMasSubTotal;
    @FXML
    private JFXButton btnEliminarLibroMas;
    @FXML
    private JFXComboBox<ConceptoConciliacionBancaria> cbConceptoLibroMenos;
    @FXML
    private TextField txtNumDocLibroMenos;
    @FXML
    private TextField txtMontoLibroMenos;
    @FXML
    private JFXButton btnAgrgarMenosLibro;
    @FXML
    private TextField txtMenosSubTotal;
    @FXML
    private JFXButton btnEliminarLibroMenos;
    @FXML
    private TextField txtSaldoConciliado;
    @FXML
    private JFXButton btnGuaradar;
    @FXML
    private TextField txtSaldoBanco;
    @FXML
    private JFXComboBox<ConceptoConciliacionBancaria> cbConceptoBancoMas;
    @FXML
    private TextField txtNumDocBancoMas;
    @FXML
    private TextField txtMontoBancoMas;
    @FXML
    private JFXButton btnAgrgarMasBanco;

    @FXML
    private TextField txtMasSubTotalBanco;
    @FXML
    private JFXButton btnEliminarBancoMas;
    @FXML
    private JFXComboBox<ConceptoConciliacionBancaria> cbConceptoBancoMenos;
    @FXML
    private TextField txtNumDocBancoMenos;
    @FXML
    private TextField txtMontoBancoMenos;
    @FXML
    private JFXButton btnAgrgarMenosBanco;
    @FXML
    private TextField txtMenosSubTotalBanco;
    @FXML
    private JFXButton btnEliminarBancoMenos;
    @FXML
    private TextField txtSaldoConciliadoBanco;
    @FXML
    private TableView<DetalleConciliacionBancariaLibro> tbMasConciliacionLibro;
    @FXML
    private TableColumn<DetalleConciliacionBancariaLibro, String> tbcMasConcLibro;
    @FXML
    private TableColumn<DetalleConciliacionBancariaLibro, Double> tbcMasMontoConcLibro;
    @FXML
    private TableView<DetalleConciliacionBancariaLibro> tbMenosConciliacionLibro;
    @FXML
    private TableColumn<DetalleConciliacionBancariaLibro, String> tbcMenosConcLibro;
    @FXML
    private TableColumn<DetalleConciliacionBancariaLibro, Double> tbcMenosMontoConcLibro;
    @FXML
    private TableView<DetalleConciliacionBancariaBanco> tbMasConciliacionBanco;
    @FXML
    private TableColumn<DetalleConciliacionBancariaBanco, String> tbcMasConcBanco;
    @FXML
    private TableColumn<DetalleConciliacionBancariaBanco, Double> tbcMasMontoConcBanco;
    @FXML
    private TableView<DetalleConciliacionBancariaBanco> tbMenosConciliacionBanco;
    @FXML
    private TableColumn<DetalleConciliacionBancariaBanco, String> tbcMenosConcBanco;
    @FXML
    private TableColumn<DetalleConciliacionBancariaBanco, Double> tbcMenosMontoConcBanco;
    @FXML
    private TableColumn<DetalleConciliacionBancariaLibro, String> tbcMasNumeroConcLibro;
    @FXML
    private TableColumn<DetalleConciliacionBancariaLibro, String> tbcMenosNumeroConcLibro;
    @FXML
    private TableColumn<DetalleConciliacionBancariaBanco, String> tbcMasNumeroConcBanco;
    @FXML
    private TableColumn<DetalleConciliacionBancariaBanco, String> tbcMenosNumeroConcBanco;

    ObservableList<DetalleConciliacionBancariaBanco> listaDetConciliacionBancoMas = FXCollections.observableArrayList();
    ObservableList<DetalleConciliacionBancariaLibro> listaDetConciliacionLibroMas = FXCollections.observableArrayList();
    ObservableList<DetalleConciliacionBancariaBanco> listaDetConciliacionBancoMenos = FXCollections.observableArrayList();
    ObservableList<DetalleConciliacionBancariaLibro> listaDetConciliacionLibroMenos = FXCollections.observableArrayList();
    ObservableList<ConceptoConciliacionBancaria> listaConceptoConciBancariaMenos = FXCollections.observableArrayList();
    ObservableList<ConceptoConciliacionBancaria> listaConceptoConciBancariaMas = FXCollections.observableArrayList();
    ObservableList<ConceptoConciliacionBancaria> listaConceptoConciBancariaBancoMenos = FXCollections.observableArrayList();
    ObservableList<ConceptoConciliacionBancaria> listaConceptoConciBancariaBancoMas = FXCollections.observableArrayList();

    ConciliacionBancaria conciliacion;
    Date fechaini, fechafin;

    DetalleConciliacionBancariaBanco detBanco = null;
    DetalleConciliacionBancariaLibro detLibro = null;
    private Catalogo catalogo;
    private CuentaBanco cuentaBanco;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conciliacion = new ConciliacionBancaria();
        inicializarCombox();
        iniciarTablaBancoMasMenos();
        iniciarTablaLibroMasMenos();
        Date fecha = ClaseUtil.getFechaUltimoDiasMesAnterior(new Date());
        dpFechaFinal.setValue(ClaseUtil.convertToLocalDateViaMilisecond(fecha));
        fechafin = fecha;
    }

    public void iniciarTablaLibroMasMenos() {

        tbcMasConcLibro.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        tbcMasMontoConcLibro.setCellValueFactory(new PropertyValueFactory<>("debito"));

        tbcMenosConcLibro.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        tbcMenosMontoConcLibro.setCellValueFactory(new PropertyValueFactory<>("credito"));
        tbcMasNumeroConcLibro.setCellValueFactory(new PropertyValueFactory<>("numeroDocumento"));
        tbcMenosNumeroConcLibro.setCellValueFactory(new PropertyValueFactory<>("numeroDocumento"));

        tbMasConciliacionLibro.setItems(listaDetConciliacionLibroMas);
        tbMenosConciliacionLibro.setItems(listaDetConciliacionLibroMenos);

        tbMenosConciliacionLibro.setEditable(true);
        tbMasConciliacionLibro.setEditable(true);

        tbcMasConcLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        tbcMenosConcLibro.setCellFactory(TextFieldTableCell.forTableColumn());

        tbcMasNumeroConcLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        tbcMenosNumeroConcLibro.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void iniciarTablaBancoMasMenos() {

        tbcMasConcBanco.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        tbcMasMontoConcBanco.setCellValueFactory(new PropertyValueFactory<>("debito"));

        tbcMenosConcBanco.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        tbcMenosMontoConcBanco.setCellValueFactory(new PropertyValueFactory<>("credito"));
        tbcMasNumeroConcBanco.setCellValueFactory(new PropertyValueFactory<>("numeroDocumento"));
        tbcMenosNumeroConcBanco.setCellValueFactory(new PropertyValueFactory<>("numeroDocumento"));

        tbMasConciliacionBanco.setItems(listaDetConciliacionBancoMas);
        tbMenosConciliacionBanco.setItems(listaDetConciliacionBancoMenos);

        tbMasConciliacionBanco.setEditable(true);
        tbMenosConciliacionBanco.setEditable(true);
        tbcMasNumeroConcBanco.setCellFactory(TextFieldTableCell.forTableColumn());
        tbcMenosNumeroConcBanco.setCellFactory(TextFieldTableCell.forTableColumn());

        tbcMasConcBanco.setCellFactory(TextFieldTableCell.forTableColumn());
        tbcMenosConcBanco.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    private void inicializarCombox() {

        listaConceptoConciBancariaMenos.addAll(ManejoConceptoConciliacionBanco.getInstancia()
                .getListaConcepto("libro", "-"));

        listaConceptoConciBancariaMas.addAll(ManejoConceptoConciliacionBanco.getInstancia()
                .getListaConcepto("libro", "+"));

        listaConceptoConciBancariaBancoMenos.addAll(ManejoConceptoConciliacionBanco.getInstancia()
                .getListaConcepto("banco", "-"));

        listaConceptoConciBancariaBancoMas.addAll(ManejoConceptoConciliacionBanco.getInstancia()
                .getListaConcepto("banco", "+"));

        cbConceptoLibroMas.setConverter(new StringConverter<ConceptoConciliacionBancaria>() {

            @Override
            public String toString(ConceptoConciliacionBancaria cuentaBanco) {
                return cuentaBanco.getNombre();
            }

            @Override
            public ConceptoConciliacionBancaria fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbConceptoLibroMenos.setConverter(new StringConverter<ConceptoConciliacionBancaria>() {

            @Override
            public String toString(ConceptoConciliacionBancaria cuentaBanco) {
                return cuentaBanco.getNombre();
            }

            @Override
            public ConceptoConciliacionBancaria fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbConceptoBancoMas.setConverter(new StringConverter<ConceptoConciliacionBancaria>() {

            @Override
            public String toString(ConceptoConciliacionBancaria cuentaBanco) {
                return cuentaBanco.getNombre();
            }

            @Override
            public ConceptoConciliacionBancaria fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbConceptoBancoMenos.setConverter(new StringConverter<ConceptoConciliacionBancaria>() {

            @Override
            public String toString(ConceptoConciliacionBancaria cuentaBanco) {
                return cuentaBanco.getNombre();
            }

            @Override
            public ConceptoConciliacionBancaria fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbConceptoLibroMas.setItems(listaConceptoConciBancariaMas);
        cbConceptoLibroMenos.setItems(listaConceptoConciBancariaMenos);

        cbConceptoBancoMas.setItems(listaConceptoConciBancariaBancoMas);
        cbConceptoBancoMenos.setItems(listaConceptoConciBancariaBancoMenos);

    }

    @FXML
    private void dpFechaFinalActionEvent(ActionEvent event) {

    }

    @FXML
    private void btnCatalogoActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/banco/movimiento/ConsultaCuentaBanco.fxml"));

        loader.load();

        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        ConsultaCuentaBancoController catalogoController = loader.getController();

        if (!(catalogoController.getCatalogo() == null)) {

            setCatalogo(catalogoController.getCatalogo());
            txtCuenta.setText(catalogoController.getCuenta().getBanco().getNombre() + "-" + getCatalogo().getCuenta());
            setCuentaBanco(catalogoController.getCuenta());
            System.out.println("Cuenta " + txtCuenta.getText());

        }

//        
    }

    @FXML
    private void cbConceptoLibroMasActionEvent(ActionEvent event) {

        if (!(cbConceptoLibroMas.getSelectionModel().getSelectedIndex() == -1)) {

            txtNumeroDocLibroMas.requestFocus();
        }
    }

    @FXML
    private void btnAgrgarMasLibroActionEvent(ActionEvent event) {
        
          if (txtSaldoLibro.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar el saldo del libro de banco ");
            txtSaldoLibro.requestFocus();
            return;
        }

        if (cbConceptoLibroMas.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje("Tiene que seleccionar un concepto");
            cbConceptoLibroMas.requestFocus();
            return;
        }

        if (txtNumeroDocLibroMas.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar el numero del documento ");
            txtNumeroDocLibroMas.requestFocus();
            return;
        }

        if (txtMontoLibroMas.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar el monto de la transacion ");
            txtMontoLibroMas.requestFocus();
            return;
        }

        detLibro = new DetalleConciliacionBancariaLibro();
        detLibro.setConcepto(cbConceptoLibroMas.getSelectionModel().getSelectedItem().getNombre());
        detLibro.setDebito(Double.parseDouble(txtMontoLibroMas.getText()));
        detLibro.setCredito(0.00);
        detLibro.setEntidad("l");
        detLibro.setFecha(new Date());
        detLibro.setConciliacionBancario(conciliacion);
        detLibro.setNombreOperacion("op");
        detLibro.setNumeroDocumento(txtNumeroDocLibroMas.getText());

        listaDetConciliacionLibroMas.add(detLibro);

        txtMasSubTotal.setText(getSubTotalMasLibro().toString());

        txtSaldoConciliado.setText(getSaldoConciliadoLibro().toString());

        cbConceptoLibroMas.getSelectionModel().clearSelection();
        txtNumeroDocLibroMas.clear();
        txtMontoLibroMas.clear();
    }

    @FXML
    private void btnEliminarLibroMasActionEvent(ActionEvent event) {

        if (!(tbMasConciliacionLibro.getSelectionModel().getSelectedIndex() == -1)) {

            listaDetConciliacionLibroMas.remove(tbMasConciliacionLibro.getSelectionModel().getSelectedIndex());

            txtMasSubTotal.setText(getSubTotalMasLibro().toString());
            txtSaldoConciliado.setText(getSaldoConciliadoLibro().toString());
        }
    }

    @FXML
    private void cbConceptoLibroMenosActionEvent(ActionEvent event) {
        if (!(cbConceptoLibroMenos.getSelectionModel().getSelectedIndex() == -1)) {

            txtNumDocLibroMenos.requestFocus();
        }
    }

    @FXML
    private void btnAgrgarMenosLibroActionEvent(ActionEvent event) {
        
           if (txtSaldoLibro.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar el saldo del libro de banco ");
            txtSaldoLibro.requestFocus();
            return;
        }

        if (cbConceptoLibroMenos.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje("Tiene que seleccionar un concepto");
            cbConceptoLibroMenos.requestFocus();
            return;
        }

        if (txtNumDocLibroMenos.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar el numero del documento ");
            txtNumDocLibroMenos.requestFocus();
            return;
        }

        if (txtMontoLibroMenos.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar el monto de la transacion ");
            txtNumDocLibroMenos.requestFocus();
            return;
        }

        detLibro = new DetalleConciliacionBancariaLibro();
        detLibro.setConcepto(cbConceptoLibroMenos.getSelectionModel().getSelectedItem().getNombre());
        detLibro.setDebito(0.00);
        detLibro.setCredito(Double.parseDouble(txtMontoLibroMenos.getText()));
        detLibro.setEntidad("l");
        detLibro.setFecha(new Date());
        detLibro.setConciliacionBancario(conciliacion);
        detLibro.setNombreOperacion("op");
        detLibro.setNumeroDocumento(txtNumDocLibroMenos.getText());

        listaDetConciliacionLibroMenos.add(detLibro);

        txtMenosSubTotal.setText(getSubTotalMenosLibro().toString());

        txtSaldoConciliado.setText(getSaldoConciliadoLibro().toString());

        cbConceptoLibroMenos.getSelectionModel().clearSelection();
        txtNumDocLibroMenos.clear();
        txtMontoLibroMenos.clear();
    }

    @FXML
    private void btnEliminarLibroMenosActionEvent(ActionEvent event) {
        
        
        if (!(tbMenosConciliacionLibro.getSelectionModel().getSelectedIndex() == -1)) {

            listaDetConciliacionLibroMenos.remove(tbMenosConciliacionLibro.getSelectionModel().getSelectedIndex());

            txtMenosSubTotal.setText(getSubTotalMenosLibro().toString());
            txtSaldoConciliado.setText(getSaldoConciliadoLibro().toString());
        }
    }

    @FXML
    private void btnGuaradarActionEvent(ActionEvent event) {

        try {

            Double diferencia = ClaseUtil.roundDouble(getSaldoConciliadoBanco() - getSaldoConciliadoLibro(), 0);

            if (diferencia != 0) {
                ClaseUtil.mensaje("Los saldo no estan conciliado");
                return;
            }

            conciliacion = guardarConciliacionBancanria();
//            RptConciliacionBancaria.getInstancia().imprimirConciliacion(conciliacion);

            conciliacion = new ConciliacionBancaria();

//            ClaseUtil.mensaje("Conciliacion guardada exitosamente ");
//            conciliarLibroConBanco();
//            conciliarBancoConLibro();
//            guardarConciliacion();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void cbConceptoBancoMasActionevent(ActionEvent event) {

        if (!(cbConceptoBancoMas.getSelectionModel().getSelectedIndex() == -1)) {

            txtNumDocBancoMas.requestFocus();
        }
    }

    @FXML
    private void btnAgrgarMasBancoActionEvent(ActionEvent event) {

        if (txtSaldoBanco.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar el saldo del estado bancario ");
            txtSaldoBanco.requestFocus();
            return;
        }

        if (cbConceptoBancoMas.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje("Tiene que seleccionar un concepto");
            cbConceptoBancoMas.requestFocus();
            return;
        }

        if (txtNumDocBancoMas.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar el numero del documento ");
            txtNumDocBancoMas.requestFocus();
            return;
        }

        if (txtMontoBancoMas.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar el monto de la transacion ");
            txtMontoBancoMas.requestFocus();
            return;
        }

        detBanco = new DetalleConciliacionBancariaBanco();
        detBanco.setConcepto(cbConceptoBancoMas.getSelectionModel().getSelectedItem().getNombre());
        detBanco.setDebito(Double.parseDouble(txtMontoBancoMas.getText()));
        detBanco.setCredito(0.00);
        detBanco.setEntidad("b");
        detBanco.setFecha(new Date());
        detBanco.setConciliacionBancario(conciliacion);
        detBanco.setNombreOperacion("op");
        detBanco.setNumeroDocumento(txtNumDocBancoMas.getText());

        listaDetConciliacionBancoMas.add(detBanco);

        txtMasSubTotalBanco.setText(getSubTotalMasBanco().toString());

        txtSaldoConciliadoBanco.setText(getSaldoConciliadoBanco().toString());
        cbConceptoBancoMas.getSelectionModel().clearSelection();
        txtNumDocBancoMas.clear();
        txtMontoBancoMas.clear();
    }

    @FXML
    private void btnEliminarBancoMasActionEvent(ActionEvent event) {
        
        
        if (!(tbMasConciliacionBanco.getSelectionModel().getSelectedIndex() == -1)) {

            listaDetConciliacionBancoMas.remove(tbMasConciliacionBanco.getSelectionModel().getSelectedIndex());

            txtMasSubTotalBanco.setText(getSubTotalMasBanco().toString());
            txtSaldoConciliadoBanco.setText(getSaldoConciliadoBanco().toString());
        }
    }

    @FXML
    private void cbConceptoBancoMenosActionevent(ActionEvent event) {

        if (!(cbConceptoBancoMenos.getSelectionModel().getSelectedIndex() == -1)) {

            txtNumDocBancoMenos.requestFocus();
        }
    }

    @FXML
    private void btnAgrgarMenosBancoActionevent(ActionEvent event) {

        if (txtSaldoBanco.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar el saldo del estado bancario ");
            txtSaldoBanco.requestFocus();
            return;
        }

        if (cbConceptoBancoMenos.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje("Tiene que seleccionar un concepto");
            cbConceptoBancoMenos.requestFocus();
            return;
        }

        if (txtNumDocBancoMenos.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar el numero del documento ");
            txtNumDocBancoMenos.requestFocus();
            return;
        }

        if (txtMontoBancoMenos.getText().isEmpty()) {
            ClaseUtil.mensaje("Tiene que digitar el monto de la transacion ");
            txtMontoBancoMenos.requestFocus();
            return;
        }

        detBanco = new DetalleConciliacionBancariaBanco();
        detBanco.setConcepto(cbConceptoBancoMenos.getSelectionModel().getSelectedItem().getNombre());
        detBanco.setDebito(0.00);
        detBanco.setCredito(Double.parseDouble(txtMontoBancoMenos.getText()));
        detBanco.setEntidad("b");
        detBanco.setFecha(new Date());
        detBanco.setConciliacionBancario(conciliacion);
        detBanco.setNombreOperacion("op");
        detBanco.setNumeroDocumento(txtNumDocBancoMenos.getText());

        listaDetConciliacionBancoMenos.add(detBanco);

        txtMenosSubTotalBanco.setText(getSubTotalMenosBanco().toString());

        txtSaldoConciliadoBanco.setText(getSaldoConciliadoBanco().toString());

        cbConceptoBancoMenos.getSelectionModel().clearSelection();
        txtNumDocBancoMenos.clear();
        txtMontoBancoMenos.clear();

    }

    @FXML
    private void btnEliminarBancoMenosActionEvent(ActionEvent event) {
        
        
        if (!(tbMenosConciliacionBanco.getSelectionModel().getSelectedIndex() == -1)) {

            listaDetConciliacionBancoMenos.remove(tbMenosConciliacionBanco.getSelectionModel().getSelectedIndex());

            txtMenosSubTotalBanco.setText(getSubTotalMenosBanco().toString());
            txtSaldoConciliadoBanco.setText(getSaldoConciliadoBanco().toString());

        }

    }

    private void limpiar() {

        txtCuenta.clear();
        txtMasSubTotal.clear();
        txtMenosSubTotal.clear();
        txtMontoBancoMenos.clear();
        txtMontoLibroMas.clear();
        txtMasSubTotalBanco.clear();
        txtMontoLibroMenos.clear();
        txtSaldoBanco.clear();
        txtSaldoLibro.clear();
        txtSaldoConciliado.clear();
        txtMenosSubTotalBanco.clear();
        txtSaldoConciliadoBanco.clear();

        listaDetConciliacionBancoMas.clear();
        listaDetConciliacionBancoMenos.clear();
        listaDetConciliacionLibroMas.clear();
        listaDetConciliacionLibroMenos.clear();

    }

    public Double getSubTotalMasLibro() {

        Double total = 0.00;

        for (DetalleConciliacionBancariaLibro det : listaDetConciliacionLibroMas) {

            total += det.getDebito();
            System.out.println("TDeB: " + total);
        }

        return ClaseUtil.roundDouble(total, 2);

    }

    public Double getSubTotalMasBanco() {

        Double total = 0.00;

        for (DetalleConciliacionBancariaBanco det : listaDetConciliacionBancoMas) {

            total += det.getDebito();
            System.out.println("TDeB: " + total);
        }

        return ClaseUtil.roundDouble(total, 2);

    }

    public Double getSubTotalMenosLibro() {

        Double total = 0.00;

        for (DetalleConciliacionBancariaLibro det : listaDetConciliacionLibroMenos) {

            total += det.getCredito();
            System.out.println("TDeB: " + total);
        }

        return ClaseUtil.roundDouble(total, 2);

    }

    public Double getSubTotalMenosBanco() {

        Double total = 0.00;

        for (DetalleConciliacionBancariaBanco det : listaDetConciliacionBancoMenos) {

            total += det.getCredito();
            System.out.println("TDeB: " + total);
        }

        return ClaseUtil.roundDouble(total, 2);

    }

    public Double getSaldoConciliadoBanco() {

        Double total = 0.00;

        total = (getSubTotalMasBanco() + Double.parseDouble(txtSaldoBanco.getText())) - getSubTotalMenosBanco();

        return ClaseUtil.roundDouble(total, 2);

    }

    public Double getSaldoConciliadoLibro() {

        Double total = 0.00;

        total = (Double.parseDouble(txtSaldoLibro.getText()) + getSubTotalMasLibro()) - getSubTotalMenosLibro();

        return ClaseUtil.roundDouble(total, 2);

    }

    private ConciliacionBancaria guardarConciliacionBancanria() {

        fechafin = ClaseUtil.asDate(dpFechaFinal.getValue());
        System.out.println("Fecha " + dpFechaFinal.getValue());
        fechaini = ClaseUtil.getFechaPrimerDiasDelMes(fechafin);
        conciliacion.setFechaDesde(fechaini);
        conciliacion.setFechaHasta(fechafin);
        conciliacion.setBanco(getCuentaBanco().getBanco());
        conciliacion.setCuentaBanco(getCuentaBanco().getNumeroCuenta());
        conciliacion.setFechaRegistro(new Date());
        conciliacion.setNombreBanco(getCuentaBanco().getBanco().getNombre());
        conciliacion.setSaldoConciliadoBanco(getSaldoConciliadoBanco());
        conciliacion.setSaldoConciliadoLibro(getSaldoConciliadoLibro());
        conciliacion.setSaldoAConciliarBanco(Double.parseDouble(txtSaldoBanco.getText()));
        conciliacion.setSaldoAConciliarLibro(Double.parseDouble(txtSaldoLibro.getText()));
        conciliacion.setAnulada(false);
        conciliacion.setPeriodo(1);
        conciliacion.setNombreDelPeriodo("na");
        conciliacion.setCuentaLibro(getCuentaBanco().getCatalogo().getCuenta());
        conciliacion.setNombreMes(ClaseUtil.getNombreDelMes(fechafin));
        conciliacion.setMes(ClaseUtil.getMes(fechafin));
        conciliacion.setFecha(new Date());
        conciliacion.setUsuario(VariablesGlobales.USUARIO);
        conciliacion.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

        listaDetConciliacionBancoMenos.addAll(listaDetConciliacionBancoMas);
        listaDetConciliacionLibroMenos.addAll(listaDetConciliacionLibroMas);

//        conciliacion.setDetalleConciliacionBancariaLibroCollection(listaDetConciliacionLibroMas);
        conciliacion.setDetalleConciliacionBancariaLibroCollection(listaDetConciliacionLibroMenos);
//        conciliacion.setDetalleConciliacionBancariaBancoCollection(listaDetConciliacionBancoMas);
        conciliacion.setDetalleConciliacionBancariaBancoCollection(listaDetConciliacionBancoMenos);

        conciliacion = ManejoConciliacionBancaria.getInstancia().salvar(conciliacion);

        limpiar();
        return conciliacion;
    }

}
