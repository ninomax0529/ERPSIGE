/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.cliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import controlador.venta.vendedor.BuscarVendedorController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import manejo.cliente.ManejoCliente;
import manejo.cliente.ManejoCreditoCliente;
import manejo.cliente.ManejoEstadoCliente;
import manejo.cliente.ManejoTipoCliente;
import manejo.cliente.ManejoTipoCredito;
import manejo.cliente.ManejoTipoGarantia;
import manejo.direccion.ManejoCiudad;
import manejo.direccion.ManejorRegion;
import manejo.factura.ManejoFactura;
import manejo.factura.ManejoTipoNcf;
import modelo.Ciudad;
import modelo.Cliente;
import modelo.CreditoCliente;
import modelo.DireccionCliente;
import modelo.EjecutivoDeVenta;
import modelo.EstadoCliente;
import modelo.Region;
import modelo.TipoCliente;
import modelo.TipoCredito;
import modelo.TipoGarantia;
import modelo.TipoNcf;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class RegistroClienteController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtApellido;
    @FXML
    private JFXTextField txtRnc;
    @FXML
    private JFXTextField txtdireccion;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private JFXTextField txtCelular;
    @FXML
    private JFXTextField txtDiasCresito;
    @FXML
    private JFXComboBox<EstadoCliente> cbEstado;
    @FXML
    private JFXComboBox<TipoCliente> cbTipoCliente;
    @FXML
    private JFXTextField txtMontoCredito;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTextField txtMontoDisponible;

    @FXML
    private JFXTextField txtMontoPendiente;

    final ToggleGroup group = new ToggleGroup();

    private Cliente cliente;
    public boolean editar;
    @FXML
    private JFXComboBox<TipoGarantia> cbTipoGarantia;
    @FXML
    private JFXComboBox<TipoCredito> cbTipoCredito;
    @FXML
    private JFXTextField txtGarantia;
    @FXML
    private JFXTextField txtMonto;
    @FXML
    private JFXButton btnagregar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private TableView<CreditoCliente> tbCreditoCliente;
    @FXML
    private TableColumn<CreditoCliente, String> tbcTipoGarantia;
    @FXML
    private TableColumn<CreditoCliente, String> tbcGarantia;
    @FXML
    private TableColumn<CreditoCliente, Double> tbcMonto;
    @FXML
    private TableColumn<CreditoCliente, CreditoCliente> tbcHabilitado;
    @FXML
    private JFXCheckBox chBloquiarCredito;
    @FXML
    private JFXTextField txtCorreoElectronico;
    @FXML
    private JFXTextField txtCiudad;
    @FXML
    private JFXTextField txtMunicipio;
    @FXML
    private JFXTextField txtSector;
    @FXML
    private JFXTextField txtUrbaBarrioOResidencia;
    @FXML
    private JFXTextField txtNoKilometro;
    @FXML
    private JFXTextField txtLetra;
    @FXML
    private JFXTextField txtPiso;
    @FXML
    private JFXTextField txtReferencia;
    @FXML
    private JFXTextField txtPersonaResponsable;
    @FXML
    private JFXTextField txtTelefonoPersona;
    @FXML
    private JFXTextField txtCedulaOPasarporte;
    @FXML
    private TextArea txtObservacion;
    @FXML
    private JFXComboBox<Region> cbRegion;
    @FXML
    private JFXTextField txtCalle;
    @FXML
    private JFXTextField txtAvenida;
    @FXML
    private JFXTextField txtNumCasa;
    @FXML
    private JFXTextField txtNumEdificio;
    @FXML
    private JFXTextField txtNumApartamento;
    @FXML
    private JFXTextField txtNumSuite;
    @FXML
    private JFXTextField txtNumLocal;
    @FXML
    private JFXTextField txtkilometro;
    @FXML
    private JFXRadioButton rbSi;
    @FXML
    private JFXRadioButton rbNo;
    @FXML
    private JFXButton btnagregar1;
    @FXML
    private JFXButton btnEliminar1;
    @FXML
    private TableView<DireccionCliente> tbDireccionCliente;
    @FXML
    private TableColumn<DireccionCliente, String> tbcRegion;
    @FXML
    private TableColumn<DireccionCliente, String> tbcCiudad;
    @FXML
    private TableColumn<DireccionCliente, String> tbcMunicipio;
    @FXML
    private TableColumn<DireccionCliente, String> tbcSector;
    @FXML
    private TableColumn<DireccionCliente, String> tbcDireccion;
    @FXML
    private JFXComboBox<TipoNcf> cbTipoComprobante;
    @FXML
    private JFXComboBox<Ciudad> cbCiudad;
    @FXML
    private JFXButton btnBuscarVendedor;
    @FXML
    private JFXTextField txtVendedor;

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    EjecutivoDeVenta ejecutivoDeVenta;

    public EjecutivoDeVenta getEjecutivoDeVenta() {
        return ejecutivoDeVenta;
    }

    public void setEjecutivoDeVenta(EjecutivoDeVenta ejecutivoDeVenta) {
        this.ejecutivoDeVenta = ejecutivoDeVenta;
    }

    CreditoCliente creditoCliente;
    ObservableList<EstadoCliente> listaEstadoCliente = FXCollections.observableArrayList();
    ObservableList<TipoCliente> listaTipoCliente = FXCollections.observableArrayList();
    ObservableList<CreditoCliente> listaCreditoCliente = FXCollections.observableArrayList();
    ObservableList<TipoCredito> listaTipoCredito = FXCollections.observableArrayList();
    ObservableList<TipoGarantia> listaTipoGarantia = FXCollections.observableArrayList();
    ObservableList<DireccionCliente> listadireCliente = FXCollections.observableArrayList();
    ObservableList<Region> listaRegion = FXCollections.observableArrayList();
    ObservableList<TipoNcf> listaTipoNcf = FXCollections.observableArrayList();
    ObservableList<Ciudad> listaCiudades = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            if (editar == false) {
                nuevo();
            }

            inicializarCombox();
            inicializarTabla();

            cbEstado.getSelectionModel().select(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void inicializarTabla() {

        tbCreditoCliente.setEditable(true);
        tbcGarantia.setCellValueFactory(new PropertyValueFactory<>("garantia"));
        tbcMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        tbcTipoGarantia.setCellValueFactory(new PropertyValueFactory<>("tipoGarantia"));

        tbcHabilitado.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue());
                    }
                    return property;
                });

        tbcMonto.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {

                return object.toString();
            }

            @Override
            public Double fromString(String string) {

                return Double.parseDouble(string);

            }

        }));

        tbcMonto.setOnEditCommit(data -> {

            try {

                CreditoCliente p = data.getRowValue();
                Double montoPendiente, montoDisponible = 0.00;
                montoPendiente = ManejoFactura.getInstancia().getMontoPendiente(p.getCliente());

                if (data.getNewValue() > 0) {

                    p.setMonto(data.getNewValue());

                    montoDisponible = p.getMonto() - montoPendiente;
                    tbCreditoCliente.refresh();
                    tbCreditoCliente.requestFocus();

                    txtMontoCredito.setText(Double.toString(p.getMonto()));
                    txtMontoPendiente.setText(montoPendiente.toString());
                    txtMontoDisponible.setText(montoPendiente.toString());

                } else {

                    ClaseUtil.mensaje(" El valor no puede ser igual o menor que cero ");
                    tbCreditoCliente.refresh();
                    tbCreditoCliente.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        tbcHabilitado.setCellFactory((TableColumn<CreditoCliente, CreditoCliente> param) -> {

            TableCell<CreditoCliente, CreditoCliente> cellsc = new TableCell<CreditoCliente, CreditoCliente>() {
                @Override
                public void updateItem(CreditoCliente item, boolean empty) {
                    super.updateItem(item, empty);

                    Button btnHabilitar;

                    if (item != null) {

                        btnHabilitar = new Button();
                        btnHabilitar.setPrefSize(60.0, 20.0);

                        if (item.getHabilitado()) {

                            btnHabilitar.setText("SI");

                            btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 15px;\n"
                                    + " -fx-text-fill: white;"
                            // + "    -fx-font-size: 12pt;" 
                            );

                        } else {

                            btnHabilitar.setText("NO");
                            btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 15px;\n"
                                    + " -fx-text-fill: white;"
                            //        + "    -fx-font-size: 12pt;" 
                            );

                        }

                        HBox hbox = new HBox();

//                        hbox.getChildren().addAll(imageview);
                        hbox.getChildren().add(btnHabilitar);

                        hbox.setAlignment(Pos.CENTER);

                        btnHabilitar.setOnMouseClicked((event) -> {

                            if (item.getHabilitado()) {

                                item.setHabilitado(false);
                                btnHabilitar.setText("NO");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 15px;\n"
                                        + "    -fx-b-radius: 15px;\n"
                                        + " -fx-text-fill: white;"
                                // + "    -fx-font-size: 12pt;"
                                );

                            } else {

                                item.setHabilitado(true);

                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 15px;\n"
                                        + " -fx-text-fill: white;"
                                //  + "    -fx-font-size: 12pt;" 
                                );

                            }

                        });

                        setGraphic(btnHabilitar);
                        setText(null);
                    } else {
                        setGraphic(null);
                        setText(null);
                    }
                }
            };
            return cellsc;
        });

        tbCreditoCliente.setItems(listaCreditoCliente);

    }

    private void inicializarCombox() {

        cbEstado.setConverter(new StringConverter<EstadoCliente>() {

            @Override
            public String toString(EstadoCliente estadoCliente) {
                return String.valueOf(estadoCliente.getNombre());
            }

            @Override
            public EstadoCliente fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbCiudad.setConverter(new StringConverter<Ciudad>() {

            @Override
            public String toString(Ciudad ciudad) {
                return String.valueOf(ciudad.getNombre());
            }

            @Override
            public Ciudad fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoCliente.setConverter(new StringConverter<TipoCliente>() {

            @Override
            public String toString(TipoCliente tipoCliente) {

                return String.valueOf(tipoCliente.getNombre());
            }

            @Override
            public TipoCliente fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoCredito.setConverter(new StringConverter<TipoCredito>() {

            @Override
            public String toString(TipoCredito tipoCredito) {

                return String.valueOf(tipoCredito.getNombre());
            }

            @Override
            public TipoCredito fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbTipoGarantia.setConverter(new StringConverter<TipoGarantia>() {

            @Override
            public String toString(TipoGarantia tipoGarantia) {

                return String.valueOf(tipoGarantia.getNombre());
            }

            @Override
            public TipoGarantia fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        cbRegion.setConverter(new StringConverter<Region>() {

            @Override
            public String toString(Region region) {

                return String.valueOf(region.getNombre());
            }

            @Override
            public Region fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaTipoNcf.addAll(ManejoTipoNcf.getInstancia().getListaTipoNcf());

        cbTipoComprobante.setItems(listaTipoNcf);
        cbTipoComprobante.setConverter(new StringConverter<TipoNcf>() {

            @Override
            public String toString(TipoNcf unidad) {
                return unidad.getDescripcion();
            }

            @Override
            public TipoNcf fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        listaEstadoCliente.addAll(ManejoEstadoCliente.getInstancia().getLista());
        listaTipoCliente.addAll(ManejoTipoCliente.getInstancia().getLista());
        listaTipoCredito.addAll(ManejoTipoCredito.getInstancia().getLista());
        listaTipoGarantia.addAll(ManejoTipoGarantia.getInstancia().getLista());
        listaRegion.addAll(ManejorRegion.getInstancia().getLista());
        listaCiudades.addAll(ManejoCiudad.getInstancia().getLista());

        cbEstado.setItems(listaEstadoCliente);
        cbTipoCliente.setItems(listaTipoCliente);
        cbTipoCredito.setItems(listaTipoCredito);
        cbTipoGarantia.setItems(listaTipoGarantia);
        cbRegion.setItems(listaRegion);
        cbCiudad.setItems(listaCiudades);

        cbTipoComprobante.setItems(listaTipoNcf);

    }

    @FXML
    private void btnGuardarEventAction(ActionEvent event) {

        try {

            if (editar == false) {
                cliente = new Cliente();
            }

//            if (txtNombre.getText().isEmpty()) {
//
//                ClaseUtil.mensaje("El nombre esta vacio");
//                txtNombre.requestFocus();
//                return;
//            }
            if (cbTipoCliente.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("El tipo de cliente esta vacio ");
                cbTipoCliente.requestFocus();
                return;
            }

            if (cbTipoComprobante.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("El tipo de facturacion esta vacio ");
                cbTipoComprobante.requestFocus();
                return;
            }

            if (cbCiudad.getSelectionModel().getSelectedIndex() == -1) {

                ClaseUtil.mensaje("Tiene que selecionar una ciudad ");
                cbCiudad.requestFocus();
                return;
            }

            if (cbTipoCliente.getSelectionModel().getSelectedItem().getCodigo() == 1
                    && txtPersonaResponsable.getText().isEmpty()) {

                ClaseUtil.mensaje("Tiene que digitar el representante de la empresa ");
                txtPersonaResponsable.requestFocus();
                return;
            }
            //            if (cbTipoCliente.getSelectionModel().getSelectedItem().getCodigo() == 1
            //                    && txtCedulaOPasarporte.getText().isEmpty()) {
            //
            //                ClaseUtil.mensaje("Tiene que digitar la cedula o pasaporte del  representante de la empresa ");
            //                txtCedulaOPasarporte.requestFocus();
            //                return;
            //            }
            //
            //            if (cbTipoCliente.getSelectionModel().getSelectedItem().getCodigo() == 1
            //                    && txtTelefonoPersona.getText().isEmpty()) {
            //
            //                ClaseUtil.mensaje("Tiene que digitar el telefono del representante de la empresa ");
            //                txtTelefonoPersona.requestFocus();
            //                return;
            //            }

            //            if (txtDiasCresito.getText().isEmpty()) {
            //
            //                ClaseUtil.mensaje("El dia de Credito esta vacio ");
            //                txtDiasCresito.requestFocus();
            //                return;
            //            }
            //            if (txtMontoCredito.getText().isEmpty()) {
            //
            //                ClaseUtil.mensaje("El Monto Credito esta vacio ");
            //                txtMontoCredito.requestFocus();
            //                return;
            //            }
            //            if (txtRnc.getText().isEmpty()) {
            //
            //                ClaseUtil.mensaje("El Rnc esta vacio ");
            //                txtRnc.requestFocus();
            //                return;
            //            }
            //
            //            if (txtdireccion.getText().isEmpty()) {
            //
            //                ClaseUtil.mensaje("La direccion esta vacia");
            //                txtdireccion.requestFocus();
            //                return;
            //            }
            //            Double montoDisponible=Double.parseDouble(txtMontoDisponible.getText().isEmpty()?0.0:txtMontoDisponible.getText());
            System.out.println("txtTelefonoPersona.getText() " + txtTelefonoPersona.getText() + " txtRnc.getText() " + txtRnc.getText());

            cliente.setApellido(txtApellido.getText().isEmpty() ? "N/A" : txtApellido.getText());
            cliente.setNombre(txtNombre.getText().isEmpty() ? "N/A" : txtNombre.getText());
            cliente.setCelular(txtCelular.getText().isEmpty() ? "N/A" : txtCelular.getText());
            cliente.setTelefono(txtTelefono.getText().isEmpty() ? "N/A" : txtTelefono.getText());
            cliente.setDiasCredito(Integer.parseInt(txtDiasCresito.getText().isEmpty() ? "0" : txtDiasCresito.getText()));
            cliente.setMontoCredito(Double.parseDouble(txtMontoCredito.getText().isEmpty() ? "0" : txtMontoCredito.getText()));
            cliente.setEstadoCliente(cbEstado.getSelectionModel().getSelectedItem());
            cliente.setCiudad(cbCiudad.getSelectionModel().getSelectedItem().getCodigo());

            cliente.setRnc(txtRnc.getText().isEmpty() ? "N/A" : txtRnc.getText());
            cliente.setTelefonoPersonaResponsable(txtTelefonoPersona.getText().isEmpty() ? "N/A" : txtTelefonoPersona.getText());
            cliente.setPersonaResponsable(txtPersonaResponsable.getText().isEmpty() ? "N/A" : txtPersonaResponsable.getText());
            cliente.setCedulaPasaportePersonaResponsable(txtCedulaOPasarporte.getText().isEmpty() ? "N/A" : txtCedulaOPasarporte.getText());
            cliente.setTipoCliente(cbTipoCliente.getSelectionModel().getSelectedItem());
            cliente.setEmail(txtCorreoElectronico.getText());
            cliente.setFechaCumpleano(ClaseUtil.asDate(dpFecha.getValue()));

            cliente.setDireccion(txtdireccion.getText().isEmpty() ? "N/A" : txtdireccion.getText());
            cliente.setMontoDisponible(cliente.getMontoCredito());
            cliente.setCreditoClienteCollection(listaCreditoCliente);
            cliente.setCreditoBloquiado(chBloquiarCredito.isSelected());
            cliente.setTipoNcf(cbTipoComprobante.getSelectionModel().getSelectedItem());
            cliente.setUnidadDeNegocio(utiles.VariablesGlobales.USUARIO.getUnidadDeNegocio());
            cliente.setVendedor(getEjecutivoDeVenta());

            if (editar) {
                ManejoCliente.getInstancia().actualizar(cliente);
                ClaseUtil.mensaje("Cliente Actualizado Exitosamente ");
            } else {
                cliente.setFecha(new Date());
                ManejoCliente.getInstancia().salvar(cliente);
                ClaseUtil.mensaje("Cliente Creado Exitosamente ");
            }

            nuevo();

        } catch (Exception e) {
            ClaseUtil.mensaje("Hubo un Error Creando el Cliente ");

            e.printStackTrace();
        }
    }

    @FXML
    private void btnCerrarActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();

    }

    private void limpiar() {

        txtApellido.clear();
        txtCelular.clear();
        txtDiasCresito.clear();
        txtMontoCredito.clear();
        txtNombre.clear();
        txtRnc.clear();
        txtTelefono.clear();
        txtdireccion.clear();
        txtPersonaResponsable.clear();
        txtTelefonoPersona.clear();
        txtVendedor.clear();
        txtObservacion.clear();
        txtCedulaOPasarporte.clear();
        cbEstado.getSelectionModel().clearSelection();
        cbTipoCliente.getSelectionModel().clearSelection();
        cbCiudad.getSelectionModel().clearSelection();

    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void llenarCampo() {

        if (getCliente().getFechaCumpleano() == null) {
            dpFecha.setValue(LocalDate.now());
        } else {
            dpFecha.setValue(ClaseUtil.convertToLocalDateViaMilisecond(getCliente().getFechaCumpleano()));
        }
        txtApellido.setText(getCliente().getApellido() == null ? "na" : getCliente().getApellido());
        txtNombre.setText(getCliente().getNombre());
        txtCelular.setText(getCliente().getCelular());
        txtRnc.setText(getCliente().getRnc());
        txtTelefono.setText(getCliente().getTelefono());
        txtDiasCresito.setText(getCliente().getDiasCredito().toString());
        txtCorreoElectronico.setText(getCliente().getEmail());

        cbEstado.getSelectionModel().select(getCliente().getEstadoCliente());

        Ciudad ciudad = ManejoCiudad.getInstancia().getCiudad(getCliente().getCiudad());
        cbCiudad.getSelectionModel().select(ciudad);
        cbTipoCliente.getSelectionModel().select(getCliente().getTipoCliente());
        cbTipoComprobante.getSelectionModel().select(getCliente().getTipoNcf());
        chBloquiarCredito.setSelected(cliente.getCreditoBloquiado());

        txtdireccion.setText(getCliente().getDireccion());

        txtPersonaResponsable.setText(getCliente().getPersonaResponsable() == null ? "na"
                : getCliente().getPersonaResponsable()
        );
        txtTelefonoPersona.setText(getCliente().getTelefonoPersonaResponsable() == null ? "na"
                : getCliente().getTelefonoPersonaResponsable()
        );

        txtCedulaOPasarporte.setText(getCliente().getCedulaPasaportePersonaResponsable() == null
                ? "na" : getCliente().getCedulaPasaportePersonaResponsable());

        setEjecutivoDeVenta(getCliente().getVendedor());
        txtVendedor.setText(getCliente().getVendedor() == null ? "" : getCliente().getVendedor().getNombre());

        Double montoCredito = ManejoCreditoCliente.getInstancia()
                .getMontoCreditoCliente(getCliente().getCodigo());

        txtMontoCredito.setText(montoCredito.toString());
        listaCreditoCliente.clear();
        listaCreditoCliente.setAll(ManejoCreditoCliente.getInstancia().getCreditoCliente(getCliente().getCodigo()));

//        txtMontoCredito.setText(getCliente().getMontoCredito().toString());
        txtMontoDisponible.setText(ManejoFactura.getInstancia().getMontoDisponible(getCliente()).toString());
        txtMontoPendiente.setText(ManejoFactura.getInstancia()
                .getMontoPendiente(getCliente()).toString());

    }

    private void nuevo() {

        cliente = new Cliente();
        dpFecha.setValue(LocalDate.now());
        limpiar();
    }

    @FXML
    private void btnagregarActionEvent(ActionEvent event) {

        creditoCliente = new CreditoCliente();

        creditoCliente.setCliente(cliente);
        creditoCliente.setFecha(new Date());
        creditoCliente.setFechaRegistro(new Date());
        creditoCliente.setFechaVencimiento(null);
        creditoCliente.setGarantia(txtGarantia.getText());
        creditoCliente.setMonto(Double.parseDouble(txtMonto.getText()));
        creditoCliente.setTipoCredito(cbTipoCredito.getSelectionModel().getSelectedItem());
        creditoCliente.setTipoGarantia(cbTipoGarantia.getSelectionModel().getSelectedItem());
        creditoCliente.setMontoDisponible(creditoCliente.getMonto());
        creditoCliente.setMontoPendiente(0.00);
        creditoCliente.setHabilitado(true);

        listaCreditoCliente.add(creditoCliente);

    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        if (!(tbCreditoCliente.getSelectionModel().getFocusedIndex() == -1)) {

            listaCreditoCliente.remove(tbCreditoCliente.getSelectionModel().getSelectedIndex());
            tbCreditoCliente.refresh();
        }
    }

    @FXML
    private void btnBuscarVendedorActionEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/vista/venta/vendedor/BuscarVendedor.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        ClaseUtil.getStageModal(root);

        BuscarVendedorController vendedorController = loader.getController();

        if (!(vendedorController.getEjecutivoDeVenta() == null)) {

            setEjecutivoDeVenta(vendedorController.getEjecutivoDeVenta());
            txtVendedor.setText(getEjecutivoDeVenta().getNombre());

        }
    }

}
