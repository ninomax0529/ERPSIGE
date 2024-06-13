/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.facturar;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import correo.ServicioDeCorreo;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import manejo.factura.ManejoFactura;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import modelo.ContratoDeServicio;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.FacturaDatosDeVehiculo;
import modelo.MenuModulo;
import modelo.RelacionNcf;
import modelo.RolMenuModulo;
import reporte.factura.RptFacturaIghTrack;
import reporte.unidadnegocio.RptFactura;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FacturaParaEnvierPorCorreoController implements Initializable {

    @FXML
    private JFXButton btnEnviarrFactura;
    @FXML
    private JFXDatePicker dpFechaDesde;
    @FXML
    private JFXDatePicker dpFecgaHasta;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private TableColumn<Factura, Factura> tbcEnviar;
    @FXML
    private JFXTextField txtFiltroContrato;

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
    private TableView<Factura> tbContratoDeServicio;
    @FXML
    private TableColumn<Factura, Factura> tbcEnviada;
    @FXML
    private TableColumn<Factura, String> tbcFechaFac;
    @FXML
    private TableColumn<Factura, String> tbcNcf;
    @FXML
    private Label lbCantidadContrato;
    private TableView<Factura> tbFacturaDeContrato;
    @FXML
    private TableColumn<Factura, Integer> tbcFacturaFact;
    @FXML
    private TableColumn<Factura, String> tbcClienteFact;
    @FXML
    private TableColumn<Factura, Double> tbcMontoFact;
    @FXML
    private TableColumn<Factura, String> tbcCorreo;

    ObservableList<Factura> listaFactura = FXCollections.observableArrayList();
    ObservableList<Factura> listaFacturaEnviada = FXCollections.observableArrayList();

    ObservableList<DetalleFactura> listaDetalleFactura = FXCollections.observableArrayList();
    ObservableList<FacturaDatosDeVehiculo> listaDetalleFacDdatosVehiculo = FXCollections.observableArrayList();
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    private Integer codigoMenuModulo;
    Date fechaVencimiento = null;

    RelacionNcf relacionNcf = null;
    @FXML
    private ProgressBar progresBar;

    TaskEnviarCorreao taskEnviarCorreao;
    @FXML
    private Label lbProgreso;
    @FXML
    private ProgressIndicator pgIndicador;
    int cantidadEnviada = 0, cantidadNoEnviada = 0;
    @FXML
    private Label lbCantEnviada;
    @FXML
    private Label lbCantNoEnviada;
    @FXML
    private HBox hbPermisos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
        setCodigoMenuModulo(ManejoMenuModulo.getInstancia().getMenuModulo(9, "btnFacturaPorCorreo"));
        pgIndicador.setVisible(false);
        progresBar.setVisible(false);
        agregarOpciones();
        activarOpciones();
        dpFechaDesde.setValue(LocalDate.now());
        dpFecgaHasta.setValue(LocalDate.now());
        buscar();
        iniciazarFiltroFactura();

    }

    private void iniciazarFiltroFactura() {

        FilteredList<Factura> filteredData = new FilteredList<>(tbContratoDeServicio.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltroContrato.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(fact -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (fact.getNombreCliente().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (fact.getNumero().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (fact.getFecha().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Factura> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbContratoDeServicio.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbContratoDeServicio.setItems(sortedData);
    }

    public void inicializarTabla() {

        try {

            listaFactura.addAll(ManejoFactura.getInstancia().getFacturaPorEnviarPorCorreo());

            tbcClienteFact.setCellValueFactory(new PropertyValueFactory<>("cliente"));
            tbcNcf.setCellValueFactory(new PropertyValueFactory<>("ncf"));
            tbcFacturaFact.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tbcFechaFac.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tbcMontoFact.setCellValueFactory(new PropertyValueFactory<>("total"));
            tbcCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));

            tbcEnviada.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcFechaFac.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFecha() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFecha()));
                        }
                        return property;
                    });

            tbcCorreo.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();

                        property.setValue(cellData.getValue().getCliente().getEmail());

                        return property;
                    });

            tbcClienteFact.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();

                        property.setValue(cellData.getValue().getNombreCliente());

                        return property;
                    });

            tbcEnviada.setCellFactory((TableColumn<Factura, Factura> param) -> {

                TableCell<Factura, Factura> cellsc = new TableCell<Factura, Factura>() {
                    @Override
                    public void updateItem(Factura item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;

                        if (item != null) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(50.0, 15.0);

                            if (item.getEnviadaPorCorreo()) {

                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            } else {

                                btnHabilitar.setText("NO");
                                btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            }

                            HBox hbox = new HBox();

//                        hbox.getChildren().addAll(imageview);
                            hbox.getChildren().add(btnHabilitar);

                            hbox.setAlignment(Pos.CENTER);

//                            btnHabilitar.setOnMouseClicked((event) -> {
//
//                                if (item.getEnviadaPorCorreo()) {
//
//                                    item.setHabilitado(false);
//                                    btnHabilitar.setText("NO");
//
//                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
//                                            + "    -fx-border-color: -fx-secondary;\n"
//                                            + "    -fx-border-radius: 15px;\n"
//                                            + "    -fx-background-radius: 15px;\n"
//                                            + "    -fx-b-radius: 10px;\n"
//                                            + " -fx-text-fill: white;"
//                                            + "    -fx-font-size: 12pt;");
//
//                                } else {
//
//                                    item.setHabilitado(true);
//
//                                    btnHabilitar.setText("SI");
//
//                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
//                                            + "    -fx-border-color: -fx-secondary;\n"
//                                            + "    -fx-border-radius: 15px;\n"
//                                            + "    -fx-background-radius: 10px;\n"
//                                            + " -fx-text-fill: white;"
//                                            + "    -fx-font-size: 12pt;");
//
//                                }
//
//
//                            });
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

            tbcEnviar.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcEnviar.setCellFactory((TableColumn<Factura, Factura> param) -> {

                TableCell<Factura, Factura> cellsc = new TableCell<Factura, Factura>() {
                    @Override
                    public void updateItem(Factura item, boolean empty) {
                        super.updateItem(item, empty);

                        Button btnHabilitar;

                        if (item != null) {

                            btnHabilitar = new Button();
                            btnHabilitar.setPrefSize(50.0, 20.0);

                            if (item.getDespachada()) {

                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            } else {

                                btnHabilitar.setText("NO");
                                btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 10px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            }

                            HBox hbox = new HBox();

                            hbox.getChildren().add(btnHabilitar);

                            hbox.setAlignment(Pos.CENTER);

                            btnHabilitar.setOnMouseClicked((event) -> {

                                if (item.getDespachada()) {

                                    item.setDespachada(false);
                                    btnHabilitar.setText("NO");
                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 15px;\n"
                                            + "    -fx-b-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 12pt;");

                                } else {

                                    item.setDespachada(true);
                                    btnHabilitar.setText("SI");

                                    btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                            + "    -fx-border-color: -fx-secondary;\n"
                                            + "    -fx-border-radius: 15px;\n"
                                            + "    -fx-background-radius: 10px;\n"
                                            + " -fx-text-fill: white;"
                                            + "    -fx-font-size: 12pt;");
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

            tbContratoDeServicio.setItems(listaFactura);
            lbCantidadContrato.setText(Integer.toString(listaFactura.size()));
            lbCantidadContrato.setText(Integer.toString(listaFactura.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {

        try {

            cantidadEnviada = 0;
            cantidadNoEnviada = 0;

            tareaMarcatProbeta();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tbContratoDeServicioClicked(MouseEvent event) {
    }

    private Integer cantidadContrato() {

        return listaFactura.size();
    }

    private Integer cantidadFactura() {

        return listaFacturaEnviada.size();
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        try {
            buscar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class TaskEnviarCorreao extends Task<List<Void>> {

        @Override
        protected List<Void> call() throws Exception {

            try {

                int cantidad = listaFactura.size();
                int i = 0;

                for (Factura fact : listaFactura) {

                    if (fact.getDespachada() == true) {

                        System.out.println("Enviada " + fact.getDespachada());
                        i++;
                        if (enviarCorreao(fact, i, cantidad)) {

                            cantidadEnviada++;
                            listaFacturaEnviada.add(fact);
                            fact.setEnviadaPorCorreo(true);
                            tbContratoDeServicio.refresh();
                            ManejoFactura.getInstancia().actualizar(fact);

                        } else {
                            cantidadNoEnviada++;

                        }

                        this.updateProgress(i, cantidad);

                    }

                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;

        }

        private boolean enviarCorreao(Factura factura, int cantidad, int total) throws Exception {

            ServicioDeCorreo sc = new ServicioDeCorreo();
            String rutaFactura = "";
            int ung=factura.getUnidadDeNegocio().getCodigo();

            Boolean enviado = false;

            try {

                if (!(factura.getCliente().getEmail() == null) && factura.getCliente().getEmail().contains("@")) {

                      rutaFactura = RptFactura.getInstancia().exportarFacturaAPDF(factura,ung);//devuelve la ruta donde se genero el pdf
//                    rutaFactura = RptFacturaIghTrack.getInstancia().exportarFacturaAPDF(factura);//devuelve la ruta donde se genero el pdf

                    System.out.println("Factura num " + factura.getNumero() + " " + rutaFactura);

                    enviado = sc.enviarCorreoFact("Su Factura IGH TRACK, corte " + new SimpleDateFormat("dd/MM/yyyy").format(factura.getFecha()),
                            " Estimado(a) cliente: " + factura.getCliente().getNombre() + "\n "
                            + " Un placer saludarle.\n"
                            + "Anexamos la (s) factura (s) correspondiente (s), antes de realizar el pago, favor descargar  el (los) archivo (s) con la finalidad de verificar el balance de los servicios contratados.\n"
                            + "Estamos para servirle en todo momento, puede llamar o escribir al teléfono y WhatsApp 809-241-1234 \n E-mail: clientesightrack@gmail.com ", factura.getCliente().getEmail(),
                            new File(rutaFactura));

//                    if (enviado) {
//                        listaFacturaEnviada.add(factura);
//                        factura.setEnviadaPorCorreo(true);
//                        tbContratoDeServicio.refresh();
//                        cantidadEnviada++;
//                        lbCantEnviada.setText(Integer.toString(cantidadEnviada));
//
//                    } else {
//
//                        cantidadNoEnviada++;
//                        lbCantNoEnviada.setText(Integer.toString(cantidadNoEnviada));
//                    }
//                    this.updateMessage(" Procesada  " + cantidad + "  De " + total + "     Enviada " + cantidadEnviada + "  No Enviada " + cantidadNoEnviada);
                    this.updateMessage(" Procesada  " + cantidad + "  De " + total);

                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            Thread.sleep(200);
            return enviado;
        }

    }

    private void tareaMarcatProbeta() {

        try {

            btnEnviarrFactura.setDisable(true);
            progresBar.setProgress(0);
            pgIndicador.setProgress(0);
            pgIndicador.setVisible(true);
            progresBar.setVisible(true);

            taskEnviarCorreao = new TaskEnviarCorreao();

            progresBar.progressProperty().unbind();

            progresBar.progressProperty().bind(taskEnviarCorreao.progressProperty());

            pgIndicador.progressProperty().unbind();

            pgIndicador.progressProperty().bind(taskEnviarCorreao.progressProperty());
//
            lbProgreso.textProperty().unbind();
            lbProgreso.textProperty().bind(taskEnviarCorreao.messageProperty());

            taskEnviarCorreao.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent t) -> {

                lbProgreso.textProperty().unbind();

                progresBar.setVisible(false);
                pgIndicador.setVisible(false);

                taskEnviarCorreao.cancel();

                progresBar.progressProperty().unbind();
                lbCantEnviada.setText(Integer.toString(cantidadEnviada));
                lbCantNoEnviada.setText(Integer.toString(cantidadNoEnviada));
                pgIndicador.progressProperty().unbind();

            });

            // Start the Task.
            new Thread(taskEnviarCorreao).start();

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

    private void buscar() {

        listaFactura.clear();
        Date fechaDesde = ClaseUtil.asDate(dpFechaDesde.getValue());
        Date fechaHasta = ClaseUtil.asDate(dpFecgaHasta.getValue());
        listaFactura.addAll(ManejoFactura.getInstancia().getFacturaPorEnviarPorCorreo(fechaDesde, fechaHasta));

        lbCantidadContrato.setText(Integer.toString(listaFactura.size()));

        if (listaFactura.size() > 0) {
            btnEnviarrFactura.setDisable(false);

            for (Factura fact : listaFactura) {
                fact.setDespachada(false);
            }

        } else {
            btnEnviarrFactura.setDisable(true);
        }

    }

}
