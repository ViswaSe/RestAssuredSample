package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ChangeRequestDefinitions {
	
	public RequestSpecification reqSpec;
	public Response response;
	public static String sys_id="";
	
	@Given("Add the parameters required")
	public void add_the_parameters_required() {
		reqSpec = RestAssured.given().log().all()
					.contentType(ContentType.JSON)
					.queryParam("sysparm_fields", "number,sys_id,short_description,description,category");
	}
	
	@When("Call the GET API to get all the change request")
	public void call_the_get_api_to_get_all_the_change_request() {
		response = reqSpec.when().get();
	}
	
	@Then("Verify the status code as {int}")
	public void verify_the_status_code_as(int value) {
		response = response.then().assertThat().statusCode(value).extract().response();
	}
	
	@Then("Verify the content type json")
	public void verify_the_content_type_json() {
		response = response.then().assertThat().contentType(ContentType.JSON).extract().response();
	}
	
	@Then("Print the response along with response status code")
	public void print_the_response_along_with_response_status_code() {
		System.out.println(response.statusCode());
		response.prettyPrint();
	}
	
	@Then("Store the resulted sys_id value and print it")
	public void store_the_resulted_sys_id_value() {
		sys_id = response.jsonPath().get("result.sys_id");
		System.out.println(sys_id);
	}
	
	@Given("Add the body param as {string} and {string} for the POST")
	public void add_the_body_param(String short_desc,String category) {
	   reqSpec = reqSpec.body(
			   "{\r\n"
			   + "	\"short_description\": \""+short_desc+"\",\r\n"
			   + "	\"category\" : \""+category+"\",\r\n"
			   + "	\"assigned_to\":\"681b365ec0a80164000fb0b05854a0cd\"\r\n"
			   + "}"
			   );
	}
	
	@When("Call the POST API to create a new change request")
	public void call_the_post_api_to_create_a_new_change_request() {
	    response = reqSpec.when().post();
	}
	
	@Then("Verify the content type as {string}")
	public void verify_the_content_type_as_json(String content_type) {
		if(content_type.equalsIgnoreCase("Json")) { 
			response = response.then().assertThat().contentType(ContentType.JSON).extract().response();
		}
	}
	
	@Given("Add the body param as {string} and {string} and {string} for the PATCH")
	public void add_the_body_param_as_and_and_for_the_patch(String short_desc, String description, String category) {
		reqSpec = reqSpec
				.pathParam("sysID", sys_id)
				.body(""
				+ "{\r\n"
				+ "	\"short_description\": \""+short_desc+"\",\r\n"
				+ "	\"Category\": \""+category+"\",\r\n"
				+ "	\"description\": \""+description+"\"\r\n"
				+ "}\r\n"
				+ "");
	}
	
	@When("Call the PATCH API to update the change request")
	public void call_the_patch_api_to_update_the_change_request() {
		response = reqSpec.when().patch("{sysID}");	
	}
	
	@When("Call the DELETE API to delete the change request")
	public void call_the_delete_api_to_delete_the_change_request() {
	   response = reqSpec
			   .pathParam("sysID",sys_id)
			   .when().delete("{sysID}");
	}
}