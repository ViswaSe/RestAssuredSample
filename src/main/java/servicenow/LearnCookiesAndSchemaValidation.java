package servicenow;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class LearnCookiesAndSchemaValidation {
	
	@Test
	public void createNewProblem() { 
		RestAssured.baseURI = "https://dev256341.service-now.com/api/now/table/problem";
		
		String username = "admin";
		String password = "NhpEh+NK7s7$";
		
		//RestAssured.authentication = RestAssured.basic(username, password);

		Response response = RestAssured.given().log().all()
		.cookie("JSESSIONID","9ED85339092EE12AFDD7B82B70F3ACE2")
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(new File("./data/CreateProblem1.json"))
		.post();
		
		
		System.out.println(response.statusCode());
		response.prettyPrint();
		Map<String,String> allCookies = response.getCookies();
		
		for(Entry<String,String> eachCookie: allCookies.entrySet()) { 
			System.out.println(eachCookie);
		}
		
		response = response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchema(new File("./data/CreateProblemSchema.json")))
				.extract().response();
	}

}
