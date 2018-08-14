/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOMAIN;

/**
 *
 * @author Andriy
 */
public class GroupInfo implements java.io.Serializable{

    private int notesCount;
    private int notesSum;

    public GroupInfo(int notesCount, int notesSum) {
        this.notesCount = notesCount;
        this.notesSum = notesSum;
    }

    public int getNotesCount() {
        return notesCount;
    }

    public void setNotesCount(int notesCount) {
        this.notesCount = notesCount;
    }

    public int getNotesSum() {
        return notesSum;
    }

    public void setNotesSum(int notesSum) {
        this.notesSum = notesSum;
    }
    
    
    
}
