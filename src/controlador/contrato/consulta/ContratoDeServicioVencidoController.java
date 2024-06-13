/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.contrato.consulta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
import manejo.contrato.ManejoContratoDeServicio;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoRelacionNcf;
import manejo.secuenciaDocumento.ManejoSecuenciaDocumento;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.RelacionNcf;
import modelo.SecuenciaDocumento;
import modelo.dto.ContratoVencidoDto;
import utiles.ClaseUtil;
import utiles.FormatNum;
import utiles.VariablesGlobales;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ContratoDeServicioVencidoController implements Initializable {

    @FXML
    private TableView<ContratoVencidoDto> tbContratoDeServicio;
    @FXML
    private TableColumn<ContratoVencidoDto, String> tbcNumContratoContra;
    @FXML
    private TableColumn<ContratoVencidoDto, String> tbcClienteContra;
    @FXML
    private TableColumn<ContratoVencidoDto, String> tbcTipoDePlan;

    @FXML
    private TableColumn<ContratoVencidoDto, String> tbcFechaUltimoPago;
    @FXML
    private TableColumn<ContratoVencidoDto, String> tbcDescripcion;
    @FXML
    private TableColumn<ContratoVencidoDto, String> tbcDiasVencido;
    @FXML
    private Label lbCantidadContrato;

    ObservableList<ContratoVencidoDto> listaContrato = FXCollections.observableArrayList();

    Date fechaVencimiento = null;

    RelacionNcf relacionNcf = null;
    @FXML
    private JFXButton btnExportar;
    @FXML
    private JFXTextField txtFiltro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablaContrato();
        iniciazarFiltro();
       

    }

    
    private void iniciazarFiltro() {

        FilteredList<ContratoVencidoDto> filteredData = new FilteredList<>(tbContratoDeServicio.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(contrato -> {

                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (contrato.getCliente().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (contrato.getContrato().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ContratoVencidoDto> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tbContratoDeServicio.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbContratoDeServicio.setItems(sortedData);
    }
    
    public void inicializarTablaContrato() {

        try {

            listaContrato.addAll(ManejoContratoDeServicio.getInstancia().getContratoVencido());

            tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tbcFechaUltimoPago.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento"));

            tbcNumContratoContra.setCellValueFactory(new PropertyValueFactory<>("contrato"));
            tbcClienteContra.setCellValueFactory(new PropertyValueFactory<>("cliente"));

            tbcTipoDePlan.setCellValueFactory(new PropertyValueFactory<>("plan"));

            tbcDiasVencido.setCellValueFactory(new PropertyValueFactory<>("dias"));
//
//            tbcClienteContra.setCellValueFactory(
//                    cellData -> {
//                        SimpleStringProperty property = new SimpleStringProperty();
//                        if (cellData.getValue().getContratoDeServicio().getCliente() != null) {
//                            property.setValue(cellData.getValue().getContratoDeServicio().getCliente().getNombre());
//                        }
//                        return property;
//                    });
//
//            tbcDiasVencido.setCellValueFactory(
//                    cellData -> {
//                        SimpleStringProperty property = new SimpleStringProperty();
//                        if (cellData.getValue().getContratoDeServicio().getCliente() != null) {
//
//                            Integer dias = ManejoContratoDeServicio.getInstancia().getDiasVencido(cellData.getValue().getContratoDeServicio().getCodigo());
//
//                            property.setValue(dias.toString());
//
//                        }
//                        return property;
//                    });
//
//            tbcNumContratoContra.setCellValueFactory(
//                    cellData -> {
//                        SimpleStringProperty property = new SimpleStringProperty();
//                        if (cellData.getValue().getFrecuenciaDePago() != null) {
//                            property.setValue(cellData.getValue().getContratoDeServicio().getNumeroDeContrato());
//                        }
//                        return property;
//                    });
//
//            tbcTipoDePlan.setCellValueFactory(
//                    cellData -> {
//                        SimpleStringProperty property = new SimpleStringProperty();
//                        if (cellData.getValue().getFrecuenciaDePago() != null) {
//                            property.setValue(cellData.getValue().getContratoDeServicio().getPlanDeServicio().getNombre());
//                        }
//                        return property;
//                    });
//
            tbcFechaUltimoPago.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getFechaVencimiento()!= null) {
                            property.setValue(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getFechaVencimiento()));
                        }
                        return property;
                    });

            tbContratoDeServicio.setItems(listaContrato);
            lbCantidadContrato.setText(Integer.toString(listaContrato.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void btnBuscarActionEvent(ActionEvent event) {

        try {

//            Date fecha = ClaseUtil.asDate(dpFechaFacturacion.getValue());
//
//            if (ManejoCorteDeFacturacion.getInstancia().existeCortedeMensualDeFacturacion(fecha)) {
//
//                ClaseUtil.mensaje("Existe un corte de facturacion en esta fecha");
//
//                return;
//            }
            listaContrato.clear();
            lbCantidadContrato.setText("");
//            listaContrato.addAll(ManejoContratoDeServicio.getInstancia()
//                    .getListaContrato(ClaseUtil.asDate(dpFechaFacturacion.getValue())));//contrato activo y no facturado en la fecha de corte

            lbCantidadContrato.setText(cantidadContrato().toString());

            if (listaContrato.size() <= 0) {

                ClaseUtil.mensaje("No hay  contrato para generarle factura en esta fecha ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tbContratoDeServicioClicked(MouseEvent event) {
    }

    private Integer cantidadContrato() {

        return listaContrato.size();
    }

    private Factura guardar(Factura fac) {

        Factura facturaDb = null;

        try {

            SecuenciaDocumento secDoc = ManejoSecuenciaDocumento.getInstancia()
                    .getSecuenciaDocumento(fac.getUnidadDeNegocio().getCodigo(), 7);

            if (!(secDoc == null)) {

                boolean existe = ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero());

                if (existe) {

                    System.out.println("existe " + secDoc.getNumero());

                    while (ManejoFactura.getInstancia().existeSecuencia(secDoc.getNumero())) {

                        secDoc.setNumero(secDoc.getNumero() + 1);
                        ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                    }

                    secDoc = ManejoSecuenciaDocumento.getInstancia().getSecuenciaDocumento(fac.getUnidadDeNegocio().getCodigo(), 7);

                    fac.setNumero(secDoc.getNumero());
                    fac.setSecuenciaDocumento(secDoc);

                } else {

                    System.out.println("No existe " + secDoc.getNumero());
                    fac.setNumero(secDoc.getNumero());
                    fac.setSecuenciaDocumento(secDoc);
                    secDoc.setNumero(secDoc.getNumero() + 1);
                    ManejoSecuenciaDocumento.getInstancia().actualizar(secDoc);

                }

            } else {

                ClaseUtil.mensaje("La secuencia para la factura de la unidad de negocio "
                        + "" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getNombre() + "\n  no esta configurada ");
                return facturaDb;
            }

            fac.setTipoNcf(fac.getCliente().getTipoNcf());//ponemos el tipo de ncf
//                factura.setTipoNcf(cbTipoNcf.getSelectionModel().getSelectedItem());

            System.out.println("fac.getCliente().getTipoNcf() " + fac.getCliente().getTipoNcf());
            relacionNcf = ManejoRelacionNcf.getInstancia()
                    .getNCFUnidadDeNegocio(fac.getCliente().getTipoNcf(), fac.getUnidadDeNegocio().getCodigo());

            if (relacionNcf == null) {//SI ES IGUAL A NULL LA UNIDAD DE NEGOCIO NO TIENE COMPROBANTE FISCALES CONFIGURADO

                relacionNcf = ManejoRelacionNcf.getInstancia()
                        .getNCFUnidadDeNegocio(fac.getCliente().getTipoNcf(), 3);//EL CODIGO 3 ES EL CODIGO DEL GRUPO FS COMERCIAL

            }

            System.out.println("relacionNcf  " + relacionNcf);
            boolean existe = ManejoFactura.getInstancia().existeNCF(relacionNcf.getActual());

            if (existe) {

                while (ManejoFactura.getInstancia().existeNCF(relacionNcf.getActual())) {

                    relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo());

                }

            } else {

                relacionNcf = ClaseUtil.generarNCFPorTipo(relacionNcf, relacionNcf.getTipoNcf().getCodigo());

            }

            fac.setNcf(relacionNcf.getActual());
            fac.setUnidadDeNegocio(VariablesGlobales.USUARIO.getUnidadDeNegocio());

            System.out.println("Sin ncf");

            facturaDb = ManejoFactura.getInstancia().salvar(fac);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return facturaDb;
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
