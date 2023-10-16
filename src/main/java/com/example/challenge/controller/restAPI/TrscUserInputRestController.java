package com.example.challenge.controller.restAPI;
import java.util.ArrayList;
import java.util.List;

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
import com.example.challenge.model.TrscUserInput;
import com.example.challenge.repository.TrscRepository;

@RestController
@RequestMapping("api")
public class TrscUserInputRestController {
    @Autowired
    TrscRepository trscRepository;

    @GetMapping("trscuserinput")
    public ResponseEntity<Object> get() {
        return CustomResponse.generate(HttpStatus.OK, "Data retrieved", trscRepository.findAll());
    }

    // @PostMapping(value = {"trscuserinput", "trscuserinput/{id}"})
    // public ResponseEntity<Object> save(@RequestBody List<TrscUserInput> trscUserInput, @PathVariable(required = false) Integer id) {
    //     List<TrscUserInput> savedTrscUserInputs = new ArrayList<>();
    //     if (id != null) {
    //         TrscUserInput existingTrscUserInput = trscRepository.findById(id).orElse(null);
    //         if (existingTrscUserInput != null) {
    //             existingTrscUserInput.setId(trscUserInput.getId());
    //             existingTrscUserInput.setLabel(trscUserInput.getLabel());
    //             existingTrscUserInput.setProduct(trscUserInput.getProduct());
    //             existingTrscUserInput.setUser_input(trscUserInput.getUser_input());
    //             trscRepository.save(existingTrscUserInput);
    //             return CustomResponse.generate(HttpStatus.OK, "Data saved", trscRepository.findById(existingTrscUserInput.getId()));
    //         } else {
    //             return CustomResponse.generate(HttpStatus.NOT_FOUND, "TrscUserInput not found");
    //         }
    //     } else {
    //         trscRepository.save(trscUserInput);
    //         if (trscUserInput.getId() != null) {
    //             return CustomResponse.generate(HttpStatus.OK, "Data saved", trscRepository.findById(trscUserInput.getId()));
    //         } else {
    //             return CustomResponse.generate(HttpStatus.BAD_REQUEST, "Data failed to save");
    //         }
    //     }
    // }
    @PostMapping(value = {"trscuserinput", "trscuserinput/{id}"})
    public ResponseEntity<Object> save(@RequestBody List<TrscUserInput> trscUserInputs, @PathVariable(required = false) Integer id) {
                List<TrscUserInput> savedTrscUserInputs = new ArrayList<>();
                
                for (TrscUserInput trscUserInput : trscUserInputs) {
                    if (id != null) {
                        TrscUserInput existingTrscUserInput = trscRepository.findById(id).orElse(null);
                        if (existingTrscUserInput != null) {
                            existingTrscUserInput.setLabel(trscUserInput.getLabel());
                            existingTrscUserInput.setProduct(trscUserInput.getProduct());
                            existingTrscUserInput.setUser_input(trscUserInput.getUser_input());
                            trscRepository.save(existingTrscUserInput);
                            savedTrscUserInputs.add(existingTrscUserInput);
                            return CustomResponse.generate(HttpStatus.OK, "Data saved", trscRepository.findById(existingTrscUserInput.getId()));
                        }
                        return CustomResponse.generate(HttpStatus.NOT_FOUND, "TrscUserInput not found");
                    } else {
                        trscRepository.save(trscUserInput);
                        if (trscUserInput.getId() != null) {
                            savedTrscUserInputs.add(trscUserInput);
         
                        }
                    }
                }
                
                if (!savedTrscUserInputs.isEmpty()) {
                    return CustomResponse.generate(HttpStatus.OK, "Data saved", savedTrscUserInputs);
                } else {
                    return CustomResponse.generate(HttpStatus.BAD_REQUEST, "Data failed to save");
                }
            }


    @DeleteMapping("trscuserinput/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Integer id) {
        trscRepository.deleteById(id);
        boolean isDeleted = trscRepository.findById(id).isEmpty();
        if (isDeleted) {
            return CustomResponse.generate(HttpStatus.OK, "Data deleted");
        } else {
            return CustomResponse.generate(HttpStatus.BAD_REQUEST, "Data failed to delete");
        }
    }

    @GetMapping("trscuserinput/{id}")
    public ResponseEntity<Object> get(@PathVariable(required = true) Integer id) {
        List<TrscUserInput> trscUserInput = trscRepository.findByProductId(id);
        if (trscUserInput != null) {
            return CustomResponse.generate(HttpStatus.OK, "Data retrieved", trscUserInput);
        } else {
            return CustomResponse.generate(HttpStatus.NOT_FOUND, "TrscUserInput not found");
        }
    }
}

