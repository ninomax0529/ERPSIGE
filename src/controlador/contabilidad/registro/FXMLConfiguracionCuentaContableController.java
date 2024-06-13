/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.registro;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.contabilidadd.CatalogoDao;
import manejo.contabilidadd.ConfiguracionCuentaContableDao;
import manejo.contabilidadd.ModuloDao;
import manejo.contabilidadd.TipoConceptoDao;
import modelo.Catalogo;
import modelo.ConfiguracionCuentaContable;
import modelo.DetalleConfiguaracionCuentaContable;
import modelo.Modulo;
import modelo.TipoConcepto;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLConfiguracionCuentaContableController implements Initializable {

    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXComboBox<Modulo> cbModulo;
    @FXML
    private JFXComboBox<TipoConcepto> cbTipoconcepto;
    @FXML
    private JFXTextField txtComentario;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXTextField txtBuscarNombreCuenta;
    @FXML
    private TableView<Catalogo> tbCatalogo;
    @FXML
    private TableColumn<Catalogo, String> tcCuentaCatalogo;
    @FXML
    private TableColumn<Catalogo, String> tcNombreCuenta;
    @FXML
    private JFXRadioButton rbDebitar;
    @FXML
    private JFXRadioButton rbCreditar;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private TableView<DetalleConfiguaracionCuentaContable> tblDetalleCuenta;
    @FXML
    private TableColumn<DetalleConfiguaracionCuentaContable, String> tbrCuenta;
    @FXML
    private TableColumn<DetalleConfiguaracionCuentaContable, String> tbrDescripcion;
    @FXML
    private TableColumn<DetalleConfiguaracionCuentaContable, String> tbcDebitar;
    @FXML
    private TableColumn<DetalleConfiguaracionCuentaContable, String> tbcAcreditar;
    ObservableList<Modulo> listaModulo = FXCollections.observableArrayList();
    ObservableList<TipoConcepto> listaTipoConcepto = FXCollections.observableArrayList();

    @FXML
    private JFXButton btnEliminar;
    final ToggleGroup group = new ToggleGroup();

    ObservableList<DetalleConfiguaracionCuentaContable> listaDetalleConfiguracionCuenta = FXCollections.observableArrayList();
    ObservableList<Catalogo> listaCatalogo = FXCollections.observableArrayList();

    ConfiguracionCuentaContable configuracionCuentaContable;
    @FXML
    private JFXButton btnTipoConcepto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTablaCatalogo();
        inicializarTablaConfigCuenta();
        inicializarCombox();
        iniciazarFiltro();
        rbDebitar.setToggleGroup(group);
        rbCreditar.setToggleGroup(group);
        configuracionCuentaContable = new ConfiguracionCuentaContable();
        dpFecha.setValue(LocalDate.now());
    }

    private void inicializarCombox() {

        listaModulo.addAll(ModuloDao.getInstancia().getModulo());

        cbModulo.setConverter(new StringConverter<Modulo>() {

            @Override
            public String toString(Modulo modulo) {
                return modulo.getNombre();
            }

            @Override
            public Modulo fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoconcepto.setConverter(new StringConverter<TipoConcepto>() {

            @Override
            public String toString(TipoConcepto tipoConcepto) {
                return tipoConcepto.getNombre();
            }

            @Override
            public TipoConcepto fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoconcepto.setItems(listaTipoConcepto);
        cbModulo.setItems(listaModulo);

    }

    private void iniciazarFiltro() {

        FilteredList<Catalogo> filteredData = new FilteredList<>(tbCatalogo.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtBuscarNombreCuenta.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(suplidorFiltrocatalogoFiltro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (suplidorFiltrocatalogoFiltro.getDescripcion() != null && suplidorFiltrocatalogoFiltro.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (suplidorFiltrocatalogoFiltro.getCuenta() != null && suplidorFiltrocatalogoFiltro.getCuenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Catalogo> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbCatalogo.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbCatalogo.setItems(sortedData);
    }

    private void inicializarTablaCatalogo() {

        try {

            tcCuentaCatalogo.setCellValueFactory(new PropertyValueFactory<>("Cuenta"));
            tcNombreCuenta.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
            listaCatalogo.addAll(CatalogoDao.getInstancia().getCatalogo());
            tbCatalogo.setItems(listaCatalogo);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void inicializarTablaConfigCuenta() {

        tbrCuenta.setCellValueFactory(new PropertyValueFactory<>("Cuenta"));
        tbrDescripcion.setCellValueFactory(new PropertyValueFactory<>("NombreCuenta"));
        tbcDebitar.setCellValueFactory(new PropertyValueFactory<>("Debitar"));
        tbcAcreditar.setCellValueFactory(new PropertyValueFactory<>("ACreditar"));

        tbcAcreditar.setCellValueFactory(
                cellData -> {

                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {

                        property.setValue(cellData.getValue().getAcreditar() == true ? "SI" : "NO");
                    }
                    return property;
                });

        tbcDebitar.setCellValueFactory(
                cellData -> {

                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {

                        property.setValue(cellData.getValue().getDebitar() == true ? "SI" : "NO");
                    }
                    return property;
                });

        tbCatalogo.setEditable(true);
        tblDetalleCuenta.setItems(listaDetalleConfiguracionCuenta);

    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (cbModulo.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar un modulo");
                return;
            }

            if (cbTipoconcepto.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar un sub modulo");
                return;
            }

            if (txtComentario.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digital un descripcion ");
                txtComentario.requestFocus();
                return;
            }

            ConfiguracionCuentaContable config = ConfiguracionCuentaContableDao.getInstancia()
                    .getConfigCuentaPorTipoConcepto(cbTipoconcepto.getSelectionModel().getSelectedItem().getCodigo());

            System.out.println("Valor config " + config);

            if (!(config == null)) {

                ClaseUtil.mensaje("Existe una configuracion con este Tipo de Concepto,tiene que desabilitarla para hacer otra configuracion");
                return;
            }

            configuracionCuentaContable.setFecha(ClaseUtil.asDate((dpFecha.getValue())));
            configuracionCuentaContable.setFechaRegistro(new Date());
            configuracionCuentaContable.setModulo(cbModulo.getSelectionModel().getSelectedItem());
            configuracionCuentaContable.setNombreModulo(cbModulo.getSelectionModel().getSelectedItem().getNombre());
            configuracionCuentaContable.setTipoConcepto(cbTipoconcepto.getSelectionModel().getSelectedItem());
            configuracionCuentaContable.setNombreTipoConcepto(cbTipoconcepto.getSelectionModel().getSelectedItem().getNombre());
            configuracionCuentaContable.setHabilitada(true);
            configuracionCuentaContable.setUsuario(VariablesGlobales.USUARIO);
            configuracionCuentaContable.setDescripcion(txtComentario.getText());
            configuracionCuentaContable.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            configuracionCuentaContable.setDetalleConfiguaracionCuentaContableCollection(listaDetalleConfiguracionCuenta);

            ConfiguracionCuentaContableDao.getInstancia().salvar(configuracionCuentaContable);

            listaDetalleConfiguracionCuenta.clear();
            txtComentario.clear();

            ClaseUtil.mensaje("Configuracion guardada correctamente");

            configuracionCuentaContable = new ConfiguracionCuentaContable();
        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error guardando los datos");
            e.printStackTrace();
        }

    }

    @FXML
    private void tbCatalogoActionEvent(MouseEvent event) {
    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {

        if (tbCatalogo.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar una cuenta");
            return;
        }

        if (rbCreditar.isSelected() == false && rbDebitar.isSelected() == false) {

            ClaseUtil.mensaje(" Tiene que Seleccionar Debitar o Acreditar para esta cuenta");
            return;
        }

        Catalogo catalogo = tbCatalogo.getSelectionModel().getSelectedItem();
        DetalleConfiguaracionCuentaContable detalleConfiguaracionCuentaContable = new DetalleConfiguaracionCuentaContable();

        detalleConfiguaracionCuentaContable.setAcreditar(rbCreditar.isSelected());
        detalleConfiguaracionCuentaContable.setDebitar(rbDebitar.isSelected());
        detalleConfiguaracionCuentaContable.setCatalogo(catalogo);
        detalleConfiguaracionCuentaContable.setCuenta(catalogo.getCuenta());
        detalleConfiguaracionCuentaContable.setNombreCuenta(catalogo.getDescripcion());
        detalleConfiguaracionCuentaContable.setConfiguracionCuentaContable(configuracionCuentaContable);

        listaDetalleConfiguracionCuenta.add(detalleConfiguaracionCuentaContable);

        rbCreditar.setSelected(false);
        rbDebitar.setSelected(false);
        txtBuscarNombreCuenta.clear();

    }

    @FXML
    private void tbDetalleEntActionEvent(KeyEvent event) {
    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        if (tblDetalleCuenta.getSelectionModel().getSelectedIndex() == -1) {

            ClaseUtil.mensaje("Tiene que seleccionar una cuenta");
            return;
        }

        listaDetalleConfiguracionCuenta.remove(tblDetalleCuenta.getSelectionModel().getFocusedIndex());
    }

    @FXML
    private void cbModuloActionEvent(ActionEvent event) {

        listaTipoConcepto.clear();

        if (!(cbModulo.getSelectionModel().getSelectedIndex() == -1)) {

            Modulo modulo = cbModulo.getSelectionModel().getSelectedItem();
            listaTipoConcepto.addAll(TipoConceptoDao.getInstancia().getTipoConceptoPorModulo(modulo.getCodigo()));
            cbTipoconcepto.setItems(listaTipoConcepto);
        }
    }

    @FXML
    private void btnTipoConceptoActionEvent(ActionEvent event) {

        try {

            System.out.println("Imprimir balance");

            Parent root = FXMLLoader.load(getClass().getResource("/vista/contabilidad/registro/FXMLRegistroTipoConcepto.fxml"));

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.setTitle("Tipo de Concepto");

            stage.setScene(scene);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
