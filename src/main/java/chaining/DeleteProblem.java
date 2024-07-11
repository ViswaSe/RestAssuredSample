package chaining;

import org.testng.annotations.Test;

public class DeleteProblem extends BaseRequest {
	
	@Test(dependsOnMethods="chaining.UpdateProblem.updateProblem")
	public void deleteExistingProblem() { 
		response = reqSpec.pathParam("sysID", sys_Id)
		.delete("{sysID}");
	
		//To print the response and status code
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		//validate the response status code
		response = response.then().assertThat().statusCode(204).extract().response();
	}

}
