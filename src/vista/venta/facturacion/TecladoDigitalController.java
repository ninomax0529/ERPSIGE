/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.venta.facturacion;

import dto.articulo.ArticuloDTO;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import manejo.articulo.ManejoArticulo;
import modelo.Articulo;
import utiles.ClaseUtil;
import utiles.FormatNum;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class TecladoDigitalController implements Initializable {

    /**
     * @return the articuloDTO
     */
    public ArticuloDTO getArticuloDTO() {
        return articuloDTO;
    }

    /**
     * @param articuloDTO the articuloDTO to set
     */
    public void setArticuloDTO(ArticuloDTO articuloDTO) {
        this.articuloDTO = articuloDTO;
    }

    /**
     * @return the lbUnidadDespacho
     */
    public Label getLbUnidadDespacho() {
        return lbUnidadDespacho;
    }

    /**
     * @param lbUnidadDespacho the lbUnidadDespacho to set
     */
    public void setLbUnidadDespacho(Label lbUnidadDespacho) {
        this.lbUnidadDespacho = lbUnidadDespacho;
    }

    /**
     * @return the unidadDespacho
     */
    public int getUnidadDespacho() {
        return unidadDespacho;
    }

    /**
     * @param unidadDespacho the unidadDespacho to set
     */
    public void setUnidadDespacho(int unidadDespacho) {
        this.unidadDespacho = unidadDespacho;
    }

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn3;
    @FXML
    private Button btn6;
    @FXML
    private Button btn9;
    @FXML
    private TextField txtCAtidad;
    @FXML
    private Button btnLimpiar;
    @FXML
    private ImageView imgArt;
    @FXML
    private TextArea lbNombreArt;

    Articulo articulo;
    @FXML
    private Button btnUnidad;
    @FXML
    private Button btnPesos;
    @FXML
    private Label lbUnidadDespacho;
    @FXML
    private TableView<ArticuloDTO> tbArticuloUnidad;
    @FXML
    private TableColumn<ArticuloDTO, String> tbcListaPrecio;
    @FXML
    private TableColumn<ArticuloDTO, Double> tbcExistencia;
    @FXML
    private TableColumn<ArticuloDTO, String> tbcUnidad;
    @FXML
    private TableColumn<ArticuloDTO, Double> tbcPrecioDeVenta;

    ObservableList<ArticuloDTO> listaArticuloUnidad = FXCollections.observableArrayList();
    private ArticuloDTO articuloDTO;

    private int unidadDespacho = 1;//uoo es Unidad despacho en unidad de medidas por defecto unidad ,2 es en pesos

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public TextField getTxtCAtidad() {
        return txtCAtidad;
    }

    public void setTxtCAtidad(TextField txtCAtidad) {
        this.txtCAtidad = txtCAtidad;
    }
    @FXML
    private Button btnPunto;
    @FXML
    private Button btn0;
    @FXML
    private Button btnAgregar;

    Integer cantidad = 0;
    String numStr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabla();
    }

    public void iniciarTabla() {

        tbArticuloUnidad.setItems(listaArticuloUnidad);
        tbcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tbcPrecioDeVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        tbcExistencia.setCellValueFactory(new PropertyValueFactory<>("existencia"));
        tbcListaPrecio.setCellValueFactory(new PropertyValueFactory<>("nombre"));

//        tbc.setCellValueFactory(
//                cellData -> {
//                    SimpleStringProperty property = new SimpleStringProperty();
//                    if (cellData.getValue() != null) {
//                        property.setValue(cellData.getValue().getUnidad().getDescripcion());
//                    }
//                    return property;
//                });
    }

    @FXML
    private void btn1ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 1;
        txtCAtidad.setText(numStr);

    }

    @FXML
    private void btn2ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 2;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn4ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 4;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn5ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 5;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn7ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 7;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn8ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 8;
        txtCAtidad.setText(numStr);

    }

    @FXML
    private void btn3ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 3;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn6ActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + 6;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn9ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + 9;
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {

        if (txtCAtidad.getText().isEmpty()) {

            ClaseUtil.mensaje("Tiene que digitar una cantidad");

            return;
        }
//
//        if (tbArticuloUnidad.getSelectionModel().getSelectedIndex() == -1) {
//            ClaseUtil.mensaje("Tiene que selccionar una unidad de Venta ");
//            return;
//        }

        setArticuloDTO(tbArticuloUnidad.getSelectionModel().getSelectedItem());

        Double cantidadArt = Double.parseDouble(txtCAtidad.getText());
//
//        Double existencia = ManejoExistenciaArticulo
//                .getInstancia()
//                .getExistenciaArticulo(getArticulo().getCodigo(), 1, unidad, listaPrecio);
//
//        existencia = FormatNum.FormatearDouble(existencia, 2);

        if (cantidadArt <= 0) {

            ClaseUtil.mensaje("La cantidad no puede ser igual a cero");
            txtCAtidad.clear();

            return;
        }

        if (unidadDespacho == 2) {

            cantidadArt = FormatNum
                    .FormatearDouble(cantidadArt / getArticulo()
                            .getPrecioVenta1(), 4);
            System.out.println("Cantidad en peso " + cantidadArt);
        }

//        if (existencia < cantidadArt) {
//
//            ClaseUtil.mensaje("La cantidad a Despachar ** " + cantidadArt
//                    + "  no puede ser mayor que la existencia ,Existencia igual a " + existencia);
//
//            return;
//        }
        Stage stage = (Stage) this.btnAgregar.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void btnPuntoActionEvent(ActionEvent event) {
        numStr = txtCAtidad.getText() + ".";
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btn0ActionEvent(ActionEvent event) {

        numStr = txtCAtidad.getText() + "0";
        txtCAtidad.setText(numStr);
    }

    @FXML
    private void btnLimpiarActionEvent(ActionEvent event) {
        txtCAtidad.clear();
    }

    public void llenarCampo() {

        File imageFile;
        lbNombreArt.setText("Codigo-" + getArticulo().getNumero() + "-" + getArticulo().getNombre());

        System.out.println("getArticulo().getCodigo(), 4 : " + getArticulo().getCodigo() + " " + 4);

        listaArticuloUnidad.addAll(ManejoArticulo.getInstancia().getListaArticuloDTO(getArticulo()));

        if (getArticulo().getRutaImg() == null) {

            imageFile = new File("foto/img_articulo.jpg");
            System.out.println("imageFile es null " + imageFile);

        } else {

            imageFile = new File(getArticulo().getRutaImg());
            System.out.println("imageFile no es null " + imageFile);
        }

        if (getArticulo().getParaDetalle() == false) {
            btnPesos.setDisable(true);
        } else {
            btnPesos.setDisable(false);
        }

        if (listaArticuloUnidad.size() == 1) {

            tbArticuloUnidad.getSelectionModel().select(0);
        }

//        File imageFile = new File(getArticulo().getRutaImg());
//                    img = new ImageView(new Image(imageFile.toURI().toString(), 150, 130, false, false));
        imgArt.setImage(new Image(imageFile.toURI().toString(), 250, 200, false, false));
        imgArt.setFitWidth(250);
        imgArt.setFitHeight(200);

    }

    @FXML
    private void btnUnidadActionEvent(ActionEvent event) {
        setUnidadDespacho(1);// en unidad de  medidas
        lbUnidadDespacho.setText(lbUnidadDespacho.getText());
    }

    @FXML
    private void btnPesosActionEvent(ActionEvent event) {
        setUnidadDespacho(2);//Unidad en pesos;
        lbUnidadDespacho.setText("Pesos");
    }

}
