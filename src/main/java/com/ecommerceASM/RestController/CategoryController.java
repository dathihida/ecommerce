package com.ecommerceASM.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceASM.dao.CategoryDAO;
import com.ecommerceASM.entity.Category;


@CrossOrigin("*")
@RestController
public class CategoryController {
	@Autowired
	CategoryDAO categoryDao;
	
	@GetMapping("/rest/categories")
	public ResponseEntity<List<Category>> getAll(Model model) {
		return ResponseEntity.ok(categoryDao.findAll());
	}
	
	@GetMapping("/rest/categories/{id}")
	public ResponseEntity<Category> getOne(@PathVariable("id") String id) {
		if(!categoryDao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoryDao.findById(id).get());
	}
	
	@PostMapping("/rest/categories")
	public ResponseEntity<Category> post(@RequestBody Category category) {
		if(categoryDao.existsById(category.getId())) {
			return ResponseEntity.badRequest().build();
		}
		categoryDao.save(category);
		return ResponseEntity.ok(category);
	}
	
	@PutMapping("/rest/categories/{id}")
	public ResponseEntity<Category> put(@PathVariable("id") String id, @RequestBody Category category) {
		if(!categoryDao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		categoryDao.save(category);
		return ResponseEntity.ok(category);
	}
	
	@DeleteMapping("/rest/categories/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		if(!categoryDao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		categoryDao.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
