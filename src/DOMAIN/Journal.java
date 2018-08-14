/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOMAIN;

import java.util.ArrayList;

/**
 *
 * @author Andriy
 */
public class Journal implements java.io.Serializable {
    private int journalID;
    private int journalSubjID;
    private int journalStudID;
    private int journalNote;
    private int journalRenote;

    
    
    private String subjectName;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    
    public Journal(int journalID, int journalSubjID, int journalStudID, int journalNote, int journalRenote) {
        this.journalID = journalID;
        this.journalSubjID = journalSubjID;
        this.journalStudID = journalStudID;
        this.journalNote = journalNote;
        this.journalRenote = journalRenote;
    }
    
    public Journal(int journalID, String _subjectName, int note, int renote){
        this.journalID = journalID;
        this.subjectName = _subjectName;
        this.journalNote = note;
        this.journalRenote = renote;
    }

    public int getJournalID() {
        return journalID;
    }

    public void setJournalID(int journalID) {
        this.journalID = journalID;
    }

    public int getJournalSubjID() {
        return journalSubjID;
    }

    public void setJournalSubjID(int journalSubjID) {
        this.journalSubjID = journalSubjID;
    }

    public int getJournalStudID() {
        return journalStudID;
    }

    public void setJournalStudID(int journalStudID) {
        this.journalStudID = journalStudID;
    }

    public int getJournalNote() {
        return journalNote;
    }

    public void setJournalNote(int journalNote) {
        this.journalNote = journalNote;
    }

    public int getJournalRenote() {
        return journalRenote;
    }

    public void setJournalRenote(int journalRenote) {
        this.journalRenote = journalRenote;
    }
    
    
}
