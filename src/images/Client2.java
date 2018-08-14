/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images;
import java.io.DataInputStream;
import java.io.DataOutputStream; 
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import DOMAIN.Student;


public class Client2 {
    static Socket socketConnection;
    public static void main(String[] arg) throws IOException {
        ArrayList<Student> students = new  ArrayList<Student>();
        try {
             socketConnection = new Socket("127.0.0.1", 11111);
            DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
            String SQL = "2";
            outToServer.writeUTF(SQL);
        } catch (Exception e) {System.out.println(e); }
         try {
                ObjectInputStream objectInput = new ObjectInputStream(socketConnection.getInputStream()); //Error Line!
                try {
                    students =  (ArrayList<Student>) objectInput.readObject();
                    //Display(students);
                    for(int i = 0; i < students.size(); i++)
                    {
                        System.out.println(students.get(i).getLastName() + " " 
                                + students.get(i).getFirstName() + " " + students.get(i).getStudyForm());
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("The title list has not come from the server");
                    e.printStackTrace();
                }
            } catch (IOException e) {
                System.out.println("The socket for reading the object has problem");
                e.printStackTrace();
            }           
       // DataInputStream infromClient = new DataInputStream(socketConnection.getInputStream());
       // String response = (String) infromClient.readUTF();
       // System.out.println(response);
    }
}