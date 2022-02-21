package com.softserve.Controllers;

import com.softserve.entity.Cart;
import com.softserve.entity.Form;
import com.softserve.services.CartService;
import com.softserve.services.FormService;
import com.softserve.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class CartController {

    private static final Logger LOG = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @GetMapping("/cart/my")
    public String listCarts(Model theModel) {
        LOG.debug("Show Carts handler method");
        List<Cart> theCarts = cartService.findAllByID(userService.getId());
        theModel.addAttribute("carts", theCarts);
        return "list-carts";
    }
    @GetMapping("/cart/showForm")
    public String showFormForAdd(Model theModel) {
        LOG.debug("Inside show cart-form handler method");
        Cart theCart = new Cart();
        theModel.addAttribute("cart", theCart);
        return "cart-form";
    }

    @PostMapping("/cart/saveCart")
    public String saveCart(@ModelAttribute("cart") Cart theCart) {
        LOG.debug("Save Cart handler method");
        cartService.create(theCart,userService.getId());
        return "redirect:/cart/my";
    }

    @GetMapping("/cart/add/{id}")
    public String addCart(@PathVariable long id)  {
        LOG.debug("Delete Cart handler method");
        cartService.request(id,userService.getId());
        return "redirect:/cart/my";
    }
    @GetMapping("/cart/return/{id}")
    public String returnCart(@PathVariable long id)  {
        LOG.debug("Delete Cart handler method");
        cartService.returnBook(id,userService.getId());
        return "redirect:/cart/my";
    }
    @GetMapping("/cart/updateForm")
    public String showFormForUpdate(@RequestParam("cartID") long theId,
                                    Model theModel) {
        LOG.debug("Update Cart handler method");
        Cart theCart = cartService.findByID(theId);
        theModel.addAttribute("cart", theCart);
        return "cart-form";
    }
    @GetMapping("/cart/delete/{id}")
    public String deleteCart(@PathVariable long id)  {
        LOG.debug("Delete Cart handler method");
        cartService.delete(id);
        return "redirect:/cart/my";
    }

    //admin deletes
    @GetMapping("/carts/delete/{id}")
    public String deleteCarts(@PathVariable long id)  {
        LOG.debug("Delete Cart handler method");
        cartService.delete(id);
        return "redirect:/carts";
    }

    @GetMapping("/carts")
    public String allCarts(Model theModel) {
        LOG.debug("Show Carts handler method");
        List<Cart> theCarts = cartService.getAll();
        List<Cart> newCarts = theCarts.stream().filter(x->x.getAction()==0).collect(Collectors.toList());
        List<Cart> returnCarts = theCarts.stream().filter(x->x.getAction()==1).collect(Collectors.toList());
        theModel.addAttribute("newcarts", newCarts);
        theModel.addAttribute("returncarts", returnCarts);
        return "all-carts";
    }

}