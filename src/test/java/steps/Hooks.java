package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;

public class Hooks {
	
	@Before
	public void initialSetup() { 
		RestAssured.baseURI = "https://dev256341.service-now.com/api/now/table/change_request";
		String username = "admin";String password = "NhpEh+NK7s7$";
		RestAssured.authentication = RestAssured.basic(username, password);
	}
	
	@After
	public void tearDown(Scenario sc) { 
		System.out.println(sc.getName() +": "+ sc.getStatus());
	}
}
