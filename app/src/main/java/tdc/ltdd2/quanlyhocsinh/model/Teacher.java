package tdc.ltdd2.quanlyhocsinh.model;

public class Teacher {
    private int teacherId;
    private String teacherName;
    private String teacherClass;
    private String teacherGender;
    private String teacherBirth;

    public Teacher(){}

    public Teacher(int teacherId, String teacherName, String teacherClass, String teacherGender, String teacherBirth) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherClass = teacherClass;
        this.teacherGender = teacherGender;
        this.teacherBirth = teacherBirth;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherClass() {
        return teacherClass;
    }

    public void setTeacherClass(String teacherClass) {
        this.teacherClass = teacherClass;
    }

    public String getTeacherGender() {
        return teacherGender;
    }

    public String getTeacherBirth() {
        return teacherBirth;
    }

    public void setTeacherBirth(String teacherBirth) {
        this.teacherBirth = teacherBirth;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
