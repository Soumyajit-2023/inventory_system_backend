package com.example.inventory;

import com.example.BaseContractTest;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;

import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@SuppressWarnings("rawtypes")
public class ControllerTest extends BaseContractTest {

	@Test
	public void validate_shouldCreateCustomer() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json")
					.body("{\"name\":\"John Doe\"}");

		// when:
			ResponseOptions response = given().spec(request)
					.post("/customers");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");

		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['id']").matches("-?(\\d*\\.\\d+|\\d+)");
			assertThatJson(parsedJson).field("['name']").isEqualTo("John Doe");
	}

	@Test
	public void validate_shouldCreateInventoryItem() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json")
					.body("{\"name\":\"Widget\",\"quantity\":100}");

		// when:
			ResponseOptions response = given().spec(request)
					.post("/inventory");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");

		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['id']").matches("-?(\\d*\\.\\d+|\\d+)");
			assertThatJson(parsedJson).field("['name']").isEqualTo("Widget");
			assertThatJson(parsedJson).field("['quantity']").isEqualTo(100);
	}

}
