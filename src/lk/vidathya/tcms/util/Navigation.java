package lk.vidathya.tcms.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage)Navigation.pane.getScene().getWindow();
        window.setTitle("Vidathya Higher Education Centre");
        window.setResizable(false);

        switch (route) {
            case ADDCLASS:
                initUI("AddClassForm.fxml");
                break;

            case ADDEXTRACLASS:
                initUI("AddExtraClass.fxml");
                break;

            case ADDSTUDENTTOCLASSES:
                initUI("AddStudentToClasses.fxml");
                break;

            case ATTENDANCE:
                initUI("AttendanceForm.fxml");
                break;

            case CLASSFEEINFO:
                initUI("ClassFeeInfo.fxml");
                break;

            case DASHBOARDADMIN:
                initUI("DashboardAdmin.fxml");
                break;

            case DASHBOARDASSISTANT:
                initUI("DashboardAssistant.fxml");
                break;

            case DASHBOARDVIEW:
                initUI("DashboardView.fxml");
                break;

            case FINANCEMANAGE:
                initUI("FinanceManage.fxml");
                break;

            case HALL:
                initUI("HallForm.fxml");
                break;

            case INCOMEANDEXPENDITURE:
                initUI("IncomeAndExpenditureForm.fxml");
                break;

            case LOGIN:
                initUI("LoginForm.fxml");
                break;

            case MAIL :
                initUI("MailForm.fxml");
                break;

            case MANAGECLASSES:
                initUI("ManageClassesForm.fxml");
                break;

            case MANAGEEXTRACLASSES:
                initUI("ManageExtraClass.fxml");
                break;

            case MANAGEUSER:
                initUI("ManageUserForm.fxml");
                break;

            case NEWUSER:
                initUI("NewUserForm.fxml");
                break;

            case PAYMENTS:
                initUI("PaymentsForm.fxml");
                break;

            case PAYSTAFFSALARIES:
                initUI("PayStaffSalaries.fxml");
                break;

            case PAYTUTORSALARIES:
                initUI("PayTutorSalaries.fxml");
                break;

            case REFUNDS:
                initUI("RefundsForm.fxml");
                break;

            case REPORTS:
                initUI("ReportForm.fxml");
                break;

            case REPORTSTAFFINFO:
                initUI("ReportStaffInfo.fxml");
                break;

            case REPORTTUTORINFO:
                initUI("ReportTutorInfo.fxml");
                break;

            case STAFFMANAGE:
                initUI("StaffManageForm.fxml");
                break;

            case STAFFREGISTRATION:
                initUI("StaffRegistrationForm.fxml");
                break;

            case STAFFSALARYINFO:
                initUI("StaffSalaryInfo.fxml");
                break;

            case STUDENTMANAGE:
                initUI("StudentManageForm.fxml");
                break;

            case STUDENTREGISTRATION:
                initUI("StudentRegistrationForm.fxml");
                break;

            case TUTORMANAGE:
                initUI("TutorManageForm.fxml");
                break;

            case TUTORREGISTRATION:
                initUI("TutorRegistrationForm.fxml");
                break;

            case VIEWATTENDANCE:
                initUI("ViewAttendance.fxml");
                break;

            case VIEWCLASSESINFO:
                initUI("ViewClassesInfo.fxml");
                break;

            case VIEWCLASSFEEPAYMENTS:
                initUI("ViewClassFeePayments.fxml");
                break;

            case VIEWCLASSSTUDENTS:
                initUI("ViewClassStudent.fxml");
                break;

            case VIEWEXTRACLASSES:
                initUI("ViewExtraClasses.fxml");
                break;

            case VIEWGUARDIANINFO:
                initUI("ViewGuardianInfo.fxml");
                break;

            case VIEWHALLINFO:
                initUI("ViewHallInfo.fxml");
                break;

            case VIEWHALLRESERVATIONS:
                initUI("ViewHallReservations.fxml");
                break;

            case VIEWINFOCONTEXT:
                initUI("ViewInfoContextForm.fxml");
                break;

            case VIEWINFORMATION:
                initUI("ViewInformationForm.fxml");
                break;

            case VIEWREFUNDS:
                initUI("ViewRefunds.fxml");
                break;

            case VIEWREGISTRATIONPAYMENTS:
                initUI("ViewRegistrationPayments.fxml");
                break;

            case VIEWSTAFFINFO:
                initUI("ViewStaffInfo.fxml");
                break;

            case VIEWSTAFFSALARY:
                initUI("ViewStaffSalary.fxml");
                break;

            case VIEWSTUDENTINFO:
                initUI("ViewStudentInfo.fxml");
                break;

            case VIEWSTUDENTSCLASSES:
                initUI("ViewStudentsClasses.fxml");
                break;

            case VIEWTUTORSINFO:
                initUI("ViewTutorsInfo.fxml");
                break;

            case VIEWTUTORSSALARY:
                initUI("ViewTutorsSalary.fxml");
                break;

            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/vidathya/tcms/view/" + location)));

    }

}
