/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion.autorizacion;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manejo.ManejoRol;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import modelo.MenuModulo;
import modelo.Modulo;
import modelo.Rol;
import modelo.RolMenuModulo;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class AutorizacionController implements Initializable {

    @FXML
    private VBox vbRol;
    @FXML
    private TableView<RolMenuModulo> tbOperacionnesRol;
    @FXML
    private TableColumn<RolMenuModulo, String> tbcModuloOpRol;
    @FXML
    private TableColumn<RolMenuModulo, String> tbcNombreOperacionOpRol;
    @FXML
    private VBox vbModulos;
    @FXML
    private TableView<MenuModulo> tbMenuModulo;
    @FXML
    private TableColumn<MenuModulo, MenuModulo> tbcNombreOpMenuModulo;
    @FXML
    private TableView<RolMenuModulo> tbRolMenuModulo;
    @FXML
    private TableColumn<RolMenuModulo, String> tbRolMenuModuloRol;
    @FXML
    private TableColumn<RolMenuModulo, String> tbRolMenuModuloModulo;
    @FXML
    private TableColumn<RolMenuModulo, String> tbRolMenuModuloOperacion;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnGuardar;

    ObservableList<MenuModulo> listaMenuModulo = FXCollections.observableArrayList();
    ObservableList<RolMenuModulo> listaRolMen = FXCollections.observableArrayList();
    ObservableList<RolMenuModulo> listaRolMenNuevo = FXCollections.observableArrayList();

    Rol rol = null;
    RolMenuModulo rolMenuModulo;
    Modulo modulo;
    @FXML
    private TableColumn<RolMenuModulo, RolMenuModulo> tbcHabilitado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTablaOPModulo();
        inicializarTablaOpRol();
        inicializarTablaOpRolMenuModulo();
        crearModulo();
        crearRoles();
    }

    private void inicializarTablaOPModulo() {

        try {

            tbcNombreOpMenuModulo.setCellValueFactory(new PropertyValueFactory<>("menu"));

            tbcNombreOpMenuModulo.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

            tbcNombreOpMenuModulo.setCellFactory((TableColumn<MenuModulo, MenuModulo> param) -> {

                TableCell<MenuModulo, MenuModulo> cellsc = new TableCell<MenuModulo, MenuModulo>() {
                    @Override
                    public void updateItem(MenuModulo item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null) {

                            HBox hbox = visualizarMenuModulo(item);

                            hbox.setOnMouseClicked((event) -> {

                                try {

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            });

                            setGraphic(hbox);
                            setText(null);
                        } else {
                            setGraphic(null);
                            setText(null);
                        }
                    }
                };

                return cellsc;
            });

            tbMenuModulo.setItems(listaMenuModulo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inicializarTablaOpRol() {

        try {

            tbOperacionnesRol.setItems(listaRolMen);
            tbcModuloOpRol.setCellValueFactory(new PropertyValueFactory<>("modulo"));
            tbcNombreOperacionOpRol.setCellValueFactory(new PropertyValueFactory<>("nombreMenu"));
            tbcHabilitado.setCellValueFactory(new PropertyValueFactory<>("habilitado"));

            tbcModuloOpRol.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getRol() != null) {
                            property.setValue(cellData.getValue().getMenuModulo().getNombreModulo());
                        }
                        return property;
                    });

            tbcHabilitado.setCellValueFactory(
                    cellData -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();
                        if (cellData.getValue() != null) {
                            property.setValue(cellData.getValue());
                        }
                        return property;
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

        tbcHabilitado.setCellFactory((TableColumn<RolMenuModulo, RolMenuModulo> param) -> {

            TableCell<RolMenuModulo, RolMenuModulo> cellsc = new TableCell<RolMenuModulo, RolMenuModulo>() {
                @Override
                public void updateItem(RolMenuModulo item, boolean empty) {
                    super.updateItem(item, empty);
                    File imageFile;
                    Image img;
                    Button btnHabilitar;
                    ImageView imageview;
                    Label label;
                    if (item != null) {

//                        if (item.getRutaImg() == null) {
//
                        imageFile = new File("foto/img_articulo.jpg");
//
//                        } else {
//
//                            imageFile = new File(item.getRutaImg());
//                        }

//                    File imageFile = new File(lista.get(num).getRutaImg());
                        label = new Label();
                        btnHabilitar = new Button();
                        btnHabilitar.setPrefSize(70.0, 25.0);

                        if (item.getHabilitado()) {

                            btnHabilitar.setText("SI");

                            btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 15px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 12pt;");

                        } else {

                            btnHabilitar.setText("NO");
                            btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                    + "    -fx-border-color: -fx-secondary;\n"
                                    + "    -fx-border-radius: 15px;\n"
                                    + "    -fx-background-radius: 15px;\n"
                                    + " -fx-text-fill: white;"
                                    + "    -fx-font-size: 12pt;");

                        }

                        imageview = new ImageView(new Image(imageFile.toURI().toString(), 130, 100, false, false));
                        imageview.setFitWidth(50);
                        imageview.setFitHeight(30);

                        HBox hbox = new HBox();

//                        hbox.getChildren().addAll(imageview);
                        hbox.getChildren().add(btnHabilitar);

                        hbox.setAlignment(Pos.CENTER);

                        btnHabilitar.setOnMouseClicked((event) -> {

                            if (item.getHabilitado()) {

                                item.setHabilitado(false);
                                btnHabilitar.setText("NO");

                                btnHabilitar.setStyle("    -fx-background-color: linear-gradient(to left,  #d53400 ,  #d53400);"
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 15px;\n"
                                        + "    -fx-b-radius: 15px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            } else {

                                item.setHabilitado(true);

                                btnHabilitar.setText("SI");

                                btnHabilitar.setStyle(" -fx-background-color: linear-gradient(to left, #5CB85C , #5CB85C); "
                                        + "    -fx-border-color: -fx-secondary;\n"
                                        + "    -fx-border-radius: 15px;\n"
                                        + "    -fx-background-radius: 15px;\n"
                                        + " -fx-text-fill: white;"
                                        + "    -fx-font-size: 12pt;");

                            }

                            ManejoRolMenuModulo.getInstancia().actualizar(item);
                            tbRolMenuModulo.refresh();
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
    }

    private void inicializarTablaOpRolMenuModulo() {

        try {

            tbRolMenuModuloModulo.setCellValueFactory(new PropertyValueFactory<>("modulo"));
            tbRolMenuModuloRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
            tbRolMenuModuloOperacion.setCellValueFactory(new PropertyValueFactory<>("nombreMenu"));

            tbRolMenuModuloRol.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getRol() != null) {
                            property.setValue(cellData.getValue().getRol().getNombre());
                        }
                        return property;
                    });

            tbRolMenuModuloModulo.setCellValueFactory(
                    cellData -> {
                        SimpleStringProperty property = new SimpleStringProperty();
                        if (cellData.getValue().getRol() != null) {
                            property.setValue(cellData.getValue().getMenuModulo().getNombreModulo());
                        }
                        return property;
                    });

            tbRolMenuModulo.setItems(listaRolMenNuevo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crearModulo() {

        Button btn = new Button("BOTON");
        vbModulos.getChildren().clear();
        List<Modulo> lista = ManejoModulo.getInstancia().getLista();

        for (int i = 0; i < lista.size(); i++) {

            btn = new Button(lista.get(i).getNombre());
            btn.setId(Integer.toString(lista.get(i).getCodigo()));
            btn.setCursor(Cursor.HAND);
            btn.setPrefSize(135, 50);
            btn.setMinWidth(135);
            btn.setMaxWidth(135);
            vbModulos.getChildren().add(btn);

        }

        visualizarModulo();

    }

    private void crearHbModulo() {

        Button btn = new Button("BOTON");
        vbModulos.getChildren().clear();
        List<Modulo> lista = ManejoModulo.getInstancia().getLista();

        for (int i = 0; i < lista.size(); i++) {

            btn = new Button(lista.get(i).getNombre());
            btn.setId(Integer.toString(lista.get(i).getCodigo()));
            btn.setCursor(Cursor.HAND);
            btn.setPrefSize(135, 50);
            btn.setMinWidth(135);
            btn.setMaxWidth(135);
            vbModulos.getChildren().add(btn);

        }

        visualizarModulo();

    }

    public void visualizarModulo() {

        vbModulos.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

        for (int i = 0; i < vbModulos.getChildren().size(); i++) {

            Button bt = (Button) vbModulos.getChildren().get(i);

            bt.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

            bt.setOnMouseClicked((event) -> {

                modulo = ManejoModulo.getInstancia().getModulo(Integer.parseInt(bt.getId()));
                listaMenuModulo.setAll(ManejoMenuModulo.getInstancia().getLista(Integer.parseInt(bt.getId())));

            });

        }

    }

    private void crearRoles() {

        Button btn = new Button("BOTON");
        vbRol.getChildren().clear();
        List<Rol> lista = ManejoRol.getInstancia().getRol();

        for (int i = 0; i < lista.size(); i++) {

            btn = new Button(lista.get(i).getNombre());

            btn.setId(Integer.toString(lista.get(i).getCodigo()));
            btn.setCursor(Cursor.HAND);
            btn.setPrefSize(135, 50);
            btn.setMinWidth(135);
            btn.setMaxWidth(135);
            vbRol.getChildren().add(btn);

        }

        visualizarRoles();

    }

    public void visualizarRoles() {

        vbRol.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

        for (int i = 0; i < vbRol.getChildren().size(); i++) {

            Button bt = (Button) vbRol.getChildren().get(i);

            bt.getStylesheets().add(getClass().getResource("/css/mai.css").toExternalForm());

            bt.setOnMouseClicked((event) -> {

                listaRolMen.setAll(ManejoRolMenuModulo.getInstancia().getRolMenuModulo(Integer.parseInt(bt.getId())));

                rol = ManejoRol.getInstancia().getRol(Integer.parseInt(bt.getId()));

            });

        }

    }

    @FXML
    private void btnAgregarActionEvent(ActionEvent event) {

        if (!(tbMenuModulo.getSelectionModel().getSelectedIndex() == -1)
                && rol != null) {

            rolMenuModulo = new RolMenuModulo();

            rolMenuModulo.setMenuModulo(tbMenuModulo.getSelectionModel().getSelectedItem());
            rolMenuModulo.setModulo(tbMenuModulo.getSelectionModel().getSelectedItem().getModulo());
            rolMenuModulo.setNombreMenu(tbMenuModulo.getSelectionModel().getSelectedItem().getMenu());
            rolMenuModulo.setHabilitado(true);
            rolMenuModulo.setRol(rol);

            RolMenuModulo rolMenuModuloDb = ManejoRolMenuModulo.getInstancia()
                    .getRolMenuModulo(rolMenuModulo.getRol().getCodigo(), rolMenuModulo.getModulo(), rolMenuModulo.getMenuModulo().getCodigo());

            if (existe(rolMenuModulo)) {

                ClaseUtil.mensaje("Esta Operacion ya esta agregada");
                return;
            }

            if (!(rolMenuModuloDb == null)) {

                ClaseUtil.mensaje(" Esta Operacion ya esta Asignada en este Rol");
                return;
            }

            listaRolMenNuevo.add(rolMenuModulo);

        } else {

            ClaseUtil.mensaje("Tiene que seleccionar un menu y un rol");
        }
    }

    @FXML
    private void btnEliminarActionEvent(ActionEvent event) {

        if (!(tbRolMenuModulo.getSelectionModel().getSelectedIndex() == -1)) {

            listaRolMenNuevo.remove(tbRolMenuModulo.getSelectionModel().getFocusedIndex());
            tbRolMenuModulo.refresh();

        } else {

            ClaseUtil.mensaje("Tiene que seleccionar un registro");
        }
    }

    @FXML
    private void btnGuardarActionEvent(ActionEvent event) {

        try {

            if (tbRolMenuModulo.getItems().size() <= 0) {

                ClaseUtil.mensaje("No hay registro para guardar");
                return;
            }

            for (RolMenuModulo menuModulo : listaRolMenNuevo) {

                ManejoRolMenuModulo.getInstancia().salvar(menuModulo);

            }
            listaRolMenNuevo.clear();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private HBox visualizarAction(RolMenuModulo rolMenuModulo) {

        ImageView img = null;
        File imageFile;

        imageFile = new File("foto/img_articulo.jpg");

        img = new ImageView(new Image(imageFile.toURI().toString(), 130, 100, false, false));
        img.setFitWidth(90);
        img.setFitHeight(70);

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER_LEFT);

        HBox hb = new HBox();

        hb.setAlignment(Pos.CENTER_LEFT);
        vb.setSpacing(5);
        vb.setPadding(new Insets(5, 5, 5, 5));
        Label lbHabilitado = new Label();

        hb.setStyle("   -fx-text-fill:000000;\n"
                + " -fx-background-color: #FFFFFF;"
                + "    -fx-border-color:  #00bb2d;\n"
                + "    -fx-border-radius: 5px;\n"
                + "    -fx-background-radius: 10px;\n"
                + "    -fx-font-size: 12pt;");

        if (rolMenuModulo.getHabilitado()) {
            lbHabilitado.setText("SI");
        } else {
            lbHabilitado.setText("NO");
        }
        vb.getChildren().add(lbHabilitado);
        hb.getChildren().add(img);
        hb.getChildren().add(vb);
        hb.setCursor(Cursor.HAND);
        return hb;

    }

    @FXML
    private void tbOperacionnesRolMouseClicked(MouseEvent event) {

        if (!(tbOperacionnesRol.getSelectionModel().getSelectedIndex() == -1)) {

            if (event.getClickCount() == 2) {

                rolMenuModulo = tbOperacionnesRol.getSelectionModel().getSelectedItem();

                if (rolMenuModulo.getHabilitado()) {
                    rolMenuModulo.setHabilitado(false);
                } else {
                    rolMenuModulo.setHabilitado(true);
                }

                ManejoRolMenuModulo.getInstancia().actualizar(rolMenuModulo);
            }
        }
    }

    private HBox visualizarMenuModulo(MenuModulo menuModulo) {

        ImageView img = null;

        img = new ImageView(new Image(modulo.getImg(), 130, 100, false, false));
        img.setFitWidth(90);
        img.setFitHeight(70);

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER_LEFT);

        HBox hb = new HBox();

        hb.setAlignment(Pos.CENTER_LEFT);

        vb.setSpacing(5);
        vb.setPadding(new Insets(5, 5, 5, 5));

        hb.setStyle("   -fx-text-fill:000000;\n"
                + "     -fx-background-color: #FFFFFF;"
                + "    -fx-border-color:  #00bb2d;\n"
                + "    -fx-border-radius: 5px;\n"
                + "    -fx-background-radius: 10px;\n");
        vb.setStyle("   -fx-text-fill:000000;\n"
                + "     -fx-background-color: #FFFFFF;"
                //  + "    -fx-border-color:  #00bb2d;\n"
                + "    -fx-border-radius: 5px;\n"
                + "    -fx-background-radius: 10px;\n");

        Label lbNombre = new Label("  " + menuModulo.getMenu());

        lbNombre.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 14pt;");

        vb.getChildren().add(lbNombre);

        hb.getChildren().add(img);
        hb.getChildren().add(vb);
        hb.setCursor(Cursor.HAND);
        return hb;

    }

    private HBox visualizarModulo(Modulo modulo) {

        ImageView img = null;

        img = new ImageView(new Image(modulo.getImg(), 130, 100, false, false));
        img.setFitWidth(90);
        img.setFitHeight(70);

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER_LEFT);

        HBox hb = new HBox();

        hb.setAlignment(Pos.CENTER_LEFT);

        vb.setSpacing(5);
        vb.setPadding(new Insets(5, 5, 5, 5));

        hb.setStyle("   -fx-text-fill:000000;\n"
                + "     -fx-background-color: #FFFFFF;"
                + "    -fx-border-color:  #00bb2d;\n"
                + "    -fx-border-radius: 5px;\n"
                + "    -fx-background-radius: 10px;\n");
        vb.setStyle("   -fx-text-fill:000000;\n"
                + "     -fx-background-color: #FFFFFF;"
                //  + "    -fx-border-color:  #00bb2d;\n"
                + "    -fx-border-radius: 5px;\n"
                + "    -fx-background-radius: 10px;\n");

        Label lbNombre = new Label("  " + modulo.getNombre());

        lbNombre.setStyle(" -fx-text-fill: #000000;"
                + " -fx-font-size: 14pt;");

        vb.getChildren().add(lbNombre);

        hb.getChildren().add(img);
        hb.getChildren().add(vb);
        hb.setCursor(Cursor.HAND);
        return hb;

    }

    private boolean existe(RolMenuModulo det) {

        for (RolMenuModulo detalle : tbRolMenuModulo.getItems()) {

            if (Objects.equals(detalle.getMenuModulo().getCodigo(), det.getMenuModulo().getCodigo())
                    && Objects.equals(detalle.getRol().getCodigo(), det.getRol().getCodigo())) {

                return true;

            }
        }

        return false;
    }

}
