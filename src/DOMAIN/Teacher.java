/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOMAIN;

/**
 *
 * @author Admin
 */
public class Teacher implements java.io.Serializable{
    
    private Integer teacherID;
    private String teacherSurname;
    private String teacherForeName;
    private String teacherSpeciality;
    private String teacherDiplom;
    private String teacherKategory;
    private String teacherDegree;
    private Integer teacheExperience;
    
    public Teacher(Integer teacherID, String teacherSurname, String teacherForeName, String teacherSpeciality, String teacherDiplom, String teacherKategory, String teacherDegree, Integer teacheExperience) {
        this.teacherID = teacherID;
        this.teacherSurname = teacherSurname;
        this.teacherForeName = teacherForeName;
        this.teacherSpeciality = teacherSpeciality;
        this.teacherDiplom = teacherDiplom;
        this.teacherKategory = teacherKategory;
        this.teacherDegree = teacherDegree;
        this.teacheExperience = teacheExperience;
    }

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public void setTeacherSurname(String teacherSurname) {
        this.teacherSurname = teacherSurname;
    }

    public String getTeacherForeName() {
        return teacherForeName;
    }

    public void setTeacherForeName(String teacherForeName) {
        this.teacherForeName = teacherForeName;
    }

    public String getTeacherSpeciality() {
        return teacherSpeciality;
    }

    public void setTeacherSpeciality(String teacherSpeciality) {
        this.teacherSpeciality = teacherSpeciality;
    }

    public String getTeacherDiplom() {
        return teacherDiplom;
    }

    public void setTeacherDiplom(String teacherDiplom) {
        this.teacherDiplom = teacherDiplom;
    }

    public String getTeacherKategory() {
        return teacherKategory;
    }

    public void setTeacherKategory(String teacherKategory) {
        this.teacherKategory = teacherKategory;
    }

    public String getTeacherDegree() {
        return teacherDegree;
    }

    public void setTeacherDegree(String teacherDegree) {
        this.teacherDegree = teacherDegree;
    }

    public Integer getTeacheExperience() {
        return teacheExperience;
    }

    public void setTeacheExperience(Integer teacheExperience) {
        this.teacheExperience = teacheExperience;
    }
    
    
}
