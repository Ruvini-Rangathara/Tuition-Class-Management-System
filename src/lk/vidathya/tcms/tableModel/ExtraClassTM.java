package lk.vidathya.tcms.tableModel;

import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExtraClassTM {
    private String eCode;
    private String classCode;
    private String subject;
    private String grade;
    private String tutorName;
    private String date;

    public ExtraClassTM() {
    }

    public ExtraClassTM(String eCode, String classCode, String subject, String grade, String tutorName, String date) {
        this.eCode = eCode;
        this.classCode = classCode;
        this.subject = subject;
        this.grade = grade;
        this.tutorName = tutorName;
        this.date = date;
    }

    public String getECode() {
        return eCode;
    }

    public void setECode(String eCode) {
        this.eCode = eCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
