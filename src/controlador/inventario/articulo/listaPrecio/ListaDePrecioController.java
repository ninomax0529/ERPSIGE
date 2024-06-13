/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo.listaPrecio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import manejo.articulo.ManejoArticulo;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoListaDePrecio;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.DetalleListaDePrecio;
import modelo.ListaDePrecio;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ListaDePrecioController implements Initializable {

    @FXML
    private TabPane tabPane;

    /**
     * @return the listaDePrecio
     */
    public ListaDePrecio getListaDePrecio() {
        return listaDePrecio;
    }

    /**
     * @param listaDePrecio the listaDePrecio to set
     */
    public void setListaDePrecio(ListaDePrecio listaDePrecio) {
        this.listaDePrecio = listaDePrecio;
    }

    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private TableView<ListaDePrecio> tbListaDePrecio;
    @FXML
    private TableColumn<ListaDePrecio, Integer> tbcCodigo;
    @FXML
    private TableColumn<ListaDePrecio, String> tbcNombre;
    @FXML
    private TableColumn<ListaDePrecio, String> tbcTipoLista;
    @FXML
    private TableColumn<ListaDePrecio, String> tbcTipoVenta;
    @FXML
    private TableColumn<ListaDePrecio, String> tbcHabilitada;

    /*Articulo*/
    @FXML
    private TableView<Articulo> tbArticulo;
    @FXML
    private TableColumn<Articulo, Integer> tbcArticuloCodigo;
    @FXML
    private TableColumn<Articulo, String> tbcArticuloNombre;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private TableView<DetalleListaDePrecio> tbDetalleListaDePrecio;
    @FXML
    private TableColumn<DetalleListaDePrecio, String> tbcArtListaCodigo;
    @FXML
    private TableColumn<DetalleListaDePrecio, String> tbcArtListaNombre;
    @FXML
    private TableColumn<DetalleListaDePrecio, Double> tbcArtListaPrecio;
    @FXML
    private TableColumn<DetalleListaDePrecio, Double> tbcCantMinima;
    @FXML
    private TableColumn<DetalleListaDePrecio, Double> tbcCantMaxima;
    @FXML
    private TableColumn<DetalleListaDePrecio, String> tbcArtListaUnida;

    @FXML
    private TableColumn<DetalleListaDePrecio, Double> tbcCostoUnitario;
    @FXML
    private TableColumn<DetalleListaDePrecio, DetalleListaDePrecio> tbcHabilitado;

    @FXML
    private Label lbNombre;
    @FXML
    private Label lbTipoLista;
    @FXML
    private Label lbtipoVenta;
    @FXML
    private Tab tabAgregarArticuloListaPrecio;
    boolean actualizarListaPrecio;

    /**
     * Initializes the controller class.
     */
    private ListaDePrecio listaDePrecio;
    ObservableList<ListaDePrecio> listaListaDePrecio = FXCollections.observableArrayList();
    ObservableList<Articulo> listaArticulo = FXCollections.observableArrayList();
    ObservableList<DetalleListaDePrecio> listaDetalleListaDePrecio = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtFiltrarArticulo;
    @FXML
    private JFXTextField txtFiltrarArticuloPrecioLista;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTablaArticulo();
        iniciarTablaListaPrecio();
        iniciarTablaDetalleListaDePrecio();
        tabAgregarArticuloListaPrecio.setDisable(true);

        inicializarFiltroArticulo();
        inicializarFiltroDetallePrecioLista();
    }

    private void inicializarFiltroArticulo() {

        FilteredList<Articulo> filteredData = new FilteredList<>(tbArticulo.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltrarArticulo.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getDescripcion()!= null && filtro.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getNumero()!= null && filtro.getNumero().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Articulo> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbArticulo.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbArticulo.setItems(sortedData);
    }

    private void inicializarFiltroDetallePrecioLista() {

        FilteredList<DetalleListaDePrecio> filteredData = new FilteredList<>(tbDetalleListaDePrecio.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltrarArticuloPrecioLista.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getArticulo().getDescripcion()!= null && filtro.getArticulo().getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getArticulo().getNumero()!= null && filtro.getArticulo().getNumero().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<DetalleListaDePrecio> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbDetalleListaDePrecio.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbDetalleListaDePrecio.setItems(sortedData);
    }

    public void iniciarTablaListaPrecio() {

        listaListaDePrecio.clear();

        tbListaDePrecio.setItems(listaListaDePrecio);
        listaListaDePrecio.addAll(ManejoListaDePrecio.getInstancia().getListaDePrecio());

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcTipoLista.setCellValueFactory(new PropertyValueFactory<>("tipoLista"));
        tbcTipoVenta.setCellValueFactory(new PropertyValueFactory<>("tipoVenta"));

        tbcTipoLista.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getTipoLista().getNombre());
                    }
                    return property;
                });

        tbcTipoVenta.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getTipoVenta().getNombre());
                    }
                    return property;
                });

        tbcHabilitada.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getHabilitada() ? "SI" : "NO");
                    }
                    return property;
                });

    }

    public void iniciarTablaArticulo() {

        tbArticulo.setEditable(true);
        listaArticulo.addAll(ManejoArticulo.getInstancia().getListaArticulos());
        tbArticulo.setItems(listaArticulo);

        tbcArticuloCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcArticuloNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
//
//        levelChoice = FXCollections.observableArrayList("Bla", "Blo");
//
//        levelColumn.setCellValueFactory(
//                new PropertyValueFactory<ClassesProperty, String>("level")
//        );
//        levelColumn.setCellFactory(ComboBoxTableCell.forTableColumn(levelChoice));
//        levelColumn.setOnEditCommit(
//                new EventHandler<CellEditEvent<ClassesProperty, String>>() {
//            @Override
//            public void handle(CellEditEvent<ClassesProperty, String> t) {
//                ((ClassesProperty) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLevel(t.getNewValue());
//            }
//        ;
//    }
//
//    );

    }

    public void iniciarTablaDetalleListaDePrecio() {

        listaDetalleListaDePrecio.clear();

        tbcArtListaCodigo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcArtListaNombre.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcArtListaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tbcArtListaUnida.setCellValueFactory(new PropertyValueFactory<>("unidadSalida"));
        tbcCantMinima.setCellValueFactory(new PropertyValueFactory<>("cantidadMinima"));
        tbcCantMaxima.setCellValueFactory(new PropertyValueFactory<>("cantidadMaxima"));
        tbcCostoUnitario.setCellValueFactory(new PropertyValueFactory<>("costoUnitario"));

        tbcHabilitada.setCellValueFactory(new PropertyValueFactory<>("habilitado"));

        tbcHabilitado.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbDetalleListaDePrecio.setEditable(true);

        tbcArtListaUnida.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidadSalida().getDescripcion());
                    }
                    return property;
                });

        tbcArtListaNombre.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getDescripcion());
                    }
                    return property;
                });

        tbcArtListaCodigo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getNumero().toString());
                    }
                    return property;
                });

        tbcArtListaPrecio.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcCostoUnitario.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcCantMinima.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcCantMaxima.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcArtListaPrecio.setOnEditCommit(data -> {

            try {

                DetalleListaDePrecio p = data.getRowValue();

                if (data.getNewValue() >= 1) {

                    p.setPrecio(data.getNewValue());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleListaDePrecio.refresh();
                    tbDetalleListaDePrecio.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcCostoUnitario.setOnEditCommit(data -> {

            try {

                DetalleListaDePrecio p = data.getRowValue();

                if (data.getNewValue() >= 1) {

                    p.setCostoUnitario(data.getNewValue());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleListaDePrecio.refresh();
                    tbDetalleListaDePrecio.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcCantMaxima.setOnEditCommit(data -> {

            try {

                DetalleListaDePrecio p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setCantidadMaxima(data.getNewValue());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleListaDePrecio.refresh();
                    tbDetalleListaDePrecio.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcCantMinima.setOnEditCommit(data -> {

            try {

                DetalleListaDePrecio p = data.getRowValue();

                if (data.getNewValue() >= 0) {

                    p.setCantidadMinima(data.getNewValue());

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbDetalleListaDePrecio.refresh();
                    tbDetalleListaDePrecio.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcHabilitado.setCellFactory((TableColumn<DetalleListaDePrecio, DetalleListaDePrecio> param) -> {

            TableCell<DetalleListaDePrecio, DetalleListaDePrecio> cellsc = new TableCell<DetalleListaDePrecio, DetalleListaDePrecio>() {
                @Override
                public void updateItem(DetalleListaDePrecio item, boolean empty) {
                    super.updateItem(item, empty);

                    Button btnHabilitar;

                    if (item != null) {

                        btnHabilitar = new Button();
                        btnHabilitar.setPrefSize(70.0, 25.0);

                        if (item.getHabilitado()) {

                            btnHabilitar.setText("SI");

                            btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 15px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 12pt;");

                        } else {

                            btnHabilitar.setText("NO");
                            btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 15px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 12pt;");

                        }

                        HBox hbox = new HBox();

//                        hbox.getChildren().addAll(imageview);
                        hbox.getChildren().add(btnHabilitar);

                        hbox.setAlignment(Pos.CENTER);

                        btnHabilitar.setOnMouseClicked((event) -> {

                            if (item.getHabilitado()) {

                                item.setHabilitado(false);
                                btnHabilitar.setText("NO");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 15px;\n"
                                        + "    -fx-b-radius: 15px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            } else {

                                item.setHabilitado(true);

                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 15px;\n"
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

        tbDetalleListaDePrecio.setItems(listaDetalleListaDePrecio);

    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        try {

//              System.out.println("Lista general de precio  " + listaDePrecio.getCodigo());
            ArticuloUnidad au;
            if (actualizarListaPrecio == true) {

                listaDePrecio.setDetalleListaDePrecioCollection(listaDetalleListaDePrecio);

                ManejoListaDePrecio.getInstancia().actualizar(listaDePrecio);

                for (DetalleListaDePrecio dlp : listaDetalleListaDePrecio) {

                    au = ManejoArticuloUnidad.getInstancia().getArticuloUnidadSslida(dlp.getArticulo().getCodigo(), dlp.getUnidadSalida().getCodigo());
                    au.setPrecioVenta(dlp.getPrecio());
                    au.setCostoUnitario(dlp.getCostoUnitario());
                    ManejoArticuloUnidad.getInstancia().actualizar(au);

                }

                listaDetalleListaDePrecio.clear();

                ClaseUtil.mensaje("Precio Actualizado correctamente");

            } else {

                Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/articulo/listaPrecio/RegistroListaDePrecio.fxml"));

                ClaseUtil.crearStageModal(root);

                listaListaDePrecio.clear();
                listaListaDePrecio.addAll(ManejoListaDePrecio.getInstancia().getListaDePrecio());
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void btnEditarActionEvent(ActionEvent event) throws IOException {

        if (tbArticulo.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar una Lista");

        } else {

            setListaDePrecio(tbListaDePrecio.getSelectionModel().getSelectedItem());

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/vista/inventario/articulo/listaPrecio/RegistroListaDePrecio.fxml"));
            loader.load();

            Parent root = loader.getRoot();

            RegistroListaDePrecioController registroListaDePrecioController = loader.getController();

            registroListaDePrecioController.setEditar(true);
            registroListaDePrecioController.setListaDePrecio(getListaDePrecio());

            registroListaDePrecioController.llenarCampo();

            ClaseUtil.getStageModal(root);

            listaArticulo.clear();
            listaArticulo.addAll(ManejoArticulo.getInstancia().getListaArticulo());

        }

    }

    @FXML
    private void tbListaDePrecioMouseClicked(MouseEvent event) {

        if (!(tbListaDePrecio.getSelectionModel().getSelectedIndex() == -1)) {

            tabAgregarArticuloListaPrecio.setDisable(false);
            actualizarListaPrecio = true;
            btnNuevo.setText("Actualizar");

            setListaDePrecio(tbListaDePrecio.getSelectionModel().getSelectedItem());
            lbNombre.setText(getListaDePrecio().getNombre());
            lbTipoLista.setText(getListaDePrecio().getTipoLista().getNombre());
            lbtipoVenta.setText(getListaDePrecio().getTipoVenta().getNombre());

            listaDetalleListaDePrecio.clear();

            listaDetalleListaDePrecio.setAll(ManejoListaDePrecio
                    .getInstancia()
                    .getDetalleListaDePrecio(getListaDePrecio().getCodigo()));

            tbDetalleListaDePrecio.setItems(listaDetalleListaDePrecio);
            inicializarFiltroDetallePrecioLista();

            if (event.getClickCount() == 2) {
                tabPane.getSelectionModel().select(1);
            }

        } else {

            listaDetalleListaDePrecio.clear();
            tabAgregarArticuloListaPrecio.setDisable(true);
            actualizarListaPrecio = false;
            btnNuevo.setText("Nuevo");
            tbDetalleListaDePrecio.setItems(listaDetalleListaDePrecio);
            inicializarFiltroDetallePrecioLista();
        }
    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {

        if (!(tbArticulo.getSelectionModel().getSelectedIndex() == -1)) {

            Articulo articulo = tbArticulo.getSelectionModel().getSelectedItem();

            List<ArticuloUnidad> listaAU = ManejoArticulo.getInstancia().getArticuloUnidad(articulo.getCodigo());

            for (ArticuloUnidad articuloUnidad : listaAU) {

                DetalleListaDePrecio det = new DetalleListaDePrecio();

                det.setArticulo(articulo);
                det.setHabilitado(true);
                det.setListaDePrecio(getListaDePrecio());
                det.setPrecio(ManejoListaDePrecio.getInstancia()
                        .getPrecioVentaDeArticulo(articulo.getCodigo(), articuloUnidad.getUnidad().getCodigo()));

                det.setUnidadSalida(articuloUnidad.getUnidad());
                det.setMargenDeBeneficio(articulo.getMargenBeneficio1());
                det.setPorcientoUtilidad(articulo.getPorcientoUtilidad1());
                det.setCostoUnitario(articuloUnidad.getCostoUnitario());
                det.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

                if (!existe(det)) {

                    listaDetalleListaDePrecio.add(det);

                }

            }

        } else {
            ClaseUtil.mensaje("Tiene que selccionar un articulo");
        }

    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        if (!(tbDetalleListaDePrecio.getSelectionModel().getSelectedIndex() == -1)) {

            listaDetalleListaDePrecio.remove(tbDetalleListaDePrecio.getSelectionModel().getFocusedIndex());

        } else {
            ClaseUtil.mensaje("Tiene que selccionar un articulo");
        }

    }

    private boolean existe(DetalleListaDePrecio det) {

        for (DetalleListaDePrecio detalle : tbDetalleListaDePrecio.getItems()) {

            if (Objects.equals(detalle.getArticulo().getCodigo(), det.getArticulo().getCodigo())
                    && Objects.equals(detalle.getUnidadSalida().getCodigo(), det.getUnidadSalida().getCodigo())) {

                return true;

            }
        }

        return false;
    }

}
