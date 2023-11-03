package com.ecommerceASM.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.ecommerceASM.dao.ProductDAO;
import com.ecommerceASM.entity.Product;


@CrossOrigin("*")
@RestController
public class ProductController {
	@Autowired
	ProductDAO productDao;
	
	@GetMapping("/rest/products")
	public ResponseEntity<List<Product>> getAll(Model model) {
		return ResponseEntity.ok(productDao.findAll());
	}
	
	@GetMapping("/rest/products/{id}")
	public Optional<Product> getOne1(@PathVariable("id") Integer id) {
		return productDao.findById(id);
	}

	@PostMapping("/rest/products")
	public ResponseEntity<Product> post(@RequestBody Product product) {
//		if(productDao.existsById(product.getId())) { 
//			return ResponseEntity.badRequest().build(); 
//		}
		productDao.save(product);
		System.out.println("ok");
		return ResponseEntity.ok(product);
	}
	
	@PutMapping("/rest/products/{id}")
	public ResponseEntity<Product> put(@PathVariable("id") Integer id, @RequestBody Product product) {
		if(!productDao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		productDao.save(product);
		return ResponseEntity.ok(product);
	}
	
	@DeleteMapping("/rest/products/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		if(!productDao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		productDao.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	//list product with category
	@GetMapping("/rest/productsbycategory/{id}")
	public ResponseEntity<List<Product>> getCategory(@PathVariable("id") String id) {
		return ResponseEntity.ok(productDao.findByCategoryId(id));
	}
	
	//list product with price min max
	@GetMapping("/rest/productsbycategory/{min}/{max}")
	public ResponseEntity<List<Product>> getPriceMinMax(@PathVariable("min") Double min, @PathVariable("max") Double max) {
		return ResponseEntity.ok(productDao.findProductByPrice(min, max));
	}
}
