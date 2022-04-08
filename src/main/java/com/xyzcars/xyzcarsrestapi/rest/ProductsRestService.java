package com.xyzcars.xyzcarsrestapi.rest;

import com.xyzcars.xyzcarsrestapi.model.Customer;
import com.xyzcars.xyzcarsrestapi.model.Product;
import com.xyzcars.xyzcarsrestapi.model.ProductInformationQuestion;
import com.xyzcars.xyzcarsrestapi.model.ProductInformationRequest;
import com.xyzcars.xyzcarsrestapi.persistence.CustomerRepository;
import com.xyzcars.xyzcarsrestapi.persistence.ProductInformationQuestionRepository;
import com.xyzcars.xyzcarsrestapi.persistence.ProductInformationRequestRepository;
import com.xyzcars.xyzcarsrestapi.persistence.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "ProductsRestService", description = "Catalog api")
public class ProductsRestService {

	Logger logger = LoggerFactory.getLogger(ProductsRestService.class);

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductInformationRequestRepository productInformationRequestRepository;
	@Autowired
	private ProductInformationQuestionRepository productInformationQuestionRepository;

	@Operation(summary = "Products", description = "Get all products", tags = { "CatalogRestService" })
	@GetMapping("/products")
	public @ResponseBody Iterable<Product> getProducts() {
		return productRepository.findAll();
	}

	@Operation(summary = "Product by Id", description = "Get product by it's id", tags = { "CatalogRestService" })
	@GetMapping("/products/{id}")
	public @ResponseBody Product getProductById(
			@Parameter(description = "Product id", required = true, example = "1", in = ParameterIn.PATH) @PathVariable(value = "id") Integer id) {
		return productRepository.findById(id).get();
	}

	@Operation(summary = "Product information request", description = "Save product information request", tags = {
			"CatalogRestService" })
	@PostMapping("/productRequest")
	public @ResponseBody ProductInformationRequest productRequest(@RequestParam Integer productId,
			@RequestParam String customerName, @RequestParam String customerLastName,
			@RequestParam String[] customerQuestions) throws Exception {

		Customer customer = new Customer();
		customer.setName(customerName);
		customer.setLastName(customerLastName);

		customerRepository.save(customer);

		Product product = new Product();
		product.setId(productId);

		ProductInformationRequest productInformationRequest = new ProductInformationRequest();
		productInformationRequest.setCustomer(customer);
		productInformationRequest.setProduct(product);

		productInformationRequest = productInformationRequestRepository.save(productInformationRequest);

		for (int i = 0; i < customerQuestions.length; i++) {
			ProductInformationQuestion productInformationQuestion = new ProductInformationQuestion();
			productInformationQuestion.setProductInformationRequest(productInformationRequest);
			productInformationQuestion.setQuestion(customerQuestions[i]);
			productInformationQuestionRepository.save(productInformationQuestion);
		}

		return productInformationRequest;
	}

	@Operation(summary = "Questions", description = "Get all questions", tags = { "CatalogRestService" })
	@GetMapping("/questions")
	public @ResponseBody Iterable<ProductInformationQuestion> getQuestions() {
		return productInformationQuestionRepository.findAll();
	}
}
