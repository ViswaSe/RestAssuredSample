package chaining;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GetAllProblems extends BaseRequest {
	
	@Test
	public void getAllProblems() { 
		response = reqSpec.get();
		
		//print the response and status code
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		//To know the size of the list of problems. 
		int size = response.jsonPath().getList("result.number").size();
		System.out.println("Total problems:" +size);
		
		//Validate the response 
		response = response.then().assertThat()
		.body("result.number", Matchers.hasItem("PRB0000051"))
		.statusCode(200)
		.contentType(ContentType.JSON)
		.extract().response();
	}

}
