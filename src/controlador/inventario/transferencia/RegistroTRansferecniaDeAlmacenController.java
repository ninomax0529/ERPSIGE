/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.transferencia;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlador.inventario.salida.FXMLBuscarSolicitanteController;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import manejo.articulo.ManejoAlmacen;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.articulo.ManejoListaDePrecio;
import manejo.inventario.transferencia.ManejoTransferencia;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.Almacen;
import modelo.ArticuloUnidad;
import modelo.DetalleListaDePrecio;
import modelo.DetalleTransferenciaAlmacen;
import modelo.ExistenciaArticulo;
import modelo.SecuenciaDocumento;
import modelo.Solicitante;
import modelo.TransferenciaAlmacen;
import reporte.inventario.transferencia.RptTransferencia;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroTRansferecniaDeAlmacenController implements Initializable {

    @FXML
    private JFXTextField txtNumeroTrnasferecnia;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtSolicitante;
    @FXML
    private JFXButton btnBuscarSoliciitante;
    @FXML
    private JFXTextField txtCantidadPedida;
    @FXML
    private JFXButton btnAgregarArticulo;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private TableView<DetalleTransferenciaAlmacen> tbDetalleTransferecnia;
    @FXML
    private TableColumn<DetalleTransferenciaAlmacen, String> tbcAlmacenOrigen;
    @FXML
    private TableColumn<DetalleTransferenciaAlmacen, String> tbcalmacenDestino;
    @FXML
    private TableColumn<DetalleTransferenciaAlmacen, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleTransferenciaAlmacen, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleTransferenciaAlmacen, Double> tbcCantidad;
    @FXML
    private TableColumn<DetalleTransferenciaAlmacen, String> tbcUnidad;

    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXButton btnGuardar;
    private JFXTextField txtFiltraralmOrigen;
    @FXML
    private TableView<ExistenciaArticulo> tbArticuloAlmacenOrigen;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcCodalmOrigen;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcNomAlmOrigen;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcCodArtAlmOrigen;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcDescArtalmOrigen;
    @FXML
    private TableColumn<ExistenciaArticulo, Double> tbcalmOrigenExistencia;
    private JFXTextField txtFiltraralmDestino;
    @FXML
    private TableView<ExistenciaArticulo> tbArticuloAlmacenDestino;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcCodalmDestino;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcNomAlmDestino;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcCodArtAlmDestino;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcDescArtalmDestino;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcalmOrigenUnidad;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcalmDestinoUnidad;
    @FXML
    private TableColumn<ExistenciaArticulo, Double> tbcalmDestinoExistencia;

    ObservableList<DetalleTransferenciaAlmacen> listadetalle = FXCollections.observableArrayList();
    ObservableList<ExistenciaArticulo> listaExistenciaAlmDestino = FXCollections.observableArrayList();
    ObservableList<ExistenciaArticulo> listaExistenciaAlmOrigen = FXCollections.observableArrayList();
    ObservableList<ArticuloUnidad> listaUnidadsAlmDestino = FXCollections.observableArrayList();
    ObservableList<ArticuloUnidad> listaUnidadsAlmOrigen = FXCollections.observableArrayList();

    ObservableList<Almacen> listaAlmacenOrigen = FXCollections.observableArrayList();
    ObservableList<Almacen> listaAlmacenDestino = FXCollections.observableArrayList();

    TransferenciaAlmacen transferecnia;
    DetalleTransferenciaAlmacen detalleTras;
    private Solicitante solicitante;
    @FXML
    private JFXComboBox<ArticuloUnidad> cbUnidadAlmOrigen;
    @FXML
    private JFXComboBox<ArticuloUnidad> cbUnidadAlmDestino;

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    ExistenciaArticulo existenciaArticulo;
    @FXML
    private JFXComboBox<Almacen> cbAlmacenOrigen;
    @FXML
    private JFXComboBox<Almacen> cbAlmacenDestino;

    public ExistenciaArticulo getExistenciaArticulo() {
        return existenciaArticulo;
    }

    public void setExistenciaArticulo(ExistenciaArticulo existenciaArticulo) {
        this.existenciaArticulo = existenciaArticulo;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaDetalle();
        inicializarTablaalmDestino();
        inicializarTablaalmorigen();

        iniciazarFiltroAlmDestino();
        iniciazarFiltroAlmOrigen();
        inicializarCombox();

        nuvo();
        inicializarSecuencia();
        dpFecha.setValue(LocalDate.now());
    }

    private void inicializarCombox() {

        listaAlmacenOrigen.addAll(ManejoAlmacen.getInstancia()
                .getAlmacenPorUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()));

        cbAlmacenDestino.setConverter(new StringConverter<Almacen>() {

            @Override
            public String toString(Almacen almacen) {
                return String.valueOf(almacen.getNombre());
            }

            @Override
            public Almacen fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbAlmacenOrigen.setConverter(new StringConverter<Almacen>() {

            @Override
            public String toString(Almacen almacen) {
                return String.valueOf(almacen.getNombre());
            }

            @Override
            public Almacen fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbUnidadAlmDestino.setConverter(new StringConverter<ArticuloUnidad>() {

            @Override
            public String toString(ArticuloUnidad unidad) {
                return String.valueOf(unidad.getUnidad().getDescripcion());
            }

            @Override
            public ArticuloUnidad fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbUnidadAlmOrigen.setConverter(new StringConverter<ArticuloUnidad>() {

            @Override
            public String toString(ArticuloUnidad unidad) {
                return String.valueOf(unidad.getUnidad().getDescripcion());
            }

            @Override
            public ArticuloUnidad fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbAlmacenDestino.setItems(listaAlmacenDestino);
        cbAlmacenOrigen.setItems(listaAlmacenOrigen);
        cbUnidadAlmDestino.setItems(listaUnidadsAlmDestino);
        cbUnidadAlmOrigen.setItems(listaUnidadsAlmOrigen);

    }

    private void inicializarTablaalmorigen() {

        try {

//            listaExistenciaAlmOrigen.addAll(ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo());
            tbcCodalmOrigen.setCellValueFactory(new PropertyValueFactory<>("almacen"));
            tbcNomAlmOrigen.setCellValueFactory(new PropertyValueFactory<>("almacen"));

            tbcCodArtAlmOrigen.setCellValueFactory(new PropertyValueFactory<>("articulo"));
            tbcDescArtalmOrigen.setCellValueFactory(new PropertyValueFactory<>("articulo"));
            tbcalmOrigenExistencia.setCellValueFactory(new PropertyValueFactory<>("existencia"));

            tbcalmOrigenUnidad.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getUnidad() != null) {
                            property.setValue(cellData.getValue().getUnidad().getDescripcion());
                        }
                        return property;
                    });

            tbcCodalmOrigen.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getArticulo() != null) {
                            property.setValue(cellData.getValue().getAlmacen().getCodigo().toString());
                        }
                        return property;
                    });

            tbcNomAlmOrigen.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getArticulo() != null) {
                            property.setValue(cellData.getValue().getAlmacen().getNombre());
                        }
                        return property;
                    });

            tbcCodArtAlmOrigen.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getArticulo() != null) {
                            property.setValue(cellData.getValue().getArticulo().getNumero().toString());
                        }
                        return property;
                    });

            tbcDescArtalmOrigen.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getArticulo() != null) {
                            property.setValue(cellData.getValue().getArticulo().getDescripcion());
                        }
                        return property;
                    });

            tbArticuloAlmacenOrigen.setItems(listaExistenciaAlmOrigen);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inicializarTablaalmDestino() {

        try {

//            listaExistenciaAlmDestino.addAll(ManejoExistenciaArticulo.getInstancia().getExistenciaArticulo());
            tbcCodalmDestino.setCellValueFactory(new PropertyValueFactory<>("almacen"));
            tbcNomAlmDestino.setCellValueFactory(new PropertyValueFactory<>("almacen"));

            tbcCodArtAlmDestino.setCellValueFactory(new PropertyValueFactory<>("articulo"));
            tbcDescArtalmDestino.setCellValueFactory(new PropertyValueFactory<>("articulo"));
            tbcalmDestinoExistencia.setCellValueFactory(new PropertyValueFactory<>("existencia"));

            tbcalmDestinoUnidad.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getUnidad() != null) {
                            property.setValue(cellData.getValue().getUnidad().getDescripcion());
                        }
                        return property;
                    });

            tbcCodalmDestino.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getArticulo() != null) {
                            property.setValue(cellData.getValue().getAlmacen().getCodigo().toString());
                        }
                        return property;
                    });

            tbcNomAlmDestino.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getArticulo() != null) {
                            property.setValue(cellData.getValue().getAlmacen().getNombre());
                        }
                        return property;
                    });

            tbcCodArtAlmDestino.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getArticulo() != null) {
                            property.setValue(cellData.getValue().getArticulo().getNumero().toString());
                        }
                        return property;
                    });

            tbcDescArtalmDestino.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getArticulo() != null) {
                            property.setValue(cellData.getValue().getArticulo().getDescripcion());
                        }
                        return property;
                    });

            tbArticuloAlmacenDestino.setItems(listaExistenciaAlmDestino);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iniciarTablaDetalle() {

        listadetalle.clear();

        tbDetalleTransferecnia.setItems(listadetalle);

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));

        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        tbcCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getNumero().toString());
                    }
                    return property;
                });

        tbcDescripcionArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getDescripcion());
                    }
                    return property;
                });

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getNombreUnidad());
                    }
                    return property;
                });

        tbcAlmacenOrigen.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getAlmacenOrigen().getNombre());
                    }
                    return property;
                });

        tbcalmacenDestino.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getAlmacenDestino().getNombre());
                    }
                    return property;
                });

    }

    private void iniciazarFiltroAlmOrigen() {

        FilteredList<ExistenciaArticulo> filteredData = new FilteredList<>(tbArticuloAlmacenOrigen.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
//        txtFiltraralmOrigen.textProperty().addListener((observable, oldValue, newValue) -> {
//
//            filteredData.setPredicate(articuloFiltro -> {
//
//                // If filter text is empty, display all persons.
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//
//                // Compare first name and last name of every person with filter text.
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (articuloFiltro.getAlmacen() != null && articuloFiltro.getAlmacen().getNombre().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches first name.
//                } else if (articuloFiltro.getArticulo() != null && articuloFiltro.getArticulo().getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
//                } else if (articuloFiltro.getAlmacen() != null && articuloFiltro.getAlmacen().getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
//                } else if (articuloFiltro.getArticulo() != null && articuloFiltro.getArticulo().getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
//                }
//
//                return false; // Does not match.
//            });
//        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ExistenciaArticulo> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbArticuloAlmacenOrigen.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbArticuloAlmacenOrigen.setItems(sortedData);
    }

    private void iniciazarFiltroAlmDestino() {

        FilteredList<ExistenciaArticulo> filteredData = new FilteredList<>(tbArticuloAlmacenDestino.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
//        txtFiltraralmDestino.textProperty().addListener((observable, oldValue, newValue) -> {
//
//            filteredData.setPredicate(articuloFiltro -> {
//
//                // If filter text is empty, display all persons.
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//
//                // Compare first name and last name of every person with filter text.
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (articuloFiltro.getAlmacen() != null && articuloFiltro.getAlmacen().getNombre().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches first name.
//                } else if (articuloFiltro.getArticulo() != null && articuloFiltro.getArticulo().getNombre().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
//                } else if (articuloFiltro.getAlmacen() != null && articuloFiltro.getAlmacen().getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
//                } else if (articuloFiltro.getArticulo() != null && articuloFiltro.getArticulo().getCodigo().toString().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
//                }
//
//                return false; // Does not match.
//            });
//        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ExistenciaArticulo> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbArticuloAlmacenDestino.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbArticuloAlmacenDestino.setItems(sortedData);
    }

    @FXML
    private void btnBuscarSoliciitanteActionEvent(ActionEvent event) {

        try {

            System.out.println("Entre");
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/inventario/salida/FXMLBuscarSolicitante.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            System.out.println("ESPERANDOOO!!!");

            FXMLBuscarSolicitanteController articuloController = loader.getController();

            ClaseUtil.getStageModal(root);

            if (!(articuloController.getSolicitante() == null)) {

                setSolicitante(articuloController.getSolicitante());
                txtSolicitante.setText(getSolicitante().getNombre());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnAgregarArticuloActionEvent(ActionEvent event) {

        try {

            if (tbArticuloAlmacenOrigen.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar el articulo del almacen origen ");
                return;
            }

            if (tbArticuloAlmacenDestino.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar el articulo del almacen destino ");
                return;
            }
//
//            if (cbUnidadAlmOrigen.getSelectionModel().getSelectedIndex() == -1) {
//
//                ClaseUtil.mensaje("Tiene que seleccionar la unidad origen ");
//                return;
//            }

            if (txtCantidadPedida.getText().isEmpty()) {

                ClaseUtil.mensaje(" Tiene que digitar la cantidad a transferir ");
                txtCantidadPedida.requestFocus();
                return;
            }

            if (tbArticuloAlmacenOrigen.getSelectionModel().getSelectedItem().getExistencia() <= 0) {

                ClaseUtil.mensaje("Este articulo no tiene existencia en el almacen origen ");
                return;
            }

            agregar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

    }

    @FXML
    private void tbDetalleEntradaInventarioMouseCliked(MouseEvent event) {

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (txtSolicitante.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar un solicitante");
                return;

            }

            if (txtComentario.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar un comentario");
                return;

            }

            if (tbDetalleTransferecnia.getItems().size() <= 0) {

                ClaseUtil.mensaje("No hay articulo agregado para realizar esta tranferencuia ");
                return;

            }

            transferecnia.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(transferecnia.getUnidadDeNegocio().getCodigo(), 13);

            if (!(secDoc == null)) {

                boolean existe = ManejoTransferencia.getInstancia().existeSecuencia(secDoc.getNumero());

                if (existe) {

                    System.out.println("existe " + secDoc.getNumero());

                    while (ManejoTransferencia.getInstancia().existeSecuencia(secDoc.getNumero())) {

                        secDoc.setNumero(secDoc.getNumero() + 1);
                        ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                    }

                    secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(transferecnia.getUnidadDeNegocio().getCodigo(), 13);

                    transferecnia.setNumero(secDoc.getNumero());
                    transferecnia.setSecuenciaDocumento(secDoc);

                } else {

                    System.out.println("No existe " + secDoc.getNumero());
                    transferecnia.setNumero(secDoc.getNumero());
                    transferecnia.setSecuenciaDocumento(secDoc);
                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

            } else {

                ClaseUtil.mensaje(" La secuencia para la transferencia de la unidad de negocio "
                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return;
            }

            transferecnia.setAnulada(false);
            transferecnia.setAutorizada(true);
            transferecnia.setSolicitante(VariablesGlobales.USUARIO.getCodigo());
            transferecnia.setAutorizador(null);
            transferecnia.setDepartamentoSolicitante(0);
            transferecnia.setFecha(ClaseUtil.asDate(dpFecha.getValue()));
            transferecnia.setFechaAnulada(null);
            transferecnia.setFechaRegistro(new Date());
            transferecnia.setFechaAutorizada(new Date());
            transferecnia.setObservacion(txtComentario.getText());
            transferecnia.setUsuario(VariablesGlobales.USUARIO);
            transferecnia.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            transferecnia.setDetalleTransferenciaAlmacenCollection(listadetalle);
            transferecnia.setSolicitante(getSolicitante().getCodigo());

            TransferenciaAlmacen trans = ManejoTransferencia.getInstancia().salvar(transferecnia);

            if (!(trans == null)) {

                try {

                    ManejoExistenciaArticulo.getInstancia().actualizarExistenciaEntradaPorTransferencia(listadetalle);
                    ManejoExistenciaArticulo.getInstancia().actualizarExistenciaSalidaPorTransferencia(listadetalle);
                    RptTransferencia.getInstancia().imprimir(trans.getCodigo());

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }

            nuvo();

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error realizando la  tranferencuia ");
            e.printStackTrace();
        }

    }

    private void nuvo() {

        transferecnia = new TransferenciaAlmacen();
        limpiar();
        inicializarSecuencia();
    }

    private void limpiar() {

        txtComentario.clear();

        txtCantidadPedida.clear();
        txtSolicitante.clear();
        listaAlmacenDestino.clear();
        listaExistenciaAlmDestino.clear();
        listaExistenciaAlmOrigen.clear();
        listadetalle.clear();
    }

    private void agregar() {

        try {

            Double total = 0.00;

            detalleTras = new DetalleTransferenciaAlmacen();
            detalleTras.setTransferenciaAlmacen(transferecnia);

            detalleTras.setAlmacenOrigen(tbArticuloAlmacenOrigen.getSelectionModel().getSelectedItem().getAlmacen());
            detalleTras.setAlmacenDestino(tbArticuloAlmacenDestino.getSelectionModel().getSelectedItem().getAlmacen());
            detalleTras.setArticulo(tbArticuloAlmacenOrigen.getSelectionModel().getSelectedItem().getArticulo());
            detalleTras.setNombreArticulo(tbArticuloAlmacenOrigen.getSelectionModel().getSelectedItem().getArticulo().getNombre());
            detalleTras.setUnidad(tbArticuloAlmacenOrigen.getSelectionModel().getSelectedItem().getUnidad());
            detalleTras.setNombreUnidad(tbArticuloAlmacenOrigen.getSelectionModel().getSelectedItem().getUnidad().getDescripcion());
            detalleTras.setCantidad(Double.parseDouble(txtCantidadPedida.getText()));

            DetalleListaDePrecio detLp = ManejoListaDePrecio.getInstancia()
                    .getDetalleListaDePrecio(detalleTras.getArticulo().getCodigo(), detalleTras.getUnidad().getCodigo());

            if (detLp == null) {
                ClaseUtil.mensaje("Este articulo no tiene precio  configurado ");
                return;
            }

            detalleTras.setPrecio(detLp.getPrecio());

            total = detalleTras.getPrecio() * detalleTras.getCantidad();
            detalleTras.setTotal(total);

            if (existe(detalleTras)) {

                ClaseUtil.mensaje(" Este articulo ya fue agregado ");
                return;
            }

            cbAlmacenOrigen.getSelectionModel().select(-1);
            cbAlmacenDestino.getSelectionModel().select(-1);

            cbUnidadAlmDestino.getSelectionModel().select(-1);
            cbUnidadAlmOrigen.getSelectionModel().select(-1);

            listaExistenciaAlmOrigen.clear();
            listaAlmacenDestino.clear();
            listaExistenciaAlmDestino.clear();

            txtCantidadPedida.clear();

            listadetalle.add(detalleTras);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void inicializarSecuencia() {

        txtNumeroTrnasferecnia.setText(ManejoTransferencia.getInstancia().getNumero().toString());
    }

    @FXML
    private void tbArticuloAlmacenOrigenMouseClicked(MouseEvent event) {

        if (!(tbArticuloAlmacenOrigen.getSelectionModel().getSelectedIndex() == -1)) {

            listaUnidadsAlmDestino.clear();
            listaUnidadsAlmOrigen.clear();

            int codAlm = tbArticuloAlmacenOrigen.getSelectionModel().getSelectedItem().getAlmacen().getCodigo();
            int art = tbArticuloAlmacenOrigen.getSelectionModel().getSelectedItem().getArticulo().getCodigo();

            listaUnidadsAlmDestino.addAll(ManejoArticuloUnidad.getInstancia().getListaUnidadSslida(art));
            listaUnidadsAlmOrigen.addAll(ManejoArticuloUnidad.getInstancia().getListaUnidadSslida(art));

            listaExistenciaAlmDestino.clear();
            listaAlmacenDestino.clear();
            listaAlmacenDestino.addAll(ManejoAlmacen.getInstancia().getLista(codAlm));

        }
    }

    @FXML
    private void tbArticuloAlmacenDestinoMouseClicked(MouseEvent event) {

        if (!(tbArticuloAlmacenDestino.getSelectionModel().getSelectedIndex() == -1)) {

            if (event.getClickCount() == 2) {
                agregar();
            }
        }
    }

    @FXML
    private void cbAlmacenOrigenActionEvent(ActionEvent event) {

        if (!(cbAlmacenOrigen.getSelectionModel().getSelectedIndex() == -1)) {

            int codAlm = cbAlmacenOrigen.getSelectionModel().getSelectedItem().getCodigo();

            listaExistenciaAlmOrigen.clear();
            listaAlmacenDestino.clear();
            listaExistenciaAlmDestino.clear();

            listaExistenciaAlmOrigen.addAll(ManejoExistenciaArticulo.getInstancia().getExistenciaAlmacen(codAlm));
//            listaAlmacenDestino.addAll(ManejoAlmacen.getInstancia().getLista(codAlm));
             listaAlmacenDestino.addAll(ManejoAlmacen.getInstancia().getAlmacenPorUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()));
               
        } else {

            listaExistenciaAlmOrigen.clear();
            listaAlmacenDestino.clear();
            listaExistenciaAlmDestino.clear();

        }
    }

    @FXML
    private void cbAlmacenDestinoActionEvent(ActionEvent event) {

        if (!(cbAlmacenDestino.getSelectionModel().getSelectedIndex() == -1)) {

            int codAlm = cbAlmacenDestino.getSelectionModel().getSelectedItem().getCodigo();

            if (!(tbArticuloAlmacenOrigen.getSelectionModel().getSelectedIndex() == -1)) {

                listaExistenciaAlmDestino.clear();
                int codArticulo = tbArticuloAlmacenOrigen.getSelectionModel().getSelectedItem().getArticulo().getCodigo();

                listaExistenciaAlmDestino.addAll(ManejoExistenciaArticulo.getInstancia().getExistenciaAlmacen(codAlm, codArticulo));

            } else {

                listaExistenciaAlmDestino.clear();
                listaExistenciaAlmDestino.addAll(ManejoExistenciaArticulo.getInstancia().getExistenciaAlmacen(codAlm));
            }

        }
    }

    private boolean existe(DetalleTransferenciaAlmacen det) {

        for (DetalleTransferenciaAlmacen detalle : tbDetalleTransferecnia.getItems()) {

            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())) {

                return true;

            }
        }

        return false;
    }

    @FXML
    private void cbUnidadAlmOrigenActionevent(ActionEvent event) {

        if (!(cbUnidadAlmOrigen.getSelectionModel().getSelectedIndex() == -1)) {

            cbUnidadAlmDestino.getSelectionModel().select(cbUnidadAlmOrigen.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void cbUnidadAlmDestinoActionEvent(ActionEvent event) {
    }

}
