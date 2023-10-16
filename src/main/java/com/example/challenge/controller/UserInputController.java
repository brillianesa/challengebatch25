package com.example.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.challenge.model.Product;
import com.example.challenge.model.UserInput;
import com.example.challenge.repository.UserInputRepository;

@Controller
@RequestMapping("userinput")
public class UserInputController {
    @Autowired
    UserInputRepository userInputRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("userInputs", userInputRepository.findAll());
        return "userinput/index";
    }

    @GetMapping(value = {"form", "form/{id}"})
    public String form(Model model, @PathVariable(required = false) Integer id){
        if (id != null){
            model.addAttribute("userInput", userInputRepository.findById(id));
        }else{
            model.addAttribute("userInput", new Product());
        }
        return "userinput/form";
    }

    @PostMapping("save")
    public String save(UserInput userInput) {
        userInputRepository.save(userInput);
        if (userInput.getId() != null) {
            return "redirect:/userinput";
        } else {
            return "userinput/form";
        }
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable(required = true) Integer id) {
        UserInput userInput = userInputRepository.findById(id).orElse(null);
        if (userInput != null) {
            userInputRepository.delete(userInput);
            return "redirect:/userinput";
        } else {
            return "redirect:/userinput";
        }
    }
}

