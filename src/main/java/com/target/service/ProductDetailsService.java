/**
 * This service is used to get and update Product details of products by id.
 * @author sjain
 * @version 1.0 
 */
package com.target.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.target.model.ProductDetails;

@Service
public class ProductDetailsService {

	@Value("${endpoint.url}")
	private String endpointURL;

	public ProductDetails getProductDetailsById(String productId) {
		String url = endpointURL + productId;
		RestTemplate restTemplate = new RestTemplate();
		ProductDetails details = restTemplate.getForObject(url, ProductDetails.class);
		return details;
	}

}
