/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.articulo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import manejo.articulo.ManejoCategoria;
import manejo.articulo.ManejoExistenciaArticulo;
import manejo.articulo.ManejoSubCategoria;
import manejo.articulo.ManejoTipoArticulo;
import manejo.unidad.ManejoUnidad;
import modelo.Articulo;
import modelo.Categoria;
import modelo.ExistenciaArticulo;
import modelo.SubCategoria;
import modelo.Unidad;
import utiles.ClaseUtil;
import utiles.FormatNum;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroArticuloController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtDescripcion;
    @FXML
    private JFXDatePicker dpFecha;
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
    private JFXTextField txtPrecioVenta1Itbis;
    @FXML
    private JFXTextField txtPrecioVenta2Itbis;
    @FXML
    private JFXTextField txtPrecioVenta3Itbis;
    @FXML
    private JFXCheckBox chsePuedeDetallar;

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

    @FXML
    private JFXTextField txtPrecioCompra;

    @FXML
    private JFXButton btnCalcularPv;

    @FXML
    private JFXTextField txtPrecioVenta1;
    @FXML
    private JFXTextField txtPrecioVenta2;
    @FXML
    private JFXTextField txtPrecioVenta3;
    @FXML
    private JFXTextField txtPorcientoUtilidad1;
    @FXML
    private JFXTextField txtPorcientoUtilidad2;
    @FXML
    private JFXTextField txtPorcientoUtilidad3;
    @FXML
    private JFXTextField txtMargenUtilidad1;
    @FXML
    private JFXTextField txtMargenUtilidad2;
    @FXML
    private JFXTextField txtMargenUtilidad3;
    @FXML
    private JFXTextField txtcodigoBarra;
    @FXML
    private JFXComboBox<Unidad> cbUnidadEntrada;
    @FXML
    private JFXComboBox<Unidad> cbUnidadSalida;
    @FXML
    private JFXTextField txtMarca;
    @FXML
    private JFXCheckBox chParaVenta;
    @FXML
    private JFXCheckBox chParaConsumo;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXTextField txtPreciioCompraUnidatrio;
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
        dpFecha.setValue(LocalDate.now());
        inicializarSecuencia();

        txtPrecioCompra.setOnKeyReleased((event) -> {

            Double precioCompraUnitario = 0.000000, cantidad = Double.parseDouble(txtCantidad.getText()),
                    
                    precioCompra = Double.parseDouble(txtPrecioCompra.getText().isEmpty() ? "0" : txtPrecioCompra.getText());

            if (!txtCantidad.getText().isEmpty() && !txtPrecioCompra.getText().isEmpty()) {

                precioCompraUnitario = precioCompra / cantidad;
                precioCompraUnitario = ClaseUtil.roundDoubleSies(precioCompraUnitario, 6);
                txtPreciioCompraUnidatrio.setText(precioCompraUnitario.toString());

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

        cbUnidadSalida.setConverter(new StringConverter<Unidad>() {

            @Override
            public String toString(Unidad subCategoria) {
                return String.valueOf(subCategoria.getDescripcion());
            }

            @Override
            public Unidad fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaCategoria.addAll(ManejoCategoria.getInstancia().getCategoria());
        listaUnidad.addAll(ManejoUnidad.getInstancia().getLista());

        cbCategoria.setItems(listaCategoria);
        listaSubCategoria.clear();

//        if (editar == false) {
        cbSubCategoria.setItems(listaSubCategoria);
//        }

        cbUnidadEntrada.setItems(listaUnidad);
        cbUnidadSalida.setItems(listaUnidad);

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (cbCategoria.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar una categoria");
                cbCategoria.requestFocus();
                return;
            }

            if (cbSubCategoria.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar una sub categoria");
                cbSubCategoria.requestFocus();
                return;
            }

            if (txtNombre.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar un nombre");
                txtNombre.requestFocus();
                return;
            }

            if (txtMarca.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar un marca");
                txtMarca.requestFocus();
                return;
            }

            if (txtDescripcion.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar una descripcion");
                txtDescripcion.requestFocus();
                return;
            }

            if (cbUnidadEntrada.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar una unidad de Entrada");
                cbUnidadEntrada.requestFocus();
                return;
            }

            if (cbUnidadSalida.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar una unidad de Salida");
                cbUnidadSalida.requestFocus();
                return;
            }

            if (txtCantidad.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar una cantidad");
                txtCantidad.requestFocus();
                return;
            }

            Double precioCompra = Double.parseDouble(txtPrecioCompra.getText().isEmpty() ? "0" : txtPrecioCompra.getText()),
                    precioCompraUnitario = Double.parseDouble(txtPreciioCompraUnidatrio.getText().isEmpty() ? "0" : txtPreciioCompraUnidatrio.getText()),
                    precioVenta1 = Double.parseDouble(txtPrecioVenta1.getText().isEmpty() ? "0" : txtPrecioVenta1.getText()),
                    precioVenta2 = Double.parseDouble(txtPrecioVenta2.getText().isEmpty() ? "0" : txtPrecioVenta2.getText()),
                    precioVenta3 = Double.parseDouble(txtPrecioVenta3.getText().isEmpty() ? "0" : txtPrecioVenta3.getText()),
                    precioVenta1Itbis = Double.parseDouble(txtPrecioVenta1Itbis.getText().isEmpty() ? "0" : txtPrecioVenta1Itbis.getText()),
                    precioVenta2Itbis = Double.parseDouble(txtPrecioVenta2Itbis.getText().isEmpty() ? "0" : txtPrecioVenta2Itbis.getText()),
                    precioVenta3Itbis = Double.parseDouble(txtPrecioVenta3Itbis.getText().isEmpty() ? "0" : txtPrecioVenta3Itbis.getText()),
                    porciento1 = Double.parseDouble(txtPorcientoUtilidad1.getText().isEmpty() ? "0" : txtPorcientoUtilidad1.getText()),
                    porciento2 = Double.parseDouble(txtPorcientoUtilidad2.getText().isEmpty() ? "0" : txtPorcientoUtilidad2.getText()),
                    porciento3 = Double.parseDouble(txtPorcientoUtilidad3.getText().isEmpty() ? "0" : txtPorcientoUtilidad3.getText()),
                    margenUtilidad1 = Double.parseDouble(txtMargenUtilidad1.getText().isEmpty() ? "0" : txtMargenUtilidad1.getText()),
                    margenUtilidad2 = Double.parseDouble(txtMargenUtilidad2.getText().isEmpty() ? "0" : txtMargenUtilidad2.getText()),
                    margenUtilidad3 = Double.parseDouble(txtMargenUtilidad3.getText().isEmpty() ? "0" : txtMargenUtilidad3.getText());

            String codigoBarra = txtcodigoBarra.getText().isEmpty() ? null : txtcodigoBarra.getText();

            if (!isEditar()) {

                setArticulo(new Articulo());
            }

            getArticulo().setActivo(true);
            getArticulo().setApedir(0.0);
            getArticulo().setMinimo(0.0);
            getArticulo().setCategoria(cbCategoria.getValue());
            getArticulo().setSubCategoria(cbSubCategoria.getValue());
            getArticulo().setCodigoBarra(codigoBarra);
            getArticulo().setAlmacen(ManejoAlmacen.getInstancia().getLista().get(0).getCodigo());
            getArticulo().setDescripcion(txtDescripcion.getText());
            getArticulo().setExentoItbis(chExcentoItbis.isSelected());
            getArticulo().setNombre(txtNombre.getText());
            getArticulo().setInventariable(chInventariable.isSelected());
            getArticulo().setActivo(chActivo.isSelected());
            getArticulo().setReferencia("n/a");
            getArticulo().setUbicacion("Almacen Principal");
            getArticulo().setTipoArticulo(ManejoTipoArticulo.getInstancia().get(7));
            getArticulo().setUnidadEntrada(cbUnidadEntrada.getSelectionModel().getSelectedItem());
            getArticulo().setUnidadSalida(cbUnidadSalida.getSelectionModel().getSelectedItem());
            getArticulo().setPrecioCompra(precioCompra);
            getArticulo().setPrecioCompraUnitario(precioCompraUnitario);
            getArticulo().setCodigoBarra(codigoBarra);
            getArticulo().setMarca(txtMarca.getText());
            getArticulo().setParaConsumo(chParaConsumo.isSelected());
            getArticulo().setParaDetalle(chsePuedeDetallar.isSelected());
            getArticulo().setCantidadUnidades(Double.parseDouble(txtCantidad.getText()));

            getArticulo().setPrecioVenta1(precioVenta1);
            getArticulo().setPrecioVenta2(precioVenta2);
            getArticulo().setPrecioVenta3(precioVenta3);

            getArticulo().setPrecioventa1Itbis(precioVenta1Itbis);
            getArticulo().setPrecioventa2Itbis(precioVenta2Itbis);
            getArticulo().setPrecioventa3Itbis(precioVenta3Itbis);

            getArticulo().setPorcientoUtilidad1(porciento1);
            getArticulo().setPorcientoUtilidad2(porciento2);
            getArticulo().setPorcientoUtilida3(porciento3);

            getArticulo().setMargenBeneficio1(margenUtilidad1);
            getArticulo().setMargenBeneficio2(margenUtilidad2);
            getArticulo().setMargenBeneficio3(margenUtilidad3);

            String nombreRuta = " ";
//
//            File imageFile;
//
//            if (getArticulo().getRutaImg() == null) {
//
//                imageFile = new File("foto/img_articulo.jpg");
//
//            } else {
//
//                imageFile = new File(getArticulo().getRutaImg());
//            }

            if (isEditar()) {

                setArticulo(ManejoArticulo.getInstancia().actualizar(getArticulo()));

            } else {

                setArticulo(ManejoArticulo.getInstancia().salvar(getArticulo()));
            }

            if (!(lbRutaImg.getText().isEmpty())) {

                nombreRuta = "foto/articulo_" + getArticulo().getCodigo() + "." + ClaseUtil.getExtensionArchivo(lbRutaImg.getText());

                System.out.println("Imagen " + nombreRuta);
                getArticulo().setRutaImg(nombreRuta);

                ClaseUtil.copiarArchivo(lbRutaImg.getText(), nombreRuta);

            }

            if (!(getArticulo() == null) && !isEditar()) {

                ExistenciaArticulo existenciaArticulo = new ExistenciaArticulo();
                existenciaArticulo.setAlmacen(ManejoAlmacen.getInstancia().getLista().get(0));
                existenciaArticulo.setArticulo(getArticulo());
                existenciaArticulo.setExistencia(0);
                existenciaArticulo.setExistenciaAnterior(0.00);
                existenciaArticulo.setFecha(new Date());
                existenciaArticulo.setPrecioVentaAnterior(0.00);
                ManejoExistenciaArticulo.getInstancia().salvar(existenciaArticulo);
            }

            ClaseUtil.mensaje("Articulo Guardado Correctamente");
            limpiar();

        } catch (Exception e) {

            ClaseUtil.mensaje("Hubo un error Guardando el Articulo");
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

        if (txtPreciioCompraUnidatrio.getText().isEmpty()) {
            txtPreciioCompraUnidatrio.setText("0");

        }

        calcularpv.setPrecioCompra(Double.parseDouble(txtPreciioCompraUnidatrio.getText()));
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

                txtPrecioVenta1.setText(calcularpv.getPrecioVenta().toString());
                txtPorcientoUtilidad1.setText(calcularpv.getPorcientoUtilidad().toString());
                txtMargenUtilidad1.setText(calcularpv.getMargenUtilidad().toString());
                txtPrecioVenta1Itbis.setText(precioItbis.toString());

                txtPrecioVenta2.setText(calcularpv.getPrecioVenta().toString());
                txtPorcientoUtilidad2.setText(calcularpv.getPorcientoUtilidad().toString());
                txtMargenUtilidad2.setText(calcularpv.getMargenUtilidad().toString());
                txtPrecioVenta2Itbis.setText(precioItbis.toString());

                txtPrecioVenta3.setText(calcularpv.getPrecioVenta().toString());
                txtPorcientoUtilidad3.setText(calcularpv.getPorcientoUtilidad().toString());
                txtMargenUtilidad3.setText(calcularpv.getMargenUtilidad().toString());
                txtPrecioVenta3Itbis.setText(precioItbis.toString());

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

                txtPrecioVenta2.setText(calcularpv.getPrecioVenta().toString());
                txtPorcientoUtilidad2.setText(calcularpv.getPorcientoUtilidad().toString());
                txtMargenUtilidad2.setText(calcularpv.getMargenUtilidad().toString());
                txtPrecioVenta2Itbis.setText(precioItbis.toString());

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

                txtPrecioVenta3.setText(FormatNum.formatoNum(calcularpv.getPrecioVenta()));
                txtPorcientoUtilidad3.setText(FormatNum.formatoNum(calcularpv.getPorcientoUtilidad()));
                txtMargenUtilidad3.setText(FormatNum.formatoNum(calcularpv.getMargenUtilidad()));
                txtPrecioVenta3Itbis.setText(FormatNum.formatoNum(precioItbis));
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

                Logger.getLogger(RegistroArticuloController.class.getName()).log(Level.SEVERE, null, ex);
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

        }
    }

    private void limpiar() {

        txtNombre.clear();
        txtMarca.clear();
        txtDescripcion.clear();
        txtPrecioVenta1.clear();
        dpFecha.setValue(LocalDate.now());
        cbCategoria.getSelectionModel().clearSelection();
        cbSubCategoria.getEditor().clear();
        cbUnidadEntrada.getSelectionModel().clearSelection();
        cbUnidadSalida.getSelectionModel().clearSelection();
        listaSubCategoria.clear();
        listaSubCategoria.clear();
        chExcentoItbis.setSelected(false);
        chActivo.setSelected(false);
        chInventariable.setSelected(false);
        txtPorcientoUtilidad1.clear();
        txtPrecioCompra.clear();
        txtCantidad.clear();
        txtPrecioVenta1.clear();
        txtPrecioVenta2.clear();
        txtPrecioVenta3.clear();
        txtMargenUtilidad1.clear();
        txtMargenUtilidad2.clear();
        txtMargenUtilidad3.clear();
        txtPorcientoUtilidad1.clear();
        txtPorcientoUtilidad2.clear();
        txtPorcientoUtilidad3.clear();
        txtPreciioCompraUnidatrio.clear();
        imgFoto.setImage(null);
        txtcodigoBarra.clear();
        editar = false;
        setArticulo(null);

    }

    public void llenarCampo() {

//        inicializarCombox();
        txtCodigo.setText(getArticulo().getCodigo().toString());

        chExcentoItbis.setSelected(getArticulo().getExentoItbis());
        chActivo.setSelected(getArticulo().getActivo());
        chsePuedeDetallar.setSelected(getArticulo().getParaDetalle());
        chInventariable.setSelected(getArticulo().getInventariable());
        txtPrecioVenta1.setText(getArticulo().getPrecioVenta1().toString());
        txtPrecioVenta2.setText(getArticulo().getPrecioVenta2().toString());
        txtPrecioVenta3.setText(getArticulo().getPrecioVenta3().toString());

        txtPrecioVenta1Itbis.setText(getArticulo().getPrecioventa1Itbis().toString());
        txtPrecioVenta2Itbis.setText(getArticulo().getPrecioventa2Itbis().toString());
        txtPrecioVenta3Itbis.setText(getArticulo().getPrecioventa3Itbis().toString());

        cbCategoria.getSelectionModel().select(getArticulo().getCategoria());
        System.out.println("Sub Categoria " + getArticulo().getSubCategoria().getNombre());
        Categoria categoria = cbCategoria.getSelectionModel().getSelectedItem();
        listaSubCategoria.clear();

        cbSubCategoria.getSelectionModel().select(getArticulo().getSubCategoria());
        listaSubCategoria.addAll(ManejoSubCategoria.getInstancia().getSubCategoria(categoria));

        cbUnidadEntrada.getSelectionModel().select(getArticulo().getUnidadEntrada());
        cbUnidadSalida.getSelectionModel().select(getArticulo().getUnidadSalida());
        txtNombre.setText(getArticulo().getNombre());
        txtDescripcion.setText(getArticulo().getDescripcion());

        txtPorcientoUtilidad1.setText(getArticulo().getPorcientoUtilidad1().toString());
        txtPorcientoUtilidad2.setText(getArticulo().getPorcientoUtilidad2().toString());
        txtPorcientoUtilidad3.setText(getArticulo().getPorcientoUtilida3().toString());

        txtMargenUtilidad1.setText(getArticulo().getMargenBeneficio1().toString());
        txtMargenUtilidad2.setText(getArticulo().getMargenBeneficio1().toString());
        txtMargenUtilidad3.setText(getArticulo().getMargenBeneficio1().toString());
        txtcodigoBarra.setText(articulo.getCodigoBarra() == null ? "n/a" : articulo.getCodigoBarra());
        txtCantidad.setText(articulo.getCantidadUnidades().toString());
        txtMarca.setText(articulo.getMarca());

//        Double precioUnidad = getArticulo().getPrecioCompra() / getArticulo().getCantidadUnidades();
        txtPreciioCompraUnidatrio.setText(getArticulo().getPrecioCompraUnitario().toString());
        txtPrecioCompra.setText(getArticulo().getPrecioCompra().toString());

        File imageFile;

        if (getArticulo().getRutaImg() == null) {

            imageFile = new File("foto/img_articulo.jpg");

        } else {

            imageFile = new File(getArticulo().getRutaImg());
        }

        Image image = new Image(imageFile.toURI().toString(), 80, 50, false, false);

//           lbRutaImg.setText(imageFile.getAbsolutePath());
        imgFoto.setImage(image);

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
    }

    private void cbUnidadSalidaAtionEvent(ActionEvent event) {

        if (!(cbUnidadSalida.getSelectionModel().getSelectedIndex() == -1)) {

            txtCantidad.requestFocus();
        }
    }

    @FXML
    private void nombreArticuloKeyRealessed(KeyEvent event) {

        txtDescripcion.setText(txtNombre.getText().toUpperCase());

    }

}
