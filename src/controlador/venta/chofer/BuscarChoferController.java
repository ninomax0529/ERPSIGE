/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.chofer;

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
import manejo.chofer.ManejoChofer;
import modelo.Chofer;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class BuscarChoferController implements Initializable {

    @FXML
    private JFXTextField txtFiltrar;
    @FXML
    private TabPane tabPane;
    @FXML
    private TableView<Chofer> tbChofer;
    @FXML
    private TableColumn<Chofer, String> tbcNombre;
    Chofer chofer;

    ObservableList<Chofer> lista = FXCollections.observableArrayList();

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTablaEncabezado();
    }

    public void inicializarTablaEncabezado() {

        lista.addAll(ManejoChofer.getInstancia().getLista());
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        tbChofer.setItems(lista);
    }

    @FXML
    private void tbFacturaMouseCliked(MouseEvent event) {

        if (!(tbChofer.getSelectionModel().getSelectedIndex() == -1)) {

            if (event.getClickCount() == 2) {

                setChofer(tbChofer.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) tbChofer.getScene().getWindow();

                stage.close();

            }
        }
    }

}
