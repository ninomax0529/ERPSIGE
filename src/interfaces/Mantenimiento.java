/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author maximilianoa-te
 */
public interface Mantenimiento {

    @FXML
    public  void nuevo(ActionEvent event);
    
    public void guardar();

    public void nuevo();

    public void editar();

    public void limpiar();

    public void buscar();
}
