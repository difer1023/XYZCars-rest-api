package com.xyzcars.xyzcarsrestapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InformationRequest {
	@JsonProperty("productId")
	private String productId;
	
	@JsonProperty("customerLastName")
	private String customerLastName;
	
	@JsonProperty("customerQuestions")
	private String customerQuestions;
	
	@JsonProperty("customerName")
	private String customerName;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public String getCustomerQuestions() {
		return customerQuestions;
	}
	public void setCustomerQuestions(String customerQuestions) {
		this.customerQuestions = customerQuestions;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}	
	
}
