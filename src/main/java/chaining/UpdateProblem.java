package chaining;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UpdateProblem extends BaseRequest {
	
	@DataProvider(name="fetchData") 
	public String[] setData() { 
		String[] data = new String[1];
		data[0] = "./data/UpdateProblem.json";
		return data;
	}
	
	@Test(dataProvider="fetchData",dependsOnMethods={"chaining.CreateNewProblem.createNewProblem"}) 
	public void updateProblem(String filePath)	{ 
		response = reqSpec.pathParam("sysID", sys_Id)
		.body(new File(filePath))
		.patch("{sysID}");
		
		//To print the response and status code
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		//validate the response
		response = response.then().assertThat()
					.statusCode(200)
					.body("result.short_description", Matchers.equalTo("Problem has been updated via RestAssured"))
					.body("result.description", Matchers.containsStringIgnoringCase("Patch"))
					.extract().response();
	}
}
