/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsofyxcollegebuddy;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;

/**
 *
 * @author TashaOgoti
 */
public class AbstractModel implements TsofyxEditWord{
    protected PropertyChangeSupport propertyChangeSupport;
    
    private String userFilename;
    private String verifyUser;
    private Boolean verifyPassword;
    private User student;
    private String user;
    private String passcode;
    
    private Integer themeTrack ;
    private Integer infoBtnTrack ;
    private TsofyxSignInController userSignIn;
    //private User loadStudent;
    private String info ;
    private String appInfo ;
    private String optionsInfo;
    
    private Integer editBtnTrack;
    
    private String courseName;
    private String courseStatus;
    private SemesterTrack subjects;
    private Integer courseTrack;
    private String coursefilename;
    
    private Integer expenseBtnTrack;
    private XYChart.Series<String, Number> incomeSeries;
    
    public AbstractModel(){
        verifyPassword = false;
        verifyUser = "test";
        userFilename = "";
        student = new User("test"," ","pass");
        user= "";
        passcode="";
        
        /*Home Values*/
        themeTrack = 0;
        infoBtnTrack = 5;
        info = "Sheep";
        
        optionsInfo = "The application has three windows: \n"
                       +"\nDashboard Window - contains general information regarding the user like their name, hometown etc"
                       +"\n\nGraduation Plan Window - allows the user to organize the classes they plan to take and on which year."
                       +"\nThis is for them to be able to track their progress."
                       +"\n\nBudget Window - allows the user to record their income and trace their expenditure."
                       +" There are visual aids to assist in determining possible areas that costs the highests";
        appInfo = "The application is intended to assist a Mizzou college student as they strike a balance between their academics and finances.";
        
        editBtnTrack = 5;
        courseTrack = 5;
        
        /*Graduation values*/
        courseName = "Unknown";
        courseStatus = "Planned";
        subjects = new SemesterTrack();
        courseTrack = 5;
        coursefilename = "";        
                
        expenseBtnTrack = 5;
        incomeSeries = new XYChart.Series();
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * @return filename the filename to set
     */
    public String getUserFilename(){
        setUserFilename();
        return this.userFilename;
    }
    
    /**
     */
    public void setUserFilename() {
        String userFile = "";
        if(!user.isEmpty() || !user.equals(" ")){
            userFile = user;
        }
        else if (student == null){
            userFile = lowerCaseFirst(student.getFirstName()) + upperCaseFirst(student.getLastName());
        }
        String filename = userFile + ".ser";
        this.userFilename = filename;
    }

    /**
     * @return the verifyUser
     */
    public String getVerifyUser() {
        return verifyUser;
    }

    /**
     * @param verifyUser the verifyUser to set
     */
    public void setVerifyUser(String verifyUser) {
        this.verifyUser = verifyUser;
    }


    /**
     * @return the student
     */
    public User getStudent() {
        return student;
    }


    public void setStudent(String userFirst, String userLast, String userPass) {
        this.student = new User(userFirst,userLast,userPass);
        setNewUser();
        setUserFilename();
        setCoursefilename();
    }
    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * 
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }
    public void setNewUser() {
        user = lowerCaseFirst(student.getFirstName())+upperCaseFirst(student.getLastName());
    }

    /**
     * @return the passcode
     */
    public String getPasscode() {
        return passcode;
    }

    /**
     * @param passcode the passcode to set
     */
    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
  
    public void loadStudentUser(){
        userFilename = user + ".ser";
        //System.out.println(filename);
        File myObj = new File(userFilename);

        if(myObj.exists()){    
            try{ 
                try {
                    FileInputStream fileIn = new FileInputStream(myObj.getPath());
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    student = (User)in.readObject();
                    in.close();
                    fileIn.close();
                    verifyUser = lowerCaseFirst(student.getFirstName()) + upperCaseFirst(student.getLastName());
                    //verifyPassword = student.getPassword();

                } catch (IOException ex) {
                    Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
                }catch (Exception ex) {
                    Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                verifyUser = lowerCaseFirst(student.getFirstName()) + upperCaseFirst(student.getLastName());
                System.out.print(verifyUser);
                //verifyPassword = student.getPassword();
                    
            }
            catch (Exception ex){
                String message = "Exception occured while saving to " + myObj.getPath();
                firePropertyChange("Alert","",message);
                Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            firePropertyChange("Create Account","","You need to create an account. Sign Up");
        }
    }
    public void writeToUserFile(File myObj, User Obj) {
        try{
            FileOutputStream fileOut = new FileOutputStream(myObj.getPath());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    
            out.writeObject(Obj);
            out.close();
            fileOut.close();      
                    
        }catch(FileNotFoundException ex){
            String message = "File not found exception occured while saving to " + myObj.getPath();
            firePropertyChange("Alert","",message);
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (IOException ex){
            String message = "IOException occured while saving to " + myObj.getPath();
            firePropertyChange("Alert","",message);
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex){
            String message = "Exception occured while saving to " + myObj.getPath();
             firePropertyChange("Alert","",message);
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean verification(){
        verifyPassword = student.getPassword(passcode);
        System.out.print(user);
        System.out.print(verifyUser);
        System.out.print(verifyPassword);
        if(user.equals(verifyUser) && verifyPassword){
            return true;
        }
        else{
            String content = "";
            if(!user.equals(verifyUser) && verifyPassword){
                //System.out.println(verifyUser);
                content = "Incorrect username. Try Again.";

            }
            else if(user.equals(verifyUser) && ! verifyPassword){
                //System.out.println(verifyPassword);
                content = "Incorrect password. Try Again.";

            }
            else{
               // System.out.println(verifyUser);
               //System.out.println(verifyPassword);
                content = "Incorrect username and password.";
 
            }
            firePropertyChange("Unauthorized","",content);
        
        }
        return false;
    }
    
    public boolean signUp(){
        try{
            
            File myFile = new File(getUserFilename());
            if(myFile.createNewFile()){
                //System.out.print("File did not exist, but has been created");
            }
            writeToUserFile(myFile,student);
            return true;
        }  
        catch (IOException ex) {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //Source code: https://www.delftstack.com/howto/java/how-to-capitalize-the-first-letter-of-a-string-in-java/
    @Override
    public String upperCaseFirst(String name){
        char[] arr = name.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }
    @Override
    public String lowerCaseFirst(String name){
        char[] arr = name.toCharArray();
        arr[0] = Character.toLowerCase(arr[0]);
        return new String(arr);
    }

    /**
     * @return the themeTrack
     */
    public Integer getThemeTrack() {
        return themeTrack;
    }

    /**
     * @param themeTrack the themeTrack to set
     */
    public void setThemeTrack(Integer themeTrack) {
        this.themeTrack = themeTrack;
    }
    
    public void updateThemeTrack() {
        this.themeTrack ++;
        if(themeTrack >=3){
            themeTrack = 0;
        }
    }

    /**
     * @return the infoBtnTrack
     */
    public Integer getInfoBtnTrack() {
        return infoBtnTrack;
    }

    /**
     * @param infoBtnTrack the infoBtnTrack to set
     */
    public void setInfoBtnTrack(Integer infoBtnTrack) {
        this.infoBtnTrack = infoBtnTrack;
    }
    
    public void updateInfoBtnTrack() {
        if(getInfoBtnTrack().equals(5)){
            setInfoBtnTrack((Integer) 8);
        }
        else if(getInfoBtnTrack().equals(8)){
            setInfoBtnTrack((Integer) 5);
        }
    }
    

    /**
     * @return the userSignIn
     */
    public TsofyxSignInController getUserSignIn() {
        return userSignIn;
    }

    /**
     * @param userSignIn the userSignIn to set
     */
    public void setUserSignIn(TsofyxSignInController userSignIn) {
        this.userSignIn = userSignIn;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return the appInfo
     */
    public String getAppInfo() {
        return appInfo;
    }

    /**
     * @param appInfo the appInfo to set
     */
    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    /**
     * @return the optionsInfo
     */
    public String getOptionsInfo() {
        return optionsInfo;
    }

    /**
     * @param optionsInfo the optionsInfo to set
     */
    public void setOptionsInfo(String optionsInfo) {
        this.optionsInfo = optionsInfo;
    }

    /**
     * @return the editBtnTrack
     */
    public Integer getEditBtnTrack() {
        return editBtnTrack;
    }

    /**
     * @param editBtnTrack the editBtnTrack to set
     */
    public void setEditBtnTrack(Integer editBtnTrack) {
        this.editBtnTrack = editBtnTrack;
    }
    
    public void updateEditBtnTrack(){
        if(editBtnTrack.equals(5)){
            setEditBtnTrack(8);
        }
        else if(editBtnTrack.equals(8)){
            setEditBtnTrack(5);
        }
    }
    
    public void writeToSemesterFile(File myObj, SemesterTrack Obj) {
        try{
            FileOutputStream fileOut = new FileOutputStream(myObj.getPath());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    
            out.writeObject(Obj);
            out.close();
            fileOut.close();      
                    
        }catch(FileNotFoundException ex){
            String message = "File not found exception occured while saving to " + myObj.getPath();
            firePropertyChange("Alert","",message);
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (IOException ex){
            String message = "IOException occured while saving to " + myObj.getPath();
            firePropertyChange("Alert","",message);
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex){
            String message = "Exception occured while saving to " + myObj.getPath();
            firePropertyChange("Alert","",message);
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void readFromSemesterFile(File myObj) {
        try {
            FileInputStream fileIn = new FileInputStream(myObj.getPath());
            ObjectInputStream in = new ObjectInputStream(fileIn);
            //while(in.available() > 0){
                setSubjects((SemesterTrack)in.readObject());
            //}
            
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
           Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public void loadCoursesFromFile(){
        try {
            String filename = getCoursefilename();
            
            File fileObj = new File(filename);
       
            if(fileObj.createNewFile()){
                System.out.println("File has now been created");
                writeToSemesterFile(fileObj, getSubjects());
            }
            readFromSemesterFile(fileObj);
        } catch (IOException ex) {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the courseStatus
     */
    public String getCourseStatus() {
        return courseStatus;
    }

    /**
     * @param courseStatus the courseStatus to set
     */
    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    /**
     * @return the subjects
     */
    public SemesterTrack getSubjects() {
        return subjects;
    }

    /**
     * @param subjects the subjects to set
     */
    public void setSubjects(SemesterTrack subjects) {
        this.subjects = subjects;
    }

    /**
     * @return the courseTrack
     */
    public Integer getCourseTrack() {
        return courseTrack;
    }

    /**
     * @param courseTrack the courseTrack to set
     */
    public void setCourseTrack(Integer courseTrack) {
        this.courseTrack = courseTrack;
    }
    
    public void updateCourseTrack(){
        if(courseTrack.equals(5)){
            courseTrack = 8;
        }
        else if(courseTrack.equals(8)){
            courseTrack = 5;
        }
    }
    public void updateFreshmanList(){
        File courseFile = new File(getCoursefilename());
        subjects.getFreshmanCourseList().add(courseName);
        writeToSemesterFile(courseFile,subjects);
    }
    public void updateSophomoreList(){
        File courseFile = new File(getCoursefilename());
        subjects.getSophomoreCourseList().add(courseName);
        writeToSemesterFile(courseFile,subjects);
    }
    public void updateJuniorList(){
        File courseFile = new File(getCoursefilename());
        subjects.getJuniorCourseList().add(courseName);
        writeToSemesterFile(courseFile,subjects);
    }
    public void updateSeniorList(){
        File courseFile = new File(getCoursefilename());
        subjects.getSeniorCourseList().add(courseName);
        writeToSemesterFile(courseFile,subjects);
    }

    /**
     * @return the coursefilename
     */
    public String getCoursefilename() {
        setCoursefilename();
        return coursefilename;
    }

    /**
     * @param coursefilename the coursefilename to set
     */
    public void setCoursefilename() {
        String coursefile = "";
        String userFile = "";
        if(!user.isEmpty() || !user.equals(" ")){
            userFile = user;
        }
        else if (student == null){
            userFile = lowerCaseFirst(student.getFirstName()) + upperCaseFirst(student.getLastName());
        }
        coursefile = userFile + "Graduation" +".ser";
        this.coursefilename = coursefile;
    }
    
    public void saveStudent(){
        File userFile = new File(getUserFilename());
        writeToUserFile(userFile,student);
    }
    public void saveCourses(){
        File semesterFile = new File(getCoursefilename());
        writeToSemesterFile(semesterFile,subjects);
    }
    
    public boolean validateNumbersInput(String number){
        if(number.matches(".*[a-zA-Z]+.*")){
            firePropertyChange("Alert","","Invalid input: Numerical values only");
            return false;
        }
        else{
            if(Double.parseDouble(number) < 0){
                firePropertyChange("Alert","","Invalid input: Positive Numerical values only");
                return false;
            }
        }
        return true;
    }

    /**
     * @return the incomeSeries
     */
    public XYChart.Series<String, Number> getIncomeSeries() {
        return incomeSeries;
    }

    /**
     * @param incomeSeries the incomeSeries to set
     */
    public void setIncomeSeries(XYChart.Series< String, Number> incomeSeries) {
        this.incomeSeries = incomeSeries;
    }

    /**
     * @return the expenseBtnTrack
     */
    public Integer getExpenseBtnTrack() {
        return expenseBtnTrack;
    }

    /**
     * @param expenseBtnTrack the expenseBtnTrack to set
     */
    public void setExpenseBtnTrack(Integer expenseBtnTrack) {
        this.expenseBtnTrack = expenseBtnTrack;
    }
    
    public void updateExpenseBtnTrack(){
       if(expenseBtnTrack.equals(5)){
            setExpenseBtnTrack(8);
        }
        else if(expenseBtnTrack.equals(8)){
            setExpenseBtnTrack(5);
        }
    }
    
    public void updateStudentExpenditureList(String key, String value){
        Double newValue = Double.parseDouble(value);
        if( student.getStudentExpenditure().containsKey(key)){
            Double oldVal = student.getStudentExpenditure().get(key);
            student.getStudentExpenditure().replace(key, oldVal, newValue);
        }
        else{
            student.getStudentExpenditure().put(key,newValue);
        }
        File userFile = new File(getUserFilename());
        writeToUserFile(userFile,student);
        String total = String.format("%06.2f",student.getTotalExpenditure());
        firePropertyChange("expenseUpdate","", total);
    }
    
    public void updateLineChartSeries(){
        Integer times = 0;
        incomeSeries.getData().clear();
        incomeSeries = new XYChart.Series();
        for (String i : student.getGraphStudentIncome()) { 
            incomeSeries.getData().add(new XYChart.Data(times.toString(),Double.parseDouble(i)));
            times++;
        }
        //firePropertyChange("connectLine","","");
    }


    
    public void updateHometown(String home){
        student.setHometown(home);
        saveStudent();
    }
    
    public void updateMajor(String major){
        subjects.setMajor(major);
        saveCourses();
    }
    
    public void updateMinor(String minor){
        subjects.setMinor(minor);
        saveCourses();
    }

    public String getFullName(){
        String fullName = upperCaseFirst(student.getFirstName()) + " " + upperCaseFirst(student.getLastName());
        return fullName;
    }
    
}
