package servicenow;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetIncidents {
	
	@Test
	public void getIncidentsFromServiceNow() { 
		RestAssured.baseURI = "https://dev256341.service-now.com/api/now/table/incident";
		
		String username = "admin";
		String password = "NhpEh+NK7s7$";
		RestAssured.authentication = RestAssured.basic(username, password);
	
		Response response = RestAssured.given().log().all()
								.queryParam("sysparm_fields", "number,sys_id,short_description,category,description")
								.accept(ContentType.JSON)
								.get();
		
		System.out.println(response.statusCode());
		response.prettyPrint();
	}

}
