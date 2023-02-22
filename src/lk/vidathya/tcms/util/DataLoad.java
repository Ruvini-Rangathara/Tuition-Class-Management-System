package lk.vidathya.tcms.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lk.vidathya.tcms.model.*;

import java.sql.SQLException;

public class DataLoad {



    public static void loadDays(ComboBox<String> cmbDay){
        ObservableList<String> days = FXCollections.observableArrayList();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");

        cmbDay.setItems(days);

    }

    public static void loadMonths(ComboBox<String> cmbMonth){
        ObservableList<String> months = FXCollections.observableArrayList();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

        cmbMonth.setItems(months);
    }

    public static void loadHallNo(ComboBox<String> cmbHallNo)  {

        try {
            ObservableList<String> hallNo = HallModel.loadHallNoToComboBox();
            cmbHallNo.setItems(hallNo);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    public static void loadTutorId(ComboBox<String> cmbTutorId)  {
        try {
            ObservableList<String> tutorId = TutorModel.loadTutorIdToComboBox();
            cmbTutorId.setItems(tutorId);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    public static void loadClassCode(ComboBox<String> cmbClassCode)  {
        try {
            ObservableList<String> classCode = ClassModel.loadClassCodeToComboBox();
            cmbClassCode.setItems(classCode);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    public static void loadGrade(ComboBox<String> cmbGrade)  {
        try {
            ObservableList<String> grade = ClassModel.loadGradeToComboBox();
            cmbGrade.setItems(grade);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    public static void loadSubject(ComboBox<String> cmbSubject)  {
        try {
            ObservableList<String> subject = TutorModel.loadSubjectToComboBox();
            cmbSubject.setItems(subject);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    public static void loadIncomeAndExpenditureToComboBox(ComboBox<String> cmbIncomeExpenditure) {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Income");
        options.add("Expenditure");

        cmbIncomeExpenditure.setItems(options);
    }

    public static void loadExtraClassCode(ComboBox cmbEClassCode) {
        try {
            ObservableList<String> eClassCode = ExtraClassModel.loadExtraClassCodeToComboBox();
            cmbEClassCode.setItems(eClassCode);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    public static void loadStaffId(ComboBox<String> cmbStaffId) {
        try {
            ObservableList<String> staffIds = StaffModel.loadStaffIdToComboBox();
            cmbStaffId.setItems(staffIds);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    public static void loadPaymentDescription(ComboBox<String> cmbDescription) {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Class Fee");
        options.add("Registration Fee");

        cmbDescription.setItems(options);
    }

    public static void loadGenderOptions(ComboBox<String> cmbGender) {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Male");
        options.add("Female");
        cmbGender.setItems(options);
    }

    public static void loadRecipientGroup(ComboBox cmbGroupRecipient) {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("To Staff");
        options.add("To Tutors");
        options.add("To Students");
        options.add("To Parents");
        cmbGroupRecipient.setItems(options);
    }
}
