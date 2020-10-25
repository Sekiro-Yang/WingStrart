package main.entity;

public class Competitor {
    private String competitorAccountNum;
    private String competitorName;
    private String competitorPassword;
    private String projectId;
    private int competitorVotesNum;
    private int competitorRight;

    public String getCompetitorAccountNum() {
        return competitorAccountNum;
    }

    public void setCompetitorAccountNum(String competitorAccountId) {
        this.competitorAccountNum = competitorAccountId;
    }

    public String getCompetitorName() {
        return competitorName;
    }

    public void setCompetitorName(String competitorName) {
        this.competitorName = competitorName;
    }

    public String getCompetitorPassword() {
        return competitorPassword;
    }

    public void setCompetitorPassword(String competitorPassword) {
        this.competitorPassword = competitorPassword;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getCompetitorVotesNum() {
        return competitorVotesNum;
    }

    public void setCompetitorVotesNum(int competitorVotesNum) {
        this.competitorVotesNum = competitorVotesNum;
    }

    public int getCompetitorRight() {
        return competitorRight;
    }

    public void setCompetitorRight(int competitorRight) {
        this.competitorRight = competitorRight;
    }
}
