package ku.cs.model;

import java.time.LocalDateTime;

public class Complaint {

    private String headLineName;

    private String detail;

    private String category;

    private int scoreVote;

    private LocalDateTime dateTime;

    public Complaint(String headLineName,String detail,String category,int scoreVote){
        this(headLineName,detail,category,LocalDateTime.of(0,1,1,0,0),scoreVote);
    }

    public Complaint(String headLineName, String detail, String category, LocalDateTime dateTime ,int scoreVote) {
        this.headLineName = headLineName;
        this.detail = detail;
        this.category = category;
        this.scoreVote = scoreVote;
        this.dateTime = dateTime;
    }

    public String getHeadLineName() {
        return headLineName;
    }

    public String getDetail() {
        return detail;
    }

    public String getCategory() {
        return category;
    }

    public int getScoreVote() {
        return scoreVote;
    }

    public LocalDateTime getLastLogin() {
        return dateTime;
    }

    public void setHeadLineName(String headLineName) {
        this.headLineName = headLineName;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setScoreVote(int scoreVote) {
        this.scoreVote = scoreVote;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "headLineName='" + headLineName + '\'' +
                ", detail='" + detail + '\'' +
                ", category='" + category + '\'' +
                ", scoreVote=" + scoreVote +'\'' +
                ", lastLogin=" + dateTime +
                '}';
    }

    public String toCsv(){
        return "Complaint" + "," + headLineName + "," + detail + "," + category + "," +scoreVote +  ","+ dateTime;
    }
}
