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
import com.example.challenge.model.Product;
import com.example.challenge.repository.ProductRepository;

@RestController
@RequestMapping("api")
public class ProductRestController {
    @Autowired
    ProductRepository productRepository;

     @GetMapping("product")
    public ResponseEntity<Object> Get(){
        return CustomResponse.generate(HttpStatus.OK, "Data retrieved", productRepository.findAll());
    }

    @PostMapping(value = {"product", "product/{id}"})
    public ResponseEntity<Object> save(@RequestBody Product product, @PathVariable(required = false) Integer id){
        if (id != null){
            Product newProduct  = productRepository.findById(id).orElse(null);
            newProduct.setId(product.getId());
            newProduct.setTitle(product.getTitle());
            newProduct.setDescription(product.getDescription());
            newProduct.setTrsc(product.getTrsc());
            productRepository.save(newProduct);
            return CustomResponse.generate(HttpStatus.OK, "Data saved");
        }else{
            productRepository.save(product);
            Boolean isCreated = productRepository.findById(product.getId()).isPresent();
            if(isCreated){
                return CustomResponse.generate(HttpStatus.OK, "Data saved", productRepository.findById(product.getId()));
            } 
        }return CustomResponse.generate(HttpStatus.OK, "Data failed to save");
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Integer id){
        productRepository.deleteById(id);
        Boolean isDeleted = productRepository.findById(id).isEmpty();
        if(isDeleted){
            return CustomResponse.generate(HttpStatus.OK, "Data deleted");
        }else{
            return CustomResponse.generate(HttpStatus.BAD_REQUEST, "Data failed to delete");
        }
        
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Object> Get(@PathVariable(required = true) Integer id){
        productRepository.findById(id);
        Boolean isExist = productRepository.findById(id).isPresent();
        if(isExist){
            return CustomResponse.generate(HttpStatus.OK, "Data retrieved", productRepository.findById(id));
        }else{
            return CustomResponse.generate(HttpStatus.BAD_REQUEST, "Data failed to retrieve");
        }
    }
}
