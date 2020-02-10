package com.chenglong.dbs.service.impl;

import com.chenglong.dbs.dao.ScoreDao;
import com.chenglong.dbs.models.ScoreBean;
import com.chenglong.dbs.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {


    @Autowired
    private ScoreDao scoreDao;


    @Override
    public void add(ScoreBean scoreBean) {
        scoreDao.insert(scoreBean.getScoreId(),scoreBean.getScoreAidId(),scoreBean.getScoreContent(),scoreBean.getCreateTime());
    }

    @Override
    public ScoreBean find(String scoreAidId) {
        return scoreDao.selectByAidId(scoreAidId);
    }
}
