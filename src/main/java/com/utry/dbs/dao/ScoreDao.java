package com.utry.dbs.dao;

import com.utry.dbs.models.ScoreBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;

@Mapper
public interface ScoreDao {

    @Insert("insert into t_score(score_id,score_aid_id,score_content,create_time) values (#{scoreId},#{scoreAidId},#{scoreContent},#{createTime})")
    void insert(@Param("scoreId") String scoreId,@Param("scoreAidId") String scoreAidId,@Param("scoreContent") String scoreContent,@Param("createTime") Date createTime);

    @Select("select score_id as scoreId,score_aid_id as scoreAidId,score_content as scoreContent,create_time as createTime from t_score where score_aid_id = #{scoreAidId}")
    ScoreBean selectByAidId(@Param("scoreAidId") String scoreAidId);
}
