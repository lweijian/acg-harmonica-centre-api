package com.acg.harmonica;

import com.acg.harmonica.service.SheetMusicService;

import com.acg.harmonica.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class HarmonicaCentreApplicationTests {

    @Resource
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        redisUtil.set("test","value");
    }

}


