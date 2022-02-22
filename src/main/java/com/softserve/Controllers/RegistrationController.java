package com.softserve.Controllers;

import com.softserve.entity.User;
import com.softserve.services.UserService;
import com.softserve.validation.ValidationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;


    List<String> list = new ArrayList<>();
    private final Logger logger = Logger.getLogger(getClass().getName());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("registrationError",list);
        theModel.addAttribute("validationForm", new ValidationForm());
        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("validationForm") ValidationForm theValidationForm,
            BindingResult theBindingResult,
            Model theModel) {
        String userEmail = theValidationForm.getEmail();
        logger.info("Processing registration form for: " + userEmail);
        if (theBindingResult.hasErrors()) {
            return "registration-form";
        }

        User existing = userService.findByUserEmail(userEmail);
        if (existing != null) {
            list.add("User with this email already exists!");
            theModel.addAttribute("validationForm", new ValidationForm());
            theModel.addAttribute("registrationError",list);
            logger.warning("User name already exists.");
            return "registration-form";
        }
        // create user account
        userService.save(theValidationForm);
        logger.info("Successfully created user: " + userEmail);
        return "registration-confirmation";
    }

}
