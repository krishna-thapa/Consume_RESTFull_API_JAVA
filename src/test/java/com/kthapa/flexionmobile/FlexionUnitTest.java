package com.kthapa.flexionmobile;

import org.junit.Assert;

import org.junit.Test;

import com.flexionmobile.codingchallenge.integration.Purchase;
import com.kthapa.flexionmobile.integration.FlexionMobileIntegration;

public class FlexionUnitTest {

	@Test
	public void testBuyItem(){
		FlexionMobileIntegration flexionIntegration = new FlexionMobileIntegration();
		Purchase purchase = flexionIntegration.buy("item3");
		Assert.assertEquals("item3", purchase.getItemId());
	}
}
