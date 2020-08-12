package tdc.ltdd2.quanlyhocsinh.model;

public class Teachers {
    private int teachersId,classId;
    private String teachersName;
    private String teachersGender;
    private String teachersBirth;

    public Teachers(int teachersId, int classId, String teachersName, String teachersGender, String teachersBirth) {
        this.teachersId = teachersId;
        this.classId = classId;
        this.teachersName = teachersName;
        this.teachersGender = teachersGender;
        this.teachersBirth = teachersBirth;
    }

    public int getTeachersId() {
        return teachersId;
    }

    public void setTeachersId(int teachersId) {
        this.teachersId = teachersId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getTeachersName() {
        return teachersName;
    }

    public void setTeachersName(String teachersName) {
        this.teachersName = teachersName;
    }

    public String getTeachersGender() {
        return teachersGender;
    }

    public void setTeachersGender(String teachersGender) {
        this.teachersGender = teachersGender;
    }

    public String getTeachersBirth() {
        return teachersBirth;
    }

    public void setTeachersBirth(String teachersBirth) {
        this.teachersBirth = teachersBirth;
    }

    @Override
    public String toString() {
        return "Teachers{" +
                "teachersId=" + teachersId +
                ", classId=" + classId +
                ", teachersName='" + teachersName + '\'' +
                ", teachersGender='" + teachersGender + '\'' +
                ", teachersBirth='" + teachersBirth + '\'' +
                '}';
    }
}
