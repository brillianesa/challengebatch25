package com.example.challenge.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.challenge.model.TrscUserInput;
import com.example.challenge.repository.ProductRepository;
import com.example.challenge.repository.TrscRepository;
import com.example.challenge.repository.UserInputRepository;

@Controller
@RequestMapping("transaction")
public class TrscUserInputController {
    @Autowired
    TrscRepository trscRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserInputRepository userInputRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("trscUserInputs", trscRepository.findAll());
        return "transaction/index";
    }

    @GetMapping(value = {"form", "form/{id}"})
    public String form(Model model, @PathVariable(required = false) Integer id){
        if (id != null){
            model.addAttribute("trscUserInput", trscRepository.findById(id));
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("userinputs", userInputRepository.findAll());
        }else{
            model.addAttribute("trscUserInput", new TrscUserInput());
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("userinputs", userInputRepository.findAll());
        }
        return "transaction/form";
    }

    @PostMapping("save")
    public String save(TrscUserInput trscUserInput) {
        trscRepository.save(trscUserInput);
        if (trscUserInput.getId() != null) {
            return "redirect:/transaction";
        } else {
            return "transaction/form";
        }
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable(required = true) Integer id) {
        TrscUserInput trscUserInput = trscRepository.findById(id).orElse(null);
        if (trscUserInput != null) {
            trscRepository.delete(trscUserInput);
        }
        return "redirect:/transaction";
    }
}

