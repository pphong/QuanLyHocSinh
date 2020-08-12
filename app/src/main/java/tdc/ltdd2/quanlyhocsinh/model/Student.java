package tdc.ltdd2.quanlyhocsinh.model;

public class Student {
    private int studentId;
    private String studentName;
    private String studentClass;
    private String studentGender;
    private String studentBirth;

    public Student(){}

    public Student(int studentId, String studentName, String studentClass, String studentGender, String studentBirth) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.studentGender = studentGender;
        this.studentBirth = studentBirth;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public String getStudentBirth() {
        return studentBirth;
    }

    public void setStudentBirth(String studentBirth) {
        this.studentBirth = studentBirth;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
