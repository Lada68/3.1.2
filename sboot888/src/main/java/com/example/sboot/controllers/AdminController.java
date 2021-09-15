package com.example.sboot.controllers;

import com.example.sboot.model.User;
import com.example.sboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

@GetMapping("/getAll")
    public String getAll(Model model, User user, Principal principal){
model.addAttribute("userP", userService.findByUsername(principal.getName()));
        model.addAttribute("users", userService.getAll());
        model.addAttribute("user",userService.findById(user.getId()));
        return "admin/users";
}

@RequestMapping("/getOne")
@ResponseBody
public Optional<User> getOne(Integer id){
        return userService.getOne(id);
}

@PostMapping("/addNew")
public String addNew(@ModelAttribute("user") User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userService.addNew(user);
    return "redirect:/admin/getAll";
}
@RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
public String update(@ModelAttribute("user") User user){
        userService.update(user);
    return "redirect:/admin/getAll";
}

    @RequestMapping(value ="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteUser(Integer id) {
        System.out.println("controller " +id);
        userService.delete(id);
        return "redirect:/admin/getAll";
    }
//    @GetMapping("/{id}")
//    public String showUser(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.findById(id));
//        return "admin/show";
//    }
    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model, Principal principal) {
       model.addAttribute("userP", userService.findByUsername(principal.getName()));
        model.addAttribute("user", userService.findById(id));
        return "admin/show";
    }

}



