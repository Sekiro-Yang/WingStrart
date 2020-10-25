package main.entity;

public class Project {
    private String founderId;
    private String projectId;
    private String projectName;
    private String founderName;
    private int projectSheave;
    private int projectRight;
    private int endSheave;

    public String getFounderId() {
        return founderId;
    }

    public void setFounderId(String founderId) {
        this.founderId = founderId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFounderName() {
        return founderName;
    }

    public void setFounderName(String founderName) {
        this.founderName = founderName;
    }

    public int getProjectSheave() {
        return projectSheave;
    }

    public void setProjectSheave(int projectSheave) {
        this.projectSheave = projectSheave;
    }

    public int getProjectRight() {
        return projectRight;
    }

    public void setProjectRight(int projectRight) {
        this.projectRight = projectRight;
    }

    public int getEndSheave() {
        return endSheave;
    }

    public void setEndSheave(int endSheave) {
        this.endSheave = endSheave;
    }
}
