package com.shelling.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.FileUtils;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.shelling.entity.Laptop;
import com.shelling.entity.Order;
import com.shelling.service.LaptopService;

import util.PriceUtil;

@Controller
public class LaptopController {
	
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	private LaptopService service;
//	
//	//-------------ORDER----------------------
////	@RequestMapping("/orderpage")
////	public String viewOrderPage(Model model) {
////		List<Order> orders = service.getOrders();
////		model.addAttribute("orders", orders);
////		
////		return "order";
////	}
////	@RequestMapping("/deleteorder/{id}")
////	public String deleteOrder(@PathVariable(name = "id") Long orderId) {
////	    service.deleteOrder(orderId);
////	    return "redirect:/orderpage";   
////	}
//	
//	//--------------LAPTOP--------------------
//	@RequestMapping("/laptoppage")
//	public String viewLaptopPage(Model model) {
//		//List<Laptop> laptops = service.getLaptops();
//		List<Laptop> laptops = new ArrayList<Laptop>();
//		Laptop laptop = new Laptop();
//		laptop.setCardGraphic("ABC");
//		laptops.add(laptop);
//		model.addAttribute("laptops", laptops);
//		return "laptop";
//	}
//	@RequestMapping("/newlaptop")
//	public String newLaptop(Model model) {
//		
//		Laptop laptop = new Laptop();
//		model.addAttribute("laptop", laptop);
//		
//		return "new_laptop";
//	}
//	@RequestMapping(value = "/savelaptop", method = RequestMethod.POST)
//	public String saveLaptop(@ModelAttribute("laptop") Laptop laptop, @RequestParam("photo") MultipartFile photo) {
//	    
//	    if(!photo.isEmpty()) {
//	    	Path path = Paths.get("src/main/resources/static/images/");
//	    	
//	    	if(this.laptop.getImage() != null) {
//		    	File file = new File("src/main/resources/static" + this.laptop.getImage());
//			    file.delete();	
//	    	}
//	    	
//	    	String str = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
//			String s[] = str.split("/");
//			String url = "";
//			s[0]+= "//";
//			for(int i = 0; i < s.length-1; i++) {
//				System.out.println(s[i] + "-");
//				url += s[i];
//			}
//			
//		    try {
//				InputStream inputStream = photo.getInputStream();
//				Files.copy(inputStream, path.resolve(photo.getOriginalFilename()),
//						StandardCopyOption.REPLACE_EXISTING);
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			 
//			laptop.setImage("/images/" + photo.getOriginalFilename());
//			laptop.setImageUrl(url+"/getimage/"+photo.getOriginalFilename());
//	    }else {
//			laptop.setImage(this.laptop.getImage());
//			laptop.setImageUrl(this.laptop.getImageUrl());
//	    }
//	   	    
//		String setupPrice = PriceUtil.setupPrice(laptop.getPrice().toString());
//		laptop.setPriceStr(setupPrice + " VND");
//		
//	    service.saveLaptop(laptop);
//	     
//	    return "redirect:/laptoppage";
//	}
//	private Laptop laptop = new Laptop(); //getLaptop to delete when update
//	@RequestMapping("/editlaptop/{id}")
//	public ModelAndView editLaptop(@PathVariable(name = "id") Long id) {
//	    ModelAndView mav = new ModelAndView("edit_laptop");
//	    laptop = service.getLaptop(id);
//	    mav.addObject("laptop", laptop);
//	     
//	    return mav;
//	}
//	
//	@RequestMapping("/deletelaptop/{id}")
//	public String deleteLaptop(@PathVariable(name = "id") Long id) {
//	    Laptop laptop = service.getLaptop(id);
//	    File file = new File("src/main/resources/static" + laptop.getImage());
//	    file.delete();
//	    service.deleteLaptop(id);
//	    
//	    return "redirect:/laptoppage";       
//	}
//	
//	@RequestMapping(value = "getimage/{photo:.+}", method =  RequestMethod.GET)
//	@ResponseBody
//	public ResponseEntity<ByteArrayResource> getImage(@PathVariable("photo") String photo){
//		try {
//			logger.info("path paaram, photo: {}", photo);
//
//			if(!photo.equals("")) {
//				Path fileName = Paths.get("src/main/resources/static/images", photo);
//				byte[] buffer;
//				buffer = Files.readAllBytes(fileName);
//				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
//				return ResponseEntity.ok()
//						.contentLength(buffer.length)
//						.contentType(MediaType.parseMediaType("image/png"))
//						.body(byteArrayResource);
//			}
//		} catch (IOException e) {
//			logger.error("", e);
//		}
//		
//		
//		return ResponseEntity.badRequest().build();
//	}
	
}
