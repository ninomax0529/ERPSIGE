/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.gps;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import controlador.inventario.articulo.FXMLBuscarArticuloController;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import manejo.contrato.ManejoImeiGps;
import modelo.Articulo;
import modelo.RegistroDeImei;
import modelo.RegistroDeSim;
import utiles.ClaseUtil;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroDeImeiController implements Initializable {

    @FXML
    private Label lbEquipo;
    @FXML
    private JFXCheckBox chFijarGps;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<RegistroDeImei> tbImeiGps;
    @FXML
    private TableColumn<RegistroDeImei, String> tbcArticulo;
    @FXML
    private TableColumn<RegistroDeImei, String> tbcNumeroImei;
    @FXML
    private TableColumn<RegistroDeImei, String> tbcSim;

    /**
     * @return the registroDeSim
     */
    public RegistroDeSim getRegistroDeSim() {
        return registroDeSim;
    }

    /**
     * @param registroDeSim the registroDeSim to set
     */
    public void setRegistroDeSim(RegistroDeSim registroDeSim) {
        this.registroDeSim = registroDeSim;
    }

    @FXML
    private JFXTextField txtNumeroSim;
    @FXML
    private JFXButton btnBuscarSim;

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

    @FXML
    private JFXDatePicker dpFechaEntrada;
    @FXML
    private JFXButton btnBuscarArticulo;

    /**
     * @return the registroImei
     */
    public RegistroDeImei getRegistroImei() {
        return registroImei;
    }

    /**
     * @param registroImei the registroImei to set
     */
    public void setRegistroImei(RegistroDeImei registroImei) {
        this.registroImei = registroImei;
    }

    @FXML
    private JFXRadioButton rbDisponibleSi;
    @FXML
    private JFXRadioButton rbDisponibleNo;

    @FXML
    private JFXTextField txtNumero;

    /**
     * @return the cargo
     */
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    boolean editar;

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    private RegistroDeImei registroImei;
    private Articulo articulo;
    private RegistroDeSim registroDeSim;
    final ToggleGroup group = new ToggleGroup();
    ObservableList<RegistroDeImei> listaImei = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rbDisponibleNo.setToggleGroup(group);
        rbDisponibleSi.setToggleGroup(group);
        inicializarTabla();

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            Boolean existe = ManejoImeiGps.getInstancia().existeNumeroDeImei(txtNumero.getText().trim());

            if (existe == true && editar == false) {
                ClaseUtil.mensaje("Este numero de Imei ya existe ");
                txtNumero.requestFocus();
                return;
            }

            if (txtNumero.getText().isEmpty()) {
                ClaseUtil.mensaje("Tiene que digitar un numero");
                txtNumero.requestFocus();
                return;
            }

            if (dpFechaEntrada.getValue() == null) {
                ClaseUtil.mensaje("Tiene que seleccionar una fecha ");
                dpFechaEntrada.requestFocus();
                return;
            }

            if (rbDisponibleNo.isSelected() == false && rbDisponibleSi.isSelected() == false) {
                ClaseUtil.mensaje("Tiene que seleccionar si esta disponible ");

                return;
            }

            if (getArticulo() == null) {
                ClaseUtil.mensaje("Tiene que selecionar un gps  ");
                lbEquipo.setText("");
                return;
            }

            if (!editar) {

                setRegistroImei(new RegistroDeImei());
            }

            Boolean disponible = rbDisponibleSi.isSelected() ? true : false;
            getRegistroImei().setNumero(txtNumero.getText());
            getRegistroImei().setNumeroEntrada("na");
            getRegistroImei().setDisponible(disponible);
            getRegistroImei().setFechaRegistro(new Date());
            getRegistroImei().setFechaEntrada(ClaseUtil.asDate(dpFechaEntrada.getValue()));
            getRegistroImei().setArticulo(getArticulo());

            if (!(getRegistroDeSim() == null)) {
                getRegistroImei().setSim(getRegistroDeSim());
                getRegistroImei().setNumeroSim(getRegistroDeSim().getNumero());
            }

            getRegistroImei().setUsuario(VariablesGlobales.USUARIO);

            if (editar) {

                ManejoImeiGps.getInstancia().actualizar(getRegistroImei());

            } else {

                getRegistroImei().setFechaRegistro(new Date());
                ManejoImeiGps.getInstancia().salvar(getRegistroImei());
            }

            txtNumero.clear();
            txtNumeroSim.clear();
            setRegistroDeSim(null);
            dpFechaEntrada.setValue(null);
            rbDisponibleNo.setSelected(false);
            rbDisponibleSi.setSelected(false);

            lbEquipo.setText("");

            registroImei = new RegistroDeImei();
            if (!(chFijarGps.isSelected())) {
                setArticulo(null);
            }

            editar = false;
            buscar();
//            ClaseUtil.mensaje("Imei registrado correctamente ");

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un error registrado el Imei ");
            e.printStackTrace();
        }

    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();
    }

    public void inicializarTabla() {

        try {

            tbcNumeroImei.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tbcSim.setCellValueFactory(new PropertyValueFactory<>("numeroSim"));

            tbcArticulo.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getArticulo() != null) {
                            property.setValue(cellData.getValue().getArticulo().getNumero().toString());
                        }
                        return property;
                    });

            tbImeiGps.setItems(listaImei);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void llenarCampo() {

        editar = true;
        setRegistroDeSim(getRegistroImei().getSim());
        txtNumero.setText(getRegistroImei().getNumero());
        txtNumeroSim.setText(getRegistroImei().getNumeroSim());
        dpFechaEntrada.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getRegistroImei().getFechaEntrada()));
        setArticulo(getRegistroImei().getArticulo());

        if (getRegistroImei().getDisponible()) {
            rbDisponibleSi.setSelected(true);
        } else {
            rbDisponibleNo.setSelected(true);
        }

        buscar();
    }

    @FXML
    private void rbDisponibleSiActionEvent(ActionEvent event) {

    }

    @FXML
    private void rbDisponibleNoActionEvent(ActionEvent event) {
    }

    @FXML
    private void btnBuscarArticuloActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/inventario/articulo/FXMLBuscarArticulo.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        FXMLBuscarArticuloController articuloController = loader.getController();
        articuloController.setOrigen(1);//el valor  3 es para  buscar solo articulos de consumo

        articuloController.llenarCampo();

        utiles.ClaseUtil.getStageModal(root);

        System.out.println("ESPERANDOOO!!!");

        if (!(articuloController.getArticulo() == null)) {

            setArticulo(articuloController.getArticulo());
            System.out.println("Estado articulo " + articuloController.getArticulo().getActivo());
            if (articuloController.getArticulo().getActivo() == false) {

                util.ClaseUtil.mensaje("ESTE ARTICULO ESTA INACTIVO,NO SE LE PUEDE HACER MOVIMIENTO ");
                return;
            }

//            if (getArticulo().getSubCategoria().getNombre().toLowerCase().contains("gps")) {//servicio es codigo 3
//
            lbEquipo.setText(getArticulo().getNombre());
//
//            } else {
//
//                util.ClaseUtil.mensaje("EL ATICULO TIENE QUE SER TIPO GPS ");
//            }
        }

    }

    @FXML
    private void btnBuscarSimActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/contrato/gps/BuscarSimCard.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        BuscarSimCardController controller = loader.getController();

        utiles.ClaseUtil.getStageModal(root);

        if (!(controller.getRegistro() == null)) {

            setRegistroDeSim(controller.getRegistro());
            txtNumeroSim.setText(controller.getRegistro().getNumero());

        } else {
            ClaseUtil.mensaje("Tiene que seleccionar un sim");
        }
    }

    private void buscar() {

        Date fi = new Date();
        listaImei.clear();
        listaImei.addAll(ManejoImeiGps.getInstancia().getLista(fi));

    }

    @FXML
    private void tbSimCardMouseClicked(MouseEvent event) {

        if (!(tbImeiGps.getSelectionModel().getSelectedIndex() == -1)) {

            setRegistroImei(tbImeiGps.getSelectionModel().getSelectedItem());
            llenarCampo();

        }

    }
}
