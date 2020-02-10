package com.chenglong.dbs.service;

import com.chenglong.dbs.models.ScoreBean;

public interface ScoreService {
    void add(ScoreBean scoreBean);

    ScoreBean find(String scoreAidId);
}
