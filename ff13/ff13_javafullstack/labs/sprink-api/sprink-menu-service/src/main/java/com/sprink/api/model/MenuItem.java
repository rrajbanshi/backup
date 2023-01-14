package com.sprink.api.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Table // Optional
public class MenuItem {

    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "Menu Item Name can not be null")
    @NotBlank(message = "Menu Item Name can not be blank")
    private String name;
    @NotNull(message = "Menu Item Description can not be null")
    @NotBlank(message = "Menu Item Description can not be blank")
    private String description;
    @NotNull(message = "Menu Item Price can not be null")
    private double price;
    private MenuItemType type;
    private CusineType cusineType;
    private MealSession mealSession;
    private PortionSize portionSize;
    private LocalDateTime createdTime;
    private String createdBy;
    private LocalDateTime updatedTime;
    private String updatedBy;
}
