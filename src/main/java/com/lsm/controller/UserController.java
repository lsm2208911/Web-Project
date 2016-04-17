package com.lsm.controller;

import com.lsm.domain.User;
import com.lsm.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/4/17.
 */

@Controller
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/index")
    public String index(Model model){

        User user = userService.getUserById(1);
        model.addAttribute("user",user);
        return "test";
    }
}
