package com.utry.dbs.models;

import java.io.Serializable;
import java.util.Date;

public class ScoreBean implements Serializable {

    private String scoreId;

    private String scoreAidId;

    private String scoreContent;

    private Date createTime;

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public String getScoreAidId() {
        return scoreAidId;
    }

    public void setScoreAidId(String scoreAidId) {
        this.scoreAidId = scoreAidId;
    }

    public String getScoreContent() {
        return scoreContent;
    }

    public void setScoreContent(String scoreContent) {
        this.scoreContent = scoreContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
