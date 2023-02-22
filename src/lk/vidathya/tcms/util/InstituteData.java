package lk.vidathya.tcms.util;

import javafx.scene.control.Alert;
import lk.vidathya.tcms.model.HallModel;
import lk.vidathya.tcms.model.StudentModel;
import lk.vidathya.tcms.model.TutorModel;

import java.sql.SQLException;

public class InstituteData {

    private  static double registrationFee = 1000.00;
    private  static double salaryRate = 20.00;
    private  static String email = "vidathyaInstitute@gmail.com";
    private static int studentCount=0;
    private static int tutorCount=0;
    private static int hallCount=0;
    private static String contactNo="0705811994";
    private static String whatsAppContactNo="0786628489";
    private static String address="No:48,Galle Road,Panadura";


    public InstituteData() {
    }


    public static double getRegistrationFee() {
        return registrationFee;
    }

    public static void setRegistrationFee(double registrationFee) {
        InstituteData.registrationFee = registrationFee;
    }

    public static double getSalaryRate() {
        return salaryRate;
    }

    public static void setSalaryRate(double salaryRate) {
        InstituteData.salaryRate = salaryRate;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        InstituteData.email = email;
    }


    public static int getStudentCount() {
        return studentCount;
    }

    public static void setStudentCount() {
        try {
            studentCount = StudentModel.getStudentCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    public static int getTutorCount() {
        return tutorCount;
    }

    public static void setTutorCount() {
        try {
            tutorCount = TutorModel.getTutorCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    public static int getHallCount() {
        return hallCount;
    }

    public static void setHallCount() {
        try {
            hallCount = HallModel.getHallCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    public static String getContactNo() {
        return contactNo;
    }

    public static void setContactNo(String contactNo) {
        InstituteData.contactNo = contactNo;
    }

    public static String getWhatsAppContactNo() {
        return whatsAppContactNo;
    }

    public static void setWhatsAppContactNo(String whatsAppContactNo) {
        InstituteData.whatsAppContactNo = whatsAppContactNo;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        InstituteData.address = address;
    }
}
