/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Students;

import GUI.MainForm;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.runtime.JSType.toInteger;
import DOMAIN.Journal;

/**
 *
 * @author Andriy
 */
public class StudentNotes extends javax.swing.JFrame {

    DefaultTableModel dtm = new DefaultTableModel();
    int studID = 0;
    Socket socketConnection;
    ArrayList<Journal> notes = new  ArrayList<Journal>();
    int id = 0;
    String lastName = "";
    String firstName = "";
    /**
     * Creates new form StudentNotes
     */
    public StudentNotes(int _studID, String _LastN, String _FirstN) {
        initComponents();
        dtm.addColumn("ID");
        dtm.addColumn("Предмет");
        dtm.addColumn("Оцінка");
        dtm.addColumn("Перездача");
        jTable1.setModel(dtm);
        jTable1.getColumn("ID").setMaxWidth(0);
        studID = _studID;
        lastName = _LastN;
        firstName = _FirstN;
        jTable1.getTableHeader().addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent e) {
            int col = jTable1.columnAtPoint(e.getPoint());
            String sortCol = "";
            String name = jTable1.getColumnName(col);
            switch (col) {
                case 0:
                    sortCol = "journalID";
                    break;
                case 1:
                    sortCol = "subjectName";
                    break;
                case 2:
                    sortCol = "journal_Note";
                    break;
                case 3:
                    sortCol = "journal_Renote";
                    break;
                default: sortCol = "journalID";
                    break;
            }
             sortNotes(sortCol);
        }});
        
        showNotes(lastName, firstName);
    }

    StudentNotes() { }
   
    public void showNotes(String _LastN, String _FirstN){
        jLabel5.setText(_LastN);
        jLabel6.setText(_FirstN);
        displayNotes();
    }
    
    public void sortNotes(String column){
         dtm.getDataVector().removeAllElements();
        ArrayList<Journal> notes = new  ArrayList<Journal>();
        try {
            socketConnection = new Socket("127.0.0.1", 11111);
            DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
            String SQL = "sortNotes";
            outToServer.writeUTF(SQL);
        } catch (IOException e) {System.out.println(e); }
        try {
            DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
            outToServer.writeUTF(column);
        } catch (IOException e) {System.out.println(e); }
        try {
            DataOutputStream outToServer1 = new DataOutputStream(socketConnection.getOutputStream());
            outToServer1.writeInt(studID);
        } catch (IOException e) {System.out.println(e); }
         try {
                ObjectInputStream objectInput = new ObjectInputStream(socketConnection.getInputStream());
                try {
                    notes = (ArrayList<Journal>) objectInput.readObject();
                    for(int i = 0; i < notes.size(); i++){
                        dtm.addRow(new Object[]{notes.get(i).getJournalID(),notes.get(i).getSubjectName(),
                                    notes.get(i).getJournalNote(), notes.get(i).getJournalRenote()});
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("The title list has not come from the server");
                }
            } catch (IOException e) {
                System.out.println("The socket for reading the object has problem");
            }
    }
    
    public void displayNotes(){
       
        int noteSum = 0;
        int count = 0;
        dtm.getDataVector().removeAllElements();
        //
        try {
            socketConnection = new Socket("127.0.0.1", 11111);
            DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
            String SQL = "getAllStudentNotes";
            outToServer.writeUTF(SQL);
        } catch (IOException e) {System.out.println(e); }
        try {
            DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
            //int SQL = (int) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            outToServer.writeInt(studID);
        } catch (IOException e) {System.out.println(e); }
         try {
                ObjectInputStream objectInput = new ObjectInputStream(socketConnection.getInputStream());
                try {
                    notes = (ArrayList<Journal>) objectInput.readObject();
                    if(notes.isEmpty()){JOptionPane.showMessageDialog(this,"У цього студента немає оцінок!");}
                    else{
                    for(int i = 0; i < notes.size(); i++)
                    {
                        dtm.addRow(new Object[]{notes.get(i).getJournalID(), notes.get(i).getSubjectName(), notes.get(i).getJournalNote(), notes.get(i).getJournalRenote()});
                        noteSum += notes.get(i).getJournalNote();
                        count++;
                        //System.out.println(notes.get(i).getSubjectName()+" "+notes.get(i).getJournalNote()+" "+notes.get(i).getJournalRenote());
                    }
               jLabel8.setText(Double.toString(noteSum / count));
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("The title list has not come from the server");
                }
            } catch (IOException e) {
                System.out.println("The socket for reading the object has problem");
            }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 250));
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(64, 64));

        jLabel1.setBackground(new java.awt.Color(0, 51, 255));
        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Оцінки студента");

        jLabel2.setBackground(new java.awt.Color(0, 51, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graduate-student-avatar1.png"))); // NOI18N

        jLabel3.setBackground(new java.awt.Color(0, 51, 255));
        jLabel3.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/error.png"))); // NOI18N
        jLabel3.setText("Закрити");
        jLabel3.setToolTipText("");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(75, 75, 75)
                .addComponent(jLabel3)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 60));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/pencil (2).png"))); // NOI18N
        jButton1.setText("Редагувати оцінку");
        jButton1.setActionCommand("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/notepad (1).png"))); // NOI18N
        jButton2.setText("Видалити оцінку");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel4.setText("Студент:");
        jLabel4.setMaximumSize(new java.awt.Dimension(0, 0));
        jLabel4.setMinimumSize(new java.awt.Dimension(0, 0));

        jLabel5.setFont(new java.awt.Font("Monotype Corsiva", 0, 24)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Monotype Corsiva", 0, 24)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel7.setText("Середній бал:");
        jLabel7.setMaximumSize(new java.awt.Dimension(0, 0));
        jLabel7.setMinimumSize(new java.awt.Dimension(0, 0));

        jLabel8.setFont(new java.awt.Font("Monotype Corsiva", 0, 24)); // NOI18N

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/notepad.png"))); // NOI18N
        jButton3.setText("Додати оцінку");
        jButton3.setActionCommand("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/reload (1).png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(47, 47, 47)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 500, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        new StudentsForm().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       id = (int) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(id == 0){
            JOptionPane.showMessageDialog(this, "Виберіть, будь ласка, предмет!");
       }
        else {
            String name = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 1);
            new SetNote(name, id).setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        //displayNotes();
    }//GEN-LAST:event_formWindowActivated

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new AddNote(studID).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(id == 0){ JOptionPane.showMessageDialog(this, "Спочатку потрібно вибрати оцінку зі списку!");}
        else{
        int s = JOptionPane.showConfirmDialog( null, "Видалити оцінку?", 
                    "An Inane Question", JOptionPane.YES_NO_OPTION);
            if(s == JOptionPane.YES_OPTION){
                try {
                    socketConnection = new Socket("127.0.0.1", 11111);
                    DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
                    String SQL = "deleteNote";
                    outToServer.writeUTF(SQL);
                } catch (IOException e) {System.out.println(e); }
                try {
                    DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
                    outToServer.writeInt(id);
                } catch (IOException e) {System.out.println(e); }
                JOptionPane.showMessageDialog(this, "Оцінку видалено!");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        showNotes(lastName, firstName);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentNotes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentNotes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentNotes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentNotes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentNotes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
