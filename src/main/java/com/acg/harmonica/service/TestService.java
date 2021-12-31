package com.acg.harmonica.service;

import com.acg.harmonica.dao.TestDao;
import com.acg.harmonica.entity.Test;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class TestService {
    @Autowired
    TestDao dao;
    public String savePicByFormData(MultipartFile file) throws IOException {
        // 图片存储路径
        String path = "E:\\项目\\网页设计大赛\\acg-harmonica-centre-api\\src\\main\\resources\\static\\img\\uploadImage";
        // 判断是否有路径
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
//        随机生成名字
        String fileName = UUID.randomUUID().toString().replace("-","") + ".png";
        File tempFile = new File(path,fileName);
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        file.transferTo(tempFile);
        String filePath=path+"/"+fileName;
        String dbPath=filePath
                .replace("\\", "/")
                .replace("E:/项目/网页设计大赛/acg-harmonica-centre-api/src/main/resources/static/", "http://localhost:3000/");
        System.out.println(dbPath);
        Test test=new Test();
        test.setImgsrc(dbPath);
        dao.insert(test);
        return dbPath;
    }
}
