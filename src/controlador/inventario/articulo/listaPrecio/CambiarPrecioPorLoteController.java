/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo.listaPrecio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;
import manejo.articulo.ManejoCalidadPintura;
import manejo.articulo.ManejoCategoria;
import manejo.articulo.ManejoListaDePrecio;
import manejo.articulo.ManejoSubCategoria;
import manejo.articulo.ManejoTipoArticulo;
import manejo.unidad.ManejoUnidad;
import modelo.CalidadPintura;
import modelo.Categoria;
import modelo.DetalleListaDePrecio;
import modelo.ListaDePrecio;
import modelo.SubCategoria;
import modelo.TipoArticulo;
import modelo.Unidad;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class CambiarPrecioPorLoteController implements Initializable {

    @FXML
    private JFXComboBox<TipoArticulo> cbTipoArticulo;
    @FXML
    private JFXComboBox<Categoria> cbCategoria;
    @FXML
    private JFXComboBox<SubCategoria> cbSubCategoria;
    @FXML
    private JFXComboBox<Unidad> cbUnidad;
    @FXML
    private JFXButton btnBuecarArticulo;
    @FXML
    private TextField txtNuevoPrecio;
    @FXML
    private JFXButton btnAplicarPrecio;
    @FXML
    private TableView<DetalleListaDePrecio> tbDetalleListaDePrecio;
    @FXML
    private TableColumn<DetalleListaDePrecio, String> tbcArtListaCodigo;
    @FXML
    private TableColumn<DetalleListaDePrecio, String> tbcArtListaNombre;
    @FXML
    private TableColumn<DetalleListaDePrecio, Double> tbcArtListaPrecio;

    @FXML
    private TableColumn<DetalleListaDePrecio, String> tbcArtListaUnida;
    @FXML
    private TableColumn<DetalleListaDePrecio, Double> tbcCostoUnitario;
    @FXML
    private TableColumn<DetalleListaDePrecio, Double> tbcNuevoPrecio;
    @FXML
    private TextField txtCantidadArticulo;

    ObservableList<DetalleListaDePrecio> listaDetalleListaDePrecio = FXCollections.observableArrayList();
    ObservableList<TipoArticulo> listaTipoArticulo = FXCollections.observableArrayList();
    ObservableList<Categoria> listaCategoria = FXCollections.observableArrayList();
    ObservableList<SubCategoria> listaSubCategoria = FXCollections.observableArrayList();
    ObservableList<CalidadPintura> listaCalidad = FXCollections.observableArrayList();
    ObservableList<Unidad> listaUnidad = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXComboBox<CalidadPintura> cbCalidad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTablaDetalleListaDePrecio();
        inicializarCombox();

        txtNuevoPrecio.setOnKeyReleased((event) -> {

            if (event.getCode() == KeyCode.ENTER) {

                try {

                    if (txtNuevoPrecio.getText().isEmpty()) {

                        ClaseUtil.mensaje("Tiene que digitar un precio");
                        return;
                    }

                    for (DetalleListaDePrecio det : listaDetalleListaDePrecio) {

                        det.setPrecioAnterior(Double.parseDouble(txtNuevoPrecio.getText()));

                    }

                    tbDetalleListaDePrecio.refresh();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

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

        cbCalidad.setConverter(new StringConverter<CalidadPintura>() {

            @Override
            public String toString(CalidadPintura tipoArticulo) {
                return String.valueOf(tipoArticulo.getNombre());
            }

            @Override
            public CalidadPintura fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbUnidad.setConverter(new StringConverter<Unidad>() {

            @Override
            public String toString(Unidad unidad) {
                return String.valueOf(unidad.getDescripcion());
            }

            @Override
            public Unidad fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaCategoria.addAll(ManejoCategoria.getInstancia().getCategoria());
        listaUnidad.addAll(ManejoUnidad.getInstancia().getLista());
        listaTipoArticulo.addAll(ManejoTipoArticulo.getInstancia().getListaTipoArticulo());
        listaCalidad.addAll(ManejoCalidadPintura.getInstancia().getLista());

        cbCategoria.setItems(listaCategoria);
        cbCalidad.setItems(listaCalidad);
        listaSubCategoria.clear();

        cbSubCategoria.setItems(listaSubCategoria);

        cbTipoArticulo.setItems(listaTipoArticulo);
        cbUnidad.setItems(listaUnidad);

    }

    public void iniciarTablaDetalleListaDePrecio() {

        listaDetalleListaDePrecio.clear();

        tbDetalleListaDePrecio.setItems(listaDetalleListaDePrecio);

        tbcArtListaCodigo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcArtListaNombre.setCellValueFactory(new PropertyValueFactory<>("articulo"));
        tbcArtListaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tbcNuevoPrecio.setCellValueFactory(new PropertyValueFactory<>("precioAnterior"));
        tbcArtListaUnida.setCellValueFactory(new PropertyValueFactory<>("unidadSalida"));

        tbcCostoUnitario.setCellValueFactory(new PropertyValueFactory<>("costoUnitario"));

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
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });

        tbcNuevoPrecio.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcNuevoPrecio.setOnEditCommit(data -> {

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

    }

    @FXML
    private void cbCategoriaActionEvent(ActionEvent event) {

        if (!(cbCategoria.getSelectionModel().getSelectedIndex() == -1)) {

            Categoria categoria = cbCategoria.getSelectionModel().getSelectedItem();
            listaSubCategoria.clear();
            listaSubCategoria.addAll(ManejoSubCategoria.getInstancia().getSubCategoria(categoria));

        }

    }

    @FXML
    private void btnBuecarArticuloActionevent(ActionEvent event) {

        if (cbTipoArticulo.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje("Tiene que seleccionar un tipo de articulo");
            return;
        }

        if (cbCategoria.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje("Tiene que seleccionar una categoria");
            return;
        }

        if (cbSubCategoria.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje("Tiene que seleccionar una sub categoria");
            return;
        }

        if (cbCalidad.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje("Tiene que seleccionar una calidad");
            return;
        }

        if (cbUnidad.getSelectionModel().getSelectedIndex() == -1) {
            ClaseUtil.mensaje("Tiene que seleccionar una unidad");
            return;
        }

        int tipoArt = 0, categoria = 0, subCategoria = 0, unidad = 0, calidad;

        tipoArt = cbTipoArticulo.getSelectionModel().getSelectedItem().getCodigo();
        categoria = cbCategoria.getSelectionModel().getSelectedItem().getCodigo();
        subCategoria = cbSubCategoria.getSelectionModel().getSelectedItem().getCodigo();
        unidad = cbUnidad.getSelectionModel().getSelectedItem().getCodigo();
        calidad = cbCalidad.getSelectionModel().getSelectedItem().getCodigo();

        listaDetalleListaDePrecio.clear();

        listaDetalleListaDePrecio.addAll(ManejoListaDePrecio.getInstancia()
                .getArticuloDetalleListaDePrecio(tipoArt, categoria, subCategoria, unidad, calidad));
        txtCantidadArticulo.setText(Integer.toString(listaDetalleListaDePrecio.size()));
        txtNuevoPrecio.requestFocus();

    }

    @FXML
    private void btnAplicarPrecioActionEvent(ActionEvent event) {

        if (txtNuevoPrecio.getText().isEmpty()) {

            ClaseUtil.mensaje("Tiene que digitar un precio");
            return;
        }

        for (DetalleListaDePrecio det : listaDetalleListaDePrecio) {

            det.setPrecioAnterior(Double.parseDouble(txtNuevoPrecio.getText()));

        }

        tbDetalleListaDePrecio.refresh();

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        Optional<ButtonType> op = ClaseUtil.confirmarMensaje("Seguro que quiere guardar  esta actualizacion de precio");

        try {

            if (op.get() == ButtonType.YES) {

                Double precioanterior = 0.00;
                for (DetalleListaDePrecio det : listaDetalleListaDePrecio) {

                    precioanterior = det.getPrecio();
                    System.out.println("Precio anterior " + det.getPrecioAnterior());
                    System.out.println("Precio actual  " + det.getPrecio());
                    det.setPrecio(det.getPrecioAnterior());
                    det.setPrecioAnterior(precioanterior);

                }

                ListaDePrecio lp = ManejoListaDePrecio.getInstancia().getListaDePrecio(1);
                lp.setDetalleListaDePrecioCollection(listaDetalleListaDePrecio);
                ManejoListaDePrecio.getInstancia().actualizar(lp);

                ClaseUtil.mensaje("Actualizacion de precio realizado exitosamente");

            } else {

                for (DetalleListaDePrecio det : listaDetalleListaDePrecio) {

                    det.setPrecioAnterior(Double.parseDouble("0.00"));

                }

            }

            txtNuevoPrecio.clear();
            txtCantidadArticulo.clear();
            tbDetalleListaDePrecio.refresh();

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error actualizando los precio");
            e.printStackTrace();
        }

        listaDetalleListaDePrecio.clear();

    }

}
