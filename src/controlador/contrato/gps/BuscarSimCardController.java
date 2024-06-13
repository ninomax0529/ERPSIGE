/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.gps;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import manejo.contrato.ManejoContratoDeServicio;
import manejo.contrato.ManejoSimCard;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import modelo.DetalleContratoDeServicio;
import modelo.MenuModulo;
import modelo.RegistroDeSim;
import modelo.RolMenuModulo;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class BuscarSimCardController implements Initializable {

    @FXML
    private JFXTextField txtCantidadSim;

    /**
     * @return the registro
     */
    public RegistroDeSim getRegistro() {
        return registro;
    }

    /**
     * @param registro the registro to set
     */
    public void setRegistro(RegistroDeSim registro) {
        this.registro = registro;
    }

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
    private TabPane tabPane;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<RegistroDeSim> tbSimCard;
    @FXML
    private TableColumn<RegistroDeSim, String> tbcNumeroSim;
    @FXML
    private TableColumn<RegistroDeSim, String> tbcFecha;
    @FXML
    private TableColumn<RegistroDeSim, String> tbcFechaRegistro;

    ObservableList<RegistroDeSim> listaSim = FXCollections.observableArrayList();
    ObservableList<RegistroDeSim> listaSimDuplicado = FXCollections.observableArrayList();
    ObservableList<DetalleContratoDeServicio> listaSimMovimiento = FXCollections.observableArrayList();
    private HBox hbPermiso;
    int codigoRol = VariablesGlobales.USUARIO.getRol().getCodigo();
    private Integer codigoMenuModulo;
    private RegistroDeSim registro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        setCodigoMenuModulo(ManejoMenuModulo.getInstancia().getMenuModulo(9, "btnRegistroContrato").getCodigo());
//        agregarOpciones();
//        activarOpciones();
        inicializarTabla();
        iniciazarFiltro();

    }

    private void iniciazarFiltro() {

        FilteredList<RegistroDeSim> filteredData = new FilteredList<>(tbSimCard.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(registro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (registro.getNumero().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (registro.getFechaDeCompra().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<RegistroDeSim> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbSimCard.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbSimCard.setItems(sortedData);
    }

    public void inicializarTabla() {

        try {

            listaSim.addAll(ManejoSimCard.getInstancia().getListaSimDisponible());

            tbcNumeroSim.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fechaDeCompra"));

            tbcFecha.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaDeCompra() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaDeCompra()));
                        }
                        return property;
                    });

            tbcFechaRegistro.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaRegistro() != null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaRegistro()));
                        }
                        return property;
                    });

            tbSimCard.setItems(listaSim);
//            lb.setText(Integer.toString(listaSim.size()));

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

        for (Node n : hbPermiso.getChildren()) {

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

            for (Node n : hbPermiso.getChildren()) {
                n.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, 9);

            for (Node n : hbPermiso.getChildren()) {

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

    @FXML
    private void tbSimCardMouseClicked(MouseEvent event) {

        if (!(tbSimCard.getSelectionModel().getSelectedIndex() == -1)) {

            if (event.getClickCount() == 2) {
                setRegistro(tbSimCard.getSelectionModel().getSelectedItem());

                Stage stage = (Stage) tbSimCard.getScene().getWindow();
                stage.close();
            }

        }

    }
    
      public void llenarCampo() {

        listaSim.clear();
        listaSim.addAll(ManejoSimCard.getInstancia().getListaSimDisponible());

    }

}
