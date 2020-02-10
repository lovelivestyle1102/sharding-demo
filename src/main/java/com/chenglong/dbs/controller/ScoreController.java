package com.chenglong.dbs.controller;

import com.chenglong.dbs.models.ScoreBean;
import com.chenglong.dbs.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

     @Autowired
     private ScoreService scoreService;


     @PostMapping("/add")
     public String add(@RequestBody ScoreBean scoreBean){
          scoreService.add(scoreBean);

          return "Success";
     }

     @GetMapping("/find")
     public ScoreBean find(String scoreAidId){
         ScoreBean scoreBean = scoreService.find(scoreAidId);
         return scoreBean;
     }
}
