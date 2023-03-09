package com.hellokoding.tutorials.controller;

import com.hellokoding.tutorials.model.*;
import com.hellokoding.tutorials.repository.ProdusRepository;
import com.hellokoding.tutorials.service.ProdusService;
import com.hellokoding.tutorials.service.SecurityService;
import com.hellokoding.tutorials.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ProdusRepository produsService;

    private User userglobal;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        //
        model.addAttribute("userForm", new User());

        return "registration"; // se returneaza pagina registration
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
 // operatii din registration
        if (bindingResult.hasErrors()) {
            return "registration";
        }
 // aici salvam userul
        userglobal=userForm;
        userService.save(userForm);
 // aici facem login
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
  // redirect to /welcome
        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";  // cand te.ai autentificat local 8 te redirectioneaza la /
        }
    // aici e login
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    //aici e pt welcome:
    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        model.addAttribute("produse", produsService.findAll());
        return "welcome";
    }

    @GetMapping({ "/shopping_cart"})
    public String goShopping(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        com.hellokoding.tutorials.model.User user = userService.findByUsername(auth.getName());
        if (user == null) throw new UsernameNotFoundException(auth.getName());


        System.out.println("hello");
        Set<CartItem> cartItems = user.getLista();
        List<Item> lista = new ArrayList<Item>();
        for (CartItem cartItem:
             cartItems) {
            Produs p = cartItem.getProductid();
            lista.add(new Item(p.getId(),p.getNume(),p.getPret(),p.getProducator(),p.getCantitate(),cartItem.getQuantity()));
        }

        System.out.println(lista);
        model.addAttribute("items", lista);

        return "shopping_cart";
    }





}
