/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsofyxcollegebuddy;

import java.util.ArrayList;
/**
 *
 * @author TashaOgoti
 */
public class SemesterTrack extends User {
    private String Major;
    private String Minor;
    private ArrayList<String> freshmanCourseList;
    private ArrayList<String> sophomoreCourseList;
    private ArrayList<String> juniorCourseList;
    private ArrayList<String> seniorCourseList;

    
    public SemesterTrack() {
        this.Major = "Unknown";
        this.Minor = "Unknown";
        this.freshmanCourseList = new ArrayList<>();
        this.sophomoreCourseList = new ArrayList<>();
        this.juniorCourseList = new ArrayList<>();
        this.seniorCourseList = new ArrayList<>();
    }
    public SemesterTrack(String Major, String Minor) {
        this();
        this.Major = Major;
        this.Minor = Minor;
    }
    
    public SemesterTrack(String Major, String Minor, ArrayList<String> freshmanCourseList, ArrayList<String> sophomoreCourseList, ArrayList<String> juniorCourseList, ArrayList<String> seniorCourseList) {
        this(Major, Minor);
        this.freshmanCourseList = freshmanCourseList;
        this.sophomoreCourseList = sophomoreCourseList;
        this.juniorCourseList = juniorCourseList;
        this.seniorCourseList = seniorCourseList;
    }

    /**
     * @return the Major
     */
    public String getMajor() {
        return Major;
    }

    /**
     * @param Major the Major to set
     */
    public void setMajor(String Major) {
        this.Major = Major;
    }

    /**
     * @return the Minor
     */
    public String getMinor() {
        return Minor;
    }

    /**
     * @param Minor the Minor to set
     */
    public void setMinor(String Minor) {
        this.Minor = Minor;
    }

    /**
     * @return the freshmanCourseList
     */
    public ArrayList<String> getFreshmanCourseList() {
        return freshmanCourseList;
    }

    /**
     * @param freshmanCourseList the freshmanCourseList to set
     */
    public void setFreshmanCourseList(ArrayList<String> freshmanCourseList) {
        this.freshmanCourseList = freshmanCourseList;
    }

    /**
     * @return the sophomoreCourseList
     */
    public ArrayList<String> getSophomoreCourseList() {
        return sophomoreCourseList;
    }

    /**
     * @param sophomoreCourseList the sophomoreCourseList to set
     */
    public void setSophomoreCourseList(ArrayList<String> sophomoreCourseList) {
        this.sophomoreCourseList = sophomoreCourseList;
    }

    /**
     * @return the juniorCourseList
     */
    public ArrayList<String> getJuniorCourseList() {
        return juniorCourseList;
    }

    /**
     * @param juniorCourseList the juniorCourseList to set
     */
    public void setJuniorCourseList(ArrayList<String> juniorCourseList) {
        this.juniorCourseList = juniorCourseList;
    }

    /**
     * @return the seniorCourseList
     */
    public ArrayList<String> getSeniorCourseList() {
        return seniorCourseList;
    }

    /**
     * @param seniorCourseList the seniorCourseList to set
     */
    public void setSeniorCourseList(ArrayList<String> seniorCourseList) {
        this.seniorCourseList = seniorCourseList;
    }

    
}
