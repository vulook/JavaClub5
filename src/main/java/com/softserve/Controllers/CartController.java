package com.softserve.Controllers;

import com.softserve.entity.Cart;
import com.softserve.services.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {

    private static final Logger LOG = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    @GetMapping("/list")
    public String listCarts(Model theModel) {
        LOG.debug("Show Carts handler method");
        List<Cart> theCarts = cartService.findAll();
        theModel.addAttribute("carts", theCarts);
        return "list-carts";
    }
    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        LOG.debug("Inside show cart-form handler method");
        Cart theCart = new Cart();
        theModel.addAttribute("cart", theCart);
        return "cart-form";
    }

    @PostMapping("/saveCart")
    public String saveCart(@ModelAttribute("cart") Cart theCart) {
        LOG.debug("Save Cart handler method");
        cartService.create(theCart);
        return "redirect:/cart/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("cartID") long theId,
                                    Model theModel) {
        LOG.debug("Update Cart handler method");
        Cart theCart = cartService.findByID(theId);
        theModel.addAttribute("cart", theCart);
        return "cart-form";
    }
    @GetMapping("/delete/{id}")
    public String deleteCart(@PathVariable long id)  {
        LOG.debug("Delete Cart handler method");
        cartService.delete(id);
        return "redirect:/cart/list";
    }
    @GetMapping("/add/{id}")
    public String addCart(@PathVariable long id)  {
        LOG.debug("Delete Cart handler method");
        cartService.request(id);
        return "redirect:/cart/list";
    }

}