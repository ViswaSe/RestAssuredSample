package changerequest;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateNewChangeRequest {
	
	@DataProvider(name = "fetchData")
	public String[] setData() { 
		String[] data = new String[2];
		data[0] = "./data/CreateCR1.json";
		data[1] = "./data/CreateCR2.json"; 
		return data;
	}
	
	@Test(dataProvider = "fetchData")
	public void createNewCR(String filePath) { 
		RestAssured.baseURI = "https://dev256341.service-now.com/api/now/table/change_request";
		
		String username = "admin";String password = "NhpEh+NK7s7$";
		RestAssured.authentication = RestAssured.basic(username, password);
	
		RequestSpecification reqSpec = RestAssured.given().log().all()
										.queryParam("sysparm_fields", "sys_id,number,short_description,description,assigned_to,category")
										.contentType(ContentType.JSON)
										.accept(ContentType.JSON)
										.body(new File(filePath));
		
		//get the response 
		Response response = reqSpec.post();
		response.prettyPrint();
		System.out.println(response.statusCode());
	}

}
