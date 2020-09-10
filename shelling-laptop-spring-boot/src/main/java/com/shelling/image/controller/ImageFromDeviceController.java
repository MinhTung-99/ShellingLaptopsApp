package com.shelling.image.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageFromDeviceController {
	
	@RequestMapping("/")
	public String takeImage() {
		 return "takeimage";
	}
	
	@PostMapping("/save")
	public String save(@RequestParam("photo") MultipartFile photo) {
		
		if(photo.isEmpty()) {
			return "takeimage";
		}
		
		Path path = Paths.get("uploads/");
		
		try {
			InputStream inputStream = photo.getInputStream();
			Files.copy(inputStream, path.resolve(photo.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "success";
		
	}
}