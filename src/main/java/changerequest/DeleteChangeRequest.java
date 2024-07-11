package changerequest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteChangeRequest {

	@Test
	public void deleteChangeRequest() { 
		RestAssured.baseURI = "https://dev256341.service-now.com/api/now/table/change_request";
		
		String username = "admin";String password = "NhpEh+NK7s7$";
		RestAssured.authentication = RestAssured.basic(username, password);
		
		Response response = RestAssured.given().log().all()
							.pathParam("sys_id", "f4ba142583c20210268720d0deaad3d3")
							.delete("{sys_id}");
		
		System.out.println(response.statusCode());
	}
	
}
