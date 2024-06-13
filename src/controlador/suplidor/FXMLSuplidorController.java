/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.suplidor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import manejo.suplidor.SuplidorDao;
import modelo.Suplidor;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class FXMLSuplidorController implements Initializable {

    @FXML
    private TableColumn<Suplidor, ?> tbcCodigo;
    @FXML
    private TableColumn<Suplidor, ?> tbcDescripcion;
    @FXML
    private TableColumn<Suplidor, ?> tbcRNC;
    @FXML
    private TableColumn<Suplidor, ?> tbcTelefono;
    @FXML
    private TableColumn<Suplidor, ?> tbcContacto;
    @FXML
    private TableView<Suplidor> tbSuplidores;

    ObservableList<Suplidor> listaSuplidores = FXCollections.observableArrayList();

    private Suplidor suplidor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {

            tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
            tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
            tbcRNC.setCellValueFactory(new PropertyValueFactory<>("Rnc"));
            tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
            tbcContacto.setCellValueFactory(new PropertyValueFactory<>("Contacto"));

            listaSuplidores.addAll(SuplidorDao.getInstancia().getSuplidores());
            tbSuplidores.setItems(listaSuplidores);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }

    @FXML
    private void tbSuplidorActionEvent(MouseEvent event) {

        Suplidor suplidor = tbSuplidores.getSelectionModel().getSelectedItem();
        System.out.println(suplidor.getDescripcion());
        setSuplidor(suplidor);
        Stage stage = (Stage) tbSuplidores.getScene().getWindow();
        stage.close();
    }

}
