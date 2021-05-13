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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author TashaOgoti
 */
public class TsofyxCollegeBuddyController implements Initializable {

    @FXML
    private Label budget;
    @FXML
    private Button themeBtn;
    @FXML
    private VBox sideTheme;
    @FXML
    private Label dashboard;
    @FXML
    private Label performance;
    @FXML
    private Label graduationPlan;
    @FXML
    private Button signOut;
    @FXML
    private HBox headerTheme;
    @FXML
    private Text userName;
    @FXML
    private Label about;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void changeTheme(ActionEvent event) {
    }

    @FXML
    private void loadDashboard(MouseEvent event) {
    }

    @FXML
    private void loadPerformance(MouseEvent event) {
    }

    @FXML
    private void loadGraduationPlan(MouseEvent event) {
    }

    @FXML
    private void loadBudgetPage(MouseEvent event) {
    }

    @FXML
    private void signUserOut(ActionEvent event) {
    }

    @FXML
    private void loadAbout(MouseEvent event) {
    }
    
}
