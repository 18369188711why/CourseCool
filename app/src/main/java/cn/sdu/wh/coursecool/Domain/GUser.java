package cn.sdu.wh.coursecool.Domain;

/**
 * 用户类
 * Created by shukang on 14-11-29.
 */
public class GUser {
    private String userid;//用户id
    private String username;//学号（用户名）
    private String password;//密码
    private String uname;//姓名
    private String nickname;//昵称
    private String specialty;//专业
    private int grade;//年级 1，2，3，4
    private String lcids;//获取用户喜欢课程列表，格式：,课程id#课程名,课程id#课程名
    private String touxiangsrc;//web头像路径
    private int hidespe;//获取用户是否隐藏专业 1为隐藏 0或null为没有隐藏
    private int hidegrade;// 获取用户是否隐藏年级
    private String zanplid;//获取用户赞过的评论，格式：,cai或ding#评论的昵称#评论的内容

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getLcids() {
        return lcids;
    }

    public void setLcids(String lcids) {
        this.lcids = lcids;
    }

    public String getTouxiangsrc() {
        return touxiangsrc;
    }

    public void setTouxiangsrc(String touxiangsrc) {
        this.touxiangsrc = touxiangsrc;
    }

    public int getHidespe() {
        return hidespe;
    }

    public void setHidespe(int hidespe) {
        this.hidespe = hidespe;
    }

    public int getHidegrade() {
        return hidegrade;
    }

    public void setHidegrade(int hidegrade) {
        this.hidegrade = hidegrade;
    }

    public String getZanplid() {
        return zanplid;
    }

    public void setZanplid(String zanplid) {
        this.zanplid = zanplid;
    }
}
