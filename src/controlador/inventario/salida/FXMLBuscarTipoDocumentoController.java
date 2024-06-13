package controlador.inventario.salida;


import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import manejo.documento.ManejoTipodocumento;

import modelo.TipoDocumento;
import util.TooltippedTableCell;

public class FXMLBuscarTipoDocumentoController implements Initializable {

    @FXML
    private TableColumn<TipoDocumento, String> tbcCodigo;
    @FXML
    private TableColumn<TipoDocumento, String> tbcdescripcion;
    @FXML
    private TableView<TipoDocumento> tbTipoDocumento;

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @FXML
    private JFXTextField txtFiltro;

    private TipoDocumento tipoDocumento;

    ObservableList<TipoDocumento> listaTipoDocumento = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
        iniciazarFiltro();
    }

    private void inicializarTabla() {

        try {

            listaTipoDocumento.addAll(ManejoTipodocumento.getInstancia().getLista());
            tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            tbcdescripcion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tbcdescripcion.setCellFactory(TooltippedTableCell.forTableColumn());
            tbTipoDocumento.setItems(listaTipoDocumento);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void iniciazarFiltro() {

        FilteredList<TipoDocumento> filteredData = new FilteredList<>(tbTipoDocumento.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(solicitanteFiltro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return false;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (solicitanteFiltro.getNombre() != null && solicitanteFiltro.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (solicitanteFiltro.getCodigo() != null && String.valueOf(solicitanteFiltro.getCodigo()).toLowerCase().contains(lowerCaseFilter)) {

                    return true; // Filter matches first ficha.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<TipoDocumento> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbTipoDocumento.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbTipoDocumento.setItems(sortedData);
    }

    @FXML
    private void tbTipoDocumentoMouseClicked(MouseEvent event) {

        if (!(tbTipoDocumento.getSelectionModel().getSelectedIndex() == -1)) {

            if (event.getClickCount() == 2) {
                setTipoDocumento(tbTipoDocumento.getSelectionModel().getSelectedItem());
                System.out.println(getTipoDocumento().getNombre());

                Stage stage = (Stage) tbTipoDocumento.getScene().getWindow();
                stage.close();
            }

        }
    }

}
