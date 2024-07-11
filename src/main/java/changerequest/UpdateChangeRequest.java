package changerequest;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateChangeRequest {
	
	@DataProvider(name="fetchData") 
	public String[] setData() { 
		String[] data = new String[1]; 
		data[0] = "./data/UpdateCR.json";
		return data;
	}
	
	@Test(dataProvider="fetchData")
	public void updateCR(String filePath) { 
		RestAssured.baseURI = "https://dev256341.service-now.com/api/now/table/change_request";
		
		String username = "admin";String password = "NhpEh+NK7s7$";
		RestAssured.authentication = RestAssured.basic(username, password);
		
		RequestSpecification reqSpec = RestAssured.given().log().all()
											.accept(ContentType.JSON)
											.queryParam("sysparm_fields","sys_id,short_description,description,number,category,assigned_to")
											.pathParam("sys_id","f4ba142583c20210268720d0deaad3d3")
											.contentType(ContentType.JSON)
											.body(new File(filePath));
	
		Response response = reqSpec.patch("{sys_id}");
		
		//print the response 
		response.prettyPrint();
		System.out.println(response.statusCode());
	}

}
