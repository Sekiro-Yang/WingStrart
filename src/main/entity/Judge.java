package main.entity;

public class Judge {
    private String judgeName;
    private String judgeAccountNum;
    private String judgePassword;
    private String projectId;
    private String projectName;
    private int judgeVotesNum;
    private int judgeRight;

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public String getJudgeAccountNum() {
        return judgeAccountNum;
    }

    public void setJudgeAccountNum(String judgeAccountNum) {
        this.judgeAccountNum = judgeAccountNum;
    }

    public String getJudgePassword() {
        return judgePassword;
    }

    public void setJudgePassword(String judgePassword) {
        this.judgePassword = judgePassword;
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

    public int getJudgeVotesNum() {
        return judgeVotesNum;
    }

    public void setJudgeVotesNum(int judgeVotesNum) {
        this.judgeVotesNum = judgeVotesNum;
    }

    public int getJudgeRight() {
        return judgeRight;
    }

    public void setJudgeRight(int judgeRight) {
        this.judgeRight = judgeRight;
    }
}
