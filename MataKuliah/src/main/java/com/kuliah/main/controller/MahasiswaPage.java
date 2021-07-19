package com.kuliah.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.Mahasiswa;
import com.kuliah.main.repository.MahasiswaRepository;
@Controller
public class MahasiswaPage {
	
	@Autowired
	MahasiswaRepository mahasiswaRepository;
	
	@GetMapping("/mahasiswa/view")
	public String viewIndexMahasiswa(Model model) {
		
		model.addAttribute("listMahasiswa",mahasiswaRepository.findAll());
		model.addAttribute("active",4);
		return "view_mahasiswa";
	}
	
	
	@GetMapping("/mahasiswa/add")
	public String viewAddMahasiswa(Model model) {
		
		// buat penampung data MataKuliah di halaman htmlnya
		model.addAttribute("mahasiswa",new Mahasiswa());
		
		return "add_mahasiswa";
	}
	
	@PostMapping("/mahasiswa/view")
	  public String addMahasiswa(@ModelAttribute Mahasiswa mahasiswa, Model model) {
		
		this.mahasiswaRepository.save(mahasiswa);
		model.addAttribute("listMahasiswa",mahasiswaRepository.findAll());
		
		
		return "redirect:/matakuliah/view";
	}
	
	
	@GetMapping("/mahasiswa/update/{id}")
	public String viewUpdateMahasiswa(@PathVariable Long id, Model model) {
		
		Mahasiswa mahasiswa = mahasiswaRepository.findByIdMahasiswa(id);
		
		model.addAttribute("mahasiswa", mahasiswa);
		
		return "add_mahasiswa";
	}
	
	@GetMapping("/mahasiswa/delete/{id}")
	public String deleteMahasiswa(@PathVariable Long id, Model model) {
		
		this.mahasiswaRepository.deleteById(id);
		model.addAttribute("listMahasiswa",mahasiswaRepository.findAll());
		
		
		return "redirect:/mahasiswa/view";
	}

	
	
}
