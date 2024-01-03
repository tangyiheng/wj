package com.tangyiheng.wj.controller;

import com.tangyiheng.wj.service.UserService;
import com.tangyiheng.wj.entity.User;
import com.tangyiheng.wj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassword());
        if (null == user) {
            return Result.builder().code(400).build();
        } else {
            return Result.builder().code(200).build();
        }
    }
}
