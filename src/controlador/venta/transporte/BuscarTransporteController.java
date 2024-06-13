/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.transporte;

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
import manejo.transporte.ManejoTransporte;
import modelo.Transporte;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class BuscarTransporteController implements Initializable {

    @FXML
    private JFXTextField txtFiltrar;
    @FXML
    private TabPane tabPane;
    @FXML
    private TableView<Transporte> tbVehiculo;
    @FXML
    private TableColumn<Transporte, String> tbcNombre;

    ObservableList<Transporte> lista = FXCollections.observableArrayList();
    Transporte transporte;

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTablaEncabezado();
    }

    public void inicializarTablaEncabezado() {

        lista.addAll(ManejoTransporte.getInstancia().getLista());
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        tbVehiculo.setItems(lista);
    }

    @FXML
    private void tbFacturaMouseCliked(MouseEvent event) {

        if (!(tbVehiculo.getSelectionModel().getSelectedIndex() == -1)) {

            if (event.getClickCount() == 2) {

                setTransporte(tbVehiculo.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) tbVehiculo.getScene().getWindow();

                stage.close();

            }
        }
    }

}
