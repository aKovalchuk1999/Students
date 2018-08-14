/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package students;
import DOMAIN.GroupInfo;
import DOMAIN.Journal;
import DOMAIN.Subject;
import DOMAIN.Teacher;
import DOMAIN.Student;
import DOMAIN.User;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ConnectToDB {
     Connection conn;
    
    public ConnectToDB() throws SQLException, ClassNotFoundException, SQLException {
        String hostName = "localhost";
        String dbName = "studentcard";
        String userName = "root";
        String password = "";
        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName+"?useUnicode=true&characterEncoding=utf-8";
        conn = DriverManager.getConnection(connectionURL, userName, password);
        if (conn == null) {
            System.out.println("Помилка у підключенні до бази даних!!!");
        }
    }
    
     public ArrayList<User> LoginUser(String _login, String _password) throws SQLException{
        ArrayList<User> userInfo = new ArrayList<>();
        String sqlCheck = "SELECT * FROM `user` WHERE `userLogin` = '"+_login+"' AND `userPassword` = '"+_password+"';";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sqlCheck);
        if(rs.next() == true){
            userInfo.add(new User(rs.getInt("userID"), rs.getString("userName"), rs.getString("userLogin"), rs.getString("userPassword")));
            return userInfo;
        }
        else {return null; }
    }
     
     public void changeUsername(int userID, String newData) throws SQLException{
         PreparedStatement pre = conn.prepareStatement("UPDATE `user` "
                + "SET `userName`=? WHERE  `userID` = ?;");
         pre.setString(1, newData);
          pre.setInt(2, userID);
        pre.executeUpdate();
     }
     public void changeLogin(int userID, String newData) throws SQLException{
         PreparedStatement pre = conn.prepareStatement("UPDATE `user` "
                + "SET `userLogin`=? WHERE  `userID` = ?;");
         pre.setString(1, newData);
          pre.setInt(2, userID);
        pre.executeUpdate();
     }
     public void changePassword(int userID, String newData) throws SQLException{
         PreparedStatement pre = conn.prepareStatement("UPDATE `user` "
                + "SET `userPassword`=? WHERE  `userID` = ?;");
         pre.setString(1, newData);
          pre.setInt(2, userID);
        pre.executeUpdate();
     }
    
    public ArrayList<Student> getAllStudents() throws SQLException, ParseException{
        String sql = "SELECT * FROM `students`";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Student> allStudents = new ArrayList<>();
        while(rs.next()){
            allStudents.add(new Student(rs.getInt("studentID"), rs.getString("studentLastname"), 
                    rs.getString("studentFirstname"), rs.getString("studentForename"), 
                    rs.getString("studentGroup"), rs.getString("studentNumberOfRecordBook"),
                    rs.getString("studentFormOfStudy"),rs.getString("studentTypeOfTraining"), 
                    rs.getString("studentBirthdate"), rs.getString("studentSex"), 
                    rs.getString("studentAddress"), rs.getString("studentPhone"),
                    rs.getBytes("studentPhoto"),rs.getString("studentPhotoUrl")));
        }
        return allStudents;
    }
    
     public ArrayList<Student> getStudent(Integer _studentID) throws SQLException, ParseException{
       // String sql = "SELECT * FROM `students` WHERE `studentID` = '"+_studentID+"'";
        String sql = "SELECT *  FROM `students`  WHERE `studentID` = '"+_studentID+"'";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Student> students = new ArrayList<>();
        while(rs.next()){
            students.add(new Student(rs.getInt("studentID"), rs.getString("studentLastname"), 
                    rs.getString("studentFirstname"), rs.getString("studentForename"), 
                    rs.getString("studentGroup"), rs.getString("studentNumberOfRecordBook"),
                    rs.getString("studentFormOfStudy"),rs.getString("studentTypeOfTraining"), 
                    rs.getString("studentBirthdate"), rs.getString("studentSex"),
                    rs.getString("studentAddress"),rs.getString("studentPhone"),
                    rs.getBytes("studentPhoto"), rs.getString("studentPhotoUrl") ));
        }
        return students;
    }
    
     public void insertStudent(ArrayList<Student> student) throws SQLException, ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(student.get(0).getBirthdate());
        java.sql.Date date = new java.sql.Date(parsed.getTime());
        PreparedStatement pre = conn.prepareStatement("INSERT INTO `students`(`studentLastname`,`studentFirstname`,"
                  + "`studentForename`,`studentGroup`,`studentNumberOfRecordBook`,"
                  + "`studentFormOfStudy`,`studentTypeOfTraining`,`studentBirthdate`," 
                  + "`studentSex`,`studentAddress`,`studentPhone`,`studentPhoto`,`studentPhotoUrl`)"
                 + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);");
         pre.setString(1, student.get(0).getLastName());
          pre.setString(2, student.get(0).getFirstName());
           pre.setString(3, student.get(0).getForeName());
            pre.setString(4, student.get(0).getGroupName());
             pre.setString(5, student.get(0).getZalikCardNumber());
              pre.setString(6, student.get(0).getStudyForm());
               pre.setString(7, student.get(0).getStudyType());
                pre.setDate(8, date);
                 pre.setString(9, student.get(0).getSex());
                  pre.setString(10, student.get(0).getAddress());
                   pre.setString(11, student.get(0).getPhone());
        Blob blob = new SerialBlob(student.get(0).getFoto());
                    pre.setBlob(12, blob);
                    pre.setString(13, student.get(0).getFotoUrl());
        pre.executeUpdate();
     }
    
     public void deleteStudent(int id) throws SQLException{
          PreparedStatement pre = conn.prepareStatement("DELETE FROM `students` WHERE `studentID` = ?;");
          pre.setInt(1, id);
          pre.executeUpdate();
     }
     
      public void updateStudent(ArrayList<Student> student, int id) throws SQLException, ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(student.get(0).getBirthdate());
        java.sql.Date date = new java.sql.Date(parsed.getTime());
        PreparedStatement pre = conn.prepareStatement("UPDATE `students` "
                + "SET `studentLastName`=?,`studentFirstName`=?,`studentForeName`=?,"
                + "`studentGroup`=?,`studentNumberOfRecordBook`=?,`studentFormOfStudy`=?,"
                + "`studentTypeOfTraining`=?,`studentBirthdate`=?,`studentSex`=?,"
                + "`studentAddress`=?,`studentPhone`=?,`studentPhoto`=?, `studentPhotoUrl`=?  "
                + "WHERE  `studentID` = '"+id+"';");
         pre.setString(1, student.get(0).getLastName());
          pre.setString(2, student.get(0).getFirstName());
           pre.setString(3, student.get(0).getForeName());
            pre.setString(4, student.get(0).getGroupName());
             pre.setString(5, student.get(0).getZalikCardNumber());
              pre.setString(6, student.get(0).getStudyForm());
               pre.setString(7, student.get(0).getStudyType());
                pre.setDate(8, date);
                 pre.setString(9, student.get(0).getSex());
                  pre.setString(10, student.get(0).getAddress());
                   pre.setString(11, student.get(0).getPhone());
        Blob blob = new SerialBlob(student.get(0).getFoto());
                    pre.setBlob(12, blob);
                    pre.setString(13, student.get(0).getFotoUrl());
        pre.executeUpdate();
     }
      
      
      public ArrayList<Student> sortStudents(String column) throws SQLException, ParseException{
        String sql = "SELECT * FROM `students` ORDER BY `"+column+"`";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Student> allStudents = new ArrayList<>();
        while(rs.next()){
            allStudents.add(new Student(rs.getInt("studentID"), rs.getString("studentLastname"), 
                    rs.getString("studentFirstname"), rs.getString("studentForename"), 
                    rs.getString("studentGroup"), rs.getString("studentNumberOfRecordBook"),
                    rs.getString("studentFormOfStudy"),rs.getString("studentTypeOfTraining"), 
                    rs.getString("studentBirthdate"), rs.getString("studentSex"), 
                    rs.getString("studentAddress"), rs.getString("studentPhone"),
                    rs.getBytes("studentPhoto"),rs.getString("studentPhotoUrl")));
        }
        return allStudents;
    }
      
      
      
      
      public ArrayList<Teacher> getAllTeachers() throws SQLException, ParseException{
        String sql = "SELECT * FROM `teachers`";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Teacher> allTeachers = new ArrayList<>();
        while(rs.next()){
            allTeachers.add(new Teacher(rs.getInt("teacherID"), rs.getString("teacherLastname"), 
                    rs.getString("teacherNameAndForeName"), rs.getString("teacherSpeciality"), 
                    rs.getString("teacherDiplom"), rs.getString("teacherKategory"),
                    rs.getString("teacherDegree"),rs.getInt("teacheExperience")));
        }
        return allTeachers;
    }
      
       public ArrayList<Teacher> getTeacher(Integer teacherID) throws SQLException, ParseException{
       // String sql = "SELECT * FROM `students` WHERE `studentID` = '"+_studentID+"'";
        String sql = "SELECT *  FROM `teachers`  WHERE `teacherID` = '"+teacherID+"'";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Teacher> teacher = new ArrayList<>();
        while(rs.next()){
            teacher.add(new Teacher(rs.getInt("teacherID"), rs.getString("teacherLastname"), 
                    rs.getString("teacherNameAndForeName"), rs.getString("teacherSpeciality"), 
                    rs.getString("teacherDiplom"), rs.getString("teacherKategory"),
                    rs.getString("teacherDegree"),rs.getInt("teacheExperience")));
        }
        return teacher;
    }
       
       
       public void insertTeacher(ArrayList<Teacher> teacher) throws SQLException, ParseException{
        PreparedStatement pre = conn.prepareStatement("INSERT INTO `teachers`(`teacherLastname`,"
                + "`teacherNameAndForeName`,`teacherSpeciality`,`teacherDiplom`,`teacherKategory`,"
                + "`teacherDegree`,`teacheExperience`) VALUES(?,?,?,?,?,?,?);");
         pre.setString(1, teacher.get(0).getTeacherSurname());
          pre.setString(2, teacher.get(0).getTeacherForeName());
           pre.setString(3, teacher.get(0).getTeacherSpeciality());
            pre.setString(4, teacher.get(0).getTeacherDiplom());
             pre.setString(5, teacher.get(0).getTeacherKategory());
              pre.setString(6, teacher.get(0).getTeacherDegree());
               pre.setInt(7, teacher.get(0).getTeacheExperience());
        pre.executeUpdate();
     }
    
       
       public void deleteTeacher(int id) throws SQLException{
          PreparedStatement pre = conn.prepareStatement("DELETE FROM `teachers` WHERE `teacherID` = ?;");
          pre.setInt(1, id);
          pre.executeUpdate();
          JOptionPane.showMessageDialog(null, "Викладача видалено!");
     }
       
       public void updateTeacher(ArrayList<Teacher> teacher, int id) throws SQLException, ParseException{
        PreparedStatement pre = conn.prepareStatement("UPDATE `teachers` "
                + "SET `teacherLastname`=?,`teacherNameAndForeName`=?,`teacherSpeciality`=?,"
                + "`teacherDiplom`=?,`teacherKategory`=?,`teacherDegree`=?,"
                + "`teacheExperience`=? WHERE  `teacherID` = ?;");
         pre.setString(1, teacher.get(0).getTeacherSurname());
          pre.setString(2, teacher.get(0).getTeacherForeName());
           pre.setString(3, teacher.get(0).getTeacherSpeciality());
            pre.setString(4, teacher.get(0).getTeacherDiplom());
             pre.setString(5, teacher.get(0).getTeacherKategory());
              pre.setString(6, teacher.get(0).getTeacherDegree());
               pre.setInt(7, teacher.get(0).getTeacheExperience());
                pre.setInt(8, id);
        pre.executeUpdate();
     }
      public ArrayList<Teacher> sortTeachers(String column) throws SQLException, ParseException{
        String sql = "SELECT * FROM `teachers` ORDER BY `"+column+"`";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Teacher> allTeachers = new ArrayList<>();
        while(rs.next()){
            allTeachers.add(new Teacher(rs.getInt("teacherID"), rs.getString("teacherLastname"), 
                    rs.getString("teacherNameAndForeName"), rs.getString("teacherSpeciality"), 
                    rs.getString("teacherDiplom"), rs.getString("teacherKategory"),
                    rs.getString("teacherDegree"),rs.getInt("teacheExperience")));
        }
        return allTeachers;
    }
       
      
      
      
     public ArrayList<Subject> getAllSubjects() throws SQLException, ParseException{
        String sql = "SELECT `subjectID`,`subjectName`,concat(`teacherLastname`,' ',"
                + "substring(`teacherNameAndForeName`,1,1)),`subjectECTS`,"
                + "`subjectTotalNumberOfHours`,`subjecNumberOfLecturesHours`,"
                + "`subjectNumberOfPracticeHours`,`subjectNumberOfLaboratoryHours`,"
                + "`subjectConsultationHours`,`subjectFormOfControl` FROM `teachers`,`subjects`"
                + "WHERE `teacherTeacherID` = `teacherID`";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Subject> allSubjects = new ArrayList<>();
        while(rs.next()){
            allSubjects.add(new Subject(rs.getInt("subjectID"), rs.getString("subjectName"), 
                    rs.getString("concat(`teacherLastname`,' ',substring(`teacherNameAndForeName`,1,1))"), 
                    rs.getDouble("subjectECTS"), rs.getInt("subjectTotalNumberOfHours"), 
                    rs.getInt("subjecNumberOfLecturesHours"),rs.getInt("subjectNumberOfPracticeHours"),
                    rs.getInt("subjectNumberOfLaboratoryHours"), rs.getInt("subjectConsultationHours"),
                    rs.getString("subjectFormOfControl")));
        }
        return allSubjects;
    }
       
    
     
    public ArrayList<Subject> getSubject(Integer subjectID) throws SQLException, ParseException{
        String sql = "SELECT `subjectID`,`subjectName`,`teacherTeacherID` ,concat(`teacherLastname`,' ',"
                + "`teacherNameAndForeName`),`subjectECTS`,"
                + "`subjectTotalNumberOfHours`,`subjecNumberOfLecturesHours`,"
                + "`subjectNumberOfPracticeHours`,`subjectNumberOfLaboratoryHours`,"
                + "`subjectConsultationHours`,`subjectFormOfControl` FROM `teachers`,`subjects`"
                + "WHERE `teacherTeacherID` = `teacherID` AND `subjectID` = '"+subjectID+"' ";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Subject> subject = new ArrayList<>();
        while(rs.next()){
            subject.add(new Subject(rs.getInt("subjectID"), rs.getString("subjectName"),rs.getInt("teacherTeacherID"), 
                    rs.getString("concat(`teacherLastname`,' ',`teacherNameAndForeName`)"), 
                    rs.getDouble("subjectECTS"), rs.getInt("subjectTotalNumberOfHours"), 
                    rs.getInt("subjecNumberOfLecturesHours"), rs.getInt("subjectNumberOfPracticeHours"), 
                    rs.getInt("subjectNumberOfLaboratoryHours"), rs.getInt("subjectConsultationHours"), 
                    rs.getString("subjectFormOfControl")));
        }
        return subject;
    }
    
    
    
    public void insertSubject(ArrayList<Subject> subject) throws SQLException, ParseException{
        PreparedStatement pre = conn.prepareStatement("INSERT INTO `subjects`(`subjectName`,"
                + "`teacherTeacherID`,`subjectECTS`,`subjectTotalNumberOfHours`,"
                + "`subjecNumberOfLecturesHours`,`subjectNumberOfPracticeHours`,"
                + "`subjectNumberOfLaboratoryHours`,`subjectConsultationHours`,"
                + "`subjectFormOfControl`) VALUES(?,?,?,?,?,?,?,?,?);");
         pre.setString(1, subject.get(0).getSubjectName());
          pre.setInt(2, subject.get(0).getSubjectTeacherID());
           pre.setDouble(3, subject.get(0).getSubjectECTS());
            pre.setInt(4, subject.get(0).getSubjectTotal());
             pre.setInt(5, subject.get(0).getSubjectLecture());
              pre.setInt(6, subject.get(0).getSubjectPractice());
               pre.setInt(7, subject.get(0).getSubjectLaboratory());
                pre.setInt(8, subject.get(0).getSubjectConsultation());
                 pre.setString(9, subject.get(0).getSubjectFormControl());
        pre.executeUpdate();
     }
    
    public void updateSubject(ArrayList<Subject> subject, int subjID)throws SQLException, ParseException{
        PreparedStatement pre = conn.prepareStatement("UPDATE `subjects` SET `subjectName` = ?, "
                + "`teacherTeacherID` = ?, `subjectECTS` = ?,`subjectTotalNumberOfHours` = ?,"
                + "`subjecNumberOfLecturesHours` = ?, `subjectNumberOfPracticeHours` = ?,"
                + "`subjectNumberOfLaboratoryHours` = ?,`subjectConsultationHours` = ?,"
                + "`subjectFormOfControl` = ? WHERE `subjectID` = ?");
         pre.setString(1, subject.get(0).getSubjectName());
          pre.setInt(2, subject.get(0).getSubjectTeacherID());
           pre.setDouble(3, subject.get(0).getSubjectECTS());
            pre.setInt(4, subject.get(0).getSubjectTotal());
             pre.setInt(5, subject.get(0).getSubjectLecture());
              pre.setInt(6, subject.get(0).getSubjectPractice());
               pre.setInt(7, subject.get(0).getSubjectLaboratory());
                pre.setInt(8, subject.get(0).getSubjectConsultation());
                 pre.setString(9, subject.get(0).getSubjectFormControl());
                  pre.setInt(10, subjID);
         pre.executeUpdate();
    }
    
    public void deleteSubject(int subjID) throws SQLException, ParseException {
        PreparedStatement pre = conn.prepareStatement("DELETE FROM `subjects` WHERE `subjectID` = ?");
        pre.setInt(1, subjID);
        pre.executeUpdate();
    }
     
     public ArrayList<Subject> sortSubjects(String column) throws SQLException, ParseException{
        String sql = "SELECT `subjectID`,`subjectName`,`teacherTeacherID` ,`teacherLastname`,"
                + "`subjectECTS`,`subjectTotalNumberOfHours`,`subjecNumberOfLecturesHours`,"
                + "`subjectNumberOfPracticeHours`,`subjectNumberOfLaboratoryHours`,"
                + "`subjectConsultationHours`,`subjectFormOfControl` FROM `teachers`,`subjects`"
                + "WHERE `teacherTeacherID` = `teacherID` ORDER BY `"+column+"`";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Subject> subject = new ArrayList<>();
        while(rs.next()){
            subject.add(new Subject(rs.getInt("subjectID"), rs.getString("subjectName"),rs.getInt("teacherTeacherID"), 
                    rs.getString("teacherLastname"), rs.getDouble("subjectECTS"), rs.getInt("subjectTotalNumberOfHours"), 
                    rs.getInt("subjecNumberOfLecturesHours"), rs.getInt("subjectNumberOfPracticeHours"), 
                    rs.getInt("subjectNumberOfLaboratoryHours"), rs.getInt("subjectConsultationHours"), 
                    rs.getString("subjectFormOfControl")));
        }
        return subject;
    }
    
    
    
    public ArrayList<Journal> getNotesByStudent(int studID) throws SQLException{
         String sql = "SELECT `subjectID`,`subjectName`,`journalID`, `journal_subjectID`,`journal_studentID`,`journal_Note`,"
                 + "`studentID`,journal_Renote FROM `subjects`,`journal`,`students` WHERE `subjectID` = `journal_subjectID` AND "
                 + "`journal_studentID` = `studentID` AND `studentID` = '"+studID+"'";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Journal> notes = new ArrayList<>();
        while(rs.next()){
            notes.add(new Journal(rs.getInt("journalID"),rs.getString("subjectName"),rs.getInt("journal_Note"), rs.getInt("journal_Renote")));
        }
        return notes;
    }
    
    
    public void setNewNote(int journalID, int newNote) throws SQLException {
     PreparedStatement pre = conn.prepareStatement("UPDATE `journal` "
                + "SET `journal_Note`= ? WHERE  `journalID` = ?;");
         pre.setInt(1, newNote);
          pre.setInt(2, journalID);
          
        pre.executeUpdate();
    }
    public void insertNote(ArrayList<Journal> newNote) throws SQLException, ParseException{
        PreparedStatement pre = conn.prepareStatement("INSERT INTO `journal`(`journal_subjectID`,"
                + "`journal_studentID`,`journal_Note`,`journal_Renote`) "
                + "VALUES(?,?,?,?);");
         pre.setInt(1, newNote.get(0).getJournalSubjID());
          pre.setInt(2, newNote.get(0).getJournalStudID());
           pre.setInt(3, newNote.get(0).getJournalNote());
            pre.setInt(4, newNote.get(0).getJournalRenote());
        pre.executeUpdate();
     }
    
    public void deleteNote(int noteID) throws SQLException, ParseException{
        PreparedStatement pre = conn.prepareStatement("DELETE FROM `journal` WHERE `journalID` = ?");
        pre.setInt(1, noteID);
        pre.executeUpdate();
    }
    public ArrayList<Journal> sortNotes(int studID, String column) throws SQLException, ParseException{
        String sql = "SELECT `subjectID`,`subjectName`,`journalID`,`journal_subjectID`,`journal_studentID`,`journal_Note`,"
                 + "`studentID`,`journal_Renote` FROM `subjects`,`journal`,`students` WHERE `subjectID` = `journal_subjectID` AND "
                 + "`journal_studentID` = `studentID` AND `studentID` = "+studID+" ORDER BY `"+column+"`";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Journal> notes = new ArrayList<>();
        while(rs.next()){
            notes.add(new Journal(rs.getInt("journalID"),rs.getString("subjectName"),rs.getInt("journal_Note"), rs.getInt("journal_Renote")));
        }
        return notes;
    }
    
    
    
    
    
    
    public ArrayList<Student> getAllGroups() throws SQLException{
        ArrayList<Student> groupsList = new ArrayList<Student>();
       // String sql = "SELECT * FROM `students`  GROUP BY `studentGroup`";
        String sql = "SELECT `studentGroup`, COUNT(`studentGroup`) FROM `students` GROUP BY `studentGroup`";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            groupsList.add(new Student(rs.getString("studentGroup"), rs.getInt("COUNT(`studentGroup`)")));
        }
        return groupsList;
    }
    
    public ArrayList<Student> getStudentsByGroup(String gpName) throws SQLException{
        ArrayList<Student> studList = new ArrayList<Student>();
        String sql = "SELECT * FROM `students` WHERE `studentGroup` = '"+gpName+"'";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            studList.add(new Student(rs.getInt("studentID"), rs.getString("studentLastname"), 
                    rs.getString("studentFirstname"), rs.getString("studentForename"), 
                    rs.getString("studentGroup"), rs.getString("studentNumberOfRecordBook"),
                    rs.getString("studentFormOfStudy"),rs.getString("studentTypeOfTraining"), 
                    rs.getString("studentBirthdate"), rs.getString("studentSex"),
                    rs.getString("studentAddress"),rs.getString("studentPhone"),
                    rs.getBytes("studentPhoto"), rs.getString("studentPhotoUrl") ));
        }
         
         return studList;
    }
    
    public ArrayList<Student> sortGroups(String column) throws SQLException, ParseException{
        String sql = "SELECT `studentGroup`, COUNT(`studentGroup`) FROM `students` GROUP BY `studentGroup` "
                + "ORDER BY "+column+";";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Student> groups = new ArrayList<>();
        while(rs.next()){
            groups.add(new Student(rs.getString("studentGroup"), rs.getInt("COUNT(`studentGroup`)")));
        }
        return groups;
    }
    
    public ArrayList<GroupInfo> getGroupInfo(String gpName)throws SQLException, ParseException{
        ArrayList<GroupInfo> gpInfo = new ArrayList<GroupInfo>();
        String sql = "SELECT `studentGroup`, COUNT(`journal_Note`) as 'count', SUM(`journal_Note`) as 'sum' "
                + "FROM `students`,`journal` WHERE `studentID` = `journal_StudentID` "
                + "AND `studentGroup` = '"+gpName+"'  GROUP BY `studentGroup`";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            gpInfo.add(new GroupInfo(rs.getInt("count"), rs.getInt("sum")));
        }
         return gpInfo;
    }
    
     public ArrayList<GroupInfo> getStudentInfo(int studID)throws SQLException, ParseException{
        ArrayList<GroupInfo> studInfo = new ArrayList<GroupInfo>();
        String sql = "SELECT  COUNT(`journal_Note`) as 'count', SUM(`journal_Note`) as 'sum' "
                + "FROM `students`,`journal` WHERE `studentID` = `journal_StudentID` "
                + "AND `studentID` = '"+studID+"'";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            studInfo.add(new GroupInfo(rs.getInt("count"), rs.getInt("sum")));
        }
         return studInfo;
    }
    
}
