package com.e.d.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.e.d.model.service.IpService;
import com.e.d.model.service.MyCloudService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

	@Autowired
	private MyCloudService service;
	
	@Autowired
	private IpService ipService;
	
	@GetMapping("/")
	public String index() {
		ipService.ipPrint();
		return "index";
	}
	
	@PostMapping("/upload")
	public String upload(@RequestParam MultipartFile file, Model m) 
			throws IllegalStateException, IOException {
	    service.upload(file);
	    return "redirect:/";
	}
	
}
