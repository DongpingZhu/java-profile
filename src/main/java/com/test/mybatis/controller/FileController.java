package com.test.mybatis.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;


@RestController
public class FileController {

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("hello"); // 转向hello.jsp页面
        view.addObject("userName", "jsp页面");  // 传入参数
        return view;
    }

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @SneakyThrows
    @PostMapping("/upload")
    public String upload(MultipartFile file){
        if(file.isEmpty()){
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\zhudongping\\Desktop\\upload";
        File dest = new File(filePath, fileName);
        file.transferTo(dest);
        return "上传成功";

    }
    @GetMapping("/multiUpload")
    public String multiUpload(){
        return "multiUpload";
    }
    @SneakyThrows
    @PostMapping("/multiUpload")
    public String multiUpload(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filePath = "C:\\Users\\zhudongping\\Desktop\\upload";
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if(file.isEmpty()){
                return "上传多个文件失败";
            }
            String originalFilename = file.getOriginalFilename();
            File dest = new File(filePath, originalFilename);
            file.transferTo(dest);
        }
        return "上传多个文件成功";
    }
}
