/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contabilidad.registro;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import manejo.contabilidadd.ModuloDao;
import manejo.contabilidadd.TipoConceptoDao;
import modelo.Modulo;
import modelo.TipoConcepto;
import util.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class FXMLRegistroTipoConceptoController implements Initializable {

    @FXML
    private JFXTextField txtNombre;
    @FXML
    private TableView<TipoConcepto> tbTipoConcepto;
    @FXML
    private TableColumn<TipoConcepto, Integer> tbcCodigo;
    @FXML
    private TableColumn<TipoConcepto, String> tbcNombre;

    ObservableList<TipoConcepto> listaTipoConcepto = FXCollections.observableArrayList();
    ObservableList<Modulo> listaModulo = FXCollections.observableArrayList();
    TipoConcepto tipoConcepto;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXComboBox<Modulo> cbModulo;
    @FXML
    private TableColumn<TipoConcepto, String> tbcModulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
        inicializarCombox();
        btnEliminar.setDisable(true);
        tipoConcepto = new TipoConcepto();
    }

    private void inicializarTabla() {

        listaTipoConcepto.addAll(TipoConceptoDao.getInstancia().getTipoConcepto());
        tbTipoConcepto.setItems(listaTipoConcepto);

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcModulo.setCellValueFactory(new PropertyValueFactory<>("Modulo"));

        tbcModulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getModulo() != null) {
                        property.setValue(cellData.getValue().getModulo().getNombre());
                    }
                    return property;
                });

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

        cbModulo.setItems(listaModulo);

    }

    @FXML
    private void tbTipoConceptoActionEvent(MouseEvent event) {

        if (!(tbTipoConcepto.getSelectionModel().getSelectedIndex() == -1)) {

            tipoConcepto = tbTipoConcepto.getSelectionModel().getSelectedItem();
            txtNombre.setText(tipoConcepto.getNombre());
            btnGuardar.setText("Editar");
            btnEliminar.setDisable(false);

        } else {

            btnGuardar.setText("Guardar");
            btnEliminar.setDisable(true);

        }
    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (cbModulo.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que seleccionar un modulo ");
                cbModulo.requestFocus();
                return;
            }

            if (txtNombre.getText().isEmpty()) {
                ClaseUtil.mensaje("El nombre no puede estar vacio");
                txtNombre.requestFocus();
                return;
            }

            tipoConcepto.setNombre(txtNombre.getText());
            tipoConcepto.setModulo(cbModulo.getSelectionModel().getSelectedItem());

            TipoConceptoDao.getInstancia().salvar(tipoConcepto);
            tipoConcepto = new TipoConcepto();

            listaTipoConcepto.clear();
            listaTipoConcepto.addAll(TipoConceptoDao.getInstancia().getTipoConcepto());

            txtNombre.clear();
            cbModulo.getItems().clear();
            btnGuardar.setText("Guardar");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        if (!(tbTipoConcepto.getSelectionModel().getSelectedIndex() == -1)) {

            tipoConcepto = tbTipoConcepto.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("FalconERP");
            alert.setHeaderText("FalconERP");
            alert.setContentText("Esta seguro que quiere eliminar Este registro " + tipoConcepto.getNombre() + " ?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

//                if (MovimientoBancoDao.getInstancia().getMovimientoBanco(tipoConcepto) > 0) {
//
//                    ClaseUtil.mensaje("Este Tipo de Concepto ya tiene Movimiento");
//                    txtNombre.clear();
//                    tbTipoConcepto.getSelectionModel().clearSelection();
//
//                    return;
//                }

                listaTipoConcepto.remove(tbTipoConcepto.getSelectionModel().getSelectedIndex());

                TipoConceptoDao.getInstancia().remover(tipoConcepto);
                listaTipoConcepto.addAll(TipoConceptoDao.getInstancia().getTipoConcepto());
                tbTipoConcepto.setItems(listaTipoConcepto);

            }

        }
    }

    @FXML
    private void cbModuloActionEvent(ActionEvent event) {
    }

}
