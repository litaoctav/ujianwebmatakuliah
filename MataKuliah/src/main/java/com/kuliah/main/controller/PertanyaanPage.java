
  
package com.kuliah.main.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.kuliah.main.entity.Pertanyaan;
import com.kuliah.main.repository.PertanyaanRepository;
import com.kuliah.main.utility.FileUtility;

@Controller
public class PertanyaanPage {
	
	@Autowired
	PertanyaanRepository pertanyaanRepository;
	
	@GetMapping("/pertanyaan/view")
	public String viewIndexPertanyaan(Model model) {
		
		model.addAttribute("listpertanyaan",pertanyaanRepository.findAll());
		model.addAttribute("active",7);
		return "view_pertanyaan";
	}
	
	
	@GetMapping("/pertanyaan/add")
	public String viewAddPertanyaan(Model model) {
		
		model.addAttribute("pertanyaan",new Pertanyaan());
		
		return "add_pertanyaan";
	}
	
	@PostMapping("/pertanyaan/view")
	  public String addPertanyaan(@RequestParam(value = "file") MultipartFile file, @ModelAttribute Pertanyaan pertanyaan, Model model) throws IOException {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	        String uploadDir = "user-photos" ;
	    	
	        FileUtility.saveFile(uploadDir, fileName, file);
	        
	        pertanyaan.setStatusGambar("/"+uploadDir + fileName);
	        
	        
		this.pertanyaanRepository.save(pertanyaan);
		model.addAttribute("listpertanyaan", pertanyaanRepository.findAll());
		
		
		return "redirect:/pertanyaan/view";
	}
	
	
	@GetMapping("/pertanyaan/update/{id}")
	public String viewUpdatePertanyaan(@PathVariable Long id, Model model) {
		
		Pertanyaan pertanyaan = pertanyaanRepository.findById(id).orElse(null);
		
		model.addAttribute("pertanyaan", pertanyaan);
		
		return "add_pertanyaan";
	}
	
	@GetMapping("/pertanyaan/delete/{id}")
	public String deletePertanyaan(@PathVariable Long id, Model model) {
		
		this.pertanyaanRepository.deleteById(id);
		model.addAttribute("listpertanyaan",pertanyaanRepository.findAll());
		
		
		return "redirect:/pertanyaan/view";
	}

	
}