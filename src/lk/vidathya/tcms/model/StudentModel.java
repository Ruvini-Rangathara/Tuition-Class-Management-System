package lk.vidathya.tcms.model;

import lk.vidathya.tcms.transferObject.Student;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentModel {

    public static String getLastStudentId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT studentId FROM Student ORDER BY studentId DESC LIMIT 1");
        if(result.next()){
            return result.getString("studentId");
        }
        return null;
    }

    public static Student getStudentDetails(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Student WHERE studentId=?",id);
        if(result.next()){
           Student student = new Student(
                    result.getString("studentId"),
                    result.getString("name"),
                    result.getString("nic"),
                    result.getString("gender"),
                    result.getString("address"),
                    result.getString("contactNo"),
                    result.getString("email"),
                    result.getString("dob"),
                    result.getInt("age"),
                    result.getString("grade"),
                    result.getString("date"),
                    result.getString("guardianNic")
            );
            return student;
        }
        return null;
    }

    public static int getStudentCount() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT studentId FROM Student");
        int count=0;
        while(result.next()){
            count++;
        }
        return count;
    }


    public static String getStudentMailAddress(String studentId) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT email FROM Student WHERE studentId=?", studentId);

        if(result.next()){
            return result.getString("email");
        }
        return null;
    }

    public static ArrayList<String> getAllEmailAddress() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT email FROM Student");

        ArrayList<String> allMailAddress = new ArrayList<>();
        while(result.next()){
            allMailAddress.add(result.getString("email"));
        }
        return allMailAddress;
    }

    public static String getGuardianNic(String studentId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT guardianNic FROM Student WHERE studentId=?",studentId);
        if(resultSet.next()){
            return resultSet.getString("guardianNic");
        }
        return null;
    }

    public static boolean addStudent(Student student) throws SQLException, ClassNotFoundException {
        boolean isAdd=CrudUtil.execute("INSERT INTO Student VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",
                student.getStudentId(),
                student.getName(),
                student.getNic(),
                student.getGender(),
                student.getAddress(),
                student.getContactNo(),
                student.getEmail(),
                student.getDob(),
                student.getAge(),
                student.getGrade(),
                student.getDate(),
                student.getGuardianNic()
        );
        return isAdd;
    }

    public static boolean updateStudent(Student student) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE Student SET name=?, nic=?, gender=?, address=?, contactNo=?, email=?, dob=?, age=?, grade=?, guardianNic=? WHERE studentId=?",
                student.getName(),
                student.getNic(),
                student.getGender(),
                student.getAddress(),
                student.getContactNo(),
                student.getEmail(),
                student.getDob(),
                student.getAge(),
                student.getGrade(),
                student.getGuardianNic(),
                student.getStudentId()
                );
        return isUpdate;
    }

    public static boolean isExistImage(String studentId) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT studentId FROM StudentImage");

        while (result.next()){
            if(result.getString("studentId").equals(studentId)){
                return true;
            }
        }
        return false;
    }

    public static ResultSet getImage(String studentId) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT image FROM StudentImage Where studentId=?",studentId);
        return result;

    }
}
