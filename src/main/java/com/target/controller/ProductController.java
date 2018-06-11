/**
 * Product service is a RESTful service used to get product details and price. 
 * @author sjain
 * @version 1.0 
 */
package com.target.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.target.model.ProductDetails;
import com.target.model.ProductPrice;
import com.target.model.ProductPriceResponse;
import com.target.model.RestResponse;
import com.target.service.ProductDetailsService;
import com.target.service.ProductPriceService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	ProductDetailsService productDetailsService;

	@Autowired
	ProductPriceService productPriceService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RestResponse getProductDetailsById(@Valid @PathVariable("id") int productId) throws Exception {
		String id = Integer.toString(productId);
		int threadNum = 2;
		ExecutorService executor = Executors.newFixedThreadPool(threadNum);
		Future<ProductPrice> productPriceTask = executor.submit(new Callable<ProductPrice>() {
			@Override
			public ProductPrice call() {
				return productPriceService.getProductPriceDetailsById(id);
			}
		});

		Future<ProductDetails> productDetailsTask = executor.submit(new Callable<ProductDetails>() {
			@Override
			public ProductDetails call() {
				return productDetailsService.getProductDetailsById(id);
			}
		});

		RestResponse response = new RestResponse();
		response.setId(id);
		try {
			response.setName(productDetailsTask.get().getProductName());
			ProductPriceResponse priceResponse = new ProductPriceResponse();
			priceResponse.setCurrency_code(productPriceTask.get().getCurrencyCode());
			priceResponse.setValue(productPriceTask.get().getValue());
			response.setCurrentPrice(priceResponse);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.shutdown();
		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateProductDetailsById(@Valid @PathVariable("id") int productId, @Valid @RequestBody RestResponse product) {
		productPriceService.updateProductPriceById(Integer.toString(productId), product.getCurrentPrice().getValue(), 
				product.getCurrentPrice().getCurrency_code());
	}
}
