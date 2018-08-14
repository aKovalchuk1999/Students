/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Teachers;


import GUI.MainForm;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import DOMAIN.Teacher;
import GUI.Main;

/**
 *
 * @author Admin
 */
public class TeachersForm extends javax.swing.JFrame {
    static Socket socketConnection;
    int teachID = 0;
    DefaultTableModel dtm = new DefaultTableModel();
   
    public TeachersForm() {
        initComponents();
        dtm.addColumn("ID");
        dtm.addColumn("Прізвище");
        dtm.addColumn("Ім'я");
        dtm.addColumn("Спеціаьність");
        dtm.addColumn("Досвід роботи");
        jTable1.setModel(dtm);
        jTable1.getTableHeader().addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent e) {
            int col = jTable1.columnAtPoint(e.getPoint());
            String sortCol = "";
            String name = jTable1.getColumnName(col);
            switch (col) {
                case 0:
                    sortCol = "teacherID";
                    break;
                case 1:
                    sortCol = "teacherLastname";
                    break;
                case 2:
                    sortCol = "teacherNameAndForeName";
                    break;
                case 3:
                    sortCol = "teacherSpeciality";
                    break;
                case 4:
                    sortCol = "teacheExperience";
                    break;
                default: sortCol = "teacherID";
                    break;
            }
            sortTeachers(sortCol);
        }});
        jTable1.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent me){
                me.getSource();
                Point point = me.getPoint();
                int row = jTable1.rowAtPoint(point);
                if(me.getClickCount() == 2){
                    new EditTeacher((int) jTable1.getValueAt(jTable1.getSelectedRow(), 0)).setVisible(true);
                    closeFrame();
                }
            }
        });   
        displayTeachers();   
    }
    
    public void closeFrame(){
        this.setVisible(false);
    }
     public void Search(String query){
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dtm);
        jTable1.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
    
    private void sortTeachers(String column){
        dtm.getDataVector().removeAllElements();
        ArrayList<Teacher> teacher = new  ArrayList<Teacher>();
        try {
            socketConnection = new Socket("127.0.0.1", 11111);
            DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
            String SQL = "sortTeachers";
            outToServer.writeUTF(SQL);
        } catch (IOException e) {System.out.println(e); }
        try {
            DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
            outToServer.writeUTF(column);
        } catch (IOException e) {System.out.println(e); }
         try {
                ObjectInputStream objectInput = new ObjectInputStream(socketConnection.getInputStream());
                try {
                    teacher = (ArrayList<Teacher>) objectInput.readObject();
                    for(int i = 0; i < teacher.size(); i++){
                        dtm.addRow(new Object[]{teacher.get(i).getTeacherID(),
                        teacher.get(i).getTeacherSurname(), teacher.get(i).getTeacherForeName(),
                        teacher.get(i).getTeacherSpeciality(), teacher.get(i).getTeacheExperience()});
                    }

                } catch (ClassNotFoundException e) {
                    System.out.println("The title list has not come from the server");
                }
            } catch (IOException e) {
                System.out.println("The socket for reading the object has problem");
            }
        
    }

    public void displayTeachers(){
        dtm.getDataVector().removeAllElements();
         ArrayList<Teacher> allTeachers = new  ArrayList<>();
        try {
             socketConnection = new Socket("127.0.0.1", 11111);
            DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
            String SQL = "getAllTeachers";
            outToServer.writeUTF(SQL);
        } catch (IOException e) {System.out.println(e); }
         try {
                ObjectInputStream objectInput = new ObjectInputStream(socketConnection.getInputStream());
                try {
                    allTeachers = (ArrayList<Teacher>) objectInput.readObject();
                    for(int i = 0; i < allTeachers.size(); i++)
                    {
                        dtm.addRow(new Object[]{allTeachers.get(i).getTeacherID(),
                        allTeachers.get(i).getTeacherSurname(), allTeachers.get(i).getTeacherForeName(),
                        allTeachers.get(i).getTeacherSpeciality(), allTeachers.get(i).getTeacheExperience()});
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jTextField3 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jTextField6 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jTextField7 = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jTextField8 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel17 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JToolBar.Separator();
        jLabel16 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JToolBar.Separator();
        jLabel18 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JToolBar.Separator();
        jLabel4 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JToolBar.Separator();
        jLabel12 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        jLabel20 = new javax.swing.JLabel();

        jMenuItem1.setText("Додати");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Редагувати");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        jMenuItem6.setText("Зберегти");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem6);

        jMenuItem3.setText("Видалити");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem3);

        jMenuItem5.setText("Друк");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem5);

        jMenuItem4.setText("Закрити");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(450, 250));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));

        jLabel1.setBackground(new java.awt.Color(0, 51, 255));
        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Список викладачів");

        jLabel2.setBackground(new java.awt.Color(0, 51, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/teacher-teaching-with-a-stick.png"))); // NOI18N

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(103, 103, 103)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 60));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel2MouseReleased(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 330));

        jSeparator1.setBackground(new java.awt.Color(0, 51, 255));
        jSeparator1.setForeground(new java.awt.Color(0, 51, 255));
        jSeparator1.setOpaque(true);
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 160, -1));

        jSeparator3.setBackground(new java.awt.Color(0, 51, 255));
        jSeparator3.setForeground(new java.awt.Color(0, 51, 255));
        jSeparator3.setOpaque(true);
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 160, -1));

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setBorder(null);
        jPanel2.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 160, 20));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setBorder(null);
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 160, 20));

        jLabel6.setBackground(new java.awt.Color(0, 51, 255));
        jLabel6.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 255));
        jLabel6.setText("Ім'я/по-батькові");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

        jLabel5.setBackground(new java.awt.Color(0, 51, 255));
        jLabel5.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 255));
        jLabel5.setText("Прізвище");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/add-contact.png"))); // NOI18N
        jButton1.setText("Додати");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, 110, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/pencil (2).png"))); // NOI18N
        jButton2.setText("Редагувати");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 130, -1));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/delete.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 280, -1, -1));

        jLabel7.setBackground(new java.awt.Color(0, 51, 255));
        jLabel7.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 255));
        jLabel7.setText("Досвід роботи");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, -1, -1));

        jSeparator2.setBackground(new java.awt.Color(0, 51, 255));
        jSeparator2.setForeground(new java.awt.Color(0, 51, 255));
        jSeparator2.setOpaque(true);
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 160, -1));

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setBorder(null);
        jPanel2.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 160, 20));

        jLabel8.setBackground(new java.awt.Color(0, 51, 255));
        jLabel8.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 255));
        jLabel8.setText("Спеціальність");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        jLabel9.setBackground(new java.awt.Color(0, 51, 255));
        jLabel9.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 255));
        jLabel9.setText("Диплом");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, -1));

        jSeparator4.setBackground(new java.awt.Color(0, 51, 255));
        jSeparator4.setForeground(new java.awt.Color(0, 51, 255));
        jSeparator4.setOpaque(true);
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, 160, -1));

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setBorder(null);
        jPanel2.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, 160, 20));

        jLabel10.setBackground(new java.awt.Color(0, 51, 255));
        jLabel10.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 255));
        jLabel10.setText("Категорія");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

        jLabel11.setBackground(new java.awt.Color(0, 51, 255));
        jLabel11.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 255));
        jLabel11.setText("Вчена ступінь");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, -1, -1));

        jSeparator5.setBackground(new java.awt.Color(0, 51, 255));
        jSeparator5.setForeground(new java.awt.Color(0, 51, 255));
        jSeparator5.setOpaque(true);
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 160, -1));

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setBorder(null);
        jPanel2.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 160, 20));

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/reload (1).png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 40, 30));

        jSeparator6.setBackground(new java.awt.Color(0, 51, 255));
        jSeparator6.setForeground(new java.awt.Color(0, 51, 255));
        jSeparator6.setOpaque(true);
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 160, -1));

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(255, 255, 255));
        jTextField7.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setBorder(null);
        jPanel2.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 160, 20));

        jSeparator7.setBackground(new java.awt.Color(0, 51, 255));
        jSeparator7.setForeground(new java.awt.Color(0, 51, 255));
        jSeparator7.setOpaque(true);
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 230, 160, -1));

        jTextField8.setEditable(false);
        jTextField8.setBackground(new java.awt.Color(255, 255, 255));
        jTextField8.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setBorder(null);
        jPanel2.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 160, 20));

        jLabel21.setBackground(new java.awt.Color(0, 51, 255));
        jLabel21.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel21.setText("Швидкий пошук");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, -1, 30));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/magnifying-glass (1).png"))); // NOI18N
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, -1, -1));

        jTextField12.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField12KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 200, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 630, 330));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setRollover(true);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/plus-symbol-in-a-rounded-black-square.png"))); // NOI18N
        jLabel17.setToolTipText("Додати студента");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jToolBar1.add(jLabel17);
        jToolBar1.add(jSeparator11);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/edit-interface-sign.png"))); // NOI18N
        jLabel16.setToolTipText("Редагувати дані студента");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jToolBar1.add(jLabel16);
        jToolBar1.add(jSeparator12);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/exit.png"))); // NOI18N
        jLabel18.setToolTipText("Видалити студента");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jToolBar1.add(jLabel18);
        jToolBar1.add(jSeparator13);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/save.png"))); // NOI18N
        jLabel4.setToolTipText("Зберегти...");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jToolBar1.add(jLabel4);
        jToolBar1.add(jSeparator14);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/printer-.png"))); // NOI18N
        jLabel12.setToolTipText("Друк");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jToolBar1.add(jLabel12);
        jToolBar1.add(jSeparator8);

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/reload.png"))); // NOI18N
        jLabel20.setToolTipText("Оновити дані");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jToolBar1.add(jLabel20);

        getContentPane().add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 630, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        new Main().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        teachID = (int) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
        ArrayList<Teacher> teacher = new  ArrayList<>();
        //int studID;
        try {
            socketConnection = new Socket("127.0.0.1", 11111);
            DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
            String SQL = "getTeacher";
            outToServer.writeUTF(SQL);
        } catch (IOException e) {System.out.println(e); }
        try {
            DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
            outToServer.writeInt(teachID);
        } catch (IOException e) {System.out.println(e); }
         try {
                ObjectInputStream objectInput = new ObjectInputStream(socketConnection.getInputStream());
                try {
                    teacher = (ArrayList<Teacher>) objectInput.readObject();

                        jTextField2.setText(teacher.get(0).getTeacherSurname());
                        jTextField3.setText(teacher.get(0).getTeacherForeName());
                        jTextField4.setText(teacher.get(0).getTeacherSpeciality());
                        jTextField5.setText(teacher.get(0).getTeacherKategory());
                        jTextField6.setText(teacher.get(0).getTeacheExperience().toString());
                        
                         jTextField8.setText(teacher.get(0).getTeacherDegree());
                       
                         jTextField7.setText(teacher.get(0).getTeacherDiplom());

                } catch (ClassNotFoundException e) {
                    System.out.println("The title list has not come from the server");
                }
            } catch (IOException e) {
                System.out.println("The socket for reading the object has problem");
            }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new NewTeacher().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(teachID == 0){
            JOptionPane.showMessageDialog(this, "Викладача не вибрано! \nВиберіть, будь ласка, викладача!");
        }
        else{
            int s = JOptionPane.showConfirmDialog( null, "Видалити цього викладача?", 
                    "An Inane Question", JOptionPane.YES_NO_OPTION);
            if(s == JOptionPane.YES_OPTION){
                try {
                    socketConnection = new Socket("127.0.0.1", 11111);
                    DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
                    String SQL = "deleteTeacher";
                    outToServer.writeUTF(SQL);
                } catch (IOException e) {System.out.println(e); }
                try {
                    DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
                    outToServer.writeInt(teachID);
                } catch (IOException e) {System.out.println(e); }
            }
        }
        displayTeachers();
    }//GEN-LAST:event_jButton3ActionPerformed

    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(teachID == 0){
            JOptionPane.showMessageDialog(this, "Виберіть, будь ласка, викладача!");
        }
        else{
            new EditTeacher(teachID).setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        displayTeachers();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
       Search(jTextField12.getText());
    }//GEN-LAST:event_jTextField12KeyReleased

    private void jPanel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseReleased
         if(evt.isPopupTrigger())
        {
            jPopupMenu1.show(this, evt.getX(), evt.getY() + 80);
        }
    }//GEN-LAST:event_jPanel2MouseReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new NewTeacher().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
         if(teachID == 0){
            JOptionPane.showMessageDialog(this, "Виберіть, будь ласка, викладача!");
        }
        else{
            new EditTeacher(teachID).setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
         if(teachID == 0){
            JOptionPane.showMessageDialog(this, "Викладача не вибрано! \nВиберіть, будь ласка, викладача!");
        }
        else{
            int s = JOptionPane.showConfirmDialog( null, "Видалити цього викладача?", 
                    "An Inane Question", JOptionPane.YES_NO_OPTION);
            if(s == JOptionPane.YES_OPTION){
                try {
                    socketConnection = new Socket("127.0.0.1", 11111);
                    DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
                    String SQL = "deleteTeacher";
                    outToServer.writeUTF(SQL);
                } catch (IOException e) {System.out.println(e); }
                try {
                    DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
                    outToServer.writeInt(teachID);
                } catch (IOException e) {System.out.println(e); }
            }
        }
        displayTeachers();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new MainForm().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        new NewTeacher().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        if(teachID == 0){
            JOptionPane.showMessageDialog(this, "Виберіть, будь ласка, викладача!");
        }
        else{
            new EditTeacher(teachID).setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        if(teachID == 0){
            JOptionPane.showMessageDialog(this, "Викладача не вибрано! \nВиберіть, будь ласка, викладача!");
        }
        else{
            int s = JOptionPane.showConfirmDialog( null, "Видалити цього викладача?", 
                    "An Inane Question", JOptionPane.YES_NO_OPTION);
            if(s == JOptionPane.YES_OPTION){
                try {
                    socketConnection = new Socket("127.0.0.1", 11111);
                    DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
                    String SQL = "deleteTeacher";
                    outToServer.writeUTF(SQL);
                } catch (IOException e) {System.out.println(e); }
                try {
                    DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
                    outToServer.writeInt(teachID);
                } catch (IOException e) {System.out.println(e); }
            }
        }
        displayTeachers();
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        displayTeachers();
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        MessageFormat header = new MessageFormat("Список викладачів коледжу");
        MessageFormat footer = new MessageFormat("Page(0,number,integer)");
        try{
            jTable1.print(JTable.PrintMode.NORMAL, header, footer);
        } catch (PrinterException ex) {
            Logger.getLogger(TeachersForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        MessageFormat header = new MessageFormat("Список викладачів коледжу");
        MessageFormat footer = new MessageFormat("Page(0,number,integer)");
        try{
            jTable1.print(JTable.PrintMode.NORMAL, header, footer);
        } catch (PrinterException ex) {
            Logger.getLogger(TeachersForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        FileWriter fw = null;
        File file = new File("Список викладачів.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(TeachersForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
           fw = new FileWriter(file.getAbsoluteFile());
        } catch (IOException ex) {
            Logger.getLogger(TeachersForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter bw = new BufferedWriter(fw);
        for(int i = 0; i < jTable1.getRowCount(); i++){
            for(int j = 0; j < jTable1.getColumnCount(); j++){
                try {
                    bw.write(jTable1.getModel().getValueAt(i, j) + " ");
                } catch (IOException ex) {
                    Logger.getLogger(TeachersForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                bw.newLine();
            } catch (IOException ex) {
                Logger.getLogger(TeachersForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        try {
            bw.newLine();
            bw.write("Дата збереження: " + dateFormat.format(date).toString());
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(TeachersForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "Дані збережено у файл!");
        
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        FileWriter fw = null;
        File file = new File("Список викладачів.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(TeachersForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
           fw = new FileWriter(file.getAbsoluteFile());
        } catch (IOException ex) {
            Logger.getLogger(TeachersForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter bw = new BufferedWriter(fw);
        for(int i = 0; i < jTable1.getRowCount(); i++){
            for(int j = 0; j < jTable1.getColumnCount(); j++){
                try {
                    bw.write(jTable1.getModel().getValueAt(i, j) + " ");
                } catch (IOException ex) {
                    Logger.getLogger(TeachersForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                bw.newLine();
            } catch (IOException ex) {
                Logger.getLogger(TeachersForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        try {
            bw.newLine();
            bw.write("Дата збереження: " + dateFormat.format(date).toString());
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(TeachersForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "Дані збережено у файл!");
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeachersForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator13;
    private javax.swing.JToolBar.Separator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
