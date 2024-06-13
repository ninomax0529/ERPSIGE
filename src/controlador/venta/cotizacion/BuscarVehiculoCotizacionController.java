/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.cotizacion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import manejo.contrato.ManejoDatosDeVehiculo;
import modelo.ContratoDeServicio;
import modelo.DatosDeVehiculo;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class BuscarVehiculoCotizacionController implements Initializable {

    /**
     * @return the codigoClienete
     */
    public int getCodigoClienete() {
        return codigoClienete;
    }

    /**
     * @param codigoClienete the codigoClienete to set
     */
    public void setCodigoClienete(int codigoClienete) {
        this.codigoClienete = codigoClienete;
    }

    /**
     * @return the listaVehiculoSeleccionado
     */
    public List<DatosDeVehiculo> getListaVehiculoSeleccionado() {
        return listaVehiculoSeleccionado;
    }

    /**
     * @param listaVehiculoSeleccionado the listaVehiculoSeleccionado to set
     */
    public void setListaVehiculoSeleccionado(List<DatosDeVehiculo> listaVehiculoSeleccionado) {
        this.listaVehiculoSeleccionado = listaVehiculoSeleccionado;
    }

    /**
     * @return the datosDeVehiculo
     */
    public DatosDeVehiculo getDatosDeVehiculo() {
        return datosDeVehiculo;
    }

    /**
     * @param datosDeVehiculo the datosDeVehiculo to set
     */
    public void setDatosDeVehiculo(DatosDeVehiculo datosDeVehiculo) {
        this.datosDeVehiculo = datosDeVehiculo;
    }

    @FXML
    private JFXTextField txtFiltrar;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private TableView<DatosDeVehiculo> tbVehiculos;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcVehiculo;
    @FXML
    private TableColumn<DatosDeVehiculo, String> tbcPlaca;
    @FXML
    private TableColumn<DatosDeVehiculo, DatosDeVehiculo> tbcSeleccionar;

    ObservableList<DatosDeVehiculo> lista = FXCollections.observableArrayList();

    private List<DatosDeVehiculo> listaVehiculoSeleccionado = new ArrayList();

    private DatosDeVehiculo datosDeVehiculo;
    private int codigoClienete = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
        iniciazarFiltro();
        
    }

    public void inicializarTabla() {

        try {

            tbVehiculos.setItems(lista);
//            lista.addAll(ManejoDatosDeVehiculo.getInstancia().getListaDatosVehiculo(670));

            tbcVehiculo.setCellValueFactory(new PropertyValueFactory<>("vehiculo"));
            tbcPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));

            tbcSeleccionar.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcSeleccionar.setCellFactory((TableColumn<DatosDeVehiculo, DatosDeVehiculo> param) -> {

                TableCell<DatosDeVehiculo, DatosDeVehiculo> cellsc = new TableCell<DatosDeVehiculo, DatosDeVehiculo>() {
                    @Override
                    public void updateItem(DatosDeVehiculo item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;

                        if (item != null) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(40.0, 5.0);
//                            btnHabilitar.setDisable(true);

                            if (item.getSeleccionado()) {

                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 10px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 8pt;");

                            } else {

                                btnHabilitar.setText("NO");
                                btnHabilitar.setStyle("   -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 8pt;");

                            }

                            HBox hbox = new HBox();

                            hbox.getChildren().add(btnHabilitar);

                            btnHabilitar.setAlignment(Pos.CENTER);

                            btnHabilitar.setOnMouseClicked((event) -> {

                                if (item.getSeleccionado()) {

                                    item.setSeleccionado(false);
//
                                    tbVehiculos.refresh();
                                    btnHabilitar.setText("NO");
                                    btnHabilitar.setStyle("   -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 8pt;");

                                } else {

                                    item.setSeleccionado(true);
                                    tbVehiculos.refresh();
                                    btnHabilitar.setText("SI");
                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 10px;\n"
                                            + "    -fx-background-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 8pt;");

                                }

                            });
                            setGraphic(btnHabilitar);
                            setText(null);
                        } else {
                            setGraphic(null);
                            setText(null);
                        }
                    }
                };
                return cellsc;
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciazarFiltro() {

        FilteredList<DatosDeVehiculo> filteredData = new FilteredList<>(tbVehiculos.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltrar.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(contrato -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (contrato.getVehiculo().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (contrato.getPlaca().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<DatosDeVehiculo> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbVehiculos.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbVehiculos.setItems(sortedData);
    }

    @FXML
    private void btnAceptarActionEvent(ActionEvent event) {

        for (DatosDeVehiculo v : lista) {

            if (v.getSeleccionado()) {
                getListaVehiculoSeleccionado().add(v);
            }
        }

        Stage stage = (Stage) tbVehiculos.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void tbSolicitudDePagoMouseClicked(MouseEvent event) {

//        if (!(tbVehiculos.getSelectionModel().getSelectedIndex() == -1)) {
//
//            setDatosDeVehiculo(tbVehiculos.getSelectionModel().getSelectedItem());
//
//            if (event.getClickCount() == 2) {
//
//                Stage stage = (Stage) tbVehiculos.getScene().getWindow();
//                stage.close();
//            }
//        }
    }

    public void iniData() {

        System.out.println("codigoClienye :" + codigoClienete);
        lista.clear();
        lista.addAll(ManejoDatosDeVehiculo.getInstancia().getListaDatosVehiculo(codigoClienete));
        for (DatosDeVehiculo dv : lista) {
            dv.setSeleccionado(false);
        }

        tbVehiculos.refresh();
    }
}
