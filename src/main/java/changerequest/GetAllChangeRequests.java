package changerequest;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllChangeRequests {
	
	@Test 
	public void getAllChangeRequests() { 
		RestAssured.baseURI = "https://dev256341.service-now.com/api/now/table/change_request";
		
		String username = "admin";String password = "NhpEh+NK7s7$";
		RestAssured.authentication = RestAssured.basic(username, password);

		RequestSpecification reqSpec = RestAssured.given().log().all()
										.queryParam("sysparm_fields", "number,sys_id,short_description,description,assigned_to,category")
										.accept(ContentType.JSON);	
		
		Response response = reqSpec.get();
		//print the status code
		System.out.println(response.statusCode());
		
		//print the response
		response.prettyPrint();
		
		//print the last response
		JsonPath jsonResponse = response.jsonPath();
		List<String> list_Number = jsonResponse.getList("result.number");
		String lastValue = list_Number.get(list_Number.size()-1);
		System.out.println(lastValue);
		
		//to know the change request in the application
		System.out.println(list_Number.size());
	}

}
