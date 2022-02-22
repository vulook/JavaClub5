package com.softserve.Controllers;

import com.softserve.entity.User;
import com.softserve.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "fancy-login";
    }


    @GetMapping("/access-denied")
    public String showAccessDenied() {
        Long id = userService.getId();
        User user = userService.getAll().stream().filter(x->x.getId()==id).findFirst().orElse(null);
//                getReaders().stream().filter(x->x.getId()==id).findFirst().orElse(null);
        if (user.getRole().getRole().equals("ROLE_Reader")){
            return "access-denied";
        }
        return "admin-denied";
    }

}









