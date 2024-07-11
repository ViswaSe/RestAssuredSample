package chaining;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {
	
	public static String sys_Id="";
	public static RequestSpecification reqSpec;
	public static Response response;
	
	@BeforeSuite
	public void startApp() { 
		RestAssured.baseURI = "https://dev256341.service-now.com/api/now/table/problem";
		
		String username = "admin";String password = "NhpEh+NK7s7$"; 
		RestAssured.authentication = RestAssured.basic(username, password);
	}
	
	@BeforeMethod
	public void declareParams() { 
		reqSpec = RestAssured.given().log().all()
					.queryParam("sysparm_fields", "number,sys_id,category,description,short_description,assigned_to")
					.accept(ContentType.JSON)
					.contentType(ContentType.JSON);
	}
}
