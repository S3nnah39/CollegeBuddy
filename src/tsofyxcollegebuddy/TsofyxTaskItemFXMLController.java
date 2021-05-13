/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsofyxcollegebuddy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * Source: https://www.youtube.com/watch?v=8fK4Bkqf-_Y
 *         https://www.youtube.com/watch?v=5T_9K3ZkCf8
 * 
 * @author TashaOgoti
 */
public class TsofyxTaskItemFXMLController implements Initializable {

    @FXML
    private Circle checkIcon;
    @FXML
    private Label taskName;
    @FXML
    private Button taskBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setTask(TaskModel model){
        taskName.setText(model.getTaskName());
        taskBtn.setText(model.getTaskStatus());
    }
    
}
