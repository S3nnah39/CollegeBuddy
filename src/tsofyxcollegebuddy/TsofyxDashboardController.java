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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author TashaOgoti
 */
public class TsofyxDashboardController extends Switchable implements Initializable, PropertyChangeListener{

    public AbstractModel model;
    
    @FXML
    private HBox headerTheme;
    @FXML
    public Text userName;
    @FXML
    private Button themeBtn;
    @FXML
    private VBox sideTheme;
    @FXML
    private Label about;
    @FXML
    private Label graduationPlan;
    @FXML
    private Button signOut;
    @FXML
    private Text studentName;
    @FXML
    private Text studentMajor;
    @FXML
    private Text studentHometown;
    @FXML
    private Text studentMinor;
    @FXML
    private Button editDashboardBtn;
    @FXML
    private Label budget;
    @FXML
    private TextField editHometown;
    @FXML
    private TextField editMajor;
    @FXML
    private TextField editMinor;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        model = new AbstractModel();
        setModel();
        model.addPropertyChangeListener(this);
        setDashboardUserName("");
    }    

    @FXML
    private void changeTheme(ActionEvent event) {
        switch (model.getThemeTrack()) {
            case 0:
                sideTheme.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #47126b, #571089)");
                headerTheme.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #47126b, #571089)");
                break;
            case 1:
                sideTheme.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #D91F4E, #FF8546)");
                headerTheme.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #D91F4E, #FF8546)");
                break;
            case 2:
                sideTheme.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #00296b, #003f88)");
                headerTheme.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #00296b, #003f88)");
                break;
            default:
                break;
        }
        model.updateThemeTrack();
    }

    @FXML
    private void loadAbout(MouseEvent event) {
        Switchable.switchTo("TsofyxHomeFXML"); 
        TsofyxHomeFXMLController controller = (TsofyxHomeFXMLController)getControllerByName("TsofyxHomeFXML");
        
        if(controller != null){
            controller.setHomeUserName(userName.getText());
        }
    }


    @FXML
    private void loadGraduationPlan(MouseEvent event) {
        Switchable.switchTo("TsofyxGraduationFXML"); 
        TsofyxGraduationController controller = (TsofyxGraduationController)getControllerByName("TsofyxGraduation");
        
        if(controller != null){
            controller.setGraduationUserName(userName.getText());
        }
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
    private void editDashboard(ActionEvent event) {
        if(model.getEditBtnTrack().equals(5) && editDashboardBtn.getText().equals("Edit")){
         
            studentHometown.setVisible(false);
            studentMajor.setVisible(false);
            studentMinor.setVisible(false);
            
            editHometown.setVisible(true);
            editMajor.setVisible(true);
            editMinor.setVisible(true);
            
            editDashboardBtn.setText("Save");
            
        }
        else if(model.getEditBtnTrack().equals(8) && editDashboardBtn.getText().equals("Save")){

            if(! editHometown.getText().isEmpty()){
                studentHometown.setText(editHometown.getText());
                model.updateHometown(editHometown.getText());
            }
            if(! editMajor.getText().isEmpty()){
                studentMajor.setText(editMajor.getText());
                model.updateMajor(editMajor.getText());
            }
            if(! editMinor.getText().isEmpty()){
                studentMinor.setText(editMinor.getText());
                model.updateMinor(editMinor.getText());
            }
            
            studentHometown.setVisible(true);
            studentMajor.setVisible(true);
            studentMinor.setVisible(true);
            

            editHometown.setVisible(false);
            editMajor.setVisible(false);
            editMinor.setVisible(false);
            
            editDashboardBtn.setText("Edit");
        }
        model.updateEditBtnTrack();
    }

    @FXML
    private void loadBudget(MouseEvent event) {
        Switchable.switchTo("TsofyxBudgetFXML"); 
        TsofyxBudgetController controller = (TsofyxBudgetController)getControllerByName("TsofyxBudget");
        
        if(controller != null){
            controller.setBudgetUserName(userName.getText());
        }
    }
    public void setDashboardUserName(String name){
        TsofyxHomeFXMLController controller = (TsofyxHomeFXMLController)getControllerByName("TsofyxHomeFXML");
        if(controller != null){
            
            name = controller.model.getUser();
        }
        userName.setText(name);
        studentName.setText(model.getFullName());
    }

    public void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Input Error");
         alert.setHeaderText("Error");
         alert.setContentText(content);
         alert.showAndWait();
    }
    
    public void setModel(){
        TsofyxHomeFXMLController controller = (TsofyxHomeFXMLController)getControllerByName("TsofyxHomeFXML");
        if(controller != null){
            System.out.println("Dashboard: Model transferred");
            
            this.model = controller.model;
            System.out.println(model.getUser());
        }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Alert")){
            showAlert(evt.getNewValue().toString());
        }
        

    }
}

