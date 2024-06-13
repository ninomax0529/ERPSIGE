/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.teclado;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.annotation.PostConstruct;
import manejo.direccion.ManejoDireccion;
import modelo.Articulo;
import modelo.Direccion;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author Departamento IT
 */
public class TecladoDigitalDireccionController implements Initializable {

    @FXML
    private TableView<Direccion> tbDireccion;
    @FXML
    private TableColumn<Direccion, String> tbcDireccion;

    ObservableList<Direccion> listaDireccion = FXCollections.observableArrayList();

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @return the casa
     */
    public String getCasa() {
        return casa;
    }

    /**
     * @param casa the casa to set
     */
    public void setCasa(String casa) {
        this.casa = casa;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return the valorDevuelta
     */
    public Double getValorDevuelta() {
        return valorDevuelta;
    }

    /**
     * @param valorDevuelta the valorDevuelta to set
     */
    public void setValorDevuelta(Double valorDevuelta) {
        this.valorDevuelta = valorDevuelta;
    }

    /**
     * @return the txtPagadocon
     */
    public TextField getTxtPagadocon() {
        return txtPagadocon;
    }

    /**
     * @param txtPagadocon the txtPagadocon to set
     */
    public void setTxtPagadocon(TextField txtPagadocon) {
        this.txtPagadocon = txtPagadocon;
    }

    /**
     * @return the txtLeSobran
     */
    public TextField getTxtLeSobran() {
        return txtLeSobran;
    }

    /**
     * @param txtLeSobran the txtLeSobran to set
     */
    public void setTxtLeSobran(TextField txtLeSobran) {
        this.txtLeSobran = txtLeSobran;
    }

    @FXML
    private Button btnCalle;
    @FXML
    private Button btnCasa;
    @FXML
    private Button btnDireccion;
    @FXML
    private Button btnEspaciadora;
    @FXML
    private Button btnDevuelta;
    @FXML
    private TextField txtPagadocon;
    @FXML
    private TextField txtLeSobran;

    /**
     * @return the txtDireccion
     */
    public TextField getTxtDireccion() {
        return txtDireccion;
    }

    /**
     * @param txtDireccion the txtDireccion to set
     */
    public void setTxtDireccion(TextField txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    @FXML
    private Button btnSalir;
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
    private Button btnLimpiar;
    @FXML
    private Button btn0;

    Articulo articulo;
    @FXML
    private TextField txtCalleNumero;
    @FXML
    private TextField txtCasaNumero;
    @FXML
    private Button btni;
    @FXML
    private Button btno;
    @FXML
    private Button btnd;
    @FXML
    private Button btnf;
    @FXML
    private Button btnk;
    @FXML
    private Button btnl;
    @FXML
    private Button btnp;
    @FXML
    private Button btng;
    @FXML
    private Button btnz;
    @FXML
    private Button btnv;
    @FXML
    private Button btnt;
    @FXML
    private Button btnn;
    @FXML
    private Button btnb;
    @FXML
    private Button btnu;
    @FXML
    private Button btns;
    @FXML
    private Button btnc;
    @FXML
    private Button btnj;
    @FXML
    private Button btny;
    @FXML
    private Button btna;
    @FXML
    private Button btnh;
    @FXML
    private Button btnx;
    @FXML
    private Button btnr;
    @FXML
    private Button btnw;
    @FXML
    private Button btnQ;
    @FXML
    private Button btne;
    @FXML
    private Button btnm;
    @FXML
    private Button btnñ;
    @FXML
    private TextField txtDireccion;
    @FXML
    private Button btnAceptar;

    public TextField getTxtCalleNumero() {
        return txtCalleNumero;
    }

    public void setTxtCalleNumero(TextField txtCalleNumero) {
        this.txtCalleNumero = txtCalleNumero;
    }

    public TextField getTxtCasaNumero() {
        return txtCasaNumero;
    }

    public void setTxtCasaNumero(TextField txtCasaNumero) {
        this.txtCasaNumero = txtCasaNumero;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    Integer cantidad = 0;
    String numStr;
    private String direccion;
    private String calle;
    private String casa;
    int operacion = 0;
    private Double total = 0.00;
    private Double valorDevuelta = 0.00;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTabla();
        txtCalleNumero.setOnKeyReleased((event) -> {

            if (event.getCode() == KeyCode.ENTER) {

                try {

                    if (txtCalleNumero.getText().isEmpty()) {

                        ClaseUtil.mensaje("Tiene que digitar un numero de calle");

                        return;
                    }

                    Double cantidadArt = Double.parseDouble(txtCalleNumero.getText());

                    if (cantidadArt <= 0) {

                        ClaseUtil.mensaje("El numero de la calle no puede ser igual a cero");
                        txtCalleNumero.clear();

                        return;
                    }

                    Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
                    stage.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        txtCasaNumero.setOnKeyReleased((event) -> {

            if (event.getCode() == KeyCode.ENTER) {

                try {

                    if (txtCasaNumero.getText().isEmpty()) {

                        ClaseUtil.mensaje("Tiene que digitar un numero de casa");

                        return;
                    }

                    Double cantidadArt = Double.parseDouble(txtCasaNumero.getText());

                    if (cantidadArt <= 0) {

                        ClaseUtil.mensaje("El numero de la casa no puede ser igual a cero");
                        txtCasaNumero.clear();

                        return;
                    }

                    Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
                    stage.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        getTxtDireccion().setOnKeyReleased((event) -> {

            if (event.getCode() == KeyCode.ENTER) {

                try {

                    if (getTxtDireccion().getText().isEmpty()) {

                        ClaseUtil.mensaje("Tiene que digitar un direccion");

                        return;
                    }

                    Double cantidadArt = Double.parseDouble(getTxtDireccion().getText());

                    if (cantidadArt <= 0) {

                        ClaseUtil.mensaje("La direccion no puede ser igual a cero");
                        getTxtDireccion().clear();

                        return;
                    }

                    Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
                    stage.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        getTxtPagadocon().setOnKeyReleased((event) -> {

            if (event.getCode() == KeyCode.ENTER) {

                try {

                    if (getTxtPagadocon().getText().isEmpty()) {

                        ClaseUtil.mensaje("Tiene que digitar un valor");

                        getTxtPagadocon().requestFocus();
                        return;
                    }

                    Double cantidadArt = Double.parseDouble(getTxtPagadocon().getText());

                    if (cantidadArt <= 0) {

                        ClaseUtil.mensaje("El valor  no puede ser igual a cero");
                        getTxtPagadocon().clear();
                        getTxtPagadocon().requestFocus();

                        return;
                    }

                    Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
                    stage.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }

    public void inicializarTabla() {

        listaDireccion.addAll(ManejoDireccion.getInstancia().getLista());
        tbDireccion.setItems(listaDireccion);
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("sector"));

        tbcDireccion.setCellValueFactory(
                cellData -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    if (cellData.getValue() != null) {
                        property.setValue(cellData.getValue().getSector());
                    }
                    return property;
                });

    }

    @PostConstruct
    public void afterInitialize() {
        txtCalleNumero.requestFocus();
    }

    @FXML
    private void btn1ActionEvent(ActionEvent event) {

        if (operacion == 1) {

            numStr = numStr + 1;
            txtCalleNumero.setText(numStr);

        } else if (operacion == 2) {

            numStr = txtCasaNumero.getText() + 1;
            txtCasaNumero.setText(numStr);

        } else if (operacion == 3) {

            numStr = txtDireccion.getText() + 1;
            txtDireccion.setText(numStr);

        } else if (operacion == 4) {

            numStr = getTxtPagadocon().getText() + 1;
            getTxtPagadocon().setText(numStr);
            txtLeSobran.setText(getDevuelta(Double.parseDouble(numStr)).toString());
        }

    }

    @FXML
    private void btn2ActionEvent(ActionEvent event) {

        if (operacion == 1) {

            numStr = numStr + 2;
            txtCalleNumero.setText(numStr);

        } else if (operacion == 2) {

            numStr = txtCasaNumero.getText() + 2;
            txtCasaNumero.setText(numStr);

        } else if (operacion == 3) {

            numStr = txtDireccion.getText() + 2;
            txtDireccion.setText(numStr);

        } else if (operacion == 4) {

            numStr = getTxtPagadocon().getText() + 2;
            getTxtPagadocon().setText(numStr);
            txtLeSobran.setText(getDevuelta(Double.parseDouble(numStr)).toString());
        }

    }

    @FXML
    private void btn4ActionEvent(ActionEvent event) {

        if (operacion == 1) {

            numStr = numStr + 4;
            txtCalleNumero.setText(numStr);

        } else if (operacion == 2) {

            numStr = txtCasaNumero.getText() + 4;
            txtCasaNumero.setText(numStr);

        } else if (operacion == 3) {

            numStr = txtDireccion.getText() + 4;
            txtDireccion.setText(numStr);

        } else if (operacion == 4) {

            numStr = getTxtPagadocon().getText() + 4;
            getTxtPagadocon().setText(numStr);
            txtLeSobran.setText(getDevuelta(Double.parseDouble(numStr)).toString());
        }

    }

    @FXML
    private void btn5ActionEvent(ActionEvent event) {

        if (operacion == 1) {

            numStr = numStr + 5;
            txtCalleNumero.setText(numStr);

        } else if (operacion == 2) {

            numStr = txtCasaNumero.getText() + 5;
            txtCasaNumero.setText(numStr);

        } else if (operacion == 3) {

            numStr = txtDireccion.getText() + 5;
            txtDireccion.setText(numStr);
        } else if (operacion == 4) {

            numStr = getTxtPagadocon().getText() + 5;
            getTxtPagadocon().setText(numStr);
            txtLeSobran.setText(getDevuelta(Double.parseDouble(numStr)).toString());
        }

    }

    @FXML
    private void btn7ActionEvent(ActionEvent event) {

        if (operacion == 1) {

            numStr = numStr + 7;
            txtCalleNumero.setText(numStr);

        } else if (operacion == 2) {

            numStr = txtCasaNumero.getText() + 7;
            txtCasaNumero.setText(numStr);

        } else if (operacion == 3) {

            numStr = txtDireccion.getText() + 7;
            txtDireccion.setText(numStr);
        } else if (operacion == 4) {

            numStr = getTxtPagadocon().getText() + 7;
            getTxtPagadocon().setText(numStr);
            txtLeSobran.setText(getDevuelta(Double.parseDouble(numStr)).toString());
        }
    }

    @FXML
    private void btn8ActionEvent(ActionEvent event) {

        if (operacion == 1) {

            numStr = numStr + 8;
            txtCalleNumero.setText(numStr);

        } else if (operacion == 2) {

            numStr = txtCasaNumero.getText() + 8;
            txtCasaNumero.setText(numStr);

        } else if (operacion == 3) {

            numStr = txtDireccion.getText() + 8;
            txtDireccion.setText(numStr);
        } else if (operacion == 4) {

            numStr = getTxtPagadocon().getText() + 8;
            getTxtPagadocon().setText(numStr);
            txtLeSobran.setText(getDevuelta(Double.parseDouble(numStr)).toString());
        }

    }

    @FXML
    private void btn3ActionEvent(ActionEvent event) {

        if (operacion == 1) {

            numStr = numStr + 3;
            txtCalleNumero.setText(numStr);

        } else if (operacion == 2) {

            numStr = txtCasaNumero.getText() + 3;
            txtCasaNumero.setText(numStr);

        } else if (operacion == 4) {

            numStr = getTxtPagadocon().getText() + 3;
            getTxtPagadocon().setText(numStr);
            txtLeSobran.setText(getDevuelta(Double.parseDouble(numStr)).toString());
        }

    }

    @FXML
    private void btn6ActionEvent(ActionEvent event) {

        if (operacion == 1) {

            numStr = numStr + 6;
            txtCalleNumero.setText(numStr);

        } else if (operacion == 2) {

            numStr = txtCasaNumero.getText() + 6;
            txtCasaNumero.setText(numStr);

        } else if (operacion == 3) {

            numStr = txtDireccion.getText() + 6;
            txtDireccion.setText(numStr);

        } else if (operacion == 4) {

            numStr = getTxtPagadocon().getText() + 6;
            getTxtPagadocon().setText(numStr);
            txtLeSobran.setText(getDevuelta(Double.parseDouble(numStr)).toString());
        }

    }

    @FXML
    private void btn9ActionEvent(ActionEvent event) {

        if (operacion == 1) {

            numStr = numStr + 9;
            txtCalleNumero.setText(numStr);

        } else if (operacion == 2) {

            numStr = txtCasaNumero.getText() + 9;
            txtCasaNumero.setText(numStr);

        } else if (operacion == 3) {

            numStr = txtDireccion.getText() + 9;
            txtDireccion.setText(numStr);

        } else if (operacion == 4) {

            numStr = getTxtPagadocon().getText() + 9;
            getTxtPagadocon().setText(numStr);
            txtLeSobran.setText(getDevuelta(Double.parseDouble(numStr)).toString());
        }

    }

    @FXML
    private void btn0ActionEvent(ActionEvent event) {

        if (operacion == 1) {

            numStr = numStr + 0;
            txtCalleNumero.setText(numStr);

        } else if (operacion == 2) {

            numStr = txtCasaNumero.getText() + 0;
            txtCasaNumero.setText(numStr);

        } else if (operacion == 3) {

            numStr = txtDireccion.getText() + 0;
            txtDireccion.setText(numStr);

        } else if (operacion == 4) {

            numStr = getTxtPagadocon().getText() + 0;
            getTxtPagadocon().setText(numStr);
            txtLeSobran.setText(getDevuelta(Double.parseDouble(numStr)).toString());
        }
    }

    @FXML
    private void btnLimpiarActionEvent(ActionEvent event) {

        txtCalleNumero.clear();
        txtCasaNumero.clear();
        txtDireccion.clear();
        txtLeSobran.clear();
        txtPagadocon.clear();

        numStr = "";
    }

    @FXML
    private void btnSalirActionEvent(ActionEvent event) {

        Stage stage = (Stage) this.btnSalir.getScene().getWindow();

        stage.close();
    }

    @FXML
    private void btniActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "I";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnoActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "O";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btndActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "D";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnfActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "F";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnkActionEvent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "K";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnlActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "L";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnpActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "P";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btngActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "G";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnzActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "Z";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnvActionEvent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "V";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btntActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "T";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnnActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "N";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnbActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "B";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnuActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "U";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnsActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "S";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btncAactionEvent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "C";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnjActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "J";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnyActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "Y";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnaActionEvent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "A";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnhActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "H";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnxActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "X";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnrActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "R";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnwActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "W";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnQActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "Q";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btneActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "E";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnmActionevent(ActionEvent event) {
        if (operacion == 3) {
            numStr = numStr + "M";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnñActionEvent(ActionEvent event) {

        if (operacion == 3) {
            numStr = numStr + "Ñ";
            txtDireccion.setText(numStr);

        }
    }

    @FXML
    private void btnEspaciadoraActionEvent(ActionEvent event) {

        if (operacion == 3) {
            numStr = numStr + " ";
            txtDireccion.setText(numStr);

        }
    }

    private void lbDireccionMouseClicked(MouseEvent event) {
        operacion = 3;
    }

    @FXML
    private void btnAceptarActionevent(ActionEvent event) {

        if (txtCalleNumero.getText().isEmpty()) {

            ClaseUtil.mensaje("Tiene que digitar un numero de calle");

            return;
        }

        if (txtCasaNumero.getText().isEmpty()) {

            ClaseUtil.mensaje("Tiene que digitar un numero de casa");

            return;
        }

        Double numeroDeCalle = Double.parseDouble(txtCalleNumero.getText());
        Double numeroDeCasa = Double.parseDouble(txtCasaNumero.getText());

        if (numeroDeCalle <= 0) {

            ClaseUtil.mensaje("El numero de calle no ser igual a cero");
            txtCalleNumero.clear();

            return;
        }

        if (numeroDeCasa <= 0) {

            ClaseUtil.mensaje("El numero de la casa no ser igual a cero");
            txtCalleNumero.clear();

            return;
        }

        if (txtDireccion.getText().isEmpty()) {

            ClaseUtil.mensaje("La direccion no puede estar vacia");
            txtCalleNumero.clear();

            return;
        }

        Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void txtCalleNumeroActionEvent(ActionEvent event) {

        txtCalleNumero.setText(numStr);
        numStr = "";
    }

    @FXML
    private void txtCasaNumeroActionevent(ActionEvent event) {

        txtCasaNumero.setText(numStr);
        numStr = "";
    }

    @FXML
    private void txtDireccionActionevent(ActionEvent event) {

    }

    @FXML
    private void btnCalleActionevent(ActionEvent event) {
        operacion = 1;
        numStr = "";
        txtCalleNumero.clear();
        txtCalleNumero.setStyle(" -fx-background-color: #5CB85C; "
                + " -fx-text-fill:ffffff; ");
    }

    @FXML
    private void btnCasaActionEvent(ActionEvent event) {

        operacion = 2;
        numStr = "";
        txtCasaNumero.clear();
        txtCasaNumero.setStyle(" -fx-background-color: #5CB85C;"
                + " -fx-text-fill:ffffff; ");
    }

    @FXML
    private void btnDireccionActionevent(ActionEvent event) {
        operacion = 3;
        numStr = "";
        txtDireccion.clear();
        txtDireccion.setStyle(" -fx-background-color: #5CB85C;"
                + " -fx-text-fill:ffffff; ");
    }

    @FXML
    private void btnDevueltaActionevent(ActionEvent event) {

        operacion = 4;
        numStr = "";
        txtPagadocon.clear();
        txtLeSobran.clear();
        txtPagadocon.setStyle(" -fx-background-color: #5CB85C; "
                + " -fx-text-fill:ffffff; ");
        txtLeSobran.setStyle(" -fx-background-color: #5CB85C; "
                + " -fx-text-fill:ffffff; ");

    }

    public void llenarCampo() {

        getTxtLeSobran().setText(getValorDevuelta().toString());
        getTxtCalleNumero().setText(getCalle());
        getTxtCasaNumero().setText(getCasa());
        getTxtDireccion().setText(getDireccion());
        getTxtPagadocon().setText(getTotal().toString());

    }

    private Double getDevuelta(Double valor) {

        System.out.println("Valor " + valor);
        valorDevuelta = valor - getTotal();

        return valorDevuelta;
    }

    @FXML
    private void tbDireccionMouseClickedd(MouseEvent event) {
        
        if (!(tbDireccion.getSelectionModel().getFocusedIndex() == -1)) {

            txtDireccion.setText(tbDireccion.getSelectionModel().getSelectedItem().getDireccion());
        }
    }

}
