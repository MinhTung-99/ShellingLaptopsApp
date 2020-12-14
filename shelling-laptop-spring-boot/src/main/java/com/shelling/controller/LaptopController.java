package com.shelling.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.shelling.repository.User;
import com.shelling.repository.Laptop;
import com.shelling.repository.Store;
import com.shelling.service.LaptopService;

@Controller
public class LaptopController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LaptopService service;
	
	@RequestMapping("/")
	public String viewHomePage() {
		return "index";
	}
	
	//-------------ACCOUNT--------------------
	@RequestMapping("/userpage")
	public String viewUserPage(Model model) {
		List<User> users = service.getAccounts();
		model.addAttribute("users", users);
		return "user";
	}
	
	//-------------STORE----------------------
	@RequestMapping("/storepage")
	public String viewStorePage(Model model) {
		List<Store> stores = service.getStores();
		model.addAttribute("stores", stores);
		
		return "store";
	}
	@RequestMapping("/deletestore/{id}")
	public String deleteStore(@PathVariable(name = "id") Long storeId) {
	    service.deleteStore(storeId);
	    return "redirect:/";   
	}
	
	//--------------LAPTOP--------------------
	@RequestMapping("/laptoppage")
	public String viewLaptopPage(Model model) {
		List<Laptop> laptops = service.getLaptops();
		model.addAttribute("laptops", laptops);
		return "laptop";
	}
	@RequestMapping("/newlaptop")
	public String newLaptop(Model model) {
		
		Laptop laptop = new Laptop();
		model.addAttribute("laptop", laptop);
		
		return "new_laptop";
	}
	@RequestMapping(value = "/savelaptop", method = RequestMethod.POST)
	public String saveLaptop(@ModelAttribute("laptop") Laptop laptop, @RequestParam("photo") MultipartFile photo) {
	    
	    if(!photo.isEmpty()) {
	    	Path path = Paths.get("uploads/");
			
			try {
				InputStream inputStream = photo.getInputStream();
				Files.copy(inputStream, path.resolve(photo.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
				
			} catch (IOException e) {
				e.printStackTrace();
			    System.out.println(photo);
			}
			
			laptop.setImage("http://localhost:8080/getimage/" + photo.getOriginalFilename());
	    }
	    
	    service.saveLaptop(laptop);
	     
	    return "redirect:/";
	}
	
	@RequestMapping("/editlaptop/{id}")
	public ModelAndView editLaptop(@PathVariable(name = "id") Long id) {
	    ModelAndView mav = new ModelAndView("edit_laptop");
	    Laptop laptop = service.getLaptop(id);
	    mav.addObject("laptop", laptop);
	     
	    return mav;
	}
	
	@RequestMapping("/deletelaptop/{id}")
	public String deleteLaptop(@PathVariable(name = "id") Long id) {
	    service.deleteLaptop(id);
	    return "redirect:/";       
	}
	
	@RequestMapping(value = "getimage/{photo:.+}", method =  RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImage(@PathVariable("photo") String photo){
		try {
			logger.info("path paaram, photo: {}", photo);

			if(!photo.equals("")) {
				Path fileName = Paths.get("uploads", photo);
				byte[] buffer;
				buffer = Files.readAllBytes(fileName);
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok()
						.contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png"))
						.body(byteArrayResource);
			}
		} catch (IOException e) {
			logger.error("", e);
		}
		
		
		return ResponseEntity.badRequest().build();
	}
	
}
