/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsofyxcollegebuddy;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * Source : https://www.debugcn.com/en/article/5418583.html
 *          https://www.youtube.com/watch?v=8fK4Bkqf-_Y&t=560s
 * 
 * @author TashaOgoti
 */
public class TsofyxGraduationController extends Switchable implements Initializable{

    private TextInputDialog taskDialog;
    ArrayList<TaskModel> taskModel;
    public AbstractModel model;
    
    @FXML
    private HBox headerTheme;
    @FXML
    private Text userName;
    @FXML
    private Button themeBtn;
    @FXML
    private VBox sideTheme;
    @FXML
    private Label about;
    @FXML
    private Label dashboard;
    @FXML
    private Label graduationPlan;
    @FXML
    private Label budget;
    @FXML
    private Button signOut;
    @FXML
    private Button freshman;
    @FXML
    private Pane semesterPane;
    @FXML
    private ScrollPane courseList;
    @FXML
    private VBox vTaskItems;
    @FXML
    private Button sophomore;
    @FXML
    private Button junior;
    @FXML
    private Label courseListLabel;
    @FXML
    private Button senior;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
        taskModel = new ArrayList<>();
        model = new AbstractModel();
        setModel();
        setGraduationUserName("");
        model.loadCoursesFromFile();
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
    private void loadDashboard(MouseEvent event) {
        Switchable.switchTo("TsofyxDashboardFXML"); 
    }


    @FXML
    private void loadBudget(MouseEvent event) {
        Switchable.switchTo("TsofyxBudgetFXML"); 
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
    private void openFreshmanCourse(ActionEvent event) {
        if(model.getCourseTrack().equals(5))
        {
            sophomore.setVisible(false);
            junior.setVisible(false);
            senior.setVisible(false);
            semesterPane.setVisible(true);
            courseListLabel.setStyle("-fx-background-color: #ffc300");
            courseListLabel.setTextFill(Color.BLACK);
            vTaskItems.getChildren().clear();
            loadSemesterList(1);
        }
        else if( model.getCourseTrack().equals(8))
        {
            semesterPane.setVisible(false);
            sophomore.setVisible(true);
            junior.setVisible(true);
            senior.setVisible(true);
            
        }
        model.updateCourseTrack();
    }

    @FXML
    private void openSophomoreCourse(ActionEvent event) {
        if( model.getCourseTrack().equals(5))
        {
            sophomore.setLayoutX(183.0);
            freshman.setVisible(false);
            junior.setVisible(false);
            senior.setVisible(false);
            semesterPane.setVisible(true);
            courseListLabel.setStyle("-fx-background-color: #3d348b");
            courseListLabel.setTextFill(Color.WHITE);

            vTaskItems.getChildren().clear();
            loadSemesterList(2);
        }
        else if( model.getCourseTrack().equals(8))
        {
            sophomore.setLayoutX(310.0);
            semesterPane.setVisible(false);
            freshman.setVisible(true);
            junior.setVisible(true);
            senior.setVisible(true);

        }
        model.updateCourseTrack();
    }

    @FXML
    private void openJuniorCourse(ActionEvent event) {
        if( model.getCourseTrack().equals(5))
        {
            junior.setLayoutX(183.0);
            sophomore.setVisible(false);
            freshman.setVisible(false);
            senior.setVisible(false);
            semesterPane.setVisible(true);
            courseListLabel.setStyle("-fx-background-color: #d81159");
            courseListLabel.setTextFill(Color.WHITE);

            vTaskItems.getChildren().clear();
            loadSemesterList(3);
        }
        else if( model.getCourseTrack().equals(8))
        {
            junior.setLayoutX(435.0);
            semesterPane.setVisible(false);
            sophomore.setVisible(true);
            freshman.setVisible(true);
            senior.setVisible(true);
        }
        model.updateCourseTrack();
    }
    

    @FXML
    private void openSeniorCourse(ActionEvent event) {
        if(model.getCourseTrack().equals(5))
        {
            senior.setLayoutX(183.0);
            sophomore.setVisible(false);
            freshman.setVisible(false);
            junior.setVisible(false);
            semesterPane.setVisible(true);
            courseListLabel.setStyle("-fx-background-color: #64dfdf");
            courseListLabel.setTextFill(Color.BLACK);

            vTaskItems.getChildren().clear();
            loadSemesterList(4);
        }
        else if( model.getCourseTrack().equals(8))
        {
            senior.setLayoutX(560.0);
            semesterPane.setVisible(false);
            sophomore.setVisible(true);
            freshman.setVisible(true);
            junior.setVisible(true);
            
        }
        model.updateCourseTrack();
    }
        
    @FXML
    private void addCourse(MouseEvent event) throws IOException {
        setupTask();
        
        TaskModel addTask = new TaskModel(model.getCourseName(),model.getCourseStatus());
        TsofyxTaskItemFXMLController taskController = new TsofyxTaskItemFXMLController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TsofyxTaskItemFXML.fxml"));
        Parent parent = (Parent)loader.load(); // I am not sure you need a cast here
        taskController  = loader.getController();
        taskController.setTask(addTask);
        //Parent parent = (Parent)taskController.load();
        vTaskItems.getChildren().add(parent);

        if(freshman.isVisible()){
            model.updateFreshmanList();
        }
        else if(sophomore.isVisible()){
            model.updateSophomoreList();
        }
        else if(junior.isVisible()){
            model.updateJuniorList();
        }
        else if(senior.isVisible()){
            model.updateSeniorList();
        }
    }
    
    public void setupTask(){
        taskDialog = new TextInputDialog();
        taskDialog.setTitle("Add course");
        taskDialog.setHeaderText("Enter the name of the course: ");
        taskDialog.setContentText("Course: ");
        
        Optional <String> result = taskDialog.showAndWait();
        
        if(result.isPresent()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Error");
            if(result.get().matches(".*[a-zA-Z]+.*")){
               model.setCourseName(result.get());
            }
            else{
                if(Integer.parseInt(result.get()) < 0 || Integer.parseInt(result.get()) > 0){
                    alert.setContentText("Invalid input: Text input only");
                    alert.showAndWait();
                    setupTask();
                }
            }
        }
    }
    public void loadSemesterList(Integer sem){
        Integer limit = 0;
        taskModel.clear();
        switch (sem) {
            case 1:
                limit = model.getSubjects().getFreshmanCourseList().size();
                for(int i=0;i<limit;i++){
                    taskModel.add(new TaskModel(model.getSubjects().getFreshmanCourseList().get(i),model.getCourseStatus()));
                }   
                loadCourseNodes(taskModel);
                break;
            case 2:
                limit = model.getSubjects().getSophomoreCourseList().size();
                for(int i=0;i<limit;i++){
                    taskModel.add(new TaskModel(model.getSubjects().getSophomoreCourseList().get(i),model.getCourseStatus()));
                }
                loadCourseNodes(taskModel);
                break;
            case 3:
                limit = model.getSubjects().getJuniorCourseList().size();
                for(int i=0;i<limit;i++){
                    taskModel.add(new TaskModel(model.getSubjects().getJuniorCourseList().get(i),model.getCourseStatus()));
                }
                loadCourseNodes(taskModel);
                break;
            case 4:
                limit = model.getSubjects().getSeniorCourseList().size();
                for(int i=0;i<limit;i++){
                    taskModel.add(new TaskModel(model.getSubjects().getSeniorCourseList().get(i),model.getCourseStatus()));
                }   
                loadCourseNodes(taskModel);
                break;
            default:
                break;
        }
        
    }



    public void showErrorAlert(String content) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Input Error");
         alert.setHeaderText("Error");
         alert.setContentText(content);
         alert.showAndWait();
    }

    
    public void setGraduationUserName(String name){
        TsofyxHomeFXMLController controller = (TsofyxHomeFXMLController)getControllerByName("TsofyxHomeFXML");
        if(controller != null){
            System.out.println("Budget: Model transferred");
            name = controller.model.getUser();
        }
        userName.setText(name);
    }
    
    public void setModel(){
        TsofyxHomeFXMLController controller = (TsofyxHomeFXMLController)getControllerByName("TsofyxHomeFXML");
        if(controller != null){
            System.out.println("Dashboard: Model transferred");
            this.model = controller.model;
        }
    }
        
    public void loadCourseNodes(ArrayList<TaskModel> addTasks){
        Integer limit = addTasks.size();
        Parent[] parent = new Parent[limit];
        
        //TsofyxTaskItemFXMLController taskController = new TsofyxTaskItemFXMLController();
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("TsofyxTaskItemFXML.fxml"));
        
        for(int i =0; i < limit; i++){
            try {
                TsofyxTaskItemFXMLController taskController = new TsofyxTaskItemFXMLController();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TsofyxTaskItemFXML.fxml"));
        
                parent[i] = (Parent)loader.load(); // I am not sure you need a cast here
                taskController  = loader.getController();
                taskController.setTask(addTasks.get(i));
                //Parent parent = (Parent)taskController.load();
                vTaskItems.getChildren().add(parent[i]);
            } catch (IOException ex) {
                Logger.getLogger(TsofyxGraduationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
