/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.inventario.salida;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.adjunto.ManejoAdjunto;
import manejo.inventario.salida.ManejoSolicitudSalida;

import modelo.Adjunto;
import modelo.DetalleSolicitudSalidaInventario;
import modelo.SolicitudSalidaInventario;
//import reporte.inventario.salida.RptSolicitudSalidaInventario;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class SolicitudSalidaInventarioController implements Initializable {

    @FXML
    private JFXDatePicker dpFechaInicio;
    @FXML
    private JFXDatePicker dpFechaFinal;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnVerEntrada;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private TableView<SolicitudSalidaInventario> tbSolicitudSalida;
    @FXML
    private TableColumn<SolicitudSalidaInventario, Integer> tbcEntrada;
    @FXML
    private TableColumn<SolicitudSalidaInventario, Date> tbcFecha;
    @FXML
    private TableColumn<SolicitudSalidaInventario, String> tbcAnulada;
    @FXML
    private TableColumn<SolicitudSalidaInventario, String> tbcSolicitante;  
    @FXML
    private TableColumn<SolicitudSalidaInventario, String> tbcEstado;
 
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private TableView<DetalleSolicitudSalidaInventario> tbDetalleSolicitudSalida;
    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, String> tbcCodigoArticulo;
    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, String> tbcDescripcionArticulo;
    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, Double> tbcCantidad;
    @FXML
    private TableColumn<DetalleSolicitudSalidaInventario, String> tbcUnidad;
    @FXML
    private TextArea txtComentario;
    @FXML
    private JFXTextField txtCantidadArticulo;

    ObservableList<SolicitudSalidaInventario> listaSolicitud = FXCollections.observableArrayList();
    ObservableList<DetalleSolicitudSalidaInventario> listaDetalle = FXCollections.observableArrayList();
    ObservableList<Adjunto> listaAdjunto = FXCollections.observableArrayList();
    @FXML
    private TableView<Adjunto> tbAdjunto;
    @FXML
    private TableColumn<Adjunto, String> tbcNombreAdjunto;
    @FXML
    private TableColumn<Adjunto, String> tbcUrlAdjunto;
    @FXML
    private JFXButton btnVerAdjunto;
    @FXML
    private Tab tabAdjunto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciaTabla();
        iniciaTablaDetalle();
        iniciaTablaAdjunto();
        dpFechaInicio.setValue(LocalDate.now());
        dpFechaFinal.setValue(LocalDate.now());
      

    }

    public void iniciaTablaAdjunto() {

        tbcNombreAdjunto.setCellValueFactory(new PropertyValueFactory<>("nombreDocumentoDestino"));
        tbcUrlAdjunto.setCellValueFactory(new PropertyValueFactory<>("urlDestino"));

        tbAdjunto.setItems(listaAdjunto);
    }

    @FXML
    private void btnNuevoActionEvent(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/inventario/salida/RegistroSolicitudSalidaInventario.fxml"));

        ClaseUtil.crearStageModal(root);

        listaSolicitud.clear();
        listaSolicitud.addAll(ManejoSolicitudSalida.getInstancia().getListaSolicitud());
    }

    @FXML
    private void btnBuscarActionEvent(ActionEvent event) {

        Date fi, ff;
        fi = ClaseUtil.asDate(dpFechaInicio.getValue());
        ff = ClaseUtil.asDate(dpFechaFinal.getValue());

        listaDetalle.clear();
        listaSolicitud.clear();
        listaSolicitud.addAll(ManejoSolicitudSalida.getInstancia().getLista(fi, ff));

        txtCantidad.setText(Integer.toString(listaSolicitud.size()));

    }

    @FXML
    private void btnVerEntradaActionEvent(ActionEvent event) {
        try {
            if (tbSolicitudSalida.getSelectionModel().getSelectedIndex() == -1) {
                ClaseUtil.mensaje("Tiene que selccionar una solicitud");
                return;
            } else {
//                RptSolicitudSalidaInventario.getInstancia().imprimir(tbSolicitudSalida.getSelectionModel().getSelectedItem().getCodigo());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnAnularAtionEvent(ActionEvent event) {
        try {
            if (!(tbSolicitudSalida.getSelectionModel().getSelectedIndex() == -1)) {

                Optional<ButtonType> ok = ClaseUtil.confirmarMensaje("SEGURO QUE QUIERE ANULAR LA SOLICITUD NUMERO " + tbSolicitudSalida.getSelectionModel().getSelectedItem().getCodigo());
                System.out.println("valor button " + ok.get());
                if (ok.get() == ButtonType.YES) {

                    SolicitudSalidaInventario solicitud = ManejoSolicitudSalida.getInstancia().getSolicitud(tbSolicitudSalida.getSelectionModel().getSelectedItem().getCodigo());

                    if (solicitud.getAnulada() == true) {
                        ClaseUtil.mensaje("Esta solicitud esta anulada ");
                        return;
                    }

                    if (solicitud.getEstado().equalsIgnoreCase("procesado")) {
                        ClaseUtil.mensaje("Esta solicitud tiene una salida de Inventario Activa.");
                        return;
                    }

                    solicitud.setAnulada(true);
                    solicitud.setFechaAnulada(new Date());
//                    solicitud.setUsuario(null);
                    ManejoSolicitudSalida.getInstancia().actualizar(solicitud);
                    listaDetalle.clear();
                    listaSolicitud.clear();
                    ClaseUtil.mensaje("Solicitud anulada exitosamente ");

                    listaSolicitud.addAll(ManejoSolicitudSalida.getInstancia().getListaSolicitud());

                }

            } else {

                ClaseUtil.mensaje("Tiene que seleccionar una solicitud ");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void iniciaTabla() {

        listaDetalle.clear();

        tbSolicitudSalida.setItems(listaSolicitud);
        tbSolicitudSalida.refresh();

        listaSolicitud.addAll(ManejoSolicitudSalida.getInstancia().getListaSolicitud());

        tbcEntrada.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        tbcAnulada.setCellValueFactory(new PropertyValueFactory<>("anulada"));

        tbcSolicitante.setCellValueFactory(new PropertyValueFactory<>("solicitante"));
     
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tbcAnulada.setCellValueFactory(
                cellData
                -> {
            SimpleStringProperty property = new SimpleStringProperty();
            if (cellData.getValue() != null) {
                try {

                    if (!cellData.getValue().getAnulada()) {
                        property.set("va");
                    } else {
                        property.set("");
                    }

                } catch (Exception e) {
                }
            }
            return property;
        });

        tbcAnulada.setCellFactory((TableColumn<SolicitudSalidaInventario, String> param) -> {
            
            TableCell<SolicitudSalidaInventario, String> cellsc = new TableCell<SolicitudSalidaInventario, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item != null) {
                        Image image;
                        Label label;
                        if (item.isEmpty()) {
                            image = new Image("/imagen/inactive.png");
                            label = new Label("Inactivo  ".toUpperCase());
                        } else {
                            image = new Image("/imagen/active.png");
                            label = new Label("Activo  ".toUpperCase());
                        }

                        HBox hb = new HBox();
                        VBox vb = new VBox();
                        vb.setAlignment(Pos.CENTER);
                        hb.setAlignment(Pos.CENTER);
                        ImageView imageview = new ImageView();
                        imageview.setFitHeight(16);
                        imageview.setFitWidth(17);
                        imageview.setImage(image);
                        hb.getChildren().addAll(label, imageview, vb);
                        setGraphic(hb);
                        setText(null);
                    } else {
                        setGraphic(null);
                        setText(null);
                    }
                }
            };
            return cellsc;
        });

       

        tbcEstado.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getEstado().toUpperCase());
                    }
                    return property;
                });

        txtCantidad.setText(Integer.toString(listaSolicitud.size()));

    }

    public void iniciaTablaDetalle() {

        tbcCodigoArticulo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        tbcDescripcionArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcionArticulo"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadSolicitada"));
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));

        tbcUnidad.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
                    }
                    return property;
                });
        
        tbcCodigoArticulo.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getArticulo().getCodigo().toString());
                    }
                    return property;
                });

        tbDetalleSolicitudSalida.setItems(listaDetalle);
    }

    @FXML
    private void tbSolicitudSalidaMouseClicked(MouseEvent event) {

        if (!(tbSolicitudSalida.getSelectionModel().getSelectedIndex() == -1)) {

            SolicitudSalidaInventario solicitud = tbSolicitudSalida.getSelectionModel().getSelectedItem();

            if (solicitud.getAnulada() == true) {

                btnAnular.setDisable(true);

            } else {

                btnAnular.setDisable(false);
            }

            listaDetalle.clear();
            listaDetalle.addAll(ManejoSolicitudSalida.getInstancia().getDetalleSolicitud(solicitud));
            txtComentario.setText(solicitud.getComentario());
            txtCantidadArticulo.setText(Integer.toString(listaDetalle.size()));

            //Adjunto
//            listaAdjunto.clear();
//            listaAdjunto.addAll(ManejoAdjunto.getInstancia().getListaAdjunto(solicitud.getCodigo(), 1));
        }
    }

    @FXML
    private void tbDetalleEntradaInventarioMouseCliked(MouseEvent event) {
    }

    @FXML
    private void btnVerAdjuntoActionevent(ActionEvent event) {

//        if (!(tbAdjunto.getSelectionModel().getSelectedIndex() == -1)) {
//
//            Adjunto adjunto = tbAdjunto.getSelectionModel().getSelectedItem();
//
//            String url = adjunto.getUrlDestino();
//            System.out.println("URL " + url);
//
//            try {
//
//                File fileUrl = new File(url);
//
//                if (fileUrl.exists()) {
//
//                    ClaseUtil.abrirArchivo(fileUrl, adjunto);
//
//                } else {
//
//                    ClaseUtil.mensaje("Este archivo " + adjunto.getNombreDocumentoDestino() + " no existen en esta rura " + adjunto.getUrlDestino());
//                }
//
//            } catch (Exception ex) {
//
//                Logger.getLogger(SolicitudSalidaInventario.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } else {
//
//            ClaseUtil.mensaje("tiene que selccionar un registro ");
//        }
    }

}
