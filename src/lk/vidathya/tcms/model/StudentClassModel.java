package lk.vidathya.tcms.model;

import lk.vidathya.tcms.transferObject.StudentClass;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentClassModel {

    public static ArrayList<String> getDetails(String classCode) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT studentId FROM StudentClass WHERE classCode=?",classCode);
        ArrayList<String> studentIdArray = new ArrayList<>();
        while(result.next()){
            studentIdArray.add(result.getString("studentId"));
        }
        return studentIdArray;
    }

    public static ArrayList<String> getGuardianNic(String classCodes) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT guardianNic FROM StudentClass WHERE classCode=?",classCodes);
        ArrayList<String> guardianNicArray = new ArrayList<>();
        while(result.next()){
            guardianNicArray.add(result.getString("guardianNic"));
        }
        return guardianNicArray;
    }

    public static boolean addData(StudentClass studentClass) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO StudentClass VALUES (?,?,?,?)",
                studentClass.getStudentId(),
                studentClass.getClassCode(),
                studentClass.getGuardianNic(),
                studentClass.getDate()
                );
        return isAdd;
    }

    public static ResultSet getInfo(String studentId) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM StudentClass WHERE studentId=?", studentId);
        return result;
    }

    public static boolean existStudent(String classCode, String studentId) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT studentId FROM StudentClass WHERE classCode=?", classCode);
        ArrayList<String> studentListArray = new ArrayList<>();
        while (result.next()){
            studentListArray.add(result.getString("studentId"));
        }
        for(String id : studentListArray){
            if(studentId.equals(id)){
                return true;
            }
        }
        return false;
    }

    public static ResultSet getAllData(String classCode) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM StudentClass WHERE classCode=? ORDER BY studentId ASC",classCode);
        return result;
    }

    public static ArrayList<String> getClassesStudents(String classCode) throws SQLException, ClassNotFoundException {
        ArrayList<String> studentIdArray = new ArrayList<>();
        ResultSet result = CrudUtil.execute("SELECT studentId FROM StudentClass WHERE classCode=? ORDER BY studentId ASC", classCode);

        while (result.next()){
            studentIdArray.add(result.getString("studentId"));
        }
        return studentIdArray;
    }

    public static boolean isExist(String studentId, String classCode) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT classCode FROM StudentClass WHERE studentId=? ",studentId);

        while(result.next()){
            if(classCode.equals(result.getString("classCode"))){
                return true;
            }
        }
        return false;
    }

    
}
