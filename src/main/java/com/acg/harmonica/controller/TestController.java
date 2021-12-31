package com.acg.harmonica.controller;

import com.acg.harmonica.service.TestService;
import com.acg.harmonica.utils.JsonUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class TestController {

    @GetMapping(value = "test")
    public String savePicByFormData() {
        return "test";
    }}