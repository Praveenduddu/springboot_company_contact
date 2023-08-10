package de.zeroco.companycontact.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.zeroco.companycontact.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Map<String, Object> map) {
		return ResponseEntity.ok(companyService.insert(companyService.getObjectFromMap(map)));
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestParam Integer id, @RequestBody Map<String, Object> map) {
		return ResponseEntity.ok(companyService.update(companyService.getObjectFromMap(id, map)));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam Integer id) {
		return ResponseEntity.ok(companyService.delete(id));
	}
	
	@GetMapping("/get")
	public ResponseEntity<Map<String, Object>> get(@RequestParam Integer id) {
		return ResponseEntity.ok(companyService.get(id));
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Map<String, Object>>> list() {
		return ResponseEntity.ok(companyService.list());
	}
}
