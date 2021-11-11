package day1;

import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class GitHubExample {
	@Test(enabled=false, description="Getting all repositaries")
	public void getAllRepo() {
		given()
		  .auth() // Specifying Authentication needed
		  .oauth2("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")
	    .when()
		   .get("https://api.github.com/user/repos")
		 .then()
		   .log()
		   .body()
		   .statusCode(200)
		   .time(Matchers.lessThan(2000L), TimeUnit.MILLISECONDS);
	}

	@Test(enabled=true, description="Adding repository")
	public void addRepository() {
		JSONObject js=new JSONObject();
		js.put("name", "tsl968-reAssured");
		js.put("description", "I am created by RestAssured");
		js.put("homepage", "https://github.com/bokareaish6");
		
		given()
		  .auth() // Specifying Authentication needed
		  .oauth2("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")
		  .header("Content-Type","application/json")
		  .body(js.toJSONString())
	    .when()
		   .post("https://api.github.com/user/repos")
		 .then()
		   .log()
		   .body()
		   .statusCode(201)
		   .time(Matchers.lessThan(5000L), TimeUnit.MILLISECONDS);
	}
}