package lk.vidathya.tcms.model;

import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.transferObject.Guardian;
import lk.vidathya.tcms.transferObject.IncomeAndExpenditure;
import lk.vidathya.tcms.transferObject.Student;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuardianModel {

    public static Guardian getGuardianDetails(String nic) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Guardian WHERE guardianNic=?",nic);
        if(result.next()){
            Guardian guardian = new Guardian(
                    result.getString("guardianNic"),
                    result.getString("name"),
                    result.getString("contactNo"),
                    result.getString("email"),
                    result.getString("occupation")
            );
            return guardian;
        }
        return null;

    }

    public static String GuardianMailAddress(String guardianNic) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT email FROM Guardian WHERE guardianNic=?", guardianNic);
        if(result.next()){
            return result.getString("email");
        }
        return null;
    }

    public static ArrayList<String> getAllEmailAddress() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT email FROM Guardian");

        ArrayList<String> allMails = new ArrayList<>();
        while(result.next()){
            allMails.add(result.getString("email"));
        }
        return allMails;
    }

    public static boolean addGuardian(Guardian guardian, Student student) throws SQLException, ClassNotFoundException {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);

            ArrayList<String> guardianNic=new ArrayList<>();
            ResultSet result = CrudUtil.execute("SELECT guardianNic FROM Guardian");
            while (result.next()){
                guardianNic.add(result.getString("guardianNic"));
            }
            boolean b=false;
            for(String nic : guardianNic){
                if(nic.equals(guardian.getGuardianNic())){
                    b=true;
                }
            }
            if(!b){
                CrudUtil.execute("INSERT INTO Guardian VALUES(?,?,?,?,?)",
                        guardian.getGuardianNic(),
                        guardian.getName(),
                        guardian.getContactNo(),
                        guardian.getEmail(),
                        guardian.getOccupation()
                );
            }

            boolean isAddStudent = StudentModel.addStudent(student);
            if(isAddStudent) {
                DBConnection.getInstance().getConnection().commit();
                return true;
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }


    public static boolean updateGuardian(Student student, Guardian guardian) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isUpdateGuardian = CrudUtil.execute("UPDATE Guardian SET name=?, contactNo=?, email=?, occupation=? WHERE  guardianNic=?",
                    guardian.getName(),
                    guardian.getContactNo(),
                    guardian.getEmail(),
                    guardian.getOccupation(),
                    guardian.getGuardianNic()
            );

            if (isUpdateGuardian) {
                boolean isUpdateStudent = StudentModel.updateStudent(student);
                if (isUpdateStudent) {
                    DBConnection.getInstance().getConnection().commit();
                    return true;
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }


}
