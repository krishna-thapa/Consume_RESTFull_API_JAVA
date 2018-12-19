package com.kthapa.flexionmobile;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;
import com.kthapa.flexionmobile.integration.FlexionMobileIntegration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlexionmobileApplicationTests {

	@Test
	public void contextLoads() {
		Integration integration = new FlexionMobileIntegration();
		IntegrationTestRunner integrationTestRunner = new IntegrationTestRunner();
		integrationTestRunner.runTests(integration);
	}

}

