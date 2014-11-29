package cn.sdu.wh.coursecool.Domain;

/**
 * 该类为通选课类
 * Created by root on 14-11-23.
 */
public class Course {
    private String semester;//学期
    private String college;//大学
    private String professional;//专业
    private String coursename;//课程名
    private String courseteacher;//课程老师名
    private String coursenum ;//课程号
    private String classnum;//课序号
    private String classweek;//上课星期
    private String classsection;//上课节次
    private String classatr;//课程属性

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCourseteacher() {
        return courseteacher;
    }

    public void setCourseteacher(String courseteacher) {
        this.courseteacher = courseteacher;
    }

    public String getCoursenum() {
        return coursenum;
    }

    public void setCoursenum(String coursenum) {
        this.coursenum = coursenum;
    }

    public String getClassnum() {
        return classnum;
    }

    public void setClassnum(String classnum) {
        this.classnum = classnum;
    }

    public String getClassweek() {
        return classweek;
    }

    public void setClassweek(String classweek) {
        this.classweek = classweek;
    }

    public String getClasssection() {
        return classsection;
    }

    public void setClasssection(String classsection) {
        this.classsection = classsection;
    }

    public String getClassatr() {
        return classatr;
    }

    public void setClassatr(String classatr) {
        this.classatr = classatr;
    }
}
