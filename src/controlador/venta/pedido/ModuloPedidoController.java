/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.venta.pedido;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import utiles.ClaseUtil;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class ModuloPedidoController implements Initializable {

    /**
     * @return the btnPedidoPendiente
     */
    public JFXButton getBtnPedidoPendiente() {
        return btnPedidoPendiente;
    }

    /**
     * @param btnPedidoPendiente the btnPedidoPendiente to set
     */
    public void setBtnPedidoPendiente(JFXButton btnPedidoPendiente) {
        this.btnPedidoPendiente = btnPedidoPendiente;
    }

    /**
     * @return the bpVenta
     */
    public BorderPane getBpVenta() {
        return bpVenta;
    }

    /**
     * @param bpVenta the bpVenta to set
     */
    public void setBpVenta(BorderPane bpVenta) {
        this.bpVenta = bpVenta;
    }

    /**
     * @return the lbModuloVenta
     */
    public Label getLbModuloVenta() {
        return lbModuloVenta;
    }

    /**
     * @param lbModuloVenta the lbModuloVenta to set
     */
    public void setLbModuloVenta(Label lbModuloVenta) {
        this.lbModuloVenta = lbModuloVenta;
    }

    @FXML
    private BorderPane bpVenta;
    private Label lbModuloVenta;
    @FXML
    private JFXButton btnPedido;
    @FXML
    private JFXButton btnPedidoPendiente;
    @FXML
    private JFXButton btnPedidoDespachado;
    @FXML
    private JFXButton btnEntregadoADelivery;
    @FXML
    private JFXButton btnEntregadoACliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        try {

//            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/pedido/PedidoTochGridPane.fxml"));
            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/pedido/PedidoTochGridPaneUltimo.fxml"));
//            lbModuloVenta.setText("Modulo de Venta -> Consulta Factura");
            getBpVenta().setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void btnClienteActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/cliente/Cliente.fxml"));
            getLbModuloVenta().setText("Modulo de Venta -> Cliente");
            getBpVenta().setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnPuntoVentaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/Facturacion.fxml"));
            getLbModuloVenta().setText("Modulo de Venta -> Facturación");
            getBpVenta().setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void btnCajaAcionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/caja/RegistroCaja.fxml"));
            getLbModuloVenta().setText("Modulo de Venta -> Caja");
            getBpVenta().setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void btnConsultaFacturaActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/ConsultaFacturacion.fxml"));
            getLbModuloVenta().setText("Modulo de Venta -> Consulta Factura");
            getBpVenta().setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void NcfActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/ConfiguracionNcf.fxml"));
            getLbModuloVenta().setText("Modulo de Venta -> Configuracion Ncf");
            getBpVenta().setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void rptDiarioDeVentaActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/ReporteDiarioDeVenta.fxml"));

            ClaseUtil.crearStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void rptClienteActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/cliente/ReporteCliente.fxml"));

            ClaseUtil.crearStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void rptDeVentaEntreFechaActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/ReporteDeVentaEntreFecha.fxml"));

            ClaseUtil.crearStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void rptMensualDeVentaActionEvent(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/vista/venta/facturacion/ReporteMensualDeVenta.fxml"));

            ClaseUtil.crearStageModal(root);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnPedidoActionEvent(ActionEvent event) {

        try {

//            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/pedido/PedidoTochGridPane.fxml"));
            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/pedido/PedidoTochGridPaneUltimo.fxml"));
//            lbModuloVenta.setText("Modulo de Venta -> Consulta Factura");
            getBpVenta().setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnPedidoPendienteActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/pedido/PedidosParaDespachar.fxml"));
//            lbModuloVenta.setText("Modulo de Venta -> Consulta Factura");
            getBpVenta().setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnPedidoDespachadoActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/pedido/PedidoListoParaDelivery.fxml"));
//            lbModuloVenta.setText("Modulo de Venta -> Consulta Factura");
            getBpVenta().setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnEntregadoADeliveryActionEvent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/pedido/PedidosEntregadoADelivery.fxml"));
//            lbModuloVenta.setText("Modulo de Venta -> Consulta Factura");
            getBpVenta().setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnEntregadoAClienteActionevent(ActionEvent event) {

        try {

            Node node = (Node) FXMLLoader.load(getClass().getResource("/vista/venta/pedido/PedidosEntregadoACliente.fxml"));
//            lbModuloVenta.setText("Modulo de Venta -> Consulta Factura");
            getBpVenta().setCenter(node);

        } catch (IOException ex) {

            Logger.getLogger(ModuloPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
