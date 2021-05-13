/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsofyxcollegebuddy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author TashaOgoti
 */
public class TsofyxBudgetController extends Switchable implements Initializable, PropertyChangeListener {

    public AbstractModel model;
    
    @FXML
    private Label about;
    @FXML
    private Label graduationPlan;
    @FXML
    private Button signOut;
    @FXML
    public Text userName;
    @FXML
    private Button themeBtn;
    @FXML
    private Text studentIncome;
    @FXML
    private Text studentExpenses;
    @FXML
    private NumberAxis amountYaxis;
    @FXML
    private CategoryAxis monthXAxis;
    @FXML
    private ImageView changeIncome;
    @FXML
    private ImageView changeExpenses;
    @FXML
    private Button generateReport;
    @FXML
    private VBox sideTheme;
    @FXML
    private HBox headerTheme;
    
    @FXML
    private HBox addExpenseBox;
    @FXML
    private TextField expenseDescription;
    @FXML
    private TextField expenseAmount;
    @FXML
    private Button addNewExpense;
    @FXML
    private PieChart expensesPieChart;
    @FXML
    private LineChart<String, Number> incomeLineChart;
    @FXML
    private Button viewExpenseList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new AbstractModel();
        setModel();
        //incomeLineChart.getData().add(model.getIncomeSeries());
        
        setBudgetUserName("");
        loadPieChart();
        loadLineChart();
    }    

    @FXML
    private void loadAbout(MouseEvent event) {
        model.saveStudent();
        Switchable.switchTo("TsofyxHomeFXML");
        TsofyxHomeFXMLController controller = (TsofyxHomeFXMLController)getControllerByName("TsofyxHomeFXML");
        
        if(controller != null){
            controller.setHomeUserName(userName.getText());
        }
    }


    @FXML
    private void loadGraduationPlan(MouseEvent event) {
        model.saveStudent();
        Switchable.switchTo("TsofyxGraduationFXML"); 
        TsofyxGraduationController controller = (TsofyxGraduationController)getControllerByName("TsofyxGraduation");
        
        if(controller != null){
            controller.setGraduationUserName(userName.getText());
        }
    }

    @FXML
    private void signUserOut(ActionEvent event) {
        model.saveStudent();
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
    private void generateExpenditureReport(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("List of income: ");
        alert.setHeaderText("Your Income as processed: ");
        String list = "";
        for(String i : model.getStudent().getGraphStudentIncome()){
            list = list +"\t"+ i +"\n";
        }
        
        alert.setContentText(list);
        alert.showAndWait();
        
    }

    @FXML
    private void addExpense(ActionEvent event) {
        String searchKey = expenseDescription.getText();
        String searchValue = expenseAmount.getText();
        if(model.validateNumbersInput(searchValue)){

            model.updateStudentExpenditureList(searchKey, searchValue);
            addExpenseBox.setVisible(false);        
            for(Data d : expensesPieChart.getData())
            {
                if(d.getName().equals(searchKey))
                {
                    d.setPieValue(Double.parseDouble(searchValue));
                    return;
                }
            }  
            expensesPieChart.getData().add(new PieChart.Data(searchKey, Double.parseDouble(searchValue)));
            studentExpenses.setText(String.format("%06.2f",model.getStudent().getTotalExpenditure()));
        
            reset();
        }
    }
    
    private void loadPieChart(){
        for (String i : model.getStudent().getStudentExpenditure().keySet()) {
            Double newValue = model.getStudent().getStudentExpenditure().get(i);
            expensesPieChart.getData().add(new PieChart.Data(i, newValue));
        }
        expensesPieChart.setLabelLineLength(1);

        studentExpenses.setText(String.format("%06.2f",model.getStudent().getTotalExpenditure()));
        
    }
    
    private void loadLineChart(){
        incomeLineChart.getData().clear();
        model.updateLineChartSeries();
        incomeLineChart.getData().add(model.getIncomeSeries());
        studentIncome.setText(String.format("%06.2f",model.getStudent().getYourIncome()));

    }
    
    public void setBudgetUserName(String name){
        TsofyxHomeFXMLController controller = (TsofyxHomeFXMLController)getControllerByName("TsofyxHomeFXML");
        if(controller != null){
            name = controller.model.getUser();
        }
        userName.setText(name);
    }
 
    public void showAlert(String content){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Input Error");
         alert.setHeaderText("Error");
         alert.setContentText(content);
         alert.showAndWait();
    }
    

    @FXML
    private void showExpenseList(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Expenditure");
        alert.setHeaderText("List of expenses:");
        String list = "";
        for(String i : model.getStudent().getStudentExpenditure().keySet()){
            list = list + i +" => "+ model.getStudent().getStudentExpenditure().get(i).toString() + "\n";
            
        }
        list = list + "Total = " + model.getStudent().getTotalExpenditure().toString();
        
        alert.setContentText(list);
        alert.showAndWait();
    }
   
    public void setModel(){
        TsofyxHomeFXMLController controller = (TsofyxHomeFXMLController)getControllerByName("TsofyxHomeFXML");
        if(controller != null){
            System.out.println("Budget: Model transferred");
            this.model = controller.model;
        }
    }
        
    public void reset(){
        expenseDescription.setText("");
        expenseAmount.setText("");
    }


    @FXML
    private void editExpensesText(MouseEvent event) {
        if(model.getExpenseBtnTrack().equals(5)){
            addExpenseBox.setVisible(true);
            model.updateExpenseBtnTrack();
        }
        else if(model.getExpenseBtnTrack().equals(8)){
            addExpenseBox.setVisible(false);
            model.updateExpenseBtnTrack();
        }
        
    }

    @FXML
    private void editIncomeText(MouseEvent event) {
        TextInputDialog userIncome = new TextInputDialog();
        userIncome.setTitle("Edit Total income");
        userIncome.setHeaderText("Enter income amount: ");
        userIncome.setContentText("Income: ");
        
        Optional <String> result = userIncome.showAndWait();
        
        if(result.isPresent()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Error");
            if(result.get().matches(".*[a-zA-Z]+.*")){
                alert.setContentText("Invalid input: Numerical values only");
                alert.showAndWait();
            }
            else{
                if(Double.parseDouble(result.get()) < 0){
                   alert.setContentText("Invalid input: Positive Numerical values only");
                   alert.showAndWait();
                }
                //System.out.println(String.format("%06.2f",Double.parseDouble(result.get())));
                studentIncome.setText(String.format("%06.2f",Double.parseDouble(result.get())));
                model.getStudent().setYourIncome(Double.parseDouble(result.get()));
                model.getStudent().getGraphStudentIncome().add(String.format("%06.2f",Double.parseDouble(result.get())));

                model.saveStudent();
                loadLineChart();
                
            }
        }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Alert")){
            showAlert(evt.getNewValue().toString());
            reset();
        }

    }
}
