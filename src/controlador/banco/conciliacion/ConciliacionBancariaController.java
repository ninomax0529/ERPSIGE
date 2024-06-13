/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.banco.conciliacion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.banco.ManejoConciliacionBancaria;
import manejo.banco.ManejoMovimientoBanco;
import modelo.Articulo;
import modelo.ConciliacionBancaria;
import modelo.DetalleConciliacionBancariaBanco;
import modelo.DetalleConciliacionBancariaLibro;
import modelo.ReciboIngreso;
import reporte.banco.conciliacion.RptConciliacionBancaria;
import reporte.cxc.RptReciboIngreso;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ConciliacionBancariaController implements Initializable {

    @FXML
    private VBox vbVisorDeProgreso;
    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private HBox hbPermiso;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private JFXButton btnExportPdf;
    @FXML
    private TabPane tabPane;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<ConciliacionBancaria> tbConciliacion;
    @FXML
    private TableColumn<ConciliacionBancaria, String> tbcFecha;
    @FXML
    private TableColumn<ConciliacionBancaria, String> tbcBanco;
    @FXML
    private TableColumn<ConciliacionBancaria, String> tbcNumCuenta;
    @FXML
    private TableColumn<ConciliacionBancaria, String> tbcNumero;
    @FXML
    private TableColumn<ConciliacionBancaria, Double> tbcSaldoAConBanco;
    @FXML
    private TableColumn<ConciliacionBancaria, Double> tbcSaldoaConLibro;
    @FXML
    private TableColumn<ConciliacionBancaria, Double> tbcSaldoConciliado;
    @FXML
    private TableColumn<ConciliacionBancaria, ConciliacionBancaria> tbcVerDocumento;

    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TableView<DetalleConciliacionBancariaBanco> tbDetBanco;
    @FXML
    private TableColumn<DetalleConciliacionBancariaBanco, String> tbcDetBancoConcepto;
    @FXML
    private TableColumn<DetalleConciliacionBancariaBanco, String> tbcDetBancoNumdoc;
    @FXML
    private TableColumn<DetalleConciliacionBancariaBanco, Double> tbcDetBancoDebito;
    @FXML
    private TableColumn<DetalleConciliacionBancariaBanco, Double> tbcDetBancoCredito;
    @FXML
    private TableView<DetalleConciliacionBancariaLibro> tbDetLibro;
    @FXML
    private TableColumn<DetalleConciliacionBancariaLibro, String> tbcDetLibroConcepto;
    @FXML
    private TableColumn<DetalleConciliacionBancariaLibro, String> tbcDetLibroNumdoc;
    @FXML
    private TableColumn<DetalleConciliacionBancariaLibro, Double> tbcDetLibroDebito;
    @FXML
    private TableColumn<DetalleConciliacionBancariaLibro, Double> tbcDetLibroCredito;

    ObservableList<ConciliacionBancaria> listaConciliacion = FXCollections.observableArrayList();
    ObservableList<DetalleConciliacionBancariaBanco> listaConciliacionBanco = FXCollections.observableArrayList();
    ObservableList<DetalleConciliacionBancariaLibro> listaConciliacionLibro = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablaConciliacion();
        inicializarTablaConciliacionBanco();
        inicializarTablaConciliacionLibro();
    }

    public void inicializarTablaConciliacion() {

        try {

            listaConciliacion.clear();
            listaConciliacion.addAll(ManejoConciliacionBancaria.getInstancia().getConciliacionBancaria());

            tbcNumero.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tbcNumCuenta.setCellValueFactory(new PropertyValueFactory<>("cuentaBanco"));
            tbcSaldoAConBanco.setCellValueFactory(new PropertyValueFactory<>("saldoAConciliarBanco"));
            tbcSaldoaConLibro.setCellValueFactory(new PropertyValueFactory<>("saldoAConciliarLibro"));
            tbcSaldoConciliado.setCellValueFactory(new PropertyValueFactory<>("saldoConciliadoLibro"));

            tbcBanco.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getBanco() != null) {
                            property.setValue(cellData.getValue().getBanco().getNombre());
                        }
                        return property;
                    });

            tbcVerDocumento.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcVerDocumento.setCellFactory((TableColumn<ConciliacionBancaria, ConciliacionBancaria> param) -> {

                TableCell<ConciliacionBancaria, ConciliacionBancaria> cellsc = new TableCell<ConciliacionBancaria, ConciliacionBancaria>() {
                    @Override
                    public void updateItem(ConciliacionBancaria item, boolean empty) {
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

                                RptConciliacionBancaria.getInstancia().imprimirConciliacion(item);
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

            tbConciliacion.setItems(listaConciliacion);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTablaConciliacionLibro() {

        try {

            listaConciliacionLibro.clear();

            tbcDetLibroConcepto.setCellValueFactory(new PropertyValueFactory<>("concepto"));
            tbcDetLibroNumdoc.setCellValueFactory(new PropertyValueFactory<>("numeroDocumento"));

            tbcDetLibroDebito.setCellValueFactory(new PropertyValueFactory<>("debito"));
            tbcDetLibroCredito.setCellValueFactory(new PropertyValueFactory<>("credito"));

            tbDetLibro.setItems(listaConciliacionLibro);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarTablaConciliacionBanco() {

        try {

            listaConciliacionBanco.clear();

            tbcDetBancoConcepto.setCellValueFactory(new PropertyValueFactory<>("concepto"));
            tbcDetBancoNumdoc.setCellValueFactory(new PropertyValueFactory<>("numeroDocumento"));

            tbcDetBancoDebito.setCellValueFactory(new PropertyValueFactory<>("debito"));
            tbcDetBancoCredito.setCellValueFactory(new PropertyValueFactory<>("credito"));

            tbDetBanco.setItems(listaConciliacionBanco);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/banco/conciliacion/RegistrarConciliacionBancaria.fxml"));

        ClaseUtil.getStageModal(root);

        listaConciliacion.clear();

        listaConciliacion.addAll(ManejoConciliacionBancaria.getInstancia().getConciliacionBancaria());
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {
    }

    @FXML
    private void btnExportPdfActionEvent(ActionEvent event) {
    }

    @FXML
    private void tbConciliacionMouseClick(MouseEvent event) {

        if (!(tbConciliacion.getSelectionModel().getSelectedIndex() == -1)) {

            int conci = tbConciliacion.getSelectionModel().getSelectedItem().getCodigo();
            listaConciliacionBanco.clear();
            listaConciliacionLibro.clear();
            listaConciliacionBanco.addAll(ManejoConciliacionBancaria.getInstancia().getConciliacionBancariaBanco(conci));
            listaConciliacionLibro.addAll(ManejoConciliacionBancaria.getInstancia().getConciliacionBancariaLibro(conci));

            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(1);
            }
        }
    }

    @FXML
    private void tbMovimientoBancoMouseClick(MouseEvent event) {

    }

}
