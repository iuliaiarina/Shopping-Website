package com.hellokoding.tutorials.controller;

import com.hellokoding.tutorials.model.CartItem;
import com.hellokoding.tutorials.model.Produs;
import com.hellokoding.tutorials.repository.CartItemRepository;
import com.hellokoding.tutorials.repository.ProdusRepository;
import com.hellokoding.tutorials.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class ProdusController {

    private final ProdusRepository produsRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public ProdusController(ProdusRepository produsRepository, CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.produsRepository = produsRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/signup")
    public String getProd(Produs produs)
    {
        return "add-produs";
    }

    @PostMapping("/addprodus")
    public String addUser(@Valid Produs produs, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-produs";
        }

        produsRepository.save(produs);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("produse", produsRepository.findAll());
        return "index";
    }



    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Produs produs = produsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid produs Id:" + id));
        model.addAttribute("produs", produs);
        return "update-produs";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Produs produs,
                             BindingResult result, Model model) { //primi un produs valid si idul
        if (result.hasErrors()) {
            produs.setId(id); //
            return "update-produs";
        }
        produsRepository.save(produs);
        return "redirect:/index";
    }

    @GetMapping("/add/{id}")
    public String addcos(@PathVariable("id") long id,Model model)
    {
        Produs p=produsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid produs Id:" + id));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        com.hellokoding.tutorials.model.User user = userRepository.findByUsername(auth.getName());
        if (user == null) throw new UsernameNotFoundException(auth.getName());
        System.out.println(user.getId()+" "+user.getUsername()); // asta mere bine



        Boolean ok=false;
        Set<CartItem> setCartItems= user.getLista();
        System.out.println("din tot cosul:"+setCartItems.toString());
        for(CartItem cartItem:setCartItems)
        {
            System.out.println("din cos:"+cartItem.getProductid().toString());
            if (cartItem.getProductid().equals(p) && p.getCantitate()>0) { //am gasit un produs
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                p.setCantitate(p.getCantitate()-1);
                produsRepository.save(p);
                ok=true;
                cartItemRepository.save(cartItem);
            }
        }// acum trebuie salvat noul cart item:

        if(ok==false && p.getCantitate()>0) {
            CartItem cartItem=new CartItem();
            p.setCantitate(p.getCantitate()-1);
            produsRepository.save(p);
            cartItem.setProductid(p);
            cartItem.setQuantity(1);
            setCartItems.add(cartItem);
            cartItemRepository.save(cartItem);

        }

        user.setLista(setCartItems);
        System.out.println(setCartItems);
        userRepository.save(user);

        System.out.println(p.toString());
        //model.addAttribute("produse", produsRepository.findAll());
        return "redirect:/welcome";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Produs produs = produsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid produs Id:" + id));
        produsRepository.delete(produs);
        return "redirect:/index";
    }

    @GetMapping("/deleteCos/{id}")
    public String deletecos(@PathVariable("id") long id,Model model)
    {
        Produs p=produsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid produs Id:" + id));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        com.hellokoding.tutorials.model.User user = userRepository.findByUsername(auth.getName());
        if (user == null) throw new UsernameNotFoundException(auth.getName());
        System.out.println(user.getId()+" "+user.getUsername()); // asta mere bine



        Boolean ok=false;
        Set<CartItem> setCartItems= user.getLista();
        System.out.println("din tot cosul:"+setCartItems.toString());
        for(CartItem cartItem:setCartItems)
        {
            System.out.println("din cos:"+cartItem.getProductid().toString());
            if (cartItem.getProductid().equals(p) && cartItem.getQuantity()>0) { //am gasit un produs
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                p.setCantitate(p.getCantitate()+1);
                produsRepository.save(p);
                ok=true;
                cartItemRepository.save(cartItem);
            }
        }// acum trebuie salvat noul cart item:


        user.setLista(setCartItems);
        System.out.println(setCartItems);
        userRepository.save(user);

        System.out.println(p.toString());
        model.addAttribute("produse", produsRepository.findAll());
        return "redirect:/shopping_cart";
    }

    @GetMapping({"/cumpara"})
    public String welcome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        com.hellokoding.tutorials.model.User user = userRepository.findByUsername(auth.getName());
        if (user == null) throw new UsernameNotFoundException(auth.getName());
        System.out.println(user.getId()+" "+user.getUsername()); // asta mere bine



        Boolean ok=false;
        Set<CartItem> setCartItems= user.getLista();
        System.out.println("din tot cosul:"+setCartItems.toString());

        for(CartItem cartItem:setCartItems)
        { // fiecare obiect din cart item este sters
            CartItem c = new CartItem();
            c.setId(cartItem.getId());
            cartItemRepository.save(c);
        }

        setCartItems =new HashSet<>();
        user.setLista(setCartItems);
        System.out.println(setCartItems);
        userRepository.save(user);

        model.addAttribute("produse", produsRepository.findAll());
        return "chitanta";
    }/*

    @GetMapping({"/chitanta"})
    public String welcome(Model model) {
        return "welcome";
    }*/


}
