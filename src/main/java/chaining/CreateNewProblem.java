package chaining;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class CreateNewProblem extends BaseRequest {

	@DataProvider(name="fetchData")
	public String[] setData() { 
		String[] data = new String[2];
		data[0] = "./data/CreateProblem1.json";
		data[1] = "./data/CreateProblem2.json";
		return data;
	}

	@Test(dataProvider = "fetchData")
	public void createNewProblem(String filePath) { 
		response = reqSpec.body(new File(filePath))
				.post();

		//print the response and status code
		response.prettyPrint();
		System.out.println(response.statusCode());


		//To store the sys_id from the response body
		sys_Id = response.jsonPath().get("result.sys_id");
		System.out.println(sys_Id);
		
		//validate the response
		response = response.then().assertThat()
					.statusCode(201)
					.body("result.short_description", Matchers.equalTo("New Problem Created using Rest Assured API"))
					.body("result.description", Matchers.containsString("RestAssured"))
					.contentType(ContentType.JSON)
					.extract().response();
	}

}
