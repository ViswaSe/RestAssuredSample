package servicenow;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAllProblemWithOAuth {
	
	@Test
	public void getAllProblem() { 
		RestAssured.baseURI = "https://dev256341.service-now.com/api/now/table/problem";
		RestAssured.authentication = RestAssured.oauth2("QsolWz52tRiJdeqPXQ5cw5TTA8ZMcrP_8NoxcVotXA-jP5ERbKvonSDZwwN54b8lwwctMjaRJUMK8nMr3FMLvw");
		
		Response response = RestAssured.given().log().all()
							.queryParam("sysparm_fields", "number,sys_id,short_description,description,category")
							.get();
		
		response.prettyPrint();
		System.out.println(response.statusCode());
	}

}
