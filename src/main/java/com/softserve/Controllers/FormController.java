package com.softserve.Controllers;

import com.softserve.entity.Cart;
import com.softserve.entity.Form;
import com.softserve.entity.Form;
import com.softserve.services.CartService;
import com.softserve.services.FormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class FormController {
    private static final Logger LOG = LoggerFactory.getLogger(FormController.class);

    @Autowired
    FormService formService;
    @Autowired
    CartService cartService;
    @GetMapping("/form/list")
    public String listForms(Model theModel) {
        LOG.debug("Show Forms handler method");
        List<Form> theForms = formService.findAllByID();
        theModel.addAttribute("forms", theForms);
        return "list-forms";
    }
    @GetMapping("/forms")
    public String allForms(Model theModel) {
        LOG.debug("Show Forms handler method");
        List<Form> theForms = formService.getAll();
        theModel.addAttribute("forms", theForms);
        return "all-forms";
    }
//    @GetMapping("/forms/showForm")
//    public String showFormForAdd(Model theModel) {
//        LOG.debug("Inside show form-form handler method");
//        Form theForm = new Form();
//        theModel.addAttribute("form", theForm);
//        return "form-form";
//    }

    @PostMapping("/forms/saveForm")
    public String saveForm(@ModelAttribute("form") Form theForm) {
        LOG.debug("Save Form handler method");
        formService.create(theForm);
        return "redirect:/forms";
    }



    @GetMapping("/forms/delete/{id}")
    public String deleteForms(@PathVariable long id)  {
        LOG.debug("Delete Form handler method");
        formService.delete(id);
        return "redirect:/forms";
    }

    @GetMapping("/forms/createForm")
    public String showForm(@RequestParam("cartID") long theId,
                           Model theModel) {
        LOG.debug("Update Form handler method");
        Form theForm = new Form();
        Cart cart = cartService.findByID(theId);
        Date date = new Date();
        theForm.setFormUser(cart.getCartUser());
        theForm.setFormBook(cart.getCartBook());
//        java.sql.Date date1= (java.sql.Date) date;

        theModel.addAttribute("form", theForm);
        return "form-form";
    }
    @GetMapping("/forms/updateForm")
    public String showFormForUpdate(@RequestParam("formID") long theId,
                                    Model theModel) {
        LOG.debug("Update Form handler method");
        Form theForm = formService.findByID(theId);
        theModel.addAttribute("form", theForm);
        return "form-form";
    }
}
