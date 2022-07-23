package com.test.mybatis.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Api()
public class JSPController {
    @ApiOperation(value = "测试/test1", notes = "测试jsp页面")
    @GetMapping("/test1")
    public ModelAndView test1(){
        ModelAndView view = new ModelAndView("hello"); // 转向hello.jsp页面
        view.addObject("userName", "test1");  // 传入参数
        return view;
    }

    @GetMapping("/test2")
    public String test2(Model model){
        model.addAttribute("userName", "test2");
        return "hello";

    }
}
