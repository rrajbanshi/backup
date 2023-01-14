package com.sprink.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.sprink.api.exception.MenuItemNotFoundException;
import com.sprink.api.model.CusineType;
import com.sprink.api.model.MealSession;
import com.sprink.api.model.MenuItem;
import com.sprink.api.model.MenuItemType;
import com.sprink.api.model.PortionSize;

@SpringBootTest
public class MenuItemServiceImplTests {
	private static final MenuItemType MenuItemType = null;
	@Autowired
  MenuItemServiceImpl menuItemServiceImpl;
	
	@BeforeAll
	public static void init() {
		// Logic to initialize test data goes here
		System.out.println("Test data initialization at class level..");
	}

	@AfterAll
	public static void tearDown() {
		// Logic to clean up test data goes here
		System.out.println("Test data clean up at class level..");
	}
	@BeforeEach
	public void setup() {
		//item 1
		MenuItem samosa =  new MenuItem();
		samosa.setId(1);
		samosa.setName("samosa");
		samosa.setDescription("very good");
		samosa.setPrice(120.0);
		samosa.setType(MenuItemType.VEG);
		samosa.setCusineType(CusineType.NORTH);
		samosa.setMealSession(MealSession.DINNER);
		samosa.setPortionSize(PortionSize.PREMIUM);
		menuItemServiceImpl.createMenuItem(samosa);
		
		//item 2
		MenuItem biryani =  new MenuItem();
		biryani.setName("chicken biryani");
		biryani.setDescription("best selling biryani");
		biryani.setPrice(300.0);
		biryani.setType(MenuItemType.NON_VEG);
		biryani.setCusineType(CusineType.NORTH);
		biryani.setMealSession(MealSession.DINNER);
		biryani.setPortionSize(PortionSize.JUMBO);
		menuItemServiceImpl.createMenuItem(biryani);	
	}
	
	@AfterEach
	public void cleanup() {
	  menuItemServiceImpl.menuItemDeleteAll();	
	}
	
	@Test
	public void shoudCreateMenuItemWhenPassingMandatoryDetails() {
		MenuItem panner = new MenuItem();
		panner.setId(3);
		panner.setName("panner tikka");
		panner.setDescription("this is panner");
		
		menuItemServiceImpl.createMenuItem(panner);
		try {
			assertNotNull(menuItemServiceImpl.getMenuItem(3));
		} catch (MenuItemNotFoundException e) {
			e.printStackTrace();
		}
		try {
			assertEquals(3, menuItemServiceImpl.getMenuItem(3).getId());
		} catch (MenuItemNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldDeleteMenuItemWhenPassingValidMenuId() {
		try {
			menuItemServiceImpl.deleteMenuItem(2);
			assertNull(menuItemServiceImpl.getMenuItem(2));
			assertEquals(1, menuItemServiceImpl.getMenuItems().size());
		} catch (MenuItemNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
