package com.kthapa.flexionmobile.integration;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kthapa.flexionmobile.constant.FlexionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;
import com.kthapa.flexionmobile.model.PurchaseImpl;

/**
 * Created by Krishna Thapa on 19/12/2018
 */
public class FlexionMobileIntegration implements Integration{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlexionMobileIntegration.class);
	String developerId = FlexionConstants.DEVELOPERID;
	String baseUrl = FlexionConstants.URL;

	/**
	 * Buy Item: buy one item. Items can be bought multiple times
	 * @param itemId, developerId
	 * @return Purchase
	 */
	@Override
	public Purchase buy(String itemId) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = baseUrl + FlexionConstants.BUY_URL;
				//"http://sandbox.flexionmobile.com/javachallenge/rest/developer/{developerId}/buy/{itemId}";
			Map<String, String> urlParam = new HashMap<>();
			urlParam.put("developerId", developerId);
			urlParam.put("itemId", itemId);

		    ResponseEntity<PurchaseImpl> purchaseObj = restTemplate.postForEntity(url, null, PurchaseImpl.class, urlParam);
			HttpStatus status = purchaseObj.getStatusCode();
			
			if(status.is2xxSuccessful()) {
				LOGGER.info("buy: Success on buy request on itemId: {}, with response status: {}", purchaseObj.getBody().getItemId(), status);
				return purchaseObj.getBody();
			}else {
				LOGGER.error("buy: Error on buy request, response status is: {}", status);
			}
		}catch(Exception e) {
			LOGGER.error("buy: Error on calling a buy request: {}", e.getMessage());
		}
		return null;
	}

	/**
	 * Consume purchase: Call this method to consume one purchase.
	 * @param purchase, developerId
	 */
	@Override
	public void consume(Purchase purchase) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = baseUrl + FlexionConstants.CONSUME_URL;
				//"http://sandbox.flexionmobile.com/javachallenge/rest/developer/{developerId}/consume/{purchaseId}";
			Map<String, String> urlParam = new HashMap<>();
			urlParam.put("developerId", developerId);
			urlParam.put("purchaseId", purchase.getId());

		    ResponseEntity<PurchaseImpl> purchaseObj = restTemplate.postForEntity(url, null, PurchaseImpl.class, urlParam);
			HttpStatus status = purchaseObj.getStatusCode();
			
			if(status.is2xxSuccessful()) {
				LOGGER.info("consume: Success on consume request with response status: {}", status);
			}else {
				LOGGER.error("consume: Error on consume request, response status is: {}", status);
			}
		}catch(Exception e) {
			LOGGER.error("consume: Error on calling a consume request: {}", e.getMessage());
		}
	}

	/**
	 * GetPurchases: to retrieve all previous purchases
	 * @param
	 * @return List<Purchase>
	 */
	@Override
	public List<Purchase> getPurchases() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = FlexionConstants.URL+FlexionConstants.GETPURCHASES_URL;
				//"http://sandbox.flexionmobile.com/javachallenge/rest/developer/{developerId}/all";
			Map<String, String> urlParam = new HashMap<>();
			urlParam.put("developerId", developerId);

			ResponseEntity<String> purchaseObj = restTemplate.getForEntity(url, String.class, urlParam);
			HttpStatus status = purchaseObj.getStatusCode();

			if(status.is2xxSuccessful()) {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode actualObj = mapper.readTree(purchaseObj.getBody()).get("purchases");
				List<Purchase> purchaseObjList = mapper.readValue(actualObj.toString(), new TypeReference<List<PurchaseImpl>>(){});

				LOGGER.info("getPurchases: Success on getPruchases request with purchases size: {}, response status is: {}", purchaseObjList.size(), status);
				return purchaseObjList;
			}else {
				LOGGER.error("getPurchases: Error on getPruchases request, response status is: {}", status);
			}
		}catch(IOException e) {
			LOGGER.error("getPurchases: Error on calling a getPruchases request: {}", e.getMessage());
		}
		return Collections.emptyList();
	}
}
