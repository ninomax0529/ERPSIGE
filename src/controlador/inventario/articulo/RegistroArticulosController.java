/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.ManejoConfiguracion;
import manejo.articulo.ManejoAlmacen;
import manejo.articulo.ManejoArticulo;
import manejo.articulo.ManejoArticuloUnidad;
import manejo.articulo.ManejoCalidadPintura;
import manejo.articulo.ManejoCategoria;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.articulo.ManejoListaDePrecio;
import manejo.articulo.ManejoSubCategoria;
import manejo.articulo.ManejoTipoArticulo;
import manejo.articulo.ManejoTipoPintura;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import manejo.unidad.ManejoUnidad;
import modelo.Almacen;
import modelo.Articulo;
import modelo.ArticuloUnidad;
import modelo.CalidadPintura;
import modelo.Categoria;
import modelo.DetalleListaDePrecio;
import modelo.ExistenciaArticulo;
import modelo.ListaDePrecio;
import modelo.SecuenciaDocumento;
import modelo.SubCategoria;
import modelo.TipoArticulo;
import modelo.TipoPintura;
import modelo.Unidad;
import utiles.ClaseUtil;
import utiles.FormatNum;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroArticulosController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtDescripcion;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXCheckBox chInventariable;
    @FXML
    private JFXCheckBox chExcentoItbis;
    @FXML
    private JFXCheckBox chActivo;
    @FXML
    private JFXButton btnFoto;
    @FXML
    private ImageView imgFoto;
    @FXML
    private JFXCheckBox chsePuedeDetallar;
    @FXML
    private JFXTextField txtFiltrarArticulo;
    @FXML
    private TableView<Unidad> tbAUnidad;
    @FXML
    private TableColumn<Unidad, String> tbcUnidad;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private TableView<ArticuloUnidad> tbArticuloUnidad;
    @FXML
    private TableColumn<ArticuloUnidad, String> tbcArticuloUnidad;
    @FXML
    private TableColumn<ArticuloUnidad, Double> tbcCantidadUnidades;
    @FXML
    private TableColumn<ArticuloUnidad, Double> tbcCostoUnidad;
    @FXML
    private TableColumn<ArticuloUnidad, Double> tbcPrecioVenta;
    @FXML
    private TableColumn<ArticuloUnidad, Double> tbcFactorUnidad;

    @FXML
    private JFXComboBox<TipoArticulo> cbTipoArticulo;
    @FXML
    private TableView<Almacen> tbAlmacen;
    @FXML
    private TableColumn<Almacen, Integer> tbcCodigoAlmacen;
    @FXML
    private TableColumn<Almacen, String> tbcNombreAlmacen;
    @FXML
    private JFXTextField txtCantidadAlmacen;
    @FXML
    private TableView<ExistenciaArticulo> tbArticuloAlmacen;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcAlmacenArtAlm;
    @FXML
    private TableColumn<ExistenciaArticulo, Double> tbcExistencia;
    @FXML
    private TableColumn<ExistenciaArticulo, String> tbcUnidadExistencia;
    @FXML
    private JFXTextField txtExistenciaArticulo;
    @FXML
    private JFXButton btnAgregarArticalmacen;
    @FXML
    private JFXButton btnEliminarartalmacen;
    @FXML
    private JFXComboBox<CalidadPintura> cbCalidad;
    @FXML
    private JFXTextField txtModelo;
    @FXML
    private JFXTextField txtImei;
    @FXML
    private JFXTextField txtSin;
    @FXML
    private JFXRadioButton rbServProfecional;
    @FXML
    private JFXRadioButton rbServTecnico;
    ToggleGroup toggleButton = new ToggleGroup();
    ToggleGroup toggleVentaPor = new ToggleGroup();
    @FXML
    private JFXTextField txtPorcientoItbis;
    @FXML
    private JFXRadioButton rbPorUnidad;
    @FXML
    private JFXRadioButton rbPorBascula;
    @FXML
    private JFXRadioButton rbPorPaquete;
    @FXML
    private JFXTextField txtPrecioDeVenta;

    public ImageView getImgFoto() {
        return imgFoto;
    }

    public void setImgFoto(ImageView imgFoto) {
        this.imgFoto = imgFoto;
    }
    @FXML
    private JFXComboBox<Categoria> cbCategoria;
    @FXML
    private JFXComboBox<SubCategoria> cbSubCategoria;

    private Articulo articulo;
    boolean editar = false;

    ObservableList<Categoria> listaCategoria = FXCollections.observableArrayList();
    ObservableList<SubCategoria> listaSubCategoria = FXCollections.observableArrayList();
    ObservableList<Unidad> listaUnidad = FXCollections.observableArrayList();
    ObservableList<ArticuloUnidad> listaArticuloUnidad = FXCollections.observableArrayList();
    ObservableList<TipoArticulo> listaTipoArticulo = FXCollections.observableArrayList();
    ObservableList<ExistenciaArticulo> listaExistenciaArticulo = FXCollections.observableArrayList();
    ObservableList<Almacen> listaAlmacen = FXCollections.observableArrayList();
    ObservableList<CalidadPintura> listaCalidadPintura = FXCollections.observableArrayList();
    ObservableList<TipoPintura> listaTipoPintura = FXCollections.observableArrayList();

    @FXML
    private JFXTextField txtPrecioCompra;

    @FXML
    private JFXButton btnCalcularPv;

    @FXML
    private JFXTextField txtcodigoBarra;
    @FXML
    private JFXComboBox<Unidad> cbUnidadEntrada;

    @FXML
    private JFXTextField txtMarca;
    @FXML
    private JFXCheckBox chParaVenta;
    @FXML
    private JFXCheckBox chParaConsumo;

    @FXML
    private Label lbRutaImg;
    private boolean capsIsOn;

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarCombox();
        iniciarTabla();
        inicializarFiltroUnidad();
        inicializarSecuencia();
        iniciarTablaExistenciaArticulo();
        iniciarTablaAlmacen();
        rbServProfecional.setToggleGroup(toggleButton);
        rbServTecnico.setToggleGroup(toggleButton);

        rbPorUnidad.setToggleGroup(toggleVentaPor);
        rbPorBascula.setToggleGroup(toggleVentaPor);
        rbPorPaquete.setToggleGroup(toggleVentaPor);

        if (!isEditar()) {
            setArticulo(new Articulo());
        }

    }

    public void iniciarTablaAlmacen() {

        listaAlmacen.clear();
        listaAlmacen.addAll(ManejoAlmacen.getInstancia()
                .getAlmacenPorUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo()));
        tbAlmacen.setItems(listaAlmacen);

        tbcCodigoAlmacen.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombreAlmacen.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        txtCantidadAlmacen.setText(Integer.toString(listaAlmacen.size()));

    }

    public void iniciarTablaExistenciaArticulo() {

        listaExistenciaArticulo.clear();

        tbArticuloAlmacen.setItems(listaExistenciaArticulo);

        tbcAlmacenArtAlm.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcExistencia.setCellValueFactory(new PropertyValueFactory<>("existencia"));

        tbcAlmacenArtAlm.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getNombreAlmacen());
                    }
                    return property;
                });

        tbcUnidadExistencia.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();

                    System.out.println("cellData.getValue().getUnidad() " + cellData.getValue()
                            + cellData.getValue().getUnidad());
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

    }

    public void iniciarTabla() {

        tbArticuloUnidad.setEditable(true);
        tbAUnidad.setItems(listaUnidad);
        listaUnidad.setAll(ManejoUnidad.getInstancia().getListaUnidad());
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tbArticuloUnidad.setItems(listaArticuloUnidad);

        tbcArticuloUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcCantidadUnidades.setCellValueFactory(new PropertyValueFactory<>("cantidadUnidades"));
        tbcCostoUnidad.setCellValueFactory(new PropertyValueFactory<>("costoUnitario"));
        tbcPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        tbcFactorUnidad.setCellValueFactory(new PropertyValueFactory<>("fatorVenta"));

        tbcArticuloUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });

        tbcCantidadUnidades.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcCostoUnidad.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcPrecioVenta.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcCantidadUnidades.setOnEditCommit(data -> {

            try {

                Double costo = 0.00;
                ArticuloUnidad p = data.getRowValue();

                if (data.getNewValue() >= 1) {

                    p.setCantidadUnidades(data.getNewValue());

                    costo = Double.parseDouble(txtPrecioCompra.getText());
                    costo = ClaseUtil.roundDouble(costo / p.getCantidadUnidades(), 4);
                    p.setCostoUnitario(costo);
                    tbArticuloUnidad.refresh();
                    tbArticuloUnidad.requestFocus();

                    if (tbArticuloAlmacen.getItems().size() > 0) {

                        if (editar == true && tbArticuloAlmacen.getItems().get(0).getExistencia() > 0) {

                            ClaseUtil.mensaje("TIENE QUE AJUSTAR LA EXISTENCIA DE ESTE ARTICULO \n EN FUNCION"
                                    + " DE SU UNIDAD MINIMA DE MEDIDA ");
                        }
                    }

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbArticuloUnidad.refresh();
                    tbArticuloUnidad.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcPrecioVenta.setOnEditCommit(data -> {

            try {

                ArticuloUnidad p = data.getRowValue();

                if (data.getNewValue() >= 1) {

                    p.setPrecioVenta(data.getNewValue());
                    tbArticuloUnidad.refresh();
                    tbArticuloUnidad.requestFocus();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbArticuloUnidad.refresh();
                    tbArticuloUnidad.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcCostoUnidad.setOnEditCommit(data -> {

            try {

                ArticuloUnidad p = data.getRowValue();

                if (data.getNewValue() >= 1) {

                    p.setCostoUnitario(data.getNewValue());
                    tbArticuloUnidad.refresh();
                    tbArticuloUnidad.requestFocus();

                } else {

                    ClaseUtil.mensaje("El valor no puede ser igual o menor que cero");
                    tbArticuloUnidad.refresh();
                    tbArticuloUnidad.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    private void inicializarFiltroUnidad() {

        FilteredList<Unidad> filteredData = new FilteredList<>(tbAUnidad.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltrarArticulo.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filtro.getDescripcion() != null && filtro.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Unidad> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbAUnidad.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbAUnidad.setItems(sortedData);
    }

    private void inicializarCombox() {

        cbCategoria.setConverter(new StringConverter<Categoria>() {

            @Override
            public String toString(Categoria categoria) {
                return String.valueOf(categoria.getNombre());
            }

            @Override
            public Categoria fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbCalidad.setConverter(new StringConverter<CalidadPintura>() {

            @Override
            public String toString(CalidadPintura calidad) {
                return String.valueOf(calidad.getNombre());
            }

            @Override
            public CalidadPintura fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbSubCategoria.setConverter(new StringConverter<SubCategoria>() {

            @Override
            public String toString(SubCategoria subCategoria) {
                return String.valueOf(subCategoria.getNombre());
            }

            @Override
            public SubCategoria fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbUnidadEntrada.setConverter(new StringConverter<Unidad>() {

            @Override
            public String toString(Unidad subCategoria) {
                return String.valueOf(subCategoria.getDescripcion());
            }

            @Override
            public Unidad fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoArticulo.setConverter(new StringConverter<TipoArticulo>() {

            @Override
            public String toString(TipoArticulo tipoArticulo) {
                return String.valueOf(tipoArticulo.getDescripcion());
            }

            @Override
            public TipoArticulo fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaCategoria.addAll(ManejoCategoria.getInstancia().getCategoria());
        listaUnidad.addAll(ManejoUnidad.getInstancia().getLista());
        listaTipoArticulo.addAll(ManejoTipoArticulo.getInstancia().getListaTipoArticulo());
        listaCalidadPintura.addAll(ManejoCalidadPintura.getInstancia().getLista());
        listaTipoPintura.addAll(ManejoTipoPintura.getInstancia().getListaTipoPintura());

        cbCategoria.setItems(listaCategoria);
        cbCalidad.setItems(listaCalidadPintura);

        listaSubCategoria.clear();

//        if (editar == false) {
        cbSubCategoria.setItems(listaSubCategoria);
//        }

        cbUnidadEntrada.setItems(listaUnidad);
        cbTipoArticulo.setItems(listaTipoArticulo);

        cbSubCategoria.getSelectionModel().selectFirst();
        cbCategoria.getSelectionModel().selectFirst();
        cbTipoArticulo.getSelectionModel().selectFirst();

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (cbCategoria.getSelectionModel().getSelectedIndex() == -1) {

                cbCategoria.getSelectionModel().selectFirst();

            }

            if (cbSubCategoria.getSelectionModel().getSelectedIndex() == -1) {

                cbSubCategoria.getSelectionModel().select(ManejoSubCategoria.getInstancia().getSubCategoria(1));
            }

            if (cbCalidad.getSelectionModel().getSelectedIndex() == -1) {

                getArticulo().setCalidadPintura(null);

            } else {
                getArticulo().setCalidadPintura(cbCalidad.getSelectionModel().getSelectedItem());
            }

            if (txtMarca.getText().isEmpty()) {

//                ClaseUtil.mensaje("Tiene que digitar un marca");
                txtMarca.setText("na");
//                return;
            }

            if (txtDescripcion.getText().isEmpty()) {

                ClaseUtil.mensaje(" Tiene que digitar una descripcion ");
                txtDescripcion.requestFocus();
                return;
            }

            if (cbUnidadEntrada.getSelectionModel().getSelectedIndex() == -1) {

//                ClaseUtil.mensaje("Tiene que seleccionar una unidad de Entrada");
                cbUnidadEntrada.getSelectionModel().select(ManejoUnidad.getInstancia().getUnidad(1));

            }

            if (cbTipoArticulo.getSelectionModel().getSelectedItem().getCodigo() == 3) {

                if (rbServProfecional.isSelected() == false && rbServTecnico.isSelected() == false) {
                    ClaseUtil.mensaje(" Tiene que seleccionar un tipo de servicio ");
                    cbTipoArticulo.requestFocus();
                    return;
                }

            }

            if (chExcentoItbis.isSelected() == false && txtPorcientoItbis.getText().isEmpty()) {

                ClaseUtil.mensaje(" Tiene que digitar el porciento del Itbis ");
                txtPorcientoItbis.requestFocus();

                return;
            } else if (chExcentoItbis.isSelected() == true) {
                txtPorcientoItbis.setText("0");
            }

            getArticulo().setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
            getArticulo().setModelo(txtModelo.getText().isEmpty() ? "n/a" : txtModelo.getText());
            getArticulo().setNumeroImei(txtImei.getText().isEmpty() ? "n/a" : txtImei.getText());
            getArticulo().setNumeroSim(txtSin.getText().isEmpty() ? "n/a" : txtSin.getText());

            if (rbServProfecional.isSelected()) {
                getArticulo().setTipoServicio(1);
            } else if (rbServTecnico.isSelected()) {
                getArticulo().setTipoServicio(2);

            }

            if (rbPorUnidad.isSelected()) {
                getArticulo().setUnidadDeVenta(1);
            } else if (rbPorBascula.isSelected()) {
                getArticulo().setUnidadDeVenta(2);

            } else if (rbPorPaquete.isSelected()) {
                getArticulo().setUnidadDeVenta(3);

            } else {
                ClaseUtil.mensaje(" Tiene quew seleccionar una unidad de venta ");
                return;
            }

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(getArticulo().getUnidadDeNegocio().getCodigo(), 21);

            if (!isEditar()) {

                if (!(secDoc == null)) {

                    boolean existe = ManejoArticulo.getInstancia().existeSecuencia(secDoc.getNumero());

                    if (existe) {

                        System.out.println("existe " + secDoc.getNumero());

                        while (ManejoArticulo.getInstancia().existeSecuencia(secDoc.getNumero())) {

                            secDoc.setNumero(secDoc.getNumero() + 1);
                            ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                        }

                        secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(getArticulo().getUnidadDeNegocio().getCodigo(), 21);

                        getArticulo().setNumero(secDoc.getNumero());
                        getArticulo().setSecuenciaDocumento(secDoc);

                    } else {

                        System.out.println("No existe " + secDoc.getNumero());
                        getArticulo().setNumero(secDoc.getNumero());
                        getArticulo().setSecuenciaDocumento(secDoc);
                        secDoc.setNumero(secDoc.getNumero() + 1);
                        ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                    }

                } else {

                    ClaseUtil.mensaje(" La secuencia para articulo de la unidad de negocio "
                            + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                    return;
                }

            }

            Double precioCompra = Double.parseDouble(txtPrecioCompra.getText().isEmpty() ? "0" : txtPrecioCompra.getText()),
                    //                    precioCompraUnitario = Double.parseDouble(txtPreciioCompraUnidatrio.getText().isEmpty() ? "0" : txtPreciioCompraUnidatrio.getText()),
                    precioVenta1 = Double.parseDouble(txtPrecioDeVenta.getText().isEmpty() ? "0" : txtPrecioDeVenta.getText()),
                    //                    precioVenta2 = Double.parseDouble(txtPrecioVenta2.getText().isEmpty() ? "0" : txtPrecioVenta2.getText()),
                    //                    precioVenta3 = Double.parseDouble(txtPrecioVenta3.getText().isEmpty() ? "0" : txtPrecioVenta3.getText()),
                    precioVenta1Itbis = Double.parseDouble("0"),
                    //                    precioVenta2Itbis = Double.parseDouble(txtPrecioVenta2Itbis.getText().isEmpty() ? "0" : txtPrecioVenta2Itbis.getText()),
                    //                    precioVenta3Itbis = Double.parseDouble(txtPrecioVenta3Itbis.getText().isEmpty() ? "0" : txtPrecioVenta3Itbis.getText()),
                    porciento1 = Double.parseDouble("0"),
                    //                    porciento2 = Double.parseDouble(txtPorcientoUtilidad2.getText().isEmpty() ? "0" : txtPorcientoUtilidad2.getText()),
                    //                    porciento3 = Double.parseDouble(txtPorcientoUtilidad3.getText().isEmpty() ? "0" : txtPorcientoUtilidad3.getText()),
                    margenUtilidad1 = Double.parseDouble("0");
//                    margenUtilidad2 = Double.parseDouble(txtMargenUtilidad2.getText().isEmpty() ? "0" : txtMargenUtilidad2.getText()),
//                    margenUtilidad3 = Double.parseDouble(txtMargenUtilidad3.getText().isEmpty() ? "0" : txtMargenUtilidad3.getText());

            String codigoBarra = txtcodigoBarra.getText().isEmpty() ? txtCodigo.getText() : txtcodigoBarra.getText();

            getArticulo().setApedir(0.0);
            getArticulo().setMinimo(0.0);
            getArticulo().setCategoria(cbCategoria.getValue());
            getArticulo().setSubCategoria(cbSubCategoria.getValue());
            getArticulo().setCodigoBarra(codigoBarra);
            getArticulo().setAlmacen(ManejoAlmacen.getInstancia().getLista().get(0).getCodigo());
            getArticulo().setDescripcion(txtDescripcion.getText().trim());
            getArticulo().setExentoItbis(chExcentoItbis.isSelected());
            getArticulo().setItbisGravado(Double.parseDouble(txtPorcientoItbis.getText()));
            if (getArticulo().getItbisGravado() < 0) {

                ClaseUtil.mensaje(" El porciento del Itbis gravado no puede ser menor que cero ");
                txtPorcientoItbis.requestFocus();
                return;
            }

            getArticulo().setNombre(txtDescripcion.getText().trim());
            getArticulo().setInventariable(chInventariable.isSelected());
            getArticulo().setActivo(chActivo.isSelected());
            getArticulo().setReferencia("n/a");
            getArticulo().setUbicacion("Almacen Principal");
            getArticulo().setUnidadEntrada(cbUnidadEntrada.getSelectionModel().getSelectedItem());
            getArticulo().setTipoArticulo(cbTipoArticulo.getSelectionModel().getSelectedItem());
            getArticulo().setPrecioCompra(precioCompra);
            getArticulo().setPrecioCompraUnitario(precioCompra);
            getArticulo().setPrecioVenta1(precioVenta1);
            getArticulo().setCodigoBarra(codigoBarra.trim());
            getArticulo().setMarca(txtMarca.getText().trim());
            getArticulo().setParaConsumo(chParaConsumo.isSelected());
            getArticulo().setParaDetalle(chsePuedeDetallar.isSelected());
            getArticulo().setCalidadPintura(cbCalidad.getSelectionModel().getSelectedItem());

            if (!(cbCalidad.getSelectionModel().getSelectedIndex() == -1)) {

                getArticulo().setDescripcion(getArticulo().getNombre()
                        + " " + getArticulo()
                                .getSubCategoria().getNombre()
                        + " " + getArticulo().getCalidadPintura().getNombre());
            }

            getArticulo().setPrecioventa1Itbis(precioVenta1Itbis);
            getArticulo().setPorcientoUtilidad1(porciento1);
            getArticulo().setMargenBeneficio1(margenUtilidad1);

            if (isEditar()) {

                setArticulo(ManejoArticulo.getInstancia().actualizar(getArticulo()));

            } else {

                setArticulo(ManejoArticulo.getInstancia().salvar(getArticulo()));

            }

            for (ExistenciaArticulo ext : listaExistenciaArticulo) {

                ext.setUnidad(getArticulo().getUnidadEntrada());
                ext.setNombreUnidadBase(getArticulo().getUnidadEntrada().getDescripcion());
                ext.setUnidadDeNegocio(getArticulo().getUnidadDeNegocio());

                ManejoExistenciaArticulo.getInstancia().actualizar(ext);
            }

            agregarAlistaSDePrecioGeneral();

            ClaseUtil.mensaje(" Articulo Guardado Correctamente ");
            limpiar();

        } catch (Exception e) {

            ClaseUtil.mensaje(" Hubo un error Guardando el Articulo ");
            e.printStackTrace();
        }
    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();

    }

    @FXML
    void btnCalcularPvActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/inventario/articulo/CalcularPrecioVenta.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        CalcularPrecioVentaController calcularpv = loader.getController();

        calcularpv.setPrecioCompra(Double.parseDouble(txtPrecioCompra.getText()));
        calcularpv.llenarCampo();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        System.out.println("Esperando ....");
        stage.showAndWait();
        System.out.println("saliendo de la espera ...." + calcularpv.getPrecioVenta());
        Double precioItbis = 0.00;

//        Stage stage = ClaseUtil.getStageModal(root);
        if (!(calcularpv.getPrecioVenta() == null)) {

            if (calcularpv.getPrecioVentaSeleccionado() == 1) {

                precioItbis = calcularpv.getPrecioVenta();

                if (editar == true && getArticulo().getExentoItbis() == false) {

                    double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                    precioItbis = calcularpv.getPrecioVenta() * (itbis / 100 + 1);
                    precioItbis = FormatNum.FormatearDouble(precioItbis, 2);

                } else if (editar == false && chExcentoItbis.isSelected() == false) {

                    double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                    precioItbis = calcularpv.getPrecioVenta() * (itbis / 100 + 1);
                    precioItbis = FormatNum.FormatearDouble(precioItbis, 2);

                }

            } else if (calcularpv.getPrecioVentaSeleccionado() == 2) {

                precioItbis = FormatNum.FormatearDouble(calcularpv.getPrecioVenta(), 0);

                if (editar == true && getArticulo().getExentoItbis() == false) {

                    double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                    precioItbis = calcularpv.getPrecioVenta() * (itbis / 100 + 1);
                    precioItbis = FormatNum.FormatearDouble(precioItbis, 2);

                } else if (editar == false && chExcentoItbis.isSelected() == false) {

                    double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                    precioItbis = calcularpv.getPrecioVenta() * (itbis / 100 + 1);
                    precioItbis = FormatNum.FormatearDouble(precioItbis, 2);

                }

            } else if (calcularpv.getPrecioVentaSeleccionado() == 3) {

                precioItbis = calcularpv.getPrecioVenta();

                if (editar == true && getArticulo().getExentoItbis() == false) {

                    double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                    precioItbis = calcularpv.getPrecioVenta() * (itbis / 100 + 1);
                    precioItbis = FormatNum.FormatearDouble(precioItbis, 2);

                } else if (editar == false && chExcentoItbis.isSelected() == false) {

                    double itbis = ManejoConfiguracion.getInstancia().getConfiguracion().getItbis();
                    precioItbis = calcularpv.getPrecioVenta() * (itbis / 100 + 1);
                    precioItbis = FormatNum.FormatearDouble(precioItbis, 2);

                }

            }

        }
    }

    @FXML
    private void btnFotoActionEvent(ActionEvent event) {

        try {

//        final FileChooser fileChooser = new FileChooser();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.setInitialDirectory(
                    new File(System.getProperty("user.home")));

            fileChooser.getExtensionFilters().addAll(
                    //  new ExtensionFilter("Text Files", "*.txt"),
                    //                    new FileChooser.ExtensionFilter("XLS Files", "*.xls")
                    new ExtensionFilter("Imagen Files", "*.jpg", "*.png", "*.jpg")
            //                                new FileChooser.ExtensionFilter("All Files", "*.*")
            );

            try {

                File selectedFile = fileChooser.showOpenDialog(new Stage());

                if (selectedFile != null) {

                    lbRutaImg.setText(selectedFile.getAbsolutePath());

                    System.out.println("Paren paht " + selectedFile.getParentFile() + "Paren paht " + selectedFile.getCanonicalFile());

                    Image image = new Image(selectedFile.getCanonicalFile().toURI().toString(), 150, 100, false, false);

                    imgFoto.setImage(image);

                }

            } catch (IOException ex) {

                Logger.getLogger(RegistroArticulosController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void cbCategoriaActionEvent(ActionEvent event) {

        if (!(cbCategoria.getSelectionModel().getSelectedIndex() == -1)) {

            Categoria categoria = cbCategoria.getSelectionModel().getSelectedItem();
            listaSubCategoria.clear();
            listaSubCategoria.addAll(ManejoSubCategoria.getInstancia().getSubCategoria(categoria));

            if (categoria.getCodigo() == 2) {

                txtMarca.setText("TRIPLE A");

            } else {

                txtMarca.clear();
            }

        }
    }

    private void limpiar() {

        txtMarca.clear();
        txtModelo.clear();
        txtDescripcion.clear();
        txtSin.clear();
        txtImei.clear();
        txtPrecioCompra.clear();
        txtPrecioDeVenta.clear();

        cbCategoria.getSelectionModel().clearSelection();
        cbTipoArticulo.getSelectionModel().clearSelection();
        cbSubCategoria.getEditor().clear();
        cbUnidadEntrada.getSelectionModel().clearSelection();

        listaSubCategoria.clear();
        listaSubCategoria.clear();
        chExcentoItbis.setSelected(false);
        chActivo.setSelected(false);
        chInventariable.setSelected(false);
        rbPorBascula.setSelected(false);
        rbPorPaquete.setSelected(false);
        rbPorUnidad.setSelected(false);
        listaArticuloUnidad.clear();
        listaExistenciaArticulo.clear();

        imgFoto.setImage(null);
        txtcodigoBarra.clear();
        editar = false;
        setArticulo(new Articulo());

    }

    public void llenarCampo() {

//        inicializarCombox();
        txtCodigo.setText(getArticulo().getCodigo().toString());

        chExcentoItbis.setSelected(getArticulo().getExentoItbis());
        chActivo.setSelected(getArticulo().getActivo());
        chsePuedeDetallar.setSelected(getArticulo().getParaDetalle());
        chInventariable.setSelected(getArticulo().getInventariable());
        cbCategoria.getSelectionModel().select(getArticulo().getCategoria());

        if (getArticulo().getUnidadDeVenta() == 1) {
            rbPorUnidad.setSelected(true);
        } else if (getArticulo().getUnidadDeVenta() == 2) {
            rbPorBascula.setSelected(true);
        }
        if (getArticulo().getUnidadDeVenta() == 3) {
            rbPorPaquete.setSelected(true);
        }

        System.out.println("Sub Categoria " + getArticulo().getSubCategoria().getNombre());
        Categoria categoria = cbCategoria.getSelectionModel().getSelectedItem();
        listaSubCategoria.clear();

        cbSubCategoria.getSelectionModel().select(getArticulo().getSubCategoria());
        cbTipoArticulo.getSelectionModel().select(getArticulo().getTipoArticulo());
        cbCalidad.getSelectionModel().select(getArticulo().getCalidadPintura());

        listaSubCategoria.addAll(ManejoSubCategoria.getInstancia().getSubCategoria(categoria));

        cbUnidadEntrada.getSelectionModel().select(getArticulo().getUnidadEntrada());

        txtDescripcion.setText(getArticulo().getDescripcion());

        Double itbisGravado = getArticulo().getItbisGravado() == null ? 100.00 : getArticulo().getItbisGravado();
        txtPorcientoItbis.setText(itbisGravado.toString());

        txtcodigoBarra.setText(articulo.getCodigoBarra() == null ? "n/a" : articulo.getCodigoBarra());

        txtMarca.setText(articulo.getMarca() == null ? "na" : articulo.getMarca());
        txtModelo.setText(articulo.getModelo() == null ? "n/a" : articulo.getModelo());
        txtSin.setText(articulo.getNumeroSim() == null ? "n/a" : articulo.getNumeroSim());
        txtImei.setText(articulo.getNumeroImei() == null ? "n/a" : articulo.getNumeroImei());
        txtPrecioCompra.setText(getArticulo().getPrecioCompra().toString());
        txtPrecioDeVenta.setText(getArticulo().getPrecioVenta1()==null ? "0" :
                getArticulo().getPrecioVenta1().toString());

        if (getArticulo().getTipoArticulo().getCodigo() == 3) {

            if (!(getArticulo().getTipoServicio() == null)) {
                if (getArticulo().getTipoServicio() == 1) {
                    rbServProfecional.setSelected(true);
                } else if (getArticulo().getTipoServicio() == 2) {
                    rbServTecnico.setSelected(true);
                }
            }

        }

        listaArticuloUnidad.clear();
        listaAlmacen.clear();
        listaExistenciaArticulo.clear();

        listaExistenciaArticulo.addAll(ManejoExistenciaArticulo
                .getInstancia().getExistenciaArticulo(getArticulo().getCodigo()));

        listaArticuloUnidad.addAll(ManejoArticulo.getInstancia().getArticuloUnidad(getArticulo().getCodigo()));

        listaArticuloUnidad.forEach((au) -> {
            au.setPrecioVenta(ManejoListaDePrecio.getInstancia()
                    .getPrecioVentaDeArticulo(articulo.getCodigo(), au.getUnidad().getCodigo()));
        });

        File imageFile;

        if (getArticulo().getRutaImg() == null) {

            imageFile = new File("foto/img_articulo.jpg");

        } else {

            imageFile = new File(getArticulo().getRutaImg());
        }

        Image image = new Image(imageFile.toURI().toString(), 80, 50, false, false);

//           lbRutaImg.setText(imageFile.getAbsolutePath());
        imgFoto.setImage(image);
        iniciarTablaAlmacen();

    }

    /**
     * @return the articulo
     */
    public Articulo getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    private void inicializarSecuencia() {

        txtCodigo.setText(ManejoArticulo.getInstancia().getNumero().toString());
        txtcodigoBarra.setText("GFSC" + txtCodigo.getText());
    }

    @SuppressWarnings({"BoxedValueEquality", "NumberEquality"})
    private void agregarAlistaSDePrecioGeneral() {

        try {

            DetalleListaDePrecio detalleListaDePrecio = null;

            ListaDePrecio encabezadolistaDePrecio = ManejoListaDePrecio.getInstancia().getListaDePrecioGeneral(4);

            List<DetalleListaDePrecio> lisDetalleListaDePrecios = ManejoListaDePrecio.getInstancia().getDetalleListaDePrecio(encabezadolistaDePrecio.getCodigo());

//            List<ArticuloUnidad> listaArticuloUnidads = ManejoArticuloUnidad.getInstancia().getListaUnidadSslida(getArticulo().getCodigo());
//            for (ArticuloUnidad articuloUnidad : listaArticuloUnidads) {
            detalleListaDePrecio = new DetalleListaDePrecio();
            detalleListaDePrecio.setListaDePrecio(encabezadolistaDePrecio);
            detalleListaDePrecio.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            detalleListaDePrecio.setArticulo(getArticulo());
            detalleListaDePrecio.setPrecio(getArticulo().getPrecioVenta1());

            detalleListaDePrecio.setHabilitado(true);
            detalleListaDePrecio.setCostoUnitario(getArticulo().getPrecioCompra());
            detalleListaDePrecio.setUnidadSalida(getArticulo().getUnidadEntrada());

            for (DetalleListaDePrecio dpl : lisDetalleListaDePrecios) {

                System.out.println("dpl.getArticulo() " + dpl.getArticulo() + "detalleListaDePrecio.getArticulo() " + detalleListaDePrecio.getArticulo());

                if (Objects.equals(dpl.getArticulo().getCodigo(), detalleListaDePrecio.getArticulo().getCodigo())
                        && dpl.getHabilitado() == true) {

                    detalleListaDePrecio = dpl;
                    detalleListaDePrecio.setCostoUnitario(getArticulo().getPrecioCompra());
                    detalleListaDePrecio.setPrecio(getArticulo().getPrecioVenta1());

                }

            }

            lisDetalleListaDePrecios.add(detalleListaDePrecio);

////            }
            encabezadolistaDePrecio.setDetalleListaDePrecioCollection(lisDetalleListaDePrecios);

            ManejoListaDePrecio.getInstancia().actualizar(encabezadolistaDePrecio);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {

        if (cbUnidadEntrada.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que selccionar una unidad de entrada");
            return;
        }

        if (!(tbAUnidad.getSelectionModel().getSelectedIndex() == -1)) {

            Unidad unidad = tbAUnidad.getSelectionModel().getSelectedItem();

            ArticuloUnidad articuloUnidad = new ArticuloUnidad();

            if (unidad.equals(cbUnidadEntrada.getSelectionModel().getSelectedItem())) {

                articuloUnidad.setCantidadUnidades(1);
                articuloUnidad.setCostoUnitario(Double.parseDouble(txtPrecioCompra.getText().isEmpty() ? "0.00"
                        : txtPrecioCompra.getText()
                ));
            }

            articuloUnidad.setArticulo(getArticulo());
            articuloUnidad.setUnidad(unidad);
            articuloUnidad.setUnidadEntrada(cbUnidadEntrada.getSelectionModel().getSelectedItem().getCodigo());
            articuloUnidad.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            if (existe(articuloUnidad)) {

                ClaseUtil.mensaje(" Esta unidad ya esta agregada ");
                return;
            }

            listaArticuloUnidad.add(articuloUnidad);

        } else {

            ClaseUtil.mensaje("Tiene que seleccionar una unidad ");
        }

    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        if (!(tbArticuloUnidad.getSelectionModel().getSelectedIndex() == -1)) {

//            if (editar) {
//                
//                ManejoArticuloUnidad.getInstancia().eliminar(tbArticuloUnidad.getSelectionModel().getSelectedItem());
//            }
            listaArticuloUnidad.remove(tbArticuloUnidad.getSelectionModel().getFocusedIndex());

        } else {

            ClaseUtil.mensaje("Tiene que selccionar un articulo");
        }

    }

    private boolean existe(ArticuloUnidad det) {

        for (ArticuloUnidad detalle : tbArticuloUnidad.getItems()) {

            if (Objects.equals(detalle.getUnidad().getCodigo(), det.getUnidad().getCodigo())) {

                return true;

            }
        }

        return false;
    }

    @FXML
    private void cbTipoArticuloActionEvent(ActionEvent event) {

        if (!(cbTipoArticulo.getSelectionModel().getSelectedIndex() == -1)) {
//
//            if (cbTipoArticulo.getSelectionModel().getSelectedItem().getCodigo() == 3) {
//                hbTipoServicio.setVisible(true);
//            } else {
//                hbTipoServicio.setVisible(false);
//            }

            if (cbTipoArticulo.getSelectionModel().getSelectedItem().getCodigo() == 4) {

                chParaConsumo.setSelected(true);

            } else {
                chParaConsumo.setSelected(false);
            }
        }

    }

    @FXML
    private void cbUnidadEntradaActionEvent(ActionEvent event) {

        txtPrecioCompra.requestFocus();
    }

    @FXML
    private void btnAgregarArticalmacenActionEvent(ActionEvent event) {

        try {

            if (!(tbAlmacen.getSelectionModel().getSelectedIndex() == -1)) {

                ExistenciaArticulo existenciaArticulo = new ExistenciaArticulo();
                Almacen almacen = tbAlmacen.getSelectionModel().getSelectedItem();

                existenciaArticulo.setAlmacen(almacen);
                existenciaArticulo.setArticulo(getArticulo());
                existenciaArticulo.setExistencia(0.00);
                existenciaArticulo.setFecha(new Date());
                existenciaArticulo.setNombreAlmacen(almacen.getNombre());
                existenciaArticulo.setUbicacion(almacen.getUbicacion());
                existenciaArticulo.setUnidad(cbUnidadEntrada.getValue());
                existenciaArticulo.setEstado("na");
                existenciaArticulo.setExistenciaAnterior(0.0);
                existenciaArticulo.setPrecioVentaAnterior(0.00);
                System.out.println("VariablesGlobales.USUARIO.getUnidadDeNegocio() " + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
                existenciaArticulo.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

                if (existe(existenciaArticulo)) {

                    ClaseUtil.mensaje("Este Articulo ya Esta Asignado a este Almacen ");
                    return;
                }

                listaExistenciaArticulo.add(existenciaArticulo);

            } else {

                ClaseUtil.mensaje("Tiene que seleccionar un almacen");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEliminarartalmacenActionEvent(ActionEvent event) {

        if (tbArticuloAlmacen.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar un registro");
            return;
        }

        listaExistenciaArticulo.remove(tbArticuloAlmacen.getSelectionModel().getSelectedIndex());

    }

    private boolean existe(ExistenciaArticulo det) {

        for (ExistenciaArticulo detalle : tbArticuloAlmacen.getItems()) {

            if (Objects.equals(detalle.getAlmacen().getCodigo(), det.getAlmacen().getCodigo())) {

                return true;

            }
        }

        return false;
    }

    @FXML
    private void txtCantidadKeyPressed(KeyEvent event) {
    }

}
