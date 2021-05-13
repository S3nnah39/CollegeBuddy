/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsofyxcollegebuddy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author TashaOgoti
 */
public class TsofyxHomeFXMLController extends Switchable implements Initializable, PropertyChangeListener {

    public AbstractModel model;
    
    @FXML
    private Label graduationPlan;
    @FXML
    private Button signOut;
    @FXML
    public Text userName;
    @FXML
    private Button themeBtn;
    @FXML
    private Label about;
    @FXML
    private Label dashboard;
    @FXML
    private Label budget;
    @FXML
    private VBox sideTheme;
    @FXML
    private HBox headerTheme;
    @FXML
    private Pane details;
    @FXML
    private Label infoAboutOptions;
    @FXML
    private Label aboutPageInfo;
    @FXML
    private Label collegeBuddyLabel;
    @FXML
    private ImageView collegeBuddyImage;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        model = new AbstractModel();
        model.addPropertyChangeListener(this);
        //setModel();
        //setHomeUserName("");
        infoAboutOptions.setText(model.getOptionsInfo());
        aboutPageInfo.setText(model.getAppInfo());
    }    


    @FXML
    private void loadGraduationPlan(MouseEvent event) {
        Switchable.switchTo("TsofyxGraduationFXML"); 
    }

    @FXML
    private void signUserOut(ActionEvent event) {
        //Switchable.switchTo("TsofyxSignInFXML"); 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign Out");
        alert.setHeaderText("SUCCESSFULL");
        alert.setContentText("Have a nice day");
        alert.showAndWait();
        Platform.exit();
    }

    @FXML
    private void changeTheme(ActionEvent event) {
        switch (model.getThemeTrack()) {
            case 0:
                sideTheme.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #47126b, #571089)");
                headerTheme.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #47126b, #571089)");
                details.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #47126b, #571089)");
                break;
            case 1:
                sideTheme.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #D91F4E, #FF8546)");
                headerTheme.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #D91F4E, #FF8546)");
                details.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #D91F4E, #FF8546)");
                break;
            case 2:
                sideTheme.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #00296b, #003f88)");
                headerTheme.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #00296b, #003f88)");
                details.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #00296b, #003f88)");
                break;
            default:
                break;
        }
        model.updateThemeTrack();
    }


    @FXML
    private void loadDashboard(MouseEvent event) {
        Switchable.switchTo("TsofyxDashboardFXML"); 
        TsofyxDashboardController controller = (TsofyxDashboardController)getControllerByName("TsofyxDashboard");
        
        if(controller != null){
            controller.setDashboardUserName(userName.getText());
        }
    }

    @FXML
    private void loadBudget(MouseEvent event) {
        Switchable.switchTo("TsofyxBudgetFXML"); 
        //TsofyxBudgetController controllerBudget = (TsofyxBudgetController)getControllerByName("TsofyxBudget");
        TsofyxBudgetController controllerBudget = (TsofyxBudgetController)getControllerByName("TsofyxBudgetController");

        if(controllerBudget != null){
            System.out.println("Got here");
            controllerBudget.setBudgetUserName(userName.getText());
        }
    }
   
    @FXML
    private void showMoreInfo(ActionEvent event) {
        if(model.getInfoBtnTrack().equals(5)){
            details.setVisible(true);
            collegeBuddyLabel.setVisible(false);
            collegeBuddyImage.setVisible(false);
            aboutPageInfo.setVisible(false);
        }
        else if(model.getInfoBtnTrack().equals(8)){
            details.setVisible(false);
            collegeBuddyLabel.setVisible(true);
            collegeBuddyImage.setVisible(true);
            aboutPageInfo.setVisible(true);
        }
        model.updateInfoBtnTrack();
    }
    
    public void showErrorAlert(String content) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Input Error");
         alert.setHeaderText("Error");
         alert.setContentText(content);
         alert.showAndWait();
    }

    public void setHomeUserName(String name){
        
        TsofyxSignInController controller = (TsofyxSignInController)getControllerByName("TsofyxSignIn");
        
        System.out.println("We got to home");
        if(controller != null){
            System.out.println("In home: ");
            name = controller.model.getUser();
            System.out.println(controller.model.getUser());
        }
        System.out.println(userName);
        System.out.println(name);
        /*
        TsofyxHomeFXMLController controller = (TsofyxHomeFXMLController)getControllerByName("TsofyxHomeFXML");
        if(controller != null){
           name = controller.model.getUser();
        }*/
        userName.setText(name);
    }
    
    public void setModel(AbstractModel loadModel){
        /*
        TsofyxHomeFXMLController controller = (TsofyxHomeFXMLController)getControllerByName("TsofyxHomeFXML");
        if(controller != null){
            System.out.println("Home: Model transferred");
            this.model = controller.model;
        }*/
        this.model = loadModel;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Alert")){
            showErrorAlert(evt.getNewValue().toString());
        }
    }
}
