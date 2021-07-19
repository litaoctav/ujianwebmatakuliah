package com.kuliah.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.Soal;
import com.kuliah.main.repository.SoalRepository;

@Controller
public class SoalPage {
	
	
	@Autowired
	SoalRepository soalRepository;
	
	@GetMapping("/soal/view")
	public String viewIndexSoal(Model model) {
		
		model.addAttribute("listSoal",soalRepository.findAll());
		model.addAttribute("active",6);
		return "view_soal";
	}
	
	
	@GetMapping("/soal/add")
	public String viewAddSoal(Model model) {
		

		model.addAttribute("soal",new Soal());
		
		return "add_soal";
	}
	
	@PostMapping("/soal/view")
	  public String addSoal(@ModelAttribute Soal soal, Model model) {
		
		this.soalRepository.save(soal);
		model.addAttribute("listMataKuliah",soalRepository.findAll());
		
		
		return "redirect:/soal/view";
	}
	
	
	@GetMapping("/soal/update/{id}")
	public String viewUpdateMataKuliah(@PathVariable Long id, Model model) {
		
		Soal soal = soalRepository.findById(id).orElse(null);
		
		// buat penampung data MataKuliah di halaman htmlnya
		model.addAttribute("soal", soal);
		
		return "add_soal";
	}
	
	@GetMapping("/soal/delete/{id}")
	public String deleteSoal(@PathVariable Long id, Model model) {
		
		this.soalRepository.deleteById(id);
		model.addAttribute("listSoal",soalRepository.findAll());
		
		
		return "redirect:/soal/view";
	}


}
