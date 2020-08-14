package tdc.ltdd2.quanlyhocsinh.model;

class Point {
    private String pointSubject;
    private String pointPoint;

    public Point() {
    }

    public Point(String pointSubject, String pointPoint) {
        this.pointSubject = pointSubject;
        this.pointPoint = pointPoint;
    }

    public String getPointSubject() {
        return pointSubject;
    }

    public void setPointSubject(String pointSubject) {
        this.pointSubject = pointSubject;
    }

    public String getPointPoint() {
        return pointPoint;
    }

    public void setPointPoint(String pointPoint) {
        this.pointPoint = pointPoint;
    }
}
