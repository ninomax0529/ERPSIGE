/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.registro;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import manejo.contabilidadd.CatalogoDao;
import manejo.contabilidadd.EntradaDiarioDao;
import manejo.contabilidadd.ManejoGrupoCuenta;
import manejo.contabilidadd.ManejoSubGrupoCuenta;
import modelo.Catalogo;
import modelo.GrupoCuenta;
import modelo.SubGrupoCuenta;
import util.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class FXMLRegistroCatalogoContableController implements Initializable {

    @FXML
    private JFXTextField txtBuscarCuenta;
    @FXML
    private JFXTextField txtCodigoCuenta;

    @FXML
    private JFXTextField txtNombreCuenta;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private TableView<Catalogo> tbCatalogo;
    @FXML
    private TableColumn<Catalogo, String> tcCuenta;
    @FXML
    private TableColumn<Catalogo, String> tcNombreCuenta;
    @FXML
    private TableColumn<Catalogo, String> tcOrigen;
    @FXML
    private TableColumn<Catalogo, String> tbcNivel;

    @FXML
    private JFXRadioButton rbDebito;
    @FXML
    private JFXRadioButton rbCredito;
    @FXML
    private JFXTextField txtBuscarNombreCuenta;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXComboBox<Integer> cbNivel;
    @FXML
    private JFXComboBox<GrupoCuenta> cbGrupo;
    @FXML
    private JFXComboBox<SubGrupoCuenta> cbSubGrupo;
    @FXML
    private JFXComboBox<Catalogo> cbCuentaControl;

    Catalogo catalogo;
    boolean editar = false;

    ObservableList<Catalogo> listaCatalogo = FXCollections.observableArrayList();
    ObservableList<Catalogo> listaCuentaControl = FXCollections.observableArrayList();
    ObservableList<Integer> listaNivel = FXCollections.observableArrayList();
    ObservableList<GrupoCuenta> listaGrupo = FXCollections.observableArrayList();
    ObservableList<SubGrupoCuenta> listaSubGrupo = FXCollections.observableArrayList();

    final ToggleGroup group = new ToggleGroup();
    @FXML
    private JFXButton btnExportar;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
        iniciazarFiltro();
        listaNivel.addAll(1, 2, 3, 4);
        listaGrupo.addAll(ManejoGrupoCuenta.getInstancia().getLista());
//        listaSubGrupo.addAll(ManejoSubGrupoCuenta.getInstancia().getLista());
        cbGrupo.setItems(listaGrupo);
//        cbSubGrupo.setItems(listaSubGrupo);
        cbNivel.setItems(listaNivel);
//        iniciazarFiltro();
        iniciazarFiltroCuenta();
        rbDebito.setToggleGroup(group);
        rbCredito.setToggleGroup(group);
        catalogo = new Catalogo();
        inicializarCombox();
        
    }

    private void inicializarCombox() {

        cbCuentaControl.setConverter(new StringConverter<Catalogo>() {

            @Override
            public String toString(Catalogo catalogo) {
                return catalogo.getDescripcion();
            }

            @Override
            public Catalogo fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        cbGrupo.setConverter(new StringConverter<GrupoCuenta>() {

            @Override
            public String toString(GrupoCuenta grupoCuenta) {
                return grupoCuenta.getNombre();
            }

            @Override
            public GrupoCuenta fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        cbSubGrupo.setConverter(new StringConverter<SubGrupoCuenta>() {

            @Override
            public String toString(SubGrupoCuenta subGrupoCuenta) {
                return subGrupoCuenta.getNombre();
            }

            @Override
            public SubGrupoCuenta fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbCuentaControl.setItems(listaCuentaControl);
        cbGrupo.setItems(listaGrupo);
        cbSubGrupo.setItems(listaSubGrupo);

    }

    private void inicializarTabla() {

        try {

            listaCatalogo.addAll(CatalogoDao.getInstancia().getCatalogo());
            tcCuenta.setCellValueFactory(new PropertyValueFactory<>("cuenta"));
            tcNombreCuenta.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tcOrigen.setCellValueFactory(new PropertyValueFactory<>("origen"));
            tbcNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));

        } catch (Exception e) {

            e.printStackTrace();
        }

        tbCatalogo.setItems(listaCatalogo);

    }

    private void iniciazarFiltro() {

        FilteredList<Catalogo> filteredData = new FilteredList<>(tbCatalogo.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtBuscarCuenta.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(catalogo -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (catalogo.getCuenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (catalogo.getCuenta().toLowerCase().contains(lowerCaseFilter)) {
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

    private void iniciazarFiltroCuenta() {

        FilteredList<Catalogo> filteredData = new FilteredList<>(tbCatalogo.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtCodigoCuenta.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(catalogo -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (catalogo.getCuenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (catalogo.getCuenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        txtNombreCuenta.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(catalogo -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (catalogo.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (catalogo.getCuenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        txtBuscarNombreCuenta.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(catalogo -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (catalogo.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (catalogo.getCuenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 2. Set the filter Predicate whenever the filter changes.
        txtBuscarCuenta.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(catalogo -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (catalogo.getCuenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (catalogo.getCuenta().toLowerCase().contains(lowerCaseFilter)) {
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

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) {
        catalogo = new Catalogo();
        limpiar();
    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (txtCodigoCuenta.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar el codigo de Cuenta");
                return;
            }

            if (txtNombreCuenta.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar el nombre de Cuenta");
                return;
            }

            if (!rbCredito.isSelected() && !rbDebito.isSelected()) {

                ClaseUtil.mensaje("Tiene que seleccionar el origen");
                return;
            }

            if (rbDebito.isSelected()) {

                catalogo.setOrigen("d");

            }
            if (rbCredito.isSelected()) {

                catalogo.setOrigen("c");

            }

            if (cbNivel.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar el nivelo de la Cuenta");
                return;
            }
            if (cbGrupo.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar el grupo de la Cuenta");
                return;
            }

            if (cbSubGrupo.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar el subGrupo de la Cuenta");
                return;
            }

            catalogo.setCuenta(txtCodigoCuenta.getText());
            catalogo.setDescripcion(txtNombreCuenta.getText());
            catalogo.setFechaCreacion(new Date());
            catalogo.setNivel(cbNivel.getSelectionModel().getSelectedItem());
            catalogo.setGrupo(cbGrupo.getSelectionModel().getSelectedItem());
            catalogo.setSubGrupo(cbSubGrupo.getSelectionModel().getSelectedItem());
            catalogo.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());
                
            if (cbCuentaControl.getSelectionModel().getSelectedIndex() == -1
                    && catalogo.getNivel() == 4) {

                ClaseUtil.mensaje("Tiene que seleccionar la Cuenta control");
                return;
            }

            if (cbCuentaControl.getSelectionModel().getSelectedIndex() == -1) {

                if (null==catalogo.getNivel()) {
                    
                    catalogo.setCuentaControl(catalogo.getCodigo());
                } else 
                    switch (catalogo.getNivel()) {
                     case 2:
                        catalogo.setCuentaControl(CatalogoDao.getInstancia()
                                .getCuentaControl(catalogo.getGrupo().getCodigo(), catalogo.getSubGrupo().getCodigo(), 1).getCodigo());
                        break;
                    case 3:
                        catalogo.setCuentaControl(CatalogoDao.getInstancia()
                                .getCuentaControl(catalogo.getGrupo().getCodigo(), catalogo.getSubGrupo().getCodigo(), 2).getCodigo());
                        break;
                    default:
                        catalogo.setCuentaControl(catalogo.getCodigo());
                        break;
                }

            } else if (catalogo.getNivel() == 4) {

                catalogo.setCuentaControl(cbCuentaControl.getSelectionModel().getSelectedItem().getCodigo());

            } else {

                catalogo.setCuentaControl(catalogo.getCodigo());
            }

            if (editar) {
                CatalogoDao.getInstancia().actualizar(catalogo);
            } else {
                CatalogoDao.getInstancia().salvar(catalogo);
            }

            listaCatalogo.clear();
            listaCatalogo.addAll(CatalogoDao.getInstancia().getCatalogo());
            tbCatalogo.setItems(listaCatalogo);
            limpiar();
//            inicializarTabla();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tbCatalogoActionEvent(MouseEvent event) {

        if (!(tbCatalogo.getSelectionModel().getSelectedIndex() == -1)) {

            editar = true;
            catalogo = tbCatalogo.getSelectionModel().getSelectedItem();
            txtCodigoCuenta.setText(catalogo.getCuenta());
            txtNombreCuenta.setText(catalogo.getDescripcion());
//
//            GrupoCuenta grupoCuenta = ManejoGrupoCuenta.getInstancia().getGrupoCuenta();
//
//            SubGrupoCuenta subGrupoCuenta = ManejoSubGrupoCuenta.getInstancia().getSubGrupoCuenta(catalogo.getCodigoSubGrupo());

            cbGrupo.getSelectionModel().select(catalogo.getGrupo());
            cbSubGrupo.getSelectionModel().select(catalogo.getSubGrupo());
            cbNivel.getSelectionModel().select(catalogo.getNivel());

//            if (catalogo.getNivel() == 4) {
//
//                cbCuentaControl.getSelectionModel().select(catalogo.getCuentaControl());
////                cbCuentaControl.getSelectionModel().select(0);
//            }
            System.out.println("SubGrupo " + catalogo.getSubGrupo());

            if (catalogo.getOrigen().equals("d")) {
                rbDebito.setSelected(true);
                rbCredito.setSelected(false);
            } else {
                rbCredito.setSelected(true);
                rbDebito.setSelected(false);
            }
        }
    }

    private void limpiar() {

        txtCodigoCuenta.clear();
        txtNombreCuenta.clear();
        rbCredito.setSelected(false);
        rbDebito.setSelected(false);
        cbGrupo.getSelectionModel().clearSelection();
        cbSubGrupo.getSelectionModel().clearSelection();
        cbNivel.getSelectionModel().clearSelection();
        cbCuentaControl.getSelectionModel().clearSelection();
        txtBuscarCuenta.clear();
        txtBuscarNombreCuenta.clear();
        catalogo = new Catalogo();
        editar = false;
        this.iniciazarFiltroCuenta();

    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        try {

            if (!(tbCatalogo.getSelectionModel().getSelectedIndex() == -1)) {

                catalogo = tbCatalogo.getSelectionModel().getSelectedItem();

                if (!(EntradaDiarioDao.getInstancia().getTieneMovimientoEstaCuenta(catalogo) == null)) {

                    ClaseUtil.mensaje("Esta Cuenta tiene movimiento no puede ser eliminada");
                    return;
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("FalconERP");
                alert.setHeaderText("FalconERP");
                alert.setContentText("Esta seguro que quiere eliminar esta cuenta " + catalogo.getCuenta() + " ?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {

                    CatalogoDao.getInstancia().remover(catalogo);
                    listaCatalogo.remove(tbCatalogo.getSelectionModel().getSelectedIndex());

                    listaCatalogo.clear();
                    listaCatalogo.addAll(CatalogoDao.getInstancia().getCatalogo());
                    tbCatalogo.setItems(listaCatalogo);

                }

                limpiar();
                catalogo = new Catalogo();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void cbGrupoActionevent(ActionEvent event) {

        if (!(cbGrupo.getSelectionModel().getSelectedIndex() == -1)) {

            listaCuentaControl.clear();
            listaSubGrupo.clear();

            String grupoStr = cbGrupo.getSelectionModel().getSelectedItem().getCodigo().toString();
            Integer grupo = cbGrupo.getSelectionModel().getSelectedItem().getCodigo();
            System.out.println("Grupo " + grupo);
            listaCuentaControl.addAll(CatalogoDao.getInstancia().getCuentaControl(grupoStr));
            listaSubGrupo.addAll(ManejoSubGrupoCuenta.getInstancia().getLista(grupo));

        }
    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) {

        try {

            util.ClaseUtil.exportarDAtos(tbCatalogo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
