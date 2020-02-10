package com.utry.dbs.service;

import com.utry.dbs.models.ScoreBean;

public interface ScoreService {
    void add(ScoreBean scoreBean);

    ScoreBean find(String scoreAidId);
}
