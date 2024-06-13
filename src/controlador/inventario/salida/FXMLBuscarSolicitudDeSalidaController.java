/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.salida;


import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import manejo.inventario.salida.ManejoSolicitudSalida;
import modelo.SolicitudSalidaInventario;
import util.TooltippedTableCell;
import utiles.ClaseUtil;

public class FXMLBuscarSolicitudDeSalidaController implements Initializable {

    @FXML
    private JFXTextField txtFiltro;

    ObservableList<SolicitudSalidaInventario> listaSolicitudSalida = FXCollections.observableArrayList();

    @FXML
    private TableView<SolicitudSalidaInventario> tbSolicitudSalida;
    @FXML
    private TableColumn<SolicitudSalidaInventario, Date> tbcFecha;
    @FXML
    private TableColumn<SolicitudSalidaInventario, String> tbcAutorizada;
    @FXML
    private TableColumn<SolicitudSalidaInventario, Date> tbcFechaautorizada;
    @FXML
    private TableColumn<SolicitudSalidaInventario, String> tbcNoSolicitud;
    @FXML
    private TableColumn<SolicitudSalidaInventario, String> tbcSolicitante;
    @FXML
    private TableColumn<SolicitudSalidaInventario, String> tbcComentario;
    @FXML
    private TableColumn<SolicitudSalidaInventario, String> tbcEstado;

    SolicitudSalidaInventario solicitudSalidaInventario;
    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXCheckBox CKCheckTodo;
    @FXML
    private TableColumn<SolicitudSalidaInventario, String> tbcAnulada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());

        inicializarTabla();
        iniciazarFiltro();

        CKCheckTodo.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    try {
                        txtFiltro.clear();
                        if (new_val) {
                            dpFechaInicio.setValue(LocalDate.now());
                            dpFechaFinal.setValue(LocalDate.now());
                            dpFechaInicio.setDisable(true);
                            dpFechaFinal.setDisable(true);
                            filtrarPorFechaInicioFinal();
                        } else {
                            dpFechaInicio.setValue(LocalDate.now());
                            dpFechaFinal.setValue(LocalDate.now());
                            dpFechaInicio.setDisable(false);
                            dpFechaFinal.setDisable(false);
                            filtrarPorFechaInicioFinal();
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });

        dpFechaInicio.valueProperty().addListener((ov, oldValue, newValue) -> {
            try {
                filtrarPorFechaInicioFinal();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        dpFechaInicio.valueProperty().addListener((ov, oldValue, newValue) -> {
            try {
                filtrarPorFechaInicioFinal();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

    }

    private void filtrarPorFechaInicioFinal() {
        try {
            Date fi, ff;
            fi = ClaseUtil.asDate(dpFechaInicio.getValue());
            ff = ClaseUtil.asDate(dpFechaFinal.getValue());
            listaSolicitudSalida.clear();
            if (!CKCheckTodo.isSelected()) {
                listaSolicitudSalida.addAll(ManejoSolicitudSalida.getInstancia().getListaFechaEstado(fi, ff, "pendiente", false));
            } else {
                listaSolicitudSalida.addAll(ManejoSolicitudSalida.getInstancia().getListaSolicitudPorEstado("pendiente", false));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void tbSuplidorActionEvent(MouseEvent event) {
        try {
            if (!(tbSolicitudSalida.getSelectionModel().getSelectedIndex() == -1)) {

                if (event.getClickCount() == 2) {

                    if (tbSolicitudSalida.getSelectionModel().getSelectedItem().getAutorizada()) {

                        setSolicitudSalidaInventario(tbSolicitudSalida.getSelectionModel().getSelectedItem());
                        System.out.println(getsolicitudSalidaInventario().getSolicitante());

                        Stage stage = (Stage) tbSolicitudSalida.getScene().getWindow();
                        stage.close();

                    } else {

                        ClaseUtil.mensaje("La Solicitud de Salida de Inventario debe estar AUTORIZADA PARA CONTINUAR. #" + tbSolicitudSalida.getSelectionModel().getSelectedItem().getCodigo() + " SOLICITANTE: " + tbSolicitudSalida.getSelectionModel().getSelectedItem().getSolicitante());
                    }

                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void inicializarTabla() {

        try {

            listaSolicitudSalida.clear();

            if (!CKCheckTodo.isSelected()) {
                Date fi, ff;
                fi = ClaseUtil.asDate(dpFechaInicio.getValue());
                ff = ClaseUtil.asDate(dpFechaFinal.getValue());
                
                listaSolicitudSalida.addAll(ManejoSolicitudSalida.getInstancia().getListaFechaEstado(fi, ff, "pendiente", false));
            } else {
                
                listaSolicitudSalida.addAll(ManejoSolicitudSalida.getInstancia().getListaSolicitudPorEstado("pendiente", false));
            }

            tbcNoSolicitud.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tbcFecha.setCellFactory(new Callback<TableColumn<SolicitudSalidaInventario, Date>, TableCell<SolicitudSalidaInventario, Date>>() {
                
                @Override
                public TableCell<SolicitudSalidaInventario, Date> call(TableColumn<SolicitudSalidaInventario, Date> param) {
                    
                    TableCell<SolicitudSalidaInventario, Date> cellsc = new TableCell<SolicitudSalidaInventario, Date>() {
                        @Override
                        public void updateItem(Date item, boolean empty) {
                            try {
                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                if (item != null) {
                                    String val = "";
                                    val = format.format(item);
                                    setText(val);
                                } else {
                                    setText("");
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    };
                    return cellsc;
                }
            });
            tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
            //  tbcAutorizada.setCellValueFactory(new PropertyValueFactory<>("autorizada"));
            tbcAutorizada.setCellValueFactory(cellData -> {
                SimpleStringProperty property = new SimpleStringProperty();
                if (cellData.getValue() != null) {
                    try {
                        if (cellData.getValue().getAutorizada()) {
                            property.setValue("AUTORIZADO");
                        } else {
                            property.setValue("NO AUTORIZADO");
                        }

                    } catch (Exception e) {
                        property.setValue(String.valueOf(0));
                    }
                }
                return property;
            });
            tbcFechaautorizada.setCellValueFactory(new PropertyValueFactory<>("fechaAutorizacion"));
            tbcFechaautorizada.setCellFactory(new Callback<TableColumn<SolicitudSalidaInventario, Date>, TableCell<SolicitudSalidaInventario, Date>>() {
                @Override
                public TableCell<SolicitudSalidaInventario, Date> call(TableColumn<SolicitudSalidaInventario, Date> param) {
                    TableCell<SolicitudSalidaInventario, Date> cellsc = new TableCell<SolicitudSalidaInventario, Date>() {

                        @Override
                        public void updateItem(Date item, boolean empty) {
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
                            if (item != null) {
                                String val = "";
                                val = format.format(item);
                                setText(val);
                            } else {
                                setText("");
                            }
                        }
                    };
                    return cellsc;
                }
            });
            tbcSolicitante.setCellValueFactory(new PropertyValueFactory<>("solicitante"));
            tbcSolicitante.setCellFactory(TooltippedTableCell.forTableColumn());
            tbcComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));
            tbcComentario.setCellFactory(TooltippedTableCell.forTableColumn());
            tbcComentario.setCellValueFactory(cellData -> {
                SimpleStringProperty property = new SimpleStringProperty();
                if (cellData.getValue() != null) {
                    try {
                        if (cellData.getValue().getComentario().isEmpty()) {
                            property.setValue("NO SE ESPECIFICO NINGUN COMENTARIO");
                        } else {
                            property.setValue(cellData.getValue().getComentario().toUpperCase());
                        }

                    } catch (Exception e) {
                        property.setValue(String.valueOf(0));
                    }
                }
                return property;
            });

            tbcAnulada.setCellValueFactory(
                    cellData
                    -> {
                SimpleStringProperty property = new SimpleStringProperty();
                if (cellData.getValue() != null) {
                    try {

                        if (!cellData.getValue().getAnulada()) {
                            property.set("va");
                        } else {
                            property.set("");
                        }

                    } catch (Exception e) {
                    }
                }
                return property;
            });

            tbcAnulada.setCellFactory((TableColumn<SolicitudSalidaInventario, String> param) -> {
                TableCell<SolicitudSalidaInventario, String> cellsc = new TableCell<SolicitudSalidaInventario, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null) {
                            Image image;
                            Label label;
                            if (item.isEmpty()) {
                                image = new Image("/imagen/inactive.png");
                                label = new Label("Inactivo  ".toUpperCase());
                            } else {
                                image = new Image("/imagen/active.png");
                                label = new Label("Activo  ".toUpperCase());
                            }

                            HBox box = new HBox();
                            VBox vbox = new VBox();
                            vbox.setAlignment(Pos.CENTER);
                            box.setAlignment(Pos.CENTER);
                            ImageView imageview = new ImageView();
                            imageview.setFitHeight(16);
                            imageview.setFitWidth(17);
                            imageview.setImage(image);
                            box.getChildren().addAll(label, imageview, vbox);
                            setGraphic(box);
                            setText(null);
                        } else {
                            setGraphic(null);
                            setText(null);
                        }
                    }
                };
                return cellsc;
            });
            tbSolicitudSalida.setItems(listaSolicitudSalida);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciazarFiltro() {

        FilteredList<SolicitudSalidaInventario> filteredData = new FilteredList<>(tbSolicitudSalida.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(detalleSolicitud -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (detalleSolicitud.getCodigo() != null && String.valueOf(detalleSolicitud.getCodigo()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (detalleSolicitud.getFecha() != null && String.valueOf(detalleSolicitud.getFecha()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (detalleSolicitud.getSolicitante() != null && detalleSolicitud.getSolicitante().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (detalleSolicitud.getComentario() != null && detalleSolicitud.getComentario().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false;
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<SolicitudSalidaInventario> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbSolicitudSalida.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbSolicitudSalida.setItems(sortedData);
    }

    public SolicitudSalidaInventario getsolicitudSalidaInventario() {
        return solicitudSalidaInventario;
    }

    public void setSolicitudSalidaInventario(SolicitudSalidaInventario solicitudSalidaInventario) {
        this.solicitudSalidaInventario = solicitudSalidaInventario;
    }
 
}
