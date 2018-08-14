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
public class Subject implements java.io.Serializable{
    private Integer subjectID;
    private String subjectName;
    private String subjectTeacher;
    private double subjectECTS;
    private int subjectTeacherID;
    private Integer subjectTotal;
    private Integer subjectLecture;
    private Integer subjectPractice;
    private Integer subjectLaboratory;
    private Integer subjectConsultation;
    private String subjectFormControl;

    public Subject(Integer subjectID, String subjectName, String subjectTeacher, double subjectECTS, Integer subjectTotal, Integer subjectLecture, Integer subjectPractice, Integer subjectLaboratory, Integer subjectConsultation, String subjectFormControl) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.subjectTeacher = subjectTeacher;
        this.subjectECTS = subjectECTS;
        this.subjectTotal = subjectTotal;
        this.subjectLecture = subjectLecture;
        this.subjectPractice = subjectPractice;
        this.subjectLaboratory = subjectLaboratory;
        this.subjectConsultation = subjectConsultation;
        this.subjectFormControl = subjectFormControl;
    }
    
    public Subject(Integer subjectID, String subjectName, Integer subjectTeacherID, String subjectTeacherNID, double subjectECTS, Integer subjectTotal, Integer subjectLecture, Integer subjectPractice, Integer subjectLaboratory, Integer subjectConsultation, String subjectFormControl) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.subjectTeacher = subjectTeacherNID;
        this.subjectTeacherID = subjectTeacherID;
        this.subjectECTS = subjectECTS;
        this.subjectTotal = subjectTotal;
        this.subjectLecture = subjectLecture;
        this.subjectPractice = subjectPractice;
        this.subjectLaboratory = subjectLaboratory;
        this.subjectConsultation = subjectConsultation;
        this.subjectFormControl = subjectFormControl;
    }

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public double getSubjectECTS() {
        return subjectECTS;
    }

    public void setSubjectECTS(double subjectECTS) {
        this.subjectECTS = subjectECTS;
    }

    public Integer getSubjectTotal() {
        return subjectTotal;
    }

    public void setSubjectTotal(Integer subjectTotal) {
        this.subjectTotal = subjectTotal;
    }

    public Integer getSubjectLecture() {
        return subjectLecture;
    }

    public void setSubjectLecture(Integer subjectLecture) {
        this.subjectLecture = subjectLecture;
    }

    public Integer getSubjectPractice() {
        return subjectPractice;
    }

    public void setSubjectPractice(Integer subjectPractice) {
        this.subjectPractice = subjectPractice;
    }

    public Integer getSubjectLaboratory() {
        return subjectLaboratory;
    }

    public void setSubjectLaboratory(Integer subjectLaboratory) {
        this.subjectLaboratory = subjectLaboratory;
    }

    public Integer getSubjectConsultation() {
        return subjectConsultation;
    }

    public void setSubjectConsultation(Integer subjectConsultation) {
        this.subjectConsultation = subjectConsultation;
    }

    public String getSubjectFormControl() {
        return subjectFormControl;
    }

    public void setSubjectFormControl(String subjectFormControl) {
        this.subjectFormControl = subjectFormControl;
    }
    
    public int getSubjectTeacherID() {
        return subjectTeacherID;
    }
    public void setSubjectTeacherID(int subjectTeacherID) {
        this.subjectTeacherID = subjectTeacherID;
    }
    
    
}
