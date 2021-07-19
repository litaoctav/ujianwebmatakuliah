package com.kuliah.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.Dosen;
import com.kuliah.main.repository.DosenRepository;

@Controller
public class DosenPage {
	
	@Autowired
	DosenRepository dosenRepository;
	
	@GetMapping("/dosen/view")
	public String viewIndexdosen(Model model) {
		
		model.addAttribute("listDosen",dosenRepository.findAll());
		model.addAttribute("active",5);
		return "view_dosen";
	}
	
	
	@GetMapping("/dosen/add")
	public String viewAddDosen(Model model) {
		
		// buat penampung data MataKuliah di halaman htmlnya
		model.addAttribute("dosen",new Dosen());
		
		return "add_dosen";
	}
	
	@PostMapping("/dosen/view")
	  public String addDosen(@ModelAttribute Dosen dosen, Model model) {
		
		// buat penampung data MataKuliah di halaman htmlnya
		this.dosenRepository.save(dosen);
		model.addAttribute("listDosen",dosenRepository.findAll());
		
		
		return "redirect:/dosen/view";
	}
	
	
	@GetMapping("/dosen/update/{id}")
	public String viewUpdateDosen(@PathVariable Long id, Model model) {
		
		Dosen dosen = dosenRepository.findByIdDosen(id);
		
		// buat penampung data MataKuliah di halaman htmlnya
		this.dosenRepository.save(dosen);
		model.addAttribute("dosen",dosenRepository.findByIdDosen(id));
		
		return "add_dosen";
	}
	
	@GetMapping("/dosen/delete/{id}")
	public String deleteDosen(@PathVariable Long id, Model model) {
		
		this.dosenRepository.deleteById(id);
		model.addAttribute("listDosen",dosenRepository.findByIdDosen(id));
		
		
		return "redirect:/dosen/view";
	}
	
	

}
