/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.principal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author maximilianoa-te
 */
public class PnPanelPrincipalController implements Initializable {

  
    @FXML
    private ImageView img;
    
    @FXML
    private ImageView img1;
    
    @FXML
    private ImageView img2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        img.setImage(new Image(getClass().getResourceAsStream("/imagen/compraventa.jpg")));
        img1.setImage(new Image(getClass().getResourceAsStream("/imagen/compraventa.jpg")));
        img2.setImage(new Image(getClass().getResourceAsStream("/imagen/compraventa.jpg")));

    }

}
