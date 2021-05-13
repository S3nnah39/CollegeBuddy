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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author TashaOgoti
 */
public class TsofyxSignInController extends Switchable implements Initializable, PropertyChangeListener {

    public AbstractModel model;
    
    @FXML
    private TextField usernameSign;
    @FXML
    private PasswordField passwordSign;
    @FXML
    private Button signInPageBtn;
    @FXML
    private TextField firstnameSignUP;
    @FXML
    private TextField lastnameSignUp;
    @FXML
    private PasswordField signUpPassword;
    @FXML
    private Button signUpPageBtn;

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
    }    

    @FXML
    private void signInVerification(ActionEvent event) {
        model.setUser(usernameSign.getText());
        model.setPasscode(passwordSign.getText());
        System.out.println("Username: " + model.getUser());
        System.out.println("Firstname: " + model.getStudent().getFirstName());
        //if(!model.getUser().equals("testSubject") && !model.getPasscode().equals("pass")){

        if(!model.getUser().equals("test")){
            model.loadStudentUser();
        }
        System.out.println("Filename: " + model.getUserFilename());
        if(model.verification()){
            showInfoAlert("AUTHORIZED","Successful Login");
            switchToHomePage(); 
        }
        
    }

    @FXML
    private void signUpStudent(ActionEvent event) {
        System.out.println("SIGN UP IN PROGRESS...");
        model.setStudent(firstnameSignUP.getText(),lastnameSignUp.getText(),signUpPassword.getText());
        System.out.println("User = " + model.getUser());
        if(model.getStudent() == null){
            showErrorAlert("Sign Up Initialization Issue");
            return;
        }
        if(model.signUp()){
            switchToHomePage(); 
            
        }else{
            showErrorAlert("Sign Up saving Issue");
        }
    }    
    
    @FXML
    private void helpUser(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Username");
        alert.setHeaderText("Username consists of: ");
        alert.setContentText("Example:\n Firstname: Sarah Lastname: Anne\n Username: sarahAnne");
        alert.showAndWait();
    }
    
    
    public void switchToHomePage(){
        Switchable.switchTo("TsofyxHomeFXML"); 
        System.out.println("Got here");
        System.out.println("The user" + model.getUser());
        //TsofyxHomeFXMLController controller = (TsofyxHomeFXMLController)getControllerByName("TsofyxHomeFXMLController");
        TsofyxHomeFXMLController controller = (TsofyxHomeFXMLController)getControllerByName("TsofyxHomeFXML");
        if(controller != null){
            System.out.println("And also here");
            String s = model.getUser();
            System.out.println("In switching = " + model.getUser());
            
            //controller.userName.setText(s);
            controller.setHomeUserName(s);
            controller.model = model;
        }
        
        
        //reset();
    }

    public void showErrorAlert(String content){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("");
         alert.setHeaderText("Error");
         alert.setContentText(content);
         alert.showAndWait();
    }
  
    
    public void showInfoAlert(String title,String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void reset(){
        usernameSign.setText("");
        passwordSign.setText("");
        firstnameSignUP.setText("");
        lastnameSignUp.setText("");
        signUpPassword.setText("");
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Main Goes here");
        if(evt.getPropertyName().equals("Alert")){
            System.out.println("Goes here");
            showErrorAlert(evt.getNewValue().toString());
        }
        else if(evt.getPropertyName().equals("Create Account")){
            showErrorAlert(evt.getNewValue().toString());
            reset();
        }
        else if(evt.getPropertyName().equals("Unauthorized")){
            System.out.println("Goes here");
            showInfoAlert("UNAUTHORIZED",evt.getNewValue().toString());
            reset();
        }
    }
    

    
}
