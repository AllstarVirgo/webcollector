package com.potato.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String welcome() {
        return "hello world main controller";
    }
}
