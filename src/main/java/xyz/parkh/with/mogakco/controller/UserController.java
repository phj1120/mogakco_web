package xyz.parkh.with.mogakco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.parkh.with.mogakco.entity.User;
import xyz.parkh.with.mogakco.service.UserService;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getUserList(Model model){
        List<User> userList = userService.getUserListAll();
        model.addAttribute("userList", userList);
        return "user";
    }

}
