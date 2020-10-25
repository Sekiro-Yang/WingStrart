package main.entity;

public class Founder {
    private String founderName;
    private String password;
    private String sex;
    private int age;
    private String founderId;
    private String email;
    private int state;
    private String code;
    private int projectNum;

    public Founder() {
    }

    public Founder(String founderName, String password, String sex, int age, String founderId, String email, int state, String code) {
        this.founderName = founderName;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.founderId = founderId;
        this.email = email;
        this.state = state;
        this.code = code;
    }

    public String getFounderName() {
        return founderName;
    }

    public void setFounderName(String founderName) {
        this.founderName = founderName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFounderId() {
        return founderId;
    }

    public void setFounderId(String founderId) {
        this.founderId = founderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(int projectNum) {
        this.projectNum = projectNum;
    }
}
