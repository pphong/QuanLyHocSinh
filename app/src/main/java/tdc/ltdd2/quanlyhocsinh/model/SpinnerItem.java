package tdc.ltdd2.quanlyhocsinh.model;

public class SpinnerItem {
    private String className;
    private int classImage;
    public SpinnerItem(String countryName, int flagImage) {
        className = countryName;
        classImage = flagImage;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassImage() {
        return classImage;
    }

    public void setClassImage(int classImage) {
        this.classImage = classImage;
    }
}
