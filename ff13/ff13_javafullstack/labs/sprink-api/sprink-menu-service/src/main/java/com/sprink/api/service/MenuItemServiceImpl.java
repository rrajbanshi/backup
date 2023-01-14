package com.sprink.api.service;

import com.sprink.api.exception.MenuItemNotFoundException;
import com.sprink.api.model.MenuItem;
import com.sprink.api.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    MenuItemRepository menuItemRepo;

    @Override
    public MenuItem createMenuItem(MenuItem item) {
        item.setCreatedTime(LocalDateTime.now());
        return menuItemRepo.save(item);
    }

    @Override
    public MenuItem updateMenuItem(MenuItem item, int id) throws MenuItemNotFoundException {
        Optional<MenuItem> itemOptional = menuItemRepo.findById(id);
        if(itemOptional.isEmpty()) {
            throw new MenuItemNotFoundException("No menu item found for given id - " + id);
        }
        item.setId(id);
        item.setUpdatedTime(LocalDateTime.now());
        return menuItemRepo.save(item);
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return menuItemRepo.findAll();
    }

    @Override
    public MenuItem getMenuItem(int id) throws MenuItemNotFoundException {
        Optional<MenuItem> itemOptional = menuItemRepo.findById(id);
        if(itemOptional.isEmpty()) {
            throw new MenuItemNotFoundException("No menu item found for given id - " + id);
        } else {
            return itemOptional.get();
        }
    }

    @Override
    public void deleteMenuItem(int id) throws MenuItemNotFoundException {
        Optional<MenuItem> itemOptional = menuItemRepo.findById(id);
        if(itemOptional.isEmpty()) {
            throw new MenuItemNotFoundException("No menu item found for given id - " + id);
        }
        menuItemRepo.deleteById(id);
    }
    public void menuItemDeleteAll() {
    	menuItemRepo.deleteAll();
    }
}
