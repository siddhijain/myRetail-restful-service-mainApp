/**
 * This service is used to get and update Product price of products by id.
 * 
 * @author sjain
 * @version 1.0 
 */
package com.target.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.model.ProductPrice;
import com.target.model.ProductPriceRepository;

@Service
public class ProductPriceService {

	@Autowired
	private ProductPriceRepository productPriceRepository;

	public ProductPrice getProductPriceDetailsById(String productId) {

		return productPriceRepository.findDocumentById(productId);
	}

	public void updateProductPriceById(String productId, double value, String currencyCode) {
		ProductPrice priceDetails = productPriceRepository.findDocumentById(productId);
		priceDetails.setValue(value);
		priceDetails.setCurrencyCode(currencyCode);
		productPriceRepository.save(priceDetails);
	}

}
