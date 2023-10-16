package com.example.challenge.controller.restAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.challenge.handler.CustomResponse;
import com.example.challenge.model.UserInput;
import com.example.challenge.repository.UserInputRepository;


@RestController
@RequestMapping("api")
public class UserInputRestController {
    @Autowired
    UserInputRepository userInputRepository;

    @GetMapping("userinput")
    public ResponseEntity<Object> get() {
        return CustomResponse.generate(HttpStatus.OK, "Data retrieved", userInputRepository.findAll());
    }

    @PostMapping(value = {"userinput", "userinput/{id}"})
    public ResponseEntity<Object> save(@RequestBody UserInput userInput, @PathVariable(required = false) Integer id) {
        if (id != null) {
            UserInput existingUserInput = userInputRepository.findById(id).orElse(null);
            if (existingUserInput != null) {
                existingUserInput.setId(userInput.getId());
                existingUserInput.setName(userInput.getName());
                existingUserInput.setTrsc(userInput.getTrsc());
                userInputRepository.save(existingUserInput);
                return CustomResponse.generate(HttpStatus.OK, "Data saved");
            } else {
                return CustomResponse.generate(HttpStatus.NOT_FOUND, "UserInput not found");
            }
        } else {
            userInputRepository.save(userInput);
            if (userInput.getId() != null) {
                return CustomResponse.generate(HttpStatus.OK, "Data saved");
            } else {
                return CustomResponse.generate(HttpStatus.BAD_REQUEST, "Data failed to save");
            }
        }
    }

    @DeleteMapping("userinput/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Integer id) {
        userInputRepository.deleteById(id);
        boolean isDeleted = userInputRepository.findById(id).isEmpty();
        if (isDeleted) {
            return CustomResponse.generate(HttpStatus.OK, "Data deleted");
        } else {
            return CustomResponse.generate(HttpStatus.BAD_REQUEST, "Data failed to delete");
        }
    }

    @GetMapping("userinput/{id}")
    public ResponseEntity<Object> get(@PathVariable(required = true) Integer id) {
        UserInput userInput = userInputRepository.findById(id).orElse(null);
        if (userInput != null) {
            return CustomResponse.generate(HttpStatus.OK, "Data retrieved", userInput);
        } else {
            return CustomResponse.generate(HttpStatus.NOT_FOUND, "UserInput not found");
        }
    }
}

