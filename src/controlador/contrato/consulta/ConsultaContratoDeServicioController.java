/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.consulta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import manejo.contrato.ManejoContratoDeServicio;
import modelo.ContratoDeServicio;
import modelo.DetalleFactura;
import modelo.RelacionNcf;
import utiles.FormatNum;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ConsultaContratoDeServicioController implements Initializable {

    @FXML
    private TableView<ContratoDeServicio> tbContratoDeServicio;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcNumContratoContra;
    @FXML
    private TableColumn<ContratoDeServicio, String> tbcClienteContra;

    @FXML
    private Label lbCantidadContrato;

    ObservableList<ContratoDeServicio> listaContrato = FXCollections.observableArrayList();

    Date fechaVencimiento = null;

    RelacionNcf relacionNcf = null;
    @FXML
    private JFXButton btnExportar;
    @FXML
    private JFXTextField txtFiltro;

    public ContratoDeServicio getContrato() {
        return contrato;
    }

    public void setContrato(ContratoDeServicio contrato) {
        this.contrato = contrato;
    }

    ContratoDeServicio contrato;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablaContrato();
        iniciazarFiltro();

    }

    private void iniciazarFiltro() {

        FilteredList<ContratoDeServicio> filteredData = new FilteredList<>(tbContratoDeServicio.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(filtro -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if(filtro.getCliente() != null && filtro.getCliente().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (filtro.getNumeroDeContrato()!= null && filtro.getNumeroDeContrato().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ContratoDeServicio> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbContratoDeServicio.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbContratoDeServicio.setItems(sortedData);
    }

    public void inicializarTablaContrato() {

        try {

            listaContrato.addAll(ManejoContratoDeServicio.getInstancia().getLista());

            tbcNumContratoContra.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tbcClienteContra.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
            tbContratoDeServicio.setItems(listaContrato);
            lbCantidadContrato.setText(Integer.toString(listaContrato.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tbContratoDeServicioClicked(MouseEvent event) {

        if (!(tbContratoDeServicio.getSelectionModel().getSelectedIndex() == -1)) {

            setContrato(tbContratoDeServicio.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) tbContratoDeServicio.getScene().getWindow();
            stage.close();
        }

    }

    private Integer cantidadContrato() {

        return listaContrato.size();
    }

    private Double getSubTotal(List<DetalleFactura> lista) {

        Double subTotal = 0.00;

        for (DetalleFactura det : lista) {

            subTotal += det.getSubTotal();

        }

        return FormatNum.FormatearDouble(subTotal, 2);
    }

    private Double getTotal(List<DetalleFactura> lista) {

        Double total = 0.00;

        for (DetalleFactura det : lista) {

            total += det.getTotal();

        }

        return FormatNum.FormatearDouble(total, 2);
    }

    private Double getItbis(List<DetalleFactura> lista) {

        Double itbis = 0.00;

        for (DetalleFactura det : lista) {

            itbis += det.getItbis();

        }

        return FormatNum.FormatearDouble(itbis, 2);
    }

    @FXML
    private void btnExportarActionEvent(ActionEvent event) {

        try {

            util.ClaseUtil.exportarDAtos(tbContratoDeServicio);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
