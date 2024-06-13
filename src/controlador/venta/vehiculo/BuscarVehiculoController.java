/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.vehiculo;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import manejo.vehiculo.ManejoVehiculo;
import modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class BuscarVehiculoController implements Initializable {

    @FXML
    private JFXTextField txtFiltrar;
    @FXML
    private TabPane tabPane;
    @FXML
    private TableView<Vehiculo> tbVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> tbcNombre;
    @FXML
    private TableColumn<Vehiculo, String> tbcPlaca;
    ObservableList<Vehiculo> lista = FXCollections.observableArrayList();

    Vehiculo vehiculo;

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      inicializarTablaEncabezado();
    }

    public void inicializarTablaEncabezado() {

        lista.addAll(ManejoVehiculo.getInstancia().getLista());
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));

        tbVehiculo.setItems(lista);
    }

    @FXML
    private void tbFacturaMouseCliked(MouseEvent event) {

        if (!(tbVehiculo.getSelectionModel().getSelectedIndex() == -1)) {

            if (event.getClickCount() == 2) {

                setVehiculo(tbVehiculo.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) tbVehiculo.getScene().getWindow();

                stage.close();

            }
        }

    }

}
