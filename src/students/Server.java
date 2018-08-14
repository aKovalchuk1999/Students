package students;
import DOMAIN.Journal;
import DOMAIN.Subject;
import DOMAIN.Teacher;
import DOMAIN.Student;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


class Multi extends Thread  {
    private Socket s = null;
    DataInputStream infromClient;
    
    Multi() throws IOException{ }
    
    Multi(Socket s) throws IOException  {
        this.s=s;
        infromClient = new DataInputStream(s.getInputStream());
    }
    
    public void run(){  
        String Request = new String();
        ConnectToDB con = null;
        try {
          con = new ConnectToDB();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Request = infromClient.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
        }
//******************* Students *******************
        if(Request.equals("getAllStudents")){
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.getAllStudents());
                out.close();
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(Request.equals("getStudent")){
            DataInputStream fromClient = null;
            int id = 1;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                id = (int) fromClient.readInt();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.getStudent(id));
                out.close();
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(Request.equals("insertStudent")){
            ObjectInputStream fromClient = null;
            ArrayList<Student> student = new ArrayList<Student>();
            try {
                fromClient = new ObjectInputStream(s.getInputStream());
                student = (ArrayList<Student>) fromClient.readObject();
                fromClient.close();
                con.insertStudent(student);
            } catch (IOException | ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(Request.equals("deleteStudent")){
            DataInputStream fromClient = null;
            int id = 0;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                id = (int) fromClient.readInt();
                fromClient.close();
                con.deleteStudent(id);
            } catch (IOException | SQLException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(Request.equals("updateStudent")){
            DataInputStream from = null;
            int id = 0;
            try {
                from = new DataInputStream(s.getInputStream());
                id = (int) from.readInt();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectInputStream fromClient = null;
            ArrayList<Student> student = new ArrayList<>();
            try {
                fromClient = new ObjectInputStream(s.getInputStream());
                student = (ArrayList<Student>) fromClient.readObject();
                fromClient.close();
                con.updateStudent(student, id);
            } catch (IOException | ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         if(Request.equals("sortStudents")){
            DataInputStream fromClient = null;
           String column = null;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                column = (String) fromClient.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.sortStudents(column));
                out.close();
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
 //******************* Teachers *******************       
         if(Request.equals("getAllTeachers")){
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.getAllTeachers());
                out.close();
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
         if(Request.equals("getTeacher")){
                DataInputStream fromClient = null;
                int id = 1;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                id = (int) fromClient.readInt();
            } catch (IOException ex) {
                    Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
                }
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.getTeacher(id));
                out.close();
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
         if(Request.equals("insertTeacher")){
            ObjectInputStream fromClient = null;
            ArrayList<Teacher> teacher = new ArrayList<Teacher>();
            try {
                fromClient = new ObjectInputStream(s.getInputStream());
                teacher = (ArrayList<Teacher>) fromClient.readObject();
                fromClient.close();
                con.insertTeacher(teacher);
            } catch (IOException | ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
        if(Request.equals("deleteTeacher")){
            DataInputStream fromClient = null;
            int id = 0;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                id = (int) fromClient.readInt();
                fromClient.close();
                con.deleteTeacher(id);
            } catch (IOException | SQLException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(Request.equals("updateTeacher")){
            DataInputStream from = null;
            int id = 0;
            try {
                from = new DataInputStream(s.getInputStream());
                id = (int) from.readInt();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectInputStream fromClient = null;
            ArrayList<Teacher> teacher = new ArrayList<>();
            try {
                fromClient = new ObjectInputStream(s.getInputStream());
                teacher = (ArrayList<Teacher>) fromClient.readObject();
                fromClient.close();
                con.updateTeacher(teacher, id);
            } catch (IOException | ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(Request.equals("sortTeachers")){
            DataInputStream fromClient = null;
           String column = null;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                column = (String) fromClient.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.sortTeachers(column));
                out.close();
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
 //******************* Subjects *******************    
            if(Request.equals("getAllSubjects")){
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.getAllSubjects());
                out.close();
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
         
        if(Request.equals("getSubject")){
                DataInputStream fromClient = null;
                int id = 1;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                id = (int) fromClient.readInt();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.getSubject(id));
                out.close();
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(Request.equals("insertSubject")){
            ObjectInputStream fromClient = null;
            ArrayList<Subject> subject = new ArrayList<Subject>();
            try {
                fromClient = new ObjectInputStream(s.getInputStream());
                subject = (ArrayList<Subject>) fromClient.readObject();
                fromClient.close();
                con.insertSubject(subject);
            } catch (IOException | ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(Request.equals("updateSubject")){
            DataInputStream from = null;
            int id = 0;
            try {
                from = new DataInputStream(s.getInputStream());
                id = (int) from.readInt();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectInputStream fromClient = null;
            ArrayList<Subject> subject = new ArrayList<>();
            try {
                fromClient = new ObjectInputStream(s.getInputStream());
                subject = (ArrayList<Subject>) fromClient.readObject();
                fromClient.close();
                con.updateSubject(subject, id);
            } catch (IOException | ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         if(Request.equals("deleteSubject")){
            DataInputStream fromClient = null;
            int id = 0;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                id = (int) fromClient.readInt();
                fromClient.close();
                con.deleteSubject(id);
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(Request.equals("sortSubjects")){
            DataInputStream fromClient = null;
            String column = null;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                column = (String) fromClient.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.sortSubjects(column));
                out.close();
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
         
        
   //******************* Notes ******************* 
        if(Request.equals("getAllStudentNotes")){
            DataInputStream fromClient = null;
            int id = 1;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                id = (int) fromClient.readInt();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.getNotesByStudent(id));
                out.close();
            } catch (IOException | SQLException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(Request.equals("updateNote")){
            DataInputStream from = null;
            int id = 0;
            try {
                from = new DataInputStream(s.getInputStream());
                id = (int) from.readInt();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            DataInputStream fromClient = null;
            int newNote = 0;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                newNote = (int) fromClient.readInt();
                fromClient.close();
                con.setNewNote(id, newNote);
            } catch (IOException | SQLException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        if(Request.equals("insertNote")){
            ObjectInputStream fromClient = null;
            ArrayList<Journal> newNote = new ArrayList<Journal>();
            try {
                fromClient = new ObjectInputStream(s.getInputStream());
                newNote = (ArrayList<Journal>) fromClient.readObject();
                fromClient.close();
                con.insertNote(newNote);
            } catch (IOException | ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(Request.equals("deleteNote")){
            DataInputStream fromClient = null;
            int id = 0;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                id = (int) fromClient.readInt();
                fromClient.close();
                 con.deleteNote(id);
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(Request.equals("sortNotes")){
           DataInputStream fromClient = null;
           DataInputStream fromClient1 = null;
           String column = null;
            int id = 0;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                column = (String) fromClient.readUTF();
                fromClient1 = new DataInputStream(s.getInputStream());
                id = (int) fromClient1.readInt();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                 out.writeObject(con.sortNotes(id, column));
                out.close();
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        
        
    //******************* Groups *******************   

        if(Request.equals("getGroupsList")){
           ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.getAllGroups());
                out.close();
            } catch (IOException | SQLException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(Request.equals("getStudentsByGroup")){
            DataInputStream fromClient = null;
            ObjectOutputStream out = null;
            String gpName = "";
            try {
                fromClient = new DataInputStream(s.getInputStream());
                gpName = (String) fromClient.readUTF();
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.getStudentsByGroup(gpName));
                out.close();
            } catch (IOException | SQLException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
               }  
        }
        
        if(Request.equals("sortGroups")){
           DataInputStream fromClient = null;
           String column = null;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                column = (String) fromClient.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.sortGroups(column));
                out.close();
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        if(Request.equals("getGroupInfo")){
            DataInputStream fromClient = null;
            String gpName = null;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                gpName = (String) fromClient.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.getGroupInfo(gpName));
                out.close();
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(Request.equals("getStudentInfo")){
            DataInputStream fromClient = null;
            int studID = 0;
            try {
                fromClient = new DataInputStream(s.getInputStream());
                studID = (int) fromClient.readInt();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.getStudentInfo(studID));
                out.close();
            } catch (IOException | SQLException | ParseException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    //******************* User *******************     
        if(Request.equals("changeUserInfo")){
            DataInputStream userIDFromClient = null;
            int userID = 0;
            int choose = 0;
            String newData = "";
            try {
                userIDFromClient = new DataInputStream(s.getInputStream());
                userID = (int) userIDFromClient.readInt();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            DataInputStream chooseFromClient = null;
            try {
                chooseFromClient = new DataInputStream(s.getInputStream());
                choose = (int) chooseFromClient.readInt();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
             DataInputStream newDataFromClient = null;
            try {
                newDataFromClient = new DataInputStream(s.getInputStream());
                newData = (String) newDataFromClient.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            } 
            if(choose == 1){
                try {
                    con.changeUsername(userID, newData);
                } catch (SQLException ex) {
                    Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(choose == 2){
                try {
                    con.changeLogin(userID, newData);
                } catch (SQLException ex) {
                    Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(choose == 3){
                try {
                    con.changePassword(userID, newData);
                } catch (SQLException ex) {
                    Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
        if(Request.equals("login")){
            DataInputStream log = null;
            DataInputStream pass = null;
            String login = "";
            String passWord = "";
            try {
                log = new DataInputStream(s.getInputStream());
                login = (String) log.readUTF();
                pass = new DataInputStream(s.getInputStream());
                passWord = (String) pass.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(con.LoginUser(login, passWord));
                out.close();
            } catch (IOException | SQLException ex) {
                Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("*ОТРИМАНА КОМАНДА: " + Request + "*"); 
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
      
}
public class Server {

    public static void main(String args[]) throws IOException, InterruptedException { 
        System.out.println("СЕРВЕР ГОТОВИЙ ДО РОБОТИ..."); 
        while(true){
            ServerSocket ss = new ServerSocket(11111); 
            Socket s = ss.accept();
            Multi t = new Multi(s);
            t.start();
            Thread.sleep(100);
            ss.close();
        }
    }   
}