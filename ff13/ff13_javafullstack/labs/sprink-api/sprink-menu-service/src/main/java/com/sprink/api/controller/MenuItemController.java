package com.sprink.api.controller;

import com.sprink.api.exception.MenuItemNotFoundException;
import com.sprink.api.model.MenuItem;
import com.sprink.api.model.ResponseMessage;
import com.sprink.api.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class MenuItemController {

    @Autowired
    MenuItemService menuItemService;


    // POST http://localhost:8081/sprink/api/items

    @PostMapping
    public ResponseEntity<ResponseMessage> createMenuItem(@RequestBody @Valid  MenuItem menuItem) throws URISyntaxException {
        MenuItem item = menuItemService.createMenuItem(menuItem);
        ResponseMessage response = new ResponseMessage();
        response.setStatus("Success");
        response.setMessage("Menu Item created successfully.");
        return ResponseEntity.created(new URI("http://localhost:8081/sprink/api/items/" + item.getId())).body(response);
    }

    // GET http://localhost:8081/sprink/api/items
    @GetMapping
    public List<MenuItem> getMenuItems() {
        return menuItemService.getMenuItems();
    }

    // GET http://localhost:8081/sprink/api/items/{id}
    @GetMapping("/{id}")
    public MenuItem getMenuItem(@PathVariable int id) throws MenuItemNotFoundException {
       return menuItemService.getMenuItem(id);
    }

    // PUT http://localhost:8081/sprink/api/items/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateMenuItem(@RequestBody MenuItem menuItem, @PathVariable int id) throws MenuItemNotFoundException {
        menuItemService.updateMenuItem(menuItem, id);
        ResponseMessage response = new ResponseMessage();
        response.setStatus("Success");
        response.setMessage("Menu Item updated successfully.");
        return ResponseEntity.ok(response);
    }

    // DELETE http://localhost:8081/sprink/api/items/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteMenuItem(@PathVariable int id) throws MenuItemNotFoundException{
        menuItemService.deleteMenuItem(id);
        ResponseMessage response = new ResponseMessage();
        response.setStatus("Success");
        response.setMessage("Menu Item deleted successfully.");
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleNoMenuItemFoundException(MenuItemNotFoundException e) {
        ResponseMessage response = new ResponseMessage();
        response.setStatus("Failure");
        response.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> handleValidationException(MethodArgumentNotValidException e) {
        ResponseMessage response = new ResponseMessage();
        response.setStatus("Failure");
        StringBuilder errorMsg = new StringBuilder();
        for(ObjectError error: e.getAllErrors()) {
            errorMsg.append(error.getDefaultMessage());
            errorMsg.append(" ");
        }
        response.setMessage("Validation Error: " + errorMsg);
        return ResponseEntity.badRequest().body(response);
    }

}
