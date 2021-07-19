package com.kuliah.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.PlotMataKuliah;
import com.kuliah.main.repository.PlotMataKuliahRepository;

@Controller
public class PlotMataKuliahPage {
	
	@Autowired
	PlotMataKuliahRepository plotMataKuliahRepository;
	
	@GetMapping("/plotmatakuliah/view")
	public String viewIndexPlotMataKuliah(Model model) {
		
		model.addAttribute("listPlotMataKuliah",plotMataKuliahRepository.findAll());
		model.addAttribute("active",8);
		return "view_plotmatakuliah";
	}
	
	
	@GetMapping("/plotmatakuliah/add")
	public String viewAddPlotMataKuliah(Model model) {
		

		model.addAttribute("plotmatakuliah",new PlotMataKuliah());
		
		return "add_plotmatakuliah";
	}
	
	@PostMapping("/plotmatakuliah/view")
	  public String addPlotMataKuliah(@ModelAttribute PlotMataKuliah plotMataKuliah, Model model) {
		
		this.plotMataKuliahRepository.save(plotMataKuliah);
		model.addAttribute("listPlotMataKuliah",plotMataKuliahRepository.findAll());
		
		
		return "redirect://plotmatakuliah/view";
	}
	
	
	@GetMapping("/plotmatakuliah/update/{id}")
	public String viewUpdatePlotMataKuliah(@PathVariable Long id, Model model) {
		
		PlotMataKuliah plotMataKuliah = plotMataKuliahRepository.findById(id).orElse(null);
		
		model.addAttribute("plotmatakuliah", plotMataKuliah);
		
		return "add_plotmatakuliah";
	}
	
	@GetMapping("/plotmatakuliahh/delete/{id}")
	public String deletePlotMataKuliah(@PathVariable Long id, Model model) {
		
		this.plotMataKuliahRepository.deleteById(id);
		model.addAttribute("listPlotMataKuliah",plotMataKuliahRepository.findAll());
		
		
		return "redirect:/plotmatakuliah/view";
	}
	
}
